package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Appointment;
import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.exception.DuplicateEmailException;
import com.clinicmanagement.clinic.repository.AppointmentRepository;
import com.clinicmanagement.clinic.repository.DoctorRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> findAllByStatus(Boolean status) {
        return doctorRepository.findAllByStatus(status);
    }

    public Doctor findById(Integer doctorID) {
        return doctorRepository.findById(doctorID)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor saveDoctor(Doctor doctor) throws DuplicateEmailException {
        try {
            return doctorRepository.save(doctor);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateEmailException("Email already exists: " + doctor.getEmail());
            }
            throw ex; // Ném lại nếu là lỗi khác
        }
    }

    public Doctor updateDoctor(Integer id, Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if (existingDoctor.isPresent()) {
            doctor.setId(id);
            return doctorRepository.save(doctor);
        } else {
            throw new IllegalArgumentException("Doctor not found");
        }
    }

    public Optional<Doctor> findFirstAvailableDoctor(LocalDate date, LocalTime startTime, LocalTime endTime) {
        // Lấy danh sách tất cả bác sĩ đang hoạt động (status = true)
        List<Doctor> allDoctors = doctorRepository.findAllByStatus(true);

        // Duyệt qua từng bác sĩ và kiểm tra lịch trống
        for (Doctor doctor : allDoctors) {
            // Lấy danh sách các cuộc hẹn của bác sĩ vào ngày yêu cầu
            List<Appointment> doctorAppointments = appointmentRepository.findByDoctorAndAppointmentDate(doctor, date);

            // Kiểm tra xem bác sĩ có lịch hẹn nào xung đột không
            boolean isAvailable = doctorAppointments.stream()
                    .noneMatch(appointment ->
                            !(
                                    appointment.getAppointmentTime().isAfter(endTime) || // Sau giờ kết thúc
                                            appointment.getAppointmentTime().plusHours(1).isBefore(startTime) // Trước giờ bắt đầu
                            )
                    );

            if (isAvailable) {
                return Optional.of(doctor);
            }
        }

        // Không tìm thấy bác sĩ nào
        return Optional.empty();
    }
}

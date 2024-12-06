package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Appointment;
import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.repository.AppointmentRepository;
import com.clinicmanagement.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public List<Doctor> findAllByStatus(Boolean status) {
        return doctorRepository.findAllByStatus(status);
    }

    public Doctor findById(Integer doctorID) {
        return doctorRepository.findById(doctorID)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Autowired
    private AppointmentRepository appointmentRepository;

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

    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}

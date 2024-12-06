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
        // Lấy danh sách tất cả các bác sĩ
        List<Doctor> allDoctors = doctorRepository.findAll();

        for (Doctor doctor : allDoctors) {
            // Lấy danh sách các cuộc hẹn của bác sĩ trong ngày cụ thể
            List<Appointment> appointments = appointmentRepository.findByDoctorAndAppointmentDate(doctor, date);

            // Kiểm tra xem bác sĩ có trống trong khoảng thời gian không
            boolean isAvailable = appointments.stream()
                    .noneMatch(appointment ->
                            (startTime.isBefore(appointment.getAppointmentTime().plusHours(1)) &&
                                    endTime.isAfter(appointment.getAppointmentTime()))
                    );

            if (isAvailable) {
                return Optional.of(doctor);
            }
        }

        return Optional.empty(); // Không tìm thấy bác sĩ phù hợp
    }

    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}

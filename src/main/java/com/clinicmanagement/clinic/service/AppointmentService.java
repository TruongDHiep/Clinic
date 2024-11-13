package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Appointment;
import com.clinicmanagement.clinic.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository _appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        _appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllUsers() {
        return _appointmentRepository.findAll();
    }


}

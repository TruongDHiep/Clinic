package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Appointment;
import com.clinicmanagement.clinic.dto.AppointmentDTO;
import com.clinicmanagement.clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class AppointmentController {
    private AppointmentService _appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this._appointmentService = appointmentService;
    }

    @GetMapping("/appointment")
    public List<Appointment> appointments(Model model) {
        return _appointmentService.getAllUsers();
    }
}

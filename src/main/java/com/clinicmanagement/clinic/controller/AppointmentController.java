package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Appointment;
import com.clinicmanagement.clinic.dto.AppointmentDTO;
import com.clinicmanagement.clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/appointments")
@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService _appointmentService;

    @GetMapping
    public List<Appointment> GetAllAppointments() {
        return _appointmentService.GetAllUsers();
    }

    @GetMapping("/{id}")
    public Appointment GetAppointmentById(@PathVariable int id) {
        return _appointmentService.GetAppointmentById(id);
    }

    @GetMapping("/doctor/{id}")
    public List<Appointment> GetAppointmentByDocTorId(@PathVariable int id) {
        return _appointmentService.GetAppointmentByDocTorId(id);
    }

    @PostMapping
    public Appointment CreateAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return _appointmentService.AddAppointment(appointmentDTO);
    }

    @PutMapping
    public Appointment UpdateAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return _appointmentService.UpdateAppointment(appointmentDTO);
    }

    @DeleteMapping("/{id}")
    public String DeleteAppointment(@PathVariable int id) {
        return _appointmentService.DeleteAppointment(id);
    }
}

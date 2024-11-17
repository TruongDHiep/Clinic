package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Appointment;
import com.clinicmanagement.clinic.dto.AppointmentDTO;
import com.clinicmanagement.clinic.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/information/appointment")
@Controller
public class AppointmentController {
    @Autowired
    private AppointmentService _appointmentService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @GetMapping
    public String GetAllAppointments(Model model) {
        try {
            List<Appointment> appointments = _appointmentService.getAllAppointments();
            model.addAttribute("appointments", appointments);
            return "information/appointment/index";
        } catch (Exception e) {
            e.printStackTrace();  // Log exception for debugging
            model.addAttribute("errorMessage", "An error occurred while fetching appointments.");
            return "error";
        }
    }

    @GetMapping("/search")
    public String searchAppointments(@RequestParam String keyword, Model model) {
        List<Appointment> appointments = _appointmentService.searchAppointments(keyword);
        model.addAttribute("appointments", appointments);
        model.addAttribute("keyword", keyword);  // Thêm keyword vào model
        return "information/appointment/index";
    }

    //Viết add controller ở đây nha hiệp

    @PostMapping("/cancel")
    public String cancelAppointment(@RequestParam("id") int id) {
        try {
            _appointmentService.cancelAppointment(id);
            return "redirect:/information/appointment";
        } catch (Exception e) {
            return "error";
        }
    }
}
package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Patient;
import com.clinicmanagement.clinic.Entities.Services;
import com.clinicmanagement.clinic.enums.Time;
import com.clinicmanagement.clinic.service.PatientService;
import com.clinicmanagement.clinic.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private ServicesService _servicesService;
    @Autowired
    private PatientService _patientService;

    @GetMapping("/booking")
    public String booking(Model model) {
    try {
        List<Services> services = _servicesService.getAll();
        model.addAttribute("services", services);

        List<Patient> patient = _patientService.findAll();
        model.addAttribute("patients", patient);

        model.addAttribute("timeSlots", Time.values());


        return "booking/bookingSite";}
    catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "An error occurred while fetching booking.");
        return "error";
    }
    }
}

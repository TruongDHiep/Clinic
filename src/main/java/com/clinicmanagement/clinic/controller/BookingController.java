package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Patient;
import com.clinicmanagement.clinic.Entities.Services;
import com.clinicmanagement.clinic.service.PatientService;
import com.clinicmanagement.clinic.service.ServicesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String booking(Model model) {
        ServicesService servicesService = new ServicesService();
        PatientService patientService = new PatientService();


        List<Services> services = servicesService.getAll();
        model.addAttribute("services", services);

        Patient patient = patientService.g;


        return "booking/bookingSite";
    }
}

package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.dto.DoctorDTO;
import com.clinicmanagement.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/doctors")
    public String getDoctors(Model model){
        model.addAttribute("doctors",doctorService.findAll());
        return "doctor/doctors";
    }
}

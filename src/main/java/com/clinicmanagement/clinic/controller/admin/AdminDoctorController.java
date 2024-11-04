package com.clinicmanagement.clinic.controller.admin;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminDoctorController {
    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/doctors")
    public List<Doctor> getDoctors(){
//        model.addAttribute("doctors",doctorService.findAll());
//        return "doctor/doctors";
        return doctorService.findAll();
    }
}

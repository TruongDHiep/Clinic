package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.dto.doctor.DoctorCreateRequest;
import com.clinicmanagement.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public String showAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.findAllByStatus(true));
        return "doctor/doctors";
    }

    @GetMapping("/doctors/{id}")
    public String showDoctorById(@PathVariable("id") Integer id, Model model) {
        try {
            var doctor = doctorService.findById(id);
            model.addAttribute("doctors", doctor);
            return "/doctor/doctors";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không tìm thấy bác sĩ với ID: " + id);
            return "/doctor/doctors";
        }
    }
    @GetMapping("/doctors/newDoctor")
    public String newDoctor(){
            return "doctor/newDoctor";
    }

    @PostMapping("/doctors/newDoctor")
    public String addDoctor(Doctor doctor) {
        try {
            doctorService.createDoctor(doctor);
            return "doctor/newDoctor";
        } catch (Exception e) {
            return "redirect:/doctor";
        }
    }
}

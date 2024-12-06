package com.clinicmanagement.clinic.controller.admin;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public String doctor(Model model){
        model.addAttribute("doctors", doctorService.findAllByStatus(true));
        return "/admin/doctor/doctors";
    }

    @GetMapping("/doctors/{id}")
    public String showDoctorById(@PathVariable("id") Integer id, Model model) {
        try {
            var doctor = doctorService.findById(id);
            model.addAttribute("doctors", doctor);
            return "admin/doctor/doctors";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không tìm thấy bác sĩ với ID: " + id);
            return "admin/doctor/doctors";
        }
    }
    @GetMapping("/admin/doctors/newDoctor")
    public String newDoctor(){
        return "admin/doctor/newDoctor";
    }

    @PostMapping("/admin/doctors/newDoctor")
    public String addDoctor(Doctor doctor) {
        try {
            doctorService.createDoctor(doctor);
            return "admin/doctor/newDoctor";
        } catch (Exception e) {
            return "redirect:admin/doctor";
        }
    }
}

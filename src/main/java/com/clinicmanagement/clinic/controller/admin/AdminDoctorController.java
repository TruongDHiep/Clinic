package com.clinicmanagement.clinic.controller.admin;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Specialization;
import com.clinicmanagement.clinic.dto.doctor.DoctorCreateRequest;
import com.clinicmanagement.clinic.mapper.DoctorMapper;
import com.clinicmanagement.clinic.service.DoctorService;
import com.clinicmanagement.clinic.service.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminDoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping("/doctors")
    public String showAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.findAllByStatus(true));
        return "admin/doctor/doctors";
    }

    @GetMapping("/doctors/create")
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new DoctorCreateRequest());
        model.addAttribute("specializations", specializationService.getAllSpecialization());
        return "admin/doctor/create"; // Trang form tạo bác sĩ
    }

    @PostMapping("/create")
    public String createDoctor(@Valid @ModelAttribute("doctor") DoctorCreateRequest doctorCreateRequest,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            List<Specialization> specializations = specializationService.getAllSpecialization();
            model.addAttribute("specializations", specializations);
            return "admin/doctor/create";
        }
        doctorService.saveDoctor(doctorMapper.toDoctor(doctorCreateRequest));
        return "redirect:/admin/doctors";
    }
}

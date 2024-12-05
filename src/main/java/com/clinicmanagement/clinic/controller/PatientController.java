package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public String showAllPatients(Model model) {
        model.addAttribute("patients", patientService.findAll());
        return "/patient/patients";
    }

    @GetMapping("/patients/{id}")
    public String showPatientById(@PathVariable("id") Integer id, Model model) {
        var patient = patientService.findById(id);

        if (patient == null || patient.getId() == null) { // Kiểm tra đối tượng rỗng
            model.addAttribute("errorMessage", "Không tìm thấy bệnh nhân với ID: " + id);
            return "/patient/patients"; // Chuyển hướng đến trang lỗi
        }

        model.addAttribute("patients", patient);
        return "/patient/patients";
    }



}
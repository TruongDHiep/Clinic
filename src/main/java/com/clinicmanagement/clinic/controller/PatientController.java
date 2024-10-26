package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.dto.PatientDTO;
import com.clinicmanagement.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public String getPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patient/patients";
    }

    @GetMapping("/patients/new")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new PatientDTO());
        return "patient/add-patient";
    }

    @PostMapping("/patients")
    public String addPatient(@ModelAttribute("patient") PatientDTO patientDTO) {
        patientService.addPatient(patientDTO);
        return "redirect:patient/patients";
    }
}
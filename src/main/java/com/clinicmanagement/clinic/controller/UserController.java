package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Patient;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.mapper.UserMapper;
import com.clinicmanagement.clinic.service.DoctorService;
import com.clinicmanagement.clinic.service.PatientService;
import com.clinicmanagement.clinic.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Locale;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserRequest userRequest) {
        try {
            LocalDate dateDefault = LocalDate.of(1999, 1, 1);
            Patient patient = Patient.builder()
                    .fullName(userRequest.getFullname())
                    .email(null)
                    .phone(null)
                    .address("")
                    .dob(dateDefault)
                    .status(true)
                    .build();
            patientService.createPatient(patient);
            userRequest.setPatient(patient);
            userService.createUser(userRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return "login/register";
        }
        return "redirect:/login";
    }


    @GetMapping("/myInfo")
    public String myinfo(){
        return "test/getuser";
    }
}

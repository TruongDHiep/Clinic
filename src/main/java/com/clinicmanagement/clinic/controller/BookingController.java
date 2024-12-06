package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.*;
import com.clinicmanagement.clinic.enums.Time;
import com.clinicmanagement.clinic.exception.AppException;
import com.clinicmanagement.clinic.exception.ErrorCode;
import com.clinicmanagement.clinic.repository.UserRepository;
import com.clinicmanagement.clinic.service.AppointmentService;
import com.clinicmanagement.clinic.service.PatientService;
import com.clinicmanagement.clinic.service.PaymentService;
import com.clinicmanagement.clinic.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {
    @Autowired
    private ServicesService _servicesService;
    @Autowired
    private PatientService _patientService;

    @Autowired
    private AppointmentService _appointmentService;

    @Autowired
    private PaymentService _paymentService;

    @Autowired
    private UserRepository userRepo;


    @GetMapping("/booking")
    public String booking(Model model) {
    try {

        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        System.out.println("ten: "+name);
        Useracount user = userRepo.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        System.out.println("username" +user.getPassword() + "-------iduser: " +user.getPatient());



        List<Services> services = _servicesService.getAll();
        model.addAttribute("services", services);
        System.out.println("dichvu" + services);

        List<Patient> patient = _patientService.findAll();
        System.out.println("patient" + patient);
        model.addAttribute("patients", patient);
        model.addAttribute("user", user.getPatient());

        model.addAttribute("timeSlots", Time.values());

        return "booking/bookingSite";}

    catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "An error occurred while fetching booking.");
        return "error";
    }
    }


}

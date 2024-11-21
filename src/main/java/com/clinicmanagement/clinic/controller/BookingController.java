package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Services;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String booking(Model model) {
        List<Services> services;

        return "booking/bookingSite";
    }
}

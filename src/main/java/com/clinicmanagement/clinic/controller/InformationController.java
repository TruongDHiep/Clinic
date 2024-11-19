package com.clinicmanagement.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class InformationController {

    @GetMapping("/info")
    public String home() {
        return "information/schedule/index";  // Trả về trang "layout.html"
    }
}

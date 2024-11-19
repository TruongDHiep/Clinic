package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")

    public String home() {
        return "home/index";
    }

    @GetMapping("/login")
    public  String login(Model model){
        return "login/login";
    }

    @GetMapping("/register")
    public String signup(){
        return "login/register";
    }

}

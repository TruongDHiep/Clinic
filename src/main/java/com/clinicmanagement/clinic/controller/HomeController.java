package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "/home/index";
    }

    @GetMapping("/login")
    public  String login(@RequestParam(value = "error", required = false) String error, Model model){
        if (error != null) {
            model.addAttribute("error", "Invalid username or password. Please try again.");
        }
        return "login/login";
    }

    @GetMapping("/register")
    public String signup(Model model){
        UserRequest userRequest = new UserRequest();
        model.addAttribute("user",userRequest);
        return "login/register";
    }

}

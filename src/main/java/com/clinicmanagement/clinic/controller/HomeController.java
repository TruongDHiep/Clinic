package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "home/index";  // Trả về trang "layout.html"
    }

    @GetMapping("/login")
    public  String login(Model model){
        model.addAttribute("userRequest", new UserRequest());
        return "login/login";
    }

    @GetMapping("/register")
    public String signup(){
        return "login/register";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute
                        UserRequest user, Model model) {
        return "/patient/patients";
    }
}

package com.clinicmanagement.clinic.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public String adminHome(){
        return "/admin/index";
    }

    @GetMapping("/test")
    public String test(){
        return "/admin/test";
    }

}

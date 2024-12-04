package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.mapper.UserMapper;
import com.clinicmanagement.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

//    @PostMapping("/login")
//    public String


    @GetMapping("/myInfo")
    public String myinfo(){
        return "test/getuser";
    }
}

package com.clinicmanagement.clinic.controller.admin;

import com.clinicmanagement.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/accounts")
    public String getAll(Model model){
        model.addAttribute("Useraccount",userService.getAllUsers());
        return "/admin/account/accounts";
    }


}

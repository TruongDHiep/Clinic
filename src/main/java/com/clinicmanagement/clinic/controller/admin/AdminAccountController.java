package com.clinicmanagement.clinic.controller.admin;


import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.dto.user.UserResponse;
import com.clinicmanagement.clinic.service.DoctorService;
import com.clinicmanagement.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/accounts/new")
    public String addAccount(Model model){
        List<UserResponse> users = userService.getAllUsers();
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<Doctor> availableDoctors = doctors.stream()
                .filter(doctor -> users.stream().noneMatch(user -> user.getDoctor() != null && user.getDoctor().equals(doctor)))
                .collect(Collectors.toList());
        model.addAttribute("doctors",availableDoctors);
        return "/admin/account/addAccount";
    }

    @GetMapping("/accounts")
    public String getAllUserAccounts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<UserResponse> userAccountsPage = userService.getAllUsers(PageRequest.of(page - 1, size));
        model.addAttribute("useraccount", userAccountsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userAccountsPage.getTotalPages());
        return "/admin/account/accounts"; // Tên view Thymeleaf
    }



}

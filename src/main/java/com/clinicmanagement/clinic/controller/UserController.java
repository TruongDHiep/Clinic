package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.user.ApiResponse;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.dto.user.UserUpdateRequest;
import com.clinicmanagement.clinic.mapper.UserMapper;
import com.clinicmanagement.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public List<Useracount> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Useracount getById(@PathVariable("id") int id){
        return  userService.getByID(id);
    }

//    @PostMapping("/users")
//    public ApiResponse<Useracount> createUser (@RequestBody UserRequest userRequest){
//        ApiResponse<Useracount> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(userService.createUser(userRequest));
//        return apiResponse;
//    }

    @PostMapping("/users")
    public Useracount createUser(@RequestBody UserRequest userRequest){
       return userService.createUser(userRequest);
    }
//    }

//    @PostMapping("/register")
//    public Useracount createUser(@RequestBody UserRequest request){
//       return userService.registerUser(request);
//    }
//
//    @PutMapping("/users/{userID}")
//    @PreAuthorize("hasRole('Admin')")
//    public Useracount updateUser(@PathVariable Integer userID,@RequestBody UserUpdateRequest userRequest){
//        return userService.updateUser(userID,userRequest);
//    }
}

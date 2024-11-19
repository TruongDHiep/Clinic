package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.ApiResponse;
import com.clinicmanagement.clinic.dto.user.UserReponse;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.dto.user.UserUpdateRequest;
import com.clinicmanagement.clinic.mapper.UserMapper;
import com.clinicmanagement.clinic.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
//
//    @GetMapping("/users")
//    public List<UserReponse> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/myInfo")
//    public ApiResponse<UserReponse> getInfo(){
//        ApiResponse<UserReponse> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(userService.getUserInfo());
//        return apiResponse;
//    }
//
//    @GetMapping("/users/id/{id}")
//    public UserReponse getById(@PathVariable("id") int id){
//        return  userService.getByID(id);
//    }
//
//    @GetMapping("/users/name/{name}")
//    public Useracount getByName(@PathVariable("name") String name){
//        return userService.getByName(name);
//    }
//
//    @PostMapping("/users/register")
//    public ApiResponse<Useracount> createUser(@RequestBody @Valid UserRequest userRequest){
//        ApiResponse<Useracount> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(userService.createUser(userRequest));
//        return apiResponse;
//    }
//
//    @PutMapping("/users/{userID}")
//    public UserReponse updateUser(@PathVariable Integer userID,@RequestBody UserUpdateRequest userRequest){
//        return userService.updateUser(userID,userRequest);
//    }

}

package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Role;
import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.ChangePasswordDTO;
import com.clinicmanagement.clinic.dto.user.UserReponse;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.exception.AppException;
import com.clinicmanagement.clinic.exception.ErrorCode;
import com.clinicmanagement.clinic.mapper.UserMapper;
import com.clinicmanagement.clinic.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class UserService{
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserReponse> getAllUsers() {
        log.info("In method get users");
        return userRepo.findAll().stream().map(userMapper::toUserReponse).toList();
    }

    public UserReponse getByID(int id){
        return userMapper.toUserReponse(userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

//    public UserReponse getUserInfo(){
//        var context = SecurityContextHolder.getContext();
//        String name = context.getAuthentication().getName();
//        System.out.println(name);
//        Useracount user = userRepo.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        return userMapper.toUserReponse(user);
//    }

//    public Useracount createUser(UserRequest userRequest) {
//        if(userRepo.existsByUsername(userRequest.getUsername()))
//            throw new AppException(ErrorCode.USER_EXISTED);
//        Useracount user = userMapper.toUser(userRequest);
//        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//
//        //Set role user khi dang ki thanh cong
//        HashSet<Role> role = new HashSet<>();
////        if(userRequest.getRole().str)
//        user.setRoles(role);
//
//        return userRepo.save(user);
//    }

//    public UserReponse updateUser(Integer id, UserUpdateRequest userUpdateRequest){
//        Useracount user = userRepo.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        userMapper.updateUser(userUpdateRequest,user);
//        return userMapper.toUserReponse(userRepo.save(user));
//    }
//
    public Useracount getByName(String username) {
        Optional<Useracount> optionalUser = userRepo.findByUsername(username);
        return optionalUser.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }


    //================================TOANLD===============================
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        Useracount user = userRepo.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        System.out.println("Current Password from DTO: " + changePasswordDTO.getCurrentPassword());
        System.out.println("Current Password: " + user.getPassword());

        //Kiểm tra mật khẩu hiện tại
        if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
            System.out.println("New Password: " + changePasswordDTO.getNewPassword());
        }

        // Kiểm tra độ dài mật khẩu
        if (changePasswordDTO.getNewPassword().length() < 8) {
            System.out.println("Mat khau co do dai nho hon 8 ky tu");
        }


        String encodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());

        user.setPassword(encodedPassword);
        userRepo.save(user);

    }







}

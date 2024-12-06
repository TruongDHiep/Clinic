package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Role;
import com.clinicmanagement.clinic.Entities.UserRole;
import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.user.UserReponse;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.enums.Roles;
import com.clinicmanagement.clinic.exception.AppException;
import com.clinicmanagement.clinic.exception.ErrorCode;
import com.clinicmanagement.clinic.mapper.UserMapper;
import com.clinicmanagement.clinic.repository.RoleRepository;
import com.clinicmanagement.clinic.repository.UserRepository;
import com.clinicmanagement.clinic.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

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

    public Useracount createUser(UserRequest userRequest) {
        if (userRepo.existsByUsername(userRequest.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        Useracount user = userMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        try {
            user = userRepo.save(user);
            Role role = roleRepository.findByRoleName(Roles.USER.name());
            UserRole userRole = userRoleRepository.save(UserRole.builder()
                    .user(user)
                    .role(role)
                    .build());
            return userRepo.save(user);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Useracount getByEmail(String email) {
        try{
            return userRepo.findByPatient_Email(email);
        } catch (Exception ex){
            throw new IllegalArgumentException("Could not find any customer with the email " + email);
        }
    }


    public Useracount getByName(String username) {
        Optional<Useracount> optionalUser = userRepo.findByUsername(username);
        return optionalUser.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }


    public void updateResetPasswordToken(Useracount useracount, String token) {
        useracount.setResetPasswordToken(token);
        userRepo.save(useracount);
    }

    public void updatePassword(Useracount customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);

        customer.setResetPasswordToken(null);
        userRepo.save(customer);
    }

    public Optional<Useracount> getByResetPasswordToken(String token) {
        return userRepo.findByResetPasswordToken(token);
    }
}

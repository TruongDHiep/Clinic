package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.dto.user.UserRequest;
import com.clinicmanagement.clinic.mapper.UserMapper;
import com.clinicmanagement.clinic.repository.PatientRepository;
import com.clinicmanagement.clinic.repository.UserRopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRopsitory userRepo;

    @Autowired
    private PatientRepository patientRepository;


    @Autowired
    private UserMapper userMapper;

    public boolean login(UserRequest user){
        Useracount userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null && userFromDB.getPassword().equals(user.getPassword())) {

            return true;
        } else {
            return false;
        }
    }

//    public void save(Useracount user){
//        userRepo.save(user);
//    }
//
//    public  void delete(Integer id){
//        userRepo.deleteById(id);
//    }
    public List<Useracount> getAllUsers() {
        return userRepo.findAll();
    }

    public Useracount getByID(int id){
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public Useracount createUser(UserRequest userRequest) {
        Useracount user = userMapper.toUser(userRequest);
        return userRepo.save(user);
    }
//
//    public Useracount updateUser(Integer id, UserUpdateRequest userUpdateRequest){
//        Role role = roleRepo.findById(userUpdateRequest.getRoleId())
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//        Patient patient = patientRepository.findById(userUpdateRequest.getPatientId())
//                .orElseThrow(() -> new RuntimeException("Patient not found"));
//        Useracount user = getById(id);
//        user.setPassword(userUpdateRequest.getPassword());
//        user.setRole(role);
//        user.setPatient(patient);
//        return userRepo.save(user);
//    }
//    public Useracount findByName(String username){
//        return userRepo.findByUserName(username);
//    }


}

package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Role;
import com.clinicmanagement.clinic.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findBynName(String name){
        return roleRepository.findByRoleName(name);
    }
}

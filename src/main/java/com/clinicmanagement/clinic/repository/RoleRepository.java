package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.Role;
import com.clinicmanagement.clinic.Entities.Useracount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String roleName);
}

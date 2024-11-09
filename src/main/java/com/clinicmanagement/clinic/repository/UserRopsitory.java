package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.Useracount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRopsitory extends JpaRepository<Useracount, Integer> {
    boolean existsByUsername(String username);
    Optional<Useracount> findByUsername(String username);
}

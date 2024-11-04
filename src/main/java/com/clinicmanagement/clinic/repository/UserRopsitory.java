package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.Useracount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRopsitory extends JpaRepository<Useracount, Integer> {
    Useracount findByUsername(String username);
}

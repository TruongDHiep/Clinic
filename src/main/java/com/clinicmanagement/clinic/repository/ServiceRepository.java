package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Services,Integer> {
    Optional<Services> findByserviceName(String nameServices);
}

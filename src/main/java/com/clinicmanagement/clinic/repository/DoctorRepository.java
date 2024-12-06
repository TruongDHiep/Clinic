package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findAllByStatus(Boolean status);
    Doctor findByEmail(String email);
}


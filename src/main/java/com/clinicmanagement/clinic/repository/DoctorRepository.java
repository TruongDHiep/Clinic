package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}

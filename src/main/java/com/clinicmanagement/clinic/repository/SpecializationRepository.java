package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

}

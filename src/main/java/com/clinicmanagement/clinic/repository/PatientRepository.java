package com.clinicmanagement.clinic.repository;
import com.clinicmanagement.clinic.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
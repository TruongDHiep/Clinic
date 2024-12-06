package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Patient;
import com.clinicmanagement.clinic.dto.PatientDTO;
import com.clinicmanagement.clinic.repository.PatientRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    public List<Patient> findAllByStatus(Boolean status) {
        return patientRepository.findAllByStatus(status);
    }

    public Patient findById(Integer patientID) {
        return patientRepository.findById(patientID)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}

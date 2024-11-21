package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Patient;
import com.clinicmanagement.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }
}

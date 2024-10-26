package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.dto.PatientDTO;
import com.clinicmanagement.clinic.Entities.Patient;
import com.clinicmanagement.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll(); // Lấy tất cả bệnh nhân từ database

        return patients.stream()
                .map(patient -> new PatientDTO(
                        patient.getId(),
                        patient.getFullName(),
                        patient.getDob(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getPhone()))
                .collect(Collectors.toList());
    }

    private PatientDTO convertToDTO(Patient patient) {
        return new PatientDTO(
                patient.getId(),
                patient.getFullName(),
                patient.getDob(),
                patient.getAddress(),
                patient.getEmail(),
                patient.getPhone()
        );
    }

    public void addPatient(PatientDTO patientDTO) {
        Patient patient = new Patient(
                null,
                patientDTO.getFullName(),
                patientDTO.getDob(),
                patientDTO.getAddress(),
                patientDTO.getEmail(),
                patientDTO.getPhone()
        );
        patientRepository.save(patient);
    }
}

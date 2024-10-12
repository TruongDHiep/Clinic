package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.dto.PatientDTO;
import com.clinicmanagement.clinic.model.Patient;
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
        return patientRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
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
                patientDTO.getFullName(),
                patientDTO.getDob(),
                patientDTO.getAddress(),
                patientDTO.getEmail(),
                patientDTO.getPhone()
        );
        patientRepository.save(patient);
    }
}

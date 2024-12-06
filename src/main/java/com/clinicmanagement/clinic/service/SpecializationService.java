package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Specialization;
import com.clinicmanagement.clinic.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {
    @Autowired
    private SpecializationRepository specializationRepository;

    public List<Specialization> getAllSpecialization() {
        return specializationRepository.findAll();
    }
}

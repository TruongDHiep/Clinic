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

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public Specialization findByNameAndNotId(String name, Integer id) {
        return specializationRepository.findByNameAndNotId(name, id);
    }

    public Specialization findByName(String name) {
        return specializationRepository.findByName(name);
    }

    public Specialization findById(Integer id) {
        return specializationRepository.findById(id).orElse(null);
    }
    public Specialization saveSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization);
    }
}

package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.repository.DoctorRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> findAllByStatus(Boolean status) {
        return doctorRepository.findAllByStatus(status);
    }

    public Doctor findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    public Doctor saveDoctor(Doctor doctor) {
            return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Integer id, Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if (existingDoctor.isPresent()) {
            doctor.setId(id);
            return doctorRepository.save(doctor);
        } else {
            throw new IllegalArgumentException("Doctor not found");
        }
    }
}

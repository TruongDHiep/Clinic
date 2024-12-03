package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public List<Doctor> findAllByStatus(Boolean status) {
        return doctorRepository.findAllByStatus(status);
    }

    public Doctor findById(Integer doctorID) {
        return doctorRepository.findById(doctorID)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}

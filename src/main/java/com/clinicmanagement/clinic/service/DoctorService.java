package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Doctor> findByEmailAndNotId(String email, Integer id) {
        return doctorRepository.findByEmailAndNotId(email, id);
    }

    public Optional<Doctor> findById(Integer id) {
        return doctorRepository.findById(id);
    }

    public Doctor findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    public Doctor saveDoctor(Doctor doctor) {
            return doctorRepository.save(doctor);
    }

}

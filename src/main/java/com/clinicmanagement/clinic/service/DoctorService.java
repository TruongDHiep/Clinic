package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.dto.DoctorDTO;
import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public List<DoctorDTO> findAll() {
        List<Doctor> doctors = doctorRepository.findAll(); // Lấy tất cả bệnh nhân từ database

        return doctors.stream()
                .map(docTors -> new DoctorDTO(
                        docTors.getId(),
                        docTors.getFullName(),
                        docTors.getSpecialization(),
                        docTors.getEmail(),
                        docTors.getPhone()))
                .collect(Collectors.toList());
    }

}

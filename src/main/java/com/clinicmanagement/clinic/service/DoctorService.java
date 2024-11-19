package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.dto.DoctorDTO;
import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.mapper.DoctorMapper;
import com.clinicmanagement.clinic.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor getByID(int id){
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
    public Doctor update(int id,DoctorDTO docres){
        Doctor doctor = getByID(id);
        doctorMapper.updateDoctorFromDto(docres,doctor);
        return doctorRepository.save(doctor);
    }
}

package com.clinicmanagement.clinic.mapper;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.dto.DoctorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor toDoctor(DoctorDTO doctorDTO);
    void updateDoctorFromDto(DoctorDTO doctorDTO, @MappingTarget Doctor doctor);
}

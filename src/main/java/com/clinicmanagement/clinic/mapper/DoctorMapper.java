package com.clinicmanagement.clinic.mapper;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.dto.doctor.DoctorCreateRequest;
import com.clinicmanagement.clinic.dto.doctor.DoctorUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor toDoctor(DoctorCreateRequest request);
    void updateDoctor(@MappingTarget Doctor doctor, DoctorUpdateRequest request);
}

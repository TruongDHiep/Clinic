package com.clinicmanagement.clinic.mapper;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Specialization;
import com.clinicmanagement.clinic.dto.doctor.DoctorCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "specialization_id", expression = "java(mapSpecialization(doctorCreateRequest.getSpecialization_id()))")
    Doctor toDoctor(DoctorCreateRequest doctorCreateRequest);

    default Specialization mapSpecialization(Integer specializationId) {
        if (specializationId == null) {
            return null;
        }
        Specialization specialization = new Specialization();
        specialization.setId(specializationId);
        return specialization;
    }
}

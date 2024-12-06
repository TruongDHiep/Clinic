package com.clinicmanagement.clinic.mapper;

import com.clinicmanagement.clinic.Entities.Services;
import com.clinicmanagement.clinic.dto.ServiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    Services toServices(ServiceDTO serviceDTO);
    void updateServices(ServiceDTO serviceDTO, @MappingTarget Services services);
}

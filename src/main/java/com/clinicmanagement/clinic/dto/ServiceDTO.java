package com.clinicmanagement.clinic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDTO {
    private Integer id;
    private String description;
    private Double price;
    private String service_name;
}

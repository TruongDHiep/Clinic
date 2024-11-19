package com.clinicmanagement.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private Integer id;
    private String fullName;
    private String specialization;
    private String phone;
    private String email;
    private boolean status = true;


}

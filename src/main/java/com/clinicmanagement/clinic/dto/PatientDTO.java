package com.clinicmanagement.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {

    private int id;
    private String fullName;
    private LocalDate dob;
    private String address;
    private String email;
    private String phone;
    private boolean status = true;
}



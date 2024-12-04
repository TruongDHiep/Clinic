package com.clinicmanagement.clinic.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class PatientCreateRequest {
    String fullName;
    LocalDate dob;
    String address;
    String phone;
    String email;
    boolean status = true;
}

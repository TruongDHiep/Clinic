package com.clinicmanagement.clinic.dto.user;


import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Patient;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.logging.Level;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserReponse {
    int id;
    String username;
    Doctor doctor;
    Patient patient;
    Set<String> role;
    boolean status;
}

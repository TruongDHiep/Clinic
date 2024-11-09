package com.clinicmanagement.clinic.dto.user;
import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Patient;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
        @Size(min = 4,message = "USERNAME_INVALID")
        String username;

        @Size(min = 8,message = "PASS_INVALID")
         String password;
         Doctor doctor;
         Patient patient;
         boolean role;
}

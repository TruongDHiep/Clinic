package com.clinicmanagement.clinic.dto.user;
import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Patient;
import com.clinicmanagement.clinic.Entities.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    String password;

    @NotBlank(message = "Full name is mandatory")
    @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
    String fullname;

    @NotNull(message = "Doctor information cannot be null")
    Doctor doctor;

    Patient patient;

    Set<UserRole> role;

    boolean status = true;
}

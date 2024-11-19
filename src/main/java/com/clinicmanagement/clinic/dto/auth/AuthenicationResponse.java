package com.clinicmanagement.clinic.dto.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenicationResponse {
    String token;
    String username;
    boolean authenicated;

}

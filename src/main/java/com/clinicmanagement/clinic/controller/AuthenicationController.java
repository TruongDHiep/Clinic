package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.dto.auth.AuthenicationRequest;
import com.clinicmanagement.clinic.dto.auth.AuthenicationResponse;
import com.clinicmanagement.clinic.dto.ApiResponse;
import com.clinicmanagement.clinic.service.AuthenicationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthenicationController {

//    AuthenicationService authenicationService;
//
//    @PostMapping("/login")
//    ApiResponse<AuthenicationResponse> authenicate(@RequestBody AuthenicationRequest request) {
//        var result = authenicationService.authenicate(request);
//        return ApiResponse.<AuthenicationResponse>builder()
//                .result(result)
//                .build();
//    }

}

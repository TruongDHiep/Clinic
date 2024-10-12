package com.clinicmanagement.clinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/patients/**").permitAll() // Cho phép truy cập không cần đăng nhập vào /patients
                .anyRequest().permitAll() // Tạm thời cho phép tất cả các yêu cầu khác
                .and();
//                .csrf().disable(); // Tắt CSRF bảo vệ
        return http.build();
    }
}


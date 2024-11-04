package com.clinicmanagement.clinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
//
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINT = {"/api/**","api/users/**", "/api/users", "/patients/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}


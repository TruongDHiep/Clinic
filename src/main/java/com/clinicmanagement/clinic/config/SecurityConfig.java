package com.clinicmanagement.clinic.config;

import com.clinicmanagement.clinic.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.security.web.SecurityFilterChain;

//
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private final String[] PUBLIC_ENDPOINT = {"verifytoken","/resetPass","/forgotpassword","/","/login","/js/**","/images/**","/css/**", "/fonts/**","/register"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
              request.requestMatchers(PUBLIC_ENDPOINT).permitAll()
                      .requestMatchers("/admin/**").hasAuthority("ADMIN")
                      .requestMatchers("/**").hasAnyAuthority("DOCTOR","USER")
                      .requestMatchers("/doctor/**").hasAuthority("DOCTOR")
                      .requestMatchers("/myInfo").authenticated()
                      .anyRequest().authenticated()
      )
                .formLogin(form ->
                        form
                                .loginPage("/login").loginProcessingUrl("/login")
                                .usernameParameter("username").passwordParameter("password")
                                .successHandler((request, response, authentication) -> {
                                    String role = authentication.getAuthorities().stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .findFirst()
                                            .orElse("");
                                    switch (role) {
                                        case "ADMIN":
                                            response.sendRedirect("/admin");
                                            break;
                                        case "USER":
                                            response.sendRedirect("/");
                                            break;
                                        case "DOCTOR":
                                            response.sendRedirect("/doctor");
                                            break;
                                        default:
                                            response.sendRedirect("/login?error=true");
                                    }
                                })
                                .failureUrl("/login?error=true")

                                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
        ;
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}


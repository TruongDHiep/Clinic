package com.clinicmanagement.clinic.config;

import com.clinicmanagement.clinic.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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

    private final String[] PUBLIC_ENDPOINT = {"/api/users/register","/login","/","/patients","/auth/login",
            "/booking/**", "/vnpay/**", "/payment/**", "/js/**","/images/**","/css/**", "/fonts/**"};
    private final Environment environment;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

//    private final String[] PUBLIC_ENDPOINT = {"/","/login","/js/**","/images/**","/css/**", "/fonts/**"};
    private final String[] AUTHENICATE_ENDPOINT = {"/myInfo"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
              request.requestMatchers(PUBLIC_ENDPOINT).permitAll()
                      .requestMatchers("/images/**", "/css/**", "/js/**","/fonts/**").permitAll()
                      .requestMatchers("/admin/**").hasAuthority("ADMIN")
                      .requestMatchers("/**").hasAuthority("USER")
                      .requestMatchers("/doctor/**").hasAuthority("DOCTOR")
                      .requestMatchers(AUTHENICATE_ENDPOINT).authenticated()
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
//                                        case "DOCTOR":
//                                            response.sendRedirect("/doctor");
//                                            break;
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


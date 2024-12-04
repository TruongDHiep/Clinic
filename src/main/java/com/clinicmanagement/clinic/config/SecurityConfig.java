package com.clinicmanagement.clinic.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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

import javax.crypto.spec.SecretKeySpec;

//
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINT = {"/admin/**","/api/users/register","/login","/","/patients","/auth/login", "/booking/**", "/vnpay/**", "/payment/**"};
    private final Environment environment;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      httpSecurity.authorizeHttpRequests(request ->
              request.requestMatchers(PUBLIC_ENDPOINT).permitAll()
                      .requestMatchers("/images/**", "/css/**", "/js/**","/fonts/**").permitAll()
                      .anyRequest().authenticated()
      );

//          httpSecurity.oauth2ResourceServer(oauth2 ->
//                  oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
//                          .jwtAuthenticationConverter(jwtAuthenticationConverter())));

          httpSecurity.csrf(AbstractHttpConfigurer::disable);
          return httpSecurity.build();
    }
//
//
//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter(){
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder(){
//        String signkey = environment.getProperty("security.signerkey");
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signkey.getBytes(),"HS512");
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//    }
//
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}


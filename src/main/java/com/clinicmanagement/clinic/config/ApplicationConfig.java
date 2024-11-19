package com.clinicmanagement.clinic.config;

import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.enums.Role;
import com.clinicmanagement.clinic.repository.UserRopsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ApplicationConfig {

//    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRopsitory userRopsitory){
        return args -> {
            if (userRopsitory.findByUsername("admin").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                Useracount user = Useracount.builder()
                        .username("admin")
                        .password("admin1234")//passwordEncoder.encode("admin1234")
                        .role(roles)
                        .status(true)
                        .build();
                userRopsitory.save(user);
                log.warn("Admin account has create with default password: admin1234, please change your admin password");
            }
        };
    }

}

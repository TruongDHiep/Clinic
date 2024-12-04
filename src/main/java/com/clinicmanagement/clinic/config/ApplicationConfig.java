package com.clinicmanagement.clinic.config;

import com.clinicmanagement.clinic.Entities.Role;
import com.clinicmanagement.clinic.Entities.UserRole;
import com.clinicmanagement.clinic.Entities.Useracount;
import com.clinicmanagement.clinic.enums.Roles;
import com.clinicmanagement.clinic.repository.RoleRepository;
import com.clinicmanagement.clinic.repository.UserRepository;
import com.clinicmanagement.clinic.repository.UserRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ApplicationConfig {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                Role adminRole = roleRepository.findByRoleName("ADMIN");
                Useracount user = Useracount.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin1234"))
                        .status(true)
                        .build();
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(adminRole);
                user.setUserRoles(new HashSet<>());
                user.getUserRoles().add(userRole);
                userRepository.save(user);
                userRoleRepository.save(userRole);
                log.warn("Admin account has been created with the default password: admin1234, please change your admin password.");
            }
        };
    }

}

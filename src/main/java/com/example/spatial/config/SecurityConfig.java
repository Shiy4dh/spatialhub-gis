package com.example.spatial.config;

import com.example.spatial.model.AppUser;
import com.example.spatial.model.UserRole;
import com.example.spatial.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Delete old mismatched entries
            userRepository.findByUsername("dispatcher").ifPresent(userRepository::delete);
            userRepository.findByUsername("technician").ifPresent(userRepository::delete);

            // Seed dispatcher
            AppUser dispatcher = new AppUser();
            dispatcher.setUsername("dispatcher");
            dispatcher.setPassword(passwordEncoder.encode("admin123"));
            dispatcher.setFullName("John Dispatcher");
            dispatcher.setRole(UserRole.NOC_OPERATOR);
            userRepository.save(dispatcher);

            // Seed technician
            AppUser tech = new AppUser();
            tech.setUsername("technician");
            tech.setPassword(passwordEncoder.encode("tech123"));
            tech.setFullName("Ramesh Tech");
            tech.setRole(UserRole.FIELD_TECH);
            userRepository.save(tech);
        };
    }
}
package com.example.spatial.controller;

import com.example.spatial.model.AppUser;
import com.example.spatial.model.AuthResponse;
import com.example.spatial.model.LoginRequest;
import com.example.spatial.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("--> Login attempt for username: [" + request.getUsername() + "]");

        var userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            System.out.println("--> FAILED: Username not found in database!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = userOpt.get();
        System.out.println("--> User found in DB. Stored hash/pwd: [" + user.getPassword() + "]");

        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        System.out.println("--> Password matches: " + matches);

        if (!matches) {
            System.out.println("--> FAILED: Password mismatch!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(new AuthResponse(user.getUsername(), user.getFullName(), user.getRole().name()));
    }
}
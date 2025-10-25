package com.example.demo.controller;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationDto dto) {
        return ResponseEntity.ok(service.registerUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto dto) {
        boolean success = service.loginUser(dto);
        if (success) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password!");
        }
    }
}

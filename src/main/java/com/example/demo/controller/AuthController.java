package com.example.demo.controller;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDto dto, BindingResult result) {
        if (result.hasErrors()) {
            // Prefer @NotBlank messages first
            String errorMessage = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .sorted((a, b) -> a.contains("required") ? -1 : 1)
                    .findFirst()
                    .orElse("Invalid input");

            return ResponseEntity.badRequest().body(errorMessage);
        }

        // Otherwise continue as normal
        User savedUser = service.registerUser(dto);
        return ResponseEntity.ok(savedUser);
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

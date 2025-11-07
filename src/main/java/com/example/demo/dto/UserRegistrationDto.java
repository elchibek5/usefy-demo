package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRegistrationDto {

    @NotBlank(message = "Username is required")
    @Pattern(regexp = ".*\\S.*", message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be 3–20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = ".*\\S.*", message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
    private String password;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }
}

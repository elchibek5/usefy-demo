package com.example.demo.service;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.User;

public interface AuthService {
    User registerUser(UserRegistrationDto dto);
    boolean loginUser(UserLoginDto dto);
}

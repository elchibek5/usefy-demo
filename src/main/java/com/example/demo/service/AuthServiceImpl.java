package com.example.demo.service;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public User registerUser(UserRegistrationDto dto) {
        if (repo.findByUsername(dto.getUsername()) != null) {
            throw new UserAlreadyExistsException(dto.getUsername());
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(encoder.encode(dto.getPassword())); // encode the password
        return repo.save(user);
    }

    @Override
    public boolean loginUser(UserLoginDto dto) {
        User user = repo.findByUsername(dto.getUsername());
        if (user == null) return false;
        return encoder.matches(dto.getPassword(), user.getPasswordHash());
    }
}

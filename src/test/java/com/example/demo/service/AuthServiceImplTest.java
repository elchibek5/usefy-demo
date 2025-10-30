package com.example.demo.service;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes @Mock and @InjectMocks
    }

    @Test
    void testLoginUser_Success() {
        // Prepare DTO
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername("test");
        dto.setPassword("password");

        // Prepare user in repository
        User user = new User();
        user.setUsername("test");
        user.setPasswordHash("encodedPassword");

        // Mock repository and encoder behavior
        when(userRepository.findByUsername("test")).thenReturn(user);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        // Call the service
        boolean result = authService.loginUser(dto);

        // Assertions
        assertTrue(result);

        // Verify interactions
        verify(userRepository).findByUsername("test");
        verify(passwordEncoder).matches("password", "encodedPassword");
    }

    @Test
    void testLoginUser_Failure_WrongPassword() {
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername("test");
        dto.setPassword("wrongPassword");

        User user = new User();
        user.setUsername("test");
        user.setPasswordHash("encodedPassword");

        when(userRepository.findByUsername("test")).thenReturn(user);
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        boolean result = authService.loginUser(dto);

        assertFalse(result);

        verify(userRepository).findByUsername("test");
        verify(passwordEncoder).matches("wrongPassword", "encodedPassword");
    }

    @Test
    void testLoginUser_Failure_UserNotFound() {
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername("nonexistent");
        dto.setPassword("password");

        when(userRepository.findByUsername("nonexistent")).thenReturn(null);

        boolean result = authService.loginUser(dto);

        assertFalse(result);

        verify(userRepository).findByUsername("nonexistent");
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto("newUser", "plainPassword");

        when(userRepository.findByUsername("newUser")).thenReturn(null);
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setUsername("newUser");
        savedUser.setPasswordHash("encodedPassword");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = authService.registerUser(dto);

        // Assert
        assertNotNull(result);
        assertEquals("newUser", result.getUsername());
        assertEquals("encodedPassword", result.getPasswordHash());

        verify(passwordEncoder).encode("plainPassword");
        verify(userRepository).save(any(User.class));
    }
}


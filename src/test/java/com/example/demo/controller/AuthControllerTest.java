package com.example.demo.controller;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegister_Success() throws Exception {
        // Arrange
        UserRegistrationDto dto = new UserRegistrationDto("testUser", "password");
        User savedUser = new User();
        savedUser.setUsername("testUser");
        savedUser.setPasswordHash("encodedPassword");

        when(authService.registerUser(any(UserRegistrationDto.class))).thenReturn(savedUser);

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));

        verify(authService).registerUser(any(UserRegistrationDto.class));
    }

    @Test
    void testLogin_Success() throws Exception {
        // Arrange
        UserLoginDto dto = new UserLoginDto("testUser", "password");
        when(authService.loginUser(any(UserLoginDto.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful!"));

        verify(authService).loginUser(any(UserLoginDto.class));
    }

    @Test
    void testLogin_Failure() throws Exception {
        // Arrange
        UserLoginDto dto = new UserLoginDto("wrongUser", "badPassword");
        when(authService.loginUser(any(UserLoginDto.class))).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid username or password!"));

        verify(authService).loginUser(any(UserLoginDto.class));
    }

    @Test
    void testRegister_ValidationError_EmptyUsername() throws Exception {
        // Arrange : create DTO with empty username
        UserRegistrationDto dto = new UserRegistrationDto("", "password123");

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("Username must be 3–20 characters"));
    }

    @Test
    void testRegister_ValidationError_ShortPassword() throws Exception {
        // Arrange : create DTO with short password
        UserRegistrationDto dto = new UserRegistrationDto("validUser", "123");

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.password").value("Password must be at least 6 characters"));
    }

    @Test
    void testRegister_ValidationError_EmptyPassword() throws Exception {
        // Arrange : create DTO with empty password
        UserRegistrationDto dto = new UserRegistrationDto("validUser", "");

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.password").value("Password is required"));
    }

}

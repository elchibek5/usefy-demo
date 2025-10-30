package com.example.demo.controller;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;  // allows sending HTTP requests

    @MockBean
    private AuthService authService; // mock the service (we don't hit the DB)

    @Autowired
    private ObjectMapper objectMapper; // converts Java objects <-> JSON

    @Test
    void testRegister_Success() throws Exception {
        // Arrange

        UserRegistrationDto dto = new UserRegistrationDto("testUser", "password");
        User savedUser = new User();
        savedUser.setUsername("testUser");
        savedUser.setPasswordHash("encodedPassword");

        when(authService.registerUser(any(UserRegistrationDto.class))).thenReturn(savedUser);

        // Act & Assert
        mockMvc.perform(post("/api/auth/register"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));

        verify(authService).registerUser(any(UserRegistrationDto.class));
    }


}

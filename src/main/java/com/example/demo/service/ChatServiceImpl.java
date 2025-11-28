package com.example.demo.service;

import com.example.demo.dto.ChatRequestDto;
import com.example.demo.dto.ChatResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final String apiKey;

    // inject from application.properties (defaults to empty string if missing)
    public ChatServiceImpl(@Value("${external.ai.api-key:}") String apiKey) {
        this.apiKey = apiKey;
    }

    // Optional no-arg constructor if you ever want to use it in plain unit tests
    public ChatServiceImpl() {
        this("");
    }

    @Override
    public ChatResponseDto getChatResponse(ChatRequestDto requestDto) {
        String userMessage = requestDto.getMessage();

        String reply;
        if (userMessage == null || userMessage.trim().isEmpty()) {
            reply = "Please enter a message.";
        } else {
            reply = "Echo from AiDA (dummy): " + userMessage;
        }

        return new ChatResponseDto(reply);
    }
}

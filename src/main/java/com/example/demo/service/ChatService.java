package com.example.demo.service;

import com.example.demo.dto.ChatRequestDto;
import com.example.demo.dto.ChatResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public ChatResponseDto getChatResponse(ChatRequestDto request) {
        ChatResponseDto dto = new ChatResponseDto();
        dto.setReply("AI says: " + request.getMessage());
        return dto;
    }
}


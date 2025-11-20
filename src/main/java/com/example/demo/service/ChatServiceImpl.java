package com.example.demo.service;

import com.example.demo.dto.ChatRequestDto;
import com.example.demo.dto.ChatResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Override
    public ChatResponseDto getAiReply(ChatRequestDto requestDto) {
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

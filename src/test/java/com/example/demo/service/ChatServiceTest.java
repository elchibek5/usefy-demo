package com.example.demo.service;

import com.example.demo.dto.ChatRequestDto;
import com.example.demo.dto.ChatResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatServiceTest {

    // Use the concrete implementation in tests
    private final ChatService chatService = new ChatServiceImpl("");

    @Test
    void getChatResponse_returnsPromptForEmptyMessage() {
        ChatRequestDto dto = new ChatRequestDto();
        dto.setMessage("   "); // blank message

        ChatResponseDto response = chatService.getChatResponse(dto);

        assertEquals("Please enter a message.", response.getReply());
    }

    @Test
    void getChatResponse_echoesMessageWhenNotEmpty() {
        ChatRequestDto dto = new ChatRequestDto();
        dto.setMessage("Hello AiDA");

        ChatResponseDto response = chatService.getChatResponse(dto);

        assertTrue(response.getReply().contains("Hello AiDA"));
        assertTrue(response.getReply().startsWith("Echo from AiDA"));
    }
}

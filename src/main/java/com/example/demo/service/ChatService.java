package com.example.demo.service;

import com.example.demo.dto.ChatRequestDto;
import com.example.demo.dto.ChatResponseDto;

public interface ChatService {

    ChatResponseDto getAiReply(ChatRequestDto requestDto);
}

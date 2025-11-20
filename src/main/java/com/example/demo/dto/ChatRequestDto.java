package com.example.demo.dto;

public class ChatRequestDto {

    private String message;

    public ChatRequestDto() {
    }

    public ChatRequestDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage() {
        this.message = message;
    }
}

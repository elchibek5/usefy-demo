package com.example.demo.dto;

public class ChatResponseDto {

    private String reply;

    public ChatResponseDto() {
    }

    public ChatResponseDto(String reply) {
        this.reply = reply;
    }

    public String getReply(String reply) {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}

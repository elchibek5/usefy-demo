package com.example.demo.model;

public class User {
    private Long id;
    private String username;
    private String passwordHash;

    public User() {}

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getPasswordHash() {
        return passwordHash;
    }

    private void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


}

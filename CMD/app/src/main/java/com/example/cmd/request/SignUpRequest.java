package com.example.cmd.request;

public class SignUpRequest {
    String userId;
    String password;

    public SignUpRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

package com.example.cmd.Request;

public class SignInRequest {
    private String userId;
    private String password;

    public SignInRequest(String userId, String password) {
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

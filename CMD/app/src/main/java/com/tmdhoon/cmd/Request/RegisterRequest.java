package com.tmdhoon.cmd.Request;

public class RegisterRequest {

    private String username;
    private String number;
    private String userId;
    private String password;

    public RegisterRequest(String username, String number, String userId, String password) {
        this.username = username;
        this.number = number;
        this.userId = userId;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getNumber() {
        return number;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

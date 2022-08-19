package com.example.cmd.Request;

public class InfoUpdateRequest {
    private String password;

    public InfoUpdateRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

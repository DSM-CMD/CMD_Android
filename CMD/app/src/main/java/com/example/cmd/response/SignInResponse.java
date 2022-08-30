package com.example.cmd.response;

public class SignInResponse {
    private String accessToken;

    public SignInResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

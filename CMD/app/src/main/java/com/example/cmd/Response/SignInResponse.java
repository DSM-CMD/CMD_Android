package com.example.cmd.Response;

public class SignInResponse {
    private String accessToken;

    public SignInResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

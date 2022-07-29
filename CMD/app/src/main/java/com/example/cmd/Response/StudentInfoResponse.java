package com.example.cmd.Response;

public class StudentInfoResponse {
    private String username;
    private String number;

    public StudentInfoResponse(String username, String number) {
        this.username = username;
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public String getNumber() {
        return number;
    }
}

package com.example.cmd.Response;

public class StudentInfoResponse {
    private String name;
    private String number;

    public StudentInfoResponse(String username, String number) {
        this.name = username;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}

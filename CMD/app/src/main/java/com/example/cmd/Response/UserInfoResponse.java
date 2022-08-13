package com.example.cmd.Response;

public class UserInfoResponse {
    String username;
    String number;
    String birthday;
    String field;

    public UserInfoResponse(String username, String number, String birthday, String field) {
        this.username = username;
        this.number = number;
        this.birthday = birthday;
        this.field = field;
    }

    public String getUsername() {
        return username;
    }

    public String getNumber() {
        return number;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getField() {
        return field;
    }
}

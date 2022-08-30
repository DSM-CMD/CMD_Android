package com.example.cmd.Request;

public class InfoUpdateRequest {
    private String password;
    private String birthday;
    private String field;

    public InfoUpdateRequest(String password, String birthday, String field) {
        this.password = password;
        this.birthday = birthday;
        this.field = field;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getField() {
        return field;
    }
}

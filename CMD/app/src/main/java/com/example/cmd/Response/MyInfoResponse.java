package com.example.cmd.Response;

public class MyInfoResponse {
    private String username;
    private String number;
    private String birthday;
    private String field;
    private String seatNumber;
    private String userId;

    public MyInfoResponse(String username, String number, String birthday, String field, String seatNumber, String userId) {
        this.username = username;
        this.number = number;
        this.birthday = birthday;
        this.field = field;
        this.seatNumber = seatNumber;
        this.userId = userId;
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

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getUserId() {
        return userId;
    }
}

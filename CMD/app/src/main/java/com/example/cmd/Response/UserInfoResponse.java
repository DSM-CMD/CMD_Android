package com.example.cmd.Response;

public class UserInfoResponse {
    String username;
    String number;
    String birthday;
    String field;
    Long seatNumber;

    public UserInfoResponse(String username, String number, String birthday, String field, Long seatNumber) {
        this.username = username;
        this.number = number;
        this.birthday = birthday;
        this.field = field;
        this.seatNumber = seatNumber;
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

    public Long getSeatNumber() {
        return seatNumber;
    }
}

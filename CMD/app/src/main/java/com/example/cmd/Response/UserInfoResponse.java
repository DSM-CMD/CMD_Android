package com.example.cmd.Response;

public class UserInfoResponse {
    private String name;
    private String number;
    private String birthday;
    private String field;
    private Long seatNumber;

    public UserInfoResponse(String name, String number, String birthday, String field, Long seatNumber) {
        this.name = name;
        this.number = number;
        this.birthday = birthday;
        this.field = field;
        this.seatNumber = seatNumber;
    }

    public String getName() {
        return name;
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

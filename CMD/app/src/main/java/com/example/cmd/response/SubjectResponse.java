package com.example.cmd.Response;

public class SubjectResponse {
    private String name;
    private String teacher;
    private String place;

    public SubjectResponse(String name, String teacher, String place) {
        this.name = name;
        this.teacher = teacher;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getPlace() {
        return place;
    }
}

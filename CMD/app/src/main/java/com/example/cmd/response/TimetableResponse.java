package com.example.cmd.response;

public class TimetableResponse {
    private String day;
    private String period1st;
    private String period2nd;
    private String period3th;
    private String period4th;
    private String period5th;
    private String period6th;
    private String period7th;
    private String period8th;
    private String period9th;
    private String period10th;

    public TimetableResponse(String day, String period1st, String period2nd, String period3th, String period4th, String period5th, String period6th, String period7th, String period8th, String period9th, String period10th) {
        this.day = day;
        this.period1st = period1st;
        this.period2nd = period2nd;
        this.period3th = period3th;
        this.period4th = period4th;
        this.period5th = period5th;
        this.period6th = period6th;
        this.period7th = period7th;
        this.period8th = period8th;
        this.period9th = period9th;
        this.period10th = period10th;
    }


    public String getDay() {
        return day;
    }

    public String getPeriod1st() {
        return period1st;
    }

    public String getPeriod2nd() {
        return period2nd;
    }

    public String getPeriod3th() {
        return period3th;
    }

    public String getPeriod4th() {
        return period4th;
    }

    public String getPeriod5th() {
        return period5th;
    }

    public String getPeriod6th() {
        return period6th;
    }

    public String getPeriod7th() {
        return period7th;
    }

    public String getPeriod8th() {
        return period8th;
    }

    public String getPeriod9th() {
        return period9th;
    }

    public String getPeriod10th() {
        return period10th;
    }
}

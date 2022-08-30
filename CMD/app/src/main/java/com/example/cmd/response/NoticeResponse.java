package com.example.cmd.Response;

public class NoticeResponse {
    private String title;
    private String contents;

    public NoticeResponse(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}


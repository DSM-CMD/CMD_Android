package com.example.cmd.Response;

public class UserPostResponse {
    private long id;
    private String title;
    private String contents;

    public UserPostResponse(long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}

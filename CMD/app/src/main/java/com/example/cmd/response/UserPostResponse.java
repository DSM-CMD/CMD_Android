package com.example.cmd.Response;

public class UserPostResponse {
    private long id;
    private String title;
    private String contents;
    private String writer;

    public UserPostResponse(long id, String title, String contents, String writer) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
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

    public String getWriter(){
        return writer;
    }
}

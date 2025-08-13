package com.example.apitest.dto;

public class UpdateTodoRequest {

    private String title;
    private Boolean done;

    public UpdateTodoRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Boolean getDone() { return done; }
    public void setDone(Boolean done) { this.done = done; }
}
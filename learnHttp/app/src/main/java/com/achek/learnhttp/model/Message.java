package com.achek.learnhttp.model;

public class Message {
    private long id;
    private long time;
    private String text;
    private String image;

    public Message(long id, long time, String text, String image) {
        this.id = id;
        this.time = time;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

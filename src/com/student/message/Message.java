package com.student.message;

public class Message {
    private static int count =0 ;
    private String content;
    private int id=0;

    public Message(String content) {
        id=count++;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

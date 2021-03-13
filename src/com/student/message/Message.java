package com.student.message;

public class Message {
    private static int count =0 ;
    private String content;
    private ContentType contentType;
    private int id=0;

    public Message(String content, ContentType contentType) {
        id=count++;
        this.content = content;
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

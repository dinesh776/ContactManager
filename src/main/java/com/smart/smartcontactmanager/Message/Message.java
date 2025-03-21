package com.smart.smartcontactmanager.Message;

public class Message {
    public String content;
    public String type;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Message() {
    }

    public Message(String content, String type) {
        this.content = content;
        this.type = type;
    }
}

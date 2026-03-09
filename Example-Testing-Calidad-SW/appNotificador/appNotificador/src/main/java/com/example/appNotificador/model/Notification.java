package com.example.appNotificador.model;

public class Notification {
    private String to;
    private String message;

    public Notification(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public String getTo() { return to; }
    public String getMessage() { return message; }
}
package com.example.appNotificador.repository;


import com.example.appNotificador.model.Notification;

public interface INotificationChannel {
    void send(Notification notification);
    String channelName();
}

package com.example.appNotificador.service;

import com.example.appNotificador.model.Notification;
import com.example.appNotificador.repository.INotificationChannel;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final INotificationChannel channel;

    public NotificationService(INotificationChannel channel) {
        this.channel = channel;
    }

    public void notify(String to, String message) {
        channel.send(new Notification(to, message));
    }

    public String channel() {
        return channel.channelName();
    }
}

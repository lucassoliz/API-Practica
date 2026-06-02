package com.example.appNotificador.repository;

import com.example.appNotificador.model.Notification;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")
public class DevConsoleChannel implements INotificationChannel {
    @Override
    public void send(Notification n) {
        System.out.println("[DEV] Notificando a " + n.getTo() + ": " + n.getMessage());
    }
    @Override
    public String channelName() { return "dev-console"; }
}

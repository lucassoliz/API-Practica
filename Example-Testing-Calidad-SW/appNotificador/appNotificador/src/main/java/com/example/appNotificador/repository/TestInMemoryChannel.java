package com.example.appNotificador.repository;

import com.example.appNotificador.model.Notification;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("test")
public class TestInMemoryChannel implements INotificationChannel {
    private final List<Notification> sent = new ArrayList<>();

    @Override
    public void send(Notification n) {
        sent.add(n);
    }
    @Override
    public String channelName() { return "test-inmemory"; }

    // Getter para verificar en tests
    public List<Notification> getSent() { return sent; }
}

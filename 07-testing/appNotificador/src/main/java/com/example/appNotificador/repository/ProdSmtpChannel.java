package com.example.appNotificador.repository;

import com.example.appNotificador.model.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod")
public class ProdSmtpChannel implements INotificationChannel {

    @Value("${app.smtp.host}")
    private String smtpHost;

    @Override
    public void send(Notification n) {
        // Aquí iría la integración real con SMTP. Simulamos:
        System.out.println("[PROD] Enviando vía SMTP(" + smtpHost + ") a " + n.getTo() + ": " + n.getMessage());
    }
    @Override
    public String channelName() { return "prod-smtp"; }
}

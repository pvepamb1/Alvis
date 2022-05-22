package com.automation.butler.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class MailEventListener {

    private final Mailer mailer;

    @Autowired
    public MailEventListener(Mailer mailer) {
        this.mailer = mailer;
    }

    @EventListener
    public void sensorMailEventListener(SensorMailEvent event) {
        mailer.sendSimpleMessage(event.getAlias(), event.getMessage());
    }

    @EventListener
    public void heartbeatEventListener(HeartbeatMailEvent event) {
        mailer.sendSimpleMessage(event.getSubject(), event.getBody());
    }

}

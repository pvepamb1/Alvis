package com.alvis.scheduled;

import com.alvis.notification.HeartbeatMailEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class HeartbeatEventPublisher {

    private final ApplicationEventPublisher publisher;

    public HeartbeatEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(HeartbeatMailEvent event) {
        publisher.publishEvent(event);
    }
}

package com.automation.butler.scheduled;

import com.automation.butler.notification.HeartbeatMailEvent;
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

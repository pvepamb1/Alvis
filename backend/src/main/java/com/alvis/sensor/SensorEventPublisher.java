package com.alvis.sensor;

import com.alvis.notification.SensorMailEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SensorEventPublisher {

    private final ApplicationEventPublisher publisher;

    public SensorEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(SensorMailEvent event) {
        publisher.publishEvent(event);
    }
}

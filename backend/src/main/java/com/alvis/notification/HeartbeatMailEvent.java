package com.alvis.notification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeartbeatMailEvent {
    String subject;
    String body;
}

package com.alvis.notification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorMailEvent {
    String alias;
    String message;
}

package com.automation.butler.ldr;

import com.automation.butler.sensor.SensorConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
class LdrConfig extends SensorConfig {

    int minThreshold = 200;
    int maxThreshold = 600;
    char currentState = 'N';
    int sensorDelay = 10000;
}

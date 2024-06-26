package com.alvis.ldr;

import com.alvis.sensor.SensorConfig;
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
    boolean wasHigh = false;
    int sensorDelay = 10000;
}

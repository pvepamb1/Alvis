package com.automation.butler.ldr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
class LdrConfig {

    @Id
    String alias;
    int minThreshold = 200;
    int maxThreshold = 600;
    char currentState = 'N';
    int sensorDelay = 10000;
}

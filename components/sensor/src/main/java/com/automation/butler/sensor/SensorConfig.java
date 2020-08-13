package com.automation.butler.sensor;

import com.automation.butler.sensorlookup.SensorLookupID;
import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class SensorConfig {

    @Id
    @JacksonInject
    SensorLookupID id;
}

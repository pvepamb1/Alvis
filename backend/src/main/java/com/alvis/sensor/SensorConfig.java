package com.alvis.sensor;

import com.alvis.sensorlookup.SensorLookupID;
import com.alvis.sensorlookup.SensorLookupViews;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class SensorConfig {

    @Id
    @JacksonInject
    @JsonView(SensorLookupViews.Id.class)
    SensorLookupID id;
}

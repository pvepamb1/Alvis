package com.alvis.mic;

import com.alvis.sensor.SensorConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class MicConfig extends SensorConfig {
}

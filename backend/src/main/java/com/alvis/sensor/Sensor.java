package com.alvis.sensor;

import com.alvis.deviceaddress.DeviceAddress;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Sensor {

	@ManyToOne
	@JoinColumn(name = "fk_mac")
	private DeviceAddress mac;

	@Column(name = "sensor_id", length = 50)
	private String id;

}

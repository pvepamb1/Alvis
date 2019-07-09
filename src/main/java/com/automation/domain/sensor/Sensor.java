package com.automation.domain.sensor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.automation.domain.DeviceAddress;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class Sensor {

	@ManyToOne
	@JoinColumn(name = "fk_mac")
	private DeviceAddress mac;

	@Column(name = "sensor_id", length = 50)
	private String id;

	@Column(length = 15)
	private String value;

}

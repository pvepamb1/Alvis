package com.automation.sensor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.automation.table.DeviceAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

	@ManyToOne
	@JoinColumn(name = "fk_mac")
	private DeviceAddress mac;

	@Column(name = "sensor_id", length = 50)
	private String id;

	@Column(length = 15)
	private String value;

}

package com.automation.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.automation.table.DeviceAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SensorLookupID implements Serializable {

	private static final long serialVersionUID = -7769334084663720925L;

	@ManyToOne
	@JoinColumn
	private DeviceAddress address;
	@Column(length = 5)
	private String id;

	public SensorLookupID(Sensor sensor) {
		this.address = sensor.getMac();
		this.id = sensor.getId();
	}

}

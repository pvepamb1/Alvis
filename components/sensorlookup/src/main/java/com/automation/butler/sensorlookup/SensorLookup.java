package com.automation.butler.sensorlookup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.automation.butler.enums.SensorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SensorLookup {

	@Id
	private SensorLookupID id;

	private SensorType type;

	@Column(length = 15, unique = true)
	private String alias;

}

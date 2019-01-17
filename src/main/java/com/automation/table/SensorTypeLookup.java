package com.automation.table;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.automation.embeddable.SensorTypeLookupID;
import com.automation.enums.SensorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SensorTypeLookup {

	@Id
	private SensorTypeLookupID id;
	private SensorType type;

}

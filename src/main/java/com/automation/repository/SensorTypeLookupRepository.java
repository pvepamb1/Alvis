package com.automation.repository;

import org.springframework.data.repository.CrudRepository;

import com.automation.embeddable.SensorTypeLookupID;
import com.automation.enums.SensorType;
import com.automation.table.SensorTypeLookup;

public interface SensorTypeLookupRepository extends CrudRepository<SensorTypeLookup, SensorTypeLookupID> {
	
	Iterable<SensorTypeLookup> findByType(SensorType type);

}

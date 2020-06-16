package com.automation.butler.ldr;

import com.automation.butler.sensorlookup.SensorLookupID;
import org.springframework.data.repository.CrudRepository;

interface LdrConfigRepository extends CrudRepository<LdrConfig, SensorLookupID> {
}

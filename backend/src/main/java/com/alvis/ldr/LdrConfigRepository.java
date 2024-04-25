package com.alvis.ldr;

import com.alvis.sensorlookup.SensorLookupID;
import org.springframework.data.repository.CrudRepository;

interface LdrConfigRepository extends CrudRepository<LdrConfig, SensorLookupID> {
}

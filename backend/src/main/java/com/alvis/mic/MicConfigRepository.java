package com.alvis.mic;

import com.alvis.sensorlookup.SensorLookupID;
import org.springframework.data.repository.CrudRepository;

interface MicConfigRepository extends CrudRepository<MicConfig, SensorLookupID> {
}

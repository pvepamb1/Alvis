package com.automation.butler.mic;

import com.automation.butler.sensorlookup.SensorLookupID;
import org.springframework.data.repository.CrudRepository;

interface MicConfigRepository extends CrudRepository<MicConfig, SensorLookupID> {
}

package com.automation.butler.sensor;

import com.automation.butler.sensorlookup.SensorLookup;
import com.fasterxml.jackson.databind.JsonNode;

public interface RestlessController {

	void updateData(JsonNode body);
	void createConfigFile(SensorLookup sensor);
}

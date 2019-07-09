package com.automation.controller;

import com.automation.domain.SensorLookup;
import com.fasterxml.jackson.databind.JsonNode;

public interface RestlessController {

	void updateData(JsonNode body);
	void createConfigFile(SensorLookup sensor);
}

package com.automation.controller;

import com.automation.embeddable.Sensor;
import com.automation.table.SensorTypeLookup;

public interface RestlessController {

	void updateData(Sensor body);
	void createConfigFile(SensorTypeLookup sensor);
}

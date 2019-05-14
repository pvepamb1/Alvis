package com.automation.controller;

import com.automation.embeddable.Sensor;
import com.automation.table.SensorLookup;

public interface RestlessController {

	void updateData(Sensor body);
	void createConfigFile(SensorLookup sensor);
}

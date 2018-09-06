package com.automation.sensors;

import com.automation.types.MACAddress;

public interface Sensor {

	public MACAddress getMACAddress();
	public int getID();
	
}

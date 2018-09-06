package com.automation.sensors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.automation.tables.MACToIPMap;
import com.automation.types.MACAddress;

@Entity
public class WaterflowSensor implements Sensor{
	
	@Id
	int id;
	@ManyToOne
	@JoinColumn(name = "MAC_ADDRESS")
	MACToIPMap mac;
	
	String value;
	
	public WaterflowSensor() {}
	
	public WaterflowSensor(String macAddress, int id, String value) {
		this.mac = new MACToIPMap(macAddress,null);
		this.id = id;
		this.value = value;
	}

	@Override
	public MACAddress getMACAddress() {
		return mac.getMacAddress();
	}

	@Override
	public int getID() {
		return id;
	}

}

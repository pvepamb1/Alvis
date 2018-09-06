package com.automation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.repositories.WaterflowRepository;
import com.automation.sensors.WaterflowSensor;

@Service
public class WaterflowService {

	@Autowired
	WaterflowRepository repository;
	
	public String store(WaterflowSensor sensor) {
		repository.save(sensor);
		return "success";
	}
}

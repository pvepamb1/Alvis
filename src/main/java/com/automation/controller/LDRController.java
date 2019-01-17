package com.automation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automation.embeddable.Sensor;
import com.automation.sensor.LDR;
import com.automation.service.LDRService;

@Component
public class LDRController implements RestlessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LDRController.class);

	LDRService service;

	@Autowired
	public LDRController(LDRService service) {
		this.service = service;
	}

	public void updateData(Sensor body) {
		LOGGER.info("Storing {}", body);
		service.store(new LDR(body));
	}

}

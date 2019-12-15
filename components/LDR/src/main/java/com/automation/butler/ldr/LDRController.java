package com.automation.butler.ldr;

import com.automation.butler.sensor.RestlessController;
import com.automation.butler.sensorlookup.SensorLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LDRController implements RestlessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LDRController.class);

	private final LDRService service;
	private final LDRNotifier notifier;

	@Autowired
	public LDRController(LDRService service, LDRNotifier notifier) {
		this.service = service;
		this.notifier = notifier;
	}

	public void updateData(JsonNode body) {
		LOGGER.info("Storing {}", body);
		try {
			LDRDTO ldr = new ObjectMapper().treeToValue(body, LDRDTO.class);
			service.store(ldr);
			notifier.notifyUser(ldr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void createConfigFile(SensorLookup sensor) {
		notifier.createConfigFile(sensor);
	}
	
}

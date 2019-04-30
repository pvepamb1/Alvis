package com.automation.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automation.embeddable.Sensor;
import com.automation.notification.LDRNotifier;
import com.automation.sensor.LDR;
import com.automation.service.LDRService;
import com.automation.table.SensorTypeLookup;

@Component
public class LDRController implements RestlessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LDRController.class);

	LDRService service;
	LDRNotifier notifier;

	@Autowired
	public LDRController(LDRService service, LDRNotifier notifier) {
		this.service = service;
		this.notifier = notifier;
	}

	public void updateData(Sensor body) {
		LOGGER.info("Storing {}", body);
		LDR ldr = new LDR(body);
		service.store(ldr);
		notifier.notifyUser(ldr);
	}

	@Override
	public void createConfigFile(SensorTypeLookup sensor) {
		OutputStream output = null;
		Properties prop = new Properties();
		String identifier = sensor.getId().getMac().getMac().replaceAll(":", "") + sensor.getId().getId();

		try {

			output = new FileOutputStream(System.getenv("HOME") + "/.homeauto/config/" + identifier + ".properties");
			prop.setProperty("emailSent", "false");
			prop.setProperty("min", "200");
			prop.setProperty("max", "700");
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
			LOGGER.warn("{}", io.getMessage());
		}
	}
	
}

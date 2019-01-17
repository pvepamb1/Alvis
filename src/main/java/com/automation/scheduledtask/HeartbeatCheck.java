package com.automation.scheduledtask;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.automation.service.DeviceAddressService;
import com.automation.table.DeviceAddress;

@Component
public final class HeartbeatCheck {

	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatCheck.class);

	private static final long checkDelay = 5; // in minutes

	private DeviceAddressService service;

	@Autowired
	public HeartbeatCheck(DeviceAddressService service) {
		this.service = service;
	}

	@Scheduled(fixedDelay = 60000)
	public void checkStatus() {
		for (DeviceAddress mtip : service.retrieveAll()) {
			if (ChronoUnit.MINUTES.between(mtip.getUpdateDateTime(), LocalDateTime.now()) > checkDelay) {
				LOGGER.warn("{} with ip address {} couldn't be reached", mtip.getMac(), mtip.getIp());
			}
		}
	}

}

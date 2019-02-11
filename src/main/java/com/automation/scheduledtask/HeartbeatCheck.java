package com.automation.scheduledtask;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.automation.notification.Mailer;
import com.automation.service.DeviceAddressService;
import com.automation.table.DeviceAddress;

@Component
public final class HeartbeatCheck {

	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatCheck.class);
	private static final long checkDelay = 60; // in minutes
	private static final long fixedDelayValue = 60; //in minutes
	private DeviceAddressService service;
	private Mailer mailer;
	@Value("${mailTo:}")
	String to;

	@Autowired
	public HeartbeatCheck(DeviceAddressService service, Mailer mailer) {
		this.service = service;
		this.mailer = mailer;
	}

	@Scheduled(fixedDelay = fixedDelayValue*60000)
	public void checkStatus() {
		for (DeviceAddress device : service.retrieveAll()) {
			if (ChronoUnit.MINUTES.between(device.getUpdateDateTime(), LocalDateTime.now()) > checkDelay) {
				LOGGER.warn("{} with ip address {} couldn't be reached", device.getMac(), device.getIp());
				LOGGER.warn("mailing {} about unreachable device", to);
				mailer.sendSimpleMessage(to, "Device down!",
						device.getMac() + " with ip " + device.getIp() + " couldn't be reached");
			}
		}
	}

}

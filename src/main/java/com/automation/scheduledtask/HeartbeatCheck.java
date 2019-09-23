package com.automation.scheduledtask;

import com.automation.domain.DeviceAddress;
import com.automation.notification.Mailer;
import com.automation.service.DeviceAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public final class HeartbeatCheck {

	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatCheck.class);
	private static long checkDelay = 60; // in minutes
	private static final long fixedDelayValue = 60; //in minutes
	private final DeviceAddressService service;
	private final Mailer mailer;
	@Value("${mailTo:}")
	private String to;

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

	public static long getCheckDelay() {
		return checkDelay;
	}

	public static void setCheckDelay(long checkDelay) {
		HeartbeatCheck.checkDelay = checkDelay;
	}
}

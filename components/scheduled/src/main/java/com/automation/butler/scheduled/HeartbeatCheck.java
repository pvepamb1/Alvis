package com.automation.butler.scheduled;

import com.automation.butler.deviceaddress.DeviceAddress;
import com.automation.butler.deviceaddress.DeviceAddressService;
import com.automation.butler.notification.Mailer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public final class HeartbeatCheck {

    private static final long FIXED_DELAY_VALUE = 60; //in minutes
    private static long checkDelay = 60; // in minutes
    private final DeviceAddressService service;
    private final Mailer mailer;
    @Value("${mailTo:}")
    private String to;

    @Autowired
    public HeartbeatCheck(DeviceAddressService service, Mailer mailer) {
        this.service = service;
        this.mailer = mailer;
    }

    public static long getCheckDelay() {
        return checkDelay;
    }

    public static void setCheckDelay(long checkDelay) {
        HeartbeatCheck.checkDelay = checkDelay;
    }

    @Scheduled(fixedDelay = FIXED_DELAY_VALUE * 60000)
    public void checkStatus() {
        for (DeviceAddress device : service.retrieveAll()) {
            if (ChronoUnit.MINUTES.between(device.getUpdateDateTime(), LocalDateTime.now()) > checkDelay) {
                log.debug("{} with ip address {} couldn't be reached", device.getMac(), device.getIp());
                log.debug("mailing {} about unreachable device", to);
                mailer.sendSimpleMessage(to, "Device down!",
                        device.getMac() + " with ip " + device.getIp() + " couldn't be reached");
            }
        }
    }
}

package com.alvis.scheduled;

import com.alvis.deviceaddress.DeviceAddress;
import com.alvis.deviceaddress.DeviceAddressService;
import com.alvis.notification.HeartbeatMailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final HeartbeatEventPublisher publisher;

    @Autowired
    public HeartbeatCheck(DeviceAddressService service, HeartbeatEventPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
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
                publisher.publish(new HeartbeatMailEvent("Device Down!", device.getMac() +
                        " with ip address " + device.getIp() + " couldn't be reached"));
            }
        }
    }
}

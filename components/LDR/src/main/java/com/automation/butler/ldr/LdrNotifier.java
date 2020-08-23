package com.automation.butler.ldr;

import com.automation.butler.notification.SensorMailEvent;
import com.automation.butler.sensor.SensorEventPublisher;
import com.automation.butler.sensorlookup.SensorLookup;
import com.automation.butler.sensorlookup.SensorLookupID;
import com.automation.butler.sensorlookup.SensorLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class LdrNotifier {

    private final SensorLookupService lookupService;
    private final LdrConfigService configService;
    private final SensorEventPublisher publisher;

    @Autowired
    public LdrNotifier(SensorLookupService lookupService, LdrConfigService configService, SensorEventPublisher publisher) {
        this.lookupService = lookupService;
        this.configService = configService;
        this.publisher = publisher;
    }

    @EventListener
    public void notifyUser(LdrDTO ldr) {
        SensorLookupID lookupID;
        String alias;
        Optional<SensorLookup> lookupOptional = lookupService.findById(ldr.getMac(), ldr.getId());
        if (lookupOptional.isPresent()) {
            SensorLookup lookup = lookupOptional.get();
            lookupID = lookup.getId();
            alias = lookup.getAlias();

            Optional<LdrConfig> configOptional = configService.getConfigById(lookupID);
            if (configOptional.isPresent()) {
                LdrConfig config = configOptional.get();
                if (ldr.getValue() >= config.getMaxThreshold() && !config.isWasHigh()) {
                    String message = alias + " has been turned ON. Value: " + ldr.getValue();
                    log.info("{} value above max threshold of {}", alias, config.getMaxThreshold());
                    publisher.publish(new SensorMailEvent(alias, message));
                    config.setWasHigh(true);
                    configService.saveConfig(config);
                } else if (ldr.getValue() <= config.getMinThreshold() && config.isWasHigh()) {
                    String message = alias + " has been turned OFF. Value: " + ldr.getValue();
                    log.info("{} value below min threshold of {}", alias, config.getMinThreshold());
                    publisher.publish(new SensorMailEvent(alias, message));
                    config.setWasHigh(false);
                    configService.saveConfig(config);
                }
            }
        }
    }
}

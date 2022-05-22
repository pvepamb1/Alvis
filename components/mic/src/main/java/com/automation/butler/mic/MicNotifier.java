package com.automation.butler.mic;

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
public class MicNotifier {

    private final SensorLookupService lookupService;
    private final MicConfigService configService;
    private final SensorEventPublisher publisher;

    @Autowired
    public MicNotifier(SensorLookupService lookupService, MicConfigService configService, SensorEventPublisher publisher) {
        this.lookupService = lookupService;
        this.configService = configService;
        this.publisher = publisher;
    }

    @EventListener
    public void notifyUser(MicDTO mic) {
        SensorLookupID lookupID;
        Optional<SensorLookup> lookupOptional = lookupService.findById(mic.getMac(), mic.getId());
        if (lookupOptional.isPresent()) {
            SensorLookup lookup = lookupOptional.get();
            lookupID = lookup.getId();
            String alias = lookup.getAlias();

            Optional<MicConfig> configOptional = configService.getConfigById(lookupID);
            if (configOptional.isPresent()) {
                MicConfig config = configOptional.get();
            }
        }
    }

}

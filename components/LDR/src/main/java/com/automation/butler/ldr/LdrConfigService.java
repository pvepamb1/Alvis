package com.automation.butler.ldr;

import com.automation.butler.enums.SensorType;
import com.automation.butler.sensor.SensorConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.automation.butler.enums.SensorType.LDR;

@Slf4j
@Service
public class LdrConfigService extends SensorConfigService<LdrConfigRepository, LdrConfig, SensorType> {

    @Autowired
    public LdrConfigService(@Autowired LdrConfigRepository repo, @Autowired ApplicationEventPublisher eventPublisher) {
        super(repo, LdrConfig.class, LDR, eventPublisher);
    }
}

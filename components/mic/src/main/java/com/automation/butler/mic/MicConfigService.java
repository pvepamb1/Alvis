package com.automation.butler.mic;

import com.automation.butler.sensor.SensorConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.automation.butler.enums.SensorType.MIC;

@Slf4j
@Service
public class MicConfigService extends SensorConfigService<MicConfigRepository, MicConfig> {

    @Autowired
    public MicConfigService(@Autowired MicConfigRepository repo, @Autowired ApplicationEventPublisher eventPublisher) {
        super(repo, MicConfig.class, MIC, eventPublisher);
    }
}

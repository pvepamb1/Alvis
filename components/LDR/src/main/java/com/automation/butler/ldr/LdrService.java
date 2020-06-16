package com.automation.butler.ldr;

import com.automation.butler.enums.SensorType;
import com.automation.butler.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.automation.butler.enums.SensorType.LDR;

@Service
public class LdrService extends SensorService<LdrRepository, LdrDTO, SensorType> {

    @Autowired
    public LdrService(@Autowired LdrRepository repo, @Autowired ApplicationEventPublisher eventPublisher) {
        super(repo, LdrDTO.class, LDR, eventPublisher);
    }
}

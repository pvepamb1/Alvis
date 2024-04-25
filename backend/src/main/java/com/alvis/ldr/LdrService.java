package com.alvis.ldr;

import com.alvis.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.alvis.enums.SensorType.LDR;

@Service
public class LdrService extends SensorService<LdrRepository, LdrDTO> {

    @Autowired
    public LdrService(@Autowired LdrRepository repo, @Autowired ApplicationEventPublisher eventPublisher) {
        super(repo, LdrDTO.class, LDR, eventPublisher);
    }
}

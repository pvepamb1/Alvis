package com.automation.butler.mic;

import com.automation.butler.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.automation.butler.enums.SensorType.MIC;

@Service
public class MicService extends SensorService<MicRepository, MicDTO> {

    @Autowired
    public MicService(@Autowired MicRepository repo, @Autowired ApplicationEventPublisher eventPublisher) {
        super(repo, MicDTO.class, MIC, eventPublisher);
    }
}

package com.alvis.mic;

import com.alvis.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.alvis.enums.SensorType.MIC;

@Service
public class MicService extends SensorService<MicRepository, MicDTO> {

    @Autowired
    public MicService(@Autowired MicRepository repo, @Autowired ApplicationEventPublisher eventPublisher) {
        super(repo, MicDTO.class, MIC, eventPublisher);
    }
}

package com.automation.butler.ldr;

import com.automation.butler.sensor.RestlessController;
import com.automation.butler.sensorlookup.SensorLookup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LDRController implements RestlessController {

    private final LdrService service;
    private final LdrNotifier notifier;

    @Autowired
    public LDRController(LdrService service, LdrNotifier notifier) {
        this.service = service;
        this.notifier = notifier;
    }

    public void updateData(JsonNode body) {
        log.info("Storing {}", body);
        try {
            LdrDTO ldr = new ObjectMapper().treeToValue(body, LdrDTO.class);
            service.store(ldr);
            notifier.notifyUser(ldr);
        } catch (JsonProcessingException e) {
            log.debug("Ignored {}", body);
            log.debug(e.getMessage());
        }
    }

    @Override
    public JsonNode getConfigByAlias(String alias) {
        return new ObjectMapper().valueToTree(service.getConfigByAlias(alias));
    }

    @Override
    public void createDefaultConfig(String alias) {
        service.createDefaultConfig(alias);
    }

    @Override
    public void createConfigFile(SensorLookup sensor) {
        notifier.createConfigFile(sensor);
    }

}

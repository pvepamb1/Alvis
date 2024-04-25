package com.alvis.sensor;

import com.alvis.enums.SensorType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.CrudRepository;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SensorService<T extends CrudRepository<U, ?>, U extends Sensor> {

    protected T repo;
    private Class<U> clazz;
    private SensorType type;
    private ApplicationEventPublisher eventPublisher;

    public void save(JsonNode body) {
        log.info("Storing {}", body);
        try {
            U value = new ObjectMapper().treeToValue(body, clazz);
            repo.save(value);
            publishEvent(value);
        } catch (JsonProcessingException e) {
            log.debug("Ignored {}", body);
            log.debug(e.getMessage());
        }
    }

    private void publishEvent(U obj) {
        eventPublisher.publishEvent(obj);
    }

    public SensorType getType() {
        return this.type;
    }
}

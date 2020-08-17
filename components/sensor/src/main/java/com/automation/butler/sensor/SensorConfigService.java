package com.automation.butler.sensor;

import com.automation.butler.enums.SensorType;
import com.automation.butler.sensorlookup.SensorLookupID;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SensorConfigService<T extends CrudRepository<U, SensorLookupID>, U extends SensorConfig, V extends SensorType> {

    protected T repo;
    private Class<U> clazz;
    private V type;
    private ApplicationEventPublisher eventPublisher;

    public void createDefaultConfig(SensorLookupID id) {
        try {
            Constructor<U> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true); //reflection magic to get around protected class modifier
            U obj = constructor.newInstance();
            obj.setId(id);
            repo.save(obj);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            log.error("Cannot create instance {}", ex.getMessage());
        }
    }

    public Optional<U> getConfigById(SensorLookupID id) {
        return repo.findById(id);
    }

    public void saveConfig(U obj) {
        repo.save(obj);
    }

    public void saveConfig(JsonNode jsonNode, SensorLookupID id) throws JsonProcessingException {
        InjectableValues inject = new InjectableValues.Std().addValue(SensorLookupID.class, id);
        U obj = new ObjectMapper().reader(inject).treeToValue(jsonNode, clazz);
        repo.save(obj);
    }

    public SensorType getType() {
        return this.type;
    }

}

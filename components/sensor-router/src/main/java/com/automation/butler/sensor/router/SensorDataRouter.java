package com.automation.butler.sensor.router;

import com.automation.butler.enums.SensorType;
import com.automation.butler.sensor.Sensor;
import com.automation.butler.sensor.SensorConfig;
import com.automation.butler.sensor.SensorConfigService;
import com.automation.butler.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class SensorDataRouter {

    private static final Map<SensorType, SensorService<? extends CrudRepository, ? extends Sensor, ? extends SensorType>> serviceCache = new EnumMap<>(SensorType.class);
    private static final Map<SensorType, SensorConfigService<? extends CrudRepository, ? extends SensorConfig, ? extends SensorType>> configServiceCache = new EnumMap<>(SensorType.class);
    @Autowired
    private List<SensorService<? extends CrudRepository, ? extends Sensor, ? extends SensorType>> services;
    @Autowired
    private List<SensorConfigService<? extends CrudRepository, ? extends SensorConfig, ? extends SensorType>> configServices;

    static SensorService<? extends CrudRepository, ? extends Sensor, ? extends SensorType> getService(SensorType type) {
        return serviceCache.get(type);
    }

    static SensorConfigService<? extends CrudRepository, ? extends SensorConfig, ? extends SensorType> getConfigService(SensorType type) {
        return configServiceCache.get(type);
    }

    @PostConstruct
    public void initCache() {
        for (SensorService<? extends CrudRepository, ? extends Sensor, ? extends SensorType> service : services) {
            serviceCache.put(service.getType(), service);
        }

        for (SensorConfigService<? extends CrudRepository, ? extends SensorConfig, ? extends SensorType> configService : configServices) {
            configServiceCache.put(configService.getType(), configService);
        }
    }


}

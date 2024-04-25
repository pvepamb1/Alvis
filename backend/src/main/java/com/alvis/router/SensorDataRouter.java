package com.alvis.router;

import com.alvis.enums.SensorType;
import com.alvis.sensor.Sensor;
import com.alvis.sensor.SensorConfig;
import com.alvis.sensor.SensorConfigService;
import com.alvis.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class SensorDataRouter {

    private static final Map<SensorType, SensorService<? extends CrudRepository<?,?>, ? extends Sensor>> serviceCache = new EnumMap<>(SensorType.class);
    private static final Map<SensorType, SensorConfigService<? extends CrudRepository<?,?>, ? extends SensorConfig>> configServiceCache = new EnumMap<>(SensorType.class);
    @Autowired
    private List<SensorService<? extends CrudRepository<?,?>, ? extends Sensor>> services;
    @Autowired
    private List<SensorConfigService<? extends CrudRepository<?,?>, ? extends SensorConfig>> configServices;

    static SensorService<? extends CrudRepository<?,?>, ? extends Sensor> getService(SensorType type) {
        return serviceCache.get(type);
    }

    static SensorConfigService<? extends CrudRepository<?,?>, ? extends SensorConfig> getConfigService(SensorType type) {
        return configServiceCache.get(type);
    }

    @PostConstruct
    public void initCache() {
        for (SensorService<? extends CrudRepository<?,?>, ? extends Sensor> service : services) {
            serviceCache.put(service.getType(), service);
        }

        for (SensorConfigService<? extends CrudRepository<?,?>, ? extends SensorConfig> configService : configServices) {
            configServiceCache.put(configService.getType(), configService);
        }
    }


}

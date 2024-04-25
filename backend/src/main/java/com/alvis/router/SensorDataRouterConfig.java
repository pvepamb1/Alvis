package com.alvis.router;

import com.alvis.ldr.LdrConfigService;
import com.alvis.ldr.LdrService;
import com.alvis.sensor.Sensor;
import com.alvis.sensor.SensorConfig;
import com.alvis.sensor.SensorConfigService;
import com.alvis.sensor.SensorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SensorDataRouterConfig {

    @Bean
    public List<SensorService<? extends CrudRepository, ? extends Sensor>> serviceList(LdrService ldrService) {
        List<SensorService<? extends CrudRepository, ? extends Sensor>> services = new ArrayList<>();
        services.add(ldrService);
        return services;
    }

    @Bean
    public List<SensorConfigService<? extends CrudRepository, ? extends SensorConfig>> configServiceList(LdrConfigService ldrConfigService) {
        List<SensorConfigService<? extends CrudRepository, ? extends SensorConfig>> configServices = new ArrayList<>();
        configServices.add(ldrConfigService);
        return configServices;
    }
}

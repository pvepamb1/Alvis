package com.automation.butler.sensor.router;

import com.automation.butler.enums.SensorType;
import com.automation.butler.ldr.LdrConfigService;
import com.automation.butler.ldr.LdrService;
import com.automation.butler.sensor.Sensor;
import com.automation.butler.sensor.SensorConfig;
import com.automation.butler.sensor.SensorConfigService;
import com.automation.butler.sensor.SensorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SensorDataRouterConfig {

    @Bean
    public List<SensorService<? extends CrudRepository, ? extends Sensor, ? extends SensorType>> serviceList(LdrService ldrService) {
        List<SensorService<? extends CrudRepository, ? extends Sensor, ? extends SensorType>> services = new ArrayList<>();
        services.add(ldrService);
        return services;
    }

    @Bean
    public List<SensorConfigService<? extends CrudRepository, ? extends SensorConfig, ? extends SensorType>> configServiceList(LdrConfigService ldrConfigService) {
        List<SensorConfigService<? extends CrudRepository, ? extends SensorConfig, ? extends SensorType>> configServices = new ArrayList<>();
        configServices.add(ldrConfigService);
        return configServices;
    }
}

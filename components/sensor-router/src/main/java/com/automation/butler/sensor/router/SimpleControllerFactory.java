package com.automation.butler.sensor.router;

import com.automation.butler.enums.SensorType;
import com.automation.butler.sensor.RestlessController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
class SimpleControllerFactory {

    private static ApplicationContext ctx;

    @Autowired
    public SimpleControllerFactory(ApplicationContext applicationContext) {
        SimpleControllerFactory.ctx = applicationContext;
    }

    static RestlessController getController(SensorType type) {
		return (RestlessController) ctx.getBean(type + "Controller");
	}
}

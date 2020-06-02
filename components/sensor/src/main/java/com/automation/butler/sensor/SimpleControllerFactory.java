package com.automation.butler.sensor;

import com.automation.butler.enums.SensorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
class SimpleControllerFactory {

	@Autowired
    private static ApplicationContext ctx;

    private SimpleControllerFactory() {
    }

    static RestlessController getController(SensorType type) {
		return (RestlessController) ctx.getBean(type + "Controller");
	}
}

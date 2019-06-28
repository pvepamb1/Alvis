package com.automation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This program is an attempt to provide maximum flexibility when it
 * comes to home-automation. Written with ESP8266 in mind, this allows
 * developers and users with little tech know-how to build and deploy
 * sensors that can relay data to a raspberry-pi or a similar server. 
 */

@EnableScheduling
@SpringBootApplication
public class HomeAutomationJavaApplication {

	public static void main(String[] args) {
		//search for application and db props
		new SpringApplicationBuilder(HomeAutomationJavaApplication.class)
				.properties("spring.config.name:application,db", "spring.config.location:classpath:/," + System.getenv("HOME") + "/.homeauto/config/")
				.build()
				.run(args);
	}
}

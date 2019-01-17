package com.automation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HomeAutomationJavaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(HomeAutomationJavaApplication.class)
				.properties("spring.config.name:application,db", "spring.config.location:classpath:/,../")
				.build()
				.run(args);
	}
}

package com.automation.butler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

/**
 * This program is an attempt to provide maximum flexibility when it
 * comes to home-automation. Written with ESP8266 in mind, this allows
 * developers and users with little tech know-how to build and deploy
 * sensors that can relay data to a raspberry-pi or a similar server. 
 */

@Slf4j
@EnableScheduling
@SpringBootApplication
public class ButlerService {

	private static final String CONFIG_DIR = System.getenv("HOME") + "/.homeauto/config/";
	
	public static void main(String[] args) {

		// identify if the app is running on a container like docker
		String containerType = System.getenv("CONTAINER");

		// Terminate program if running on host(not container) and loading config dir encounters issues
		if("host".equals(containerType) && !validateConfigDir()) {
            log.error("Config directory error: {}", CONFIG_DIR);
			System.exit(0);
		}
		
		//load application and db props from servlet root and $HOME
        new SpringApplicationBuilder(ButlerService.class)
				.properties("host".equals(containerType)?"spring.config.location:classpath:/," + CONFIG_DIR:"")
				.build()
				.run(args);
	}
	
	//create all the necessary dirs if non-existent
	private static boolean validateConfigDir() {
		File folder = new File(CONFIG_DIR);
		if(!folder.exists()) {
			log.debug("Creating Config directory..");
			return folder.mkdirs();
		}
		log.debug("Config directory found!");
		return true;
	}
}

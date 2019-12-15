package com.automation.butler;

import java.io.File;

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
public class ButlerMicroservice {

	private static final String CONFIG_DIR = System.getenv("HOME") + "/.homeauto/config/";
	
	public static void main(String[] args) {
		
		//Terminate program if loading config dir encounters issues
		if(!validateConfigDir()) {
			System.out.print("Config directory error: " + CONFIG_DIR);
			System.exit(0);
		}
		
		//load application and db props from servlet root and $HOME
		new SpringApplicationBuilder(ButlerMicroservice.class)
				.properties("spring.config.name:application,db", "spring.config.location:classpath:/," + CONFIG_DIR)
				.build()
				.run(args);
	}
	
	//create all the necessary dirs if non-existent
	private static boolean validateConfigDir() {
		File folder = new File(CONFIG_DIR);
		if(!folder.exists()) {
			return folder.mkdirs();
		}
		return true;
	}
}

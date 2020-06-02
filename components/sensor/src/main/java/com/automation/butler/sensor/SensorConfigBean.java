package com.automation.butler.sensor;

import com.automation.butler.sensorlookup.SensorLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This class is responsible for loading all configurations for all sensors
 * into one bean by reading the properties file created by default for each
 * registered device 
 */

@Slf4j
@Component
public class SensorConfigBean {

	private final SensorLookupService lookupService;

	private static final String CONFIG_DIR = System.getenv("HOME") + "/.homeauto/config/";
	private ConcurrentMap<String, Properties> allProperties;
	
	@Autowired
	public SensorConfigBean(SensorLookupService lookupService) {
		this.lookupService = lookupService;
		this.allProperties = new ConcurrentHashMap<>();
		loadConfig();
	}

	private void loadConfig() {
		lookupService.findAll().forEach(x -> {
			String name = x.getAlias();
			Properties props = new Properties();
			try {
				if (new File(CONFIG_DIR).exists()) {
					props.load(new FileInputStream(CONFIG_DIR + name + ".properties"));
					log.info("Loaded configuration for {}", name);
					allProperties.put(name, props);
				}else {
					new File(CONFIG_DIR).mkdirs();
				}
			} catch (FileNotFoundException e) {
				log.debug("File not found {}{}.properties", CONFIG_DIR, name);
			} catch (IOException e) {
				log.debug("IO Exception {}", e.getMessage());
			}
		});
	}

	public ConcurrentMap<String, Properties> getAllProperties() {
		return this.allProperties;
	}

	public void setAllProperties(ConcurrentMap<String, Properties> allProperties) {
		this.allProperties = allProperties;
	}
	
}

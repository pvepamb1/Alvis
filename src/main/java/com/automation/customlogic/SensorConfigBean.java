package com.automation.customlogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automation.service.SensorLookupService;

/**
 * This class is responsible for loading all configurations for all sensors
 * into one bean by reading the properties file created by default for each
 * registered device 
 */

@Component
public class SensorConfigBean {

	private final SensorLookupService lookupService;

	private ConcurrentHashMap<String, Properties> allProperties;

	private static final String configDir = System.getenv("HOME") + "/.homeauto/config/";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SensorConfigBean.class);
	
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
				if(new File(configDir).exists()) {
					props.load(	new FileInputStream(configDir + name + ".properties"));
					LOGGER.info("Loaded configuration for {}", name);
					allProperties.put(name, props);
				}else {
					new File(configDir).mkdirs();
				}
			} catch (FileNotFoundException e) {
				LOGGER.warn("File not found {}{}.properties", configDir,name );
			} catch (IOException e) {
				LOGGER.warn("IO Exception {}", e.getMessage());
			}
		});
	}

	public Map<String, Properties> getAllProperties() {
		return this.allProperties;
	}

	public void setAllProperties(ConcurrentHashMap<String, Properties> allProperties) {
		this.allProperties = allProperties;
	}
	
}

package com.automation.customlogic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automation.service.SensorLookupService;

@Component
public class SensorConfigBean {

	SensorLookupService lookupService;

	private Map<String, Properties> allProperties;

	private static final String configDir = System.getenv("HOME") + "/.homeauto/config/";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SensorConfigBean.class);
	
	@Autowired
	public SensorConfigBean(SensorLookupService lookupService, Map<String, Properties> allProperties) {
		this.lookupService = lookupService;
		this.allProperties = allProperties;
		loadConfig();
	}

	public void loadConfig() {
		lookupService.findAll().forEach(x -> {
			String name = x.getAlias();
			Properties props = new Properties();
			try {
				props.load(	new FileInputStream(configDir + name + ".properties"));
				LOGGER.info("Loaded configuration for {}", name);
				allProperties.put(name, props);
			} catch (FileNotFoundException e) {
				LOGGER.warn("File not found {}{}.properties", configDir,name );
			} catch (IOException e) {
				LOGGER.warn("IO Exception {}", e.getMessage());
			}
		});
	}

	public Map<String, Properties> getAllProperties() {
		return allProperties;
	}

	public void setAllProperties(Map<String, Properties> allProperties) {
		this.allProperties = allProperties;
	}
	
}

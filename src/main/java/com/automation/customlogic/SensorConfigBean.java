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

import com.automation.repository.SensorTypeLookupRepository;

@Component
public class SensorConfigBean {

	SensorTypeLookupRepository repo;

	private Map<String, Properties> allProperties;

	private static final String configDir = "../config/";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SensorConfigBean.class);
	
	@Autowired
	public SensorConfigBean(SensorTypeLookupRepository repo, Map<String, Properties> allProperties) {
		this.repo = repo;
		this.allProperties = allProperties;
		loadConfig();
	}

	public void loadConfig() {
		repo.findAll().forEach(x -> {
			String name = x.getId().getMac().getMac().replaceAll(":", "") + x.getId().getId();
			Properties props = new Properties();
			try {
				props.load(	new FileInputStream(configDir + name + ".properties"));
				LOGGER.info("Loaded configuration for {}", name);
			} catch (FileNotFoundException e) {
				LOGGER.warn("File not found {}{}.properties", configDir,name );
			} catch (IOException e) {
				LOGGER.warn("IO Exception {}", e.getMessage());
			}
			allProperties.put(name, props);
		});
	}

	public Map<String, Properties> getAllProperties() {
		return allProperties;
	}

	public void setAllProperties(Map<String, Properties> allProperties) {
		this.allProperties = allProperties;
	}
	
}

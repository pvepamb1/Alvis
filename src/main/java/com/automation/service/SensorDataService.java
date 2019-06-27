package com.automation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.automation.embeddable.SensorLookupID;
import com.automation.enums.SensorType;
import com.automation.factory.SimpleControllerFactory;
import com.automation.sensor.Sensor;
import com.automation.table.SensorLookup;
import com.automation.util.IDWrapper;

@Service
public class SensorDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SensorDataService.class);

	private DeviceAddressService macService;
	private SensorLookupService lookupService;

	@Autowired
	public SensorDataService(DeviceAddressService macService, SensorLookupService lookupService) {
		this.lookupService = lookupService;
		this.macService = macService;
	}

	public void updateData(@RequestBody Sensor body) {
		SensorLookupID id = new SensorLookupID(body);
		if (lookupService.existsById(id)) {
			SensorType type = lookupService.findById(id).get().getType();
			if (type != null) {
				SimpleControllerFactory.getController(type).updateData(body);
			}
		}
	}

	public Iterable<SensorLookup> retrieveUnmapped() {
		macService.retrieveAll().forEach(
				x -> new RestTemplate().getForObject("http://" + x.getIp() + "/ids", IDWrapper.class).forEach(y -> {
					if (!lookupService.existsById(x, y)) {
						lookupService.saveUnmapped(x, y);
					}
				}));
		return lookupService.findByType(null);
	}

	public void updateLookup(@RequestBody List<SensorLookup> body) {
		for (SensorLookup sensorData : body) {
			lookupService.save(sensorData);
			SimpleControllerFactory.getController(sensorData.getType()).createConfigFile(sensorData);
		}
	}

	public Iterable<SensorLookup> retrieveMapped() {
		return lookupService.findAll();
	}

	public String getSensorPage(@PathVariable String alias) {
		String ip = macService.retrieve(lookupService.findByAlias(alias).get().getId().getAddress().getMac()).get()
				.getIp();
		return new RestTemplate().getForObject("http://" + ip + "/", String.class);
	}

	public SensorType[] retrieveTypes() {
		return SensorType.values();
	}

}

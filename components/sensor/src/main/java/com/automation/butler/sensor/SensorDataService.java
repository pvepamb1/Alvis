package com.automation.butler.sensor;

import java.util.List;
import java.util.Optional;

import com.automation.butler.deviceaddress.DeviceAddressService;
import com.automation.butler.enums.SensorType;
import com.automation.butler.sensorlookup.SensorLookup;
import com.automation.butler.sensorlookup.SensorLookupService;
import com.automation.butler.util.IDWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class SensorDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SensorDataService.class);

	private final DeviceAddressService macService;
	private final SensorLookupService lookupService;

	@Autowired
	public SensorDataService(DeviceAddressService macService, SensorLookupService lookupService) {
		this.lookupService = lookupService;
		this.macService = macService;
	}

	public void updateData(JsonNode body) {
		Optional<SensorLookup> sensor = lookupService.findByMacAndId(body.path("mac").asText(),
				body.path("id").asText());
		if (sensor.isPresent()) {
			SensorType type = sensor.get().getType();
			if (type != null) {
				SimpleControllerFactory.getController(type).updateData(body);
			}
		}
	}

	//Need to handle null pointer exception for that foreach
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
		Optional<String> ip = lookupService.findIpByAlias(alias);
		if(ip.isPresent())
			return new RestTemplate().getForObject("http://" + ip + "/", String.class);
		else
			return "Invalid sensor";
	}

	public SensorType[] retrieveTypes() {
		return SensorType.values();
	}

}

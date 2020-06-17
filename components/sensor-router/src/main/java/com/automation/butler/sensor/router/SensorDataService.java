package com.automation.butler.sensor.router;

import com.automation.butler.deviceaddress.DeviceAddress;
import com.automation.butler.enums.SensorType;
import com.automation.butler.sensorlookup.SensorLookup;
import com.automation.butler.sensorlookup.SensorLookupService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class SensorDataService {

	private final SensorLookupService lookupService;

	@Autowired
	public SensorDataService(SensorLookupService lookupService) {
		this.lookupService = lookupService;
	}

	void updateData(JsonNode body) {
		Optional<SensorLookup> sensor = lookupService.findByMacAndId(body.path("mac").asText(),
				body.path("id").asText());
		if (sensor.isPresent()) {
			SensorType type = sensor.get().getType();
			if (type != null) {
				SensorDataRouter.getService(type).save(body);
			}
		}
	}

	Iterable<SensorLookup> retrieveUnmapped() {
		return lookupService.findByType(null);
	}

	int createUnmappedIfIdNonExistent(DeviceAddress address, List<String> ids) {
		int count = 0;
		for (String id : ids) {
			if (!lookupService.existsById(address, id)) {
				lookupService.saveUnmapped(address, id);
				count++;
			}
		}
		return count;
	}

	List<Optional<SensorLookup>> getSensorLookups(String mac) {
		return lookupService.findByMac(mac);
	}

	void updateLookup(@RequestBody List<SensorLookup> body) {
		for (SensorLookup sensorData : body) {
			lookupService.save(sensorData);
		}
	}

	Iterable<SensorLookup> retrieveMapped() {
		return lookupService.findAll();
	}

	String getSensorPage(@PathVariable String alias) {
		Optional<String> ip = lookupService.findIpByAlias(alias);
		if(ip.isPresent())
			return new RestTemplate().getForObject("http://" + ip + "/", String.class);
		else
			return "Invalid sensor";
	}

	SensorType[] retrieveTypes() {
		return SensorType.values();
	}
}

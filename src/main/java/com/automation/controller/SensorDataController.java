package com.automation.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.automation.embeddable.Sensor;
import com.automation.embeddable.SensorTypeLookupID;
import com.automation.enums.SensorType;
import com.automation.factory.SimpleControllerFactory;
import com.automation.repository.SensorTypeLookupRepository;
import com.automation.service.DeviceAddressService;
import com.automation.table.SensorTypeLookup;
import com.automation.util.IDWrapper;

@CrossOrigin
@RestController
public class SensorDataController {

	private DeviceAddressService macService;
	private SensorTypeLookupRepository repo;
	private RestlessController controller;

	@Autowired
	public SensorDataController(DeviceAddressService macService, SensorTypeLookupRepository repo,
			RestlessController controller) {
		this.repo = repo;
		this.controller = controller;
		this.macService = macService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sensordata")
	public void updateData(@RequestBody Sensor body) {
		SensorType type = null;
		SensorTypeLookupID id = new SensorTypeLookupID(body);
		if (repo.existsById(id)) {
			type = repo.findById(id).get().getType();
			if (type != null) {
				controller = SimpleControllerFactory.getController(type);
				controller.updateData(body);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public Iterable<SensorTypeLookup> retrieveUnmapped() {
		macService.retrieveAll().forEach(
				x -> new RestTemplate().getForObject("http://" + x.getIp() + "/idlist", IDWrapper.class).forEach(y -> {
					if (!repo.existsById(new SensorTypeLookupID(x, y))) {
						repo.save(new SensorTypeLookup(new SensorTypeLookupID(x, y), null));
					}
				}));
		return repo.findByType(null);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update")
	public void updateLookup(@RequestBody List<SensorTypeLookup> body) {
		for (SensorTypeLookup sensorData : body) {
			repo.save(sensorData);
			SimpleControllerFactory.getController(sensorData.getType()).createConfigFile(sensorData);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/devices")
	public Iterable<SensorTypeLookup> retrieveMapped() {
		return repo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/fetchtype")
	public SensorType[] retrieveTypes() {
		return SensorType.values();
	}

}

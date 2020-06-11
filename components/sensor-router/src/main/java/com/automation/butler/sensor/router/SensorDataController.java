package com.automation.butler.sensor.router;

import com.automation.butler.deviceaddress.DeviceAddress;
import com.automation.butler.deviceaddress.DeviceAddressService;
import com.automation.butler.enums.SensorType;
import com.automation.butler.sensorlookup.SensorLookup;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/sensors")
public class SensorDataController {

	private final SensorDataService service;
	private final DeviceAddressService addressService;

	@Autowired
	public SensorDataController(SensorDataService service, DeviceAddressService addressService) {
		this.service = service;
		this.addressService = addressService;
	}

	//the following is garbage code. No denying it, it's pure trash. Will revisit later to fix
	@PostMapping(value = "/config")
	public List<JsonNode> getConfig(@RequestBody ConfigRequest configRequest) {
		List<JsonNode> configList = new ArrayList<>();
		DeviceAddress address = new DeviceAddress(configRequest.getMac(), configRequest.getIp(), null);
		addressService.store(address);
		service.createUnmappedIfIdNonExistent(address, configRequest.getIds());
		//wait for updatelookup to finish
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException ex) {

		}
		//get sensorlookup
		List<Optional<SensorLookup>> optionals = service.getSensorLookups(configRequest.getMac());
		//for each based on type send alias to type service and add return to list
		for (Optional<SensorLookup> sensorLookup : optionals) {
			if (sensorLookup.isPresent()) {
				SensorType type = sensorLookup.get().getType();
				if (type != null) {
					configList.add(SimpleControllerFactory.getController(type).getConfigByAlias(sensorLookup.get().getAlias()));
				}
			}
		}
		return configList;
	}

	@PostMapping
	public void updateData(@RequestBody JsonNode body) {
		service.updateData(body);
	}

	@GetMapping(value = "/unmapped")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<SensorLookup> retrieveUnmapped() {
		return service.retrieveUnmapped();
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void updateLookup(@RequestBody List<SensorLookup> body) {
		service.updateLookup(body);
		//create default configs
		for (SensorLookup sensorLookup : body) {
			SimpleControllerFactory.getController(sensorLookup.getType()).createDefaultConfig(sensorLookup.getAlias());
		}
		synchronized (this) {
			notifyAll();
		}
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<SensorLookup> retrieveMapped() {
		return service.retrieveMapped();
	}

	@GetMapping(value = "/{alias}")
	@ResponseStatus(HttpStatus.OK)
	public String getSensorPage(@PathVariable String alias) {
		return service.getSensorPage(alias);
	}

	@GetMapping(value = "/types")
	@ResponseStatus(HttpStatus.OK)
	public SensorType[] retrieveTypes() {
		return service.retrieveTypes();
	}

}

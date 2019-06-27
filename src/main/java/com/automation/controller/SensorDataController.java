package com.automation.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.automation.enums.SensorType;
import com.automation.sensor.Sensor;
import com.automation.service.SensorDataService;
import com.automation.table.SensorLookup;

@CrossOrigin
@RestController
@RequestMapping("/sensors")
public class SensorDataController {

	private SensorDataService service;

	@Autowired
	public SensorDataController(SensorDataService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/data")
	public void updateData(@RequestBody Sensor body) {
		service.updateData(body);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/unmapped")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<SensorLookup> retrieveUnmapped() {
		return service.retrieveUnmapped();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/")
	@ResponseStatus(HttpStatus.OK)
	public void updateLookup(@RequestBody List<SensorLookup> body) {
		service.updateLookup(body);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<SensorLookup> retrieveMapped() {
		return service.retrieveMapped();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{alias}")
	@ResponseStatus(HttpStatus.OK)
	public String getSensorPage(@PathVariable String alias) {
		return service.getSensorPage(alias);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/types")
	@ResponseStatus(HttpStatus.OK)
	public SensorType[] retrieveTypes() {
		return service.retrieveTypes();
	}

}

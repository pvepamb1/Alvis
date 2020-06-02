package com.automation.butler.sensor;

import com.automation.butler.enums.SensorType;
import com.automation.butler.sensorlookup.SensorLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/sensors")
public class SensorDataController {

	private final SensorDataService service;

	@Autowired
	public SensorDataController(SensorDataService service) {
		this.service = service;
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

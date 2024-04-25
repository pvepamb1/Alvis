package com.alvis.router;

import com.alvis.deviceaddress.DeviceAddress;
import com.alvis.deviceaddress.DeviceAddressService;
import com.alvis.enums.SensorType;
import com.alvis.sensor.SensorConfig;
import com.alvis.sensorlookup.SensorLookup;
import com.alvis.sensorlookup.SensorLookupViews;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.alvis.router.SensorDataRouter.getConfigService;

@Slf4j
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
	//re-writing the client to poll periodically would be a better approach
	@PostMapping(value = "/config")
    @JsonView(SensorLookupViews.Id.class)
    public ResponseEntity<List<SensorConfig>> getConfig(@RequestBody ConfigRequest configRequest) {
        List<SensorConfig> configList = new ArrayList<>();
		DeviceAddress address = new DeviceAddress(configRequest.getMac(), configRequest.getIp(), null);
		addressService.store(address);
		service.createUnmappedIfIdNonExistent(address, configRequest.getIds());

		//get sensorlookup
		List<Optional<SensorLookup>> optionals = service.getSensorLookupsByMac(configRequest.getMac());
		//for each based on type send alias to type service and add return to list
		for (Optional<SensorLookup> sensorLookup : optionals) {
			if (sensorLookup.isPresent()) {
				SensorType type = sensorLookup.get().getType();
				if (type != null) {
                    configList.add(getConfigService(type).getConfigById(sensorLookup.get().getId()).orElse(null));
				} else {
					return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND); // all or nothing
				}
			}
		}
		return new ResponseEntity<>(configList, HttpStatus.OK);
	}

	@PostMapping
	public void updateData(@RequestBody JsonNode body) {
		service.updateData(body);
	}

	@GetMapping(value = "/unmapped") //needs to be secured
    @JsonView(SensorLookupViews.Address.class)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<SensorLookup> retrieveUnmapped() {
		return service.retrieveUnmapped();
	}

	@PutMapping //needs to be secured
	@ResponseStatus(HttpStatus.OK)
	public void updateLookup(@RequestBody List<SensorLookup> body) {
		service.updateLookup(body);
		//create default configs
		for (SensorLookup sensorLookup : body) {
			getConfigService(sensorLookup.getType()).createDefaultConfig(sensorLookup.getId());
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

	@GetMapping(value = "/{alias}/config")
    @JsonView(SensorLookupViews.OnlyConfig.class)
	@ResponseStatus(HttpStatus.OK)
	public SensorConfig getSensorConfig(@PathVariable String alias) {
		Optional<SensorLookup> optional = service.getSensorLookupByAlias(alias);
        return optional.flatMap(sensorLookup -> getConfigService(sensorLookup.getType())
                .getConfigById(sensorLookup.getId()))
				.orElse(null);
	}

	@PutMapping(value = "/{alias}/config")
	@ResponseStatus(HttpStatus.OK)
	public void updateSensorConfig(@PathVariable String alias, @RequestBody JsonNode body) throws JsonProcessingException {
		Optional<SensorLookup> optional = service.getSensorLookupByAlias(alias);
		if (optional.isPresent()) {
            getConfigService(optional.get().getType()).saveConfig(body, optional.get().getId());
			new RestTemplate().getForObject("http://" + optional.get().getId().getAddress().getIp() + "/reset", String.class);
		}
	}

	@GetMapping(value = "/types")
	@ResponseStatus(HttpStatus.OK)
	public SensorType[] retrieveTypes() {
		return service.retrieveTypes();
	}
}

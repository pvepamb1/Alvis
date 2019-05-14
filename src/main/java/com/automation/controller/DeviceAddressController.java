package com.automation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.automation.service.DeviceAddressService;
import com.automation.table.DeviceAddress;

@CrossOrigin
@RestController
public class DeviceAddressController {

	@Autowired
	private DeviceAddressService service;

	@RequestMapping(method = RequestMethod.POST, value = "/heartbeat")
	public void updatePulse(@RequestBody DeviceAddress body) {
		service.store(body);
	}

}

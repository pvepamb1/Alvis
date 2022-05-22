package com.automation.butler.deviceaddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class DeviceAddressController {

	@Autowired
	private DeviceAddressService service;

    @PostMapping("/heartbeat")
	public void updatePulse(@RequestBody DeviceAddress body) {
		if (service.exists(body))
			service.store(body);
	}

}

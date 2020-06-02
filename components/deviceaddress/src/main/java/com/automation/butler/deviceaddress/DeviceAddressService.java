package com.automation.butler.deviceaddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceAddressService {

	@Autowired
	private DeviceAddressRepository macRepository;

    void store(DeviceAddress address) {
		macRepository.save(address);
	}
	
	public Iterable<DeviceAddress> retrieveAll(){
		return macRepository.findAll();
	}
	
}
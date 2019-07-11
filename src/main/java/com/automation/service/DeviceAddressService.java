package com.automation.service;

import com.automation.domain.DeviceAddress;
import com.automation.repository.DeviceAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceAddressService {

	@Autowired
	private DeviceAddressRepository macRepository;
	
	public void store(DeviceAddress address) {
		macRepository.save(address);
	}
	
	public Iterable<DeviceAddress> retrieveAll(){
		return macRepository.findAll();
	}
	
}
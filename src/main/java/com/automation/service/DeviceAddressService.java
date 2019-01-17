package com.automation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.repository.DeviceAddressRepository;
import com.automation.table.DeviceAddress;

@Service
public class DeviceAddressService {

	@Autowired
	private DeviceAddressRepository macRepository;
	
	public void store(DeviceAddress mtip) {
		macRepository.save(mtip);
	}
	
	public Optional<DeviceAddress> retrieve(String macAddress) {
		return macRepository.findById(macAddress);
	}
	
	public Iterable<DeviceAddress> retrieveAll(){
		return macRepository.findAll();
	}
	
}
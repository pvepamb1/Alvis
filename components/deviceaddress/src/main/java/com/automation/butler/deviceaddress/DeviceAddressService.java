package com.automation.butler.deviceaddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceAddressService {

	@Autowired
	private DeviceAddressRepository macRepository;

    public void store(DeviceAddress address) {
		macRepository.save(address);
	}

    boolean exists(DeviceAddress address) {
        return macRepository.existsById(address.getMac());
    }
	
	public Iterable<DeviceAddress> retrieveAll(){
		return macRepository.findAll();
	}
	
}
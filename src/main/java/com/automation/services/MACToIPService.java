package com.automation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.repositories.MACToIPRepository;
import com.automation.tables.MACToIPMap;

@Service
public class MACToIPService {

	@Autowired
	private MACToIPRepository macService;
	
	public void store(MACToIPMap mtip) {
		macService.save(mtip);
	}
	
	public String retrieve(String macAddress) {
		return macService.findById(macAddress).get().getIPAddress();
	}
	
	public Iterable<MACToIPMap> retrieveAll(){
		return macService.findAll();
	}
	
}
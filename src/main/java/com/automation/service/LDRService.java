package com.automation.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.repository.LDRRepository;
import com.automation.sensor.LDR;

@Service
public class LDRService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LDRService.class);

	private LDRRepository ldrRepository;
	
	@Autowired
	public LDRService(LDRRepository ldrRepository) {
		this.ldrRepository = ldrRepository;
	}

	public void store(LDR value) {
		LOGGER.info("Storing {}", value);
		ldrRepository.save(value);
	}

	public Optional<LDR> retrieve(String macAddress) {
		return ldrRepository.findById(macAddress);
	}

	public Iterable<LDR> retrieveAll() {
		return ldrRepository.findAll();
	}

}

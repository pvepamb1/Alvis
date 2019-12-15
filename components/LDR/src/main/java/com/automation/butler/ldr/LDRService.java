package com.automation.butler.ldr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LDRService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LDRService.class);

	private final LDRRepository ldrRepository;
	
	@Autowired
	public LDRService(LDRRepository ldrRepository) {
		this.ldrRepository = ldrRepository;
	}

	public void store(LDRDTO value) {
		LOGGER.info("Storing {}", value);
		ldrRepository.save(value);
	}

}

package com.automation.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.domain.DeviceAddress;
import com.automation.domain.SensorLookup;
import com.automation.domain.embeddable.SensorLookupID;
import com.automation.enums.SensorType;
import com.automation.repository.SensorLookupRepository;

@Service
public class SensorLookupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SensorLookupService.class);

	private final SensorLookupRepository repository;

	@Autowired
	public SensorLookupService(SensorLookupRepository repository) {
		this.repository = repository;
	}

	boolean existsById(DeviceAddress address, String id) {
		return repository.existsById(new SensorLookupID(address, id));
	}

	public Iterable<SensorLookup> findAll() {
		return repository.findAll();
	}

	void save(SensorLookup lookup) {
		LOGGER.info("Storing sensor info {} type {} and alias {}", lookup.getId(), lookup.getType(), lookup.getAlias());
		repository.save(lookup);
	}

	Iterable<SensorLookup> findByType(SensorType type) {
		return repository.findByType(type);
	}

	void saveUnmapped(DeviceAddress address, String id) {
		repository.saveUnmapped(address.getMac(), id);
	}

	public Optional<SensorLookup> findById(DeviceAddress address, String id) {
		return repository.findById(new SensorLookupID(address, id));
	}

	Optional<SensorLookup> findByMacAndId(String mac, String id) {
		return repository.findByMacAndId(mac, id);
	}
	
	Optional<String> findIpByAlias(String alias){
		return repository.findIpByAlias(alias);
	}

}

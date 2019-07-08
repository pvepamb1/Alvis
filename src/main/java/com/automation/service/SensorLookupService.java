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

	private SensorLookupRepository repository;

	@Autowired
	public SensorLookupService(SensorLookupRepository repository) {
		this.repository = repository;
	}

	public boolean existsById(SensorLookupID id) {
		return repository.existsById(id);
	}

	public boolean existsById(DeviceAddress address, String id) {
		return this.existsById(new SensorLookupID(address, id));
	}

	public Iterable<SensorLookup> findAll() {
		return repository.findAll();
	}

	public void save(SensorLookup lookup) {
		LOGGER.info("Storing sensor info {} type {} and alias {}", lookup.getId(), lookup.getType(), lookup.getAlias());
		repository.save(lookup);
	}

	public Iterable<SensorLookup> findByType(SensorType type) {
		return repository.findByType(type);
	}

	public void saveUnmapped(DeviceAddress address, String id) {
		repository.saveUnmapped(address.getMac(), id);
	}

	public Optional<SensorLookup> findById(SensorLookupID id) {
		return repository.findById(id);
	}

	public Optional<SensorLookup> findById(DeviceAddress address, String id) {
		return this.findById(new SensorLookupID(address, id));
	}

	public Optional<SensorLookup> findByAlias(String alias) {
		return repository.findByAlias(alias);
	}
	
	public Optional<SensorLookup> findByMacAndId(String mac, String id) {
		return repository.findByMacAndId(mac, id);
	}
	
	public Optional<String> findIpByAlias(String alias){
		return repository.findIpByAlias(alias);
	}

}

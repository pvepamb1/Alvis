package com.automation.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.embeddable.SensorLookupID;
import com.automation.enums.SensorType;
import com.automation.repository.SensorLookupRepository;
import com.automation.table.DeviceAddress;
import com.automation.table.SensorLookup;

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
		return repository.existsById(new SensorLookupID(address, id));
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
		repository.save(new SensorLookup(new SensorLookupID(address, id), null, null));
	}

	public Optional<SensorLookup> findById(SensorLookupID id) {
		return repository.findById(id);
	}

	public Optional<SensorLookup> findById(DeviceAddress address, String id) {
		return repository.findById(new SensorLookupID(address, id));
	}

	public Optional<SensorLookup> findByAlias(String alias) {
		return repository.findByAlias(alias);
	}

}

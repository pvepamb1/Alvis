package com.automation.butler.sensorlookup;

import com.automation.butler.deviceaddress.DeviceAddress;
import com.automation.butler.enums.SensorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SensorLookupService {

    private final SensorLookupRepository repository;

    @Autowired
    public SensorLookupService(SensorLookupRepository repository) {
        this.repository = repository;
    }

    public boolean existsById(DeviceAddress address, String id) {
        return repository.existsById(new SensorLookupID(address, id));
    }

    public Iterable<SensorLookup> findAll() {
        return repository.findAll();
    }

    public void save(SensorLookup lookup) {
        log.info("Storing sensor info {} type {} and alias {}", lookup.getId(), lookup.getType(), lookup.getAlias());
        repository.save(lookup);
    }

    public Iterable<SensorLookup> findByType(SensorType type) {
        return repository.findByType(type);
    }

    public void saveUnmapped(DeviceAddress address, String id) {
        repository.saveUnmapped(address.getMac(), id);
    }

    public Optional<SensorLookup> findById(DeviceAddress address, String id) {
        return repository.findById(new SensorLookupID(address, id));
    }

    public Optional<SensorLookup> findByMacAndId(String mac, String id) {
        return repository.findByMacAndId(mac, id);
    }

    public Optional<String> findIpByAlias(String alias) {
        return repository.findIpByAlias(alias);
    }

}

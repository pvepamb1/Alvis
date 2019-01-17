package com.automation.repository;

import org.springframework.data.repository.CrudRepository;

import com.automation.table.DeviceAddress;

public interface DeviceAddressRepository extends CrudRepository<DeviceAddress, String> {

}
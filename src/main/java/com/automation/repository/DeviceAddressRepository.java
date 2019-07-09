package com.automation.repository;

import org.springframework.data.repository.CrudRepository;

import com.automation.domain.DeviceAddress;

/**
 * Specifies methods used to obtain and modify device/sensor related information
 * which is stored in the database.
 */

public interface DeviceAddressRepository extends CrudRepository<DeviceAddress, String> {

}
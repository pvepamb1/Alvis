package com.automation.butler.deviceaddress;

import org.springframework.data.repository.CrudRepository;

/**
 * Specifies methods used to obtain and modify device/sensor related information
 * which is stored in the database.
 */

interface DeviceAddressRepository extends CrudRepository<DeviceAddress, String> {

}
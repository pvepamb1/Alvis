package com.automation.repository;

import org.springframework.data.repository.CrudRepository;

import com.automation.domain.sensor.LDR;

/**
 * Specifies methods used to obtain and modify LDR related information
 * which is stored in the database.
 */

public interface LDRRepository extends CrudRepository<LDR, String> {

}

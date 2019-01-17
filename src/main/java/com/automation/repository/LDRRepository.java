package com.automation.repository;

import org.springframework.data.repository.CrudRepository;

import com.automation.sensor.LDR;

public interface LDRRepository extends CrudRepository<LDR, String> {

}

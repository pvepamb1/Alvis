package com.automation.repositories;

import org.springframework.data.repository.CrudRepository;

import com.automation.sensors.WaterflowSensor;

public interface WaterflowRepository extends CrudRepository<WaterflowSensor, String>{

}

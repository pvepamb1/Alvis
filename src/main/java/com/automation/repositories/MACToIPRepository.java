package com.automation.repositories;

import org.springframework.data.repository.CrudRepository;

import com.automation.tables.MACToIPMap;

public interface MACToIPRepository extends CrudRepository<MACToIPMap, String> {

}
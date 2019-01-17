package com.automation.repository;

import org.springframework.data.repository.CrudRepository;

import com.automation.table.User;

public interface UserRepository extends CrudRepository<User, String> {

}

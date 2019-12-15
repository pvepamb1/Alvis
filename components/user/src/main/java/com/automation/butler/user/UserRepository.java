package com.automation.butler.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDTO, String> {

}

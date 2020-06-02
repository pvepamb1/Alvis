package com.automation.butler.user;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<UserDTO, String> {

}

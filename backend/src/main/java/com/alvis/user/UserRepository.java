package com.alvis.user;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<UserDTO, String> {

}

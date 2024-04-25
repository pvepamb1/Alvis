package com.alvis.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository repository;

    void storeOrUpdate(UserDTO userDTO) {
		userDTO.setName(userDTO.getName().trim().toLowerCase(Locale.ENGLISH));
		userDTO.setEmail(userDTO.getEmail().trim().toLowerCase(Locale.ENGLISH));
        log.debug("Storing {}", userDTO);
		repository.save(userDTO);
	}

    Optional<UserDTO> retrieveUserDetails(String name) {
		return repository.findById(name);
	}

	public Iterable<UserDTO> retrieveAll() {
		return repository.findAll();
	}

	public void deleteUser(String name) {
		repository.deleteById(name);
	}

}

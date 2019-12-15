package com.automation.butler.user;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping
	public void storeUserDetails(@RequestBody UserDTO userDTO) {
		LOGGER.info("Storing user information for {}", userDTO.getName());
		service.storeOrUpdate(userDTO);
	}
	
	public Optional<UserDTO> getUserCreds(String name){
		return service.retrieveUserDetails(name);
	}
	
}

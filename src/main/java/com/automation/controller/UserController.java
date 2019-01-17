package com.automation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.automation.service.UserService;
import com.automation.table.User;

@CrossOrigin
@RestController
public class UserController {

	private UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public void storeUserDetails(@RequestBody User user) {
		service.storeOrUpdate(user);
	}
	
	public Optional<User> getUserCreds(String name){
		return service.retrieveUserDetails(name);
	}
	
}

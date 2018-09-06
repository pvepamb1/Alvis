package com.automation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.automation.notifications.Mailer;

@RestController
public class LDRController {
	
	@Autowired
	Mailer mailer;
	
	@RequestMapping(method = RequestMethod.POST, value = "/LDR")
	public String test(@RequestBody String body) {
		System.out.println(body);
		mailer.sendSimpleMessage("homeautomation137@gmail.com", "LDR", "LDR..");
		return "success";
	}

}

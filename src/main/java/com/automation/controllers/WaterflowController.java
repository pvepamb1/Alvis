package com.automation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.automation.notifications.Mailer;
import com.automation.sensors.WaterflowSensor;
import com.automation.services.WaterflowService;

@RestController
public class WaterflowController {
	
	@Autowired
	Mailer mailer;
	
	@Autowired
	WaterflowService service;

	@RequestMapping(method=RequestMethod.POST, value = "/waterflow")
	public String test(@RequestBody String body) {
		System.out.println(body);
		mailer.sendSimpleMessage("homeautomation137@gmail.com", "Testing", "Whatevs, yo!");
		return "hi";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add2")
	public String add(@RequestBody String body) {
		String[] arr = body.split(" ");
		return service.store(new WaterflowSensor(arr[0], Integer.parseInt(arr[1]), arr[2]));
	}
}

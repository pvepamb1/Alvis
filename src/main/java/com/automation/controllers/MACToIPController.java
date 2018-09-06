package com.automation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.automation.services.MACToIPService;
import com.automation.tables.MACToIPMap;

@RestController
public class MACToIPController {

	@Autowired
	private MACToIPService service;
	
	@RequestMapping(method=RequestMethod.POST, value="/heartbeat")
	public String sayHi(@RequestBody String body) {
		System.out.println(body);
		return "hi";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public String add(@RequestBody String body) {
		System.out.println(body);
		String[] arr = body.split(" ");
		service.store(new MACToIPMap(arr[0], arr[1]));
		return "hi";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/get")
	public String get(@RequestBody String macAddress) {
		return service.retrieve(macAddress);
	}
}

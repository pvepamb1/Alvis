package com.automation.scheduledtasks;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.automation.services.MACToIPService;
import com.automation.tables.MACToIPMap;

public class HeartbeatCheck {
	
	@Autowired
	MACToIPService service;
	
	private static final long checkDelay = 6;
	
	@Scheduled(fixedDelay=60000)
	public void checkStatus() {
		for(MACToIPMap mtip : service.retrieveAll()) {
			if(ChronoUnit.MINUTES.between(mtip.getUpdateDateTime(), LocalDateTime.now()) > checkDelay) {
				System.out.println("Yo! I can't him foo!");
			}
		}
	}

}

package com.automation.butler.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mailer{

	private final JavaMailSender emailSender;
	
	@Autowired
	public Mailer(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}
	
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(subject);
		message.setText(text);
		message.setTo(to);
		emailSender.send(message);
	}
	
}

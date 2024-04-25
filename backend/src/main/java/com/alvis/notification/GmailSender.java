package com.alvis.notification;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class GmailSender extends JavaMailSenderImpl {

	@Override
	public int getPort() {
		return 587; // will add ability to configure via external props file later.
					// return customPort || 587. Should be simple [nothing's been simple so far :\]
	}

	@Override
	public String getHost() {
		return "smtp.gmail.com";
	}

	@Override
	public Properties getJavaMailProperties() {

		Properties properties = new Properties();

		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

		return properties;
	}
}

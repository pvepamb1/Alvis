package com.alvis.notification;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class YahooSender extends JavaMailSenderImpl {

	@Override
	public int getPort() {
		return 587;
	}

	@Override
	public String getHost() {
		return "smtp.yahoo.com";
	}

	@Override
	public Properties getJavaMailProperties() {

		Properties properties = new Properties();

		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.yahoo.com");

		return properties;
	}
}

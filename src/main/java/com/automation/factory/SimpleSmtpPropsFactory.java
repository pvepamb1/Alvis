package com.automation.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.automation.enums.SMTP;
import com.automation.notification.GmailSender;
import com.automation.notification.YahooSender;

public class SimpleSmtpPropsFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSmtpPropsFactory.class);

	public static JavaMailSenderImpl getSmtpProps(SMTP host) {

		LOGGER.debug("Returning smtp properties for {}", host);

		if (host != null) {
			switch (host) {
			case GMAIL:
				return new GmailSender();
			case YAHOO:
				return new YahooSender();
			default:
				return null;
			}
		}

		LOGGER.warn("Unidentified SMTP type");

		return null;
	}

	public static JavaMailSenderImpl getSmtpPropsByString(String email) {
		return getSmtpProps(getSmtpType(email));
	}

	public static SMTP getSmtpType(String email) {

		LOGGER.debug("Returning the smtp type for {}", email);

		Matcher matcher = Pattern.compile("@(.*?).").matcher(email);

		if (matcher.find()) {
			try {
				return SMTP.valueOf(matcher.group(1).toUpperCase());
			} catch (Exception e) {
				LOGGER.warn("{} for {}", e.getMessage(), email);
			}
		}

		return null;
	}
}

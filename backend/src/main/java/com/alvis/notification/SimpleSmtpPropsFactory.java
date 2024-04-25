package com.alvis.notification;

import com.alvis.enums.SMTP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
class SimpleSmtpPropsFactory {

	private SimpleSmtpPropsFactory() {
	}

	private static JavaMailSenderImpl getSmtpProps(SMTP host) {
		log.debug("Returning smtp properties for {}", host);

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

		log.debug("Unidentified SMTP type");
		return null;
	}

	static JavaMailSenderImpl getSmtpPropsByString(String email) {
		return getSmtpProps(getSmtpType(email));
	}

	private static SMTP getSmtpType(String email) {
		log.debug("Returning the smtp type for {}", email);
		Matcher matcher = Pattern.compile("@(.*?)\\.").matcher(email);
		if (matcher.find()) {
			try {
				return SMTP.valueOf(matcher.group(1).toUpperCase());
			} catch (Exception e) {
				log.debug("{} for {}", e.getMessage(), email);
			}
		}
		return null;
	}
}

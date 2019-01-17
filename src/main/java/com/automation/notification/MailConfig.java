package com.automation.notification;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.automation.controller.UserController;
import com.automation.enums.SMTP;
import com.automation.factory.SimpleSmtpPropsFactory;
import com.automation.table.User;

@Configuration
public class MailConfig {

	@Autowired
	ApplicationContext ctx;
	@Autowired
	UserController userController;
	@Value("${dbuser:}")
	String username;

	@Bean
	public JavaMailSender javaMailService() {
		
		JavaMailSenderImpl jSenderImpl = new JavaMailSenderImpl();
		Optional<User> user = userController.getUserCreds(username);
		if(user.isPresent()) {
			jSenderImpl.setUsername(user.get().getEmail());
			jSenderImpl.setPassword(user.get().getPassword());
			configProps(SimpleSmtpPropsFactory.getSmtpType(user.get().getEmail()));
		}
		return jSenderImpl;
	}

	public void configProps(SMTP host) {
		JavaMailSenderImpl jSenderImpl = ctx.getBean(JavaMailSenderImpl.class);
		JavaMailSenderImpl senderConfig = SimpleSmtpPropsFactory.getSmtpProps(host);
		jSenderImpl.setHost(senderConfig.getHost());
		jSenderImpl.setPort(senderConfig.getPort());
		jSenderImpl.setJavaMailProperties(senderConfig.getJavaMailProperties());
	}

}

package com.automation.butler.notification;

import java.util.Optional;

import com.automation.butler.user.UserDTO;
import com.automation.butler.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Autowired
	ApplicationContext ctx;
	@Autowired
	private
	UserController userController;
	@Value("${dbuser:}")
	private
	String username;

	@Bean
	public JavaMailSender javaMailService() {
		
		JavaMailSenderImpl jSenderImpl = new JavaMailSenderImpl();
		Optional<UserDTO> user = userController.getUserCreds(username);
		if(user.isPresent()) {
			jSenderImpl.setUsername(user.get().getEmail());
			jSenderImpl.setPassword(user.get().getPassword());
			JavaMailSenderImpl senderConfig = SimpleSmtpPropsFactory.getSmtpPropsByString(user.get().getEmail());
			jSenderImpl.setHost(senderConfig.getHost());
			jSenderImpl.setPort(senderConfig.getPort());
			jSenderImpl.setJavaMailProperties(senderConfig.getJavaMailProperties());
		}
		return jSenderImpl;
	}
}

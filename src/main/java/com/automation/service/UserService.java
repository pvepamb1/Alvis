package com.automation.service;

import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.automation.factory.SimpleSmtpPropsFactory;
import com.automation.repository.UserRepository;
import com.automation.table.User;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	ApplicationContext ctx;

	@Value("${dbuser:}")
	String username;

	public void storeOrUpdate(User user) {
		user.setName(user.getName().trim().toLowerCase(Locale.ENGLISH));
		user.setEmail(user.getEmail().trim().toLowerCase(Locale.ENGLISH));
		LOGGER.debug("Storing {}", user);
		repository.save(user);
		this.updateEmail(user);
	}

	public Optional<User> retrieveUserDetails(String name) {
		return repository.findById(name);
	}

	public Iterable<User> retrieveAll() {
		return repository.findAll();
	}

	public void deleteUser(String name) {
		repository.deleteById(name);
	}

	public void updateEmail(User user) {
		JavaMailSenderImpl jSenderImpl = ctx.getBean(JavaMailSenderImpl.class);
		if (user.getName().equals(username.trim().toLowerCase(Locale.ENGLISH))) {
			jSenderImpl.setUsername(user.getEmail());
			jSenderImpl.setPassword(user.getPassword());
			JavaMailSenderImpl senderConfig = SimpleSmtpPropsFactory.getSmtpPropsByString(user.getEmail());
			if (senderConfig != null) {
				jSenderImpl.setHost(senderConfig.getHost());
				jSenderImpl.setPort(senderConfig.getPort());
				jSenderImpl.setJavaMailProperties(senderConfig.getJavaMailProperties());
			}
		}
	}
}

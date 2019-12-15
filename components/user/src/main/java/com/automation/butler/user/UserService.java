package com.automation.butler.user;

import java.util.Locale;
import java.util.Optional;

//import com.automation.butler.notification.SimpleSmtpPropsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private ApplicationContext ctx;

	@Value("${dbuser:}")
	private String username;

	public void storeOrUpdate(UserDTO userDTO) {
		userDTO.setName(userDTO.getName().trim().toLowerCase(Locale.ENGLISH));
		userDTO.setEmail(userDTO.getEmail().trim().toLowerCase(Locale.ENGLISH));
		LOGGER.debug("Storing {}", userDTO);
		repository.save(userDTO);
		//this.updateEmail(user);
	}

	public Optional<UserDTO> retrieveUserDetails(String name) {
		return repository.findById(name);
	}

	public Iterable<UserDTO> retrieveAll() {
		return repository.findAll();
	}

	public void deleteUser(String name) {
		repository.deleteById(name);
	}

	/*private void updateEmail(User user) {
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
	}*/
}

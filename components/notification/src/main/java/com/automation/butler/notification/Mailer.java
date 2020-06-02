package com.automation.butler.notification;

import com.automation.butler.user.UserController;
import com.automation.butler.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class Mailer{

    @Autowired
    private UserController userController;

    @Value("${dbuser:}")
    private String username;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(to);
        JavaMailSender emailSender = fetchMailConfig();
        emailSender.send(message);
    }

    private JavaMailSender fetchMailConfig() {
        JavaMailSenderImpl jSenderImpl = new JavaMailSenderImpl();
        Optional<UserDTO> user = userController.getUserCreds(username);
        if (user.isPresent()) {
            log.info("Found {}", user.get().getEmail());
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

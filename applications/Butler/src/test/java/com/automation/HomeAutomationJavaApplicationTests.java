package com.automation;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.automation.butler.deviceaddress.DeviceAddress;
import com.automation.butler.deviceaddress.DeviceAddressService;
import com.automation.butler.notification.Mailer;
import com.automation.butler.scheduled.HeartbeatCheck;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:travis.properties")
@ContextConfiguration
public class HomeAutomationJavaApplicationTests {

	@Mock
	DeviceAddressService deviceAddressServiceMock;

	@Mock
	Mailer mailer;

	@InjectMocks
	HeartbeatCheck heartbeatCheck;

	@Test
	public void HeartbeatTest(){
		DeviceAddress address = new DeviceAddress("AB","12", LocalDateTime.now());
		address.setUpdateDateTime(LocalDateTime.now());
		when(deviceAddressServiceMock.retrieveAll()).thenReturn(Collections.singletonList(address));
		Logger LOGGER = (Logger)LoggerFactory.getLogger(HeartbeatCheck.class);
		ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
		listAppender.start();
		LOGGER.addAppender(listAppender);
		HeartbeatCheck.setCheckDelay(-60);
		heartbeatCheck.checkStatus();
		List<ILoggingEvent> logsList = listAppender.list;
		Assert.assertEquals("AB with ip address 12 couldn't be reached", logsList.get(0).getFormattedMessage());
	}
	@Test
	public void contextLoads() {
	}

}

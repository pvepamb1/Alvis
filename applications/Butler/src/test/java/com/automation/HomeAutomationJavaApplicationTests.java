package com.automation;

import com.automation.butler.deviceaddress.DeviceAddressService;
import com.automation.butler.notification.Mailer;
import com.automation.butler.scheduled.HeartbeatCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

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
		/*DeviceAddress address = new DeviceAddress("AB","12", LocalDateTime.now());
		address.setUpdateDateTime(LocalDateTime.now());
		when(deviceAddressServiceMock.retrieveAll()).thenReturn(Collections.singletonList(address));
		Logger LOGGER = (Logger)LoggerFactory.getLogger(HeartbeatCheck.class);
		ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
		listAppender.start();
		LOGGER.addAppender(listAppender);
		HeartbeatCheck.setCheckDelay(-60);
		heartbeatCheck.checkStatus();
		List<ILoggingEvent> logsList = listAppender.list;
		Assert.assertEquals("AB with ip address 12 couldn't be reached", logsList.get(0).getFormattedMessage());*/
	}
	@Test
	public void contextLoads() {
	}

}

package com.alvis;

import com.alvis.deviceaddress.DeviceAddressService;
import com.alvis.notification.Mailer;
import com.alvis.scheduled.HeartbeatCheck;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:travis.properties")
@ContextConfiguration
public class AlvisServiceTests {

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

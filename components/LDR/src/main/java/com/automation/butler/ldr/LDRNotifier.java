package com.automation.butler.ldr;

import com.automation.butler.notification.Mailer;
import com.automation.butler.sensor.SensorConfigBean;
import com.automation.butler.sensorlookup.SensorLookup;
import com.automation.butler.sensorlookup.SensorLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

@Component
public class LDRNotifier {

	@Value("${mailTo:}")
	private String to;
	private final Mailer mailer;
	private final SensorConfigBean configBean;
	private final SensorLookupService lookupService;
	private static final Logger LOGGER = LoggerFactory.getLogger(LDRNotifier.class);

	@Autowired
	public LDRNotifier(Mailer mailer, SensorConfigBean configBean, SensorLookupService lookupService) {
		this.mailer = mailer;
		this.configBean = configBean;
		this.lookupService = lookupService;
	}

	public void notifyUser(LDRDTO ldr) {

		String identifier = lookupService.findById(ldr.getMac(), ldr.getId()).get().getAlias();
		Properties prop = configBean.getAllProperties().get(identifier);
		if (prop != null) {
			if (Integer.parseInt(ldr.getValue()) > Integer.parseInt(prop.getProperty("max"))
					&& prop.getProperty("emailSent").equals("false")) {
				LOGGER.info("{} value above {} and haven't notified user previously. Notifying user at {}", identifier,
						prop.getProperty("max"), to);
				mailer.sendSimpleMessage(to, "LDR", ldr.getValue());
				writeToFile(identifier, prop, "true");

			}

			else if (Integer.parseInt(ldr.getValue()) < Integer.parseInt(prop.getProperty("min"))) {
				LOGGER.info("{} value below {}. resetting mail state to false", identifier, prop.getProperty("min"));
				writeToFile(identifier, prop, "false");
			}
		}
	}

	private void writeToFile(String identifier, Properties prop, String value) {
		OutputStream output;

		try {

			output = new FileOutputStream(System.getenv("HOME") + "/.homeauto/config/" + identifier + ".properties");
			prop.setProperty("emailSent", value);
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
			LOGGER.warn("{}", io.getMessage());
		}
	}
	
	public void createConfigFile(SensorLookup sensor) {
		OutputStream output;
		Properties prop = new Properties();
		String identifier = sensor.getAlias();

		try {

			output = new FileOutputStream(System.getenv("HOME") + "/.homeauto/config/" + identifier + ".properties");
			prop.setProperty("emailSent", "false");
			prop.setProperty("min", "200");
			prop.setProperty("max", "700");
			prop.store(output, null);
			configBean.getAllProperties().put(identifier, prop);

		} catch (IOException io) {
			io.printStackTrace();
			LOGGER.warn("{}", io.getMessage());
		}
	}
}

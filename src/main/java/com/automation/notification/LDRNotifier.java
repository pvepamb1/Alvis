package com.automation.notification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.automation.customlogic.SensorConfigBean;
import com.automation.sensor.LDR;

@Component
public class LDRNotifier {

	@Value("${mailTo:}")
	String to;
	Mailer mailer;
	SensorConfigBean configBean;

	@Autowired
	public LDRNotifier(Mailer mailer, SensorConfigBean configBean) {
		this.mailer = mailer;
		this.configBean = configBean;
	}

	public void notifyUser(LDR ldr) {

		String identifier = ldr.getSensor().getMac().getMac().replaceAll(":", "") + ldr.getSensor().getId();
		Properties prop = configBean.getAllProperties().get(identifier);
		if (prop != null) {
			if (Integer.parseInt(ldr.getValue()) > Integer.parseInt(prop.getProperty("max")) && prop.getProperty("emailSent").equals("false")) {

				mailer.sendSimpleMessage(to, "LDR", ldr.getValue());
				writeToFile(identifier, prop, "true");

			}

			else if (Integer.parseInt(ldr.getValue()) < Integer.parseInt(prop.getProperty("min"))) {
				writeToFile(identifier, prop, "false");
			}
		}
	}

	private void writeToFile(String identifier, Properties prop, String value) {
		OutputStream output = null;

		try {

			output = new FileOutputStream(identifier + ".properties");
			prop.setProperty("emailSent", value);
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}

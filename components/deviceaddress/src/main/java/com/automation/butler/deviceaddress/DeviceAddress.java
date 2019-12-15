package com.automation.butler.deviceaddress;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceAddress {

	@Id
	@Column(length = 17)
	private String mac;

	@Column(length = 15)
	private String ip;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	/*public DeviceAddress(String mac) {
		this.mac = mac;
	}

	public DeviceAddress(String mac, String ip) {
		this.mac = mac;
		this.ip = ip;
	}
*/
}

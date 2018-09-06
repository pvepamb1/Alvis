package com.automation.tables;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.UpdateTimestamp;

import com.automation.types.IPAddress;
import com.automation.types.MACAddress;

@Entity
public class MACToIPMap {

	//@Transient
	@Embedded
	private MACAddress macAddress;
	
	@Transient
	private IPAddress ip;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	public MACToIPMap() {
	}
	
	public MACToIPMap(String macAddressString, String  ipAddress) {
		this.macAddress = new MACAddress(macAddressString);
		this.ip = new IPAddress(ipAddress);
	}
	
	@Id
	@Column(name="MAC_ADDRESS")
	public String getMAC() {
		return this.macAddress.getMACAddressString();
	}
	
	//@Transient
	public void setMAC(String address) {
		this.macAddress.setMACAddressString(address);
	}
	
	@Transient
	public String getIPAddress() {
		return this.ip.getIpString();
	}
	
	@Transient
	public void setIPAddress(String address) {
		this.ip.setIpString(address);
	}
	
	/**
	 * @return the macAddress
	 */
	@Transient
	public MACAddress getMacAddress() {
		return macAddress;
	}

	/**
	 * @param macAddress the macAddress to set
	 */
	@Transient
	public void setMacAddress(MACAddress macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * @return the ip
	 */
	@Transient
	public IPAddress getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	@Transient
	public void setIp(IPAddress ip) {
		this.ip = ip;
	}

	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * @return the updateDateTime
	 */
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
}

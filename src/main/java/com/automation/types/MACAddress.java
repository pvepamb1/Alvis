package com.automation.types;

import javax.persistence.Embeddable;

@Embeddable
public class MACAddress {

	String MACAddressString;
	
	public MACAddress() {}
	
	public MACAddress(String MACAddressString) {
		this.MACAddressString = MACAddressString;
	}
	
	/**
	 * @return the mACAddressString
	 */
	public String getMACAddressString() {
		return MACAddressString;
	}

	/**
	 * @param mACAddressString the mACAddressString to set
	 */
	public void setMACAddressString(String mACAddressString) {
		MACAddressString = mACAddressString;
	}

	@Override
	public String toString() {
		return MACAddressString;
	}
}

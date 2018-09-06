package com.automation.types;

public class IPAddress {
	
	String ipString;

	
	public IPAddress() {}
	
	public IPAddress(String ipString) {
		this.ipString = ipString;
	}
	
	/**
	 * @return the ipString
	 */
	public String getIpString() {
		return ipString;
	}

	/**
	 * @param ipString the ipString to set
	 */
	public void setIpString(String ipString) {
		this.ipString = ipString;
	}

	@Override
	public String toString() {
		return ipString;
	}
}

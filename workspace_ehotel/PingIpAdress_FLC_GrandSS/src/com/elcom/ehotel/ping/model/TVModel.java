package com.elcom.ehotel.ping.model;

public class TVModel {
	private String key = "";
	private String ip = "";
	private int status = 0;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TVModel [key=" + key + ", ip=" + ip + ", status=" + status + "]";
	}

}

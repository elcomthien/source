package com.elcom.ehotel.esmile.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private String dbiHost = "";
	private String dbiPort = "";
	private String linkWS = "";

	public String getDbiHost() {
		return dbiHost;
	}

	public void setDbiHost(String dbiHost) {
		this.dbiHost = dbiHost;
	}

	public String getDbiPort() {
		return dbiPort;
	}

	public void setDbiPort(String dbiPort) {
		this.dbiPort = dbiPort;
	}

	public String getLinkWS() {
		return linkWS;
	}

	public void setLinkWS(String linkWS) {
		this.linkWS = linkWS;
	}

	@Override
	public String toString() {
		return "Config [dbiHost=" + dbiHost + ", dbiPort=" + dbiPort + ", linkWS=" + linkWS + "]";
	}

	public Config() {
		String path = "Config/config.properties";
//		System.out.println(path);
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);
			dbiHost = prop.getProperty("dbi.host");
			dbiPort = prop.getProperty("dbi.port");
			linkWS = prop.getProperty("link.ws");

			System.out.println(toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

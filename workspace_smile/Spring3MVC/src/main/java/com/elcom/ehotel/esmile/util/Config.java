package com.elcom.ehotel.esmile.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class Config {

	private String dbiHost = "";
	private String dbiPort = "";
	private String pathsave = "";
	private String linkjson = "";

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

	public String getPathsave() {
		return pathsave;
	}

	public void setPathsave(String pathsave) {
		this.pathsave = pathsave;
	}

	public String getLinkjson() {
		return linkjson;
	}

	public void setLinkjson(String linkjson) {
		this.linkjson = linkjson;
	}

	@Override
	public String toString() {
		return "Config [dbiHost=" + dbiHost + ", dbiPort=" + dbiPort + ", pathsave=" + pathsave + ", linkjson=" + linkjson + "]";
	}

	public Config() {
		String path = System.getProperty("catalina.base") + "/webapps/Config/config.properties";
		System.out.println(path);
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);
			dbiHost = prop.getProperty("dbi.host");
			dbiPort = prop.getProperty("dbi.port");
			pathsave = prop.getProperty("path.save");
			linkjson = prop.getProperty("link.json");
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

	public static void main(String[] args) {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}

package com.elcom.mysql.delete.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigModel {
	private String dbhost = "";
	private String dbport = "";
	private String dbname = "";
	private String dbuser = "";
	private String dbpass = "";

	private String timehour = "";
	private String timeminute = "";
	private String timeday = "";
	private String dbtable = "";
	private String datereset = "";

	public String getDbhost() {
		return dbhost;
	}

	public void setDbhost(String dbhost) {
		this.dbhost = dbhost;
	}

	public String getDbport() {
		return dbport;
	}

	public void setDbport(String dbport) {
		this.dbport = dbport;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpass() {
		return dbpass;
	}

	public void setDbpass(String dbpass) {
		this.dbpass = dbpass;
	}

	public String getTimehour() {
		return timehour;
	}

	public void setTimehour(String timehour) {
		this.timehour = timehour;
	}

	public String getTimeday() {
		return timeday;
	}

	public void setTimeday(String timeday) {
		this.timeday = timeday;
	}

	public String getDbtable() {
		return dbtable;
	}

	public void setDbtable(String dbtable) {
		this.dbtable = dbtable;
	}

	public String getDatereset() {
		return datereset;
	}

	public void setDatereset(String datereset) {
		this.datereset = datereset;
	}

	
	public String getTimeminute() {
		return timeminute;
	}

	public void setTimeminute(String timeminute) {
		this.timeminute = timeminute;
	}

	@Override
	public String toString() {
		return "ConfigModel [dbhost=" + dbhost + ", dbport=" + dbport
				+ ", dbname=" + dbname + ", dbuser=" + dbuser + ", dbpass="
				+ dbpass + ", timehour=" + timehour + ", timeminute="
				+ timeminute + ", timeday=" + timeday + ", dbtable=" + dbtable
				+ ", datereset=" + datereset + "]";
	}

	public ConfigModel() {
		String path = "Config/config.properties";
		System.out.println(path);
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);
			dbhost = prop.getProperty("db.host");
			dbport = prop.getProperty("db.port");
			dbname = prop.getProperty("db.name");
			dbuser = prop.getProperty("db.user");
			dbpass = prop.getProperty("db.pass");

			timehour = prop.getProperty("time.hour");
			timeminute = prop.getProperty("time.minute");
			timeday = prop.getProperty("time.day");
			dbtable = prop.getProperty("db.table");
			datereset = prop.getProperty("date.reset");

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
//		ConfigModel c = new ConfigModel();
	}

}

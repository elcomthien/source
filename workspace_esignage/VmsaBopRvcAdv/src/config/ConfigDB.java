package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigDB {
	private String host = "";
	private String port = "";
	private String database = "";
	private String user = "";
	private String password = "";

	public ConfigDB() {
		String filepath = ConfigDB.class.getResource("").getPath();
		String temp = filepath.substring(0, filepath.lastIndexOf("classes/config/"));
		String path = temp + "configdb.properties";
//		System.out.println(path);
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);

			host = prop.getProperty("hostdb");
			port = prop.getProperty("portdb");
			database = prop.getProperty("dbname");
			user = prop.getProperty("userdb");
			password = prop.getProperty("passdb");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void main(String[] args) {
		ConfigDB c = new ConfigDB();
	}

}

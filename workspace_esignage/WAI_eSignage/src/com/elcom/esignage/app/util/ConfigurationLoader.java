package com.elcom.esignage.app.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigurationLoader
{
	static Logger log = Logger.getLogger(ConfigurationLoader.class);

	private static ConfigurationLoader instance = null;
	private static Configuration configuration = null;

	private ConfigurationLoader()
	{
		configuration = new Configuration();

		build();
	}

	public static synchronized ConfigurationLoader getInstance()
	{
		if (instance == null)
		{
			instance = new ConfigurationLoader();
		}

		return instance;
	}

	public Configuration getConfiguration()
	{
		return configuration;
	}

	private void build()
	{
		try {
			String currentDir = System.getProperty("user.dir");
			System.out.println("Current folder " + currentDir);
			Properties prop = new Properties();
			FileInputStream inStream = new FileInputStream("Config/adcenter.properties");
			prop.load(inStream);

			configuration.host = prop.getProperty("adcenterservice.host", "localhost");
			configuration.port = prop.getProperty("adcenterservice.port", "3306");
			configuration.database = prop.getProperty("adcenterservice.database", "ABOPDB");
			configuration.user = prop.getProperty("adcenterservice.user", "adcenter");
			configuration.pass = prop.getProperty("adcenterservice.pass", "adcenter");

			inStream.close();
		} catch (Throwable t) {
			// We have to catch Throwable, otherwise we will miss
			// NoClassDefFoundError and other subclasses of Error
			log.error("Building ConfigurationLoader failed.", t);
			throw new RuntimeException(t);
		}
	}
}

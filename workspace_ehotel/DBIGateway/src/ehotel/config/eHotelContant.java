package ehotel.config;

import java.util.Properties;

public class eHotelContant {
	public static String HOSTNAME = "localhost";
	public static int PORT = 9090;
	public static String SERVICENAME = "eHotelPMSAPP";
	public static String CONFIGFILE = "eodapp.properties";
	public static int REMOTEVIEWPORT = 0;
	public static String CONFIGPATH = getConfigPath();

	public static String getConfigPath() {
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");
		path += "\\Config\\";
		return path;
	}

	public String toString() {
		return "FTPContants[SERVER=" + HOSTNAME + ",SERVICENAME=" + SERVICENAME
				+ ",PORT=" + PORT + "]";
	}
}

package ehotel.connect;

import java.util.Properties;

import org.apache.log4j.Logger;

public class PathDef {
	private static Logger log = Logger.getLogger(PathDef.class);

	public static String getIpConfigPath(String fileName) {
		if (fileName == null)
			fileName = "eodapp.properties";
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");
		path += "/Config/" + fileName;
		log.info(path);
		return path;
	}

	public static String getLog4jConfigPath() {
		Properties properties = System.getProperties();
		String path = "";
		try {
			if (properties != null)
				path = properties.getProperty("user.dir");
			path += "/Config/log4j.properties";
		} catch (Exception ex) {
			path += "/Config/log4j.properties";
		}
		return path;
	}

}

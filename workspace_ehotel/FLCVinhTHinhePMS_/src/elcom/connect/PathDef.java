package elcom.connect;

import java.util.Properties;

import org.apache.log4j.Logger;

public class PathDef {
	private static Logger log = Logger.getLogger(PathDef.class);

	public static String getIpConfigPath() {
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");
		path += "/Config/eodapp.properties";
		log.info(path);
		return path;
	}

	public static String getLog4jConfigPath() {
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");
		path += "/Config/log4j.properties";
		return path;
	}

	public static void main(String[] args) {
		PathDef.getIpConfigPath();
	}

}

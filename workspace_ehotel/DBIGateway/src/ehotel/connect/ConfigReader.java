package ehotel.connect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigReader {
	private static Logger log = Logger.getLogger(ConfigReader.class);

	private Properties props;

	public static ConfigReader getIpConfigReader() throws IOException {
		return new ConfigReader();
	}

	public static ConfigReader getLog4jConfigReader() throws IOException {
		return new ConfigReader(PathDef.getLog4jConfigPath());
	}

	public static ConfigReader getOtherConfigReader(String fullPath)
			throws IOException {
		return new ConfigReader(fullPath);
	}

	private ConfigReader() throws IOException {
		init(null);
	}

	private ConfigReader(String pathConfigFile) throws IOException {
		init(pathConfigFile);
	}

	private void init(String pathConfigFile) {
		if (pathConfigFile == null) {
			pathConfigFile = PathDef.getIpConfigPath(pathConfigFile);
		}

		File fconf = new File(pathConfigFile);
		if (fconf.exists()) {
			props = new Properties();
			try {
				FileInputStream fileinputstream = new FileInputStream(fconf);
				props.load(fileinputstream);
			} catch (IOException ex) {
				log.error(null, ex);
			}
		}
	}

	public String getProperty(String s) {
		return props.getProperty(s);
	}

	public String getProperty(String key, String defauldValue) {
		String value = getProperty(key);
		if (value != null) {
			return value;
		}
		return defauldValue;
	}

	public Properties getProperties() {
		return props;
	}
}

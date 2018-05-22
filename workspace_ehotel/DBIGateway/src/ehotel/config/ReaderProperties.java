package ehotel.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import ehotel.connect.PathDef;

public class ReaderProperties {
	private static Logger log = Logger.getLogger(ReaderProperties.class);

	private Properties props;

	private static final String fileName = "eodapp.properties";

	public static boolean fileExist = false;

	public ReaderProperties() throws IOException {
		init(null);
	}

	public ReaderProperties(String pathConfigFile) throws IOException {
		init(pathConfigFile);
	}

	private void init(String pathConfigFile) throws IOException {
		String pathCF = "";
		if (props == null) {
			pathCF = PathDef.getIpConfigPath(pathConfigFile);

			System.out.println(pathCF);
			File fconf = new File(pathCF);
			if (fconf.exists()) {
				fileExist = true;
				Properties properties = new Properties();
				try {
					FileInputStream fileinputstream = new FileInputStream(fconf);
					properties.load(fileinputstream);
					props = properties;
				} catch (IOException ex) {
					log.error(null, ex);
					throw ex;
				}
			} else {
				fileExist = false;
			}
		}
	}

	public String getProperty(String s) {
		return props.getProperty(s);
	}

	public String getProperty(String key, String defauldValue) {
		String value = getProperty(key);
		if (value == null)
			return defauldValue;
		else
			return value;
	}

	public static String getFileName() {
		return fileName;
	}

	public String getPathHome() {
		java.util.Properties properties = System.getProperties();
		return properties.getProperty("user.dir");
	}

	public Properties getProperties() {
		return props;
	}

	public void setProperties(Properties properties) {
		this.props = properties;
	}
}

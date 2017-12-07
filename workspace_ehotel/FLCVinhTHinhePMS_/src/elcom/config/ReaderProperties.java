package elcom.config;

import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;

import elcom.connect.PathDef;

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
			if (pathConfigFile == null) {
				pathCF =PathDef.getIpConfigPath();
				// System.out.println(pathCF);
			} else {
				pathCF = pathConfigFile;
			}

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

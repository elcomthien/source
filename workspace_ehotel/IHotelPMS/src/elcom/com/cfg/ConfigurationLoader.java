package elcom.com.cfg;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationLoader {
	// static Logger log = Logger.getLogger(ConfigurationLoader.class);

	private static ConfigurationLoader instance = null;
	private static eConfiguration configuration = null;

	private ConfigurationLoader() {
		configuration = new eConfiguration();
		build();
	}

	public static synchronized ConfigurationLoader getInstance() {
		if (instance == null) {
			instance = new ConfigurationLoader();
		}
		return instance;
	}

	public eConfiguration getConfiguration() {
		return configuration;
	}

	private void build() {
		try {
			Properties prop = new Properties();
			FileInputStream inStream = new FileInputStream("Config/eodapp.properties");
			prop.load(inStream);
			configuration.dbihostname = prop.getProperty("sqlapp.dbihostname");
			configuration.dbiport = Integer.parseInt(prop.getProperty("sqlapp.dbiport"));
			configuration.dbiservicename = prop.getProperty("sqlapp.dbiservicename", "DBInterface");
			configuration.pool_size_max = Integer.parseInt(prop.getProperty("sqlapp.pool_size_max", "10"));
			configuration.conn_tries_numbers = Integer.parseInt(prop.getProperty("sqlapp.conn_tries_numbers", "10"));
			configuration.eHotel_wsdl = prop.getProperty("pmstransfer.ehotel_wsdl", "http://localhost:8123/eHotelInterface/services/CoreInterface?wsdl");
			System.out.println(configuration.dbihostname + ":" + configuration.dbiport + "/" + configuration.dbiservicename);
			inStream.close();
			System.out.println(configuration.toString());
		} catch (Exception t) {
			t.printStackTrace();
			throw new RuntimeException(t);
		}
	}
}

package elcom.com.cfg;

public class eConfiguration {
	public static Object have_data = new Object();
	public String dbihostname = "192.168.0.55";
	public int dbiport = 9999;
	public String dbiservicename = "DBISQL";
	public int debug = 1;

	public int pool_size_max = 5;
	// the numbers of times try to reconnect to the DBI/SM
	public int conn_tries_numbers = 10;
	public long sleepInterval = 500;
	public long timedata = 5000;

	// web service config
	public String eHotel_wsdl = "";

	public eConfiguration() {
	}

	@Override
	public String toString() {
		return "eConfiguration [dbihostname=" + dbihostname + ", dbiport=" + dbiport + ", dbiservicename=" + dbiservicename + ", debug="
				+ debug + ", pool_size_max=" + pool_size_max + ", conn_tries_numbers=" + conn_tries_numbers + ", sleepInterval="
				+ sleepInterval + ", timedata=" + timedata + ", eHotel_wsdl=" + eHotel_wsdl + "]";
	}

}

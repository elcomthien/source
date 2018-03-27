package elcom.com.webserver;

import org.apache.log4j.PropertyConfigurator;

import com.elcom.eodapp.ehotel.processor.MainProcess;

	public class Program {
		public static void main (String [] args) {
	    	PropertyConfigurator.configure("Config/log4j.properties");
	    	MainProcess mp = new MainProcess();
		 	mp.RunProgram();
	 }
}

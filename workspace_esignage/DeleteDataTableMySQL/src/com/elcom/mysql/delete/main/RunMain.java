package com.elcom.mysql.delete.main;

import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.elcom.mysql.delete.service.ServiceDelete;
import com.elcom.mysql.delete.util.ConfigModel;
import com.elcom.mysql.delete.util.UtilBasic;

public class RunMain {
	private final static Logger log = Logger.getLogger(RunMain.class);

	public static void main(String[] args) {
		InputStream is = null;
		Properties props = new Properties();
		try {
			is = RunMain.class.getResourceAsStream("Config/log4j.properties");
			if (is != null) {
				props.load(is);
				PropertyConfigurator.configure(props);
				System.out.println(props.toString());
			} else {
				PropertyConfigurator.configure("Config/log4j.properties");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ConfigModel configModel = new ConfigModel();
//		String hour = configModel.getTimehour();
//		String minute = configModel.getTimeminute();
		String day = configModel.getTimeday();
		String datereset = configModel.getDatereset();
		boolean isReset = UtilBasic.isReset(datereset);
		long timesleep = 0;
		log.info("-----------------------------------");
		if (!isReset) {
			timesleep = 1 * 60 * 1000;
			try {
				log.info("Time sleep: " + timesleep);
				Thread.sleep(timesleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			ServiceDelete serviceDelete = new ServiceDelete();
//			serviceDelete.deleteService();
			long timeout = Integer.parseInt(day) * 24 * 60 * 60 * 1000;
			Timer timer = new Timer();
			timer.schedule(new RunTemp(), 0, timeout);
		} else{
			log.info("To day, data had reseted!!!");
			return;
		}
	}
}

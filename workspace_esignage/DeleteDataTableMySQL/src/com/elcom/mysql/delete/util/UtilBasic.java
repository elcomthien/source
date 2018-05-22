package com.elcom.mysql.delete.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

public class UtilBasic {

	public long countTimeout(String hour, String minute) {
		int h = Integer.parseInt(hour);
		int m = Integer.parseInt(minute);
		long time = 0;

		Date date = new Date();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		int hh = calendar.get(Calendar.HOUR_OF_DAY);
		int mm = calendar.get(Calendar.MINUTE);
		int ss = calendar.get(Calendar.SECOND);
		long delay = m * 60 * 1000;
		if (h > hh) {
			long timecurrent = (hh * 60 * 60 + mm * 60 + ss) * 1000;
			long timealarm = (h * 60 * 60) * 1000;
			time = timealarm - timecurrent;
		} else {
			long timecurrent = (hh * 60 * 60 + mm * 60 + ss) * 1000;
			long timealarm = (h * 60 * 60) * 1000;
			long timetemp = timecurrent - timealarm;
			long timeday = 24 * 60 * 60 * 1000;
			time = timeday - timetemp;
		}
		return time + delay;
	}

	public static boolean isReset(String datereset) {
		String today = getToday();
		if (datereset.equals(today))
			return true;
		return false;
	}

	public static String getToday() {
		String today = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = Calendar.getInstance().getTime();
		today = df.format(date);
		return today;
	}

	public static void updateDateReset() {
		try {
			FileInputStream in;
			in = new FileInputStream("Config/config.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream("Config/config.properties");
			props.setProperty("date.reset", getToday());
			props.store(out, null);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

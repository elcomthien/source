package com.elcom.adcenter.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This class contains helper methods for dealing with Date objects.
 */
public final class DateHelper {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String convertMoney(String money, String unit) {
		if (unit != null && unit.equals("USD")) {
			return money;
		} else {
			List ah = new ArrayList();
			int lenghtA = money.length();
			// System.out.println("lenghtA=" + lenghtA);
			int soDuA = lenghtA % 3;
			// System.out.println("soDuA=" + soDuA);
			int soNguyen = lenghtA / 3;
			int index = 1;
			if (soDuA > 0) {
				index = soNguyen + 1;
				// System.out.println("index=" + index);
			} else {
				if (soNguyen > 1) {
					index = soNguyen;
				}
			}
			String newA = money;
			// System.out.println(newA);
			for (int i = 0; i < index; i++) {
				// System.out.println("newA bd =" + newA);
				if (newA.length() > 3) {
					String tmp = money.substring(newA.length() - 3,
							newA.length());
					newA = newA.substring(0, newA.length() - 3);
					// System.out.println("i=" + i + ",newA=" + newA + ",tmp="
					// +tmp);
					ah.add(0, tmp);
				} else {
					ah.add(0, newA);
				}
			}
			// System.out.println(ah.toString());
			Iterator iterator = ah.iterator();
			return processMoney(iterator);
		}
	}

	@SuppressWarnings("rawtypes")
	private static String processMoney(Iterator vIterator) {
		StringBuffer str = new StringBuffer();
		while (vIterator.hasNext()) {
			str.append(vIterator.next());
			str.append(",");
		}
		String tmp = str.toString();
		tmp = tmp.substring(0, tmp.length() - 1);
		System.out.println(tmp);
		return tmp;

	}

	public static String format(Date date) {

		// returns a String representation of the date argument,
		// formatted according to the pattern argument, which
		// has the same syntax as the argument of the SimpleDateFormat
		// class1E
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return formatter.format(date);
	}

	public static java.util.Date parseDate(String strDate) {
		try {
			// return a Date
			// Parse the previous string back into a Date.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
					"MM/dd/yyyy HH:mm:ss");
			java.text.ParsePosition pos = new java.text.ParsePosition(0);
			java.util.Date d = formatter.parse(strDate, pos);

			return d;

		} catch (Exception ex) {
		}

		return null;
	}

	public static long toYYYYMMDD(java.util.Date date) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyyMMdd");
		return Long.parseLong(formatter.format(date));
	}

	public static long getDateInSeconds(java.util.Date d) {
		int day = getDay(d);
		int month = getMonth(d);
		int year = getYear(d);

		long seconds = ((year - 1900) * 365 * 24 + (year - 1900) * 6 + month
				* 30 * 24 + day * 24) * 60 * 60;

		Long convertLong = new Long(seconds);
		return convertLong.intValue();
	}

	public static long getTimeInSeconds(java.sql.Time t) {
		java.util.Date d = new java.util.Date(t.getTime());
		int h = getHour(d);
		int m = getMinute(d);
		int s = getSecond(d);

		long seconds = h * 60 * 60 + m * 60 + s;
		Long convertLong = new Long(seconds);
		return convertLong.intValue();
	}

	// =====================================================================
	public static final Date getDate(int year, int month, int day, int hour,
			int minute) {
		// returns a Date with the specified time elements
		Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
				day, hour, minute);

		return cal.getTime();

	} // getDate

	public static final Date getDate(int year, int month, int day) {
		// returns a Date with the specified time elements,
		// with the hour and minutes both set to 0 (midnight)

		Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
				day);

		return cal.getTime();

	} // getDate

	static public int dayDiff(Date first, Date second) {

		// returns the difference, in days, between the first
		// and second Date arguments

		long msPerDay = 1000 * 60 * 60 * 24;
		long diff = (first.getTime() / msPerDay)
				- (second.getTime() / msPerDay);
		Long convertLong = new Long(diff);
		return convertLong.intValue();
	} // dayDiff

	static public int getYear(Date date) {

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	} // getYear

	static public int getMonth(Date date) {

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int calendarMonth = cal.get(Calendar.MONTH);
		return calendarMonthToInt(calendarMonth);
	} // getMonth

	static public int getDay(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	} // getDay

	static public int getHour(Date date) {

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	} // geHour

	static public int getMinute(Date date) {

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);
	} // geMinute

	static public int getSecond(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.SECOND);
	} // getSecond

	private static int calendarMonthToInt(int calendarMonth) {

		if (calendarMonth == Calendar.JANUARY) {
			return 1;
		} else if (calendarMonth == Calendar.FEBRUARY) {
			return 2;
		} else if (calendarMonth == Calendar.MARCH) {
			return 3;
		} else if (calendarMonth == Calendar.APRIL) {
			return 4;
		} else if (calendarMonth == Calendar.MAY) {
			return 5;
		} else if (calendarMonth == Calendar.JUNE) {
			return 6;
		} else if (calendarMonth == Calendar.JULY) {
			return 7;
		} else if (calendarMonth == Calendar.AUGUST) {
			return 8;
		} else if (calendarMonth == Calendar.SEPTEMBER) {
			return 9;
		} else if (calendarMonth == Calendar.OCTOBER) {
			return 10;
		} else if (calendarMonth == Calendar.NOVEMBER) {
			return 11;
		} else if (calendarMonth == Calendar.DECEMBER) {
			return 12;
		} else {
			return 1;
		}

	} // calendarMonthToInt

	private static int intToCalendarMonth(int month) {

		if (month == 1) {
			return Calendar.JANUARY;
		} else if (month == 2) {
			return Calendar.FEBRUARY;
		} else if (month == 3) {
			return Calendar.MARCH;
		} else if (month == 4) {
			return Calendar.APRIL;
		} else if (month == 5) {
			return Calendar.MAY;
		} else if (month == 6) {
			return Calendar.JUNE;
		} else if (month == 7) {
			return Calendar.JULY;
		} else if (month == 8) {
			return Calendar.AUGUST;
		} else if (month == 9) {
			return Calendar.SEPTEMBER;
		} else if (month == 10) {
			return Calendar.OCTOBER;
		} else if (month == 11) {
			return Calendar.NOVEMBER;
		} else if (month == 12) {
			return Calendar.DECEMBER;
		} else {
			return Calendar.JANUARY;
		}

	} // intToCalendarMonth

	public static String format(Date date, String pattern) {

		// returns a String representation of the date argument,
		// formatted according to the pattern argument, which
		// has the same syntax as the argument of the SimpleDateFormat
		// class1E

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static long toYYYYMMDDHHMISS(java.util.Date d) {
		String temp = format(d, "yyyyMMddHHmmss");

		return (Long.parseLong(temp));
	}

	public static long toYYYYMMDDHHMISS(java.sql.Timestamp ts) {
		String temp = format(ts, "yyyyMMddHHmmss");

		return (Long.parseLong(temp));
	}

	public static String getIpRemote() {
		/*
		 * MessageContext curContext = MessageContext.getCurrentMessageContext(); //String remoteClientAddress =
		 * curContext.getProperty(MessageContext.REMOTE_ADDR); Object ipProperty = curContext.getProperty(MessageContext.REMOTE_ADDR); String remoteip
		 * = ipProperty.toString();
		 */
		return "000:000:000:000";
	}

	public static String stringToHTMLString(String string) {
		StringBuffer sb = new StringBuffer(string.length());
		// true if last char was blank
		boolean lastWasBlankChar = false;
		int len = string.length();
		char c;

		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (c == ' ') {
				// blank gets extra work,
				// this solves the problem you get if you replace all
				// blanks with &nbsp;, if you do that you loss
				// word breaking
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
				} else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			} else {
				lastWasBlankChar = false;

				int ci = 0xffff & c;
				if (ci < 160)
					// nothing special only 7 Bit
					sb.append(c);
				else {
					// Not 7 Bit use the unicode system
					sb.append("&#");
					sb.append(new Integer(ci).toString());
					sb.append(';');
				}

			}
		}
		return sb.toString();
	}

	public void logout(boolean log, String message) {
		if (log) {
			System.out.println(message);
		}
	}

	public static Vector<Integer> getPlaylistid(String playlistids) {
		Vector<Integer> result = new Vector<Integer>();
		StringTokenizer st = new StringTokenizer(playlistids, ",");
		while (st.hasMoreTokens()) {
			String key = st.nextToken();
			if(checkExsist(result,key))
			result.add(new Integer(key));
		}
		return result;
	}

	public static boolean checkExsist(Vector<Integer> vector, String key) {
		int id = Integer.parseInt(key);
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i) == id)
				return false;
		}
		return true;
	}

	public static String pareCapture(String capture_) {
		String xml = "";
		String server = "";
		String port = "";
		String cycle = "";
		String time = "";
		String memory = "";
		StringTokenizer st = new StringTokenizer(capture_, ";");
		while (st.hasMoreTokens()) {
			String key = st.nextToken();
			int i = key.indexOf("server");
			int j = key.indexOf("port");
			int a = key.indexOf("cycle");
			int b = key.indexOf("time");
			int c = key.indexOf("memory");
			if (i >= 0) {
				server = key.substring(key.indexOf("=") + 1, key.length());
			}
			if (j >= 0) {
				port = key.substring(key.indexOf("=") + 1, key.length());
			}
			if (a >= 0) {
				cycle = key.substring(key.indexOf("=") + 1, key.length());
			}
			if (b >= 0) {
				time = key.substring(key.indexOf("=") + 1, key.length());
			}
			if (c >= 0) {
				memory = key.substring(key.indexOf("=") + 1, key.length());
			}
		}
		xml = "<?xml version='1.0' encoding='utf-8'?>\r\n";
		xml = xml + "<resources>\r\n";
		xml = xml + "<config_capture>\r\n";
		xml = xml + "<auto>2</auto>\r\n";
		xml = xml + "<miligiay>300</miligiay>\r\n";
		xml = xml + "<server>" + server + "</server>\r\n";
		xml = xml + "<port>" + port + "</port>\r\n";
		xml = xml + "<cycle>" + cycle + "</cycle>\r\n";
		xml = xml + "<time>\r\n";
		st = new StringTokenizer(time, ",");
		while (st.hasMoreTokens()) {
			String key = st.nextToken();
			xml = xml + "<item>" + key + "</item>\r\n";
		}
		xml = xml + "</time>\r\n";
		xml = xml + "<memory>" + memory + "</memory>\r\n";
		xml = xml + "</config_capture>\r\n";
		xml = xml + "</resources>\r\n";
		// System.out.println(xml);
		return xml;
	}

	public static String decodeURIComponent(String encodedURI) {
		char actualChar;

		StringBuffer buffer = new StringBuffer();

		int bytePattern, sumb = 0;

		for (int i = 0, more = -1; i < encodedURI.length(); i++) {
			actualChar = encodedURI.charAt(i);

			switch (actualChar) {
			case '%': {
				actualChar = encodedURI.charAt(++i);
				int hb = (Character.isDigit(actualChar) ? actualChar - '0'
						: 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
				actualChar = encodedURI.charAt(++i);
				int lb = (Character.isDigit(actualChar) ? actualChar - '0'
						: 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
				bytePattern = (hb << 4) | lb;
				break;
			}
			case '+': {
				bytePattern = ' ';
				break;
			}
			default: {
				bytePattern = actualChar;
			}
			}

			if ((bytePattern & 0xc0) == 0x80) { // 10xxxxxx
				sumb = (sumb << 6) | (bytePattern & 0x3f);
				if (--more == 0)
					buffer.append((char) sumb);
			} else if ((bytePattern & 0x80) == 0x00) { // 0xxxxxxx
				buffer.append((char) bytePattern);
			} else if ((bytePattern & 0xe0) == 0xc0) { // 110xxxxx
				sumb = bytePattern & 0x1f;
				more = 1;
			} else if ((bytePattern & 0xf0) == 0xe0) { // 1110xxxx
				sumb = bytePattern & 0x0f;
				more = 2;
			} else if ((bytePattern & 0xf8) == 0xf0) { // 11110xxx
				sumb = bytePattern & 0x07;
				more = 3;
			} else if ((bytePattern & 0xfc) == 0xf8) { // 111110xx
				sumb = bytePattern & 0x03;
				more = 4;
			} else { // 1111110x
				sumb = bytePattern & 0x01;
				more = 5;
			}
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		// DateHelper.pareCapture("server=172.16.9.188;port=5500;cycle=60;time=10:45:00,11:30:00,14:00:00,16:00:00,17:00:00;memory=200");
		System.out.println(DateHelper.getHour(new Date()));
		System.out
				.println(DateHelper.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out
				.println(DateHelper
						.decodeURIComponent("C%C3%B4ng%20ty%20D%E1%BB%8Bch%20v%E1%BB%A5%20MobiFone%20Khu%20v%E1%BB%B1c%208.%2022%2F08%2C%20Nguy%E1%BB%85n%20%C3%81i%20Qu%E1%BB%91c%2C%20P.Quang%20Vinh%2C%20Bi%C3%AAn%20Ho%C3%A0%20%E2%80%93%20%C4%90%E1%BB%93ng%20Nai%0A"));
		System.out.println(DateHelper.stringToHTMLString("Nguy&#7877;n H&#7919;u Thi&#7879;n"));
	}
} // class

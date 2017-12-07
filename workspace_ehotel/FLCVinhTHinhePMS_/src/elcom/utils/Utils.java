package elcom.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Utils {
	/**
	 * 
	 * @param number
	 * @return
	 */
	public static int parseInt(String number) {
		number = trim(number);
		if (number != null && !number.equals("null") && !number.equals("")) {
			return Integer.parseInt(number);
		}
		return 0;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static int parseInt2(Object number) {
		if (number != null) {
			String temp = trim(number.toString());
			if (isNotNull(temp)) {
				return Integer.parseInt(temp);
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String trim(String data) {
		if (data != null) {
			return data.trim();
		}
		return null;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		if (str != null && !str.equals("null") && !str.equals("")) {
			return true;
		}
		return false;
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * 
	 * @param strDate
	 *            String
	 * @return Date
	 */
	public static java.util.Date parseDate(String strDate) {

		try {
			// return a Date
			// Parse the previous string back into a Date.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");
			java.text.ParsePosition pos = new java.text.ParsePosition(0);
			java.util.Date d = formatter.parse(strDate, pos);

			return d;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String convertDateToFullString(Date date) {
		String fmt = "dd/MM/yyyy hh:mm:ss"; // "dd_MM_yyyy_hh_mm_ss";
		String d = null;

		SimpleDateFormat sdm = new SimpleDateFormat(fmt);
		try {
			d = sdm.format(date);
		} catch (Exception e) {

		}
		return d;
	}

	/**
	 * 
	 * @param money
	 * @param unit
	 * @return
	 */
	public static String convertMoney(String money, String unit) {
		if (money == null)
			return "0 " + unit;
		if (unit != null && unit.equals("USD")) {
			return money;
		} else {
			List<String> ah = new ArrayList<String>();
			money = money.trim();
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
			//System.out.println(newA);
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
			//System.out.println("Cái gi đây: " + ah.toString());
			Iterator<String> iterator = ah.iterator();
			return processMoney(iterator);
		}
	}

	private static String processMoney(Iterator<String> vIterator) {
		StringBuffer str = new StringBuffer();
		while (vIterator.hasNext()) {
			str.append(vIterator.next());
			str.append(",");

		}
		String tmp = str.toString();
		tmp = tmp.substring(0, tmp.length() - 1);
		tmp = tmp.replace(",.",".");
		// System.out.println("Tien ne: " + tmp.replace(",.","."));
		return tmp;

	}

	public static String processAmountOfUser(String amount) {
		String Usd = "";
		String Vnd = "";
		String currency = "";
		if (amount != null) {
			int pos = amount.indexOf("&");
			if (pos > 0) {
				Usd = amount.substring(0, pos);
				System.out.println("Usd=" + Usd);
				Vnd = amount.substring(pos + 1, amount.length()).trim();
				System.out.println("Vnd=" + Vnd);
				String unit = Vnd.substring(Vnd.length() - 3, Vnd.length());
				String money = Vnd.substring(0, Vnd.length() - 3);
				currency = Usd.trim() + " & "
						+ convertMoney(money, unit.trim()) + unit;
			} else {
				System.out.println(amount);
				String unit = amount.substring(amount.length() - 3,
						amount.length());
				String money = amount.substring(0, amount.length() - 3);
				// -1 khoang trang
				currency = convertMoney(money, unit.trim()) + " " + unit.trim();
			}
		}
		System.out.println(currency);
		return currency;
	}

	public void format(String money) {
		// double money = 0.5;
		NumberFormat formatter = new DecimalFormat("#0.00");
		System.out.println(money);
		System.out.println(formatter.format(money));
	}

	public static void main(String[] args) {
		System.out.print(Utils.convertMoney("1049999.90","VND"));
	}
}

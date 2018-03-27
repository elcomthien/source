package com.elcom.ehotel.esmile.util;

public class ConvertUtil {
	public static int convertToInteger(String val) {
		try {
			int value = Integer.parseInt(val);
			return value;
		} catch (Exception ex) {

		}
		return 0;
	}

	public static String convertArrayToString(String[] arr) {
		String rs = "";
		if (arr.length > 0) {
			for (int i = 0; i < arr.length; i++)
				rs += arr[i] + ",";
			rs = rs.substring(0, rs.length() - 1);
		}
		return rs;
	}

	public static void main(String[] args) {
		String rs = "1,2,3,4,5,";
		rs = rs.substring(0, rs.length() - 1);
		System.out.println(rs);
	}
}

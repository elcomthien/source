package com.elcom.ehotel.esmile.util;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
	public Util() {
	}

	public void delay(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}

	public boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists())
			return false;
		if (file.isFile()) {
			file.delete();
			return true;
		}
		String files[] = file.list();
		int i = 0;
		for (int max = files.length; i < max; i++)
			deleteFile(filePath + fsp + files[i]);

		file.delete();
		return true;
	}

	public String formatDateTime(String strFormat, Date date) {
		df = new SimpleDateFormat(strFormat);
		df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		if (date == null)
			return df.format(new Date());
		else
			return df.format(date);
	}

	public String getDayBeforeCurrent(int dayBefore, String format) {
		DateFormat df;
		df = new SimpleDateFormat(format);
		df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
		df.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		return df.format(new Date((new Date()).getTime() - (long) (dayBefore * 0x5265c00)));
	}

	public String getPathHOME() {
		if (pathHOME == null) {
			Properties properties = System.getProperties();
			pathHOME = properties.getProperty("user.dir");
		}
		return pathHOME;
	}

	public String getTimeCurrent(String format) {
		DateFormat df;
		df = new SimpleDateFormat(format);
		df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
		df.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		return df.format(new Date());
	}

	public String getTimeCurrent() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
		df.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		return df.format(new Date());
	}

	public boolean isNumber(String str) {
		Long.parseLong(str);
		return true;
	}

	public static void main(String args[]) throws Exception {
//		Util util = new Util();
		System.out.println(convertObject("{\"image\":[]}", "image"));
	}

	public boolean moveFileTo(String folderFrom, String filenameFrom, String folderTo, String filenameTo) {
		File folder = new File(folderTo);
		if (!folder.exists()) {
			folder.mkdirs();
			folder.mkdir();
		}
		File fileFrom = new File(folderFrom + fsp + filenameFrom);
		if (!fileFrom.exists()) {
			return false;
		} else {
			File fileTo = new File(folderTo + fsp + filenameTo);
			fileFrom.renameTo(fileTo);
			return true;
		}
	}

	public Object readObj(String path) throws Exception {
		Object obj = null;
		FileInputStream inFile = new FileInputStream(path);
		ObjectInputStream inStream = new ObjectInputStream(inFile);
		obj = inStream.readObject();
		inStream.close();
		inFile.close();
		return obj;
	}

	public String standardAddr(String strAddr) {
		String temp = strAddr;
		try {
			temp = temp.trim();
			int iBegin = temp.lastIndexOf('<');
			int iEnd = temp.lastIndexOf('>');
			int iSpace = temp.lastIndexOf(' ');
			if (iBegin > 0 && iEnd > 0)
				temp = temp.substring(iBegin + 1, iEnd);
			else if (iBegin > 0)
				temp = temp.substring(iBegin + 1);
			else if (iEnd > 0 && iSpace < 0)
				temp = temp.substring(0, iEnd);
			else if (iSpace > 0)
				temp = temp.substring(iSpace + 1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return temp;
	}

	public synchronized void writeObj(Object obj, String path) throws Exception {
		FileOutputStream outFile = new FileOutputStream(path);
		ObjectOutput outStream = new ObjectOutputStream(outFile);
		outStream.writeObject(obj);
		outStream.flush();
		outStream.close();
		outFile.close();
	}

	private static DateFormat df = null;
	private static String fsp;
	private static String pathHOME = null;

	static {
		fsp = File.separator;
	}

	public static HashMap<String, String> convertObject(String object, String listname) {
		HashMap<String, String> map = new HashMap<String, String>();
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			Iterator<?> keys = jObject.keys();

			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = "";
				if ((key.equals("location") || key.equals("times") || key.equals("user_id") || checkKey(key, listname))
						&& object.indexOf("]", object.indexOf(key)) > 0) {
					String temp = "{" + object.substring(object.indexOf(key) - 1, object.indexOf("]", object.indexOf(key) - 1) + 1) + "}";
					JSONObject json = new JSONObject(temp);
					JSONArray location = json.getJSONArray(key);
					int length = location.length();
					if (length == 0)
						value = "";
					else {
						String vl = "";
						for (int i = 0; i < length; i++) {
							vl += location.get(i).toString() + ",";
						}
						vl = vl.substring(0, vl.length() - 1);
						value = vl;
					}
				} else
					value = jObject.getString(key);
				map.put(key, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static boolean checkKey(String key, String listname) {
		String[] arr = listname.split(",");
		for (int i = 0; i < arr.length; i++)
			if (arr[i].equals(key))
				return true;
		return false;
	}

	public static HashMap<String, String> noneParam() {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("message", "None params");
		System.out.println("None param input???");
		return hm;
	}

	public static boolean checkObject(String object) {
		if (object.equals("{}") || object.equals("null") || object.equals("") || object.equals(null))
			return false;
		return true;
	}

}

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

	public static HashMap<String, String> convertObject(String object) {
		HashMap<String, String> map = new HashMap<String, String>();
		JSONObject jObject;
		try {
			jObject = new JSONObject(object);
			Iterator<?> keys = jObject.keys();

			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = "";
				if (key.equals("location") || key.equals("times")) {
					String temp = "{" + object.substring(object.indexOf(key) - 1, object.indexOf("]") + 1) + "}";
					JSONObject json = new JSONObject(temp);
					JSONArray location = json.getJSONArray(key);
					int length = location.length();
					String vl = "";
					for (int i = 0; i < length; i++) {
						vl += location.get(i).toString() + ",";
					}
					vl = vl.substring(0, vl.length() - 1);
					value = vl;
				} else
					value = jObject.getString(key);
				map.put(key, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
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

	public static String parseJson(String object) throws JSONException {
		System.out.println("parse");
		JSONArray jarray = new JSONArray(object);
		for (int i = 0; i < jarray.length(); i++) {
			JSONObject jb = (JSONObject) jarray.get(i);
			System.out.println(jb);
		}
		return "";
	}

	public static void main(String[] args) throws JSONException {
		// String str =
		// "{"date_from":"04-05-2017","date_to":"04-05-2017","location":"[\"243\", \"242\", \"2\", \"3\", \"1\"]","smile_id":"1","lang_id":"2"}"";
		// String text = "{\"date_from\":\"04-05-2017\",\"date_to\":\"04-05-2017\",\"location\":\"[243, 242, 2, 3, 1]\"}";
		// String t = "{\"date_from\":\"04-05-2017\",\"date_to\":\"04-05-2017\",\"location\":\"[243,242,2,3,1]\",\"lang_id\":\"2\"}";
		// parseJson(text);
		// System.out.println(convertObject(text));
		// HashMap<String, String> hm = convertObject(t);
		// System.out.println(hm.get("date_from"));
		// System.out.println(hm.get("location"));

		// JSONObject result = new JSONObject(text);
		// JSONArray lc = result.getJSONArray("location");
		// int size = lc.length();
		// String[] loca = new String[size];
		// for (int i = 0; i < size; i++) {
		// JSONObject l = lc.getJSONObject(i);
		// System.out.println(l);
		// }
		// System.out.println(Arrays.toString(loca));

		//
		// JSONArray jsonArray = new JSONArray(text);
		// JSONArray jsonPersonData = jsonArray.getJSONArray(2);
		// for (int i = 0; i < jsonPersonData.length(); i++) {
		// JSONObject item = jsonPersonData.getJSONObject(i);
		// System.out.println(item);
		// }

		// JSONObject js = new JSONObject(text);
		// JSONArray jsonMainArr = js.getJSONArray("location");
		// for (int i = 0; i < jsonMainArr.length(); i++) {
		// JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
		// System.out.println(childJSONObject);
		// }

		// JSONObject json = new JSONObject(text);
		// JSONArray location = json.getJSONArray("location");
		// int length = location.length();
		// for (int i = 0; i < length; i++) {
		// System.out.println(location.get(i).toString());
		// }

		// String a = "asdasdasd[";
		// a = a.replaceAll("\\[", "");
		// System.out.println(a);

		// HashMap<String, String> map = new HashMap<String, String>();
		// String ttt = "{\"location\":[\"243\",\"2\",\"3\",\"1\"]}";
		// JSONObject jObject;
		// try {
		// jObject = new JSONObject(ttt);
		// Iterator<?> keys = jObject.keys();
		// System.out.println(keys.toString());
		// while (keys.hasNext()) {
		// String key = (String) keys.next();
		// System.out.println(key.toString());
		//
		// JSONObject json = new JSONObject(ttt);
		// JSONArray location = json.getJSONArray("location");
		// int length = location.length();
		// for (int i = 0; i < length; i++) {
		// System.out.println(location.get(i).toString());
		// }

		// String value = jObject.getString(key);
		// value = value.replaceAll("\\[", "");
		// value = value.replaceAll("\\]", "");
		// map.put(key, value);
		// }
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }

		// JSONObject json = new JSONObject(ttt);
		// JSONArray location = json.getJSONArray("location");
		// int length = location.length();
		// for (int i = 0; i < length; i++) {
		// System.out.println(location.get(i).toString());
		// }

		String t = "{\"times\":[\"07:00\",\"08:00\",\"09:00\",\"10:00\",\"07:00\",\"08:00\",\"09:00\",\"10:00\"]}";
		System.out.println(t);
		System.out.println(convertObject(t));
	}

}
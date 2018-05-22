package com.elcom.esignage.test.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang.StringUtils;


public class UtilBasic {
//	public static ArrayList<ObjectABOP> abopContent = new ArrayList<ObjectABOP>();
//	private static final Logger logger = Logger.getLogger(UtilBasic.class);
	public static String os = System.getProperty("os.name").toLowerCase();

	public static String getResource(String path, String key) {
		Properties props = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = props.getProperty(key);
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String parseDate(String date) {
		String[] arr = date.split("-");
		return arr[2] + "-" + arr[1] + "-" + arr[0];
	}

	public static String subString(String note, int length) {
		if (note.isEmpty()) {
			return "";
		}
		if (note.length() <= length) {
			return note;
		} else {
			return (note.substring(0, length) + "...");
		}
	}

	public static String convertFileType(String str) {
		if (str.equalsIgnoreCase("1"))
			return "Video";
		else if (str.equalsIgnoreCase("2"))
			return "";
		else if (str.equalsIgnoreCase("3"))
			return "Image";
		else if (str.equalsIgnoreCase("4"))
			return "Video";
		else if (str.equalsIgnoreCase("5"))
			return "Text";
		else if (str.equalsIgnoreCase("6"))
			return "Browser";
		else if (str.equalsIgnoreCase("7"))
			return "Image";
		else if (str.equalsIgnoreCase("8"))
			return "Image";
		else if (str.equalsIgnoreCase("9"))
			return "Video";
		return "";
	}

	public static String convertTypeFile(String str) {
		if (str.equalsIgnoreCase("Video"))
			return "1";
		else if (str.equalsIgnoreCase("2"))
			return "";
		else if (str.equalsIgnoreCase("Image"))
			return "3";
		else if (str.equalsIgnoreCase("Video"))
			return "4";
		else if (str.equalsIgnoreCase("Text"))
			return "5";
		else if (str.equalsIgnoreCase("Browser"))
			return "6";
		else if (str.equalsIgnoreCase("Image"))
			return "7";
		else if (str.equalsIgnoreCase("Image"))
			return "8";
		else if (str.equalsIgnoreCase("Video"))
			return "9";
		return "";
	}


	public static String getSequence() {
		final String alphabet = "0123456789ABCDE";
		final int N = alphabet.length();
		String result = "";
		Random r = new Random();
		for (int i = 0; i < 50; i++) {
			result += (alphabet.charAt(r.nextInt(N)));
		}
		return result;
	}

	private static String getHex(byte buf[]) {
		StringBuilder o = new StringBuilder(buf.length * 3);
		for (int i = 0; i < buf.length; i++) {
			int n = (int) buf[i] & 0xff;
			o.append("%");
			if (n < 0x10) {
				o.append("0");
			}
			o.append(Long.toString(n, 16).toUpperCase());
		}
		return o.toString();
	}

	public static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

	public static String encodeURIComponent(String input) {
		if (StringUtils.isEmpty(input)) {
			return input;
		}

		int l = input.length();
		StringBuilder o = new StringBuilder(l * 3);
		try {
			for (int i = 0; i < l; i++) {
				String e = input.substring(i, i + 1);
				if (ALLOWED_CHARS.indexOf(e) == -1) {
					byte[] b = e.getBytes("utf-8");
					o.append(getHex(b));
					continue;
				}
				o.append(e);
			}
			return o.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
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

	public static String getParentPath(String path) {
		String parentPath = path.substring(0, path.length() - 2);
		int i = parentPath.lastIndexOf(System.getProperty("file.separator"));
		parentPath = parentPath.substring(0, i + 1);
		return parentPath;
	}
	
	public static boolean pingIp(String ip)
	{
		boolean flag = false;
		if ( isWindows() ) {
			//do nothing, neu la windows thi ko can lam gi vi khong co ham
			String lsString = null;
			//command to ping ip
			String[] listCmd = new String[] {"cmd.exe ","/c"," ping -n 1 " + ip};
			Runtime run = Runtime.getRuntime();
			Process runtimeProcess = null;
			try {
				runtimeProcess = run.exec(listCmd); 
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
				while ((lsString = bufferedReader.readLine()) != null)
				{
					if (lsString.indexOf("Destination Host Unreachable") >= 0) {
						return false;
					}
					else if (lsString.indexOf("Request timed out") >= 0) {
						return false;
					}
					else if (lsString.indexOf("100% loss") >= 0) {
						return false;
					}
					else {
						flag = true;
					}
				}
				run.freeMemory();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if ( isUnix() ) {
			System.out.println("ip=" + ip);
			if (ip == null) {
				return false;
			}
			else {
				String lsString = null;
				//command to ping ip
				String[] listCmd = new String[] {"/bin/bash","-c","ping -c 1 " + ip};
				Runtime run = Runtime.getRuntime();
				Process runtimeProcess = null;
				try {
					runtimeProcess = run.exec(listCmd); 
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
					while ((lsString = bufferedReader.readLine()) != null)
					{
						System.out.println(lsString);
						if (lsString.indexOf("Destination Host Unreachable") >= 0) {
							return false;
						}
						else if (lsString.indexOf("Request timed out") >= 0) {
							return false;
						}
						else if (lsString.indexOf("100% loss") >= 0) {
							return false;
						}
						else {
							flag = true;
						}
					}
					run.freeMemory();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	public static boolean isWindows() {
		return (os.indexOf("win") >= 0);
	}
	public static boolean isUnix() {
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}
	
	public static void main(String[] args) {
		System.out.println(decodeURIComponent("ABCD%C3%88hlkd%E1%BA%A1%3Bflk%C3%A1'fk'akf'alk'fa'fl'alf'%0AfazGH"));
		System.out.println(decodeURIComponent("C%C3%B4ng%20ty%20D%E1%BB%8Bch%20v%E1%BB%A5%20MobiFone%20Khu%20v%E1%BB%B1c%208.%2022%2F08%2C%20Nguy%E1%BB%85n%20%C3%81i%20Qu%E1%BB%91c%2C%20P.Quang%20Vinh%2C%20Bi%C3%AAn%20Ho%C3%A0%20%E2%80%93%20%C4%90%E1%BB%93ng%20Nai%0A"));
		System.out.println(decodeURIComponent("T%E1%BA%B7ng%2050%25%20cho%20kh%C3%A1ch%20h%C3%A0ng%20tr%E1%BA%A3%20tr%C6%B0%E1%BB%9Bc%20n%E1%BA%A1p%20ti%E1%BB%81n%20ng%C3%A0y%205%2F4%2F2016%20"));
		System.out.println(decodeURIComponent("T%E1%BA%B7ng%2050%25%20cho%20kh%C3%A1ch%20h%C3%A0ng%20tr%E1%BA%A3%20tr%C6%B0%E1%BB%9Bc%20n%E1%BA%A1p%20ti%E1%BB%81n%20ng%C3%A0y%205%2F4%2F2016%20"));
		System.out.println(decodeURIComponent("T%E1%BA%B7ng%2050%25%20cho%20kh%C3%A1ch%20h%C3%A0ng%20tr%E1%BA%A3%20tr%C6%B0%E1%BB%9Bc%20n%E1%BA%A1p%20ti%E1%BB%81n%20ng%C3%A0y%2005%2F04%2F2016"));
		System.out.println(decodeURIComponent("Chi%E1%BA%BFt%20kh%E1%BA%A5u%203%25%20khi%20thanh%20to%C3%A1n%20b%E1%BA%B1ng%20th%E1%BA%BB%20ng%C3%A2n%20h%C3%A0ng%20t%E1%BB%AB%20ng%C3%A0y%2001%2F01%20%C4%91%E1%BA%BFn%20ng%C3%A0y%2031%2F12%2F2016"));
		System.out.println(decodeURIComponent("%E1%BB%A8ng%20d%E1%BB%A5ng%20m%E1%BB%9Bi%20My%20MobiFone%20cho%20ph%C3%A9p%20kh%C3%A1ch%20h%C3%A0ng%20MobiFone%20s%E1%BB%AD%20d%E1%BB%A5ng%20thi%E1%BA%BFt%20b%E1%BB%8B%20%C4%91%E1%BA%A7u%20cu%E1%BB%91i%20h%E1%BB%87%20%C4%91i%E1%BB%81u%20h%C3%A0nh%20Android%202.3%20tr%E1%BB%9F%20l%C3%AAn%20ho%E1%BA%B7c%20IOS%205.1%20tr%E1%BB%9F%20l%C3%AAn%20c%C3%B3%20th%E1%BB%83%20tra%20c%E1%BB%A9u%20c%C3%A1c%20th%C3%B4ng%20tin%20c%E1%BA%ADp%20nh%E1%BA%ADt%20v%E1%BB%81%20khuy%E1%BA%BFn%20m%E1%BA%A1i%2C%20ch%C4%83m%20s%C3%B3c%20kh%C3%A1ch%20h%C3%A0ng%2C%20tra%20c%E1%BB%A9u%20t%C3%A0i%20kho%E1%BA%A3n%2C%20n%E1%BA%A1p%20ti%E1%BB%81n%2C%20t%C3%ACm%20hi%E1%BB%83u%20th%C3%B4ng%20tin%20v%C3%A0%20%C4%91%C4%83ng%20k%C3%BD%2Fh%E1%BB%A7y%20c%C3%A1c%20d%E1%BB%8Bch%20v%E1%BB%A5%20GTGT%2C%20th%C3%B4ng%20tin%20v%E1%BB%81%20c%C3%A1c%20c%E1%BB%ADa%20h%C3%A0ng%20c%E1%BB%A7a%20MobiFone%E2%80%A6."));
		System.out.println(decodeURIComponent("%C6%AFu%20%C4%91%C3%A3i%203%25%20cho%20kh%C3%A1ch%20h%C3%A0ng%20khi%20thanh%20to%C3%A1n%20ho%E1%BA%B7c%20n%E1%BA%A1p%20ti%E1%BB%81n%20tr%E1%BB%B1c%20tuy%E1%BA%BFn%20%20-%20%20Chi%E1%BA%BFn%20d%E1%BB%8Bch%20tri%20%C3%A2n%20kh%C3%A1ch%20h%C3%A0ng%20c%E1%BB%A7a%20MobiFone%20-%20%E2%80%9CTri%E1%BB%87u%20tr%C3%A1i%20tim%20k%E1%BA%BFt%20n%E1%BB%91i%22%20%20-%20%20H%C3%A3y%20%C4%91%E1%BA%BFn%20v%E1%BB%9Bi%20ch%C6%B0%C6%A1ng%20tr%C3%ACnh%20khuy%E1%BA%BFn%20m%E1%BA%A1i%20%E2%80%9CNh%E1%BB%8Bp%20s%E1%BB%91ng%20s%C3%B4i%20%C4%91%E1%BB%99ng%E2%80%9D%20%C4%91%E1%BB%83%20th%E1%BB%95i%20bay%20n%E1%BA%AFng%20h%C3%A8%20oi%20b%E1%BB%A9c%20c%C3%B9ng%20c%C6%A1%20h%E1%BB%99i%20tr%C3%BAng%2001%20gi%E1%BA%A3i%20th%C6%B0%E1%BB%9Fng%20h%E1%BA%A5p%20d%E1%BA%ABn%20-%20100%20tri%E1%BB%87u%20%C4%91%E1%BB%93ng%20ti%E1%BB%81n%20m%E1%BA%B7t%20v%C3%A0%20270%20gi%E1%BA%A3i%20th%C6%B0%E1%BB%9Fng%20th%E1%BA%BB%20n%E1%BA%A1p%20ti%E1%BB%81n%20MobiFone%20m%E1%BB%87nh%20gi%C3%A1%20100.000%20%C4%91%E1%BB%93ng%2Fgi%E1%BA%A3i.%20%20-%20%20Nh%C3%A2n%20%C4%91%C3%B4i%20%C4%91i%E1%BB%83m%20th%C6%B0%E1%BB%9Fng%20%22K%E1%BA%BFt%20n%E1%BB%91i%20d%C3%A0i%20l%C3%A2u%22%20nh%C3%A2n%20ng%C3%A0y%20truy%E1%BB%81n%20th%E1%BB%91ng%20MobiFone.%20%20-%20%20H%C3%A8%20s%E1%BA%A3ng%20kho%C3%A1i%2C%20Qu%C3%A0%20tho%E1%BA%A3i%20m%C3%A1i%20l%C3%AAn%20t%E1%BB%9Bi%2095%20tri%E1%BB%87u%20%C4%91%E1%BB%93ng"));
		System.out.println(decodeURIComponent("Khi%20thanh%20to%C3%A1n%20tr%C3%AAn%20web%20portal%20ho%E1%BA%B7c%20qua%20%E1%BB%A9ng%20d%E1%BB%A5ng%20My%20MobiFone%2C%20KH%20%C4%91%C6%B0%E1%BB%A3c%20chi%E1%BA%BFt%20kh%E1%BA%A5u%203%25%20tr%C3%AAn%20t%E1%BB%95ng%20s%E1%BB%91%20ti%E1%BB%81n%20thanh%20to%C3%A1n%2F%20n%E1%BA%A1p%20ti%E1%BB%81n%20tr%E1%BB%B1c%20tuy%E1%BA%BFn%20-%20H%C3%A8%20s%E1%BA%A3ng%20kho%C3%A1i%2C%20Qu%C3%A0%20tho%E1%BA%A3i%20m%C3%A1i%20l%C3%AAn%20t%E1%BB%9Bi%2095%20tri%E1%BB%87u%20%C4%91%E1%BB%93ng%20-%20Chi%E1%BA%BFn%20d%E1%BB%8Bch%20tri%20%C3%A2n%20kh%C3%A1ch%20h%C3%A0ng%20c%E1%BB%A7a%20MobiFone%20%22%20Tri%E1%BB%87u%20tr%C3%A1i%20tim%20k%E1%BA%BFt%20n%E1%BB%91i%22%20-%20H%C3%A3y%20%C4%91%E1%BA%BFn%20v%E1%BB%9Bi%20ch%C6%B0%C6%A1ng%20tr%C3%ACnh%20khuy%E1%BA%BFn%20m%E1%BA%A1i%20%22Nh%E1%BB%8Bp%20s%E1%BB%91ng%20s%C3%B4i%20%C4%91%E1%BB%99ng%22%20%C4%91%E1%BB%83%20th%E1%BB%95i%20bay%20n%E1%BA%AFng%20h%C3%A8%20oi%20b%E1%BB%A9c%20c%C3%B9ng%20c%C6%A1%20h%E1%BB%99i%20tr%C3%BAng%20m%E1%BB%99t%20gi%E1%BA%A3i%20th%C6%B0%E1%BB%9Fng%20h%E1%BA%A5p%20d%E1%BA%ABn%20-%20100%20tri%E1%BB%87u%20%C4%91%E1%BB%93ng%20ti%E1%BB%81n%20m%E1%BA%B7t%20v%C3%A0%20270%20gi%E1%BA%A3i%20th%C6%B0%E1%BB%9Fng%20th%E1%BA%BB%20n%E1%BA%A1p%20ti%E1%BB%81n%20MobiFone%20m%E1%BB%87nh%20gi%C3%A1%20100.000%20%C4%91%E1%BB%93ng%2Fgi%E1%BA%A3i%20-%20S%E1%BB%AD%20d%E1%BB%A5ng%20s%E1%BB%91%20%C4%91%E1%BA%B9p%20v%E1%BB%9Bi%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20tr%C3%AAn%20trang%20web%20http%3A%2F%2Fchonso.mobifone.vn%20-%20T%E1%BA%A3i%20mConnect%20%C4%91%E1%BB%83%20t%E1%BA%ADn%20h%C6%B0%E1%BB%9Fng%20h%C3%A0ng%20ng%C3%A0n%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20t%E1%BB%AB%20c%C3%A1c%20%C4%91%E1%BB%91i%20t%C3%A1c%20li%C3%AAn%20k%E1%BA%BFt%20c%E1%BB%A7a%20MobiFone"));
		System.out.println(decodeURIComponent("Khi%20thanh%20to%C3%A1n%20tr%C3%AAn%20web%20portal%20ho%E1%BA%B7c%20qua%20%E1%BB%A9ng%20d%E1%BB%A5ng%20My%20MobiFone%2C%20KH%20%C4%91%C6%B0%E1%BB%A3c%20chi%E1%BA%BFt%20kh%E1%BA%A5u%203%25%20tr%C3%AAn%20t%E1%BB%95ng%20s%E1%BB%91%20ti%E1%BB%81n%20thanh%20to%C3%A1n%2F%20n%E1%BA%A1p%20ti%E1%BB%81n%20tr%E1%BB%B1c%20tuy%E1%BA%BFn%20-%20H%C3%A8%20s%E1%BA%A3ng%20kho%C3%A1i%2C%20Qu%C3%A0%20tho%E1%BA%A3i%20m%C3%A1i%20l%C3%AAn%20t%E1%BB%9Bi%2095%20tri%E1%BB%87u%20%C4%91%E1%BB%93ng%20-%20Chi%E1%BA%BFn%20d%E1%BB%8Bch%20tri%20%C3%A2n%20kh%C3%A1ch%20h%C3%A0ng%20c%E1%BB%A7a%20MobiFone%20%22%20Tri%E1%BB%87u%20tr%C3%A1i%20tim%20k%E1%BA%BFt%20n%E1%BB%91i%22%20-%20H%C3%A3y%20%C4%91%E1%BA%BFn%20v%E1%BB%9Bi%20ch%C6%B0%C6%A1ng%20tr%C3%ACnh%20khuy%E1%BA%BFn%20m%E1%BA%A1i%20%22Nh%E1%BB%8Bp%20s%E1%BB%91ng%20s%C3%B4i%20%C4%91%E1%BB%99ng%22%20%C4%91%E1%BB%83%20th%E1%BB%95i%20bay%20n%E1%BA%AFng%20h%C3%A8%20oi%20b%E1%BB%A9c%20c%C3%B9ng%20c%C6%A1%20h%E1%BB%99i%20tr%C3%BAng%20m%E1%BB%99t%20gi%E1%BA%A3i%20th%C6%B0%E1%BB%9Fng%20h%E1%BA%A5p%20d%E1%BA%ABn%20-%20100%20tri%E1%BB%87u%20%C4%91%E1%BB%93ng%20ti%E1%BB%81n%20m%E1%BA%B7t%20v%C3%A0%20270%20gi%E1%BA%A3i%20th%C6%B0%E1%BB%9Fng%20th%E1%BA%BB%20n%E1%BA%A1p%20ti%E1%BB%81n%20MobiFone%20m%E1%BB%87nh%20gi%C3%A1%20100.000%20%C4%91%E1%BB%93ng%2Fgi%E1%BA%A3i%20-%20S%E1%BB%AD%20d%E1%BB%A5ng%20s%E1%BB%91%20%C4%91%E1%BA%B9p%20v%E1%BB%9Bi%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20tr%C3%AAn%20trang%20web%20http%3A%2F%2Fchonso.mobifone.vn%20-%20T%E1%BA%A3i%20mConnect%20%C4%91%E1%BB%83%20t%E1%BA%ADn%20h%C6%B0%E1%BB%9Fng%20h%C3%A0ng%20ng%C3%A0n%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20t%E1%BB%AB%20c%C3%A1c%20%C4%91%E1%BB%91i%20t%C3%A1c%20li%C3%AAn%20k%E1%BA%BFt%20c%E1%BB%A7a%20MobiFone"));
		System.out.println(decodeURIComponent("C4%91%E1%BB%93ng%20h%C3%A0nh%20t%E1%BB%AB%20Sim%20T%C3%A1m%20MobiFone.%20Mi%E1%BB%85n%20ph%C3%AD%201000%20ph%C3%BAt%2Fth%C3%A1ng%20g%E1%BB%8Di%20cho%202%20s%E1%BB%91%20MobiFone%20kh%C3%A1c.%20C%C6%B0%E1%BB%9Bc%20g%E1%BB%8Di%20ngo%E1%BA%A1i%20m%E1%BA%A1ng%20%3D%20n%E1%BB%99i%20m%E1%BA%A1ng%20ch%E1%BB%89%20690%C4%91%2Fph%C3%BAt.%20T%E1%BA%B7ng%20th%C3%AAm%20s%E1%BB%91%20ph%C3%BAt%20g%E1%BB%8Di%20n%E1%BB%99i%20m%E1%BA%A1ng%20khi%20ph%C3%A1t%20sinh%20ph%C3%AD%20li%C3%AAn%20l%E1%BA%A1c%20ngo%E1%BA%A1i%20m%E1%BA%A1ng%20v%C3%A0%20c%C3%B2n%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20kh%C3%A1c.%20Ch%C6%B0%C6%A1ng%20tr%C3%ACnh%20%C3%A1p%20d%E1%BB%A5ng%20cho%20kh%C3%A1ch%20h%C3%A0ng%20t%E1%BA%A1i%209%20t%E1%BB%89nh%3A%20V%C5%A9ng%20T%C3%A0u%2C%20%C4%90%E1%BB%93ng%20Nai%2C%20Long%20An%2C%20T%C3%A2y%20Ninh%2C%20L%C3%A2m%20%C4%90%E1%BB%93ng%2C%20B%C3%ACnh%20D%C6%B0%C6%A1ng%2C%20B%C3%ACnh%20Ph%C6%B0%E1%BB%9Bc%2C%20B%C3%ACnh%20Thu%E1%BA%ADn%2C%20Ninh%20Thu%E1%BA%ADn.%20Th%C3%B4ng%20tin%20chi%20ti%E1%BA%BFt%20qu%C3%BD%20kh%C3%A1ch%20vui%20l%C3%B2ng%20li%C3%AAn%20h%E1%BB%87%20t%E1%BB%95ng%20%C4%91%C3%A0i%209090%20(200%C4%91%2Fph%C3%BAt)%20%C4%91%E1%BB%83%20%C4%91%C6%B0%E1%BB%A3c%20gi%E1%BA%A3i%20%C4%91%C3%A1p.%0ASo%E1%BA%A1n%20D7%20g%E1%BB%ADi%20999%20%C4%91%E1%BB%83%20c%C3%B3%20ngay%201%2C2Gb%20truy%20c%E1%BA%ADp%20Internet%20t%E1%BB%91c%20%C4%91%E1%BB%99%20cao.%0AT%E1%BA%A3i%20app%20mConnect%20t%E1%BA%A1i%20Appstore%2C%20Google%20Play%2C%20Windows%20Market%20%C4%91%E1%BB%83%20t%E1%BA%ADn%20h%C6%B0%E1%BB%9Fng%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20l%C3%AAn%20t%E1%BB%9Bi%2050%25%20c%E1%BB%A7a%20c%C3%A1c%20d%E1%BB%8Bch%20v%E1%BB%A5%20nh%C3%A0%20h%C3%A0ng%2C%20gi%E1%BA%A3i%20tr%C3%AD%2C...%20li%C3%AAn%20k%E1%BA%BFt%20v%E1%BB%9Bi%20MobiFone"));
		System.out.println(decodeURIComponent("%BB%91%20MobiFone%20kh%C3%A1c.%20C%C6%B0%E1%BB%9Bc%20g%E1%BB%8Di%20ngo%E1%BA%A1i%20m%E1%BA%A1ng%20%3D%20n%E1%BB%99i%20m%E1%BA%A1ng%20ch%E1%BB%89%20690%C4%91%2Fph%C3%BAt.%20T%E1%BA%B7ng%20th%C3%AAm%20s%E1%BB%91%20ph%C3%BAt%20g%E1%BB%8Di%20n%E1%BB%99i%20m%E1%BA%A1ng%20khi%20ph%C3%A1t%20sinh%20ph%C3%AD%20li%C3%AAn%20l%E1%BA%A1c%20ngo%E1%BA%A1i%20m%E1%BA%A1ng%20v%C3%A0%20c%C3%B2n%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20kh%C3%A1c.%20Ch%C6%B0%C6%A1ng%20tr%C3%ACnh%20%C3%A1p%20d%E1%BB%A5ng%20cho%20kh%C3%A1ch%20h%C3%A0ng%20t%E1%BA%A1i%209%20t%E1%BB%89nh%3A%20V%C5%A9ng%20T%C3%A0u%2C%20%C4%90%E1%BB%93ng%20Nai%2C%20Long%20An%2C%20T%C3%A2y%20Ninh%2C%20L%C3%A2m%20%C4%90%E1%BB%93ng%2C%20B%C3%ACnh%20D%C6%B0%C6%A1ng%2C%20B%C3%ACnh%20Ph%C6%B0%E1%BB%9Bc%2C%20B%C3%ACnh%20Thu%E1%BA%ADn%2C%20Ninh%20Thu%E1%BA%ADn.%20Th%C3%B4ng%20tin%20chi%20ti%E1%BA%BFt%20qu%C3%BD%20kh%C3%A1ch%20vui%20l%C3%B2ng%20li%C3%AAn%20h%E1%BB%87%20t%E1%BB%95ng%20%C4%91%C3%A0i%209090%20(200%C4%91%2Fph%C3%BAt)%20%C4%91%E1%BB%83%20%C4%91%C6%B0%E1%BB%A3c%20gi%E1%BA%A3i%20%C4%91%C3%A1p.%0ASo%E1%BA%A1n%20D7%20g%E1%BB%ADi%20999%20%C4%91%E1%BB%83%20c%C3%B3%20ngay%201%2C2Gb%20truy%20c%E1%BA%ADp%20Internet%20t%E1%BB%91c%20%C4%91%E1%BB%99%20cao.%0AT%E1%BA%A3i%20app%20mConnect%20t%E1%BA%A1i%20Appstore%2C%20Google%20Play%2C%20Windows%20Market%20%C4%91%E1%BB%83%20t%E1%BA%ADn%20h%C6%B0%E1%BB%9Fng%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20l%C3%AAn%20t%E1%BB%9Bi%2050%25%20c%E1%BB%A7a%20c%C3%A1c%20d%E1%BB%8Bch%20v%E1%BB%A5%20nh%C3%A0%20h%C3%A0ng%2C%20gi%E1%BA%A3i%20tr%C3%AD%2C...%20li%C3%AAn%20k%E1%BA%BFt%20v%E1%BB%9Bi%20MobiFone"));
		System.out.println(decodeURIComponent("%C4%90%C4%83ng%20k%C3%BD%20ngay%20ch%C6%B0%C6%A1ng%20tr%C3%ACnh%20K%E1%BA%BFt%20n%E1%BB%91i%20d%C3%A0i%20l%C3%A2u%20c%E1%BB%A7a%20Mobifone%20%C4%91%E1%BB%83%20nh%E1%BA%ADn%20%C4%91%C6%B0%E1%BB%A3c%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn.%20C%C3%A1ch%201%3A%20%20So%E1%BA%A1n%20DK%20g%E1%BB%ADi%209237%20(mi%E1%BB%85n%20ph%C3%AD)%20C%C3%A1ch%202%3A%20%C4%90%C4%83ng%20k%C3%BD%203G%20Mobifone%20v%C3%A0%20truy%20c%E1%BA%ADp%20trang%20http%3A%2F%2Fmobifone.vn%2C%20ch%E1%BB%8Dn%20K%E1%BA%BFt%20n%E1%BB%91i%20d%C3%A0i%20l%C3%A2u%2C%20sau%20%C4%91%C3%B3%20ch%E1%BB%8Dn%20%C4%90%C4%83ng%20k%C3%BD%20h%E1%BB%99i%20vi%C3%AAn%20k%E1%BA%BFt%20n%E1%BB%91i%20d%C3%A0i%20l%C3%A2u%20v%C3%A0%20l%C3%A0m%20theo%20h%C6%B0%E1%BB%9Bng%20d%E1%BA%ABn.%20C%C3%A1ch%203%3A%20%C4%90%E1%BA%BFn%20c%E1%BB%ADa%20h%C3%A0ng%20Mobifone%20%C4%91%C4%83ng%20k%C3%BD.%20L%C6%B0u%20%C3%BD%3A%20Thu%C3%AA%20bao%20tr%E1%BA%A3%20tr%C6%B0%E1%BB%9Bc%20ch%E1%BB%89%20%C4%91%C6%B0%E1%BB%A3c%20%C4%91%C4%83ng%20k%C3%BD%20theo%20c%C3%A1ch%202%20v%C3%A0%20c%C3%A1ch%203.%0ASo%E1%BA%A1n%208COM%20g%E1%BB%ADi%20999%20(50.000%C4%91%2F%2030%20ng%C3%A0y)%20c%C3%B3%20ngay%201%2C5GB%20Data%0AT%C3%A1m%20v%C3%B4%20t%C6%B0%2C%20ti%E1%BB%81n%20v%E1%BA%ABn%20d%C6%B0%20khi%20b%E1%BA%A1n%20%C4%91%E1%BB%93ng%20h%C3%A0nh%20t%E1%BB%AB%20Sim%20T%C3%A1m%20MobiFone.%20Mi%E1%BB%85n%20ph%C3%AD%201000%20ph%C3%BAt%2Fth%C3%A1ng%20g%E1%BB%8Di%20cho%202%20s%E1%BB%91%20MobiFone%20kh%C3%A1c.%20C%C6%B0%E1%BB%9Bc%20g%E1%BB%8Di%20ngo%E1%BA%A1i%20m%E1%BA%A1ng%20%3D%20n%E1%BB%99i%20m%E1%BA%A1ng%20ch%E1%BB%89%20690%C4%91%2Fph%C3%BAt.%20T%E1%BA%B7ng%20th%C3%AAm%20s%E1%BB%91%20ph%C3%BAt%20g%E1%BB%8Di%20n%E1%BB%99i%20m%E1%BA%A1ng%20khi%20ph%C3%A1t%20sinh%20ph%C3%AD%20li%C3%AAn%20l%E1%BA%A1c%20ngo%E1%BA%A1i%20m%E1%BA%A1ng%20v%C3%A0%20c%C3%B2n%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20kh%C3%A1c.%20Ch%C6%B0%C6%A1ng%20tr%C3%ACnh%20%C3%A1p%20d%E1%BB%A5ng%20cho%20kh%C3%A1ch%20h%C3%A0ng%20t%E1%BA%A1i%209%20t%E1%BB%89nh%3A%20V%C5%A9ng%20T%C3%A0u%2C%20%C4%90%E1%BB%93ng%20Nai%2C%20Long%20An%2C%20T%C3%A2y%20Ninh%2C%20L%C3%A2m%20%C4%90%E1%BB%93ng%2C%20B%C3%ACnh%20D%C6%B0%C6%A1ng%2C%20B%C3%ACnh%20Ph%C6%B0%E1%BB%9Bc%2C%20B%C3%ACnh%20Thu%E1%BA%ADn%2C%20Ninh%20Thu%E1%BA%ADn.%20Th%C3%B4ng%20tin%20chi%20ti%E1%BA%BFt%20qu%C3%BD%20kh%C3%A1ch%20vui%20l%C3%B2ng%20li%C3%AAn%20h%E1%BB%87%20t%E1%BB%95ng%20%C4%91%C3%A0i%209090%20(200%C4%91%2Fph%C3%BAt)%20%C4%91%E1%BB%83%20%C4%91%C6%B0%E1%BB%A3c%20gi%E1%BA%A3i%20%C4%91%C3%A1p.%0ASo%E1%BA%A1n%20D7%20g%E1%BB%ADi%20999%20%C4%91%E1%BB%83%20c%C3%B3%20ngay%201%2C2Gb%20truy%20c%E1%BA%ADp%20Internet%20t%E1%BB%91c%20%C4%91%E1%BB%99%20cao.%0AT%E1%BA%A3i%20app%20mConnect%20t%E1%BA%A1i%20Appstore%2C%20Google%20Play%2C%20Windows%20Market%20%C4%91%E1%BB%83%20t%E1%BA%ADn%20h%C6%B0%E1%BB%9Fng%20nhi%E1%BB%81u%20%C6%B0u%20%C4%91%C3%A3i%20h%E1%BA%A5p%20d%E1%BA%ABn%20l%C3%AAn%20t%E1%BB%9Bi%2050%25%20c%E1%BB%A7a%20c%C3%A1c%20d%E1%BB%8Bch%20v%E1%BB%A5%20nh%C3%A0%20h%C3%A0ng%2C%20gi%E1%BA%A3i%20tr%C3%AD%2C...%20li%C3%AAn%20k%E1%BA%BFt%20v%E1%BB%9Bi%20MobiFone"));
		
	}
}

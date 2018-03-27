package elcom.com.util;

import java.util.*;
import com.elcom.DBI.SubProParam;
import java.util.StringTokenizer;
import elcom.com.core.dao.*;
import java.io.*;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class testdemo {
	enum ValueEnum {
		checInGuest, checOutGuest, changeGuestInfo, moveGuestRoom, moveAllGuestRoom, recievMessage, requestMessage
	}

	public testdemo() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void processRecord(String aLine, String proName, int pamramNum) {
		StringBuffer buffer = new StringBuffer();
		String bufferData = "";
		StringBuffer bufferValue = new StringBuffer();
		StringTokenizer tokenizer = new StringTokenizer(aLine, "|");
		Vector params = new Vector();
		int i = 0;
		int countToken = tokenizer.countTokens() - 1;
		System.out.println("countToken=[" + countToken + "]");
		tokenizer.nextToken();// bo di token dau tien chua command
		while (tokenizer.hasMoreTokens()) {
			String data = tokenizer.nextToken();
			SubProParam subVar = new SubProParam(data, SubProParam.IN);
			params.add(subVar);
			buffer.append("?");
			bufferValue.append(data);
			// if (i != countToken) {
			buffer.append(",");
			bufferValue.append(",");
			// }
		}
		if (countToken < pamramNum) {
			for (int k = countToken; k < 12; k++) {
				buffer.append("?");
				SubProParam subVar = new SubProParam("-900009", SubProParam.IN);
				params.add(subVar);
				bufferValue.append("-900009");
				if (k != pamramNum - 1) {
					buffer.append(",");
					bufferValue.append(",");
				}
			}
			bufferData = buffer.toString();
		} else {
			String tmp = buffer.toString();
			bufferData = tmp.substring(0, (tmp.length() - 1));

		}

		SQLPMS.BODYROCEDURE = proName + "(" + bufferData + ")";
		String sqlQuery = SQLPMS.STARTPROCEDURE + SQLPMS.BODYROCEDURE + SQLPMS.ENDPROCEDURE;
		System.out.println("sqlQuery=[" + sqlQuery + "]");
		System.out.println("value=[" + bufferValue.toString() + "]");

	}

	public void test(String proName_) {
		ValueEnum enumpro = ValueEnum.valueOf(proName_);
		switch (enumpro) {
		case checInGuest: // checkin

			// xu ly o day
			break;
		case changeGuestInfo: // change info guest

			// xu ly o day
			break;
		case checOutGuest:

			// xu ly o day
			break;
		}

	}

	protected void processLine(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("|");
		String procedureName = "checInGuest";
		String paramNum = "12";

		if (scanner.hasNext()) {

			if (procedureName != null && !procedureName.equals("")) {
				processRecord(aLine, procedureName, Utils.parseInt(paramNum));
			}
		}
	}

	public void createFile() {
		File aFile = new File("E:/Project/Workspace/Eod_App/IHotelPMS/Up");
		try {
			aFile.createNewFile();
		} catch (IOException ex) {
		}
	}

	public static String getPathLang() {
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");

		System.out.println(path);
		return path;
	}

	@SuppressWarnings({ "unused", "static-access" })
	public static void main(String[] arg) {
		testdemo demo = new testdemo();

		demo.getPathLang();

		String checkIn = "GI|R#1234|G#8874|GA110327|GD110329|GTMr.|GFTiep|GNDo Viet|RN7425|GP9876|GE0|";
		// demo.processRecord(checkIn, "checInGuest", 12);

		String checkOut = "GO|R#1234|";
		// demo.processRecord(checkOut, "checOutGuest", 1);

		String move = "GC|R#1234|RN7132|";
		// demo.processRecord(move, "moveGuestRoom", 2);
		Date date = Utils.parseDate("28/04/2011 12:35:55");
		System.out.println(Utils.format(date, "hh:MM:ss"));

//		String data = "XL|MI11|G#22| tour Nha Trang\r\ |DA110428|TI21:27:04|RN220|";
//		System.out.println(data.replaceAll("\r\n", "</br>"));
	}

}

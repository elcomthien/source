package elcom.com.core.read;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import com.elcom.DBI.DBI;
import com.elcom.Log.FileEvent;

import elcom.com.apiconnect.FTPConnect;
import elcom.com.apiconnect.eConnection;
import elcom.com.util.Utils;

/**
 * <p>
 * Title: Interface PMS and IPTV
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 03/2011
 * </p>
 * 
 * <p>
 * Company:Elcom-HCM@
 * </p>
 * 
 * @author hoavk@
 * @version 1.0
 */
public abstract class ReadFileCommon {
	protected File file;
	InputStreamReader aFile;
	protected String filePath;
	CMDMap cmdMap = null;
	ParamMap paramMap = null;
	FileEvent log = new FileEvent("RECIVE");

	public ReadFileCommon(String fileName) {
		this.file = new File(fileName);
		this.filePath = fileName;
		cmdMap = new CMDMap();
		paramMap = new ParamMap();
	}

	@SuppressWarnings("unused")
	public void readFile() {
		System.out.println("Timer start process file =" + new Date());
		Scanner scanner = null;
		String aline = "";
		String path = Utils.getRealPath();// duong dan thu muc lam viec
		String pmsDir = filePath;// thu muc dang xu ly file pms
		String pmsProcessed = path + "/PMSProcessed/" + file.getName();// thu muc xu ly xong file pms

		try {
			Utils.outScreen(log, "eHotel recieve file = [" + file.getName() + "]", true);
			System.out.println("eHotel recieve file = [" + file.getName() + "]-------------------------");
			scanner = new Scanner(file, "UTF-8");

			while (scanner.hasNextLine()) {
				aline = scanner.nextLine();
				Utils.outScreen(log, "aLine=====================================================================", false);
				Utils.outScreen(log, "Before process data = [" + aline + "] , [fileName=" + file.getName() + "]", false);
				if (aline != null && aline.length() > 1) {// it nhat co command=2 char
					String strProccess = processLine(aline);
				}
			}
			scanner.close();
			System.out.println("Timer end process file= " + new Date());// hoan tat qua trinh doc va xu ly file
			// thuc hien thao tac move va delete file
			try {

				Utils.outScreen(log, "Move [" + file.getName() + "] into dir=[" + pmsProcessed + "]", false);
				System.out.println("Move [" + file.getName() + "] into dir=[" + pmsProcessed + "]");
				FTPConnect.copy(pmsDir, pmsProcessed);
				file.delete();

			} catch (IOException ex1) {
				Utils.outScreen(log, "Error process FTPConnect=[" + ex1.getMessage() + "]", false);
				FTPConnect.copy(pmsDir, pmsProcessed);
				file.delete();

			}
		} catch (Exception ex) {
			Utils.outScreen(log, "Error process at aLine=[" + aline + "]|Cause :" + ex.getMessage() + "]", false);
			try {
				FTPConnect.copy(pmsDir, pmsProcessed);
			} catch (IOException ex2) {
				Utils.outScreen(log, ex2.getMessage(), false);
			}
			file.delete();
		}
	}

	@SuppressWarnings("static-access")
	protected String processLine(String aLine) {
		String procedureName = "";
		String paramNum = "";
		String result = "";
		int pos = aLine.indexOf("|");
		String command = aLine.substring(0, pos);//
		procedureName = (String) cmdMap.ICMDMap.get(command);
		if (procedureName == null) {
			Utils.outScreen(log, "Error encoding is not Unicode.", false);
		}
		paramNum = (String) cmdMap.IParamMap.get(procedureName);
		System.out.print("Command=" + command + " ,store=" + procedureName + " ,paramNum=" + paramNum);
		if (procedureName != null && !procedureName.equals("")) {
			result = processRecord(aLine, procedureName, Utils.parseInt(paramNum), log);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	protected void executeSQL(String statement, Vector params) {
		eConnection con = new eConnection();
		DBI dbi = con.getDBICore();
		try {
			dbi.executeSubPro(statement, params);
		} catch (Exception ex) {
		}
	}

	public abstract String processRecord(String aLine, String proName, int pamramNum, FileEvent log);
}

package com.elcom.ehotel.esmile.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLog {
	private String file;
	private String fileNameLogDefault;
	private static String fs;
	private boolean isJustCreate;
	private String lastMsg;
	private String logbuff;
	private static String lsp = System.getProperty("line.separator");
	@SuppressWarnings("unused")
	private String name;
	private String path;
	private String pathHOME;
	private String pathNameLogDefault;
	private String prefix;
	private boolean screenout;
	private Util util;

	public FileLog(String aPrefix) {
		screenout = false;
		name = "noname";
		lastMsg = "";
		isJustCreate = false;
		logbuff = "";
		path = "";
		file = "";
		util = new Util();
		pathHOME = util.getPathHOME();
		prefix = "";
		prefix = aPrefix;
	}

	public FileLog() {
		screenout = false;
		name = "noname";
		lastMsg = "";
		isJustCreate = false;
		logbuff = "";
		path = "";
		file = "";
		util = new Util();
		pathHOME = util.getPathHOME();
		prefix = "";
		prefix = "";
	}

	public synchronized void flush() {
		File logfile;
		if (logbuff.length() == 0)
			return;
		if (path.length() == 1 && path.equals(fs))
			if (path.length() == 1) {
				path = fs;
			}
		File folder = new File(path);
		folder.mkdirs();
		logfile = new File(path + file);
		if (logfile.exists())
			try {
				logfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		isJustCreate = true;
		isJustCreate = false;
		String pathfile = path + file;
		PrintWriter pw;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(pathfile, true)));

			if (isJustCreate) {
				pw.println("[dd/mm/yyyy hh:mm:ss messageLog]");
				isJustCreate = false;
			}
			pw.print(logbuff);
			logbuff = "";
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	private void initPathFile() {
		pathNameLogDefault = pathHOME + fs + "Log" + fs;
		fileNameLogDefault = prefix + util.getTimeCurrent("yyyyMMdd") + ".log";
		isJustCreate = false;
	}

	public static void main(String args[]) {
		System.out.println("Begin");
		FileLog log = new FileLog();
		log.write("dfg fd dfbg dgdf gdgd  ");
		log.write("dfg fd dfbg dgdf gdgd  ");
		log.flush();
		System.out.println("End");
	}

	public synchronized void setScreenOut(boolean b) {
		screenout = b;
	}

	public synchronized void write(String strPath, String strFile, String content) {
		String currentTime;
		String currMsg;
		currentTime = util.getTimeCurrent();
		currMsg = "";
		currMsg = currentTime.substring(0, 16) + " " + content;
		if (lastMsg.equals(currMsg))
			return;
		lastMsg = currMsg;
		path = strPath;
		file = strFile;
		logbuff += currentTime + " " + content + lsp;
		if (logbuff.length() >= 1024)
			flush();
		return;
	}

	public synchronized void write(String file, String content) {
		initPathFile();
		write(pathNameLogDefault, file, content);
	}

	public synchronized void write(String content) {
		System.out.println(content);
		initPathFile();
		if (screenout) {
			System.out.println(content);
			return;
		} else {
			write(fileNameLogDefault, content);
			return;
		}
	}

	static {
		fs = File.separator;
	}

}

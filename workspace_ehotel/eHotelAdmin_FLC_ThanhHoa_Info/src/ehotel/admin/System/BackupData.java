package ehotel.admin.System;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;

public class BackupData extends ServiceParent {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd = -1;
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
			}
		}
		switch (cmd) {
		case -1: {
			int subId = -1;
			int menuid = -1;
			// redirect page den trang ServiceSystemMain.jsp
			if (request.getParameter(Def.MenuId) != null) {
				menuid = Integer.parseInt(request.getParameter(Def.MenuId)
						.toString());
			}
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId)
						.toString());
			}
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);
			request.setAttribute("fileJSP", "../system/Backup.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 3: { //set schedule backup
			System.out.println("Config schedule backup");
			String schedule = "";
			if (request.getParameter("schedule") != null) {
				schedule = request.getParameter("schedule").toString().trim();
			}
			updateSchedule(schedule);
			runBackup();
			break;
		}
		case 9: { // get list file backup
			response.setContentType("text/xml");
			String path = getValueProperties("linksavebackup");
			String st = getListFileBackup(path);
			out.print(st);
			break;
		}

		case 2: { // get schedule backup

			String st = "";
			st = getScheduleBackup();
			out.print(st);
			break;
		}
		default: {
			break;
		}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public String getListFileBackup(String path) {
		String link = getValueProperties("linkdownloadbakup");
		List<String> list = new ArrayList<String>();
		list = getFile(path);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n";
		mData += "<xml  count=\"" + list.size() + "\">";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i) + "]]>";
			mData += "</name>";
			mData += "<file>";
			mData += "<![CDATA[" + link + list.get(i) + "]]>";
			mData += "</file>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	public List<String> getFile(String path) {
		List<String> results = new ArrayList<String>();
		File[] files = new File(path).listFiles();
		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}
		return results;
	}

	public String getValueProperties(String key) {
		String path = System.getProperty("user.dir");
		String result = "";
		Properties prop = new Properties();
		InputStream is;
		try {
			is = new FileInputStream(path + "/Config/config.properties");
			prop.load(is);
			result = prop.getProperty(key);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getScheduleBackup() {
		String pathbackup = getValueProperties("pathbackup");
		String result = "";
		Properties prop = new Properties();
		InputStream is;
		try {
			is = new FileInputStream(pathbackup + "/Config/config.properties");
			prop.load(is);
			result = prop.getProperty("day_schedule");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateSchedule(String value) {
		boolean flag = true;
		String pathbackup = getValueProperties("pathbackup");
		String path = pathbackup + "/Config/config.properties";
		try {
			FileInputStream in;
			in = new FileInputStream(path);

			Properties props = new Properties();
			props.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream(path);
			props.setProperty("day_schedule", value);
			props.store(out, null);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void runBackup() {
		String[] listCmd = new String[] { "/bin/bash", "-c",
				"service eod_backupdb start" };
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(listCmd);
			run.freeMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("D://data/a.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("D://data/a.properties");
		props.setProperty("filename", "testtest2");
		props.store(out, null);
		out.close();
	}

}

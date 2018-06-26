package ehotel.admin.System;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.ServiceSystemPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.Vod.ConfigService;
import ehotel.admin.common.ftpClient;
import ehotel.admin.dao.FolioServiceDBI;
//import ehotel.admin.dbi.FolioTypePojo;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.core.FTPGatewayInterface;
import ehotel.core.FTPServerStruct;

public class Background extends ServiceParent {
	private static final long serialVersionUID = 1L;
	public String os = System.getProperty("os.name").toLowerCase();
	// service cung cap API cho ServiceSystem
	ServiceSystemPMS main = new ServiceSystemPMS();
	private ConfigService configService = new ConfigService();
	private FolioServiceDBI folioServiceDBI = new FolioServiceDBI();

	// 3 ham khoi tao va destroy
	public Background() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
	}

	// phuong thuc GET
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
			request.setAttribute("fileJSP", "../system/Background.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 3: {
			System.out.println("Get background Information");
			this.showJSPpage(request, response, "/system/detailBackground.jsp");
			break;
		}
		case 9: { // get link background logo
			String path = System.getProperty("user.dir");
			String linkimage = "";
			String hotel = "";
			Properties prop = new Properties();
			InputStream is;
			try {
				is = new FileInputStream(path + "/Config/config.properties");
				prop.load(is);
				linkimage = prop.getProperty("linksaveimage");
				hotel = prop.getProperty("image.dir.Hotelinfo");
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			String result = "";
			result = linkimage + hotel;
			// String st = getFileBackgroundLogo(result);
			// System.out.println(st);
			out.print(result);
			break;
		}
		case 10: {
			System.out.println("get list file Movie FTP");
			String path = "";
			if (request.getParameter("path") != null) {
				path = request.getParameter("path").toString().trim();
			}

			String host = "";
			int port = 21;
			String user = "";
			String pass = "";
			String pathFile = getServletContext().getRealPath("")
					+ File.separatorChar + "data" + File.separatorChar
					+ "data.txt";
			String text = configService.ReadFile(pathFile);
			if (!(text.equalsIgnoreCase(""))) {
				String[] arr = text.split(",");
				host = arr[0];
				port = Integer.parseInt(arr[1]);
				user = arr[2];
				pass = arr[3];
			}

			response.setContentType("text/xml");
			String xml = getfileFTP(path, host, user, pass, port);
			out.print(xml);
			break;
		}
		case 2: { // get type for background anh music
			String path = System.getProperty("user.dir");
			String linkimage = "";
			String welcom = "";
			Properties prop = new Properties();
			InputStream is;
			try {
				is = new FileInputStream(path + "/Config/config.properties");
				prop.load(is);
				linkimage = prop.getProperty("linksaveimage");
				welcom = prop.getProperty("image.dir.welcome");
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			String link = "";
			link = linkimage + welcom;
			response.setContentType("text/xml");
			String st = "";//getTypeFolio(link);
			out.print(st);
			break;
		}
		case 4: {
			System.out.println("Get background image for folio type");
			this.showJSPpage(request, response,
					"/system/detailBackgroundImage.jsp");
			break;
		}
		case 5: {
			System.out.println("Get background music for folio type");
			this.showJSPpage(request, response,
					"/system/detailBackgroundMusic.jsp");
			break;
		}
		default: {
			break;
		}
		}
	}

	// phuong thuc POST
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd = -1;
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		switch (cmd) {
		case 3: {
			System.out.println("Update background system");
			String name = "";
			String image = "";
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			String filename = image.substring(0, image.lastIndexOf("."));
			if (!filename.equalsIgnoreCase(name)) {
				String filetype = image.substring(image.lastIndexOf("."));
				String path1 = config._temp + "/" + image;
//				String path2 = config._pathImage + config._hotel + "/" + name + filetype;
				String path2 = config._pathImage + config._hotel + "/" + name + ".png";
				System.out.println("path 1: " + path1);
				System.out.println("path 2: " + path2);
				ManagerFile file = new ManagerFile();
				file.deletefile(path2);
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 4: {
			String filename = "";
			String path = "";
			if (request.getParameter("filename") != null)
				filename = request.getParameter("filename").trim();
			if (request.getParameter("path") != null)
				path = request.getParameter("path").trim();
			if (transferBackgroundVideo(filename, path) != null)
				out.print("success");
			else
				out.print("unsuccess");
			break;
		}
		case 1: {
			String filename = "";
			String path = "";
			String name = "";
			if (request.getParameter("filename") != null)
				filename = request.getParameter("filename").trim();
			if (request.getParameter("path") != null)
				path = request.getParameter("path").trim();
			if (request.getParameter("name") != null)
				name = request.getParameter("name").trim();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar cal = Calendar.getInstance();
			String save = dateFormat.format(cal.getTime());

			if (transferBackgroundMusic(filename, path, save) != null) {
				// if (!save.equalsIgnoreCase("")){
//				folioServiceDBI.updateBackgroundMusic(name, save + ".mp3");
				out.print("success");
			} else
				out.print("unsuccess");
			break;
		}
		case 2: {
			System.out.println("Update background image");
			String name = "";
			String image = "";
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
//			folioServiceDBI.updateBackgroundImage(name, image);
			String path1 = config._temp + "/" + image;
			String path2 = config._pathImage + config._advertise + "/" + image;
			// System.out.println("path 1: " + path1);
			// System.out.println("path 2: " + path2);
			ManagerFile file = new ManagerFile();
			file.copy(path1, path2);
			file.deletefile(path1);
			break;
		}
		default: {
			break;
		}
		}
	}

	public String getFileBackgroundLogo(String link) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String path = config._pathImage + config._hotel;
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n";
		mData += "<xml>";

		String[] listCmd = new String[] { "/bin/bash", "-c",
				"ls " + path + "/bg.* " + path + "/logo*" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		String lsString = null;
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(runtimeProcess.getInputStream()));
			while ((lsString = bufferedReader.readLine()) != null) {
				String name = lsString.substring(lsString.lastIndexOf("/") + 1);
				mData += setXML(name);
			}
			run.freeMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mData += "<link><![CDATA[" + link + "]]></link>";
		mData += "</xml>";
		return mData;
	}

	public String setXML(String name) {
		String text = "<Item>";
		if (name.indexOf("bg") >= 0)
			text += "<background><![CDATA[" + name + "]]></background>";
		else if (name.indexOf("small") >= 0)
			text += "<logo_small><![CDATA[" + name + "]]></logo_small>";
		else
			text += "<logo><![CDATA[" + name + "]]></logo>";
		text += "</Item>";
		return text;
	}

	private String getfileFTP(String path, String Host, String User,
			String Pass, int Port) {

		ftpClient ftp = new ftpClient(Host, User, Pass, Port);
		ftp.connect();
		Vector<String> v_files = new Vector<String>();
		Vector<String> v_folder = new Vector<String>();
		v_files = ftp.getFiles(path);
		v_folder = ftp.getFolder(path);
		ftp.close();
		for (int i = 0; i < v_folder.size(); i++) {
			for (int j = 0; j < v_files.size(); j++) {
				if (v_files.get(j).equals(v_folder.get(i))) {
					v_files.remove(j);
					j--;
				}
			}
		}
		for (int i = 0; i < v_folder.size(); i++) {
			if (v_folder.get(i).equals(".") || v_folder.get(i).equals("..")) {
				v_folder.remove(i);
				i--;
			}
		}
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < v_folder.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += v_folder.get(i);
			mData += "</name>";
			mData += "<type>";
			mData += "1";
			mData += "</type>";
			mData += "</Item>";
		}
		for (int i = 0; i < v_files.size(); i++) {
			System.out.println("file:" + v_files.get(i));
			mData += "<Item>";
			mData += "<name>";
			mData += v_files.get(i);
			mData += "</name>";
			mData += "<type>";
			mData += "0";
			mData += "</type>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	public UUID transferBackgroundVideo(String filename, String path) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		// ////////////duong dan luu background
		String pathsave = config._FilePathserver + config._FolderMusic;
		// ////////////ten file background day len server
		String namesave = "-200";

		String filetype = "";
		filetype = filename.substring(filename.lastIndexOf("."));
		String hostsrc = "";
		int portsrc = 21;
		String usersrc = "";
		String passsrc = "";
		String pathFile = getServletContext().getRealPath("")
				+ File.separatorChar + "data" + File.separatorChar + "data.txt";
		String text = configService.ReadFile(pathFile);
		if (text != "") {
			String[] arr = text.split(",");
			hostsrc = arr[0];
			portsrc = Integer.parseInt(arr[1]);
			usersrc = arr[2];
			passsrc = arr[3];
		}

		String hostserver = config._HostFtpServer;
		UUID id = null;
		try {
			// cau hinh rmi
			FTPGatewayInterface ftpgateway;
			ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://"
					+ hostserver + ":2099/elc_ftpgateway");

			// down load file from src to server
			FTPServerStruct server = new FTPServerStruct(hostsrc, portsrc,
					usersrc, passsrc, path + "/" + filename);
			id = ftpgateway.download(server, hostserver, pathsave + "/"
					+ namesave + filetype, 5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public UUID transferBackgroundMusic(String filename, String path,
			String namesave) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		// ////////////duong dan luu background
		String pathsave = config._FilePathserver + config._FolderMusic;
		// ////////////ten file background day len server
		// String namesave = "-100";

		String filetype = "";
		filetype = filename.substring(filename.lastIndexOf("."));
		String hostsrc = "";
		int portsrc = 21;
		String usersrc = "";
		String passsrc = "";
		String pathFile = getServletContext().getRealPath("")
				+ File.separatorChar + "data" + File.separatorChar + "data.txt";
		String text = configService.ReadFile(pathFile);
		if (text != "") {
			String[] arr = text.split(",");
			hostsrc = arr[0];
			portsrc = Integer.parseInt(arr[1]);
			usersrc = arr[2];
			passsrc = arr[3];
		}

		String hostserver = config._HostFtpServer;
		UUID id = null;
		try {
			// cau hinh rmi
			FTPGatewayInterface ftpgateway;
			ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://"
					+ hostserver + ":2099/elc_ftpgateway");

			// down load file from src to server
			FTPServerStruct server = new FTPServerStruct(hostsrc, portsrc,
					usersrc, passsrc, path + "/" + filename);
			id = ftpgateway.download(server, hostserver, pathsave + "/"
					+ namesave + filetype, 5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

//	public String getTypeFolio(String link) {
//		List<FolioTypePojo> list = new ArrayList<FolioTypePojo>();
//		list = folioServiceDBI.getTypeFolio();
//		String mData = "";
//		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n";
//		mData += "<xml  count=\"" + list.size() + "\">";
//		for (int i = 0; i < list.size(); i++) {
//			mData += "<Item>";
//			mData += "<id>";
//			mData += "<![CDATA[" + list.get(i).getId() + "]]>";
//			mData += "</id>";
//			mData += "<name>";
//			mData += "<![CDATA[" + list.get(i).getTypename() + "]]>";
//			mData += "</name>";
//			mData += "<bgpic>";
//			mData += "<![CDATA[" + link + "/" + list.get(i).getTypepic()
//					+ "]]>";
//			mData += "</bgpic>";
//			mData += "<bgmusic>";
//			mData += "<![CDATA[" + list.get(i).getTypemusic() + "]]>";
//			mData += "</bgmusic>";
//			mData += "</Item>";
//		}
//		mData += "</xml>";
//		return mData;
//	}
	
}

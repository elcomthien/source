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
import java.util.Date;
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
import ehotel.admin.dbi.FolioServiceDBI;
import ehotel.admin.dbi.FolioTypePojo;
import ehotel.admin.dbi.SystemDBI;
import ehotel.admin.model.WelcomeMediaModel;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.TransferImageFLC;
import ehotel.core.FTPGatewayInterface;
import ehotel.core.FTPServerStruct;

public class Background extends ServiceParent {
	
	private SystemDBI systemDBI = new SystemDBI();
	public Background() {
		os = System.getProperty("os.name").toLowerCase();
		main = new ServiceSystemPMS();
		configService = new ConfigService();
		folioServiceDBI = new FolioServiceDBI();
		transferImageFLC = new TransferImageFLC();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd = -1;
		if (request.getParameter("CMD") != null)
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception exception) {
			}
		switch (cmd) {
		case 0: // '\0'
		case 1: // '\001'
//		case 6: // '\006'
//		case 7: // '\007'
//		case 8: // '\b'
		default:
			break;

		case -1: {
			int subId = -1;
			int menuid = -1;
			if (request.getParameter("MenuId") != null)
				menuid = Integer.parseInt(request.getParameter("MenuId").toString());
			if (request.getParameter("SubId") != null)
				subId = Integer.parseInt(request.getParameter("SubId").toString());
			request.setAttribute("MenuId", Integer.valueOf(menuid));
			request.setAttribute("SubId", Integer.valueOf(subId));
			request.setAttribute("fileJSP", "../system/Background.jsp");
			showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}

		case 3: // '\003'
		{
			System.out.println("Get background Information");
			showJSPpage(request, response, "/system/detailBackground.jsp");
			break;
		}

		case 9: // '\t'
		{
			String path = System.getProperty("user.dir");
			String linkimage = "";
			String hotel = "";
			Properties prop = new Properties();
			try {
				InputStream is = new FileInputStream((new StringBuilder(String.valueOf(path))).append("/Config/config.properties")
						.toString());
				prop.load(is);
				linkimage = prop.getProperty("linksaveimage");
				hotel = prop.getProperty("image.dir.Hotelinfo");
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String result = "";
			result = (new StringBuilder(String.valueOf(linkimage))).append(hotel).toString();
			out.print(result);
			break;
		}

		case 10: // '\n'
		{
			System.out.println("get list file Movie FTP");
			String path = "";
			if (request.getParameter("path") != null)
				path = request.getParameter("path").toString().trim();
			String host = "";
			int port = 21;
			String user = "";
			String pass = "";
			String pathFile = (new StringBuilder(String.valueOf(getServletContext().getRealPath("")))).append(File.separatorChar)
					.append("data").append(File.separatorChar).append("data.txt").toString();
			String text = configService.ReadFile(pathFile);
			if (!text.equalsIgnoreCase("")) {
				String arr[] = text.split(",");
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

		case 2: // '\002'
		{
			String path = System.getProperty("user.dir");
			String linkimage = "";
			String welcom = "";
			Properties prop = new Properties();
			try {
				InputStream is = new FileInputStream((new StringBuilder(String.valueOf(path))).append("/Config/config.properties")
						.toString());
				prop.load(is);
				linkimage = prop.getProperty("linksaveimage");
				welcom = prop.getProperty("image.dir.welcome");
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String link = "";
			link = (new StringBuilder(String.valueOf(linkimage))).append(welcom).toString();
			response.setContentType("text/xml");
			String st = getTypeFolio(link);
			out.print(st);
			break;
		}

		case 4: // '\004'
		{
			System.out.println("Get background image for folio type");
			showJSPpage(request, response, "/system/detailBackgroundImage.jsp");
			break;
		}

		case 5: // '\005'
		{
			System.out.println("Get background music for folio type");
			showJSPpage(request, response, "/system/detailBackgroundMusic.jsp");
			break;
		}
		case 6: {
			System.out.println("get list welcome media!!!");
			String type = request.getParameter("type").trim();
			String xml = getListMedia(type);
			response.setContentType("text/xml");
			out.print(xml);
			break;
		}
		case 7: {
			System.out.println("Change status welcome media(Video)");
			String id = request.getParameter("id");
			int rs = systemDBI.changeStatusWelcomeMedia(id);
			out.print(rs);
			break;
		}
		case 8: {
			System.out.println("Delete welcome media(Video)");
			String id = request.getParameter("id");
			int rs = systemDBI.deleteWelcomeMedia(id);
			out.print(rs);
			break;
		}
		case 11: {
			System.out.println("Get form add welcome media!!!");
			this.showJSPpage(request, response, "/system/formAddBackgroundVideo.jsp");
			break;
		}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd = -1;
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		if (request.getParameter("CMD") != null)
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		switch (cmd) {
		default:
			break;

		case 3: // '\003'
		{
			System.out.println("Update background system");
			String name = "";
			String image = "";
			if (request.getParameter("name") != null)
				name = request.getParameter("name").toString();
			if (request.getParameter("image") != null)
				image = request.getParameter("image").toString().trim();
			String filename = image.substring(0, image.lastIndexOf("."));
			if (!filename.equalsIgnoreCase(name)) {
				String filetype = image.substring(image.lastIndexOf("."));
				String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
				String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._hotel).append("/").append(name)
						.append(".png").toString();
				System.out.println((new StringBuilder("path 1: ")).append(path1).toString());
				System.out.println((new StringBuilder("path 2: ")).append(path2).toString());
				ManagerFile file = new ManagerFile();
				file.deletefile(path2);
				file.copy(path1, path2);
				transferImageFLC.transferImageFLC23(path2, config._hotel);
				// file.deletefile(path1);
			}
			break;
		}

		case 4: {
			String name = "";
			String index = "";
			String filename = "";
			String path = "";
			if (request.getParameter("filename") != null)
				filename = request.getParameter("filename").trim();
			if (request.getParameter("name") != null)
				name = request.getParameter("name").trim();
			if (request.getParameter("index") != null)
				index = request.getParameter("index").trim();
			if (request.getParameter("path") != null)
				path = request.getParameter("path").trim();
			String savename = transferBackgroundVideo(filename, path);
			WelcomeMediaModel wlc = new WelcomeMediaModel();
			wlc.setName(name);
			wlc.setFilename(savename);
			wlc.setIndex(index);
			wlc.setType("VIDEO");
			int rs = systemDBI.addWelcomeMedia(wlc);
			out.print(rs);
			break;
		}

		case 1: // '\001'
		{
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
				folioServiceDBI.updateBackgroundMusic(name, (new StringBuilder(String.valueOf(save))).append(".mp3").toString());
				out.print("success");
			} else {
				out.print("unsuccess");
			}
			break;
		}

		case 2: // '\002'
		{
			System.out.println("Update background image");
			String name = "";
			String image = "";
			if (request.getParameter("name") != null)
				name = request.getParameter("name").toString();
			if (request.getParameter("image") != null)
				image = request.getParameter("image").toString().trim();
			folioServiceDBI.updateBackgroundImage(name, image);
			String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
			String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._advertise).append("/").append(image)
					.toString();
			ManagerFile file = new ManagerFile();
			file.copy(path1, path2);
			transferImageFLC.transferImageFLC23(path1, config._advertise);
			file.deletefile(path1);
			break;
		}
		}
	}

	public String getFileBackgroundLogo(String link) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String path = (new StringBuilder(String.valueOf(config._pathImage))).append(config._hotel).toString();
		String mData = "";
		mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n")
				.toString();
		mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
		String listCmd[] = { "/bin/bash", "-c",
				(new StringBuilder("ls ")).append(path).append("/bg.* ").append(path).append("/logo*").toString() };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		String lsString = null;
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
			while ((lsString = bufferedReader.readLine()) != null) {
				String name = lsString.substring(lsString.lastIndexOf("/") + 1);
				mData = (new StringBuilder(String.valueOf(mData))).append(setXML(name)).toString();
			}
			run.freeMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mData = (new StringBuilder(String.valueOf(mData))).append("<link><![CDATA[").append(link).append("]]></link>").toString();
		mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
		return mData;
	}

	public String setXML(String name) {
		String text = "<Item>";
		if (name.indexOf("bg") >= 0)
			text = (new StringBuilder(String.valueOf(text))).append("<background><![CDATA[").append(name).append("]]></background>")
					.toString();
		else if (name.indexOf("small") >= 0)
			text = (new StringBuilder(String.valueOf(text))).append("<logo_small><![CDATA[").append(name).append("]]></logo_small>")
					.toString();
		else
			text = (new StringBuilder(String.valueOf(text))).append("<logo><![CDATA[").append(name).append("]]></logo>").toString();
		text = (new StringBuilder(String.valueOf(text))).append("</Item>").toString();
		return text;
	}

	@SuppressWarnings("rawtypes")
	private String getfileFTP(String path, String Host, String User, String Pass, int Port) {
		ftpClient ftp = new ftpClient(Host, User, Pass, Port);
		ftp.connect();
		Vector v_files = new Vector();
		Vector v_folder = new Vector();
		v_files = ftp.getFiles(path);
		v_folder = ftp.getFolder(path);
		ftp.close();
		for (int i = 0; i < v_folder.size(); i++) {
			for (int j = 0; j < v_files.size(); j++)
				if (((String) v_files.get(j)).equals(v_folder.get(i))) {
					v_files.remove(j);
					j--;
				}

		}

		for (int i = 0; i < v_folder.size(); i++)
			if (((String) v_folder.get(i)).equals(".") || ((String) v_folder.get(i)).equals("..")) {
				v_folder.remove(i);
				i--;
			}

		String mData = "";
		mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n")
				.toString();
		mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
		for (int i = 0; i < v_folder.size(); i++) {
			mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append((String) v_folder.get(i)).toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("1").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
		}

		for (int i = 0; i < v_files.size(); i++) {
			System.out.println((new StringBuilder("file:")).append((String) v_files.get(i)).toString());
			mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append((String) v_files.get(i)).toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("0").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
		}

		mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
		return mData;
	}

	// public UUID transferBackgroundVideo(String filename, String path) {
	// ConfigLoader loader = new ConfigLoader();
	// Config config = loader.getConfig();
	// String pathsave = (new StringBuilder(String.valueOf(config._FilePathserver))).append(config._FolderMusic).toString();
	// String namesave = "-100";
	// String filetype = "";
	// filetype = filename.substring(filename.lastIndexOf("."));
	// String hostsrc = "";
	// int portsrc = 21;
	// String usersrc = "";
	// String passsrc = "";
	// String pathFile = (new StringBuilder(String.valueOf(getServletContext().getRealPath("")))).append(File.separatorChar).append("data")
	// .append(File.separatorChar).append("data.txt").toString();
	// String text = configService.ReadFile(pathFile);
	// if (text != "") {
	// String arr[] = text.split(",");
	// hostsrc = arr[0];
	// portsrc = Integer.parseInt(arr[1]);
	// usersrc = arr[2];
	// passsrc = arr[3];
	// }
	// String hostserver = config._HostFtpServer;
	// UUID id = null;
	// try {
	// FTPGatewayInterface ftpgateway = (FTPGatewayInterface) Naming.lookup((new StringBuilder("rmi://")).append(hostserver)
	// .append(":2099/elc_ftpgateway").toString());
	// FTPServerStruct server = new FTPServerStruct(hostsrc, portsrc, usersrc, passsrc, (new
	// StringBuilder(String.valueOf(path))).append("/")
	// .append(filename).toString());
	// id = ftpgateway.download(server, hostserver, (new
	// StringBuilder(String.valueOf(pathsave))).append("/").append(namesave).append(filetype)
	// .toString(), 5000);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return id;
	// }

	@SuppressWarnings("unused")
	public String transferBackgroundVideo(String filename, String path) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		// ////////////duong dan luu background
		String pathsave = config._FilePathserver + config._FolderMovie;
		// ////////////ten file background day len server
		String namesave = getSaveFile();

		String filetype = "";
		filetype = filename.substring(filename.lastIndexOf("."));
		String hostsrc = "";
		int portsrc = 21;
		String usersrc = "";
		String passsrc = "";
		String pathFile = getServletContext().getRealPath("") + File.separatorChar + "data" + File.separatorChar + "data.txt";
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
			ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + hostserver + ":2099/elc_ftpgateway");

			// down load file from src to server
			FTPServerStruct server = new FTPServerStruct(hostsrc, portsrc, usersrc, passsrc, path + "/" + filename);
			id = ftpgateway.download(server, hostserver, pathsave + "/" + namesave + filetype, 5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return namesave + filetype;
	}

	public UUID transferBackgroundMusic(String filename, String path, String namesave) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String pathsave = (new StringBuilder(String.valueOf(config._FilePathserver))).append(config._FolderMusic).toString();
		String filetype = "";
		filetype = filename.substring(filename.lastIndexOf("."));
		String hostsrc = "";
		int portsrc = 21;
		String usersrc = "";
		String passsrc = "";
		String pathFile = (new StringBuilder(String.valueOf(getServletContext().getRealPath("")))).append(File.separatorChar)
				.append("data").append(File.separatorChar).append("data.txt").toString();
		String text = configService.ReadFile(pathFile);
		if (text != "") {
			String arr[] = text.split(",");
			hostsrc = arr[0];
			portsrc = Integer.parseInt(arr[1]);
			usersrc = arr[2];
			passsrc = arr[3];
		}
		String hostserver = config._HostFtpServer;
		UUID id = null;
		try {
			FTPGatewayInterface ftpgateway = (FTPGatewayInterface) Naming.lookup((new StringBuilder("rmi://")).append(hostserver)
					.append(":2099/elc_ftpgateway").toString());
			FTPServerStruct server = new FTPServerStruct(hostsrc, portsrc, usersrc, passsrc, (new StringBuilder(String.valueOf(path)))
					.append("/").append(filename).toString());
			id = ftpgateway.download(server, hostserver,
					(new StringBuilder(String.valueOf(pathsave))).append("/").append(namesave).append(filetype).toString(), 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@SuppressWarnings("rawtypes")
	public String getTypeFolio(String link) {
		List list = new ArrayList();
		list = folioServiceDBI.getTypeFolio();
		String mData = "";
		mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n")
				.toString();
		mData = (new StringBuilder(String.valueOf(mData))).append("<xml  count=\"").append(list.size()).append("\">").toString();
		for (int i = 0; i < list.size(); i++) {
			mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<id>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(((FolioTypePojo) list.get(i)).getId())
					.append("]]>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(((FolioTypePojo) list.get(i)).getTypename())
					.append("]]>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<bgpic>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(link).append("/")
					.append(((FolioTypePojo) list.get(i)).getTypepic()).append("]]>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</bgpic>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<bgmusic>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(((FolioTypePojo) list.get(i)).getTypemusic())
					.append("]]>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</bgmusic>").toString();
			mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
		}

		mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
		return mData;
	}
	
	
	public String getListMedia(String type) {
		List<WelcomeMediaModel> list = new ArrayList<WelcomeMediaModel>();
		list = systemDBI.getWelcomeMedia(type);
		String mData = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n";
		mData += "<xml  count=\"" + list.size() + "\">";
		WelcomeMediaModel wel = new WelcomeMediaModel();
		for (int i = 0; i < list.size(); i++) {
			wel = list.get(i);
			mData += "<Item>";
			mData += "<id>";
			mData += "<![CDATA[" + wel.getId() + "]]>";
			mData += "</id>";
			mData += "<name>";
			mData += "<![CDATA[" + wel.getName() + "]]>";
			mData += "</name>";
			mData += "<filename>";
			mData += "<![CDATA[" + wel.getFilename() + "]]>";
			mData += "</filename>";
			mData += "<status>";
			mData += "<![CDATA[" + list.get(i).getStatus() + "]]>";
			mData += "</status>";
			mData += "<index>";
			mData += "<![CDATA[" + list.get(i).getIndex() + "]]>";
			mData += "</index>";
			mData += "<type>";
			mData += "<![CDATA[" + list.get(i).getType() + "]]>";
			mData += "</type>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
	
	public String getSaveFile() {
		String rs = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		rs = "welcome_video_" + dateFormat.format(date);
		return rs;
	}

	private static final long serialVersionUID = 1L;
	public String os;
	ServiceSystemPMS main;
	private ConfigService configService;
	private FolioServiceDBI folioServiceDBI;
	private TransferImageFLC transferImageFLC;
}

package ehotel.admin.System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.abs.pms.ServiceSystemPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.pms.eService;

public class ServiceSystem extends ServiceParent {
	private static final long serialVersionUID = 1L;
	public String os = System.getProperty("os.name").toLowerCase();
	// service cung cap API cho ServiceSystem
	ServiceSystemPMS main = new ServiceSystemPMS();

	// 3 ham khoi tao va destroy
	public ServiceSystem() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
	}

	// phuong thuc GET
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				menuid = Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);
			request.setAttribute("fileJSP", "../system/ServiceSystemMain.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		// bo sung viec lay list khi moi load page
		case 1: {
			System.out.println("Khoi tao: Lay tat ca service trong ServiceSystem");
			int index = 0;
			int page = 6;
//			int langId = 1;
//			if (request.getParameter("lang") != null) {
//				langId = Integer.parseInt(request.getParameter("lang").toString().trim());
//			}
			response.setContentType("text/xml");
			String st = getServices(index, page, LangID);
			out.print(st);
			break;
		}
		// chi show thong tin name va apk cua service hien tai
		case 2: {
			System.out.println("Get APK list");
			int id = -1;
			String name = "";
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			request.setAttribute("apk", main.getApkPath(id));
			request.setAttribute("name", name);
			this.showJSPpage(request, response, "/system/detailApk.jsp");
			break;
		}
		// lay thong tin cua 1 service
		case 3: {
			System.out.println("Get Service Information");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
//			int langId = 1;
//			if (request.getParameter("lang") != null) {
//				langId = Integer.parseInt(request.getParameter("lang").toString().trim());
//			}
			eService eItem = main.getServiceInfo(id, LangID);
			request.setAttribute("eItem", eItem);
			this.showJSPpage(request, response, "/system/detailServiceSystem.jsp");
			break;
		}
		// update status cua 1 service
		case 4: {
			System.out.println("Update service status");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			changeVisbleService(id);
			break;
		}
		// lay service cho order 24.1
		case 5: {
			System.out.println("Lay tat ca service trong ServiceSystem cho ORDER");
			int index = 0;
			int page = 6;
//			int langId = 1;
//			if (request.getParameter("lang") != null) {
//				langId = Integer.parseInt(request.getParameter("lang").toString().trim());
//			}
			response.setContentType("text/xml");
			Vector<eService> st = getServicesForOrder(index, page, LangID);
			request.setAttribute("eItem", st);
			this.showJSPpage(request, response, "/system/detailOrder.jsp");
			break;
		}
		/*
		 * lay ra cac dich vu may ao dang chay, tong cong co 11 12 may ao dang chay dbi, dbora, eas, ehotelmanservice, ehotelservice,
		 * ftpclient, ftpgateway, ievsserver, initVLC, livetv, lsnrora, mediafileparse monitorcenter, pmscom, rmc, srvmonitor
		 */
		case 6: {
			System.out.println("Get service and check status of service!");
			response.setContentType("text/xml");
			String st = getServicesNameXml();
			// System.out.println("after get service name to xml!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			out.print(st);
			break;
		}
		// update status 30.1
		case 7: {
			String name = "";
			String type = "";
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("type") != null) {
				type = request.getParameter("type").toString();
			}
			System.out.println("Thay doi trang thai status cua module: " + name + " chuyen sang " + type);
			response.setContentType("text/xml");
			changeStatusModule(name, type);
			break;
		}
		// xem log cua module 31.1
		case 8: {
			String name = "";
			String port = "";
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("port") != null) {
				port = request.getParameter("port").toString();
			}
			System.out.println("Xem log cua " + name);
			response.setContentType("text/xml");
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/eHotelAdmin";
			System.out.println("url=" + url);
			System.out.println("port=" + port);
			String result = trackLog(port, url);
			out.print(result);
			break;
		}
		case 9: {
			System.out.println("Get info memory usage");
			String memory = getInfoMemory();
			String ram = getInfoRam();
			String st = memory + ram;
			out.print(st);
			break;
		}
		default: {
			break;
		}
		}
	}

	// phuong thuc POST
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		response.setContentType("text/html");
		int cmd = -1;
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
			}
		}
		switch (cmd) {
		// update apk
		case 2: {
			System.out.println("Update service apk");
			int serviceId = -1;
			String apkvalue = "";
			if (request.getParameter("id") != null) {
				serviceId = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("apkvalue") != null) {
				apkvalue = request.getParameter("apkvalue").toString();
			}
			updateApk(serviceId, apkvalue);
			break;
		}
		// update service
		case 3: {
			System.out.println("Update service system");
			String name = "";
			String image = "";
//			int lang = 1;
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
//			if (request.getParameter("lang") != null) {
//				lang = Integer.parseInt(request.getParameter("lang").toString().trim());
//			}
			boolean b = main.updateServiceMain(id, name, config._main + "/" + image, LangID);
			if (b) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._main + "/" + image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		// update order submit 24.1
		case 4: {
			System.out.println("Dang set order cho service");
			String id = "";
			String order = "";
			if (request.getParameter("id") != null) {
				id = request.getParameter("id").toString();
			}
			if (request.getParameter("order") != null) {
				order = request.getParameter("order").toString();
			}
			System.out.println("Thu tu order la id=" + id + " order=" + order);
			orderByService(id, order);
			break;
		}
		default: {
			break;
		}
		}
	}

	// 2.update apk
	private boolean updateApk(int serviceId, String apkvalue) {
		return main.setValueApkcode(serviceId, apkvalue);
	}

	// 3.public boolean changeVisbleService(int serviceId)
	private boolean changeVisbleService(int serviceId) {
		return main.changeVisbleService(serviceId);
	}

	// 4 update order public boolean orderByService(String str_service_id, String str_order)
	private boolean orderByService(String str_service_id, String str_order) {
		return main.orderByService(str_service_id, str_order);
	}

	// 5. Ham lay gia tri cua toan bo service
	private String getServices(int index, int page, int langId) {
		Vector<eService> info = main.getAllServices(langId);
		String mData = "";
		// so luong cac service duoc thong ke
		int count = info.size();
		// chuan bi file xml
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n";
		mData += "<xml  count=\"" + count + "\">";

		// ap dung cho philao: bo HK 6, Game 10, Wakeup 14 va Internet 9: 13.03.13

		for (int i = 0; i < info.size(); i++) {
			eService item = info.get(i);
			// ap dung cho philao: bo HK 6, Game 10, Wakeup 14 va Internet 9: 13.03.13
			if (checkFlag(item.getServiceId())) {
				mData += "<Item>";
				mData += "<name>";
				mData += "<![CDATA[" + item.getServiceName() + "]]>";
				mData += "</name>";
				mData += "<id>\n";
				mData += item.getServiceId();
				mData += "</id>";
				mData += "<action>";
				mData += "<![CDATA[" + item.getAction() + "]]>";
				mData += "</action>";
				mData += "<status>";
				mData += "<![CDATA[" + item.getInvisble() + "]]>";
				mData += "</status>";
				mData += "<image>";
				mData += "<![CDATA[" + item.getUrlImage() + "]]>";
				mData += "</image>";
				mData += "</Item>";
			}
		}
		mData += "</xml>";
		return mData;
	}

	// neu id giong trong list thi FALSE
	public boolean checkFlag(int id) {
		boolean flag = true;

		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		String list = prop.getProperty("ehotel.servicesytem.remove");
		String[] listStr = list.split(",");
		int[] listInt = new int[listStr.length];
		for (int i = 0; i < listStr.length; i++) {
			listInt[i] = Integer.parseInt(listStr[i]);
		}

		if (listInt.length == 0) {
			return true;
		} else {
			for (int j = 0; j < listInt.length; j++) {
				if (id != listInt[j]) {
					flag = true;
				} else {
					return false;
				}
			}
		}

		return flag;
	}

	private Vector<eService> getServicesForOrder(int index, int page, int langId) {
		Vector<eService> info = main.getAllServices(langId);
		return info;
	}

	private ArrayList<HashMap<String, String>> getDaemon() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		if (isWindows()) {
		}
		if (isUnix()) {
			String lsString = null;
			String[] listCmd = new String[] { "/bin/bash", "-c", "ls /etc/init.d/eod* /etc/init.d/httpd /etc/init.d/vsftpd -l" };
			Runtime run = Runtime.getRuntime();
			Process runtimeProcess = null;
			try {
				Properties prop = new Properties();
				try {
					prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				runtimeProcess = run.exec(listCmd);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
				while ((lsString = bufferedReader.readLine()) != null) {
					System.out.println(lsString);
					String name = lsString.substring(lsString.lastIndexOf(" ") + 1);
					name = name.substring(name.lastIndexOf("/") + 1);
					if (checkServiceValid(name) == 1) {
						if (!(name.equalsIgnoreCase("eod_wer")) && !(name.equalsIgnoreCase("eod_syndatabase"))
								&& !(name.equalsIgnoreCase("eod_synlogic"))) {

							HashMap<String, String> mp = new HashMap<String, String>();
							mp.put("name", name);
							mp.put("view", prop.getProperty(name + "_name"));

							mp.put("portlog", prop.getProperty(name + ".log"));
							mp.put("port", prop.getProperty(name));

							String[] executeCmd = new String[] { "/bin/bash", "-c", "lsof -i -n -P | grep " + prop.getProperty(name) };
							Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd);
							BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runtimeProcess1.getInputStream()));
							if (bufferedReader1.readLine() != null)
								mp.put("status", "0");
							else
								mp.put("status", "1");
							if (name.equalsIgnoreCase("httpd")) {
								String[] executeCmd1 = new String[] { "/bin/bash", "-c", "lsof -i -n -P | grep " + prop.getProperty(name) };
								Process runtimeProcess11 = Runtime.getRuntime().exec(executeCmd1);
								BufferedReader bufferedReader11 = new BufferedReader(new InputStreamReader(
										runtimeProcess11.getInputStream()));
								String temp = "";
								while (bufferedReader11.readLine() != null) {
									temp += bufferedReader11.readLine();
								}
								boolean flag = temp.contains("httpd");
								if (flag)
									mp.put("status", "0");
								else
									mp.put("status", "1");
							}
							result.add(mp);
						} else if (name.equalsIgnoreCase("eod_wer")) { // get status for weather
							HashMap<String, String> mp = new HashMap<String, String>();
							mp.put("name", name);
							mp.put("view", prop.getProperty(name + "_name"));
							mp.put("portlog", prop.getProperty(name + ".log"));

							String[] executeCmd = new String[] { "/bin/bash", "-c", "ps -aux | grep WeatherSynApp.jar" };
							Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd);
							BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runtimeProcess1.getInputStream()));
							String temp = bufferedReader1.readLine();
							boolean flag = temp.contains("app");
							if (flag) {
								mp.put("status", "0");
							} else {
								mp.put("status", "1");
							}
							result.add(mp);
						} else if (name.equalsIgnoreCase("eod_syndatabase")) {
							HashMap<String, String> mp = new HashMap<String, String>();
							mp.put("name", name);
							mp.put("view", prop.getProperty(name + "_name"));
							// add port tu file config 30.1
							mp.put("portlog", prop.getProperty(name + ".log"));

							String[] executeCmd = new String[] { "/bin/bash", "-c", "ps -aux | grep syndatabase.jar" };
							Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd);
							BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runtimeProcess1.getInputStream()));
							String temp = bufferedReader1.readLine();
							temp += bufferedReader1.readLine();
							System.out.println(temp);
							boolean flag = temp.contains("app");
							if (flag) {
								mp.put("status", "0");
							} else {
								mp.put("status", "1");
							}
							result.add(mp);
						} else if (name.equalsIgnoreCase("eod_synlogic")) {
							HashMap<String, String> mp = new HashMap<String, String>();
							mp.put("name", name);
							mp.put("view", prop.getProperty(name + "_name"));
							// add port tu file config 30.1
							mp.put("portlog", prop.getProperty(name + ".log"));

							String[] executeCmd = new String[] { "/bin/bash", "-c", "ps -aux | grep SyncData.jar" };
							Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd);
							BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runtimeProcess1.getInputStream()));
							String temp = bufferedReader1.readLine();
							temp += bufferedReader1.readLine();
							boolean flag = temp.contains("app");
							if (flag) {
								mp.put("status", "0");
							} else {
								mp.put("status", "1");
							}
							result.add(mp);
						}
					}
				}
				run.freeMemory();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// function thay doi trang thai module 30.1
	private String changeStatusModule(String name, String type) {
		if (isWindows()) {
			// do nothing, neu la windows thi ko can lam gi vi khong co ham
		}
		if (isUnix()) {
			System.out.println("service " + name + " " + type);
			String[] listCmd = new String[] { "/bin/bash", "-c", "service " + name + " " + type };

			// String listCmd = "service " + name + " " + type;
			Runtime run = Runtime.getRuntime();
			try {
				run.exec(listCmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (name.equalsIgnoreCase("eod_dbi_02") && type.equalsIgnoreCase("start")) {
				String[] Cmd = new String[] { "/bin/bash", "-c", "service eod_ehotelmanservice start" };

				// String listCmd = "service " + name + " " + type;
				// Runtime run = Runtime.getRuntime();
				try {
					run.exec(Cmd);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (name.equalsIgnoreCase("eod_dbi_01") && type.equalsIgnoreCase("start")) {
				String[] Cmd = new String[] { "/bin/bash", "-c", "service eod_ehotelservice start" };

				// String listCmd = "service " + name + " " + type;
				// Runtime run = Runtime.getRuntime();
				try {
					run.exec(Cmd);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("update status service success!");
		return "ok";
	}

	// ham xu ly viec lay log 31.1
	private String trackLog(String port, String url) {
		String result = "";
		if (isWindows()) {
			// do nothing, neu la windows thi ko can lam gi vi khong co ham
		}
		if (isUnix()) {
			// tao ra file LogAppletjnlp.jnlp
			boolean flag = writeToFileJnlp(port);
			while (flag) {
				result = url + "/LogAppletjnlp.jnlp";
				break;
			}
		}
		return result;
	}

	public boolean writeToFileJnlp(String port) {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		String realPath = getServletContext().getRealPath("/");
		String filePath = realPath + File.separator + "LogAppletjnlp.jnlp";
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
			file = new File(filePath);
		}
		try {
			// Create file jnlp 5.2
			FileWriter fstream = new FileWriter(filePath);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			out.write("<jnlp spec=\"1.0+\" codebase=\"http://" + prop.getProperty("ehotel.module.host")
					+ ":8888/eHotelAdmin\" href=\"LogAppletjnlp.jnlp\">\n");
			out.write("<information>\n");
			out.write("<title>Log Information</title>\n");
			out.write("<vendor>Log Information</vendor>\n");
			out.write("</information>\n");
			out.write("<resources>\n");
			out.write("<j2se version=\"1.6+\" href=\"http://java.sun.com/products/autodl/j2se\"/>\n");
			out.write("<jar href=\"LogApplication.jar\" main=\"true\"/>\n");
			out.write("</resources>\n");
			out.write("<application-desc name=\"Log Information\" main-class=\"LogApplication\" width=\"300\" height=\"300\">\n");
			out.write("<argument>" + prop.getProperty("ehotel.module.host") + "</argument>\n");
			out.write("<argument>" + port + "</argument>\n");
			out.write("</application-desc>\n");
			out.write("<update check=\"background\"/>\n");
			out.write("</jnlp>");
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return true;
	}

	private String getServicesNameXml() {
		System.out.println("Get service name to xml!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		ArrayList<HashMap<String, String>> info = getDaemon();
		String mData = "";
		// so luong cac service duoc thong ke
		int count = info.size();
		// chuan bi file xml
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n";
		mData += "<xml count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			HashMap<String, String> item = info.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.get("name") + "]]>";
			mData += "</name>";
			mData += "<view>";
			mData += "<![CDATA[" + item.get("view") + "]]>";
			mData += "</view>";
			mData += "<id>\n";
			mData += i;
			mData += "</id>";
			mData += "<port>";
			mData += "<![CDATA[" + item.get("port") + "]]>";
			mData += "</port>";
			mData += "<portlog>";
			mData += "<![CDATA[" + item.get("portlog") + "]]>";
			mData += "</portlog>";
			mData += "<status>";
			mData += "<![CDATA[" + item.get("status") + "]]>";
			mData += "</status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	// check os 21.9
	public boolean isWindows() {
		return (os.indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (os.indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}

	public boolean isSolaris() {
		return (os.indexOf("sunos") >= 0);
	}

	public int checkServiceValid(String text) {
		if (text == "eod_lsnrora")
			return 0;
		if (text == "eod_ehotelmanservice")
			return 0;
		if (text == "eod_dbora")
			return 0;
		return 1;
	}

	public String getMemoryUsage() {
		String line = null;
		String result = "";
		String[] listCmd = new String[] { "/bin/bash", "-c", "df -m /data" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				result = line;
			}
			run.freeMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 2286767 1853387 317213 86% /data
	public String getInfoMemory() {
		String info = getMemoryUsage();
		// String info = "                        2286767 1853387    317213  86% /data";
		info = replaceSpace(info);
		String[] arr = info.split(" ");
		long used = Long.parseLong(arr[1]);
		long free = Long.parseLong(arr[2]);
		long total = used + free;
		int usedper = Integer.parseInt(arr[3]);
		int freeper = 100 - usedper;
		String data = "";
		data += "<totalhdd>" + total + "</totalhdd>";
		data += "<usedhdd>" + used + "</usedhdd>";
		data += "<freehdd>" + free + "</freehdd>";
		data += "<usedperhdd>" + usedper + "</usedperhdd>";
		data += "<freeperhdd>" + freeper + "</freeperhdd>";
		return data;
	}

	public String getRamUsage() {
		String result = "";
		String line = null;
		String[] listCmd = new String[] { "/bin/bash", "-c", "free -m" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
			int i = 1;
			while ((line = bufferedReader.readLine()) != null) {
				if (i == 2)
					result = line;
				i++;
			}
			run.freeMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Mem: 3878 3831 46 0 251 1729
	public String getInfoRam() {
		String info = getRamUsage();
		// String info = "Mem:          3878       3831         46          0        251       1729";
		info = replaceSpace(info);
		String[] arr = info.split(" ");
		long total = Long.parseLong(arr[1]);
		long used = Long.parseLong(arr[2]);
		long free = Long.parseLong(arr[3]);
		int usedper = Math.round((used * 100) / total);
		int freeper = 100 - usedper;
		String data = "";
		data += "<totalram>" + total + "</totalram>";
		data += "<usedram>" + used + "</usedram>";
		data += "<freeram>" + free + "</freeram>";
		data += "<usedperram>" + usedper + "</usedperram>";
		data += "<freeperram>" + freeper + "</freeperram>";
		return data;
	}

	public String replaceSpace(String info) {
		while (info.indexOf("  ") >= 0) {
			info = info.replaceAll("  ", " ");
		}
		info = info.substring(1);
		info = info.replace("%", "");
		return info;
	}
}
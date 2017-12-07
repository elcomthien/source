package ehotel.admin.Vod;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;

public class ConfigFtpServer extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private ConfigService configService = new ConfigService();

	public ConfigFtpServer() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
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
			if (request.getParameter(Def.MenuId) != null) {
				menuid = Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);
			String host = "";
			String port = "";
			String user = "";
			String pass = "";
			String pathFile = getServletContext().getRealPath("") + File.separatorChar + "data" + File.separatorChar + "data.txt";
			String text = configService.ReadFile(pathFile);
			System.out.println("config = = = = = = = = =  = = = = = = = = = = = " + text);
			if (text != "") {
				String[] arr = text.split(",");
				System.out.println(arr[0] + arr[1] + arr[2] + arr[3]);
				host = arr[0];
				port = arr[1];
				user = arr[2];
				pass = arr[3];
			}
			request.setAttribute("host", host);
			request.setAttribute("port", port);
			request.setAttribute("user", user);
			request.setAttribute("pass", pass);
			this.showJSPpage(request, response, "/vodMgn/content/config.jsp");
			break;
		}
		case 1: {
			System.out.println("Config FTP server");
			String host = "";
			int port = 21;
			String user = "";
			String pass = "";
			if (request.getParameter("host") != null)
				host = request.getParameter("host").toString().trim();
			if (request.getParameter("port") != null)
				port = Integer.parseInt(request.getParameter("port").toString().trim());
			if (request.getParameter("user") != null)
				user = request.getParameter("user").toString().trim();
			if (request.getParameter("pass") != null)
				pass = request.getParameter("pass").toString().trim();
			String pathFile = getServletContext().getRealPath("") + File.separatorChar + "data" + File.separatorChar + "data.txt";
//			System.out.println(pathFile);
			String text = host + "," + port + "," + user + "," + pass;
			boolean result = configService.WriteFile(pathFile, text, true);
			if (result)
				out.print("Config FTP Server success!");
			else
				out.print("Config FTP Server unseccess!");
			break;
		}
		default:
			break;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

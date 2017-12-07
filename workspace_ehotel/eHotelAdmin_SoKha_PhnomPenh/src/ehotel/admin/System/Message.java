package ehotel.admin.System;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.util.Def;

public class Message extends ServiceParent {
	private static final long serialVersionUID = 1L;
	PMSServiceDBI pmsServiceDBI = new PMSServiceDBI();

	public Message() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
	}

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
			String mess = pmsServiceDBI.getWelcomeMessage();
			mess = "<p>" + mess + "</p>";
			mess = mess.replaceAll("<br/><br/>", "</p><p>&nbsp;</p><p>");
			mess = mess.replaceAll("<br/>", "</p><p>");
			request.setAttribute("mess", mess);
			request.setAttribute("fileJSP", "../system/Message.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1: { // get message welcome
			String mess = pmsServiceDBI.getWelcomeMessage();
			out.print(mess);
			break;
		}
		default: {
			break;
		}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd = -1;
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		switch (cmd) {
		case 1: {
			System.out.println("Update welcome message");
			String message = "";
			if (request.getParameter("message") != null) {
				message = request.getParameter("message").toString();
			}
			message = message.replaceAll("<strong>", "<b>");
			message = message.replaceAll("</strong>", "</b>");
			message = message.replaceAll("<em>", "<i>");
			message = message.replaceAll("</em>", "</i>");
			message = message.replaceAll(
					"<span style=\"text-decoration: underline;\">", "<u>");
			message = message.replaceAll("</span>", "</u>");
			message = message.replaceAll("<p>", "");
			message = message.replaceAll("</p>", "");
			while (message.indexOf("\n \n") >= 0)
				message = message.replace("\n \n", "\n\n");
			message = message.replaceAll("\n", "<br/>");
			System.out.println(message);
			boolean flag = pmsServiceDBI.UpdateMessage(message);
			out.print(flag);
			break;
		}
		default: {
			break;
		}
		}
	}

}

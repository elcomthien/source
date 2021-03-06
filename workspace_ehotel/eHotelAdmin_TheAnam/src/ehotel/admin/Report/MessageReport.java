package ehotel.admin.Report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.ReportServiceDBI;
import ehotel.admin.util.Def;

public class MessageReport extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private ReportServiceDBI reportServiceDBI = new ReportServiceDBI();

	public MessageReport() {
		super();
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
				e.printStackTrace();
			}
		}
		switch (cmd) {
		case -1:
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
			request.setAttribute("fileJSP", "../report/MessageReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case 1: {

			System.out.println("Get static room");
			String frDate = "";
			String toDate = "";
			if (request.getParameter("from") != null) {
				frDate = (request.getParameter("from").toString());
			}
			if (request.getParameter("to") != null) {
				toDate = (request.getParameter("to").toString());
			}

			response.setContentType("text/xml");
			String st = getMessageReport(frDate, toDate);
			out.print(st);
			break;
		}
		case 2: {
			System.out.println("Get Room Message Report");
			String frDate = "";
			String toDate = "";
			if (request.getParameter("from") != null) {
				frDate = (request.getParameter("from").toString());
			}
			if (request.getParameter("to") != null) {
				toDate = (request.getParameter("to").toString());
			}
			String st = getRoomMessage(frDate, toDate);
			out.print(st);
			break;
		}
		default:
			break;
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public String getMessageReport(String frDate, String toDate) {
		List<MessageReportModel> list = reportServiceDBI.getMessageReport(
				frDate, toDate);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<room>";
			mData += list.get(i).getRoom();
			mData += "</room>";
			mData += "<guest>";
			mData += "<![CDATA[" + list.get(i).getGuest() + "]]>";
			mData += "</guest>";
			mData += "<checkin>";
			mData += "<![CDATA[" + list.get(i).getCheckin() + "]]>";
			mData += "</checkin>";
			mData += "<checkout>";
			mData += "<![CDATA[" + list.get(i).getCheckout() + "]]>";
			mData += "</checkout>";
			mData += "<create>";
			mData += "<![CDATA[" + list.get(i).getCreate() + "]]>";
			mData += "</create>";
			if(list.get(i).getRead().equals(""))
				list.get(i).setRead("Unread");
			mData += "<read>";
			mData += "<![CDATA[" + list.get(i).getRead() + "]]>";
			mData += "</read>";
			mData += "<sender>";
			mData += "<![CDATA[" + list.get(i).getSender() + "]]>";
			mData += "</sender>";
			mData += "<content>";
			mData += "<![CDATA[" + list.get(i).getContent() + "]]>";
			mData += "</content>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
	
	public String getRoomMessage(String from, String to) {
		String data = "";
		List<String> list = reportServiceDBI.getRoomMessage(from, to);
		data += "<option value='all'>All</option>";
		for (int i = 0; i < list.size(); i++) {
			data += "<option value='" + list.get(i) + "'>" + list.get(i) + "</option>";
		}
		return data;
	}

}

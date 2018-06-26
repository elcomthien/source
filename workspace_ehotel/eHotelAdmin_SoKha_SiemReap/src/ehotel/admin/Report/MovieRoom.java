package ehotel.admin.Report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dao.ReportServiceDBI;
import ehotel.admin.util.Def;

public class MovieRoom extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private ReportServiceDBI reportServiceDBI = new ReportServiceDBI();

	public MovieRoom() {
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
			request.setAttribute("fileJSP", "../report/RoomReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1: {
			System.out.println("Get Movie Room Report");
			String frDate = "";
			String toDate = "";
			if (request.getParameter("from") != null) {
				frDate = (request.getParameter("from").toString());
			}
			if (request.getParameter("to") != null) {
				toDate = (request.getParameter("to").toString());
			}
			response.setContentType("text/xml");
			String st = getRoomReport(frDate, toDate);
			out.print(st);
			break;
		}
		case 2: {
			System.out.println("Get Room Report");
			String frDate = "";
			String toDate = "";
			if (request.getParameter("from") != null) {
				frDate = (request.getParameter("from").toString());
			}
			if (request.getParameter("to") != null) {
				toDate = (request.getParameter("to").toString());
			}
			String st = getRoom(frDate, toDate);
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

	public String getRoomReport(String from, String to) {
		List<RoomReportModel> list = reportServiceDBI.getRoomReport(from, to);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<room>";
			mData += list.get(i).getRoom();
			mData += "</room>";
			mData += "<title>";
			mData += "<![CDATA[" + list.get(i).getTitle() + "]]>";
			mData += "</title>";
			mData += "<date>";
			mData += "<![CDATA[" + list.get(i).getDate() + "]]>";
			mData += "</date>";
			mData += "<qty>";
			mData += list.get(i).getQty();
			mData += "</qty>";
			mData += "<currency>";
			mData += "<![CDATA[" + list.get(i).getCurrency() + "]]>";
			mData += "</currency>";
			mData += "<rate>";
			mData += list.get(i).getRate();
			mData += "</rate>";
			mData += "<amount>";
			mData += list.get(i).getRate();
			mData += "</amount>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	public String getRoom(String from, String to) {
		String data = "";
		List<String> list = reportServiceDBI.getRoom(from, to);
		data += "<option value='all'>All</option>";
		for (int i = 0; i < list.size(); i++) {
			data += "<option value='" + list.get(i) + "'>" + list.get(i) + "</option>";
		}
		return data;
	}
}

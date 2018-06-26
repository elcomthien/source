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

public class RoomServiceMenu extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private ReportServiceDBI reportServiceDBI = new ReportServiceDBI();

	public RoomServiceMenu() {
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
			request.setAttribute("fileJSP", "../report/RoomMenuReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1: {
			System.out.println("Get Room Service Menu Report");
			String frDate = "";
			String toDate = "";
			if (request.getParameter("from") != null) {
				frDate = (request.getParameter("from").toString());
			}
			if (request.getParameter("to") != null) {
				toDate = (request.getParameter("to").toString());
			}
			response.setContentType("text/xml");
			String st = getServiceMenuReport(frDate, toDate);
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

	public String getServiceMenuReport(String from, String to) {
		List<ServiceMenuModel> list = reportServiceDBI
				.getServiceMenuReport(from, to);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<id>";
			mData += list.get(i).getId();
			mData += "</id>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getName() + "]]>";
			mData += "</name>";
			mData += "<updatedate>";
			mData += "<![CDATA[" + list.get(i).getDatetime() + "]]>";
			mData += "</updatedate>";
			mData += "<rate>";
			mData += "<![CDATA[" + list.get(i).getRate() + "]]>";
			mData += "</rate>";
			mData += "<currency>";
			mData += "<![CDATA[" + list.get(i).getCurency() + "]]>";
			mData += "</currency>";
			mData += "<updateby>";
			mData += "<![CDATA[" + list.get(i).getUpdateby() + "]]>";
			mData += "</updateby>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
	
	public static void main(String[] args) {
		RoomServiceMenu m = new RoomServiceMenu();
		System.out.println(m.getServiceMenuReport("01/05/2015", "01/01/2016"));
	}

}

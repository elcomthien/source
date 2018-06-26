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

public class RoomServiceOrder extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private ReportServiceDBI reportServiceDBI = new ReportServiceDBI();

	public RoomServiceOrder() {
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
			request.setAttribute("fileJSP", "../report/RoomOrderReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1: {
			System.out.println("Get Room Service Order Report");
			String frDate = "";
			String toDate = "";
			if (request.getParameter("from") != null) {
				frDate = (request.getParameter("from").toString());
			}
			if (request.getParameter("to") != null) {
				toDate = (request.getParameter("to").toString());
			}
			response.setContentType("text/xml");
			String st = getServiceOrderReport(frDate, toDate);
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

	public String getServiceOrderReport(String from, String to) {
		List<RoomServiceOrderModel> list = reportServiceDBI
				.getServiceOrderReport(from, to);
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
			mData += "<orderdate>";
			mData += "<![CDATA[" + list.get(i).getOrderdate() + "]]>";
			mData += "</orderdate>";
			mData += "<quantity>";
			mData += "<![CDATA[" + list.get(i).getQuantity() + "]]>";
			mData += "</quantity>";
			mData += "<currency>";
			mData += "<![CDATA[" + list.get(i).getCurrency() + "]]>";
			mData += "</currency>";
			mData += "<amount>";
			mData += "<![CDATA[" + list.get(i).getAmount() + "]]>";
			mData += "</amount>";
			mData += "<guest>";
			mData += "<![CDATA[" + list.get(i).getGuest() + "]]>";
			mData += "</guest>";
			mData += "<room>";
			mData += "<![CDATA[" + list.get(i).getRoom() + "]]>";
			mData += "</room>";
			mData += "<checkin>";
			mData += "<![CDATA[" + list.get(i).getCheckin() + "]]>";
			mData += "</checkin>";
			mData += "<checkout>";
			mData += "<![CDATA[" + list.get(i).getCheckout() + "]]>";
			mData += "</checkout>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	public static void main(String[] args) {
		RoomServiceOrder m = new RoomServiceOrder();
		System.out.println(m.getServiceOrderReport("01/05/2015", "01/01/2016"));
	}

}

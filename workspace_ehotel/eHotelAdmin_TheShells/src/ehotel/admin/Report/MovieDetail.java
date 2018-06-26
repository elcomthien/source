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

public class MovieDetail extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private ReportServiceDBI reportServiceDBI = new ReportServiceDBI();

	public MovieDetail() {
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
			request.setAttribute("fileJSP", "../report/DetailReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1: {
			System.out.println("Get Movie Details Report");
			String frDate = "";
			String toDate = "";
			if (request.getParameter("from") != null) {
				frDate = (request.getParameter("from").toString());
			}
			if (request.getParameter("to") != null) {
				toDate = (request.getParameter("to").toString());
			}
			response.setContentType("text/xml");
			String st = getDetailReport(frDate, toDate);
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

	public String getDetailReport(String from, String to) {
		List<MovieDetailModel> list = reportServiceDBI
				.getDetailReport(from, to);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<id>";
			mData += list.get(i).getId();
			mData += "</id>";
			mData += "<title>";
			mData += "<![CDATA[" + list.get(i).getTitle() + "]]>";
			mData += "</title>";
			mData += "<upload>";
			mData += "<![CDATA[" + list.get(i).getUpload() + "]]>";
			mData += "</upload>";
			if(list.get(i).getCountsub().equals(""))
				list.get(i).setCountsub("0");
			mData += "<countsub>";
			mData += list.get(i).getCountsub();
			mData += "</countsub>";
			if (list.get(i).getCountsub().equals("0")) {
				mData += "<langsub>";
				mData += "<![CDATA[0]]>";
				mData += "</langsub>";
			} else {
				mData += "<langsub>";
				mData += "<![CDATA[" + list.get(i).getLangsub() + "]]>";
				mData += "</langsub>";
			}
			if(list.get(i).getPrice().equals(""))
				list.get(i).setPrice("0");
			mData += "<price>";
			mData += list.get(i).getPrice();
			mData += "</price>";
			if(list.get(i).getCurrency().equals(""))
				list.get(i).setCurrency("USD");
			mData += "<currency>";
			mData += list.get(i).getCurrency();
			mData += "</currency>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
	
	public static void main(String[] args) {
		MovieDetail m = new MovieDetail();
		System.out.println(m.getDetailReport("01/05/2014", "01/01/2015"));
	}

}

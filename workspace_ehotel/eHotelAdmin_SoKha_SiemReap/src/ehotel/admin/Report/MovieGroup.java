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

public class MovieGroup extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private ReportServiceDBI reportServiceDBI = new ReportServiceDBI();

	public MovieGroup() {
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
			request.setAttribute("fileJSP", "../report/GroupReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1: {
			System.out.println("Get subject movie group");
			response.setContentType("text/xml");
			String st = getSubjectMovieServiceGroup();
			out.print(st);
			break;
		}
		case 2: {
			System.out.println("Get content of movie group");
			String id = "0";
			if(request.getParameter("id") != null)
				id = request.getParameter("id").trim();
			response.setContentType("text/xml");
			String st = getContentOfMovieServiceGroup(id,"");
			out.print(st);
			break;
		}
		case 3: {
			System.out.println("Get movie group for combobox");
			String st = getGroupSelect();
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

	public String getSubjectMovieServiceGroup() {
		List<SubjectServiceModel> list = reportServiceDBI
				.getSubjectMovieService();
		System.out.println("list: " + list);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			String text = getContentOfMovieServiceGroup(list.get(i).getId(),
					list.get(i).getName());
			mData += text;
			mData += "<Item>";
			mData += "<id>";
			mData += list.get(i).getId();
			mData += "</id>";
			mData += "<group>";
			mData += "<![CDATA[Sub-total]]>";
			mData += "</group>";
			mData += "<title>";
			mData += "<![CDATA[" + list.get(i).getCount() + "]]>";
			mData += "</title>";
			mData += "<upload>";
			mData += " ";
			mData += "</upload>";
			mData += "<countsub>";
			mData += " ";
			mData += "</countsub>";
			mData += "<langsub>";
			mData += " ";
			mData += "</langsub>";
			mData += "</Item>";
			total += Integer.parseInt(list.get(i).getCount());
		}
		// Grand Total
		mData += "<Item>";
		mData += "<id>";
		mData += "0";
		mData += "</id>";
		mData += "<group>";
		mData += "<![CDATA[Grand Total]]>";
		mData += "</group>";
		mData += "<title>";
		mData += "<![CDATA[" + total + "]]>";
		mData += "</title>";
		mData += "<upload>";
		mData += " ";
		mData += "</upload>";
		mData += "<countsub>";
		mData += " ";
		mData += "</countsub>";
		mData += "<langsub>";
		mData += " ";
		mData += "</langsub>";
		mData += "</Item>";
		mData += "</xml>";
		// System.out.println("xml movie group: " + mData);
		return mData;
	}

	public String getContentOfMovieServiceGroup(String id, String name) {
		System.out.println("Get content of " + name + " - id: " + id);
		List<MovieGoupModel> list = reportServiceDBI.getMovieGroup(id);
		String mData = "";
		// mData +=
		// "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		// mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<id>";
			mData += id;
			mData += "</id>";
			if (i == 0) {
				mData += "<group>";
				mData += "<![CDATA[" + name + "]]>";
				mData += "</group>";
			} else {
				mData += "<group>";
				mData += " ";
				mData += "</group>";
			}
			mData += "<title>";
			mData += "<![CDATA[" + list.get(i).getTitle() + "]]>";
			mData += "</title>";
			mData += "<upload>";
			mData += "<![CDATA[" + list.get(i).getUpload() + "]]>";
			mData += "</upload>";
			mData += "<countsub>";
			mData += list.get(i).getCountsub();
			mData += "</countsub>";
			mData += "<langsub>";
			mData += "<![CDATA[" + list.get(i).getLangsub() + "]]>";
			mData += "</langsub>";
			mData += "</Item>";
		}
		// mData += "</xml>";
		return mData;
	}

	public String getGroupSelect() {
		String data = "";
		List<SubjectServiceModel> list = reportServiceDBI
				.getSubjectMovieService();
		data += "<option value='all'>All</option>";
		for (int i = 0; i < list.size(); i++) {
			data += "<option value='"+list.get(i).getId()+"'>"+list.get(i).getName()+"</option>";
		}
		return data;
	}

	public static void main(String[] args) {
		MovieGroup m = new MovieGroup();
		System.out.println(m.getSubjectMovieServiceGroup());
	}

}

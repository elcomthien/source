package ehotel.admin.Report;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.DBI.DBI;
import com.elcom.DBI.SubProParam;

import ehotel.abs.report.OrderReport;
import ehotel.abs.report.VideoReport;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;
import ehotel.domain.report.DataReport;

public class LivetvReport extends ServiceParent {
	public LivetvReport() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	/**
	 * The doGet method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to get.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd=-1;
		if(request.getParameter("CMD")!=null)
		{
			try{
				cmd=Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
			}		
		}
		switch (cmd) {
		case -1:
		{
			int subId=-1;
			int menuid=-1;
			if(request.getParameter(Def.MenuId)!=null)
			{
				menuid=Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			if(request.getParameter(Def.SubId)!=null)
			{
				subId=Integer.parseInt(request.getParameter(Def.SubId).toString());
			}			
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);		
			request.setAttribute("fileJSP", "../report/LivetvReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");			
			break;
		}
		//dang dung o day 24.1
		case 1://get month
		{
			System.out.println("LIVE month");
			int year=-1;
			int index=0;
			int page=6;
			if(request.getParameter("year")!=null)
			{
				year=Integer.parseInt(request.getParameter("year").toString());
			}
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}
			
			response.setContentType("text/xml");
			System.out.println("Report LIVE getlistMonth:" + year);
			String st= getlistMonth(year);
			out.print(st);
			break;		
		}
		case 2://get genres - chuyen sang get week
		{
			System.out.println("LIVE week");
			int index=0;
			int page=6;
			int month=-1;
			if(request.getParameter("month")!=null)
			{
				month=Integer.parseInt(request.getParameter("month").toString());
			}
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}			
			response.setContentType("text/xml");
			String st= getGeners(month);	
			out.print(st);
			break;		
		}
		case 3://get list film
		{
			System.out.println("Livetv Get static films");
			String frDate="";
			String toDate="";
			int index=0;
			int page=6;
			if(request.getParameter("from")!=null)
			{
				frDate=(request.getParameter("from").toString());
			}
			if(request.getParameter("to")!=null)
			{
				toDate=(request.getParameter("to").toString());
			}
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}			
			response.setContentType("text/xml");
			String st= getstaticfilm(frDate, toDate);	
			out.print(st);
			break;		
		}
		default:
			break;
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	private String getlistMonth(int year )
	{
		String frDate = Integer.toString(year);
		String toDate = "month";
		VideoReport rmi=new VideoReport();
		String mData="";
		Vector<DataReport> v_rs= rmi.getUsedFrequency_name(frDate, toDate, LangID, 1, 1);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		System.out.println("VOD size=" + v_rs.size());
		for(int i=0;i<v_rs.size();i++)
		{		
			DataReport item=v_rs.get(i);
			if (item.getAmount().equalsIgnoreCase("live")) {
				mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+item.getName()+"]]>";
				mData+="</name>";
				mData+="<id>\n";
				mData+=(i+1);
				mData+="</id>";
				mData+="<Amount>\n";
				mData+=item.getQuanlity();
				mData+="</Amount>";
				mData+="</Item>";
			}
		}	
		mData+="</xml>";
		return mData;
	}
	private String getGeners(int month)
	{
		VideoReport rmi=new VideoReport();
		String mData="";
		String frDate = Integer.toString(month);
		if (month < 10) frDate = "0" + frDate;
		System.out.println("LIVE week:" + frDate);
		String toDate = "week";
		Vector<DataReport> v_rs= rmi.getUsedFrequency_name(frDate, toDate, LangID, 1, 1);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		System.out.println("LIVE week size=" + v_rs.size());
		for(int i=0;i<v_rs.size();i++)
		{
			DataReport item=v_rs.get(i);
			if (item.getAmount().equalsIgnoreCase("live")) {
				mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+item.getName()+"]]>";
				mData+="</name>";
				mData+="<id>\n";
				mData+=(i+1);
				mData+="</id>";
				mData+="<Amount>\n";
				mData+=item.getQuanlity();
				mData+="</Amount>";
				mData+="</Item>";
			}
		}	
		mData+="</xml>";
		return mData;
	}
	private String getstaticfilm(String frDate,String toDate )
	{
		VideoReport rmi=new VideoReport();
		String mData="";
		System.out.println("LIVE thong ke:" + frDate);
		Vector<DataReport> v_rs= rmi.getUsedFrequency_name(frDate, toDate, LangID, -1, -1);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<v_rs.size();i++)
		{
			DataReport item=v_rs.get(i);
			if (item.getAmount().equalsIgnoreCase("live")) {
				mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+item.getName()+"]]>";
				mData+="</name>";
				mData+="<id>\n";
				mData+=(i+1);
				mData+="</id>";
				mData+="<price>\n";
				mData+=item.getPrice();
				mData+="</price>";
				mData+="<quantity>\n";
				mData+=item.getQuanlity();
				mData+="</quantity>";
				mData+="<Amount>\n";
				mData+=item.getAmount();
				mData+="</Amount>";
				mData+="</Item>";
			}
		}	
		mData+="</xml>";
		return mData;
	}
	/**
	 * Initialization of the servlet. <br>
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	}
}
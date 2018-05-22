package ehotel.admin.Report;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.DBI.DBI;
import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;

import ehotel.abs.report.VideoReport;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.ReportServiceDBI;
import ehotel.admin.util.Def;
import ehotel.domain.report.DataReport;
import ehotel.utils.DataUtils;
import ehotel.utils.Utils;

public class VodReport extends ServiceParent {
	/**
	 * Constructor of the object.
	 */
	public static final String MONTHLY_CHECK = "{call ereport.getUsedFrequency_monthly(?,?)}";
	private LinkedList pool = new LinkedList();
	private Object mutex = new Object();
	private int max_pool_size = 10;
	public static Vector outParam = new Vector();
	private ReportServiceDBI reportServiceDBI = new ReportServiceDBI();

	public VodReport() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	/**
	 * The doGet method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
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
			if (request.getParameter(Def.MenuId) != null) {
				menuid = Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);
			request.setAttribute("fileJSP", "../report/VideoReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		// dang dung o day 24.1
		case 1:// get month
		{
			System.out.println("VOD month");
			int year = -1;
			int index = 0;
			int page = 6;
			if (request.getParameter("year") != null) {
				year = Integer.parseInt(request.getParameter("year").toString());
			}
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}

			response.setContentType("text/xml");
			System.out.println("Report VOD getlistMonth:" + year);
			String st = getlistMonth(year);
			out.print(st);
			break;
		}
		case 2:// get genres - chuyen sang get week
		{
			System.out.println("VOD daily");
			// String frDate="";
			// String toDate="";
			int index = 0;
			int page = 6;
			int month = -1;
			/*
			 * if(request.getParameter("from")!=null) {
			 * frDate=(request.getParameter("from").toString()); }
			 * if(request.getParameter("to")!=null) {
			 * toDate=(request.getParameter("to").toString()); }
			 */
			if (request.getParameter("month") != null) {
				month = Integer.parseInt(request.getParameter("month").toString());
			}
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			response.setContentType("text/xml");
			String st = getDailyReport(month);
			out.print(st);
			break;
		}
		case 3:// get list film
		{
			System.out.println("VOD Get static films");
			String frDate = "";
			String toDate = "";
			int index = 0;
			int page = 6;
			if (request.getParameter("from") != null) {
				frDate = (request.getParameter("from").toString());
			}
			if (request.getParameter("to") != null) {
				toDate = (request.getParameter("to").toString());
			}
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			response.setContentType("text/xml");
			String st = getstaticfilm(frDate);
			out.print(st);
			break;
		}
		default:
			break;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	// private String getlistMonth(int year) {
	// String frDate = Integer.toString(year);
	// String toDate = "month";
	// VideoReport rmi = new VideoReport();
	// String mData = "";
	// Vector<DataReport> v_rs = rmi.getUsedFrequency_name(frDate, toDate, LangID, 1, 1);
	// mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
	// mData += "<xml>";
	// System.out.println("VOD month size=" + v_rs.size());
	// for (int i = 0; i < v_rs.size(); i++) {
	// DataReport item = v_rs.get(i);
	// if (item.getAmount().equalsIgnoreCase("vod")) {
	// mData += "<Item>";
	// mData += "<name>";
	// mData += "<![CDATA[" + item.getName() + "]]>";
	// mData += "</name>";
	// mData += "<id>\n";
	// mData += (i + 1);
	// mData += "</id>";
	// mData += "<Amount>\n";
	// mData += item.getQuanlity();
	// mData += "</Amount>";
	// mData += "</Item>";
	// }
	// }
	// mData += "</xml>";
	// return mData;
	// }

	private String getlistMonth(int year) {
		String frDate = Integer.toString(year);
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		list = reportServiceDBI.getMonthlyStaticReport(frDate);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		System.out.println("VOD month size=" + list.size());
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getDate() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += (i + 1);
			mData += "</id>";
			mData += "<Amount>\n";
			mData += list.get(i).getQuantity();
			mData += "</Amount>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	// thay bang week
//	private String getGeners(int month) {
//		VideoReport rmi = new VideoReport();
//		String mData = "";
//		String frDate = Integer.toString(month);
//		if (month < 10)
//			frDate = "0" + frDate;
//		System.out.println("VOD week:" + frDate);
//		String toDate = "week";
//		// Vector<DataReport> v_rs= rmi.getUsedFrequency_gener(frDate, toDate, LangID);
//		Vector<DataReport> v_rs = rmi.getUsedFrequency_name(frDate, toDate, LangID, 1, 1);
//		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
//		mData += "<xml>";
//		System.out.println("VOD week size=" + v_rs.size());
//		for (int i = 0; i < v_rs.size(); i++) {
//			DataReport item = v_rs.get(i);
//			if (item.getAmount().equalsIgnoreCase("vod")) {
//				mData += "<Item>";
//				mData += "<name>";
//				mData += "<![CDATA[" + item.getName() + "]]>";
//				mData += "</name>";
//				mData += "<id>\n";
//				mData += (i + 1);
//				mData += "</id>";
//				mData += "<Amount>\n";
//				mData += item.getQuanlity();
//				mData += "</Amount>";
//				mData += "</Item>";
//			}
//		}
//		mData += "</xml>";
//		return mData;
//	}
	
	private String getDailyReport(int month) {
		String mData = "";
		String frDate = Integer.toString(month);
		if (month < 10)
			frDate = "0" + frDate;
		System.out.println("VOD week:" + frDate);
		// Vector<DataReport> v_rs= rmi.getUsedFrequency_gener(frDate, toDate, LangID);
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		list = reportServiceDBI.getDailyStaticReport(frDate);
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		System.out.println("VOD week size=" + list.size());
		for (int i = 0; i < list.size(); i++) {
				mData += "<Item>";
				mData += "<name>";
				mData += "<![CDATA[" + list.get(i).getDate() + "]]>";
				mData += "</name>";
				mData += "<id>\n";
				mData += (i + 1);
				mData += "</id>";
				mData += "<Amount>\n";
				mData += list.get(i).getQuantity();
				mData += "</Amount>";
				mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	// private String getstaticfilm(String frDate,String toDate )
	// {
	// VideoReport rmi=new VideoReport();
	// String mData="";
	// System.out.println("VOD thong ke:" + frDate);
	// Vector<DataReport> v_rs= rmi.getUsedFrequency_name(frDate, toDate, LangID, -1, -1);
	// mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
	// mData+="<xml>";
	// for(int i=0;i<v_rs.size();i++)
	// {
	// DataReport item=v_rs.get(i);
	// if (item.getAmount().equalsIgnoreCase("vod")) {
	// mData+="<Item>";
	// mData+="<name>";
	// mData+="<![CDATA["+item.getName()+"]]>";
	// mData+="</name>";
	// mData+="<id>\n";
	// mData+=(i+1);
	// mData+="</id>";
	// mData+="<price>\n";
	// mData+=item.getPrice();
	// mData+="</price>";
	// mData+="<quantity>\n";
	// mData+=item.getQuanlity();
	// mData+="</quantity>";
	// mData+="<Amount>\n";
	// mData+=item.getAmount();
	// mData+="</Amount>";
	// mData+="</Item>";
	// }
	// }
	// mData+="</xml>";
	// return mData;
	// }

	private String getstaticfilm(String month) {
		System.out.println("VOD report:" + month);
		List<VodReportModel> list = new ArrayList<VodReportModel>();
		list = reportServiceDBI.getStaticFilmReport(month);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += (i + 1);
			mData += "</id>";
			mData += "<price>\n";
			mData += list.get(i).getPrice();
			mData += "</price>";
			mData += "<quantity>\n";
			mData += list.get(i).getQuantity();
			mData += "</quantity>";
			mData += "<unit>\n";
			mData += list.get(i).getUnit();
			mData += "</unit>";
			mData += "</Item>";

		}
		mData += "</xml>";
		return mData;
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
	}

	// bo sung ham ket noi db
	public void putDBI(DBI dbi) {
		synchronized (mutex) {
			if (dbi != null && pool.size() < max_pool_size - 1) {
				pool.addFirst(dbi);
			}
		}
	}

	public void clearPool() {
		synchronized (mutex) {
			pool.clear();
		}
	}

	private DBI getNewDBI() throws RemoteException, ClassNotFoundException {
		DBI dbi = null;
		int attemptNo = 1;
		while (attemptNo < 3) {
			try {
				dbi = (DBI) Naming.lookup("rmi://" + getHost() + ":" + getPort() + "/" + getDbiservicename());
			} catch (Exception ex) {
				ex.printStackTrace();
				String msg = "Connect to the DBI failed in times: " + attemptNo;
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex1) {
				}
			}
			attemptNo++;
		}
		return dbi;
	}

	// khoi tao bien de luu tru monthlyReport cho philao 18.09.2013
	public Vector<DataReport> monthlyreportDbi(int year) {
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = null;
		in = new SubProParam(new BigDecimal(year), SubProParam.IN);
		params.add(in); // 0
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(subOut);// 1
		Vector<DataReport> vImages = new Vector<DataReport>();
		try {
			params = executeSubPro(MONTHLY_CHECK, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
		}
		vImages = LoadMonth(outParam);// DataUtils.LoadReport(outParam);
		return vImages;
	}

	public Vector LoadMonth(Vector tmp) {
		System.out.println("loadMonth");
		Vector ret = new Vector();
		DataReport rep = null;
		if (tmp != null && tmp.size() > 0) {
			for (int i = 0; i < tmp.size(); i += 3) {
				rep = new DataReport();
				rep.setName((String) tmp.get(i));
				rep.setQuanlity(Utils.parseInt((String) tmp.get(i + 1)));
				rep.setAmount((String) tmp.get(i + 2));
				ret.add(rep);
			}
		}
		return ret;
	}

	public Vector executeSubPro(String sqlSubPro, Vector params) throws ClassNotFoundException {
		DBI dbi = null;
		try {
			// tao moi 1 dbi 8.1
			dbi = getNewDBI();
			System.out.println("VOD executeSubPro");
			return (dbi.executeSubPro(sqlSubPro, UnicodeConverter.changeFontForDBI(params)));
		} catch (SQLException ex) {
		} catch (RemoteException ex) {
			System.out.println("VOD executeSubPro ERROR");
			dbi = null;
			clearPool();
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex1) {
			}
			return executeSubPro(sqlSubPro, params);
		} finally {
			putDBI(dbi);
			return null;
		}
	}

	public String getHost() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.host");
	}

	public String getPort() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.port");
	}

	public String getDbiservicename() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.dbiservicename");
	}

}
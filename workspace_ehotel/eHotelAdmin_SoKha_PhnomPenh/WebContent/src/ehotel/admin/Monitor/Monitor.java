package ehotel.admin.Monitor;
import ehotel.admin.Service.ServiceParent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.menu.Menu;
import ehotel.domain.vod.Subject;
import ehotel.impl.AMDSvcLiveTVImp;
import ehotel.inter.AMDLiveTV;
import ehotel.inter.AMDMenu;
import ehotel.inter.AMDVod;
import ehotel.render.DBIGateway;
import ehotel.render.VOD;

import java.util.Vector;

public class Monitor extends ServiceParent {
	/**
	 * Constructor of the object.
	 */
	LibraySSH openSSH = new LibraySSH( "192.168.0.141", "root", "123456" );
	private String startService ="/etc/init.d/eod_syn_video start";
	private String stopService ="/etc/init.d/eod_syn_video stop";
	private String statusService="";
	
	public Monitor() 
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
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
			}catch (Exception e) {
				// TODO: handle exception
			}		
		}
		switch (cmd) {
		case -1:
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
			request.setAttribute("fileJSP", "../monitor/monitor.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case  1:
			response.setContentType("text/xml");
			String st1= "";
			out.print(st1);
			break;
		case 2:
			//start/stop service video
			String str="";
			int check=-1;
			if(request.getParameter("str")!=null)
			{
				check=Integer.parseInt(request.getParameter("str").toString());
			}
			if(check==0){
				str =startService;
			}else{
				str=stopService;
			}
			List<ehotelMonitor> list =new ArrayList<ehotelMonitor>();
			if( openSSH.connect() ) 
            {
            		list= openSSH.executeCommand(str);
	                openSSH.logout();
            }
			String chuoi="";
			chuoi =list.get(0).getFullname();
			out.print(chuoi);
			break;
		case 3:
			//view log
			String date="";
			int id =-1;
			if(request.getParameter("date")!=null)
			{
				date=request.getParameter("date").toString();
			}
			if(request.getParameter("CurrId")!=null)
			{
				id=Integer.parseInt(request.getParameter("CurrId").toString());
			}
			String viewLog ="";
			if(id==0){
				//show ngay khac khong phai ngay hien tai
				//viewLog+="."+date;
				viewLog ="cat /home/app/SynVideo/Log/daily.log.2012-03-14";
				
			}else
			{
				 viewLog ="cat /home/app/SynVideo/Log/daily.log";
			}
			List<ehotelMonitor> listLog =new ArrayList<ehotelMonitor>();
			if( openSSH.connect() ) 
            {
				listLog= openSSH.executeCommandLog(viewLog);
	            openSSH.logout();
            }
			viewLog="";
			response.setContentType("text/xml");
			String st=getLog(listLog);		
			out.print(st);
			System.out.println(st);
			break;
		default:
			break;
		}
	}	
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.doPost(request, response);
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		int cmd=-1;
		if(request.getParameter("CMD")!=null)
		{
			try{
				cmd=Integer.parseInt(request.getParameter("CMD").toString());
			}catch (Exception e) {
				// TODO: handle exception
			}		
		}
		switch (cmd) {
		case 1:
			int kq =1;
			if(kq==-1){
				out.print("failed");
			}else if(kq==-2){
				out.print("f");
			}
			break;
		
		default:
			break;
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getLog(List<ehotelMonitor> list)
	{
		
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		
		for(int i=0;i<list.size();i++)
		{
			ehotelMonitor item=list.get(i);
			mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+item.getFullname()+"]]>";
				mData+="</name>";
				mData+="\n";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	} 
	
}
package ehotel.admin.Mod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.domain.menu.Group;
import ehotel.domain.menu.Menu;
import ehotel.inter.AMDMenu;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;

public class ServiceCommon extends ServiceParent {
	/**
	 * Constructor of the object.
	 */
	public ServiceCommon() {
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
	 * This method is called when a form has its tag value method equals to get.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		
		String ip=request.getRemoteAddr();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int cmd=-1;
		
		if(request.getParameter("CMD")!=null)
		{
			try{
				cmd=Integer.parseInt(request.getParameter("CMD").toString());
			}catch (Exception e) {
				// TODO: handle exception
			}		
		}	
		
		switch (cmd) 
		{
			case 1://getlist  menu	group		
			{
				System.out.println("GET SUB list MENU");
				response.setContentType("text/xml");
				AMDMenu admMenu = DBIGateway.getAMDMenu();
				Vector<Group> groups = admMenu.getGroups(2,ip);
				System.out.println("length:"+groups.size());				
				String st=getxmlMenu(groups);
				out.print(st);					
				break;
			}		
			case 2:
			{
				System.out.println("GET SUB MENU");
				int groupsID=-1;
				if(request.getParameter("groups")!=null)
				{
					groupsID=Integer.parseInt(request.getParameter("groups").toString());				
					response.setContentType("text/xml");
					AMDMenu admMenu = DBIGateway.getAMDMenu();
					Vector<Menu> menus = admMenu.getMenuList(groupsID, LangID);				
					String st=getxmlSubMenu(menus);
					out.print(st);
				}
				break;
			}
		default:
			break;
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to post.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
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

	/**
	 * Initialization of the servlet. <br>
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getxmlMenu(Vector<Group> v)
	{
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";			
		for(int i=0;i<v.size();i++)
		{
			mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+v.get(i).getGroupName()+"]]>";
				mData+="</name>";
				mData+="<id>\n";
				mData+=v.get(i).getGroupId();
				mData+="</id>\n";				
				mData+="<parent>";
				mData+=v.get(i).getParentId();
				mData+="</parent>";
				
			mData+="</Item>";
		}
		mData+="</xml>";		
		return mData;
	}
	private String getxmlSubMenu(Vector<Menu> v)
	{
		ILOGIN iuser = DBIGateway.getILogin();	
		boolean isadmin=iuser.isAdmin(ipAdress);
		System.out.println("Admin:"+isadmin);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";			
		for(int i=0;i<v.size();i++)
		{
			boolean role=false;			
			if(!isadmin)
			{
				try
				{
					role=iuser.checkRoleUser(ipAdress, v.get(i).getRole());
				}catch (Exception e) {
				}
			}
			if (checkFlag(v.get(i).getMenuId())) {
				mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+v.get(i).getMenuName()+"]]>";
				mData+="</name>";
				mData+="<id>";				
				mData+=v.get(i).getMenuId();				
				mData+="</id>";				
				mData+="<parent>";
				mData+=v.get(i).getParentId();
				mData+="</parent>";
				mData+="<url>";
				mData+=v.get(i).getHref();
				mData+="</url>";
				mData+="<role>";
				if(isadmin||role)
				{
					mData+=1;					
				}else
				{
					mData+=-1;
				}				
				mData+="</role>";
				mData+="</Item>";
			}
		}
		mData+="</xml>";
		return mData;
	}
	
	//neu id giong trong list thi FALSE
	public boolean checkFlag(int id) {
		boolean flag = true;
		Properties prop = new Properties();
       	try {
       		prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
       	} catch (IOException ex) {
       		ex.printStackTrace();
       	}
       	String list = prop.getProperty("ehotel.mainmenu.submenu.remove");
       	String[] listStr = list.split(",");
       	int[] listInt = new int[listStr.length];
       	for (int i = 0; i<listStr.length;i++) {
       		listInt[i] = Integer.parseInt(listStr[i]);
       	}
       	if (listInt.length == 0) {
       		return true;
       	}
       	else {
       		for (int j=0;j<listInt.length;j++) {
       			if (id != listInt[j]) {
       				flag = true;
       			}
       			else {
       				return false;
       			}
       		}
       	}
		return flag;
	}
}

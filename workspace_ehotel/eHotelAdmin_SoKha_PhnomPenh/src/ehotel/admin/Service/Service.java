package ehotel.admin.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.util.Def;
import ehotel.domain.menu.Menu;
import ehotel.inter.AMDMenu;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;

public class Service extends ServiceParent
{

	/**
	 * Constructor of the object.
	 */
	public Service() 
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
		
		super.doGet(request, response)	;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int id=1;	
		int subId=-1;
		try
		{
			id=Integer.parseInt(request.getParameter("ID").toString());
			
		}catch (Exception e)
		{
			// TODO: handle exception
		}
		if(request.getParameter("SubId")!=null)
		{
			subId=Integer.parseInt(request.getParameter("SubId").toString());
		}
		if(id==-1)
		{
			//redirecd to login
			
		}else
		{
			// - check sesion 
			// - quyen truy xuat
			ILOGIN iuser = DBIGateway.getILogin();	
		    String ipAdress=request.getRemoteAddr();
		    boolean isadmin=iuser.isAdmin(ipAdress);
			AMDMenu admMenu = DBIGateway.getAMDMenu();
			//Vector<Group> groups = admMenu.getGroups(LangID);
			Vector<Menu> menus = admMenu.getMenuList(id, LangID);	
				int i=0;
				Menu iMenu=menus.get(0);
				if(!isadmin)
				for(int j=0;j<menus.size();j++)
				{
					iMenu=menus.get(j);				
					if(iuser.checkRoleUser(ipAdress, iMenu.getRole()))
					{
						break;
					}
				}								
				for(;i<menus.size();i++)
				{
					if(menus.get(i).getMenuId()==subId)
					{
						iMenu=menus.get(i);
						break;
					}
				};	
				String url=iMenu.getHref();
				url+="?"+Def.MenuId+"="+id;
				url+="&"+Def.SubId+"="+iMenu.getMenuId();
				url+="&r="+Math.random();
				if(iMenu.getHref()!=null)
				{
					System.out.println("path:"+url);
					this.sendRedirect(request,response,url);
				}else
				{
					out.write("building...");
				}		
				
				
		}	
	}	
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


	public void init() throws ServletException {
		// Put your code here
	}
	

}

package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;

public class ExchangeRate extends ServiceParent
{
	private static final long serialVersionUID = 1L;

	public ExchangeRate() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		switch (cmd)
		{
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
				request.setAttribute("fileJSP", "../pmsMng/Other/ExchangeRate.jsp");
				this.showJSPpage(request, response, "/include/Mainpage.jsp");
				break;
			}
			case 1://
			{
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

	public void init() throws ServletException {
		// Put your code here
	}
}
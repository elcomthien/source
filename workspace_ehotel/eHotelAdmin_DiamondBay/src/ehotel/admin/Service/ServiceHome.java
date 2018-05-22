package ehotel.admin.Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehotel.admin.util.Def;
import ehotel.admin.util.ReaderLang;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;

public class ServiceHome extends HttpServlet 
{

	public int LangID=2;
	/**
	 * Constructor of the object.
	 */
	public ServiceHome()
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
			throws ServletException, IOException 
	{
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		if(request.getParameter(Def._lang)!=null)
		{
			LangID=Integer.parseInt(request.getParameter(Def._lang).toString().trim());
			ReaderLang readerLang=new ReaderLang();
			readerLang.init(LangID);
			session.setAttribute(Def._lang, readerLang);
			session.setAttribute(Def._langID, String.valueOf(LangID));			
		}		
		if(session.getAttribute(Def._langID)!=null)
		{
			LangID=Integer.parseInt(session.getAttribute(Def._langID).toString());
		}		
		if(session.getAttribute(Def._lang)==null)
		{
			ReaderLang readerLang=new ReaderLang();
			readerLang.init(LangID);
			session.setAttribute(Def._lang, readerLang);
		}		
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
			this.showJSPpage(request, response, "/include/home.jsp");			
			break;
		case 1:
		{
			request.getSession().removeAttribute("user");
			String ip=request.getRemoteAddr();
			ILOGIN iuser = DBIGateway.getILogin();
			System.out.println(iuser.getLoginUserName("------"));
			iuser.logout(ip);
			this.showJSPpage(request, response, "/include/home.jsp");
			break;
		}
		case 2: 
		{
			String ip=request.getRemoteAddr();
			ILOGIN iuser = DBIGateway.getILogin();
			boolean b= iuser.isLogedinByAnotherUser(ip);
			out.print(b);
			break;
		}
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

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
//		PrintWriter out = response.getWriter();
		String user="Guest";
		String pass="";
		String ip=request.getRemoteAddr();
		if(request.getParameter("user")!=null)
		{
			user=request.getParameter("user").toString().trim();
		}
		if(request.getParameter("password")!=null)
		{
			pass=request.getParameter("password").toString().trim();			
		}				
		ILOGIN iuser = DBIGateway.getILogin();
		
		boolean login=iuser.login(user, pass, ip);
		HttpSession session=request.getSession();
		if(session.getAttribute(Def._lang)==null)
		{				
			ReaderLang readerLang=new ReaderLang();
			readerLang.init(LangID);
			session.setAttribute(Def._lang, readerLang);
		}
		if(login)
		{		
			iuser.isAdmin(user);			
			session.setAttribute("user", user);			
						
		}
		this.showJSPpage(request, response, "/include/home.jsp");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	public void showJSPpage(HttpServletRequest request, HttpServletResponse response,
			String url) {
		try {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);			
			rd.forward(request, response);			
		} catch (Exception ex)
		{
			
			//logger.info(TAG + "ERROR Goto=>>" + url+req.getRemoteAddr(), ex);
			ex.printStackTrace();
		}
	}
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response,String url)
	{
		try
		{
			response.sendRedirect(url);
		}catch (Exception e) {
		}
	}
}

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

public class ServiceParent extends HttpServlet {

	public int LangID = 2;
	public String ipAdress = "";

	/**
	 * Constructor of the object.
	 */
	public ServiceParent() {
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

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ipAdress = request.getRemoteAddr();
		if (request.getParameter(Def._lang) != null) {
			LangID = Integer.parseInt(request.getParameter(Def._lang).toString().trim());
			ReaderLang readerLang = new ReaderLang();
			readerLang.init(LangID);
			session.setAttribute(Def._lang, readerLang);
			session.setAttribute(Def._langID, String.valueOf(LangID));
		}
		if (session.getAttribute(Def._langID) != null) {
			LangID = Integer.parseInt(session.getAttribute(Def._langID).toString());
		}
		if (session.getAttribute(Def._lang) == null) {
			ReaderLang readerLang = new ReaderLang();
			readerLang.init(LangID);
			session.setAttribute(Def._lang, readerLang);
		}
		if (session.getAttribute(Def._user) == null) {
			this.sendRedirect(request, response, "/eHotelAdmin/ServiceHome");
			return;
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to post.
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	public void showJSPpage(HttpServletRequest request, HttpServletResponse response, String url) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Def._user) == null) {
			this.sendRedirect(request, response, "/eHotelAdmin/ServiceHome");
			return;
		} else {
			String ip = request.getRemoteAddr();
			ILOGIN iuser = DBIGateway.getILogin();
			boolean b = iuser.isLogedinByAnotherUser(ip);

			if (b) {
				request.getSession().removeAttribute(Def._user);
				this.sendRedirect(request, response, "/eHotelAdmin/ServiceHome");
				return;
			}
		}
		try {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception ex) {

			// logger.info(TAG + "ERROR Goto=>>" + url+req.getRemoteAddr(), ex);
			ex.printStackTrace();
		}
	}

	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (Exception e) {
		}
	}

}

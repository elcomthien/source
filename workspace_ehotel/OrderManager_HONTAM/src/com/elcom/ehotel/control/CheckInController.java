package com.elcom.ehotel.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;

//@WebServlet("/CheckIn")
public class CheckInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckInController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("checkout".equalsIgnoreCase(action)){
			request.getSession().removeAttribute("user");
			String ip=request.getRemoteAddr();
			ILOGIN iuser = DBIGateway.getILogin();
			System.out.println(iuser.getLoginUserName("------"));
			iuser.logout(ip);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("checkin".equalsIgnoreCase(action)) {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			System.out.println("login: username");
			// PrintWriter out = response.getWriter();
			String user = "Guest";
			String pass = "";
			String ip = request.getRemoteAddr();
			if (request.getParameter("user") != null) {
				user = request.getParameter("user").toString().trim();
			}
			if (request.getParameter("password") != null) {
				pass = request.getParameter("password").toString().trim();
			}
			System.out.println("login: username = " + user + " --- password = " + pass);
			ILOGIN iuser = DBIGateway.getILogin();

			boolean login = iuser.login(user, pass, ip);
			System.out.println("login = " + login);
			HttpSession session = request.getSession();
			if (login) {
				iuser.isAdmin(user);
				session.setAttribute("user", user);
				if (session.getAttribute("user") != null) {
					String ipAdress = request.getRemoteAddr();
					if (!iuser.isAdmin(ipAdress)) {
						try {
							if (iuser.checkRoleUser(ipAdress, "PMS$ORDER")) {
								System.out.println("User co quyen order!");
								response.sendRedirect("order?action=all");
							} else {
								System.out.println("User khong co quyen order");
								String error = "You do not have permission to access!";
								request.setAttribute("message", error);
								request.getRequestDispatcher("login.jsp").forward(request, response);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("User admin");
						response.sendRedirect("order?action=all");
					}
				}
			}else {
				System.out.println("Username or Password is invalid!");
				String error = "Username or Password is invalid!";
				request.setAttribute("message", error);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
}

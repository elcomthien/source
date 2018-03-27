package com.elcom.ehotel.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;


@WebServlet("/CheckOut")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CheckOutController() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("user");
		String ip=request.getRemoteAddr();
		ILOGIN iuser = DBIGateway.getILogin();
		System.out.println(iuser.getLoginUserName("------"));
		iuser.logout(ip);
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

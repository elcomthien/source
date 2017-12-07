package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.service.HomeService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HomeService homeService = new HomeService();

	public HomeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action.equals("checklogin")) {
			String username = "";
			String password = "";
			username = request.getParameter("username");
			password = request.getParameter("password");

			LogUtil.logControl(HomeController.class.toString(), "checklogin", "username,,," + username + ",,,password,,," + password);

			// iduser = -1: account is not exist; = -2: wrong password
			HashMap<String, String> map = homeService.checkLogin(username, password);
			response.getWriter().write(new Gson().toJson(map));
		}
	}

}

package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.UserActiveModel;
import com.elcom.ehotel.admin.service.UserActiveService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class UserActiveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserActiveService userActiveService = new UserActiveService();
	public UserActiveController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(action.equals("getuseractive")){
			String from = "";
			String to = "";
			from = request.getParameter("from");
			to = request.getParameter("to");
			
			LogUtil.logControl(UserActiveController.class.toString(), "getuseractive", "from|" + from + "|to|" + to);
			
			List<UserActiveModel> list = new ArrayList<UserActiveModel>();
			list = userActiveService.getListUserActive(from, to);
			response.getWriter().write(new Gson().toJson(list));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();

		if (action.equals("adduseractive")) {
			String username = request.getParameter("username");
			String subject = request.getParameter("subject");
			String description = request.getParameter("description");

			LogUtil.logControl(UserActiveController.class.toString(), "adduseractive", "username|" + username + "|subject|" + subject + "|description|" + description);

			int rs = userActiveService.addUserActive(username, subject, description);
			out.println(rs);
		}

	}

}

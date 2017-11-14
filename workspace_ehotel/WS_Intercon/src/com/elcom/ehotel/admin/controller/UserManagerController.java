package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.UserManagerModel;
import com.elcom.ehotel.admin.service.UserManagerService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;
import com.google.gson.Gson;

public class UserManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManagerService userManagerService = new UserManagerService();

	public UserManagerController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (action.equals("getlistuser")) {
			LogUtil.logControl(UserManagerController.class.toString(), "getlistuser", "none");

			List<UserManagerModel> list = new ArrayList<UserManagerModel>();
			list = userManagerService.getListUser();
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistrole")) {
			String langid = request.getParameter("langid");

			LogUtil.logControl(UserManagerController.class.toString(), "getlistuser", "langid,,," + langid);

			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			list = userManagerService.getListRold(langid);
			response.getWriter().write(new Gson().toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action.equals("adduser")) {
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String department = request.getParameter("department");
			String active = request.getParameter("active");

			LogUtil.logControl(UserManagerController.class.toString(), "adduser", "user,,," + user + ",,,pass,,," + pass + ",,,name,,,"
					+ name + ",,,address,,," + address + ",,,department,,," + department + ",,,active,,," + active);

			UserManagerModel um = new UserManagerModel();
			um.setUser(user);
			um.setPass(pass);
			um.setName(UnicodeConverter.encodeUnicode(name));
			um.setAddress(address);
			um.setDepartment(department);
			um.setActive(active);

			int rs = userManagerService.addUser(um);
			out.println(rs);
		}

		if (action.equals("edituser")) {
			String id = request.getParameter("id");
			// String user = request.getParameter("user");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String department = request.getParameter("department");
			String active = request.getParameter("active");

			LogUtil.logControl(UserManagerController.class.toString(), "adduser", "id,,," + id + ",,,name,,," + name + ",,,address,,,"
					+ address + ",,,department,,," + department + ",,,active,,," + active);

			UserManagerModel um = new UserManagerModel();
			um.setId(id);
			// um.setUser(user);
			um.setName(UnicodeConverter.encodeUnicode(name));
			um.setAddress(address);
			um.setDepartment(department);
			um.setActive(active);

			int rs = userManagerService.editUser(um);
			out.println(rs);
		}

		if (action.equals("changepass")) {
			String id = request.getParameter("id");
			String newpass = request.getParameter("newpass");
			String oldpass = request.getParameter("oldpass");

			LogUtil.logControl(UserManagerController.class.toString(), "changepass", "id,,," + id + ",,,oldpass,,," + oldpass
					+ ",,,newpass,,," + newpass);

			int rs = userManagerService.changePass(id, newpass, oldpass);
			out.println(rs);
		}

		if (action.equals("deleteuser")) {
			String id = request.getParameter("id");

			LogUtil.logControl(UserManagerController.class.toString(), "deleteuser", "id,,," + id);

			int rs = userManagerService.deleteUser(id);
			out.println(rs);
		}

		if (action.equals("changepassadmin")) {
			String id = request.getParameter("id");
			String newpass = request.getParameter("newpass");

			LogUtil.logControl(UserManagerController.class.toString(), "changepass", "id,,," + id + ",,,newpass,,," + newpass);

			int rs = userManagerService.changePassAdmin(id, newpass);
			out.println(rs);
		}

	}

}

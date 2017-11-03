package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.ConfigFTPModel;
import com.elcom.ehotel.admin.model.FileModel;
import com.elcom.ehotel.admin.service.ConfigFTPService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class ConfigFTPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfigFTPService configFTPService = new ConfigFTPService();

	public ConfigFTPController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action.equals("getconfig")) {

			LogUtil.logControl(ConfigFTPController.class.toString(), "getconfig", "none");

			ConfigFTPModel config = new ConfigFTPModel();
			config = configFTPService.getConfigFTP();
			response.getWriter().write(new Gson().toJson(config));
		}

		if (action.equals("getlistfile")) {
			String type = request.getParameter("type");

			LogUtil.logControl(ConfigFTPController.class.toString(), "getlistfile", "type,,," + type);

			List<FileModel> list = new ArrayList<FileModel>();
			list = configFTPService.getListFileFTP(type);
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistfolder")) {
			String path = request.getParameter("path");

			LogUtil.logControl(ConfigFTPController.class.toString(), "getlistfolder", "path,,," + path);

			List<String> list = new ArrayList<String>();
			list = configFTPService.getListFolderFTP(path);
			response.getWriter().write(new Gson().toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();

		if (action.equals("editftpconfig")) {
			String host = request.getParameter("host");
			String port = request.getParameter("port");
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");

			LogUtil.logControl(ConfigFTPController.class.toString(), "editftpconfig", "host,,," + host + ",,,port,,," + port + ",,,user,,," + user + ",,,pass,,,"
					+ pass);

			ConfigFTPModel ftp = new ConfigFTPModel();
			ftp.setHost(host);
			ftp.setPort(port);
			ftp.setUser(user);
			ftp.setPass(pass);

			int rs = configFTPService.editConfigFTP(ftp);
			out.println(rs);
		}
	}

}

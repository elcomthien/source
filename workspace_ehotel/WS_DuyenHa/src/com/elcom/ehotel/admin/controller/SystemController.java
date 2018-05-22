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

import com.elcom.ehotel.admin.model.SystemAdvertiseModel;
import com.elcom.ehotel.admin.model.SystemServiceModel;
import com.elcom.ehotel.admin.service.SystemService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class SystemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemService systemService = new SystemService();

	public SystemController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (action.equals("getlistservice")) {
			String langId = request.getParameter("langid");

			LogUtil.logControl(SystemController.class.toString(), "getlistservice", "langId,,," + langId);

			List<SystemServiceModel> list = new ArrayList<SystemServiceModel>();
			list = systemService.getListService(langId);
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("gettextwelcome")) {
			LogUtil.logControl(SystemController.class.toString(), "gettextwelcome", "none");

			HashMap<String, String> hmap = new HashMap<String, String>();
			hmap = systemService.getTextWelcome();
			response.getWriter().write(new Gson().toJson(hmap));
		}

		if (action.equals("getlistadvertise")) {
			String type = request.getParameter("type");

			LogUtil.logControl(SystemController.class.toString(), "getlistadvertise", "type,,," + type);

			List<SystemAdvertiseModel> list = new ArrayList<SystemAdvertiseModel>();
			list = systemService.getListAdvertise(type);
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getservice")) {
			String langId = request.getParameter("langid");
			// parentid = -1: service main; != -1 service child
			String parentId = request.getParameter("parentid");

			LogUtil.logControl(SystemController.class.toString(), "getservice", "langId,,," + langId + ",,,parentid,,," + parentId);

			List<SystemServiceModel> list = new ArrayList<SystemServiceModel>();
			list = systemService.getListServiceForParent(langId, parentId);
			response.getWriter().write(new Gson().toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action.equals("editsystemservice")) {
			String id = request.getParameter("idservice");
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String langid = request.getParameter("langid");
			String index = request.getParameter("index");
			String invisible = request.getParameter("invisible");

			LogUtil.logControl(SystemController.class.toString(), "editsystemservice", "idservice,,," + id + ",,,name,,," + name + ",,,image,,,"
					+ image + ",,,langid,,," + langid + ",,,index,,," + index + ",,,invisible,,," + invisible);

			SystemServiceModel service = new SystemServiceModel();
			service.setId(id);
			service.setName(name);
			service.setImage(image);
			service.setIndex(index);
			service.setInvisible(invisible);

			int rs = systemService.editSystemService(service, langid);
			out.println(rs);
		}

		if (action.equals("updateinviservice")) {
			String idservice = request.getParameter("idservice");

			LogUtil.logControl(SystemController.class.toString(), "updateinviservice", "idservice,,," + idservice);

			int rs = systemService.updateStatusSystemSerivce(idservice);
			out.println(rs);
		}

		if (action.equals("updatetextwelcome")) {
			String line01 = request.getParameter("line01");
			String line02 = request.getParameter("line02");
			String logo = request.getParameter("logo");
			String logosmall = request.getParameter("logosmall");

			LogUtil.logControl(SystemController.class.toString(), "updatetextwelcome", "line01,,," + line01 + ",,,line02,,," + line02 + ",,,logo,,,"
					+ logo + ",,,logosmall,,," + logosmall);

			int rs = systemService.updateTextWelcome(line01, line02, logo, logosmall);
			out.println(rs);
		}

		if (action.equals("addadvertise")) {
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String type = request.getParameter("type");
			String setbg = request.getParameter("setbg");
			String invisible = request.getParameter("invisible");

			LogUtil.logControl(SystemController.class.toString(), "addadvertise", "name,,," + name + ",,,image,,," + image + ",,,type,,," + type
					+ ",,,setbg,,," + setbg + ",,,invisible,,," + invisible);

			SystemAdvertiseModel adv = new SystemAdvertiseModel();
			adv.setName(name);
			adv.setImage(image);
			adv.setType(type);
			adv.setBackground(setbg);
			adv.setInvisible(invisible);

			int rs = systemService.addAdvertise(adv);
			out.println(rs);
		}

		if (action.equals("editadvertise")) {
			String advid = request.getParameter("advid");
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String type = request.getParameter("type");
			String setbg = request.getParameter("setbg");
			String invisible = request.getParameter("invisible");

			LogUtil.logControl(SystemController.class.toString(), "editadvertise", "advertiseid,,," + advid + ",,,name,,," + name + ",,,image,,,"
					+ image + ",,,type,,," + type + ",,,setbg,,," + setbg + ",,,invisible,,," + invisible);

			SystemAdvertiseModel adv = new SystemAdvertiseModel();
			adv.setId(advid);
			adv.setName(name);
			adv.setImage(image);
			adv.setType(type);
			adv.setBackground(setbg);
			adv.setInvisible(invisible);

			int rs = systemService.editAdvertise(adv);
			out.println(rs);
		}

		if (action.equals("deleteadvertise")) {
			String advid = request.getParameter("id");

			LogUtil.logControl(SystemController.class.toString(), "deleteadvertise", "idservice,,," + advid);

			int rs = systemService.deleteAdvertise(advid);
			out.println(rs);
		}

	}

}

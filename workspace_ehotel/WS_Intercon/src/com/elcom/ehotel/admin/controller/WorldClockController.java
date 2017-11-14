package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.WorldClockModel;
import com.elcom.ehotel.admin.service.WorldClockService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

@WebServlet("/WorldClockController")
public class WorldClockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorldClockService worldClockService = new WorldClockService();

	public WorldClockController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (action.equals("getlistworldclock")) {

			LogUtil.logControl(WorldClockController.class.toString(), "getlistworldclock", "none");

			Map<String, Object> map = new HashMap<String, Object>();
			map = worldClockService.getListWorldClock();
			response.getWriter().write(new Gson().toJson(map));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (action.equals("addworldclock")) {
			String city = request.getParameter("city");
			String national = request.getParameter("national");
			String timezone = request.getParameter("timezone");
			String image = request.getParameter("image");

			LogUtil.logControl(WorldClockController.class.toString(), "addworldclock", "city,,," + city + ",,,national,,," + national
					+ ",,,timezone,,," + timezone + ",,,image,,," + image);

			WorldClockModel clock = new WorldClockModel(city, national, timezone, image);

			Map<String, Object> map = new HashMap<String, Object>();
			map = worldClockService.addWorldClock(clock);
			response.getWriter().write(new Gson().toJson(map));
		}

		if (action.equals("editworldclock")) {
			String id = request.getParameter("id");
			String city = request.getParameter("city");
			String national = request.getParameter("national");
			String timezone = request.getParameter("timezone");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String index = request.getParameter("index");

			LogUtil.logControl(WorldClockController.class.toString(), "editworldclock", "id,,," + id + ",,,city,,," + city
					+ ",,,national,,," + national + ",,,timezone,,," + timezone + ",,,image,,," + image + ",,,status,,," + status
					+ ",,,index,,," + index);

			WorldClockModel clock = new WorldClockModel(id, city, national, timezone, status, index, image);

			Map<String, Object> map = new HashMap<String, Object>();
			map = worldClockService.editWorldClock(clock);
			response.getWriter().write(new Gson().toJson(map));
		}

		if (action.equals("deleteworldclock")) {
			String id = request.getParameter("id");

			LogUtil.logControl(WorldClockController.class.toString(), "deleteworldclock", "id,,," + id);

			Map<String, Object> map = new HashMap<String, Object>();
			map = worldClockService.deleteWorldClock(id);
			response.getWriter().write(new Gson().toJson(map));
		}
	}

}

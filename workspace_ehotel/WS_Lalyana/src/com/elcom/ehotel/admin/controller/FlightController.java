package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.AirportModel;
import com.elcom.ehotel.admin.service.FlightScheduleService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

@WebServlet("/FlightController")
public class FlightController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightScheduleService flightScheduleService = new FlightScheduleService();

	public FlightController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if (action.equals("getlistairport")) {
			String langid = request.getParameter("langid");

			LogUtil.logControl(FlightController.class.toString(), "getlistairport", "langid,,," + langid);

			Map<String, Object> map = new HashMap<String, Object>();
			map = flightScheduleService.getListAirport(langid);
			response.getWriter().write(new Gson().toJson(map));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (action.equals("addairport")) {
			String name = request.getParameter("name");
			String code = request.getParameter("code");
			String image = request.getParameter("image");

			LogUtil.logControl(FlightController.class.toString(), "addairport", "name,,," + name + ",,,code,,," + code + ",,,image,,,"
					+ image);

			AirportModel air = new AirportModel(name, code, image);

			Map<String, Object> map = new HashMap<String, Object>();
			map = flightScheduleService.addAirport(air);
			response.getWriter().write(new Gson().toJson(map));
		}

		if (action.equals("editairport")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String code = request.getParameter("code");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String index = request.getParameter("index");
			String langid = request.getParameter("langid");

			LogUtil.logControl(FlightController.class.toString(), "addairport", "id,,," + id + ",,,name,,," + name + ",,,code,,," + code
					+ ",,,image,,," + image + ",,,status,,," + status + ",,,index,,," + index + ",,,langid,,," + langid);

			AirportModel air = new AirportModel(id, name, code, status, index, image, langid);

			Map<String, Object> map = new HashMap<String, Object>();
			map = flightScheduleService.editAirport(air);
			response.getWriter().write(new Gson().toJson(map));
		}

		if (action.equals("deleteairport")) {
			String id = request.getParameter("id");

			LogUtil.logControl(FlightController.class.toString(), "deleteairport", "id,,," + id);

			Map<String, Object> map = new HashMap<String, Object>();
			map = flightScheduleService.deleteAirport(id);
			response.getWriter().write(new Gson().toJson(map));
		}
	}

}

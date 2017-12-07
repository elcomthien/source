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

import com.elcom.ehotel.admin.model.PMSWeatherModel;
import com.elcom.ehotel.admin.model.SystemServiceModel;
import com.elcom.ehotel.admin.service.PMSInternetService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class PMSInternetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PMSInternetService pmsInternetService = new PMSInternetService();

	public PMSInternetController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (action.equals("getlistweather")) {
			LogUtil.logControl(PMSInternetController.class.toString(), "getlistweather", "none");

			List<PMSWeatherModel> list = new ArrayList<PMSWeatherModel>();
			list = pmsInternetService.getListCountryWeather();
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getitemservice")) {
			String serviceid = "";
			String langid = "";

			serviceid = request.getParameter("serviceid");
			langid = request.getParameter("langid");

			LogUtil.logControl(PMSInternetController.class.toString(), "getitemservice", "serviceid|" + serviceid + "|langid|" + langid);

			HashMap<String, String> hm = pmsInternetService.getItemInternet(serviceid, langid);
			response.getWriter().write(new Gson().toJson(hm));

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action.equals("addweather")) {
			String name = "";
			String def = "";
			String country = "";
			String iscurrent = "";
			String location = "";
			String index = "";
			String image = "";
			String status = "";

			name = request.getParameter("name");
			def = request.getParameter("def");
			country = request.getParameter("country");
			iscurrent = request.getParameter("iscurrent");
			location = request.getParameter("location");
			index = request.getParameter("index");
			image = request.getParameter("image");
			status = request.getParameter("status");

			LogUtil.logControl(PMSInternetController.class.toString(), "addweather", "name|" + name + "|def|" + def + "|country|" + country
					+ "|iscurrent|" + iscurrent + "|location|" + location + "|index|" + index + "|image|" + image + "|status|" + status);

			PMSWeatherModel wea = new PMSWeatherModel();
			wea.setName(name);
			wea.setDef(def);
			wea.setCountry(country);
			wea.setIscurrent(iscurrent);
			wea.setLocation(location);
			wea.setIndex(index);
			wea.setImage(image);
			wea.setStatus(status);

			int rs = pmsInternetService.addCountryWeather(wea);
			out.println(rs);
		}

		if (action.equals("editweather")) {
			String id = "";
			String name = "";
			String def = "";
			String country = "";
			String iscurrent = "";
			String location = "";
			String index = "";
			String image = "";
			String status = "";

			id = request.getParameter("id");
			name = request.getParameter("name");
			def = request.getParameter("def");
			country = request.getParameter("country");
			iscurrent = request.getParameter("iscurrent");
			location = request.getParameter("location");
			index = request.getParameter("index");
			image = request.getParameter("image");
			status = request.getParameter("status");

			LogUtil.logControl(PMSInternetController.class.toString(), "editweather", "id|" + id + "name|" + name + "|def|" + def + "|country|"
					+ country + "|iscurrent|" + iscurrent + "|location|" + location + "|index|" + index + "|image|" + image + "|status|" + status);

			PMSWeatherModel wea = new PMSWeatherModel();
			wea.setId(id);
			wea.setName(name);
			wea.setDef(def);
			wea.setCountry(country);
			wea.setIscurrent(iscurrent);
			wea.setLocation(location);
			wea.setIndex(index);
			wea.setImage(image);
			wea.setStatus(status);

			int rs = pmsInternetService.editCountryWeather(wea);
			out.println(rs);
		}

		if (action.equals("deleteweather")) {
			String id = "";

			id = request.getParameter("id");

			LogUtil.logControl(PMSInternetController.class.toString(), "deleteweather", "id|" + id);

			int rs = pmsInternetService.deleteCountryWeather(id);
			out.println(rs);
		}

		if (action.equals("edititemservice")) {
			String serviceid = "";
			String servicename = "";
			String invisible = "";
			String index = "";
			String apkpackage = "";
			String image = "";
			String apkpath = "";
			String langid = "";

			serviceid = request.getParameter("serviceid");
			servicename = request.getParameter("servicename");
			invisible = request.getParameter("invisible");
			index = request.getParameter("index");
			apkpackage = request.getParameter("apkpackage");
			image = request.getParameter("image");
			apkpath = request.getParameter("apkpath");
			langid = request.getParameter("langid");

			LogUtil.logControl(PMSInternetController.class.toString(), "edititemservice", "serviceid" + serviceid + "servicename|" + servicename
					+ "|invisible|" + invisible + "|index|" + index + "|apkpackage|" + apkpackage + "|image|" + image + "|apkpath|" + apkpath
					+ "|langid|" + langid);

			SystemServiceModel item = new SystemServiceModel();
			item.setId(serviceid);
			item.setName(servicename);
			item.setInvisible(invisible);
			item.setIndex(index);
			item.setApkpackage(apkpackage);
			item.setImage(image);
			item.setApkpath(apkpath);
			item.setLangid(langid);

			int rs = pmsInternetService.editItemInternet(item);
			out.println(rs);
		}
	}

}

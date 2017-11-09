package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.PMSExchangeRateModel;
import com.elcom.ehotel.admin.service.PMSExchangeRateService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class PMSExchangeRateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PMSExchangeRateService pmsExchangeRateService = new PMSExchangeRateService();

	public PMSExchangeRateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action.equals("getlistexchange")) {
			LogUtil.logControl(PMSExchangeRateController.class.toString(), "getlistexchange", "none");

			List<PMSExchangeRateModel> list = new ArrayList<PMSExchangeRateModel>();
			list = pmsExchangeRateService.getListExchangeRate();

			response.getWriter().write(new Gson().toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action.equals("addexchange")) {
			String code = "";
			String name = "";
			String buy = "";
			String sell = "";
			String transfer = "";
			String image = "";
			String icon = "";
			String invisible = "";
			String index = "";

			code = request.getParameter("code");
			name = request.getParameter("name");
			buy = request.getParameter("buy");
			sell = request.getParameter("sell");
			transfer = request.getParameter("transfer");
			image = request.getParameter("image");
			icon = request.getParameter("icon");
			invisible = request.getParameter("invisible");
			index = request.getParameter("index");

			LogUtil.logControl(PMSExchangeRateController.class.toString(), "addexchange", "code,,," + code + ",,,name,,," + name + ",,,buy,,," + buy + ",,,sell,,,"
					+ sell + ",,,transfer,,," + transfer + ",,,image,,," + image + ",,,icon,,," + icon + ",,,invisible,,," + invisible + ",,,index,,," + index);

			PMSExchangeRateModel ex = new PMSExchangeRateModel();
			ex.setCode(code);
			ex.setName(name);
			ex.setBuy(buy);
			ex.setSell(sell);
			ex.setTransfer(transfer);
			ex.setImage(image);
			ex.setIcon(icon);
			ex.setInvisible(invisible);
			ex.setIndex(index);

			int rs = pmsExchangeRateService.addExchangeRate(ex);
			out.print(rs);
		}

		if (action.equals("editexchange")) {
			String id = "";
			String code = "";
			String name = "";
			String buy = "";
			String sell = "";
			String transfer = "";
			String image = "";
			String icon = "";
			String invisible = "";
			String index = "";

			id = request.getParameter("id");
			code = request.getParameter("code");
			name = request.getParameter("name");
			buy = request.getParameter("buy");
			sell = request.getParameter("sell");
			transfer = request.getParameter("transfer");
			image = request.getParameter("image");
			icon = request.getParameter("icon");
			invisible = request.getParameter("invisible");
			index = request.getParameter("index");

			LogUtil.logControl(PMSExchangeRateController.class.toString(), "editexchange", "id,,," + id + ",,,code,,," + code + ",,,name,,," + name + ",,,buy,,,"
					+ buy + ",,,sell,,," + sell + ",,,transfer,,," + transfer + ",,,image,,," + image + ",,,icon,,," + icon + ",,,invisible,,," + invisible + ",,,index,,,"
					+ index);

			PMSExchangeRateModel ex = new PMSExchangeRateModel();
			ex.setId(id);
			ex.setCode(code);
			ex.setName(name);
			ex.setBuy(buy);
			ex.setSell(sell);
			ex.setTransfer(transfer);
			ex.setImage(image);
			ex.setIcon(icon);
			ex.setInvisible(invisible);
			ex.setIndex(index);

			int rs = pmsExchangeRateService.editExchangeRate(ex);
			out.print(rs);
		}

		if (action.equals("deleteexchange")) {
			String id = "";

			id = request.getParameter("id");

			LogUtil.logControl(PMSExchangeRateController.class.toString(), "deleteexchange", "id,,," + id);

			int rs = pmsExchangeRateService.deleteExchangeRate(id);
			out.print(rs);
		}
	}

}

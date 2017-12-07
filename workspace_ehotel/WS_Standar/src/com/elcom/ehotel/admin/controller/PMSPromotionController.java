package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.PMSPromotionModel;
import com.elcom.ehotel.admin.service.PMSPromotionService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class PMSPromotionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PMSPromotionService pmsPromotionService = new PMSPromotionService();

	public PMSPromotionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action.equals("getlistpromotion")) {
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSPromotionController.class.toString(), "getlistpromotion", "langid|" + langId);

			List<PMSPromotionModel> list = new ArrayList<PMSPromotionModel>();
			list = pmsPromotionService.getListPromotion(langId);

			response.getWriter().write(new Gson().toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action.equals("addpromotion")) {
			String name = "";
			String def = "";
			String url = "";
			String image = "";
			String invisible = "";

			name = request.getParameter("name");
			def = request.getParameter("def");
			url = request.getParameter("url");
			image = request.getParameter("image");
			invisible = request.getParameter("invisible");

			LogUtil.logControl(PMSPromotionController.class.toString(), "addpromotion", "name|" + name + "|def|" + def + "|url|" + url + "|image|"
					+ image + "|invisible|" + invisible);

			PMSPromotionModel pro = new PMSPromotionModel();
			pro.setName(name);
			pro.setDef(def);
			pro.setUrl(url);
			pro.setImage(image);
			pro.setInvisible(invisible);

			int rs = pmsPromotionService.addPromotion(pro);
			out.print(rs);
		}

		if (action.equals("editpromotion")) {
			String proId = "";
			String name = "";
			String def = "";
			String url = "";
			String image = "";
			String invisible = "";
			String langId = "";

			proId = request.getParameter("id");
			name = request.getParameter("name");
			def = request.getParameter("def");
			url = request.getParameter("url");
			image = request.getParameter("image");
			invisible = request.getParameter("invisible");
			langId = request.getParameter("langid");

			LogUtil.logControl(PMSPromotionController.class.toString(), "editpromotion", "proid|" + proId + "|name|" + name + "|def|" + def + "|url|"
					+ url + "|image|" + image + "|invisible|" + invisible + "|langid|" + langId);

			PMSPromotionModel pro = new PMSPromotionModel();
			pro.setId(proId);
			pro.setName(name);
			pro.setDef(def);
			pro.setUrl(url);
			pro.setImage(image);
			pro.setInvisible(invisible);
			pro.setLangid(langId);

			int rs = pmsPromotionService.editPromotion(pro);
			out.print(rs);
		}

		if (action.equals("deletepromotion")) {
			String proId = "";

			proId = request.getParameter("id");

			LogUtil.logControl(PMSPromotionController.class.toString(), "deletepromotion", "proid|" + proId);

			int rs = pmsPromotionService.deletePromotion(proId);
			out.print(rs);
		}
	}

}

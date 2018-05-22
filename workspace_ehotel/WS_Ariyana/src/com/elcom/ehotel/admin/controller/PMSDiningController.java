package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.PMSDiningDetailModel;
import com.elcom.ehotel.admin.model.PMSDiningItemModel;
import com.elcom.ehotel.admin.model.PMSDiningSubjectModel;
import com.elcom.ehotel.admin.service.PMSDiningService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class PMSDiningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PMSDiningService pmsDiningService = new PMSDiningService();

	public PMSDiningController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action.equals("getsubjectdining")) {
			String parentId = request.getParameter("parentid");
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSDiningController.class.toString(), "getsubjectdining", "parentId,,," + parentId + ",,,langId,,," + langId);

			List<PMSDiningSubjectModel> list = new ArrayList<PMSDiningSubjectModel>();
			list = pmsDiningService.getSubjectDining(parentId, langId);

			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getitemdining")) {
			String itemId = request.getParameter("itemid");
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSDiningController.class.toString(), "getitemdining", "itemId,,," + itemId + ",,,langId,,," + langId);

			List<PMSDiningItemModel> list = new ArrayList<PMSDiningItemModel>();
			list = pmsDiningService.getItemDining(itemId, langId);

			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getitemdetail")) {
			String itemId = request.getParameter("iditem");
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSDiningController.class.toString(), "getitemdetail", "itemId,,," + itemId + ",,,langId,,," + langId);

			List<PMSDiningDetailModel> list = new ArrayList<PMSDiningDetailModel>();
			list = pmsDiningService.getItemDetail(itemId, langId);

			response.getWriter().write(new Gson().toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();

		if (action.equals("addsubjectdining")) {
			String parentId = "";
			String name = "";
			String image = "";
			// String imageIC = "";
			String active = "";
			String index = "";

			parentId = request.getParameter("parentid");
			name = request.getParameter("name");
			image = request.getParameter("image");
			// imageIC = request.getParameter("imageic");
			// imageIC = "none";
			active = request.getParameter("active");
			index = request.getParameter("index");

			LogUtil.logControl(PMSDiningController.class.toString(), "addsubjectdining", "parentId,,," + parentId + ",,,name,,," + name
					+ ",,,image,,," + image + ",,,imageic,,," + image + ",,,active,,," + active + ",,,index,,," + index);

			PMSDiningSubjectModel sub = new PMSDiningSubjectModel();
			sub.setParent(parentId);
			sub.setName(name);
			sub.setImage(image);
			sub.setImageIC(image);
			sub.setActive(active);
			sub.setIndex(index);

			int rs = pmsDiningService.addSubjectDining(sub);
			out.print(rs);
		}

		if (action.equals("editsubjectdining")) {
			String subjectId = "";
			String name = "";
			String image = "";
			// String imageIC = "";
			String active = "";
			String index = "";
			String langId = "";

			subjectId = request.getParameter("subjectid");
			name = request.getParameter("name");
			image = request.getParameter("image");
			// imageIC = request.getParameter("imageic");
			// imageIC = "none";
			active = request.getParameter("active");
			index = request.getParameter("index");
			langId = request.getParameter("langid");

			LogUtil.logControl(PMSDiningController.class.toString(), "editsubjectdining", "subjectId,,," + subjectId + ",,,name,,," + name
					+ ",,,image,,," + image + ",,,imageic,,," + image + ",,,active,,," + active + ",,,index,,," + index + ",,,langId,,,"
					+ langId);

			PMSDiningSubjectModel sub = new PMSDiningSubjectModel();
			sub.setId(subjectId);
			sub.setName(name);
			sub.setImage(image);
			sub.setImageIC(image);
			sub.setActive(active);
			sub.setIndex(index);
			sub.setLangId(langId);

			int rs = pmsDiningService.editSubjectDining(sub);
			out.print(rs);
		}

		if (action.equals("deletesubjectdining")) {
			String subjectId = "";

			subjectId = request.getParameter("subjectid");

			LogUtil.logControl(PMSDiningController.class.toString(), "deletesubjectdining", "subjectId,,," + subjectId);

			int rs = pmsDiningService.deleteSubjectDining(subjectId);
			out.print(rs);
		}

		if (action.equals("additemdining")) {
			String subjectId = "";
			String name = "";
			String def = "";
			String price = "";
			String iunit = "";
			String image = "";
			String active = "";
			String index = "";

			subjectId = request.getParameter("subjectid");
			name = request.getParameter("name");
			image = request.getParameter("image");
			def = request.getParameter("def");
			price = request.getParameter("price");
			iunit = request.getParameter("iunit");
			active = request.getParameter("active");
			index = request.getParameter("index");

			LogUtil.logControl(PMSDiningController.class.toString(), "additemdining", "parentId,,," + subjectId + ",,,name,,," + name
					+ ",,,def,,," + def + ",,,price,,," + price + ",,,iunit,,," + iunit + ",,,image,,," + image + ",,,active,,," + active
					+ ",,,index,,," + index);

			PMSDiningItemModel item = new PMSDiningItemModel();
			item.setSubjectId(subjectId);
			item.setName(name);
			item.setDef(def);
			item.setPrice(price);
			item.setIunit(iunit);
			item.setImage(image);
			item.setActive(active);
			item.setIndex(index);

			int rs = pmsDiningService.addItemDining(item);
			out.print(rs);
		}

		if (action.equals("edititemdining")) {
			String itemId = "";
			String name = "";
			String def = "";
			String price = "";
			String iunit = "";
			String image = "";
			String active = "";
			String index = "";
			String langId = "";

			itemId = request.getParameter("itemid");
			name = request.getParameter("name");
			image = request.getParameter("image");
			def = request.getParameter("def");
			price = request.getParameter("price");
			iunit = request.getParameter("iunit");
			active = request.getParameter("active");
			index = request.getParameter("index");
			langId = request.getParameter("langId");

			LogUtil.logControl(PMSDiningController.class.toString(), "edititemdining", "itemid,,," + itemId + ",,,name,,," + name
					+ ",,,def,,," + def + ",,,price,,," + price + ",,,iunit,,," + iunit + ",,,image,,," + image + ",,,active,,," + active
					+ ",,,index,,," + index + ",,,langId,,," + langId);

			PMSDiningItemModel item = new PMSDiningItemModel();
			item.setId(itemId);
			item.setName(name);
			item.setDef(def);
			item.setPrice(price);
			item.setIunit(iunit);
			item.setImage(image);
			item.setActive(active);
			item.setIndex(index);
			item.setLangid(langId);

			int rs = pmsDiningService.editItemDining(item);
			out.print(rs);
		}

		if (action.equals("deleteitemdining")) {
			String itemId = "";

			itemId = request.getParameter("itemid");

			LogUtil.logControl(PMSDiningController.class.toString(), "deleteitemdining", "itemid,,," + itemId);

			int rs = pmsDiningService.deleteItemDining(itemId);
			out.print(rs);
		}

		if (action.equals("additemdetail")) {
			String itemId = "";
			String detail = "";
			String invisible = "";
			String index = "";

			itemId = request.getParameter("iditem");
			detail = request.getParameter("def");
			invisible = request.getParameter("invisible");
			index = request.getParameter("index");

			LogUtil.logControl(PMSDiningController.class.toString(), "additemdetail", "itemId,,," + itemId + ",,,detail,,," + detail
					+ ",,,invisible,,," + invisible + ",,,index,,," + index);

			PMSDiningDetailModel item = new PMSDiningDetailModel();
			item.setId(itemId);
			item.setDetail(detail);
			item.setInvisible(invisible);
			item.setIndex(index);

			int rs = pmsDiningService.addItemDetail(item);
			out.print(rs);
		}

		if (action.equals("edititemdetail")) {
			String itemId = "";
			String detail = "";
			String invisible = "";
			String index = "";
			String langid = "";

			itemId = request.getParameter("id");
			detail = request.getParameter("def");
			invisible = request.getParameter("invisible");
			index = request.getParameter("index");
			langid = request.getParameter("langid");

			LogUtil.logControl(PMSDiningController.class.toString(), "edititemdetail", "Id,,," + itemId + ",,,detail,,," + detail
					+ ",,,invisible,,," + invisible + ",,,index,,," + index + ",,,langid,,," + langid);

			PMSDiningDetailModel item = new PMSDiningDetailModel();
			item.setId(itemId);
			item.setDetail(detail);
			item.setInvisible(invisible);
			item.setIndex(index);
			item.setLangid(langid);

			int rs = pmsDiningService.editItemDetail(item);
			out.print(rs);
		}

		if (action.equals("deleteitemdetail")) {
			String itemId = "";

			itemId = request.getParameter("id");

			LogUtil.logControl(PMSDiningController.class.toString(), "deleteitemdetail", "Id,,," + itemId);

			int rs = pmsDiningService.deleteItemDetail(itemId);
			out.print(rs);
		}

	}
}

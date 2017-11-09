package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.ModContentModel;
import com.elcom.ehotel.admin.model.ModSubjectModel;
import com.elcom.ehotel.admin.service.ModService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class ModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModService modService = new ModService();

	public ModController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (action.equals("getlistsubject")) {
			String langid = request.getParameter("langid");

			LogUtil.logControl(ModController.class.toString(), "getlistsubject", "langId,,," + langid);

			List<ModSubjectModel> list = new ArrayList<ModSubjectModel>();
			list = modService.getListSubjectMod(langid);
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistcontent")) {
			String idSubject = request.getParameter("idsubject");
			String langid = request.getParameter("langid");

			LogUtil.logControl(ModController.class.toString(), "getlistcontent", "idSubject,,," + idSubject + ",,,langId,,," + langid);

			List<ModContentModel> list = new ArrayList<ModContentModel>();
			list = modService.getListContent(idSubject, langid);
			response.getWriter().write(new Gson().toJson(list));

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();

		if (action.equals("addsubject")) {
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String imageIC = request.getParameter("imageic");
			String invisible = request.getParameter("invisible");

			LogUtil.logControl(ModController.class.toString(), "addsubject", "name,,," + name + ",,,image,,," + image + ",,,imageic,,," + imageIC
					+ ",,,invisible,,," + invisible);

			ModSubjectModel mod = new ModSubjectModel();
			mod.setName(name);
			mod.setImage(image);
			mod.setImageIC(imageIC);
			mod.setInvisible(invisible);

			int rs = modService.addSubjectMod(mod);
			out.println(rs);
		}

		if (action.equals("editsubject")) {
			String idSubject = request.getParameter("idsubject");
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String imageIC = request.getParameter("imageic");
			String invisible = request.getParameter("invisible");
			String langid = request.getParameter("langid");

			LogUtil.logControl(ModController.class.toString(), "editsubject", "idSubject,,," + idSubject + ",,,name,,," + name + ",,,image,,,"
					+ image + ",,,imageic,,," + imageIC + ",,,invisible,,," + invisible + ",,,langid,,," + langid);

			ModSubjectModel mod = new ModSubjectModel();
			mod.setIdSubject(idSubject);
			mod.setName(name);
			mod.setImage(image);
			mod.setImageIC(imageIC);
			mod.setInvisible(invisible);
			mod.setLangid(langid);

			int rs = modService.editSubjectMod(mod);
			out.println(rs);
		}

		if (action.equals("deletesubject")) {
			String idSubject = request.getParameter("idsubject");

			LogUtil.logControl(ModController.class.toString(), "deletesubject", "idSubject,,," + idSubject);

			int rs = modService.deleteSubjectMod(idSubject);
			out.println(rs);
		}

		if (action.equals("addmod")) {
			String idSubject = request.getParameter("idsubject");
			String name = request.getParameter("name");
			String url = request.getParameter("url");
			String invisible = request.getParameter("invisible");

			LogUtil.logControl(ModController.class.toString(), "addmod", "idSubject,,," + idSubject + ",,,name,,," + name + ",,,url,,," + url
					+ ",,,invisible,,," + invisible);

			ModContentModel con = new ModContentModel();
			con.setIdSubject(idSubject);
			con.setName(name);
			con.setUrl(url);
			con.setInvisible(invisible);

			int rs = modService.addMod(con);
			out.println(rs);
		}

		if (action.equals("editmod")) {
			String idSubject = request.getParameter("idsubject");
			String idContent = request.getParameter("idcontent");
			String name = request.getParameter("name");
			String invisible = request.getParameter("invisible");
			String langid = request.getParameter("langid");

			LogUtil.logControl(ModController.class.toString(), "editmod", "idSubject,,," + idSubject + ",,,idContent,,," + idContent + ",,,name,,,"
					+ name + ",,,invisible,,," + invisible + ",,,langid,,," + langid);

			ModContentModel con = new ModContentModel();
			con.setIdSubject(idSubject);
			con.setIdContent(idContent);
			con.setName(name);
			con.setInvisible(invisible);
			con.setLangid(langid);

			int rs = modService.editMod(con);
			out.println(rs);
		}

		if (action.equals("deletemod")) {
			String idContent = request.getParameter("idcontent");

			LogUtil.logControl(ModController.class.toString(), "deletemod", "idContent,,," + idContent);

			int rs = modService.deleteMod(idContent);
			out.println(rs);
		}
	}

}

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

		if (action.equals("getlistsubjectgroup")) {
			String langid = request.getParameter("langid");
			String idGroup = request.getParameter("idgroup");

			LogUtil.logControl(ModController.class.toString(), "getlistsubjectgroup", "langId,,," + langid + ",,,idGroup,,," + idGroup);

			List<ModSubjectModel> list = new ArrayList<ModSubjectModel>();
			list = modService.getListSubjectModGroup(langid, idGroup);
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistcontentgroup")) {
			String idSubject = request.getParameter("idsubject");
			String langid = request.getParameter("langid");
			String idGroup = request.getParameter("idgroup");

			LogUtil.logControl(ModController.class.toString(), "getlistcontentgroup", "idSubject,,," + idSubject + ",,,langId,,," + langid
					+ ",,,idGroup,,," + idGroup);

			List<ModContentModel> list = new ArrayList<ModContentModel>();
			list = modService.getListContentGroup(idSubject, langid, idGroup);
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

			LogUtil.logControl(ModController.class.toString(), "addsubject", "name,,," + name + ",,,image,,," + image + ",,,imageic,,,"
					+ imageIC + ",,,invisible,,," + invisible);

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

			LogUtil.logControl(ModController.class.toString(), "editsubject", "idSubject,,," + idSubject + ",,,name,,," + name
					+ ",,,image,,," + image + ",,,imageic,,," + imageIC + ",,,invisible,,," + invisible + ",,,langid,,," + langid);

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

			LogUtil.logControl(ModController.class.toString(), "addmod", "idSubject,,," + idSubject + ",,,name,,," + name + ",,,url,,,"
					+ url + ",,,invisible,,," + invisible);

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

			LogUtil.logControl(ModController.class.toString(), "editmod", "idSubject,,," + idSubject + ",,,idContent,,," + idContent
					+ ",,,name,,," + name + ",,,invisible,,," + invisible + ",,,langid,,," + langid);

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

		// //////////////Group/////////////////////

		if (action.equals("addsubjectgroup")) {
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String imageIC = request.getParameter("imageic");
			String invisible = request.getParameter("invisible");
			String idGroup = request.getParameter("idgroup");

			LogUtil.logControl(ModController.class.toString(), "addsubjectgroup", "name,,," + name + ",,,image,,," + image
					+ ",,,imageic,,," + imageIC + ",,,invisible,,," + invisible + ",,,idGroup,,," + idGroup);

			ModSubjectModel mod = new ModSubjectModel();
			mod.setName(name);
			mod.setImage(image);
			mod.setImageIC(imageIC);
			mod.setInvisible(invisible);
			mod.setIdGroup(idGroup);

			int rs = modService.addSubjectModGroup(mod);
			out.println(rs);
		}

		if (action.equals("editsubjectgroup")) {
			String idSubject = request.getParameter("idsubject");
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String imageIC = request.getParameter("imageic");
			String invisible = request.getParameter("invisible");
			String langid = request.getParameter("langid");
			String idGroup = request.getParameter("idgroup");

			LogUtil.logControl(ModController.class.toString(), "editsubjectgroup", "idSubject,,," + idSubject + ",,,name,,," + name
					+ ",,,image,,," + image + ",,,imageic,,," + imageIC + ",,,invisible,,," + invisible + ",,,langid,,," + langid
					+ ",,,idGroup,,," + idGroup);

			ModSubjectModel mod = new ModSubjectModel();
			mod.setIdSubject(idSubject);
			mod.setName(name);
			mod.setImage(image);
			mod.setImageIC(imageIC);
			mod.setInvisible(invisible);
			mod.setLangid(langid);
			mod.setIdGroup(idGroup);

			int rs = modService.editSubjectModGroup(mod);
			out.println(rs);
		}

		if (action.equals("deletesubjectgroup")) {
			String idSubject = request.getParameter("idsubject");
			String idGroup = request.getParameter("idgroup");

			LogUtil.logControl(ModController.class.toString(), "deletesubjectgroup", "idSubject,,," + idSubject + ",,,idGroup,,," + idGroup);

			int rs = modService.deleteSubjectModGroup(idSubject, idGroup);
			out.println(rs);
		}

		if (action.equals("addmodgroup")) {
			String idSubject = request.getParameter("idsubject");
			String name = request.getParameter("name");
			String url = request.getParameter("url");
			String invisible = request.getParameter("invisible");
			String idGroup = request.getParameter("idgroup");

			LogUtil.logControl(ModController.class.toString(), "addmodgroup", "idSubject,,," + idSubject + ",,,name,,," + name
					+ ",,,url,,," + url + ",,,invisible,,," + invisible + ",,,idGroup,,," + idGroup);

			ModContentModel con = new ModContentModel();
			con.setIdSubject(idSubject);
			con.setName(name);
			con.setUrl(url);
			con.setInvisible(invisible);
			con.setIdGroup(idGroup);

			int rs = modService.addModGroup(con);
			out.println(rs);
		}

		if (action.equals("editmodgroup")) {
			String idSubject = request.getParameter("idsubject");
			String idContent = request.getParameter("idcontent");
			String name = request.getParameter("name");
			String invisible = request.getParameter("invisible");
			String langid = request.getParameter("langid");
			String idGroup = request.getParameter("idgroup");

			LogUtil.logControl(ModController.class.toString(), "editmodgroup", "idSubject,,," + idSubject + ",,,idContent,,," + idContent
					+ ",,,name,,," + name + ",,,invisible,,," + invisible + ",,,langid,,," + langid + ",,,idGroup,,," + idGroup);

			ModContentModel con = new ModContentModel();
			con.setIdSubject(idSubject);
			con.setIdContent(idContent);
			con.setName(name);
			con.setInvisible(invisible);
			con.setLangid(langid);
			con.setIdGroup(idGroup);

			int rs = modService.editModGroup(con);
			out.println(rs);
		}

		if (action.equals("deletemodgroup")) {
			String idContent = request.getParameter("idcontent");
			String idGroup = request.getParameter("idgroup");

			LogUtil.logControl(ModController.class.toString(), "deletemodgroup", "idContent,,," + idContent + ",,,idGroup,,," + idGroup);

			int rs = modService.deleteModGroup(idContent, idGroup);
			out.println(rs);
		}
	}

}

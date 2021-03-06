package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.PMSInfoContentModel;
import com.elcom.ehotel.admin.model.PMSInfoSubjectModel;
import com.elcom.ehotel.admin.service.PMSInfoService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.elcom.ehotel.admin.util.UnicodeConverter;
import com.google.gson.Gson;

public class PMSInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PMSInfoService pmsInfoService = new PMSInfoService();

	public PMSInfoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action.equals("getsubjectinfo")) {
			String serviceId = request.getParameter("serviceid");
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSInfoController.class.toString(), "getsubjectinfo", "serviceId,,," + serviceId + ",,,langId,,," + langId);

			List<PMSInfoSubjectModel> list = new ArrayList<PMSInfoSubjectModel>();
			list = pmsInfoService.getListSubjectInfo(serviceId, langId);

			response.getWriter().write(new Gson().toJson(list));

		}

		if (action.equals("getcontentinfo")) {
			String subjectId = request.getParameter("subjectid");
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSInfoController.class.toString(), "getcontentinfo", "subjectId,,," + subjectId + ",,,langId,,," + langId);

			List<PMSInfoContentModel> list = new ArrayList<PMSInfoContentModel>();
			list = pmsInfoService.getListContentInfo(subjectId, langId);

			response.getWriter().write(new Gson().toJson(list));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();

		if (action.equals("addsubjectinfo")) {
			String serviceId = request.getParameter("serviceid");
			String subjectName = request.getParameter("subjectname");
			String image = request.getParameter("image");
			String imageIC = request.getParameter("imageic");
			String invisible = request.getParameter("invisible");
			String index = request.getParameter("index");

			LogUtil.logControl(PMSInfoController.class.toString(), "addsubjectinfo", "serviceId,,," + serviceId + ",,,subjectName,,," + subjectName
					+ ",,,image,,," + image + ",,,imageic,,," + imageIC + ",,,invisible,,," + invisible + ",,,index,,," + index);

			PMSInfoSubjectModel info = new PMSInfoSubjectModel();
			info.setServiceId(serviceId);
			info.setSubjectName(UnicodeConverter.encodeUnicode(subjectName));
			info.setImage(image);
			info.setImageIC(imageIC);
			info.setInvisible(invisible);
			info.setIndex(index);

			int rs = pmsInfoService.addSubjectInfo(info);
			out.print(rs);
		}

		if (action.equals("editsubjectinfo")) {
			String serviceId = request.getParameter("serviceId");
			String subjectId = request.getParameter("subjectid");
			String subjectName = request.getParameter("subjectname");
			String image = request.getParameter("image");
			String imageIC = request.getParameter("imageic");
			String invisible = request.getParameter("invisible");
			String index = request.getParameter("index");
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSInfoController.class.toString(), "editsubjectinfo", "subjectId,,," + subjectId + ",,,subjectId,,," + subjectId
					+ ",,,subjectName,,," + subjectName + ",,,image,,," + image + ",,,imageic,,," + imageIC + ",,,invisible,,," + invisible
					+ ",,,index,,," + index + ",,,langId,,," + langId);

			PMSInfoSubjectModel info = new PMSInfoSubjectModel();
			info.setServiceId(serviceId);
			info.setSubjectId(subjectId);
			info.setSubjectName(UnicodeConverter.encodeUnicode(subjectName));
			info.setImage(image);
			info.setImageIC(imageIC);
			info.setInvisible(invisible);
			info.setIndex(index);
			info.setLangId(langId);

			int rs = pmsInfoService.editSubjectInfo(info);
			out.print(rs);
		}

		if (action.equals("deletesubjectinfo")) {
			String serviceId = request.getParameter("serviceid");
			String subjectId = request.getParameter("subjectid");

			LogUtil.logControl(PMSInfoController.class.toString(), "deletesubjectinfo", "serviceId,,," + serviceId + ",,,subjectId,,," + subjectId);

			int rs = pmsInfoService.deleteSubjectInfo(serviceId, subjectId);
			out.print(rs);
		}

		if (action.equals("addcontentinfo")) {
			String subjectId = request.getParameter("subjectid");
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String invisible = request.getParameter("invisible");

			LogUtil.logControl(PMSInfoController.class.toString(), "addcontentinfo", "subjectId,,," + subjectId + ",,,name,,," + name
					+ ",,,description,,," + description + ",,,invisible,,," + invisible);

			PMSInfoContentModel info = new PMSInfoContentModel();
			info.setSubjectId(subjectId);
			info.setName(name);
			info.setDescription(description);
			info.setInvisible(invisible);

			int rs = pmsInfoService.addContentInfo(info);
			out.print(rs);
		}

		if (action.equals("editcontentinfo")) {
			String contentId = request.getParameter("contentid");
			String name = request.getParameter("name");
			String invisible = request.getParameter("invisible");
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSInfoController.class.toString(), "editcontentinfo", "subjectId,,," + contentId + ",,,name,,," + name
					+ ",,,invisible,,," + invisible + ",,,langId,,," + langId);

			PMSInfoContentModel info = new PMSInfoContentModel();
			info.setContentId(contentId);
			info.setName(name);
			info.setInvisible(invisible);
			info.setLangId(langId);

			int rs = pmsInfoService.editContentInfo(info);
			out.print(rs);
		}

		if (action.equals("deletecontentinfo")) {
			String contentId = request.getParameter("contentid");

			LogUtil.logControl(PMSInfoController.class.toString(), "deletecontentinfo", "contentId,,," + contentId);

			int rs = pmsInfoService.deleteContentInfo(contentId);
			out.print(rs);
		}

	}
}

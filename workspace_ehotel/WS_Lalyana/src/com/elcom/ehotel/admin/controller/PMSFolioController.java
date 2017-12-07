package com.elcom.ehotel.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.ehotel.admin.model.PMSFolioGuestModel;
import com.elcom.ehotel.admin.model.PMSFolioMessageModel;
import com.elcom.ehotel.admin.model.PMSFolioRoomModel;
import com.elcom.ehotel.admin.service.PMSFolioService;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class PMSFolioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PMSFolioService pmsFolioService = new PMSFolioService();

	public PMSFolioController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (action.equals("getlistfolio")) {

			LogUtil.logControl(PMSFolioController.class.toString(), "getlistfolio", "none");

			List<PMSFolioRoomModel> list = new ArrayList<PMSFolioRoomModel>();
			list = pmsFolioService.getListFolio();
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistguest")) {
			String folionum = request.getParameter("folionum");

			LogUtil.logControl(PMSFolioController.class.toString(), "getlistguest", "folionum,,," + folionum);

			List<PMSFolioGuestModel> list = new ArrayList<PMSFolioGuestModel>();
			list = pmsFolioService.getListGuestForFolio(folionum);
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistmessage")) {
			String folioNum = request.getParameter("folionum");
			String langId = request.getParameter("langid");

			LogUtil.logControl(PMSFolioController.class.toString(), "getlistmessage", "folionum,,," + folioNum + ",,,langId,,," + langId);

			List<PMSFolioMessageModel> list = new ArrayList<PMSFolioMessageModel>();
			list = pmsFolioService.getListMessageForRoom(folioNum, langId);
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getsmartcardfolio")) {
			String folioNum = request.getParameter("folionum");
			LogUtil.logControl(PMSFolioController.class.toString(), "getsmartcardfolio", "folionum,,," + folioNum);

			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			list = pmsFolioService.getSmartcardFolio(folioNum);
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistsmartcard")) {
			LogUtil.logControl(PMSFolioController.class.toString(), "getlistsmartcard", "none");

			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			list = pmsFolioService.getListSmartcard();
			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistfoliopreview")) {
			LogUtil.logControl(PMSFolioController.class.toString(), "getlistfoliopreview", "none");

			Map<String, Object> map = new HashMap<String, Object>();
			map = pmsFolioService.getListFolioPreview();
			response.getWriter().write(new Gson().toJson(map));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action.equals("addorupdateguest")) {
			String folionum = request.getParameter("folionum");
			String clientId = request.getParameter("clientid");
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String personal = request.getParameter("personal");
			String roomShare = request.getParameter("roomshare");

			LogUtil.logControl(PMSFolioController.class.toString(), "addorupdateguest", "folionum,,," + folionum + ",,,clientId,,,"
					+ clientId + ",,,firstName,,," + firstName + ",,,lastName,,," + lastName + ",,,personal,,," + personal
					+ ",,,roomShare,,," + roomShare);

			PMSFolioGuestModel guest = new PMSFolioGuestModel();
			guest.setFolionum(folionum);
			guest.setClientid(clientId);
			guest.setFirstname(firstName);
			guest.setLastname(lastName);
			guest.setClientname(personal);
			guest.setRoomsharer(roomShare);

			int rs = pmsFolioService.addOrUpdateGuest(guest);
			out.println(rs);
		}

		if (action.equals("removeguest")) {
			String folionum = request.getParameter("folionum");
			String clientid = request.getParameter("clientid");

			LogUtil.logControl(PMSFolioController.class.toString(), "removeguest", "folionum,,," + folionum + ",,,clientid,,," + clientid);

			int rs = pmsFolioService.deleteGuest(folionum, clientid);
			out.println(rs);
		}

		if (action.equals("addmessage")) {
			String folionum = request.getParameter("folionum");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String sender = request.getParameter("sender");

			LogUtil.logControl(PMSFolioController.class.toString(), "addmessage", "folionum,,," + folionum + ",,,title,,," + title
					+ ",,,content,,," + content + ",,,sender,,," + sender);

			PMSFolioMessageModel mess = new PMSFolioMessageModel();
			mess.setRoomId(folionum);
			mess.setTitle(title);
			mess.setContent(content);
			mess.setSender(sender);

			int rs = pmsFolioService.addMessageFolio(mess);
			out.println(rs);
		}

		if (action.equals("deletemessage")) {
			String folionum = request.getParameter("folionum");
			String messId = request.getParameter("messid");

			LogUtil.logControl(PMSFolioController.class.toString(), "deletemessage", "folionum,,," + folionum + ",,,messID,,," + messId);

			int rs = pmsFolioService.deleteMessageFolio(folionum, messId);
			out.println(rs);
		}

		if (action.equals("deletesmartcard")) {
			String serinumber = request.getParameter("serinumber");

			LogUtil.logControl(PMSFolioController.class.toString(), "deletesmartcard", "serinumber,,," + serinumber);

			int rs = pmsFolioService.deleteSmartcard(serinumber);
			out.println(rs);
		}

		if (action.equals("setroompreview")) {
			String room = request.getParameter("room");
			String preview = request.getParameter("preview");
			LogUtil.logControl(PMSFolioController.class.toString(), "setroompreview", "room,,," + room + ",,,preview,,," + preview);

			int rs = pmsFolioService.updatePreview(room, preview);
			out.println(rs);
		}

		if (action.equals("publicpreview")) {

			LogUtil.logControl(PMSFolioController.class.toString(), "publicpreview", "none");

			int rs = pmsFolioService.publicPreview();
			out.println(rs);
		}
	}

}

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

import com.elcom.ehotel.admin.model.LiveTVChannelModel;
import com.elcom.ehotel.admin.model.LiveTVSubjectModel;
import com.elcom.ehotel.admin.service.LiveTVService;
import com.elcom.ehotel.admin.util.ConvertUtil;
import com.elcom.ehotel.admin.util.LogUtil;
import com.google.gson.Gson;

public class LiveTVController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LiveTVService liveTVService = new LiveTVService();

	public LiveTVController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action.equals("getlistsubject")) {
			String langId = request.getParameter("langid");

			LogUtil.logControl(LiveTVController.class.toString(), "getlistsubject", "langId,,," + langId);

			List<LiveTVSubjectModel> list = new ArrayList<LiveTVSubjectModel>();
			list = liveTVService.getListSubjectLiveTV(ConvertUtil.convertToInteger(langId));

			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistchannel")) {
			String idSubject = request.getParameter("idsubject");

			LogUtil.logControl(LiveTVController.class.toString(), "getlistchannel", "idsubject,,," + idSubject);

			List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
			list = liveTVService.getListChannel(ConvertUtil.convertToInteger(idSubject));

			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistadvertise")) {
			LogUtil.logControl(LiveTVController.class.toString(), "getlistadvertise", "none");

			List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
			list = liveTVService.getListChannelAdvertise();

			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getnotify")) {
			String langId = request.getParameter("langid");

			LogUtil.logControl(LiveTVController.class.toString(), "getnotify", "langId,,," + langId);

			HashMap<String, String> map = new HashMap<String, String>();
			map = liveTVService.getNotify(langId);

			response.getWriter().write(new Gson().toJson(map));
		}

		if (action.equals("getnotifygroup")) {
			String langId = request.getParameter("langid");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "getnotifygroup", "langId,,," + langId + ",,,idgroup,,," + idgroup);

			HashMap<String, String> map = new HashMap<String, String>();
			map = liveTVService.getNotifyGroup(langId, idgroup);

			response.getWriter().write(new Gson().toJson(map));
		}

		if (action.equals("getlistadvertisegroup")) {
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "getlistadvertisegroup", "idgroup,,," + idgroup);

			List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
			list = liveTVService.getListChannelAdvertiseGroup(idgroup);

			response.getWriter().write(new Gson().toJson(list));
		}

		if (action.equals("getlistchannelgroup")) {
			String idSubject = request.getParameter("idsubject");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "getlistchannelgroup", "idsubject,,," + idSubject + ",,,idgroup,,,"
					+ idgroup);

			List<LiveTVChannelModel> list = new ArrayList<LiveTVChannelModel>();
			list = liveTVService.getListChannelGroup(ConvertUtil.convertToInteger(idSubject), idgroup);

			response.getWriter().write(new Gson().toJson(list));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action.equals("addnewsubject")) {
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String listadd = "";
			listadd = request.getParameter("listadd");
			if (listadd.length() > 0)
				listadd = listadd.substring(0, listadd.length() - 1);

			LogUtil.logControl(LiveTVController.class.toString(), "addnewsubject", "name,,," + name + ",,,image,,," + image
					+ ",,,status,,," + status + ",,,listadd,,," + listadd);

			LiveTVSubjectModel livetv = new LiveTVSubjectModel();
			livetv.setName(name);
			livetv.setImage(image);
			livetv.setListadd(listadd);
			livetv.setStatus(status);

			int rs = liveTVService.addNewSubjectLiveTV(livetv);
			out.println(rs);
		}

		if (action.equals("editsubject")) {
			String id = request.getParameter("idsubject");
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String langid = request.getParameter("langid");
			String status = request.getParameter("status");
			String listadd = "";
			listadd = request.getParameter("listadd");
			String listremove = "";
			listremove = request.getParameter("listremove");
			if (listadd.length() > 0)
				listadd = listadd.substring(0, listadd.length() - 1);
			if (listremove.length() > 0)
				listremove = listremove.substring(0, listremove.length() - 1);

			LogUtil.logControl(LiveTVController.class.toString(), "editsubject", "idsubject,,," + id + ",,,name,,," + name + ",,,image,,,"
					+ image + ",,,status,,," + status + ",,,listadd,,," + listadd + ",,,listremove,,," + listremove);

			LiveTVSubjectModel livetv = new LiveTVSubjectModel();
			livetv.setIdSubject(id);
			livetv.setName(name);
			livetv.setImage(image);
			livetv.setStatus(status);
			livetv.setLangId(langid);
			livetv.setOderby("0");
			livetv.setListadd(listadd);
			livetv.setListremove(listremove);

			int rs = liveTVService.editSubjectLiveTV(livetv);
			out.println(rs);
		}

		if (action.equals("deletesubject")) {
			String idSubject = request.getParameter("idsubject");

			LogUtil.logControl(LiveTVController.class.toString(), "deletesubject", "idSubject,,," + idSubject);

			int rs = liveTVService.deleteSubjcetLiveTV(ConvertUtil.convertToInteger(idSubject));
			out.println(rs);
		}

		if (action.equals("addnewchannel")) {
			String idSubject = request.getParameter("idsubject");
			String name = request.getParameter("name");
			String index = request.getParameter("index");
			String link = request.getParameter("link");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String language = request.getParameter("language");
			String subtitle = request.getParameter("subtitle");

			LogUtil.logControl(LiveTVController.class.toString(), "addnewchannel", "idsubject,,," + idSubject + ",,,name,,," + name
					+ ",,,index,,," + index + ",,,link,,," + link + ",,,image,,," + image + ",,,status,,," + status + ",,,language,,,"
					+ language + ",,,subtitle,,," + subtitle);

			LiveTVChannelModel livetv = new LiveTVChannelModel();
			livetv.setName(name);
			livetv.setCode(index);
			livetv.setLink(link);
			livetv.setImage(image);
			livetv.setStatus(status);
			livetv.setLanguage(language);
			livetv.setSubtitle(subtitle);

			int rs = liveTVService.addNewChannel(livetv, idSubject);
			out.println(rs);
		}

		if (action.equals("editchannel")) {
			String idChannel = request.getParameter("idchannel");
			String name = request.getParameter("name");
			String index = request.getParameter("index");
			String link = request.getParameter("link");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String language = request.getParameter("language");
			String subtitle = request.getParameter("subtitle");

			LogUtil.logControl(LiveTVController.class.toString(), "editchannel", "idChannel,,," + idChannel + ",,,name,,," + name
					+ ",,,index,,," + index + ",,,link,,," + link + ",,,image,,," + image + ",,,status,,," + status + ",,,language,,,"
					+ language + ",,,subtitle,,," + subtitle);

			LiveTVChannelModel livetv = new LiveTVChannelModel();
			livetv.setIdChannel(idChannel);
			livetv.setName(name);
			livetv.setCode(index);
			livetv.setLink(link);
			livetv.setImage(image);
			livetv.setStatus(status);
			livetv.setLanguage(language);
			livetv.setSubtitle(subtitle);

			int rs = liveTVService.editChannel(livetv);
			out.println(rs);
		}

		if (action.equals("deletechannel")) {
			String idChannel = request.getParameter("idchannel");

			LogUtil.logControl(LiveTVController.class.toString(), "deletechannel", "idChannel,,," + idChannel);

			int rs = liveTVService.deleteChannel(ConvertUtil.convertToInteger(idChannel));
			out.println(rs);
		}

		if (action.equals("addchanneladvertise")) {
			String name = request.getParameter("name");
			String filename = request.getParameter("filename");
			String image = request.getParameter("image");
			String status = request.getParameter("status");

			LogUtil.logControl(LiveTVController.class.toString(), "addchanneladvertise", "name,,," + name + ",,,filename,,," + filename
					+ ",,,image,,," + image + ",,,status,,," + status);

			LiveTVChannelModel livetv = new LiveTVChannelModel();
			livetv.setName(name);
			livetv.setLink(filename);
			livetv.setImage(image);
			livetv.setStatus(status);

			int rs = liveTVService.addChannelAdvertise(livetv);
			out.println(rs);
		}

		if (action.equals("editchanneladvertise")) {
			String channelId = request.getParameter("channelid");
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String status = request.getParameter("status");

			LogUtil.logControl(LiveTVController.class.toString(), "editchanneladvertise", "channelId,,," + channelId + ",,,name,,," + name
					+ ",,,image,,," + image + ",,,status,,," + status);

			LiveTVChannelModel livetv = new LiveTVChannelModel();
			livetv.setIdChannel(channelId);
			livetv.setName(name);
			livetv.setImage(image);
			livetv.setStatus(status);

			int rs = liveTVService.editChannelAdvertise(livetv);
			out.println(rs);
		}

		if (action.equals("deletechanneladvertise")) {
			String channelId = request.getParameter("channelid");

			LogUtil.logControl(LiveTVController.class.toString(), "deletechanneladvertise", "channelId,,," + channelId);

			int rs = liveTVService.deleteChannelAdvertise(channelId);
			out.println(rs);
		}

		if (action.equals("editnotify")) {
			String size = request.getParameter("size");
			String color = request.getParameter("color");
			String status = request.getParameter("status");
			String langid = request.getParameter("langid");
			String text = request.getParameter("text");

			LogUtil.logControl(LiveTVController.class.toString(), "editnotify", "size,,," + size + ",,,color,,," + color + ",,,status,,,"
					+ status + ",,,langid,,," + langid + ",,,text,,," + text);

			int rs = liveTVService.editNotify(size, color, status, text, langid);
			out.println(rs);
		}

		if (action.equals("editnotifygroup")) {
			String size = request.getParameter("size");
			String color = request.getParameter("color");
			String status = request.getParameter("status");
			String langid = request.getParameter("langid");
			String text = request.getParameter("text");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "editnotifygroup", "size,,," + size + ",,,color,,," + color
					+ ",,,status,,," + status + ",,,langid,,," + langid + ",,,text,,," + text + ",,,idgroup,,," + idgroup);

			int rs = liveTVService.editNotifyGroup(size, color, status, text, langid, idgroup);
			out.println(rs);
		}

		if (action.equals("addchanneladvertisegroup")) {
			String name = request.getParameter("name");
			String filename = request.getParameter("filename");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "addchanneladvertisegroup", "name,,," + name + ",,,filename,,,"
					+ filename + ",,,image,,," + image + ",,,status,,," + status + ",,,idgroup,,," + idgroup);

			LiveTVChannelModel livetv = new LiveTVChannelModel();
			livetv.setName(name);
			livetv.setLink(filename);
			livetv.setImage(image);
			livetv.setStatus(status);
			livetv.setIdgroup(idgroup);

			int rs = liveTVService.addChannelAdvertiseGroup(livetv);
			out.println(rs);
		}

		if (action.equals("editchanneladvertisegroup")) {
			String channelId = request.getParameter("channelid");
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "editchanneladvertisegroup", "channelId,,," + channelId + ",,,name,,,"
					+ name + ",,,image,,," + image + ",,,status,,," + status + ",,,idgroup,,," + idgroup);

			LiveTVChannelModel livetv = new LiveTVChannelModel();
			livetv.setIdChannel(channelId);
			livetv.setName(name);
			livetv.setImage(image);
			livetv.setStatus(status);
			livetv.setIdgroup(idgroup);

			int rs = liveTVService.editChannelAdvertiseGroup(livetv);
			out.println(rs);
		}

		if (action.equals("deletechanneladvertisegroup")) {
			String channelId = request.getParameter("channelid");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "deletechanneladvertisegroup", "channelId,,," + channelId
					+ ",,,idgroup,,," + idgroup);

			int rs = liveTVService.deleteChannelAdvertiseGroup(channelId, idgroup);
			out.println(rs);
		}

		if (action.equals("addnewchannelgroup")) {
			String idSubject = request.getParameter("idsubject");
			String name = request.getParameter("name");
			String index = request.getParameter("index");
			String link = request.getParameter("link");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String language = request.getParameter("language");
			String subtitle = request.getParameter("subtitle");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "addnewchannelgroup", "idsubject,,," + idSubject + ",,,name,,," + name
					+ ",,,index,,," + index + ",,,link,,," + link + ",,,image,,," + image + ",,,status,,," + status + ",,,language,,,"
					+ language + ",,,subtitle,,," + subtitle + ",,,idgroup,,," + idgroup);

			LiveTVChannelModel livetv = new LiveTVChannelModel();
			livetv.setName(name);
			livetv.setCode(index);
			livetv.setLink(link);
			livetv.setImage(image);
			livetv.setStatus(status);
			livetv.setLanguage(language);
			livetv.setSubtitle(subtitle);
			livetv.setIdgroup(idgroup);

			int rs = liveTVService.addNewChannelGroup(livetv, idSubject);
			out.println(rs);
		}

		if (action.equals("editchannelgroup")) {
			String idChannel = request.getParameter("idchannel");
			String name = request.getParameter("name");
			String index = request.getParameter("index");
			String link = request.getParameter("link");
			String image = request.getParameter("image");
			String status = request.getParameter("status");
			String language = request.getParameter("language");
			String subtitle = request.getParameter("subtitle");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "editchannelgroup", "idChannel,,," + idChannel + ",,,name,,," + name
					+ ",,,index,,," + index + ",,,link,,," + link + ",,,image,,," + image + ",,,status,,," + status + ",,,language,,,"
					+ language + ",,,subtitle,,," + subtitle + ",,,idgroup,,," + idgroup);

			LiveTVChannelModel livetv = new LiveTVChannelModel();
			livetv.setIdChannel(idChannel);
			livetv.setName(name);
			livetv.setCode(index);
			livetv.setLink(link);
			livetv.setImage(image);
			livetv.setStatus(status);
			livetv.setLanguage(language);
			livetv.setSubtitle(subtitle);
			livetv.setIdgroup(idgroup);

			int rs = liveTVService.editChannelGroup(livetv);
			out.println(rs);
		}

		if (action.equals("deletechannelgroup")) {
			String idChannel = request.getParameter("idchannel");
			String idgroup = request.getParameter("idgroup");

			LogUtil.logControl(LiveTVController.class.toString(), "deletechannelgroup", "idChannel,,," + idChannel + ",,,idgroup,,,"
					+ idgroup);

			int rs = liveTVService.deleteChannelGroup(ConvertUtil.convertToInteger(idChannel), idgroup);
			out.println(rs);
		}

	}
}

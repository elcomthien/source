package com.elcom.esignage.app.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.XML;

import com.elcom.esignage.app.service.ProgramService;
import com.elcom.esignage.app.util.DateHelper;
import com.elcom.esignage.app.util.Method;
import com.elcom.esignage.app.util.Param;

public class ProgramController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProgramService programService = new ProgramService();

	public ProgramController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String sernumber = request.getParameter(Param.sernumber);
		String mac = request.getParameter(Param.mac);
		String ipClient = request.getRemoteAddr();
		String current = request.getParameter(Param.current);
		String command = request.getParameter("command");

		System.out.println("Ip: " + ipClient + " - Commmand: " + command + " - curent time stb: " + current + " - VER: "
				+ request.getParameter("ver"));

		if (command.equals(Method.getChanngeTemplates_2)) {
			String ramtotal = request.getParameter(Param.ramtotal);
			String hddtotal = request.getParameter(Param.hddtotal);
			String sdcardtotal = request.getParameter(Param.sdcardtotal);
			String cputotal = request.getParameter(Param.cputotal);
			String ram = request.getParameter(Param.ram);
			String cpu = request.getParameter(Param.cpu);
			String hdd = request.getParameter(Param.hdd);
			String sdcard = request.getParameter(Param.sdcard);
			String cursizefile = request.getParameter(Param.cursizefile);
			String totalsizefile = request.getParameter(Param.totalsizefile);
			if (cursizefile == null)
				cursizefile = "0";
			if (totalsizefile == null)
				totalsizefile = "0";
			String xml = programService.spgetChanngeTemplates(ipClient, sernumber, mac, current, ramtotal, ram, cputotal, cpu, hddtotal, hdd,
					sdcardtotal, sdcard, cursizefile, totalsizefile);
			printResponse(response, xml);
		} else if (command.equals(Method.getDownloadFileStb_10)) {
			String xml = programService.getDownloadFileStb(sernumber, mac);
			printResponse(response, xml);
		} else if (command.equals(Method.downloadComplate_11)) {
			String contentid = request.getParameter(Param.contentid);
			String process = request.getParameter(Param.process);
			String xml = programService.downloadComplate(sernumber, mac, contentid, process);
			printResponse(response, xml);
		} else if (command.equals(Method.capturestb_13)) {
			String xml = programService.getCaptureCounterStb(sernumber, mac);
			printResponse(response, xml);
		} else if (command.equals(Method.getContentLayout_3)) {
			int idplaylist = new Integer(request.getParameter(Param.idplaylist)).intValue();
			int changecontent = new Integer(request.getParameter(Param.changecontent)).intValue();
			String xml = programService.spgetContentLayout(sernumber, mac, idplaylist, changecontent);
			printResponse(response, xml);
		} else if (command.equals(Method.getContentLayouts_9)) {
			String xml = "<Playlists>\r\n";
			String idplaylists = request.getParameter(Param.idplaylist);
			int changecontent = new Integer(request.getParameter(Param.changecontent)).intValue();
			Vector<Integer> keys = DateHelper.getPlaylistid(idplaylists);
			for (int i = 0; i < keys.size(); i++)
			{
				Integer playlistid = keys.get(i);
				xml += programService.spgetContentLayout(sernumber, mac, playlistid, changecontent);
			}
			xml += DateHelper.stringToHTMLString((DateHelper.decodeURIComponent(xml)));
			xml += "</Playlists>";
			printResponse(response, xml);
		} else if (command.equals(Method.getInfoStb_4)) {
			String xml = programService.spgetInfoStb(sernumber, mac);
			printResponse(response, xml);
		} else if (command.equals(Method.getSchedule_5)) {
			String schduelid = request.getParameter(Param.scheduleid);
			String xml = programService.spgetSchedule(schduelid, sernumber, mac);
			printResponse(response, xml);
		} else if (command.equals(Method.loginstb_6)) {
			String xml = programService.sploginstb(sernumber, mac);
			printResponse(response, xml);
		} else if (command.equals(Method.getSchedulePeri_8)) {
			String xml = programService.getSchedulePeri(sernumber, mac);
			printResponse(response, xml);
		} else if (command.equals(Method.regstb_7)) {
			String savelocalmedia = request.getParameter(Param.savelocalmedia);
			String savelocalfile = request.getParameter(Param.savelocalfile);
			if (savelocalmedia != null)
				savelocalmedia = savelocalmedia.replace("@", "/");
			if (savelocalfile != null)
				savelocalfile = savelocalfile.replace("@", "/");
			String xml = programService.sp_regstb(ipClient, sernumber, mac, savelocalmedia, savelocalfile);
			xml = "<Result><message>successful</message><status>1</status></Result>";
			printResponse(response, xml);
		} else if (command.equals(Method.getHomeDefault_14)) {
			String xml = programService.spgetDefauleHome(sernumber, mac);
			printResponse(response, xml);
		} else if (command.equals(Method.iptvSubject_15)) {
			String xml = programService.spiptvSubject();
			printResponse(response, xml);
		} else if (command.equals(Method.iptvContentSubject_16)) {
			String idsubject = request.getParameter(Param.idsubject);
			String xml = programService.spiptvContentSubject(idsubject);
			printResponse(response, xml);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void printResponse(HttpServletResponse response, String xml) {
		try {
			response.getWriter().write(XML.toJSONObject(xml).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}

}

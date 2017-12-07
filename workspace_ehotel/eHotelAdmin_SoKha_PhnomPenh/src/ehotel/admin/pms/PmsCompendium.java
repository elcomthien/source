package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.HotelInfoPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.pms.eMenu;

public class PmsCompendium extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private PMSServiceDBI pmsServiceDBI = new PMSServiceDBI();
       
    public PmsCompendium() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int cmd = -1;
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
			}
		}
		switch (cmd) {
		case -1: {
			int subId = -1;
			int menuid = -1;
			if (request.getParameter(Def.MenuId) != null) {
				menuid = Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);
			request.setAttribute("fileJSP", "../pmsMng/hotel/pmsCompendium.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1:// list subject
		{
			System.out.println("Get subject Ctn");
			response.setContentType("text/xml");
			String st = pmsServiceDBI.getSubject(LangID, 103);
			out.print(st);
			break;
		}
		case 5:// list subject left
		{
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			response.setContentType("text/xml");
			String str = pmsServiceDBI.getSuboutHotel(id, LangID, 103);
			out.print(str);
			break;
		}
		case 6:// list subject right
		{
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			response.setContentType("text/xml");
			String str = pmsServiceDBI.getSubinHotel(id, LangID, 103);
			out.print(str);
			break;
		}
		case 7:// change subject PMS
		{
			System.out.println("change subject pms");
			int Id = -1;
			if (request.getParameter("id") != null) {
				Id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			Vector<Integer> list = new Vector<Integer>();
			int i = 0;
			while (request.getParameter("SubId" + i) != null) {
				int subid = Integer.parseInt(request.getParameter("SubId" + i).toString().trim());
				list.add(subid);
				i++;
			}
			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1) + ")";
			HotelInfoPMS hotel = new HotelInfoPMS();
			boolean b = hotel.changeSubjectOfItem(Id, param);
			System.out.println("chenge subject:" + b);
			break;
		}
		default:
			break;
		}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	super.doPost(request, response);
		response.setContentType("text/xml");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int cmd = -1;
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
			}
		}
		switch (cmd) {
		case 1:// insert pms subject
		{
			System.out.println("inser subject PMS!");
			response.setContentType("text/xml");
			String subjectName = "";
			String image = "";
			String urlBg = "";
			if (request.getParameter("image1") != null) {
				image = request.getParameter("image1").toString();
			}
			if (request.getParameter("image2") != null) {
				urlBg = request.getParameter("image2").toString();
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();
				eMenu menu = new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(config._hotel + "/" + image);
				menu.setUrlBg(config._hotel + "/" + urlBg);
				int id = pmsServiceDBI.addMenuService(menu, 103);

				if (id > 0) {
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + "/" + image;
					String path2 = config._pathImage + config._hotel + "/" + image;
					file.copy(path1, path2);
					file.deletefile(path1);
					path1 = config._temp + "/" + urlBg;
					path2 = config._pathImage + config._hotel + "/" + urlBg;
					file.copy(path1, path2);
					file.deletefile(path1);
				}
				out.write(id);
			}
			break;
		}
		}
    }

}

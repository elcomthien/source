package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.abs.pms.HotelActivityPMS;
import ehotel.abs.pms.HotelInfoPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.model.PMSMainMenu;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.UtilString;
import ehotel.domain.pms.eActivity;
import ehotel.domain.pms.eMenu;

public class ServiceActiviti extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private PMSServiceDBI pmsServiceDBI = new PMSServiceDBI();

	public ServiceActiviti() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
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
				menuid = Integer.parseInt(request.getParameter(Def.MenuId)
						.toString());
			}
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId)
						.toString());
			}
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);
			request.setAttribute("fileJSP", "../pmsMng/hotel/pmsActiviti.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1:// list subject activiti
		{
			System.out.println("Get subject Activiti");
			response.setContentType("text/xml");
			// String st=getsub();
			String st = getMainMenuHotel();
			out.print(st);
			break;
		}
		case 2: {
			System.out.println("Get content");
			int id = -1;
			int index = 0;
			int page = 6;
			if (request.getParameter("SubId") != null) {
				id = Integer.parseInt(request.getParameter("SubId").toString());
			}
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex")
						.toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString()
						.trim());
			}
			response.setContentType("text/xml");
			String st = getContent(id, index, page);
			out.print(st);
			break;
		}
		case 3:// delete subject
		{
			System.out.println("DELETE SUBJECT PMS");
			HotelActivityPMS hotel = new HotelActivityPMS();
			int subid = -1;
			if (request.getParameter("SubId") != null) {
				subid = Integer.parseInt(request.getParameter("SubId")
						.toString());
			}
			boolean b = hotel.removeActiMenu(subid);
			System.out.println("Delete :" + b);
			break;
		}
		case 4:// show form detail
		{
			System.out.println("Show detail Activiti");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString());
			}
			HotelActivityPMS hotel = new HotelActivityPMS();
			eActivity item = null;
			// if(id!=-1)
			item = hotel.getItemInfo(id, LangID);
			request.setAttribute("eImage", item);
			this.showJSPpage(request, response,
					"/pmsMng/hotel/detailActiviti.jsp");
			break;
		}
		case 5:// list subject left
		{
			System.out.println("show form change subject");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString());
			}
			HotelActivityPMS hotel = new HotelActivityPMS();
			eActivity item = null;
			// if(id!=-1)
			item = hotel.getItemInfo(id, LangID);
			request.setAttribute("Item", item);
			this.showJSPpage(request, response,
					"/pmsMng/hotel/ActiChangeSub.jsp");
			break;
		}
		case 6:// list subject right
		{
			System.out.println("Change upsubject UPA");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString());
			}
			int subid = -1;
			if (request.getParameter("SubId") != null) {
				subid = Integer.parseInt(request.getParameter("SubId")
						.toString());
			}
			HotelActivityPMS hotel = new HotelActivityPMS();
			hotel.changeSubjectOfItem(id, String.valueOf(subid));
			break;
		}
		case 7:// change subject PMS
		{
			int Id = -1;
			if (request.getParameter("id") != null) {
				Id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			Vector<Integer> list = new Vector();
			int i = 0;
			while (request.getParameter("SubId" + i) != null) {
				int subid = Integer.parseInt(request.getParameter("SubId" + i)
						.toString().trim());
				list.add(subid);
				i++;
			}
			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1) + ")";
			System.out.println("change subject pms " + param);
			HotelInfoPMS hotel = new HotelInfoPMS();
			boolean b = hotel.changeSubjectOfItem(Id, param);
			System.out.println("chenge subject:" + b);
			break;
		}
		case 8:// delete item Attaction
		{
			Vector<Integer> list = new Vector();
			int i = 0;
			int subId = -1;
			while (request.getParameter("id" + i) != null) {
				int subid = Integer.parseInt(request.getParameter("id" + i)
						.toString().trim());
				list.add(subid);
				i++;
			}
			if (request.getParameter("SubId") != null) {
				subId = Integer.parseInt(request.getParameter("SubId")
						.toString().trim());
			}
			HotelActivityPMS hotel = new HotelActivityPMS();
			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1) + ")";
			System.out.println("delete item Attaction " + param);
			hotel.removeItem(param);
			break;
		}
		case 9:// change status
		{
			System.out.println("change status ");
			int id = -1;
			HotelActivityPMS hotel = new HotelActivityPMS();
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			hotel.changeStatus(id);
		}
		case 10:// change status invisible main menu
		{
			System.out.println("change status invisible");
			int subId = -1;
			int invisible = -1;
			if (request.getParameter("subid") != null) {
				subId = Integer.parseInt(request.getParameter("subid").toString());
			}
			if (request.getParameter("invisible") != null) {
				invisible = Integer.parseInt(request.getParameter("invisible").toString());
			}
			if(invisible == -1){
				out.write("f");
				break;
			}
			boolean flag = true;
			if(invisible == 0)
				flag = pmsServiceDBI.updateInvisibleMainMenu(subId, 1);
			else if(invisible == 1)
				flag = pmsServiceDBI.updateInvisibleMainMenu(subId, 0);
			if(flag)
				out.write("t");
			else
				out.write("f");
			break;
		}
		default:
			break;
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		response.setContentType("text/html");
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
			System.out.println("insert subject PMS Activiti!");
			response.setContentType("text/xml");
			String subjectName = "";
			String image = "";
			String urlBg = "";
			int parent = -1;
			if (request.getParameter("image1") != null) {
				image = request.getParameter("image1").toString();
			}
			if (request.getParameter("image2") != null) {
				urlBg = request.getParameter("image2").toString();
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();
				HotelActivityPMS hotel = new HotelActivityPMS();
				eMenu menu = new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(config._activities + "/" + image);
				menu.setUrlBg(config._activities + "/" + urlBg);
				int id = hotel.addActiMenu(menu);
				if (id > 0) {
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + "/" + image;
					String path2 = config._pathImage + config._activities + "/"
							+ image;
					file.copy(path1, path2);
					file.deletefile(path1);
					path1 = config._temp + "/" + urlBg;
					path2 = config._pathImage + config._activities + "/"
							+ urlBg;
					file.copy(path1, path2);
					file.deletefile(path1);
				}
				out.write(id);
			}
			break;
		}
		case 2:// update subject
		{
			System.out.println("UPDATE SUBJECT PMS");
			String subjectName = "";
			String image = "";
			String bgimage = "";
			if (request.getParameter("image1") != null) {
				image = request.getParameter("image1").toString();
			}
			if (request.getParameter("image2") != null) {
				bgimage = request.getParameter("image2").toString();
			}
			int subjId = -1;
			if (request.getParameter("name") != null) {
				if (request.getParameter(Def.SubId) != null) {
					subjId = Integer.parseInt(request.getParameter(Def.SubId)
							.toString());
					subjectName = request.getParameter("name").toString();
					HotelActivityPMS hotel = new HotelActivityPMS();
					eMenu menu = new eMenu();
					menu.setMenuName(subjectName);
					menu.setMenuId(subjId);
					menu.setUrlImage(config._activities + "/" + image);
					menu.setUrlBg(config._activities + "/" + bgimage);
					boolean t = hotel.editActiMenu(menu, LangID);
					System.out.println("UPDATE SUBJECT PMS ACTIVITI:" + t);
					if (t) {
						ManagerFile file = new ManagerFile();
						String path1 = config._temp + "/" + image;
						String path2 = config._pathImage + config._activities
								+ "/" + image;
						file.copy(path1, path2);
						file.deletefile(path1);
						path1 = config._temp + "/" + bgimage;
						path2 = config._pathImage + config._activities + "/"
								+ bgimage;
						file.copy(path1, path2);
						file.deletefile(path1);
					}
				}
			}
			break;
		}
		case 3:// update eimage
		{
			System.out.println("Update eImage");
			int id = -1;
			String name = "";
			String def = "";
			int status = 1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString().trim();
			}
			if (request.getParameter("des") != null) {
				def = request.getParameter("des").toString();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll(
						"<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			if (request.getParameter("status") != null) {
				status = Integer.parseInt(request.getParameter("status")
						.toString().trim());
			}
			HotelActivityPMS hotel = new HotelActivityPMS();
			eActivity item = new eActivity();
			item.setId(id);
			item.setInvisible(status);
			item.setDef(def);
			item.setName(name);
			boolean b = hotel.editItem(item, LangID);
			break;
		}
		case 4://
		{
			int id = -1;
			String name = "";
			String def = "";
			int status = 1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString().trim();
			}
			if (request.getParameter("des") != null) {
				def = request.getParameter("des").toString();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll(
						"<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			if (request.getParameter("status") != null) {
				status = Integer.parseInt(request.getParameter("status")
						.toString().trim());
			}
			HotelActivityPMS hotel = new HotelActivityPMS();
			eActivity item = new eActivity();
			item.setInvisible(status);
			item.setDef(def);
			item.setName(name);
			int b = hotel.addItem(id, item);
			System.out.println("insert eActiviti " + b);
			break;
		}
		default:
			break;
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	private String getsub() {
		HotelActivityPMS hotel = new HotelActivityPMS();
		Vector<eMenu> subject = hotel.getMenus(LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < subject.size(); i++) {
			eMenu item = subject.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getMenuName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getMenuId();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<imagebg>\n";
			mData += item.getUrlBg();
			mData += "</imagebg>";
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getMainMenuHotel() {
		List<PMSMainMenu> list = new ArrayList<PMSMainMenu>();
		list = pmsServiceDBI.getMainMenu(18, LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getMenuname() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += list.get(i).getMenuid();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += list.get(i).getMenuimage();
			mData += "</image>";
			mData += "<imagebg>\n";
			mData += list.get(i).getMenubackground();
			mData += "</imagebg>";
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "<invisible>";
			mData += list.get(i).getMenuinvisible();
			mData += "</invisible>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}

	private String getContent(int subjId, int index, int page) {
		HotelActivityPMS hotel = new HotelActivityPMS();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		Vector<eActivity> info = hotel.getItems(subjId, LangID, fr, to);
		int count = hotel.countItem(subjId);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			eActivity item = info.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>";
			mData += "<Des>";
			mData += "<![CDATA[" + UtilString.converString(item.getDef())
					+ "]]>";
			mData += "</Des>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getInvisible() + "]]>";
			mData += "</status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
}
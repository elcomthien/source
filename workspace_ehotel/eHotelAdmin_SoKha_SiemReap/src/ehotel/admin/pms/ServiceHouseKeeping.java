package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehotel.abs.pms.HousekeepingPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dao.HouseKeepingDao;
import ehotel.admin.dao.ReportServiceDBI;
import ehotel.admin.model.MinibarModel;
import ehotel.admin.model.RoomStatusModel;
import ehotel.admin.model.UserHouseKeepingModel;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.pms.eHousekeeping;
import ehotel.domain.pms.eMenu;

public class ServiceHouseKeeping extends ServiceParent {

	private static final long serialVersionUID = 1L;
	private HouseKeepingDao houseKeepingDao = new HouseKeepingDao();

	public ServiceHouseKeeping() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	@SuppressWarnings("unused")
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
			request.setAttribute("fileJSP",
					"../pmsMng/keeping/housekeeping.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1:// get subject
		{
			System.out.println("Get subject housekkeping");
			response.setContentType("text/xml");
			String st = getmenu();
			out.print(st);
			break;
		}
		case 2://
		{
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
			String st = getItem(id);
			out.print(st);
			break;
		}
		case 3: {
			System.out.println("Show form detail");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString());
			}
			HousekeepingPMS hotel = new HousekeepingPMS();
			eHousekeeping item = null;
			if (id != -1)
				item = hotel.getItemInfo(id, LangID);
			request.setAttribute("Item", item);
			this.showJSPpage(request, response, "/pmsMng/keeping/detaiItem.jsp");
			break;
		}
		case 4: {
			System.out.println("Delete item:");
			Vector<Integer> list = new Vector<Integer>();
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
			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1) + ")";
			HousekeepingPMS hotel = new HousekeepingPMS();
			hotel.removeItem(param);
			break;
		}
		case 5: {
			System.out.println("Show form detail");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString());
			}
			HousekeepingPMS hotel = new HousekeepingPMS();
			hotel.changeStatus(id);
			break;
		}
		// /////////////////////////////////////////
		// Phan housekeeping cho sokha siem reap //
		// ////////////////////////////////////////
		case 6://
		{
			System.out.println("Get list user housekeeping");
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex")
						.toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString()
						.trim());
			}
			response.setContentType("text/xml");
			String st = getListUserHouseKeeping(index, page);
			out.print(st);
			break;
		}
		case 7://
		{
			System.out.println("Show form add user");
			request.getRequestDispatcher("/pmsMng/keeping/DetailUserHK.jsp")
					.forward(request, response);
			break;
		}
		case 8://
		{
			System.out.println("Delete user housekeeping");
			int id = -1;
			String account = "";

			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			if (request.getParameter("account") != null) {
				account = request.getParameter("account").toString().trim();
			}
			boolean flag = houseKeepingDao.deleteUserHousekeeping(id);
			response.setContentType("text/xml");
			if (flag) {
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities(
						"Delete user housekeeping with account \"" + account
								+ "\"", user);
				out.print("t");
			} else
				out.print("f");
			break;
		}
		case 9://
		{
			System.out.println("Change status user housekeeping");
			int id = -1;
			String account = "";
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			if (request.getParameter("account") != null) {
				account = request.getParameter("account").toString().trim();
			}
			boolean flag = houseKeepingDao.changeStatusUserHousekeeping(id);
			response.setContentType("text/xml");
			if (flag) {
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities(
						"Change status user housekeeping with account \"" + account
								+ "\"", user);
				out.print("t");
			} else
				out.print("f");
			break;
		}
		case 10://
		{
			System.out.println("Get list minibar");
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex")
						.toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString()
						.trim());
			}
			response.setContentType("text/xml");
			String st = getListMinibar(index, page);
			out.print(st);
			break;
		}
		case 11://
		{
			System.out.println("Show form add item minibar");
			request.getRequestDispatcher("/pmsMng/keeping/detailItemMinibar.jsp")
					.forward(request, response);
			break;
		}
		case 12://
		{
			System.out.println("Delete item minibar");
			int id = -1;
			String name = "";
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString().trim();
			}
			boolean flag = houseKeepingDao.deleteItemMiniBar(id);
			response.setContentType("text/xml");
			if (flag) {
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Delete item minibar \"" + name + "\"", user);
				out.print("t");
			} else
				out.print("f");
			break;
		}
		case 13://
		{
			System.out.println("Change status item minibar");
			int id = -1;
			String name = "";
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString().trim();
			}
			boolean flag = houseKeepingDao.changeStatusItemMiniBar(id);
			response.setContentType("text/xml");
			if (flag) {
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Change status item minibar \"" + name + "\"", user);
				out.print("t");
			} else
				out.print("f");
			break;
		}
		case 14://
		{
			System.out.println("Get list room status");
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex")
						.toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString()
						.trim());
			}
			response.setContentType("text/xml");
			String st = getListRoomStatus(index, page);
			out.print(st);
			break;
		}
		case 15://
		{
			System.out.println("Show form add item minibar");
			request.getRequestDispatcher("/pmsMng/keeping/detailRoomStatus.jsp")
					.forward(request, response);
			break;
		}
		case 16://
		{
			System.out.println("Delete room status");
			int id = -1;
			String name = "";
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString().trim();
			}
			boolean flag = houseKeepingDao.deleteRoomStatus(id);
			response.setContentType("text/xml");
			if (flag) {
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Delete room status \"" + name + "\"", user);
				out.print("t");
			} else
				out.print("f");
			break;
		}
		case 17://
		{
			System.out.println("Change status room status");
			int id = -1;
			String name = "";
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString().trim();
			}
			boolean flag = houseKeepingDao.changeStatusRoomStatus(id);
			response.setContentType("text/xml");
			if (flag) {
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Change status room status \"" + name + "\"", user);
				out.print("t");
			} else
				out.print("f");
			break;
		}
		default:
			break;
		}
	}

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
		case 1: {
			System.out.println("Insert item housekeeping");
			String name = "";
			String image = "";
			String price = "";
			int subid = -1;
			String unit = "";
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			if (request.getParameter("price") != null) {
				price = request.getParameter("price").toString().trim();
			}
			if (request.getParameter("subid") != null) {
				subid = Integer.parseInt(request.getParameter("subid")
						.toString().trim());
			}
			if (request.getParameter("unit") != null) {
				unit = request.getParameter("unit").toString().trim();
			}
			HousekeepingPMS dinning = new HousekeepingPMS();
			eHousekeeping item = new eHousekeeping();
			item.setPrice(price);
			item.setName(name);
			item.setUrlImage(config._housekeeping + "/" + image);
			item.setIunit(unit);
			int t = dinning.addItem(subid, item);
			if (t > 0) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._housekeeping + "/"
						+ image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 2: {
			System.out.println("update item housekeeping");
			String name = "";
			String image = "";
			String price = "";
			String unit = "";
			int id = -1;
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			if (request.getParameter("price") != null) {
				price = request.getParameter("price").toString().trim();
			}
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			if (request.getParameter("unit") != null) {
				unit = request.getParameter("unit").toString().trim();
			}
			HousekeepingPMS dinning = new HousekeepingPMS();
			eHousekeeping item = new eHousekeeping();
			item.setPrice(price);
			item.setName(name);
			item.setUrlImage(config._housekeeping + "/" + image);
			item.setId(id);
			item.setIunit(unit);
			boolean t = dinning.editItem(item, LangID);
			if (t) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._housekeeping + "/"
						+ image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 3:// update subject
		{
			System.out.println("update subject PMS!");
			response.setContentType("text/xml");
			String subjectName = "";
			String image = "";
			String urlBg = "";
//			int parent = -1;
			int subid = -1;
			if (request.getParameter("image1") != null) {
				image = request.getParameter("image1").toString();
			}
			if (request.getParameter("image2") != null) {
				urlBg = request.getParameter("image2").toString();
			}
			if (request.getParameter("SubId") != null) {
				subid = Integer.parseInt(request.getParameter("SubId")
						.toString().trim());
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();
				HousekeepingPMS hotel = new HousekeepingPMS();
				eMenu menu = new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(config._housekeeping + "/" + image);
				menu.setUrlBg(config._housekeeping + "/" + urlBg);
				menu.setMenuId(subid);
				boolean id = hotel.editMenuHousekeeping(menu, LangID);
				if (id) {
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + "/" + image;
					String path2 = config._pathImage + config._housekeeping
							+ "/" + image;
					file.copy(path1, path2);
					file.deletefile(path1);
					path1 = config._temp + "/" + urlBg;
					path2 = config._pathImage + config._housekeeping + "/"
							+ urlBg;
					file.copy(path1, path2);
					file.deletefile(path1);
				}
			}
			break;
		}
		// /////////////////////////////////////////
		// Phan housekeeping cho sokha siem reap //
		// ////////////////////////////////////////
		case 4: {
			int kq = addUser(request, response);
			if (kq > 0)
				out.print("t");
			else
				out.print("f");
			break;
		}
		case 5: {
			int kq = editUser(request, response);
			if (kq > 0)
				out.print("t");
			else
				out.print("f");
			break;
		}
		case 6: {
			int kq = addMinibar(request, response);
			if (kq > 0)
				out.print("t");
			else
				out.print("f");
			break;
		}
		case 7: {
			int kq = editMinibar(request, response);
			if (kq > 0)
				out.print("t");
			else
				out.print("f");
			break;
		}
		case 8: {
			int kq = addRoomStatus(request, response);
			if (kq > 0)
				out.print("t");
			else
				out.print("f");
			break;
		}
		case 9: {
			int kq = editRoomStatus(request, response);
			if (kq > 0)
				out.print("t");
			else
				out.print("f");
			break;
		}
		default:
			break;
		}
	}

	private String getmenu() {
		HousekeepingPMS hotel = new HousekeepingPMS();
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

	private String getItem(int id) {
		HousekeepingPMS keeping = new HousekeepingPMS();
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		Vector<eHousekeeping> v_rs = keeping.getItems(id, LangID, -1, -1);
		for (int i = 0; i < v_rs.size(); i++) {
			eHousekeeping item = v_rs.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>";
			mData += "<Def>";
			if (item.getDef() == null) {
				mData += "<![CDATA[]]>";
			} else
				mData += "<![CDATA[" + item.getDef() + "]]>";
			mData += "</Def>";
			mData += "<status>";
			mData += item.getInvisible();
			mData += "</status>";
			mData += "<image>";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<price>";
			mData += item.getPrice();
			mData += "</price>";
			mData += "<unit>";
			mData += item.getIunit();
			mData += "</unit>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	public void init() throws ServletException {
	}

	// /////////////////////////////////////////
	// Phan housekeeping cho sokha siem reap //
	// ////////////////////////////////////////
	private String getListUserHouseKeeping(int index, int page) {
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		List<UserHouseKeepingModel> list = houseKeepingDao
				.getListUserHouseKeeping(fr, to);
		int count = houseKeepingDao.countUserHouseKeeping();
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">\n";
		for (int i = 0; i < list.size(); i++) {
			UserHouseKeepingModel item = list.get(i);
			mData += "<Item>";
			mData += "<username>";
			mData += "<![CDATA[" + item.getUsername() + "]]>";
			mData += "</username>";
			mData += "<id>";
			mData += item.getId();
			mData += "</id>";
			mData += "<pass>";
			mData += "<![CDATA[" + item.getPassword() + "]]>";
			mData += "</pass>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getStatus() + "]]>";
			mData += "</status>";
			mData += "<date>";
			mData += "<![CDATA[" + item.getModifydate() + "]]>";
			mData += "</date>";
			mData += "<address>";
			mData += "<![CDATA[" + item.getAddress() + "]]>";
			mData += "</address>";
			mData += "<usermodify>";
			mData += "<![CDATA[" + item.getUsermodify() + "]]>";
			mData += "</usermodify>";
			mData += "<fullname>";
			mData += "<![CDATA[" + item.getFullname() + "]]>";
			mData += "</fullname>";
			mData += "</Item>\n";
		}
		mData += "</xml>";
		return mData;
	}

	private int addUser(HttpServletRequest request, HttpServletResponse response) {
		int iduser = -1;
		String account = "";
		String password = "";
		String name = "";
		if (request.getParameter("username") != null) {
			account = request.getParameter("username").toString().trim();
		}
		if (request.getParameter("pass") != null) {
			password = request.getParameter("pass").toString().trim();
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		HttpSession session = request.getSession(true);
		String user = "";
		user = session.getAttribute("user").toString();
		iduser = houseKeepingDao.addUserHousekeeping(account, password, name,
				user);
		ReportServiceDBI.addUserActivities(
				"Create user housekeeping with account \"" + account + "\"",
				user);
		return iduser;
	}

	private int editUser(HttpServletRequest request,
			HttpServletResponse response) {
		int iduser = -1;
		int id = -1;
		String account = "";
		String password = "";
		String name = "";

		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id").toString().trim());
		}
		if (request.getParameter("username") != null) {
			account = request.getParameter("username").toString().trim();
		}
		if (request.getParameter("pass") != null) {
			password = request.getParameter("pass").toString().trim();
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		HttpSession session = request.getSession(true);
		String user = "";
		user = session.getAttribute("user").toString();
		iduser = houseKeepingDao.editUserHousekeeping(id, account, password,
				name, user);
		ReportServiceDBI
				.addUserActivities("Edit user housekeeping with account \""
						+ account + "\"", user);
		return iduser;
	}
	
	private String getListMinibar(int index, int page) {
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		List<MinibarModel> list = houseKeepingDao
				.getListMinibar(fr, to);
		int count = houseKeepingDao.countMinibar();
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">\n";
		for (int i = 0; i < list.size(); i++) {
			MinibarModel item = list.get(i);
			mData += "<Item>";
			mData += "<id>";
			mData += item.getId();
			mData += "</id>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<code>";
			mData += "<![CDATA[" + item.getCode()+ "]]>";
			mData += "</code>";
			mData += "<amount>";
			mData += "<![CDATA[" + item.getAmount() + "]]>";
			mData += "</amount>";
			mData += "<price>";
			mData += "<![CDATA[" + item.getPrice() + "]]>";
			mData += "</price>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getStatus() + "]]>";
			mData += "</status>";
			mData += "<date>";
			mData += "<![CDATA[" + item.getModifydate() + "]]>";
			mData += "</date>";
			mData += "<usermodify>";
			mData += "<![CDATA[" + item.getUsermodify() + "]]>";
			mData += "</usermodify>";
			mData += "</Item>\n";
		}
		mData += "</xml>";
		return mData;
	}
	
	private int addMinibar(HttpServletRequest request, HttpServletResponse response) {
		int id = -1;
		String name = "";
		String code = "";
		String amount = "";
		String price = "";
		
		if (request.getParameter("amount") != null) {
			amount = request.getParameter("amount").toString().trim();
		}
		if (request.getParameter("price") != null) {
			price = request.getParameter("price").toString().trim();
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		if (request.getParameter("code") != null) {
			code = request.getParameter("code").toString().trim();
		}
		HttpSession session = request.getSession(true);
		String user = "";
		user = session.getAttribute("user").toString();
		id = houseKeepingDao.addItemMinibar(name, code, amount, price, user);
		ReportServiceDBI.addUserActivities(
				"Create item minibar \"" + name + "\"",
				user);
		return id;
	}
	
	private int editMinibar(HttpServletRequest request,
			HttpServletResponse response) {
		int idItem = -1;
		int id = -1;
		String name = "";
		String amount = "";
		String price = "";
		String code = "";
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id").toString().trim());
		}
		if (request.getParameter("amount") != null) {
			amount = request.getParameter("amount").toString().trim();
		}
		if (request.getParameter("price") != null) {
			price = request.getParameter("price").toString().trim();
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		if (request.getParameter("code") != null) {
			code = request.getParameter("code").toString().trim();
		}
		HttpSession session = request.getSession(true);
		String user = "";
		user = session.getAttribute("user").toString();
		idItem = houseKeepingDao.editItemMinibar(id, name, code, amount, price, user);
		ReportServiceDBI
				.addUserActivities("Edit item minibar \"" + name + "\"", user);
		return idItem;
	}
	
	private String getListRoomStatus(int index, int page) {
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		List<RoomStatusModel> list = houseKeepingDao
				.getListRoomStatus(fr, to);
		int count = houseKeepingDao.countRoomStatus();
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">\n";
		for (int i = 0; i < list.size(); i++) {
			RoomStatusModel item = list.get(i);
			mData += "<Item>";
			mData += "<id>";
			mData += item.getId();
			mData += "</id>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getStatusName() + "]]>";
			mData += "</name>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getInvisible() + "]]>";
			mData += "</status>";
			mData += "<date>";
			mData += "<![CDATA[" + item.getModifyDate() + "]]>";
			mData += "</date>";
			mData += "<user>";
			mData += "<![CDATA[" + item.getUserModify() + "]]>";
			mData += "</user>";
			mData += "</Item>\n";
		}
		mData += "</xml>";
		return mData;
	}
	
	private int addRoomStatus(HttpServletRequest request, HttpServletResponse response) {
		int id = -1;
		String name = "";
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		HttpSession session = request.getSession(true);
		String user = "";
		user = session.getAttribute("user").toString();
		id = houseKeepingDao.addRoomStatus(name, user);
		ReportServiceDBI.addUserActivities("Create room status \"" + name + "\"", user);
		return id;
	}
	
	private int editRoomStatus(HttpServletRequest request, HttpServletResponse response) {
		int idItem = -1;
		int id = -1;
		String name = "";
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id").toString().trim());
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		HttpSession session = request.getSession(true);
		String user = "";
		user = session.getAttribute("user").toString();
		idItem = houseKeepingDao.editRoomStatus(id, name, user);
		ReportServiceDBI.addUserActivities("Edit room status \"" + name + "\"", user);
		return idItem;
	}
	
}

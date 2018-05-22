package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.abs.pms.DiningPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.model.PMSMainMenu;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.TransferImageFLC;
import ehotel.admin.util.UtilString;
import ehotel.domain.pms.eItem;
import ehotel.domain.pms.eMenu;
import ehotel.domain.pms.eRestaurant;

public class ServiceDinning extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private PMSServiceDBI pmsServiceDBI = new PMSServiceDBI();
	private TransferImageFLC transferImageFLC = new TransferImageFLC();

	public ServiceDinning() {
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
			request.setAttribute("fileJSP", "../pmsMng/Dinning/Dinning.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");

			break;
		}
		case 1:// get menu
		{
			System.out.println("Get subject Ctn");
			response.setContentType("text/xml");
			String st = getmenu();
			System.out.println(st);
			out.print(st);
			break;
		}
		case 2:// get item roomservice
		{
			System.out.println("Get ");
			response.setContentType("text/xml");
			int subId = -1;
			if (request.getParameter("SubId") != null) {
				subId = Integer.parseInt(request.getParameter("SubId")
						.toString().trim());
			}
			String st = getItemRoomService(subId);
			out.print(st);
			break;
		}
		case 3:// delete subject
		{
			System.out.println("Delete subject dinning");
			int id = -1;
			if (request.getParameter("SubId") != null) {
				id = Integer.parseInt(request.getParameter("SubId").toString()
						.trim());
			}
			DiningPMS ding = new DiningPMS();
			ding.removeDiningMenu(id);
			break;
		}
		case 4: {
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			DiningPMS ding = new DiningPMS();
			eItem item = new eItem();
			item = ding.getRoomSvcItemInfo(id, LangID);
			// if(id!=-1)
			// loai bo vi -1 ko bo qua, chi bo qua neu nhu do la subject
			// 16.03.13
			{
				request.setAttribute("Item", item);
			}
			this.showJSPpage(request, response,
					"/pmsMng/Dinning/detaiDinning.jsp");
			break;
		}
		case 5:// delete item
		{
			System.out.println("Delete item dinning");
			Vector<Integer> list = new Vector();
			int i = 0;
			while (request.getParameter("id" + i) != null) {
				int subid = Integer.parseInt(request.getParameter("id" + i)
						.toString().trim());
				list.add(subid);
				i++;
			}
			DiningPMS ding = new DiningPMS();
			for (i = 0; i < list.size(); i++) {
				System.out.println("ID:" + list.get(i));
				ding.removeRoomSvcItem(list.get(i));
			}
			break;
		}
		case 6:// get item restaurance
		{
			System.out.println("Get item restaurance");
			response.setContentType("text/xml");
			int subId = -1;
			if (request.getParameter("SubId") != null) {
				subId = Integer.parseInt(request.getParameter("SubId")
						.toString().trim());
			}
			String st = getItemRestaurant(subId);
			out.print(st);
			break;
		}
		case 7:// detail restaurant
		{
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			DiningPMS ding = new DiningPMS();
			eRestaurant item = new eRestaurant();
			item = ding.getResItemInfo(id, LangID);
			request.setAttribute("Item", item);
			this.showJSPpage(request, response,
					"/pmsMng/Dinning/detaiRestaurant.jsp");
			break;
		}
		case 9:// break;
		{
			System.out.println("Delete item dinning");
			Vector<Integer> list = new Vector();
			int i = 0;
			while (request.getParameter("id" + i) != null) {
				int subid = Integer.parseInt(request.getParameter("id" + i)
						.toString().trim());
				list.add(subid);
				i++;
			}
			DiningPMS ding = new DiningPMS();
			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1) + ")";
			ding.removeRestaurantItem(param);
			break;
		}
		case 10: {
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			System.out.println("Change status res=" + id);
			DiningPMS ding = new DiningPMS();
			boolean t = ding.changeStatus(id);
			break;
		}
		case 11: {
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			System.out.println("Change status din=" + id);
			DiningPMS ding = new DiningPMS();
			boolean t = ding.changeItemStatus(id);
			break;
		}
		case 12:// change status invisible dining menu
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
				flag = pmsServiceDBI.updateInvisibleDiningMenu(subId, 1);
			else if(invisible == 1)
				flag = pmsServiceDBI.updateInvisibleDiningMenu(subId, 0);
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

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
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
		case 1:// insert new subject
		{
			System.out.println("insert new subject dining");
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
			if (request.getParameter("id") != null) {
				parent = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();

				DiningPMS dinning = new DiningPMS();
				eMenu menu = new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(config._dining + "/" + image);
				menu.setUrlBg(config._dining + "/" + urlBg);
				int id = dinning.addDiningMenu(parent, menu);
				if (id > 0) {
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + "/" + image;
					String path2 = config._pathImage + config._dining + "/"
							+ image;
					file.copy(path1, path2);
					transferImageFLC.transferImageMain(path1, config._dining);
					file.deletefile(path1);
					path1 = config._temp + "/" + urlBg;
					path2 = config._pathImage + config._dining + "/" + urlBg;
					file.copy(path1, path2);
					transferImageFLC.transferImageMain(path1, config._dining);
					file.deletefile(path1);
				}
				out.write(id);
			}
			break;
		}
		case 2:// update
		{
			System.out.println("Upadte subject dinning");
			response.setContentType("text/xml");
			String subjectName = "";
			String image = "";
			String urlBg = "";
			int id = -1;
			if (request.getParameter("image1") != null) {
				image = request.getParameter("image1").toString();
			}
			if (request.getParameter("image2") != null) {
				urlBg = request.getParameter("image2").toString();
			}
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();

				DiningPMS dinning = new DiningPMS();
				eMenu menu = new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(config._dining + "/" + image);
				menu.setUrlBg(config._dining + "/" + urlBg);
				menu.setMenuId(id);
				boolean t = dinning.editDiningMenu(menu, LangID);
				/**
				 * copy
				 */
				if (t) {
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + "/" + image;
					String path2 = config._pathImage + config._dining + "/"
							+ image;
					file.copy(path1, path2);
					transferImageFLC.transferImageMain(path1, config._dining);
					file.deletefile(path1);
					path1 = config._temp + "/" + urlBg;
					path2 = config._pathImage + config._dining + "/" + urlBg;
					file.copy(path1, path2);
					transferImageFLC.transferImageMain(path1, config._dining);
					file.deletefile(path1);
				}
			}
			break;
		}
		case 3:// insert item restaurant
		{
			System.out.println("Insert item reataurant");
			String name = "";
			String currency = "";
			String currency_small = "";
			String price_large = "";
			String unit = "";
			int subid = -1;
			String image = "";
			String def = "";
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("price") != null) {
				currency = request.getParameter("price").toString().trim();
			}
			if (request.getParameter("price_small") != null) {
				currency_small = request.getParameter("price_small").toString()
						.trim();
			}
			if (request.getParameter("price_large") != null) {
				price_large = request.getParameter("price_large").toString()
						.trim();
			}
			if (request.getParameter("unit") != null) {
				unit = request.getParameter("unit").toString();
			}
			// if(request.getParameter("subid")!=null)
			// {
			// subid=Integer.parseInt(request.getParameter("subid").toString().trim());
			// }
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			if (request.getParameter("des") != null) {
				def = request.getParameter("des").toString().trim();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll(
						"<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			System.out
					.println("Before insert subject hiden = = = = = = = = = = =");
			subid = intsertSubjectDining(name, image, image, -1);
			System.out.println("Subject hiden = = = = = = = = = " + subid);

			eItem item = new eItem();
			item.setName(name);
			item.setCurrency(currency);
			item.setCurrency_large(price_large);
			item.setCurrency_small(currency_small);
			item.setIUnit(unit);
			item.setUrlImage(config._dining + "/" + image);
			item.setDef(def);
			DiningPMS dinning = new DiningPMS();
			int t = dinning.addRoomSvcItem(item, subid);
			if (t > 0) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._dining + "/" + image;
				file.copy(path1, path2);
				transferImageFLC.transferImageMain(path1, config._dining);
				file.deletefile(path1);
			}
			break;
		}
		case 4://
		{
			System.out.println("update item dinning");
			String name = "";
			String currency = "";
			String currency_small = "";
			String price_large = "";
			String unit = "";
			String image = "";
			String def = "";
			int id = -1;
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("price") != null) {
				currency = request.getParameter("price").toString().trim();
			}
			if (request.getParameter("price_small") != null) {
				currency_small = request.getParameter("price_small").toString()
						.trim();
			}
			if (request.getParameter("price_large") != null) {
				price_large = request.getParameter("price_large").toString()
						.trim();
			}
			if (request.getParameter("unit") != null) {
				unit = request.getParameter("unit").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			if (request.getParameter("des") != null) {
				def = request.getParameter("des").toString().trim();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll(
						"<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			System.out.println("Unit:" + unit);
			eItem item = new eItem();
			item.setName(name);
			item.setCurrency(currency);
			item.setCurrency_large(price_large);
			item.setCurrency_small(currency_small);
			item.setIUnit(unit);
			item.setUrlImage(config._dining + "/" + image);
			item.setDef(def);
			item.setICode(id);
			DiningPMS dinning = new DiningPMS();
			boolean t = dinning.editRoomSvcItem(item, LangID);
			// if(t)
			{
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._dining + "/" + image;
				file.copy(path1, path2);
				transferImageFLC.transferImageMain(path1, config._dining);
				file.deletefile(path1);
			}
			break;
		}
		case 5:// insert item restaurant
		{
			System.out.println("Insert item restaurant");
			String name = "";
			String image = "";
			String def = "";
			int subid = -1;
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			if (request.getParameter("des") != null) {
				def = request.getParameter("des").toString().trim();
			}
			if (request.getParameter("subid") != null) {
				subid = Integer.parseInt(request.getParameter("subid")
						.toString().trim());
			}

			int id = intsertSubjectDining(name, image, image, subid);

			def = def.replaceAll("<strong>", "<b>");
			def = def.replaceAll("</strong>", "</b>");
			def = def.replaceAll("<em>", "<i>");
			def = def.replaceAll("</em>", "</i>");
			def = def.replaceAll(
					"<span style=\"text-decoration: underline;\">", "<u>");
			def = def.replaceAll("</span>", "</u>");
			DiningPMS dinning = new DiningPMS();
			eRestaurant item = new eRestaurant();
			item.setDef(def);
			item.setName(name);
			item.setUrlImage(config._dining + "/" + image);
			int t = dinning.addRestaurantItem(item, id);
			if (t > 0) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._dining + "/" + image;
				file.copy(path1, path2);
				transferImageFLC.transferImageMain(path1, config._dining);
				file.deletefile(path1);
			}
			break;
		}
		case 6: {
			System.out.println("update item restaurant");
			String name = "";
			String image = "";
			String def = "";
			int id = -1;
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			if (request.getParameter("des") != null) {
				def = request.getParameter("des").toString().trim();
			}
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			def = def.replaceAll("<strong>", "<b>");
			def = def.replaceAll("</strong>", "</b>");
			def = def.replaceAll("<em>", "<i>");
			def = def.replaceAll("</em>", "</i>");
			def = def.replaceAll(
					"<span style=\"text-decoration: underline;\">", "<u>");
			def = def.replaceAll("</span>", "</u>");
			DiningPMS dinning = new DiningPMS();
			eRestaurant item = new eRestaurant();
			item.setDef(def);
			item.setName(name);
			item.setUrlImage(config._dining + "/" + image);
			item.setId(id);
			boolean b = dinning.editRestaurantItem(item, LangID);
			if (b) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._dining + "/" + image;
				file.copy(path1, path2);
				transferImageFLC.transferImageMain(path1, config._dining);
				file.deletefile(path1);
			}
			break;
		}
		case 7:// insert item restaurant
		{
			System.out.println("Insert item Dining");
			int menuid = -1;
			String name = "";
			String def = "";
			String price = "";
			String price_large = "";
			String price_small = "";
			String url = "";
			String unit = "";
			menuid = Integer.parseInt(request.getParameter("subid"));
			name = request.getParameter("name");
			def = request.getParameter("des");
			def = def.replaceAll("<strong>", "<b>");
			def = def.replaceAll("</strong>", "</b>");
			def = def.replaceAll("<em>", "<i>");
			def = def.replaceAll("</em>", "</i>");
			def = def.replaceAll(
					"<span style=\"text-decoration: underline;\">", "<u>");
			def = def.replaceAll("</span>", "</u>");
			price = request.getParameter("price");
			price_large = request.getParameter("price_large");
			price_small = request.getParameter("price_small");
			url = request.getParameter("image");
			url = config._dining +"/"+ url;
			unit = request.getParameter("unit");
			PMSServiceDBI pmsServiceDBI = new PMSServiceDBI();
			int t = pmsServiceDBI.addItemDining(menuid, name, def, price,
					price_large, price_small, url, unit);
			if (t > 0) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + url;
				String path2 = config._pathImage + config._dining + "/" + url;
				file.copy(path1, path2);
				transferImageFLC.transferImageMain(path1, config._dining);
				file.deletefile(path1);
			}
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
	}

	private String getmenu() {
		DiningPMS hotel = new DiningPMS();
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
			mData += "<type>";
			if (item.getType().equals("RESTAURANT")) {
				mData += 0;
			}
			if (item.getType().equals("ROOMSERVICE")) {
				mData += 1;
			}
			mData += "</type>";
			mData += "<level>";
			mData += 0;
			mData += "</level>";
			mData += "<invisible>";
			mData += 1;
			mData += "</invisible>";
			mData += "</Item>";
			if (item.getType().equals("RESTAURANT")) {
				// mData += getsubmenu1(item.getMenuId());
				mData += getRestaurantMenu(item.getMenuId());
			}
			if (item.getType().equals("ROOMSERVICE")) {
				// mData += getsubmenu2(item.getMenuId());
				mData += getInRoomMenu(item.getMenuId());
			}
		}
		mData += "</xml>";
		return mData;
	}

	private String getRestaurantMenu(int menuid) {
		List<PMSMainMenu> list = new ArrayList<PMSMainMenu>();
		list = pmsServiceDBI.getMenuDining(menuid, LangID);
		String mData = "";
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
			mData += menuid;
			mData += "</parent>";
			mData += "<type>";
			mData += 0;
			mData += "</type>";
			mData += "<level>";
			mData += 1;
			mData += "</level>";
			mData += "<invisible>";
			mData += list.get(i).getMenuinvisible();
			mData += "</invisible>";
			mData += "</Item>";
		}
		return mData;
	}

	private String getInRoomMenu(int menuid) {
		List<PMSMainMenu> list = new ArrayList<PMSMainMenu>();
		list = pmsServiceDBI.getMenuDining(menuid, LangID);
		String mData = "";
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
			mData += menuid;
			mData += "</parent>";
			mData += "<type>";
			mData += 1;
			mData += "</type>";
			mData += "<level>";
			mData += 0;
			mData += "</level>";
			mData += "<invisible>";
			mData += list.get(i).getMenuinvisible();
			mData += "</invisible>";
			mData += "</Item>";
			List<PMSMainMenu> item = new ArrayList<PMSMainMenu>();
			int id = Integer.parseInt(list.get(i).getMenuid());
			item = pmsServiceDBI.getMenuDining(id, LangID);
			for (int j = 0; j < item.size(); j++) {
				mData += "<Item>";
				mData += "<name>";
				mData += "<![CDATA[" + item.get(j).getMenuname() + "]]>";
				mData += "</name>";
				mData += "<id>\n";
				mData += item.get(j).getMenuid();
				mData += "</id>\n";
				mData += "<image>\n";
				mData += item.get(j).getMenuimage();
				mData += "</image>";
				mData += "<imagebg>\n";
				mData += item.get(j).getMenubackground();
				mData += "</imagebg>";
				mData += "<parent>";
				mData += id;
				mData += "</parent>";
				mData += "<type>";
				mData += 1;
				mData += "</type>";
				mData += "<level>";
				mData += 1;
				mData += "</level>";
				mData += "<invisible>";
				mData += item.get(j).getMenuinvisible();
				mData += "</invisible>";
				mData += "</Item>";
			}
		}
		return mData;
	}

	private String getsubmenu1(int menuid) {
		DiningPMS hotel = new DiningPMS();
		String mData = "";
		Vector<eMenu> menu = hotel.getSubMenus(menuid, LangID);
		for (int i = 0; i < menu.size(); i++) {
			eMenu item = menu.get(i);
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
			mData += menuid;
			mData += "</parent>";
			mData += "<type>";
			mData += 0;
			mData += "</type>";
			mData += "<level>";
			mData += 1;
			mData += "</level>";
			mData += "</Item>";
			// Vector<eMenu> submenu = new Vector<eMenu>();
			// submenu = hotel.getSubMenus(item.getMenuId(), LangID);
			// for (int j = 0; j < submenu.size(); j++) {
			// eMenu item1 = submenu.get(j);
			// mData += "<Item>";
			// mData += "<name>";
			// mData += "<![CDATA[" + item1.getMenuName() + "]]>";
			// mData += "</name>";
			// mData += "<id>\n";
			// mData += item1.getMenuId();
			// mData += "</id>\n";
			// mData += "<image>\n";
			// mData += item1.getUrlImage();
			// mData += "</image>";
			// mData += "<imagebg>\n";
			// mData += item1.getUrlBg();
			// mData += "</imagebg>";
			// mData += "<parent>";
			// mData += item.getMenuId();
			// mData += "</parent>";
			// mData += "<type>";
			// mData += 0;
			// mData += "</type>";
			// mData += "<level>";
			// mData += 1;
			// mData += "</level>";
			// mData += "</Item>";
			// }
		}
		return mData;
	}

	private String getsubmenu2(int menuid) {
		DiningPMS hotel = new DiningPMS();
		String mData = "";
		Vector<eMenu> subject = hotel.getSubMenus(menuid, LangID);
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
			mData += menuid;
			mData += "</parent>";
			mData += "<type>";
			mData += 1;
			mData += "</type>";
			mData += "<level>";
			mData += 0;
			mData += "</level>";
			mData += "</Item>";
			Vector<eMenu> submenu = new Vector<eMenu>();
			submenu = hotel.getSubMenus(item.getMenuId(), LangID);
			for (int j = 0; j < submenu.size(); j++) {
				eMenu item1 = submenu.get(j);
				mData += "<Item>";
				mData += "<name>";
				mData += "<![CDATA[" + item1.getMenuName() + "]]>";
				mData += "</name>";
				mData += "<id>\n";
				mData += item1.getMenuId();
				mData += "</id>\n";
				mData += "<image>\n";
				mData += item1.getUrlImage();
				mData += "</image>";
				mData += "<imagebg>\n";
				mData += item1.getUrlBg();
				mData += "</imagebg>";
				mData += "<parent>";
				mData += item.getMenuId();
				mData += "</parent>";
				mData += "<type>";
				mData += 1;
				mData += "</type>";
				mData += "<level>";
				mData += 1;
				mData += "</level>";
				mData += "</Item>";
			}
		}
		return mData;
	}

	private String getItemRoomService(int id) {
		DiningPMS dinning = new DiningPMS();
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		Vector<eItem> v_rs = dinning.getRoomSvcItems(id, LangID, -1, -1);
		for (int i = 0; i < v_rs.size(); i++) {
			eItem item = v_rs.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getICode();
			mData += "</id>";
			mData += "<Def>";
			if (item.getDef() == null) {
				mData += "<![CDATA[]]>";
			} else
				mData += "<![CDATA[" + UtilString.converString(item.getDef())
						+ "]]>";
			mData += "</Def>";
			mData += "<status>";
			mData += item.getInvisible();
			mData += "</status>";
			mData += "<image>";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<price>";
			if (item.getCurrency() != null) {
				mData += item.getCurrency();
			} else {
				mData += 0;
			}
			mData += "</price>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getItemRestaurant(int id) {
		DiningPMS dinning = new DiningPMS();
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		Vector<eRestaurant> v_rs = dinning.getRestaurantItems(id, LangID, -1,
				-1);
		for (int i = 0; i < v_rs.size(); i++) {
			eRestaurant item = v_rs.get(i);
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
				mData += "<![CDATA[" + UtilString.converString(item.getDef())
						+ "]]>";
			mData += "</Def>";
			mData += "<status>";
			mData += item.getInvisible();
			mData += "</status>";
			mData += "<image>";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	public int intsertSubjectDining(String subjectName, String image,
			String urlBg, int parent) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();

		DiningPMS dinning = new DiningPMS();
		eMenu menu = new eMenu();
		menu.setMenuName(subjectName);
		menu.setUrlImage(config._dining + "/" + image);
		menu.setUrlBg(config._dining + "/" + urlBg);
		int id = dinning.addDiningMenu(parent, menu);
		if (id > 0) {
			ManagerFile file = new ManagerFile();
			String path1 = config._temp + "/" + image;
			String path2 = config._pathImage + config._dining + "/" + image;
			file.copy(path1, path2);
			transferImageFLC.transferImageMain(path1, config._dining);
			file.deletefile(path1);
			path1 = config._temp + "/" + urlBg;
			path2 = config._pathImage + config._dining + "/" + urlBg;
			file.copy(path1, path2);
			transferImageFLC.transferImageMain(path1, config._dining);
			file.deletefile(path1);
		}
		return id;
	}

	public static void main(String[] args) {
		ServiceDinning s = new ServiceDinning();
		System.out.println(s.getmenu());
	}
}
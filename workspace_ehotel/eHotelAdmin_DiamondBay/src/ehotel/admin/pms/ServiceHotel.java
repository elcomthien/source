package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.HotelInfoPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.FolioServiceDBI;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.model.Message_Detail;
import ehotel.admin.model.PMSMainMenu;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.UtilString;
import ehotel.domain.pms.eImage;
import ehotel.domain.pms.eMenu;

import com.elcom.eod.util.UnicodeConverter;

public class ServiceHotel extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private FolioServiceDBI folioServiceDBI = new FolioServiceDBI();
	private PMSServiceDBI pmsServiceDBI = new PMSServiceDBI();
	public ServiceHotel() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				menuid = Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);
			request.setAttribute("fileJSP", "../pmsMng/hotel/pmsHotel.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1:// list subject
		{
			System.out.println("Get subject Ctn");
			response.setContentType("text/xml");
//			String st = getsub();
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
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			response.setContentType("text/xml");
			String st = getContentHotel(id, index, page);
			out.print(st);
			break;
		}
		case 3:// delete subject
		{
			System.out.println("DELETE SUBJECT PMS");
			HotelInfoPMS hotel = new HotelInfoPMS();
			int subid = -1;
			if (request.getParameter("SubId") != null) {
				subid = Integer.parseInt(request.getParameter("SubId").toString());
			}
			boolean b = hotel.removeHotelMenu(subid);
			System.out.println("Delete :" + b);
			break;
		}
		case 4:// show form detail
		{
			System.out.println("Show form detail");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString());
			}
			HotelInfoPMS hotel = new HotelInfoPMS();
			eImage item = null;
			item = hotel.getItemInfo(id, LangID);
			request.setAttribute("eImage", item);
			this.showJSPpage(request, response, "/pmsMng/hotel/detailHotel.jsp");
			break;
		}
		case 5:// list subject left
		{
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			response.setContentType("text/xml");
			String str = getSuboutHotel(id);
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
			String str = getSubinHotel(id);
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
		case 8:// delete item hotel
		{
			Vector<Integer> list = new Vector<Integer>();
			int i = 0;
			int subId = -1;
			while (request.getParameter("id" + i) != null) {
				int subid = Integer.parseInt(request.getParameter("id" + i).toString().trim());
				list.add(subid);
				i++;
			}
			if (request.getParameter("SubId") != null) {
				subId = Integer.parseInt(request.getParameter("SubId").toString().trim());
			}

			HotelInfoPMS hotel = new HotelInfoPMS();
			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1) + ")";
			System.out.println(param);
			hotel.removeItem(param);
			break;
		}
		case 9:// change status
		{
			System.out.println("change status hotel");
			int id = -1;
			HotelInfoPMS hotel = new HotelInfoPMS();
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			hotel.changeStatus(id);
			break;
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
		case 11:{
			System.out.println("Show form Edit Message");
			Message_Detail listmg = new Message_Detail();
			int idmessage = Integer.parseInt(request.getParameter("idmessage").trim()); //id của message
			int folionum = Integer.parseInt(request.getParameter("folionum").trim());//id của room
			listmg =folioServiceDBI.getListMessage_Detail(folionum, idmessage, LangID);
			request.setAttribute("eImage", listmg);
			this.showJSPpage(request, response, "/pmsMng/folio/formMessage_Update.jsp");
			break;
		}
		case 12:{
			//response.setContentType("text/xml");
			System.out.println("Get Message detail");
			Message_Detail mg= new Message_Detail();
			List<Message_Detail> listmg = new ArrayList<Message_Detail>();
			int idmessage = Integer.parseInt(request.getParameter("idmessage").trim()); //id của message
			int folionum = Integer.parseInt(request.getParameter("folionum").trim());//id của room
			//listmg =folioServiceDBI.getListMessage_Detail(folionum, idmessage, LangID);
			response.setContentType("text/xml");
			String st = getMessage_Detail(listmg);
			out.print(st);
			
	/*		for(int i=0;i<listmg.size();i++){
				mg=listmg.get(i);
				out.println( mg.getId_room());
				out.println(mg.getId_message());
				out.println(mg.getSubject());
				out.println(mg.getSender());
				out.println(UnicodeConverter.decodeUnicode(mg.getContent()));
			}*/
			break;
		}
		default:
			break;
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to post.
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
//				HotelInfoPMS hotel = new HotelInfoPMS();
//				eMenu menu = new eMenu();
//				menu.setMenuName(subjectName);
//				menu.setUrlImage(config._hotel + "/" + image);
//				menu.setUrlBg(config._hotel + "/" + urlBg);
				boolean flag = pmsServiceDBI.addSubjectHotel(subjectName, 33, 1, config._hotel + "/" + image, config._hotel + "/" + urlBg);
				if (flag) {
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
				out.write(1);
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
					subjId = Integer.parseInt(request.getParameter(Def.SubId).toString());
					subjectName = request.getParameter("name").toString();
					HotelInfoPMS hotel = new HotelInfoPMS();
					eMenu menu = new eMenu();
					menu.setMenuName(subjectName);
					menu.setMenuId(subjId);
					menu.setUrlImage(config._hotel + "/" + image);
					menu.setUrlBg(config._hotel + "/" + bgimage);
					boolean t = hotel.editHotelMenu(menu, LangID);
					System.out.println("UPDATE SUBJECT PMS:" + t);
					if (t) {
						ManagerFile file = new ManagerFile();
						String path1 = config._temp + "/" + image;
						String path2 = config._pathImage + config._hotel + "/" + image;
						file.copy(path1, path2);
						file.deletefile(path1);
						path1 = config._temp + "/" + bgimage;
						path2 = config._pathImage + config._hotel + "/" + bgimage;
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
			String image = "";
			String def = "";
			int status = 1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString().trim();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			if (request.getParameter("des") != null) {
				def = request.getParameter("des").toString();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			if (request.getParameter("status") != null) {
				status = Integer.parseInt(request.getParameter("status").toString().trim());
			}
			HotelInfoPMS hotel = new HotelInfoPMS();
			eImage item = new eImage();
			item.setId(id);
			item.setInvisible(status);
			item.setUrlImage(config._hotel + "/" + image);
			item.setDef(def);
			item.setName(name);
			boolean b = hotel.editItem(item, LangID);
			if (b) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._hotel + "/" + image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 4://
		{
			System.out.println("insert eImage");
			int id = -1;
			String name = "";
			String image = "";
			String def = "";
			int status = 1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString().trim();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString().trim();
			}
			if (request.getParameter("des") != null) {
				def = request.getParameter("des").toString();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			if (request.getParameter("status") != null) {
				status = Integer.parseInt(request.getParameter("status").toString().trim());
			}
			HotelInfoPMS hotel = new HotelInfoPMS();
			int b = hotel.addItemHotel(id, name, def, config._hotel + "/" + image, "");
			if (b > 0) {
				ManagerFile file = new ManagerFile();
				String path1 = config._temp + "/" + image;
				String path2 = config._pathImage + config._hotel + "/" + image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 5: {

			System.out.println("Save message");
		/*	int folionum = -1;
			String sender = "";
			String subject = "";
			String content = "";
			if (request.getParameter("folionum") != null) {
				folionum = Integer.parseInt(request.getParameter("folionum").toString().trim());
			}
			sender = request.getParameter("sender").toString().trim();
			sender = UnicodeConverter.encodeUnicode(sender);
			subject = request.getParameter("subject");
			subject = UnicodeConverter.encodeUnicode(subject);
			content = request.getParameter("content");
			System.out.println("--------------content_111111: "+content);*/
			
			int folionum = -1;
			
			String sender = "";
			String subject = "";
			String content = "";
			if (request.getParameter("folionum") != null) {
				folionum = Integer.parseInt(request.getParameter("folionum").toString().trim());
			}
			
			sender = request.getParameter("sender").toString().trim();
			sender = UnicodeConverter.encodeUnicode(sender);
			subject = request.getParameter("subject");
			subject = UnicodeConverter.encodeUnicode(subject);
			content = request.getParameter("content").toString().trim();	
			
			/*content=content.replaceAll("<div>", "<br/>");
			content=content.replaceAll("</div>", "  ");
			content=content.replaceAll("<br>", "\r\n");		*/	
			
			/*content=content.replaceAll("<div><br></div><div><br></div><div>", "<br/><br/></br>");
			content=content.replaceAll("<div><br></div><div>", "<br/><br/>");
			content=content.replaceAll("<div></div>", "<br/>");
			content=content.replaceAll("</div>", "");
			content=content.replaceAll("<div>", "<br/>");	
			content=content.replaceAll("<br>", "<br/>");	*/
		
			content = UnicodeConverter.encodeUnicode(content);			
			System.out.println(sender + " " + subject + " " + content);
			boolean flag = folioServiceDBI.insertMessage(folionum, subject, content, sender);
			System.out.println(flag);
			break;
		}
		case 6: {
			System.out.println("Check in for room");
			int folionum = Integer.parseInt(request.getParameter("folionum").trim());
			String guest = request.getParameter("guest");
			int amount = Integer.parseInt(request.getParameter("amount"));
			String checkindate = convertDay(request.getParameter("checkindate"));
			String checkoutdate = convertDay(request.getParameter("checkoutdate"));
			String[] array = guest.split(";");
			int checkmainguest = folioServiceDBI.checkExistsMainGuest(folionum);
			if (checkmainguest == 0) {
				for (int i = 0; i < amount; i++) {
					String[] arr = array[i].split(",");
					String temp1 = UnicodeConverter.encodeUnicode(arr[0]);
					String temp2 = UnicodeConverter.encodeUnicode(arr[1]);
					
					if (i == 0)
						folioServiceDBI.CheckIn(folionum, temp1, temp2, arr[2], checkindate, checkoutdate, 0);
					else {
						folioServiceDBI.CheckIn(folionum, temp1, temp2, arr[2], checkindate, checkoutdate, 1);
					}
				}
			} else {
				for (int i = 0; i < amount; i++) {
					String[] arr = array[i].split(",");
					String temp1 = UnicodeConverter.encodeUnicode(arr[0]);
					String temp2 = UnicodeConverter.encodeUnicode(arr[1]);
					folioServiceDBI.CheckIn(folionum, temp1, temp2, arr[2], checkindate, checkoutdate, 1);
				}
			}
			break;
		}
		case 7:{
			response.setContentType("text/xml");
			System.out.println("Update guest name");
			int room = Integer.parseInt(request.getParameter("room").trim());
			int id = Integer.parseInt(request.getParameter("id").trim());
			String name = request.getParameter("name");
			boolean flag = folioServiceDBI.editGuestName(room, id, name);
			out.print(flag);
			break;
		}case 8: {

			System.out.println("Save message edit");
			int folionum = -1;
			int id_message = -1;
			String sender = "";
			String subject = "";
			String content = "";
			if (request.getParameter("folionum") != null) {
				folionum = Integer.parseInt(request.getParameter("folionum").toString().trim());
			}
			if (request.getParameter("id_message") != null) {
				id_message = Integer.parseInt(request.getParameter("id_message").toString().trim());
			}
			sender = request.getParameter("sender").toString().trim();
			sender = UnicodeConverter.encodeUnicode(sender);
			subject = request.getParameter("subject");
			subject = UnicodeConverter.encodeUnicode(subject);
			content = request.getParameter("content").toString().trim();	
			
			/*content=content.replaceAll("<div>", "<br/>");
			content=content.replaceAll("</div>", "  ");
			content=content.replaceAll("<br>", "\r\n");		*/	
			
			/*content=content.replaceAll("<div><br></div><div><br></div><div>", "<br/><br/></br>");
			content=content.replaceAll("<div><br></div><div>", "<br/><br/>");
			content=content.replaceAll("<div></div>", "<br/>");
			content=content.replaceAll("</div>", "");
			content=content.replaceAll("<div>", "<br/>");	
			content=content.replaceAll("<br>", "<br/>");	*/
		
			content = UnicodeConverter.encodeUnicode(content);			
					System.out.println(sender + " " + subject + " " + content);
			//boolean flag = folioServiceDBI.insertMessage(folionum, subject, content, sender); boolean flag = folioServiceDBI
					//(int id_room,int id_message,int lang_id,String subject,String sender,String content) {
			boolean flag = folioServiceDBI.UpdateMessage_Detail(folionum,id_message,LangID,subject,sender,content);
			System.out.println(flag);
			break;
		}	
	
		
		default:
			break;
		}
	}

	public void init() throws ServletException {
	}

	private String getsub() {
		HotelInfoPMS hotel = new HotelInfoPMS();
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
		list = pmsServiceDBI.getMainMenu(33, LangID);
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

	private String getContentHotel(int subjId, int index, int page) {
		HotelInfoPMS hotel = new HotelInfoPMS();
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		Vector<eImage> info = hotel.getItemsOfHotelInfo(subjId, LangID, fr, to);
		int count = hotel.countItem(subjId);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		mData += "<xml count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			eImage item = info.get(i);
			String t = "";
			if (item.getDef() != null)
				t = UtilString.converString(item.getDef());
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>";
			mData += "<Des>";
			mData += "<![CDATA[" + t.replaceAll("ehotel.elcom.tv", config._HostFtpServer) + "]]>";
			mData += "</Des>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getInvisible() + "]]>";
			mData += "</status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getSubinHotel(int itemId) {
		HotelInfoPMS hotel = new HotelInfoPMS();
		Vector<eMenu> menu = hotel.getSubjectsInItem(itemId, LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
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
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getSuboutHotel(int itemId) {
		HotelInfoPMS hotel = new HotelInfoPMS();
		Vector<eMenu> menu = hotel.getSubjectsOutItem(itemId, LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
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
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
	public String convertDay(String str) {
		if (str.equals("") == true)
			return "";
		String[] arr = str.split(" ");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = arr[0];
		String result = "";
		try {
			Date date = formatter.parse(dateInString);
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
			result = (String) format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private String getMessage_Detail(List<Message_Detail> list) {		
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<id_room>";
			mData += "<![CDATA[" + list.get(i).getId_room() + "]]>";
			mData += "</id_room>";
			mData += "<id_message>\n";
			mData += list.get(i).getId_message();
			mData += "</id_message>\n";
			mData += "<subject>\n";
			mData += list.get(i).getSubject();
			mData += "</subject>";
			mData += "<sender>\n";
			mData += list.get(i).getSender();
			mData += "</sender>";			
			mData += "<content>";
			mData += list.get(i).getContent();
			mData += "</content>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}
	
	public static void main(String[] args) {
		ServiceHotel s = new ServiceHotel();
		System.out.println(s.convertDay("2015-01-06"));
	}
}
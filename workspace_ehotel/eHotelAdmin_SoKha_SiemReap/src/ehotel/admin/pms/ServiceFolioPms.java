package ehotel.admin.pms;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elcom.eod.util.UnicodeConverter;

import ehotel.abs.pms.FolioStbPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dao.FolioServiceDBI;
import ehotel.admin.dao.ReportServiceDBI;
import ehotel.admin.dbi.DBIPool;
import ehotel.admin.model.Record;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.domain.pms.eBillCharge;
import ehotel.domain.pms.eFolio;
import ehotel.domain.pms.eMessage;
import ehotel.domain.pms.eSTB;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;

@SuppressWarnings("serial")
public class ServiceFolioPms extends ServiceParent {

	private FolioServiceDBI folioServiceDBI = new FolioServiceDBI();

	public ServiceFolioPms() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		super.doGet(request, response);
		response.setContentType("text/xml");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
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
			request.setAttribute("fileJSP", "../pmsMng/folio/folio.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1:// get list room
		{
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			String key = "";
			if (request.getParameter("key") != null) {
				key = request.getParameter("key").toString();
			}
			response.setContentType("text/xml");
			String st = getListRoom(index, page);
			out.print(st);
			break;
		}
		case 2:// get list guest
		{
			System.out.println("Get list guest");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			response.setContentType("text/xml");
			String st = getGuest(id);
			out.print(st);
			break;
		}
		case 3:// get list messages
		{
			System.out.println("Get list messages");
			int id = -1;
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}

			response.setContentType("text/xml");
			String st = getMess(id, index, page);
			out.print(st);
			break;
		}
		case 4:// get list bill
		{
			System.out.println("Get list Bill");
			int id = -1;
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			response.setContentType("text/xml");
			String st = getBill(id, index, page);
			out.print(st);
			break;
		}
		case 5://
		{
			System.out.println("Get list STB in room");
			int role = -1;
			ILOGIN iuser = DBIGateway.getILogin();
			String ipAdress = request.getRemoteAddr();
			if (iuser.isAdmin(ipAdress) || iuser.checkRoleUser(ipAdress, "AUD$STB")) {
				role = 1;
			}
			int id = -1;
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}

			response.setContentType("text/xml");
			String st = getSTB(id, role);
			out.print(st);
			break;
		}
		case 6:// show form setup
		{
			this.showJSPpage(request, response, "/pmsMng/folio/ListSTB.jsp");
			break;
		}
		case 7://
		{
			System.out.println("Get list STB not room");
			int id = -1;
			int index = 0;
			int page = 6;
			String key = "";
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			if (request.getParameter("key") != null) {
				key = request.getParameter("key").toString().trim();
			}
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			response.setContentType("text/xml");
			String st = getAddSTB(id, index, page, key);
			out.print(st);
			break;
		}
		case 8://
		{
			System.out.println("insert stb ti roomm");
			String stb = "";
			if (request.getParameter("stb") != null) {
				stb = request.getParameter("stb").toString().trim();
			}
			int roomid = -1;
			if (request.getParameter("room") != null) {
				roomid = Integer.parseInt(request.getParameter("room").toString().trim());
			}
			System.out.println("smartcard:" + stb);
			System.out.println("Room:" + roomid);
			FolioStbPMS folio = new FolioStbPMS();
			String t = folio.addSTBIntoFolio(stb, roomid);
			out.print(t);
			break;
		}
		case 9://
		{

			System.out.println("remove stb ti roomm");
			String stb = "";
			if (request.getParameter("stb") != null) {
				stb = request.getParameter("stb").toString().trim();
			}
			FolioStbPMS folio = new FolioStbPMS();
			boolean t = folio.removeSTBOutFolio(stb);
			out.print(t);
			break;
		}
		// lay danh sach STB
		case 10: {
			System.out.println("Get list STB");
			int index = 0;
			int page = 6;
			String key = "";
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			response.setContentType("text/xml");
			String st = "";
			if (!(request.getParameter("id").toString().trim().equalsIgnoreCase(""))) {
				key = request.getParameter("id").toString().trim();
				st = getListSearchSTB(key);
				System.out.println("key = = = = = = = = = " + key);
			} else {
				st = getAllSTB(index, page);
			}
			out.print(st);
			break;
		}
		case 11:// get key seach
		{
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			String key = "";
			if (request.getParameter("key") != null) {
				key = request.getParameter("key").toString();
			}
			response.setContentType("text/xml");
			String st = getListRoomSearch(key, index, page);
			out.print(st);
			break;
		}
		case 12:// check out
		{

			System.out.println("Check out guest");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			FolioStbPMS folio = new FolioStbPMS();
			boolean b = folio.checkOutGuest(id);

		}
		case 13:// check out
		{
			System.out.println("Check out room");
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			// FolioStbPMS folio = new FolioStbPMS();
			// boolean b = folio.checkOutFolio(id);
			boolean b = folioServiceDBI.checkoutRoom(id);
			if (b)
				out.print("Check out success!");
			else
				out.print("Check out unsuccess!");
			break;
		}
		case 14:// reset pin
		{
			System.out.println("reset pincode");
			// ILOGIN iuser = DBIGateway.getILogin();
			// String ipAdress = request.getRemoteAddr();
			// boolean role = iuser.checkRoleUser(ipAdress, "AUD$FOLIO");
			// System.out.println("role:" + role);
			// if (!role && (!iuser.isAdmin(ipAdress))) {
			// out.print(-1);
			// } else {

			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			FolioStbPMS folio = new FolioStbPMS();
			boolean b = folio.resetPincode(id, id, "admin");
			// }
			break;
		}

		case 15: // show form check in
		{
			System.out.println("Show form check in");
			List<String> date = getNowDay();
			request.setAttribute("date", date);
			// System.out.println("cach moi = - = - = -= -= - = - = - = - = -");
			request.getRequestDispatcher("/pmsMng/folio/formCheckIn.jsp").forward(request, response);
			// this.showJSPpage(request, response,
			// "/pmsMng/folio/formCheckIn.jsp");
			break;
		}

		case 16: // check in
		{
			System.out.println("Check in for room");
			int folionum = Integer.parseInt(request.getParameter("folionum"));
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

			// boolean result = folioServiceDBI.CheckIn(folionum, firstname,
			// lastname,sex,checkindate,checkoutdate);
			// request.setAttribute(Def.MenuId, 4);
			// request.setAttribute(Def.SubId, 11);
			// request.setAttribute("fileJSP", "../pmsMng/folio/folio.jsp");
			// this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 17: // lay danh sach record
		{
			System.out.println("Get list Record");
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			response.setContentType("text/xml");
			String st = getAllRecord();
			out.print(st);
			break;
		}

		case 18: // lay danh sach record theo phong
		{
			System.out.println("Get list Record for folio");
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			String folio = request.getParameter("id");
			response.setContentType("text/xml");
			String st = getRecordForFolio(folio);
			out.print(st);
			break;
		}
		case 19: {
			System.out.println("Update status to delete for record");
			int id = Integer.parseInt(request.getParameter("id"));
			boolean flag = updateStatusRecord(id);
			out.print(flag);
			break;
		}
		case 20: // show form message
		{
			System.out.println("Show form send message");
			// List<String> date = getNowDay();
			// request.setAttribute("date", date);
			this.showJSPpage(request, response, "/pmsMng/folio/formMessage.jsp");
			break;
		}
		case 21: // add message
		{
			System.out.println("Save message");
			int folionum = -1;
			String sender = "";
			String subject = "";
			String content = "";
			if (request.getParameter("folionum") != null) {
				folionum = Integer.parseInt(request.getParameter("folionum").toString().trim());
			}
			if (request.getParameter("sender") != null) {
				sender = request.getParameter("sender").toString().trim();
				System.out.println("sender before = = = = = = = = = = = = = = = " + sender);
				sender = sender.replace("/&/g", "&amp;").replace("/>/g", "&gt;").replace("/</g", "&lt;");
				System.out.println("sender after = = = = = = = = = = = = = = = " + sender);
				sender = new String(sender.getBytes("8859_1"), "UTF8");
			}
			if (request.getParameter("subject") != null) {
				subject = request.getParameter("subject").toString().trim();
				subject = new String(subject.getBytes("8859_1"), "UTF8");
			}
			if (request.getParameter("content") != null) {
				content = request.getParameter("content").toString().trim();
				content = new String(content.getBytes("8859_1"), "UTF8");
			}
			System.out.println(sender + " " + subject + " " + content);
			boolean flag = folioServiceDBI.insertMessage(folionum, subject, content, sender);
			// boolean flag = folioServiceDBI.insertMessage(folionum, "thử",
			// "thữ", "thử");
			break;
		}
		case 22: // ping ip
		{
			System.out.println("Check ping ip");
			String ip = "";
			if (request.getParameter("ip") != null) {
				ip = request.getParameter("ip").toString().trim();
			}
			PmsFolioStb stb = new PmsFolioStb();
			boolean flag = stb.pingIp(ip);
			// HttpSession session = request.getSession();
			// ReaderLang readerLang = new ReaderLang();
			// readerLang = (ReaderLang) session.getAttribute(Def._lang);
			if (flag)
				out.print("success");
			else
				out.print("unsuccess");
			break;
		}
		case 23: // change status xxx movie
		{
			System.out.println("Change status charge movie");
			int id = -1;
			int x = 0;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("xxx") != null) {
				x = Integer.parseInt(request.getParameter("xxx").toString().trim());
			}
			if (x == 0)
				x = 1;
			else {
				x = 0;
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Disable Charge Movies to room " + id, user);
			}
			boolean flag = true;
			flag = folioServiceDBI.updateAccountValue(id, x);
			if (flag)
				out.print("success");
			else
				out.print("unsuccess");
			break;
		}
		case 24:// reset unique pincode
		{
			System.out.println("Reset unique pincode");
			int id = -1;
			if (request.getParameter("id") != null)
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			boolean flag = FolioServiceDBI.resetUniquePincode(id);
			if (flag)
				out.print("success");
			else
				out.print("unsuccess");
			break;
		}
		case 25: // show form edit charge unique
		{
			System.out.println("Show form edit charge unique");
			this.showJSPpage(request, response, "/pmsMng/folio/formCharge.jsp");
			break;
		}
		case 26: // edit charge unique
		{
			System.out.println("Edit charge unique");
			int charge = -1;
			if (request.getParameter("charge") != null)
				charge = Integer.parseInt(request.getParameter("charge").toString().trim());
			boolean flag = folioServiceDBI.editUniqueCharge(charge);
			if (flag)
				out.print("success");
			else
				out.print("unsuccess");
			break;
		}
		case 27: // update ota to room
		{
			System.out.println("Update ota to room");
			int id = -1;
			if (request.getParameter("id") != null)
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			boolean flag = updateOTAToRoom(id);
			if (flag)
				out.print("success");
			else
				out.print("unsuccess");
			break;
		}
		case 28: // change status xxx movie
		{
			System.out.println("Change status xxx movie");
			int id = -1;
			int x = 0;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("xxx") != null) {
				x = Integer.parseInt(request.getParameter("xxx").toString().trim());
			}
			if (x == 0)
				x = 1;
			else {
				x = 0;
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Disable XXX Movies to room " + id, user);
			}
			boolean flag = true;
			flag = folioServiceDBI.updateStatusXXX(id, x);
			if (flag)
				out.print("success");
			else
				out.print("unsuccess");
			break;
		}
		case 29: // change status free movie
		{
			System.out.println("Change status free movie");
			int id = -1;
			int x = 0;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("xxx") != null) {
				x = Integer.parseInt(request.getParameter("xxx").toString().trim());
			}
			if (x == 0)
				x = 1;
			else {
				x = 0;
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Disable Free Movies to room " + id, user);
			}
			boolean flag = true;
			flag = folioServiceDBI.updateStatusFree(id, x);
			if (flag)
				out.print("success");
			else
				out.print("unsuccess");
			break;
		}
		case 30:
		{	
			response.setContentType("text/xml");
			System.out.println("get all folio for message");
			String rs = getAllFolio();
			out.print(rs);
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
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
			}
		}
		switch (cmd) {
		case 5: {

			System.out.println("Save message");
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
			content = request.getParameter("content").toString();
			content = content.replaceAll("<strong>", "<b>");
			content = content.replaceAll("</strong>", "</b>");
			content = content.replaceAll("<em>", "<i>");
			content = content.replaceAll("</em>", "</i>");
			content = content.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
			content = content.replaceAll("</span>", "</u>");
			content = content.replaceAll("<p>", "");
			content = content.replaceAll("</p>", "");
			while (content.indexOf("\n \n") >= 0)
				content = content.replace("\n \n", "\n\n");
			content = content.replaceAll("\n", "<br/>");
			content = UnicodeConverter.encodeUnicode(content);
			// System.out.println(sender + " " + subject + " " + content);
			folioServiceDBI.insertMessage(folionum, subject, content, sender);
			HttpSession session = request.getSession(true);
			String user = "";
			user = session.getAttribute("user").toString();
			ReportServiceDBI.addUserActivities("Sent message to room " + folionum, user);
			// System.out.println(flag);
			break;
		}
		case 6: {

			System.out.println("Save message all folio");
			String sender = "";
			String subject = "";
			String content = "";
			sender = request.getParameter("sender").toString().trim();
			sender = UnicodeConverter.encodeUnicode(sender);
			subject = request.getParameter("subject");
			subject = UnicodeConverter.encodeUnicode(subject);
			content = request.getParameter("content").toString();
			content = content.replaceAll("<strong>", "<b>");
			content = content.replaceAll("</strong>", "</b>");
			content = content.replaceAll("<em>", "<i>");
			content = content.replaceAll("</em>", "</i>");
			content = content.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
			content = content.replaceAll("</span>", "</u>");
			content = content.replaceAll("<p>", "");
			content = content.replaceAll("</p>", "");
			while (content.indexOf("\n \n") >= 0)
				content = content.replace("\n \n", "\n\n");
			content = content.replaceAll("\n", "<br/>");
			content = UnicodeConverter.encodeUnicode(content);
			boolean flag = folioServiceDBI.insertMessageAllFolio(subject, content, sender);
			if (flag) {
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Sent message to all room ", user);
				out.print("success");
			} else
				out.print("unsuccess");
			break;
		}
		case 7: {

			System.out.println("Save message list folio");
			String listroom = "";
			String sender = "";
			String subject = "";
			String content = "";
			listroom = request.getParameter("listroom").toString().trim();
			sender = request.getParameter("sender").toString().trim();
			sender = UnicodeConverter.encodeUnicode(sender);
			subject = request.getParameter("subject");
			subject = UnicodeConverter.encodeUnicode(subject);
			content = request.getParameter("content").toString();
			content = content.replaceAll("<strong>", "<b>");
			content = content.replaceAll("</strong>", "</b>");
			content = content.replaceAll("<em>", "<i>");
			content = content.replaceAll("</em>", "</i>");
			content = content.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
			content = content.replaceAll("</span>", "</u>");
			content = content.replaceAll("<p>", "");
			content = content.replaceAll("</p>", "");
			while (content.indexOf("\n \n") >= 0)
				content = content.replace("\n \n", "\n\n");
			content = content.replaceAll("\n", "<br/>");
			// System.out.println(sender + " " + subject + " " + content);
			content = UnicodeConverter.encodeUnicode(content);
			boolean flag = folioServiceDBI.insertMessageListFolio(listroom, subject, content, sender);
			if (flag) {
				HttpSession session = request.getSession(true);
				String user = "";
				user = session.getAttribute("user").toString();
				ReportServiceDBI.addUserActivities("Sent message to list room ", user);
				out.print("success");
			} else
				out.print("unsuccess");
			break;
		}
		default:
			break;
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

	private String getGuest(int roomId) {
		List<GuestModel> list = new ArrayList<GuestModel>();
		list = folioServiceDBI.getGuestForFolio(roomId);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + list.size() + "\">";
		for (int i = 0; i < list.size(); i++) {
			System.out.println("ID:" + list.get(i).getClientid());
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getLastname() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += list.get(i).getClientid();
			mData += "</id>";
			mData += "<children>";
			mData += "<![CDATA[" + 0 + "]]>";
			mData += "</children>";
			mData += "<Arrival>";
			mData += "<![CDATA[" + list.get(i).getArrivaltime() + "]]>";
			mData += "</Arrival>";
			mData += "<out>";
			mData += "<![CDATA[" + list.get(i).getDepartmenttime() + "]]>";
			mData += "</out>";
			mData += "<status>";
			mData += "<![CDATA[" + list.get(i).getRoomshare() + "]]>";
			mData += "</status>";
			mData += "<pincode>";
			mData += "<![CDATA[" + list.get(i).getPincode() + "]]>";
			mData += "</pincode>";
			mData += "<unique>";
			mData += "<![CDATA[" + list.get(i).getUnique() + "]]>";
			mData += "</unique>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getMess(int roomId, int index, int page) {
		FolioStbPMS folio = new FolioStbPMS();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		Vector<eMessage> info = folio.getMessages(roomId, fr, to);
		String mData = "";
		int count = folio.countFolio();
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			eMessage item = info.get(i);
			String read = item.getType();
			if (read != null)
				read = read.replace("00:00:00", "");
			else
				read = "Unread";
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getSubject() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>";
			mData += "<content>";
			mData += "<![CDATA[" + item.getContent() + "]]>";
			mData += "</content>";
			mData += "<date>";
			if (item.getEnterDate() != null) {
				mData += "<![CDATA[" + item.getEnterDate() + " " + item.getEnterTime() + "]]>";
			} else
				mData += "<![CDATA[]]>";
			mData += "</date>";
			mData += "<from>";
			mData += "<![CDATA[" + item.getSender() + "]]>";
			mData += "</from>";
			mData += "<read>";
			mData += "<![CDATA[" + read + "]]>";
			mData += "</read>";
			mData += "</Item>";
		}
		mData += "</xml>";

		// System.out.println(mData);
		return mData;
	}

	private String getBill(int roomId, int index, int page) {
		FolioStbPMS folio = new FolioStbPMS();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		Vector<eBillCharge> info = folio.getBillCharges(roomId, fr, to);
		String mData = "";

		int count = (int) (folio.countBill(roomId) / 2);
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			eBillCharge item = info.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getCode() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>";
			mData += "<price>";
			if (item.getPrice() != null) {
				mData += "<![CDATA[" + item.getPrice() + "]]>";
			} else {
				mData += "<![CDATA[]]>";
			}
			mData += "</price>";
			mData += "<quantity>";
			mData += "<![CDATA[" + item.getQuantity() + "]]>";
			mData += "</quantity>";
			mData += "<date>";
			mData += "<![CDATA[" + item.getDateTime() + "]]>";
			mData += "</date>";
			mData += "<amount>";
			// sua sai vi tri dat dau phat 25062013
			// int a = 0;
			// String temp = item.getAmount();
			// if (temp.indexOf(" VND") >= 0) {
			// temp = temp.substring(0, temp.indexOf(" VND"));
			// } else if (temp.indexOf(" USD") >= 0) {
			// temp = temp.substring(0, temp.indexOf(" USD"));
			// }
			// if (temp.indexOf(".") >= 0) {
			// a = Integer.parseInt(temp.substring(0,
			// temp.indexOf(".")).replaceAll(",", ""));
			// } else {
			// a = Integer.parseInt(temp.replaceAll(",", ""));
			// }
			// mData += "<![CDATA[" + ngandauphay(a) + "]]>";
			mData += "<![CDATA[" + item.getAmount() + "]]>";
			mData += "</amount>";
			mData += "<unit>";
			// loi unit hang dau tien 25062013
			if (item.getAmount().indexOf(" VND") >= 0) {
				mData += "<![CDATA[VND]]>";
			} else if (item.getAmount().indexOf(" USD") >= 0) {
				mData += "<![CDATA[USD]]>";
			} else {
				mData += "<![CDATA[" + item.getIUnit() + "]]>";
			}
			mData += "</unit>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	public String ngandauphay(int so) {
		String hello = Integer.toString(so);
		String hello2 = "";
		if (hello.indexOf("-") >= 0) {
			hello2 = hello.substring(1);
		} else {
			hello2 = hello;
		}
		int count = 0;
		StringBuffer a = new StringBuffer();
		for (int i = hello2.length() - 1; i >= 0; i--) {
			// System.out.println("vi tri:" + i + " - kytu:" +hello2.charAt(i));
			a.append(hello2.charAt(i));
			count++;
			if (count % 3 == 0 && count < hello2.length()) {
				a.append(",");
			}
		}
		if (hello.indexOf("-") >= 0) {
			a.append("-");
		}
		a.reverse();
		return a.toString();
	}

	private String getSTB(int roomId, int role) {
		FolioStbPMS folio = new FolioStbPMS();
		long start = System.currentTimeMillis();
		Vector<eSTB> info = folio.getSTBListIn(roomId);
		String mData = "";
		int count = folio.countFolio();
		long end = System.currentTimeMillis();
		System.out.println("Time:" + (end - start));
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + count + "\" role=\"" + role + "\">";
		for (int i = 0; i < info.size(); i++) {
			eSTB item = info.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getId() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getKeyCode();
			mData += "</id>";
			mData += "<ip>";
			if (item.getIP() == null) {
				mData += "<![CDATA[]]>";
			} else {
				mData += "<![CDATA[" + item.getIP() + "]]>";
			}
			mData += "</ip>";
			mData += "<keycode>";
			mData += "<![CDATA[" + item.getKeyCode() + "]]>";
			mData += "</keycode>";
			mData += "<date>";
			if (item.getCreatedate() != null) {
				mData += "<![CDATA[" + item.getCreatedate() + "]]>";
			} else {
				mData += "<![CDATA[]]>";
			}
			mData += "</date>";
			mData += "<roomcode>";
			if (item.getRoomCode() != null) {
				mData += "<![CDATA[" + item.getRoomCode() + "]]>";
			} else {
				mData += "<![CDATA[]]>";
			}
			mData += "</roomcode>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getStatus() + "]]>";
			mData += "</status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	@SuppressWarnings("unused")
	private String getAddSTB(int roomId, int index, int page, String key) {
		FolioStbPMS folio = new FolioStbPMS();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		Vector<eSTB> info = folio.searchSTB(roomId, key, -1, -1);
		String mData = "";
		int count = folio.countSearchSTB(roomId, key);

		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			eSTB item = info.get(i);

			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getId() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getKeyCode();
			mData += "</id>";
			mData += "<ip>";
			if (item.getIP() == null) {

				mData += "<![CDATA[]]>";
			} else {
				mData += "<![CDATA[" + item.getIP() + "]]>";
			}
			mData += "</ip>";
			mData += "<keycode>";
			mData += "<![CDATA[" + item.getKeyCode() + "]]>";
			mData += "</keycode>";
			mData += "<date>";
			if (item.getCreatedate() != null) {
				mData += "<![CDATA[" + item.getCreatedate() + "]]>";
			} else {
				mData += "<![CDATA[]]>";
			}
			mData += "</date>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getStatus() + "]]>";
			mData += "</status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	// ham bo sung cho viec update status stb 22.2
	@SuppressWarnings("unused")
	private boolean updateStatusStb(int id, int status) {
		eSTB estb = new eSTB();
		estb.setId(id);
		estb.setStatus(status);
		return true;
	}

	@SuppressWarnings("unused")
	private String getAllSTB(int index, int page) {
		System.out.println("function get all list STB");
		FolioStbPMS folio = new FolioStbPMS(); // khai bao 1 file giong nhu DAO
		int fr = index * page; // "fr" la muon lay gia tri tu vi tri thu "fr"
		fr += 1; // +1 la do index = 0
		int to = (index + 1) * page; // "to" la muon lay gia tri toi vi tri "to"
		Vector<eSTB> info = folio.getSTBListOut(-1, -1, -1); // "info" chua tat
																// ca cac thong
																// tin cua
																// list STB
		String mData = ""; // luu noi dung file xml
		int count = folio.countSTB(-1); // "count" la so luong STB dang danh
										// sach
		// multi thread for ping 18062013
		// String[] str = new String[info.size()]; // tao 1 array co length =
		// count
		// final boolean[] strRe = new boolean[info.size()]; // tao 1 danh sach
		// de
		// luu trang thai
		// duoi
		// dang boolean
		// PmsFolioStb stb1 = new PmsFolioStb(); // chua thong tin OS hien tai
		// for (int i = 0; i < info.size(); i++) { // duyet danh sach STB chua
		// // trong "info"
		// eSTB item = info.get(i); // tao STB gan cho tung STB trong danh sach
		// // STB cua "info"
		// str[i] = item.getIP(); // gan dia chi IP cua tung STB vao array str
		// strRe[i] = false; // gan array trang thai = false
		// }
		// boolean[] boolKq = stb1.pingIp1(str, strRe); // tao array chua trang
		// thai luu trang thai
		// ip
		// cua "str" co ping duoc khong
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			// if (boolKq[i]) {
			eSTB item = info.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getId() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getKeyCode();
			mData += "</id>";
			mData += "<ip>";
			if (item.getIP() == null) {
				mData += "<![CDATA[]]>";
			} else {
				mData += "<![CDATA[" + item.getIP() + "]]>";
			}
			mData += "</ip>";
			mData += "<keycode>";
			mData += "<![CDATA[" + item.getKeyCode() + "]]>";
			mData += "</keycode>";
			mData += "<date>";
			if (item.getCreatedate() != null) {
				mData += "<![CDATA[" + item.getCreatedate() + "]]>";
			} else {
				mData += "<![CDATA[]]>";
			}
			mData += "</date>";
			mData += "<roomcode>";
			if (item.getRoomCode() != null) {
				mData += "<![CDATA[" + item.getRoomCode() + "]]>";
			} else {
				mData += "<![CDATA[]]>";
			}
			mData += "</roomcode>";
			// lay status nghia la kiem tra IP o ngay tai day 26.2
			PmsFolioStb stb = new PmsFolioStb();
			mData += "<status>";
			// mData+="<![CDATA["+item.getStatus()+"]]>";
			/*
			 * if (stb.pingIp(item.getIP())) { mData+="<![CDATA["+1+"]]>"; } else { mData+="<![CDATA["+0+"]]>"; }
			 */
			// if (boolKq[i]) {
			// mData += "<![CDATA[" + 1 + "]]>";
			// } else {
			mData += "<![CDATA[" + 0 + "]]>";
			// }
			mData += "</status>";
			mData += "</Item>";
			// }
		}
		// for (int i = 0; i < info.size(); i++) {
		// if (!boolKq[i]) {
		// eSTB item = info.get(i);
		// mData += "<Item>";
		// mData += "<name>";
		// mData += "<![CDATA[" + item.getId() + "]]>";
		// mData += "</name>";
		// mData += "<id>\n";
		// mData += item.getKeyCode();
		// mData += "</id>";
		// mData += "<ip>";
		// if (item.getIP() == null) {
		// mData += "<![CDATA[]]>";
		// } else {
		// mData += "<![CDATA[" + item.getIP() + "]]>";
		// }
		// mData += "</ip>";
		// mData += "<keycode>";
		// mData += "<![CDATA[" + item.getKeyCode() + "]]>";
		// mData += "</keycode>";
		// mData += "<date>";
		// if (item.getCreatedate() != null) {
		// mData += "<![CDATA[" + item.getCreatedate() + "]]>";
		// } else {
		// mData += "<![CDATA[]]>";
		// }
		// mData += "</date>";
		// mData += "<roomcode>";
		// if (item.getRoomCode() != null) {
		// mData += "<![CDATA[" + item.getRoomCode() + "]]>";
		// } else {
		// mData += "<![CDATA[]]>";
		// }
		// mData += "</roomcode>";
		// // lay status nghia la kiem tra IP o ngay tai day 26.2
		// PmsFolioStb stb = new PmsFolioStb();
		// mData += "<status>";
		// // mData+="<![CDATA["+item.getStatus()+"]]>";
		// /*
		// * if (stb.pingIp(item.getIP())) { mData+="<![CDATA["+1+"]]>"; }
		// * else { mData+="<![CDATA["+0+"]]>"; }
		// */
		// if (boolKq[i]) {
		// mData += "<![CDATA[" + 1 + "]]>";
		// } else {
		// mData += "<![CDATA[" + 0 + "]]>";
		// }
		// mData += "</status>";
		//
		// mData += "</Item>";
		// }
		// }
		mData += "</xml>";
		return mData;
	}

	private String getListSearchSTB(String key) {
		System.out.println("function get list search STB");
		List<eSTB> list = new ArrayList<eSTB>();
		list = folioServiceDBI.getListSearchSTB(key);
		System.out.println(list);
		String mData = ""; // luu noi dung file xml
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + list.size() + "\">";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getId() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += list.get(i).getKeyCode();
			mData += "</id>";
			mData += "<ip>";
			if (list.get(i).getIP() == null) {
				mData += "<![CDATA[]]>";
			} else {
				mData += "<![CDATA[" + list.get(i).getIP() + "]]>";
			}
			mData += "</ip>";
			mData += "<keycode>";
			mData += "<![CDATA[" + list.get(i).getKeyCode() + "]]>";
			mData += "</keycode>";
			mData += "<date>";
			if (list.get(i).getCreatedate() != null) {
				mData += "<![CDATA[" + list.get(i).getCreatedate() + "]]>";
			} else {
				mData += "<![CDATA[]]>";
			}
			mData += "</date>";
			mData += "<roomcode>";
			if (list.get(i).getRoomCode() != null) {
				mData += "<![CDATA[" + list.get(i).getRoomCode() + "]]>";
			} else {
				mData += "<![CDATA[]]>";
			}
			mData += "</roomcode>";
			mData += "<status>";
			if (!(list.get(i).getIP().equalsIgnoreCase(""))) {
				// PmsFolioStb stb = new PmsFolioStb();
				// boolean flag = stb.pingIp(list.get(i).getIP());
				// if (flag)
				// mData += "<![CDATA[" + 1 + "]]>";
				// else
				mData += "<![CDATA[" + 0 + "]]>";
			} else
				mData += "<![CDATA[" + 0 + "]]>";
			mData += "</status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	@SuppressWarnings("unused")
	private String getRoomSeak(int index, int page, String key) {
		FolioStbPMS folio = new FolioStbPMS();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		long start = System.currentTimeMillis();
		Vector<eFolio> info = folio.searchFolio(key, fr, to);
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end - start));
		String mData = "";
		String charge = folioServiceDBI.getChargeFilm();
		int count = folio.countSearchFolio(key);
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			eFolio item = info.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getFolioCode() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getFolioCode();
			mData += "</id>";
			mData += "<key>";
			mData += "<![CDATA[" + item.getFolioNum() + "]]>";
			mData += "</key>";
			mData += "<type>";
			mData += "<![CDATA[" + item.getFolioType() + "]]>";
			mData += "</type>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getStatus() + "]]>";
			mData += "</status>";
			mData += "<fee>";
			mData += "<![CDATA[" + charge + "]]>";
			mData += "</fee>";
			String romantic = folioServiceDBI.getAccountValue(item.getFolioCode());
			String[] arr = romantic.split("#");
			mData += "<free>";
			mData += "<![CDATA[" + arr[0] + "]]>";
			mData += "</free>";
			mData += "<charge>";
			mData += "<![CDATA[" + arr[0] + "]]>";
			mData += "</charge>";
			mData += "<xxx>";
			mData += "<![CDATA[" + arr[1] + "]]>";
			mData += "</xxx>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getListRoom(int index, int page) {
		FolioStbPMS folio = new FolioStbPMS();
		int count = folio.countFolio();
		List<FolioModel> list = new ArrayList<FolioModel>();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		list = folioServiceDBI.getListFolio(fr, to);
		String mData = "";
		// int count = list.size();
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">\n";
		for (int i = 0; i < list.size(); i++) {
			FolioModel item = list.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getFoliocode() + "]]>";
			mData += "</name>";
			mData += "<id>";
			mData += item.getFoliocode();
			mData += "</id>";
			mData += "<key>";
			mData += "<![CDATA[" + item.getFolionum() + "]]>";
			mData += "</key>";
			mData += "<type>";
			mData += "<![CDATA[" + item.getType() + "]]>";
			mData += "</type>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getStatus() + "]]>";
			mData += "</status>";
			mData += "<fee>";
			mData += "<![CDATA[" + item.getFee() + "]]>";
			mData += "</fee>";
			mData += "<free>";
			mData += "<![CDATA[" + item.getFree() + "]]>";
			mData += "</free>";
			mData += "<charge>";
			mData += "<![CDATA[" + item.getCharge() + "]]>";
			mData += "</charge>";
			mData += "<xxx>";
			mData += "<![CDATA[" + item.getXxx() + "]]>";
			mData += "</xxx>";
			mData += "<roomstatus>";
			mData += "<![CDATA[" + item.getRoomstatus() + "]]>";
			mData += "</roomstatus>";
			mData += "</Item>\n";
		}
		mData += "</xml>";
		return mData;
	}

	private String getListRoomSearch(String key, int index, int page) {
		FolioStbPMS folio = new FolioStbPMS();
		int count = folio.countSearchFolio(key);
		List<FolioModel> list = new ArrayList<FolioModel>();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		list = folioServiceDBI.getListFolioSearch(key, fr, to);
		String mData = "";
		// int count = list.size();
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">\n";
		for (int i = 0; i < list.size(); i++) {
			FolioModel item = list.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getFoliocode() + "]]>";
			mData += "</name>";
			mData += "<id>";
			mData += item.getFoliocode();
			mData += "</id>";
			mData += "<key>";
			mData += "<![CDATA[" + item.getFolionum() + "]]>";
			mData += "</key>";
			mData += "<type>";
			mData += "<![CDATA[" + item.getType() + "]]>";
			mData += "</type>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getStatus() + "]]>";
			mData += "</status>";
			mData += "<fee>";
			mData += "<![CDATA[" + item.getFee() + "]]>";
			mData += "</fee>";
			mData += "<free>";
			mData += "<![CDATA[" + item.getFree() + "]]>";
			mData += "</free>";
			mData += "<charge>";
			mData += "<![CDATA[" + item.getCharge() + "]]>";
			mData += "</charge>";
			mData += "<xxx>";
			mData += "<![CDATA[" + item.getXxx() + "]]>";
			mData += "</xxx>";
			mData += "<roomstatus>";
			mData += "<![CDATA[1]]>";
			mData += "</roomstatus>";
			mData += "</Item>\n";
		}
		mData += "</xml>";
		return mData;
	}

	// ham lay ngay hien tai
	public List<String> getNowDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date today = Calendar.getInstance().getTime();

		String date = (String) dateFormat.format(today);
		String[] arr = date.split("-");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			String temp = arr[i];
			list.add(temp);
		}
		return list;
	}

	// chuyen doi ngay bao cao thanh dang dd-MMM-yy
	public String convertDay(String str) {
		if (str.equals("") == true)
			return "";
		String[] arr = str.split(" ");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = arr[0];
		String result = "";
		try {
			Date date = formatter.parse(dateInString);
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			result = (String) format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// method get all recoed
	public String getAllRecord() {
		Vector<Record> list = new Vector<Record>();
		list = folioServiceDBI.getAllRecord();
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + list.size() + "\">\n";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>\n";
			mData += "<id>" + list.get(i).getId() + "</id>\n";
			mData += "<channelname>" + list.get(i).getChannalname() + "</channelname>\n";
			mData += "<urlrecord><![CDATA[" + list.get(i).getUrlrecord() + "]]></urlrecord>\n";
			mData += "<sernumber>" + list.get(i).getSernumber() + "</sernumber>\n";
			mData += "<starttime>" + list.get(i).getStarttime() + "</starttime>\n";
			mData += "<stoptime>" + list.get(i).getStoptime() + "</stoptime>\n";
			mData += "<status>" + list.get(i).getStatus() + "</status>\n";
			mData += "<privatechannelname>" + list.get(i).getPrivatechannelname() + "</privatechannelname>\n";
			mData += "<sizeinkb><![CDATA[" + list.get(i).getSizeinkb() + "]]></sizeinkb>\n";
			mData += "<urlpicture>" + list.get(i).getUrlpicture() + "</urlpicture>\n";
			mData += "</Item>\n";
		}
		mData += "</xml>";
		return mData;
	}

	public String getRecordForFolio(String folio) {
		String mData = "";
		Vector<Record> list = new Vector<Record>();
		list = folioServiceDBI.getRecordForFolio(folio);
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + list.size() + "\">\n";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>\n";
			mData += "<id>" + list.get(i).getId() + "</id>\n";
			mData += "<channelname>" + list.get(i).getChannalname() + "</channelname>\n";
			mData += "<urlrecord><![CDATA[" + list.get(i).getUrlrecord() + "]]></urlrecord>\n";
			mData += "<sernumber>" + list.get(i).getSernumber() + "</sernumber>\n";
			mData += "<starttime>" + list.get(i).getStarttime() + "</starttime>\n";
			mData += "<stoptime>" + list.get(i).getStoptime() + "</stoptime>\n";
			mData += "<status>" + list.get(i).getStatus() + "</status>\n";
			mData += "<privatechannelname>" + list.get(i).getPrivatechannelname() + "</privatechannelname>\n";
			mData += "<sizeinkb><![CDATA[" + list.get(i).getSizeinkb() + "]]></sizeinkb>\n";
			mData += "<urlpicture>" + list.get(i).getUrlpicture() + "</urlpicture>\n";
			mData += "</Item>\n";
		}
		mData += "</xml>";
		return mData;
	}

	public boolean updateStatusRecord(int id) {
		return folioServiceDBI.updateStatusRecord(id);
	}

	public boolean updateOTAToRoom(int id) {
		Properties prop = new Properties();
		InputStream is;
		String list = "";
		try {
			String path = DBIPool.class.getResource("").getPath();
			path = path.substring(0, path.lastIndexOf("classes"));
			is = new FileInputStream(path + "philao.properties");
			prop.load(is);
			list = prop.getProperty("ehotel.list.apk");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folioServiceDBI.updateOTAToRoom(id, list);
	}
	
	public String getAllFolio() {
		String mData = "";
		List<String> list = new ArrayList<String>();
		list = folioServiceDBI.getAllRoom();
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + list.size() + "\">\n";
		for (int i = 0; i < list.size(); i = i + 2) {
			mData += "<Item>\n";
			mData += "<room>" + list.get(i) + "</room>\n";
			mData += "<area>" + list.get(i + 1) + "</area>\n";
			mData += "</Item>\n";
		}
		mData += "</xml>";
		return mData;
	}

	public static void main(String[] args) {
		ServiceFolioPms folio = new ServiceFolioPms();
		System.out.println(folio.getAllFolio());
	}

}

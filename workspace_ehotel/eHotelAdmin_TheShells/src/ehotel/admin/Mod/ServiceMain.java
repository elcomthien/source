package ehotel.admin.Mod;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.DBI.DBI;
import com.elcom.DBI.SubProParam;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.Vod.ConfigService;
import ehotel.admin.common.ftpClient;
import ehotel.admin.dbi.ModContentServiceDBI;
import ehotel.admin.model.VodSubjectService;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.Ftp4jClient;
import ehotel.admin.util.ManagerFile;
import ehotel.core.FTPGatewayInterface;
import ehotel.core.FTPServerStruct;
import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDMod;
import ehotel.render.DBIGateway;
import ehotel.render.MOD;
import ehotel.req.server.EASReqInfo;

public class ServiceMain extends ServiceParent {
	private static final long serialVersionUID = 4315274348813470111L;
	private static final Vector<Subject> Subject = null;
	private ModContentServiceDBI modContentServiceDBI = new ModContentServiceDBI();

	// add MOD to DB
	public static final String ADD_MOD = "BEGIN EMOD.addMod(?,?,?,?,?,?,?,?,?,?,?); END;";
	public static final String DELETE_MOD = "BEGIN EMOD.removeMod(?,?); END;";

	private Object mutex = new Object();
	private LinkedList pool = new LinkedList();
	private int max_pool_size = 10;
	private ConfigService configService = new ConfigService();

	/**
	 * Constructor of the object.
	 */
	public ServiceMain() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
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
			this.showJSPpage(request, response, "/modMgn/content/MainMod.jsp");
			break;
		}
		case 1: {
			System.out.println("Get subject");
			response.setContentType("text/xml");
			String st = getsubMod();
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
			System.out.println("pageIndex:" + index);
			response.setContentType("text/xml");
			String st = getContentMp3(id, index, page);
			out.print(st);
			break;
		}
		case 3:// xoa subject
		{
			int idsub = -1;
			if (request.getParameter("id") != null) {
				idsub = Integer.parseInt(request.getParameter("id").toString());
				MOD modDBI = DBIGateway.getAMDMod();
				AMDMod cntMod = modDBI.getAMDCntMod();
				int t = cntMod.removeSubject(idsub);
			}
			break;
		}
		case 4:// xoa song
		{
			System.out.println("delete MP3");
			Vector list = new Vector();
			int subjId = -1;
			int i = 0;
			if (request.getParameter("subid") != null) {
				subjId = Integer.parseInt(request.getParameter("subid").toString());
				System.out.println("subid============" + subjId);
			}
			while (request.getParameter("id" + i) != null) {
				int id = Integer.parseInt(request.getParameter("id" + i).toString().trim());
				System.out.println("id============" + id);
				// String filename = modContentServiceDBI.getFileNameModById(id);
				// deleteModDbi(id);
				boolean success = deleteFileInFtpServer(id);
				System.out.println("delete =======" + id);
				System.out.println(success);
				// list.add(id);
				i++;
			}
			// for (int j = 0; j < list.size(); j++)
			// deleteModDbi(list.get(j));
			// EASReqInfo easInfo = new EASReqInfo();
			// int[] b = easInfo.removeMods(list);

			break;
		}
		case 5: {
			System.out.println("get list subject in mod:");
			response.setContentType("text/xml");
			int modId = -1;
			if (request.getParameter("modId") != null) {
				modId = Integer.parseInt(request.getParameter("modId").toString());
				String st = getListSubMod(modId);
				out.print(st);
			}
			break;
		}
		case 6:// get subject not is in mod
		{
			System.out.println("get list subject not in mod:");
			int modId = -1;
			response.setContentType("text/xml");
			if (request.getParameter("modId") != null) {
				modId = Integer.parseInt(request.getParameter("modId").toString());
				String st = getListSubNOTMod(modId);
				out.print(st);
			}
			break;
		}
		case 7:// update subject to mod
		{
			System.out.println("update subject mod");
			int modId = -1;
			if (request.getParameter("modId") != null) {
				modId = Integer.parseInt(request.getParameter("modId").toString());
			}
			MOD modDBI = DBIGateway.getAMDMod();
			AMDMod cntMod = modDBI.getAMDCntMod();
			int i = 0;
			String param = "(";
			while (request.getParameter("subid" + i) != null) {
				param += request.getParameter("subid" + i).toString() + ",";
				i++;
			}
			param = param.substring(0, param.length() - 1) + ")";
			boolean t = cntMod.changeSubjectOfMod(modId, param);
			out.print(t);
			System.out.println(t);
			break;
		}
		case 8:// list file mp3
		{
			System.out.println("get list file Movie FTP");
			String path = "";
			if (request.getParameter("path") != null) {
				path = request.getParameter("path").toString().trim();
			}

			String host = "";
			int port = 21;
			String user = "";
			String pass = "";

			String pathFile = getServletContext().getRealPath("") + File.separatorChar + "data" + File.separatorChar + "data.txt";
			String text = configService.ReadFile(pathFile);
			if (!(text.equalsIgnoreCase(""))) {
				String[] arr = text.split(",");
				host = arr[0];
				port = Integer.parseInt(arr[1]);
				user = arr[2];
				pass = arr[3];
			}
			response.setContentType("text/xml");
			// String Host = config._HostFtpSrc;
			// String Pass = config._PassFtpSrc;
			// int Port = config._PortFtpSrc;
			// String User = config._UserFtpSrc;
			String st = getfileFTP(path, host, user, pass, port);
			out.print(st);
			break;
		}
		case 9: {
			System.out.println("Insert Mod");
			Vector<String> list = new Vector<String>();
			Vector<String> listFail = new Vector<String>();
			Vector<String> listfiletype = new Vector<String>();
			Vector<String> listfilepath = new Vector<String>();
			int i = 0;
			int subId = -1;
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString().trim());
			}
			String hostsrc = "";
			int portsrc = 21;
			String usersrc = "";
			String passsrc = "";
			// if (request.getParameter("host") != null) {
			// hostsrc = request.getParameter("host").toString().trim();
			// }
			// if (request.getParameter("port") != null) {
			// portsrc = Integer.parseInt(request.getParameter("port").toString().trim());
			// }
			// if (request.getParameter("username") != null) {
			// usersrc = request.getParameter("username").toString().trim();
			// }
			// if (request.getParameter("password") != null) {
			// passsrc = request.getParameter("password").toString().trim();
			// }
			String pathFile = getServletContext().getRealPath("") + File.separatorChar + "data" + File.separatorChar + "data.txt";
			String text = configService.ReadFile(pathFile);
			System.out.println("config = = = = = = = = =  = = = = = = = = = = = " + text);
			if (text != "") {
				String[] arr = text.split(",");
				System.out.println(arr[0] + arr[1] + arr[2] + arr[3]);
				hostsrc = arr[0];
				portsrc = Integer.parseInt(arr[1]);
				usersrc = arr[2];
				passsrc = arr[3];
			}
			while (request.getParameter("file" + i) != null) {
				String filepath = request.getParameter("file" + i).trim();
				String filename = filepath.split("/")[filepath.split("/").length - 1];
				System.out.println("file path mod = = = = = = = = " + filepath);
				System.out.println("file name mod = = = = = = = = " + filename);
				listfilepath.add(filepath);
				// String currval = modContentServiceDBI.getSeqForMod();
				String filetype = filename.substring(filename.length() - 4);
				System.out.println("title mod = = = = " + filename.substring(0, filename.length() - filetype.length()));
				// System.out.println("file name mod save in database = = = = = = = " + currval +
				// "." + filetype);
				list.add(filename.substring(0, filename.length() - filetype.length()));
				listfiletype.add(filetype);
				i++;
			}
			System.out.println("list size = " + list.size());
			for (int j = 0; j < list.size(); j++) {
				System.out.println(subId + " - " + list.get(j));
				System.out.println("i=" + j);
				int t = modContentServiceDBI.addModDbi(subId, list.get(j));
				if (t == -2) {
					listFail.add(list.get(j));
				} else {
					String maxid = modContentServiceDBI.getMaxId();
					int id = Integer.parseInt(maxid);
					modContentServiceDBI.updateUrlForMod(id, maxid + listfiletype.get(j));
					System.out.println("after insert ----------------" + t);
					boolean success = transferMod(listfilepath.get(j), maxid + listfiletype.get(j), hostsrc, portsrc, usersrc, passsrc);
					System.out.println(success);
				}
			}
			System.out.println("======= so luong fail: " + listFail.size());
			response.setContentType("text/xml");
			String st = listAddfail(listFail);
			out.print(st);
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
		case 1:// insert subject
		{
			System.out.println("insert subject of audio!");
			response.setContentType("text/xml");
			String subjectName = "";
			String image = "";
			int parent = -1;
			String urlBg = "";
			if (request.getParameter("urlBg") != null) {
				urlBg = request.getParameter("urlBg").toString().trim();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString();
			}
			if (request.getParameter("parent") != null) {
				parent = Integer.parseInt(request.getParameter("parent").toString());
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();
				MOD modDBI = DBIGateway.getAMDMod();
				AMDMod cntMod = modDBI.getAMDCntMod();
				Subject sub = new Subject();
				sub.setName(subjectName);
				sub.setUrlImage(image);
				sub.setUrlBg(urlBg);
				sub.setParentId(parent);
				int id = cntMod.addSubject(sub, parent);
				String mData = "";
				mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
				mData += "<xml>";
				mData += "<Item>";
				mData += "<name>";
				mData += "<![CDATA[" + subjectName + "]]>";
				mData += "</name>";
				mData += "<id>\n";
				mData += id;
				mData += "</id>\n";
				mData += "<image>\n";
				mData += image;
				mData += "</image>";
				mData += "<parent>";
				mData += parent;
				mData += "</parent>";
				mData += "</Item>";
				mData += "</xml>";
				out.write(mData);
				if (id > 0) {
					// luu hinh anh subject
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + image;
					String path2 = config._pathImage + config._music + "/" + image;
					file.copy(path1, path2);
					file.deletefile(path1);
					// luu hinh anh cua subject background
					String path3 = config._temp + urlBg;
					String path4 = config._pathImage + config._music + "/" + urlBg;
					file.copy(path3, path4);
					file.deletefile(path3);
				}
			}
			break;
		}
		case 2:// edit subject
		{
			String subjectName = "";
			String image = "";
			String urlBg = "";
			if (request.getParameter("urlBg") != null) {
				urlBg = request.getParameter("urlBg").toString().trim();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString();
			}
			int subjId = -1;
			if (request.getParameter("name") != null) {
				if (request.getParameter("subid") != null) {
					subjId = Integer.parseInt(request.getParameter("subid").toString());
					subjectName = request.getParameter("name").toString();
					MOD modDBI = DBIGateway.getAMDMod();
					AMDMod cntMod = modDBI.getAMDCntMod();
					Subject sub = new Subject();
					sub.setId(subjId);
					sub.setName(subjectName);
					sub.setUrlImage(image);
					sub.setUrlBg(urlBg);
					int t = cntMod.editSubject(sub, LangID);
					out.print(t);
					if (t > 0) {
						// luu hinh anh subject
						ManagerFile file = new ManagerFile();
						String path1 = config._temp + image;
						String path2 = config._pathImage + config._music + "/" + image;
						file.copy(path1, path2);
						file.deletefile(path1);
						// luu hinh anh cua subject background
						String path3 = config._temp + urlBg;
						String path4 = config._pathImage + config._music + "/" + urlBg;
						file.copy(path3, path4);
						file.deletefile(path3);
					}
				}
			}
			break;
		}
		case 3:// edit song
		{
			System.out.println("Update song service");
			int id = -1;
			String title = "";
			String singer = "";
			String album = "";
			String def = "";
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if (request.getParameter("name") != null) {
				title = request.getParameter("name").toString();
			}
			if (request.getParameter("singer") != null) {
				singer = request.getParameter("singer");
			}
			if (request.getParameter("album") != null) {
				album = request.getParameter("album");
			}
			if (request.getParameter("lyric") != null) {
				def = request.getParameter("lyric");
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			Mod mod = new Mod();
			mod.setAlbum(album);
			mod.setSinger(singer);
			mod.setTitle(title);
			mod.setId(id);
			mod.setLyric(def);
			MOD modDBI = DBIGateway.getAMDMod();
			AMDMod cntMod = modDBI.getAMDCntMod();
			Boolean t = cntMod.updateMod(mod, LangID);
			out.print(t);
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

	private String getsubMod() {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod cntMod = modDBI.getAMDCntMod();
		Vector<Subject> subject = cntMod.getSubjects(LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < subject.size(); i++) {
			Subject item = subject.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<imageBg>\n";
			mData += item.getUrlBg();
			mData += "</imageBg>";
			mData += "<parent>";
			mData += item.getParentId();
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	// tra ve list music trong tab ADD 28.01
	private String getContentMp3(int subjId, int index, int page) {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod cntMod = modDBI.getAMDCntMod();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		if (index == -1) {
			fr = -1;
			to = -1;
		}
		Vector<Mod> mod = cntMod.getMods(subjId, LangID, fr, to);
		int count = cntMod.countModOfSubject(subjId);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\">";
		for (int i = 0; i < mod.size(); i++) {
			Mod item = mod.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getTitle() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>";
			mData += "<Album>";
			if (item.getAlbum() == null) {
				mData += "<![CDATA[" + " " + "]]>";
			} else {
				mData += "<![CDATA[" + item.getAlbum() + "]]>";
			}
			mData += "</Album>";
			mData += "<Singer>";
			if (item.getSinger() == null) {
				mData += "unknow";
			} else {
				mData += "<![CDATA[" + item.getSinger() + "]]>";
			}
			mData += "</Singer>";
			mData += "<url>";
			mData += "<![CDATA[" + cntMod.getModInfo(item.getId(), LangID).getUrl() + "]]>";
			mData += "</url>";
			mData += "<lyric>";
			mData += "<![CDATA[" + item.getLyric() + "]]>";
			mData += "</lyric>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getListSubMod(int modId) {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod cntMod = modDBI.getAMDCntMod();
		Vector<Subject> v_s = cntMod.getSubjectsInMod(modId, LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < v_s.size(); i++) {
			Subject item = v_s.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<parent>";
			mData += item.getParentId();
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getListSubNOTMod(int modId) {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod cntMod = modDBI.getAMDCntMod();
		Vector<Subject> v_1 = cntMod.getSubjectsInMod(modId, LangID);
		Vector<Subject> v_2 = cntMod.getSubjects(LangID);
		for (int i = 0; i < v_2.size(); i++) {
			int id = v_2.get(i).getId();
			for (int j = 0; j < v_1.size(); j++) {
				if (v_1.get(j).getId() == id) {
					v_2.remove(i);
					i--;
					break;
				}
			}
		}
		String mData = "";
		mData += "<?xml version=\"1.0\"  standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < v_2.size(); i++) {
			Subject item = v_2.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += item.getUrlImage();
			mData += "</image>";
			mData += "<parent>";
			mData += item.getParentId();
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getfileFTP(String path, String Host, String User, String Pass, int Port) {
		ftpClient ftp = new ftpClient(Host, User, Pass, Port);
		ftp.connect();
		Vector<String> v_files = new Vector();
		Vector<String> v_folder = new Vector();
		v_files = ftp.getFiles(path);
		v_folder = ftp.getFolder(path);
		ftp.close();
		for (int i = 0; i < v_folder.size(); i++) {
			for (int j = 0; j < v_files.size(); j++) {
				if (v_files.get(j).equals(v_folder.get(i))) {
					v_files.remove(j);
					j--;
				}
			}
		}
		for (int i = 0; i < v_folder.size(); i++) {
			if (v_folder.get(i).equals(".") || v_folder.get(i).equals("..")) {
				v_folder.remove(i);
				i--;
			}
		}
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < v_folder.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += v_folder.get(i);
			mData += "</name>";
			mData += "<type>";
			mData += "1";
			mData += "</type>";
			mData += "</Item>";
		}
		for (int i = 0; i < v_files.size(); i++) {

			mData += "<Item>";
			mData += "<name>";
			mData += v_files.get(i);
			mData += "</name>";
			mData += "<type>";
			mData += "0";
			mData += "</type>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String listAddfail(Vector<String> list) {
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += list.get(i);
			mData += "</name>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	// ////// Set up Dbi and connect to database /////////

	public Vector executeSubPro(String sqlSubPro, Vector params) throws ClassNotFoundException {
		DBI dbi = null;
		try {
			// tao moi 1 dbi 8.1
			dbi = getNewDBI();
			return (dbi.executeSubPro(sqlSubPro, params));
		} catch (SQLException ex) {
		} catch (RemoteException ex) {
			dbi = null;
			clearPool();
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex1) {
			}
			return executeSubPro(sqlSubPro, params);
		} finally {
			putDBI(dbi);
			return null;
		}
	}

	private DBI getNewDBI() throws RemoteException, ClassNotFoundException {
		DBI dbi = null;
		int attemptNo = 1;
		while (attemptNo < 3) {
			try {
				dbi = (DBI) Naming.lookup("rmi://" + getHost() + ":" + getPort() + "/" + getDbiservicename());
			} catch (Exception ex) {
				ex.printStackTrace();
				String msg = "Connect to the DBI failed in times: " + attemptNo;
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex1) {
				}
			}
			attemptNo++;
		}
		return dbi;
	}

	public void clearPool() {
		synchronized (mutex) {
			pool.clear();
		}
	}

	public void putDBI(DBI dbi) {
		synchronized (mutex) {
			if (dbi != null && pool.size() < max_pool_size - 1) {
				pool.addFirst(dbi);
			}
		}
	}

	public String getHost() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.host");
	}

	public String getPort() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.port");
	}

	public String getDbiservicename() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.dbiservicename");
	}

	// method add MOD to DB
	// public int addModDbi(int subjectid, String title) {
	// int seqout = -1;
	// Vector params = new Vector(11);
	// SubProParam in = null;
	// in = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
	// params.add(in); // 0
	// in = new SubProParam(new java.lang.String(title), SubProParam.IN);
	// params.add(in); // 1
	// in = new SubProParam(new java.lang.String(""), SubProParam.IN);
	// params.add(in); // 2
	// in = new SubProParam(new java.lang.String(""), SubProParam.IN);
	// params.add(in); // 3
	// in = new SubProParam(new java.lang.String(""), SubProParam.IN);
	// params.add(in); // 4
	// in = new SubProParam(new java.lang.String(""), SubProParam.IN);
	// params.add(in); // 5
	// in = new SubProParam(new java.lang.String(title), SubProParam.IN);
	// params.add(in); // 6
	// in = new SubProParam(new java.lang.String(""), SubProParam.IN);
	// params.add(in); // 7
	// in = new SubProParam(new java.lang.String(""), SubProParam.IN);
	// params.add(in); // 8
	// in = new SubProParam(new BigDecimal(0), SubProParam.IN);
	// params.add(in); // 9
	//
	// SubProParam out = new SubProParam(new String(), SubProParam.OUT);
	// params.add(out); // 10
	//
	// try {
	// executeSubPro(ADD_MOD, params);
	// // System.out.println("B1");
	// // out = (SubProParam)params.get(10);
	// // System.out.println("B2");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// // SubProParam paramOUT = (SubProParam) params.get(10);
	// // String temp = paramOUT.getString();
	// // System.out.println("ADD_NSD:" + temp);
	//
	// return 1;
	// }

	public void deleteModDbi(int modid) {
		int seqout = -1;
		Vector params = new Vector(11);
		SubProParam in = new SubProParam(new BigDecimal(modid), SubProParam.IN);
		params.add(in); // 0
		SubProParam out = new SubProParam(new BigDecimal(seqout), SubProParam.OUT);
		params.add(out); // 1
		try {
			executeSubPro(DELETE_MOD, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean transferMod(String filepathSrc, String filenameServer, String hostSrc, int portSrc, String userSrc, String passSrc) {
		boolean flag = true;
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String hostserver = config._HostFtpServer;

		String filepathserver = config._FilePathserver;
		String folder = config._FolderMusic;

		try {
			// cau hinh rmi
			FTPGatewayInterface ftpgateway;
			ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + hostserver + ":2099/elc_ftpgateway");
			System.out.println("RMI==== " + "rmi://" + hostserver + ":2099/elc_ftpgateway");

			// down load file from src to server
			System.out.println("filepath SRC  = = = = " + filepathSrc);
			FTPServerStruct server = new FTPServerStruct(hostSrc, portSrc, userSrc, passSrc, filepathSrc);
			System.out.println("file path inserver = = = = = " + filepathserver + "/" + folder + "/" + filenameServer);
			UUID id = ftpgateway.download(server, hostserver, filepathserver + "/" + folder + "/" + filenameServer, 5000);

			if (ftpgateway.getStatus(id) == FTPServerStruct.STATUS_COMPLETED) {
				System.out.println("download completed");
			} else if (ftpgateway.getStatus(id) == FTPServerStruct.STATUS_NOT_COMPLETED) {
				System.out.println("download didn't complete");
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean deleteFileInFtpServer(int id) {

		String filename = modContentServiceDBI.getFileNameModById(id);
		deleteModDbi(id);
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String host = config._HostFtpServer;
		int port = config._PortFtpServer;
		String user = config._UserFtpServer;
		String pass = config._PassFtpServer;
		String folder = config._FolderMusic;
		try {
			Ftp4jClient ftp4j = new Ftp4jClient(host, port, user, pass);
			ftp4j.connect();
			ftp4j.deleteFileFTPMod(filename, folder);
			ftp4j.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		ServiceMain s = new ServiceMain();
	}

}
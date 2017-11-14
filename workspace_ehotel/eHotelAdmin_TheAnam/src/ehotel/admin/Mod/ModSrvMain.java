package ehotel.admin.Mod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.eod.util.UnicodeConverter;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.common.ftpClient;
import ehotel.admin.dbi.ModContentServiceDBI;
import ehotel.admin.model.VodSubjectService;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDMod;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;
import ehotel.render.MOD;

public class ModSrvMain extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private ModContentServiceDBI modContentServiceDBI = new ModContentServiceDBI();

	public ModSrvMain() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
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
			this.showJSPpage(request, response,
					"/modMgn/service/MainModSrv.jsp");
			break;
		}
		case 1:// load menu
		{
			System.out.println("Get subject ModSrv");
			response.setContentType("text/xml");
			// String st = getsubMod();
			String st = getModSubjectService();
			out.print(st);
			break;
		}

		case 2:// xoa subject
		{
			int idsub = -1;
			if (request.getParameter("id") != null) {
				idsub = Integer.parseInt(request.getParameter("id").toString());
				MOD modDBI = DBIGateway.getAMDMod();
				AMDMod cntMod = modDBI.getAMDSvcMod();
				int t = cntMod.removeSubject(idsub);
				System.out.println(t);
			}
			break;
		}
		// them vao lay info tu audio 16.1
		case 3: {
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
			String st = getContentMp3(id, index, page);
			out.print(st);
			break;
		}
		case 9: {
			System.out.println("Get content tab ADD");
			int id = -1;
			if (request.getParameter("SubId") != null) {
				id = Integer.parseInt(request.getParameter("SubId").toString());
			}
			response.setContentType("text/xml");
			String st = getContentMp3All(id);
			out.print(st);
			break;
		}
		case 4:// add mod to service
		{
			String id = "";
			int subid = -1;
			if (request.getParameter("SubId") != null) {
				subid = Integer.parseInt(request.getParameter("SubId")
						.toString());
			}
			if (request.getParameter("vodId") != null) {
				id = request.getParameter("vodId").toString().trim();
			}
			MOD modDBI = DBIGateway.getAMDMod();
			AMDMod srvMod = modDBI.getAMDSvcMod();
			String chuoi = "(" + id + ")";
			boolean t = srvMod.addMod(subid, chuoi);
			out.print(t);
			System.out.println("Add service MOD:" + t);
			break;
		}
		case 5:// delete service MOD
		{
			System.out.println("Delete MOD Service");
			ILOGIN iuser = DBIGateway.getILogin();
			String ipAdress = request.getRemoteAddr();
			if (iuser.isAdmin(ipAdress)
					|| iuser.checkRoleUser(ipAdress, Def._roleMOD)) {
				String id = "";
				int subid = -1;
				// sua lai cho nay tu vodId => modId0
				if (request.getParameter("vodId") != null) {
					id = request.getParameter("vodId").toString().trim();
				} else if (request.getParameter("modId0") != null) {
					id = request.getParameter("modId0").toString().trim();
				}
				if (request.getParameter("SubId") != null) {
					subid = Integer.parseInt(request.getParameter("SubId")
							.toString().trim());
				}
				String chuoi = "(" + id + ")";
				MOD modDBI = DBIGateway.getAMDMod();
				AMDMod srvMod = modDBI.getAMDSvcMod();
				boolean t = srvMod.removeMods(subid, chuoi);
			} else {
				out.print(-1);
			}
			break;
		}
		case 6:// get subject in MOD
		{
			int id = -1;
			response.setContentType("text/xml");

			if (request.getParameter("modId") != null) {
				id = Integer.parseInt(request.getParameter("modId").toString()
						.trim());
			}
			String st = getsubAndMod(id);
			out.print(st);
			break;
		}
		case 7: {
			int id = -1;
			response.setContentType("text/xml");

			if (request.getParameter("modId") != null) {
				id = Integer.parseInt(request.getParameter("modId").toString()
						.trim());
			}
			String st = getsubOutMod(id);
			out.print(st);
			break;
		}
		case 8://
		{
			int i = 0;
			int modId = -1;
			if (request.getParameter("modId") != null) {
				modId = Integer.parseInt(request.getParameter("modId")
						.toString().trim());
			}
			Vector<Integer> list = new Vector<Integer>();
			while (request.getParameter("subid" + i) != null) {
				int id = Integer.parseInt(request.getParameter("subid" + i)
						.toString().trim());
				list.add(id);
				i++;
			}
			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1);
			param += ")";
			MOD modDBI = DBIGateway.getAMDMod();
			AMDMod srvMod = modDBI.getAMDSvcMod();
			srvMod.changeSubjectOfMod(modId, param);
		}
		case 10: // change status vod subject service
		{
			int subId = -1;
			int invisible = -1;
			if (request.getParameter("subid") != null) {
				subId = Integer.parseInt(request.getParameter("subid")
						.toString());
			}
			if (request.getParameter("invisible") != null) {
				invisible = Integer.parseInt(request.getParameter("invisible")
						.toString());
			}
			if (invisible == -1) {
				out.write("f");
				break;
			}
			boolean flag = true;
			if (invisible == 0)
				flag = modContentServiceDBI.updateInvisibleModSubjectService(
						subId, 1);
			else if (invisible == 1)
				flag = modContentServiceDBI.updateInvisibleModSubjectService(
						subId, 0);
			if (flag)
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
		case 1:// /insert subject
		{
			System.out.println("insert subject (service->audio)!");
			response.setContentType("text/xml");
			String subjectName = "";
			String image = "";
			String urlBg = "";
			int parent = -1;
			if (request.getParameter("urlBg") != null) {
				urlBg = request.getParameter("urlBg").toString().trim();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString();
			}
			if (request.getParameter("parent") != null) {
				parent = Integer.parseInt(request.getParameter("parent")
						.toString());
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();
				MOD modDBI = DBIGateway.getAMDMod();
				AMDMod srvMod = modDBI.getAMDSvcMod();
				Subject sub = new Subject();
				sub.setName(subjectName);
				sub.setUrlImage(image);
				sub.setUrlBg(urlBg);
				sub.setParentId(parent);
				// ko can sua cho nay vi luu db chi co ten ko luu du thua du
				// lieu 18.1
				int id = srvMod.addSubject(sub, LangID);
				if (id > 0) {
					// luu hinh anh subject
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + image;
					String path2 = config._pathImage + config._music + "/"
							+ image;
					file.copy(path1, path2);
					file.deletefile(path1);
					// luu hinh anh cua subject background
					String path3 = config._temp + urlBg;
					String path4 = config._pathImage + config._music + "/"
							+ urlBg;
					file.copy(path3, path4);
					file.deletefile(path3);
				}
			}
			break;
		}
		case 2:// edit subject
		{
			System.out.println("update subject (service->audio)!");
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
					subjId = Integer.parseInt(request.getParameter("subid")
							.toString());
					subjectName = request.getParameter("name").toString();
					MOD modDBI = DBIGateway.getAMDMod();
					AMDMod cntMod = modDBI.getAMDSvcMod();
					Subject sub = new Subject();
					sub.setName(subjectName);
					sub.setUrlImage(image);
					sub.setId(subjId);
					sub.setUrlBg(urlBg);
					int t = cntMod.editSubject(sub, LangID);
					out.print(t);
					if (t > 0) {
						// luu hinh anh subject
						ManagerFile file = new ManagerFile();
						String path1 = config._temp + image;
						String path2 = config._pathImage + config._music + "/"
								+ image;
						System.out.println("path1: " + path1);
						System.out.println("path2: " + path2);
						file.copy(path1, path2);
						file.deletefile(path1);
						// luu hinh anh cua subject background
						String path3 = config._temp + urlBg;
						String path4 = config._pathImage + config._music + "/"
								+ urlBg;
						System.out.println("path3: " + path3);
						System.out.println("path4: " + path4);
						file.copy(path3, path4);
						file.deletefile(path3);
					}
				}
			}
			break;
		}
		case 3:// update song
		{
			System.out.println("Update song service");
			int id = -1;
			String title = "";
			String singer = "";
			String album = "";
			String def = "";

			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			if (request.getParameter("name") != null) {
				title = request.getParameter("name").toString();
				System.out.println("title2 = = = = = "
						+ UnicodeConverter.decodeUnicode(title));
				System.out.println("title3 = = = = = "
						+ UnicodeConverter.encodeUnicode(title));
				// title=UtilString.uni2ent2ndTry(title);
				// title = UnicodeConverter.encodeUnicode(title);

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
				def = def.replaceAll(
						"<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			Mod mod = new Mod();
			mod.setAlbum(album);
			mod.setSinger(singer);
			mod.setTitle(title);
			mod.setId(id);
			mod.setLyric(def);
			MOD modDBI = DBIGateway.getAMDMod();
			AMDMod cntMod = modDBI.getAMDSvcMod();
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

	@SuppressWarnings("unused")
	private String getsubMod() {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod srvMod = modDBI.getAMDSvcMod();
		Vector<Subject> subject = srvMod.getSubjects(LangID);

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
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getContentMp3(int subjId, int index, int page) {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod srvMod = modDBI.getAMDSvcMod();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		Vector<Mod> mod = srvMod.getMods(subjId, LangID, fr, to);
		int count = srvMod.countModOfSubject(subjId);
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
			mData += "<![CDATA["
					+ srvMod.getModInfo(item.getId(), LangID).getUrl() + "]]>";
			mData += "</url>";
			mData += "<lyric>";
			mData += "<![CDATA[" + item.getLyric() + "]]>";
			mData += "</lyric>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	// bo sung liet ke danh sach day du MOD 22.2
	private String getContentMp3All(int subjId) {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod srvMod = modDBI.getAMDSvcMod();
		Vector<Mod> mod = srvMod.getMods(subjId, LangID, -1, -1);
		int count = srvMod.countModOfSubject(subjId);
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
			mData += "<![CDATA["
					+ srvMod.getModInfo(item.getId(), LangID).getUrl() + "]]>";
			mData += "</url>";
			mData += "<lyric>";
			mData += "<![CDATA[" + item.getLyric() + "]]>";
			mData += "</lyric>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getsubAndMod(int modId) {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod srvMod = modDBI.getAMDSvcMod();
		Vector<Subject> subject = srvMod.getSubjectsInMod(modId, LangID);
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
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getsubOutMod(int modId) {
		MOD modDBI = DBIGateway.getAMDMod();
		AMDMod srvMod = modDBI.getAMDSvcMod();
		Vector<Subject> subject = srvMod.getSubjectsOutMod(modId, LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < subject.size(); i++) {
			Subject item = subject.get(i);
			System.out.println("Parent id:" + item.getParentId());
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
			mData += -1;
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	@SuppressWarnings("unused")
	private String getfileFTP(String path, String Host, String User,
			String Pass, int Port) {
		ftpClient ftp = new ftpClient(Host, User, Pass, Port);
		ftp.connect();
		Vector<String> v_files = new Vector<String>();
		Vector<String> v_folder = new Vector<String>();
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
			System.out.println("file:" + v_files.get(i));
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

	private String getModSubjectService() {
		List<VodSubjectService> list = new ArrayList<VodSubjectService>();
		list = modContentServiceDBI.getVodSubjectService(LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getSubjectname() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += list.get(i).getSubjectid();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += list.get(i).getSubjectimage();
			mData += "</image>";
			mData += "<imageBg>\n";
			mData += list.get(i).getSubjectimage();
			mData += "</imageBg>";
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "<invisible>";
			mData += list.get(i).getSubjectinvisible();
			mData += "</invisible>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
}
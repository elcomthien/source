package ehotel.admin.Vod;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.common.GetImages;
import ehotel.admin.common.ftpClient;
import ehotel.admin.common.imdFilm;
import ehotel.admin.dbi.IMBroker;
import ehotel.admin.dbi.VodContentServiceDBI;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.Ftp4jClient;
import ehotel.admin.util.ManagerFile;
import ehotel.core.FTPGatewayInterface;
import ehotel.core.FTPServerStruct;
import ehotel.core.MediaFileParserInterface;
import ehotel.core.MetadataVideo;
import ehotel.dao.eVODDao;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;
import ehotel.inter.AMDVod;
import ehotel.inter.IEVS;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;
import ehotel.render.VOD;
import ehotel.req.connect.URLConstant;
import ehotel.req.server.EVSReqInfo;

@SuppressWarnings("serial")
public class Contentvod extends ServiceParent {
	static IMBroker broker = IMBroker.getInstance();
	private VodContentServiceDBI contentServiceDBI = new VodContentServiceDBI();

	private ConfigService configService = new ConfigService();
	private VodContentServiceDBI vodContentServiceDBI = new VodContentServiceDBI();

	/**
	 * Constructor of the object.
	 */
	public Contentvod() {
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
			this.showJSPpage(request, response, "/vodMgn/content/VodCtnMain.jsp");
			break;
		}
		case 1: // get list subject content
		{
			System.out.println("Get subject content");
			response.setContentType("text/xml");
			String st = getsubVodCtn();
			out.print(st);
			break;
		}
		case 2:// get movie content
		{
			System.out.println("Get list vod content");
			List<VodPercent> list = new ArrayList<VodPercent>();
			list = contentServiceDBI.getVodTransferPercent();
			if (list.size() > 0) {
				ConfigLoader loader = new ConfigLoader();
				Config config = loader.getConfig();
				String hostserver = config._HostFtpServer;
				try {
					FTPGatewayInterface ftpgateway;
					ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + hostserver + ":2099/elc_ftpgateway");
					for (int i = 0; i < list.size(); i++) {
						Float temp = (float) (ftpgateway.transfered(list.get(i).getUid()) * 100)
								/ ftpgateway.fileSize(list.get(i).getUid());
						Float compare = Float.NaN;

						if (temp.compareTo(compare) == 0) {
							int vodId = Integer.parseInt(list.get(i).getFilename().substring(0, list.get(i).getFilename().length() - 4));
							vodContentServiceDBI.updateStatusInvisibleVod(vodId, list.get(i).getFilename());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int page = 5;
			int subId = -1;
			int index = 0;
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			response.setContentType("text/xml");
			String st = getListMovie(subId, index, page);
			out.print(st);
			break;
		}
		case 3:// search movie content
		{
			System.out.println("Search vod content");
			int page = 5;
			int subId = -1;
			int index = 0;
			String text = "";
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString().trim());
			}
			if (request.getParameter("text") != null) {
				text = request.getParameter("text").toString().trim();
			}
			response.setContentType("text/xml");
			String st = searchVod(subId, index, page, text);
//			System.out.println(st);
			out.print(st);
			break;
		}
		case 4:// delete Movie
		{
			ILOGIN iuser = DBIGateway.getILogin();
			String ipAdress = request.getRemoteAddr();
			if (iuser.isAdmin(ipAdress) || iuser.checkRoleUser(ipAdress, Def._roleVIDEO)) {
				int VodId = -1;
				if (request.getParameter(Def.VodId) != null) {
					VodId = Integer.parseInt(request.getParameter(Def.VodId).toString());
				}
				System.out.println("delete Vod Id:" + VodId);

				if (deleteFileInFtpServer(VodId))
					out.print(true);
				else
					out.print(false);
			} else {
				out.write(-1);
			}
			break;
		}
		case 5: // change subject content
		{
			int subId = -1;
			int VodId = -1;
			if (request.getParameter(Def.VodId) != null) {
				VodId = Integer.parseInt(request.getParameter(Def.VodId).toString());
			}
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			VOD vodDBI = DBIGateway.getAMDVod();
			AMDVod ctnMod = vodDBI.getAMDCntVod();
			ctnMod.changeSubjectOfVod(VodId, subId);
			System.out.println("change Vod Id:" + VodId + " to subid:" + subId);
			break;
		}
		case 6:// get ftp file
		{
			System.out.println("get list file FTP");
			String path = "";
			if (request.getParameter("path") != null) {
				path = request.getParameter("path").toString().trim();
			}
			response.setContentType("text/xml");
			ConfigLoader loader = new ConfigLoader();
			Config config = loader.getConfig();
			String Host = config._HostFtpServer;
			String Pass = config._PassFtpServer;
			int Port = config._PortFtpServer;
			String User = config._UserFtpServer;
			String st = getfileFTP(path, Host, User, Pass, Port);
			out.print(st);
			break;
		}
		case 7: // add trailer for vod content
		{
			System.out.println("add trailer");
			String path = "";
			if (request.getParameter("path") != null) {
				path = request.getParameter("path").toString().trim();
			}
			int VodId = -1;
			if (request.getParameter(Def.VodId) != null) {
				VodId = Integer.parseInt(request.getParameter(Def.VodId).toString());
			}
			EVSReqInfo evs = new EVSReqInfo();
			evs.addTrailer(VodId, path);
			break;
		}
		case 8:// get ftp file
		{
			System.out.println("get list file FTP");
			String path = "";
			if (request.getParameter("path") != null) {
				path = request.getParameter("path").toString().trim();
			}
			response.setContentType("text/xml");
			ConfigLoader loader = new ConfigLoader();
			Config config = loader.getConfig();
			String Host = config._HostFtpServer;
			String Pass = config._PassFtpServer;
			int Port = config._PortFtpServer;
			String User = config._UserFtpServer;
			String st = getfileFTP(path, Host, User, Pass, Port);
			out.print(st);
			break;
		}
		case 9: {
			int VodId = -1;
			if (request.getParameter(Def.VodId) != null) {
				VodId = Integer.parseInt(request.getParameter(Def.VodId).toString());
			}
			response.setContentType("text/xml");
			String st = getSubtitle(VodId);
			out.print(st);
			break;
		}
		case 10:// add subtitle
		{
			System.out.println("Add subtitle!");
			Vector<Integer> lang = new Vector<Integer>();
			Vector<String> name = new Vector<String>();
			Vector<Integer> ID = new Vector<Integer>();
			Vector<SubTitle> subtitle = new Vector<SubTitle>();
			int VodId = -1;
			String hostsrc = "";
			int portsrc = 21;
			String usersrc = "";
			String passsrc = "";

			String pathFile = getServletContext().getRealPath("") + File.separatorChar + "data" + File.separatorChar + "data.txt";
			String text = configService.ReadFile(pathFile);
			if (text != "") {
				String[] arr = text.split(",");
				hostsrc = arr[0];
				portsrc = Integer.parseInt(arr[1]);
				usersrc = arr[2];
				passsrc = arr[3];
			}
			if (request.getParameter(Def.VodId) != null) {
				VodId = Integer.parseInt(request.getParameter(Def.VodId).toString());
			}
			int i = 0;
			while (request.getParameter("lang" + i) != null) {
				int l = Integer.parseInt(request.getParameter("lang" + i).toString().trim());
				lang.add(l);
				i++;
			}
			i = 0;
			while (request.getParameter("name" + i) != null) {
				String n = request.getParameter("name" + i).toString();
				String[] arr = n.split("/");
				String filename = arr[arr.length - 1];
				String filetype = filename.substring(filename.lastIndexOf("."), filename.length());
				String namesave = VodId + "_" + getLang(lang.get(i)) + filetype;
				transferVod(n, namesave, "", hostsrc, portsrc, usersrc, passsrc);
				name.add(arr[arr.length - 1]);
				i++;
			}
			i = 0;
			while (request.getParameter("id" + i) != null) {
				int id = Integer.parseInt(request.getParameter("id" + i).toString().trim());
				ID.add(id);
				i++;
			}
			for (i = 0; i < lang.size(); i++) {
				System.out.println("lang:" + lang.get(i) + " ID:" + ID.get(i) + " Name:" + name.get(i));
				SubTitle sub = new SubTitle();
				sub.setName(name.get(i));
				sub.setUrl(name.get(i));
				sub.setLangId(lang.get(i));
				sub.setSubId(ID.get(i));
				subtitle.add(sub);
			}
			for (i = 0; i < subtitle.size(); i++) {
				String filetype = subtitle.get(i).getUrl()
						.substring(subtitle.get(i).getUrl().lastIndexOf("."), subtitle.get(i).getUrl().length());
				String namesave = VodId + "_" + getLang(lang.get(i)) + filetype;
				vodContentServiceDBI.addSubtitleDbi(VodId, namesave, subtitle.get(i).getLangId());
				System.out.println("VOD:" + VodId);
				System.out.println(subtitle.get(i).getUrl());
			}
			break;
		}
		case 11:// delete subtitle
		{
			int i = 0;

			while (request.getParameter("id" + i) != null) {
				int id = Integer.parseInt(request.getParameter("id" + i).toString().trim());
				System.out.println("id content vod for delete sub = = = = " + id);
				String filename = contentServiceDBI.getSubVodById(id);

				System.out.println("delete sub:  = = = = = " + filename);
				deleteFileSubInFtpServer(filename);
				vodContentServiceDBI.deleteSubtitleDbi(id);
				i++;
			}
			break;
		}
		case 12: {
			String title = "";
			if (request.getParameter("title") != null) {
				title = request.getParameter("title").toString();
			}
			response.setContentType("text/xml");
			String st = getInfoFilm(request, title);
			out.print(st);
			break;
		}
		case 13:// get current upload movie
		{
			String value = request.getParameter("value");
			response.setContentType("text/xml");
			try {
				String st = "";
				if (value != "")
					st = gerProcessrun(value);
				out.print(st);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case 14:// stop upload movie
		{
			String uuid = "";
			String filename = "";
			if (request.getParameter("filename") != null) {
				filename = request.getParameter("filename").toString().trim();
			}
			if (request.getParameter("uuid") != null) {
				uuid = request.getParameter("uuid").toString().trim();
			}
			System.out.println("Stop upload movie:" + filename);
			boolean flag = deleteTransferVod(uuid, filename);
			if (flag)
				out.print("Transfer file deleted");
			else
				out.print("Delete transfer file unsuccess");
			break;
		}
		case 15:// list file movie ftp
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
			String st = getfileFTP(path, host, user, pass, port);
			out.print(st);
			break;
		}
		case 16: {
			List<VodPercent> list = new ArrayList<VodPercent>();
			list = contentServiceDBI.getVodTransferPercent();
			String rs = "";
			if (list.size() > 0) {
				ConfigLoader loader = new ConfigLoader();
				Config config = loader.getConfig();
				String hostserver = config._HostFtpServer;
				try {
					FTPGatewayInterface ftpgateway;
					ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + hostserver + ":2099/elc_ftpgateway");
					for (int i = 0; i < list.size(); i++) {
						Float temp = (float) (ftpgateway.transfered(list.get(i).getUid()) * 100)
								/ ftpgateway.fileSize(list.get(i).getUid());
						Float compare = Float.NaN;

						if (temp.compareTo(compare) == 0) {
							int vodId = Integer.parseInt(list.get(i).getFilename().substring(0, list.get(i).getFilename().length() - 4));
							vodContentServiceDBI.updateStatusInvisibleVod(vodId, list.get(i).getFilename());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				list = new ArrayList<VodPercent>();
				list = contentServiceDBI.getVodTransferPercent();
				for (int i = 0; i < list.size(); i++) {
					rs += list.get(i).getFilename() + ",,," + list.get(i).getUid() + ",,," + list.get(i).getNameview() + ",,,";
				}
				if (rs != "")
					rs = rs.substring(0, rs.length() - 3);
			}
			out.print(rs);
			break;
		}
		case 17: {
			String name = request.getParameter("name");
			System.out.println("delete sub:  = = = = = 17 " + name);
			deleteFileSubInFtpServer(name);
			contentServiceDBI.deleteSubVodByUrl(name);
			break;
		}
		case 18: {
			String filename = request.getParameter("filename");
			int vodId = Integer.parseInt(filename.substring(0, filename.length() - 4));
			vodContentServiceDBI.updateStatusInvisibleVod(vodId, filename);
			out.print("success!");
			break;
		}
		case 100: {
			if (request.getParameter("link") != null) {
				String name = request.getParameter("link");
				String path = vodContentServiceDBI.getSrcVodToPlayVlc();
				String link = path + name;
				out.print(link);
			}
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
		case 1: {
			System.out.println("insert subject VOD!");
			response.setContentType("text/xml");
			String subjectName = "";
			String image = "";
			int parent = -1;
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString();
			}
			if (request.getParameter("parent") != null) {
				parent = Integer.parseInt(request.getParameter("parent").toString());
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();
				VOD vodDBI = DBIGateway.getAMDVod();
				AMDVod ctnMod = vodDBI.getAMDCntVod();
				int id = ctnMod.addSubject(subjectName, config._movie + "/" + image, parent);
				if (id > 0) {
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + "/" + image;
					String path2 = config._pathMovies + "/" + image;
					file.copy(path1, path2);
					file.deletefile(path1);
				}
				out.write(id);
			}
			break;
		}
		case 2:// update subject
		{
			System.out.println("update subject VOD!");
			String subjectName = "";
			String image = "";
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString();
			}
			int subjId = -1;
			if (request.getParameter("name") != null) {
				if (request.getParameter(Def.SubId) != null) {
					subjId = Integer.parseInt(request.getParameter(Def.SubId).toString());
					subjectName = request.getParameter("name").toString();
					VOD vodDBI = DBIGateway.getAMDVod();
					AMDVod ctnMod = vodDBI.getAMDCntVod();
					int t = ctnMod.editSubject(subjId, subjectName, config._movie + "/" + image, LangID);
					out.print(t);
					if (t > 0) {
						ManagerFile file = new ManagerFile();
						String path1 = config._temp + "/" + image;
						String path2 = config._pathMovies + "/" + image;
						file.copy(path1, path2);
						file.deletefile(path1);
					}
				}
			}
			break;
		}
		case 3: {
			System.out.println("delete subject VOD!");
			int subjId = -1;
			if (request.getParameter(Def.SubId) != null) {
				subjId = Integer.parseInt(request.getParameter(Def.SubId).toString());
				VOD vodDBI = DBIGateway.getAMDVod();
				AMDVod ctnMod = vodDBI.getAMDCntVod();
				ctnMod.removeSubject(subjId);
			}
			break;
		}
		case 4: {
			System.out.println("Update VOD");
			updateVod(request, response);
			break;
		}
		// insert video from tab Content 14.1
		case 5: {
			System.out.println("Insert VOD");
			String name = "";
			String actors = "";
			String director = "";
			String def = "";
			String image = "";
			String file = "";
			int subId = -1;
			String price = "0";
			String money = "";
			String hostsrc = "";
			int portsrc = 21;
			String usersrc = "";
			String passsrc = "";
			// String ipserver = "";
			if (request.getParameter("name") != null) {
				name = request.getParameter("name").toString();
			}
			if (request.getParameter("actor") != null) {
				actors = request.getParameter("actor").toString();
			}
			if (request.getParameter("director") != null) {
				director = request.getParameter("director").toString();
			}
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString();
			}
			if (request.getParameter("Desc") != null) {
				def = request.getParameter("Desc").toString();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
				// System.out.println("des=" + def);
			}
			if (request.getParameter("file") != null) {
				file = request.getParameter("file").toString();
			}
			if (request.getParameter("SubId") != null) {
				subId = Integer.parseInt(request.getParameter("SubId").toString().trim());
			}
			if (request.getParameter("price") != null) {
				price = request.getParameter("price").toString().trim();
			}
			if (request.getParameter("money") != null) {
				money = request.getParameter("money").toString().trim();
			}

			String pathFile = getServletContext().getRealPath("") + File.separatorChar + "data" + File.separatorChar + "data.txt";
			String text = configService.ReadFile(pathFile);
			if (text != "") {
				String[] arr = text.split(",");
				hostsrc = arr[0];
				portsrc = Integer.parseInt(arr[1]);
				usersrc = arr[2];
				passsrc = arr[3];
			}

			String[] arr = file.split("/");
			String filename = arr[arr.length - 1];
			filename.replaceAll("'", " i");
			Vod vod = new Vod();
			vod.setTitle(name);
			vod.setActors(actors);
			vod.setDirector(director);
			vod.setPlot(def);
			vod.setPoster(image);
			vod.setFilePath(filename);
			vod.setCurrency(price);
			vod.setIUnit(money);
			addVod(subId, vod);
			String maxid = contentServiceDBI.getMaxIdVodContent();
			String filetype = filename.substring(filename.length() - 3, filename.length());
			contentServiceDBI.updateUrlForVod(Integer.parseInt(maxid), maxid + "." + filetype);
			boolean rs = transferVod(file, maxid + "." + filetype, name, hostsrc, portsrc, usersrc, passsrc);
			System.out.println("rs = = = " + rs);
			if (rs) {
				System.out.println("Transfer vod content is success!");
				ManagerFile filem = new ManagerFile();
				String path1 = config._temp + image;
				String path2 = config._pathImage + config._movie + "/" + image;
				filem.copy(path1, path2);
				filem.deletefile(path1);
			} else {
				System.out.println("Transfer vod content is unsuccess!");
				vodContentServiceDBI.deleteVodContentById(Integer.parseInt(maxid));
			}
			out.print(rs);
			break;
		}
		default:
			break;
		}
	}

	public int addVod(int subjId, Vod vod) {
		int seq = -1;
		int storageid = 1;
		vodContentServiceDBI.addVodDbi(subjId, storageid, vod.getFilePath(), vod.getTitle(), vod.getDirector(), vod.getActors(),
				vod.getPlot(), vod.getPoster(), vod.getCurrency(), vod.getIUnit(), "0", seq);

		return seq;
	}

	public MetadataVideo readMetaDataVideo(String fileName) {
		System.out.println("Reading metadata video[fileName=" + fileName + "url=" + URLConstant.MEDIAPARSE + "]");
		MetadataVideo video = null;
		try {
			MediaFileParserInterface mediaParse = (MediaFileParserInterface) Naming.lookup(URLConstant.MEDIAPARSE);
			video = mediaParse.readMetadataVideo(fileName);
			if (video != null)
				System.out.println("Completed Reading metadata video[fileName=" + fileName + "] and result[" + video.toString() + "]");
			else
				System.out.println("mediaParse return value null.Maybe not found file");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return video;
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

	private String getsubVodCtn() {
		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDCntVod();
		Vector<Subject> subject = SrvMod.getSubjects(LangID);

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
			mData += item.getParentId();
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}

	private String getListMovie(int subId, int index, int page) {

		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod ctnMod = VodDBI.getAMDCntVod();
		IEVS evs = VodDBI.getEVSVod();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;

		int count = ctnMod.countVodOfSubject(subId);
		Vector<Vod> vod = ctnMod.getVods(subId, LangID, fr, to);

		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count='" + count + "'>";
		System.out.println("Content:" + count);
		for (int i = 0; i < vod.size(); i++) {
			String actors = "";
			if (vod.get(i).getActors() != null)
				actors = vod.get(i).getActors();

			if (actors.length() > 40)
				actors = actors.substring(0, 25) + "...";
			mData += "<Item>";
			mData += "<link>";
			mData += "<![CDATA[" + evs.getUrl(vod.get(i).getId(), VOD.VOD) + "]]>";
			mData += "</link>";
			mData += "<name>";
			mData += "<![CDATA[" + vod.get(i).getTitle() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += vod.get(i).getId();
			mData += "</id>";
			mData += "<Actors>";
			mData += "<![CDATA[" + actors + "]]>";
			mData += "</Actors>";
			mData += "<Director>";
			mData += "<![CDATA[" + vod.get(i).getDirector() + "]]>";
			mData += "</Director>";
			mData += "<Duration>";
			mData += "00";
			mData += "</Duration>";
			mData += "<New>";
			mData += vod.get(i).getIstrailer();
			mData += "</New>";
			mData += "<Desc>";
			mData += "<![CDATA[" + (vod.get(i).getPlot()) + "]]>";
			mData += "</Desc>";
			mData += "<Image>";
			mData += vod.get(i).getPoster();
			mData += "</Image>";
			mData += "<Status>";
			mData += vod.get(i).getIssubtile();
			mData += "</Status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
	
	private String searchVod(int subId, int index, int page, String text) {

		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod ctnVod = VodDBI.getAMDCntVod();
		
		IEVS evs = VodDBI.getEVSVod();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;

		int count = ctnVod.countVodOfSubject(subId);
//		Vector<Vod> vod = ctnVod.searchVod(subId, text, LangID, fr, to);
		List<Vod> vod = new ArrayList<Vod>();
		vod = vodContentServiceDBI.searchVodCnt(subId, text, LangID, fr, to);

		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count='" + count + "'>";
		System.out.println("Content:" + count);
		for (int i = 0; i < vod.size(); i++) {
			String actors = "";
			if (vod.get(i).getActors() != null)
				actors = vod.get(i).getActors();
			if (actors.length() > 30)
				actors = actors.substring(0, 25) + "...";
			mData += "<Item>";
			mData += "<link>";
			mData += "<![CDATA[" + evs.getUrl(vod.get(i).getId(), VOD.VOD) + "]]>";
			mData += "</link>";
			mData += "<name>";
			mData += "<![CDATA[" + vod.get(i).getTitle() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += vod.get(i).getId();
			mData += "</id>";
			mData += "<Actors>";
			mData += "<![CDATA[" + actors + "]]>";
			mData += "</Actors>";
			mData += "<Director>";
			mData += "<![CDATA[" + vod.get(i).getDirector() + "]]>";
			mData += "</Director>";
			mData += "<Duration>";
			mData += "00";
			mData += "</Duration>";
			mData += "<New>";
			mData += vod.get(i).getIstrailer();
			mData += "</New>";
			mData += "<Desc>";
			mData += "<![CDATA[" + (vod.get(i).getPlot()) + "]]>";
			mData += "</Desc>";
			mData += "<Image>";
			mData += vod.get(i).getPoster();
			mData += "</Image>";
			mData += "<Status>";
			mData += vod.get(i).getIssubtile();
			mData += "</Status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private void updateVod(HttpServletRequest request, HttpServletResponse response) {
		int id = -1;
		String name = "";
		String actors = "";
		String director = "";
		String def = "";
		String image = "";
		int price = 0;
		String money = "USD";
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString();
		}
		if (request.getParameter("id") != null) {
			try {
				id = Integer.parseInt(request.getParameter("id").toString().trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (request.getParameter("image") != null) {
			image = request.getParameter("image").toString();
		}
		if (request.getParameter("Actors") != null) {
			actors = request.getParameter("Actors").toString();
		}
		if (request.getParameter("Director") != null) {
			director = request.getParameter("Director").toString();
		}
		if (request.getParameter("Desc") != null) {
			def = request.getParameter("Desc").toString();
			def = def.replaceAll("<strong>", "<b>");
			def = def.replaceAll("</strong>", "</b>");
			def = def.replaceAll("<em>", "<i>");
			def = def.replaceAll("</em>", "</i>");
			def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
			def = def.replaceAll("</span>", "</u>");
		}
		if (request.getParameter("price") != null) {
			price = Integer.parseInt(request.getParameter("price").toString().trim());
		}
		if (request.getParameter("money") != null) {
			money = request.getParameter("money").toString().trim();
		}
		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod cntMod = VodDBI.getAMDCntVod();

		Vod vod = new Vod();
		vod.setId(id);
		vod.setTitle(name);
		vod.setActors(actors);
		vod.setDirector(director);
		vod.setPlot(def);
		vod.setPoster(image);
		vod.setCurrency(String.valueOf(price));
		vod.setIUnit(money);
		boolean b = cntMod.updateVod(vod, LangID);
		if (b) {
			ConfigLoader loader = new ConfigLoader();
			Config config = loader.getConfig();
			ManagerFile file = new ManagerFile();
			String path1 = config._temp + "/" + image;
			String path2 = config._pathImage + config._movie + "/" + image;
			file.copy(path1, path2);
			file.deletefile(path1);
		}
	}

	private String getfileFTP(String path, String Host, String User, String Pass, int Port) {

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

	private String getSubtitle(int VodId) {
		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod ctnMod = VodDBI.getAMDCntVod();
		Vector<SubTitle> v_rs = ctnMod.getSubtiles(VodId);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < v_rs.size(); i++) {
			mData += "<Item>";
			mData += "<id>";
			mData += v_rs.get(i).getSubId();
			mData += "</id>";
			mData += "<name>";
			mData += v_rs.get(i).getUrl();
			mData += "</name>";
			mData += "<type>";
			mData += v_rs.get(i).getLangId();
			mData += "</type>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getInfoFilm(HttpServletRequest request, String title) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		imdFilm info = new imdFilm();
		info.setName(title);
		String des = info.getPlot();
		GetImages image = new GetImages();
		String path = config._temp + "/";
		System.out.println("des:" + info.getPlot());
		String imName = image.read(path, info.getPoster());
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		mData += "<id>";
		mData += info.getID();
		mData += "</id>";
		mData += "<name>";
		mData += info.getTitle();
		mData += "</name>";
		mData += "<Actors>";
		mData += info.getActors();
		mData += "</Actors>";
		mData += "<Director>";
		mData += info.getDirector();
		mData += "</Director>";
		mData += "<Image>";
		mData += imName;
		mData += "</Image>";
		mData += "<Descript>";
		mData += des;
		mData += "</Descript>";
		mData += "</xml>";
		return mData;
	}

	private String gerProcessrun(String value) {
		// value = "960.mkv,44d7d9ed-df35-4998-a208-a4c86f327986,Transformers"
		// System.out.println("value = = = = = " + value);
		// list = contentServiceDBI.getVodTransferPercent();
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String hostserver = config._HostFtpServer;
		String[] arr = value.split(",,,");

		String mData = "";
		if (value != "-1") {
			try {
				mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
				mData += "<xml>";
				DecimalFormat df = new DecimalFormat("#.##");
				FTPGatewayInterface ftpgateway;
				ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + hostserver + ":2099/elc_ftpgateway");
				for (int i = 0; i < arr.length; i = i + 3) {
					int vodId = Integer.parseInt(arr[i].substring(0, arr[i].length() - 4));
					if (ftpgateway.getStatus(UUID.fromString(arr[i + 1])) == FTPServerStruct.STATUS_COMPLETED) {
						System.out.println("Transfer = = = = = = = = = = 100%");
						vodContentServiceDBI.updateStatusInvisibleVod(vodId, arr[i]);
						arr = deleteArray(arr, i);
					} else {
						float percentfloat = (float) (ftpgateway.transfered(UUID.fromString(arr[i + 1])) * 100)
								/ ftpgateway.fileSize(UUID.fromString(arr[i + 1]));
						Float temp = (float) (ftpgateway.transfered(UUID.fromString(arr[i + 1])) * 100)
								/ ftpgateway.fileSize(UUID.fromString(arr[i + 1]));
						Float compare = Float.NaN;

						if (temp.compareTo(compare) != 0) {
							System.out.println("percent of uuid: " + arr[i + 1] + " = = = = = " + percentfloat);
							if (percentfloat > 99.9) {
								vodContentServiceDBI.updateStatusInvisibleVod(vodId, arr[i]);
							}
							mData += "<Item>";
							mData += "<id>";
							mData += arr[i + 1];
							mData += "</id>";
							mData += "<name>";
							mData += arr[i + 2];
							mData += "</name>";
							mData += "<percent>";
							mData += df.format(percentfloat);
							mData += "</percent>";
							mData += "<filename>";
							mData += arr[i];
							mData += "</filename>";
							mData += "</Item>";
						} else {
							mData += "";
						}
					}
				}
				// }
				mData += "</xml>";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mData;
	}

	public boolean transferVod(String filepathSrc, String filenameServer, String nameview, String hostSrc, int portSrc, String userSrc,
			String passSrc) {
		boolean flag = true;
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String hostserver = config._HostFtpServer;
		String foldername = config._FolderMovie;

		String filepathserver = config._FilePathserver;
		try {
			// cau hinh rmi
			FTPGatewayInterface ftpgateway;
			ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + hostserver + ":2099/elc_ftpgateway");
			System.out.println("RMI = " + "rmi://" + hostserver + ":2099/elc_ftpgateway");

			// down load file from src to server
			System.out.println("filepath SRC  = = = = " + filepathSrc);
			FTPServerStruct server = new FTPServerStruct(hostSrc, portSrc, userSrc, passSrc, filepathSrc);
			System.out.println("file path inserver = = = = = " + filepathserver + "/" + foldername + "/" + filenameServer);
			UUID id = ftpgateway.download(server, hostserver, filepathserver + "/" + foldername + "/" + filenameServer, 5000);
			System.out.println("id uuid = = = " + id);
			String file = filenameServer.substring(filenameServer.lastIndexOf(".") - (-1));
			if ("mp4".equalsIgnoreCase(file) || "mkv".equalsIgnoreCase(file) || "mov".equalsIgnoreCase(file)
					|| "flv".equalsIgnoreCase(file) || "avi".equalsIgnoreCase(file))
				if (id != null) {
					nameview = nameview.replaceAll("'", " i");
					contentServiceDBI.insertVodRemote(filenameServer, id, nameview);

				} else {
					int vodId = Integer.parseInt(filenameServer.substring(0, filenameServer.lastIndexOf(".")));
					vodContentServiceDBI.deleteVodContentById(vodId);
					return false;
				}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public boolean deleteTransferVod(String uuid, String filename) {
		boolean flag = true;
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String hostserver = config._HostFtpServer;
		try {
			FTPGatewayInterface ftpgateway;
			ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + hostserver + ":2099/elc_ftpgateway");
			flag = ftpgateway.StopUrl(UUID.fromString(uuid));
			boolean t = contentServiceDBI.deleteVodRemotePercentByUUID(uuid);
			filename = filename.substring(0, filename.lastIndexOf("."));
			int vodid = Integer.parseInt(filename);
			boolean temp = deleteFileInFtpServer(vodid);
			if (flag && temp && t)
				System.out.println("delete transfer vod success!");
			else
				System.out.println("delete transfer vod unsuccess!");
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean deleteFileInFtpServer(int VodId) {
		String filename = contentServiceDBI.getFileNameContentById(VodId);
		// String ipserver = contentServiceDBI.getHostPortContentById(VodId);
		List<String> listsub = contentServiceDBI.getAllSubForVod(VodId);
		boolean flag = false;
		// filename = folder + "/" + filename;
		flag = deleteFileFTPSupport(filename);
		if (!flag)
			return false;
		for (int i = 0; i < listsub.size(); i++) {
			flag = deleteFileFTPSupport(listsub.get(i));
			if (!flag)
				return false;
		}
		if (flag)
			vodContentServiceDBI.deleteVodDbi(VodId);
		return true;
	}

	public boolean deleteFileFTPSupport(String filename) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String folder = config._FolderMovie;
		String host = config._HostFtpServer;
		int port = config._PortFtpServer;
		String user = config._UserFtpServer;
		String pass = config._PassFtpServer;
		System.out.println(host + " - " + port + " - " + user + " - " + pass);
		System.out.println("delete file: " + filename + " - in folder: " + folder);
		boolean flag = false;
		try {
			Ftp4jClient ftp4j = new Ftp4jClient(host, port, user, pass);
			ftp4j.connect();
			ftp4j.deleteFileFTPVod(filename, folder);
			ftp4j.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean deleteFileSubInFtpServer(String filename) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String host = config._HostFtpServer;
		int port = config._PortFtpServer;
		String user = config._UserFtpServer;
		String pass = config._PassFtpServer;
		String folder = config._FolderMovie;
		try {
			Ftp4jClient ftp4j = new Ftp4jClient(host, port, user, pass);
			ftp4j.connect();
			ftp4j.deleteFileFTPVod(filename, folder);
			ftp4j.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public String[] deleteArray(String[] a, int t) {
		for (int i = t; i < a.length - 3; i++) {
			a[i] = a[i + 3];
		}
		String[] b = new String[a.length - 3];
		for (int j = 0; j < a.length - 3; j++) {
			b[j] = a[j];
		}
		return b;
	}

	public String getLang(int index) {
		if (index == 1)
			return "vn";
		if (index == 2)
			return "en";
		return null;
	}

	public static void main(String[] args) {
		
	}

}
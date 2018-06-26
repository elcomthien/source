package ehotel.admin.Vod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.Service.VlcService;
import ehotel.admin.dao.VodContentServiceDBI;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;
import ehotel.inter.AMDVod;
import ehotel.inter.IEVS;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;
import ehotel.render.VOD;

public class serviceVodCharge extends ServiceParent {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private VlcService vlcService = new VlcService();
	private VodContentServiceDBI vodContentServiceDBI = new VodContentServiceDBI();

	public serviceVodCharge() {
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
			this.showJSPpage(request, response,
					"/vodMgn/service/VodSrvMainCharge.jsp");
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
			int page = 6;
			int subId = -1;
			int index = -1;
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId)
						.toString());
			}
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex")
						.toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString()
						.trim());
			}
			System.out.println("Get list vod");
			response.setContentType("text/xml");
			String st = getListMove(subId, index, page);
			out.print(st);
			break;
		}
		case 3: {
			System.out.println("get subject in Vod");
			response.setContentType("text/xml");
			int vodId = -1;
			if (request.getParameter("id") != null) {
				vodId = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			String st = getsubjectNotInVod(vodId);
			out.print(st);
			break;
		}
		case 4://
		{
			System.out.println("get subject not Vod");
			response.setContentType("text/xml");
			int vodId = -1;
			if (request.getParameter("id") != null) {
				vodId = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			String st = getsubjectInVod(vodId);
			out.print(st);
			break;
		}
		case 5:// new released
		{
			int vodId = -1;
			if (request.getParameter("vodId") != null) {
				vodId = Integer.parseInt(request.getParameter("vodId")
						.toString().trim());
			}
			int subId = 0;
			if (request.getParameter("subId") != null) {
				subId = Integer.parseInt(request.getParameter("subId")
						.toString().trim());
			}
			VOD vodDBI = DBIGateway.getAMDVod();
			AMDVod srvMod = vodDBI.getAMDSvcVod();
			srvMod.setNewReleased(vodId, subId);
			break;
		}
		case 6:// status
		{
			int vodId = -1;
			if (request.getParameter("vodId") != null) {
				vodId = Integer.parseInt(request.getParameter("vodId")
						.toString().trim());
			}
			int Status = 0;
			if (request.getParameter("Status") != null) {
				Status = Integer.parseInt(request.getParameter("Status")
						.toString().trim());
			}
			int subId = -1;
			if (request.getParameter("subId") != null) {
				subId = Integer.parseInt(request.getParameter("subId")
						.toString().trim());
			}
			VOD vodDBI = DBIGateway.getAMDVod();
			AMDVod srvMod = vodDBI.getAMDSvcVod();
			srvMod.setVisileStatus(vodId, subId);
			break;
		}
		case 7:// change subject
		{
			System.out.println("change subject");
			int vodId = -1;
			if (request.getParameter("vodId") != null) {
				vodId = Integer.parseInt(request.getParameter("vodId")
						.toString().trim());
			}

			Vector<Integer> list = new Vector<Integer>();
			int i = 0;
			while (request.getParameter("subid" + i) != null) {
				int subid = Integer.parseInt(request.getParameter("subid" + i)
						.toString().trim());
				list.add(subid);
				i++;
			}

			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1) + ")";

			VOD vodDBI = DBIGateway.getAMDVod();
			AMDVod srvMod = vodDBI.getAMDSvcVod();
			srvMod.changeSubjectOfSvcVod(vodId, param);
			break;
		}
		case 8:// list vod in Subcontent not SubService
		{
			int subIdCtn = -1;
			int subISvr = -1;
			if (request.getParameter(Def.SubId) != null) {
				subIdCtn = Integer.parseInt(request.getParameter(Def.SubId)
						.toString());
			}

			if (request.getParameter("subISvr") != null) {
				subISvr = Integer.parseInt(request.getParameter("subISvr")
						.toString());
			}
			System.out.println("Get list vod in content");
			response.setContentType("text/xml");
			String st = getListMovieCtn(subIdCtn, subISvr);
			out.print(st);
			break;
		}
		case 9:// add contènt srvice
		{
			int subId = -1;
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId)
						.toString());
			}
			int vodId = -1;
			if (request.getParameter("vodId") != null) {
				vodId = Integer.parseInt(request.getParameter("vodId")
						.toString().trim());
			}
			VOD VodDBI = DBIGateway.getAMDVod();
			AMDVod SrvMod = VodDBI.getAMDSvcVod();
			String str = "(" + String.valueOf(vodId) + ")";
			boolean b = SrvMod.addVod(subId, str);
			System.out.println(b);
			if (b) {
				out.write("1");
			} else {
				out.write("0");
			}
			break;
		}
		case 10:// remove
		{
			ILOGIN iuser = DBIGateway.getILogin();
			String ipAdress = request.getRemoteAddr();
			if (iuser.isAdmin(ipAdress)
					|| iuser.checkRoleUser(ipAdress, Def._roleVIDEO)) {
				int subId = -1;
				if (request.getParameter(Def.SubId) != null) {
					subId = Integer.parseInt(request.getParameter(Def.SubId)
							.toString());
				}
				String vodId = "";
				if (request.getParameter("vodId") != null) {
					vodId = request.getParameter("vodId").toString().trim();
				}
				VOD VodDBI = DBIGateway.getAMDVod();
				AMDVod SrvMod = VodDBI.getAMDSvcVod();
				String chuoi = "(" + vodId + ")";
				boolean b = SrvMod.removeVod(subId, chuoi);
			} else {
				out.print(-1);
			}
			break;
		}
		case 11:// add content service
		{
			String str = "";
			int subId = -1;
			if (request.getParameter(Def.SubId) != null) {
				subId = Integer.parseInt(request.getParameter(Def.SubId)
						.toString());
			}
			String vodId = "";
			if (request.getParameter("vodId") != null) {
				vodId = request.getParameter("vodId").toString().trim();
			}
			VOD VodDBI = DBIGateway.getAMDVod();
			AMDVod VodSvc = VodDBI.getAMDSvcVod();
			String chuoi = "(" + vodId + ")";
			boolean b = VodSvc.addVod(subId, chuoi);
			if (b) {
				out.write("1");
			} else {
				out.write("0");
			}
			break;
		}
		// them vao bo sung chuc nang play video 12.1
		case 100:// play video 12.1
		{
			if (request.getParameter("link") != null) {
				// try {
				String name = request.getParameter("link");
				String path = vodContentServiceDBI.getSrcVodToPlayVlc();
				String link = path + name;
				// String result = vlcService.playVlc(link);
				// System.out.println("play====================================="+result);
				out.print(link);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
			}
			break;
		}
		default:
			break;
		}
	}

	@SuppressWarnings("unused")
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
			System.out.println("Insert subject VOD! (service->video)");
			response.setContentType("text/xml");
			String subjectName = "";
			String image = "";
			int parent = -1;
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString();
			}
			if (request.getParameter("parent") != null) {
				parent = Integer.parseInt(request.getParameter("parent")
						.toString());
			}
			if (request.getParameter("name") != null) {
				subjectName = request.getParameter("name").toString();

				VOD vodDBI = DBIGateway.getAMDVod();
				AMDVod srvMod = vodDBI.getAMDSvcVod();
				// int id= srvMod.addSubject(subjectName, config._movie + "/" +
				// image,-1);
				int id = srvMod.addSubject(subjectName, image, 23);
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
		case 2:// update subject vod service subject 18.1
		{
			String subjectName = "";
			String image = "";
			if (request.getParameter("image") != null) {
				image = request.getParameter("image").toString();
			}
			int subjId = -1;
			if (request.getParameter("name") != null) {
				if (request.getParameter("subid") != null) {
					subjId = Integer.parseInt(request.getParameter("subid")
							.toString());
					subjectName = request.getParameter("name").toString();
					VOD vodDBI = DBIGateway.getAMDVod();
					AMDVod srvMod = vodDBI.getAMDSvcVod();
					// int t= srvMod.editSubject(subjId,subjectName,
					// config._movie + "/" +image
					// ,LangID);
					int t = srvMod.editSubject(subjId, subjectName, image,
							LangID);
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
			System.out.println("delete subject vod id:");
			int subjId = -1;
			if (request.getParameter("subid") != null) {
				subjId = Integer.parseInt(request.getParameter("subid")
						.toString());
				VOD vodDBI = DBIGateway.getAMDVod();
				AMDVod srvMod = vodDBI.getAMDSvcVod();
				boolean b = srvMod.removeSubject(subjId);
				System.out.println("delete:" + b);
			}
			break;
		}
		// update Vod=video ben service 17.1
		case 4: {
			System.out.println("Update VOD");
			ILOGIN iuser = DBIGateway.getILogin();
			String ipAdress = request.getRemoteAddr();
			if (iuser.isAdmin(ipAdress)
					|| iuser.checkRoleUser(ipAdress, Def._roleVIDEO)) {
				int id = -1;
				String name = "";
				String actors = "";
				String director = "";
				String currency = "";
				String unit = "";
				String def = "";
				String image = "";
				if (request.getParameter("name") != null) {
					name = request.getParameter("name").toString();
				}
				if (request.getParameter("id") != null) {
					try {
						id = Integer.parseInt(request.getParameter("id")
								.toString().trim());
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
				if (request.getParameter("Price") != null) {
					currency = request.getParameter("Price").toString();
				}
				if (request.getParameter("Unit") != null) {
					unit = request.getParameter("Unit").toString();
				}

				if (request.getParameter("Desc") != null) {
					def = request.getParameter("Desc").toString();
					def = def.replaceAll("<strong>", "<b>");
					def = def.replaceAll("</strong>", "</b>");
					def = def.replaceAll("<em>", "<i>");
					def = def.replaceAll("</em>", "</i>");
					def = def.replaceAll(
							"<span style=\"text-decoration: underline;\">",
							"<u>");
					def = def.replaceAll("</span>", "</u>");
				}
				VOD VodDBI = DBIGateway.getAMDVod();
				AMDVod SrvMod = VodDBI.getAMDSvcVod();
				Vod vod = new Vod();
				vod.setId(id);
				vod.setTitle(name);
				vod.setActors(actors);
				vod.setDirector(director);
				vod.setCurrency(currency);
				vod.setIUnit(unit);
				vod.setPlot(def);
				vod.setPoster(image);
				boolean b = SrvMod.updateVod(vod, LangID);
				if (b) {
					ManagerFile file = new ManagerFile();
					String path1 = config._temp + "/" + image;
					String path2 = config._pathMovies + "/" + image;
					file.copy(path1, path2);
					file.deletefile(path1);
				}
			} else {
				out.write(-1);
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

	private String getsubMod() {
//		VOD VodDBI = DBIGateway.getAMDVod();
//		AMDVod SrvMod = VodDBI.getAMDSvcVod();
//		Vector<Subject> subject = SrvMod.getSubjects(LangID);
		List<Subject> list = vodContentServiceDBI.getSubjectServiceCharge(LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			Subject item = list.get(i);
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
			mData += "-1";
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	// /dasdasdasdasdsadsadsads
	private String getListMove(int subId, int index, int page) {
		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDSvcVod();
		IEVS evs = VodDBI.getEVSVod();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		Vector<Vod> vod = new Vector<Vod>();
		if (index == -1) {
			vod = SrvMod.getVods(subId, LangID, -1, -1);
		} else {
			vod = SrvMod.getVods(subId, LangID, fr, to);
		}
		int count = SrvMod.countVodOfSubject(subId);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml count='" + count + "'>";
		System.out.println("length:" + vod.size());
		for (int i = 0; i < vod.size(); i++) {
			// them vao de lay link video 12.1
			mData += "<Item>";
			mData += "<link>";
			mData += "<![CDATA[" + evs.getUrl(vod.get(i).getId(), VOD.VOD)
					+ "]]>";
			mData += "</link>";
			mData += "<name>";
			mData += "<![CDATA[" + vod.get(i).getTitle() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += vod.get(i).getId();
			mData += "</id>";
			mData += "<Actors>";
			mData += "<![CDATA[" + vod.get(i).getActors() + "]]>";
			mData += "</Actors>";
			mData += "<Director>";
			mData += "<![CDATA[" + vod.get(i).getDirector() + "]]>";
			mData += "</Director>";
			mData += "<Duration>";
			mData += "<![CDATA[00]]>";
			mData += "</Duration>";
			mData += "<Currency>";
			mData += vod.get(i).getCurrency();
			mData += "</Currency>";
			mData += "<Unit>";
			mData += vod.get(i).getIUnit();
			mData += "</Unit>";
			mData += "<New>";
			mData += vod.get(i).getReleased();
			mData += "</New>";
			mData += "<Desc>";
			mData += "<![CDATA[" + (vod.get(i).getPlot()) + "]]>";
			mData += "</Desc>";
			mData += "<Image>";
			mData += vod.get(i).getPoster();
			mData += "</Image>";
			mData += "<Status>";
			mData += vod.get(i).getStatus();
			mData += "</Status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	@SuppressWarnings("unused")
	private String getsubjectInVod(int vodId) {
		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDSvcVod();
		Vector<Subject> subject1 = SrvMod.getSubjects(LangID);
		Vector<Subject> subject2 = SrvMod.getSubjectsInVod(vodId, LangID);
		System.out.println("size:" + subject2.size());
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";

		for (int i = 0; i < subject2.size(); i++) {
			Subject item = subject2.get(i);
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
			mData += "-1";
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private String getsubjectNotInVod(int vodId) {
		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDSvcVod();
		Vector<Subject> subject1 = SrvMod.getSubjects(LangID);

		Vector<Subject> subject2 = SrvMod.getSubjectsInVod(vodId, LangID);
		for (int i = 0; i < subject2.size(); i++) {
			for (int j = 0; j < subject1.size(); j++) {
				if (subject1.get(j).getId() == subject2.get(i).getId()) {
					subject1.remove(j);
					j--;
					break;
				}
			}
		}
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < subject1.size(); i++) {
			Subject item = subject1.get(i);
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
			mData += "-1";
			mData += "</parent>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}

	private String getListMovieCtn(int subIdCtn, int subj_svc) {
		/*
		 * VOD VodDBI = DBIGateway.getAMDVod(); AMDVod CtnMod =
		 * VodDBI.getAMDCntVod(); Vector<Vod> vod=CtnMod.getVods(subIdCtn,
		 * LangID, -1, -1);
		 * 
		 * String mData=""; mData+=
		 * "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n"
		 * ; mData+="<xml>";
		 * 
		 * for(int i=0;i<vod.size();i++) { String actor="unknow";
		 * if(vod.get(i).getActors()!=null) { actor=vod.get(i).getActors(); }
		 * mData+="<Item>"; mData+="<name>";
		 * mData+="<![CDATA["+vod.get(i).getTitle()+"]]>"; mData+="</name>";
		 * mData+="<id>\n"; mData+=vod.get(i).getId(); mData+="</id>";
		 * mData+="<Actors>";
		 * 
		 * mData+="<![CDATA["+actor+"]]>"; mData+="</Actors>";
		 * mData+="<Director>";
		 * mData+="<![CDATA["+vod.get(i).getDirector()+"]]>";
		 * mData+="</Director>"; mData+="<Duration>"; mData+="00";
		 * mData+="</Duration>"; mData+="<New>";
		 * mData+=vod.get(i).getReleased(); mData+="</New>"; mData+="<Desc>";
		 * 
		 * mData+="<![CDATA["+(vod.get(i).getPlot())+"]]>"; mData+="</Desc>";
		 * mData+="<Image>"; mData+= vod.get(i).getPoster(); mData+="</Image>";
		 * mData+="<Status>"; mData+=vod.get(i).getStatus(); mData+="</Status>";
		 * mData+="</Item>"; } mData+="</xml>";
		 * 
		 * return mData;
		 */
		VOD VodDBI = DBIGateway.getAMDVod();
		AMDVod CtnMod = VodDBI.getAMDSvcVod();
		Vector<Vod> vod = CtnMod
				.getVascVods(subIdCtn, subj_svc, LangID, -1, -1);

		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();

		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";

		for (int i = 0; i < vod.size(); i++) {
			String actor = "unknow";
			if (vod.get(i).getActors() != null) {
				actor = vod.get(i).getActors();
			}
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + vod.get(i).getTitle() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += vod.get(i).getId();
			mData += "</id>";
			mData += "<Actors>";
			mData += "<![CDATA[" + actor + "]]>";
			mData += "</Actors>";
			mData += "<Director>";
			mData += "<![CDATA[" + vod.get(i).getDirector() + "]]>";
			mData += "</Director>";
			mData += "<Duration>";
			mData += "00";
			mData += "</Duration>";
			mData += "<New>";
			mData += vod.get(i).getReleased();
			mData += "</New>";
			mData += "<Desc>";
			mData += "<![CDATA[" + (vod.get(i).getPlot()) + "]]>";
			mData += "</Desc>";
			mData += "<Image>";
			// bo sung 17.1
			mData += config._movie + "/" + vod.get(i).getPoster();
			mData += "</Image>";
			mData += "<Status>";
			mData += vod.get(i).getStatus();
			mData += "</Status>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}

	public String getListSubjectService() {
		List<SubjectMovieModel> list = vodContentServiceDBI.getSubjectServiceVod(LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			SubjectMovieModel item = list.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += item.getImage();
			mData += "</image>";
			mData += "<parent>";
			mData += "-1";
			mData += "</parent>";
			mData += "</Item>";
			List<SubjectMovieModel> ls = vodContentServiceDBI.getSubjectByIdParent(item.getId(), LangID);
			for (int j = 0; j < ls.size(); j++) {
				SubjectMovieModel ite = ls.get(j);
				mData += "<Item>";
				mData += "<name>";
				mData += "<![CDATA[" + ite.getName() + "]]>";
				mData += "</name>";
				mData += "<id>\n";
				mData += ite.getId();
				mData += "</id>\n";
				mData += "<image>\n";
				mData += ite.getImage();
				mData += "</image>";
				mData += "<parent>";
				mData += ite.getParent();
				mData += "</parent>";
				mData += "</Item>";
			}
		}
		mData += "</xml>";
		return mData;
	}
}
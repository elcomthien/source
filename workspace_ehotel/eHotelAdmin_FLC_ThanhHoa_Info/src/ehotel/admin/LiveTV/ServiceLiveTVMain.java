package ehotel.admin.LiveTV;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.elcom.DBI.DBI;
import com.elcom.DBI.SubProParam;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.Service.VlcService;
import ehotel.admin.Vod.ConfigService;
import ehotel.admin.common.ftpClient;
import ehotel.admin.dbi.LiveTvServiceDBI;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.vod.Subject;
import ehotel.impl.AMDSvcLiveTVImp;
import ehotel.inter.AMDLiveTV;

public class ServiceLiveTVMain extends ServiceParent {
	private static final long serialVersionUID = 1L;
	private AMDLiveTV livetv = new AMDSvcLiveTVImp();
	private VlcService vlcService = new VlcService();
	public static final String CHANNEL_CHECK = "BEGIN PHILAO.CHANNEL_CHECK(?,?,?,?,?); END;";
	@SuppressWarnings("rawtypes")
	private LinkedList pool = new LinkedList();
	private Object mutex = new Object();
	private int max_pool_size = 10;
	AdditionLiveTvService additionLiveTvService = new AdditionLiveTvService();

	private int nxt = 0;
	private int pr = 0;
	
	private ConfigService configService = new ConfigService();

	public ServiceLiveTVMain() {
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
		case -1:
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
			// request.setAttribute("loadMenuAD", getsubMod());
			request.setAttribute("fileJSP", "../serviceMng/srcLiveTVMain.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case 1:
			response.setContentType("text/xml");
			String st1 = getSubjectMenu();
			out.print(st1);
			break;
		case 2:
			boolean p = deleteSubject(request, response);
			if (!p) {
				out.print("failed");
			}
			break;
		case 3:
			int index = 0;
			int page = 6;
			int channelId = 0;
			if (request.getParameter("channelId") != null) {
				channelId = Integer.parseInt(request.getParameter("channelId")
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
			response.setContentType("text/xml");
			/*
			 * Thay the ham cu = viec doc liveTV 8.1
			 */
			// String st=getChannelInfo(channelId,index,page);
			String st = getChannelLiveInfo(channelId, index, page);
			out.print(st);
			break;
		case 4:
			// them ham play/stop liveTV khi nhan contextmenu 8.1
			// boolean
			// changstatus=changeStatus(request.getParameter("command"));
			// ChangeStatus(request,response);
			// if(!changstatus){
			// out.print("failed");
			// }
			boolean changstatus = ChangeStatus(request, response);
			if (!changstatus) {
				out.print("failed");
			}
			break;
		case 5:
			boolean plag1 = deleteChannel(request, response);
			if (!plag1) {
				out.print("failed");
			}
			break;
		case 6:
			boolean plag2 = deleteLiveTV(request, response);
			if (!plag2) {
				out.print("failed");
			}
			break;
		case 7:
			int subjectId = 0;
			if (request.getParameter("subjectId") != null) {
				subjectId = Integer.parseInt(request.getParameter("subjectId")
						.toString().trim());
			}
			String stlive = getChannelContentOutSujectSrc(subjectId);
			response.setContentType("text/xml");
			out.print(stlive);
			break;
		case 8:
			int subjectId1 = 0;
			if (request.getParameter("subjectId") != null) {
				subjectId1 = Integer.parseInt(request.getParameter("subjectId")
						.toString().trim());
			}
			String stchannel = getChannelInfoNotService(subjectId1);
			response.setContentType("text/xml");
			out.print(stchannel);
			break;
		case 9:
			boolean kq = addLiveTVChannel(request, response);
			if (!kq) {
				out.print("failed");
			}
			break;
		case 10:
			boolean rkq = removeLiveTVChannel(request, response);
			if (!rkq) {
				out.print("failed");
			}
			break;
		case 11:
			int index1 = 0;
			int page1 = 6;
			int subjId1 = 0;
			String nameSearch = "";
			if (request.getParameter("subjId") != null) {
				subjId1 = Integer.parseInt(request.getParameter("subjId")
						.toString());
			}
			if (request.getParameter("pageIndex") != null) {
				index1 = Integer.parseInt(request.getParameter("pageIndex")
						.toString().trim());
			}
			if (request.getParameter("page") != null) {
				page1 = Integer.parseInt(request.getParameter("page")
						.toString().trim());
			}
			if (request.getParameter("page") != null) {
				nameSearch = request.getParameter("nameSearch").toString()
						.trim();
			}
			response.setContentType("text/xml");
			nameSearch = new String(nameSearch.getBytes("8859_1"), "UTF8");
			String search1 = searchChannelLive(subjId1, nameSearch, index1,
					page1);
			out.print(search1);
			break;
		case 12: {
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
			String xml = getfileFTP(path, host, user, pass, port);
			out.print(xml);
			break;
		}
		case 13:
			response.setContentType("text/xml");
			String lc = getChannelAdvertise();
			out.print(lc);
			break;
		case 14: {
			response.setContentType("text/xml");
			int id = -1;
			String url = "";
			if (request.getParameter("channelId").trim() != null)
				id = Integer.parseInt(request.getParameter("channelId").trim());
			if (request.getParameter("url").trim() != null)
				url = request.getParameter("url").trim();
			String filename = url.substring(url.lastIndexOf("/") + 1);
			boolean flag = deleteFileVideoAdvertise(filename);
			if (flag) {
//				flag = LiveTvServiceDBI.deleteChannelAdvertise(id);
				if (flag)
					out.print("success");
				else
					out.print("failed");
			} else
				out.print("failed");
			break;
		}
		case 100:
			/*
			 * Them vao xu ly su kien play url livetv 12.1
			 */
			if (request.getParameter("link") != null) {
				try {
					String result = vlcService.playVlc(request
							.getParameter("link"));
					out.print(result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
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
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
			}
		}
		switch (cmd) {
		case 1:
			int kq = addSubjectMenu(request, response);
			if (kq == -1) {
				out.print("failed");
			} else if (kq == -2) {
				out.print("f");
			}
			break;
		case 2:
			int plag = UpdateSubject(request, response);
			if (plag == -1) {
				out.print("failed");
			} else if (plag == -2) {
				out.print("f");
			}
			break;
		case 3:
			int p = EditChannel(request, response);
			if (p == -1) {
				out.print("failed");
			} else if (p == -2) {
				out.print("f");
			}
			break;
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

	/*
	 * Ham moi cho viec lay du lieu liveTV 8.1
	 */
	public void putDBI(DBI dbi) {
		synchronized (mutex) {
			if (dbi != null && pool.size() < max_pool_size - 1) {
				pool.addFirst(dbi);
			}
		}
	}

	public void clearPool() {
		synchronized (mutex) {
			pool.clear();
		}
	}

	private DBI getNewDBI() throws RemoteException, ClassNotFoundException {
		DBI dbi = null;
		int attemptNo = 1;
		while (attemptNo < 3) {
			try {
				dbi = (DBI) Naming.lookup("rmi://" + getHost() + ":"
						+ getPort() + "/" + getDbiservicename());
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

	public void checkChannelDbi(String namechannel, String namenumber,
			String url, String status, String image) {
		Vector params = new Vector(5);
		SubProParam in = null;
		in = new SubProParam(new java.lang.String(namechannel), SubProParam.IN);
		params.add(in); // 0
		in = new SubProParam(new BigDecimal(namenumber), SubProParam.IN);
		params.add(in); // 1
		in = new SubProParam(new java.lang.String(url), SubProParam.IN);
		params.add(in); // 2
		in = new SubProParam(new BigDecimal(status), SubProParam.IN);
		params.add(in); // 3
		in = new SubProParam(new java.lang.String(image), SubProParam.IN);
		params.add(in); // 4
		try {
			executeSubPro(CHANNEL_CHECK, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector executeSubPro(String sqlSubPro, Vector params)
			throws ClassNotFoundException {
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

	public String readProperStb() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream(
					"/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.link");
	}

	public String getHost() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream(
					"/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.host");
	}

	public String getPort() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream(
					"/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.port");
	}

	public String getDbiservicename() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream(
					"/WEB-INF/philao.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("ehotel.philao.getchannel.dbiservicename");
	}

	// thay doi status cua liveTV 8.1
	public boolean changeStatus(String command) {
		System.out.println("command=" + command);
		try {
			String livetvhost = readProperStb();
			Socket echoSocket = null;
			try {
				echoSocket = new Socket(livetvhost, 7777);
				DataInputStream din = new DataInputStream(
						echoSocket.getInputStream());
				DataOutputStream dout = new DataOutputStream(
						echoSocket.getOutputStream());
				dout.writeBytes(command);
				dout.flush();
				din.close();
				dout.close();
				echoSocket.close();
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host");
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to");
				return false;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return true;
	}

	public String readXmlGetChannels() {
		String temp = this.getServletContext().getRealPath(".");
		String result = "";
		try {
			String livetvhost = readProperStb();
			Socket echoSocket = null;
			try {
				echoSocket = new Socket(livetvhost, 7777);
				DataInputStream din = new DataInputStream(
						echoSocket.getInputStream());
				DataOutputStream dout = new DataOutputStream(
						echoSocket.getOutputStream());
				String channel = "getchannel*";
				dout.writeBytes(channel);
				dout.flush();
				try {
					while (true) {
						String line = din.readLine().trim();
						result += line;
						if (line.length() == 1) {
							break;
						}
					}
					result = result.trim().substring(0,
							result.trim().length() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					din.close();
					dout.close();
				} catch (Exception e) {
				}
				echoSocket.close();
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host");
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getChannelsMap(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList imagel = eElement.getElementsByTagName("image")
						.item(0).getChildNodes();
				Node image = (Node) imagel.item(0);

				NodeList namechannell = eElement
						.getElementsByTagName("namechannel").item(0)
						.getChildNodes();
				Node namechannel = (Node) namechannell.item(0);

				NodeList namenumberl = eElement
						.getElementsByTagName("namenumber").item(0)
						.getChildNodes();
				Node namenumber = (Node) namenumberl.item(0);

				NodeList statusl = eElement.getElementsByTagName("status")
						.item(0).getChildNodes();
				Node status = (Node) statusl.item(0);

				NodeList urll = eElement.getElementsByTagName("url").item(0)
						.getChildNodes();
				Node url = (Node) urll.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();
				mp.put("image", image.getNodeValue());
				mp.put("namechannel", namechannel.getNodeValue());
				mp.put("namenumber", namenumber.getNodeValue());
				mp.put("status", status.getNodeValue());
				mp.put("url", url.getNodeValue());
				mp.put("id", eElement.getAttributeNode("id").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public String getChannelLiveInfo(int menuid1, int index, int page) {
		// buoc 1: show thong tin cac kenh da ton tai trong db ra browser 8.1
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		int c = 0;
		int fr = index * page;
		fr += 1;
		c = fr;
		int to = (index + 1) * page;
		ArrayList<HashMap<String, String>> xmlstring = new ArrayList<HashMap<String, String>>();
		String xml = "";// readXmlGetChannels();
		if (!xml.equals("")) {
			xmlstring = getChannelsMap(xml);
		} else {
			return "failed";
		}
		Vector<LiveTV> vLiveTV = livetv.getChannels(menuid1, LangID, fr, to);
		String mData = "";
		boolean flag = false;
		int count = livetv.countChannels(menuid1);
		mData += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + count + "\" >";
		if (count < c && count != 0) {
			if (count % 6 == 0) {
				nxt = count - 5;
				pr = count;
				c = pr;
			}
			vLiveTV = livetv.getChannels(menuid1, LangID, nxt, pr);
			c = nxt;
		}
		for (int i = 0; i < vLiveTV.size(); i++) {
			LiveTV item = vLiveTV.get(i);
			if (xmlstring.size() != 0) {
				for (int im = 0; im < xmlstring.size(); im++) {
					flag = false;
					HashMap<String, String> xmleach = xmlstring.get(im);
					String image = xmleach.get("image");
					String namechannel = xmleach.get("namechannel");
					String namenumber = xmleach.get("namenumber");
					String status = xmleach.get("status");
					String url = xmleach.get("url");
					if (item.getChannelname().equals(namechannel)) {
						// bo sung so luong nguoi xem 05062013
						String port = url.substring(url.lastIndexOf(":") + 1);
						System.out.println("port=" + port);
						flag = true;
						mData += "<Item>";
						mData += "<name>";
						mData += "<![CDATA[" + namechannel + "]]>";
						mData += "</name>";
						mData += "<id>\n";
						mData += namenumber;
						mData += "</id>";
						mData += "<stt>\n";
						mData += c++;
						mData += "</stt>";
						mData += "<ChannelCode>\n";
						mData += namenumber;
						mData += "</ChannelCode>";
						mData += "<address>";
						if (null != url) {
							mData += "<![CDATA[" + url + "]]>";
						} else {
							mData += -1;
						}
						mData += "</address>";
						mData += "<urlIma>\n";
						if (null != image) {
							mData += config._pathliveTV + "/" + image;
						} else {
							mData += "emp".trim();
						}
						mData += "</urlIma>";
						mData += "<serviceName>\n";
						mData += namechannel;
						mData += "</serviceName>";
						mData += "<Active>\n";
						mData += status;
						mData += "</Active>";
						mData += "<sl>\n";
						try {
							mData += additionLiveTvService
									.showSoLuongNguoiXemTv(port) + "\n";
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						mData += "</sl>";
						mData += "</Item>";
						break;
					}
				}
			}
			if (!flag) {
				mData += "<Item>";
				mData += "<name>";
				mData += "<![CDATA[" + item.getChannelname() + "]]>";
				mData += "</name>";
				mData += "<id>\n";
				mData += item.getChannelid();
				mData += "</id>";
				mData += "<stt>\n";
				mData += c++;
				mData += "</stt>";
				mData += "<ChannelCode>\n";
				mData += item.getChannelcode();
				mData += "</ChannelCode>";
				String port = "";
				mData += "<address>";
				if (null != item.getPhysical_address()) {
					mData += "<![CDATA[" + item.getPhysical_address() + "]]>";
					port = item.getPhysical_address().substring(
							item.getPhysical_address().lastIndexOf(":") + 1);
				} else {
					mData += -1;
				}
				mData += "</address>";
				System.out.println("port=" + port);
				mData += "<urlIma>\n";
				if (null != item.getUrlImage()) {
					mData += config._pathliveTV + "/" + item.getUrlImage();
				} else {
					mData += "emp".trim();
				}
				mData += "</urlIma>";
				mData += "<serviceName>\n";
				mData += item.getServicename();
				mData += "</serviceName>";
				mData += "<Active>\n";
				mData += item.getActive();
				mData += "</Active>";
				mData += "<sl>\n";
				try {
					mData += additionLiveTvService.showSoLuongNguoiXemTv(port)
							+ "\n";
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mData += "</sl>";
				mData += "</Item>";
			}
		}
		mData += "</xml>";
		// buoc 2: luu channel chua ton tai vao db
		for (int im = 0; im < xmlstring.size(); im++) {
			HashMap<String, String> xmleach = xmlstring.get(im);
			String image = xmleach.get("image");
			String namechannel = xmleach.get("namechannel");
			String namenumber = xmleach.get("namenumber");
			String status = xmleach.get("status");
			String url = xmleach.get("url");
			if (vLiveTV.size() > 0) {
				for (int i = 0; i < vLiveTV.size(); i++) {
					LiveTV item = vLiveTV.get(i);
					// ko luu vao db vi da ton tai 8.1
					if (item.getChannelname().equals(namechannel)) {
						break;
					}
					// khi van chua tim duoc channel dc load len trong db thi se
					// luu vao db 8.1
					if (i == vLiveTV.size() - 1) {
						checkChannelDbi(namechannel, namenumber, url, status,
								image);
					}
				}
			}
			// mac khac neu nhu hoan toan chua co du lieu thi chep vao db 9.1
			else {
				checkChannelDbi(namechannel, namenumber, url, status, image);
			}
		}
		return mData;
	}

	private String getSubjectMenu() {
		Vector<Subject> vGroup = livetv.getSubjects(LangID);
		String mData = "";
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		mData += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		if (vGroup.size() != 0) {
			mData += "<xml menuIdGF=\"" + vGroup.get(0).getId() + "\">";
		} else {
			mData += "<xml menuIdGF=\"" + 0 + "\">";
		}
		for (int i = 0; i < vGroup.size(); i++) {
			Subject item = vGroup.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>";
			mData += item.getId();
			mData += "</id>";
			mData += "<parent>";
			mData += item.getParentId();
			mData += "</parent>\n";
			mData += "<urlIma>";
			if (null != item.getUrlImage()) {
				mData += config._pathliveTV + "/" + item.getUrlImage();
			} else {
				mData += "emp";
			}

			mData += "</urlIma>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private int addSubjectMenu(HttpServletRequest request,
			HttpServletResponse response) {
		Subject subject = new Subject();
		String subjectName = "";
		String ulrImag = "";
		int parent = 0;
		if (request.getParameter("parent") != null) {
			parent = Integer.parseInt(request.getParameter("parent"));
		}
		if (request.getParameter("name") != null) {
			subjectName = request.getParameter("name").toString().trim();
		}
		if (request.getParameter("image") != null) {
			ulrImag = request.getParameter("image").toString().trim();
		}
		subject.setName(subjectName);
		subject.setUrlImage(ulrImag);
		int plag = livetv.addSubject(subject, parent);
		if (plag > 0) {
			ConfigLoader loader = new ConfigLoader();
			Config config = loader.getConfig();
			ManagerFile file = new ManagerFile();
			String path1 = config._temp + "/" + ulrImag;
			String path2 = config._pathImage + config._pathliveTV + "/"
					+ ulrImag;
			file.copy(path1, path2);
			file.deletefile(path1);
		}
		return plag;
	}

	private boolean deleteSubject(HttpServletRequest request,
			HttpServletResponse response) {
		int subjId = 0;
		if (request.getParameter("subjId") != null) {
			subjId = Integer.parseInt(request.getParameter("subjId"));
		}
		boolean plag = livetv.removeSubject(subjId);
		return plag;
	}

	private int UpdateSubject(HttpServletRequest request,
			HttpServletResponse response) {
		int subid = 0;
		String image = "";
		String name = "";
		if (request.getParameter("subid") != null) {
			subid = Integer.parseInt(request.getParameter("subid"));
		}
		if (request.getParameter("image") != null) {
			image = request.getParameter("image").toString().trim();
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		Subject subject = new Subject();
		subject.setId(subid);
		subject.setName(name);
		subject.setUrlImage(image);
		int plag = livetv.editSubject(subject, LangID);
		if (plag > 0) {
			ConfigLoader loader = new ConfigLoader();
			Config config = loader.getConfig();
			ManagerFile file = new ManagerFile();
			String path1 = config._temp + "/" + image;
			String path2 = config._pathImage + config._pathliveTV + "/" + image;
			file.copy(path1, path2);
			file.deletefile(path1);
		}
		return plag;
	}

	public String getChannelInfo(int menuid1, int index, int page) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		int c = 0;
		int fr = index * page;
		fr += 1;
		c = fr;
		int to = (index + 1) * page;
		Vector<LiveTV> vLiveTV = livetv.getChannels(menuid1, LangID, fr, to);
		String mData = "";
		int count = livetv.countChannels(menuid1);
		mData += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + count + "\" >";
		if (count < c && count != 0) {
			if (count % 6 == 0) {
				nxt = count - 5;
				pr = count;
				c = pr;
			}
			vLiveTV = livetv.getChannels(menuid1, LangID, nxt, pr);
			c = nxt;
		}
		for (int i = 0; i < vLiveTV.size(); i++) {
			LiveTV item = vLiveTV.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getChannelname() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getChannelid();
			mData += "</id>";
			mData += "<stt>\n";
			mData += c++;
			mData += "</stt>";
			mData += "<ChannelCode>\n";
			mData += item.getChannelcode();
			mData += "</ChannelCode>";
			mData += "<address>";
			if (null != item.getPhysical_address()) {
				mData += "<![CDATA[" + item.getPhysical_address() + "]]>";
			} else {
				mData += -1;
			}

			mData += "</address>";
			mData += "<urlIma>\n";
			if (null != item.getUrlImage()) {
				mData += config._pathliveTV + "/" + item.getUrlImage();
			} else {
				mData += "emp".trim();
			}
			mData += "</urlIma>";
			mData += "<serviceName>\n";
			mData += item.getServicename();
			mData += "</serviceName>";
			mData += "<Active>\n";
			mData += item.getActive();
			mData += "</Active>";
			String url = item.getPhysical_address();
			String port = url.substring(url.lastIndexOf(":") + 1);
			mData += "<sl>\n";
			try {
				mData += additionLiveTvService.showSoLuongNguoiXemTv(port)
						+ "\n";
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mData += "</sl>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}

	private boolean ChangeStatus(HttpServletRequest request,
			HttpServletResponse response) {
		int subjctId = 0;
		int livetvId = 0;
		if (request.getParameter("subjctId") != null) {
			subjctId = Integer.parseInt(request.getParameter("subjctId"));
		}
		if (request.getParameter("livetvId") != null) {
			livetvId = Integer.parseInt(request.getParameter("livetvId"));
		}
		boolean plag = livetv.changStatus(subjctId, livetvId);
		return plag;
	}

	private boolean deleteChannel(HttpServletRequest request,
			HttpServletResponse response) {
		int subjctId = 0;
		String chuoi = "";
		if (request.getParameter("subjctId") != null) {
			subjctId = Integer.parseInt(request.getParameter("subjctId"));
		}
		if (request.getParameter("str") != null) {
			chuoi = request.getParameter("str").toString().trim();
		}
		boolean plag = livetv.removeChannel(subjctId, chuoi);
		return plag;
	}

	private int EditChannel(HttpServletRequest request,
			HttpServletResponse response) {
		int channelId = 0;
		String name = "";
		String image = "";
		String link = "";
		if (request.getParameter("channelId") != null) {
			channelId = Integer.parseInt(request.getParameter("channelId"));
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		if (request.getParameter("link") != null) {
			link = request.getParameter("link").toString().trim();
		}
		if (request.getParameter("image") != null) {
			image = request.getParameter("image").toString().trim();
		}
		LiveTV tv = new LiveTV();
		tv.setChannelid(channelId);
		tv.setServicename(name);
		tv.setUrlImage(image);
		
//		int plag = livetv.updateChannel(tv, LangID);
		int plag = LiveTvServiceDBI.updateLiveTV(channelId, name, link, image);
		if (plag > 0) {
			ConfigLoader loader = new ConfigLoader();
			Config config = loader.getConfig();
			ManagerFile file = new ManagerFile();
			String path1 = config._temp + "/" + image;
			String path2 = config._pathImage + config._pathliveTV + "/" + image;
			file.copy(path1, path2);
			file.deletefile(path1);
		}
		return plag;
	}

	private boolean deleteLiveTV(HttpServletRequest request,
			HttpServletResponse response) {
		int subjId = 0;
		String str = "";
		if (request.getParameter("subjId") != null) {
			subjId = Integer.parseInt(request.getParameter("subjId"));
		}
		if (request.getParameter("str") != null) {
			str = request.getParameter("str").toString().trim();
		}
		String chuoi = "(" + str + ")";
		boolean plag = livetv.removeChannel(subjId, chuoi);
		return plag;
	}

	private String getChannelContentOutSujectSrc(int subjectId) {

		Vector<LiveTV> vLiveTV = livetv.getChannelContentOutSuject(subjectId,
				LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < vLiveTV.size(); i++) {
			LiveTV item = vLiveTV.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getChannelname() + "]]>";
			mData += "</name>";
			mData += "<id>";
			mData += item.getChannelid();
			mData += "</id>";
			mData += "<active>";
			mData += item.getActive();
			mData += "</active>\n";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	public String getChannelInfoNotService(int subjectId) {
		Vector<LiveTV> vLiveTV = livetv.getChannels(subjectId, LangID, -1, -1);
		int count = livetv.countChannels(subjectId);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData += "<xml count=\"" + count + "\" >";
		for (int i = 0; i < vLiveTV.size(); i++) {
			LiveTV item = vLiveTV.get(i);
			mData += "<Item>";
			mData += "<id>\n";
			mData += item.getChannelid();
			mData += "</id>";
			mData += "<serviceName>\n";
			mData += item.getServicename();
			mData += "</serviceName>";
			mData += "<active>\n";
			mData += item.getActive();
			mData += "</active>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}

	private boolean addLiveTVChannel(HttpServletRequest request,
			HttpServletResponse response) {
		String str = "";
		int subjId = 0;
		if (request.getParameter("subjId") != null) {
			subjId = Integer.parseInt(request.getParameter("subjId"));
		}
		if (request.getParameter("str") != null) {
			str = request.getParameter("str").toString().trim();
		}
		String chuoi = "(" + str + ")";
		System.out.println("subjId=" + subjId + " str=" + str);
		boolean plag = livetv.addLiveTV(subjId, chuoi);
		return plag;
	}

	private boolean removeLiveTVChannel(HttpServletRequest request,
			HttpServletResponse response) {
		String str = "";
		int subjId = 0;
		if (request.getParameter("subjId") != null) {
			subjId = Integer.parseInt(request.getParameter("subjId"));
		}
		if (request.getParameter("str") != null) {
			str = request.getParameter("str").toString().trim();
		}
		String chuoi = "(" + str + ")";
		boolean plag = livetv.removeChannel(subjId, chuoi);
		return plag;
	}

	public String searchChannelLive(int subjId, String liveName, int index,
			int page) {
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		int c = 0;
		int fr = index * page;
		fr += 1;
		c = fr;
		int to = (index + 1) * page;
		Vector<LiveTV> vLiveTV = livetv.searchChannel(subjId, liveName, fr, to);
		String mData = "";
		int count = livetv.countSearchChannel(subjId, liveName);
		mData += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + count + "\" >";
		if (count < c && count != 0) {
			if (count % 6 == 0) {
				nxt = count - 5;
				pr = count;
				c = pr;
			}
			vLiveTV = livetv.getChannels(subjId, LangID, nxt, pr);
			c = nxt;
		}
		for (int i = 0; i < vLiveTV.size(); i++) {
			LiveTV item = vLiveTV.get(i);
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getChannelname() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getChannelid();
			mData += "</id>";
			mData += "<stt>\n";
			mData += c++;
			mData += "</stt>";
			mData += "<ChannelCode>\n";
			mData += item.getChannelcode();
			mData += "</ChannelCode>";
			mData += "<address>";
			mData += "<![CDATA[" + item.getPhysical_address() + "]]>";
			mData += "</address>";
			mData += "<urlIma>\n";
			if (null != item.getUrlImage()) {
				mData += config._pathliveTV + "/" + item.getUrlImage();
			} else {
				mData += "emp".trim();
			}
			mData += "</urlIma>";
			mData += "<serviceName>\n";
			mData += item.getServicename();
			mData += "</serviceName>";
			mData += "<Active>\n";
			mData += item.getActive();
			mData += "</Active>";
			String url = item.getPhysical_address();
			String port = url.substring(url.lastIndexOf(":") + 1);
			mData += "<sl>\n";
			try {
				mData += additionLiveTvService.showSoLuongNguoiXemTv(port)
						+ "\n";
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mData += "</sl>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
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
	
	public String getChannelAdvertise() {
		String mData = "";
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		mData += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		List<LiveModel> list = LiveTvServiceDBI.getListChannelAdvertise();
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getChannelName() + "]]>";
			mData += "</name>";
			mData += "<id>";
			mData += list.get(i).getChannelId();
			mData += "</id>";
			mData += "<parent>";
			mData += list.get(i).getUrl();
			mData += "</parent>\n";
			mData += "<urlIma>";
			mData += config._pathliveTV + "/" + list.get(i).getImage();
			mData += "</urlIma>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}
	
	public boolean deleteFileVideoAdvertise(String filename) {
		System.out.println("Delete file on server 12: "+ filename);
		boolean flag = true;
		Session session = null;
		Channel channel = null;
		Channel channelSftp = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession("root", "192.168.99.12", 22);
			session.setPassword("123456");
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
//			channelSftp.rm("/home/data/loop_video/" + filename);
			channel.disconnect();
			session.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
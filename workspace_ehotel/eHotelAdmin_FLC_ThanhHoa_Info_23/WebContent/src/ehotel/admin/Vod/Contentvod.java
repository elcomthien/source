package ehotel.admin.Vod;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.common.GetImages;
import ehotel.admin.common.ftpClient;
import ehotel.admin.common.imdFilm;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigEod;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.UtilString;
import ehotel.core.VODStruct;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;
import ehotel.inter.AMDVod;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;
import ehotel.render.VOD;
import ehotel.req.server.EVSReqInfo;

public class Contentvod extends ServiceParent {

	/**
	 * Constructor of the object.
	 */
	public Contentvod() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int cmd=-1;
		if(request.getParameter("CMD")!=null)
		{
			try{
				cmd=Integer.parseInt(request.getParameter("CMD").toString());
			}catch (Exception e) {
				// TODO: handle exception
			}		
		}	
		switch (cmd) 
		{
		case -1:
		{
			int subId=-1;
			int menuid=-1;
			if(request.getParameter(Def.MenuId)!=null)
			{
				menuid=Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			if(request.getParameter(Def.SubId)!=null)
			{
				subId=Integer.parseInt(request.getParameter(Def.SubId).toString());
			}			
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);
			this.showJSPpage(request, response, "/vodMgn/content/VodCtnMain.jsp");
			break;
		}
		case 1:
		{
			System.out.println("Get subject Ctn");
			response.setContentType("text/xml");
			String st=getsubVodCtn();				
			out.print(st);
			break;
		}
		case 2://get movie content
		{
			int page=5;
			int subId=-1;
			int index=0;
			if(request.getParameter(Def.SubId)!=null)
			{
				subId=Integer.parseInt(request.getParameter(Def.SubId).toString());
			}	
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}
			System.out.println("Get list vod content");
			response.setContentType("text/xml");
			String st=getListMovie(subId, index, page);				
			out.print(st);
			break;
		}
		case 4://delete Movie
		{
			
			ILOGIN iuser = DBIGateway.getILogin();	
		    String ipAdress=request.getRemoteAddr();
			if(iuser.isAdmin(ipAdress)||iuser.checkRoleUser(ipAdress,Def._roleVIDEO))
			{
				int VodId=-1;
				if(request.getParameter(Def.VodId)!=null)
				{
					VodId=Integer.parseInt(request.getParameter(Def.VodId).toString());
				}				
				System.out.println("delete Vod Id:"+VodId);							
				EVSReqInfo evs= new EVSReqInfo();				
				boolean b= evs.removeVod(VodId);		
				
			}else
			{
				out.write(-1);
			}
			break;
		}
		case 5://break;
		{
			int subId=-1;
			int VodId=-1;
			if(request.getParameter(Def.VodId)!=null)
			{
				VodId=Integer.parseInt(request.getParameter(Def.VodId).toString());
			}
			if(request.getParameter(Def.SubId)!=null)
			{
				subId=Integer.parseInt(request.getParameter(Def.SubId).toString());
			}
			VOD  vodDBI = DBIGateway.getAMDVod();	
			AMDVod ctnMod = vodDBI.getAMDCntVod();	
			boolean b= ctnMod.changeSubjectOfVod(VodId, subId);
			System.out.println("change Vod Id:"+VodId +" to subid:"+subId);			
			break;
		}
		case 6://get ftp file
		{
			System.out.println("get list file FTP");
			String path="";
			if(request.getParameter("path")!=null)
			{
				path=request.getParameter("path").toString().trim();
			}			
			response.setContentType("text/xml");
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();	
			
			String Host=config._HostSubtitle;
			String Pass=config._passSubtitle;
			int Port=config._PortSubtitle;
			String User=config._UserSubtitle;			
			String st=getfileFTP(path,Host,User,Pass,Port);
			out.print(st);
			break;
		}
		
		case 7:
		{
			System.out.println("add trailert");
			String path="";
			if(request.getParameter("path")!=null)
			{
				path=request.getParameter("path").toString().trim();
			}
			int VodId=-1;
			if(request.getParameter(Def.VodId)!=null)
			{
				VodId=Integer.parseInt(request.getParameter(Def.VodId).toString());
			}
			EVSReqInfo evs= new EVSReqInfo();
			int seq = evs.addTrailer(VodId, path);			
			break;
		}
		case 8://get ftp file
		{
			System.out.println("get list file FTP");
			String path="";
			if(request.getParameter("path")!=null)
			{
				path=request.getParameter("path").toString().trim();
			}			
			response.setContentType("text/xml");
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			String Host=config._HostSubtitle;
			String Pass=config._passSubtitle;
			int Port=config._PortSubtitle;
			String User=config._UserSubtitle;
			String st=getfileFTP(path,Host,User,Pass,Port);
			out.print(st);
			break;
		}
		case 9:
		{
			int VodId=-1;
			if(request.getParameter(Def.VodId)!=null)
			{
				VodId=Integer.parseInt(request.getParameter(Def.VodId).toString());
			}
			response.setContentType("text/xml");
			String st=getSubtitle(VodId);
			out.print(st);
			break;
		}
		case 10://add subtitle
		{
			Vector<Integer> lang=new Vector();
			Vector< String> name=new Vector();
			Vector<Integer> ID=new Vector();
			Vector<SubTitle> subtitle=new Vector();
			int VodId=-1;
			if(request.getParameter(Def.VodId)!=null)
			{
				VodId=Integer.parseInt(request.getParameter(Def.VodId).toString());
			}
			int i=0;
			while(request.getParameter("lang"+i)!=null)
			{
				int l=Integer.parseInt(request.getParameter("lang"+i).toString().trim());
				lang.add(l);
				i++;
			}
			i=0;
			while(request.getParameter("name"+i)!=null)
			{
				String n=request.getParameter("name"+i).toString();
				name.add(n);
				i++;
			}
			i=0;
			while(request.getParameter("id"+i)!=null)
			{
				int id= Integer.parseInt(request.getParameter("id"+i).toString().trim());
				ID.add(id);
				i++;
			}
			for(i=0;i<lang.size();i++)
			{
				System.out.println("lang:"+lang.get(i)+" ID:"+ID.get(i)+" Name:"+name.get(i));
				SubTitle sub=new SubTitle();		
				sub.setName(name.get(i));
				sub.setUrl(name.get(i));
				sub.setLangId(lang.get(i));
				sub.setSubId(ID.get(i));
				subtitle.add(sub);
				
			}
			System.out.println("VOD:"+VodId);
			System.out.println(subtitle.get(0).getUrl());
			EVSReqInfo evs= new EVSReqInfo();			
			evs.addSubtitle(VodId,subtitle);
						
			break;
		}
		case 11://dlete subtitel
		{
			Vector<Integer> list=new Vector();
			EVSReqInfo evs= new EVSReqInfo();
			int i=0;
			
			while(request.getParameter("id"+i)!=null)
			{
				int id= Integer.parseInt(request.getParameter("id"+i).toString().trim());
				list.add(id);
				evs.removeSubtitle(id);
				i++;
			}				
			break;
		}
		case 12:
		{
			String title="";
			if(request.getParameter("title")!=null)
			{
				title=request.getParameter("title").toString();
			}
			response.setContentType("text/xml");
			String st=getInfoFilm(request,title);
			out.print(st);
			break;
		}
		case 13://get curent upload movie
		{
			
			int title=-1;
			if(request.getParameter("title")!=null)
			{
				title=Integer.parseInt(request.getParameter("title").toString().trim());
			}			
			EVSReqInfo evs= new EVSReqInfo();
			response.setContentType("text/xml");
			
			try
			{
				
				VODStruct[] vod= evs.getProcessingVod();
				String st= gerProcessrun(vod);
				
				out.print(st);
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			break;
		}
		case 14://stop upload movie
		{
			System.out.println("Stop upload movie:");
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			System.out.println("Stop upload movie:"+id);
			EVSReqInfo evs=new EVSReqInfo();
			evs.stopProcessingVod(id);
			break;
		}
		case 15://list file movie ftp
		{
			System.out.println("get list file Movie FTP");
			String path="";
			if(request.getParameter("path")!=null)
			{
				path=request.getParameter("path").toString().trim();
			}			
			response.setContentType("text/xml");
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			String Host=config._HostSubtitle;
			String Pass=config._passSubtitle;
			int Port=config._PortSubtitle;
			String User=config._UserSubtitle;
			String st=getfileFTP(path,Host,User,Pass,Port);
			out.print(st);
			break;
		}
		default:
			break;
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.doPost(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd=-1;
		if(request.getParameter("CMD")!=null)
		{
			try{
				cmd=Integer.parseInt(request.getParameter("CMD").toString());
			}catch (Exception e) {
				// TODO: handle exception
			}		
		}			
		switch (cmd)
		{
		case 1:
		{
			System.out.println("inser subject VOD!");
			response.setContentType("text/xml");
			String subjectName="";
			String image="";
			int parent=-1;
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString();
			}
			if(request.getParameter("parent")!=null)
			{
				parent=Integer.parseInt(request.getParameter("parent").toString());
			}
			if(request.getParameter("name")!=null)
			{								
				subjectName=request.getParameter("name").toString();
				
				VOD  vodDBI = DBIGateway.getAMDVod();	
				AMDVod ctnMod = vodDBI.getAMDCntVod();					
				//subjectName=UtilString.uni2ent2ndTry(subjectName);			
				int id= ctnMod.addSubject(subjectName,image,parent);	
				/**
				 * copy 
				 */				
				if(id>0)
				{
					ConfigLoader loader=new ConfigLoader();
					Config config=loader.getConfig();
					ManagerFile file=new ManagerFile();
					String path1=request.getRealPath(config._temp)+"/"+image;
					String path2=request.getRealPath(config._pathImage)+"/"+image;
					file.copy(path1, path2);
					file.deletefile(path1);
				}					
				out.write(id);
			}			
			break;
		}
		case 2://update subject
		{
			String subjectName="";
			String image="";
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString();
			}
			int subjId=-1;			
			if(request.getParameter("name")!=null)
			{
				if(request.getParameter(Def.SubId)!=null)
				{			
					
					subjId=Integer.parseInt(request.getParameter(Def.SubId).toString());
					subjectName=request.getParameter("name").toString();				
					VOD  vodDBI = DBIGateway.getAMDVod();	
					AMDVod ctnMod = vodDBI.getAMDCntVod();	
					//subjectName=UtilString.uni2ent2ndTry(subjectName);
					int t= ctnMod.editSubject(subjId,subjectName,image ,LangID);					
					out.print(t);
					System.out.println("Upafte:"+t);
					if(t>0)
					{						
						ConfigLoader loader=new ConfigLoader();
						Config config=loader.getConfig();
						ManagerFile file=new ManagerFile();					
						String path1=request.getRealPath(config._temp)+"/"+image;
						String path2=request.getRealPath(config._pathImage)+"/"+image;
						
						file.copy(path1, path2);
						file.deletefile(path1);
					}
				}
			}
			break;
		}
		case 3:
		{
			System.out.println("delete subject vod id:");
			int subjId=-1;
			if(request.getParameter(Def.SubId)!=null)
			{
				subjId=Integer.parseInt(request.getParameter(Def.SubId).toString());
				VOD  vodDBI = DBIGateway.getAMDVod();	
				AMDVod ctnMod = vodDBI.getAMDCntVod();
				boolean b= ctnMod.removeSubject(subjId);
				System.out.println("delete:"+b);
			}
			break;
		}
		case 4:
		{
			System.out.println("Update VOD");
			updateVod(request, response);
			break;
		}
		case 5:
		{
			System.out.println("Insert VOD");
			String name="";
			String actors="";
			String director="";
			String desc="";
			String image="";
			String file="";
			int subId=-1;
			String price="0";
			String money="";
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString();
			}
			if(request.getParameter("actor")!=null)
			{
				actors=request.getParameter("actor").toString();
			}
			if(request.getParameter("director")!=null)
			{
				director=request.getParameter("director").toString();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString();
			}
			if(request.getParameter("Desc")!=null)
			{
				desc=request.getParameter("Desc").toString();
			}
			if(request.getParameter("file")!=null)
			{
				file=request.getParameter("file").toString();
			}
			if(request.getParameter("SubId")!=null)
			{
				subId=Integer.parseInt(request.getParameter("SubId").toString().trim());
			}
			if(request.getParameter("price")!=null)
			{
				price=request.getParameter("price").toString().trim();
			}
			if(request.getParameter("money")!=null)
			{
				money=request.getParameter("money").toString().trim();
			}
			Vod vod=new Vod();
			vod.setTitle(name);
			vod.setActors(actors);
			vod.setDirector(director);
			vod.setPlot(desc);
			vod.setPoster(image);
			vod.setFilePath(file);
			vod.setCurrency(price);
			vod.setIUnit(money);
			EVSReqInfo evs = new EVSReqInfo();			
			int t= 1;
				t=evs.addVod(subId, vod);			
			out.print(t);		
			System.out.println("End:"+t);
			break;
		}
		default:
			break;
		}		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getsubVodCtn()
	{
		VOD  VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDCntVod();
		Vector<Subject> subject=SrvMod.getSubjects(LangID);		
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		
		for(int i=0;i<subject.size();i++)
		{
			Subject item=subject.get(i);
			
			mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+item.getName()+"]]>";
				mData+="</name>";
				mData+="<id>\n";
				mData+=item.getId();
				mData+="</id>\n";
				mData+="<image>\n";
				mData+=item.getUrlImage();
				mData+="</image>";
				mData+="<parent>";
				mData+=item.getParentId();
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
	private String getListMovie(int subId,int index,int page)
	{
		
		VOD  VodDBI = DBIGateway.getAMDVod();
		
		AMDVod ctnMod = VodDBI.getAMDCntVod();
		
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;
		
		int count=ctnMod.countVodOfSubject(subId);	
		
		Vector<Vod> vod=ctnMod.getVods(subId, LangID, fr, to);
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml count='"+count+"'>";
		System.out.println("Content:"+count);
		for(int i=0;i<vod.size();i++)
		{
			String actors="";
			if(vod.get(i).getActors()!=null)
			actors=	vod.get(i).getActors();
			
			if(actors.length()>40)actors=actors.substring(0,25)+"...";
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+vod.get(i).getTitle()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=vod.get(i).getId();
			mData+="</id>";
			mData+="<Actors>";
			mData+="<![CDATA["+actors+"]]>";				
			mData+="</Actors>";
			mData+="<Director>";						
			mData+="<![CDATA["+vod.get(i).getDirector()+"]]>";							
			mData+="</Director>";
			mData+="<Duration>";				
			mData+="00";				
			mData+="</Duration>";
			mData+="<New>";
			mData+=vod.get(i).getIstrailer();
			mData+="</New>";
			mData+="<Desc>";
			mData+="<![CDATA["+(vod.get(i).getPlot())+"]]>";	
			mData+="</Desc>";
			mData+="<Image>";
			mData+=	vod.get(i).getPoster();
			mData+="</Image>";
			mData+="<Status>";
			mData+=vod.get(i).getIssubtile();
			mData+="</Status>";
			mData+="</Item>";
		}			
		mData+="</xml>";	
		
		return mData;
	}
	private void updateVod(HttpServletRequest request, HttpServletResponse response)
	{
		int id=-1;
		String name="";
		String actors="";
		String director="";
		String desc="";
		String image="";
		int price=0;
		String money="USD";
		if(request.getParameter("name")!=null)
		{
			name=request.getParameter("name").toString();
		}
		
		if(request.getParameter("id")!=null)
		{
			try
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		if(request.getParameter("image")!=null)
		{
			image=request.getParameter("image").toString();
		}
		if(request.getParameter("Actors")!=null)
		{
			actors=request.getParameter("Actors").toString();
		}
		if(request.getParameter("Director")!=null)
		{
			director=request.getParameter("Director").toString();
		}
		if(request.getParameter("Desc")!=null)
		{
			desc=request.getParameter("Desc").toString();
		}		
		if(request.getParameter("price")!=null)
		{
			price=Integer.parseInt(request.getParameter("price").toString().trim());
		}
		if(request.getParameter("money")!=null)
		{
			money=request.getParameter("money").toString().trim();
		}
		VOD  VodDBI = DBIGateway.getAMDVod();
		AMDVod cntMod = VodDBI.getAMDCntVod();
		
		Vod vod=new Vod();
		vod.setId(id);
		vod.setTitle(name);
		vod.setActors(actors);
		vod.setDirector(director);
		vod.setPlot(desc);
		vod.setPoster(image);	
		vod.setCurrency(String.valueOf(price));
		vod.setIUnit(money);
		boolean b= cntMod.updateVod(vod, LangID);
		if(b)
		{
			
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			ManagerFile file=new ManagerFile();					
			String path1=request.getRealPath(config._temp)+"/"+image;
			String path2=request.getRealPath(config._pathImageVod)+"/"+image;
			file.copy(path1, path2);
			file.deletefile(path1);
		}
	}
	private String getfileFTP(String path,String Host,String User,String Pass,int Port)
	{
		
		ftpClient ftp=new ftpClient(Host,User,Pass,Port);		
		ftp.connect();
		Vector< String> v_files=new Vector();
		Vector< String> v_folder=new Vector();
		v_files=ftp.getFiles(path);
		v_folder=ftp.getFolder(path);
		ftp.close();
		for(int i=0;i<v_folder.size();i++)
		{
			
			for(int j=0;j<v_files.size();j++)
			{
				if(v_files.get(j).equals(v_folder.get(i)))
				{
					v_files.remove(j);
					j--;
				}
			}
		}
		for(int i=0;i<v_folder.size();i++)
		{
			if(v_folder.get(i).equals(".")||v_folder.get(i).equals(".."))
			{
				v_folder.remove(i);i--;
			}
		}		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";			
		for(int i=0;i<v_folder.size();i++)
		{
			mData+="<Item>";
			mData+="<name>";
			mData+=v_folder.get(i);
			mData+="</name>";
			mData+="<type>";
			mData+="1";
			mData+="</type>";
			mData+="</Item>";
		}
		for(int i=0;i<v_files.size();i++)
		{
			System.out.println("file:"+v_files.get(i));
			mData+="<Item>";
			mData+="<name>";			
			mData+=v_files.get(i);
			mData+="</name>";
			mData+="<type>";
			mData+="0";
			mData+="</type>";
			mData+="</Item>";
		}
		mData+="</xml>";	
		return mData;					
	}
	private String getSubtitle(int VodId)
	{
		VOD  VodDBI = DBIGateway.getAMDVod();
		AMDVod ctnMod = VodDBI.getAMDCntVod();
		Vector<SubTitle> v_rs=ctnMod.getSubtiles(VodId);		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<v_rs.size();i++)
		{
			mData+="<Item>";
			mData+="<id>";
			mData+=v_rs.get(i).getSubId();
			mData+="</id>";
			mData+="<name>";
			mData+=v_rs.get(i).getUrl();
			mData+="</name>";
			mData+="<type>";
			mData+=v_rs.get(i).getLangId();
			mData+="</type>";
			mData+="</Item>";
		}
		mData+="</xml>";
		return mData;
	}
	private String getInfoFilm(HttpServletRequest request,String title)
	{
		ConfigLoader loader=new ConfigLoader();
		Config config=loader.getConfig();
		imdFilm info=new imdFilm();
		info.setName(title);
		String des=info.getPlot();
		GetImages image=new GetImages();	
		String path=request.getRealPath(config._temp)+"/";
		System.out.println("des:"+info.getPlot());
		String imName=image.read(path, info.getPoster());
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		mData+="<id>";
		mData+=info.getID();
		mData+="</id>";
		mData+="<name>";
		mData+=info.getTitle();		
		mData+="</name>";
		mData+="<Actors>";
		mData+=info.getActors();
		mData+="</Actors>";
		mData+="<Director>";
		mData+=info.getDirector();
		mData+="</Director>";
		mData+="<Image>";
		mData+= imName;
		mData+="</Image>";
		mData+="<Descript>";
		mData+= des;
		mData+="</Descript>";
		mData+="</xml>";
		return mData;
	}
	private String gerProcessrun(VODStruct[] vod)
	{
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		DecimalFormat df = new DecimalFormat("#.##");

			for(int i=0;i<vod.length;i++)
			{
				mData+="<Item>";
				mData+="<id>";
				mData+=vod[i].getSeq();
				mData+="</id>";	
				mData+="<name>";
				mData+=vod[i].getFilename();
				mData+="</name>";	
				mData+="<percent>";
				mData+=df.format(vod[i].getPercent());
				mData+="</percent>";
				mData+="</Item>";
			}
		mData+="</xml>";
		return mData;
	}
}

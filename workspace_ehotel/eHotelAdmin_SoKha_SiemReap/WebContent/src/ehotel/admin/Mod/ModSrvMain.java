package ehotel.admin.Mod;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.UtilString;
import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDMod;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;
import ehotel.render.MOD;


public class ModSrvMain extends ServiceParent {

	/**
	 * Constructor of the object.
	 */
	public ModSrvMain() {
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
			this.showJSPpage(request, response, "/modMgn/service/MainModSrv.jsp");
			break;
		}
		case 1://load menu
		{
			System.out.println("Get subject ModSrv");
			response.setContentType("text/xml");
			String st=getsubMod();				
			out.print(st);
			break;
		}
		
		case 2://xoa subject
		{
			int idsub=-1;
			if(request.getParameter("id")!=null)
			{
				idsub=Integer.parseInt(request.getParameter("id").toString());
				MOD  modDBI = DBIGateway.getAMDMod();		
				AMDMod cntMod = modDBI.getAMDSvcMod();
				int t= cntMod.removeSubject(idsub);
				System.out.println(t);
			}				
			break;
		}
		case 3:
		{
			System.out.println("Get content");
			int id=-1;
			int index=0;
			int page=6;
			if(request.getParameter("SubId")!=null)
			{
				id=Integer.parseInt(request.getParameter("SubId").toString());
			}
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}
			response.setContentType("text/xml");
			String st= getContentMp3(id,index,page);			
			out.print(st);
			break;
		}
		case 4://add mod to service
		{
			int id=-1;
			int subid=-1;
			if(request.getParameter("SubId")!=null)
			{
				subid=Integer.parseInt(request.getParameter("SubId").toString());
			}
			if(request.getParameter("vodId")!=null)
			{
				id=Integer.parseInt(request.getParameter("vodId").toString().trim());
			}
			MOD  modDBI = DBIGateway.getAMDMod();
			AMDMod srvMod = modDBI.getAMDSvcMod();
			boolean t= srvMod.addMod(subid, id);
			out.print(t);
			System.out.println("Add service MOD:"+t);
			break;
		}
		case 5://delete service MOD
		{
			System.out.println("Delete MOD Service");
			ILOGIN iuser = DBIGateway.getILogin();	
		    String ipAdress=request.getRemoteAddr();
			if(iuser.isAdmin(ipAdress)||iuser.checkRoleUser(ipAdress,Def._roleMOD))
			{
			int id=-1;
			int subid=-1;
			if(request.getParameter("SubId")!=null)
			{
				subid=Integer.parseInt(request.getParameter("SubId").toString().trim());
			}
			int i=0;			
			Vector<Integer> list=new Vector();			
			while(request.getParameter("modId"+i)!=null)
			{
				id=Integer.parseInt(request.getParameter("modId"+i).toString().trim());
				list.add(id);
				i++;
			}	
			
			String param="(";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}			
			param+=")";
			MOD  modDBI = DBIGateway.getAMDMod();
			AMDMod srvMod = modDBI.getAMDSvcMod();
			boolean t= srvMod.removeMods(subid,param);
			}else
			{
				out.print(-1);
			}
			
			break;
		}
		case 6://get subject  in MOD
		{
			int id=-1;
			response.setContentType("text/xml");
			
			if(request.getParameter("modId")!=null)
			{
				id=Integer.parseInt(request.getParameter("modId").toString().trim());
			}
			String st=getsubAndMod(id);
			out.print(st);
			break;
		}
		case 7:
		{
			int id=-1;
			response.setContentType("text/xml");
			
			if(request.getParameter("modId")!=null)
			{
				id=Integer.parseInt(request.getParameter("modId").toString().trim());
			}
			String st=getsubOutMod(id);
			out.print(st);
			break;
		}
		case 8://
		{
			int i=0;	
			int modId=-1;
			if(request.getParameter("modId")!=null)
			{
				modId=Integer.parseInt(request.getParameter("modId").toString().trim());
			}
			Vector<Integer> list=new Vector();			
			while(request.getParameter("subid"+i)!=null)
			{
				int id=Integer.parseInt(request.getParameter("subid"+i).toString().trim());
				list.add(id);
				i++;
			}
			String param="(";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1);
			param+=")";			
			MOD  modDBI = DBIGateway.getAMDMod();
			AMDMod srvMod = modDBI.getAMDSvcMod();
			srvMod.changeSubjectOfMod(modId, param);
			
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
		switch (cmd) {
		case 1:///inert subject
		{
			
			System.out.println("inser subject!");
			response.setContentType("text/xml");
			String subjectName="";
			String image="";
			String urlBg="";
			int parent=-1;
			if(request.getParameter("urlBg")!=null)
			{
				urlBg=request.getParameter("urlBg").toString().trim();
			}
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
				MOD  modDBI = DBIGateway.getAMDMod();		
				AMDMod srvMod = modDBI.getAMDSvcMod();
				
				//subjectName=UtilString.uni2ent2ndTry(subjectName);
				Subject sub=new Subject();
				sub.setName(subjectName);
				sub.setUrlImage(image);
				sub.setUrlBg(urlBg);
				sub.setParentId(parent);
				
				int id= srvMod.addSubject(sub,LangID);
				System.out.println("Insert subject:"+id);
				if(id>0)
				{
					ConfigLoader loader=new ConfigLoader();
					Config config=loader.getConfig();
					ManagerFile file=new ManagerFile();					
					String path1=request.getRealPath(config._temp)+"/"+image;
					String path2=request.getRealPath(config._pathImage)+"/"+image;
					file.copy(path1, path2);
					file.deletefile(path1);
					path1=request.getRealPath(config._temp)+"/"+urlBg;
					path2=request.getRealPath(config._pathImage)+"/"+urlBg;
					file.copy(path1, path2);
					file.deletefile(path1);
				}
				
			}
			break;
		}
		case 2://edit subject
		{
			String subjectName="";
			String image="";
			String urlBg="";
			if(request.getParameter("urlBg")!=null)
			{
				urlBg=request.getParameter("urlBg").toString().trim();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString();
			}
			int subjId=-1;
		
			if(request.getParameter("name")!=null)
			{
				if(request.getParameter("subid")!=null)
				{			
					subjId=Integer.parseInt(request.getParameter("subid").toString());
					subjectName=request.getParameter("name").toString();				
					MOD  modDBI = DBIGateway.getAMDMod();		
					AMDMod cntMod = modDBI.getAMDSvcMod();	
					//subjectName=UtilString.uni2ent2ndTry(subjectName);
					Subject sub=new Subject();
					
					sub.setName(subjectName);
					sub.setUrlImage(image);
					sub.setId(subjId);		
					sub.setUrlBg(urlBg);
					int t= cntMod.editSubject(sub , LangID);					
					out.print(t);
					if(t>0)
					{						
						ConfigLoader loader=new ConfigLoader();
						Config config=loader.getConfig();
						ManagerFile file=new ManagerFile();					
						String path1=request.getRealPath(config._temp)+"/"+image;
						String path2=request.getRealPath(config._pathImage)+"/"+image;
						file.copy(path1, path2);
						file.deletefile(path1);
						path1=request.getRealPath(config._temp)+"/"+urlBg;
						path2=request.getRealPath(config._pathImage)+"/"+urlBg;
						file.copy(path1, path2);
						file.deletefile(path1);
					}
					
				}
			}
			break;
		}
		case 3://update song 
		{
			System.out.println("Update song service");
			int id=-1;			
			String title="";
			String singer="";
			String album="";
			String lyric="";
			
			if(request.getParameter("id")!=null)
			{				
				id=Integer.parseInt(request.getParameter("id").toString().trim());				
			}
			if(request.getParameter("name")!=null)
			{
				title=request.getParameter("name").toString();
				title=UtilString.uni2ent2ndTry(title);
			}
			if(request.getParameter("singer")!=null)
			{
				singer=request.getParameter("singer");
			}
			if(request.getParameter("album")!=null)
			{
				album=request.getParameter("album");
			}
			if(request.getParameter("lyric")!=null)
			{
				lyric=request.getParameter("lyric");
			}			
			Mod mod=new Mod();
			mod.setAlbum(album);
			mod.setSinger(singer);
			mod.setTitle(title);
			mod.setId(id);
			mod.setLyric(lyric);
			MOD  modDBI = DBIGateway.getAMDMod();		
			AMDMod cntMod = modDBI.getAMDSvcMod();
			Boolean t= cntMod.updateMod(mod, LangID);
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getsubMod()
	{
		
		MOD  modDBI = DBIGateway.getAMDMod();		
		AMDMod srvMod = modDBI.getAMDSvcMod();		
		Vector<Subject> subject=srvMod.getSubjects(LangID);		
		
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
				mData+="<imageBg>\n";
				mData+=item.getUrlBg();
				mData+="</imageBg>";
				mData+="<parent>";
				mData+=-1;
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
	private String getContentMp3(int subjId,int index,int page)
	{
		MOD  modDBI = DBIGateway.getAMDMod();		
		AMDMod srvMod = modDBI.getAMDSvcMod();		
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;		
		Vector<Mod> mod=srvMod.getMods(subjId, LangID, fr,to);
		int count= srvMod.countModOfSubject(subjId);		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml count=\""+count+"\">";
		for(int i=0;i<mod.size();i++)
		{			
			Mod item=mod.get(i);
			System.out.println("lyric:"+item.getLyric());
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getTitle()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";
			mData+="<Album>";
			if(item.getAlbum()==null)
			{
				mData+="<![CDATA["+" "+"]]>";
			}else
			{
				mData+="<![CDATA["+item.getAlbum()+"]]>";
			}
			mData+="</Album>";
			mData+="<Singer>";
			if(item.getSinger()==null)
			{
				mData+="unknow";
			}else
			{
				mData+="<![CDATA["+item.getSinger()+"]]>";
			}
			mData+="</Singer>";
			mData+="<url>";
			mData+="<![CDATA["+item.getUrl()+"]]>";
			mData+="</url>";
			mData+="<lyric>";
			mData+="<![CDATA["+item.getLyric()+"]]>";
			mData+="</lyric>";
			mData+="</Item>";			
		}
		mData+="</xml>";
	
		return mData;
	}
	private String getsubAndMod(int modId)
	{
		
		MOD  modDBI = DBIGateway.getAMDMod();		
		AMDMod srvMod = modDBI.getAMDSvcMod();		
		Vector<Subject> subject=srvMod.getSubjectsInMod(modId,LangID);			
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
				mData+=-1;
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
	private String getsubOutMod(int modId)
	{
		
		
		MOD  modDBI = DBIGateway.getAMDMod();		
		AMDMod srvMod = modDBI.getAMDSvcMod();		
		Vector<Subject> subject=srvMod.getSubjectsOutMod(modId,LangID);		
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<subject.size();i++)
		{
			Subject item=subject.get(i);
			System.out.println("Parent id:"+item.getParentId());
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
				mData+=-1;
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
}

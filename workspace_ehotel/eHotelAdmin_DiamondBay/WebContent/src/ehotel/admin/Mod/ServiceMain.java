package ehotel.admin.Mod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.swing.internal.plaf.basic.resources.basic;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.common.ftpClient;
import ehotel.admin.common.ftpmp3reader;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.UtilString;
import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDMod;

import ehotel.render.DBIGateway;
import ehotel.render.MOD;
import ehotel.req.server.EASReqInfo;
import ehotel.req.server.EVSReqInfo;

public class ServiceMain extends ServiceParent
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4315274348813470111L;
	private static final Vector<Subject> Subject = null;
	/**
	 * Constructor of the object.
	 */
	public ServiceMain() {
		super();
	}
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
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
			throws ServletException, IOException
	{
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
				this.showJSPpage(request, response, "/modMgn/content/MainMod.jsp");
			break;
			}
			case 1:
			{
				System.out.println("Get subject");
				response.setContentType("text/xml");
				String st=getsubMod();				
				out.print(st);
				break;
			}
			case 2:
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
				System.out.println("pageIndex:"+index);
				response.setContentType("text/xml");
				String st= getContentMp3(id,index,page);
				
				out.print(st);
				break;
			}
			case 3://xoa subject
			{
				int idsub=-1;
				if(request.getParameter("id")!=null)
				{
					idsub=Integer.parseInt(request.getParameter("id").toString());
					MOD  modDBI = DBIGateway.getAMDMod();		
					AMDMod cntMod = modDBI.getAMDCntMod();
					int t= cntMod.removeSubject(idsub);
					System.out.println(t);
				}				
				break;
			}
			case 4://xoa song
			{
				System.out.println("delete MP3");
				Vector list=new Vector();
				int subjId=-1;
				int i=0;
				if(request.getParameter("subid")!=null)
				{
					subjId=Integer.parseInt(request.getParameter("subid").toString());
				}
				while(request.getParameter("id"+i)!=null)
				{
					int id=Integer.parseInt(request.getParameter("id"+i).toString().trim());
					list.add(id);
					i++;
				}						
				EASReqInfo easInfo=new EASReqInfo();
				int[] b= easInfo.removeMods(list);
				System.out.println("delete song:"+b.length);
				break;
			}
			case 5:
			{
				System.out.println("get list subject in mod:");
				response.setContentType("text/xml");
				int modId=-1;
				if(request.getParameter("modId")!=null)
				{
					modId=Integer.parseInt(request.getParameter("modId").toString());
					
					String st=getListSubMod(modId);
					out.print(st);
					//System.out.println(st);
				}
				break;
			}
			case 6://get subject not is in mod
			{
				System.out.println("get list subject not in mod:");
				int modId=-1;
				response.setContentType("text/xml");
				if(request.getParameter("modId")!=null)
				{
					modId=Integer.parseInt(request.getParameter("modId").toString());					
					String st=getListSubNOTMod(modId);
					out.print(st);				
				}
				break;
			}
			case 7://update subject to mod
			{
				System.out.println("add subject mod");
				int modId=-1;
				if(request.getParameter("modId")!=null)
				{
					modId=Integer.parseInt(request.getParameter("modId").toString());										
				}
				MOD  modDBI = DBIGateway.getAMDMod();		
				AMDMod cntMod = modDBI.getAMDCntMod();
				
				
				int i=0;
				String param="(";
				while(request.getParameter("subid"+i)!=null)
				{					
					param+=request.getParameter("subid"+i).toString()+",";
					i++;
				}
				param=param.substring(0,param.length()-1)+")";				
				boolean t= cntMod.changeSubjectOfMod(modId, param);
				out.print(t);
				System.out.println(t);
				break;
			}
			case 8://list file mp3
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
				System.out.println("Insert Mod");
				Vector<String> list=new Vector();
				Vector<Boolean> clip=new Vector();
				int i=0;
				int subId=-1;
				if(request.getParameter(Def.SubId)!=null)
				{
					subId=Integer.parseInt(request.getParameter(Def.SubId).toString().trim());
					
				}
				while(request.getParameter("file"+i)!=null)
				{
					list.add(request.getParameter("file"+i).trim());	
					i++;
				}	
				i=0;
				while(request.getParameter("clip"+i)!=null)
				{
					boolean b=Boolean.parseBoolean(request.getParameter("clip"+i).toString().trim());
					clip.add(b);
					i++;
				}			
				for(i=0;i<list.size();i++)
				{
					EASReqInfo easInfo=new EASReqInfo();
					int t= EASReqInfo.AddMod(subId, list.get(i), clip.get(i));	
					if(t>0)
					{
						list.remove(i);
						i--;
					}
				}	
				
				response.setContentType("text/xml");
				String st=listAddfail(list);
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
		
		switch (cmd) {
		case 1://insert subject
		{
			System.out.println("inser subject!");
			response.setContentType("text/xml");
			String subjectName="";
			String image="";
			int parent=-1;
			String urlBg="";
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
				AMDMod cntMod = modDBI.getAMDCntMod();					
				Subject sub=new Subject();
				sub.setName(subjectName);
				sub.setUrlImage(image);
				sub.setUrlBg(urlBg);
				sub.setParentId(parent);
				int id= cntMod.addSubject(sub,parent);	
				/**
				 * copy 
				 */
				String mData="";
				mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
				mData+="<xml>";			
					mData+="<Item>";
						mData+="<name>";
						mData+="<![CDATA["+subjectName+"]]>";
						mData+="</name>";
						mData+="<id>\n";
						mData+=id;
						mData+="</id>\n";
						mData+="<image>\n";
						mData+=image;
						mData+="</image>";
						mData+="<parent>";
						mData+=parent;
						mData+="</parent>";
					mData+="</Item>";				
				mData+="</xml>";		
				
				out.write(mData);
				
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
					AMDMod cntMod = modDBI.getAMDCntMod();							
					Subject sub=new Subject();
					sub.setId(subjId);
					sub.setName(subjectName);
					sub.setUrlImage(image);
					sub.setUrlBg(urlBg);				
					int t= cntMod.editSubject(sub, LangID);						
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
		case 3://edit song
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
				//title=UtilString.uni2ent2ndTry(title);
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
			AMDMod cntMod = modDBI.getAMDCntMod();
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
		AMDMod cntMod = modDBI.getAMDCntMod();	
		Vector<Subject> subject=cntMod.getSubjects(LangID);		
		
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
				mData+=item.getParentId();
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
	private String getContentMp3(int subjId,int index,int page)
	{
		//System.out.println("subject id:"+subjId);
		MOD  modDBI = DBIGateway.getAMDMod();		
		AMDMod cntMod = modDBI.getAMDCntMod();
		
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;
		
		Vector<Mod> mod=cntMod.getMods(subjId, LangID, fr,to);
		int count= cntMod.countModOfSubject(subjId);
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml count=\""+count+"\">";
		for(int i=0;i<mod.size();i++)
		{
			Mod item=mod.get(i);
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
	private String getListSubMod(int modId)
	{
		MOD  modDBI = DBIGateway.getAMDMod();		
		AMDMod cntMod = modDBI.getAMDCntMod();		
		Vector<Subject> v_s= cntMod.getSubjectsInMod(modId, LangID);
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<v_s.size();i++)
		{
			Subject item=v_s.get(i);
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
	private String getListSubNOTMod(int modId)
	{
		MOD  modDBI = DBIGateway.getAMDMod();		
		AMDMod cntMod = modDBI.getAMDCntMod();		
		Vector<Subject> v_1= cntMod.getSubjectsInMod(modId, LangID);
		Vector<Subject> v_2= cntMod.getSubjects(LangID);
		
		for(int i=0;i<v_2.size();i++)
		{
			int id=v_2.get(i).getId();
			for(int j=0;j<v_1.size();j++)
			{
				if(v_1.get(j).getId()==id)
				{
					v_2.remove(i);
					i--;
					break;			
				}
			}
		}	
		
		String mData="";
		mData+="<?xml version=\"1.0\"  standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<v_2.size();i++)
		{
			Subject item=v_2.get(i);
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
	private String listAddfail(Vector<String> list)
	{
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";			
		for(int i=0;i<list.size();i++)
		{
			mData+="<Item>";
			mData+="<name>";
			mData+=list.get(i);
			mData+="</name>";
			mData+="</Item>";
		}		
		mData+="</xml>";	
		return mData;	
	}
}

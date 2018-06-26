package ehotel.admin.Vod;

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
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;
import ehotel.inter.AMDMod;
import ehotel.inter.AMDVod;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;
import ehotel.render.MOD;
import ehotel.render.VOD;

public class serviceVod extends ServiceParent
{

	/**
	 * Constructor of the object.
	 */
	public serviceVod() {
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
			this.showJSPpage(request, response, "/vodMgn/service/VodSrvMain.jsp");
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
			int page=6;
			int subId=-1;
			int index=-1;
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
			System.out.println("Get list vod");
			response.setContentType("text/xml");
			
			String st=getListMove(subId, index, page);				
			out.print(st);
		
			break;
		}
		case 3:
		{
			System.out.println("get subject in Vod");
			response.setContentType("text/xml");
			int vodId=-1;
			if(request.getParameter("id")!=null)
			{
				vodId=Integer.parseInt(request.getParameter("id").toString().trim());
			}			
			
			String st=getsubjectNotInVod(vodId);			
			out.print(st);
			break;
		}
		case 4://
		{
			System.out.println("get subject not Vod");
			response.setContentType("text/xml");
			int vodId=-1;
			if(request.getParameter("id")!=null)
			{
				vodId=Integer.parseInt(request.getParameter("id").toString().trim());
			}						
			String st=getsubjectInVod(vodId);			
			out.print(st);
			break;
		}
		case 5://new released
		{
			int vodId=-1;
			if(request.getParameter("vodId")!=null)
			{
				vodId=Integer.parseInt(request.getParameter("vodId").toString().trim());
			}
			int subId=0;
			if(request.getParameter("subId")!=null)
			{
				subId=Integer.parseInt(request.getParameter("subId").toString().trim());
			}
			VOD  vodDBI = DBIGateway.getAMDVod();	
			AMDVod srvMod = vodDBI.getAMDSvcVod();
			srvMod.setNewReleased(vodId, subId);
			break;
		}
		case 6://status
		{
			int vodId=-1;
			if(request.getParameter("vodId")!=null)
			{
				vodId=Integer.parseInt(request.getParameter("vodId").toString().trim());
			}
			int Status=0;
			if(request.getParameter("Status")!=null)
			{
				Status=Integer.parseInt(request.getParameter("Status").toString().trim());
			}
			int subId=-1;
			if(request.getParameter("subId")!=null)
			{
				subId=Integer.parseInt(request.getParameter("subId").toString().trim());
			}
			VOD  vodDBI = DBIGateway.getAMDVod();	
			AMDVod srvMod = vodDBI.getAMDSvcVod();
			srvMod.setVisileStatus(vodId, subId);
			break;
		}
		case 7://change subject
		{
			System.out.println("change subject");
			int vodId=-1;
			if(request.getParameter("vodId")!=null)
			{
				vodId=Integer.parseInt(request.getParameter("vodId").toString().trim());
			}
		
			Vector<Integer> list=new Vector();
			int i=0;
			while(request.getParameter("subid"+i)!=null)
			{
				int subid= Integer.parseInt(request.getParameter("subid"+i).toString().trim());				
				list.add(subid);
				i++;				
			}
			
			String param="(";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1)+")";	
			
			VOD  vodDBI = DBIGateway.getAMDVod();	
			AMDVod srvMod = vodDBI.getAMDSvcVod();
			srvMod.changeSubjectOfSvcVod(vodId, param);
			break;
		}
		case 8://list vod in Subcontent not SubService
		{		
			int subIdCtn=-1;			
			if(request.getParameter(Def.SubId)!=null)
			{
				subIdCtn=Integer.parseInt(request.getParameter(Def.SubId).toString());
			}										
			System.out.println("Get list vod in content");
			response.setContentType("text/xml");
			String st=getListMovieCtn(subIdCtn);		
			out.print(st);
			break;
			
		}
		case 9://add contènt srvice
		{
			int subId=-1;			
			if(request.getParameter(Def.SubId)!=null)
			{
				subId=Integer.parseInt(request.getParameter(Def.SubId).toString());
			}			
			int vodId=-1;
			if(request.getParameter("vodId")!=null)
			{
				vodId=Integer.parseInt(request.getParameter("vodId").toString().trim());
			}
			VOD  VodDBI = DBIGateway.getAMDVod();
			AMDVod CtnMod = VodDBI.getAMDCntVod();
			boolean b= CtnMod.addVod(subId, vodId);
			System.out.println(b);
			if(b)
			{
				out.write("1");
			}else
			{
				out.write("0");
			}
			break;
		}
		case 10://remove 
		{
			
			
			ILOGIN iuser = DBIGateway.getILogin();	
		    String ipAdress=request.getRemoteAddr();
			if(iuser.isAdmin(ipAdress)||iuser.checkRoleUser(ipAdress,Def._roleVIDEO))
			 {
			    				    	
					int subId=-1;			
					if(request.getParameter(Def.SubId)!=null)
					{
						subId=Integer.parseInt(request.getParameter(Def.SubId).toString());
					}			
					int vodId=-1;
					if(request.getParameter("vodId")!=null)
					{
						vodId=Integer.parseInt(request.getParameter("vodId").toString().trim());
					}
					VOD  VodDBI = DBIGateway.getAMDVod();
					AMDVod CtnMod = VodDBI.getAMDCntVod();
					boolean b= CtnMod.removeVod(subId, String.valueOf(vodId));
			    }else
			    {
			    	out.print(-1);
			    
			    }
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
				AMDVod srvMod = vodDBI.getAMDSvcVod();					
				//subjectName=UtilString.uni2ent2ndTry(subjectName);			
				int id= srvMod.addSubject(subjectName,image,-1);	
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
				if(request.getParameter("subid")!=null)
				{			
					
					subjId=Integer.parseInt(request.getParameter("subid").toString());
					subjectName=request.getParameter("name").toString();				
					VOD  vodDBI = DBIGateway.getAMDVod();	
					AMDVod srvMod = vodDBI.getAMDSvcVod();		
					//subjectName=UtilString.uni2ent2ndTry(subjectName);
					int t= srvMod.editSubject(subjId,subjectName,image ,LangID);					
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
					}
				}
			}
			break;
		}
		case 3:
		{
			System.out.println("delete subject vod id:");
			
			int subjId=-1;
			if(request.getParameter("subid")!=null)
			{
				subjId=Integer.parseInt(request.getParameter("subid").toString());
				VOD  vodDBI = DBIGateway.getAMDVod();	
				AMDVod srvMod = vodDBI.getAMDSvcVod();
				boolean b= srvMod.removeSubject(subjId);
				System.out.println("delete:"+b);
			}
			break;
		}
		case 4:
		{
			System.out.println("Update VOD");			
			ILOGIN iuser = DBIGateway.getILogin();	
		    String ipAdress=request.getRemoteAddr();		    
		    if(iuser.isAdmin(ipAdress)||iuser.checkRoleUser(ipAdress,Def._roleVIDEO))
		    {
				int id=-1;
				String name="";
				String actors="";
				String director="";
				String desc="";
				String image="";
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
				VOD  VodDBI = DBIGateway.getAMDVod();
				AMDVod SrvMod = VodDBI.getAMDSvcVod();
				desc=UtilString.uni2ent2ndTry(desc);
				Vod vod=new Vod();
				vod.setId(id);
				vod.setTitle(name);
				vod.setActors(actors);
				vod.setDirector(director);
				vod.setPlot(desc);			
				vod.setPoster(image);			
				boolean b= SrvMod.updateVod(vod, LangID);
				if(b)
				{
					
					ConfigLoader loader=new ConfigLoader();
					Config config=loader.getConfig();
					ManagerFile file=new ManagerFile();					
					String path1=request.getRealPath(config._temp)+"/"+image;
					String path2=request.getRealPath(config._pathImage)+"/"+image;
					file.copy(path1, path2);
					file.deletefile(path1);
				}
		    }else
		    {
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getsubMod()
	{
		
		VOD  VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDSvcVod();
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
				mData+="-1";
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
	private String getListMove(int subId,int index,int page)
	{
		VOD  VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDSvcVod();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;
		
		Vector<Vod> vod=new Vector();
		
		if(index==-1)
		{
			vod=SrvMod.getVods(subId, LangID, -1, -1);
		}else
		{			
			vod=SrvMod.getVods(subId, LangID, fr, to);
		}	
		int count=SrvMod.countVodOfSubject(subId);
	
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml count='"+count+"'>";	
		System.out.println("length:"+vod.size());
		for(int i=0;i<vod.size();i++)
		{
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+vod.get(i).getTitle()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=vod.get(i).getId();
			mData+="</id>";
			mData+="<Actors>";
			mData+="<![CDATA["+vod.get(i).getActors()+"]]>";				
			mData+="</Actors>";
			mData+="<Director>";
						
			mData+="<![CDATA["+vod.get(i).getDirector()+"]]>";	
						
			mData+="</Director>";
			mData+="<Duration>";				
			mData+="00";				
			mData+="</Duration>";
			mData+="<New>";
			mData+=vod.get(i).getReleased();
			mData+="</New>";
			mData+="<Desc>";
			
			mData+="<![CDATA["+(vod.get(i).getPlot())+"]]>";
		
			mData+="</Desc>";
			mData+="<Image>";
			mData+=	vod.get(i).getPoster();
			mData+="</Image>";
			mData+="<Status>";
			mData+=vod.get(i).getStatus();
			mData+="</Status>";
			mData+="</Item>";
		}			
		mData+="</xml>";	
		
		return mData;
	}
	private String getsubjectInVod(int vodId)
	{
		VOD  VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDSvcVod();
		Vector<Subject> subject1=SrvMod.getSubjects(LangID);		
		
		Vector<Subject> subject2=SrvMod.getSubjectsInVod(vodId, LangID);
		System.out.println("size:"+subject2.size());
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		
		for(int i=0;i<subject2.size();i++)
		{
			Subject item=subject2.get(i);			
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
				mData+="-1";
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
	private String getsubjectNotInVod(int vodId)
	{
		VOD  VodDBI = DBIGateway.getAMDVod();
		AMDVod SrvMod = VodDBI.getAMDSvcVod();
		Vector<Subject> subject1=SrvMod.getSubjects(LangID);		
		
		Vector<Subject> subject2=SrvMod.getSubjectsInVod(vodId, LangID);
		for(int i=0;i<subject2.size();i++)
		{
			for(int j=0;j<subject1.size();j++)
			{
				if(subject1.get(j).getId()==subject2.get(i).getId())
				{
					subject1.remove(j);
					j--;
					break;
				}
				
			}
		}
		System.out.println("size:"+subject1.size());
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		
		for(int i=0;i<subject1.size();i++)
		{
			Subject item=subject1.get(i);			
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
				mData+="-1";
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
	private String getListMovieCtn(int subIdCtn)
	{
		VOD  VodDBI = DBIGateway.getAMDVod();
		AMDVod CtnMod = VodDBI.getAMDCntVod();
		Vector<Vod> vod=CtnMod.getVods(subIdCtn, LangID, -1, -1);	
			
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		
		for(int i=0;i<vod.size();i++)
		{
			String actor="unknow";
			if(vod.get(i).getActors()!=null)
			{
				actor=vod.get(i).getActors();
			}
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+vod.get(i).getTitle()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=vod.get(i).getId();
			mData+="</id>";
			mData+="<Actors>";
			
			mData+="<![CDATA["+actor+"]]>";				
			mData+="</Actors>";
			mData+="<Director>";						
			mData+="<![CDATA["+vod.get(i).getDirector()+"]]>";							
			mData+="</Director>";
			mData+="<Duration>";				
			mData+="00";				
			mData+="</Duration>";
			mData+="<New>";
			mData+=vod.get(i).getReleased();
			mData+="</New>";
			mData+="<Desc>";
			
			mData+="<![CDATA["+(vod.get(i).getPlot())+"]]>";	
			mData+="</Desc>";
			mData+="<Image>";
			mData+=	vod.get(i).getPoster();
			mData+="</Image>";
			mData+="<Status>";
			mData+=vod.get(i).getStatus();
			mData+="</Status>";
			mData+="</Item>";
		}			
		mData+="</xml>";	
		
		return mData;
	}
}

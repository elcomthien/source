package ehotel.admin.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.vod.Subject;
import ehotel.impl.AMDSvcLiveTVImp;
import ehotel.inter.AMDLiveTV;

public class ServiceLiveTVMain  extends ServiceParent{
	/**
	 * Constructor of the object.
	 */
	private AMDLiveTV livetv=new AMDSvcLiveTVImp();
	private int nxt=0;
	private int pr =0;
	public ServiceLiveTVMain() 
	{
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
		switch (cmd) {
		case -1:
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
			//request.setAttribute("loadMenuAD", getsubMod());;
			request.setAttribute("fileJSP", "../pmsMng/Service/srcLiveTVMain.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case  1:
			response.setContentType("text/xml");
			String st1= getSubjectMenu();
			out.print(st1);
			break;
		case 2:
			boolean p =deleteSubject(request, response);
			if(!p){
				out.print("failed");
			}
			break;
		case 3:
			int index=0;
			int page=6;
			int channelId=0;
			if(request.getParameter("channelId")!=null)
			{
				channelId=Integer.parseInt(request.getParameter("channelId").toString());
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
			String st=getChannelInfo(channelId,index,page);	
			out.print(st);
			break;
		case 4:
			boolean changstatus= ChangeStatus(request,response);
			if(!changstatus){
				out.print("failed");
			}
			break;
		case 5:
			boolean plag1 =deleteChannel(request, response);
			if(!plag1){
				out.print("failed");
			}
			break;
		case 6:
			boolean plag2 =deleteLiveTV(request, response);
			if(!plag2){
				out.print("failed");
			}
			break;
		case 7:
			int subjectId=0;
			if(request.getParameter("subjectId")!=null)
			{
				subjectId=Integer.parseInt(request.getParameter("subjectId").toString().trim());
			}		
			String stlive=getChannelContentOutSujectSrc(subjectId);	
			response.setContentType("text/xml");
			out.print(stlive);
			break;
		case 8:
			int subjectId1=0;
			if(request.getParameter("subjectId")!=null)
			{
				subjectId1=Integer.parseInt(request.getParameter("subjectId").toString().trim());
			}		
			String stchannel=getChannelInfoNotService(subjectId1);	
			response.setContentType("text/xml");
			out.print(stchannel);
			break;
		case 9:
			boolean kq =addLiveTVChannel(request, response);
			if(!kq){
				out.print("failed");
			}
			break;
		case 10:
			boolean rkq =removeLiveTVChannel(request, response);
			if(!rkq){
				out.print("failed");
			}
			break;
		case 11:
			int index1=0;
			int page1=6;
			int subjId1=0;
			String nameSearch="";
			if(request.getParameter("subjId")!=null)
			{
				subjId1=Integer.parseInt(request.getParameter("subjId").toString());
			}
			if(request.getParameter("pageIndex")!=null)
			{
				index1=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page1=Integer.parseInt(request.getParameter("page").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				nameSearch=request.getParameter("nameSearch").toString().trim();
			}
			response.setContentType("text/xml");
			nameSearch = new String(nameSearch.getBytes("8859_1"),"UTF8");
			String search1=searchChannelLive(subjId1,nameSearch,index1,page1);	
			out.print(search1);
			break;
			
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
			int kq =addSubjectMenu(request, response);
			if(kq==-1){
				out.print("failed");
			}else if(kq==-2){
				out.print("f");
			}
			break;
		case 2:
			int plag =UpdateSubject(request, response);
			if(plag==-1){
				out.print("failed");
			}else if(plag==-2){
				out.print("f");
			}
			break;
		case 3:
			int p =EditChannel(request, response);
			if( p==-1){
				out.print("failed");
			}else if( p==-2){
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getSubjectMenu()
	{
		Vector<Subject> vGroup =livetv.getSubjects(LangID);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		if(vGroup.size()!=0){
			mData+="<xml menuIdGF=\""+vGroup.get(0).getId()+"\">";
		}else{
			mData+="<xml menuIdGF=\""+0+"\">";
		}
		for(int i=0;i<vGroup.size();i++)
		{	
			Subject item=vGroup.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>";
			mData+=item.getId();
			mData+="</id>";	
			mData+="<parent>";
			mData+=item.getParentId();
			mData+="</parent>\n";
			mData+="<urlIma>";
			if(null!=item.getUrlImage()){
				mData+=item.getUrlImage();
			}else{
				mData+="emp";
			}
			
			mData+="</urlIma>";
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
		
	} 
private int addSubjectMenu(HttpServletRequest request, HttpServletResponse response){
		
		Subject subject = new Subject();
		String  subjectName="";
		String ulrImag="";
		int parent=0;
		if(request.getParameter("parent")!=null)
		{
			parent=Integer.parseInt(request.getParameter("parent"));
		}
		if(request.getParameter("name")!=null)
		{
			subjectName=request.getParameter("name").toString().trim();
		}
		if(request.getParameter("image")!=null)
		{
			ulrImag=request.getParameter("image").toString().trim();
			
		}
		
		subject.setName(subjectName);
		subject.setUrlImage(ulrImag);
		int plag =livetv.addSubject(subject,parent);
		if(plag >0){
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			ManagerFile file=new ManagerFile();
			String path1=request.getRealPath(config._temp)+"/"+ulrImag;
			String path2=request.getRealPath(config._pathImage)+"/service/Subject/"+ulrImag;
			file.copy(path1, path2);
			file.deletefile(path1);
		}
		return plag;
	}
	private boolean deleteSubject(HttpServletRequest request, HttpServletResponse response){
		int subjId=0;
		if(request.getParameter("subjId")!=null)
		{
			subjId=Integer.parseInt(request.getParameter("subjId"));
		}
		boolean plag =livetv.removeSubject(subjId);
		return plag;
	}
	private int UpdateSubject(HttpServletRequest request, HttpServletResponse response){
		int  subid=0;
		String image="";
		String name="";
		if(request.getParameter("subid")!=null)
		{
			subid=Integer.parseInt(request.getParameter("subid"));
		}
		if(request.getParameter("image")!=null)
		{
			image=request.getParameter("image").toString().trim();
		}
		if(request.getParameter("name")!=null)
		{
			name=request.getParameter("name").toString().trim();
		}
		Subject subject = new Subject();
		subject.setId(subid);
		subject.setName(name);
		subject.setUrlImage(image);
		int plag =livetv.editSubject(subject,LangID);
		if(plag >0){
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			ManagerFile file=new ManagerFile();					
			String path1=request.getRealPath(config._temp)+"/"+image;
			String path2=request.getRealPath(config._pathImage)+"/service/Subject/"+image;
			
			file.copy(path1, path2);
			file.deletefile(path1);
		}
		return plag;
	}
	public String getChannelInfo(int menuid1,int index,int page){
		int c=0;
		int fr=index*page;
		fr+=1;
		c=fr;
		int to=(index+1)*page;
		Vector<LiveTV> vLiveTV =livetv.getChannels(menuid1,LangID, fr,to );
		String mData="";
		int count=livetv.countChannels(menuid1);
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\" >";
		if(count <c && count!=0){
			
			if(count%6==0){
				nxt=count-5;
				pr=count;
				c=pr;
				
			}
			vLiveTV =livetv.getChannels(menuid1,LangID, nxt,pr );
			c=nxt;
		}
		
		for(int i=0;i<vLiveTV.size();i++)
		{		
			LiveTV item=vLiveTV.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getChannelname()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getChannelid();
			mData+="</id>";	
			mData+="<stt>\n";
			mData+=c++;
			mData+="</stt>";	
			mData+="<ChannelCode>\n";
			mData+=item.getChannelcode();
			mData+="</ChannelCode>";
			mData+="<address>";
			if(null!=item.getPhysical_address()){
				mData+="<![CDATA["+item.getPhysical_address()+"]]>";
			}else{
				mData+=-1;
			}
		
			mData+="</address>";	
			mData+="<urlIma>\n";
			if(null!=item.getUrlImage()){
				mData+=item.getUrlImage();
			}else{
				mData+="emp".trim();
			}
			mData+="</urlIma>";
			mData+="<serviceName>\n";
			mData+=item.getServicename();
			mData+="</serviceName>";
			mData+="<Active>\n";
			mData+=item.getActive();
			mData+="</Active>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
	
		return mData;
	}
	private boolean ChangeStatus(HttpServletRequest request, HttpServletResponse response){
		int subjctId=0;
		int livetvId=0;
		if(request.getParameter("subjctId")!=null)
		{
			subjctId=Integer.parseInt(request.getParameter("subjctId"));
		}
		if(request.getParameter("livetvId")!=null)
		{
			livetvId=Integer.parseInt(request.getParameter("livetvId"));
		}
		boolean plag =livetv.changStatus( subjctId,  livetvId);
		return plag;
	}
	private boolean deleteChannel(HttpServletRequest request, HttpServletResponse response){
		int subjctId=0;
		String chuoi="";
		if(request.getParameter("subjctId")!=null)
		{
			subjctId=Integer.parseInt(request.getParameter("subjctId"));
		}
		if(request.getParameter("str")!=null)
		{
			chuoi=request.getParameter("str").toString().trim();
		}
		boolean plag =livetv.removeChannel(subjctId, chuoi);
		return plag;
	}
	private int EditChannel(HttpServletRequest request, HttpServletResponse response){
		int  channelId=0;
		String name="";
		String image="";
		if(request.getParameter("channelId")!=null)
		{
			channelId=Integer.parseInt(request.getParameter("channelId"));
		}
		if(request.getParameter("name")!=null)
		{
			name=request.getParameter("name").toString().trim();
		}
		if(request.getParameter("image")!=null)
		{
			image=request.getParameter("image").toString().trim();
		}
		LiveTV tv = new LiveTV();
		tv.setChannelid(channelId);
		tv.setServicename(name);
		tv.setUrlImage(image);
		int plag =livetv.updateChannel(tv,LangID);
			if(plag >0){
				ConfigLoader loader=new ConfigLoader();
				Config config=loader.getConfig();
				ManagerFile file=new ManagerFile();					
				String path1=request.getRealPath(config._temp)+"/"+image;
				String path2=request.getRealPath(config._pathImage)+"/service/LiveTV/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
		return plag;
	}
	private boolean deleteLiveTV(HttpServletRequest request, HttpServletResponse response){
		int subjId=0;
		String str="";
		if(request.getParameter("subjId")!=null)
		{
			subjId=Integer.parseInt(request.getParameter("subjId"));
		}
		if(request.getParameter("str")!=null)
		{
			str=request.getParameter("str").toString().trim();
		}
		String chuoi="("+str+")";
		boolean plag =livetv.removeChannel(subjId,chuoi);
		return plag;
	}
	private String getChannelContentOutSujectSrc(int subjectId)
	{
		
		Vector<LiveTV> vLiveTV =livetv.getChannelContentOutSuject(subjectId,LangID);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<vLiveTV.size();i++)
		{	
			LiveTV item=vLiveTV.get(i);
			mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+item.getChannelname()+"]]>";
				mData+="</name>";
				mData+="<id>";
				mData+=item.getChannelid();
				mData+="</id>";	
				mData+="<active>";
				mData+=item.getActive();
				mData+="</active>\n";
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
	} 
	public String getChannelInfoNotService(int subjectId){
		Vector<LiveTV> vLiveTV =livetv.getChannels(subjectId,LangID,-1,-1);
		int count=livetv.countChannels(subjectId);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml count=\""+count+"\" >";
		for(int i=0;i<vLiveTV.size();i++)
		{		
			LiveTV item=vLiveTV.get(i);
			mData+="<Item>";
			mData+="<id>\n";
			mData+=item.getChannelid();
			mData+="</id>";		
			mData+="<serviceName>\n";
			mData+=item.getServicename();
			mData+="</serviceName>";
			mData+="<active>\n";
			mData+=item.getActive();
			mData+="</active>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
	
		return mData;
	}
private boolean addLiveTVChannel(HttpServletRequest request, HttpServletResponse response){
		String  str="";
		int subjId=0;
		if(request.getParameter("subjId")!=null)
		{
			subjId=Integer.parseInt(request.getParameter("subjId"));
		}
		if(request.getParameter("str")!=null)
		{
			str=request.getParameter("str").toString().trim();
		}
		String chuoi ="("+str+")";
		boolean  plag =livetv.addLiveTV(subjId,chuoi);
		return plag;
	}
private boolean removeLiveTVChannel(HttpServletRequest request, HttpServletResponse response){
	String  str="";
	int subjId=0;
	if(request.getParameter("subjId")!=null)
	{
		subjId=Integer.parseInt(request.getParameter("subjId"));
	}
	if(request.getParameter("str")!=null)
	{
		str=request.getParameter("str").toString().trim();
	}
	String chuoi ="("+str+")";
	boolean  plag =livetv.removeChannel(subjId,chuoi);
	return plag;
}
public String searchChannelLive(int subjId,String liveName,int index,int page){
	int c=0;
	int fr=index*page;
	fr+=1;
	c=fr;
	int to=(index+1)*page;
	 Vector<LiveTV> vLiveTV =livetv.searchChannel(subjId,liveName, fr,to);
	String mData="";
	int count=livetv.countSearchChannel(subjId,liveName);
	mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
	mData+="<xml  count=\""+count+"\" >";
	if(count <c && count!=0){
		
		if(count%6==0){
			nxt=count-5;
			pr=count;
			c=pr;
			
		}
		vLiveTV =livetv.getChannels(subjId,LangID, nxt,pr );
		c=nxt;
	}
	
	for(int i=0;i<vLiveTV.size();i++)
	{		
		LiveTV item=vLiveTV.get(i);
		mData+="<Item>";
		mData+="<name>";
		mData+="<![CDATA["+item.getChannelname()+"]]>";
		mData+="</name>";
		mData+="<id>\n";
		mData+=item.getChannelid();
		mData+="</id>";	
		mData+="<stt>\n";
		mData+=c++;
		mData+="</stt>";	
		mData+="<ChannelCode>\n";
		mData+=item.getChannelcode();
		mData+="</ChannelCode>";
		mData+="<address>";
		mData+="<![CDATA["+item.getPhysical_address()+"]]>";
		mData+="</address>";	
		mData+="<urlIma>\n";
		if(null!=item.getUrlImage()){
			mData+=item.getUrlImage();
		}else{
			mData+="emp".trim();
		}
		mData+="</urlIma>";
		mData+="<serviceName>\n";
		mData+=item.getServicename();
		mData+="</serviceName>";
		mData+="<Active>\n";
		mData+=item.getActive();
		mData+="</Active>";	
		mData+="</Item>";			
	}
	mData+="</xml>";

	return mData;
}
}
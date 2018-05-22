package ehotel.admin.vodstb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;
import ehotel.domain.pms.eSTB;
import ehotel.domain.vod.Vod;
import ehotel.inter.IVideoSTB;
import ehotel.render.DBIGateway;
import ehotel.req.server.SynVodSTBReqInfo;

public class ServiceAddvodstb extends ServiceParent {

	/**
	 * Constructor of the object.
	 */
	public ServiceAddvodstb() {
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
			request.setAttribute("fileJSP", "../monitor/SynVod.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			
			break;
		case 1://get stb
		{
			response.setContentType("text/xml");
			String st=getAllSTB();			
			out.write(st);
			
			break;
		}
		case 2://danh sach phim co trong stb
		{
			response.setContentType("text/xml");
			String id="";
			int index=0;
			int page=6;				
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}	
			if(request.getParameter("id")!=null)
			{
				id=(request.getParameter("id").toString().trim());
			}			
			String st=getVODinSTB(id,index, page);
			out.write(st);
			break;
			
		}
		case 3:
		{
			response.setContentType("text/xml");
			String id="";
			int index=0;
			int page=6;				
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}	
			if(request.getParameter("id")!=null)
			{
				id=(request.getParameter("id").toString().trim());
			}			
			String st=getVODnotSTB(id);
			out.write(st);
			break;
		}
		case 4:
		{
			response.setContentType("text/xml");
			String id="";
			
			if(request.getParameter("id")!=null)
			{
				id=(request.getParameter("id").toString().trim());
			}
			
			String st=getprocess(id);
			out.write(st);
			break;
		}
		case 5:
		{
			System.out.println("add vod");
			String stb="";
			Vector<Integer> list=new Vector();
			int i=0;
			while(request.getParameter("id"+i)!=null)
			{
				int id=Integer.parseInt(request.getParameter("id"+i).toString().trim());
				list.add(id);
				i++;
			}
			if(request.getParameter("stb")!=null)
			{
				stb=request.getParameter("stb").toString().trim();
			}
			String param="";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1);
			param="("+param+")";
			System.out.println("List of movies add to STB:"+param);
			SynVodSTBReqInfo vod_stb=new SynVodSTBReqInfo(stb); 
			String st[]= vod_stb.synVodToSTB(param);
			
			break;
		}
		case 6:
		{
			System.out.println("add vod all");
			String stb="";
			Vector<Integer> list=new Vector();
			int i=0;
			while(request.getParameter("id"+i)!=null)
			{
				int id=Integer.parseInt(request.getParameter("id"+i).toString().trim());
				list.add(id);
				i++;
			}			
			String param="";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1);
			param="("+param+")";
			System.out.println("List of movies add to STB:"+param);
			SynVodSTBReqInfo vod_stb=new SynVodSTBReqInfo(); 
			String st[]= vod_stb.synVodToSTB(param);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getAllSTB()
	{
		IVideoSTB vdoSTB= DBIGateway.getAMDVideoSTB();
		
		Vector<eSTB> info=vdoSTB.getSTBList();
		String mData="";
		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<info.size();i++)
		{		
			eSTB item=info.get(i);					
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getId()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getKeyCode();
			mData+="</id>";
			mData+="<ip>";		
			
			if(item.getIP()==null)
			{			
				mData+="<![CDATA[]]>";		
			}else
			{
				mData+="<![CDATA["+item.getIP()+"]]>";		
			}
			mData+="</ip>";				
			mData+="<keycode>";			
			mData+="<![CDATA["+item.getKeyCode()+"]]>";			
			mData+="</keycode>";
			mData+="<date>";		
			if(item.getCreatedate()!=null)
			{
				mData+="<![CDATA["+item.getCreatedate()+"]]>";		
			}else
			{
				mData+="<![CDATA[]]>";	
			}
			mData+="</date>";
			mData+="<roomcode>";			
			if(item.getRoomCode()!=null)
			{
				mData+="<![CDATA["+item.getRoomCode()+"]]>";		
			}else
			{
				mData+="<![CDATA[]]>";	
			}		
			mData+="</roomcode>";
			mData+="<status>";			
			mData+="<![CDATA["+item.getStatus()+"]]>";			
			mData+="</status>";
			mData+="</Item>";	
		}
		mData+="</xml>";	
		return mData;
	}
	private String getVODinSTB(String id,int index,int page)
	{
		
		IVideoSTB vdoSTB= DBIGateway.getAMDVideoSTB();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		Vector<Vod> info=vdoSTB.getVodsOfStb(id, LangID, fr, to);
		String mData="";			
		int count=vdoSTB.countVodOnSTB(id);
		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml count='"+count+"'>";
		
		for(int i=0;i<info.size();i++)
		{		
			
			Vod item=info.get(i);				
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getTitle()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+= item.getId();
			mData+="</id>\n";	
			mData+="<image>\n";
			mData+=item.getPoster();
			mData+="</image>\n";
			mData+="<status>";			
			mData+="<![CDATA["+item.getStatus()+"]]>";			
			mData+="</status>";
			mData+="</Item>";	
		}
		mData+="</xml>";	
		return mData;
	}
	private String getVODnotSTB(String id)
	{
		IVideoSTB vdoSTB= DBIGateway.getAMDVideoSTB();			
		Vector<Vod> info=vdoSTB.getVodsOutStb(-1,id,LangID);
		String mData="";			
		int count=vdoSTB.countVodOnSTB(id);		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml count='"+count+"'>";		
		for(int i=0;i<info.size();i++)
		{		
			Vod item=info.get(i);					
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getTitle()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+= item.getId();
			mData+="</id>\n";	
			mData+="<image>\n";
			mData+=item.getPoster();
			mData+="</image>\n";
			mData+="<status>";			
			mData+="<![CDATA["+item.getStatus()+"]]>";			
			mData+="</status>";
			mData+="</Item>";	
		}
		mData+="</xml>";	
		return mData;
	}		
	private String getprocess(String id)
	{		
		IVideoSTB vdoSTB= DBIGateway.getAMDVideoSTB();			
		Vector<Vod> info=vdoSTB.getVods_UnSynCompleted(id,LangID);
		String mData="";			
		int count=vdoSTB.countVodOnSTB(id);		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml count='"+count+"'>";	
		for(int i=0;i<info.size();i++)
		{					
			Vod item=info.get(i);			
			SynVodSTBReqInfo vod_stb=new SynVodSTBReqInfo(id); 		
			String per=vod_stb.getPercentBySession(item.getSessionId());				
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getTitle()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+= item.getId();
			mData+="</id>\n";	
			mData+="<image>\n";
			mData+=item.getPoster();
			mData+="</image>\n";
			mData+="<status>";			
			mData+="<![CDATA["+per+"]]>";			
			mData+="</status>";
			mData+="<session>";			
			mData+="<![CDATA["+item.getSessionId()+"]]>";			
			mData+="</session>";
			mData+="</Item>";	
		}
		mData+="</xml>";	
		return mData;
	}
}

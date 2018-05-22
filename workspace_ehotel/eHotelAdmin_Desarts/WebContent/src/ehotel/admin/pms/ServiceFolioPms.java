package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.FolioStbPMS;
import ehotel.abs.pms.PromotionPMS;
import ehotel.abs.pms.TransportationPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;
import ehotel.domain.pms.eBillCharge;
import ehotel.domain.pms.eFolio;
import ehotel.domain.pms.eGuest;
import ehotel.domain.pms.eMessage;
import ehotel.domain.pms.ePromotion;
import ehotel.domain.pms.eSTB;
import ehotel.domain.pms.eUrlAirline;
import ehotel.inter.ILOGIN;
import ehotel.render.DBIGateway;

public class ServiceFolioPms extends ServiceParent {

	/**
	 * Constructor of the object.
	 */
	public ServiceFolioPms() {
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
			request.setAttribute("fileJSP", "../pmsMng/folio/folio.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");			
			break;
		}
		case 1://get list room
		{
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
			String key="";
			if(request.getParameter("key")!=null)
			{
				key=request.getParameter("key").toString();
			}
			response.setContentType("text/xml");
			String st= getRoomSeak(index,page,key);			
			out.print(st);
			break;
		}
		case 2://get list guest
		{
			System.out.println("Get list guest");
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}			
			response.setContentType("text/xml");
			String st= getGuest(id);			
			out.print(st);
			break;
		}
		case 3://get list messages
		{
			System.out.println("Get list guest");
			int id=-1;
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
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			
			response.setContentType("text/xml");
			String st= getMess(id, index, page);			
			out.print(st);
			break;
		}
		case 4://get list bill
		{
			System.out.println("Get list Bill");
			int id=-1;
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
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			
			response.setContentType("text/xml");
			String st= getBill(id, index, page);			
			out.print(st);
			break;
		}
		case 5://
		{
			System.out.println("Get list STB in room");
			int role=-1;
			ILOGIN iuser = DBIGateway.getILogin();	
		    String ipAdress=request.getRemoteAddr();
		    if(iuser.isAdmin(ipAdress)||iuser.checkRoleUser(ipAdress,"AUD$STB"))
		    {
		    	role=1;
		    }
			int id=-1;
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
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			
			response.setContentType("text/xml");
			String st= getSTB(id,role);			
			out.print(st);
			break;
		}
		case 6://show form setup
		{
			this.showJSPpage(request, response, "/pmsMng/folio/ListSTB.jsp");
			break;
		}
		case 7://
		{
			System.out.println("Get list STB not room");
			int id=-1;
			int index=0;
			int page=6;	
			String key="";
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}	
			if(request.getParameter("key")!=null)
			{
				key=request.getParameter("key").toString().trim();
			}
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}			
			response.setContentType("text/xml");
			String st= getAddSTB(id,index,page,key);			
			out.print(st);
			break;
		}
		case 8://
		{
			
			System.out.println("insert stb ti roomm");
			String stb="";
			if(request.getParameter("stb")!=null)
			{
				stb=request.getParameter("stb").toString().trim();
			}
			int roomid=-1;
			if(request.getParameter("room")!=null)
			{
				roomid=Integer.parseInt(request.getParameter("room").toString().trim());
			}
			System.out.println("smartcard:"+stb);
			System.out.println("Room:"+roomid);
			FolioStbPMS folio= new FolioStbPMS();	
			String t= folio.addSTBIntoFolio(stb,roomid);
			out.print(t);
			break;
		}
		case 9://
		{
			
			System.out.println("remove stb ti roomm");
			String stb="";
			if(request.getParameter("stb")!=null)
			{
				stb=request.getParameter("stb").toString().trim();
			}			
			FolioStbPMS folio= new FolioStbPMS();	
			boolean t= folio.removeSTBOutFolio(stb);
			out.print(t);
			break;
		}
		case 10:
		{		
			System.out.println("Get list STB");		
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
			response.setContentType("text/xml");
			String st= getAllSTB(index,page);			
			out.print(st);
			break;
		}
		case 11://get key seach
		{
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
			String key="";
			if(request.getParameter("key")!=null)
			{
				key=request.getParameter("key").toString();
			}
			response.setContentType("text/xml");
			String st= getRoomSeak(index,page,key);			
			out.print(st);
			break;
		}
		case 12://check out
		{
			
			System.out.println("Check out guest");
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			FolioStbPMS folio= new FolioStbPMS();
			boolean b= folio.checkOutGuest(id);
			
		}
		case 13://check out
		{
			System.out.println("Check out room");
			ILOGIN iuser = DBIGateway.getILogin();				
		    String ipAdress=request.getRemoteAddr();
		    boolean role=iuser.checkRoleUser(ipAdress,"AUD$FOLIO");
		    if(!role&&(!iuser.isAdmin(ipAdress)))
		    {		    	
		    	out.print(-1);
		    }else
		    {
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			FolioStbPMS folio= new FolioStbPMS();
			boolean b= folio.checkOutFolio(id);
		    }
			
		}
		case 14://check out
		{
			System.out.println("reset pin");
			ILOGIN iuser = DBIGateway.getILogin();				
		    String ipAdress=request.getRemoteAddr();
		    boolean role=iuser.checkRoleUser(ipAdress,"AUD$FOLIO");
		    System.out.println("role:"+role);
		    if(!role&&(!iuser.isAdmin(ipAdress)))
		    {		    	
		    	out.print(-1);
		    }else
		    {
		    	
				int id=-1;
				if(request.getParameter("id")!=null)
				{
					id=Integer.parseInt(request.getParameter("id").toString().trim());
				}
				FolioStbPMS folio= new FolioStbPMS();
				boolean b= folio.resetPincode(id, 123, "admin");
		    }
			
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
	
	private String getGuest(int roomId)
	{
		FolioStbPMS folio= new FolioStbPMS();
		
		Vector<eGuest> info=folio.getGuests(roomId);
		String mData="";
		int count=folio.countFolio();
		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eGuest item=info.get(i);	
			System.out.println("ID:"+item.getGuestId());
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getGuestId();
			mData+="</id>";
			mData+="<children>";			
			mData+="<![CDATA["+0+"]]>";			
			mData+="</children>";	
			mData+="<Arrival>";			
			mData+="<![CDATA["+item.getDepartmentDate()+"]]>";			
			mData+="</Arrival>";
			mData+="<out>";			
			mData+="<![CDATA["+item.getArrivalDate()+"]]>";			
			mData+="</out>";
			mData+="<status>";			
			mData+="<![CDATA["+item.getRoomshare()+"]]>";			
			mData+="</status>";				
			mData+="</Item>";			
		}
		mData+="</xml>";	
		return mData;
	}
	private String getMess(int roomId,int index,int page)
	{
		FolioStbPMS folio= new FolioStbPMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		Vector<eMessage> info=folio.getMessages(roomId, fr, to);
		String mData="";
		int count=folio.countFolio();
		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eMessage item=info.get(i);				
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getSubject()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";
			mData+="<content>";			
			mData+="<![CDATA["+item.getContent()+"]]>";			
			mData+="</content>";				
			mData+="<date>";	
			if(item.getEnterDate()!=null)
			{
				mData+="<![CDATA["+item.getEnterDate()+"]]>";	
			}else				
			mData+="<![CDATA[]]>";			
			mData+="</date>";		
			mData+="<from>";			
			mData+="<![CDATA["+item.getSender()+"]]>";			
			mData+="</from>";		
			mData+="</Item>";	
		}
		mData+="</xml>";	
		return mData;
	}
	private String getBill(int roomId,int index,int page)
	{
		FolioStbPMS folio= new FolioStbPMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;		
		Vector<eBillCharge> info=folio.getBillCharges(roomId, fr, to);		
		String mData="";
		
		int count=folio.countBill(roomId);
		System.out.println("chieu dai:"+count);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eBillCharge item=info.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getCode()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";
			mData+="<price>";	
			if(item.getPrice()!=null)
			{
				mData+="<![CDATA["+item.getPrice()+"]]>";	
			}else
			{
				mData+="<![CDATA[]]>";
			}
			mData+="</price>";				
			mData+="<quantity>";			
			mData+="<![CDATA["+item.getQuantity()+"]]>";			
			mData+="</quantity>";
			mData+="<date>";			
			mData+="<![CDATA["+item.getDateTime()+"]]>";			
			mData+="</date>";
			mData+="<amount>";			
			mData+="<![CDATA["+item.getAmount()+"]]>";			
			mData+="</amount>";
			mData+="<unit>";			
			mData+="<![CDATA["+item.getIUnit()+"]]>";			
			mData+="</unit>";
			mData+="</Item>";	
		}
		mData+="</xml>";	
		return mData;
	}
	private String getSTB(int roomId,int role)
	{
		
	    
		FolioStbPMS folio= new FolioStbPMS();		
		long start = System.currentTimeMillis();		
		Vector<eSTB> info=folio.getSTBListIn(roomId);
		String mData="";
		int count=folio.countFolio();	
		long end = System.currentTimeMillis();
		System.out.println("Time:"+(end-start));
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\" role=\""+role+"\">";
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
	private String getAddSTB(int roomId,int index,int page,String key)
	{
		FolioStbPMS folio= new FolioStbPMS();	
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		Vector<eSTB> info=folio.searchSTB(roomId,key, -1, -1);		
		String mData="";
		int count=folio.countSearchSTB(roomId, key);
		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
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
			mData+="<status>";			
			mData+="<![CDATA["+item.getStatus()+"]]>";			
			mData+="</status>";
			mData+="</Item>";	
		}
		mData+="</xml>";	
		return mData;
	}
	private String getAllSTB(int index,int page)
	{
		FolioStbPMS folio= new FolioStbPMS();	
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		Vector<eSTB> info=folio.getSTBListOut(-1, -1, -1);
		String mData="";
		int count=folio.countSTB(-1);
		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
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
	private String getRoomSeak(int index,int page,String key)
	{
		FolioStbPMS folio= new FolioStbPMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		long start = System.currentTimeMillis();			
		Vector<eFolio> info=folio.searchFolio(key, fr, to);
		long end = System.currentTimeMillis();	
		System.out.println("time:"+(end-start));
		String mData="";
		
		int count=folio.countSearchFolio(key);	
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eFolio item=info.get(i);	
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getFolioCode()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getFolioCode();
			mData+="</id>";
			mData+="<key>";			
			mData+="<![CDATA["+item.getFolioNum()+"]]>";			
			mData+="</key>";	
			mData+="<type>";			
			mData+="<![CDATA["+item.getFolioType()+"]]>";			
			mData+="</type>";
			mData+="<status>";			
			mData+="<![CDATA["+item.getStatus()+"]]>";			
			mData+="</status>";				
			mData+="</Item>";			
		}
		mData+="</xml>";
	
		return mData;
	}
	
}

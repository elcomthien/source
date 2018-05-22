package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.AdvertisePMS;
import ehotel.abs.pms.ExchangeRatePMS;
import ehotel.abs.pms.HotelInfoPMS;
import ehotel.abs.pms.TransportationPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.pms.eAdvertise;
import ehotel.domain.pms.eExchangeRate;
import ehotel.domain.pms.eMenu;
import ehotel.domain.pms.eUrlAirline;

public class ServiceTranport extends ServiceParent
{

	/**
	 * Constructor of the object.
	 */
	public ServiceTranport() {
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
			request.setAttribute("fileJSP", "../pmsMng/transport/Transport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1:
		{
			System.out.println("Get subject transport");
			response.setContentType("text/xml");
			String st=getsub();				
			out.print(st);
			break;
		}
		case 2://get content  1
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
			int menuid=-1;
			if(request.getParameter(Def.MenuId)!=null)
			{
				menuid=Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			response.setContentType("text/xml");
			String st= getContent1(menuid,index,page);			
			out.print(st);
			
			break;
		}
		case 3://show form detail url
		{
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			TransportationPMS tran=new TransportationPMS();
			if(id!=-1)
			{
				eUrlAirline item= tran.geteUrlAirlineInfo(id);
				request.setAttribute("Item", item);
			}
			this.showJSPpage(request, response, "/pmsMng/transport/detailURL.jsp");			
			break;
		}
		case 4://detailGround.jsp
		{
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			TransportationPMS tran=new TransportationPMS();
			if(id!=-1)
			{
				eMenu item= tran.getItemMenuInfo(id, LangID);
				request.setAttribute("Item", item);
			}			
			this.showJSPpage(request, response, "/pmsMng/transport/detailGround.jsp");			
			break;
		}
		case 5://get content 
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
			int menuid=-1;
			if(request.getParameter(Def.MenuId)!=null)
			{
				menuid=Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			response.setContentType("text/xml");
			String st= getContent2(menuid,index,page);			
			out.print(st);
			break;
		}
		case 6://delete ground
		{
			System.out.println("Delete group");
			
			int id=-1;
			int i=0;
			Vector<Integer> list=new Vector();
			while(request.getParameter("id"+i)!=null)
			{
				id=Integer.parseInt(request.getParameter("id"+i).toString().trim());
				list.add(id);
				i++;
			}
			
			TransportationPMS tran=new TransportationPMS();
			for(i=0;i<list.size();i++)
			{
				tran.removeItemMenu(list.get(i));
			}
			
			break;
		}
		case 7:			
		{			
			System.out.println("Delete URL");			
			int id=-1;
			int i=0;
			Vector<Integer> list=new Vector();
			while(request.getParameter("id"+i)!=null)
			{
				id=Integer.parseInt(request.getParameter("id"+i).toString().trim());
				list.add(id);
				i++;
			}			
			TransportationPMS tran=new TransportationPMS();
			for(i=0;i<list.size();i++)
			{
				tran.removeUrlAirline(list.get(i));
			}
			
			break;
		}
		case 8:			
		{			
			System.out.println("Delete URL");			
			int id=-1;			
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
				
			}			
			TransportationPMS tran=new TransportationPMS();			
			tran.changeStaus(id);
			
			
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
		case 1://insert ground
		{
			System.out.println("Insert Ground");			
			String name="";
			String image="";
			String icon="";
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString().trim();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			if(request.getParameter("icon")!=null)
			{
				icon=request.getParameter("icon").toString().trim();
			}		
			
			TransportationPMS transport=new TransportationPMS();
			eMenu menu=new eMenu();
			menu.setMenuName(name);
			menu.setUrlBg(icon);
			menu.setUrlImage(image);
			int t= transport.addItemMenu(id, menu);
			if(t>0)
			{
				ConfigLoader loader=new ConfigLoader();
				Config config=loader.getConfig();
				ManagerFile file=new ManagerFile();
				String path1=request.getRealPath(config._temp)+"/"+image;
				String path2=request.getRealPath(config._pathImage)+"/Hotel/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);
				path1=request.getRealPath(config._temp)+"/"+icon;
				path2=request.getRealPath(config._pathImage)+"/Hotel/"+icon;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 2://
		{
			System.out.println("edit Ground");			
			String name="";
			String image="";
			String icon="";
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString().trim();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			if(request.getParameter("icon")!=null)
			{
				icon=request.getParameter("icon").toString().trim();
			}		
			System.out.println("icon:"+icon);
			TransportationPMS transport=new TransportationPMS();
			eMenu menu=new eMenu();
			menu.setMenuName(name);
			menu.setUrlBg(icon);
			menu.setUrlImage(image);
			menu.setMenuId(id);			
			boolean t= transport.editItemMenu(menu, LangID);
			if(t)
			{
				ConfigLoader loader=new ConfigLoader();
				Config config=loader.getConfig();
				ManagerFile file=new ManagerFile();
				String path1=request.getRealPath(config._temp)+"/"+image;
				String path2=request.getRealPath(config._pathImage)+"/Hotel/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);
				path1=request.getRealPath(config._temp)+"/"+icon;
				path2=request.getRealPath(config._pathImage)+"/Hotel/"+icon;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 3://insert url
		{
			System.out.println("Insert URL");
			String name="";
			String url="";
			String image="";
			
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString().trim();
			}
			if(request.getParameter("url")!=null)
			{
				url=request.getParameter("url").toString().trim();
			}
			
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			TransportationPMS transport=new TransportationPMS();		
			eUrlAirline item=new eUrlAirline();
			item.setName(name);
			item.setUrlLink(url);
			item.setUrlImage(image);
			int t= transport.addUrlAirline(item);
			if(t>0)
			{
				ConfigLoader loader=new ConfigLoader();
				Config config=loader.getConfig();
				ManagerFile file=new ManagerFile();
				String path1=request.getRealPath(config._temp)+"/"+image;
				String path2=request.getRealPath(config._pathImage)+"/Hotel/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 4:
		{
			System.out.println("update URL");
			String name="";
			String url="";
			String image="";
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString().trim();
			}
			if(request.getParameter("url")!=null)
			{
				url=request.getParameter("url").toString().trim();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			TransportationPMS transport=new TransportationPMS();		
			eUrlAirline item=new eUrlAirline();
			item.setName(name);
			item.setUrlLink(url);
			item.setUrlImage(image);
			item.setId(id);
			boolean t= transport.editUrlAirline(item);
			if(t)
			{
				ConfigLoader loader=new ConfigLoader();
				Config config=loader.getConfig();
				ManagerFile file=new ManagerFile();
				String path1=request.getRealPath(config._temp)+"/"+image;
				String path2=request.getRealPath(config._pathImage)+"/Hotel/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);
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
	private String getsub()
	{
		
		TransportationPMS transport = new TransportationPMS();	
		
		Vector<eMenu> subject=transport.getMenus(LangID);
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";		
		for(int i=0;i<subject.size();i++)
		{			
			eMenu item=subject.get(i);			
			
			mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+item.getMenuName()+"]]>";
				mData+="</name>";
				mData+="<id>\n";
				mData+=item.getMenuId();
				mData+="</id>\n";
				mData+="<image>\n";
				mData+=item.getUrlImage();
				mData+="</image>";
				mData+="<imagebg>\n";
				mData+=item.getUrlBg();
				mData+="</imagebg>";
				mData+="<parent>";
				mData+=-1;
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	}
	private String getContent1(int menuId,int index,int page)
	{
		TransportationPMS  ehotel = new TransportationPMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		
		Vector<eUrlAirline> info=ehotel.getUrlAirlines(fr, to);
	
		String mData="";
		int count=ehotel.countItem();
		System.out.println("count:"+count);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eUrlAirline item=info.get(i);
			
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";			
			mData+="<image>";			
			mData+="<![CDATA["+item.getUrlImage()+"]]>";			
			mData+="</image>";
			mData+="<url>";			
			mData+="<![CDATA["+item.getUrlLink()+"]]>";			
			mData+="</url>";
			mData+="<status>";			
			mData+="<![CDATA["+item.getInvisble()+"]]>";			
			mData+="</status>";
			mData+="</Item>";			
		}
		mData+="</xml>";
	
		return mData;
	}
	private String getContent2(int menuId,int index,int page)
	{
		TransportationPMS  ehotel = new TransportationPMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		
		Vector<eMenu> info=ehotel.getItemMenu(menuId,LangID,fr,to);
		String mData="";
		int count=ehotel.countItemMenu(menuId);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eMenu item=info.get(i);
			
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getMenuName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getMenuId();
			mData+="</id>";			
			mData+="<image>";			
			mData+="<![CDATA["+item.getUrlImage()+"]]>";			
			mData+="</image>";
			mData+="<icon>";			
			mData+="<![CDATA["+item.getUrlBg()+"]]>";			
			mData+="</icon>";
			mData+="</Item>";			
		}
		mData+="</xml>";
	
		return mData;
	}

}

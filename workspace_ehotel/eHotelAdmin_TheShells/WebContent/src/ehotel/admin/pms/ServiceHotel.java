package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.HotelInfoPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.UtilString;

import ehotel.domain.pms.eImage;
import ehotel.domain.pms.eMenu;

public class ServiceHotel extends ServiceParent
{

	/**
	 * Constructor of the object.
	 */
	public ServiceHotel() 
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
			request.setAttribute("fileJSP", "../pmsMng/hotel/pmsHotel.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1://list subject
		{
			System.out.println("Get subject Ctn");
			response.setContentType("text/xml");
			String st=getsub();				
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
			response.setContentType("text/xml");
			String st= getContentHotel(id,index,page);			
			out.print(st);
			
			break;
		}
		case 3://delete subject
		{
			System.out.println("DELETE SUBJECT PMS");
			HotelInfoPMS hotel = new HotelInfoPMS();
			int subid=-1;
			if(request.getParameter("SubId")!=null)
			{
				subid=Integer.parseInt(request.getParameter("SubId").toString());
			}
			boolean b= hotel.removeHotelMenu(subid);
			System.out.println("Delete :"+b);
			break;
		}
		case 4://show form detail
		{
			System.out.println("Show form detiL");
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString());
			}
			
			HotelInfoPMS hotel = new HotelInfoPMS();	
			eImage item=null;
			
			if(id!=-1)
			item=	hotel.getItemInfo(id, LangID);
			request.setAttribute("eImage", item);
			this.showJSPpage(request, response, "/pmsMng/hotel/detailHotel.jsp");
			break;
		}
		case 5://list subject left
		{
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			response.setContentType("text/xml");
			String str=getSuboutHotel(id);
			out.print(str);
			break;
		}
		case 6://list subject right
		{
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			
			response.setContentType("text/xml");
			String str=getSubinHotel(id);
			out.print(str);
			break;
		}
		case 7://change subject PMS
		{
			System.out.println("change subject pms");
			int Id=-1;
			if(request.getParameter("id")!=null)
			{
				Id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			
			Vector<Integer> list=new Vector();
			int i=0;
			while(request.getParameter("SubId"+i)!=null)
			{
				int subid= Integer.parseInt(request.getParameter("SubId"+i).toString().trim());				
				list.add(subid);
				i++;				
			}
			
			String param="(";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1)+")";	
			
			HotelInfoPMS hotel = new HotelInfoPMS();
			boolean b= hotel.changeSubjectOfItem(Id, param);
			System.out.println("chenge subject:"+b);
			break;
		}
		case 8://delete item hotel
		{
			Vector<Integer> list=new Vector();
			int i=0;
			int subId=-1;
			while(request.getParameter("id"+i)!=null)
			{
				int subid= Integer.parseInt(request.getParameter("id"+i).toString().trim());				
				list.add(subid);
				i++;				
			}
			if(request.getParameter("SubId")!=null)
			{
				subId=Integer.parseInt(request.getParameter("SubId").toString().trim());
			}
			
			HotelInfoPMS hotel = new HotelInfoPMS();
			String param="(";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1)+")";	
			System.out.println(param);
			hotel.removeItem(param);
			break;
		}
		case 9://change status
		{
			System.out.println("change status ");
			int id=-1;
			HotelInfoPMS hotel = new HotelInfoPMS();
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			hotel.changeStatus(id);		
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
		case 1://insert pms subject
		{
			System.out.println("inser subject PMS!");
			response.setContentType("text/xml");
			String subjectName="";
			String image="";
			String urlBg="";
			int parent=-1;
			if(request.getParameter("image1")!=null)
			{
				image=request.getParameter("image1").toString();
			}
			if(request.getParameter("image2")!=null)
			{
				urlBg=request.getParameter("image2").toString();
			}
			
			if(request.getParameter("name")!=null)
			{								
				subjectName=request.getParameter("name").toString();
				
				HotelInfoPMS hotel = new HotelInfoPMS();				
				//subjectName=UtilString.uni2ent2ndTry(subjectName);	
				eMenu menu=new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(image);
				menu.setUrlBg(urlBg);
				int id= hotel.addHotelMenu(menu);
				/**
				 * copy 
				 */				
				if(id>0)
				{
					ConfigLoader loader=new ConfigLoader();
					Config config=loader.getConfig();
					ManagerFile file=new ManagerFile();
					String path1=request.getRealPath(config._temp)+"/"+image;
					String path2=request.getRealPath(config._pathImage)+"/Hotel/"+image;
					file.copy(path1, path2);
					file.deletefile(path1);
					path1=request.getRealPath(config._temp)+"/"+urlBg;
					path2=request.getRealPath(config._pathImage)+"/Hotel/"+urlBg;
					file.copy(path1, path2);
					file.deletefile(path1);
				}	
				
				out.write(id);
			}			
			break;
		}
		case 2://update subject
		{
			System.out.println("UPDATE SUBJECT PMS");
			String subjectName="";
			String image="";
			String bgimage="";
			if(request.getParameter("image1")!=null)
			{
				image=request.getParameter("image1").toString();
			}
			if(request.getParameter("image2")!=null)
			{
				bgimage=request.getParameter("image2").toString();
			}
			int subjId=-1;			
			if(request.getParameter("name")!=null)
			{
				if(request.getParameter(Def.SubId)!=null)
				{			
					
					subjId=Integer.parseInt(request.getParameter(Def.SubId).toString());
					subjectName=request.getParameter("name").toString();				
					HotelInfoPMS hotel = new HotelInfoPMS();	
					//subjectName=UtilString.uni2ent2ndTry(subjectName);
					eMenu menu=new eMenu();
					menu.setMenuName(subjectName);
					menu.setMenuId(subjId);
					menu.setUrlImage(image);
					menu.setUrlBg(bgimage);
					boolean t= hotel.editHotelMenu(menu, LangID);					
					System.out.println("UPDATE SUBJECT PMS:"+t);
					if(t)
					{						
						ConfigLoader loader=new ConfigLoader();
						Config config=loader.getConfig();
						ManagerFile file=new ManagerFile();					
						String path1=request.getRealPath(config._temp)+"/"+image;
						String path2=request.getRealPath(config._pathImage)+"/Hotel/"+image;						
						file.copy(path1, path2);
						file.deletefile(path1);
						path1=request.getRealPath(config._temp)+"/"+bgimage;
						path2=request.getRealPath(config._pathImage)+"/Hotel/"+bgimage;
						file.copy(path1, path2);
						file.deletefile(path1);
					}
				}
			}
			break;
		}
		case 3://update eimage
		{
			System.out.println("Update eImage");
			int id=-1;
			String name="";
			String image="";
			String des="";
			int status=1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt( request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString().trim();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			if(request.getParameter("des")!=null)
			{
				des=request.getParameter("des").toString();
			}
			
			if(request.getParameter("status")!=null)
			{
				status=Integer.parseInt(request.getParameter("status").toString().trim());
			}
			
			HotelInfoPMS hotel = new HotelInfoPMS();
			eImage item=new eImage();
			item.setId(id);
			item.setInvisible(status);
			item.setUrlImage(image);
			item.setDef(des);
			item.setName(name);
			boolean b= hotel.editItem(item, LangID);
			if(b)
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
		case 4://
		{
			System.out.println("inesert eImage");
			int id=-1;
			String name="";
			String image="";
			String des="";
			int status=1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt( request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString().trim();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			if(request.getParameter("des")!=null)
			{
				des=request.getParameter("des").toString();
			}
			
			if(request.getParameter("status")!=null)
			{
				status=Integer.parseInt(request.getParameter("status").toString().trim());
			}
			
			HotelInfoPMS hotel = new HotelInfoPMS();
			eImage item=new eImage();
			item.setInvisible(status);
			item.setUrlImage(image);
			item.setDef(des);
			item.setName(name);
			int b= hotel.addItemHotel(id, name, des, image,"");
			if(b>0)
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
		
		HotelInfoPMS hotel = new HotelInfoPMS();		
		Vector<eMenu> subject=hotel.getMenus(LangID);			
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
	private String getContentHotel(int subjId,int index,int page)
	{
		HotelInfoPMS hotel = new HotelInfoPMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;		
		
		Vector<eImage> info=hotel.getItemsOfHotelInfo(subjId, LangID,fr,to);
		int count= hotel.countItem(subjId);				
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		mData+="<xml count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eImage item=info.get(i);	
			String t=item.getDef();
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";
			mData+="<Des>";		
			mData+="<![CDATA["+t+"]]>";			
			mData+="</Des>";
			mData+="<status>";			
			mData+="<![CDATA["+item.getInvisible()+"]]>";			
			mData+="</status>";			
			mData+="</Item>";			
		}
		mData+="</xml>";
	
		return mData;
	}
	private String getSubinHotel(int itemId)
	{
		HotelInfoPMS hotel = new HotelInfoPMS();
		
		Vector<eMenu> menu=hotel.getSubjectsInItem(itemId, LangID);		
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";		
		for(int i=0;i<menu.size();i++)
		{
			eMenu item=menu.get(i);			
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
				mData+="<parent>";
				mData+=-1;
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";
		return mData;
	}
	private String getSuboutHotel(int itemId)
	{
		
		HotelInfoPMS hotel = new HotelInfoPMS();		
		Vector<eMenu> menu=hotel.getSubjectsOutItem(itemId, LangID);
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";		
		for(int i=0;i<menu.size();i++)
		{
			eMenu item=menu.get(i);			
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
				mData+="<parent>";
				mData+=-1;
				mData+="</parent>";
			mData+="</Item>";
		}	
		mData+="</xml>";
		return mData;
	}

}

package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.abs.pms.LocalAttractionPMS;
import ehotel.abs.pms.HotelInfoPMS;
import ehotel.abs.pms.LocalAttractionPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.pms.eAttraction;

import ehotel.domain.pms.eMenu;


public class ServiceAttaction extends ServiceParent {

	/**
	 * Constructor of the object.
	 */	
	public ServiceAttaction() 
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
			request.setAttribute("fileJSP", "../pmsMng/hotel/pmsActiviti.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		}
		case 1://list subject activiti
		{
			System.out.println("Get subject Activiti");
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
			String st= getContent(id,index,page);			
			out.print(st);
			break;
		}
		case 3://delete subject
		{
			System.out.println("DELETE SUBJECT PMS");
			LocalAttractionPMS hotel = new LocalAttractionPMS();
			int subid=-1;
			if(request.getParameter("SubId")!=null)
			{
				subid=Integer.parseInt(request.getParameter("SubId").toString());
			}
			boolean b= hotel.removeAttractionMenu(subid);
			System.out.println("Delete :"+b);
			break;
		}
		case 4://show form detail
		{
			System.out.println("Show form detial Activiti");
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString());
			}
			LocalAttractionPMS hotel = new LocalAttractionPMS();	
			eAttraction item=null;			
			if(id!=-1)				
			item=	hotel.getItemInfo(id, LangID);
			request.setAttribute("eImage", item);
			this.showJSPpage(request, response, "/pmsMng/hotel/detailAttaction.jsp");	
			break;
		}
		case 5://show form change subject
		{
			System.out.println("show form change subject");	
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString());
			}
			LocalAttractionPMS hotel = new LocalAttractionPMS();	
			eAttraction item=null;			
			if(id!=-1)				
			item=	hotel.getItemInfo(id, LangID);
			request.setAttribute("Item", item);			
			this.showJSPpage(request, response, "/pmsMng/hotel/ActiChangeSub.jsp");	
			break;
		}
		case 6://list subject right
		{
			System.out.println("Change upsubject UPA");
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString());
			}
			int subid=-1;
			if(request.getParameter("SubId")!=null)
			{
				subid=Integer.parseInt(request.getParameter("SubId").toString());
			}			
			LocalAttractionPMS hotel = new LocalAttractionPMS();	
			hotel.changeSubjectOfItem(id, String.valueOf(subid));
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
		case 8://delete item att
		{
			System.out.println("sssssssssssssssssssssssssssssssssssssssssssssss:");
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
			LocalAttractionPMS hotel = new LocalAttractionPMS();
			String param="(";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1)+")";	
			hotel.removeItem(param);
			break;
		}
		case 9://change status
		{
			System.out.println("change status ");
			int id=-1;
			LocalAttractionPMS hotel = new LocalAttractionPMS();
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			hotel.changeStatus(id);			
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
			System.out.println("inser subject PMS Activiti!");
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
				
				LocalAttractionPMS hotel = new LocalAttractionPMS();				
				//subjectName=UtilString.uni2ent2ndTry(subjectName);	
				eMenu menu=new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(image);
				menu.setUrlBg(urlBg);
				int id= hotel.addAttractionMenu(menu);
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
					LocalAttractionPMS hotel = new LocalAttractionPMS();	
					//subjectName=UtilString.uni2ent2ndTry(subjectName);
					eMenu menu=new eMenu();
					menu.setMenuName(subjectName);
					menu.setMenuId(subjId);
					menu.setUrlImage(image);
					menu.setUrlBg(bgimage);
					boolean t= hotel.editAttractionMenu(menu, LangID);					
					System.out.println("UPDATE SUBJECT PMS ACTIVITI:"+t);
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
			String des="";
			int status=1;
			String address="";
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt( request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString().trim();
			}			
			if(request.getParameter("des")!=null)
			{
				des=request.getParameter("des").toString();
			}
			
			if(request.getParameter("status")!=null)
			{
				status=Integer.parseInt(request.getParameter("status").toString().trim());
			}
			if(request.getParameter("addres")!=null)
			{
				address=request.getParameter("addres").toString().trim();
			}
			LocalAttractionPMS hotel = new LocalAttractionPMS();
			eAttraction item=new eAttraction();
			item.setId(id);
			item.setInvisible(status);
			item.setDef(des);
			item.setName(name);
			item.setAddress(address);
			
			boolean b= hotel.editItem(item, LangID);						
			break;
		}
		case 4://INSERT ATTACTION
		{
			System.out.println("inesert eAttation");
			int id=-1;
			String name="";
			String des="";
			int status=1;
			String address="";
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt( request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString().trim();
			}
			if(request.getParameter("des")!=null)
			{
				des=request.getParameter("des").toString();
			}
			
			if(request.getParameter("status")!=null)
			{
				status=Integer.parseInt(request.getParameter("status").toString().trim());
			}
			if(request.getParameter("addres")!=null)
			{
				address=request.getParameter("addres").toString().trim();
			}
			LocalAttractionPMS hotel = new LocalAttractionPMS();
			eAttraction item=new eAttraction();
			item.setInvisible(status);
			item.setDef(des);
			item.setName(name);
			item.setAddress(address);
			int b= hotel.addItem(id, item);
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
		
		LocalAttractionPMS hotel  = new LocalAttractionPMS();	
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
	private String getContent(int subjId,int index,int page)
	{
		LocalAttractionPMS hotel = new LocalAttractionPMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;		
		
		Vector<eAttraction> info=hotel.getItems(subjId, LangID,fr,to);
		
		int count= hotel.countItem(subjId);	
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eAttraction item=info.get(i);	
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";
			mData+="<Des>";			
			mData+="<![CDATA["+item.getDef()+"]]>";			
			mData+="</Des>";
			mData+="<status>";			
			mData+="<![CDATA["+item.getInvisible()+"]]>";			
			mData+="</status>";			
			mData+="</Item>";			
		}
		mData+="</xml>";	
		return mData;
	}
	
	

}

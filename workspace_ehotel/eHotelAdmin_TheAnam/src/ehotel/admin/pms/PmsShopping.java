package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.abs.pms.DiningPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.UtilString;
import ehotel.domain.pms.eItem;
import ehotel.domain.pms.eMenu;

public class PmsShopping extends ServiceParent {
	public PmsShopping() {
		super();
	}
	public void destroy()
	{
		super.destroy();
	}
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
			request.setAttribute("fileJSP", "../pmsMng/Shopping/Shopping.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");	
			
			break;
		}
		case 1://get menu 14.03.13
		{
			System.out.println("Get subject Shopping");
			response.setContentType("text/xml");
			String st=getmenu();				
			out.print(st);
			break;
		}
		case 2://get item roomservice
		{
			System.out.println("Get item");
			response.setContentType("text/xml");
			int subId=-1;
			if(request.getParameter("SubId")!=null)
			{
				subId=Integer.parseInt(request.getParameter("SubId").toString().trim());
			}				
			String st=getItemRoomService(subId);
			out.print(st);
			break;
		}
		case 3://delete subject
		{
			System.out.println("Delete subject shopping");
			int id=-1;
			if(request.getParameter("SubId")!=null)
			{
				id=Integer.parseInt(request.getParameter("SubId").toString().trim());
			}
			DiningPMS shopping=new DiningPMS();
			shopping.removeDiningMenu(id);	
			break;
		}
		//add shopping item, hien thi window de nhap thong tin shopping item can add vao 14.03.13
		case 4:
		{
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			DiningPMS ding=new DiningPMS();
			eItem item=new eItem();
			item=ding.getRoomSvcItemInfo(id, LangID);
			request.setAttribute("Item", item);				
			this.showJSPpage(request, response, "/pmsMng/Shopping/detaiShopping.jsp");
			break;
		}
		case 5://delete item
		{
			System.out.println("Delete item shopping");
			Vector<Integer> list=new Vector();
			int i=0;
			while(request.getParameter("id"+i)!=null)
			{
				int subid= Integer.parseInt(request.getParameter("id"+i).toString().trim());				
				list.add(subid);
				i++;				
			}		
			DiningPMS ding = new DiningPMS();
			for(i=0;i<list.size();i++)
			{
				System.out.println("ID:"+list.get(i));
				ding.removeRoomSvcItem(list.get(i));
			}			
			break;
		}
		case 9://break;
		{
			System.out.println("Delete item shopping");
			Vector<Integer> list=new Vector();
			int i=0;
			while(request.getParameter("id"+i)!=null)
			{
				int subid= Integer.parseInt(request.getParameter("id"+i).toString().trim());				
				list.add(subid);
				i++;				
			}		
			DiningPMS ding = new DiningPMS();
			String param="(";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1)+")";	
			ding.removeRestaurantItem(param);
			break;
		}
		case 10:
		{
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			System.out.println("changeItemStatus shopping itemid=" + id);
			DiningPMS ding=new DiningPMS();
			ding.changeItemStatus(id);
			break;
		}
		default:break;
		}
	}
	/**
	 * The doPost method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to post.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd=-1;
		ConfigLoader loader=new ConfigLoader();
		Config config=loader.getConfig();
		if(request.getParameter("CMD")!=null)
		{
			try{
				cmd=Integer.parseInt(request.getParameter("CMD").toString());
			}catch (Exception e) {
			}		
		}
		switch (cmd) {
		case 1://insert new subject
		{
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
			if(request.getParameter("id")!=null)
			{
				parent=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{								
				subjectName=request.getParameter("name").toString();
				DiningPMS dinning = new DiningPMS();				
				eMenu menu=new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(config._dining +"/"+image);
				menu.setUrlBg(config._dining +"/"+urlBg);
				//thay doi
				menu.setParentId(22);
				//ket thuc thay doi
				System.out.println("insert new subject shopping: parent=" + parent);
				int id= dinning.addDiningMenu(parent, menu);
				if(id>0)
				{
					ManagerFile file=new ManagerFile();
					String path1=config._temp+"/"+image;
					String path2=config._pathImage + config._dining +"/"+image;
					file.copy(path1, path2);
					file.deletefile(path1);
					path1=config._temp+"/"+urlBg;
					path2=config._pathImage+config._dining +"/"+urlBg;
					file.copy(path1, path2);
					file.deletefile(path1);
				}					
				out.write(id);
			}			
			break;
		}
		case 2://update  
		{
			System.out.println("Update subject shopping");
			response.setContentType("text/xml");
			String subjectName="";
			String image="";
			String urlBg="";
			int id=-1;
			if(request.getParameter("image1")!=null)
			{
				image=request.getParameter("image1").toString();
			}
			if(request.getParameter("image2")!=null)
			{
				urlBg=request.getParameter("image2").toString();
			}
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{								
				subjectName=request.getParameter("name").toString();
				DiningPMS dinning = new DiningPMS();				
				eMenu menu=new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(config._dining +"/"+image);
				menu.setUrlBg(config._dining +"/"+urlBg);
				menu.setMenuId(id);				
				boolean t= dinning.editDiningMenu(menu, LangID);
				if(t)
				{
					ManagerFile file=new ManagerFile();
					String path1=config._temp+"/"+image;
					String path2=config._pathImage+config._dining +"/"+image;
					file.copy(path1, path2);
					file.deletefile(path1);
					path1=config._temp+"/"+urlBg;
					path2=config._pathImage+config._dining +"/"+urlBg;
					file.copy(path1, path2);
					file.deletefile(path1);
				}				
			}			
			break;
		}
		case 3://insert item shopping
		{
			System.out.println("Insert item shopping");
			String name="";
			String currency="";
			String currency_small="";
			String price_large="";
			String unit="";
			int subid=-1;
			String image="";
			String def="";
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString();
			}
			if(request.getParameter("price")!=null)
			{
				currency=request.getParameter("price").toString().trim();
			}
			if(request.getParameter("price_small")!=null)
			{
				currency_small=request.getParameter("price_small").toString().trim();
			}
			if(request.getParameter("price_large")!=null)
			{
				price_large=request.getParameter("price_large").toString().trim();
			}
			if(request.getParameter("unit")!=null)
			{
				unit=request.getParameter("unit").toString();
			}
			if(request.getParameter("subid")!=null)
			{
				subid=Integer.parseInt(request.getParameter("subid").toString().trim());
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			if(request.getParameter("des")!=null)
			{
				def=request.getParameter("des").toString().trim();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			eItem item=new eItem();
			item.setName(name);
			item.setCurrency(currency);
			item.setCurrency_large(price_large);
			item.setCurrency_small(currency_small);
			item.setIUnit(unit);	
			item.setUrlImage(config._dining +"/"+image);
			item.setDef(def);
			System.out.println("insert def=" + def);
			DiningPMS dinning = new DiningPMS();
			int t= dinning.addRoomSvcItem(item,subid);
			if(t>0)
			{
				ManagerFile file=new ManagerFile();
				String path1=config._temp+"/"+image;
				String path2=config._pathImage+config._dining +"/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 4://
		{
			System.out.println("update item shopping");
			String name="";
			String currency="";
			String currency_small="";
			String price_large="";
			String unit="";
			String image="";
			String def="";
			int id=-1;
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString();
			}
			if(request.getParameter("price")!=null)
			{
				currency=request.getParameter("price").toString().trim();
			}
			if(request.getParameter("price_small")!=null)
			{
				currency_small=request.getParameter("price_small").toString().trim();
			}
			if(request.getParameter("price_large")!=null)
			{
				price_large=request.getParameter("price_large").toString().trim();
			}
			if(request.getParameter("unit")!=null)
			{
				unit=request.getParameter("unit").toString();
			}			
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			if(request.getParameter("des")!=null)
			{
				def=request.getParameter("des").toString().trim();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			System.out.println("update def=" + def);
			eItem item=new eItem();
			item.setName(name);
			item.setCurrency(currency);
			item.setCurrency_large(price_large);
			item.setCurrency_small(currency_small);
			item.setIUnit(unit);	
			item.setUrlImage(config._dining +"/"+image);
			item.setDef(def);
			item.setICode(id);
			DiningPMS dinning = new DiningPMS();
			boolean t= dinning.editRoomSvcItem(item, LangID);
			if(t)
			{
				ManagerFile file=new ManagerFile();
				String path1=config._temp+"/"+image;
				String path2=config._pathImage+config._dining +"/"+image;
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	}
	
	//sua doi menu cho nay chi co 1 thang chinh la SHOPPING 13.03.13
	private String getmenu()
	{
		String mData="";		
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		//mData+=getsubmenu1(22);
		if (LangID == 1) {
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA[Mua sắm]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=22;
			mData+="</id>\n";
			mData+="<image>\n";
			mData+="Local/Shopping.png";
			mData+="</image>";
			mData+="<imagebg>\n";
			mData+="Local/bg/Shopping_bg.png";
			mData+="</imagebg>";
			mData+="<parent>";
			mData+=-1;
			mData+="</parent>";
			mData+="<type>";
			mData+=1;
			mData+="</type>";
			mData+="<level>";
			mData+=0;
			mData+="</level>";
			mData+="</Item>";				
			mData+=getsubmenu1(22);
		}
		else {
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA[Shopping]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=22;
			mData+="</id>\n";
			mData+="<image>\n";
			mData+="Local/Shopping.png";
			mData+="</image>";
			mData+="<imagebg>\n";
			mData+="Local/bg/Shopping_bg.png";
			mData+="</imagebg>";
			mData+="<parent>";
			mData+=-1;
			mData+="</parent>";
			mData+="<type>";
			mData+=1;
			mData+="</type>";
			mData+="<level>";
			mData+=0;
			mData+="</level>";
			mData+="</Item>";				
			mData+=getsubmenu1(22);
		}
		mData+="</xml>";		
		return mData;
	}
	
	//sua doi cho nay. menu nay luon co cha la SHOPPING, ko co cap thu 3
	private String getsubmenu1(int menuid)
	{
		DiningPMS hotel = new DiningPMS();	
		String mData="";
		Vector<eMenu> menu= hotel.getSubMenus(menuid, LangID);
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
			mData+="<imagebg>\n";
			mData+=item.getUrlBg();
			mData+="</imagebg>";
			mData+="<parent>";
			mData+=menuid;
			mData+="</parent>";
			mData+="<type>";
			mData+=1;
			mData+="</type>";
			mData+="<level>";
			mData+=1;
			mData+="</level>";
			mData+="</Item>";
		}	
		return mData;
	}
	
	private String getItemRoomService(int id)
	{
		DiningPMS dinning = new DiningPMS();	
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		Vector<eItem> v_rs= dinning.getRoomSvcItems(id, LangID, -1, -1);
		for(int i=0;i<v_rs.size();i++)
		{
			eItem item=v_rs.get(i);		
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getICode();
			mData+="</id>";
			mData+="<Def>";		
			if(item.getDef()==null)
			{
				mData+="<![CDATA[]]>";
			}else				
			mData+="<![CDATA["+UtilString.converString(item.getDef())+"]]>";			
			mData+="</Def>";
			mData+="<status>";			
			mData+=item.getInvisible();			
			mData+="</status>";
			mData+="<image>";			
			mData+=item.getUrlImage();	
			mData+="</image>";	
			mData+="<price>";	
			if(item.getCurrency()!=null)
			{
			mData+=item.getCurrency();
			}else
			{
				mData+=0;
			}
			mData+="</price>";
			mData+="</Item>";
		}
		mData+="</xml>";
		return mData;
	}
	
}
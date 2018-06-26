package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.HousekeepingPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.model.PMSMainMenu;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.pms.eHousekeeping;
import ehotel.domain.pms.eMenu;

public class ServiceHouseKeeping extends ServiceParent
{
	private static final long serialVersionUID = 1L;
	private PMSServiceDBI pmsServiceDBI = new PMSServiceDBI();
	public ServiceHouseKeeping() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
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
			request.setAttribute("fileJSP", "../pmsMng/keeping/keeping.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");	
			break;
		}
		case 1://get subject 
		{
			System.out.println("Get subject housekkeping");
			response.setContentType("text/xml");
//			String st=getmenu();	
			String st= getMainMenuHotel();
			out.print(st);
			break;
		}
		case 2://
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
			String st= getItem(id);			
			out.print(st);
			break;
		}
		case 3:
		{
			System.out.println("Show form detail");
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString());
			}			
			HousekeepingPMS hotel = new HousekeepingPMS();	
			eHousekeeping item=null;			
			if(id!=-1)
			item=	hotel.getItemInfo(id, LangID);
			request.setAttribute("Item", item);
			this.showJSPpage(request, response, "/pmsMng/keeping/detaiItem.jsp");
			break;
		}
		case 4:
		{
			System.out.println("Delete item:");
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
				String param="(";
				for(i=0;i<list.size();i++)
				{
					param+=list.get(i)+",";
				}
				param=param.substring(0,param.length()-1)+")";	
				HousekeepingPMS hotel = new HousekeepingPMS();	
				hotel.removeItem(param);
				break;				
		}	
		case 5:
		{
			System.out.println("Show form detail");
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString());
			}			
			HousekeepingPMS hotel = new HousekeepingPMS();	
			hotel.changeStatus(id)	;
			break;
		}
		default:
			break;
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
		super.doPost(request, response);
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
		case 1:
		{
			System.out.println("Insert item housekeeping");
			String name="";
			String image="";
			String price="";
			int subid=-1;
			String unit="";
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			if(request.getParameter("price")!=null)
			{
				price=request.getParameter("price").toString().trim();
			}
			if(request.getParameter("subid")!=null)
			{
				subid=Integer.parseInt(request.getParameter("subid").toString().trim());
			}
			if(request.getParameter("unit")!=null)
			{
				unit=request.getParameter("unit").toString().trim();
			}
			HousekeepingPMS dinning = new HousekeepingPMS();
			eHousekeeping item=new eHousekeeping(); 
			item.setPrice(price);
			item.setName(name);
			item.setUrlImage(config._housekeeping + "/" +image);
			item.setIunit(unit);			
			int t= dinning.addItem(subid, item);
			if(t>0)
			{
				ManagerFile file=new ManagerFile();
				String path1=config._temp+"/"+image;
				String path2=config._pathImage+config._housekeeping + "/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 2:
		{
			System.out.println("update item housekeeping");
			String name="";
			String image="";
			String price="";
			String unit="";
			int id=-1;
			if(request.getParameter("name")!=null)
			{
				name=request.getParameter("name").toString();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString().trim();
			}
			if(request.getParameter("price")!=null)
			{
				price=request.getParameter("price").toString().trim();
			}
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			if(request.getParameter("unit")!=null)
			{
				unit=request.getParameter("unit").toString().trim();
			}
			HousekeepingPMS dinning = new HousekeepingPMS();
			eHousekeeping item=new eHousekeeping();
			item.setPrice(price);
			item.setName(name);
			item.setUrlImage(config._housekeeping + "/"+image);
			item.setId(id);
			item.setIunit(unit);			
			boolean t= dinning.editItem(item, LangID);
			if(t)
			{
				ManagerFile file=new ManagerFile();
				String path1=config._temp+"/"+image;
				String path2=config._pathImage+config._housekeeping + "/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);
			}
			break;
		}
		case 3://update subject
		{
			System.out.println("update subject PMS!");
			response.setContentType("text/xml");
			String subjectName="";
			String image="";
			String urlBg="";
			int parent=-1;
			int subid=-1;
			if(request.getParameter("image1")!=null)
			{
				image=request.getParameter("image1").toString();
			}
			if(request.getParameter("image2")!=null)
			{
				urlBg=request.getParameter("image2").toString();
			}
			if(request.getParameter("SubId")!=null)
			{
				subid=Integer.parseInt(request.getParameter("SubId").toString().trim());
			}
			if(request.getParameter("name")!=null)
			{								
				subjectName=request.getParameter("name").toString();
				HousekeepingPMS hotel = new HousekeepingPMS();				
				eMenu menu=new eMenu();
				menu.setMenuName(subjectName);
				menu.setUrlImage(config._housekeeping + "/"+image);
				menu.setUrlBg(config._housekeeping + "/"+urlBg);
				menu.setMenuId(subid);
				boolean id= hotel.editMenuHousekeeping(menu, LangID);
				if(id)
				{
					ManagerFile file=new ManagerFile();
					String path1=config._temp+"/"+image;
					String path2=config._pathImage+config._housekeeping + "/"+image;
					file.copy(path1, path2);
					file.deletefile(path1);
					path1=config._temp+"/"+urlBg;
					path2=config._pathImage+config._housekeeping + "/"+urlBg;
					file.copy(path1, path2);
					file.deletefile(path1);
				}	
			}			
			break;
		}
		default:
			break;
		}
	}
	private String getmenu()
	{		
		HousekeepingPMS hotel = new HousekeepingPMS();		
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
	
	private String getMainMenuHotel() {
		List<PMSMainMenu> list = new ArrayList<PMSMainMenu>();
		list = pmsServiceDBI.getMainMenu(6, LangID);
		String mData = "";
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml>";
		for (int i = 0; i < list.size(); i++) {
			mData += "<Item>";
			mData += "<name>";
			mData += "<![CDATA[" + list.get(i).getMenuname() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += list.get(i).getMenuid();
			mData += "</id>\n";
			mData += "<image>\n";
			mData += list.get(i).getMenuimage();
			mData += "</image>";
			mData += "<imagebg>\n";
			mData += list.get(i).getMenubackground();
			mData += "</imagebg>";
			mData += "<parent>";
			mData += -1;
			mData += "</parent>";
			mData += "<invisible>";
			mData += list.get(i).getMenuinvisible();
			mData += "</invisible>";
			mData += "</Item>";
		}
		mData += "</xml>";

		return mData;
	}
	private String getItem(int id)
	{
		HousekeepingPMS keeping = new HousekeepingPMS();	
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		Vector<eHousekeeping> v_rs= keeping.getItems(id, LangID, -1, -1);		
		for(int i=0;i<v_rs.size();i++)
		{
			eHousekeeping item=v_rs.get(i);			
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";
			mData+="<Def>";		
			if(item.getDef()==null)
			{
				mData+="<![CDATA[]]>";
			}else				
			mData+="<![CDATA["+item.getDef()+"]]>";			
			mData+="</Def>";
			mData+="<status>";			
			mData+=item.getInvisible();			
			mData+="</status>";	
			mData+="<image>";			
			mData+=item.getUrlImage();	
			mData+="</image>";	
			mData+="<price>";			
			mData+=item.getPrice();
			mData+="</price>";
			mData+="<unit>";			
			mData+=item.getIunit();
			mData+="</unit>";
			mData+="</Item>";
		}
		mData+="</xml>";
		return mData;
	}
	/**
	 * Initialization of the servlet. <br>
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	}
}

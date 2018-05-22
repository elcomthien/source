package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.AdvertisePMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.pms.eAdvertise;

public class ServiceAdvertise extends ServiceParent {

	/**
	 * Constructor of the object.
	 */
	public ServiceAdvertise() {
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
			}		
		}
		switch (cmd) {
		case -1:
		{
			int index=0;
			int page=5;				
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}				
			response.setContentType("text/xml");
			String st= getContent(index,page);			
			out.print(st);
			break;
		}
		case 1://
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
			response.setContentType("text/xml");
			String st= getContent(index,page);			
			out.print(st);
			break;
		}
		case 3://get
		{
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}			
			AdvertisePMS  ehotel = new AdvertisePMS();
			eAdvertise item=ehotel.getAdvertiseInfo(id, LangID);
			request.setAttribute("Item",item);
			this.showJSPpage(request, response, "/pmsMng/Other/detailAdver.jsp");			
			break;
		}
		case 4://delete advert
		{
			System.out.println("DELETE ADV");
			int i=0;
			Vector<Integer> list=new Vector();
			while(request.getParameter("id"+i)!=null)
			{
				int id=Integer.parseInt(request.getParameter("id"+i).toString().trim());
				i++;
				list.add(id);
			}
			AdvertisePMS  ehotel = new AdvertisePMS();
			String param="(";
			for(i=0;i<list.size();i++)
			{
				param+=list.get(i)+",";
			}
			param=param.substring(0,param.length()-1)+")";
			ehotel.removeAdverties(param);
			break;
		}
		case 5://change status
		{
			int id=-1;
			if(request.getParameter("id")!=null)
			{
				id=Integer.parseInt(request.getParameter("id").toString().trim());
			}
			AdvertisePMS adv=new AdvertisePMS();
			adv.changeStatus(id);
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
			System.out.println("UPDATE ADV PMS");
			String Name="";
			String image="";
			String bgimage="";
			String type="";
			if(request.getParameter("type")!=null)
			{
				type=request.getParameter("type").toString();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString();
			}
			if(request.getParameter("icon")!=null)
			{
				bgimage=request.getParameter("icon").toString();
			}
			int id=-1;					
			if(request.getParameter("name")!=null)
			{
				if(request.getParameter("id")!=null)
				{			
					id=Integer.parseInt(request.getParameter("id").toString().trim());
					Name=request.getParameter("name").toString();				
					AdvertisePMS adv=new AdvertisePMS();
					eAdvertise item=new eAdvertise();
					item.setId(id);
					item.setName(Name);
					item.setType(type);
					item.setUrlImage(config._advertise + "/"+image);
					item.setUrlBg(config._advertise + "/"+bgimage);						
					boolean t= adv.editAdvertise(item, LangID);		
					System.out.println("UPDATE ADV PMS:"+t);
					if(t)
					{						
						ManagerFile file=new ManagerFile();					
						String path1=config._temp+"/"+image;
						String path2=config._pathImage+config._advertise + "/"+image;						
						file.copy(path1, path2);
						file.deletefile(path1);
						path1=config._temp+"/"+bgimage;
						path2=config._pathImage+config._advertise + "/"+bgimage;
						file.copy(path1, path2);
						file.deletefile(path1);
					}
				}
			}
			break;
		}
		case 2:
		{
			System.out.println("insert ADV PMS");
			String Name="";
			String image="";
			String bgimage="";
			String type="";
			if(request.getParameter("type")!=null)
			{
				type=request.getParameter("type").toString();
			}
			if(request.getParameter("image")!=null)
			{
				image=request.getParameter("image").toString();
			}
			if(request.getParameter("icon")!=null)
			{
				bgimage=request.getParameter("icon").toString();
			}
			int id=-1;		
			if(request.getParameter("name")!=null)
			{				
				if(request.getParameter("id")!=null)
				{			
					id=Integer.parseInt(request.getParameter("id").toString().trim());
					Name=request.getParameter("name").toString();				
					AdvertisePMS adv=new AdvertisePMS();
					eAdvertise item=new eAdvertise();
					item.setId(id);
					item.setName(Name);
					item.setType(type);
					item.setUrlImage(config._advertise + "/"+image);
					item.setUrlBg(config._advertise + "/"+bgimage);						
					int t= adv.addAdvertise(item, type);
					System.out.println("insert ADV PMS:"+t);
					if(t>0)
					{						
						ManagerFile file=new ManagerFile();					
						String path1=config._temp+"/"+image;
						String path2=config._pathImage+config._advertise + "/"+image;						
						file.copy(path1, path2);
						file.deletefile(path1);
						path1=config._temp+"/"+bgimage;
						path2=config._pathImage+config._advertise + "/"+bgimage;
						file.copy(path1, path2);
						file.deletefile(path1);
					}
				}
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
	private String getContent(int index,int page)
	{
		AdvertisePMS  ehotel = new AdvertisePMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		Vector<eAdvertise> info=ehotel.getImageAdverties(LangID,fr,to);
		String mData="";
		int count=ehotel.countItem();
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{		
			eAdvertise item=info.get(i);
			System.out.println("Name:"+item.getName());
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
			mData+="<icon>";			
			mData+="<![CDATA["+item.getUrlIcon()+"]]>";			
			mData+="</icon>";
			mData+="<type>";			
			mData+="<![CDATA["+item.getType()+"]]>";			
			mData+="</type>";
			mData+="<status>";			
			mData+="<![CDATA["+item.getInvisibe()+"]]>";			
			mData+="</status>";
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
	}
}
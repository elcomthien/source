package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.abs.pms.PromotionPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.UtilString;
import ehotel.domain.pms.ePromotion;

public class ServicePromotion extends ServiceParent {
	/**
	 * Constructor of the object.
	 */
	public ServicePromotion() {
		super();
	}
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	/**
	 * The doGet method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to get.
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
				request.setAttribute("fileJSP", "../pmsMng/promotion/promotion.jsp");
				this.showJSPpage(request, response, "/include/Mainpage.jsp");
				break;
			}
			case 1://get list promotion
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
			case 2://show form detail
			{
				int id=-1;
				if(request.getParameter("id")!=null)
				{
					id=Integer.parseInt(request.getParameter("id").toString().trim());
				}		
				PromotionPMS promtion=new PromotionPMS();
				ePromotion item=promtion.getPromotionInfo(id, LangID);
				request.setAttribute("eItem", item);
				this.showJSPpage(request, response, "/pmsMng/Other/detailPromotion.jsp");	
				break;
			}
			case 3://delete promotion
			{
				int id=-1;
				int i=0;
				Vector<Integer> list=new Vector();
				while(request.getParameter("id"+i)!=null)
				{
					int subid= Integer.parseInt(request.getParameter("id"+i).toString().trim());				
					list.add(subid);
					i++;				
				}
				String param="(";
				for(i=0;i<list.size();i++)
				{
					param+=list.get(i)+",";
				}
				param=param.substring(0,param.length()-1)+")";
				System.out.println("Delete promotion " + param);
				PromotionPMS promtion=new PromotionPMS();
				promtion.removePromotion(param);
				break;
			}
			case 4:///promotion
			{
				System.out.println("Change status");
				int id=-1;
				if(request.getParameter("id")!=null)
				{
					id= Integer.parseInt(request.getParameter("id").toString().trim());									
				}	
				PromotionPMS promtion=new PromotionPMS();
				promtion.changeStatus(id);
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
			} catch (Exception e) {
			}	
		}
		switch (cmd) {
		case 1://insert
		{
			String name="";
			String image="";
			String def="";
			int status=1;	
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
				def=request.getParameter("des").toString();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			if(request.getParameter("status")!=null)
			{
				status=Integer.parseInt(request.getParameter("status").toString().trim());
			}
			PromotionPMS promotion= new PromotionPMS();
			ePromotion item=new ePromotion();
			item.setContent(def);
			item.setName(name);
			item.setUrlImage(config._promotion + "/"+image);
			item.setInvisible(status);
			int b= promotion.addPromotion(item);
			if(b>0)
			{
				ManagerFile file=new ManagerFile();
				String path1=config._temp+"/"+image;
				String path2=config._pathImage+config._promotion + "/"+image;
				file.copy(path1, path2);
				file.deletefile(path1);				
			}
			break;
		}
		case 2://update 
		{
			System.out.println("Update promotion");
			String name="";
			String image="";
			String def="";
			int status=1;
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
			if(request.getParameter("des")!=null)
			{
				def=request.getParameter("des").toString();
				def = def.replaceAll("<strong>", "<b>");
				def = def.replaceAll("</strong>", "</b>");
				def = def.replaceAll("<em>", "<i>");
				def = def.replaceAll("</em>", "</i>");
				def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
				def = def.replaceAll("</span>", "</u>");
			}
			if(request.getParameter("status")!=null)
			{
				status=Integer.parseInt(request.getParameter("status").toString().trim());
			}
			PromotionPMS promotion= new PromotionPMS();
			ePromotion item=new ePromotion();
			item.setId(id);
			item.setContent(def);
			item.setName(name);
			item.setUrlImage(config._promotion + "/"+image);
			item.setInvisible(status);
			boolean b= promotion.editPromotion(item, LangID);
			if(b)
			{
				ManagerFile file=new ManagerFile();
				String path1=config._temp+"/"+image;
				String path2=config._pathImage+config._promotion + "/"+image;
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
	private String getContent(int index,int page)
	{
		PromotionPMS promotion= new PromotionPMS();
		int fr=index*page;
		fr+=1;
		int to=(index+1)*page;	
		Vector<ePromotion> info=promotion.getPromotions(LangID,fr,to);
		String mData="";
		int count=promotion.countItem();
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\">";
		for(int i=0;i<info.size();i++)
		{
			ePromotion item=info.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";
			mData+="<Des>";
			if(item.getContent()!=null)
			{
				mData+="<![CDATA["+UtilString.converString(item.getContent())+"]]>";
			}else
			{
				mData+="<![CDATA[]]>";
			}
			mData+="</Des>";
			mData+="<status>";
			mData+="<![CDATA["+item.getInvisible()+"]]>";
			mData+="</status>";
			mData+="<image>";
			mData+="<![CDATA["+item.getUrlImage()+"]]>";
			mData+="</image>";
			mData+="</Item>";
		}
		mData+="</xml>";
		return mData;
	}
}
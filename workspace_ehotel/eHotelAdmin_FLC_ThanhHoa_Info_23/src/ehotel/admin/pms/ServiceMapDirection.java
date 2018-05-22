package ehotel.admin.pms;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.abs.pms.MapDirectionPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;
import ehotel.domain.pms.eLocationMap;
import ehotel.domain.pms.eMenu;

public class ServiceMapDirection extends ServiceParent  {
	private int tempMenuIdfr=0;
	private int idLocation;
	private String phone;
	private String distance;
	private String X;
	private String Y;
	/**
	 * Constructor of the object.
	 */
	public ServiceMapDirection() {
		super();
	}
	/**
	 * Destruction ow the servlet. <br>
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
		request.setCharacterEncoding("UTF-8");
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
		
			MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
			eLocationMap _eLocationMap=null;
			_eLocationMap =mapDirectionPMS.getMyLocation(LangID);
			idLocation=_eLocationMap.getId();
			 phone =_eLocationMap.getPhone();
			distance=_eLocationMap.getDistance();
			X=_eLocationMap.getX();
			Y=_eLocationMap.getY();
			request.setAttribute("getLocation", _eLocationMap);
			request.setAttribute("loadMenu", LoadMenu(LangID));
			request.setAttribute("fileJSP", "../pmsMng/Map/pmsMapDirection.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case 1:
			
			boolean kt =editLocationMap(request, response);
			if(!kt){
				out.print("failed");
			}
			break;
		case 2:
			int kq =addLocationMap(request, response);
			if(kq==-1){
				out.print("failed");
			}
		case 3:
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
			String st= getContent(tempMenuIdfr,LangID,index,page);
			out.print(st);
			break;
		case 4:
			boolean kt1 =editCoordinate(request, response);
			if(!kt1){
				out.print("failed");
			}
		case 5:
			int _menuid=0;
			if(request.getParameter("menuId")!=null)
			{
				_menuid=Integer.parseInt(request.getParameter("menuId"));
			}
			int index1=0;
			int page1=6;
			if(request.getParameter("pageIndex")!=null)
			{
				index1=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page1=Integer.parseInt(request.getParameter("page").toString().trim());
			}	
			response.setContentType("text/xml");
			String _st= getContent(_menuid,LangID,index1,page1);
			out.print(_st);
			break;
		case 6:
			
			int _menuidSearch=0;
			String search ="";
			int ids=0;
			if(request.getParameter("menuId")!=null)
			{
				_menuidSearch=Integer.parseInt(request.getParameter("menuId"));
			}
			if(request.getParameter("textsearch")!=null)
			{
				search=request.getParameter("textsearch").toString().trim();
				search = new String(search.getBytes("8859_1"),"UTF8");
			}
			/*
			if(request.getParameter("ids")!=null)
			{
				ids=Integer.parseInt(request.getParameter("idse"));
			}
			
			*/
			int index2=0;
			int page2=6;
			if(request.getParameter("pageIndex")!=null)
			{
				index2=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page2=Integer.parseInt(request.getParameter("page").toString().trim());
			}	
			/*
			if(ids==1){
				index2=0;
			}
			*/
			response.setContentType("text/xml");
			
			String textsearch= searchLocation(_menuidSearch,search,LangID,index2,page2);
			out.print(textsearch);
			break;
		case 7:
			boolean kt2 =deleteLocation(request, response);
			if(!kt2){
				out.print("failed");
			}
			break;
			
		case 8:
			boolean kt3 =editLocationMap1(request, response);
			if(!kt3){
				out.print("failed");
			}
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
		
			MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
			eLocationMap _eLocationMap=null;
			_eLocationMap =mapDirectionPMS.getMyLocation(LangID);
			idLocation=_eLocationMap.getId();
			 phone =_eLocationMap.getPhone();
			distance=_eLocationMap.getDistance();
			X=_eLocationMap.getX();
			Y=_eLocationMap.getY();
			request.setAttribute("getLocation", _eLocationMap);
			request.setAttribute("loadMenu", LoadMenu(LangID));
			request.setAttribute("fileJSP", "../pmsMng/Map/pmsMapDirection.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case 1:
			
			boolean kt =editLocationMap(request, response);
			if(!kt){
				out.print("failed");
			}
			break;
		case 2:
			int kq =addLocationMap(request, response);
			if(kq==-1){
				out.print("failed");
			}
		case 3:
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
			String st= getContent(tempMenuIdfr,LangID,index,page);
			out.print(st);
			break;
		case 4:
			boolean kt1 =editCoordinate(request, response);
			if(!kt1){
				out.print("failed");
			}
		case 5:
			int _menuid=0;
			if(request.getParameter("menuId")!=null)
			{
				_menuid=Integer.parseInt(request.getParameter("menuId"));
			}
			int index1=0;
			int page1=6;
			if(request.getParameter("pageIndex")!=null)
			{
				index1=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page1=Integer.parseInt(request.getParameter("page").toString().trim());
			}	
			response.setContentType("text/xml");
			String _st= getContent(_menuid,LangID,index1,page1);
			out.print(_st);
			break;
		case 6:
			int _menuidSearch=0;
			String search ="";
			if(request.getParameter("menuId")!=null)
			{
				_menuidSearch=Integer.parseInt(request.getParameter("menuId"));
			}
			if(request.getParameter("textsearch")!=null)
			{
				search=request.getParameter("textsearch").toString().trim();
			}
			int index2=0;
			int page2=6;
			if(request.getParameter("pageIndex")!=null)
			{
				index2=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page2=Integer.parseInt(request.getParameter("page").toString().trim());
			}	
			response.setContentType("text/xml");
			String textsearch= searchLocation(_menuidSearch,search,LangID,index2,page2);
			out.print(textsearch);
			break;
		case 7:
			boolean kt2 =deleteLocation(request, response);
			if(!kt2){
				out.print("failed");
			}
			break;
			
		case 8:
			boolean kt3 =editLocationMap1(request, response);
			if(!kt3){
				out.print("failed");
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
	private boolean editLocationMap(HttpServletRequest request, HttpServletResponse response){
		MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
		eLocationMap _eLocationMap=new eLocationMap();
		String  name="";
		String address="";
		if(request.getParameter("name")!=null)
		{
			name=request.getParameter("name").toString().trim();
		}
		if(request.getParameter("addr")!=null)
		{
			address=request.getParameter("addr").toString().trim();
			
		}
		_eLocationMap.setId(idLocation);
		_eLocationMap.setName(name);
		_eLocationMap.setAddress(address);
		_eLocationMap.setPhone(phone);
		_eLocationMap.setDistance(distance);
		_eLocationMap.setX(X);
		_eLocationMap.setY(Y);
		boolean plag =mapDirectionPMS.editLocationMap(_eLocationMap,LangID);
		return plag;
	}
	private boolean editCoordinate(HttpServletRequest request, HttpServletResponse response){
		MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
		int  idLcn=0;
		String _x="";
		String _y="";
		if(request.getParameter("id")!=null)
		{
			idLcn=Integer.parseInt(request.getParameter("id"));
		}
		if(request.getParameter("cx")!=null)
		{
			_x=request.getParameter("cx").toString().trim();
			
		}
		if(request.getParameter("cy")!=null)
		{
			_y=request.getParameter("cy").toString().trim();
			
		}
		boolean plag =mapDirectionPMS.editCoordinate(idLcn, _x, _y);
		return plag;
	}
private int addLocationMap(HttpServletRequest request, HttpServletResponse response){
			
		MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
		eLocationMap _eLocationMap=new eLocationMap();
		int mId =0;
		String  name="";
		String address="";
		String  distance="";
		String _x="";
		String  _y="";
		if(request.getParameter("Mid")!=null)
		{
			mId=Integer.parseInt(request.getParameter("Mid"));
		}
		if(request.getParameter("Name")!=null)
		{
			name=request.getParameter("Name").toString().trim();
		}
		if(request.getParameter("Address")!=null)
		{
			address=request.getParameter("Address").toString().trim();
		}
		if(request.getParameter("Distance")!=null)
		{
			distance=request.getParameter("Distance").toString().trim();
		}
		if(request.getParameter("X")!=null)
		{
			_x=request.getParameter("X").toString().trim();
		}
		if(request.getParameter("Y")!=null)
		{
			_y=request.getParameter("Y").toString().trim();
		}
		_eLocationMap.setName(name);
		_eLocationMap.setAddress(address);
		_eLocationMap.setPhone("");
		_eLocationMap.setDistance(distance);
		_eLocationMap.setX(_x);
		_eLocationMap.setY(_y);
		int plag =mapDirectionPMS.addLocaltionMap(_eLocationMap,mId);
		return plag;
	}
	public Vector LoadMenu(int LangID){
		MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
		Vector<eMenu> list=null;
		list=mapDirectionPMS.getMenus(LangID);	
		tempMenuIdfr =list.get(0).getMenuId();
		return list; 
	}
	private String getContent(int menuId,int langId,int index,int page)
	{
		int c=0;
		MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
		int fr=index*page;
		fr+=1;
		c=fr;
		int to=(index+1)*page;
		Vector<eLocationMap> info=mapDirectionPMS.getLocations(menuId, langId,fr, to);
		String mData="";
		int count=mapDirectionPMS.countItem(menuId);
		if(info.size()==0 && count>0){
			fr=1;
			to=5;
			info=mapDirectionPMS.getLocations(menuId, langId,fr, to);
		}
		System.out.println("count:"+count);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\" menuId=\""+menuId+"\">";
		if(count <c && count!=0){
			if(count%5==0){
				
				fr=(index-1)*page;
				fr+=1;
				c=fr;
				to=(index)*page;
				info=mapDirectionPMS.getLocations(menuId, langId,fr, to);
				c=count-4;
			}
		}
		for(int i=0;i<info.size();i++)
		{		
			eLocationMap item=info.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";	
			mData+="<stt>\n";
			mData+=c++;
			mData+="</stt>";	
			mData+="<phone>\n";
			mData+=item.getPhone();
			mData+="</phone>";
			mData+="<addr>\n";
			mData+=item.getAddress();
			mData+="</addr>";	
			mData+="<dis>\n";
			mData+=item.getDistance();
			mData+="</dis>";	
			mData+="<x>\n";
			mData+=item.getX();
			mData+="</x>";	
			mData+="<y>\n";
			mData+=item.getY();
			mData+="</y>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
	}
	private String searchLocation(int menuId,String searchCode,int langId,int index,int page) {
		int c=0;
		MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
		int fr=index*page;
		fr+=1;
		c=fr;
		int to=(index+1)*page;
		Vector<eLocationMap> info=mapDirectionPMS.searchLocation(menuId,searchCode, langId,fr, to);
		String mData="";
		int count=mapDirectionPMS.countItemSearch(menuId,searchCode);
		if(count>0 && info.size()==0){
			info=mapDirectionPMS.searchLocation(menuId,searchCode, langId,1, 5);
		}
		System.out.println("count:"+count);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\" menuId=\""+ menuId +"\">";
		for(int i=0;i<info.size();i++)
		{		
			eLocationMap item=info.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=item.getId();
			mData+="</id>";	
			mData+="<stt>\n";
			mData+=c++;
			mData+="</stt>";
			mData+="<phone>\n";
			mData+=item.getPhone();
			mData+="</phone>";
			mData+="<addr>\n";
			mData+="<![CDATA["+item.getAddress()+"]]>";
			mData+="</addr>";	
			mData+="<dis>\n";
			mData+=item.getDistance();
			mData+="</dis>";	
			mData+="<x>\n";
			mData+=item.getX();
			mData+="</x>";	
			mData+="<y>\n";
			mData+=item.getY();
			mData+="</y>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
	}
	private boolean deleteLocation(HttpServletRequest request, HttpServletResponse response){
		MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
		int id =0;
		if(request.getParameter("id")!=null)
		{
			id=Integer.parseInt(request.getParameter("id"));
		}
		boolean plag =mapDirectionPMS.removeLocation(id);
		return plag;
	}
	private boolean editLocationMap1(HttpServletRequest request, HttpServletResponse response){
		MapDirectionPMS mapDirectionPMS =new MapDirectionPMS();
		eLocationMap _eLocationMap=new eLocationMap();
		String  name1="";
		String addr1="";
		String dis="";
		String x1="";
		String y1="";
		String phone1="";
		int id=0;
		if(request.getParameter("id")!=null)
		{
			id=Integer.parseInt(request.getParameter("id"));
		}
		if(request.getParameter("name")!=null)
		{
			name1=request.getParameter("name").toString().trim();
		}
		if(request.getParameter("addr")!=null)
		{
			addr1=request.getParameter("addr").toString().trim();
		}
		if(request.getParameter("dis")!=null)
		{
			dis=request.getParameter("dis").toString().trim();
		}
		if(request.getParameter("x")!=null)
		{
 			x1=request.getParameter("x").toString().trim();
		}
		if(request.getParameter("phone")!=null)
		{
 			phone1=request.getParameter("phone").toString().trim();
		}
		if(request.getParameter("y")!=null)
		{
			y1=request.getParameter("y").toString().trim();
		}
		_eLocationMap.setId(id);
		_eLocationMap.setName(name1);
		_eLocationMap.setAddress(addr1);
		_eLocationMap.setPhone(phone1);
		_eLocationMap.setDistance(dis);
		_eLocationMap.setX(x1);
		_eLocationMap.setY(y1);
		boolean plag =mapDirectionPMS.editLocationMap(_eLocationMap,LangID);
		return plag;
	}
}
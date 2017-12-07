package ehotel.admin.System;

import ehotel.admin.Service.ServiceParent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.admin.util.Def;
import java.util.Vector;

import ehotel.domain.group.eCategory;
import ehotel.domain.group.eGroup;
import ehotel.domain.group.eRole;
import ehotel.domain.group.eUser;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDVod;
import ehotel.inter.IUser;
import ehotel.render.DBIGateway;
import ehotel.render.VOD;


public class ServiceAdminRoleMng extends ServiceParent{
	private static IUser iuser=DBIGateway.getIUser();
	public ServiceAdminRoleMng() {
		super();
	}
	/**
	 * Destruction ow the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
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
			request.setAttribute("fileJSP", "../pmsMng/System/sysRoleMng.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case 1:
			response.setContentType("text/xml");
			String kq =getsubMod();
			System.out.print(kq);
			out.print(kq);
			break;
		case 2:
			boolean p =deleteCategory(request, response);
			if(!p){
				out.print("failed");
			}
			break;
		case 3:
			int CateId=0;
			if(request.getParameter("CateId")!=null)
			{
				CateId=Integer.parseInt(request.getParameter("CateId"));
			}
			response.setContentType("text/xml");
			String roleLeft =getLeftRoleCategory(CateId);
			out.print(roleLeft);
			break;
		case 4:
			int CateId1=0;
			if(request.getParameter("CateId")!=null)
			{
				CateId1=Integer.parseInt(request.getParameter("CateId"));
			}
			response.setContentType("text/xml");
			String roleRight =getRightRoleCategory(CateId1);
			out.print(roleRight);
			break;
		case 5:
			boolean plag =UpdateRole(request, response);
			if(!plag){
				out.print("failed");
			}
			break;
		default:
			break;
		}
	}
	private String getsubMod()
	{
			Vector<eCategory> vCategory =iuser.getCategoryList(LangID);
			String mData="";
			mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
			mData+="<xml>";
			for(int i=0;i<vCategory.size();i++)
			{		
					eCategory item=vCategory.get(i);
					mData+="<Item>";
					mData+="<name>";
					mData+="<![CDATA["+item.getName()+"]]>";
					mData+="</name>";
					mData+="<id>\n";
					mData+=item.getCateId();
					mData+="</id>\n";
					mData+="<parent>";
					mData+=item.getParentId();
					mData+="</parent>\n";
					mData+="</Item>";	
			}
			mData+="</xml>";
			return mData;
		}
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
		case 1:
			boolean plag =EditRoleGroup(request, response);
			if(!plag){
				out.print("failed");
			}
			break;
		case 2:
			int kq =AddRoleGroup(request, response);
			if(kq==-1){
				out.print("failed");
			}else if(kq==-2){
				out.print("f");
			}
			break;
		default:
			break;
		}
	}
	private boolean EditRoleGroup(HttpServletRequest request, HttpServletResponse response){
		
		eCategory _eCategory = new eCategory();
		String CateName="";
		int CateId=0;
		if(request.getParameter("CateId")!=null)
		{
			CateId=Integer.parseInt(request.getParameter("CateId"));
		}
		if(request.getParameter("CateName")!=null)
		{
			CateName=request.getParameter("CateName").toString().trim();
			
		}
		_eCategory.setCateId(CateId);
		_eCategory.setName(CateName);
		boolean plag =iuser.editCategory(_eCategory, LangID);
		return plag;
	}
	private boolean deleteCategory(HttpServletRequest request, HttpServletResponse response){
		int cateId=0;
		if(request.getParameter("cateId")!=null)
		{
			cateId=Integer.parseInt(request.getParameter("cateId"));
		}
		
		boolean plag =iuser.removeCategory(cateId);
		return plag;
	}
	private String getLeftRoleCategory(int cateId)
	{
		Vector<eRole> vRole =iuser.getRolesOutCate(cateId,LangID);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<vRole.size();i++)
		{		
			eRole item=vRole.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>";
			mData+=item.getId();
			mData+="</id>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
		
	} 
	private String getRightRoleCategory(int cateId)
	{
		Vector<eRole> vRole =iuser.getRolesInCate(cateId,LangID);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<vRole.size();i++)
		{		
			eRole item=vRole.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>";
			mData+=item.getId();
			mData+="</id>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
		
	}
	private boolean UpdateRole(HttpServletRequest request, HttpServletResponse response){
		int  cateId=0;
		String str="";
		if(request.getParameter("cateId")!=null)
		{
			cateId=Integer.parseInt(request.getParameter("cateId"));
		}
		if(request.getParameter("str")!=null)
		{
			str=request.getParameter("str").toString().trim();
		}
		String chuoi ="("+str+")";
		boolean plag =iuser.addRoleIntoCate(cateId,chuoi);
		return plag;
	}
private int AddRoleGroup(HttpServletRequest request, HttpServletResponse response){
		
		eCategory _eCategory = new eCategory();
		String CateName="";
		int ParentId=0;
		int cateId=0;
		if(request.getParameter("CateName")!=null)
		{
			CateName=request.getParameter("CateName").toString().trim();
			
		}
		if(request.getParameter("ParentId")!=null)
		{
			ParentId=Integer.parseInt(request.getParameter("ParentId"));
			
		}
		_eCategory.setName(CateName);
		//_eCategory.setParentId(ParentId);
		//_eCategory.setCateId(cateId);
		System.out.print("ParentId" + ParentId);
		int plag =iuser.addCategory(ParentId,_eCategory);
		return plag;
	}
}

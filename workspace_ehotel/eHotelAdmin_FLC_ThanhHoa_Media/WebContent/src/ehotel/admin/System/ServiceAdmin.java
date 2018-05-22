package ehotel.admin.System;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;
import ehotel.domain.group.eGroup;
import ehotel.domain.group.eRole;
import ehotel.domain.group.eUser;
import ehotel.inter.IUser;
import ehotel.render.DBIGateway;

public class ServiceAdmin extends ServiceParent{
	private static IUser iuser=DBIGateway.getIUser();
	private int nxt=0;
	private int pr=0;
	public ServiceAdmin() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void init() throws ServletException {
		// Put your code here
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
			request.setAttribute("loadMenuAD", getsubMod());;
			request.setAttribute("fileJSP", "../pmsMng/System/sysServiceAdmin.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case 1:
			int kq =addUser(request, response);
			if(kq==-1){
				out.print("failed");
			}else if(kq==-2){
				out.print("f");
			}
			break;
		case 2:	
			int index=0;
			int page=6;
			int mennuIdUser=0;
			if(request.getParameter("menuIdUser")!=null)
			{
				mennuIdUser=Integer.parseInt(request.getParameter("menuIdUser").toString());
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
			String st=getUserInfo(mennuIdUser,index,page);	
			out.print(st);
			
			break;
		case 3:
			boolean plag =EditUser(request, response);
			if(!plag){
				out.print("failed");
			}
			break;
		case 4:
			boolean plag1 =deleteUser(request, response);
			if(!plag1){
				out.print("failed");
			}
			break;
		case 5:
			int kq3 =addMenuGroup(request, response);
			if(kq3==-1){
				out.print("failed");
			}else if(kq3==-2){
				out.print("f");
			}
			break;
		case 8:
			response.setContentType("text/xml");
			String st1= getsubModXml();
			out.print(st1);
			break;
		case 7:
			boolean plag11 =deleteGroup(request, response);
			if(!plag11){
				out.print("failed");
			}
			break;
		case 9:
			boolean plag13 =EditGroupMenu(request, response);
			if(!plag13){
				out.print("failed");
			}
			break;
		case 10:
			int userId =0;
			if(request.getParameter("userId")!=null)
			{
				userId=Integer.parseInt(request.getParameter("userId").toString());
			}
			response.setContentType("text/xml");
			String l= getLeftUser(userId);
			out.print(l);
			break;
		case 11:
			int userId1 =0;
			if(request.getParameter("userId")!=null)
			{
				userId1=Integer.parseInt(request.getParameter("userId").toString());
			}
			response.setContentType("text/xml");
			String r= getRightUser(userId1);
			out.print(r);
			break;
		case 12:
			int groupId =0;
			if(request.getParameter("groupId")!=null)
			{
				groupId=Integer.parseInt(request.getParameter("groupId").toString());
			}
			response.setContentType("text/xml");
			String g= getLeftGroup(groupId);
			out.print(g);
			break;
		case 13:
			int groupId1 =0;
			if(request.getParameter("groupId")!=null)
			{
				groupId1=Integer.parseInt(request.getParameter("groupId").toString());
			}
			response.setContentType("text/xml");
			String g1= getRightGroup(groupId1);
			System.out.print(g1);
			out.print(g1);
			break;
		case 14:
			response.setContentType("text/xml");
			boolean g2= addRoleGroup(request,response);
			if(!g2){
				out.print("failed");
			}
			break;
		case 15:
			boolean changstatus= ChangeStatus(request,response);
			if(!changstatus){
				out.print("failed");
			}
			break;
		case 16:
			boolean getS=addRoleUser(request,response);
			if(!getS){
				out.print("failed");
			}
			break;	
		case 17:
			boolean cpass= ChangePass(request,response);
			if(!cpass){
				out.print("failed");
			}
			break;	
		case 18:
			int userId18 =0;
			if(request.getParameter("userId")!=null)
			{
				userId18=Integer.parseInt(request.getParameter("userId").toString());
			}
			response.setContentType("text/xml");
			String gc1= getLeftChangeGroup(userId18);
			out.print(gc1);
			break;	
		case 19:
			int userId19 =0;
			if(request.getParameter("userId")!=null)
			{
				userId19=Integer.parseInt(request.getParameter("userId").toString());
			}
			response.setContentType("text/xml");
			String gc2= getRightChangeGroup(userId19);
			out.print(gc2);
			break;
		case 20:
			boolean cgroup= ChangeGroup(request,response);
			if(!cgroup){
				out.print("failed");
			}
			break;	
		default:
			break;
		}
		
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
			request.setAttribute("loadMenuAD", getsubMod());;
			request.setAttribute("fileJSP", "../pmsMng/System/sysServiceAdmin.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case 1:
			int kq =addUser(request, response);
			if(kq==-1){
				out.print("failed");
			}else if(kq==-2){
				out.print("f");
			}
			break;
		case 2:	
			int index=0;
			int page=6;
			int mennuIdUser=0;
			if(request.getParameter("menuIdUser")!=null)
			{
				mennuIdUser=Integer.parseInt(request.getParameter("menuIdUser").toString());
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
			String st=getUserInfo(mennuIdUser,index,page);	
			out.print(st);
			
			break;
		case 3:
			boolean plag =EditUser(request, response);
			if(!plag){
				out.print("failed");
			}
			break;
		case 4:
			boolean plag1 =deleteUser(request, response);
			if(!plag1){
				out.print("failed");
			}
			break;
		case 5:
			int kq3 =addMenuGroup(request, response);
			if(kq3==-1){
				out.print("failed");
			}else if(kq3==-2){
				out.print("f");
			}
			break;
		case 6:
			response.setContentType("text/xml");
			String _st= getsubModXml();
			out.print(_st);
		case 7:
			boolean plag11 =deleteGroup(request, response);
			if(!plag11){
				out.print("failed");
			}
			break;
		case 8:
			response.setContentType("text/xml");
			String st1= getsubModXml();
			out.print(st1);
			break;
		case 9:
			boolean plag13 =EditGroupMenu(request, response);
			if(!plag13){
				out.print("failed");
			}
			break;
		default:
			break;
		}
	}
	private Vector<eGroup> getsubMod()
	{
		Vector<eGroup> vGroup =iuser.getGroupList(LangID);
		return vGroup;
	} 
	private String getsubModXml()
	{
		Vector<eGroup> vGroup =iuser.getGroupList(LangID);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		if(vGroup.size()!=0){
			mData+="<xml menuIdGF=\""+vGroup.get(0).getGroupId()+"\">";
		}else{
			mData+="<xml menuIdGF=\""+0+"\">";
		}
		
		for(int i=0;i<vGroup.size();i++)
		{		
			eGroup item=vGroup.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>";
			mData+=item.getGroupId();
			mData+="</id>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
		
	} 
	public String getUserInfo(int menuid1,int index,int page){
		int c=0;
		int fr=index*page;
		fr+=1;
		c=fr;
		int to=(index+1)*page;
		Vector<eUser> vUsers =iuser.getUsers(menuid1, fr,to );
		String mData="";
		int count=iuser.countUsers(menuid1);
		System.out.println("count:"+count);
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml  count=\""+count+"\" >";
		
		if(count <c && count!=0){
			
			if(count%6==0){
				nxt=count-5;
				pr=count;
				c=pr;
				
			}
			vUsers =iuser.getUsers(menuid1, nxt,pr );
			c=nxt;
			
		}
		
		for(int i=0;i<vUsers.size();i++)
		{		
			eUser item=vUsers.get(i);
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
			mData+="<department>\n";
			if(null!=item.getDepartment()){
				mData+=item.getDepartment();
			}else{
				mData+=-1;
			}
			mData+="</department>";
			mData+="<address>";
			mData+="<![CDATA["+item.getAddress()+"]]>";
			mData+="</address>";
			mData+="<account>\n";
			mData+=item.getAccount();
			mData+="</account>";	
			mData+="<createDate>\n";
			mData+=item.getCreateDate();
			mData+="</createDate>";	
			mData+="<Active>\n";
			mData+=item.getActive();
			mData+="</Active>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
	
		return mData;
	}
	private int addUser(HttpServletRequest request, HttpServletResponse response){
		
		eUser u = new eUser();
		String  account="";
		String password="";
		String name="";
		String  address="";
		String  department="";
		int tempIdMenu=0;
		if(request.getParameter("tempIdMenu")!=null)
		{
			tempIdMenu=Integer.parseInt(request.getParameter("tempIdMenu"));
		}
		if(request.getParameter("account")!=null)
		{
			account=request.getParameter("account").toString().trim();
		}
		if(request.getParameter("password")!=null)
		{
			password=request.getParameter("password").toString().trim();
			
		}
		if(request.getParameter("name")!=null)
		{
			name=request.getParameter("name").toString().trim();
			
		}
		if(request.getParameter("address")!=null)
		{
			address=request.getParameter("address").toString().trim();
		}
		if(request.getParameter("department")!=null)
		{
			department=request.getParameter("department").toString().trim();
		}
		u.setName(name);
		u.setAddress(address);
		u.setDepartment(department);
		u.setAccount(account);
		u.setPasswd(password);
		int plag =iuser.addUser(tempIdMenu, u);
		return plag;
	}
private boolean EditUser(HttpServletRequest request, HttpServletResponse response){
		
		eUser u = new eUser();
		String  account="";
		String password="";
		String name="";
		String  address="";
		String  department="";
		int tempId=0;
		if(request.getParameter("tempId")!=null)
		{
			tempId=Integer.parseInt(request.getParameter("tempId"));
		}
		if(request.getParameter("name")!=null)
		{
			name=request.getParameter("name").toString().trim();
			
		}
		if(request.getParameter("address")!=null)
		{
			address=request.getParameter("address").toString().trim();
		}
		if(request.getParameter("department")!=null)
		{
			department=request.getParameter("department").toString().trim();
		}
		if(request.getParameter("account")!=null)
		{
			account=request.getParameter("account").toString().trim();
		}
		if(request.getParameter("password")!=null)
		{
			password=request.getParameter("password").toString().trim();
		}
		u.setName(name);
		u.setAddress(address);
		u.setDepartment(department);
		u.setId(tempId);
		u.setPasswd(password);
		u.setAccount(account);
		boolean plag =iuser.editUser(u);
		return plag;
	}

private boolean EditGroupMenu(HttpServletRequest request, HttpServletResponse response){
	
	eGroup group = new eGroup();
	String name="";
	int tempId=0;
	if(request.getParameter("tempId")!=null)
	{
		tempId=Integer.parseInt(request.getParameter("tempId"));
	}
	if(request.getParameter("name")!=null)
	{
		name=request.getParameter("name").toString().trim();
		
	}
	group.setGroupId(tempId);
	group.setName(name);
	boolean plag =iuser.editGroup(group, LangID);
	return plag;
}
	private boolean deleteUser(HttpServletRequest request, HttpServletResponse response){
		int id =0;
		int groupId=0;
		if(request.getParameter("groupId")!=null)
		{
			groupId=Integer.parseInt(request.getParameter("groupId"));
		}
		if(request.getParameter("id")!=null)
		{
			id=Integer.parseInt(request.getParameter("id"));
		}
		boolean plag =iuser.removeUser(groupId, id);
		return plag;
	}
	private boolean deleteGroup(HttpServletRequest request, HttpServletResponse response){
		int id =0;
		int groupId=0;
		if(request.getParameter("groupId")!=null)
		{
			groupId=Integer.parseInt(request.getParameter("groupId"));
		}
		
		boolean plag =iuser.removeGroup(groupId);
		return plag;
	}
	private int addMenuGroup(HttpServletRequest request, HttpServletResponse response){
		
		eGroup group = new eGroup();
		String  name="";
		if(request.getParameter("name")!=null)
		{
			name=request.getParameter("name").toString().trim();
		}
		group.setName(name);
		int plag =iuser.addGroup(group);
		return plag;
	}
	private String getLeftUser(int userId)
	{
		Vector<eRole> vRole =iuser.getRolesOutUser(userId,LangID);
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
	private String getRightUser(int userId)
	{
		Vector<eRole> vRole =iuser.getRolesInUser(userId,LangID);
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
	private String getLeftGroup(int groupId)
	{
		Vector<eRole> vRole =iuser.getRolesOutGroup(groupId,LangID);
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
	private String getRightGroup(int groupId)
	{
		Vector<eRole> vRole =iuser.getRolesInGroup(groupId,LangID);
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
	private String getLeftChangeGroup(int userId)
	{
		Vector<eGroup> vGroup =iuser.getGroupsOutUser(userId,LangID);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<vGroup.size();i++)
		{		
			eGroup item=vGroup.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>";
			mData+=item.getGroupId();
			mData+="</id>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
		
	} 
	private String getRightChangeGroup(int userIdId)
	{
		Vector<eGroup> vGroup =iuser.getGroupsInUser(userIdId,LangID);
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		for(int i=0;i<vGroup.size();i++)
		{		
			eGroup item=vGroup.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>";
			mData+=item.getGroupId();
			mData+="</id>";	
			mData+="</Item>";			
		}
		mData+="</xml>";
		return mData;
		
	} 
private boolean addRoleGroup(HttpServletRequest request, HttpServletResponse response){
		int  groupId=0;
		String str="";
		if(request.getParameter("groupId")!=null)
		{
			groupId=Integer.parseInt(request.getParameter("groupId"));
		}
		if(request.getParameter("str")!=null)
		{
			str=request.getParameter("str").toString().trim();
		}
		String chuoi ="("+str+")";
		boolean plag =iuser.addRoleIntoGroup(groupId,chuoi);
		return plag;
	}
private boolean addRoleUser(HttpServletRequest request, HttpServletResponse response){
	int  userId=0;
	String str="";
	if(request.getParameter("userId")!=null)
	{
		userId=Integer.parseInt(request.getParameter("userId"));
	}
	if(request.getParameter("str")!=null)
	{
		str=request.getParameter("str").toString().trim();
	}
	String chuoi ="("+str+")";
	boolean plag =iuser.addRoleIntoUser(userId,chuoi);
	return plag;
}
private boolean ChangeGroup(HttpServletRequest request, HttpServletResponse response){
	int  userId=0;
	String str="";
	if(request.getParameter("userId")!=null)
	{
		userId=Integer.parseInt(request.getParameter("userId"));
	}
	if(request.getParameter("str")!=null)
	{
		str=request.getParameter("str").toString().trim();
	}
	String chuoi ="("+str+")";
	boolean plag =iuser.changeGroup(userId,chuoi);
	return plag;
}
private boolean ChangePass(HttpServletRequest request, HttpServletResponse response){
	int  userId=0;
	String password="";
	if(request.getParameter("tempId")!=null)
	{
		userId=Integer.parseInt(request.getParameter("tempId"));
	}
	if(request.getParameter("password")!=null)
	{
		password=request.getParameter("password").toString().trim();
	}
	boolean plag =iuser.changePassword(userId,password);
	return plag;
}
	private boolean ChangeStatus(HttpServletRequest request, HttpServletResponse response){
		int userId=0;
		if(request.getParameter("userId")!=null)
		{
			userId=Integer.parseInt(request.getParameter("userId"));
		}
		boolean plag =iuser.changeActive(userId);
		return plag;
	}
	
	
	
}


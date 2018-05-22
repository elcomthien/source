<%@page import="ehotel.inter.ILOGIN"%>
<script type="text/javascript" src="../javascript/common/header.js"></script>
<%@page import="ehotel.admin.util.Def"%>
<%@page import="ehotel.inter.AMDMenu"%>
<%@page import="ehotel.render.DBIGateway"%>
<%@page import="java.util.Vector"%>
<%@page import="ehotel.domain.menu.Group"%>
<%@page import="ehotel.domain.menu.Menu"%>
<%@ include file="Paramter.jsp"%>
<%
	AMDMenu admMenu = DBIGateway.getAMDMenu();
	Vector<Group> groups = admMenu.getGroups(LangID,request.getRemoteAddr());
	Vector<Menu> menus = admMenu.getMenuList(1, 2);			
	int MenuID=-1;
	int SubID=-1;
	try {		
		MenuID=Integer.parseInt(request.getAttribute(Def.MenuId).toString());
		SubID=Integer.parseInt(request.getAttribute(Def.SubId).toString());
	} catch(Exception e){};	
 %>
<div style="width: 1024; height: 90px;">
	<div class="menu_top_1">
		<ul style="float: left; width: 700px; margin: 8px 0 10px 25px; padding: 0px;" id="div_topmenu">
			<%
			ILOGIN iuser = DBIGateway.getILogin();	
			String ipAdress=request.getRemoteAddr();
			boolean isadmin=iuser.isAdmin(ipAdress);
			for(int i=0;i<groups.size();i++)
			{
				if(MenuID==groups.get(i).getGroupId())
				{	
					//loai bo Report 25.06.2013 5
					if (groups.get(i).getGroupId() != 6 && groups.get(i).getGroupId() != 99) {
						out.print("<li class=\"menu_top_focus\" id='"+groups.get(i).getGroupId()+"'>");
						out.print("<div class=\"menu_item\">");
						out.print(groups.get(i).getGroupName());
						out.print("</div>");
						out.print("</li>");
					}
				} else {
					boolean role=true;
					if(!isadmin)
					role=iuser.checkRoleUser(ipAdress,groups.get(i).getRoleValue());
					//loai bo Report 25.06.2013
					if (groups.get(i).getGroupId() != 6 && groups.get(i).getGroupId() != 99 && groups.get(i).getGroupId() != 1 && groups.get(i).getGroupId() != 2) {
						out.print("<li class=\"menu_top_item\" id='"+groups.get(i).getGroupId()+"' role=\""+(role?1:-1)+"\">");
						out.print("<div class=\"menu_item\">");
						out.print(groups.get(i).getGroupName());
						out.print("</div>");
						out.print("</li>");
					}
				}
			}
		 %>
		</ul>
		<img src="../icon/home_w.png" style="float: right; cursor: pointer;"
			onclick="javascript:goHome()"></img>
	</div>
	<div class="submenu">
		<ul style="float: left; width: 1024px; margin: 8px 0 10px 25px; padding: 0px;" id="submenu">
		</ul>
	</div>
</div>
<script type="text/javascript">
	var menuId=<%=MenuID%>;
	var subId=<%=SubID%>;
	var menu=new MenuTop();
	menu.load();
</script>
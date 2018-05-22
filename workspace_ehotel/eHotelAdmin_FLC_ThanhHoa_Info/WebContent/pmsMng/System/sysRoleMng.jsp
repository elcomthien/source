<%@page pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../include/Paramter.jsp"%>
<jsp:directive.page import="java.util.Vector" />
<script type="text/javascript" src="../javascript/common/common.js"></script>
<script type="text/javascript" src="../javascript/ajax.js"></script>
<script type="text/javascript" src="../javascript/common/object.js"></script>
<script type="text/javascript" src="../javascript/system/functionAD.js"></script>
<script type="text/javascript" src="../javascript/common/table.js"></script>

<link rel="stylesheet" href="../css/tree.css" type="text/css"></link>
<link rel="stylesheet" href="../css/common.css" type="text/css"></link>
<link rel="stylesheet" href="../css/main.css" type="text/css"></link>
<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/VodCtn.css" type="text/css"></link>
<link rel="stylesheet" href="../css/sysAdmin.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/system/RoleMng.js"></script>
<%
String isAdmin="";
if(null!=request.getAttribute("isAdmin")){
	isAdmin =(String)request.getAttribute("isAdmin");
}
%>
<script type="text/javascript">
 var NameAssigned="";
var NameNotAssigned="";
var ok="";
var cancel="";
var checkUpdate="";
var checkDelete="";
var checkDeleteFrom="";
var update="";
var list="";
var name="";
var checkName="";
var checkAdd="";
var checkAddMenuEx="";
var NameGroupPMS="";
var creategroup="";
var deletegroup="";
var updategroup="";
var addgroup="";
var selectItem="";
selectItem="<%=readerLang.getContent("selectItem") %>";
var selectItem2="";
selectItem2="<%=readerLang.getContent("selectItem2") %>";
addgroup="<%=readerLang.getContent("addgroup") %>";
creategroup="<%=readerLang.getContent("creategroup") %>";
updategroup="<%=readerLang.getContent("updategroup") %>";
deletegroup="<%=readerLang.getContent("deletegroup") %>";
NameGroupPMS="<%=readerLang.getContent("NameGroupPMS") %>";
checkAddMenuEx="<%=readerLang.getContent("checkAddMenuEx") %>";
checkAdd="<%=readerLang.getContent("checkAdd") %>";
name="<%=readerLang.getContent("Name") %>";
checkDeleteFrom="<%=readerLang.getContent("checkDeleteFrom") %>";
ok="<%=readerLang.getContent("OK") %>";
checkDelete="<%=readerLang.getContent("checkDelete") %>";
cancel="<%=readerLang.getContent("Cancel") %>";
NameAssigned ="<%=readerLang.getContent("NameAssigned") %>";
NameNotAssigned="<%=readerLang.getContent("NameNotAssigned") %>";
checkUpdate="<%=readerLang.getContent("checkUpdate") %>";
update="<%=readerLang.getContent("update") %>";
list="<%=readerLang.getContent("list") %>";
checkName="<%=readerLang.getContent("checkName") %>";
	window.onload=function(){
	<%
		String user="";
		if(session.getAttribute("user")!=null)
		 	{
		 		user=session.getAttribute("user").toString();
		 	}
		%>
	
	checkIsAdmin("<%=user%>");
	
	var ctMenu=new contextMenu();
	//	subjectVod =new subjectVod();
	//	subjectVod.get();
	get();
		
	//LoadFrm();
	}
</script>
<div style="width: 1024;" onclick="pageclick();">
	<div class="left_content">
		<div class="left_content_top"></div>
		<div class="left_content_bottom">
			<div class="grid">
				<div class="grid_header">
					<span
						style="float: left; font-weight: bold; margin-left: 20px; margin-top: 10px; color: white; font-size: 16px;"
						id="title_subject"></span>
					<div style="float: right; width: 150px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="1"
							onclick="Mod.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("list") %></p>
						</div>
					</div>
				</div>
				<div class="grid_center">
					<div id="id_table"
						style="height: auto; min-height: 400px; padding-top: 20px">

					</div>
				</div>
				<div class="gird_bottom"></div>
			</div>

		</div>

	</div>
	<div class="content_right" align="center">
		<div class="menu" style="margin-top: 25px;" id="menu">
			<div class="menu_header">
				<div
					style="float: left; color: white; font-size: 20px; margin-left: 20px; margin-top: 15px; font-weight: bold;">
					<%=readerLang.getContent("NameGroupPMS") %>
				</div>
			</div>
			<div class="menu_center" align="center">
				<div id='menutree'></div>

			</div>
			<div id="AddMenu" class="menu_center_ttt" align="center"
				style="display: block">
				<%if(isAdmin.equals("true")){ %>
				<a href="javascript:ctMenu.fucNew(-1);" style="color: gray"> <%=readerLang.getContent("addgroup") %>
				</a>
				<% }%>
			</div>
			<div class="menu_bottom"></div>
		</div>
	</div>
</div>

<%@page pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@page import="ehotel.domain.group.eGroup"%>
<%
Vector<eGroup> vGroup =new Vector();
if(null!=request.getAttribute("loadMenuAD")){
	vGroup =(Vector<eGroup>)request.getAttribute("loadMenuAD");
}
%>
<%@ include file="../../include/Paramter.jsp"%>
<jsp:directive.page import="java.util.Vector" />
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/main.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pmsHotel.css" type="text/css"></link>
<link rel="stylesheet" href="../css/cssTableMapDirection.css"
	type="text/css"></link>
<link rel="stylesheet" href="../css/sysAdmin.css" type="text/css"></link>
<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/system/functionAD.js"></script>
<script type="text/javascript" src="../javascript/system/sysAdmin.js"></script>
<script type="text/javascript">
var no="";
var name="";
var department="";
var createDate="";
var active="";
var totalUser="";
var createNewUser="";
var account="";
var password="";
var address="";
var cfPassword="";
var ok="";
var cancel="";
var checkAccount="";
var checkPass="";
var checkcfPass="";
var checkMachPass="";
var checkName="";
var userInfo="";
var checkUpdate="";
var checkDelete="";
var checkAddEx="";
var checkDeleteFrom="";
var NewSubject="";
var checkAddMenuEx="";
var menuIdF=0;
var checkUpdateMenuEx=""; 
var changePass="";
var NamePms="";
var checkAdd="";
var errorStatus="";
var NameGrant="";
var AddUser="";
var AccountLength="";
var group="";
var list="";
var changegroup="";
var creategroup="";
var deletegroup="";
var updategroup="";
var pmsgroup="";
var grantPms="";
var detailuser="";
var deleteuserg="";
var deleteusers="";
var changeStatus="";
var changegroupPms="";
var changePass="";
var userNameGroup="";
var total="";
var selectItem="";
selectItem="<%=readerLang.getContent("selectItem") %>";
var selectItem2="";
selectItem2="<%=readerLang.getContent("selectItem2") %>";
var subjectName="";
subjectName="<%=readerLang.getContent("subjectName") %>";
	total ="<%=readerLang.getContent("total") %>";
	no ="<%=readerLang.getContent("stt") %>";
	name="<%=readerLang.getContent("Name") %>";
	department ="<%=readerLang.getContent("department") %>";
	createDate ="<%=readerLang.getContent("createDate") %>";
	active="<%=readerLang.getContent("active") %>";
	totalUser="<%=readerLang.getContent("totalUser") %>";
	account="<%=readerLang.getContent("account") %>";
	createNewUser ="<%=readerLang.getContent("createNewUser") %>";
	password="<%=readerLang.getContent("password") %>";
	cfPassword="<%=readerLang.getContent("cfPassword") %>";
	address="<%=readerLang.getContent("Address") %>";
	ok="<%=readerLang.getContent("OK") %>";
	cancel="<%=readerLang.getContent("Cancel") %>";
	checkAccount="<%=readerLang.getContent("checkAccount") %>";
	checkPass="<%=readerLang.getContent("checkPass") %>";
	checkcfPass="<%=readerLang.getContent("checkcfPass") %>";
	checkMachPass="<%=readerLang.getContent("checkMachPass") %>";
	checkName="<%=readerLang.getContent("checkName") %>";
	userInfo="<%=readerLang.getContent("userInfo") %>";
	checkUpdate="<%=readerLang.getContent("checkUpdate") %>";
	checkDelete="<%=readerLang.getContent("checkDelete") %>";
	checkAddEx="<%=readerLang.getContent("checkAddEx") %>";
	checkDeleteFrom="<%=readerLang.getContent("checkDeleteFrom") %>";
	NewSubject="<%=readerLang.getContent("NewSubjectMenu") %>";
	checkAddMenuEx="<%=readerLang.getContent("checkAddMenuEx") %>";
	checkUpdateMenuEx="<%=readerLang.getContent("checkUpdateMenuEx") %>";
	changePass="<%=readerLang.getContent("changePass") %>";
	NamePms="<%=readerLang.getContent("NamePms") %>";
	checkAdd="<%=readerLang.getContent("checkAdd") %>";
	errorStatus="<%=readerLang.getContent("errorStatus") %>";
	NameGrant="<%=readerLang.getContent("NameGrant") %>";
	AddUser="<%=readerLang.getContent("AddUser") %>";
	AccountLength="<%=readerLang.getContent("AccountLength") %>";
	group="<%=readerLang.getContent("group") %>";
	list="<%=readerLang.getContent("list") %>";
	changegroup="<%=readerLang.getContent("changegroup") %>";
	creategroup="<%=readerLang.getContent("creategroup") %>";
	deletegroup="<%=readerLang.getContent("deletegroup") %>";
	updategroup="<%=readerLang.getContent("updategroup") %>";
	pmsgroup="<%=readerLang.getContent("pmsgroup") %>";
	grantPms ="<%=readerLang.getContent("grantPms") %>";
	detailuser ="<%=readerLang.getContent("detailuser") %>";
	deleteuserg ="<%=readerLang.getContent("deleteuserg") %>";
	deleteusers ="<%=readerLang.getContent("deleteusers") %>";
	changeStatus ="<%=readerLang.getContent("changeStatus") %>";
	changegroupPms ="<%=readerLang.getContent("changegroupPms") %>";
	changePass ="<%=readerLang.getContent("changePass") %>";
	userNameGroup ="<%=readerLang.getContent("userNameGroup") %>";
	
window.onload = function()
{
<%
String user="";
if(session.getAttribute("user")!=null)
 	{
 		user=session.getAttribute("user").toString();
 	}
%>
checkIsAdmin("<%=user%>");
 
var arr =new Array();
<%for(int i=0;i< vGroup.size();i++){   
%>
arr[<%=i%>]=new ItemMenu("<%=vGroup.get(i).getGroupId()%>","<%=vGroup.get(i).getName()%>");
<%}%>
	loadMenu(arr);
	<%if(vGroup.size()!=0){%>
	loadUserInfor(<%=vGroup.get(0).getGroupId()%>);
	menuIdF="<%=vGroup.get(0).getGroupId()%>";
	<%}%>
} 
function ItemMenu(id,name)
{
this.Id=id;this.Name=name;
}
</script>
<div style="width: 1024;" onclick="closeAllMenu()">
	<div class="left_content">
		<div class="left_content_top"></div>
		<div class="left_content_bottom">
			<div class="grid">
				<div class="grid_header">
					<span
						style="float: left; font-weight: bold; margin-left: 20px; margin-top: 10px; color: white; font-size: 16px;"
						id="title_subject"><div id="totalUser"></div> </span>
					<div style="float: right; width: 150px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="1"
							onclick="Mod.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("list") %></p>
						</div>
					</div>
				</div>
				<div class="grid_center">
					<div id="id_table" style="height: auto; min-height: 250"></div>
				</div>
				<div class="gird_bottom"></div>
			</div>

		</div>

	</div>
	<div class="content_right" align="center">
		<div class="menu" style="margin-top: 25px" id="menu">
			<div class="menu_header">
				<div
					style="float: left; color: white; font-size: 20px; margin-left: 20px; margin-top: 15px; font-weight: bold">
					<%=readerLang.getContent("group") %>
				</div>
			</div>
			<div class="menu_center" align="center">
				<div id="menutreeAd"></div>

			</div>
			<div id="AddMenu" class="menu_center_ttt" align="center"
				style="display: block">

				<a href="javascript:showfromAddMenu();" style="color: gray"> <%=readerLang.getContent("addgroup") %>
				</a>
			</div>

			<div class="menu_bottom"></div>
		</div>
	</div>
</div>

<%@page pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/Paramter.jsp"%>
<script type="text/javascript" src="../javascript/common/common.js"></script>
<script type="text/javascript" src="../javascript/ajax.js"></script>
<script type="text/javascript" src="../javascript/service/objectSrc.js"></script>
<script type="text/javascript" src="../javascript/common/function.js"></script>
<script type="text/javascript" src="../javascript/common/table.js"></script>
<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/main.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pmsHotel.css" type="text/css"></link>
<link rel="stylesheet" href="../css/cssTableMapDirection.css"
	type="text/css"></link>
<link rel="stylesheet" href="../css/srcLiveTVMain.css" type="text/css"></link>
<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<script type="text/javascript"
	src="../javascript/service/ChannelAdvertise.js"></script>
<script type="text/javascript" src="../javascript/system/functionAD.js"></script>
<!--[if IE]>
    <style type="text/css">
        div.tableContainer  {
            position: relative;
         	 clear: both;
			height: 290px;
            overflow-y: scroll;
        }
        thead tr {
            position: absolute;
            top: expression(this.offsetParent.scrollTop);
        }
         tbody.scrollContent {
         display: block;
			height: 256px;
         
        }
        tbody.scrollContent td, tbody.scrollContent tr.normalRow td  {
            border-bottom: none;
	border-left: none;
	padding: 2px 3px 3px 4px
        }     
    </style>
  
<![endif]-->

<script type="text/javascript">
var ok="";
var cancel="";
var group="";
var list="";
var subject ="";
var addSubject="";
var editSubject="";
var deleteSubject="";
var checkAdd="";
var checkAddMenuEx="";
var _titleName="";
var Image="";
var checkDelete="";
var checkDeleteFrom="";
var EditSubject="";
var No="";
var name="";
var address="";
var code="";
var active="";
var liveTV="";
var addLiveTv="";
var deleteLiveTv="";
var updateLiveTv="";
var detailLiveTv="";
var changeStatus="";
var editLiveTV="";
var checkUpdate="";
var checkEditEx="";
var _serviceName="";
var No="";
var title="";
var Status="";
var total="";
var changeIma="";
var EditSubject="";
var dbclickaddchannel="";
var dbclickremovechannel="";
var subjectLength ="";
var subjectName="";
var subjectInfo="";
var subjectInfoLive="";
subjectInfoLive="<%=readerLang.getContent("subjectInfoLive") %>";
subjectInfo="<%=readerLang.getContent("subjectInfo") %>";
subjectName="<%=readerLang.getContent("subjectName") %>";
subjectLength="<%=readerLang.getContent("subjectLength") %>";
dbclickremovechannel="<%=readerLang.getContent("dbclickremovechannel") %>";
dbclickaddchannel ="<%=readerLang.getContent("dbclickaddchannel") %>";
EditSubject ="<%=readerLang.getContent("EditSubject") %>";
changeIma ="<%=readerLang.getContent("changeIma") %>";
total ="<%=readerLang.getContent("total") %>";
_trangthai ="<%=readerLang.getContent("active") %>";
title ="<%=readerLang.getContent("Name") %>";
_no ="<%=readerLang.getContent("No") %>";
_serviceName ="<%=readerLang.getContent("serviceName") %>";
checkEditEx ="<%=readerLang.getContent("checkEditEx") %>";
checkUpdate ="<%=readerLang.getContent("checkUpdate") %>";
editLiveTV ="<%=readerLang.getContent("editLiveTV") %>";
changeStatus ="<%=readerLang.getContent("changeStatus") %>";
addLiveTv="<%=readerLang.getContent("addLiveTv") %>";
deleteLiveTv="<%=readerLang.getContent("deleteLiveTv") %>";
updateLiveTv="<%=readerLang.getContent("updateLiveTv") %>";
detailLiveTv="<%=readerLang.getContent("detailLiveTv") %>";
liveTV="<%=readerLang.getContent("liveTV") %>";
active="<%=readerLang.getContent("active") %>";
address="<%=readerLang.getContent("Address") %>";
address="<%=readerLang.getContent("Address") %>";
name="<%=readerLang.getContent("Name") %>";
No="<%=readerLang.getContent("No") %>";
EditSubject="<%=readerLang.getContent("EditSubject") %>";
checkDeleteFrom="<%=readerLang.getContent("checkDeleteFrom") %>";
checkDelete="<%=readerLang.getContent("checkDelete") %>";
_Image="<%=readerLang.getContent("image_") %>";
_titleName="<%=readerLang.getContent("2") %>";
	checkAddMenuEx="<%=readerLang.getContent("checkAddMenuEx") %>";
	checkAdd="<%=readerLang.getContent("checkAdd") %>";
	ok="<%=readerLang.getContent("OK") %>";
	cancel="<%=readerLang.getContent("Cancel") %>";
	subject="<%=readerLang.getContent("Subject") %>";
	addSubject="<%=readerLang.getContent("AddSubject") %>";
	editSubject="<%=readerLang.getContent("EditSubject") %>";
	deleteSubject="<%=readerLang.getContent("DeleteSubject") %>";
	NewSubject="<%=readerLang.getContent("NewSubject") %>";
checkLinkLiveTV="<%=readerLang.getContent("checkLinkLiveTV") %>";
// var ctMenu=new contextMenu();
window.onload=function(){
// $("textSearch").value="";
loadAvertise();
};
function showAddMenu(){
ctMenu.fucNew();
}
</script>
<div style="width: 1024">
	<div class="left_content" style="float: none; width: 80%;">
		<div class="left_content_top">Channel Advertise</div>
		<div class="left_content_bottom">
			<div class="grid">
				<div class="form_header" style="">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject">
<!-- 						<div style="font-size: 18px" id="totalUser"></div>  -->
						</span>
					<div class="tab_menu" id="tab_menu" style="float: right; width: 230px;" >
						<div class="tab_menu_item_sel" id="0"
							onclick="changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent("list") %></p>
						</div>
						<div class="tab_menu_item" id="1" onclick="changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("9") %></p>
						</div>
						<div class="tab_menu_item" id="2" onclick="changeTab(this.id);" style="display: none;">
							<p style="margin-top: 5px; height: 0px;">Channels</p>
						</div>
					</div>
				</div>

				<div class="form_center">
					<div id="frmSearch" style="margin-left: 10px; display: none;"
						align="left">
						<input type="text" id="textSearch" style="margin-top: 5px"
							name="textSearch" onkeyup="search();" /> <img
							src="../icon/find-icon.png" onclick="search();">
					</div>
					<div id="id_table" style="height: auto; min-height: 250px">
					</div>
					<div id="id_table2" style="height: auto; min-height: 330px; display: none; width: 98%;">
					</div>
					<div id="id_table1"
						style="height: auto; min-height: 250px; display: none">
						<div>

							<div class="form_addContent">

								<div
									style="width: 100%; height: 10px; text-align: left; text-indent: 20px; font-weight: 800; font-size: 20px; color: #003ab6">

								</div>

								<div style="width: 100%; height: 25px">

									<div style="float: left; width: 120px; cursor: pointer"
										id="div_drop_sub1">

										<div style="float: left"><%=readerLang.getContent("content") %>
											:
										</div>
										<div style="float: left; color: #ab8718; margin-left: 2px">
											LiveTv</div>

									</div>

									<div
										style="float: left; width: 300px; margin-left: 240px; cursor: pointer; text-align: left"
										id="div_drop_sub2">

										<div style="float: left"><%=readerLang.getContent("contentSubject") %>
											:
										</div>
										<div id="namSerSub"
											style="float: left; color: #ab8718; margin-left: 1px"></div>

									</div>

								</div>

								<div style="width: 670px; height: auto;">

									<div class="left_form_addContent">

										<div id="tableCtn" class="tableContainer"></div>

										<div style="color: #878888;" title="Helo">
											(<%=readerLang.getContent("dbclickaddchannel") %>)
										</div>

									</div>
									<div style="float: left; padding-left: 10px;">
										<div class="add_subject_src" id="add_subject"
											onclick="addsubject();"></div>
										<div class="remove_subject_src" id="remove_subject"
											onclick="removesubject();"></div>
									</div>
									<div class="right_form_addContent">

										<div id="tableSrv" class="tableContainer"></div>

										<div style="color: #878888;">
											(<%=readerLang.getContent("dbclickremovechannel") %>)
										</div>

									</div>

								</div>

							</div>

						</div>
					</div>

				</div>

				<div class="form_bottom"></div>
			</div>

		</div>

	</div>
	<!-- 
	<div class="content_right" align="center" style="display: none;">
		<div class="menu" style="margin-top: 25px" id="menu">
			<div class="menu_header">
				<div
					style="float: left; color: white; font-size: 20px; margin-left: 20px; margin-top: 15px; font-weight: bold;">
					<%=readerLang.getContent("Subject") %>
				</div>
			</div>
			<div class="menu_center" align="center">
				<div id="menutree"></div>
				<div style="display: block" id="AddMenu">
					<a href="javascript:showAddMenu();"><%=readerLang.getContent("AddSubject") %></a>
				</div>
			</div>
			<div class="menu_bottom"></div>
		</div>
	</div>
	 -->
</div>
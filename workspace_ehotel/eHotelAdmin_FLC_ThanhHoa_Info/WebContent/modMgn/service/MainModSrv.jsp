<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="ehotel.admin.util.Def"%>
<%@ include file="../../include/ParamterVod.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>eHotelAdmin</title>
<script type="text/javascript" src="../javascript/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="../javascript/common/common.js"></script>
<script type="text/javascript" src="../javascript/ajax.js"></script>
<script type="text/javascript" src="../javascript/common/object.js"></script>
<script type="text/javascript" src="../javascript/common/function.js"></script>
<script type="text/javascript" src="../javascript/common/table.js"></script>
<script type="text/javascript" src="../rte/richtext.js"></script>
<script type="text/javascript" src="../javascript/modMng/modSrvAdd.js"></script>

<script type="text/javascript" src="../javascript/modMng/modSrv.js"></script>

<link rel="stylesheet" href="../css/tree.css" type="text/css"></link>
<link rel="stylesheet" href="../css/common.css" type="text/css"></link>
<link rel="stylesheet" href="../css/main.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>

<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<link rel="stylesheet" href="../css/MainMod.css" type="text/css"></link>
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
</head>
<body onload="Mod.run();" style="overflow-x: hidden;"
	onclick="Mod.onclick()" onkeydown="return Common.onkeydown(event)"
	onkeyup="return Common.onkeyup(event)" onmouseup="" onmousedown="">
	<div align="center" style="overflow: hidden; left: 0px;">
		<div class="main" align="center" onclick="Mod.onclick();" id="main">
			<jsp:include page="../../include/header.jsp"></jsp:include>
			<div class="menu_top" align="center">
				<jsp:include page="../../include/MenuTop.jsp"><jsp:param
						value="<%=Def._ContentID %>" name="Path" /><jsp:param value="1"
						name="SubId" /></jsp:include>
			</div>
			<div class="content" style="margin-bottom: 10px;">
				<div style="width: 1024">
					<div class="left_content">
						<div class="left_content_top">Audio Content Management</div>
						<div class="left_content_bottom">
							<div class="grid">
								<div class="grid_header">
									<span
										style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
										id="title_subject">Form </span>
									<div style="float: right;" class="tab_menu" id="tab_menu">
										<div class="tab_menu_item_sel" id="0"
											onclick="Mod.changeTab(this.id);">
											<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent("8") %></p>
										</div>
										<div class="tab_menu_item" id="1"
											onclick="Mod.changeTab(this.id);">
											<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("9") %></p>
										</div>
									</div>
								</div>
								<div class="grid_center">
									<div id="id_table" style="height: auto; min-height: 250px;">
									</div>
									<div id="tag_add" style="display: none;">
										<jsp:include page="../../modMgn/service/ModsrvAdd.jsp"></jsp:include>
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
									Subject</div>
							</div>
							<div class="menu_center" align="center">
								<div id='menutree'></div>
								<div id="div_AddSubject">
									<a href="javascript:Mod.sub.addSubject(-1);"
										style="color: #000066;"> <%=readerLang.getContent("AddSubject") %>
									</a>
								</div>
							</div>
							<div class="menu_bottom"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer" align="center">
			<div class="footer_center"></div>
		</div>
	</div>


	<div id="div_property"></div>
</body>
</html>

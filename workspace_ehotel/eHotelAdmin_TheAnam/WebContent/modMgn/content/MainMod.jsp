<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="ehotel.admin.util.Def"%>
<%@ include file="../../include/ParamterVod.jsp"%>
<%
	String ID = "";
	if (request.getAttribute("ID") != null)
		request.getAttribute("ID").toString();
	System.out.print("ID:" + ID);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>eHotelAdmin</title>
<script type="text/javascript" src="../javascript/common/common.js"></script>
<script type="text/javascript" src="../javascript/ajax.js"></script>
<script type="text/javascript" src="../javascript/common/object.js"></script>
<script type="text/javascript" src="../javascript/common/function.js"></script>
<script type="text/javascript" src="../javascript/common/table.js"></script>
<script type="text/javascript" src="../rte/richtext.js"></script>
<script type="text/javascript" src="../javascript/modMng/addMod.js"></script>
<script type="text/javascript" src="../javascript/modMng/MainMod.js"></script>
<script type="text/javascript" src="../javascript/tiny_mce/tiny_mce.js"></script>

<link rel="stylesheet" href="../css/tree.css" type="text/css"></link>
<link rel="stylesheet" href="../css/common.css" type="text/css"></link>
<link rel="stylesheet" href="../css/main.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>

<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<link rel="stylesheet" href="../css/MainMod.css" type="text/css"></link>
</head>
<body onload="mod.run();" style="overflow-x: hidden;"
	onkeydown="return mod.onkeydown(event)"
	onkeyup="return mod.onkeyup(event);">
	<div align="center" style="overflow: hidden; left: 0px;">
		<div class="main" align="center" onclick="mod.onclick();" id="main">
			<jsp:include page="../../include/header.jsp"></jsp:include>
			<div class="menu_top" align="center">
				<jsp:include page="../../include/MenuTop.jsp"><jsp:param
						value="<%=Def._ContentID%>" name="Path" /><jsp:param value="1"
						name="SubId" /></jsp:include>
			</div>
			<input id="hosttemp" style="display: none;" value="" /> <input
				id="porttemp" style="display: none;" value="" /> <input
				id="usertemp" style="display: none;" value="" /> <input
				id="passtemp" style="display: none;" value="" />
			<div class="content" style="margin-bottom: 10px;">
				<div style="width: 1024">
					<div class="left_content">

						<div class="left_content_top">
							<%=readerLang.getContent("AudioCntManager")%>
						</div>
						<div class="left_content_bottom">
							<div class="grid">
								<div class="grid_header">
									<span
										style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
										id="title_subject">Form </span>
									<div style="float: right;" class="tab_menu" id="tab_menu">
										<div class="tab_menu_item_sel" id="0"
											onclick="mod.changeTab(this.id);">
											<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent(8)%></p>
										</div>
										<div class="tab_menu_item" id="1"
											onclick="mod.changeTab(this.id);">
											<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent(9)%></p>
										</div>
									</div>
								</div>
								<div class="grid_center">
									<div id="id_table" style="min-height: 250; height: 250px;">
									</div>
									<div id="div_addmusic"
										style="display: none; height: 380; min-height: 200px">
										<jsp:include page="AddMod.jsp"></jsp:include>
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
									<%=readerLang.getContent("Subject")%>
								</div>
							</div>
							<div class="menu_center" align="center">
								<ul>
								</ul>

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

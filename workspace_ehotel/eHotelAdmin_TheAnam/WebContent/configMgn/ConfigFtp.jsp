<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="ehotel.admin.util.Def"%>
<%@ include file="../../include/ParamterVod.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>eHotelAdmin</title>
<script type="text/javascript" src="../javascript/common/common.js"></script>
<script type="text/javascript" src="../javascript/ajax.js"></script>
<script type="text/javascript" src="../javascript/common/object.js"></script>
<script type="text/javascript" src="../javascript/common/function.js"></script>
<script type="text/javascript" src="../javascript/common/table.js"></script>
<link rel="stylesheet" href="../css/tree.css" type="text/css"></link>
<link rel="stylesheet" href="../css/common.css" type="text/css"></link>
<link rel="stylesheet" href="../css/main.css" type="text/css"></link>
<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/VodCtn.css" type="text/css"></link>
<script type="text/javascript" src="../rte/richtext.js"></script>
<script type="text/javascript" src="../javascript/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="../javascript/vodMng/CtnAdd.js"></script>
<script type="text/javascript" src="../javascript/vodMng/content.js"></script>
</head>
<body style="overflow-x: hidden;" onclick="Vod.onclick()"
	onkeydown="return Common.onkeydown(event)"
	onkeyup="return Common.onkeyup(event)" onmouseup="" onmousedown="">
	<div align="center" style="overflow: hidden; left: 0px;">
		<div class="main" align="center" id="main">
			<jsp:include page="../../include/header.jsp"></jsp:include>
			<div class="menu_top" align="center">
				<jsp:include page="../../include/MenuTop.jsp"><jsp:param
						value="1" name="Path" />
					<jsp:param value="0" name="SubId" /></jsp:include>
			</div>
			
			
			<input id="hosttemp" style="display: none;"/>
			<input id="porttemp" style="display: none;"/>
			<input id="usertemp" style="display: none;"/>
			<input id="passtemp" style="display: none;"/>
			
			<div class="content" style="margin-bottom: 10px;">
				<div style="width: 1024;">
					<div class="left_content">
						<div class="left_content_top">
							<%=readerLang.getContent("VodCntManager") %>
						</div>
						<div class="left_content_bottom">
							<div class="grid">
								<div class="grid_header">
									<span
										style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
										id="title_subject">Form </span>
									<div style="float: right;" class="tab_menu" id="tab_menu">
										<div class="tab_menu_item_sel" id="0"
											onclick="Vod.changeTab(this.id);Vod.loadList()">
											<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent(8)%></p>
										</div>
									</div>
								</div>
								<div class="grid_center">
									<div id="id_table" style="height: auto; min-height: 250px;">
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
									<%=readerLang.getContent(10)%>
								</div>
							</div>
							<div class="menu_center" align="center">
								<div id='menutree' class='menutree' style="min-height: 200px;">
								</div>
								<div class='div_linkadd'>
									<a href="javascript:Vod.sub.addSubject(-1)"
										style="color: #000066;"><%=readerLang.getContent(11)%></a>
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
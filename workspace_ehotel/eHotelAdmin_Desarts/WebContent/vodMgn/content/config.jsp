<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="../javascript/bootstrap/bootstrap.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../javascript/bootstrap/bootstrap-select.css" type="text/css"></link>
<link rel="stylesheet"
	href="../javascript/bootstrap/bootstrap-responsive.css" type="text/css"></link>

<script type="text/javascript" src="../rte/richtext.js"></script>
<script type="text/javascript" src="../javascript/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="../javascript/vodMng/CtnAdd.js"></script>
<script type="text/javascript" src="../javascript/vodMng/content.js"></script>
<script type="text/javascript" src="../javascript/vodMng/config.js"></script>
</head>
<body style="overflow-x: hidden;"
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

			<div class="content" style="margin-bottom: 10px;">
				<div style="width: 1024;">
					<div class="left_content" style="float: none; width: auto;">
						<div class="left_content_top">
							<%=readerLang.getContent("ConfigManager")%>
						</div>
						<div class="left_content_bottom">
							<div class="grid">
								<div class="grid_header">
									<span
										style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
										id="title_subject"></span>
									<div style="float: right; width: 120px;" class="tab_menu"
										id="tab_menu">
										<div class="tab_menu_item_sel" id="0"
											onclick="Vod.changeTab(this.id);">
											<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent(22)%></p>
										</div>

									</div>
								</div>
								<div class="grid_center">
									<div id="content_config"
										style="height: auto; min-height: 250px;">
										<!-- 										<form action=""> -->
										<div class="row">
											<div class="span2"
												style="text-align: right; margin-top: 25px;">Host:</div>
											<div class="span3" style="margin-top: 20px;">
												<input type="text" class="input-block-level" id="cfhost" value="${host}"/>
											</div>
										</div>
										<div class="row" style="margin-top: 10px;">
											<div class="span2"
												style="text-align: right; margin-top: 5px;">Port:</div>
											<div class="span3">
												<input type="text" class="input-block-level" id="cfport" value="${port}"/>
											</div>
										</div>
										<div class="row" style="margin-top: 10px;">
											<div class="span2"
												style="text-align: right; margin-top: 5px;">User:</div>
											<div class="span3">
												<input type="text" class="input-block-level" id="cfuser" value="${user}"/>
											</div>
										</div>
										<div class="row" style="margin-top: 10px;">
											<div class="span2"
												style="text-align: right; margin-top: 5px;">Pass:</div>
											<div class="span3">
												<input type="password" class="input-block-level" id="cfpass" value="${pass}"/>
											</div>
										</div>

										<div class="row" style="margin-top: 20px; margin-left: 30px;">
											<div class="span3" style="text-align: right;">
											<input type="button" value=" Config " class="div_buton" id="cfbtnconfig"  onclick="config()" style="float: right; width: 100px;" />
<!-- 												<button class="btn btn-primary" style="width: 100px" id="cfbtnconfig"  onclick="config()" > Config</button> -->
											</div>
											<div class="span3" style="text-align: left; margin-left: 10px;">
											<input type="button" value=" Reset" class="div_buton" id="cfbtnreset" onclick="reset()" style="width: 100px; margin-left: 0px;"/>
<!-- 												<button class="btn btn-inverse" style="width: 100px;" id="cfbtnreset" onclick="reset()" >Reset</button> -->
											</div>
										</div>
										<!-- 										</form> -->
									</div>
								</div>
								<div class="gird_bottom"></div>
							</div>
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
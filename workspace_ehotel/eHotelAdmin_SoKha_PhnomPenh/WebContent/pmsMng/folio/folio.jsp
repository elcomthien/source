<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@page pageEncoding="utf-8" contentType="text/html; charset=UTF-8" %>
<%@page import="ehotel.admin.util.ReaderLang"%>
<%@page import="ehotel.admin.util.Def"%>
<script type="text/javascript" src="../javascript/pms/folio_room.js"></script>
<script type="text/javascript" src="../javascript/pms/room.js"></script>
<link rel="stylesheet" href="../css/folio.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/pms/folio.js"></script>
<script type="text/javascript" src="../javascript/pms/record.js"></script>
<link rel="stylesheet" href="../css/other.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/pms/pms.js"></script>
<link rel="stylesheet" href="../css/pms.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pmsHotel.css" type="text/css"></link>
<!-- <script type="text/javascript" src="../javascript/charts/jquery.js"></script>  -->
<!-- <script type="text/javascript" src="../javascript/bootstrap/jquery.flip.js"></script> -->
<link rel="stylesheet" href="../javascript/bootstrap/bootstrap.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../javascript/bootstrap/bootstrap-select.css" type="text/css"></link>
<link rel="stylesheet"
	href="../javascript/bootstrap/bootstrap-responsive.css" type="text/css"></link>
<!-- <script type="text/javascript" src="../javascript/bootstrap/bootstrap.js"></script> -->
<!-- <script type="text/javascript" src="../javascript/bootstrap/bootstrap-select.js"></script> -->
<%@ include file="../../include/ParamterPms.jsp"%>
<div style="width: 1024;">
	<div>
		<div class="left_content_top" style="margin-left: 0px;">
			<%=readerLang.getContent("FolioMgn")%>
		</div>
		<div class="left_content_bottom" align="center">
			<div style="width: 820; height: auto;">
				<div class="form_header" style="float: left;">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"> </span>
					<div style="float: right; width: 240px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="0"
							onclick="folio.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent("RoomList")%></p>
						</div>
						<div class="tab_menu_item" id="1"
							onclick="folio.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("STB")%></p>
						</div>
						<div class="tab_menu_item" id="2"
							onclick="folio.changeTab(this.id);"  style="display: none;">
							<p style="margin-top: 5px; height: 0px;">Record</p>
						</div>
					</div>
				</div>
				<div class="form_center"
					style="height: auto; position: relative; float: left;">
					<div style="float: left; width: 100%; height: 600px;" id="div_room">
						<div
							style="float: left; height: 25px; width: 50%; float: left; margin-top: 5px; margin-left: 25px;">
							<div style="float: left; width: 150px; height: 100%;"
								align="left">
								<input type="text" id="text_seach" size="20"
									onkeyup="folio.seach();">
							</div>
							<div style="float: left;">
								<img src="../icon/find-icon.png"
									style="margin-top: 5; cursor: pointer;"
									onclick="folio.seach();"></img>
							</div>
						</div>
						<div id="id_table"
							style="height: auto; min-height: 240; height: 240; width: 792px; margin-top: 0px; float: left; margin-left: 13px;">
						</div>
						<div
							style="height: auto; min-height: 2700; height: 0; width: 770px;">
							<div
								style="width: 97%; height: 250px; float: left; margin-left: 10px; margin-top: 15px;">
								<div
									style="float: left; width: 730; margin-left: 10px; margin-top: 10px;">
									<div style="float: left; width: 100%;" class="tab_menu_2">
										<div style="float: right; width: 330px; height: 25px;"
											class="tab_menu_1" id="tab_menu_2">
											<div class="tab_menu_item_1_sel" id="0"
												onclick="room.changeTab(this.id)">
												<p class="item_menu2">
													<%=readerLang.getContent("Guest")%>
												</p>
											</div>
											<div class="tab_menu_item_1" id="1"
												onclick="room.changeTab(this.id)">
												<p class="item_menu2">
													<%=readerLang.getContent("Message")%>
												</p>
											</div>
											<div class="tab_menu_item_1" id="2"
												onclick="room.changeTab(this.id)">
												<p class="item_menu2">
													<%=readerLang.getContent("Bill")%>
												</p>
											</div>
											<div class="tab_menu_item_1" id="3"
												onclick="room.changeTab(this.id)">
												<p class="item_menu2">
													<%=readerLang.getContent("SetUp")%>
												</p>
											</div>
											<div class="tab_menu_item_1" id="4"
												onclick="room.changeTab(this.id)"  style="display: none;">
												<p class="item_menu2">Record</p>
											</div>
										</div>
									</div>
									<div
										style="float: left; width: 100%; background-color: white; height: 250; overflow: auto;"
										class="inforoom_center">
										<div style="overflow: scroll; width: 99%; height: 99%;">
											<div id="div_table2"></div>
										</div>

									</div>
									<div class="inforoom_bottom"></div>
								</div>
							</div>
						</div>
					</div>
					<div id="div_stb" style="display: none; height: 300px">
					
<!-- 					sd-------------------------------------------------------------------------------------- -->
						<div
							style="float: left; height: 25px; width: 50%; float: left; margin-top: 5px; margin-left: 25px;">
							<div style="float: left; width: 150px; height: 100%;"
								align="left">
								<input type="text" id="text_seachSTB" size="20"
									onkeyup="folio.seachSTB();">
							</div>
							<div style="float: left;">
								<img src="../icon/find-icon.png"
									style="margin-top: 5; cursor: pointer;"
									onclick="folio.seachSTB();"></img>
							</div>
						</div>
<!-- --------------------------------------------------------------------------------------------------------------- -->
					
						<div id="div_tablestb">
						</div>
					</div>
					<div id="div_record" style="display: none; height: 300px">
						<div id="div_tablerecord"></div>
					</div>
				</div>
				<div class="form_bottom" style="float: left;"></div>
			</div>
		</div>
	</div>
</div>

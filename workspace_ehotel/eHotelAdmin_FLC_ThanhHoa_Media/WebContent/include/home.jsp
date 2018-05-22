<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="ehotel.admin.util.Def"%>
<%@page import="ehotel.admin.util.ReaderLang"%>
<%@page import="ehotel.inter.ILOGIN"%>
<%@page import="ehotel.render.DBIGateway"%>
<jsp:include page="Paramter.jsp"></jsp:include>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	ReaderLang readerLang = new ReaderLang();
	String link1 = "";
	String link2 = "";
	String link4 = "";
	String link5 = "";
	String link3 = "";
	String link6 = "";
	if (session.getAttribute(Def._lang) != null) {
		readerLang = (ReaderLang) session.getAttribute(Def._lang);
	}
	String user = "";
	boolean login = false;
	if (session.getAttribute("user") != null) {
		user = session.getAttribute("user").toString();
		login = true;

		ILOGIN iuser = DBIGateway.getILogin();
		String ipAdress = request.getRemoteAddr();
		
		if (!iuser.isAdmin(ipAdress)) {
			try {
				if (iuser.checkRoleUser(ipAdress, "VOD$CONTENT")) {
					link1 = "Content/VodCtnMain?MenuId=1&SubId=1";
				}
				if (iuser.checkRoleUser(ipAdress, "MOD$CONTENT")) {
					link1 = "Content/ModCtnMain?MenuId=1&SubId=2";
				}
				if (iuser.checkRoleUser(ipAdress, "VOD$SERVICE")) {
					link2 = "Service/VodSrvMain?MenuId=2&SubId=5";
				}
				if (iuser.checkRoleUser(ipAdress, "MOD$SERVICE")) {
					link2 = "Service/ModSrvMain?MenuId=2&SubId=6";
				}
				if (iuser.checkRoleUser(ipAdress, "USER$MGN")) {
					link3 = "user/UserMgn?MenuId=3&SubId=9";
				}
				if (iuser.checkRoleUser(ipAdress, "SYSTEM$SERVICE")) {
					link3 = "system/ServiceSystem?MenuId=3&SubId=26";
				}
				if (iuser.checkRoleUser(ipAdress, "LIVETV$SERVICE")) {
					link4 = "Service/LiveTVMain?MenuId=2&SubId=7";
				}
				if (iuser.checkRoleUser(ipAdress, "FOLIO$PMS")) {
					link5 = "pms/folioPms?MenuId=4&SubId=11";
				}
				if (iuser.checkRoleUser(ipAdress, "HOTEL$PMS")) {
					link5 = "pms/hotelPms?MenuId=4&SubId=12";
				}
				if (iuser.checkRoleUser(ipAdress, "DINING$PMS")) {
					link5 = "pms/ServiceDinning?MenuId=4&SubId=13";
				}
				if (iuser.checkRoleUser(ipAdress, "ACTI$PMS")) {
					link5 = "pms/ServiceActi?MenuId=4&SubId=14";
				}
				if (iuser.checkRoleUser(ipAdress, "HOUSEKEEPING$PMS")) {
					link5 = "pms/SvcHouseKeeping?MenuId=4&SubId=18";
				}
				if (iuser.checkRoleUser(ipAdress, "MAP$PMS")) {
					link5 = "pms/MapDirection?MenuId=4&SubId=19";
				}
				if (iuser.checkRoleUser(ipAdress, "OTHER$PMS")) {
					link5 = "pms/ServiceOther?MenuId=4&SubId=1000";
				}
				if (iuser.checkRoleUser(ipAdress, "REPORT$VIDEO")) {
					link6 = "report/VideoReport?MenuId=5&SubId=21";
				}
				if (iuser.checkRoleUser(ipAdress, "REPORT$LIVETV")) {
					link6 = "report/LivetvReport?MenuId=5&SubId=1002";
				}
				
				// 				if (iuser.checkRoleUser(ipAdress, "VOD$SERVICE")) {
				// 					link1 = "Service/VodSrvMain?MenuId=2&SubId=5";
				// 				} else {
				// 					if (iuser.checkRoleUser(ipAdress, "VOD$CONTENT")) {
				// 						link1 = "Content/VodCtnMain?MenuId=1&SubId=1";
				// 					}
				// 				}
				// 				if (iuser.checkRoleUser(ipAdress, "MOD$SERVICE")) {
				// 					link2 = "Service/ModSrvMain?MenuId=2&SubId=6";
				// 				} else {
				// 					if (iuser.checkRoleUser(ipAdress, "MOD$CONTENT")) {
				// 						link2 = "Content/ModCtnMain?MenuId=1&SubId=2";
				// 					}
				// 				}
				// 				if (iuser.checkRoleUser(ipAdress, "SYSTEM$MENU")) {
				// 					link3 = "ServicePath?ID=3";
				// 				}
				// 				if (iuser.checkRoleUser(ipAdress, "PMS$MENU")) {
				// 					link5 = "ServicePath?ID=4";
				// 				}
				// 				if (iuser.checkRoleUser(ipAdress, "AUD$LIVETV")) {
				// 					link4 = "ServicePath?ID=2";
				// 				}
				// 				if (iuser.checkRoleUser(ipAdress, "LIVETV$SERVICE")) {
				// 					link4 = "ServicePath?ID=2";
				// 				}
				// 				if (iuser.checkRoleUser(ipAdress, "LIVETV$CONTENT")) {
				// 					link4 = "ServicePath?ID=2";
				// 				}
			} catch (Exception e) {
			}
		} else {
			link1 = "Content/VodCtnMain?MenuId=1&SubId=1";
			link2 = "Service/VodSrvMain?MenuId=2&SubId=5";
			link5 = "pms/folioPms?MenuId=4&SubId=11";
			link3 = "user/UserMgn?MenuId=3&SubId=9";
			link4 = "Service/LiveTVMain?MenuId=2&SubId=7";
			link6 = "report/VideoReport?MenuId=5&SubId=21";
		}
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>eHotelAdmin</title>
<script type="text/javascript" src="javascript/common/common.js"></script>
<script type="text/javascript" src="javascript/common/header.js"></script>
<script type="text/javascript" src="javascript/ajax.js"></script>
<script type="text/javascript" src="javascript/common/object.js"></script>
<script type="text/javascript" src="javascript/common/function.js"></script>
<script type="text/javascript" src="javascript/home.js"></script>
<link rel="stylesheet" href="css/home.css" type="text/css"></link>
<link rel="stylesheet" href="css/common.css" type="text/css"></link>
<link rel="stylesheet" href="css/main.css" type="text/css"></link>
<link rel="stylesheet" href="css/object.css" type="text/css"></link>
<style type="text/css">
input {
	font-family: sans-serif;
}
</style>
</head>
<body onkeydown="Common.onkeydown(event)"
	onkeyup="Common.onkeyup(event)">
	<div align="center" style="overflow: hidden; left: 0px;">
		<div class="main" align="center" id="main">
			<jsp:include page="../include/header.jsp"></jsp:include>
			<div class="menu_top" align="center"></div>
			<div class="content" style="margin-bottom: 10px; height: 400px; margin-top: -45px;"
				align="center">
				<%
					if (login) {
				%>
				<div style="width: 1024; height: 100%; margin-top: 30px;">
					<div
						style="float: left; width: 100%; height: 180px; margin-left: 40px; margin-top: 80px;">
						<div style="float: left; width: 300px; height: 100%;"
							class="link_service">
							<img src="icon/content.png" class="ic_home"
								onmouseout="mouseout(this)" onmouseover="mouseover(this)"
								onclick="gotoService('<%=link1%>');"></img>
							<div style="float: left; width: 100%;" class="text_service">
								<%=readerLang.getContent(16)%>
							</div>
						</div>
						<div style="float: left; width: 300px; height: 100%;"
							class="link_service">
							<img src="icon/service.png" class="ic_home"
								onmouseout="mouseout(this)" onmouseover="mouseover(this)"
								onclick="gotoService('<%=link2%>');"></img>
							<div style="float: left; width: 100%;" class="text_service">
								<%=readerLang.getContent(17)%>
							</div>
						</div>
						<div style="float: left; width: 300px; height: 100%;"
							class="link_service">
							<img src="icon/livetv.png" class="ic_home"
								onmouseout="mouseout(this)" onmouseover="mouseover(this)"
								onclick="gotoService('<%=link4%>');"></img>
							<div style="float: left; width: 100%;" class="text_service">
								<%=readerLang.getContent(20)%>
							</div>
						</div>
					</div>
					<div style="float: left; width: 100%; height: 180px; margin-top: 20px; margin-left: 40px; display: none;">
						<div style="float: left; width: 300px; height: 100%;"
							class="link_service">
							<img src="icon/system.png" class="ic_home"
								onmouseout="mouseout(this)" onmouseover="mouseover(this)"
								onclick="gotoService('<%=link3%>');"></img>
							<div style="float: left; width: 100%;" class="text_service">
								<%=readerLang.getContent(18)%></div>
						</div>
						<div style="float: left; width: 300px; height: 100%;"
							class="link_service">
							<img src="icon/pms.png" class="ic_home"
								onmouseout="mouseout(this)" onmouseover="mouseover(this)"
								onclick="gotoService('<%=link5%>');"></img>
							<div style="float: left; width: 100%;" class="text_service">
								<%=readerLang.getContent(21)%></div>
						</div>

						<div style="float: left; width: 300px; height: 100%;"
							class="link_service">
							<img src="icon/report.png" class="ic_home"
								onmouseout="mouseout(this)" onmouseover="mouseover(this)"
								onclick="gotoService('<%=link6%>');"></img>
							<div style="float: left; width: 100%;" class="text_service">
								<%=readerLang.getContent(19)%>
							</div>
						</div>

					</div>
				</div>
				<%
					} else {
				%>
				<form action="" method="post">
					<div style="width: 1024; height: 100%; margin-top: 30px;"
						align="center">
						<div
							style="width: 400px; height: 200px; border: 1px solid #dddddd; border-right: 1px solid #dddddd; border-bottom: 1px solid #dddddd;"
							class="form_login">
							<div style="float: left; width: 100%; height: 40px;" class="">
								<div class="icon_key"></div>
							</div>
							<div
								style="float: left; width: 100%; margin-top: 10px; height: 40px">
								<div
									style="float: left; width: 80px; height: 100%; margin-left: 30px; text-align: right;">
									<span style="width: 100px; text-align: right;"><%=readerLang.getContent("user")%>:</span>
								</div>
								<input type="text" style="float: left; margin-left: 5px;" name="user" size="30">
							</div>
							<div
								style="float: left; width: 100%; margin-top: 10px; height: 40px;">
								<div
									style="float: left; width: 80px; height: 100%; margin-left: 30px; text-align: right;">
									<span style="width: 100px;"><%=readerLang.getContent("password")%>:</span>
								</div>
								<input type="password" style="float: left; margin-left: 5px;" name="password"
									size="30">
							</div>
							<div style="float: right; width: 100%; margin-top: 10px;">
								<input type="submit" value="<%=readerLang.getContent("login")%>"
									style="height: 30px; width: 90px;">
							</div>
						</div>
					</div>
				</form>
				<%
					}
				%>
			</div>
		</div>
		<div class="footer" align="center">
			<div class="footer_center"></div>
		</div>
	</div>
</body>
</html>
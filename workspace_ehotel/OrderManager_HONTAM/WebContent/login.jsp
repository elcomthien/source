<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Manager</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-select.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap-select.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/TableTools.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery.dataTables.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/timetable.packed.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/login.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.tablednd.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.tabletools.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ZeroClipboard.js"></script>
</head>
<body>
	<div align="center" style="overflow: hidden; left: 0px;">
		<div class="main" align="center" id="main">
			<%@include file="header.jsp"%>
			<div class="content" style="margin-bottom: 10px; height: 450px;"
				align="center">
				<form action="checkin?action=checkin" method="post">
					<div style="width: 1024; height: 100%; margin-top: 100px;"
						align="center">
						<div
							style="width: 400px; height: 208px; border: 1px solid #dddddd; border-right: 1px solid #dddddd; border-bottom: 1px solid #dddddd;"
							class="form_login">
							<div style="float: left; width: 100%; height: 40px;" class="">
								<div class="icon_key"></div>
							</div>
							<div
								style="float: left; width: 100%; margin-top: 10px; height: 40px">
								<div
									style="float: left; width: 80px; height: 100%; margin-left: 50px;">
									Username :</div>
								<input type="text" style="float: left;" id="username" name="user" size="30">
								
							</div>
							<div
								style="float: left; width: 100%; margin-top: 10px; height: 40px;">
								<div
									style="float: left; width: 80px; height: 100%; margin-left: 50px;">
									Password :</div>
								<input type="password" style="float: left;" id="password" name="password"
									size="30">
							</div>
							<span id="spanerror" style="color: red; margin-left: 80px;"></span>
							<div style="float: right; width: 100%; margin-top: 5px;">
								<input type="submit" value="Login"
									style="height: 30px; width: 90px;" id="btnlogin" />
							</div>
						</div>
					</div>
				</form>
			</div>
			<input value="${message}" id="messagetemp" style="display: none;"/>

			<div class="footer" align="center">
				<div class="footer_center"></div>
			</div>
		</div>
	</div>
</body>
</html>
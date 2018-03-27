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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jNotify.jquery.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/newdining.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/newhousekeeping.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/newwakeup.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/newroomstatus.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/neworder.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.tablednd.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.tabletools.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ZeroClipboard.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jNotify.jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/util.js"></script>
</head>
<body>
	<div align="center" style="overflow: hidden; left: 0px;">
		<div class="main" align="center" id="main">
			<%@include file="header.jsp"%>
			<ul class="nav nav-tabs" style="display: none;">
				<li class="active" id="tabs-dining"><a href="#" id="a-dining">Dining</a></li>
				<li id="tabs-housekeeping"><a href="#" id="a-housekeeping">Housekeeping</a></li>
				<li id="tabs-wakeup"><a href="#" id="a-wakeup">Wakeup</a></li>
				<li id="tabs-room"><a href="#" id="a-room">Room status</a></li>
			</ul>
			<div style="margin-top: 10px;">
				<center>
					<marquee direction="left" scrollamount="2" width="10%"
						behavior="alternate" style="vertical-align: middle;">
						<span style="color: #778899; font-weight: bold;">Auto
							Reload</span>
					</marquee>
					<img src="img/right.gif" width="50px;" />
<!-- 					<button id="btniconfilter"> -->
<!-- 						<img src="img/icon_filter.png" width="30px;" /> -->
<!-- 					</button> -->
					<button id="btniconautoupdate" style="margin-left: 0px;">
						<img src="img/icon_stop.png" width="30px;" />
					</button>
					<img src="img/left.gif" width="50px;" />
					<marquee direction="right" scrollamount="2" width="10%"
						behavior="alternate" style="vertical-align: middle;">
						<span style="color: #778899; font-weight: bold;">Auto
							Reload</span>
					</marquee>
				</center>
			</div>
			<input id="tempidchecked" style="display: none;" value="" />
			<div id="divdatatable"></div>

			<hr style="margin-top: 50px; border-top: 2px solid #080000;" />

			<div id="divdatatablebottom"></div>
			<div class="footer" align="center" style="margin-top: 20px;">
				<!-- style="height: 60px;" -->
				<div class="footer_center"></div>
			</div>
		</div>
	</div>

	<div style="display: none;">
		<audio id="myAudio" controls> <source src="audio/sound.mp3"
			type="audio/mpeg"> Your browser does not support the audio
		element. </audio>
	</div>

	<!-- Modal filter -->
	<div id="modalfilter" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">x</button>
			<h3 id="myModalLabel">Filter Order</h3>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="span2" style="text-align: right;">Service:</div>
				<div class="span3">
					<input type="text" placeholder="Input service..." id="ftservice" />
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="span2" style="text-align: right;">Folio:</div>
				<div class="span3">
					<input type="text" placeholder="Input folio..." id="ftfolio" />
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" id="btnfilter">Filter</button>
		</div>
	</div>
</body>
</html>
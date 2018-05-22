<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<!-- Start Title -->
<title>
	<tiles:insertAttribute name="title" ignore="true" />
</title>
<!-- End Title -->
<!-- <link href="css/bootstrap.paper.min.css" rel="stylesheet" type="text/css"
	media="screen" /> -->
<link href="css/font-awesome.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/jquery-ui.min.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/styletable.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/default.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/user-manager.css" rel="stylesheet" type="text/css"
	media="screen" />
<!--Context menu on top-->

<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>


<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.numberic.min.js"></script>
<script type="text/javascript" src="js/default.js"></script>
<script type="text/javascript" src="js/user-manager.js"></script>
<!-- Create playlist-->
<script src="js/playlist.js" type="text/javascript"></script>

<!--Lock, Unlock UI-->
<script type="text/javascript" src="js/jquery.blockUI.js" ></script>

<!--Start Menu-->
<link href="css/menumaker.css" rel="stylesheet" type="text/css"
	media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menumaker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<!-- End Menu-->

<!--Context menu-->
<link href="css/jquery.contextMenu.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.contextMenu.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.js" type="text/javascript"></script>
<!--End context menu -->


<link href="css/jNotify.jquery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jNotify.jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<%-- <link type="image/png" rel="shortcut icon" href="<%=request.getContextPath()%>/css/images/a.png"/> --%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/css/images/ic-e.png">
</head>
<body id="bodyproject">
	<div id="wrapper">

		<!--StartHeader-->
			<tiles:insertAttribute name="header" ignore="true"/>
    	<!--EndHeader-->
    	
    	
		<!-- Start Logo -->
			<tiles:insertAttribute name="logo" ignore="true"/>
		<!-- End Logo -->
		<!-- Start Content -->
		<div id="page">
			<!-- Start Content -->
			<div id="content">
				<tiles:insertAttribute name="content"/>
			</div>
			<!-- End Content -->
			
			<!-- Start Left -->
			<div id="sidebar">
				<tiles:insertAttribute name="left" />
			</div>
			<!-- End Left -->
			<div style="clear: both;">&nbsp;</div>
			<!-- Start Content Detail (Can YES OR NO)-->
			<div id='detail-player'></div>
			<tiles:insertAttribute name="detail" ignore="true"/>
			<!-- End Content Detail -->
			<!--Dialog message box-->
			<div class='dialog-msg' style="display: none">
				<div>
					<h2 class="title title-dialog"></h2>
					<span class='dialog-msg-content'></span>
				</div>
			</div>	
			<!--Dialog message box-->
			<div class='dialog-msg-warning' style="display: none">
				<div>
					<h2 class="title title-dialog"></h2>
					<span class='dialog-msg-content'></span>
				</div>
			</div>
			<!-- Dialog message warning -->
			<div class="namegroup" style="display: none">
					<span>Đặt Tên Nhóm</span>
					<input required ='required' id="namegroup" style="width:90%;height:20px; margin:5px;" type="text" class="input-name" />
					<div style="float:left; margin:5px;">
						<a href="#" class="button-dialog" style="width:30px;" onclick="return saveGroupName()">Lưu</a>
					</div>
					<div style="float:left; margin:5px;">
							<a href="#" class="button-dialog" style="width:30px;" onclick="return unLockUI()">Đóng</a>
					</div>
			</div>
			<!-- show growl -->
			<div class="growlUI" style="display: none"></div>
		</div>
		<!-- End Content -->
		<div style="clear: both;">&nbsp;</div>
		<!-- Start footer -->
		<tiles:insertAttribute name="footer" ignore="true"/>
		<!-- End footer -->
	</div>
</body>
</html>
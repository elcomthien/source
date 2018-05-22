<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="css/bootstrap-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="css/bootstrap-select.css" rel="stylesheet" type="text/css"/>
	<link href="css/config-ftp-server.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/config-ftp-server.js"></script>
<!-- 	<link href="css/jNotify.jquery.css" rel="stylesheet" type="text/css"/> -->
<%-- 	<script type="text/javascript" src="js/jNotify.jquery.js"></script> --%>
<%-- 	<script type="text/javascript" src="js/util.js"></script> --%>
	<link type="image/png" rel="shortcut icon" href="<%=request.getContextPath()%>/css/a.png"/>
</head>
<div style="margin-top: -20px;">
	<h2 class="h2-title-config">Config Ftp server</h2>
	<div class="row" style="margin-top: 10px;">
		<div class="span4 div-text-config">
			Host FTP server:
		</div>
		<div class="span4">
			<input id="config-ftp-host" type="text" placeholder="input host ftp server ...">
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="span4 div-text-config">
			Port Ftp server:
		</div>
		<div class="span4">
			<input id="config-ftp-port" type="text" placeholder="input port ftp server ...">
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="span4 div-text-config">
			Username Ftp server:
		</div>
		<div class="span4">
			<input id="config-ftp-username" type="text" placeholder="input username ftp server ...">
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="span4 div-text-config">
			Password Ftp server:
		</div>
		<div class="span4">
			<input id="config-ftp-password" type="password" placeholder="input password ftp server ...">
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="span4 div-text-config">
			Src Ftp server:
		</div>
		<div class="span4">
			<input id="config-ftp-srcftp" type="text" placeholder="input src ftp server ...">
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="span4 div-text-config">
			Ip server:
		</div>
		<div class="span4">
			<input id="config-ftp-ipserver" type="text" placeholder="input ip server ...">
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="span4 div-text-config">
			Src server:
		</div>
		<div class="span4">
			<input id="config-ftp-srcserver" type="text" placeholder="input src server ...">
		</div>
	</div>
	<div class="row" style="margin-top: 10px; height: 20px;" id="config-alert-error">
		<div class="span2 div-text-config">
		</div>
		<div class="span4 alert alert-error"  style="height: 15px;">
			<span>
				<p id="config-text-error" style="text-align: center; font-size: 12px;"></p>
			</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; height: 30px;" id="config-div-button">
		<div class="span4" style="text-align: right;">
			<button id="config-ftp-btnconfig" class="btn btn-primary button-width-config">Config</button>
		</div>
		<div class="span4">
			<button id="config-ftp-btnreset" class="btn btn-inverse button-width-config">Reset</button>
		</div>
	</div>
</div>
<div style="clear: both;">&nbsp;</div>
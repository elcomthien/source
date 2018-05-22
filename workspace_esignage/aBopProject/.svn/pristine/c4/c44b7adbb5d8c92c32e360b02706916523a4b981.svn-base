<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<!-- Start Title -->
<title>Đăng Nhập</title>
<!-- End Title -->

<meta name="keywords" content="">
<meta name="description" content="">
<link href="css/style.css" rel="stylesheet" type="text/css"
	media="screen">
<link href="css/jquery-ui.min.css" rel="stylesheet" type="text/css"
	media="screen">
<link href="css/styletable.css" rel="stylesheet" type="text/css"
	media="screen">
<link href="css/default.css" rel="stylesheet" type="text/css"
	media="screen">
<link type="image/png" rel="shortcut icon"
	href="<%=request.getContextPath()%>/css/images/a.png" />
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>

</head>
<body style="margin-top: 15px">
	<div id="wrapper">
		<tiles:insertAttribute name="logo" ignore="true" />
		<div id="page">
			<section section class='login'> <span style="top: -20px; left: -50px;"><img src="css/images/es.png" width="200px;"></span>
<!-- 			<h1>ĐĂNG NHẬP</h1> -->
			<form action="login.elcom" method="post">
				<s:textfield placeholder='Tên đăng nhập' name="username"
					id='username' type="text" />
				<s:textfield placeholder='Mật khẩu' name="password" id='password'
					type="password" />
				<s:submit type="submit" value="ĐĂNG NHẬP" />
			</form>
			<h2 class='login-error'>
				<s:property value="msgError" />
			</h2>
			</section>
		</div>
	</div>
</body>
</html>
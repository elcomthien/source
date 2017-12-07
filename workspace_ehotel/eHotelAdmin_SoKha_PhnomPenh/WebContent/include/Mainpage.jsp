<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="ehotel.admin.util.Def"%>
<jsp:include page="../include/Paramter.jsp"></jsp:include>
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
<script type="text/javascript" src="../rte/richtext.js"></script>
<script type="text/javascript" src="../javascript/tiny_mce/tiny_mce.js"></script>
</head>
<%
 	String patht="";
 	
 	if(request.getAttribute("fileJSP")!=null)
 	{
 		patht=request.getAttribute("fileJSP").toString();
 	} 	
 	System.out.println(patht);
  %>
<body onkeydown="Common.onkeydown(event)"
	onkeyup="Common.onkeyup(event)">
	<div align="center" style="overflow: hidden; left: 0px;">
		<div class="main" align="center" id="main">
			<jsp:include page="../include/header.jsp"></jsp:include>
			<div class="menu_top" align="center">
				<jsp:include page="../include/MenuTop.jsp"><jsp:param
						value="<%=Def._ContentID %>" name="Path" />
					<jsp:param value="0" name="SubId" /></jsp:include>
			</div>
			<div class="content" style="margin-bottom: 10px;">
				<jsp:include page="<%=patht%>"></jsp:include>
			</div>
		</div>
		<div class="footer" align="center">
			<div class="footer_center"></div>
		</div>
	</div>
</body>
</html>

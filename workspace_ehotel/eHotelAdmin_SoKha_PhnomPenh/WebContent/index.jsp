<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
   		var url = '<%=basePath%>';
   		url+="ServiceHome?t=";
   		url+=Math.random();
		window.location = url;	  
    </script>
</head>
<body>
</body>
</html>

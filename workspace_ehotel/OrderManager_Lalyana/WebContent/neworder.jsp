<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<title>New order</title>
<script type="text/javascript">
$(document).ready(function() {
	var interval = setInterval(function() {
		this.window.close();
	}, 3000);
});
</script>
</head>
<body>
	<div style="text-align: center;">
		<img src="img/neworder.jpg" width="350px" height="350px;">
	</div>
</body>
</html>
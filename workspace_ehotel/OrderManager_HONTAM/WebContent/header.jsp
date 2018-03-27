
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

	String user = "Guest";
	boolean login = false;
	if (session.getAttribute("user") != null) {
		user = session.getAttribute("user").toString();
		login = true;
	}
%>
<div class="header" align="center">
	<div style="width: 1024px;" class="header_center">
		<div class="logo"></div>
		<div class="div_login" style="">
			<!-- 			<div style="float: right; width: 100%;" align="right"> -->
			<%-- 				<img src="<%=request.getContextPath()%>/img/flag_vn.png" width="20px;"
<%-- 					style="cursor: pointer;" onclick="changelang(1)"></img> --%>
			<%--<img 					src="<%=request.getContextPath()%>/img/flag_en.png" width="20px;" --%>
<%-- 					style="cursor: pointer;" onclick="changelang(2)"></img> --%>
			<!-- 			</div> -->
			<div style="float: right; margin-top: 30px;">
				<div style="float: left; margin-top: 30px;">
					<img src="<%=request.getContextPath()%>/img/icon_admin.png"></img>
				</div>
				<div
					style="color: #f2efb4; float: left; margin-top: 33px; margin-left: 10px;">
					<div style="float: left;">Hello</div>
					<%
						if (login) {
					%><div
						style="float: left; color: white; margin-left: 7px;"><%=user%>
						|
					</div>

					<div style="float: left; margin-left: 5px; color: white;">
						<a href="<%=request.getContextPath()%>/checkin?action=checkout"
							style="color: white;">Logout</a>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- nhung bien tham gia nhu language -->
<meta http-equiv="Cache-control" content="no-cache">
<%@ include file="../../include/ParamterPms.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>

<%
	Properties prop = new Properties();
	try {
		prop.load(getServletContext().getResourceAsStream(
				"/WEB-INF/philao.properties"));
	} catch (IOException ex) {
		ex.printStackTrace();
	}
	String iphost = prop.getProperty("ehotel.module.host");
%>
<script type="text/javascript">
var iphost='<%=iphost%>';
</script>
<script type="text/javascript"
	src="../javascript/system/ServiceMessage.js"></script>

<link rel="stylesheet" href="../css/folio.css" type="text/css"></link>
<link rel="stylesheet" href="../css/other.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pms.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pmsHotel.css" type="text/css"></link>
<!-- <link rel="stylesheet" href="../javascript/bootstrap/bootstrap.css" -->
<!-- 	type="text/css"></link> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="../javascript/bootstrap/bootstrap-select.css" type="text/css"></link> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="../javascript/bootstrap/bootstrap-responsive.css" type="text/css"></link> -->

<div style="width: 1024;">
	<div>
		<div class="left_content_top" style="margin-left: 0px;">Welcome
			Message Manager</div>
		<div class="left_content_bottom" style="margin-bottom: 20px;">
			<div class="grid">
				<div class="grid_header">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"></span>
					<div class="tab_menu" id="tab_menu"
						style="float: right; width: 130px;">
						<div class="tab_menu_item_sel" id="0"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;">Message</p>
						</div>
					</div>
				</div>
				<div class="grid_center">
					<div id="id_table" style="height: 525px;">
<!-- 							<div class='pms_name_input' style='width: 150px;'>Welcome -->
<!-- 								message</div> -->
							<div style="float: left; margin-left: 15px; margin-top: 10px;">
								<textarea rows="22" cols="30" name="descriptionInput"
									style="width: 100%; border: 1px solid #ddddd; margin-left: 5px; float: left;"
									id="descriptionInput"><%=request.getAttribute("mess")%>
  			 								</textarea>
							</div>
<%-- 							<%=request.getAttribute("mess")%> --%>
						<div
							style='width: 100%; float: left; margin-top: 15px; margin-bottom: 15px;'
							align='center'>
							<div align='center' class='div_sub_buton'>
								<input class='div_buton' style='width: 80px; margin-left: 50px;' type='button' value='Update'
									id='messupdate'>
							</div>
						</div>
					</div>
				</div>
				<div class="gird_bottom"></div>
			</div>
		</div>
	</div>
</div>
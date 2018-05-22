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

<script type="text/javascript" src="../javascript/system/ServiceBackground.js"></script>
<script type="text/javascript" src="../javascript/system/BackgroundVideo.js"></script>
<script type="text/javascript" src="../javascript/system/BackgroundType.js"></script>
<script type="text/javascript"
	src="../javascript/system/Background.js"></script>
<!-- su dung lai cac css cua other, boi vi dung lai khung cua PMS>Other>Promotion -->
<link rel="stylesheet" href="../css/other.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/system/pms.js"></script>
<link rel="stylesheet" href="../css/VodCtn.css" type="text/css"></link>
<div style="width: 1024;" >
	<div>
		<div class="left_content_top" style="margin-left: 0px;">System Background - Logo Manager</div>
		<div class="left_content_bottom" style="margin-bottom: 20px;">
			<div class="grid">
				<div class="grid_header">
					<span style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"></span>
					<div class="tab_menu" id="tab_menu" style="float: right; width: 330px;">
						<div class="tab_menu_item_sel" id="0"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;">Bg-Logo</p>
						</div>
						<div class="tab_menu_item" id="1"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;">Bg-Media</p>
						</div>
						<div class="tab_menu_item" id="2"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;">Bg-Type</p>
						</div>
					</div>
				</div>
				<div class="grid_center">
					<div id="id_table" style="height: auto;"></div>
					<div id="id_table_bg_video" style="height: 350px; display: none;"></div>
					<div id="id_table_bg_type" style="height: auto; display: none;"></div>
				</div>
				<div class="grid_bottom"></div>
			</div>
		</div>
	</div>
</div>
<!-- nhung bien tham gia nhu language -->
<%@ include file="../../include/ParamterPms.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>

<%
	Properties prop = new Properties();
	try {
		prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
	} catch (IOException ex) {
		ex.printStackTrace();
	}
	String iphost = prop.getProperty("ehotel.module.host");
%>
<script type="text/javascript">
var iphost='<%=iphost%>';
</script>

<script type="text/javascript" src="../javascript/system/sSystem.js"></script>
<script type="text/javascript"
	src="../javascript/system/CheckServiceDaemon.js"></script>
<script type="text/javascript"
	src="../javascript/system/ServiceSystem.js"></script>
<!-- su dung lai cac css cua other, boi vi dung lai khung cua PMS>Other>Promotion -->
<link rel="stylesheet" href="../css/other.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/system/pms.js"></script>

<div style="width: 1024;">
	<div>
		<div class="left_content_top" style="margin-left: 0px;">
			<%=readerLang.getContent("serviceSystem")%>
		</div>
		<div class="left_content_bottom" >
			<div class="grid">
				<div class="grid_header">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"></span>
					<div class="tab_menu" id="tab_menu" style="float: right; width: 110px;" >
						<div class="tab_menu_item_sel" id="0"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent("serviceSystem")%></p>
						</div>
						
						<div class="tab_menu_item" id="1" onclick="other.changeTab(this.id);" style="display: none;">
							<p style="margin-top: 5px;height: 20px;"><%=readerLang.getContent("daemon")%></p>
						</div>
						
					</div>
				</div>
				<div class="grid_center">
					<div id="id_table" style="height: auto;"></div>
				</div>
				<div class="grid_bottom"></div>
			</div>
		</div>
	</div>
</div>
<link rel="stylesheet" href="../css/pms.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/dinning.css" type="text/css"></link>
<%@ include file="../../include/ParamterPms.jsp"%>
<script type="text/javascript" src="../javascript/pms/pms.js"></script>
<script type="text/javascript" src="../javascript/pms/UserHouseKeeping.js"></script>
<script type="text/javascript" src="../javascript/pms/RoomStatus.js"></script>
<script type="text/javascript" src="../javascript/pms/Minibar.js"></script>
<script type="text/javascript" src="../javascript/pms/PMSHouseKeeping.js"></script>

<script type="text/javascript">
	var langDing={
		pls_inputprice:"<%=readerLang.getContent("pls_inputprice")%>",
		pls_price_small:"<%=readerLang.getContent("pls_price_small")%>",
		pls_price_large:"<%=readerLang.getContent("pls_price_large")%>"
	};
</script>
<div style="width: 1024;">
	<div class="left_content">
		<div class="left_content_top">
			Housekeeping Management
		</div>
		<div class="left_content_bottom">
			<div class="grid">
				<div class="grid_header">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"> </span>
					<div style="float: right; width: 330px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="0"
							onclick="hk.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;">User</p>
						</div>
						<div class="tab_menu_item" id="1"
							onclick="hk.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;">Minibar</p>
						</div>
						<div class="tab_menu_item" id="2"
							onclick="hk.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;">Status</p>
						</div>
					</div>
				</div>
				<div class="grid_center">
					<div id="id_table"
						style="height: auto; min-height: 250; height: 250"></div>
				</div>
				<div class="gird_bottom"></div>
			</div>
		</div>
	</div>
</div>

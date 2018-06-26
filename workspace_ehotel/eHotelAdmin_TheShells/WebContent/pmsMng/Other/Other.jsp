<script type="text/javascript" src="../javascript/pms/advertise.js"></script>
<script type="text/javascript" src="../javascript/pms/activiti.js"></script>
<script type="text/javascript" src="../javascript/pms/exchange.js"></script>
<script type="text/javascript" src="../javascript/pms/promotion.js"></script>
<script type="text/javascript" src="../javascript/pms/Other.js"></script>
<%@ include file="../../include/ParamterPms.jsp"%>

<link rel="stylesheet" href="../css/other.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pmsHotel.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/pms/pms.js"></script>

<div style="width: 1024;">
	<div>
		<div class="left_content_top" style="margin-left: 0px;">
			<%=readerLang.getContent("OtherMgn") %>
		</div>
		<div class="left_content_bottom">
			<div>
				<div class="form_header">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"> </span>
					<div style="float: right; width: 350px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="0"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent("Promotions") %></p>
						</div>
						<div class="tab_menu_item" id="1"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("ExchangeRate") %></p>
						</div>
						<div class="tab_menu_item" id="2"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("Advertise") %></p>
						</div>
					</div>
				</div>
				<div class="form_center">
					<div id="id_table"
						style="height: auto; min-height: 350; height: 350"></div>

				</div>
				<div class="form_bottom"></div>
			</div>

		</div>

	</div>

</div>

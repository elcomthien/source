<link rel="stylesheet" href="../css/pms.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pmsHotel.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/pms/pms.js"></script>
<script type="text/javascript" src="../javascript/pms/pmsOutletPrices.js"></script>
<%@ include file="../../include/ParamterPms.jsp"%>
<div style="width: 1024;">
	<div class="left_content">
		<div class="left_content_top">
			<%=readerLang.getContent("HotelOutlet") %>
		</div>
		<div class="left_content_bottom">
			<div class="grid">
				<div class="grid_header">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"> </span>
					<div style="float: right; width: 150px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="1"
							onclick="Mod.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("Outlet") %></p>
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
	<div class="content_right" align="center">
		<div class="menu" style="margin-top: 25px;" id="menu">
			<div class="menu_header">
				<div
					style="float: left; color: white; font-size: 20px; margin-left: 20px; margin-top: 15px; font-weight: bold;">
					<%=readerLang.getContent("Subject") %>
				</div>
			</div>
			<div class="menu_center" align="center">
				<div id='menutree'></div>
				<div>
					<a href="javascript:hotel.sub.addSubject(-1);"
						class="div_addeImage"> <%=readerLang.getContent("AddSubject") %>
					</a>
				</div>
			</div>
			<div class="menu_bottom"></div>
		</div>
	</div>
</div>

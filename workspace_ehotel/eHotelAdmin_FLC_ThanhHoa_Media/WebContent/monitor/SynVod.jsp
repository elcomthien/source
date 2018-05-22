<%@ include file="../include/ParamterPms.jsp"%>
<link rel="stylesheet" href="../css/addvodstb.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/monitor/AddVod.js"></script>
<link rel="stylesheet" href="../css/pms.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pmsHotel.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/monitor/synvod.js"></script>
<script type="text/javascript"> 	
</script>
<div style="width: 1024;">
	<div class="left_content">
		<div class="left_content_top">
			<%=readerLang.getContent("SynVodStb") %>
		</div>

		<div class="left_content_bottom">
			<div class="grid">
				<div class="grid_header">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"> <%=readerLang.getContent("listaddvod") %>
					</span>
					<div style="float: right; width: 230px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="0"
							onclick="synvod.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;">VOD</p>
						</div>
						<div class="tab_menu_item" id="1"
							onclick="synvod.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;">Add</p>
						</div>
					</div>
				</div>
				<div class="grid_center">
					<div id="id_table1"
						style="height: auto; min-height: 250; height: 380; display: block;">
						<div id="id_listvod"
							style="height: 350px; width: 100%; float: left;"></div>
					</div>
					<div id="id_table2"
						style="height: auto; min-height: 250; height: 610; display: none;"
						align="center">
						<div style="float: left; width: 100%; height: 30px;">
							<div
								style="float: left; font-weight: 600; height: 20px; margin-top: 5px;">
								Set-Top-Box:</div>
							<div id="div_setopbox"
								style="height: 20px; float: left; margin-left: 3px; margin-top: 5px;">

							</div>
						</div>
						<div id="id_table"
							style="height: 350px; overflow: hidden; width: 100%; float: left;">

						</div>
						<div style="width: 300; height: 30px;">
							<input type="button" value=" Upload" class="div_buton"
								id="div_buton"/ > <input type="button"
								value=" Upload All" id="div_butonall"/ >
						</div>
						<div
							style="float: left; width: 100%; height: 20px; margin-top: 15px; font-weight: 600">
							<div id='titletable3'>
								<%=readerLang.getContent("listprocess")%>
							</div>

						</div>
						<div
							style="float: left; width: 98%; height: 100px; border: 1px solid #dddddd; margin-left: 6px;"
							id="div_process"></div>

					</div>
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
				<div id='menutree'
					style="height: 300px; overflow: hidden; overflow-y: scroll"></div>
			</div>
			<div class="menu_bottom"></div>
		</div>
	</div>
</div>

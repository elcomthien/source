<script type="text/javascript" src="../javascript/pms/advertise.js"></script>
<script type="text/javascript" src="../javascript/pms/activiti.js"></script>
<script type="text/javascript" src="../javascript/pms/exchange.js"></script>
<script type="text/javascript" src="../javascript/pms/promotion.js"></script>
<script type="text/javascript" src="../javascript/pms/settimepro.js"></script>
<script type="text/javascript" src="../javascript/pms/Other.js"></script>
<%@ include file="../../include/ParamterPms.jsp"%>

<link rel="stylesheet" href="../css/pms.css" type="text/css"></link>
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
					<div style="float: right; width: 230px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="0"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent("Promotions") %></p>
						</div>
						<div class="tab_menu_item" id="1"
							onclick="other.changeTab(this.id);" style="display: none;">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("ExchangeRate") %></p>
						</div>
						<div class="tab_menu_item" id="2"
							onclick="other.changeTab(this.id);" style="display: none;">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("Advertise") %></p>
						</div>
						<div class="tab_menu_item" id="3"
							onclick="other.changeTab(this.id);" style="display: none;">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("Internet") %></p>
						</div>
						<div class="tab_menu_item" id="4"
							onclick="other.changeTab(this.id);" style="display: none;">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("Feedback") %></p>
						</div>
						<div class="tab_menu_item" id="5"
							onclick="other.changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;">Set Time</p>
						</div>
					</div>
				</div>
				<div class="form_center">
					<div id="id_table" style="height: auto; min-height: 350; height: 350"></div>
					<div id="div_set_time" style="height: auto; min-height: 200; height: 200; display: none; margin-left: 80;">
						
						<div style='float: left; margin-left: 100px; border: 0px solid #dddddd; width: 660px; height: auto; margin-top: 20px;'>
							<div class='pms_textbox_input' style='margin-top: 8px;'>
								<div class='pms_name_input' style='width: 500px; font-size: 30px; text-align: center;'>Config time for promotion</div>
							</div>
						</div>
						
						<div style='float: left; margin-left: 100px; border: 0px solid #dddddd; width: 660px; height: auto; margin-top: 20px;'>
							<div class='pms_textbox_input' style='margin-top: 8px;'>
								<div class='pms_name_input' style='width: 180px;'>Time show promotion:</div>
								<input type='text' size='26'
									style='border: 1px solid #dddddd; height: 24; float: left; width: 200px; margin-top: 5px; text-align: center;'
									id='time_show' name='time_show' onkeypress="validate(event);"/>
								<div class='pms_name_input' style='width: 200px;'>seconds</div>
							</div>
							
							<div class='pms_textbox_input' style='margin-top: 8px;'>
								<div class='pms_name_input' style='width: 180px;'>Time hide promotion:</div>
								<input type='text' size='26'
									style='border: 1px solid #dddddd; height: 24; float: left; width: 200px; margin-top: 5px; text-align: center;'
									id='time_hide' name='time_hide' onkeypress="validate(event);"/>
								<div class='pms_name_input' style='width: 200px;'>seconds</div>
							</div>
						</div>
						<div style='width: 100%; float: left; margin-top: 15px; margin-bottom: 15px;' align='center'>
							<div class='div_sub_buton' style="width: 100px;" align="left">
								<input style='width: 80px;' type='button' value='Config' id='set_time_config' class='div_buton'>
							</div>
						</div>
					</div>
				</div>
				<div class="form_bottom"></div>
			</div>
		</div>
	</div>
</div>

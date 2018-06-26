<%@ include file="../../include/Paramter.jsp"%>
<div class="form_detail_prom" id="form_detail" style="height: 375">

	<div class='div_Title_prom' align="left">
		<div style="float: left;">
			<%=readerLang.getContent("addSTBroom") %>
		</div>
		<div
			style="float: right; height: 25px; width: 25px; margin-right: 15px; cursor: pointer; margin-top: 3px;"
			id="buton_cancel">
			<img src="../icon/close.png"></img>
		</div>
	</div>
	<div
		style="float: left; margin-left: 10px; width: 570px; margin-top: 20px;">
		<div class="add_stb_top" style="float: left;"></div>
		<div style="float: left; width: 100%; height: 265px;"
			class="add_stb_center" style="float: left;">
			<div
				style="float: left; height: 25px; width: 50%; float: left; margin-top: 5px; margin-left: 25px;">
				<div style="float: left; width: 150px; height: 100%;" align="left">
					<input type="text" id="text_seachSTB" size="20"
						onkeyup="folio.seachSTB();">
				</div>
				<div style="float: left; margin-left: 4px">
					<img src="../icon/find-icon.png"
						style="margin-top: 5; cursor: pointer;"
						onclick="folio.seachSTB();"></img>
				</div>
			</div>
			<div style="float: left; width: 100%; height: 220px;" id="listaddstb"
				align="center"></div>
			<div style="width: 100%; float: left; text-align: center;">
				(<%=readerLang.getContent("dbclickaddSettopbox") %>)
			</div>
		</div>
		<div class="add_stb_bottom" style="float: left;"></div>
		<div style="width: 100%; float: left; margin-top: 5px;" align="center">
		</div>
	</div>
</div>
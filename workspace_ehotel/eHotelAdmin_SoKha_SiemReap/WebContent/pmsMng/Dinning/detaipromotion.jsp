<%@page import="ehotel.domain.pms.eItem"%>
<%@ include file="../../include/Paramter.jsp"%>

<div class="form_detail_dinning" id="form_detail_dinning"
	style="height: 430px">
	<div class='div_Title' align="left"><%=readerLang.getContent("Promotions")%>
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 570px; margin-top: 20px;">
		<div style="float: left; width: 350; height: 140px;">
			<div class='pms_textbox_input' style='margin-top: 2px;'>
				<div class='pms_name_input'><%=readerLang.getContent("Name") %>:
				</div>
				<input type="text" size="26"
					style="border: 1px solid #dddddd; height: 22; float: left; width: 220; margin-top: 5px;"
					id="name" value="" />
			</div>
			<div class='pms_textbox_input' style='margin-top: 4px;'>
				<div class='pms_name_input' style="margin-top: 0"><%=readerLang.getContent("oldprice") %>:
				</div>
				<input type="text" size="26"
					style="border: 1px solid #dddddd; height: 22; float: left; width: 155;"
					id="price" onkeypress="return isNumberKey(event)"
					value="" />
				<div style="margin-left: 10px;">
					<select id="div_money0" style="height: 22; width: 65;">
						<option value="USD" selected='selected' >USD</option>
					</select>
				</div>
			</div>
			<div class='pms_textbox_input' style='margin-top: 2px;'>
				<div class='pms_name_input' style="margin-top: 0"><%=readerLang.getContent("newprice") %>:
				</div>
				<input type="text" size="26"
					style="border: 1px solid #dddddd; height: 22; float: left; width: 220;"
					id="sale" onkeypress="return isNumberKey(event)"
					value="" />
<!-- 				<div style="margin-left: 10px;"> -->
<!-- 					<select id="div_money1" style="height: 22; width: 65;"> -->
<!-- 						<option value="USD" selected='selected' >USD</option> -->
<!-- 						<option value="VND">VND</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
			</div>
			
			<div class='pms_textbox_input' style='margin-top: 2px;'>
				<div class='pms_name_input' style="margin-top: 0px;"><%=readerLang.getContent("Image") %>:
				</div>
				<div style="width: 220px; float: left; height: 30px;">
					<iframe class='frame_upload' src="../upload" style='float: left;'
						valign="top" align="left" scrolling="no" frameborder='0'
						width="101%" height="40px"></iframe>
				</div>
			</div>
			<div class='pms_textbox_input' style="height: 20px;">
				<div class='pms_name_input' style="height: 20px;"><%=readerLang.getContent("Description") %>:
				</div>
			</div>
		</div>
		<div style="float: left; height: 140px; width: 200px;">
			<div
				style="float: left; width: 180px; height: 120px; border: 1px solid #dddddd; margin-left: 20px; margin-top: 5px;">
				<img id='image' src="" width='180'
					height='120'></img>
			</div>
		</div>
		<div style='width: 100%; height: 200; float: left;'>
			<div style="float: left; margin-left: 10px;">
				<textarea rows="12" cols="10" name="descriptionInput"
					style="width: 550px; border: 1px solid #ddddd; margin-left: 5px; float: left;"
					id="descriptionInput">
  			 </textarea>
			</div>
		</div>
	</div>
	<div style="width: 100%; float: left; margin-top: 5px;" align="center">
		<div align='left' class='div_sub_buton'>
			<input style='' type="button"
				value='<%=readerLang.getContent("OK")%>' id="form_textbox_ok"
				class='div_buton'> <input type="button"
				value="<%=readerLang.getContent("Cancel")%>"
				id="form_textbox_cancel" class='div_buton'>
		</div>
	</div>
</div>
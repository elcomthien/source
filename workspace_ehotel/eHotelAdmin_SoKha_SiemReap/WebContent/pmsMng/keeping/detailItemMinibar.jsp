<div class="form_detail_dinning" style="height: 215px; width: 375px;" id="form_detail_item">
	<div class='div_Title' align="left">Minibar Item
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 350px; margin-top: 10px;">
		<div style="float: left; width: 350; height: 120px;">
			<div class='pms_textbox_input' style='margin-top: 2px;'>
				<div class='pms_name_input'>Name(<font color="red">*</font>):
				</div>
				<input type="text" size="26"
					style="border: 1px solid #dddddd; height: 22; float: left; width: 220; margin-top: 5px;"
					id="name" value="" />
			</div>
			<div class='pms_textbox_input' style='margin-top: 2px;'>
				<div class='pms_name_input'>Code(<font color="red">*</font>):
				</div>
				<input type="text" size="26"
					style="border: 1px solid #dddddd; height: 22; float: left; width: 220; margin-top: 5px;"
					id="code" value="" />
			</div>
			<div class='pms_textbox_input' style='margin-top: 2px;'>
				<div class='pms_name_input'>Amount:
				</div>
				<input type="text" size="26"
					style="border: 1px solid #dddddd; height: 22; float: left; width: 220; margin-top: 5px;"
					id="amount" value="" onkeypress="return isNumberKey(event)"/>
			</div>
			<div class='pms_textbox_input' style='margin-top: 2px;'>
				<div class='pms_name_input'>Price:
				</div>
				<input type="text" size="26"
					style="border: 1px solid #dddddd; height: 22; float: left; width: 220; margin-top: 5px;"
					id="price" value="" onkeypress="return isNumberKey(event)"/>
			</div>
		</div>
	</div>
	<div style="height: 20; width: 420px; margin-left: 10px">
			<span style="color: red; width: 60%; display: none;" id="span-error-user">error</span>
		</div>
	<div style="width: 100%; float: left; margin-top: 20px;" align="center">
		<div align='left' class='div_sub_buton' style="margin-left: 150;">
			<input style='' type="button" value='Ok' id="form_textbox_ok" class='div_buton'/>
			<input type="button" value="Cancel" id="form_textbox_cancel" class='div_buton' />
		</div>
	</div>
</div>
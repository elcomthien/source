<div class='form_detail_hotel' id='formcharge'
	style="width: 280px; height: auto;">
	<div class='div_Title' align='left'>Edit Unique Charge</div>
	<div style='float: left; margin-left: 10px; border: 1px solid #dddddd; width: 260px; height: 80px; margin-top: 20px;'>
		
		<div class='pms_textbox_input' style='margin-top: 8px;'>
			<div class='pms_name_input' style='width: 110px;'>Old charge: </div>
			<input type='text' size='26'
				style='border: 1px solid #dddddd; height: 24; float: left; width: 100px; margin-top: 5px;'
				id='old_charge' readonly="readonly"/> 
			<div class='pms_name_input' style='width: 30px;'>USD</div>
				
		</div>
		
		<div class='pms_textbox_input' style='margin-top: 8px;'>
			<div class='pms_name_input' style='width: 110px;'>New charge: </div>
			<input type='text' size='26'
				style='border: 1px solid #dddddd; height: 24; float: left; width: 100px; margin-top: 5px;'
				id='new_charge' placeholder="new charge ..." onkeypress="return isNumber(event)" />
			<div class='pms_name_input' style='width: 30px;'>USD</div>
		</div>
	</div>
	<div
		style='width: 100%; float: left; margin-top: 15px; margin-bottom: 15px;'
		align='center'>
		<div align='left' class='div_sub_buton'>
			<input style='width: 80px;' type='button' value='OK' id='chargeok'
				class='div_buton'> <input type='button' style='width: 80px;'
				value='Cancel' id='chargecancel' class='div_buton'>
		</div>
	</div>
</div>
<div class='form_detail_hotel' id='formcheckin'
	style="width: 720px; height: auto;">
	<div class='div_Title' align='left'>Folio</div>
	<div
		style='float: left; margin-left: 10px; border: 1px solid #dddddd; width: 700px; height: auto; margin-top: 20px;'>
		<div id="checkinparentdiv">
			<div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input'>First name</div>
				<input type='text' size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px; margin-left: -20px;'
					id='checkinfirstname1' placeholder="First Name..." />
				<div class='pms_name_input'>Last name</div>
				<input type='text' size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px; margin-left: -20px;'
					id='checkinlastname1' placeholder="Last Name..." />
				<div class='pms_name_input' style='width: 40px;'>Sex</div>
				<select size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px;'
					id='checkinsex1'>
					<option value="Mr." selected='selected'>Mr</option>
					<option value="Mrs.">Mrs</option>
					<option value="Ms.">Ms</option>
				</select> 
<!-- 				<select> -->
<!-- 					<option>1</option> -->
<!-- 					<option>2</option> -->
<!-- 					<option>3</option> -->
<!-- 					<option>4</option> -->
<!-- 				</select> -->
			</div>
		</div>

		<div class='pms_textbox_input' style='margin-top: 8px;'>
			<div class="pms_name_input">Check-in date</div>
			<div id="datebddiv" style="margin-top: 5px;">
				<span
					style="border: solid 0px; padding: 9px 1px 5px 7px; padding-bottom: 10px;">
					<input type="text" value="" id="datebdday"
					name="datebdday" class="span1 datemask"
					style="width: 30px; border: none;"> -<input type="text"
					value="" id="datebdmonth" name="datebdmonth"
					class="span1 datemask" style="width: 30px; border: none;">
					-<input type="text" value="" id="datebdyear"
					name="datebdyear" class="span1 datemask"
					style="width: 50px; border: none;">
				</span>
				<span style="border: solid 0px; padding: 9px 1px 5px 1px; padding-bottom: 10px; margin-top: 10px;">
					<span id="2leftbd" class="icon-large icon-fast-backward"
					style="margin-top: 0px;"></span> <span id="1leftbd"
					class="icon-large icon-chevron-left" style="margin-top: 0px;"></span>
					<span id="1rightbd" class="icon-large icon-chevron-right"
					style="margin-top: 0px;"></span> <span id="2rightbd"
					class="icon-large icon-fast-forward" style="margin-top: 0px;"></span>
				</span> 
				
				
				<input style='width: 50px; float: right; margin-right: 10px;'
					type='button' value='.....' id='checkinaddguest' class='div_buton'>
					<input id="checkinamount" value = '1' style="display: none;"/>
			</div>

		</div>
		<div class='pms_textbox_input'
			style='margin-top: 8px; margin-bottom: 10px;'>
			<div class="pms_name_input">Check-out date</div>
			<div id="ktdatektdiv" style="margin-top: 5px;">
				<span
					style="border: solid 0px; padding: 9px 1px 5px 7px; padding-bottom: 10px;">
					<input type="text" value="" id="ktdatektday"
					class="span1 datemask" style="width: 30px; border: none;">
					-<input type="text" value="" id="ktdatektmonth"
					class="span1 datemask" style="width: 30px; border: none;">
					-<input type="text" value="" id="ktdatektyear"
					class="span1 datemask" style="width: 50px; border: none;">
				</span> 
				<span style="border: solid 0px; padding: 9px 1px 5px 1px; padding-bottom: 10px;">
					<span id="kt2leftkt" class="icon-large icon-fast-backward"
					style="margin-top: 0px;"></span> <span id="kt1leftkt"
					class="icon-large icon-chevron-left" style="margin-top: 0px;"></span>
					<span id="kt1rightkt" class="icon-large icon-chevron-right"
					style="margin-top: 0px;"></span> <span id="kt2rightkt"
					class="icon-large icon-fast-forward" style="margin-top: 0px;"></span>
				</span>
			</div>
		</div>
	</div>
	<div
		style='width: 100%; float: left; margin-top: 15px; margin-bottom: 15px;'
		align='center'>
		<div align='left' class='div_sub_buton'>
			<input style='width: 80px;' type='button' value='OK' id='checkinok'
				class='div_buton'> <input type='button' style='width: 80px;'
				value='Cancel' id='checkincancel' class='div_buton'>
		</div>
	</div>
	<select size='26' style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px;' id='checkinsex1'>
		<option value="Mr." selected='selected'>Mr</option>
		<option value="Mrs.">Mrs</option>
		<option value="Ms.">Ms</option>
	</select>
</div>
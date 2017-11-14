<div class='form_detail_hotel' id='formcheckin'
	style="width: 720px; height: auto;">
	<div class='div_Title' align='left'>Folio</div>
	<div style='float: left; margin-left: 10px; border: 1px solid #dddddd; width: 700px; height:auto; min-height:120px; margin-top: 20px;'>
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
			</div>
		</div>

		<div class='pms_textbox_input' style='margin-top: 8px;'>
<!-- 			<div class="pms_name_input">Check-in date</div> -->
<!-- 			<div id="datebddiv" style="margin-top: 5px;"> -->
<!-- 				<input type="date" style="height: 30px;" id="date-checkin"> -->
				<input style='width: 50px; float: right; margin-right: 10px;'
					type='button' value='.....' id='checkinaddguest' class='div_buton'>
					<input id="checkinamount" value = '1' style="display: none;"/>
		</div>
		<div class='pms_textbox_input' style='margin-top: 8px; margin-bottom: 20px;'>
			<div class="pms_name_input">Check-in date</div>
			<input type="date" id="date-checkin" style="border: 1px solid #dddddd; height: 30px; float: left; width: 170px;">
			<div class="pms_name_input" style="margin-left: 110px;">Check-out date</div>
			<input type="date"  id="date-checkout" style="border: 1px solid #dddddd; height: 30px; float: left; width: 170px;">
		</div>
	</div>
		
	<div style='width: 100%; float: left; margin-top: 15px; margin-bottom: 15px;' align='center'>
		<div align='left' class='div_sub_buton'>
			<input style='width: 80px;' type='button' value='OK' id='checkinok'
				class='div_buton'> <input type='button' style='width: 80px;'
				value='Cancel' id='checkincancel' class='div_buton'>
		</div>
	</div>
</div>
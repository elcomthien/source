<%@ include file="../../include/Paramter.jsp"%>
<div class="formAddMovie">
	<div
		style="border: 1px solid #dddddd; height: 360px; float: left; margin-top: 5px; margin-left: 5px;">
		<div
			style="width: 330px; height: 300px; float: left; margin-top: 5px;">
<!-- 			<div style="float: left; width: 100%"> -->
<!-- 				<span style="float: left; margin-left: 10px;">Input FTP Server:</span> -->
<!-- 			</div> -->
<!-- 			<div style="float: left;"> -->
<!-- 				<input type="text" placeholder="Host..." id="ctaddhost" -->
<!-- 					style="width: 47%" /> <input type="text" placeholder="Port..." -->
<!-- 					id="ctaddport" style="width: 47%"/> -->
<!-- 			</div> -->
<!-- 			<div style="float: left;"> -->
<!-- 				<input type="text" placeholder="UserName..." -->
<!-- 					id="ctaddusername" style="width: 44%; margin-left: 3px;"/>  -->
<!-- 					<input type="password" placeholder="Pass..." -->
<!-- 					id="ctaddpassword" style="width: 22%;"/> -->
<!-- 					<button id="ctnadd_btn_connect" -->
<!-- 					style="float: right; margin-right: 28px;" onclick="loadFTPServer()">Connect</button> -->
<!-- 			</div> -->
			
<!-- 			<input id="hosttemp" style="display: none;"/> -->
<!-- 			<input id="porttemp" style="display: none;"/> -->
<!-- 			<input id="usertemp" style="display: none;"/> -->
<!-- 			<input id="passtemp" style="display: none;"/> -->
			
			

			<div class="ctnaddlistfile" style="">
				<ul id="ul_ftp_file" class="ul_ftp_file" style="width: 400px">
				</ul>
			</div>
			<div style="width: 100%; float: left; color: #878888;">
				(<%=readerLang.getContent("DoubleClickToAdd")%>)
			</div>
			<div
				style="float: left; width: 100%; text-align: left; height: 25px; margin-top: 10px;">

				<div style="float: left; width: 40px; color: #878888;">
					<%=readerLang.getContent("Name")%>:
				</div>
				<input id="ctnaddfilenametemp" value="" style="display: none;" />
				<input id="ctnaddfilepathtemp" value="" style="display: none;" />
				<div id="fileName"
					style="width: 80%; float: left; overflow: hidden; margin-left: 3px;"></div>
			</div>

		</div>
		<div style="width: 350px; height: auto; float: left; margin-top: 5px;">
			<div style="float: left; height: 30; width: 100%;">
				<div style="float: left; width: 100%; height: 30px;">
					<div style="float: left;"><%=readerLang.getContent("Subject")%>:
					</div>
					<div style="float: left; color: #878888; margin-left: 40px;"
						id="div_subjectname"><%=readerLang.getContent("Actors")%></div>
				</div>
			</div>
			<div style="float: left; margin-top: 0px;"><%=readerLang.getContent("Image")%>
				:
			</div>
			<div
				style="width: 245px; height: 120; float: right; margin-right: 10px; border: 1px solid #dddddd;">

				<img id="image1" src="../resource/images/noimage.gif" width="100%"
					height="100%"></img>
			</div>
			<div class="div_row_right_ctn" style="margin-top: 20px;">
				<div class="div_left_ctnadd" style="">
					<%=readerLang.getContent("FilePath")%>
					:
				</div>
				<div style="float: left; width: 250px; margin-left: 10px;">
					<iframe class='frame_upload' src="../upload?image=image1"
						style='overflow-x: hidden; overflow-y: hidden; overflow: hidden; float: left;'
						valign="top" scrolling="no" frameborder='0' width="100%"
						height="40px"></iframe>
				</div>
			</div>
			<div class="div_row_right_ctn" style="">

				<div style="width: 80px; float: left;" class="div_left_ctnadd">
					<%=readerLang.getContent("Title")%>
					:
				</div>
				<div
					style="float: left; border: 1px solid #dddddd; margin-left: 10px; width: 250px; background-color: white;"
					align="left">
					<input id="div_title" type="text" size="150" class="textbox_input"
						style="float: left; width: 215px; margin-left: 0; border-style: none;" />
					<div
						style="width: 20px; float: right; background-color: white; height: 20px;">
						<img src="../icon/drop.png" id="im_loadInfoFilm"
							title="Click get info movie from imd"></img>
					</div>
				</div>

			</div>
			<div class="div_row_right_ctn">
				<div style="width: 80px; float: left" class="div_left_ctnadd"><%=readerLang.getContent("Actors")%>
					:
				</div>
				<div style="width: 80px; float: left">
					<input id="div_actors" type="text" class="textbox_input"
						style="float: left; width: 250px; margin-left: 10;">
				</div>
			</div>
			<div class="div_row_right_ctn">
				<div style="width: 80px; float: left;" class="div_left_ctnadd"><%=readerLang.getContent("Director")%>
					:
				</div>
				<div style="width: 80px; float: left">
					<input id="div_Director" type="text" class="textbox_input"
						style="float: left; width: 250px; margin-left: 10;">
				</div>
			</div>
			<div class="div_row_right_ctn">
				<div style="width: 80px; float: left;" class="div_left_ctnadd"><%=readerLang.getContent("Price")%>
					:
				</div>
				<div style="width: 192px; float: left">
					<input id="div_price" type="text" class="textbox_input"
						style="float: left; width: 160px; margin-left: 10;"
						onkeypress="return isNumberKey(event)">
				</div>
				<div style="float: right; margin-right: 10px;">
					<select id="div_money">
						<option value="USD">USD</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div style="width: 100%; height: auto; float: left; margin-top: 20px;">
		<div style="float: left; height: 30px; margin-left: 10px"
			class="div_left_ctnadd"><%=readerLang.getContent("Description")%></div>
		<div style="float: left; margin-left: 20px;">
			<div style="float: left; margin-left: 10px;">
				<textarea rows="11" cols="9" name="descriptionInput"
					style="width: 485px; border: 1px solid #ddddd; margin-left: 5px; float: left;"
					id="descriptionInput">		  				
		  			 </textarea>
			</div>
		</div>
	</div>

	<div style="width: 100%; height: auto; float: left; margin-top: 10px;"
		align="center">
		<div style="width: 160; height: 30px;">
			<input type="button" value='<%=readerLang.getContent("OK")%>'
				class='div_buton' id="ctn_button_ok"> <input type="button"
				value='<%=readerLang.getContent("Cancel")%>' class='div_buton'
				id="ctn_button_Cancel">
		</div>
		
		<div style="width: 100%; float: left; margin-top: 10px;"
			align="center">
			<div id="div_process" style="width: 100%;"></div>
		</div>
	</div>



</div>
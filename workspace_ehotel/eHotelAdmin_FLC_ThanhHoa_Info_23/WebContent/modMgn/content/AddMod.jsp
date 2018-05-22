<%@ include file="../../include/Paramter.jsp"%>
<div style="float: left; width: 100%; min-height: 100px;">
	<div style="float: left; width: 44%; margin-left: 5px;">

<!-- 		<div style="float: left; width: 100%"> -->
<!-- 			<span style="float: left; margin-left: 10px;">Input FTP -->
<!-- 				Server:</span> -->
<!-- 		</div> -->
<!-- 		<div style="float: left;"> -->
<!-- 			<input type="text" placeholder="Host..." id="ctaddhost" -->
<!-- 				style="width: 47%" /> <input type="text" placeholder="Port..." -->
<!-- 				id="ctaddport" style="width: 47%" /> -->
<!-- 		</div> -->
<!-- 		<div style="float: left;"> -->
<!-- 			<input type="text" placeholder="UserName..." id="ctaddusername" -->
<!-- 				style="width: 47%; margin-left: 3px;" /> <input type="password" -->
<!-- 				placeholder="Pass..." id="ctaddpassword" style="width: 25%;" /> -->
<!-- 			<button id="ctnadd_btn_connect" -->
<!-- 				style="float: right; margin-right: 8px;" onclick="loadFTPServer()">Connect</button> -->
<!-- 		</div> -->

		<div class="div_formFtpmp3">
			<ul id="ul_ftp_file" class="ul_ftp_file"
				style="float: left; width: 100%">
			</ul>
		</div>
	</div>


	<div
		style="float: left; width: 54%; height: auto; border: 1px solid #dddddd; margin-top: 15px;">
		<div style="width: 100%; height: 30px;">
			<div style="float: left; width: 80px; font-weight: 800"><%=readerLang.getContent("Subject")%>:
			</div>
			<div style="float: left;" id="div_subject"></div>

		</div>
		<div
			style="float: left; width: 100%; height: 280px; max-height: 280px; overflow-y: auto; overflow-x: hidden; position: relative;">

			<table style="width: 95%" cellpadding="1" cellspacing="1"
				class="tableMp3" id="tableMp3">
				<tr>
					<th width="40px"><%=readerLang.getContent("No")%></th>
					<th><%=readerLang.getContent("AudioTitle")%></th>
<!-- 					<th width="40px">Clip</th> -->
					<th width="40px"><%=readerLang.getContent("Delete")%></th>

				</tr>

			</table>
		</div>

	</div>
	<input style="display: none;" id="addmodtemp" value=""/>
	<div style="width: 100%; float: left; margin-top: 20px;" align="center">
		<div align='left' class='div_sub_buton'>
			<input style='' type="button"
				value=' <%=readerLang.getContent("OK")%> ' id="form_add_ok"
				class='div_buton' onclick="mod.addMod.accept();"> <input
				type="button" value="<%=readerLang.getContent("Cancel")%>"
				id="form_add_cancel" class='div_buton' onclick="mod.addMod.reset();">
		</div>
	</div>
</div>
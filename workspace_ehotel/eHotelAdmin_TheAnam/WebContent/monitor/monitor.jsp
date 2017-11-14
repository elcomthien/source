<%@page pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/Paramter.jsp"%>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
<script type="text/javascript" src="../javascript/common/common.js"></script>
<script type="text/javascript" src="../javascript/ajax.js"></script>
<script type="text/javascript" src="../javascript/common/function.js"></script>
<script type="text/javascript" src="../javascript/common/table.js"></script>
<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/main.css" type="text/css"></link>
<link rel="stylesheet" href="../css/monitor.css" type="text/css"></link>
<link rel="stylesheet" href="../css/folio.css" type="text/css"></link>
<link rel="stylesheet" href="../css/other.css" type="text/css"></link>
<script type="text/javascript" src="../javascript/monitor/monitor.js"></script>

<script type="text/javascript">
window.onload=function(){
showDate();
loadstatus_video();
viewloadDf();
setInterval( "viewloadDf()", 20000 );  
showprocess();

}
var ok="";
var cancel="";
ok="<%=readerLang.getContent("OK") %>";
cancel="<%=readerLang.getContent("Cancel") %>";
</script>
<div style="width: 1024;">
	<div>
		<div class="left_content_top"></div>
		<div class="left_content_bottom" align="center">
			<div style="width: 820; height: auto;">
				<div class="form_header" style="float: left;">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"> </span>

					<div style="float: right; width: 250px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="0"
							onclick="changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;">Video</p>
						</div>
						<div class="tab_menu_item" id="1" onclick="changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;">Music</p>
						</div>
					</div>
				</div>
				<div class="form_center"
					style="height: auto; position: relative; float: left;">
					<div style="float: left; width: 100%; height: 400px;" id="div_room">
						<div id="id_table"
							style="min-height: 240; height: 240; width: 792px; margin-top: 0px; float: left; margin-left: 13px;">
							<div id="vienngoai"
								style="margin-left: 10px; margin-top: 10px; margin-right: 10px; margin-bottom: 10px; border: 1px solid gray; height: 380px">
								<div id="headVideo" style="height: 70px">
									<table border="0" cellpadding="0" cellspacing="0" width="100%"
										height="70px">
										<tr>
											<td height="20px" width="40px" align="center"
												style="color: #ab8718"><%=readerLang.getContent("No") %></td>
											<td height="20px" align="left" width="150px"
												style="color: #ab8718"><%=readerLang.getContent("serviceName") %></td>
											<td height="2px" align="left" width="150px"
												style="color: #ab8718"><%=readerLang.getContent("Status") %></td>
											<td height="20px" align="left" style="color: #ab8718"><%=readerLang.getContent("activeanddeactive") %></td>
										</tr>
										<tr>
											<td height="20px" align="center" width="40px">1</td>
											<td height="20px" align="left" width="150px">video</td>
											<td height="20px" align="left" width="150px">
												<div id="serviceNameStart"><%=readerLang.getContent("running") %></div>
												<div id="serviceStopStart" style="display: none"><%=readerLang.getContent("stop") %></div>
											</td>
											<td height="20px" align="left">
												<div id="serviceStart">
													<input type="button"
														value="<%=readerLang.getContent("stop") %>"
														onclick="processService(1,0)">
												</div>
												<div id="serviceStop" style="display: none">
													<input type="button"
														value="<%=readerLang.getContent("start") %>"
														onclick="processService(0,0)">
												</div>
											</td>
										</tr>
									</table>
								</div>
								<div id="bentrai"
									style="border: 1px solid gray; clear: both; height: 295; margin-left: 10px; margin-bottom: 10px; width: 60%; float: left">
									<div
										style="float: left; clear: both; margin-top: 5px; margin-left: 5px">Monitoring</div>
									<div style="margin-top: 5px; float: left; margin-left: 50px"><%=readerLang.getContent("date") %>:<input
											type="text" id="txtdate" name="txtdate">
									</div>
									<div id="viewbutton"
										style="float: left; margin-left: 2px; margin-top: 2px">
										<input type="button"
											value="<%=readerLang.getContent("view") %>"
											onclick="viewload()">
									</div>
									<div id="hidebutton"
										style="margin-right: 10px; margin-top: 2px; display: none">
										<input type="button" value="view ..." disabled="disabled">
									</div>
									<div id="bt_bentrong"
										style="margin-top: 2px; margin-left: 5px; margin-right: 5px; border: 1px solid gray; height: 258px; clear: both"
										class="tableContainer"></div>
								</div>
								<div id="benphai"
									style="border: 1px solid gray; height: 220; margin-left: 10px; margin-right: 10px; margin-bottom: 10px; width: 32%; float: left">
									<%=readerLang.getContent("configtimeout") %>
									<div id="bp_bentrong"
										style="border: 1px solid gray; clear: both; margin-left: 5px; margin-right: 5px; margin-top: 10px; height: 180px">
										<table border="0" cellpadding="0" width="100%" height="170px">
											<tr>
												<td height="26" width="80">Timeout(s)</td>
												<td height="26" width="112"><input type="text"
													id="txtimeout" size="15" name="txttimeout"></td>
											</tr>
											<tr>
												<td height="29" width="80"><%=readerLang.getContent("frompage") %></td>
												<td height="29" width="112"><input type="text"
													id="txtfrompage" size="15" name="txtfrompage"></td>
											</tr>
											<tr>
												<td height="35" width="80"><%=readerLang.getContent("topage") %></td>
												<td height="35" width="112"><input type="text"
													id="txttopage" size="15" name="txttopage"></td>
											</tr>
											<tr>
												<td height="34" width="80"><%=readerLang.getContent("processall") %></td>
												<td height="34" width="112"><input type="checkbox"
													name="txtprocess" id="txtprocess"></td>
											</tr>
											<tr>
												<td height="32" width="80">&nbsp;</td>
												<td height="32" width="112"><input type="button"
													id="btn_update"
													value="<%=readerLang.getContent("update") %>"
													onclick="updateConfig()"></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div id="id_table1"
							style="min-height: 240; height: 240; width: 792px; margin-top: 0px; float: left; margin-left: 13px; display: none">
							<div id="vienngoai1"
								style="margin-left: 10px; margin-top: 10px; margin-right: 10px; margin-bottom: 10px; border: 1px solid gray; height: 380px">
								<div id="headVideo1" style="height: 70px">
									<table border="0" cellpadding="0" cellspacing="0" width="100%"
										height="70px">
										<tr>
											<td height="20px" width="40px" align="center"
												style="color: #ab8718"><%=readerLang.getContent("No") %></td>
											<td height="20px" align="left" width="150px"
												style="color: #ab8718"><%=readerLang.getContent("serviceName") %></td>
											<td height="2px" align="left" width="150px"
												style="color: #ab8718"><%=readerLang.getContent("Status") %></td>
											<td height="20px" align="left" style="color: #ab8718"><%=readerLang.getContent("activeanddeactive") %></td>
										</tr>
										<tr>
											<td height="20px" align="center" width="40px">1</td>
											<td height="20px" align="left" width="150px">music</td>
											<td height="20px" align="left" width="150px">
												<div id="serviceNameStart1"><%=readerLang.getContent("running") %></div>
												<div id="serviceStopStart1" style="display: none"><%=readerLang.getContent("stop") %></div>
											</td>
											<td height="20px" align="left">
												<div id="serviceStart1">
													<input type="button"
														value="<%=readerLang.getContent("stop") %>"
														onclick="processService(1,1)">
												</div>
												<div id="serviceStop1" style="display: none">
													<input type="button"
														value="<%=readerLang.getContent("start") %>"
														onclick="processService(0,1)">
												</div>
											</td>
										</tr>
									</table>
								</div>
								<div id="bentrai1"
									style="border: 1px solid gray; clear: both; height: 295; margin-left: 10px; margin-bottom: 10px; width: 60%; float: left">
									<div
										style="float: left; clear: both; margin-top: 5px; margin-left: 5px; margin-bottom: 5px">Monitoring</div>
									<div id="hidebutton1"
										style="margin-right: 10px; margin-top: 2px; display: none">
										<input type="button" value="view ..." disabled="disabled">
									</div>
									<div id="bt_bentrong1"
										style="margin-top: 2px; margin-left: 5px; margin-right: 5px; border: 1px solid gray; height: 258px; clear: both"
										class="tableContainer"></div>
								</div>
								<div id="benphai1"
									style="border: 1px solid gray; height: 220; margin-left: 10px; margin-right: 10px; margin-bottom: 10px; width: 32%; float: left">
									<%=readerLang.getContent("configtimeout") %>
									<div id="bp_bentrong1"
										style="border: 1px solid gray; clear: both; margin-left: 5px; margin-right: 5px; margin-top: 10px; height: 180px">
										<table border="0" cellpadding="0" width="100%" height="170px">
											<tr>
												<td height="26" width="80">Timeout(s)</td>
												<td height="26" width="112"><input type="text"
													id="txtimeout_music" size="15" name="txttimeout">
												</td>
											</tr>
											<tr>
												<td height="29" width="80"><%=readerLang.getContent("frompage") %></td>
												<td height="29" width="112"><input type="text"
													id="txtfrompage_music" size="15" name="txtfrompage"></td>
											</tr>
											<tr>
												<td height="35" width="80"><%=readerLang.getContent("topage") %></td>
												<td height="35" width="112"><input type="text"
													id="txttopage_music" size="15" name="txttopage"></td>
											</tr>
											<tr>
												<td height="34" width="80"><%=readerLang.getContent("10") %></td>
												<td height="34" width="112"><input type="text"
													id="txttype" size="15" name="txttype"></td>
											</tr>
											<tr>
												<td height="32" width="80">&nbsp;</td>
												<td height="32" width="112"><input type="button"
													id="btn_update1"
													value="<%=readerLang.getContent("update") %>"
													onclick="updateConfig_music()"></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

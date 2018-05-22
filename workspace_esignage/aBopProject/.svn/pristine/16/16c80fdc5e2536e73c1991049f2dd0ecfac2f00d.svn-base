<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="css/bootstrap-select.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />

<link href="css/dataTables.tableTools.css" rel="stylesheet"
	type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href="css/publicmain.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>




<%-- 	<script type="text/javascript" src="js/dataTables.tableTools.js"></script> --%>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<%-- <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script> --%>
<%-- <script type="text/javascript" src="js/jquery.codify.min.js"></script> --%>
<%-- <script type="text/javascript" src="js/htmlbox.colors.js"></script> --%>
<%-- <script type="text/javascript" src="js/htmlbox.styles.js"></script> --%>
<%-- <script type="text/javascript" src="js/htmlbox.syntax.js"></script> --%>
<%-- <script type="text/javascript" src="js/htmlbox.undoredomanager.js"></script> --%>
<%-- <script type="text/javascript" src="js/htmlbox.min.js"></script> --%>

<script type="text/javascript" src="js/content-text.js"></script>
<script type="text/javascript" src="js/left-subject-content.js"></script>
</head>
<div class="div-content">
	<h2 class="h2-title-content">Quản lý nội dung văn bản</h2>
	<div style="margin-top: 10px;">
		<button id="btn-add-new-text" class="btn buntton-add-new-text">
			<img class="" src="css/images/icon-new-text.png" width="24px">
			Tạo văn bản mới
		</button>
	</div>
	<div id="div-table-list-text" style="margin-top: 10px;"></div>
</div>
<div style="clear: both;">&nbsp;</div>


<!---------------------- modal add new text content --------------------->
<div id="modal-add-text-content" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h3 id="myModalLabel">Tạo nội dung văn bản mới</h3>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="span4">
				<div class="row">
					<div class="span1" style="margin-top: 7px;">Tên chủ đề:</div>
					<div class="span3">
						<input type="text" id="add-new-text-subject"
							placeholder="input title text..." readonly="readonly" />
					</div>
				</div>
				<input type="text" id="id-content-text" style="display: none;"
					value="-1">
				<div class="row" style="margin-top: 10px;">
					<div class="span1" style="margin-top: 7px; width: 85px;">Tên nội dung:</div>
					<div class="span3" style="margin-left: 15px;">
						<input type="text" id="add-new-text-name"
							placeholder="input title text..." />
					</div>
				</div>
				<div class="row" style="margin-top: 10px;">
					<div class="span3" style="margin-top: 15px;">Thông tin nội dung:</div>
				</div>
			</div>
			<div class="span2" style="text-align: left; margin-left: -80px;">
				<div class="span1" >
				<select id="fontsize" style="width:200px;" onchange="changeFont()">
					<option value="8" style="font-size: 8px;">8</option>
					<option value="9" style="font-size: 9px;">9</option>
					<option value="10" style="font-size: 10px;">10</option>
					<option value="11" style="font-size: 11px;">11</option>
					<option value="12" style="font-size: 12px;">12</option>
					<option value="14" style="font-size: 14px;">14</option>
					<option value="16" style="font-size: 16px;">16</option>
					<option value="18" style="font-size: 18px;">18</option>
					<option value="20" style="font-size: 20px;">20</option>
					<option value="22" style="font-size: 22px;">22</option>
					<option value="24" style="font-size: 24px;">24</option>
					<option value="26" style="font-size: 26px;">26</option>
					<option value="28" style="font-size: 28px;">28</option>
					<option value="36" style="font-size: 36px;">36</option>
					<option value="48" style="font-size: 48px;">48</option>
					<option value="72" style="font-size: 72px;">72</option>
				</select>
			</div>
			<div class="span1" style="margin-top: 10px;">
				<select id="fontfamily" style="width:200px;" onchange="changeFont()">
					<option value="arial" style="font-family: arial;">Arial</option>
					<option value="courier" style="font-family: courier;">Courier</option>
					<option value="cursive" style="font-family: cursive;">Cursive</option>
					<option value="georgia" style="font-family: georgia;">Georgia</option>
					<option value="monospace" style="font-family: monospace;">Monospace</option>
					<option value="tahoma" style="font-family: tahoma;">Tahoma</option>
					<option value="verdana" style="font-family: verdana;">Verdana</option>
				</select>
			</div>
			<div class="span1" style="margin-top: 10px;">
				<select id="fontcolor" style="width:200px;" onchange="changeFont()">
					<option value="FFFFFF"
						style="background: #FFFFFF; color: #FFFFFF;">White</option>
					<option value="FF0000"
						style="background: #FF0000; color: #FF0000;">Red</option>
					<option value="FFA500"
						style="background: #FFA500; color: #FFA500;">Orange</option>
					<option value="FFFF00"
						style="background: #FFFF00; color: #FFFF00;">Yellow</option>
					<option value="EE82EE"
						style="background: #EE82EE; color: #EE82EE;">Violet</option>
					<option value="0000FF"
						style="background: #0000FF; color: #0000FF;">Blue</option>
					<option value="008000"
						style="background: #008000; color: #008000;">Green</option>
					<option value="A52A2A"
						style="background: #A52A2A; color: #A52A2A;">Brown</option>
					<option value="000080"
						style="background: #000080; color: #000080;">Navy</option>
					<option value="00FF00"
						style="background: #00FF00; color: #00FF00;">Lime</option>
					<option value="000000"
						style="background: #000000; color: #000000;">Black</option>
				</select>
			</div>
			</div>
			
		</div>

		<div class="row" style="margin-top: 10px;">
			<div class="span5">
				<textarea rows="10" cols="70" id="add-text-content"
					placeholder="type content in here ..." style="font-size: 18pt; font-family: arial; color: #000000; max-width: 510px; min-width: 510px;"></textarea>
			</div>
		</div>
		<div id="content-alert-error" class="span5 alert alert-error"
			style="height: 15px; width: 80%; margin-top: 10px; margin-bottom: -10px;">
			<span><p id="content-text-error"
					style="text-align: center; font-size: 12px;"></p></span>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Hủy</button>
		<button class="btn btn-primary" id="add-text-btn">Tạo mới</button>
		<button class="btn btn-primary" id="edit-text-btn">Cập nhật</button>
	</div>
</div>

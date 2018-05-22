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
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/jscolor/jscolor.js"></script>
<script type="text/javascript" src="js/content-qms.js"></script>
<%-- <script type="text/javascript" src="js/left-subject-content.js"></script> --%>

</head>
<div class="div-content">
	<h2 class="h2-title-content">QMS Config</h2>
	<div style="margin-top: 10px;">
<!-- 		<button id="btn-add-new-text" class="btn buntton-add-new-text"> -->
<!-- 			<img class="" src="css/images/icon-new-text.png" width="24px"> -->
<!-- 			Tạo văn bản mới -->
<!-- 		</button> -->
	</div>
	<div id="div-table" style="margin-top: 10px; width: 60%; float: left;"></div>
	<div id="div-edit" style="margin-top: 0px; width: 40%; float: right;">
	
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Background header</h4>
			</div>
			<div class="span1" style="float: right;">
				<input id="input-background-header" type="text" class="color"
					value="" onchange="changeBackgroundHeader()"
					style="float: right; width: 138px; margin-top: 7px;" />
			</div>
		</div>
		
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Background even</h4>
			</div>
			<div class="span1" style="float: right;">
				<input id="input-background-body-even" type="text" class="color"
					value="" onchange="changeBackgroundBodyEven()"
					style="float: right; width: 138px; margin-top: 7px;" />
			</div>
		</div>
		
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Background odd</h4>
			</div>
			<div class="span1" style="float: right;">
				<input id="input-background-body-odd" type="text" class="color"
					value="" onchange="changeBackgroundBodyOdd()"
					style="float: right; width: 138px; margin-top: 7px;" />
			</div>
		</div>
		
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Font color header</h4>
			</div>
			<div class="span1" style="float: right;">
				<input id="input-color-header" type="text" class="color"
					value="" onchange="changeColorHeader()"
					style="float: right; width: 138px; margin-top: 7px;" />
			</div>
		</div>
		
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Font color body</h4>
			</div>
			<div class="span1" style="float: right;">
				<input id="input-color-body" type="text" class="color"
					value="" onchange="changeColorBody()"
					style="float: right; width: 138px; margin-top: 7px;" />
			</div>
		</div>
		
		
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Font size header</h4>
			</div>
			<div class="span1" style="float: right;">
				<select id="select-fontsize-header" class="form-control bfh-fontsizes" data-fontsize="12" style="width: 150px; float: right; margin-top: 7px;" onchange="changeFontSizeHeader()">
					<option value="8">8px</option>
					<option value="9">9px</option>
					<option value="10">10px</option>
					<option value="11">11px</option>
					<option value="12">12px</option>
					<option value="14">14px</option>
					<option value="16">16px</option>
					<option value="18">18px</option>
					<option value="20">20px</option>
					<option value="22">22px</option>
					<option value="24">24px</option>
					<option value="26">26px</option>
					<option value="28">28px</option>
					<option value="30">30px</option>
					<option value="32">32px</option>
					<option value="34">34px</option>
					<option value="36">36px</option>
					<option value="38">38px</option>
					<option value="40">40px</option>
					<option value="42">42px</option>
					<option value="44">44px</option>
					<option value="46">46px</option>
					<option value="48">48px</option>
					<option value="50">50px</option>
				</select>
			</div>
		</div>
		
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Font size body</h4>
			</div>
			<div class="span1" style="float: right;">
				<select id="select-fontsize-body" class="form-control bfh-fontsizes" data-fontsize="12" style="width: 150px; float: right; margin-top: 7px;" onchange="changeFontSizeBody()">
					<option value="8">8px</option>
					<option value="9">9px</option>
					<option value="10">10px</option>
					<option value="11">11px</option>
					<option value="12">12px</option>
					<option value="14">14px</option>
					<option value="16">16px</option>
					<option value="18">18px</option>
					<option value="20">20px</option>
					<option value="22">22px</option>
					<option value="24">24px</option>
					<option value="26">26px</option>
					<option value="28">28px</option>
					<option value="30">30px</option>
					<option value="32">32px</option>
					<option value="34">34px</option>
					<option value="36">36px</option>
					<option value="38">38px</option>
					<option value="40">40px</option>
					<option value="42">42px</option>
					<option value="44">44px</option>
					<option value="46">46px</option>
					<option value="48">48px</option>
					<option value="50">50px</option>
				</select>
			</div>
		</div>
		
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Font family</h4>
			</div>
			<div class="span1" style="float: right;">
				<select id="select-font" class="form-control bfh-fonts" data-font="Arial" style="width: 150px; float: right; margin-top: 7px;" onchange="changeFont()">
					<option value="Arial">Arial</option>
					<option value="Courier New">Courier New</option>
					<option value="Cursive">Cursive</option>
					<option value="Grorgia">Grorgia</option>
					<option value="Monospace">Monospace</option>
					<option value="Tahoma">Tahoma</option>
					<option value="Times New Roman">Times New Roman</option>
					<option value="Verdana">Verdana</option>
				</select>
			</div>
		</div>
		
		<div class="row">
			<div class="span2" style="width: 200px;">
				<h4 style="margin-left: 20px;">Position layout</h4>
			</div>
			<div class="span1" style="float: right;">
				<select id="select-position" class="form-control bfh-fonts" data-font="Arial" style="width: 150px; float: right; margin-top: 7px;">
					<option value="1">Left</option>
					<option value="0">Center</option>
					<option value="2">Right</option>
				</select>
			</div>
		</div>
		
		<div style="margin-left: 130px; margin-top: 10px;">
			<button class="btn btn-primary" id="qms-config" onclick="configQMS()">Config</button>
			<button class="btn btn-inverse" id="qms-reset" onclick="getAllData()">Reset</button>
		</div>
	</div>
</div>
<script type="text/javascript" src="../javascript/report/groupreport.js"></script>
<link rel="stylesheet" href="../css/report.css" type="text/css"></link>
<link rel="stylesheet" href="../javascript/calenda/css/steel/steel.css"
	type="text/css"></link>
<script type="text/javascript" src="../javascript/calenda/js/jscal2.js"></script>
<script type="text/javascript" src="../javascript/calenda/js/lang/en.js"></script>
<%@page import="ehotel.admin.util.ReaderLang"%>
<%@page import="ehotel.admin.util.Def"%>

<link rel="stylesheet" href="../css/folio.css" type="text/css"></link>
<link rel="stylesheet" href="../css/other.css" type="text/css"></link>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../javascript/calenda/css/jscal2.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="../javascript/calenda/css/border-radius.css" type="text/css"></link>
<%@ include file="../include/ParamterPms.jsp"%>
<% 	
  %>
<div style="width: 1024;">
	<div>
		<div class="left_content_top">
			Movie List By Group
		</div>
		<div class="left_content_bottom" align="center">
			<div style="width: 820; height: auto;">
				<div class="form_header" style="float: left;">
				<a class='bt_excel' title="Export to excel" id='bt_excel' style="margin-top: 10px;"></a>
				<a class='bt_print' title="Print to PDF" id='bt_print' style="margin-top: 10px;"></a>
				<select style="width: 120px; margin-top: 20px; height: 25px;" id="filter-group" onchange="gruoprpt.filter();">
				</select>
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"> </span>
					<div style="float: right; width: 150px;" class="tab_menu"
						id="tab_menu">
						<div class="tab_menu_item_sel" id="0"
							onclick="folio.changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent(19) %></p>
						</div>
					</div>
				</div>
				<div class="form_center"
					style="height: auto; position: relative; float: left;">
					<div style="float: left; width: 100%; height: 400px;" id="div_room">
						<div style="width: 740px; height: 30px; margin-top: 10px; float: left; margin-left: 40px; border: 1px solid #dddddd;  display: none;">
							<div style="float: left; width: 100%; margin-top: 5px;" id="div_time" ></div>
						</div>
						<div id="div_Table" style="margin-left: 40px; float: left; width: 740px; overflow-x: hidden; overflow: scroll; position: relative; height: 380px; border: 1px solid #dddddd; margin-top: 10px;"
							align="center"></div>
					</div>
				</div>
				<div class="form_bottom" style="float: left;"></div>
			</div>
		</div>
	</div>
</div>
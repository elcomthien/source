<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="css/bootstrap-select.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />

<link href="css/dataTables.tableTools.css" rel="stylesheet"
	type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<%-- <script type="text/javascript" src="js/dataTables.tableTools.js"></script> --%>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/publicmain.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/content-media.js"></script>
<script type="text/javascript" src="js/left-subject-content.js"></script>
<%-- <script type="text/javascript" src="js/jquery-2.1.3.js"></script> --%>
</head>
<div class="div-content">
	<h2 class="h2-title-content">Quản lý nội dung đa phương tiện</h2>
	<div style="margin-top: 10px;" id="div_btn_add">
		<button id="btn-add-new-media" class="btn buntton-add-new-media">
			<img class="" src="css/images/icon-new-media.png" width="24px">Tạo nội dung đa phương tiện mới
		</button>
	</div>
<!-- 	<div style="margin-top: 10px; display: none;" id="div_iframe_add"> -->
<!-- 		<iframe src="http://103.254.12.200:8090/aBopUploadFile/" id="iframe_upload" style="width: 99%; height: 50px; border: 0;"></iframe> -->
<!-- 	</div> -->
	<div id="div-table-list-media" style="margin-top: 10px;"></div>
</div>

<!---------------------- modal add new meida content --------------------->
<div id="modal-add-meida-content" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top: 2%;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="myModalLabel">Thêm nội dung đa phương tiện</h3>
	</div>
	<div class="modal-body" style="height: 520px; padding-top: 0px; padding-bottom: 0px; max-height: 520px;">
		<div id="div_table_list_file"></div>
		<div id="content-alert-error" class="span5 alert alert-error"
			style="height: 15px; width: 80%; margin-top: 10px; margin-bottom: -10px;">
			<span><p id="content-text-error"
					style="text-align: center; font-size: 12px;"></p></span>
		</div>
	</div>

<!-- 	<div class="modal-footer"> -->
<!-- 		<button class="btn" data-dismiss="modal" aria-hidden="true">Đóng</button> -->
<!-- 	</div> -->
</div>

<!-- ------------------------------------------------------------------------------------------------------------- -->
<div id="modal-add-meida-content-upload" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top: 20%;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">X</button>
		<h3 id="myModalLabel">Thêm nội dung đa phương tiện</h3>
	</div>
	<div id="div_iframe_upload">
<!-- 		<iframe src="http://103.254.12.200:8090/aBopUploadFile/" id="iframe_upload" style="width: 99%; height: 60px; border: 0;" onchange="changeiFrame()"></iframe> -->
	</div>
	 <div class="modal-footer">
    <button class="btn btn-inverse" id="add-media-btn">Đóng</button>
  </div>
</div>
<!-- ---------------------------------------------------------------------------------------------------------------- -->

<!---------------------- modal loading --------------------->
<div id="modal-loading" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top: 40%; text-align: center; height: 0px; border: 0px;">
<!-- 	<div class="modal-body" style="text-align: center;"> -->
		<img src="css/images/loading-transfer.gif">
<!-- 	</div> -->
</div>

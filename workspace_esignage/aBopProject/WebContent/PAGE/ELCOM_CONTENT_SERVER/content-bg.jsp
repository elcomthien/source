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
<%-- <script type="text/javascript" src="js/dataTables.tableTools.js"></script> --%>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/publicmain.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/content-bg.js"></script>
<script type="text/javascript" src="js/left-subject-content.js"></script>
</head>
<div class="div-content">
	<h2 class="h2-title-content">Quản lý nội dung hình nền</h2>
	<div style="margin-top: 10px;">
		<button id="btn-add-new-background" class="btn buntton-add-new-background">
			<img class="" src="css/images/icon-new-background.png" width="24px">
			Tạo nội dung hình nền mới
		</button>
	</div>
	<div id="div-table-list-background" style="margin-top: 10px;"></div>
</div>

<!---------------------- modal add new background content --------------------->
<div id="modal-add-background-content" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Tạo nội dung hình nền mới</h3>
  </div>
  <div class="modal-body">
   <div class="row">
    	<div class="span1 text-align-right">Chủ đề:</div>
    	<div class="span4"><input type="text" id="add-background-subject" readonly="readonly" style="width: 100%;"/></div>
    </div>
    <div class="row"  style="margin-top: 10px;">
    	<div class="span1 text-align-right">Tên</div>
    	<div class="span4"><input type="text" id="add-background-name" placeholder="input background title..." style="width: 100%;"/></div>
    </div>
    <div class="row" style="margin-top: 10px;">
    	<div class="span1 text-align-right" style="">Chọn hình:</div>
    	<div class="span4" id="div-select-image" style=""></div>
    </div>
    <div id="content-alert-error" class="span5 alert alert-error"  style="height: 15px; width: 80%; margin-top: 10px; margin-bottom: -10px;">
		<span><p id="content-text-error" style="text-align: center; font-size: 12px;"></p></span>
	</div>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Hủy</button>
    <button class="btn btn-primary" id="add-background-btn">Tạo mới</button>
  </div>
</div>

<!---------------------- modal edit background content --------------------->
<div id="modal-edit-background-content" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Cập nhật nội dung hình nền</h3>
  </div>
  <div class="modal-body">
  <div class="row" >
    	<div class="span1 text-align-right">Chủ đề:</div>
    	<div class="span4"><input type="text" id="edit-background-subject" readonly="readonly" style="width: 100%;"/></div>
    </div>
    <div class="row" style="margin-top: 10px;">
    	<div class="span1 text-align-right">Tên:</div>
    	<div class="span4"><input type="text" id="edit-background-name" placeholder="input background title..." style="width: 100%;"/></div>
    </div>
		<input id="edit-background-id" value="" style="display: none;" />
		<div class="row" style="margin-top: 10px;">
    	<div class="span1 text-align-right" style="">Chọn hình:</div>
    	<div class="span4" id="div-select-image-edit" style=""></div>
    </div>
    <div id="content-alert-error-edit" class="span5 alert alert-error"  style="height: 15px; width: 80%; margin-top: 10px; margin-bottom: -10px;">
		<span><p id="content-text-error-edit" style="text-align: center; font-size: 12px;"></p></span>
	</div>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Hủy</button>
    <button class="btn btn-primary" id="edit-background-btn">Cập nhật</button>
  </div>
</div>
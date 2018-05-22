<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="css/bootstrap-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="css/bootstrap-select.css" rel="stylesheet" type="text/css"/>
	<link href="css/main.css" rel="stylesheet" type="text/css"/>
	
	<link href="css/dataTables.tableTools.css" rel="stylesheet" type="text/css"/>
	<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/left-subject-content.js"></script>
<%-- 	<script type="text/javascript" src="js/dataTables.tableTools.js"></script> --%>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<link href="css/publicmain.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/content-web.js"></script>
	
</head>
<div class="div-content">
	<h2 class="h2-title-content">Quản lý nội dung website</h2>
	<div style="margin-top: 10px;">
		<button id="btn-add-new-web" class="btn buntton-add-new-web"><img class="" src="css/images/icon-new-web.png" width="24px">Tạo nội dung website mới</button>
	</div>
	<div id="div-table-list-web" style="margin-top: 10px;">
		<table id="table-list-web-content" class='table table-striped'>
			<thead>
				<tr>
					<th style="width: 30%">Tên</th>
					<th style="width: 50%">Đường dẫn</th>
					<th >Thao tác</th>
				</tr>
			</thead>
			<tbody id="tbody-list-web-content">
			</tbody>
		</table>
	</div>
</div>
<!-- <div style="clear: both;">&nbsp;</div> -->

<!---------------------- modal add new web content --------------------->
<div id="modal-add-web-content" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Tạo nội dung website mới</h3>
  </div>
  <div class="modal-body">
   <div class="row">
    	<div class="span2 text-align-right">Tên chủ đề nội dung:</div>
    	<div class="span3"><input type="text" id="add-web-subject-name" placeholder="input web title..." style="width: 100%;" readonly="readonly" /></div>
<!--     	<input value="" id="add-web-subject-id" style="display: none;"> -->
    </div>
    <div class="row" style="margin-top: 10px;">
    	<div class="span2 text-align-right">Tên nội dung website:</div>
    	<div class="span3"><input type="text" id="add-web-name" placeholder="input web title..." style="width: 100%;"/></div>
    </div>
    <div class="row" style="margin-top: 10px;">
    	<div class="span2 text-align-right">Đường dẫn website:</div>
    	<div class="span3"><input type="text" id="add-web-link" placeholder="input link..." style="width: 100%;" /></div>
    </div>
    <div id="content-alert-error" class="span5 alert alert-error"  style="height: 15px; width: 80%; margin-top: 10px; margin-bottom: -10px;">
		<span><p id="content-text-error" style="text-align: center; font-size: 12px;">dasdas</p></span>
	</div>
  </div>
  
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Hủy</button>
    <button class="btn btn-primary" id="add-web-btn">Tạo mới</button>
  </div>
</div>

<!---------------------- modal update web content --------------------->
<div id="modal-edit-web-content" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Cập nhật nội dung website mới</h3>
  </div>
  <div class="modal-body">
    <div class="row">
    	<div class="span2 text-align-right">Tên nội dung website:</div>
    	<div class="span3"><input type="text" id="edit-web-name" placeholder="input web title..." style="width: 100%;"/></div>
    </div>
    <div class="row" style="margin-top: 10px;">
    	<div class="span2 text-align-right">Đường dẫn website:</div>
    	<div class="span3"><input type="text" id="edit-web-link" placeholder="input link..." style="width: 100%;" /></div>
    </div>
    <input type="text" id="edit-web-id" style="display: none;"/>
    <div id="content-alert-edit-error" class="span5 alert alert-error"  style="height: 15px; width: 80%; margin-top: 10px; margin-bottom: -10px;">
		<span><p id="content-text-edit-error" style="text-align: center; font-size: 12px;"></p></span>
	</div>
  </div>
  
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Hủy</button>
    <button class="btn btn-primary" id="edit-web-btn">Cập nhật</button>
  </div>
  
</div>
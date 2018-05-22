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
<script type="text/javascript" src="js/left-subject-content.js"></script>
<%-- 	<script type="text/javascript" src="js/dataTables.tableTools.js"></script> --%>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/publicmain.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/slide-image.js"></script>

</head>
<div class="div-content">
	<h2 class="h2-title-content">Quản lý nội dung trình chiếu hình ảnh</h2>
	<div style="margin-top: 10px;">
		<button id="btn-add-new-slide" class="btn buntton-add-new-web">
			<img class="" src="css/images/icon-new-slide.png" width="24px">
			Tạo nội dung trình chiếu
		</button>
	</div>
	<div id="div-table-list-slide" style="margin-top: 10px;">
		
	</div>
</div>

<!---------------------- modal add new slide content --------------------->
<div id="modal-add-slide-content" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h3 id="myModalLabel">Tạo nội dung trình chiếu mới</h3>
	</div>
	<div class="modal-body">
		<div class="row" style="width: 1000px;">
			<div class="span4">
				<div class="row">
					<div class="span2" style="margin-top: 7px;">Tên chủ đề nội
						dung:</div>
					<div class="span2" style="margin-left: -20px;">
						<input type="text" id="add-slide-subject-name"
							placeholder="input web title..." style="width: 100%; width:207px;"
							readonly="readonly" />
					</div>
				</div>

				<div class="row" style="margin-top: 10px;">
					<div class="span2" style="margin-top: 7px;">Tên nội dung:</div>
					<div class="span2" style="margin-left: -20px;">
						<input type="text" id="add-slide-name"
							placeholder="input slide title..." style="width: 100%; width:207px;"/>
					</div>
				</div>

				<div class="row" style="margin-top: 10px;">
					<div class="span2" style="margin-top: 7px;">Chọn hiệu ứng:</div>
					<div class="span2" style="margin-left: -20px; ">
						<select class="effectSelect" id="effectSelect" onchange="runEffect()" style="width: 220px;">
							<option value="-1">---Chọn 1 hiệu ứng bất kỳ---</option>
							<option value="fade">Fade</option>
							<option value="push_left">Push Left</option>
							<option value="push_up">Push Up</option>
							<option value="slide_left">Slide Left</option>
							<option value="slide_top">Slide Top</option>
							<option value="zoom">Zoom</option>
							<option value="rotate3d">Rotate3D</option>
						</select>	
					</div>
				</div>
				
				<div class="toggler" style="margin-top: 10px; height: 280px;">
							<div id="effect" style="float-left;">
								<img id="img1" src="css/images/image_test.jpeg" width="370" height="370" />
				     		</div>
				     		
						</div>
				
			</div>
				<input value="0" id="tempnumber" style="display: none;">
				<input value="0" id="idcontentslide" style="display: none;">
			<div class="span6" style="text-align: center; overflow:auto;" id="div-list-images">
			
			</div>
		</div>
	</div>
	
	<div class="modal-footer" style="vertical-align: bottom;">
		<div id="content-alert-error" class="span5 alert alert-error"
			style="height: 15px; width: 70%; margin-bottom: -10px;">
			<span><p id="content-text-error" style="text-align: center; font-size: 12px;"></p></span>
		</div>
		<button class="btn" data-dismiss="modal" aria-hidden="true">Hủy</button>
		<button class="btn btn-primary" id="add-slide-btn" >Tạo mới</button>
		<button class="btn btn-primary" id="edit-slide-btn">Cập nhật</button>
	</div>
</div>
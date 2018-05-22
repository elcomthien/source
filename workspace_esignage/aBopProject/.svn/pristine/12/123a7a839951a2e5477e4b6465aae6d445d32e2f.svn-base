<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<ul>
	<li>
		<h2 class='title'>Chủ đề nội dung</h2>
		<div id='subject'>
			<select class='listbox' size='2' id="subjectbox" onchange='subjectSelectChange()'>
<!-- 				<option value="value" onmousedown="">abc</option> -->
<!-- 				<option value="value">asd</option> -->
<!-- 				<option value="value">aasdbc</option> -->
<!-- 				<option value="value">asdasd</option> -->
<!-- 				<option value="value">abasdasdc</option> -->
<!-- 				<option value="value">aasdasdasdasdbc</option> -->
			</select>
		</div>
	</li>
</ul>

<!---------------------- modal add new subject content --------------------->
<div id="modal-add-subject-content" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Tạo chủ đề mới</h3>
  </div>
  <div class="modal-body">
    <div class="row">
    	<div class="span2 text-align-right">Tên chủ đề:</div>
    	<div class="span3"><input type="text" id="add-subject-name" placeholder="input subject title..." style="width: 100%;"/></div>
    </div>
    <div class="row" style="margin-top: 10px;">
    	<div class="span2 text-align-right">Mô tả:</div>
    	<div class="span3"><input type="text" id="add-subject-description" placeholder="input subject description..." style="width: 100%;" /></div>
    </div>
    <div id="content-alert-error-sub" class="span5 alert alert-error"  style="height: 15px; width: 80%; margin-top: 10px; margin-bottom: -10px;">
		<span><p id="content-text-error-sub" style="text-align: center; font-size: 12px;">dasdas</p></span>
	</div>
  </div>
  
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Hủy</button>
    <button class="btn btn-primary" id="add-subject-btn">Tạo mới</button>
  </div>
</div>	

<!---------------------- modal update subject content --------------------->
<div id="modal-edit-subject-content" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Cập nhật chủ đề nội dung</h3>
  </div>
  <div class="modal-body">
    <div class="row">
    	<div class="span2 text-align-right">Tên chủ đề:</div>
    	<div class="span3"><input type="text" id="edit-subject-name" placeholder="input subject title..." style="width: 100%;"/></div>
    </div>
    <div class="row" style="margin-top: 10px;">
    	<div class="span2 text-align-right">Mô tả:</div>
    	<div class="span3"><input type="text" id="edit-subject-description" placeholder="input subject description..." style="width: 100%;" /></div>
    </div>
    <input id="edit-subject-id" value="" style="display: none;">
    <div id="content-alert-error-sub-edit" class="span5 alert alert-error"  style="height: 15px; width: 80%; margin-top: 10px; margin-bottom: -10px;">
		<span><p id="content-text-error-sub-edit" style="text-align: center; font-size: 12px;"></p></span>
	</div>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Hủy</button>
    <button class="btn btn-primary" id="edit-subject-btn">Cập nhật</button>
  </div>
</div>	
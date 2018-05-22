<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<ul>
	<li>
		<h2 class='title'>Nhóm</h2>
		<div id='group'>
			<select class='listbox' size='2' id="groupbox-content" onchange='groupContentChange()'>
			</select>
		</div>
	</li>
</ul>
<!-- Dialog message warning -->
<div class="changenamegroup" style="display: none">
			<span>Tên Nhóm</span>
			<input id="namegroup" style="width:90%;height:20px; margin:5px;" type="text" class="input-name" />
			<div style="float:left; margin:5px;">
				<a href="#" class="button-dialog" style="width:30px;" onclick="return saveGroupName()">Lưu</a>
			</div>
			<div style="float:left; margin:5px;">
				<a href="#" class="button-dialog" style="width:30px;" onclick="return unLockUI()">Đóng</a>
			</div>
</div>		
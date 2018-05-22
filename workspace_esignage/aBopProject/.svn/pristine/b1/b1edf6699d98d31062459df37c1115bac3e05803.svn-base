<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<ul>
	<li>
		<h2 class='title'>Danh Sách Nhóm</h2>
		<div id='group'>
			<select class='listbox' size='2' id="groupbox" onchange='groupSelectChange()'>
				<s:iterator begin="0" step="1" value="group">
					<s:if test="id != -1">
						<option title="<s:property value="nameFull"/>" value="<s:property value="id"/>">
							<s:property value="name"/> 
						</option>
					</s:if>
				</s:iterator>
			</select>
		</div>
	</li>
</ul>

<!--<div class="changenamegroup" style="display: none">
			<span>Tên Nhóm</span>
			<input id="namegroup" style="width:90%;height:20px; margin:5px;" type="text" class="input-name" />
			<div style="float:left; margin:5px;">
				<a href="#" class="button-dialog" style="width:30px;" onclick="return saveGroupName()">Lưu</a>
			</div>
			<div style="float:left; margin:5px;">
				<a href="#" class="button-dialog" style="width:30px;" onclick="return unLockUI()">Đóng</a>
			</div>
</div>		-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<!--DrawChart-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

</head>
<div id="layout" class="post">
	<div class="tabs">
		<ul class="tab-links">
			<li class="active"><a href="#listgroup" onclick="return tabListSTB_Click()">Danh Sách</a></li>
			<li><a href="#addlist" onclick="return tabAddListOnClick()" style="width: 73px; text-align: center;">Thêm</a>
			</li>
		</ul>
		<div class="tab-content">
			<div id="listgroup" class="tab active">
				<div class="playerofgroup">
					<table>
						<thead>
							<tr>
								<th>Mac</th>
								<th>Trạng Thái</th>
								<th>Giám Sát</th>
								<th>Ghi Chú</th>
								<th>Xóa</th>
							</tr>
						</thead>
						<tbody id="scrolling" class="player-group load-box">
							<td style='text-align: center; font-size:20px'>
								Chưa có nhóm. Hãy tạo nhóm để quản lý box
				  			</td>
						</tbody>
					</table>
				</div>
			</div>
			<!-- tab add into list -->
			<div id="addlist" class="tab"></div>
		</div>
	</div>
</div>

<div class="dialog-update-group" style="display: none">
	<div class="info-detail-left-dialog">
		<form action="" method="post" class="ui-form" onclick="return false">
			<label> 
				<span>Tên Nhóm :</span> 
				<input required='required' id="name-group-input" type="text" /> 
			</label> 
			
			<label> 
				<span>Lịch Phát :</span> 
				<input id="periodic-input" type="text" readonly="readonly"/> 
			</label>
			
			<!--<label> 
				<span>Lịch Ngày :</span> 
				<input id="daily-input" type="text" readonly="readonly"/> 
			</label>-->
			
			<label> 
				<span>Danh Sách :</span> 
				<select id='option-playlist' onchange="optionPlaylistDialogChange()"></select>
				
			</label>
			
			<div class="dialog-button">
				<div style="float: left">
					<input type='submit' class='button' value='Cập Nhật' onclick='return updateGroup_Click()'>
				</div>
				<div style="float: left; margin-left: 5px;">
					<input type='submit' class='button' value='Thoát' onclick='return unLockUI()'
						style="width: 106px;">
				</div>
			</div>
		</form>
	</div>
	<div class="info-detail-right-dialog">
		<!--<div class="layout-dialog-h" >
			<div class='layout-view-dialog-h'></div>
		</div>
		<div class="layout-dialog-v" style="display: none">
			<div class='layout-view-dialog-v'></div>
		</div>-->
	</div>
</div>

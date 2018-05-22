<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>

<!--Start Date time picker plugin-->
<link href="css/bootstrap-combined.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
<!--End date time picker plugin-->
</head>
<div id="layout" class="post content-honrizonal schedule-load-content" style='height: auto'>
	<h2 class="title title-main">Danh Sách</h2>
	
	<div class="tabs">
		<ul class="tab-links">
			<li class="active"><a href="#tab-layout"
				onclick="return layoutOnClick()">Danh Sách</a>
			</li>
			<li><a href="#tab-schedule-day" onclick="return dailyOnClick()"
				style="width: 73px; text-align: center;">Lịch Ngày</a></li>
			<li><a href="#tab-schedule-periodic" onclick="return periodicOnClick()"
				style="width: 90px; text-align: center;">Lịch Định Kì</a>
			</li>
		</ul>
		<div class="tab-content">
			<!--Tab layout -->
			<div id='tab-layout'>
				<div class='layout-view-left'></div>
				<div id="item-playlist"></div>

				<!--Draw detail-playlist -->
				<div class='playlist-detail'></div>
				
				<div style="clear: both;">&nbsp;</div>
				<!--Draw playlist -->
				<div class="container-heading"></div>
				<div class="container-playlist hidden"></div>
			</div>

			<!--Tab schedule day-->
			<div id='tab-schedule-day' style="display: none">
				<!--table daily-->
				<div class='playerofgroup' style="width: 99%;">
					<table>
						<thead>
							<tr>
								<th>Tên</th>
								<th>Bắt Đầu</th>
								<th>Kết Thúc</th>
								<th>Xóa</th>

							</tr>
						</thead>
						<tbody id="scrolling" class='schedule-daily'>
							<tr class='schedule-daily-info'>
								<td><span style="font-weight:bold">Hãy kéo một danh sách từ bên trái vào 
										đây để tạo một lịch phát theo ngày</span></td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div style="clear: both;">&nbsp;</div>
				<!--Draw playlist -->
				<div class="container-heading-daily"></div>
				<div class="container-playlist-daily"></div>
				<!--End add new schedule daily-->
				
				<!--Dialog time picker-->
				<div class='dialog-timepicker' style="display: none">
					<div>
						<h2 class="title title-dialog"></h2>
						<span>Thời gian bắt đầu:</span>
						<div class='input-append time'>
							<input id='starttime-dialog'  data-format='hh:mm:ss' placeholder="00:00:00" type='text'></input>
							<span class='add-on'> <i data-time-icon='icon-time'></i> </span>
						</div>
						<span>Thời gian kết thúc:</span>
						<div class='input-append time'>
							<input id='endtime-dialog' data-format='hh:mm:ss' placeholder="00:00:00" type='text'></input>
							<span class='add-on'> <i data-time-icon='icon-time'></i> </span>
						</div>
						<div class="button-dialog-timepicker">
							<a href="#" class="button-dialog"
								style="width: 35px; margin-right: 5px;" onclick="return acceptPlaylist()">Chọn</a>
							<a href="#" class="button-dialog" style="width: 35px;"
								onclick="return unLockUI()">Đóng</a>
						</div>
					</div>
				</div>

				<!--Dialog save playlist-->
				<div class='dialog-saveplaylist' style="display: none">
					<div>
						<h2 class="title title-dialog"></h2>
						<span>Tên danh sách:</span>
						<div>
							<input style="width:95%!important" onfocus="focusRemoveMsg('#playlistname')" onblur="checkInput('#playlistname')" id="playlistname" type='text'></input>
						</div>
						<span>Mô tả:</span>
						<div>
							<textarea rows="3" cols="1" id='description'></textarea>
						</div>
						<div class="button-dialog-saveplaylist">
							<a href="#" class="button-dialog"
								style="width: 35px; margin-right: 5px;" onclick="return savePlaylist()">Lưu</a>
							<a href="#" class="button-dialog" style="width: 35px;"
								onclick="return unLockUI()">Đóng</a>
						</div>
					</div>
				</div>
			</div>
			<!--Dialog save playlist daily-->
			<div class='dialog-savedaily' style="display: none">
				<div>
					<h2 class="title title-dialog"></h2>
					<span>Tên lịch:</span>
					<div>
						<input style="width:95%!important" onfocus="focusRemoveMsg('#dailyname')" onblur="checkInput('#dailyname')" id="dailyname" type='text'></input>
					</div>
					<span>Mô tả:</span>
					<div>
						<textarea rows="3" cols="1" id='daily-description'></textarea>
					</div>
					<div class="button-dialog-saveplaylist">
						<a href="#" class="button-dialog"
								style="width: 35px; margin-right: 5px;" onclick="return saveScheduleDaily()">Lưu</a>
						<a href="#" class="button-dialog" style="width: 35px;"
							onclick="return unLockUI()">Đóng</a>
					</div>	
				</div>
			</div>
			
			<!--Dialog update playlist daily-->
			<div class='dialog-updatedaily' style="display: none">
				<div>
					<h2 class="title title-dialog"></h2>
					<span>Tên lịch:</span>
					<div>
						<input style="width:95%!important" onfocus="focusRemoveMsg('#updatedailyname')" 
						onblur="checkInput('#updatedailyname')" id="updatedailyname" type='text'></input>
					</div>
					<span>Mô tả:</span>
					<div>
						<textarea rows="3" cols="1" id='daily-description-update'></textarea>
					</div>
					<div class="button-dialog-updateplaylist">
						<a href="#" class="button-dialog"
								style="width: 68px; margin-right: 5px;" onclick="return updateScheduleDailyName()">Cập Nhật</a>
						<a href="#" class="button-dialog" style="width: 68px;"
							onclick="return unLockUI()">Đóng</a>
					</div>	
				</div>
			</div>
			
			<!--Tab schedule-->
			<div id='tab-schedule-periodic' style="display: none"></div>
				<!--Dialog option update content for box-->
				<div class="dialog-upload-content" data-type="" data-value="" data-time="" style="display: none">
					<div>
						<h2 class="title title-dialog">Thời Gian Tải Nội Dung</h2>
						<div class="note" style="padding-left:5px; padding-right:5px"></div>
						<div class="input-append time" style="padding-top: 10px;">
							<input class="radio-default" name="radio" checked="checked" type="radio" style="margin-right: 0px;margin-top: 7px;"/>
							<span class="add-on" style="padding-left:14px;background-color: transparent; border: 0px solid #ccc;">Thời gian mặc định</span>
						</div>
						<div class="input-append time" style="margin-left: -28px; padding-top:10px; padding-bottom:10px">
							<input class="radio-custom" name="radio" type="radio" style="margin-right:16px" />
							<input id="time-update-dialog" data-format="hh:mm:ss" placeholder="00:00:00" type="text">
							<span class="add-on"> <i data-time-icon="icon-time" class="icon-time"></i> </span>
						</div>
						
						<div class="button-dialog-timepicker">
							<a href="javascript:void(0)" class="button-dialog" style="width: 35px; margin-right: 5px;" onclick="return acceptUpdatePeriodic()">Chọn</a>
							<a href="javascript:void(0)" class="button-dialog" style="width: 35px;" onclick="return unLockUI()">Đóng</a>
						</div>
					</div>
				</div>
		</div>
	</div>
	<div class='dialog-playlist' style="display: none"></div>
</div>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- Schedule periodic -->
<div id='left-schedule-periodic'>
	<ul id="schedule-periodic" style="display: none">
		<li>
			<h2 class='title'>Nhóm</h2>
			<div id='data-content'>
				<ul class='data-content group-periodic-parent'>
					<s:iterator begin="0" value="group" step="1">
						<s:if test="id != -1">
							<li title='<s:property value="nameFull" />'
								class="group-periodic"  id='<s:property value="id" />'>
								<s:property value="name" />
							</li>
						</s:if>
					</s:iterator>
				</ul>
			</div>
		</li>
	</ul>
</div>
<!--Schedule daily-->
<div id='left-schedule-daily'>
	<ul id="schedule-daily" style="display: none">
		<li>
			<h2 class='title'>Lịch Ngày</h2>
			<div id='data-content' class='daily-scroll'>
				<ul class='data-content daily-schedule-parent daily-schedule-fix'>
					<s:iterator begin="0" value="scheduleDaily" step="1">
						<li title='<s:property value="nameFull" />' description='<s:property value="note" />' 
							description-full='<s:property value="noteFull" />' 
							data-time='<s:property value="time" />' 
							createdate='<s:property value="date" />' 
							item-id='<s:property value="id" />'
							class="daily-schedule" id='<s:property value="id" />'>
							<s:property value="name" />
						</li>
					</s:iterator>
				</ul>
			</div>
		</li>
	</ul>
</div>

<div id='left-playlist'>
	<ul id='playlist-item'>
		<li>
			<h2 class='title'>Danh Sách</h2>
			<div id='data-content' class='playlist-scroll'>
				<ul class='data-content playlist-fix playlist'>
					<s:iterator begin="0" step="1" value="playlist">
						<li class='item-playlist' id='<s:property value="id"/>' 
								title='<s:property value="nameFull"/>'
								item-id='<s:property value="id" />'>
							<s:property value="nameFull"/>
						</li>
					</s:iterator>
				</ul>
			</div>
		</li>
	</ul>
</div>

<div id='left-playlist-content'>
	<ul id="playlist-content">
		<li>
			<h2 class='title'>Nội Dung</h2>
			<div class="option-group">
				<input id='input' type='text' placeholder='Tìm kiếm' onkeyup="searchContent()"/>
				
				<select id="selectsubject" onchange="selectSubjectChange()">
					<option value='0'>Tất cả chủ đề</option>
					<s:iterator begin="0" step="1" value="subject">
						<option title="<s:property value="nameFull"/>" value='<s:property value="id"/>'><s:property value='nameFull'/></option>
					</s:iterator>
				</select>
				<div class='selecttype'>
					<table>
						<tr>
							<td>
								<input  value= "0" type="radio" name="radiog_dark" checked="checked" id="radio3" class="css-checkbox" />
								<label for="radio3" class="css-label radGroup2">Tất cả</label>
							</td>
							
							<td>
								<!--<input  value= "1" type="radio" name="radiog_dark" id="radio4" class="css-checkbox" />
								<label for="radio4" class="css-label radGroup2">Video</label>-->
								<a href="#javascript">
									<img type='1' alt="" src="css/images/button-video.png" class='button-type' />
								</a>
							</td>
							<td>
								<!--<input  value= "3" type="radio" name="radiog_dark" id="radio5" class="css-checkbox" />
								<label for="radio5" class="css-label radGroup2">Image</label>-->
								<a href="#javascript">
									<img type='3' alt="" src="css/images/button-image.png" class='button-type' />
								</a>
							</td>
							<td>
								<!-- <input value= "5" type="radio" name="radiog_dark" id="radio6" class="css-checkbox" />
								<label for="radio6" class="css-label radGroup2">Text</label>-->
								<a href="#javascript">
									<img type='5' alt="" src="css/images/button-text.png" class='button-type' />
								</a>
							</td>
							<td>
								<!-- <input value= "6" type="radio" name="radiog_dark" id="radio7" class="css-checkbox" />
								<label for="radio7" class="css-label radGroup2">Link</label> -->
								<a href="#javascript">
									<img alt="" src="css/images/button-browser.png" class='button-type' type='6'/>
								</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id='data-content'>
				<ul class='data-content item-content'>
					<s:iterator begin="0" step="1" value="content">
						<s:if test="type == 'Video'">
							<li  title="<s:property value="nameFull" />" id="<s:property value='id' />" class="item-video" item-tyle='video'
									time-start='00:00:00' time-end='<s:property value="time" />' 
									time-total='<s:property value="time" />' id-private='-1'>
									<div class="content-icon-video">
  										<span style="margin-left: 36px;"><s:property value='nameFull'/></span>
  									</div>
							</li>
						</s:if>
						<s:elseif test="type == 'Image'">
							<li title="<s:property value="nameFull" />" id="<s:property value='id' />" class="item-image" item-tyle='image'
									time-start='00:00:00' time-end='00:00:20' 
									time-total='00:00:20' id-private='-1'>
									<div class="content-icon-image">
  										<span style="margin-left: 36px;"><s:property value='nameFull'/></span>
  									</div>
							</li>
						</s:elseif>
						<s:elseif test="type == 'Browser'">
							<li title="<s:property value="nameFull" />" id="<s:property value='id' />" class="item-browser" item-tyle='browser'
									time-start='00:00:00' time-end='00:00:20' 
									time-total='00:00:20' id-private='-1'>
									<div class="content-icon-browser">
  										<span style="margin-left: 36px;"><s:property value='nameFull'/></span>
  									</div>
							</li>
						</s:elseif>
						<s:elseif test="type == 'Text'">
							<li title="<s:property value="nameFull" />" id="<s:property value='id' />" class="item-text" item-tyle='text'
									time-start='00:00:00' time-end='00:00:20' 
									time-total='00:00:20' id-private='-1'>
									<div class="content-icon-text">
  										<span style="margin-left: 36px;"><s:property value='nameFull'/></span>
  									</div>
							</li>
						</s:elseif>
					</s:iterator>
				</ul>
			</div></li>
	</ul>
</div>
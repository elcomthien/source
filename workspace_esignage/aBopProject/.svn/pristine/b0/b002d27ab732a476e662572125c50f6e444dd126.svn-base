<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="layout" class="post content-honrizonal">
	<h2 class="title">Bố cục</h2>
	<p class="option">
		<select id="screensize" onchange="sizechange()">
			<option value="0">Chiều ngang</option>
			<option value="1">Chiều dọc</option>
		</select>
	</p>
	<p class="option">
		<select id="tv-size">
			<option value="1" width="1280" height="720">HD 1280x720</option>
			<option value="2" width="1920" height="1080">Full HD 1920x1080</option>
			<option value="3" width="2048" height="1080">2K 2048x1080</option>
			<option value="4" width="3840" height="2160">4K 3840x2160</option>
		</select>
	</p>
	<div class="drag-content">
		<div id="video" title="Video" class="drag-video"></div>
		<div id="image" title="Image" class="drag-image text"></div>
		<div id="ebrowser" title="Browser" class="drag-browser text"></div>
		<div id="text" title="Text" class="drag-text text"></div>
	</div>
	<div id="layout-honrizontal" class="layout-honrizontal">
		<div id="honrizontal" class="honrizontal"></div>
		<div class="h-save">
			<a href="#" id='save-honrizontal' onclick="return saveOnClick('.h-save')"> <img style="width: 40px; height: 40px" title="Save"
				src="css/images/btn-save-01.png" /> </a>
		</div>
	</div>
	<div id="layout-vertical" class="layout-vertical">
		<div id="vertical" class="vertical"></div>
		<div class="v-save">
			<a href="#" onclick="return saveVeticalOnClick('.v-save')"> 
				<img style="width: 45px; height: 45px" title="Save"
				src="css/images/btn-save-01.png" /> </a>
		</div>
	</div>
</div>
<div class="post">
	<h2 class="title">Chi tiết thông số</h2>
	<p id="poitionCurrent" class="poition"></p>
	<div id="table-honrizonal" class="entry"></div>
	<div id="table-vertical" class="entry"></div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>update file demo</title>
<script type="text/javascript" src="jquery-2.1.3.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
<link rel="stylesheet" href="bootstrap.css">
<script type="text/javascript">
	function loadUser() {
		var link = window.location.href;
		var user = link.substring(link.indexOf("#") + 1);
		$("#info").val(user);
	}
	
	function readURL(input, thumbimage) {
			var reader = new FileReader();
			reader.readAsDataURL(input.files[0]);
			loadImage(input);
	}
	
	function checkUpload() {
		var filename = $("#uploadfile").val();
		if(filename == ""){
			alert("Vui lòng chọn file!");
			return false;
		}else{
			$("#modal-loading").modal("show");
			return true;
		}
	}
	function loadImage(is)
	{
		var t=false;
		var im=is.value;
		var isize=is.files[0].size;
		im=im.substring(im.lastIndexOf("."));
		
		var ar=new Array(".mp4",".mkv",".3gp",".mov",".mp3",".m4a",".wav",".aac",".flac",".png",".jpg",".gif",".bmp",".webp");
		for(var i=0;i<ar.length;i++)
		{
			if(ar[i]==im)t=true;
		}
		if(!t)
		{
			alert("Vui lòng chọn file video - image - audio");	
			$("#uploadfile").val("");
		}
	}
	
</script>
</head>
<body onload="loadUser()">
	<form action="upload?action=save" method="post"
		enctype="multipart/form-data">
		<input type="file" id="uploadfile" name="uploadfile"
			onchange="readURL(this);" style="margin-top: 3px;" /> <input type="submit"
			class="btn btn-primary" id="uploadbtn"
			name="uploadbtn" value="Upload" onclick="return checkUpload()"  style="width: 80px;"/>
		<input type="hidden" id="info" name="info">
	</form>
	<div id="modal-loading" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top: 0%; left: 200px; text-align: center; height: 0px; border: 0px;">
		<img src="loading-transfer.gif">
</div>
</body>
</html>
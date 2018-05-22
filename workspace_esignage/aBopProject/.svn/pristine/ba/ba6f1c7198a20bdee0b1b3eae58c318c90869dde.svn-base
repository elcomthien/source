$(document).ready(function() {
	$("#content-alert-error").hide();
});

$("#add_text_content").css("height","150").css("width","477").htmlbox({
    toolbars:[
	     ["separator","code","separator","bold","italic","underline","separator","strike","sub","sup","separator","undo","redo","separator",
		 "left","center","right","justify","separator","ol","ul"],
		 ["separator","fontsize","fontfamily","separator","fontcolor","highlight"]
	],
	icons:"default",
	skin:"blue"
});

$(function(){
	$("#add-text-btn").click(function(){
		$("#content-alert-error").hide();
		var link = document.URL;
		var idsubject = link.substring(link.lastIndexOf("#")-(-1), link.length);
		var name = $("#add-new-texr-name").val();
		var url = $('#add_text_content').val();
		var font = $('#add_text_content_fontfamily').val();
		var color = $('#add_text_content_fontcolor').val();
		var size = $('#add_text_content_fontsize').val();
		if(name == ""){
			$("#content-text-error").html("Tên nội dung không được để trống!");
			$("#content-alert-error").show();
			return false;
		}
		if(url == ""){
			$("#content-text-error").html("Nội dung không được để trống!");
			$("#content-alert-error").show();
			return false;
		}
		if(size == ""){
			$("#content-text-error").html("Chưa chọn Font size cho nội dung text!");
			$("#content-alert-error").show();
			return false;
		}
		if(font == ""){
			$("#content-text-error").html("Chưa chọn Font family cho nội dung text!");
			$("#content-alert-error").show();
			return false;
		}
		if(color == ""){
			$("#content-text-error").html("Chưa chọn Color cho nội dung text!!");
			$("#content-alert-error").show();
			return false;
		}
		var datajson = {"name":name,"url":url,"font":font,"color":color,"size":size,"idsubject":idsubject};
		var jdata = JSON.stringify(datajson);
		$.post("addtext.elcom", {data : jdata}, function(r) {
			alert(r.data);
			if(r.data == "true"){
				
			}else {

			}
		});
	});
});

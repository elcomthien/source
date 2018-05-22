$(document).ready(function () {
//	getAllData();
});

$(function() {
	$("#btn-add-new-slide").click(function() {
		var idsubject = $("#subjectbox").val();
		if(idsubject == null){
			confirm("Thông báo","Bạn chưa có chủ đề nội dung, bạn có muốn tạo chủ đề cho nội dung?","Không","Đồng ý",function(){
				$("#content-alert-error-sub").hide();
				$("#add-subject-name").val("");
				$("#add-subject-description").val("");
				$("#modal-add-subject-content").modal("show");
			});
		}else{
			$("#tempnumber").val("0");
			var idsubject = $("#subjectbox").val();
			var datajson = {
				"idsubject" : idsubject
			};
			var jdata = JSON.stringify(datajson);
			$.get("getimages.elcom", {
				data : jdata
			}, function(data) {
				writeImageTableForSlide(data.content);
				$.get("allsubject.elcom", function(data) {
					var idsubject = $("#subjectbox").val();
					var namesubject = "";
					for ( var i = 0; i < data.content.length; i++) {
						if (data.content[i].id == idsubject) {
							namesubject = data.content[i].name;
						}
					}
					$("#add-slide-subject-name").val(namesubject);
				});
			});
			$("#myModalLabel").html("Tạo nội dung trình chiếu mới");
			document.getElementById("edit-slide-btn").setAttribute("style", "display:none");
			document.getElementById("add-slide-btn").setAttribute("style", "display:");
			$("#add-slide-name").val("");
			document.getElementById('effectSelect').value = '-1';
			$("#modal-add-slide-content").modal("show");
			$("#content-alert-error").hide();
		}
	});
});

function writeImageTableForSlide(data) {
	
	var body = "";
		for ( var i = 0; i < data.length; i++) {
		body += "<tr style='padding: 2px;'>";
		body += "<td style='padding: 2px;' title='" + data[i].name + "'>"
				+ checkLengthName(data[i].name) + "</td>";
		body += "<td style='padding: 2px;' title='" + data[i].url + "'>"
				+ checkLengthName(data[i].url) + "</td>";
		body += "<td style='padding: 2px; text-align:center;' class='showcolor'><input class='classtime' id='"
				+ data[i].id
				+ "-time' title='Nhập thời gian (giây), chỉ nhập số > 0' type='text' style='width:50px; height:20px; padding: 0px; font-size:12px; text-align:center; border: 1px solid #006666;' onkeypress='validate(event)'></td>";
		body += "<td style='padding: 2px; text-align:center;'><input class='classorder' id='"
				+ data[i].id
				+ "-order' readonly='readonly' title='Chỉ nhập số' type='text' style='width:50px; height:20px; padding: 0px; font-size:12px; text-align:center; border: 0px solid;' onkeypress='validate(event)'></td>";
		body += "<td style='text-align: center; padding: 0px;'>"
				+ "<input class='classcheckimage' type='checkbox' title='Chọn hình' style='margin-top:5px;' value='"
				+ data[i].id + "' id='" + data[i].id + "' onclick=checkImage('"
				+ data[i].id + "')></td>";
		body += "</tr>";
	}
	var table = "<table id='table-list-images-content' class='table table-striped'><thead>"
			+ "<tr style='background-color: #006666; color:white;'><th style='width: 35%'>Tên hình</th><th style='width: 35%'>Tên file</th><th style='text-align: center; padding-left: 0px;'>Thời gian</th><th style='text-align: center;'>Thứ tự</th><th style='text-align: center;'>Chọn</th></tr></thead>"
			+ "<tbody id='tbody-list-web-content'>" + body + "</tbody></table>";
	if (data.length > 0) {
		$("#div-list-images").html("");
		$("#div-list-images").html(table);
		$("#content-alert-error").hide();
		$("#content-text-error").html("");
	} else {
		$("#div-list-images").html("Không có hình ảnh trong chủ đề hiện tại!");
		$("#content-text-error").html("Không có hình ảnh trong chủ đề hiện tại, không thể tạo nội dung slide hình!");
		document.getElementById("add-slide-btn").setAttribute("style", "display:none");
		$("#content-alert-error").show();
	}
}

function getAllData() {
	var idsubject = $("#subjectbox").val();
	var datajson = {"idsubject" : idsubject};
	var jdata = JSON.stringify(datajson);
	$.get("allslide.elcom",{data:jdata},function(data){
		writeDataTable(data.content);
	});
}

function writeDataTable(data){
	var body = "";
	for ( var i = 0; i < data.length; i++) {
		body += "<tr>";
		body += "<td>" + data[i].name + "</td>";
		body += "<td style='text-align: center;'>" + data[i].url + "</td>";
		body += "<td style='text-align: center;'>" + data[i].time + "</td>";
		body += "<td style='text-align: center;'><img src='css/images/icon-edit-slide.png' class='img-icon-table' title='Edit slide content' " +
				"onclick=editSlideCotnent("+"'"+ data[i].id +"'"+","+"'"+ data[i].name +"'"+","+"'"+ data[i].url +"'"+")> | " +
				"<img src='css/images/icon-delete-slide.png' class='img-icon-table' title='Delete slide content' " +
				"onclick=deleteSlideCotent("+"'"+ data[i].id +"'"+","+"'"+ data[i].name +"'"+")></td>";
		body += "</tr>";
	}
	var table = "<table id='table-list-slide-content' class='table table-striped'><thead>"
			+ "<tr><th style='width: 40%'>Tên nội dung</th><th style='text-align: center;'>Hiệu ứng</th><th style='text-align: center; '>Thời gian</th><th style='text-align: center; width: 50px;'>Thao tác</th></tr></thead>"
			+ "<tbody id='tbody-list-web-content'>" + body + "</tbody></table>";
		$("#div-table-list-slide").html("");
		$("#div-table-list-slide").html(table);
		drawDataTable();
}

function drawDataTable(){
	$("#table-list-slide-content")
	.dataTable(
			{
				"bFilter" : true,
				"iDisplayLength" : 10,
				"sPaginationType" : "full_numbers",
				"sDom" : 'T<"clear">lfrtip',
				"bAutoWidth" : true,
				"bStateSave" : true,
				"bRetrieve" : true,
				"oTableTools" : {
					"sSwfPath" : "<%=request.getContextPath()%>/js/copy_csv_xls_pdf.swf"
				},"oLanguage": {
					"oPaginate": {
				        "sFirst": "Đầu",
				        "sLast": "Cuối",
				        "sNext": "Tới",
				        "sPrevious": "Lùi"
				      },
						"sLengthMenu": "Hiển thị _MENU_ dòng trên bảng",
                       "sInfo": "Hiển thị từ _START_ đến _END_ của _TOTAL_ dòng",
                       "sZeroRecords": "Không có nội dung trong bảng",
                       "sInfoEmpty": "Không có dữ liệu hiển thị",
           			 	"sSearch":"Tìm nhanh:"
   	         	},"aLengthMenu" : [ [ 10, 20, 50, 100, 200, -1 ],
						[ 10, 20, 50, 100, 200, "All" ] ]
			});
}

function runEffect() {
	var selectedEffect = $("#effectSelect").val();
	$('#effect').css('left');
	var options = {};
	if (selectedEffect != "zoom") {
		$("#effect").show();
		if (selectedEffect == "slide_top") {
			$('#effect').slideUp('slow', callback);
			return;
		} else if (selectedEffect == "slide_left") {
			options = {
				direction : "left"
			};
			$("#effect").effect("slide", options, 500, callback);
			return;
		} else if (selectedEffect == "push_up") {
			$('#effect').slideDown('slow', callback);
			return;
		} else if (selectedEffect == "push_left") {
			options = {
				direction : "right"
			};
			$("#effect").effect("slide", options, 500, callback);
			return;
		} else if (selectedEffect == "rotate3d") {
			$("#effect").rotate3Di('toggle', 1000, {
				direction : 'clockwise'
			});
			return;
		} else if (selectedEffect == "fade") {
			$("#effect").effect("fade", options, 500, callback);
			return;
		}
	} else {
		if (selectedEffect == "zoom") {
			$("#effect").cycle({
				fx : 'zoom',
				sync : false,
				delay : -2000,
				timeout : 500
			});
			return;
		}
	}
};
function callback() {
	setTimeout(function() {
		$("#effect").removeAttr("style").hide().fadeIn();
	}, 1000);
};
$("#preview").click(function() {
	runEffect();
	return false;
});

function validate(evt) {
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]|\./;
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
	}
}

function checkImage(i) {
	var id = "#" + i;
	if ($(id).is(':checked')) {
		var old = $("#tempnumber").val();
		var nn = old - (-1);
		$(id+"-order").val(nn);
		$(id+"-time").val(10);
		$("#tempnumber").val(nn);
	} else {
		var temp = $(id+"-order").val();
		var old = $("#tempnumber").val();
		var nn = old - (1);
		$("#tempnumber").val(nn);
		$(id+"-order").val("");
		$(id+"-time").val("");
		$('#table-list-images-content').children('tbody').children('tr').each(function (i,e) {
			if ($(e).find('.classcheckimage').is(':checked')) {
				var num = $(e).find('.classorder').val();
   				if(num > temp){
   				 $(e).find('.classorder').val(num - (1));
   				}
   			}
		});
	}
}

function checkLengthName(name){
	name = decodeURIComponent(name);
	if(name.length>18)
		name = name.substring(0, 18) + "...";
	return name;
}

$(function(){
	$("#add-slide-btn").click(function(){
		$("#content-alert-error").hide();
		var idsubject = $("#subjectbox").val();
		var name = $("#add-slide-name").val();
		var effect = $("#effectSelect").val();
		if(name == ""){
			$("#content-text-error").html("Tên nội dung không được để trống, vui lòng điền tên nội dung!");
			$("#content-alert-error").show();
			return false;
		}
		if(effect == -1){
			$("#content-text-error").html("Chưa chọn hiệu ứng, vui lòng chọn hiệu ứng!");
			$("#content-alert-error").show();
			return false;
		}
		var listcontent = "";
		var listorder = "";
		var listtime = "";
		var flag = 0;
		$('#table-list-images-content').children('tbody').children('tr').each(function (i,e) {
			if ($(e).find('.classcheckimage').is(':checked')) {
				flag = 1;
				var order = $(e).find('.classorder').val();
   				var idcnt = $(e).find('.classcheckimage').val();
   				var time = $(e).find('.classtime').val();
   				if(time == "" || parseInt(time) <= 0){
   					$(e).find('.classtime').attr("style","background-color: #f2dede; width:50px; padding: 0px 0px; text-align:center;");
   					$("#content-text-error").html("Thời gian không hợp lệ, vui lòng nhập lại thời gian!");
   					$("#content-alert-error").show();
   					return false;
   				}
   				listcontent += idcnt + ";";
   				listorder += order + ";";
   				listtime += time + ";";
   			}
		});
		if(flag == 0){
			$("#content-text-error").html("Chưa chọn hình ảnh, vui lòng chọn hình ảnh!");
			$("#content-alert-error").show();
			return false;
		}
		listcontent = listcontent.substring(0,listcontent.length - 1);
		listorder = listorder.substring(0, listorder.length - 1);
//		alert(listorder + " - - - " + listcontent);
		var datajson = {
				"idsubject" : idsubject,
				"name"		: name,
				"effect"	: effect,
				"listcontent":listcontent,
				"listorder":listorder,
				"listtime":listtime
			};
		var jdata = JSON.stringify(datajson);
		$.post("newslide.elcom",{data : jdata}, function(r){
			if(r.data == "true"){
				jSuccess("Success",{
		            HorizontalPosition:'center',
		            VerticalPosition:'top' 
		        });
				getAllData();
			}else{
				jError("Error",{
		            HorizontalPosition:'center',
		            VerticalPosition:'top' 
		        });
			}
		}).error(function(){
			jError("Error",{
	            HorizontalPosition:'center',
	            VerticalPosition:'top'
	        });
		});
		$("#modal-add-slide-content").modal("hide");
	});
});

function deleteSlideCotent(id, name){
	confirm("Xóa nội dung slide","Bạn có chắc muốn xóa nội dung '<span style='color:red;'>"+ name +"</span>' ?"," No "," Yes ",function(){
		var datajson = {"id":id, "name":name};
		var jdata = JSON.stringify(datajson);
		$.post("deleteslide.elcom",{data : jdata},function(r){
			if(r.data == "true"){
				jSuccess("Success",{
		            HorizontalPosition:'center',
		            VerticalPosition:'top' 
		        });
				getAllData();
			}else{
				jError("Error",{
		            HorizontalPosition:'center',
		            VerticalPosition:'top' 
		        });
			}
		}).error(function(){
			jError("Error",{
	            HorizontalPosition:'center',
	            VerticalPosition:'top'
	        });
		});
	});
}

function editSlideCotnent(id,name,url){
	var datajson = {"id" : id};
	var jdata = JSON.stringify(datajson);
	$.get("contentforslide.elcom", {
		data : jdata
	}, function(dt) {
		$("#tempnumber").val(dt.content.length);
		var idsubject = $("#subjectbox").val();
		var namesubject = "";
		$.get("allsubject.elcom", function(data) {
			for ( var i = 0; i < data.content.length; i++) {
				if (data.content[i].id == idsubject) {
					namesubject = data.content[i].name;
				}
			}
			$("#content-alert-error").hide();
			$("#content-text-error").html("");
			$("#add-slide-subject-name").val(namesubject);
//			var list = ["Untitled-1.png","Untitled-2.png","Untitled-3.png"];
			previewEffect("slide_left");
		});
		var datajson = {
			"idsubject" : idsubject
		};
		var jdata = JSON.stringify(datajson);
		$.get("getimages.elcom", {
			data : jdata
		}, function(data) {
			writeImageTableForSlide(data.content);
			$('#table-list-images-content').children('tbody').children('tr').each(function (i,e) {
				var vl = $(e).find('.classcheckimage').val();
				checkImageContent(dt.content, vl);
//				previewEffect("slide_left");
			});
		});
	});
	$("#idcontentslide").val(id);
	$("#myModalLabel").html("Cập nhật nội dung slide image");
	$("#content-alert-error").hide();
	document.getElementById("add-slide-btn").setAttribute("style", "display:none");
	document.getElementById("edit-slide-btn").setAttribute("style", "display:");
	$("#add-slide-name").val(name);
	document.getElementById('effectSelect').value = url;
	$("#modal-add-slide-content").modal("show");
}

function checkImageContent(list, id) {
	for ( var i = 0; i < list.length; i++) {
		if (list[i].id == id) {
			$("#" + id).attr("checked", true);
			$("#" + id + "-time").val(list[i].time);
			$("#" + id + "-order").val(list[i].type);
		}
	}
}

$(function(){
	$("#edit-slide-btn").click(function(){
		$("#content-alert-error").hide();
		var id = $("#idcontentslide").val();
		var name = $("#add-slide-name").val();
		var effect = $("#effectSelect").val();
		if(name == ""){
			$("#content-text-error").html("Tên nội dung không được để trống, vui lòng điền tên nội dung!");
			$("#content-alert-error").show();
			return false;
		}
		if(effect == -1){
			$("#content-text-error").html("Chưa chọn hiệu ứng, vui lòng chọn hiệu ứng!");
			$("#content-alert-error").show();
			return false;
		}
		var listcontent = "";
		var listorder = "";
		var listtime = "";
		var flag = 0;
		$('#table-list-images-content').children('tbody').children('tr').each(function (i,e) {
			if ($(e).find('.classcheckimage').is(':checked')) {
				flag = 1;
				var order = $(e).find('.classorder').val();
   				var idcnt = $(e).find('.classcheckimage').val();
   				var time = $(e).find('.classtime').val();
   				if(time == "" || parseInt(time) <= 0){
   					$(e).find('.classtime').attr("style","background-color: #f2dede; width:50px; padding: 0px 0px; text-align:center;");
   					$("#content-text-error").html("Thời gian không hợp lệ, vui lòng nhập lại thời gian!");
   					$("#content-alert-error").show();
   					return false;
   				}
   				listcontent += idcnt + ";";
   				listorder += order + ";";
   				listtime += time + ";";
   			}
		});
		if(flag == 0){
			$("#content-text-error").html("Chưa chọn hình ảnh, vui lòng chọn hình ảnh!");
			$("#content-alert-error").show();
			return false;
		}
		listcontent = listcontent.substring(0,listcontent.length - 1);
		listorder = listorder.substring(0, listorder.length - 1);
		var datajson = {
				"id" : id,
				"name"		: name,
				"effect"	: effect,
				"listcontent":listcontent,
				"listorder":listorder,
				"listtime":listtime
			};
		var jdata = JSON.stringify(datajson);
		$.post("updateslide.elcom",{data : jdata}, function(r){
			if(r.data == "true"){
				jSuccess("Success",{
		            HorizontalPosition:'center',
		            VerticalPosition:'top' 
		        });
				getAllData();
			}else{
				jError("Error",{
		            HorizontalPosition:'center',
		            VerticalPosition:'top' 
		        });
			}
		}).error(function(){
			jError("Error",{
	            HorizontalPosition:'center',
	            VerticalPosition:'top'
	        });
		});
		$("#modal-add-slide-content").modal("hide");
	});
});


function previewEffect(eff) {
	var list = ["Untitled-1.png","Untitled-2.png","Untitled-3.png"];
	var i = 0;
		setTimeout(function() {
			runEffect(eff);
			document.getElementById("img1").setAttribute("src",
					"http://172.16.9.188/mp3/"+ list[i]);
			if(i-(-1) == list.length)
				i = 0;
			else
				i = i + 1;
		}, 5000);
		
}

function sleep(delay) {
	var start = new Date().getTime();
	while (new Date().getTime() < start + delay);
}

function getSubjectName(idsubject){
	var namesubject = "";
	$.get("allsubject.elcom", function(data) {
		for ( var i = 0; i < data.content.length; i++) {
			if (data.content[i].id == idsubject) {
				namesubject = data.content[i].name;
			}
		}
		return namesubject;
	});
}

//function runEffect(selectedEffect) {
//	$('#effect').css('left');
//	var options = {};
//	if (selectedEffect != "zoom") {
//		$("#effect").show();
//		if (selectedEffect == "slide_top") {
//			$('#effect').slideUp('slow', callback);
//			return;
//		} else if (selectedEffect == "slide_left") {
//			options = {
//				direction : "left"
//			};
//			$("#effect").effect("slide", options, 500, callback);
//			return;
//		} else if (selectedEffect == "push_up") {
//			$('#effect').slideDown('slow', callback);
//			return;
//		} else if (selectedEffect == "push_left") {
//			options = {
//				direction : "right"
//			};
//			$("#effect").effect("slide", options, 500, callback);
//			return;
//		} else if (selectedEffect == "rotate3d") {
//			$("#effect").rotate3Di('toggle', 1000, {
//				direction : 'clockwise'
//			});
//			return;
//		} else if (selectedEffect == "fade") {
//			$("#effect").effect("fade", options, 500, callback);
//			return;
//		}
//	} else {
//		if (selectedEffect == "zoom") {
//			$("#effect").cycle({
//				fx : 'zoom',
//				sync : false,
//				delay : -2000,
//				timeout : 500
//			});
//			return;
//		}
//	}
//};





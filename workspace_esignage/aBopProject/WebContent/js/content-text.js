$(document).ready(function() {
	removejscssfile("group-style-table.css", "css");
	removejscssfile("styletable.css", "css");
	// $("#sidebar").remove();
	// $("#content").remove();
	// $("#div-add-text-content").hide();
	// getAllData();
	$("#modal-add-text-content").on("hidden", function() {
		getAllData();
	});
	$("#modal-edit-text-content").on("hidden", function() {
		getAllData();
	});
});

function getAllData() {
	var idsubject = $("#subjectbox").val();
	var datajson = {
		"idsubject" : idsubject
	};
	var jdata = JSON.stringify(datajson);
	$.post("alltext.elcom", {
		data : jdata
	}, function(data) {
		writeDataTable(data.content);
	});
}

$(function() {
	$("#btn-add-new-text").click(function() {
//		var idsubject = $("#subjectbox").val();
//		var iframe = document.getElementById("id-iframe");
//		var url = iframe.getAttribute("src");
//		url = url + "#" + idsubject;
//		iframe.setAttribute("src", url);
		var idsubject = $("#subjectbox").val();
		if(idsubject == null){
			confirm("Thông báo","Bạn chưa có chủ đề nội dung, bạn có muốn tạo chủ đề cho nội dung?","Không","Đồng ý",function(){
				$("#content-alert-error-sub").hide();
				$("#add-subject-name").val("");
				$("#add-subject-description").val("");
				$("#modal-add-subject-content").modal("show");
			});
		}else{
			$.get("allsubject.elcom",function(data){
				var namesubject = "";
				var idsubject = $("#subjectbox").val();
				for ( var i = 0; i < data.content.length; i++) {
					if(data.content[i].id == idsubject){
						namesubject = data.content[i].name;
					}
				}
				$("#add-new-text-name").val("");
				$("#add-text-content").val("");
				$("#myModalLabel").html("Tạo nội dung văn bản mới");
				document.getElementById('fontsize').value = '12';
				document.getElementById('fontfamily').value = 'arial';
				document.getElementById('fontcolor').value = '000000';
				
				document.getElementById("add-text-content").setAttribute("style", "color: #000000; font-size: 12px; font-family: arial; max-width: 510px; min-width: 510px;");
				
				document.getElementById("edit-text-btn").setAttribute("style", "display:none");
				document.getElementById("add-text-btn").setAttribute("style", "display:");
				$("#content-text-error").html("");
				$("#add-new-text-subject").val(namesubject);
				$("#content-alert-error").hide();
				$("#modal-add-text-content").modal("show");
			});
		}
	});
});

$(function(){
	$("#add-text-btn").click(function(){
		$("#content-alert-error").hide();
		
		var idsubject = $("#subjectbox").val();
		var name = $("#add-new-text-name").val();
		var url = $('#add-text-content').val();
		var font = $('#fontfamily').val();
		var color = $('#fontcolor').val();
		var size = $('#fontsize').val();
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
		if(size == "-1"){
			$("#content-text-error").html("Chưa chọn cỡ chữ cho nội dung văn bản!");
			$("#content-alert-error").show();
			return false;
		}
		if(font == "-1"){
			$("#content-text-error").html("Chưa chọn font chữ cho nội dung văn bản!");
			$("#content-alert-error").show();
			return false;
		}
		if(color == "-1"){
			$("#content-text-error").html("Chưa chọn màu cho nội dung văn bản!!");
			$("#content-alert-error").show();
			return false;
		}
		var datajson = {"name":name,"url":url,"font":font,"color":color,"size":size,"idsubject":idsubject};
		var jdata = JSON.stringify(datajson);
		$.post("addtext.elcom", {data : jdata}, function(r) {
			if(r.data == "true"){
				jSuccess("Success",{
		            HorizontalPosition:'center',
		            VerticalPosition:'top' 
		        });
				getAllData();
			}else {
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
		$("#modal-add-text-content").modal("hide");
	});
});

function removejscssfile(filename, filetype) {
	var targetelement = (filetype == "js") ? "script"
			: (filetype == "css") ? "link" : "none";
	var targetattr = (filetype == "js") ? "src" : (filetype == "css") ? "href"
			: "none";
	var allsuspects = document.getElementsByTagName(targetelement);
	for ( var i = allsuspects.length; i >= 0; i--) {
		if (allsuspects[i]
				&& allsuspects[i].getAttribute(targetattr) != null
				&& allsuspects[i].getAttribute(targetattr).indexOf(filename) != -1)
			allsuspects[i].parentNode.removeChild(allsuspects[i]);
	}
}
function writeDataTable(data) {
	var body = "";
	for ( var i = 0; i < data.length; i++) {
		body += "<tr>";
		body += "<td>" + data[i].name + "</td>";
		body += "<td title='" + data[i].url + "'>" + checkLengthName(data[i].url) + "</td>";
		body += "<td style='text-align: center;'>" + data[i].size + "</td>";
		body += "<td style='text-align: center;'>" + data[i].font + "</td>";
		body += "<td style='text-align: center;'>" + data[i].color + "</td>";
		body += "<td style='text-align: center;'><img src='css/images/icon-edit-text.png' class='img-icon-table' title='Edit text content' "
				+ "onclick=editTextCotnent('" + data[i].id + "','"+encodeURIComponent(data[i].name)+"','"+encodeURIComponent(data[i].url)+"','"+data[i].size+"','"+data[i].font+"','"+data[i].color+"')> | "
				+ "<img src='css/images/icon-delete-text.png' class='img-icon-table' title='Delete text content' "
				+ "onclick=deleteTextCotent("
				+ "'"
				+ data[i].id
				+ "'"
				+ ","
				+ "'" + data[i].name + "'" + ") /></td>";
		body += "</tr>";
	}
	var table = "<table id='table-list-text-content' class='table table-striped'><thead>"
			+ "<tr><th style='width: 15%'>Tên</th><th style='width: 35%'>Nội dung</th><th >Cỡ chữ</th><th >Font</th><th >Màu</th><th >Thao tác</th></tr></thead>"
			+ "<tbody id='tbody-list-web-content'>" + body + "</tbody></table>";

	$("#div-table-list-text").html("");
	$("#div-table-list-text").html(table);
	drawDataTable();
}

function editTextCotnent(id,name,url,size,font,color) {
	color = color.substring(1,color.length);
	name = decodeURIComponent(name);
	url = decodeURIComponent(url);
	$.get("allsubject.elcom",function(data){
		var namesubject = "";
		var idsubject = $("#subjectbox").val();
		for ( var i = 0; i < data.content.length; i++) {
			if(data.content[i].id == idsubject){
				namesubject = data.content[i].name;
			}
		}
		$("#id-content-text").val(id);
		$("#add-new-text-name").val(name);
		$("#add-text-content").val(url);
		$("#myModalLabel").html("Cập nhật nội dung văn bản");
		document.getElementById('fontsize').value = size;
		document.getElementById('fontfamily').value = font;
		document.getElementById('fontcolor').value = color;
		
		document.getElementById("add-text-btn").setAttribute("style", "display:none");
		document.getElementById("edit-text-btn").setAttribute("style", "display:");
		
		document.getElementById("add-text-content").setAttribute("style", "color: #"+color+"; font-size: "+size+"px; font-family: "+font+"; max-width: 510px; min-width: 510px;");
		
		$("#content-text-error").html("");
		$("#add-new-text-subject").val(namesubject);
		$("#content-alert-error").hide();
		$("#modal-add-text-content").modal("show");
	});
	
	
//	var iframe = document.getElementById("id-iframe-edit");
//	var url = iframe.getAttribute("src");
//	url = url + "#" + idsubject + "-" + id;
//	iframe.setAttribute("src", url);
//	$("#modal-edit-text-content").modal("show");
}
function deleteTextCotent(id, name) {
	confirm("Xóa nội dung văn bản",
			"Bạn có chắc muốn xóa nội dung '<span style='color:red;'>" + name
					+ "</span>' ?", "Không", "Đồng ý", function() {
				var datajson = {
					"id" : id,
					"name" : name
				};
				var jdata = JSON.stringify(datajson);
				$.post("deletetext.elcom", {
					data : jdata
				}, function() {
					jSuccess("Success", {
						HorizontalPosition : 'center',
						VerticalPosition : 'top'
					});
					getAllData();
				}).error(function() {
					jError("Error", {
						HorizontalPosition : 'center',
						VerticalPosition : 'top'
					});
				});
			});
}
function drawDataTable() {
	$("#table-list-text-content")
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

$(function(){
	$("#edit-text-btn").click(function(){
		$("#content-alert-error").hide();
		
		var id = $("#id-content-text").val();
		var name = $("#add-new-text-name").val();
		var url = $('#add-text-content').val();
		var font = $('#fontfamily').val();
		var color = $('#fontcolor').val();
		var size = $('#fontsize').val();
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
		if(size == "-1"){
			$("#content-text-error").html("Chưa chọn cỡ chữ cho nội dung văn bản!");
			$("#content-alert-error").show();
			return false;
		}
		if(font == "-1"){
			$("#content-text-error").html("Chưa chọn font chữ cho nội dung text!");
			$("#content-alert-error").show();
			return false;
		}
		if(color == "-1"){
			$("#content-text-error").html("Chưa chọn màu cho nội dung text!!");
			$("#content-alert-error").show();
			return false;
		}
		var datajson = {"name":name,"url":url,"font":font,"color":color,"size":size,"id":id};
		var jdata = JSON.stringify(datajson);
		$.post("updatetext.elcom", {data : jdata}, function(r) {
			if(r.data == "true"){
				jSuccess("Success",{
		            HorizontalPosition:'center',
		            VerticalPosition:'top' 
		        });
				getAllData();
			}else {
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
		$("#modal-add-text-content").modal("hide");
	});
});

function checkLengthName(name){
	name = decodeURIComponent(name);
	if(name.length > 30)
		name = name.substring(0, 30) + "...";
	return name;
}

function changeFont(){
	var color = $("#fontcolor").val();
	var font = $("#fontfamily").val();
	var size = $("#fontsize").val();
	document.getElementById("add-text-content").setAttribute("style", "color: #"+color+"; font-size: "+size+"px; font-family: "+font+"; max-width: 510px; min-width: 510px;");
}

function convertFontSize(size){
	if(size == "1")
		return "8pt";
	if(size == "2")
		return "10pt";
	if(size == "3")
		return "12pt";
	if(size == "4")
		return "14pt";
	if(size == "5")
		return "18pt";
	if(size == "6")
		return "24pt";
	return "36pt";
}
$(document).ready(function () {
	removejscssfile("group-style-table.css", "css");
	removejscssfile("styletable.css", "css");
//	$("#sidebar").remove();
//	$("#content").remove();
//	drawDataTable();
//	getAllData();
});
function getAllData(){
	var idsubject = $("#subjectbox").val();
	var datajson = {"idsubject":idsubject};
	var jdata = JSON.stringify(datajson);
	$.post("allweb.elcom",{data : jdata}, function(data){
		writeDataTable(data.content);
	});
}
$(function() {
	$("#btn-add-new-web").click(function() {
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
				$("#content-alert-error").hide();
				$("#content-text-error").html("");
				$("#add-web-subject-name").val(namesubject);
				$("#modal-add-web-content").modal("show");
			});
		}
	});
});

$(function() {
	$("#add-web-btn").click(function() {
		$("#content-alert-error").hide();
		$("#content-text-error").html("");
		var name = $("#add-web-name").val();
		var link = $("#add-web-link").val();
		var idsubject = $("#subjectbox").val();
		if(name == ""){
			$("#content-text-error").html("Tên nội dung website không được bỏ trống!");
			$("#content-alert-error").show();
			return false;
		}
		if(link == ""){
			$("#content-text-error").html("Đường dẫn nội dung website không được bỏ trống!");
			$("#content-alert-error").show();
			return false;
		}
		var datajson = {"name":name , "url":link, "idsubject":idsubject};
		var jdata = JSON.stringify(datajson);
		$.post("addweb.elcom",{data : jdata},function(r){
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
		$("#modal-add-web-content").modal("hide");
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
		body += "<td>" + data[i].url + "</td>";
		body += "<td style='text-align: center;'><img src='css/images/icon-edit-web.png' class='img-icon-table' title='Edit web content' " +
				"onclick=editWebCotnent("+"'"+ data[i].id +"'"+","+"'"+ data[i].name +"'"+","+"'"+ data[i].url +"'"+")> | " +
				"<img src='css/images/icon-delete-web.png' class='img-icon-table' title='Delete web content' " +
				"onclick=deleteWebCotent("+"'"+ data[i].id +"'"+","+"'"+ data[i].name +"'"+")></td>";
		body += "</tr>";
	}
	var table = "<table id='table-list-web-content' class='table table-striped'><thead>" +
			"<tr><th style='width: 25%'>Tên</th><th style='width: 50%'>Đường dẫn</th><th >Thao tác</th></tr></thead>" +
			"<tbody id='tbody-list-web-content'>"+body+"</tbody></table>";
	$("#div-table-list-web").html("");
	$("#div-table-list-web").html(table);
	drawDataTable();
}

function editWebCotnent(id, name, url){
	$("#content-alert-edit-error").hide();
	$("#content-text-edit-error").html("");
	$("#edit-web-id").val(id);
	$("#edit-web-name").val(name);
	$("#edit-web-link").val(url);
	$("#modal-edit-web-content").modal("show");
}
$(function() {
	$("#edit-web-btn").click(function() {
		$("#content-alert-edit-error").hide();
		$("#content-text-edit-error").html("");
		var id = $("#edit-web-id").val();
		var name = $("#edit-web-name").val();
		var link = $("#edit-web-link").val();
		if(name == ""){
			$("#content-text-edit-error").html("Tên nội dung website không được bỏ trông!");
			$("#content-alert-edit-error").show();
			return false;
		}
		if(link == ""){
			$("#content-text-edit-error").html("Đường dẫn nội dung website không được bỏ trông!");
			$("#content-alert-edit-error").show();
			return false;
		}
		var datajson = {"id":id , "name":name , "url":link};
		var jdata = JSON.stringify(datajson);
		$.post("updateweb.elcom",{data : jdata},function(r){
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
		$("#modal-edit-web-content").modal("hide");
		
	});
});
function deleteWebCotent(id, name){
	confirm("Xóa nội dung website","Bạn có chắc muốn xóa nội dung '<span style='color:red;'>"+ name +"</span>' ?","Không","Đồng ý",function(){
		var datajson = {"id":id, "name":name};
		var jdata = JSON.stringify(datajson);
		$.post("deleteweb.elcom",{data : jdata},function(r){
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

function drawDataTable(){
	$("#table-list-web-content")
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

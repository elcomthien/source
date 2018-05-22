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
	$.get("allbackground.elcom",{data:jdata}, function(data){
		writeDataTable(data.content);
	});
}
function writeDataTable(data) {
	var body = "";
	for ( var i = 0; i < data.length; i++) {
		body += "<tr>";
		body += "<td>" + data[i].name + "</td>";
		body += "<td>" + data[i].url + "</td>";
//		body += "<td style='text-align: center;'>" + data[i].time + "</td>";
		body += "<td style='text-align: center;'>" +
				"<img src='css/images/icon-edit-background.png' class='img-icon-table' title='Edit background content' " +
				"onclick=editbackgroundCotnent("+"'"+ data[i].id +"'"+","+"'"+ data[i].name +"'"+","+"'"+ data[i].url +"'"+")> | " +
				"<img src='css/images/icon-delete-background.png' class='img-icon-table' title='Delete background content' " +
				"onclick=deleteBackgroundCotent("+"'"+ data[i].id +"'"+","+"'"+ data[i].name +"'"+")></td>";
		body += "</tr>";
	}
	var table = "<table id='table-list-background-content' class='table table-striped'><thead>" +
			"<tr><th style='width: 20%'>Tên</th><th style=''>Nội Dung</th>" +
//			"<th >Thời lượng</th>" +
			"<th style='text-align: center; width:100px;'>Thao tác</th></tr></thead>" +
			"<tbody id='tbody-list-web-content'>"+body+"</tbody></table>";
	$("#div-table-list-background").html("");
	$("#div-table-list-background").html(table);
	drawDataTable();
}

function deleteBackgroundCotent(id, name){
	confirm("Xóa nội dung media","Bạn có chắc muốn xóa nội dung '<span style='color:red;'>"+ name +"</span>' ?"," No "," Yes ",function(){
		var datajson = {"id":id, "name":name};
		var jdata = JSON.stringify(datajson);
		$.post("deletebackground.elcom",{data : jdata},function(r){
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
function drawDataTable(){
	$("#table-list-background-content")
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
	$("#btn-add-new-background").click(function(){
		var idsubject = $("#subjectbox").val();
		if(idsubject == null){
			confirm("Thông báo","Bạn chưa có chủ đề nội dung, bạn có muốn tạo chủ đề cho nội dung?","Không","Đồng ý",function(){
				$("#content-alert-error-sub").hide();
				$("#add-subject-name").val("");
				$("#add-subject-description").val("");
				$("#modal-add-subject-content").modal("show");
			});
		}else{
			var namesubject = "";
			$.get("allsubject.elcom",function(data){
				var idsubject = $("#subjectbox").val();
				for ( var i = 0; i < data.content.length; i++) {
					if(data.content[i].id == idsubject){
						namesubject = data.content[i].name;
					}
				}
				$("#content-alert-error").hide();
				$("#content-text-error").html("");
				$("#add-background-subject").val(namesubject);
				var idsubject = $("#subjectbox").val();
				var datajson = {"idsubject":idsubject};
				var jdata = JSON.stringify(datajson);
				$.get("getallimages.elcom",{data:jdata},function(data){
					writeComboboxImagesAdd(data.content);
				});
				$("#modal-add-background-content").modal("show");
			});
		}
		
	});
});

function writeComboboxImagesAdd(data){
	var cbb = "<select id='add-background-image' style='width:384px;'>" +
			"<option value='-1'>----- Chọn hình làm hình nền -----</option>";
	for ( var i = 0; i < data.length; i++) {
		cbb += "<option value='"+data[i].name+"'>"+parseInt(i-(-1))+" - "+data[i].url+"</option>";
	}
	cbb += "</select>";
	$("#div-select-image").html("");
	$("#div-select-image").html(cbb);
}

$(function(){
	$("#add-background-btn").click(function(){
		$("#content-alert-error").hide();
		$("#content-text-error").html("");
		var name = $("#add-background-name").val();
		var url = $("#add-background-image").val();
		if(name == ""){
			$("#content-text-error").html("Tên nội dung không được để trống!");
			$("#content-alert-error").show();
		}
		else if(url == "-1"){
			$("#content-text-error").html("Chưa chọn hình để làm hình nền!");
			$("#content-alert-error").show();
		}else{
			var idsubject = $("#subjectbox").val();
			var datajson = {"idsubject":idsubject,"name":name, "url":url};
			var jdata = JSON.stringify(datajson);
			$.post("addnewbg.elcom",{data : jdata},function(r){
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
			$("#modal-add-background-content").modal("hide");
		}
	});
});

function editbackgroundCotnent(id, name, url){
	var namesubject = "";
	$.get("allsubject.elcom",function(data){
		var idsubject = $("#subjectbox").val();
		for ( var i = 0; i < data.content.length; i++) {
			if(data.content[i].id == idsubject){
				namesubject = data.content[i].name;
			}
		}
		$("#edit-background-subject").val(namesubject);
		$("#content-alert-error-edit").hide();
		$("#content-text-error-edit").html("");
		$("#edit-background-id").val(id);
		$("#edit-background-name").val(name);
		$.get("getallimages.elcom",function(data){
			writeComboboxImagesEdit(data.content, url);
		});
		$("#modal-edit-background-content").modal("show");
	});
}

function writeComboboxImagesEdit(data, url){
	var cbb = "<select id='edit-background-image' style='width:384px;'>";
	for ( var i = 0; i < data.length; i++) {
		if(data[i].url != url)
		cbb += "<option value='"+data[i].name+"'>"+parseInt(i-(-1))+" - "+data[i].url+"</option>";
		else
			cbb += "<option selected='selected' value='"+data[i].name+"'>"+parseInt(i-(-1))+" - "+data[i].url+"</option>";
	}
	cbb += "</select>";
	$("#div-select-image-edit").html("");
	$("#div-select-image-edit").html(cbb);
}

$(function(){
	$("#edit-background-btn").click(function(){
		$("#content-alert-error-edit").hide();
		$("#content-text-error-edit").html("");
		var id = $("#edit-background-id").val();
		var name = $("#edit-background-name").val();
		var url = $("#edit-background-image").val();
		if(name == ""){
			$("#content-text-error-edit").html("Tên nội dung không được để trống!");
			$("#content-alert-error-edit").show();
		}
		else if(url == "-1"){
			$("#content-text-error-edit").html("Chưa chọn hình để làm hình nền!");
			$("#content-alert-error-edit").show();
		}else{
			var datajson = {"id":id, "name":name, "url":url};
			var jdata = JSON.stringify(datajson);
			$.post("editbg.elcom",{data : jdata},function(r){
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
			$("#modal-edit-background-content").modal("hide");
		}
	});
});
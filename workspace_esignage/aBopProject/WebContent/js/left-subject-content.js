$(document).ready(function () {
	getAllSubject();
});

function getAllSubject(){
	$.get("allsubject.elcom",function(data){
		writeSubjectData(data.content);
	});
}

function writeSubjectData(data) {
	var html = "";
	for ( var i = 0; i < data.length; i++) {
		if(i == 0)
			html += "<option selected='selected' value='" + data[i].id
			+ "' onmousedown='selectItemSubject(" + data[i].id + ")'>"
			+ data[i].name + "</option>";
		else
		html += "<option value='" + data[i].id
				+ "' onmousedown='selectItemSubject(" + data[i].id + ")'>"
				+ data[i].name + "</option>";
	}
	$("#subjectbox").html("");
	$("#subjectbox").html(html);
	getAllData();
}

function selectItemSubject(id){
	$("#subjectbox option[value="+id+"]").attr('selected','selected');
	getAllData();
}

function subjectSelectChange(){
//	alert("click change");
}

function initContextMenuGroup(){
    $.contextMenu({
        selector: '#subjectbox',
        build: function ($trigger, e) {
            console.log(e);
			
            return {
                callback: function (e, options) {
					var _select = $('#screensize').val();
					if(e == 'add'){				
						addNewSubjectContent($trigger);
					} else if(e == 'delete'){
						deleteSubjectContent($trigger);
						} else if(e == 'update'){
							editSubjectContent($trigger);
						}
					
                },
                items: items
            };
        }
		
    });
    var items = {
        "add": {
            name: "Thêm chủ đề",
            icon: "add"
        },
		 "update": {
            name: "Cập nhật",
            icon: "update"
        },
		"sep1": "---------",
        "delete": {
            name: "Xóa",
            icon: "delete"
        }
        
    };
}

// add new subject content
function addNewSubjectContent($trigger){
	$("#content-alert-error-sub").hide();
	$("#add-subject-name").val("");
	$("#add-subject-description").val("");
	$("#modal-add-subject-content").modal("show");
}

$(function(){
	$("#add-subject-btn").click(function(){
		$("#content-alert-error-sub").hide();
		var name = $("#add-subject-name").val();
		var des = $("#add-subject-description").val();
		if(name == ""){
			$("#content-text-error-sub").html("Tên chủ đề không được để trống");
			$("#content-alert-error-sub").show();
			return false;
		}
		if(des == "")
			des = "note";
		var flag = 0;
		$.get("allsubject.elcom",function(data){
			for ( var i = 0; i < data.content.length; i++) {
				if(name == data.content[i].name){
					flag = 1;
				}
			}
			if(flag == 1){
				$("#content-text-error-sub").html("Tên chủ đề đã tồn tại, vui lòng chọn tên khác!");
				$("#content-alert-error-sub").show();
				return false;
			}
			var datajson = {"name":name , "des":des};
			var jdata = JSON.stringify(datajson);
			$.post("addsubject.elcom",{data : jdata},function(r){
				if(r.data == "true"){
					jSuccess("Success",{
			            HorizontalPosition:'center',
			            VerticalPosition:'top' 
			        });
					getAllSubject();
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
			$("#modal-add-subject-content").modal("hide");
		});
	});
});

// update subject content
function editSubjectContent($trigger){
	var id = $("#subjectbox").val();
	var name = "";
	var des = "";
	$.get("allsubject.elcom",function(data){
		for ( var i = 0; i < data.content.length; i++) {
			if(data.content[i].id == id){
				name = data.content[i].name;
				des = data.content[i].note;
			}
		}
		$("#content-alert-error-sub-edit").hide();
		$("#edit-subject-id").val(id);
		$("#edit-subject-name").val(name);
		$("#edit-subject-description").val(des);
		$("#modal-edit-subject-content").modal("show");
	});
	
}

$(function(){
	$("#edit-subject-btn").click(function(){
		$("#content-alert-error-sub-edit").hide();
		var name = $("#edit-subject-name").val();
		if(name == ""){
			$("#content-text-error-sub-edit").html("Tên chủ đề không được để trống!");
			$("#content-alert-error-sub-edit").show();
			return false;
		}
		var id = $("#edit-subject-id").val();
		var flag = 0;
		$.get("allsubject.elcom",function(data){
			for ( var i = 0; i < data.content.length; i++) {
				if(name == data.content[i].name && data.content[i].id != id){
					flag = 1;
				}
			}
			if(flag == 1){
				$("#content-text-error-sub-edit").html("Tên chủ đề đã tồn tại, vui lòng chọn tên khác!");
				$("#content-alert-error-sub-edit").show();
				return false;
			}
			var des = $("#edit-subject-description").val();
			var datajson = {"id":id , "name":name , "des":des};
			var jdata = JSON.stringify(datajson);
			$.post("updatesubject.elcom",{data : jdata},function(r){
				if(r.data == "true"){
					jSuccess("Success",{
			            HorizontalPosition:'center',
			            VerticalPosition:'top' 
			        });
					getAllSubject();
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
			$("#modal-edit-subject-content").modal("hide");	
		});
	});
});

// delete subject content
function deleteSubjectContent($trigger){
	var id = $("#subjectbox").val();
	var name = "";
	$.get("allsubject.elcom",function(data){
		for ( var i = 0; i < data.content.length; i++) {
			if(data.content[i].id == id){
				name = data.content[i].name;
			}
		}
		confirm("Xóa chủ đề nội dung","Bạn có chắc muốn xóa chủ đề '<span style='color:red;'>"+ name +"</span>' ?"," No "," Yes ",function(){
			var datajson = {"id":id};
			var jdata = JSON.stringify(datajson);
			$.post("deletesubject.elcom",{data : jdata},function(r){
				if(r.data == "true"){
					jSuccess("Success",{
			            HorizontalPosition:'center',
			            VerticalPosition:'top' 
			        });
					getAllSubject();
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
	});
}
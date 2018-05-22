$(document).ready(function () {
	removejscssfile("group-style-table.css", "css");
	removejscssfile("styletable.css", "css");
//	removejscssfile("jquery-1.11.1.js", "js");
//	replacejscssfile("jquery-1.11.1.js", "jquery-2.1.3.js", "js");
});

function getAllData(){
	var idsubject = $("#subjectbox").val();
	var datajson = {"idsubject":idsubject};
	var jdata = JSON.stringify(datajson);
	$.post("allmedia.elcom",{data : jdata}, function(data){
		writeDataTable(data.content);
	});
}
function writeDataTable(data) {
	var body = "";
	for ( var i = 0; i < data.length; i++) {
		body += "<tr>";
		body += "<td title='" + data[i].name + "'>" + checkLengthView(data[i].name) + "</td>";
		body += "<td title='" + data[i].url + "'>" + checkLengthView(data[i].url) + "</td>";
		body += "<td style='text-align: center;'>" + data[i].time + "</td>";
		body += "<td style='text-align: center;'>" +
				"<img src='css/images/icon-delete-media.png' class='img-icon-table' title='Delete media content' " +
				"onclick=deleteMediaCotent("+"'"+ data[i].id +"'"+","+"'"+ data[i].name +"'"+")></td>";
		body += "</tr>";
	}
	var table = "<table id='table-list-meida-content' class='table table-striped'><thead>" +
			"<tr><th style='width: 30%'>T&#xEA;n</th><th style='width: 30%'>N&#x1ED9;i Dung</th><th >Th&#x1EDD;i l&#x1B0;&#x1EE3;ng</th><th >Thao t&#xE1;c</th></tr></thead>" +
			"<tbody id='tbody-list-web-content'>"+body+"</tbody></table>";
	$("#div-table-list-media").html("");
	$("#div-table-list-media").html(table);
	drawDataTable();
}

function deleteMediaCotent(id, name){
	confirm("X&#xF3;a n&#x1ED9;i dung &#x111;a ph&#x1B0;&#x1A1;ng ti&#x1EC7;n","B&#x1EA1;n c&#xF3; ch&#x1EAF;c mu&#x1ED1;n x&#xF3;a n&#x1ED9;i dung '<span style='color:red;'>"+ name +"</span>' ?","Kh&#xF4;ng","&#x110;&#x1ED3;ng &#xFD;",function(){
		var datajson = {"id":id, "name":name};
		var jdata = JSON.stringify(datajson);
		$.post("deletemedia.elcom",{data : jdata},function(r){
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
	$("#table-list-meida-content")
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
				        "sFirst": "&#x110;&#x1EA7;u",
				        "sLast": "Cu&#x1ED1;i",
				        "sNext": "T&#x1EDB;i",
				        "sPrevious": "L&#xF9;i"
				      },
						"sLengthMenu": "Hi&#x1EC3;n th&#x1ECB; _MENU_ d&#xF2;ng tr&#xEA;n b&#x1EA3;ng",
                       "sInfo": "Hi&#x1EC3;n th&#x1ECB; t&#x1EEB; _START_ &#x111;&#x1EBF;n _END_ c&#x1EE7;a _TOTAL_ d&#xF2;ng",
                       "sZeroRecords": "Kh&#xF4;ng c&#xF3; n&#x1ED9;i dung trong b&#x1EA3;ng",
                       "sInfoEmpty": "Kh&#xF4;ng c&#xF3; d&#x1EEF; li&#x1EC7;u hi&#x1EC3;n th&#x1ECB;",
           			 	"sSearch":"T&#xEC;m nhanh:"
   	         	},"aLengthMenu" : [ [ 10, 20, 50, 100, 200, -1 ],
						[ 10, 20, 50, 100, 200, "All" ] ]
			});
}

$(function(){
	$("#btn-add-new-media").click(function(){
//		var path ="";
//		var datajson = {"path":path};
//		var jdata = JSON.stringify(datajson);
//		$.get("getfileftp.elcom",{data : jdata}, function(data){
//			writeTableListFile(data.content, "-1");
//		});
		var idsubject = $("#subjectbox").val();
		if(idsubject == null){
			confirm("Th&#xF4;ng b&#xE1;o","B&#x1EA1;n ch&#x1B0;a c&#xF3; ch&#x1EE7; &#x111;&#x1EC1; n&#x1ED9;i dung, b&#x1EA1;n c&#xF3; mu&#x1ED1;n t&#x1EA1;o ch&#x1EE7; &#x111;&#x1EC1; cho n&#x1ED9;i dung?","Kh&#xF4;ng","&#x110;&#x1ED3;ng &#xFD;",function(){
				$("#content-alert-error-sub").hide();
				$("#add-subject-name").val("");
				$("#add-subject-description").val("");
				$("#modal-add-subject-content").modal("show");
			});
		}else{
			var username = $('.userlogin').attr('data-user');
			var parent = getParentCreator();
			var host = window.location.host;
			var url = "http://"+host+"/aBopUploadFile/";
			url = url + "#" + username+ "#" + parent + "#" + idsubject;
			var html = "<iframe src='"+url+"' id='iframe_upload' style='width: 99%; height: 60px; border: 0;'></iframe>";
			$("#iframe_upload").remove();
			$("#div_iframe_upload").append(html);
			$("#content-alert-error").hide();
			$("#modal-add-meida-content-upload").modal("show");
		}
	});
});

function changeiFrame(){
	var url = $("#iframe_upload").get(0).contentWindow.location;
	alert(url);
}

function replacejscssfile(oldfilename, newfilename, filetype){
    var targetelement=(filetype=="js")? "script" : (filetype=="css")? "link" : "none"; //determine element type to create nodelist using
    var targetattr=(filetype=="js")? "src" : (filetype=="css")? "href" : "none"; //determine corresponding attribute to test for
    var allsuspects=document.getElementsByTagName(targetelement);
    for (var i=allsuspects.length; i>=0; i--){ //search backwards within nodelist for matching elements to remove
        if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(oldfilename)!=-1){
            var newelement=createjscssfile(newfilename, filetype);
            allsuspects[i].parentNode.replaceChild(newelement, allsuspects[i]);
        }
    }
}
function createjscssfile(filename, filetype){
    if (filetype=="js"){ //if filename is a external JavaScript file
        var fileref=document.createElement('script');
        fileref.setAttribute("type","text/javascript");
        fileref.setAttribute("src", filename);
    }
    else if (filetype=="css"){ //if filename is an external CSS file
        var fileref=document.createElement("link");
        fileref.setAttribute("rel", "stylesheet");
        fileref.setAttribute("type", "text/css");
        fileref.setAttribute("href", filename);
    }
    return fileref;
}


$(function(){
	$("#add-media-btn").click(function(){
		$("#modal-add-meida-content-upload").modal("hide");
//		location.reload();
		getAllData();
//	    var iframe = $('#iframe_upload').contents();
//		iframe.find("#uploadbtn").click();
	});
});

function writeTableListFile(data, parent){
	var body = "";
	var back = "";
	back += "<tr>";
	back += "<td title='Back to parent folder' onclick=backFolder("+"'"+""+encodeURIComponent(parent)+""+"'"+") style='cursor: pointer;'><a onclick=backFolder("+"'"+""+parent+""+"'"+")>...</a></td>";
	back += "<td></td>";
	back += "<td style='text-align: center;'></td>";
	back += "</tr>";
	for ( var i = 0; i < data.length; i++) {
		if(data[i].type=="1"){
			var name = data[i].name;
			name = name.substring(name.lastIndexOf("/")-(-1), name.length);
			body += "<tr>";
			body += "<td style='cursor: pointer;' onclick=intoFolder("+"'"+"" + encodeURIComponent(data[i].name) + ""+"'"+")><a onclick=intoFolder("+"'"+"" + data[i].name + ""+"'"+") title='"+name+"'>" + checkLengthName(name) + "</a></td>";
			body += "<td>" + data[i].size + "</td>";
			body += "<td style='text-align: center;'></td>";
			body += "</tr>";
		}
	}
	if(parent != "-1"){
		body = back + body;
	}
	for ( var i = 0; i < data.length; i++) {
		if(data[i].type=="0"){
			var name = data[i].name;
			name = name.substring(name.lastIndexOf("/")-(-1), name.length);
			body += "<tr>";
			body += "<td title='"+name+"'>" + checkLengthName(name) + "</td>";
			body += "<td>" + (data[i].size/ (1024 * 1024)).toFixed(3) + " Mb</td>";
			body += "<td style='text-align: center;'>" +
					"<img src='css/images/icon-transfer-file.png' class='img-icon-table' title='Transfer file to server' " +
					"onclick=transferFileFTP("+"'"+ encodeURIComponent(data[i].name) +"'"+")></td>";
			body += "</tr>";
		}
	}
	var table = "<table id='table-list-meida-file' class='table table-striped'><thead>" +
			"<tr><th style='width: '>T&#xEA;n file</th><th style='width: '>Dung l&#x1B0;&#x1EE3;ng</th><th >Thao t&#xE1;c</th></tr></thead>" +
			"<tbody id='tbody-list-media-content'>"+body+"</tbody></table>";
	$("#div_table_list_file").html("");
	$("#div_table_list_file").html(table);
	$("#table-list-meida-file")
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
				        "sFirst": "&#x110;&#x1EA7;u",
				        "sLast": "Cu&#x1ED1;i",
				        "sNext": "T&#x1EDB;i",
				        "sPrevious": "L&#xF9;i"
				      },
						"sLengthMenu": "Hi&#x1EC3;n th&#x1ECB; _MENU_ d&#xF2;ng tr&#xEA;n b&#x1EA3;ng",
                       "sInfo": "Hi&#x1EC3;n th&#x1ECB; t&#x1EEB; _START_ &#x111;&#x1EBF;n _END_ c&#x1EE7;a _TOTAL_ d&#xF2;ng",
                       "sZeroRecords": "Kh&#xF4;ng c&#xF3; n&#x1ED9;i dung trong b&#x1EA3;ng",
                       "sInfoEmpty": "Kh&#xF4;ng c&#xF3; d&#x1EEF; li&#x1EC7;u hi&#x1EC3;n th&#x1ECB;",
           			 	"sSearch":"T&#xEC;m nhanh:"
   	         	},"aLengthMenu" : [ [ 10, 20, 50, 100, 200, -1 ],
						[ 10, 20, 50, 100, 200, "All" ] ]
			});
}

function transferFileFTP(name){
	var type = name.substring(name.lastIndexOf(".") - (-1), name.length);
	if(checkFileType(type) == 0){
		confirm("X&#xF3;a n&#x1ED9;i dung media","B&#x1EA1;n c&#xF3; ch&#x1EAF;c mu&#x1ED1;n x&#xF3;a n&#x1ED9;i dung '<span style='color:red;'>"+ name +"</span>' ?"," Yes ",function(){
			return false;
		});
	}
	
	name = decodeURIComponent(name);
	if(name.indexOf("/") == 0)
		name = name.substring(1, name.length);
	var idsubject = $("#subjectbox").val();
	var datajson = {"name":name,"idsubject":idsubject};
	var jdata = JSON.stringify(datajson);
	$("#modal-loading").modal("show");
	$.post("newmedia.elcom",{data : jdata},function(r){
		$("#modal-loading").modal("hide");
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
}

function backFolder(path){
	path = decodeURIComponent(path);
	var parent = path.substring(0,path.lastIndexOf("/"));
	if(path == "")
		parent = "-1";
	var datajson = {"path":path};
	var jdata = JSON.stringify(datajson);
	$.get("getfileftp.elcom",{data : jdata}, function(data){
		writeTableListFile(data.content, parent);
	});
}

function intoFolder(path){
	path = decodeURIComponent(path);
	var parent = path.substring(0,path.lastIndexOf("/"));
	var datajson = {"path":path};
	var jdata = JSON.stringify(datajson);
	$.get("getfileftp.elcom",{data : jdata}, function(data){
		writeTableListFile(data.content, parent);
	});
}

function checkLengthName(name){
	name = decodeURIComponent(name);
	if(name.length>30)
		name = name.substring(0, 30) + "...";
	return name;
}
function checkLengthView(name){
	name = decodeURIComponent(name);
	if(name.length>30)
		name = name.substring(0, 30) + "...";
	return name;
}

function checkFileType(type) {
	if (type == "mp4" || type == "mkv" || type == "3gp" || type == "mp3"
			|| type == "m4a" || type == "wav" || type == "mp4" || type == "png"
			|| type == "jpg" || type == "gif" || type == "bmp"
			|| type == "webp")
		return 1;
	return 0;
}
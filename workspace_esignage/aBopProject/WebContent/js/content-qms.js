$(document).ready(function() {
	removejscssfile("group-style-table.css", "css");
	removejscssfile("styletable.css", "css");
	// $("#sidebar").remove();
	// $("#content").remove();
	// $("#div-add-text-content").hide();
	 getAllData();
	$("#sidebar").hide();
	$("#content").css("width","100%");
	$("#modal-add-text-content").on("hidden", function() {
		//getAllData();
	});
	$("#modal-edit-text-content").on("hidden", function() {
		//getAllData();
	});
});

function getAllData() {
	$.post("getqms.elcom", {
	}, function(data) {
		writeHTML(data.contentQMS);
		loadConfigQMS(data.contentQMS);
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

function writeHTML(data){
	var body = "";
	if(data.length > 0){
		body += "<table id='table-qms' style='width: 100%; font-family: "+data[0].font+";' >";
		body += "<thead style='background-color: "+data[0].bgheader+"; color: "+data[0].clheader+"; font-size: "+data[0].fzheader+";' id='table-header'>";
		body += "<tr style='height: 50px;'>";
		body += "<th>Ticket Number</th>";
		body += "<th>Counter</th>";
		body += "</tr>";
		body += "</thead>";
		body += "<tbody style='color: "+data[0].clbody+"; font-size: "+data[0].fzbody+";' id='table-body'>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyodd+";' class='odd'>";
		body += "<td style='text-align: center;'>2001</td>";
		body += "<td style='text-align: center;'>01</td>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyeven+";' class='even'>";
		body += "<td style='text-align: center;'>2002</td>";
		body += "<td style='text-align: center;'>02</td>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyodd+";' class='odd'>";
		body += "<td style='text-align: center;'>2003</td>";
		body += "<td style='text-align: center;'>03</td>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyeven+";' class='even'>";
		body += "<td style='text-align: center;'>2004</td>";
		body += "<td style='text-align: center;'>04</td>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyodd+";' class='odd'>";
		body += "<td style='text-align: center;'>2005</td>";
		body += "<td style='text-align: center;'>05</td>";
		body += "</tr>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyeven+";' class='even'>";
		body += "<td style='text-align: center;'>2006</td>";
		body += "<td style='text-align: center;'>06</td>";
		body += "</tr>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyodd+";' class='odd'>";
		body += "<td style='text-align: center;'>2007</td>";
		body += "<td style='text-align: center;'>07</td>";
		body += "</tr>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyeven+";' class='even'>";
		body += "<td style='text-align: center;'>2008</td>";
		body += "<td style='text-align: center;'>08</td>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyodd+";' class='odd'>";
		body += "<td style='text-align: center;'>2009</td>";
		body += "<td style='text-align: center;'>09</td>";
		body += "</tr>";
		body += "<tr style='height: 30px; background-color: "+data[0].bgbodyeven+";' class='even'>";
		body += "<td style='text-align: center;'>2010</td>";
		body += "<td style='text-align: center;'>10</td>";
		body += "</tr>";
		body += "</tbody>";
		body += "</table>";
	}else{
		body += "<table id='table-qms' style='width: 100%;' >";
		body += "<thead id='table-header'>";
		body += "<tr style='height: 50px;'>";
		body += "<th>Ticket Number</th>";
		body += "<th>Counter</th>";
		body += "</tr>";
		body += "</thead>";
		body += "<tbody id='table-body'>";
		body += "<tr style='height: 30px;' class='odd'>";
		body += "<td style='text-align: center;'>2001</td>";
		body += "<td style='text-align: center;'>01</td>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='even'>";
		body += "<td style='text-align: center;'>2002</td>";
		body += "<td style='text-align: center;'>02</td>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='odd'>";
		body += "<td style='text-align: center;'>2003</td>";
		body += "<td style='text-align: center;'>03</td>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='even'>";
		body += "<td style='text-align: center;'>2004</td>";
		body += "<td style='text-align: center;'>04</td>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='odd'>";
		body += "<td style='text-align: center;'>2005</td>";
		body += "<td style='text-align: center;'>05</td>";
		body += "</tr>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='even'>";
		body += "<td style='text-align: center;'>2006</td>";
		body += "<td style='text-align: center;'>06</td>";
		body += "</tr>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='odd'>";
		body += "<td style='text-align: center;'>2007</td>";
		body += "<td style='text-align: center;'>07</td>";
		body += "</tr>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='even'>";
		body += "<td style='text-align: center;'>2008</td>";
		body += "<td style='text-align: center;'>08</td>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='odd'>";
		body += "<td style='text-align: center;'>2009</td>";
		body += "<td style='text-align: center;'>09</td>";
		body += "</tr>";
		body += "<tr style='height: 30px;' class='even'>";
		body += "<td style='text-align: center;'>2010</td>";
		body += "<td style='text-align: center;'>10</td>";
		body += "</tr>";
		body += "</tbody>";
		body += "</table>";
	}
	$("#div-table").html("");
	$("#div-table").html(body);
}

function loadConfigQMS(data){
	$("#input-background-header").val(replaceText(data[0].bgheader));
	$("#input-background-header").css("background-color",data[0].bgheader);
	
	$("#input-background-body-even").val(replaceText(data[0].bgbodyeven));
	$("#input-background-body-even").css("background-color",data[0].bgbodyeven);
	
	$("#input-background-body-odd").val(replaceText(data[0].bgbodyodd));
	$("#input-background-body-odd").css("background-color",data[0].bgbodyodd);
	
	$("#input-color-header").val(replaceText(data[0].clheader));
	$("#input-color-header").css("background-color",data[0].clheader);
	
	$("#input-color-body").val(replaceText(data[0].clbody));
	$("#input-color-body").css("background-color",data[0].clbody);
	
	$("#select-fontsize-header").val(replaceText(data[0].fzheader));
	$("#select-fontsize-body").val(replaceText(data[0].fzbody));
	$("#select-font").val(replaceText(data[0].font));
	$("#select-position").val(data[0].position);
}

function replaceText(text){
	text = text.replace("#","");
	text = text.replace("px","");
	return text;
}

function changeBackgroundHeader(){
	var color = "#"+ $("#input-background-header").val();
	$("#table-header").css('background-color', color);
}

function changeBackgroundBodyEven(){
	var color = "#"+ $("#input-background-body-even").val();
	$(".even").css('background-color', color);
}

function changeBackgroundBodyOdd(){
	var color = "#"+ $("#input-background-body-odd").val();
	$(".odd").css('background-color', color);
}

function changeColorHeader(){
	var color = "#"+ $("#input-color-header").val();
	$("#table-header").css('color', color);
}

function changeColorBody(){
	var color = "#"+ $("#input-color-body").val();
	$("#table-body").css('color', color);
}

function changeFontSizeHeader(){
	var size = $("#select-fontsize-header").val() + "px";
	$("#table-header").css('font-size', size);
}

function changeFontSizeBody(){
	var size = $("#select-fontsize-body").val() + "px";
	$("#table-body").css('font-size', size);
}

function changeFont(){
	var font = $("#select-font").val();
	$("#table-qms").css('font-family', font);
}

function configQMS(){
	var bgheader = "#"+ $("#input-background-header").val();
	var bgbodyeven = "#"+ $("#input-background-body-even").val();
	var bgbodyodd = "#"+ $("#input-background-body-odd").val();
	var clheader = "#"+ $("#input-color-header").val();
	var clbody = "#"+ $("#input-color-body").val();
	var fzheader = $("#select-fontsize-header").val() + "px";
	var fzbody = $("#select-fontsize-body").val() + "px";
	var font = $("#select-font").val();
	var position = $("#select-position").val();
	
	var datajson = {"bgheader":bgheader,"bgbodyeven":bgbodyeven,"bgbodyodd":bgbodyodd,"clheader":clheader,"clbody":clbody,"fzheader":fzheader,"fzbody":fzbody,"font":font,"position":position};
	var jdata = JSON.stringify(datajson);
	$.post("configqms.elcom", {data : jdata}, function(r) {
		if(r.data == "true"){
			jSuccess("Config QMS Successful",{
	            HorizontalPosition:'center',
	            VerticalPosition:'top' 
	        });
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
}
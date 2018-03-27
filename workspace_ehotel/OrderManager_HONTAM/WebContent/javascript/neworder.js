$(document).ready(function() {
	loadContent("tabs-dining");
	checkOrder();
	var interval = setInterval(function() {
//		checkOrder();
		loadContent("tabs-dining");
	}, 10000);
	 var temp = 1;
	$("#btniconautoupdate").click(function() {
		if (temp == 0) {
			temp = 1;
			interval = setInterval(function() {
//				checkOrder();
				loadContent("tabs-dining");
			}, 10000);
			var text = "<img src='img/icon_stop.png' width='30px;' />";
			$("#btniconautoupdate").html(text);
		} else if (temp == 1) {
			temp = 0;
			clearInterval(interval);
			var text = "<img src='img/icon_autoupdate.png' width='30px;' />";
			$("#btniconautoupdate").html(text);
		}
	});
});
$(function() {
	$("#tabs-dining").click(function() {
		$("#tabs-dining").addClass("active");
		$("#tabs-housekeeping").removeClass("active");
		$("#tabs-wakeup").removeClass("active");
		$("#tabs-room").removeClass("active");
		$("#a-dining").removeClass("bg-red");
		loadContent("tabs-dining");
	});
});
$(function() {
	$("#tabs-housekeeping").click(function() {
		$("#tabs-dining").removeClass("active");
		$("#tabs-housekeeping").addClass("active");
		$("#tabs-wakeup").removeClass("active");
		$("#tabs-room").removeClass("active");
		$("#a-housekeeping").removeClass("bg-red");
		loadContent("tabs-housekeeping");
	});
});
$(function() {
	$("#tabs-wakeup").click(function() {
		$("#tabs-dining").removeClass("active");
		$("#tabs-housekeeping").removeClass("active");
		$("#tabs-wakeup").addClass("active");
		$("#tabs-room").removeClass("active");
		$("#a-wakeup").removeClass("bg-red");
		loadContent("tabs-wakeup");
	});
});
$(function() {
	$("#tabs-room").click(function() {
		$("#tabs-dining").removeClass("active");
		$("#tabs-housekeeping").removeClass("active");
		$("#tabs-wakeup").removeClass("active");
		$("#tabs-room").addClass("active");
		$("#a-room").removeClass("bg-red");
		loadContent("tabs-room");
	});
});


function loadContent(id) {
	$.get("order?action=getcontents&tabs=" + id, function(data) {
		if (id == "tabs-dining")
			writeTable(data);
		else if (id == "tabs-housekeeping")
			writeTableHouesekeeping(data);
		else if (id == "tabs-wakeup")
			writeTableWakeup(data);
		else if (id == "tabs-room")
			writeTableRoomStatus(data);
	});
}

function checkOrder() {
	$.get("order?action=checkorder", function(data) {
		if ($("#tabs-dining").hasClass("active")) {
			if (data[0] > 0) {
				loadContent("tabs-dining");
			}
			if (data[1] > 0) {
				$("#a-housekeeping").addClass("bg-red");
			}
			if (data[2] > 0) {
				$("#a-wakeup").addClass("bg-red");
			}
			if (data[3] > 0) {
				$("#a-room").addClass("bg-red");
			}
		} else if ($("#tabs-housekeeping").hasClass("active")) {
			if (data[0] > 0) {
				$("#a-dining").addClass("bg-red");
			}
			if (data[1] > 0) {
				loadContent("tabs-housekeeping");
			}
			if (data[2] > 0) {
				$("#a-wakeup").addClass("bg-red");
			}
			if (data[3] > 0) {
				$("#a-room").addClass("bg-red");
			}
		} else if ($("#tabs-wakeup").hasClass("active")) {
			if (data[0] > 0) {
				$("#a-dining").addClass("bg-red");
			}
			if (data[1] > 0) {
				$("#a-housekeeping").addClass("bg-red");
			}
			if (data[2] > 0) {
				loadContent("tabs-wakeup");
			}
			if (data[3] > 0) {
				$("#a-room").addClass("bg-red");
			}
		} else if ($("#tabs-romm").hasClass("active")) {
			if (data[0] > 0) {
				$("#a-dining").addClass("bg-red");
			}
			if (data[1] > 0) {
				$("#a-housekeeping").addClass("bg-red");
			}
			if (data[2] > 0) {
				$("#a-wakeup").addClass("bg-red");
			}
			if (data[3] > 0) {
				loadContent("tabs-room");
			}
		}
		// var amount = data[0] + data[1] + data[2] + data[3];
		var amount = data[0];
		if(amount > 0){
			runRing();
			goclicky();
		}
			
			
	});
}

function goclicky() {
	var x = screen.width / 2 - 400 / 2;
	var y = screen.height / 2 - 400 / 2;
	var mywindow = window.open("neworder.jsp", 'sharegplus',
			'height=400,width=400,left=' + x + ',top=' + y);
	mywindow.focus();
}

function sleepFor( sleepDuration ){
    var now = new Date().getTime();
    while(new Date().getTime() < now + sleepDuration){ /* do nothing */ } 
}

function checkedCheckbox(id) {
	var check = document.getElementById(id);
	if (check.checked) {
		var temp = $("#tempidchecked").val();
		temp += id + ",";
		$("#tempidchecked").val(temp);
	} else {
		var temp = $("#tempidchecked").val();
		var res = temp.replace(id + ",", "");
		$("#tempidchecked").val(res);
	}
}

function deletelist() {
	var listid = $("#tempidchecked").val();
	if (listid != "") {
		confirm("Delete list order", "Are you sure delete list order?", " No ",
				" Yes ", function() {
					listid = listid.substring(0, listid.length - 1);
					$.get("order?action=deletelist&listid=" + listid, function(
							data) {
						writeTable(data);
						jSuccess("Success", {
							HorizontalPosition : 'center',
							VerticalPosition : 'top'
						});
					});
				});
	} else
		alert("No order is checked!");
}

function startInterval() {
	setInterval(function() {
		loadAll();
	}, 5000);
}

$(function() {
	$("#btniconfilter").click(function() {
		$('#modalfilter').modal('show');
	});
});

$(function() {
	$("#btnfilter").click(
			function() {
				var service = $("#ftservice").val();
				var folio = $("#ftfolio").val();
				$.get("order?action=filter&service=" + service + "&folio="
						+ folio + "", function(data) {
					writeTable(data);
				});
				$('#modalfilter').modal('hide');
			});
});

function loadAll() {
	$.get("order?action=request", function(data) {
		writeTable(data);
	});
}

function addPlusginTable(tablename) {
	$("#" + tablename)
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
						},
						"aLengthMenu" : [ [ 10, 20, 50, 100, 200, -1 ],
								[ 10, 20, 50, 100, 200, "All" ] ]
					});
}

function runRing(){
	var x = document.getElementById("myAudio");
	x.play();
}

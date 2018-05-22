function writeTableWakeup(data) {
	var temp = 0;
	var no = 0;
	var text = "";
	var body = "";
	$
			.each(
					data,
					function(key, value) {
						if (value.status != "0") {
							no++;
							var checkdelete = "<td><input id='"
									+ value.id
									+ "' type='checkbox' style='margin-top:5px;' onclick='checkedCheckbox("
									+ value.id + ")'/></td>";
							var stt = "<td style='text-align: center;'>" + no
									+ "</td>";
							var service = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.service + "</td>";
							var folio = "<td style='text-align: center;' class='fontsizetr'>"
								+ value.folionum + "</td>";
							var post = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.datepost + "</td>";
							var datewakeup = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.datewakeup + "</td>";
							var timewakeup = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.hours + ":" + value.minutes + "</td>";
							var status = "<td style='text-align: center; font-weight: bold;'>";
							if (value.status == "1")
								status += "Confirmed";
							else if (value.status == "2")
								status += "Denied";
							else
								status += "Unknown";
							status += "</td>";
							var action = "<td style='text-align: center;' class='fontsizetr'><a class='cssdelete' style='cursor: pointer;' onclick=deleteWakeup(" + value.id + "," + value.folionum + ")>Delete</a></td>";

							body += "<tr>" + checkdelete + stt + service
									+ folio + post + datewakeup + timewakeup + status
									+ action + "</tr>";
						}
						if (value.status == "0") {
							temp++;
							var checkdelete = "<td></td>";
							var stt = "<td style='text-align: center;'>" + temp
									+ "</td>";
							var service = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.service + "</td>";
							var folio = "<td style='text-align: center;' class='fontsizetr'>"
								+ value.folionum + "</td>";
							var post = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.datepost + "</td>";
							var datewakeup = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.datewakeup + "</td>";
							var timewakeup = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.hours + ":" + value.minutes + "</td>";
							var action = "<td style='text-align: center;' class='fontsizetr'><a class='cssconfirm' style='cursor: pointer;' onclick=confirmWakeup(" + value.id + "," + value.folionum + ")>Confirm</a>";
							var status = "";
							text += "<tr style='background-color: #FF6347;'>"
									+ checkdelete + stt + service + folio
									+ post + datewakeup + timewakeup + action + status
									+ "</tr>";
						}

					});
	var table = "<table id='tablelistorder' class='table'><thead>" + "<tr>"
			+ "<th style='text-align: center; width: 42px;'></th>"
			+ "<th style='text-align: center; width: 5%;'>No</th>"
			+ "<th style='text-align: center; width: 15%;'>Service</th>"
			+ "<th style='text-align: center; width: 10%;'>Room</th>"
			+ "<th style='text-align: center; width: 22%;'>Time Post</th>"
			+ "<th style='text-align: center; width: 18%;'>Date Wakeup</th>"
			+ "<th style='text-align: center; width: 18%;'>Time Wakeup</th>"
			+ "<th style='text-align: center;'>Action</th>"
			+ "</tr> </thead><tbody id='bodylistorder'>" + text
			+ "</tbody></table>";

	var tableaction = "<table id='tablelistaction' class='table'><thead>"
			+ "<tr>"
			+ "<th style='text-align: center; width: 3%' onclick='deletelist()'><img src='img/icon_delete.png' width='20px;'></th>"
			+ "<th style='text-align: center; width: 5%;'>No</th>"
			+ "<th style='text-align: center; width: 15%;'>Service</th>"
			+ "<th style='text-align: center; width: 10%;'>Room</th>"
			+ "<th style='text-align: center; width: 22%;'>Time Post</th>"
			+ "<th style='text-align: center; width: 18%;'>Date Wakeup</th>"
			+ "<th style='text-align: center; width: 18%;'>Time Wakeup</th>"
			+ "<th style='text-align: center;'>Status</th>"
			+ "<th style='text-align: center;'>Action</th>"
			+ "</tr> </thead><tbody id='bodylistorder'>" + body
			+ "</tbody></table>";

	$("#divdatatable").html("");
	$("#divdatatable").html(table);
	addPlusginTable("tablelistorder");

	$("#divdatatablebottom").html("");
	$("#divdatatablebottom").html(tableaction);
	addPlusginTable("tablelistaction");
}

function confirmWakeup(id, folionum){
	$.get("order?action=confirmwk&id=" + id + "&folionum=" + folionum, function(
			data) {
		writeTableWakeup(data);
	});
}

function deleteWakeup(id, folionum){
	$.get("order?action=deletewk&id=" + id + "&folionum=" + folionum, function(
			data) {
		writeTableWakeup(data);
	});
}
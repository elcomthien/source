function writeTableHouesekeeping(data) {
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
							var detail = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.name + "</td>";
							var quantity = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.amount + "</td>";
							var folio = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.folionum + "</td>";
							var implementation = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.datesave + "</td>";
							var name = "<td style='text-align: center;' class='fontsizetr'>"
								+ value.guest + "</td>";
							var status = "<td style='text-align: center; font-weight: bold;'>";
							if (value.status == "1")
								status += "Confirmed";
							else if (value.status == "2")
								status += "Denied";
							else
								status += "Unknown";
							status += "</td>";
							var action = "<td style='text-align: center;' class='fontsizetr'><a class='cssdelete' style='cursor: pointer;' onclick=deleteHK(" + value.id + "," + value.folionum + ")>Delete</a></td>";

							body += "<tr>" + checkdelete + stt + service
									+ detail + quantity + folio + implementation + name + status
									+ action + "</tr>";
						}
						if (value.status == "0") {
							temp++;
							var checkdelete = "<td></td>";
							var stt = "<td style='text-align: center;'>" + temp
									+ "</td>";
							var service = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.service + "</td>";
							var detail = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.name + "</td>";
							var quantity = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.amount + "</td>";
							var folio = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.folionum + "</td>";
							var implementation = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.datesave + "</td>";
							var name = "<td style='text-align: center;' class='fontsizetr'>"
								+ value.guest + "</td>";
							var action = "<td style='text-align: center;' class='fontsizetr'><a class='cssconfirm' style='cursor: pointer;' onclick=confirmHK(" + value.id + "," + value.folionum + ")>Confirm</a>";
							var status = "";
							text += "<tr style='background-color: #FF6347;'>"
									+ checkdelete + stt + service + detail
									+ quantity + folio + implementation + name + action + status
									+ "</tr>";
						}
					});
	var table = "<table id='tablelistorder' class='table'><thead>" + "<tr>"
			+ "<th style='text-align: center; width: 42px;'></th>"
			+ "<th style='text-align: center; width: 5%;'>No</th>"
			+ "<th style='text-align: center; width: 10%;'>Service</th>"
			+ "<th style='text-align: center; width: 20%;'>Detail</th>"
			+ "<th style='text-align: center; width: 10%;'>Quantity</th>"
			+ "<th style='text-align: center; width: 10%;'>Room</th>"
			+ "<th style='text-align: center; width: 15%;'>Implementation</th>"
			+ "<th style='text-align: center; width: 15%;'>Housekeeping name</th>"
			+ "<th style='text-align: center;'>Action</th>"
			+ "</tr> </thead><tbody id='bodylistorder'>" + text
			+ "</tbody></table>";

	var tableaction = "<table id='tablelistaction' class='table'><thead>"
			+ "<tr>"
			+ "<th style='text-align: center; width: 3%' onclick='deletelist()'><img src='img/icon_delete.png' width='20px;'></th>"
			+ "<th style='text-align: center; width: 5%;'>No</th>"
			+ "<th style='text-align: center; width: 10%;'>Service</th>"
			+ "<th style='text-align: center; width: 20%;'>Detail</th>"
			+ "<th style='text-align: center; width: 10%;'>Quantity</th>"
			+ "<th style='text-align: center; width: 10%;'>Room</th>"
			+ "<th style='text-align: center; width: 15%;'>Implementation</th>"
			+ "<th style='text-align: center; width: 15%;'>Housekeeping name</th>"
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

function confirmHK(id, folionum){
	$.get("order?action=confirmhk&id=" + id + "&folionum=" + folionum, function(
			data) {
		writeTableHouesekeeping(data);
	});
}

function deleteHK(id, folionum){
	$.get("order?action=deletehk&id=" + id + "&folionum=" + folionum, function(
			data) {
		writeTableHouesekeeping(data);
	});
}
function writeTable(data) {
	var temp = 0;
	var no = 0;
	var text = "";
	var body = "";
	$
			.each(
					data,
					function(key, value) {
						if (value.trangthai != "0") {
							no++;
							var checkdelete = "<td><input id='"
									+ value.maorder
									+ "' type='checkbox' style='margin-top:5px;' onclick='checkedCheckbox("
									+ value.maorder + ")'/></td>";
							var stt = "<td style='text-align: center;'>" + no
									+ "</td>";
							var service = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.dichvu + "</td>";
							var detail = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.tenmonan + "</td>";
							var quantity = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.soluong + "</td>";
							var folio = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.phong + "</td>";
							var price = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.gia + "</td>";
							var dateorder = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.ngaydat + "</td>";
							var implementation = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.ngaythuchien + "</td>";
							var status = "<td style='text-align: center; font-weight: bold;'>";
							if (value.trangthai == "1")
								status += "Confirmed";
							else if (value.trangthai == "2")
								status += "Denied";
							else
								status += "Unknown";
							status += "</td>";
							var action = "";
							if(value.role == "ADMIN")
								action = "<td style='text-align: center;' class='fontsizetr'><a href='order?action=delete&id="
									+ value.maorder
									+ "' class='cssdelete'>Delete</a></td>";
							else
								action = "<td style='text-align: center;' class='fontsizetr'></td>";
							body += "<tr>" + checkdelete + stt + service
									+ detail + quantity + folio + price
									+ dateorder + implementation + status
									+ action + "</tr>";
						}
						if (value.trangthai == "0") {
							temp++;
							var checkdelete = "<td></td>";
							// var checkdelete = "";
							var stt = "<td style='text-align: center;'>" + temp
									+ "</td>";
							var service = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.dichvu + "</td>";
							var detail = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.tenmonan + "</td>";
							var quantity = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.soluong + "</td>";
							var folio = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.phong + "</td>";
							var price = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.gia + "</td>";
							var dateorder = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.ngaydat + "</td>";
							var implementation = "<td style='text-align: center;' class='fontsizetr'>"
									+ value.ngaythuchien + "</td>";
							// var action = "<td style='text-align: center;'
							// class='fontsizetr'><button class='btn
							// btn-link'><a
							// href='order?action=confirm&id="+value.maorder+"&folionum="+value.phong+"&service="+value.dichvu+"&readdate="+value.ngaythuchien+"'>Confirm</a></button></td>";
							var action = "<td style='text-align: center;' class='fontsizetr'><a href='order?action=confirm&id="
									+ value.maorder
									+ "&folionum="
									+ value.phong
									+ "&service="
									+ value.dichvu
									+ "&readdate="
									+ value.ngaythuchien
									+ "' class='cssconfirm' style='margin-right: 20px;'>Confirm</a> | "
									+ "<a href='order?action=deny&id="
									+ value.maorder
									+ "&folionum="
									+ value.phong
									+ "&service="
									+ value.dichvu
									+ "&readdate="
									+ value.ngaythuchien
									+ "' class='cssconfirm' style='margin-left: 20px;'>Deny</a></td>";
							// var status = "<td style='text-align:
							// center;'><img src='img/unconfirm.png'
							// width='20px;'></td>";
							var status = "";
							text += "<tr style='background-color: #FF6347;'>"
									+ checkdelete + stt + service + detail
									+ quantity + folio + price + dateorder
									+ implementation + action + status
									+ "</tr>";
						}

					});
	var table = "<table id='tablelistorder' class='table'><thead>" + "<tr>"
			+ "<th style='text-align: center; width: 42px;'></th>"
			+ "<th style='text-align: center; width: 3%;'>No</th>"
			+ "<th style='text-align: center; width: 9%;'>Service</th>"
			+ "<th style='text-align: center; width: 20%;'>Detail</th>"
			+ "<th style='text-align: center; width: 8%;'>Quantity</th>"
			+ "<th style='text-align: center; width: 5%;'>Room</th>"
			+ "<th style='text-align: center; width: 5%;'>Price</th>"
			+ "<th style='text-align: center; width: 10%;'>Date Order</th>"
			+ "<th style='text-align: center; width: 15%;'>Implementation</th>"
			+ "<th style='text-align: center;'>Action</th>"
			// + "<th style='text-align: center;' width=''>Status</th>"
			+ "</tr> </thead><tbody id='bodylistorder'>" + text
			+ "</tbody></table>";

	var tableaction = "<table id='tablelistaction' class='table'><thead>"
			+ "<tr>"
			+ "<th style='text-align: center; width: 3%' onclick='deletelist()'><img src='img/icon_delete.png' width='20px;'></th>"
			+ "<th style='text-align: center; width: 3%;'>No</th>"
			+ "<th style='text-align: center; width: 9%;'>Service</th>"
			+ "<th style='text-align: center; width: 20%;'>Detail</th>"
			+ "<th style='text-align: center; width: 8%;'>Quantity</th>"
			+ "<th style='text-align: center; width: 5%;'>Room</th>"
			+ "<th style='text-align: center; width: 5%;'>Price</th>"
			+ "<th style='text-align: center; width: 10%;'>Date Order</th>"
			+ "<th style='text-align: center; width: 15%;'>Implementation</th>"
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
	if(temp > 0)
		runRing();
}

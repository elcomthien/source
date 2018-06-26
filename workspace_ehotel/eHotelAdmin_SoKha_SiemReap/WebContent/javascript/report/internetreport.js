var internetrpt = new function InternetReport() {
	var my = this;
	var table = new Table();
	var Index = 0;
	this.List = new Array();
	var defaults = {
		divName : "div_Table",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	};
	this.load = function() {
		my.loadtime();
		table.init(defaults);

	};
	this.loadtime = function() {
		var html = "";
		html += "<div style=\"float: left;width: 100px;\">";
		html += langpms.from_date;
		html += "</div>";

		html += "<div style=\"float: left;width:150px;\">";
		html += "<input size=\"10\" id=\"r_date1\" /><button id=\"r_btn1\" class='buttom_calenda'>...</button>";
		html += "</div>";

		html += "<div style=\"float: left;width: 100px;\">";
		html += langpms.to_date;
		html += "</div>";
		html += "<div style=\"float: left;width: 150px;\">";
		html += "<input size=\"10\" id=\"r_date2\" /><button id=\"r_btn2\" class='buttom_calenda'>...</button>";
		html += "</div>";
		html += "<select id='filter-room' style='width: 120px; height: 21px; float: right; margin-right: 20px;' onchange='internetrpt.filter();'></select>";
		$("div_time").innerHTML = html;
		$("bt_excel").onclick = my.excel;
		$("bt_print").onclick = my.print;
		var now = new Date();
		var d2 = now.getDate();
		if (d2 < 10)
			d2 = "0" + d2;
		var m2 = (now.getMonth() + 1);
		if (m2 < 10)
			m2 = "0" + m2;
		var time2 = d2 + "/" + m2 + "/" + now.getFullYear();
		$("r_date2").value = time2;

		var prev = new Date(now.setDate(now.getDate() - 6));
		var d1 = prev.getDate();
		if (d1 < 10)
			d1 = "0" + d1;
		var m1 = (prev.getMonth() + 1);
		if (m1 < 10)
			m1 = "0" + m1;
		var time1 = d1 + "/" + m1 + "/" + prev.getFullYear();
		$("r_date1").value = time1;
		var cal1 = Calendar.setup({

			inputField : "r_date1",
			trigger : "r_btn1",
			onSelect : my.changeDatefrom,
			dateFormat : "%d/%m/%Y",
			date : prev

		});
		var cal2 = Calendar.setup({
			inputField : "r_date2",
			trigger : "r_btn2",
			onSelect : my.changeDateNow,
			dateFormat : "%d/%m/%Y",
			date : now
		});

		my.get();
	};

	this.excel = function() {
		tableToExcel('data_table_room', 'Movie Room');
	};
	this.print = function() {
		printDiv();
	};
	this.changeDateNow = function(cal) {
		var date = cal.selection.get();
		var now = new Date();
		var end = new Date();
		if (date) {
			date = Calendar.intToDate(date);
			var t = Calendar.printDate(date, "%Y/%m/%d");
			end = new Date(t);
		}

		if (now.getTime() < date.getTime()) {
			cal.refresh();
			d = Calendar.intToDate(now);
			var t = Calendar.printDate(d, "%d/%m/%Y");
			$("r_date2").value = t;
			var albox = new alertBox();
			albox.show(langpms.calenda_now);
			this.hide();
			return;
		}
		my.get();
		this.hide();
	};
	this.changeDatefrom = function(cal) {
		var date = cal.selection.get();
		var now = new Date();
		var end = new Date();
		if (date) {
			date = Calendar.intToDate(date);
			var t = Calendar.printDate(date, "%Y/%m/%d");
			end = new Date(t);
		}
		if (now.getTime() < date.getTime()) {
			cal.refresh();
			d = Calendar.intToDate(now);
			var t = Calendar.printDate(d, "%d/%m/%Y");
			$("r_date1").value = t;
			var albox = new alertBox();
			albox.show(langpms.calenda_now);
			this.hide();
			return;
		}

		my.get();
		this.hide();
	};
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_listhotel'>";
		html += "<table id='data_table_room' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='center'  style='text-align: center;'  width=\"" + 15
				+ "%\"  >";
		html += "Room";
		html += "</th>";
		html += "<th align='center' width=\"" + 20
				+ "%\" style='text-align: center;'  >";
		html += "Serinumber";
		html += "</th>";
		html += "<th align='center' width=\"" + 30
				+ "%\" style='text-align: center;'  >";
		html += "Date";
		html += "</th>";
		html += "<th align='center' width=\"" + 15
				+ "%\" style='text-align: center;'  >";
		html += "Price";
		html += "</th>";
		html += "<th align='center'  style='text-align: center;'  width=\"" + 20
				+ "%\"  >";
		html += "Currency";
		html += "</th>";
		html += "</tr>";
		var temp = "";
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0)
				html += "<tr class=\"" + this.bgRow2 + "\">";
			else
				html += "<tr class=\"" + this.bgRow1 + "\">";
			if (this.List[i].Room == temp) {
				html += "<td align=\"center\"  style='text-align: center;'>";
				html += "</td>";
			} else {
				html += "<td align=\"center\"  style='text-align: center;'>";
				html += this.List[i].Room;
				html += "</td>";
			}
			temp = this.List[i].Room;
			html += "<td align=\"center\"  style='text-align: center;'>";
			html += this.List[i].Key;
			html += "</td>";
			html += "<td align='center'  style='text-align: center;'>";
			html += this.List[i].Time;
			html += "</td>";
			html += "<td align='center'  style='text-align: center;'>";
			html += this.List[i].Price;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += 'USD$';
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		return html;
	};
	this.get = function() {
		var from = $("r_date1").value;
		var to = $("r_date2").value;
		var url = "InternetChargeReport?CMD=1";
		url += "&from=" + from;
		url += "&to=" + to;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _room = it[i].getElementsByTagName("room")[0].childNodes[0].nodeValue;
				var _key = it[i].getElementsByTagName("key")[0].childNodes[0].nodeValue;
				var _time = it[i].getElementsByTagName("time")[0].childNodes[0].nodeValue;
				var _price = it[i].getElementsByTagName("price")[0].childNodes[0].nodeValue;
				arr[i] = new itemData(_id,_room, _key, _time, _price);
			}
			my.List = arr;
			table.load(my.List);
			my.loadRoom();
		};
	};
	this.filter = function() {
		var ftroom = document.getElementById("filter-room").value;
		var from = $("r_date1").value;
		var to = $("r_date2").value;
		var url = "InternetChargeReport?CMD=1";
		url += "&from=" + from;
		url += "&to=" + to;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var it = tx.getElementsByTagName("Item");
			var j = 0;
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _room = it[i].getElementsByTagName("room")[0].childNodes[0].nodeValue;
				var _key = it[i].getElementsByTagName("key")[0].childNodes[0].nodeValue;
				var _time = it[i].getElementsByTagName("time")[0].childNodes[0].nodeValue;
				var _price = it[i].getElementsByTagName("price")[0].childNodes[0].nodeValue;
				if (_room == ftroom || ftroom == "all") {
					arr[j] = new itemData(_id,_room, _key, _time, _price);
					j++;
				}
			}
			my.List = arr;
			table.load(my.List);
		};
	};
	function itemData(id, room, key, time, price) {
		this.Id = id;
		this.Room = room;
		this.Key = key;
		this.Time = time;
		this.Price = price;
	}
	this.loadRoom = function() {
		var from = $("r_date1").value;
		var to = $("r_date2").value;
		var url = "InternetChargeReport?CMD=2";
		url += "&from=" + from;
		url += "&to=" + to;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			document.getElementById("filter-room").innerHTML = tx;
		};
	};
};
function reload() {
	internetrpt.get();
}
window.onload = function() {
	internetrpt.load();
};

var tableToExcel = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', base64 = function(
			s) {
		return window.btoa(unescape(encodeURIComponent(s)));
	}, format = function(s, c) {
		return s.replace(/{(\w+)}/g, function(m, p) {
			return c[p];
		});
	};
	return function(table, name) {
		if (!table.nodeType)
			table = document.getElementById(table);
		var ctx = {
			worksheet : name || 'Worksheet',
			table : table.innerHTML
		};
		window.location.href = uri + base64(format(template, ctx));
	};
})();

function printDiv() {
	var divToPrint = document.getElementById('data_table_room');
	newWin = window.open("");
	newWin.document.write(divToPrint.outerHTML);
	newWin.print();
	newWin.close();
}
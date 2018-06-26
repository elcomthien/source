function Guest() {
	var my = this;
	this.roomId = -1;
	var list = new ListGuest();
	this.load = function(id) {
		my.roomId = id;
		list.load(id);
	};
}
function Messages() {
	var my = this;
	this.roomId = -1;
	var list = new ListMessages();
	this.load = function(id) {
		my.roomId = id;
		list.load(id);
	};
}
function Bill() {
	var my = this;
	this.roomId = -1;
	var list = new ListBill();
	this.load = function(id) {
		my.roomId = id;
		list.load(id);
	};
}
function SetBox() {
	var my = this;
	var roomid = -1;
	var key = "";
	var list = new ListSTB();
	this.load = function(id) {
		roomid = id;
		if (id == -1) {
			var albox = new alertBox();
			albox.show("Please set Room");
			return;
		}
		list.load(id);
	};
	this.seach = function(k) {
		key = k;
		list.seach(key);
	};
}
function Records() {
	var my = this;
	this.roomId = -1;
	var list = new ListRecords();
	this.load = function(id) {
		my.roomId = id;
		list.load(id);
	}
}

function ListGuest() {
	var my = this;
	var roomId = -1;
	var Index = 0;
	this.page = 5;
	var table = new Table();
	this.count = 0;
	this.load = function(id) {
		roomId = id;
		my.get(roomId);
	}
	var defaults = {
		divName : "div_table2",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	}
	table.init(defaults);
	table.dataBind = function() {
		var html = "";
		html += "<div>";
		html += "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='center' valign=\"middle\"  width=\"" + 7 + "%\">";
		html += langpms.no;
		html += "</th>";
		html += "<th align='left' width=\"" + 20 + "%\" style='text-align: left'>";
		html += langpms.name;
		html += "</th>";
		html += "<th align='left' width=\"" + 10 + "%\" style='text-align: left'>";
		html += "UniquePin";
		html += "</th>";
		html += "<th align='left' width=\"" + 10 + "%\" style='text-align: center'>";
		html += "Pincode";
		html += "</th>";
		html += "<th align='left' width=\"" + 10 + "%\" style='text-align: center'>";
		html += "Checkin";
		html += "</th>";
		html += "<th align='left' width=\"" + 10 + "%\" style='text-align: center'>";
		html += "Checkout";
		html += "</th>";
		html += "<th align='center' width=\"" + 12 + "%\" style='text-align: center'>";
		html += langpms.MainGuest;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" style='text-align: center'>";
		html += "CheckOut";
		html += "</th>";
		html += "</tr>";
		var begin = Index * my.page;
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\">";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\">";
			}
			html += "<td align=\"center\" valign=\"middle\">";
			html += begin + i + 1;
			html += "</td>";

			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Name;
			html += "</td>";
			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Unique;
			html += "</td>";
			html += "<td align=\"left\" style='text-align: center'>";
			html += this.List[i].Pincode;
			html += "</td>";
			html += "<td align=\"left\" style='text-align: center'>";
			html += this.List[i].Arrival;
			html += "</td>";
			html += "<td align=\"left\" style='text-align: center'>";
			html += this.List[i].Out;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			if (this.List[i].Status == 0) {
				html += "<img src=\"../icon/16-circle-green.png\"></img>";
			}
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += "<img src=\"../icon/16-square-green-remove.png\" id=\""
					+ this.List[i].Id + "\"></img>";
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		return html;
	};
	this.get = function(id) {
		var wt = new Waiting();
		var url = "folioPms?CMD=2";
		url += "&pageIndex=" + Index;
		url += "&id=" + id;
		url += "&page=" + my.page;
		url += "&r=" + Math.random();
		wt.show(null);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");

			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _arrival = it[i].getElementsByTagName("Arrival")[0].childNodes[0].nodeValue;
				var _out = it[i].getElementsByTagName("out")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				var _pincode = it[i].getElementsByTagName("pincode")[0].childNodes[0].nodeValue;
				var _unique = it[i].getElementsByTagName("unique")[0].childNodes[0].nodeValue;
				arr[i] = new itemGuest(_id, _name, _arrival, _out, _status, _pincode);
				arr[i].Unique = _unique;

			}
			table.load(arr);
			wt.hide();
			my.renderEvent();
		};
	};
	this.renderEvent = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var td = tr[i].getElementsByTagName("td");
			var im = td[7].getElementsByTagName("img");
			im[0].onclick = function() {
				var id = this.getAttribute("id");
				my.checkOut(id);

			};
		}
	};
	this.checkOut = function(id) {

		var conform = new conformBox();
		conform.show(langpms.checkout);
		conform.accept = function() {
			var url = "folioPms?CMD=12";
			url += "&id=" + id;
			url += "&r=" + Math.random();
			var f = new AjaxGetXML(url);
			f.complet = function(tx) {
				my.get(roomId);
				room.reload();
			};
		};
	};
}
function ListMessages() {
	var my = this;
	var roomId = -1;
	var Index = 0;
	this.page = 5;
	var table = new Table();
	this.count = 0;
	this.load = function(id) {
		roomId = id;
		my.get(roomId);
	};
	var defaults = {
		divName : "div_table2",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	};
	table.init(defaults);
	table.dataBind = function() {
		var html = "";
		html += "<div>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"98%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='center'  valign=\"middle\"  width=\"" + 8
				+ "%\"  >";
		html += langpms.no;
		html += "</th>";
		html += "<th align='left' width=\"" + 15 + "%\" valign=\"middle\"  >";
		html += langpms.sender;
		html += "</th>";
		html += "<th align='left' width=\"" + 15 + "%\" valign=\"middle\"  >";
		html += langpms.subject_mess;
		html += "</th>";
		html += "<th align='center' width=\"" + 20 + "%\" valign=\"middle\"  >";
		html += "Send";
		html += "</th>";
//		html += "<th align='left' width=\"" + 20 + "%\" style='text-align: center;'>";
//		html += "Read";
//		html += "</th>";
		html += "</tr>";
		var begin = Index * my.page;
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class='mytable_row1' id=\""
						+ this.List[i].Id + "\"title='"+this.List[i].Content.replace("<br/>","\n")+"'>";
			} else {
				html += "<tr class='mytable_row2' id=\""
						+ this.List[i].Id + "\" title='"+this.List[i].Content.replace("<br/>","\n")+"'>";
			}
			html += "<td align=\"center\"  valign=\"middle\">";
			html += begin + i + 1;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += converHTML(this.List[i].From);
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += converHTML(this.List[i].Name);
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			html += this.List[i].Data;
			html += "</td>";
//			html += "<td align=\"left\"  style='text-align: center;'>";
//			html += this.List[i].Read;
//			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		return html;
	};
	var wt = new Waiting();
	this.get = function(id) {
		var url = "folioPms?CMD=3";
		url += "&pageIndex=" + Index;
		url += "&id=" + id;
		url += "&page=" + my.page;
		url += "&r=" + Math.random();
		wt.show(null);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _content = it[i].getElementsByTagName("content")[0].childNodes[0].nodeValue;
				var _date = it[i].getElementsByTagName("date")[0].childNodes[0].nodeValue;
				var _from = it[i].getElementsByTagName("from")[0].childNodes[0].nodeValue;
				var _read = it[i].getElementsByTagName("read")[0].childNodes[0].nodeValue;
				_name = converHTML(_name);
				arr[i] = new itemMess(_id, _name, _content, _date);
				arr[i].From = _from;
				arr[i].Read = _read;
			}
			table.load(arr);
			wt.hide();
		};
	};
}
function ListBill() {
	var my = this;
	var roomId = -1;
	var Index = 0;
	this.page = 5;
	var table = new Table();
	this.count = 0;
	this.load = function(id) {
		roomId = id;
		my.get(roomId);
	};
	this.loadIdex = function(index) {
		Index = index;
		my.get(roomId);
	};
	this.nextindex = function() {
		var cpage = ((my.count - 1) / my.page) - 1;
		if (Index < cpage)
			Index++;
		my.loadIdex(Index);
	};
	this.backindex = function() {
		if (Index > 0)
			Index--;
		my.loadIdex(Index);
	};
	var defaults = {
		divName : "div_table2",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	};
	table.init(defaults);
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_listbill'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"98%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='center'  valign=\"middle\"  width=\"" + 8 + "%\">";
		html += langpms.no;
		html += "</th>";
		html += "<th align='left' width=\"" + 40 + "%\" valign=\"middle\">";
		html += langpms.name;
		html += "</th>";
		html += "<th align='left' width=\"" + 20 + "%\" valign=\"middle\">";
		html += langpms.create_date;
		html += "</th>";
		html += "<th align='left' width=\"" + 15 + "%\" valign=\"middle\">";
		html += langpms.amount;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += langpms.quantity;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += langpms.unit;
		html += "</th>";
		html += "</tr>";
		var begin = Index * my.page;
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\">";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\">";
			}
			html += "<td align=\"center\" valign=\"middle\">";
			html += begin + i + 1;
			html += "</td>";
			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Name;
			html += "</td>";
			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Date;
			html += "</td>";
			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Amount;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].Quantity;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].Unit;
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		html += "<div id=\"div_page2\" class='div_page' style='margin-top:20px;'>";
		if (my.count > 5) {
			var page = my.count / 5;
			html += createPaging(page, Index);
		}
		html += "</div>";
		return html;
	}
	this.renderEvent = function() {
		if ($("div_page2") != undefined) {
			var div = $("div_page2");
			var a = div.getElementsByTagName("a");
			for ( var i = 0; i < a.length; i++) {
				var item = a[i];
				item.onclick = function(e) {
					my.loadIdex(this.id);
				};
			}
			var div1 = $("div_page2").getElementsByTagName("div");
			if (div1.length > 0) {
				div1[1].onclick = function() {
					my.nextindex();
				};
				div1[0].onclick = function() {
					my.backindex();
				};
			}
		}
	}
	var wt = new Waiting();
	this.get = function(id) {
		var url = "folioPms?CMD=4";
		url += "&pageIndex=" + Index;
		url += "&id=" + id;
		url += "&page=" + my.page;
		url += "&r=" + Math.random();
		wt.show(null);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _amount = it[i].getElementsByTagName("amount")[0].childNodes[0].nodeValue;
				var _quantity = it[i].getElementsByTagName("quantity")[0].childNodes[0].nodeValue;
				var _unit = it[i].getElementsByTagName("unit")[0].childNodes[0].nodeValue;
				var _date = it[i].getElementsByTagName("date")[0].childNodes[0].nodeValue;
				arr[i] = new itemBill(_id, _name, _amount, _quantity, _unit);
				arr[i].Date = _date;
			}
			table.load(arr);
			my.renderEvent();
			wt.hide();
		}
	}
}
function ListSTB() {
	var key = "";
	var my = this;
	var roomId = -1;
	var Index = 0;
	this.page = 5;
	var table = new Table();
	var setup = new SetupSTB();
	this.count = 0;
	this.ListCheck = new Vector();
	this.role = -1;
	this.load = function(id) {
		roomId = id;
		my.get(roomId);
	}
	this.seach = function(k) {
		key = k;
		setup.seach(key);
	}
	this.reload = function() {
		my.get(roomId);
	}
	var defaults = {
		divName : "div_table2",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	}
	table.init(defaults);
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_liststb'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"98%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='center' valign=\"middle\"  width=\"" + 8 + "%\">";
		html += langpms.no;
		html += "</th>";
		html += "<th align='left' width=\"" + 30 + "%\" valign=\"middle\">";
		html += langpms.smartcard;
		html += "</th>";
		html += "<th align='left' width=\"" + 30 + "%\" valign=\"middle\">";
		html += "IP";
		html += "</th>";
		html += "<th align='center' width=\"" + 20 + "%\" valign=\"middle\">";
		html += langpms.create_date;
		html += "</th>";

		html += "</tr>";
		var begin = Index * my.page;
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\">";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\">";
			}
			html += "<td align=\"center\" valign=\"middle\">";
			html += begin + i + 1;
			html += "</td>";
			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Key;
			html += "</td>";
			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Ip;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].Date;
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		html += "<div class='div_addSTB' align=\"center\">";
		html += "<div class='div_addstb1'>";
		if (my.role != -1) {
			html += "<input type=\"button\" value= " + langpms.addstb
					+ " id=\"form_textbox_add\"  width='40px;'>";
			html += "<input type=\"button\" value= "
					+ langpms.removestb
					+ " id=\"form_textbox_remove\" style='margin-left: 10px;' width='70px;'>";
		}
		html += "</div>";
		html += "</div>";
		return html;
	}
	this.shiftSelect = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			if (i % 2 == 0) {
				tr[i].className = table.bgRow1;
			} else {
				tr[i].className = table.bgRow2;
			}

			for ( var j = 0; j < my.ListCheck.size(); j++) {
				if (tr[i].id == my.ListCheck.get(j)) {
					tr[i].className = table.classRowsel;
					break;
				}
			}
		}
	};
	this.selectItem = function(id) {
		my.ListCheck = new Vector();
		my.ListCheck.add(id);
		my.shiftSelect();
	};
	this.renderEvent = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var item = tr[i];
			item.onclick = function(e) {
				my.selectItem(this.id);
			};
		}
		;
		if (my.role != -1) {
			$("form_textbox_add").onclick = function() {
				setup.show(roomId);
				setup.update = function() {
					my.reload();
				};
			};
			$("form_textbox_remove").onclick = function() {
				if (my.ListCheck.size() > 0) {
					var stb = my.ListCheck.get(0);
					var conform = new conformBox();
					conform.show(langpms.remove_stb);
					conform.accept = function() {
						var url = "folioPms?CMD=9";
						url += "&t=" + Math.random();
						url += "&stb=" + stb;
						var f = new AjaxGetText(url);
						f.complet = function(tx) {
							my.reload();
						}
					}
				} else {
					var albox = new alertBox();
					albox.show(langpms.remove_stb_check);
				}
			};
		}
	};
	var wt = new Waiting();
	this.get = function(id) {
		var url = "folioPms?CMD=5";
		url += "&pageIndex=" + Index;
		url += "&id=" + id;
		url += "&page=" + my.page;
		url += "&key=" + key;
		url += "&r=" + Math.random();
		wt.show(null);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			var it = tx.getElementsByTagName("Item");
			my.count = xml[0].getAttribute("count");
			my.role = xml[0].getAttribute("role");
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _ip = it[i].getElementsByTagName("ip")[0].childNodes[0].nodeValue;
				var _keycode = it[i].getElementsByTagName("keycode")[0].childNodes[0].nodeValue;
				var _date = it[i].getElementsByTagName("date")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				arr[i] = new itemSTB(_id, _ip, _keycode, _date, _status);
			}
			table.load(arr);
			my.renderEvent();
			wt.hide();
		};
	};
}
function ListAddSTB() {
	var my = this;
	var roomId = -1;
	var Index = 0;
	this.page = 5;
	var table = new Table();
	this.count = 0;
	var key = "";
	this.list = new Array();
	this.load = function(id) {
		this.list = new Array();
		Index = 0;
		roomId = id;
		my.get(roomId);
	};
	this.seach = function(k) {
		this.list = new Array();
		Index = 0;
		key = k;
		my.get(roomId);
	};
	this.loadIndex = function(index) {
		Index = index;
		my.loadData();
	};
	this.nextindex = function() {
		var cpage = ((my.count - 1) / my.page) - 1;
		if (Index < cpage)
			Index++;
		my.loadIndex(Index);
	};
	this.backindex = function() {
		if (Index > 0)
			Index--;
		my.loadIndex(Index);
	};
	var defaults = {
		divName : "listaddstb",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	};
	this.loadData = function() {
		var b = Index * this.page;
		var e = (Number(Index) + 1) * my.page;
		if (e > my.count)
			e = my.count;

		var arr = new Array();
		for ( var i = b; i < e; i++) {
			arr[i - b] = my.list[i];
		}
		table.load(arr);
		my.renderEvent();
	};
	table.init(defaults);
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_listaddstb'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"98%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='center' valign=\"middle\" width=\"" + 9 + "%\">";
		html += langpms.no;
		html += "</th>";
		html += "<th align='left' width=\"" + 30 + "%\" valign=\"middle\">";
		html += langpms.smartcard;
		html += "</th>";
		html += "<th align='left' width=\"" + 30 + "%\" valign=\"middle\">";
		html += "IP";
		html += "</th>";

		html += "</tr>";
		var begin = Index * my.page;
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\">";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\">";
			}
			html += "<td align=\"center\" valign=\"middle\">";
			html += begin + i + 1;
			html += "</td>";
			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Key;
			html += "</td>";
			html += "<td align=\"left\" valign=\"middle\">";
			html += this.List[i].Ip;
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		html += "<div class='div_page_row' align=\"center\">";
		html += "<div id=\"div_page3\" class='div_page_stb'>";
		if (my.count > 5) {
			var page = my.count / 5;
			html += createPaging(page, Index);
		}
		html += "</div>";
		html += "</div>";
		return html;
	};
	this.renderEvent = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var item = tr[i];
			item.onclick = function(e) {
				my.selectItem(this.id);
			};
			item.ondblclick = function() {
				my.ondblclick(this.id);
			};
		}
		;
		if ($("div_page3") != undefined) {
			var div = $("div_page3");
			var a = div.getElementsByTagName("a");
			for ( var i = 0; i < a.length; i++) {
				var item = a[i];
				item.onclick = function(e) {
					my.loadIndex(this.id);
				};
			}
			var div1 = $("div_page3").getElementsByTagName("div");
			if (div1.length > 0) {
				div1[0].onclick = function() {
					my.backindex();
				};
				div1[1].onclick = function() {
					my.nextindex();
				};
			}
		}
	};
	this.ondblclick = function(id) {
	};
	this.ListCheck = new Vector();
	this.selectItem = function(id) {
		my.ListCheck = new Vector();
		my.ListCheck.add(id);
		my.shiftSelect();
	};
	this.getSelectId = function() {
		if (my.ListCheck.size() > 0)
			return my.ListCheck.get(0);
		return -1;
	};
	this.shiftSelect = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			if (i % 2 == 0) {
				tr[i].className = table.bgRow1;
			} else {
				tr[i].className = table.bgRow2;
			}

			for ( var j = 0; j < my.ListCheck.size(); j++) {
				if (tr[i].id == my.ListCheck.get(j)) {
					tr[i].className = table.classRowsel;
					break;
				}
			}
		}
	};
	this.get = function(id) {
		var wt = new Waiting();
		var url = "folioPms?CMD=7";
		url += "&pageIndex=" + Index;
		url += "&id=" + id;
		url += "&page=" + my.page;
		url += "&key=" + key;
		url += "&r=" + Math.random();
		wt.show(3000);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _ip = it[i].getElementsByTagName("ip")[0].childNodes[0].nodeValue;
				var _keycode = it[i].getElementsByTagName("keycode")[0].childNodes[0].nodeValue;
				var _date = it[i].getElementsByTagName("date")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				my.list[i] = new itemSTB(_id, _ip, _keycode, _date, _status);
			}
			my.count = my.list.length;
			my.loadData();
			wt.hide();
		};
	};
}
function SetupSTB() {
	var my = this;
	var layer = new layer_vitual();
	var roomId = -1;

	this.show = function(id) {
		roomId = id;
		layer.show();
		var html = "";
		var url = "folioPms?CMD=6";
		url += "&id=" + id;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail");
			$("buton_cancel").onclick = my.cancel;
			my.addlistSTB();
		};
	};
	var list = new ListAddSTB();
	/**
	 * add list
	 * 
	 * @return
	 */
	this.addlistSTB = function() {
		list.load(roomId);
	};
	list.ondblclick = function(id) {
		my.doAdd(id);
	};
	this.seach = function(key) {
		list.seach(key);
	};
	this.doAdd = function(stb) {
		var url = "folioPms?CMD=8";
		url += "&t=" + Math.random();
		url += "&room=" + roomId;
		url += "&stb=" + stb;
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			if (tx == "OK") {
				my.hide();
				my.update();
			} else {
				var albox = new alertBox();
				albox.show(langpms.stb_assigned + ":" + tx);
			}
		};
	};
	this.accept = function() {
		var stb = list.getSelectId();
		if (stb == -1) {
			var albox = new alertBox();
			albox.show(langpms.insert_stb);
			return;
		}
		my.doAdd(stb);
	};
	this.update = function() {
	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	};
}
function ListRecords() {
	var my = this;
	var roomId = -1;
	var Index = 0;
	this.page = 5;
	var table = new Table();
	this.count = 0;
	this.load = function(id) {
		roomId = id;
		my.get(roomId);
	};
	var defaults = {
		divName : "div_table2",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	};
	table.init(defaults);
	table.dataBind = function() {
		var html = "";
		html += "<div>";
		html += "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html+="<th align='center' valign=\"middle\" width=\""+8+"%\" >";
		html+="No";
		html+="</th>";
		html+="<th align='center' width=\""+24+"%\" valign=\"middle\" >";		
		html+="Channel";
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\" >";
		html+="SerNumber";
		html+="</th>";	
		html+="<th align='center' width=\""+23+"%\" valign=\"middle\" >";		
		html+="Start";
		html+="</th>";
		html+="<th align='center' width=\""+23+"%\" valign=\"middle\" >";
		html+="Stop";
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\" >";
		html+="Status";
		html += "</th>";
		html += "</tr>";
		var begin = Index * my.page;
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\">";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\">";
			}
			html += "<td align=\"center\" valign=\"middle\">";
			html += begin + i + 1;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].ChannelName;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].SerNumber;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].StartTime;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].StopTime;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
//			html += this.List[i].Status;
			 if(this.List[i].Status==0)
			 {
			 html+="<img src=\"../images/0.png\" width='20px;' alt='New record' onclick='clickUpdateStatus("+this.List[i].Id+")'></img>";
			 }else if(this.List[i].Status==1)
			 {
				 html+="<img src=\"../images/1.png\" width='20px;' alt='Out of memory'></img>";
			 }else if(this.List[i].Status==2)
			 {
				 html+="<img src=\"../images/2.png\" width='20px;' alt='Recording'></img>";
			 }else if(this.List[i].Status==3)
			 {
				 html+="<img src=\"../images/3.png\" width='20px;' alt='Success'></img>";
			 }else if(this.List[i].Status==4)
			 {
				 html+="<img src=\"../images/4.png\" width='20px;' alt='Waiting delete'></img>";
			 }
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		return html;
	};
	this.get = function(id) {
		var wt = new Waiting();
		var url = "folioPms?CMD=18";
		url += "&pageIndex=" + Index;
		url += "&id=" + id;
		url += "&page=" + my.page;
		url += "&r=" + Math.random();
		wt.show(null);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var channelname = it[i].getElementsByTagName("channelname")[0].childNodes[0].nodeValue;
				var urlrecord = it[i].getElementsByTagName("urlrecord")[0].childNodes[0].nodeValue;
				var sernumber = it[i].getElementsByTagName("sernumber")[0].childNodes[0].nodeValue;
				var starttime = it[i].getElementsByTagName("starttime")[0].childNodes[0].nodeValue;
				var stoptime = it[i].getElementsByTagName("stoptime")[0].childNodes[0].nodeValue;
				var status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				var privatechannelname = it[i].getElementsByTagName("privatechannelname")[0].childNodes[0].nodeValue;
				var sizeinkb = it[i].getElementsByTagName("sizeinkb")[0].childNodes[0].nodeValue;
				var urlpicture = it[i].getElementsByTagName("urlpicture")[0].childNodes[0].nodeValue;
				arr[i] = new itemRecord(id,channelname,urlrecord,sernumber,starttime,stoptime,status,privatechannelname,sizeinkb,urlpicture);
//				cntonl += 1;
			}
			table.load(arr);
			wt.hide();
			my.renderEvent();
		};
	};
	this.renderEvent = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var td = tr[i].getElementsByTagName("td");
			var im = td[8].getElementsByTagName("img");
			im[0].onclick = function() {
				var id = this.getAttribute("id");
				my.checkOut(id);
			};
		}
	};
	this.checkOut = function(id) {
//		var conform = new conformBox();
//		conform.show(langpms.checkout);
//		conform.accept = function() {
//			var url = "folioPms?CMD=12";
//			url += "&id=" + id;
//			url += "&r=" + Math.random();
//			var f = new AjaxGetXML(url);
//			f.complet = function(tx) {
//				my.get(roomId);
//				room.reload();
//			}
//		}
	};
}
function itemSTB(id, ip, keycode, date, status) {
	this.Id = id;
	this.Ip = ip;
	this.Key = keycode;
	this.Date = date;
	this.Status = status;
}
function itemBill(id, name, amount, quantity, unit) {
	this.Id = id;
	this.Name = name;
	this.Amount = amount;
	this.Quantity = quantity;
	this.Unit = unit;
}
function itemMess(id, name, content, data) {
	this.Id = id;
	this.Name = name;
	this.Content = content;
	this.Data = data;
}
function itemGuest(id, name, arrival, out, status, pincode) {
	this.Id = id;
	this.Name = name;
	this.Arrival = arrival;
	this.Out = out;
	this.Status = status;
	this.Pincode = pincode;
}
function itemRecord(id, channelname, urlrecord, sernumber, starttime, stoptime,
		status, privatechannelname, sizeinkb, urlpicture) {
	this.Id = id;
	this.ChannelName = channelname;
	this.UrlRecord = urlrecord;
	this.SerNumber = sernumber;
	this.StartTime = starttime;
	this.StopTime = stoptime;
	this.Status = status;
	this.PrivateChannelName = privatechannelname;
	this.SizeInkb = sizeinkb;
	this.UrlPicture = urlpicture;
}
function clickUpdateStatus(id){
	alert(id);
}

function splitLength(text){
	if(text.length > 35){
		text = text.substring(0, 32) + "...";
	}
	return text;
}


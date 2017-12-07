function DaemonServiceSystem() {
	var my = this;
	// khoi tao doi tuong lay list service 22.1
	var list = new Listservice1();
	this.run = function() {
		list.load();
	};
	this.onclick = function() {
		list.pageclick();
	};
	this.addItem = function() {
		list.addeImage();
	};
};
// ham lay list service 22.1
function Listservice1() {
	var Index = 0;
	var table = new Table();
	var ctTable = new contextTable1();
	this.addeImage = function() {
		var add = new addItemAttaction();
		add.load(my.SubId);
		add.update = function() {
			my.reload();
		};
	};
	// thay doi status cua service hien tai 22.1
	ctTable.fucStatus = function(rellink) {
		var wt = new Waiting();
		var url = "ServiceSystem?CMD=7";
		url += "&id=" + this.obj.Id;
		url += "&name=" + this.obj.Name;
		url += "&type=" + rellink;
		url += "&r=" + Math.random();
		wt.show(null);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			setTimeout(function() {
				var div = $("tab_menu").getElementsByTagName("div");
				for ( var i = 0; i < div.length; i++) {
					div[i].className = "tab_menu_item";
				}
				div[1].className = "tab_menu_item_sel";
				// quy dinh id cua tab
				tag = 1;
				my.load(wt);
			}, 3000);
		};
	};
	// goi log cua module 31.1
	ctTable.fucLog = function(rellog) {
		// remove text cu
		var url = "ServiceSystem?CMD=8";
		url += "&id=" + this.obj.Id;
		url += "&name=" + this.obj.Name;
		url += "&port=" + rellog;
		url += "&r=" + Math.random();
		var wt = new Waiting();
		wt.show(null);
		var f = new AjaxGetXML(url);
		f.complet = function(tx1) {
			wt.hide();
			window.open(logmodulepath, '_newtab');
			// window.open(logmodulepath,
			// '_blank' // <- This is what makes it open in a new window.
			// );
		};
	};
	this.pageclick = function() {
		if (ctTable.state)
			ctTable.hide();
	};
	var my = this;
	this.count = 0;
	var ID = 0;// id of subject
	this.SubId = -1;
	this.ListCheck = new Vector();
	var arr = new Array();
	var defaults = {
		divName : "id_table",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable2_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable2_row1",
		bgRow2 : "mytable2_row2"
	};
	this.load = function(wt) {
		if (wt == null) {
			wt = new Waiting();
			wt.show(null);
		}
		Index = 0;
		table.init(defaults);
		my.get(Index, wt);
	};
	this.reload = function() {
		Index = 0;
		my.get(ID, Index);
	};
	this.loadIdex = function(index) {
		Index = index;
		my.get(ID, index);
	};
	this.nextindex = function() {
		var page = 5;
		var cpage = ((my.count - 1) / page) - 1;
		if (Index < cpage)
			Index++;
		my.loadIdex(Index);
	};
	this.backindex = function() {
		if (Index > 0)
			Index--;
		my.loadIdex(Index);
	};
	// bat su kien phai chuot goi contextmenu 29.1
	this.renderEvent = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var item = tr[i];
			item.onclick = function(e) {
				my.selectItem(this.id);
			};
			item.oncontextmenu = function(e) {
				if (Common.ctrl) {
					my.addItem(this.id);
				} else {
					my.selectItem(this.id);
				}
				my.oncontextmenu(this.id, e);
				return false;
			};
		}
		;
		if ($("div_page") != undefined) {
			var div = $("div_page");
			var a = div.getElementsByTagName("a");
			for ( var i = 0; i < a.length; i++) {
				var item = a[i];
				item.onclick = function(e) {
					my.loadIdex(this.id);
				};
			}
			$("div_page_icon_right").onclick = function() {
				my.nextindex();
			};
			$("div_page_icon_left").onclick = function() {
				my.backindex();
			};
		}
	};
	this.selectItem = function(id) {
		if (Common.ctrl) {
			if (!this.ListCheck.isObject(id)) {
				this.ListCheck.add(id);
			} else {
				this.ListCheck.removeObj(id);
			}
		} else {
			my.ListCheck = new Vector();
			my.ListCheck.add(id);
		}
		my.shiftSelect();
	};
	this.addItem = function(id) {
		if (!this.ListCheck.isObject(id)) {
			this.ListCheck.add(id);
		}
		my.shiftSelect();
	};
	this.oncontextmenu = function(id, e, name) {
		var obj = null;
		for ( var i = 0; i < arr.length; i++) {
			if (arr[i].Id == id) {
				obj = arr[i];
				break;
			}
		}
		ctTable.load(obj, e, name);
	};
	/* render du lieu ra html 22.1, tab ServiceSystem */
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_formother1' style='overflow-y:scroll;margin-top:10px;'>";
		html += "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"97%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left' valign=\"middle\"  width=\"" + 20
				+ "%\" class='header0' >";
		html += langpms.name;
		html += "</th>";
		html += "<th align='left' valign=\"middle\"  width=\"" + 20
		+ "%\" class='header0' style='display: none;'>";
		html += "temp";
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\">";
		html += langpms.status;
		html += "</th>";
		html += "</tr>";
		for ( var i = 0; i < this.List.length; i++) {
			if (convertToHTML(this.List[i].View) != "null") {
				if (checkServiceValid(convertToHTML(this.List[i].View)) == 1) {
					if (i % 2 == 0) {
						html += "<tr rel=" + this.List[i].Name + " class=\""
								+ this.bgRow2 + "\" id=\"" + this.List[i].Id
								+ "\">";
					} else {
						html += "<tr rel=" + this.List[i].Name + " class=\""
								+ this.bgRow1 + "\" id=\"" + this.List[i].Id
								+ "\">";
					}
					html += "<td align=\"left\"  valign=\"middle\">";
					html += convertToHTML(this.List[i].View);
					html += "</td>";
					
					html += "<td align=\"left\"  valign=\"middle\" style='display: none;'>";
					html += convertToHTML(this.List[i].Name);
					html += "</td>";
					
					html += "<td align=\"center\" valign=\"middle\">";
					if (this.List[i].Status == 0) {
						html += "<img src=\"../icon/16-square-green.png\"></img>";
					} else {
						html += "<img src=\"../icon/16-square-red.png\"></img>";
					}
					html += "</td>";
					html += "</tr>";
				}
			}
		}
		html += "</table>";
		html += "</div>";
		html += "<div id='addCodeToHere'>";
		html += "</div>";
		return html;
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
	// goi phuong thuc GET CMD=6 de lay du lieu tu server 29.1
	this.get = function(id, wt) {
		var url = "ServiceSystem?CMD=6";
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _view = it[i].getElementsByTagName("view")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				var _portlog = it[i].getElementsByTagName("portlog")[0].childNodes[0].nodeValue;
				var _port = it[i].getElementsByTagName("port")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				arr[i] = new ItemHotel1(_id, _name,_view, _port, _status, _portlog); // _port
				// =>
				// luu
				// voi
				// ten
				// la:action
			}
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem3", 0);
			my.renderEvent();
			wt.hide();
		};
	};
	this.loadcomplet = function() {
	};
}
function contextTable1() {
	var my = this;
	this.state = false;
	this.obj = null;
	this.load = function(_obj, e) {
		this.obj = _obj;
		var posx = 0;
		var posy = 0;
		if (!e)
			var e = window.event;
		if (e.pageX || e.pageY) {
			posx = e.pageX;
			posy = e.pageY;
		} else if (e.clientX || e.clientY) {
			posx = e.clientX + document.body.scrollLeft
					+ document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
					+ document.documentElement.scrollTop;
		}
		my.draw(posx, posy - 10, this.obj.Name, this.obj.Portlog);
	};
	// context menu: Update status
	this.draw = function(x, y, name, portlog) {
		my.state = true;
		var html = "";
		html += "<ul>";
		if (my.obj.Status == 1) {
			html += "<li class='icon_visibility1'><a rel='start'>";
		} else {
			html += "<li class='icon_visibility2'><a rel='stop'>";
		}
		html += langMain.updatestatus + "</a>";
		html += "</li>";
		// bo sung tinh nang xem log cua module 5.2
		html += "<li class='icon_visibility1'>";
		html += "<a rel='" + portlog + "'>" + langMain.xemlog + "</a>";
		html += "</li>";
		html += "</ul>";
		if ($("div_contextMenu") == undefined) {
			var div_ = document.createElement("div");
			div_.id = "div_contextMenu";
			div_.className = "div_contextMenu";
			div_.innerHTML = html;
			$("main").appendChild(div_);
		} else {
			$("div_contextMenu").className = "div_contextMenu";
			$("div_contextMenu").innerHTML = html;
		}
		$("div_contextMenu").style.left = x + "px";
		$("div_contextMenu").style.top = y + "px";
		$("div_contextMenu").style.display = "block";
		my.renderClick();
		eventMouse();
	};
	this.renderClick = function() {
		var ul = $("div_contextMenu").getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");
		var itemstatus = li[0].getElementsByTagName("a")[0];
		var rellink = itemstatus.getAttribute("rel");
		itemstatus.onclick = function() {
			my.fucStatus(rellink);
		};
		// goi menu thu 2: xem log 5.2
		var itemlog = li[1].getElementsByTagName("a")[0];
		var rellog = itemlog.getAttribute("rel");
		itemlog.onclick = function() {
			my.fucLog(rellog);
		};
	};
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	};
}

function checkServiceValid(text) {
	if (text == "eod_lsnrora")
		return 0;
	if (text == "eod_ehotelmanservice")
		return 0;
	if (text == "eod_dbora")
		return 0;
	return 1;
}

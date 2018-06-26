var user = new function UserHouseKeeping() {
//	var my = this;
	var list = new ListHouseKeeping();
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
function ListHouseKeeping() {
	var Index = 0;
	var table = new Table();
	var ctTable = new contextTable();
	ctTable.fucDetail = function() {
		var detail = new DetailUser();
		detail.load(this.obj);
		detail.update = function() {
			my.reload();
		};
	};
	ctTable.fucdel = function() {
		var cfBox = new conformBox();
		var ob = this.obj;
		cfBox.show();
		cfBox.accept = function() {
			var url = "SvcHouseKeeping?CMD=8";
			url += "&id=" + ob.Id;
			url += "&account=" + ob.UserName;
			url += "&r=" + Math.random();
			var f = new AjaxGetText(url);
			f.complet = function(tx) {
				if(tx == "t")
					my.reload();
				else{
					var albox = new alertBox();
					albox.show("Delete user housekeeping error???");
				}
			};
		};
	};
	ctTable.fucAdd = function() {
		var add = new addUserHousekeeping();
		add.load();
		add.update = function() {
			my.reload();
		};
	};
	ctTable.fucStatus = function() {
		var url = "SvcHouseKeeping?CMD=9";
		url += "&id=" + this.obj.Id;
		url += "&account=" + this.obj.UserName;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			if(tx == "t")
				my.reload();
			else{
				var albox = new alertBox();
				albox.show("Change status user housekeeping error???");
			}
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
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	};
	this.load = function() {
		Index = 0;
		table.init(defaults);
		my.get(Index);
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
//		var page = 6;
//		var cpage = ((my.count - 1) / page) - 1;
//		if (Index < cpage)
			Index++;
		my.loadIdex(Index);
	};
	this.backindex = function() {
		if (Index > 0)
			Index--;
		my.loadIdex(Index);
	};
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
	this.oncontextmenu = function(id, e) {
		var obj = null;
		for ( var i = 0; i < arr.length; i++) {
			if (arr[i].Id == id) {
				obj = arr[i];
				break;
			}
		}
		ctTable.load(obj, e);
	};
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_formother'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"97%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left'  valign=\"middle\"  width=\"" + 10
				+ "%\"  class='header0' >";
		html += "No";
		html += "</th>";
		html += "<th align='left'  valign=\"middle\"  width=\"" + 25
				+ "%\"  class='header0' >";
		html += "Name";
		html += "</th>";
		html += "<th align='left' width=\"" + 25 + "%\" valign=\"middle\"  >";
		html += "User Name";
		html += "</th>";
		html += "<th align='center' width=\"" + 25 + "%\" valign=\"middle\"  >";
		html += "Modify Date";
		html += "</th>";
		html += "<th align='center' width=\"" + 15 + "%\" valign=\"middle\"  >";
		html += "Active";
		html += "</th>";
		html += "</tr>";
		var begin = Index * 6;
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\"  >";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\"   >";
			}
			html += "<td align=\"center\"  valign=\"middle\">";
			html += begin + i + 1;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].FullName;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].UserName;
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			html += this.List[i].Date;
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			if (this.List[i].Status == 0) {
				html += "<img src=\"../icon/16-square-green.png\"></img>";
			} else {
				html += "<img src=\"../icon/16-square-red.png\"></img>";
			}
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		if (my.count > 6) {
			var page = my.count / 6;
			html += "<div id=\"div_page\" class='div_page'>";
			html += "<div class='div_page_icon_left' id='div_page_icon_left'>";
			html += "</div>";
			for ( var i = 0; i < page; i++) {
				if (i == Index) {
					html += "<a class='div_page_a_sel' href=\"javascript:\" id=\""
							+ i + "\">";
				} else {
					html += "<a class='div_page_a' href=\"javascript:\" id=\""
							+ i + "\">";
				}
				html += (i + 1);
				html += " </a>";
			}
			html += "<div class='div_page_icon_right' id='div_page_icon_right'>";
			html += "</div>";
			html += "</div>";
		}
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
	this.get = function(id) {
		var url = "SvcHouseKeeping?CMD=6";
		url += "&pageIndex=" + Index;
		url += "&page=6";
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _username = it[i].getElementsByTagName("username")[0].childNodes[0].nodeValue;
				var _pass = it[i].getElementsByTagName("pass")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				var _date = it[i].getElementsByTagName("date")[0].childNodes[0].nodeValue;
				var _address = it[i].getElementsByTagName("address")[0].childNodes[0].nodeValue;
				var _usermodify = it[i].getElementsByTagName("usermodify")[0].childNodes[0].nodeValue;
				var _fullname = it[i].getElementsByTagName("fullname")[0].childNodes[0].nodeValue;
				arr[i] = new ItemUserHK(_id, _username, _pass, _status, _date,
						_address, _usermodify, _fullname);
			}
			;

			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem3", 0);
			my.renderEvent();
		};
	};
	this.loadcomplet = function() {
		// $("title_subject").innerHTML=my.subname+"("+my.count+")";
	};
};

function ItemUserHK(id, username, pass, status, date, address, usermodify,
		fullname) {
	this.Id = id;
	this.UserName = username;
	this.Pass = pass;
	this.Status = status;
	this.Date = date;
	this.Address = address;
	this.UserModify = usermodify;
	this.FullName = fullname;
}
function contextTable() {
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

		my.draw(posx, posy - 10);
	};
	this.draw = function(x, y) {
		my.state = true;
		var html = "";
		html += "<ul>";
		html += "<li class='icon_contextmenuEdit'>";
		html += "<a>Add new user</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuplay'>";

		html += "<a>Edit user</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuDel'>";
		html += "<a>Delete user</a>";
		html += "</li>";
		if (my.obj.Status == 1) {
			html += "<li class='icon_visibility1'>";
		} else {
			html += "<li class='icon_visibility2'>";
		}

		html += "<a>Change status</a>";
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
		var fucAdd = li[0].getElementsByTagName("a")[0];
		fucAdd.onclick = function() {
			my.fucAdd();
		};
		var fucDetail = li[1].getElementsByTagName("a")[0];
		fucDetail.onclick = function() {
			my.fucDetail();
		};
		var fucdel = li[2].getElementsByTagName("a")[0];
		fucdel.onclick = function() {
			my.fucdel();
		};
		var itemstatus = li[3].getElementsByTagName("a")[0];
		itemstatus.onclick = function() {
			my.fucStatus();
		};
	};
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	};
};
function addUserHousekeeping() {

	var my = this;
	var layer = new layer_vitual();
	this.load = function() {
		my.show();
	};
	this.show = function() {
		layer.show();
		var url = "SvcHouseKeeping?CMD=7";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_user");
			initTinymce(2, "540px");
			$("form_textbox_ok").onclick = function() {
				my.accept();
			};
			$("form_textbox_cancel").onclick = my.cancel;
		};
	};
	this.accept = function() {
		var username = $("username").value;
		var pass = $("pass").value;
		var repass = $("repass").value;
		var name = $("name").value;
		
		if(username.length == 0 || pass.length == 0 || repass.length == 0 || name.length == 0){
			$("span-error-user").style.display="block";
			$("span-error-user").innerHTML = "(*) Information must not be blank???";
			return;
		}else if (pass != repass){
			$("span-error-user").style.display="block";
			$("span-error-user").innerHTML = "Please check that your passwords match and try again???";
			return;
		}
		name = encode(name);
		var url = "SvcHouseKeeping?CMD=4";
		url += "&t=" + Math.random();
		var param = "&name=" + name;
		param += "&username=" + username;
		param += "&pass=" + pass;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			if(tx == "t"){
				my.hide();
				my.update();
			}else{
				var albox = new alertBox();
				albox.show("Add user housekeeping error???");
			}
		};
	};
	this.update = function() {

	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	};
};
function DetailUser() {
	var my = this;
	var layer = new layer_vitual();
	this.Id = -1;
	this.load = function(obj) {
		my.Id = obj.Id;
		my.show(obj);
	};
	this.show = function(obj) {
		layer.show();
//		var html = "";
		var url = "SvcHouseKeeping?CMD=7";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_user");
			initTinymce(2, "540px");
			document.getElementById("username").readOnly = true;
			$("username").value = obj.UserName;
			$("pass").value = obj.Pass;
			$("repass").value = obj.Pass;
			$("name").value = obj.FullName;
			$("form_textbox_ok").onclick = function() {
				my.accept(obj.Id);
			};
			$("form_textbox_cancel").onclick = my.cancel;
		};
	};
	this.accept = function(id) {
		var username = $("username").value;
		var pass = $("pass").value;
		var repass = $("repass").value;
		var name = $("name").value;
		
		if(username.length == 0 || pass.length == 0 || repass.length == 0 || name.length == 0){
			$("span-error-user").style.display="block";
			$("span-error-user").innerHTML = "(*) Information must not be blank???";
			return;
		}else if (pass != repass){
			$("span-error-user").style.display="block";
			$("span-error-user").innerHTML = "Please check that your passwords match and try again???";
			return;
		}
		name = encode(name);
		var url = "SvcHouseKeeping?CMD=5";
		url += "&t=" + Math.random();
		var param = "&name=" + name;
		param += "&id=" + id;
		param += "&username=" + username;
		param += "&pass=" + pass;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			if(tx == "t"){
				my.hide();
				my.update();
			}else{
				var albox = new alertBox();
				albox.show("Edit user housekeeping error???");
			}
		};
	};
	this.update = function() {
	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	};
};
var room = new function Room() {
	this.key = "";
	var my = this;
	var tag = 0;
	this.list = new ListRoom();
	var obj = new Guest();
	var roomId = -1;
	this.reload = function() {
		my.list.reload();
	};
	my.list.loadcomplet = function(id) {
		roomId = id;
		if (tag == 0) {
			obj = new Guest();
			obj.load(roomId);
		}
		if (tag == 1) {
			obj = new Messages();
			obj.load(roomId);
		}
		if (tag == 2) {
			obj = new Bill();
			obj.load(roomId);
		}
		if (tag == 3) {
			obj = new SetBox();
			obj.load(roomId);
		}
		if (tag == 4) {
			obj = new Records();
			obj.load(roomId);
		}
	};
	this.seach = function(key) {
		my.key = key;
		my.list.load(my.key);
	};
	this.seachSTB = function(key) {
		if (tag == 3) {
			obj.seach(key);
		}
	};
	this.changeTab = function(id) {
		var div = $("tab_menu_2").getElementsByTagName("div");
		for ( var i = 0; i < div.length; i++) {
			div[i].className = "tab_menu_item_1";
		}
		div[id].className = "tab_menu_item_1_sel";
		tag = id;
		this.loadM(id);
	};
	this.loadM = function(id) {
		if (id == 0) {
			obj = new Guest();
			obj.load(roomId);
		}
		if (id == 1) {
			obj = new Messages();
			obj.load(roomId);
		}
		if (id == 2) {
			obj = new Bill();
			obj.load(roomId);
		}
		if (id == 3) {
			obj = new SetBox();
			obj.load(roomId);
		}
		if (id == 4) {
			obj = new Records();
			obj.load(roomId);
		}
	};
	this.onclick = function() {
		my.list.pageclick();
	};
	this.load = function() {
		my.list.load(my.key);
	};
};
function ListRoom() {
	var wt = new Waiting();
	var Index = 0;
	var table = new Table();
	// remove philao 25062013
	var ctTable = new contextTableEX();
	ctTable.fucreset = function() {
		var conform = new conformBox();
		var me = this;
		conform.show(langpms.reset_pin);
		conform.accept = function() {

			var url = "folioPms?CMD=14";
			url += "&id=" + me.obj.Id;
			url += "&r=" + Math.random();
			var f = new AjaxGetXML(url);
			f.complet = function(tx) {

				Common.checkRole(tx);
			};
		};
	};
	ctTable.fuccheckout = function() {
		if (this.obj.Status == 1) {
			var conform = new conformBox();
			var me = this;
			conform.show(langpms.checkout);
			conform.accept = function() {
				var url = "folioPms?CMD=13";
				url += "&id=" + me.obj.Id;
				url += "&r=" + Math.random();
				var f = new AjaxGetText(url);
				f.complet = function(tx) {
					var albox = new successBox();
					albox.show(tx);
					// if (Common.checkRole(tx)) {
					my.reload();
					my.loadcomplet(me.obj.Id);
					// }
				};
			};
		} else {
			var albox = new alertBox();
			albox.show(langpms.room_checkout);
		}
	};
	ctTable.fuccheckin = function() {
		var me = this;
//		var add = new addItemCheckIn(me.obj.Key);
//		add.load();
//		my.reload();
//		my.loadcomplet(me.obj.Id);
		var layer = new layer_vitual();
		layer.show();
		var url = "folioPms?CMD=15";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("formcheckin");
			initTinymce(2, "540px");
			$("checkinaddguest").onclick = function() {
				var amount = parseInt($("checkinamount").value);
				amount = amount - (-1);
				$("checkinamount").value = amount;

				var firstnameid = 'checkinfirstname' + amount;
				var lastnameid = 'checkinlastname' + amount;
				var sexid = 'checkinsex' + amount;
				var html = "";
				html += "<div class='pms_textbox_input' style='margin-top: 8px;'>";
				html += "<div class='pms_name_input'>First name</div>";
				html += "<input type='text' size='26' style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px; margin-left: -20px;' id='"
						+ firstnameid + "' placeholder='First Name...'/>";
				html += "<div class='pms_name_input'>Last name</div>";
				html += "<input type='text' size='26' style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px; margin-left: -20px;'id='"
						+ lastnameid + "' placeholder='Last Name...'/>";
				html += "<div class='pms_name_input' style='width: 40px;'>Sex</div>";
				html += "<select size='26' style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px;'id='"
						+ sexid + "'>";
				html += "<option value='Mr.' selected='selected'>Mr</option> <option value='Mrs.'>Mrs</option><option value='Ms.'>Ms</option></select></div>";
				var div_ = document.createElement("div");
				div_.innerHTML = html;
				$("checkinparentdiv").appendChild(div_);
			};
			document.getElementById('date-checkin').valueAsDate = new Date();
			document.getElementById('date-checkout').valueAsDate = new Date();
			$("checkinok").onclick = function() {
				my.acceptcheckin(me.obj.Id);
			};
			$("checkincancel").onclick = function() {
				 $("div_layer_vitual").style.display = "none";
			};
		};
	};
	
	this.acceptcheckin = function(id) {
		var amount = parseInt($("checkinamount").value);
		var guest = "";
		for ( var i = 1; i <= amount; i++) {
			var person = "";
			var firstnameid = 'checkinfirstname' + i;
			var lastnameid = 'checkinlastname' + i;
			var sexid = 'checkinsex' + i;

			var firstname = encode($(firstnameid).value);
			var lastname = encode($(lastnameid).value);
			var e = document.getElementById(sexid);
			var sex = e.options[e.selectedIndex].value;

			person = firstname + "," + lastname + "," + sex;
			guest += person + ";";
		}
		var checkindate = $('date-checkin').value;
		var checkoutdate = $('date-checkout').value;

		var url = "hotelPms?CMD=6";
		var param = "&folionum=" + id;
		param += "&guest=" + encode(guest);
		param += "&amount=" + amount;
		param += "&checkindate=" + checkindate;
		param += "&checkoutdate=" + checkoutdate;
		url += "&t=" + Math.random();
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			 $("div_layer_vitual").style.display = "none";
			 my.reload();
			my.loadcomplet(id);
		};
	};

	ctTable.fucsendmessage = function() {
		var me = this;
		var layer = new layer_vitual();
		layer.show();
		var url = "folioPms?CMD=20";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("formmessage");
			initTinymce(2, "540px");
			$("messok").onclick = function() {
				my.acceptmessage(me.obj.Key);
			};
			$("messcancel").onclick = function() {
				 $("div_layer_vitual").style.display = "none";
			};
		};
	};
	
	this.acceptmessage = function(id) {
		var sender = $("messagesender").value;
		sender = encode(sender);
		var subject = $("messagesubject").value;
		var content = $("messagecontent").value;
		content = encode(content);

		if (subject.length != 0 && sender.length != 0) {
			
			var url = "hotelPms?CMD=5";
			url += "&t=" + Math.random();
			var param = "&folionum=" + id;
			param += "&sender=" + sender;
			param += "&subject=" + subject;
			param += "&content=" + content;
			var f = new AjaxPostText(url, param);
			f.complet = function(tx) {
				$("div_layer_vitual").style.display = "none";
				my.reload();
				my.loadcomplet(id);
			};
		} else {
			var albox = new alertBox();
			albox.show("Subject or Content is empty!");
			return;
		}
	};
	
	ctTable.fucXXX = function() {
		var me = this;
		var url = "folioPms?CMD=24";
		url += "&id=" + me.obj.Id;
		url += "&xxx=" + me.obj.XXX;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			if(tx == "success"){
				my.reload();
				my.loadcomplet(me.obj.Id);
			}else{
				var albox = new alertBox();
				albox.show("Change status adult movie error!");
			}
		};
	};
	
	ctTable.fucresetpasscellphone = function() {
		var conform = new conformBox();
		var me = this;
		conform.show("Are you sure reset password for cellphone?");
		conform.accept = function() {
			var url = "folioPms?CMD=23";
			url += "&idfolio=" + me.obj.Key;
			url += "&r=" + Math.random();
			var f = new AjaxGetText(url);
			f.complet = function(tx) {
				$("div_layer_vitual").style.display = "none";
				if(tx == "f"){
					var albox = new alertBox();
					albox.show("Reset pass cell phone error!");
				}else{
					my.reload();
					my.loadcomplet(me.obj.Key);
				}
			};
		};
	};
	
	this.pageclick = function() {
		// remove philao 25062013
		if (ctTable.state)
			ctTable.hide();
	};
	var my = this;
	this.count = 0;
	var ID = 0;// id of subject
	this.SubId = -1;
	this.page = 6;
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
	/**
	 * @param id
	 *            id subject
	 * @return
	 */
	this.key = "";
	this.load = function(key) {
		if (wt.wt)
			return;
		Index = 0;
		my.key = key;
		table.init(defaults);
		my.getdefault(Index);
	};
	this.reload = function() {
		my.get(Index);
	};
	this.loadIdex = function(index) {
		Index = index;
		my.get(ID);
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
		/**
		 * render paging
		 */
		if ($("div_page") != undefined) {
			var div = $("div_page");
			var a = div.getElementsByTagName("a");
			for ( var i = 0; i < a.length; i++) {
				var item = a[i];
				item.onclick = function(e) {
					my.loadIdex(this.id);
				};
			}
			var div1 = $("div_page").getElementsByTagName("div");
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
	this.selectItem = function(id) {
		my.loadcomplet(id);
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
	this.selectItemdefault = function(id) {
		my.loadcomplet(id);
		my.ListCheck = new Vector();
		my.ListCheck.add(id);
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
		// remove philao 25062013
		ctTable.load(obj, e);
	};
	table.dataBind = function() {
		var html = "";
		html += "<div class='folio_listroom'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left' valign=\"middle\"  width=\"" + 10
				+ "%\" class='header0'>";
		html += langpms.no;
		html += "</th>";
		html += "<th align='left' valign=\"middle\"  width=\"" + 15 + "%\">";
		html += langpms.room;
		html += "</th>";
		html += "<th align='left' width=\"" + 25 + "%\" valign=\"middle\">";
		html += langpms.type_room;
		html += "</th>";
//		html += "<th align='center' width=\"" + 20 + "%\" valign=\"middle\">";
//		html += "Pass CellPhone";
//		html += "</th>";
//		html += "<th align='left' width=\"" + 15 + "%\" valign=\"middle\" style='text-align:center'>";
//		html += "Adult Movie";
//		html += "</th>";
		html += "<th align='center' width=\"" + 15 + "%\" valign=\"middle\">";
		html += langpms.status;
		html += "</th>";
		html += "</tr>";
		// if(this.List.length==0)return;
		var begin = Index * my.page;
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
			html += this.List[i].Name;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Type;
			html += "</td>";
//			html += "<td align=\"center\"  valign=\"middle\">";
//			html += this.List[i].Pass;
//			html += "</td>";
			
//			html += "<td align=\"left\"  valign=\"middle\" style='text-align:center'>";
//			if (this.List[i].XXX == "1") {
//				html += "<img src=\"../icon/16-square-green.png\"></img>";
//			} else if (this.List[i].XXX == "0"){
//				html += "<img src=\"../icon/16-square-red.png\"></img>";
//			}
//			else{
//				html += "";
//			}
			
			html += "<td align=\"center\"  valign=\"middle\">";
			if (this.List[i].Status == 1) {
				html += "<img src=\"../icon/16-square-green.png\"></img>";
			} else {
				html += "<img src=\"../icon/16-square-red.png\"></img>";
			}
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		html += "<div id=\"div_page\" class='div_page'>";
		if (my.count > 6) {
			var page = my.count / 6;
			html += createPaging(page, Index);
		}
		html += "</div>";
		html += "<div class='div_bottom_table'>";
		html += "";
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
	this.get = function(id) {
		var url = "folioPms?CMD=1";
		url += "&pageIndex=" + Index;
		url += "&page=" + my.page;
		url += "&key=" + my.key;
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
				var _key = it[i].getElementsByTagName("key")[0].childNodes[0].nodeValue;
				var _type = it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
//				var _pass = it[i].getElementsByTagName("pass")[0].childNodes[0].nodeValue;
				var _xxx = it[i].getElementsByTagName("xxx")[0].childNodes[0].nodeValue;
				arr[i] = new itemFolio(_id, _name, _type, _key, _status, "pass", _xxx);
			}
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem3", 0);
			my.renderEvent();
			my.shiftSelect();
			wt.hide();
		};
	};
	this.getdefault = function(id) {
		var url = "folioPms?CMD=1";
		url += "&pageIndex=" + Index;
		url += "&page=" + my.page;
		url += "&key=" + my.key;
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
				var _key = it[i].getElementsByTagName("key")[0].childNodes[0].nodeValue;
				var _type = it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
//				var _pass = it[i].getElementsByTagName("pass")[0].childNodes[0].nodeValue;
				var _xxx = it[i].getElementsByTagName("xxx")[0].childNodes[0].nodeValue;
				arr[i] = new itemFolio(_id, _name, _type, _key, _status, "pass", _xxx);
			}
			table.load(arr);
			wt.hide();
			table.classCol("classItem1", 1);
			table.classCol("classItem3", 0);
			my.renderEvent();
			if (arr.length > 0)
				my.selectItemdefault(arr[0].Id);
			my.shiftSelect();
		};
	};
	this.loadcomplet = function(id) {
		// $("title_subject").innerHTML=my.subname+"("+my.count+")";
	};
}
function contextTableEX() {
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
		if (my.obj.Status == 1) {
			html += "<li class='icon_visibility2'>";
			html += "<a>Check in</a>";
			html += "</li>";

			html += "<li class='icon_visibility1'>";
			html += "<a>Check out</a>";
			html += "</li>";
		} else {
			html += "<li class='icon_visibility1'>";
			html += "<a>Check in</a>";
			html += "</li>";

			html += "<li class='icon_visibility2'>";
			html += "<a>Check out</a>";
			html += "</li>";
		}
		html += "<li class='icon_reset_pin'>";
		html += "<a>Reset pin</a>";
		html += "</li>";

		html += "<li class='icon_reset_pass_cellphone' style='display: none;'>";
		html += "<a>Reset pass cellphone</a>";
		html += "</li>";
		
		if (my.obj.XXX == "0") {
			html += "<li class='icon_visibility1'>";
			html += "<a>Adult Movie</a>";
			html += "</li>";
		} else if(my.obj.XXX == "1"){
			html += "<li class='icon_visibility2'>";
			html += "<a>Adult Movie</a>";
			html += "</li>";
		}else{
			html += "<li class='icon_visibility2' style='display: none;'>";
			html += "<a>Adult Movie</a>";
			html += "</li>";
		}
		
		if (my.obj.Status == 1) {
			html += "<li class='icon_send_message'>";
			html += "<a>Send message</a>";
			html += "</li>";
		} else {
			html += "<li class='icon_send_message' style='display: none;'>";
			html += "<a>Send message</a>";
			html += "</li>";
		}

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
	};
	this.renderClick = function() {
		var ul = $("div_contextMenu").getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");

		var fuccheckin = li[0].getElementsByTagName("a")[0];
		fuccheckin.onclick = function() {
			my.fuccheckin();
		};

		var fuccheckout = li[1].getElementsByTagName("a")[0];
		fuccheckout.onclick = function() {
			my.fuccheckout();
		};

		var fucreset = li[2].getElementsByTagName("a")[0];
		fucreset.onclick = function() {
			my.fucreset();
		};

		var fucresetpasscellphone = li[3].getElementsByTagName("a")[0];
		fucresetpasscellphone.onclick = function() {
			my.fucresetpasscellphone();
		};
		
		var fucXXX = li[4].getElementsByTagName("a")[0];
		fucXXX.onclick = function() {
			my.fucXXX();
		};
		
		var fucsendmessage = li[5].getElementsByTagName("a")[0];
		fucsendmessage.onclick = function() {
			my.fucsendmessage();
		};
		
	};
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	};
}
function itemFolio(id, name, type, key, status, pass, xxx) {
	this.Id = id;
	this.Name = name;
	this.Key = key;
	this.Status = status;
	this.Type = type;
	this.Pass = pass;
	this.XXX = xxx;
}
// show dinalog check in room in hotel

function addItemCheckIn(id) {
	var my = this;
	var layer = new layer_vitual();
	this.load = function() {
		my.show();
	};
	this.show = function() {
		layer.show();
		var url = "folioPms?CMD=15";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("formcheckin");
			initTinymce(2, "540px");
			$("checkinaddguest").onclick = function() {
				var amount = parseInt($("checkinamount").value);
				amount = amount - (-1);
				$("checkinamount").value = amount;

				var firstnameid = 'checkinfirstname' + amount;
				var lastnameid = 'checkinlastname' + amount;
				var sexid = 'checkinsex' + amount;
				var html = "";
				html += "<div class='pms_textbox_input' style='margin-top: 8px;'>";
				html += "<div class='pms_name_input' style='width: 80px;'>First name</div>";
				html += "<input type='text' size='26' style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px; margin-left: -20px;' id='"
						+ firstnameid + "' placeholder='First Name...'/>";
				html += "<div class='pms_name_input' style='width: 80px;'>Last name</div>";
				html += "<input type='text' size='26' style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px; margin-left: -20px;'id='"
						+ lastnameid + "' placeholder='Last Name...'/>";
				html += "<div class='pms_name_input' style='width: 40px;'>Sex</div>";
				html += "<select size='26' style='border: 1px solid #dddddd; height: 24; float: left; width: 150; margin-top: 5px;'id='"
						+ sexid + "'>";
				html += "<option value='Mr.' selected='selected'>Mr</option> <option value='Mrs.'>Mrs</option><option value='Ms.'>Ms</option></select></div>";
				var div_ = document.createElement("div");
				div_.innerHTML = html;
				$("checkinparentdiv").appendChild(div_);
			};
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			var yyyy = today.getFullYear();
			if(dd<10) {
			    dd='0'+dd;
			} 
			if(mm<10) {
			    mm='0'+mm;
			} 
			$("datebdday").value=dd;
			$("datebdmonth").value=mm;
			$("datebdyear").value=yyyy;
			
			$("ktdatektday").value=dd;
			$("ktdatektmonth").value=mm;
			$("ktdatektyear").value=yyyy;
			$("checkinok").onclick = function() {
				my.accept();
			};
			$("checkincancel").onclick = my.cancel;
		};
	};
	this.accept = function() {
		var amount = parseInt($("checkinamount").value);
		var guest = "";
		for ( var i = 1; i <= amount; i++) {
			var person = "";
			var firstnameid = 'checkinfirstname' + i;
			var lastnameid = 'checkinlastname' + i;
			var sexid = 'checkinsex' + i;

			var firstname = encode($(firstnameid).value);
			var lastname = encode($(lastnameid).value);
			var e = document.getElementById(sexid);
			var sex = e.options[e.selectedIndex].value;

			person = firstname + "," + lastname + "," + sex;
			guest += person + ";";
		}
		// var firstname = $('checkinfirstname1').value;
		// var lastname = $('checkinlastname1').value;
		// var e = document.getElementById("checkinsex1");
		// var sex = e.options[e.selectedIndex].value;

		var checkindate = $('datebdday').value + '/' + $('datebdmonth').value
				+ '/' + $('datebdyear').value;
		var checkoutdate = $('ktdatektday').value + '/'
				+ $('ktdatektmonth').value + '/' + $('ktdatektyear').value;

		var url = "hotelPms?CMD=6";
		var param = "&folionum=" + id;
		param += "&guest=" + encode(guest);
		param += "&amount=" + amount;
		param += "&checkindate=" + checkindate;
		param += "&checkoutdate=" + checkoutdate;
		url += "&t=" + Math.random();
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			my.hide();
		};
	};
	this.update = function() {

	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		$("formcheckin").style.display = "none";
		document.location.href = "folioPms?MenuId=4&SubId=11&t=0.9852005187422037";
	};
}

function addItemMessage(id) {
	var my = this;
	var layer = new layer_vitual();
	this.load = function() {
		my.show();
	};
	this.show = function() {
		layer.show();
		var url = "folioPms?CMD=20";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("formmessage");
			initTinymce(2, "540px");

			$("messok").onclick = function() {
				my.accept();
			};
			$("messcancel").onclick = my.cancel;
		};
	};
	this.accept = function() {
		var sender = $("messagesender").value;
		sender = encode(sender);
		var subject = $("messagesubject").value;
		var content = $("messagecontent").value;
		content = encode(content);

		if (subject.length != 0 && sender.length != 0) {
			var url = "hotelPms?CMD=5";
			url += "&t=" + Math.random();
			var param = "&folionum=" + id;
			param += "&sender=" + sender;
			param += "&subject=" + subject;
			param += "&content=" + content;
			var f = new AjaxPostText(url, param);
			f.complet = function(tx) {
				my.hide();
			};
		} else {
			var albox = new alertBox();
			albox.show("Subject or Content is empty!");
			return;
		}
	};
	this.update = function() {

	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		$("formmessage").style.display = "none";
		document.location.href = "folioPms?MenuId=4&SubId=11&t=0.9852005187422037";
		// var lsroom = new ListRoom();
		// lsroom.reload();
		// lsroom.loadcomplet(id);
	};
}

function encodetext(s){
//	return s.replace(/&/g, "&amp;").replace(/>/g, "&gt;").replace(/</g, "&lt;").replace(/"/g, "&quot;");
	return $('<div/>').text(s).html();
} 
function converHTMLtext(st)
{
	
	st=encode(st);
	var index1=st.indexOf("<");
	var index2=st.indexOf(">");
	
	while(index1>-1&&index2>-1)
	{
		var st1=st.substring(0,index1);
		var st2=st.substring(index2+1);
		st=st1+st2;
		index1=st.indexOf("<");
		index2=st.indexOf(">");
	}			
	return st;
}

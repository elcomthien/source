var hotel = new function Hotel() {
	var my = this;
	this.sub = new subject();
	this.list = new listHotel();

	this.onclick = function() {
		my.sub.pageclick();
		my.list.pageclick();
	}
	my.sub.loadComplet = function(obj) {
		my.list.load(obj.Id);
	}

	this.run = function() {
		my.sub.run();
	}
	my.run();
}
function subject() {
	var menu = new Tree();
	var ctMenu = new contextMenu();
	ctMenu.fucNew = function() {
		my.addSubject(this.obj);
	}
	ctMenu.fucdel = function() {
		my.deletesubject(this.obj);
	}
	ctMenu.fucedit = function() {
		my.editsubject(this.obj);
	}
	var my = this;
	this.divName = "menu";
	var _row = 0;
	this.ID = -1;
	this.List = new Array();
	this.defaults = {
		divName : "menutree",
		classTree : "treeview",
		title : "Subject",
		classItem : "itemnomal",
		classfocus : "itemfocus",
		classRootOpen : "treeviewrootopen",
		classRootClose : "treeviewrootcolse",
		clcassleaves : "treeviewleaves",
		classopen : "treeopen",
		classclose : "treecolse"
	}
	this.pageclick = function() {
		if (ctMenu.state)
			ctMenu.hide();
	}
	this.selectID = function(id) {
		my.ID = id;
		var a = $(menu.divName).getElementsByTagName("a");
		for ( var i = 0; i < a.length; i++) {
			if (a[i].id == id) {
				a[i].style.color = "#ab8718";
			} else {
				a[i].style.color = "#878888";
			}
		}
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == id) {
				my.loadComplet(my.List[i]);
				break;
			}
		}
	}
	this.addSubject = function(id) {
		var textbox = new pmsBoxSub(langpms.addSubject, "", "", "");
		textbox.show();
		textbox.accept = function(value, image1, image2) {
			if (value.length == 0) {
				var albox = new alertBox();
				albox.show(langpms.pls_Subject);
				return;
			}
			image1 = image1.substring(image1.lastIndexOf("/") + 1);
			image2 = image2.substring(image2.lastIndexOf("/") + 1);
			this.hide();
			var url = "roomservice?CMD=1";
			url += "&image1=" + image1;
			url += "&image2=" + image2;
			url += "&t=" + Math.random();
			var param = "&name=" + encode(value);
			var f = new AjaxPost(url, param);
			f.complet = function(tx) {
				my.get();
			}
		}
	}
	this.deletesubject = function(id) {
		var cfBox = new conformBox();
		cfBox.show();
		cfBox.accept = function() {
			var url = "hotelPms?CMD=3";
			url += "&SubId=" + id;
			url += "&t=" + Math.random();
			var f = new funPropery();
			f.complet = function(tx) {
				my.get();
			}
			AjaxFuncGetXML(url, f);
		}
	}
	this.editsubject = function(id) {
		var obj = null;
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == id) {
				obj = my.List[i];
				break;
			}
		}
		var im1 = obj.Image;
		var im2 = obj.ImageBG;
		var textbox = new pmsBoxSub(langpms.editSubject, obj.Name, im1, im2);
		textbox.show();
		textbox.accept = function(value, image1, image2) {
			if (value.length == 0) {
				var albox = new alertBox();
				albox.show(langpms.pls_Subject);
				return;
			}
			image1 = image1.substring(image1.lastIndexOf("/") + 1);
			image2 = image2.substring(image2.lastIndexOf("/") + 1);
			this.hide();
			var url = "hotelPms?CMD=2";
			url += "&image1=" + image1;
			url += "&image2=" + image2;
			url += "&SubId=" + id;
			url += "&t=" + Math.random();
			var param = "&name=" + encode(value);
			var f = new AjaxPost(url, param);
			f.complet = function(tx) {
				my.get();
			}
		}
	}
	this.setfocus = function() {
		var ul = $(menu.divName).getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");
		for ( var i = 0; i < li.length; i++) {
			var a = li[i].getElementsByTagName("a");
			if (li[i].id == _row) {
				a[0].className = "menu_item_focus";
			} else {
				a[0].className = "menu_item_a";
			}
		}
	}
	this.run = function() {
		menu.init(this.defaults);
		my.get();
	}
	this.loadmp3 = function(id) {
	}
	this.reload = function(id) {
		/**
		 * neu xoa trung id dang focus
		 */
		var url = "roomservice?CMD=1";
		url += "&r=" + Math.random();
		var f = new funPropery();
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _imagebg = it[i].getElementsByTagName("imagebg")[0].childNodes[0].nodeValue;
				var _parent = it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
				var item = new ItemMenu(_id, _parent, _name, _image);
				item.ImageBG = _imagebg;
				arr[i] = item;
			}
			my.List = arr;
			menu.reload(arr);
			my.selectID(id);
		}
		AjaxFuncGetXML(url, f);
	}
	this.subjectClick = function(id, name) {
	}
	this.loadComplet = function(id) {
	};
	menu.renderEvent = function() {
		var ul = $(this.divName).getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");
		var me = this;
		for ( var i = 0; i < li.length; i++) {
			var a = li[i].getElementsByTagName("a");
			var item = a[0];
			item.onclick = function(e) {
				my.selectID(this.id);
				me.change(this.id);
			};
			item.oncontextmenu = function(e) {
				my.selectID(this.id);
				return my.contexmenu(this.id, e);
			};
		}
	}
	this.contexmenu = function(id, e) {
		ctMenu.load(id, e);
		return false;
	}
	this.get = function() {
		var url = "roomservice?CMD=1";
		url += "&r=" + Math.random();
		var f = new funPropery();
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _imagebg = it[i].getElementsByTagName("imagebg")[0].childNodes[0].nodeValue;
				var _parent = it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
				var item = new ItemMenu(_id, _parent, _name, _image);
				item.ImageBG = _imagebg;
				arr[i] = item;
			}
			my.List = arr;
			if (my.List.length == 0)
				return;
			menu.load(arr);
			my.selectID(arr[0].Id);
		}
		AjaxFuncGetXML(url, f);
	}
}
function listHotel() {
	var table = new Table();
	var ctTable = new contextTable();
	ctTable.fucDetail = function() {
		var detail = new detailItem();
		detail.load(this.obj);
		detail.update = function() {
			my.reload();
		}
	}
	ctTable.fucdel = function() {
		var list = "";
		if (my.ListCheck.length == 0) {
			list += "&id0=" + this.obj;
		} else {
			for (i = 0; i < my.ListCheck.size(); i++) {
				list += "&id" + i + "=" + my.ListCheck.get(i);
			}
		}
		var cfBox = new conformBox();
		cfBox.show();
		cfBox.accept = function() {
			var url = "hotelPms?CMD=8";
			url += list;
			url += "&SubId=" + my.SubId;
			url += "&r=" + Math.random();
			var f = new funPropery();
			f.complet = function(tx) {
				my.reload();
			}
			AjaxFuncGetXML(url, f);
		}
	}
	ctTable.fucAdd = function() {
		var add = new addItemHotel();
		add.load(my.SubId);
		add.update = function() {
			my.reload();
		}
	}
	this.addeImage = function() {
		var add = new addItemHotel();
		add.load(my.SubId);
		add.update = function() {
			my.reload();
		}
	}
	ctTable.change = function() {
		var obj = this.obj;
		var url1 = "roomservice?CMD=6";
		url1 += "&id=" + obj.Id;
		url1 += "&r=" + Math.random();
		var obj = this.obj;
		var url2 = "roomservice?CMD=5";
		url2 += "&id=" + obj.Id;
		url2 += "&r=" + Math.random();
		var url3 = "roomservice?CMD=7";
		url3 += "&id=" + obj.Id;
		var change = new changeSubject(url2, url1, url3);
		change.load(obj);
		change.complet = function() {
			my.reload();
		}
	}
	ctTable.fucStatus = function() {
		var url = "hotelPms?CMD=9";
		url += "&id=" + this.obj.Id;
		url += "&r=" + Math.random();
		var f = new funPropery();
		f.complet = function(tx) {
			my.reload();
		}
		AjaxFuncGetXML(url, f);
	}
	this.pageclick = function() {
		if (ctTable.state)
			ctTable.hide();
	}
	var my = this;
	this.count = 0;
	var ID = 0;// id of subject
	this.SubId = -1;
	var Index = 0;
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
	}
	/**
	 * @param id
	 *            id subject
	 * @return
	 */
	this.load = function(id) {
		Index = 0;
		this.SubId = id;
		ID = id;
		table.init(defaults);
		my.get(id, Index);
	}
	this.reload = function() {
		Index = 0;
		my.get(ID, Index);
	}
	this.loadIdex = function(index) {
		Index = index;
		my.get(ID, index);
	}
	this.nextindex = function() {
		var page = 6;
		var cpage = ((my.count - 1) / page) - 1;
		if (Index < cpage)
			Index++;
		my.loadIdex(Index);
	}
	this.backindex = function() {
		if (Index > 0)
			Index--;
		my.loadIdex(Index);
	}
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
			$("div_page_icon_right").onclick = function() {
				my.nextindex();
			};
			$("div_page_icon_left").onclick = function() {
				my.backindex();
			};
		}
	}
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
	}
	this.addItem = function(id) {
		if (!this.ListCheck.isObject(id)) {
			this.ListCheck.add(id);
		}
		my.shiftSelect();
	}
	this.oncontextmenu = function(id, e) {
		var obj = null;
		for ( var i = 0; i < arr.length; i++) {
			if (arr[i].Id == id) {
				obj = arr[i];
				break;
			}
		}
		ctTable.load(obj, e);
	}
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_listhotel'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left'  valign=\"middle\"  width=\"" + 20 + "%\" >";
		html += langpms.name;
		html += "</th>";
		html += "<th align='left' width=\"" + 50 + "%\" valign=\"middle\" >";
		html += langpms.description;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\" >";
		html += langpms.status;
		html += "</th>";
		html += "</tr>";
		for ( var i = 0; i < this.List.length; i++) {
			var des = this.List[i].Des;
			if (des.length > 60)
				des = des.substring(0, 60) + "...";
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\" >";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\" >";
			}
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Title;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += (des);
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
		if (this.List.length == 0) {
			html += "<div  align='center'>";
			html += "<a href='javascript:hotel.list.addeImage()' class='div_addeImage' title='Click here add "
					+ langpms.addItem + "'>" + langpms.addItem + "</a>";
			html += "</div>";
		}
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
	}
	this.get = function(id, index) {
		var url = "hotelPms?CMD=2";
		url += "&SubId=" + id;
		url += "&pageIndex=" + index;
		url += "&page=6";
		url += "&r=" + Math.random();
		var f = new funPropery();
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _des = it[i].getElementsByTagName("Des")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				arr[i] = new ItemHotel(_id, _name, _des, _status);
			}
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem0", 0);
			my.renderEvent();
		}
		AjaxFuncGetXML(url, f);
	}
	this.loadcomplet = function() {
	}
}
function detailItem() {
	var my = this;
	var layer = new layer_vitual();
	this.obj = null;
	this.load = function(_obj) {
		this.obj = _obj;
		my.show();
	}
	this.show = function() {
		layer.show();
		var html = "";
		var url = "hotelPms?CMD=4";
		url += "&id=" + my.obj.Id;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_hotel");
			initTinymce(2, "540px");
			$("form_textbox_ok").onclick = function() {
				my.accept();
			};
			$("form_textbox_cancel").onclick = my.cancel;
		}
	}
	this.accept = function() {
		var name = $("name").value;
		name = encode(name);
		var image = $("image").src;
//		var des = getDataFromEditor("descriptionInput");
		var des = "Room Service";
		image = image.substring(image.lastIndexOf("/") + 1);
		if (name.length == 0) {
			var albox = new alertBox();
			albox.show("Name is empty");
			return;
		}
		;
		var url = "hotelPms?CMD=3"
		url += "&t=" + Math.random();
		var param = "&name=" + name;
		param += "&id=" + my.obj.Id;
		param += "&image=" + image;
		param += "&des=" + (des);
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			my.hide();
			my.update();
		}
	}
	this.update = function() {
	}
	this.cancel = function() {
		my.hide();
	}
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	}
}
function addItemHotel() {
	var my = this;
	var layer = new layer_vitual();
	this.subid = -1;
	this.load = function(id) {
		my.subid = id;
		my.show();
	}
	this.show = function() {
		layer.show();
		var html = "";
		var url = "hotelPms?CMD=4";
		url += "&id=-1";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_hotel");
			initTinymce(2, "540px");
			$("form_textbox_ok").onclick = function() {
				my.accept();
			};
			$("form_textbox_cancel").onclick = my.cancel;
		}
	}
	this.accept = function() {
		var name = $("name").value;
		name = encode(name);
		var image = $("image").src;
//		var des = getDataFromEditor("descriptionInput");
		var des = "Room Service";
		image = image.substring(image.lastIndexOf("/") + 1);
		if (name.length == 0) {
			var albox = new alertBox();
			albox.show("Name  is empty");
			return;
		}
		;
		var url = "hotelPms?CMD=4";
		url += "&t=" + Math.random();
		var param = "&name=" + name;
		param += "&id=" + my.subid;
		param += "&image=" + image;
		param += "&des=" + (des);
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			my.hide();
			my.update();
		}
	}
	this.update = function() {
	}
	this.cancel = function() {
		my.hide();
	}
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	}
}
function contextMenu() {
	var my = this;
	this.state = false;
	this.obj = null;
	this.load = function(obj, e) {
		my.obj = obj;
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
	}
	this.draw = function(x, y) {
		my.state = true;
		var html = "";
		html += "<ul>";
		html += "<li class='icon_contextmenuAdd'>";
		html += "<a>" + langpms.addSubject + "</a>"
		html += "</li>";
		html += "<li class='icon_contextmenuDel'>";
		html += "<a>" + langpms.deleteSubject + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuEdit'>";
		html += "<a>" + langpms.editSubject + "</a>";
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
	}
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	}
	this.renderClick = function() {
		var ul = $("div_contextMenu").getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");
		var itemnew = li[0].getElementsByTagName("a")[0];
		itemnew.onclick = function() {
			my.fucNew();
		};
		var itemdel = li[1].getElementsByTagName("a")[0];
		itemdel.onclick = function() {
			my.fucdel();
		};
		var itemedit = li[2].getElementsByTagName("a")[0];
		itemedit.onclick = function() {
			my.fucedit();
		};
	}
	this.fucNew = function() {
	}
	this.fucdel = function() {
	}
	this.fucedit = function() {
	}
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
		if (posy > 466) {
			posy = posy - 50;
		}
		my.draw(posx, posy - 10);
	}
	this.draw = function(x, y) {
		my.state = true;
		var html = "";
		html += "<ul>";
		html += "<li class='icon_contextmenuAdd'>";
		html += "<a>" + langpms.addItem + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuEdit'>";
		html += "<a>" + langpms.detailItem + "</a>"
		html += "</li>";
		html += "<li class='icon_contextmenuDel'>";
		html += "<a>" + langpms.removeItem + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenucopy'>";
		html += "<a>" + langpms.changeSubject + "</a>";
		html += "</li>";
		if (my.obj.Status == 1) {
			html += "<li class='icon_visibility1'>";
		} else {
			html += "<li class='icon_visibility2'>";
		}
		html += "<a>" + langpms.changeStatus + "</a>";
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
		var eh = $("div_contextMenu").style.height;
		my.renderClick();
		eventMouse();
	}
	this.renderClick = function() {
		var ul = $("div_contextMenu").getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");
		var itemdetail = li[1].getElementsByTagName("a")[0];
		itemdetail.onclick = function() {
			my.fucDetail();
		};
		var itemdel = li[2].getElementsByTagName("a")[0];
		itemdel.onclick = function() {
			my.fucdel();
		};
		var itemchange = li[3].getElementsByTagName("a")[0];
		itemchange.onclick = function() {
			my.change();
		};
		var itemAdd = li[0].getElementsByTagName("a")[0];
		itemAdd.onclick = function() {
			my.fucAdd();
		};
		var itemstatus = li[4].getElementsByTagName("a")[0];
		itemstatus.onclick = function() {
			my.fucStatus();
		};
	}
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	}
}
addLoadEvent();
function addLoadEvent() {
	window.onload = function() {
		document.body.onclick = hotel.onclick;
	}
}
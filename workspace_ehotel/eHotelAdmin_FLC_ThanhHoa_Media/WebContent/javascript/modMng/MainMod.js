var rte = new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");

var mod = new function Mod() {
	var tag = 0;
	var my = this;
	this.sub = new subject();
	this.mp3 = new contentMp3();
	var ctMenu = new contextMenu();
	this.addMod = new AddMod();
	this.ctrl = false;
	this.onclick = function() {
		my.sub.pageclick();
		my.mp3.pageclick();
	}
	my.sub.loadComplet = function(obj) {
		if (tag == 0) {
			$("title_subject").innerHTML = obj.Name;
			$("div_subject").innerHTML = obj.Name;
			my.mp3.load(obj.Id, obj.Name);
		} else {
			$("div_subject").innerHTML = obj.Name;
			my.addMod.SubID = obj.Id;
		}
	}
	my.sub.subjectClick = function(id, name) {

	}
	this.changeTab = function(id) {
		var div = $("tab_menu").getElementsByTagName("div");
		tag = id;
		if (id == 0) {
			div[0].className = "tab_menu_item_sel";
			div[1].className = "tab_menu_item";
			this.loadtabList();
		} else {
			div[1].className = "tab_menu_item_sel";
			div[0].className = "tab_menu_item";
			this.loadtabAdd();
		}
	}
	this.loadtabAdd = function() {
		$("div_addmusic").style.display = "block";
		$("id_table").style.display = "none";

		this.addMod.load();
	}
	this.loadtabList = function() {
		$("div_addmusic").style.display = "none";
		$("id_table").style.display = "block";
		my.mp3.reload();
	};
	/**
	 */
	this.run = function() {
		my.sub.run();
	};
	this.setKeyEventHandler = function() {
		var d = document;
		if (d.addEventListener) { // key process
			// d.onkeypress=onkeypress;
			// d.onkeyup=mod.onkeyup;
			// d.onkeydown=mod.onkeydown;
		} else {
			if (d.attachEvent) {
				// d.attachEvent('onkeypress',onkeypress);
				// d.attachEvent('onkeydown',mod.onkeydown);
				// d.attachEvent('onkeyup',mod.onkeyup);
			}
		}
	};
	function onkeypress(e) {
		var keycode;
		if (window.event)
			keycode = window.event.keyCode;
		else if (e)
			keycode = e.which;

	}
	this.onkeydown = function(e) {
		var keycode;
		if (window.event)
			keycode = window.event.keyCode;
		else if (e)
			keycode = e.which;

		switch (keycode) {
		case 17:
			my.ctrl = true;
			setonmouse(false)
			break;

		default:
			break;
		}
		return true;
	}
	this.onkeyup = function() {
		setonmouse(true);
		my.ctrl = false;
		return true;
	}
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

		var textbox = new BoxSubject(lang.addSubject, "", "", "");
		textbox.show();
		textbox.accept = function(text, image1, image2) {
			image1 = image1.substring(image1.lastIndexOf("/") + 1);
			image2 = image2.substring(image2.lastIndexOf("/") + 1);
			if (text.length <= 0) {
				var albox = new alertBox();
				albox.show("Please input subject name !");
				return;
			}
			this.hide();
			var url = "ModCtnMain?CMD=1";
			url += "&t=" + Math.random();
			url += "&image=" + image1;
			url += "&urlBg=" + image2;
			url += "&parent=" + id;
			var param = "&name=" + text;
			var f = new AjaxPost(url, param);
			f.complet = function(tx) {
				my.reload(id);
			}
		}
	}
	this.deletesubject = function(id) {
		var cfBox = new conformBox();
		cfBox.show();
		cfBox.accept = function() {
			var url = "ModCtnMain?CMD=3";
			url += "&id=" + id;
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
		var textbox = new BoxSubject(lang.editSubject, obj.Name, obj.Image,
				obj.ImageBg);
		textbox.show();
		textbox.accept = function(text, image1, image2) {
			image1 = image1.substring(image1.lastIndexOf("/") + 1);
			image2 = image2.substring(image2.lastIndexOf("/") + 1);
			if (text.length <= 0) {
				var albox = new alertBox();
				albox.show("Please input subject name !");
				return;
			}
			this.hide();
			var url = "ModCtnMain?CMD=2";
			url += "&subid=" + id;
			url += "&t=" + Math.random();
			url += "&image=" + image1;
			url += "&urlBg=" + image2;
			var param = "&name=" + encode(text);
			var f = new AjaxPost(url, param);
			f.complet = function(tx) {
				my.reload(id);
			}
		}
	}

	this.setfocus = function() {
		var ul = $(menu.divName).getElementsByTagName("ul")
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
	this.drawHTML = function() {
		var html = "";
		html += "<div class=\"menu_header\">";
		html += "<div style=\"float: left;color: white;font-size: 20px;margin-left: 20px;margin-top: 15px;font-weight: bold;\">";
		html += lang.Subject;
		html += "</div>";
		html += "</div>";
		html += "<div class=\"menu_center\" align=\"center\" >";
		html += "<div id='menutree' class='menutree' ></div>";
		html += "<div class='div_linkadd'><a href='javascript:mod.sub.addSubject(-1)' style='color: #000066;'>"
				+ lang.addSubject + "</a></div>";
		html += "</div>";
		html += "<div class=\"menu_bottom\">";
		html += "</div>";
		$(my.divName).innerHTML = html;
	}
	this.run = function() {
		menu.init(this.defaults);
		my.drawHTML();
		my.get();
	}
	this.loadmp3 = function(id) {

	}
	this.reload = function(id) {
		/**
		 * neu xoa trung id dang focus
		 */
		var url = "ModCtnMain?CMD=1";
		url += "&r=" + Math.random();
		var f = new funPropery();
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _parent = it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
				var _imageBg = it[i].getElementsByTagName("imageBg")[0].childNodes[0].nodeValue;
				arr[i] = new ItemMenu(_id, _parent, _name, _image);
				arr[i].ImageBg = _imageBg;
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
		var ul = $(this.divName).getElementsByTagName("ul")
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
		// my.mp3.load(obj.Id,obj.Name);
		ctMenu.load(id, e);
		return false;
	}
	this.get = function() {
		var url = "ModCtnMain?CMD=1"
		url += "&r=" + Math.random();
		var f = new funPropery();
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _parent = it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
				var _imageBg = it[i].getElementsByTagName("imageBg")[0].childNodes[0].nodeValue;
				arr[i] = new ItemMenu(_id, _parent, _name, _image);
				arr[i].ImageBg = _imageBg;
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
/**
 * 
 * 
 * @return
 */
function contentMp3() {
	var table = new Table();
	var ctTable = new contextTable();
	ctTable.fucNew = function() {
		// var play=new playMod();
		// play.load(this.obj.Url)
		var albox = new alertBox();
		albox.show("Chuc nang nay chua mo !");
	}

	ctTable.fucdel = function() {
		// my.ListCheck
		var list = "";
		if (my.ListCheck.length == 0) {
			list += "&id0=" + this.obj;
		} else {
			for (i = 0; i < my.ListCheck.length; i++) {
				list += "&id" + i + "=" + my.ListCheck[i];
			}
		}
		var cfBox = new conformBox();
		cfBox.show();
		cfBox.accept = function() {
			var url = "ModCtnMain?CMD=4";
			url += list;
			url += "&r=" + Math.random();
			var f = new funPropery();
			f.complet = function(tx) {
				my.reload();
			}
			AjaxFuncGetXML(url, f);
		}
	}
	ctTable.fucedit = function() {
		var obj = this.obj;
		var detail = new detailSong();
		detail.load(obj);
		detail.update = function() {
			my.get(ID, Index);
		}
	}
	// them vao bo sung chuc nang play audio 4.2
	ctTable.fucPlayAudio = function(rel) {
		var eb = new playEmbedAudio(rel);
		eb.show(rel);
	}
	ctTable.change = function() {
		var obj = this.obj;
		var change = new changeSubject();
		change.load(obj);
	}
	this.pageclick = function() {
		if (ctTable.state)
			ctTable.hide();
	}
	var my = this;
	this.count = 0;
	var ID = 0;// id of subject
	var Index = 0;
	this.ListCheck = new Array();
	var arr = new Array();
	var defaults = {
		divName : "id_table",
		col : 6,
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
	this.load = function(id, name) {
		Index = 0
		this.subname = name;
		ID = id;
		table.init(defaults);
		my.get(id, Index);
	}
	this.reload = function() {
		Index = 0
		my.get(ID, Index);
	}
	this.loadIdex = function(index) {
		Index = index
		my.get(ID, index);
	}
	this.nextindex = function() {
		var page = 5;
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
				if (mod.ctrl) {
					my.addItem(this.id);
				} else {
					my.selectItem(this.id);
				}
			};
			item.oncontextmenu = function(e) {
				if (mod.ctrl) {
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
		my.ListCheck = new Array();
		my.ListCheck[0] = id;
		my.shiftSelect();
	}
	this.addItem = function(id) {

		var is = false;
		for ( var i = 0; i < my.ListCheck.length; i++) {
			if (my.ListCheck[i] == id) {
				is = true;
				break;
			}
		}
		if (is) {
			var ar = new Array();
			var j = 0;
			for ( var i = 0; i < my.ListCheck.length; i++) {
				if (my.ListCheck[i] != id) {
					ar[j] = my.ListCheck[i];
					j++;
				}
			}
			;
			my.ListCheck = ar;
		} else {
			my.ListCheck[my.ListCheck.length] = id;
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
		html += "<div class='div_table'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";

		html += "<th align='left'  valign=\"middle\"  width=\"" + 40
				+ "%\" class='header0' >";
		html += lang.AudioTitle;
		html += "</th>";
		html += "<th align='left' width=\"" + 20 + "%\" valign=\"middle\"  >";
		html += lang.Singer;
		html += "</th>";

		html += "<th align='left' width=\"" + 30 + "%\" valign=\"middle\"  >";
		html += lang.Album;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += lang.Status;
		html += "</th>";
		html += "</tr>";

		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id
						+ "\" oncontextmenu='mod.oncontextmenu("
						+ this.List[i].Id + ");return false;' >";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id
						+ "\" oncontextmenu='mod.oncontextmenu("
						+ this.List[i].Id + ");return false;'  >";
			}
			html += "<td align=\"left\"  valign=\"middle\" class='header0'>";
			html += this.List[i].Title;
			html += "</td>";

			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Singer;
			html += "</td>";

			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Album;
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			html += "<img src=\"../icon/16-square-green.png\"></img>";
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
	}
	this.shiftSelect = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			if (i % 2 == 0) {
				tr[i].className = table.bgRow1;
			} else {
				tr[i].className = table.bgRow2;
			}
			for ( var j = 0; j < my.ListCheck.length; j++) {
				if (tr[i].id == my.ListCheck[j]) {
					tr[i].className = table.classRowsel;
					break;
				}
			}
		}

	}
	this.get = function(id, index) {
		var url = "ModCtnMain?CMD=2";
		url += "&SubId=" + id;
		url += "&pageIndex=" + index;
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
				var _singer = it[i].getElementsByTagName("Singer")[0].childNodes[0].nodeValue;
				var _url = it[i].getElementsByTagName("url")[0].childNodes[0].nodeValue;
				var _album = it[i].getElementsByTagName("Album")[0].childNodes[0].nodeValue;
				var _lyric = it[i].getElementsByTagName("lyric")[0].childNodes[0].nodeValue;
				arr[i] = new ItemMp3(_id, _name, _singer, _album, _url, _lyric);
			}
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem0", 0);
			table.setwidthHeader(500, 0);
			my.renderEvent();
			my.loadcomplet();
		}
		AjaxFuncGetXML(url, f);
	}
	this.loadcomplet = function() {
		$("title_subject").innerHTML = my.subname + "(" + my.count + ")";
	}
}
function Menu() {
	var my = this;
	this.List = new Array();
	this.defaults = {
		divName : "",
		title : "",
		classItem : "itemnomal",
		classfocus : "itemfocus"
	}
	this.init = function(options) {

		for ( var name in my.defaults) {
			this[name] = (options !== undefined && options[name] !== undefined) ? options[name]
					: my.defaults[name];
			if (options !== undefined && options[name] !== undefined) {
				my.defaults[name] = options[name];
			}
			my[name] = this[name];
		}
		$(my.divName).innerHTML = "";
	};

	this.load = function(_list) {
		my.List = _list;
		this.drawHTML();
		this.renderEvent();
		this.loadComplet(my.List[0].Id);

	}
	this.loadComplet = function(id) {
		return id;
	}
	this.drawHTML = function() {
		var html = "";
		html += "<div class=\"menu_header\">";
		html += "<div style=\"float: left;color: white;font-size: 20px;margin-left: 20px;margin-top: 15px;font-weight: bold;\">";
		html += my.title;
		html += "</div>";
		html += "</div>";
		html += "<div class=\"menu_center\" align=\"center\" >";
		html += "<ul>"
		for ( var i = 0; i < my.List.length; i++) {
			html += "<li id='" + my.List[i].Id + "'>"
			html += "<a class='menu_item_a'>";
			html += my.List[i].Name;
			html += "</a>";
			html += "</li>";
		}
		html += "	</ul>";
		html += "</div>";
		html += "</div>";
		html += "<div class=\"menu_bottom\">";
		html += "</div>";
		$(my.divName).innerHTML = html;

	}
	this.renderClick = function() {
		var ul = $(my.divName).getElementsByTagName("ul")
		var li = ul[0].getElementsByTagName("li");
		for ( var i = 0; i < li.length; i++) {
			var a = li[i].getElementsByTagName("a");
			var item = a[0];
			item.setAttribute("href", "javascript:alert(1);");
		}
	}
	this.rendercontexmenu = function() {

	}
	this.onclick = function(id) {

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
		html += "<a>" + lang.addSubject + "</a>"
		html += "</li>";
		html += "<li class='icon_contextmenuDel'>";
		html += "<a>" + lang.deleteSubject + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuEdit'>";
		html += "<a>" + lang.editSubject + "</a>";
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
		var ls = this.obj.Url;
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
		my.draw(posx, posy - 10, ls);
	}
	this.draw = function(x, y, ls) {
		var link = iponly + "/mp3/" + ls;
		my.state = true;
		var html = "";
		html += "<ul>";
		html += "<li class='icon_contextmenuEdit'>";
		html += "<a>" + lang.edit_song + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuDel'>";
		html += "<a>" + lang.DeleteSong + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenucopy'>";
		html += "<a>" + lang.changeSubject + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuplay'>";
		html += "<a rel='" + link + "'>" + lang.checkAudio + "</a>";
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
	this.renderClick = function() {
		var ul = $("div_contextMenu").getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");

		var itemdel = li[1].getElementsByTagName("a")[0];
		itemdel.onclick = function() {
			my.fucdel();
		};
		var itemchange = li[2].getElementsByTagName("a")[0];
		itemchange.onclick = function() {
			my.change();
		};
		var itemedit = li[0].getElementsByTagName("a")[0];
		itemedit.onclick = function() {
			my.fucedit();
		};
		var playembed = li[3].getElementsByTagName("a")[0];
		var rellink = playembed.getAttribute("rel");
		playembed.onclick = function() {
			my.fucPlayAudio(rellink);
		};
	}
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	}
}
function changeSubject() {
	var my = this;
	this.obj = null;
	var layer = new layer_vitual();
	this.load = function(_obj) {
		my.obj = _obj;
		my.show();
	}
	this.show = function() {

		layer.show();
		var html = "";
		html += "<div class=\"form_change_subject\" id=\"form_change_subject\">";
		html += "<div class='div_Title_song' align=\"center\">"
				+ lang.ChangeSubject;
		html += "</div>";
		html += "<div class='change_subject_top'>";
		html += "</div>";
		// html+="<div style='float:left;width:100%;height: auto;'>";
		html += "<div class='change_subject_center'  >";
		html += "<div style='float:left;width:100%;height: 200px;margin-top:10px;'>"
		html += "<div class='left_change_subject' id='left_change_subject'>";
		html += "</div>";

		html += "<div style='float:left;width:20;' align=\"center\">"
		html += "<div class='add_subject' id='add_subject'></div>"
		html += "<div class='remove_subject' id='remove_subject'></div>"
		html += "</div>";
		html += "<div class='right_change_subject' id='right_change_subject'>";
		html += "</div>";

		html += "</div>"
		html += "<div align='center' class='div_sub_buton' style='float:left;width:100%;'>";
		html += "<div style='width:160'>"
		html += "<input type=\"button\" 	' value='  OK  ' id=\"form_textbox_ok\" class='div_buton'/ >"
		html += "<input type=\"button\" value=Cancel id=\"form_textbox_cancel\" class='div_buton'/>"
		html += "</div>"
		html += "</div>"
		html += "</div>"
		html += "<div class='change_subject_bottom'>";
		html += "</div>";
		// html+="</div>";
		html += "</div>";
		layer.addHtml(html);
		var x = 0;
		var y = 0;
		x = document.body.clientWidth + document.body.scrollLeft
				+ document.body.scrollLeft;
		y = document.body.clientHeight + document.body.scrollTop
				+ document.body.scrollTop;
		$("form_change_subject").style.left = (x / 2) - 300 + "px";
		$("form_change_subject").style.top = (y / 2) - 200 + "px";
		// render function
		$("form_textbox_ok").onclick = function() {

			my.accept();
		};
		$("form_textbox_cancel").onclick = my.cancel;
		$("add_subject").onclick = this.addsubject;
		$("remove_subject").onclick = this.removesubject;
		//
		my.loadleft();
		my.loadright();

	}
	this.loadleft = function() {
		var url = "ModCtnMain?CMD=6";
		url += "&modId=" + my.obj.Id;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _parent = it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;

				arr[i] = new ItemMenu(_id, _parent, _name, _image);
			}
			var html = ""
			html += "<select size=\"10\" multiple=\"multiple\" id='list_select1' class='list_select_sub'>";

			for ( var i = 0; i < arr.length; i++) {
				html += "<option value='" + arr[i].Id + "'>" + arr[i].Name
						+ "</option>";
			}

			html += "</select>";
			// alert(arr.length);
			html += "<div>" + lang.select_subject + "</div>"
			$("left_change_subject").innerHTML = html
			my.eventrender();
			return arr;
		}
	}

	this.loadright = function() {
		var url = "ModCtnMain?CMD=5";
		url += "&modId=" + my.obj.Id;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");

			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _parent = it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
				arr[i] = new ItemMenu(_id, _parent, _name, _image);

			}
			var html = ""
			html += "<select size=\"10\" multiple=\"multiple\" id='list_select2' class='list_select_sub'>";

			for ( var i = 0; i < arr.length; i++) {
				html += "<option value='" + arr[i].Id + "'>" + arr[i].Name
						+ "</option>";
			}
			html += "</select>";
			html += "<div>" + lang.CurrentSubject + "</div>";
			$("right_change_subject").innerHTML = html;
			my.eventrender();
			return arr;

		}
	}
	this.eventrender = function() {
		var select1 = $("list_select1");
		select1.ondblclick = function() {
			my.addsubject();
		};
		var select2 = $("list_select2");
		select2.ondblclick = function() {
			my.removesubject();
		};
	}
	this.addsubject = function() {

		var select1 = $("list_select1");
		var select2 = $("list_select2");
		if (select1.selectedIndex < 0) {
			var albox = new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		}
		for ( var i = 0; i < select1.options.length; i++) {
			if (select1.options[i].selected == true) {
				select2.add(new Option(select1.options[i].text,
						select1.options[i].value));
				select1.remove(i);
				i--;
			}
		}

	}
	this.removesubject = function() {
		var select1 = $("list_select1");
		var select2 = $("list_select2");
		if (select2.selectedIndex < 0) {
			var albox = new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		}
		for ( var i = 0; i < select2.options.length; i++) {
			if (select2.options[i].selected == true) {
				select1.add(new Option(select2.options[i].text,
						select2.options[i].value));
				select2.remove(i);
				i--;
			}
		}
	}
	this.accept = function() {
		var select2 = $("list_select2");
		if (select2.options.length <= 0) {
			var albox = new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		}
		var param = "";
		for ( var i = 0; i < select2.options.length; i++) {
			param += "&subid" + i + "=" + select2.options[i].value;
		}
		var url = "ModCtnMain?CMD=7";
		url += "&modId=" + my.obj.Id;
		url += param;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			mod.mp3.reload();
			my.hide();
		}
	}
	this.cancel = function() {
		my.hide();
	}
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	}
}
function detailSong() {

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
		html += "<div class=\"form_detail_song\" id=\"form_detail_song\">";
		html += "<div class='div_Title_song' align=\"left\">" + lang.edit_song;
		html += "</div>";
		html += "<div class='div_name_song'>";
		html += "<div class='left_detail'>" + lang.AudioTitle + ":</div>"
		html += "<div class='right_detail'> <input type=\"text\" id='title' class='textbox_detail' value='"
				+ my.obj.Title + "'/></div>"
		html += "</div>";
		html += "<div class='div_singer_song'>";
		html += "<div class='left_detail'>" + lang.Singer + ":</div>"
		html += "<div class='right_detail'> <input type=\"text\" id='singer' class='textbox_detail'  value='"
				+ my.obj.Singer + "'/></div>"
		html += "</div>";
		html += "<div class='div_album_song'>";
		html += "<div class='left_detail'>" + lang.Album + ":</div>"
		html += "<div class='right_detail'> <input type=\"text\" id='album' class='textbox_detail'  value='"
				+ my.obj.Album + "'/></div>"
		html += "</div>";
		html += "<div class='div_lyric_song'>";
		html += "<div class='left_detail' style='height:30;width:100%;'>"
				+ lang.lyric + ":</div>"
		html += "<div class='right_detail' id='lyric' style='margin-left: 10px;' >";
		html += "<div style=\"float: left;margin-left: 10px;\">";
		html += "<textarea rows=\"8\" cols=\"9\" name=\"descriptionInput\" style=\"width: 485px; border: 1px solid #ddddd;margin-left: 5px;float: left;\" id=\"descriptionInput\"> ";
		html += "" + my.obj.Lyric;
		html += "</textarea>";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		html += "<div style='float: left;width: 100%;height:30px;' align='center'>"
		html += "<div  style='width: 170;height:30px'>";
		html += "<input type=\"button\" value='" + lang.ok
				+ "' id=\"form_textbox_ok\" class='div_buton' >"
		html += "<input type=\"button\" value=" + lang.cancel
				+ " id=\"form_textbox_cancel\" class='div_buton'>"
		html += "</div>"
		html += "</div>";
		html += "</div>";
		layer.addHtml(html);

		setCenterDIV("form_detail_song");
		$("form_textbox_ok").onclick = function() {
			my.accept();
		};
		$("form_textbox_cancel").onclick = my.cancel;
		initTinymce(2, "570px");
	}
	this.accept = function() {
		var title = encode($("title").value);
		var singer = encode($("singer").value);
		var album = encode($("album").value);
		var slyriv = getDataFromEditor("descriptionInput");
		if (title.length == 0) {
			var albox = new alertBox();
			albox.show("Name song is empty");
			return;
		}
		;
		var url = "ModCtnMain?CMD=3"
		url += "&t=" + Math.random();
		var param = "&name=" + title;
		param += "&id=" + my.obj.Id;
		param += "&singer=" + singer;
		param += "&album=" + album;
		param += "&lyric=" + slyriv;
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
function playMod() {
	this.url = "";
	var my = this;
	this.load = function(url) {
		my.url = url;
		my.show();
	}
	this.show = function() {
		var html = "";
		html += "<div class='objectplay'>"

		html += "<OBJECT  width=\"320\" height=\"55\" "
		html += "codebase=http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701 "
		html += "CLASSID=\"CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6\""
		html += " type=\"application/x-oleobject\">"
		html += "	<PARAM NAME=\"URL\" VALUE=\"localhost\">"
		html += "	<PARAM NAME=\"SendPlayStateChangeEvents\" VALUE=\"True\">"
		html += "	<PARAM NAME=\"SendPlayStateChangeEvents\" VALUE=\"True\">";
		html += "	<PARAM NAME=\"AutoStart\" VALUE=\"true\">"

		html += "	<PARAM name=\"PlayCount\" value=\"9999\">"
		html += "</OBJECT>"
		html += '<embed type="application/x-shockwave-flash"  style="" id="mpl" name="mpl" quality="high" allowfullscreen="true" allowscriptaccess="always" wmode="opaque" flashvars="playlistfile=http://hn.nhac.vui.vn/asx2.php%3Ftype%3D1%26id%3D9837&amp;frontcolor=3333FF&amp;lightcolor=3333FF&amp;screencolor=2A2A2A&amp;bufferlength=10&amp;volume=100&amp;playlist=bottom&amp;playlistsize=60&amp;autostart=true&amp;repeat=always&amp;controlbar=bottom&amp;dock=false&amp;plugins=http://hn.nhac.vui.vn/upload/players/spectrumvisualizer-1.swf&amp;spectrumvisualizer.effect=reflection" width="508" height="50">';

		html += "</div>";
		html += "	<div class='div_bt_play'>"
		html += "<input type=\"button\" value=\"OK\" id='bt_ok_play' class='div_buton'>"
		html += "</div>"
		if ($("idplay") == undefined) {
			var div = document.createElement("div");
			div.id = "idplay";
			div.className = "div_playmod";
			div.innerHTML = html
			document.body.appendChild(div);
		} else {
			$("idplay").style.display = "block";
		}
		var x = 0;
		var y = 0;
		x = document.body.clientWidth + document.body.scrollLeft
				+ document.body.scrollLeft;
		y = document.body.clientHeight + document.body.scrollTop
				+ document.body.scrollTop;
		div.style.left = (x / 2) - 300 + "px";
		div.style.top = (y / 2) - 220 + "px";
		//
		$("bt_ok_play").onclick = function() {
			$("idplay").style.display = "none";
		}
	}
}

function setonmouse(state) {
	if (document.all) {
		document.onselectstart = function() {
			return state;
		};
	} else {
		document.body.onmousedown = function() {
			return state;
		};
	}
	if (document.layers) {
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown = function(evt) {
			return state;
		};
	}
}
function editBox(obj) {
	var my = this;
	this.show = function() {
		if ($("div_layer_vitual") == undefined) {
			var div = document.createElement("div");
			div.id = "div_layer_vitual";
			div.className = "div_layer_vitual";
			document.body.appendChild(div);
			// $("div_layer_vitual").style.display="block";
		} else {
			$("div_layer_vitual").style.display = "block";
		}
		var html = "";
		var linkImg = linksaveimage + obj.Image;
		html += "<div class=\"form_textbox\" id=\"form_textbox\"  align='center'>";
		html += "<div class='div_form_titile'></div>"
		html += "<div class='form_textbox_top'>"
		html += "</div>"
		html += "<div class='form_textbox_center'>"
		html += "<div class=\"form_textbox_subject\">Edit Subject</div>";
		html += "<div class=\"form_textbox_input\">";
		html += "<input type=\"text\" id=\"form_textbox_input\" value='"
				+ obj.Name + "'>"
		html += "</div>"
		html += "<div class='div_subject_image'>";
		html += "<img id='image' src=\"" + linkImg
				+ "\" width='100' height='100'></img>";
		html += "</div>"
		html += "<div class='div_upload'>"
		html += "	<iframe class='frame_upload' src=\"../upload\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' scrolling=\"no\" frameborder='0' align=\"top\" width=\"90%\" height=\"40px\" ></iframe>"
		html += "</div>"
		html += "<div class='form_1' >";
		html += "<input type=\"button\" value='  OK  '  class='div_buton'id=\"form_textbox_ok\" >"
		html += "<input type=\"button\" value=Cancel  class='div_buton' id=\"form_textbox_cancel\">"
		html += "</div>"
		html += "</div>"
		html += "<div class='form_textbox_bottom'>"
		html += "</div>"
		html += "</div>";

		var x = 0;
		var y = 0;

		x = document.body.clientWidth + document.body.scrollLeft
				+ document.body.scrollLeft;
		y = document.body.clientHeight + document.body.scrollTop
				+ document.body.scrollTop;

		$("div_layer_vitual").innerHTML = html;

		$("form_textbox").style.left = (x / 2) - 100 + "px";
		$("form_textbox").style.top = (y / 2) - 150 + "px";

		$("form_textbox_ok").onclick = function() {
			var value = $("form_textbox_input").value;
			var image = $("image").src;
			my.accept(value, image);

		};
		$("form_textbox_cancel").onclick = my.cancel;
	}
	this.accept = function(t) {
		my.hide();
	}
	this.cancel = function() {
		my.hide();
	}
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	}
}

function playEmbedAudio(name) {
	var my = this;
	var layer = new layer_vitual();
	this.obj = null;
	this.load = function(_obj) {
		this.obj = _obj;
		my.show();
	}
	this.show = function(name) {
		layer.show();
		var embedlink = "<embed name='embedplay' id='embedplay' type='application/x-vlc-plugin' name='player' autoplay='false' ";
		embedlink += "loop='no' src='" + name
				+ "' width='450px;' height='350px;'/>";

		var embedie = "<object type='application/x-vlc-plugin' id='embedobj' width='450px' height='350px'";
		embedie += " classid='clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921'>";
		embedie += "<param id='ieparam' name='src' value='" + name + "'/>";
		embedie += "<param name='autoplay' value='false'/>";
		embedie += "<param name='toolbar' value='true' />";
		embedie += "</object>";

		var html = "<div class='form_detail_song' id='div_playEmbed' align='center' style='height: 450px;'>";
		html += "<div class='div_Title_song'>";
		html += "CHECK AUDIO LINK</div>";
		html += "<div align='center' >";
		html += embedlink;
		html += embedie;
		html += "</div>";
		html += "<div style='float: left;width: 100%;height:30px;' align='center'>";
		html += "<div style='width: 70;height:30px'>";
		html += "<input type=\"button\" value='" + langMain.ok
				+ " '  class='div_buton'id=\"button_ok\" >";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		layer.addHtml(html);

		// run on IE only to active vlc 16.1
		if (/* @cc_on!@ */false) {
			document.documentElement.className += ' ie10';
			document.getElementById("embedplay").style.display = "none";
			document.getElementById("embedobj").style.display = "inline-block";
			document.getElementById("embedobj").style.width = 450;
			document.getElementById("embedobj").style.height = 350;
		} else {
			document.getElementById("embedplay").style.display = "inline-block";
			document.getElementById("embedobj").style.display = "none";
		}
		setCenterDIV("div_playEmbed");
		$("button_ok").onclick = function() {
			my.accept();
			my.hide();
		};
	}
	this.accept = function() {
		my.hide();
	}
	this.hide = function() {
		document.getElementById("embedplay").setAttribute("src", "");
		$("div_layer_vitual").style.display = "none";
		document.getElementById("embedobj").removeNode(true);
		// dang co loi tu dong play audio 16.1
	}
}

function BgType() {
	var list = new ListType();
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

function ListType() {
	var Index = 0;
	var table = new Table();
	var ctTable = new contextTable();
	var path = "";
	this.addeImage = function() {
		var add = new addItemAttaction();
		add.load(my.SubId);
		add.update = function() {
			my.reload();
		};
	};

	this.pageclick = function() {
		// if(ctTable.state) ctTable.hide();
	};
	var my = this;
	this.count = 0;
	var ID = 0;// id of subject
	this.SubId = -1;
	this.ListCheck = new Vector();
	var arr = new Array();
	var defaults = {
		divName : "id_table_bg_type",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable2_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable2_row1",
		bgRow2 : "mytable2_row2"
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
		html += "<div class='div_formother1' style='overflow-y:hidden; margin-top:0px; height: auto;'>";
		html += "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"97%\" class=\""
				+ this.classTable
				+ "\" id=\"table_"
				+ this.divName
				+ "\" style='margin-top:10px; margin-bottom: 10px;'>";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left' valign=\"middle\"  width=\"" + 50
				+ "%\" class='header0' >";
		html += "Folio Type";
		html += "</th>";
		html += "<th align='center' width=\"" + 20 + "%\" valign=\"middle\">";
		html += langpms.image;
		html += "</th>";
		html += "<th align='center' width=\"" + 20 + "%\" valign=\"middle\">";
		html += "Music";
		html += "</th>";
		// html+="<th align='center' width=\""+10+"%\" valign=\"middle\">";
		// html+="Action";
		// html+="</th>";
		html += "</tr>";

		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr rel=" + this.List[i].Name + " class=\""
						+ this.bgRow2 + "\" id=\"" + this.List[i].Id + "\">";
			} else {
				html += "<tr rel=" + this.List[i].Name + " class=\""
						+ this.bgRow1 + "\" id=\"" + this.List[i].Id + "\">";
			}
			;

			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Name;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += "<img class='im_ontable' src='" + this.List[i].BgPic
					+ "'></img>";
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].BgMusic;
			html += "</td>";
			// html+="<td align=\"center\" valign=\"middle\">";
			// html+="<img src=\"../images/edit_bg.png\" width='30px;'
			// title='Change image background'
			// onclick=changeImage('bg','"+this.List[0]+"') style='cursor:
			// pointer;'></img>";
			// html+="</td>";
			html += "</tr>";
		}
		html += "</table>";
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
		var url = "Background?CMD=2";
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _bgpic = it[i].getElementsByTagName("bgpic")[0].childNodes[0].nodeValue;
				var _bgmusic = it[i].getElementsByTagName("bgmusic")[0].childNodes[0].nodeValue;
				arr[i] = new ItemFolioType(_id, _name, _bgpic, _bgmusic);
			}
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem3", 0);
			my.renderEvent();
		};
	};
	this.loadcomplet = function() {
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
	this.pageclick = function() {
		if (ctTable.state)
			ctTable.hide();
	};

	ctTable.fucBgPic = function(obj) {
		var layer = new layer_vitual();
		layer.show();
		var url = "Background?CMD=4";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_background_image");
			$("name").value = obj.Name;
			document.getElementById("name").readOnly = true;
			$("image").src = obj.BgPic;
			$("form_textbox_ok").onclick = function() {
				my.acceptimage();
			};
			$("form_textbox_cancel").onclick = my.cancel;
		};
	};
	ctTable.fucBgMusic = function(obj) {
		var layer = new layer_vitual();
		layer.show();
		var url = "Background?CMD=5";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_background_music");
			// $("name").value = obj.Name;
			// document.getElementById("name").readOnly = true;
			// $("image").src =obj.BgPic;
			$("form_textbox_ok").onclick = function() {
				my.acceptmusic(obj.Name);
			};
			$("form_textbox_cancel").onclick = my.cancel;
			my.loadFormBackgroundMusic("");
		};
	};
	this.acceptmusic = function(name) {
		var filename = $("ctnaddfilenametempmusic").value;
		if (filename == "") {
			var albox = new alertBox();
			albox.show("ERROR: Please, select the .mp3 file???");
			return false;
		}
		var url = "Background?CMD=1";
		url += "&t=" + Math.random();
		var param = "&filename=" + filename;
		param += "&path=" + path;
		param += "&name=" + name;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			if (tx == "success") {
				var albox = new successBox();
				albox.show("INFO<br/><br/>Add video successful!");
				my.hide();
				my.reload();
			} else {
				var albox = new alertBox();
				albox.show("ERROR<br/><br/>Add video unsuccessful!");
			}
		};
	};

	this.loadFormBackgroundMusic = function(p) {
		var wt = new Waiting();
		wt.show(null);
		var url = "Background?CMD=10";
		url += "&path=" + p;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _type = it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
				var item = new itemFTP(_name, _type);
				arr[i] = item;
			}
			my.bindData(arr);
			wt.hide();
		};
		wt.hide();
	};
	function itemFTP(name, type) {
		this.Name = name;
		this.Type = type;
	}
	this.bindData = function(list) {
		if (path.length > 0) {
			var arr = new Array();
			arr[0] = new itemFTP("..", 1);
			var j = 1;
			for ( var i = 0; i < list.length; i++) {
				arr[j] = list[i];
				j++;
			}
			list = arr;
		}
		var ul = $("ul_ftp_file_music");
		ul.innerHTML = "";
		for ( var i = 0; i < list.length; i++) {
			var li = document.createElement("li");
			var a = document.createElement("a");
			var name = list[i].Name;
			a.innerHTML = name;
			a.href = "javascript:";
			a.name = list[i].Name;
			if (list[i].Type == 1) {
				li.setAttribute("type", 1);
				a.className = "itemfolder";
				a.onclick = function() {
					my.onclickFolder(this.name);
				};
			} else {
				li.setAttribute("type", 0);
				li.setAttribute("title", "Double click to select video");
				a.className = "itemfile";
				a.ondblclick = function() {
					my.onclickFile(this.name);
				};
			}
			;
			li.appendChild(a);
			ul.appendChild(li);
		}
	};
	this.onclickFolder = function(me) {
		if (me == "..") {
			if (path.indexOf("/") == 0) {
				path = path.substring(1);
			}
			;
			path = path.substring(0, path.lastIndexOf("/"));
			my.loadFormBackgroundMusic(path);
		} else {
			path += "/" + me;
			if (path.indexOf("/") == 0) {
				path = path.substring(1);
			}
			$("ctnaddfilepathtempmusic").value = path;
			my.loadFormBackgroundMusic(path);
		}
	};
	this.onclickFile = function(name) {
		// var ar = new Array(".mov", ".mkv", ".mp4", ".avi");
		var ar = new Array(".mp3");
		var type = name.substring(name.lastIndexOf("."));
		var t = false;
		for ( var i = 0; i < ar.length; i++) {
			if (ar[i] == type)
				t = true;
		}
		if (!t) {
			var albox = new alertBox();
			albox.show("Please select file .mp3!");
		} else {
			$("fileNameMusic").innerHTML = name;
			$("ctnaddfilenametempmusic").value = name;
			filename = name;
		}
	};
	this.acceptimage = function() {
		var name = $("name").value;
		var image = $("image").src;
		image = image.substring(image.lastIndexOf("/") + 1);

		var url = "Background?CMD=2";
		url += "&t=" + Math.random();
		var param = "&name=" + name;
		param += "&image=" + image;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			my.hide();
			my.reload();
		};
	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	};
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
		my.draw(posx, posy - 10, _obj);
	};
	// context menu: thu tu la Update order, Update apk value, Update status,
	// Update service
	this.draw = function(x, y, _obj) {
		my.state = true;
		var html = "";
		html += "<ul>";
		html += "<li class='icon_background_image'>";
		html += "<a>Update Image</a>";
		html += "</li>";
		html += "<li class='icon_background_music'>";
		html += "<a>Update Music</a>";
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
		my.renderClick(_obj);
		eventMouse();
	};
	this.renderClick = function(_obj) {

		var ul = $("div_contextMenu").getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");

		var fucBgPic = li[0].getElementsByTagName("a")[0];
		fucBgPic.onclick = function() {
			my.fucBgPic(_obj);
		};
		var fucBgMusic = li[1].getElementsByTagName("a")[0];
		fucBgMusic.onclick = function() {
			my.fucBgMusic(_obj);
		};

	};
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	};
}

function ItemFolioType(id, name, bgpic, bgmusic) {
	this.Id = id;
	this.Name = name;
	this.BgPic = bgpic;
	this.BgMusic = bgmusic;
}
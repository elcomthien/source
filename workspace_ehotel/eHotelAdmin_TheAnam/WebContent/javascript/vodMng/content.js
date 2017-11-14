var rte = new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var Vod = new function VodContent() {
	var tag = 0;
	var my = this;
	this.sub = new subjectVod();
	this.list = new VodList();
	this.ctn = null;
	this.onclick = function() {
		this.sub.pageclick();
		this.list.pageclick();
	};
	this.run = function() {
		this.sub.load();
	};
	this.sub.SelectItem = function(id) {
		if (tag == 0) {
			my.list.load(id);
		} else {
			my.ctn.loadSub(this.getItemId(id));
		}
	};
	this.changeTab = function(id) {
	
		var div = $("tab_menu").getElementsByTagName("div");
		tag = id;
		if (id == 0) {
			div[0].className = "tab_menu_item_sel";
			div[1].className = "tab_menu_item";
			$("div_search_vod_content").style.display = "block";
			// my.loadList();
		} else {
			div[1].className = "tab_menu_item_sel";
			div[0].className = "tab_menu_item";
			$("div_search_vod_content").style.display = "none";
			my.loadAdd();
		}

	};
	this.loadAdd = function() {
		my.ctn = new ContentAdd();
		var obj = my.sub.getItemId(my.sub.ID);
		my.ctn.load(obj);

	};
	this.loadList = function() {
		my.list.load(my.sub.ID);
	};
	this.search = function(){
		var key=$("vod_search").value;
//		alert(key + " - " + my.sub.ID);
		my.list.search(my.sub.ID, 0, key);
	};
};
function subjectVod() {
	var my = this;
	var menu = new Tree();
	this.List = new Array();
	var ctMenu = new contextMenu();
	this.ID;
	ctMenu.fucNew = function() {
		my.addSubject(this.obj);
	};
	ctMenu.fucdel = function() {
		var cfBox = new conformBox();
		cfBox.show();
		var id = this.obj;
		cfBox.accept = function() {
			var url = "VodCtnMain?CMD=3";
			url += "&SubId=" + id;
			url += "&t=" + Math.random();
			var f = new AjaxPost(url, "");
			f.complet = function(tx) {
				my.get();
			};
		};
	};
	ctMenu.fucedit = function() {
		my.editSubject(this.obj);
	};
	this.pageclick = function() {
		if (ctMenu.state)
			ctMenu.hide();
	};
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
	};
	this.load = function() {
		menu.init(my.defaults);
		my.get();
	};
	this.reload = function() {
		var url = "VodCtnMain?CMD=1";
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
			my.List = arr;
			menu.reload(arr);
			my.shiftfocus();
		};
	};
	this.get = function() {
		var url = "VodCtnMain?CMD=1";
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
			my.List = arr;
			menu.load(arr);
			my.ClickItem(arr[0].Id);
		};
	};
	this.ClickItem = function(id) {
	
		this.ID = id;
		var ul = $(menu.divName).getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");
		for ( var i = 0; i < li.length; i++) {
			var a = li[i].getElementsByTagName("a");
			if (a[0].id == id) {
				a[0].style.color = "#ab8718";
			} else {
				a[0].style.color = "#878888";
			}
		}
		my.SelectItem(id);
	};
	this.getCurent = function() {
		var obj = null;
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == my.ID) {
				obj = my.List[i];
			}
		}
		return obj;
	};
	this.shiftfocus = function() {
		var ul = $(menu.divName).getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");
		for ( var i = 0; i < li.length; i++) {
			var a = li[i].getElementsByTagName("a");
			if (a[0].id == my.ID) {
				a[0].style.color = "#ab8718";
			} else {
				a[0].style.color = "#878888";
			}
		}
	};
	menu.renderEvent = function() {
		var ul = $(this.divName).getElementsByTagName("ul");
		var li = ul[0].getElementsByTagName("li");
		var me = this;
		for ( var i = 0; i < li.length; i++) {
			var a = li[i].getElementsByTagName("a");
			var item = a[0];
			item.onclick = function(e) {
				my.ClickItem(this.id);
				me.change(this.id);
			};
			item.oncontextmenu = function(e) {
				my.ClickItem(this.id);
				ctMenu.load(this.id, e);
				return false;
			};
		}
	};
	this.addSubject = function(id) {
		var textbox = new newBox(lang.addSubject, "");
		textbox.show();
		textbox.accept = function(text, image) {
			image = image.substring(image.lastIndexOf("/") + 1);
			if (text.length <= 0) {
				var albox = new alertBox();
				albox.show(lang.pls_Subject);
				return;
			}
			this.hide();
			var url = "VodCtnMain?CMD=1";
			url += "&t=" + Math.random();
			url += "&image=" + image;
			url += "&parent=" + id;
			var param = "&name=" + encode(text);
			var f = new AjaxPost(url, param);
			f.complet = function(tx) {
				my.reload();
			};
		};
	};
	this.editSubject = function(id) {
		var obj = null;
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == id) {
				obj = my.List[i];
				break;
			}
		}
		var pathIm = "/" + moviedir + "/" + obj.Image;
		var textbox = new newBox(lang.editSubject, obj.Name, obj.Image);
		textbox.show();
		textbox.accept = function(text, image) {
			image = image.substring(image.lastIndexOf("/") + 1);

			if (text.length <= 0) {
				var albox = new alertBox();
				albox.show(lang.pls_Subject);
				return;
			}
			this.hide();
			var url = "VodCtnMain?CMD=2";
			url += "&SubId=" + id;
			url += "&t=" + Math.random();
			url += "&image=" + image;
			var param = "&name=" + encode(text);
			var f = new AjaxPost(url, param);
			f.complet = function(tx) {
				my.reload();
			};
		};
	};
	this.getItemId = function(id) {
		var obj = null;
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == id) {
				obj = my.List[i];
				break;
			}
		}
		return obj;
	};
}
function VodList() {
	var my = this;
	this.SubId = -1;
	this.ListCheck = new Vector();
	this.ListCheck = new Vector();
	this.List = new Array();
	var table = new Table();
	this.count = 0;
	var Index = 0;
	var page = 6;
	var ctTable = new contextTable();
	ctTable.fucDetail = function() {
		var detail = new detailMovie(this.Id);
		detail.show();
		detail.update = function() {
			my.reload();
		};
	};
	ctTable.fucdel = function() {
		var _id = my.ListCheck.lastElement();
		var cfBox = new conformBox();
		cfBox.show();
		cfBox.accept = function() {
			var wt = new Waiting();
			var url = "VodCtnMain?CMD=4";
			url += "&VodId=" + _id;
			url += "&t=" + Math.random();
			wt.show(30000);
			var f = new AjaxGetText(url);
			f.complet = function(tx) {
				if (Common.checkRole(tx))
					my.reload();
				wt.hide();
			};
		};
	};
	ctTable.changeSub = function() {
		var change = new changeSubject(this.Id, my.SubId);
		change.load();
		change.accept = function() {
			var select = $("selectSubject");
			if (select.options[select.selectedIndex].value == -1) {
				var al = new alertBox();
				al.show(lang.pls_selectsubject);
			} else {
				var newid = select.options[select.selectedIndex].value;
				var url = "VodCtnMain?CMD=5";
				url += "&VodId=" + this.VodId;
				url += "&SubId=" + newid;
				url += "&t=" + Math.random();
				var f = new AjaxGetText(url);
				f.complet = function(tx) {
					my.reload();
					change.hide();
				};
			}
		};
	};
	ctTable.fucPlayVideo = function(rel) {
		var url="VodCtnMain?CMD=100";
//		url+="&vodId="+this.ID;
		url+="&link="+rel;
//		if(this.Status==1)
//		{
//			url+="&Status=0";
//		}else
//		{
//			url+="&Status=1";
//		}
//		url+="&subId="+my.ID;
		url+="&t="+Math.random();
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{		
			var eb = new playEmbedVideo(tx);
			eb.show(tx);
		};
		
	};
	ctTable.setTrailer = function() {
		var trailer = new MovieTrailer(this.Id);
		trailer.show();
	};
	ctTable.Setsubtitle = function() {
		var subtitle = new Subtitle(this.Id);
		subtitle.show();
		subtitle.complet = function() {
			my.reload();
		};
	};
	this.pageclick = function() {
		if (ctTable.state)
			ctTable.hide();
	};
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
	table.init(defaults);
	this.load = function(id) {
		Index = 0;
		this.SubId = id;
		this.get(my.SubId, Index);
	};
	this.reload = function() {
		this.get(my.SubId, Index);
	};
	this.loadIndex = function(index) {
		Index = index;
		this.get(my.SubId, Index);
	};
	this.Next = function() {
		var cpage = Math.round(((my.count - 1) / page) - 0.4);
		if (my.count % 5 == 0) {
			cpage = (my.count / page) - 1;
		} else {
			cpage = Math.round(((my.count) / page) - 0.4);
		}
		if (Index < cpage)
			Index++;
		my.loadIndex(Index);
	};
	this.Back = function() {
		if (Index > 0)
			Index--;
		my.loadIndex(Index);
	};
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_table'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left'  valign=\"middle\"  width=\"" + 30 + "%\"  >";
		html += lang.name;
		html += "</th>";
		html += "<th align='left' width=\"" + 25 + "%\" valign=\"middle\"  >";
		html += lang.actors;
		html += "</th>";
		html += "<th align='left' width=\"" + 15 + "%\" valign=\"middle\"  >";
		html += lang.director;
		html += "</th>";
		// remove trailer 18.1
		// html+="<th align='center' width=\""+7+"%\" valign=\"middle\" >";
		// html+="Trailer";
		// html+="</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += "Subtitle";
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
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Name;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Actors;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Director;
			html += "</td>";
			// remove trailer 18.1
			// html+="<td align=\"center\" valign=\"middle\">";
			// if(this.List[i].New==0)
			// {
			// html+="<img src=\"../icon/159.png\"></img>"
			// }else
			// {
			// html+="<img src=\"../icon/156.png\"></img>"
			// }
			// html+="</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			if (this.List[i].Status == 0) {
				html += "<img src=\"../icon/16-square-red.png\"></img>";
			} else {
				html += "<img src=\"../icon/16-square-green.png\"></img>";
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
		return html;
	};
	table.renderEvent = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var item = tr[i];
			var obj = null;
			var id = tr[i].id;
			for ( var j = 0; j < my.List.length; j++) {
				if (my.List[j].Id == id) {
					obj = my.List[j];
					break;
				}
			}
			item.VideoPath = obj.VideoPath;
			item.onclick = function(e) {
				if (Common.ctrl) {
					my.addItem(this.id);
				} else {
					my.selectItem(this.id);
				}
			};
			item.oncontextmenu = function(e) {
				my.selectItem(this.id);
				ctTable.load(this.id, e, this.VideoPath);
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
					my.loadIndex(this.id);
				};
			}
			var div1 = $("div_page").getElementsByTagName("div");
			if (div1.length > 0) {
				div1[0].onclick = function() {
					my.Back();
				};
				div1[1].onclick = function() {
					my.Next();
				};
			}
		}
	};
	this.selectItem = function(id) {
		my.ListCheck.Empty();
		my.ListCheck.add(id);
		my.shiftSelect();
	};
	this.addItem = function(id) {
		if (my.ListCheck.isObject(id)) {
			my.ListCheck.removeObj(id);
		} else {
			my.ListCheck.add(id);
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
			if (my.ListCheck.isObject(tr[i].id)) {
				tr[i].className = table.classRowsel;
			}
		}
	};
	this.get = function(id, index) {
		var url = "VodCtnMain?CMD=2";
		url += "&SubId=" + id;
		url += "&pageIndex=" + index;
		url += "&page=" + page;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _videopath = it[i].getElementsByTagName("link")[0].childNodes[0].nodeValue;
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _actors = it[i].getElementsByTagName("Actors")[0].childNodes[0].nodeValue;
				var _director = it[i].getElementsByTagName("Director")[0].childNodes[0].nodeValue;
				var _duration = it[i].getElementsByTagName("Duration")[0].childNodes[0].nodeValue;
				var _new = it[i].getElementsByTagName("New")[0].childNodes[0].nodeValue;
				var _des = it[i].getElementsByTagName("Desc")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("Image")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("Status")[0].childNodes[0].nodeValue;
				if (_director == "null")
					_director = "";
				var item = new ItemVOD(_id, _name, _actors, _director,
						_duration, _videopath);
				item.Status = _status;
				item.New = _new;
				item.Desc = _des;
				item.Image = _image;
				// them vao 12.1 lay link video
				item.VideoPath = _videopath;
				arr[i] = item;
			}
			my.List = arr;
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem0", 0);
			table.renderEvent();
			$("title_subject").innerHTML = Vod.sub.getCurent().Name + "("
					+ my.count + ")";
		};
	};
	this.search = function(id, index, text) {
		var url = "VodCtnMain?CMD=3";
		url += "&SubId=" + id;
		url += "&pageIndex=" + index;
		url += "&text=" + text;
		url += "&page=" + page;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			my.count = xml[0].getAttribute("count");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _videopath = it[i].getElementsByTagName("link")[0].childNodes[0].nodeValue;
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _actors = it[i].getElementsByTagName("Actors")[0].childNodes[0].nodeValue;
				var _director = it[i].getElementsByTagName("Director")[0].childNodes[0].nodeValue;
				var _duration = it[i].getElementsByTagName("Duration")[0].childNodes[0].nodeValue;
				var _new = it[i].getElementsByTagName("New")[0].childNodes[0].nodeValue;
				var _des = it[i].getElementsByTagName("Desc")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("Image")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("Status")[0].childNodes[0].nodeValue;
				if (_director == "null")
					_director = "";
				var item = new ItemVOD(_id, _name, _actors, _director,
						_duration, _videopath);
				item.Status = _status;
				item.New = _new;
				item.Desc = _des;
				item.Image = _image;
				// them vao 12.1 lay link video
				item.VideoPath = _videopath;
				arr[i] = item;
			}
			my.List = arr;
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem0", 0);
			table.renderEvent();
			$("title_subject").innerHTML = Vod.sub.getCurent().Name + "("
					+ my.count + ")";
		};
	};
}
function contextMenu() {
	var my = this;
	this.state = false;
	this.obj = null;
	this.load = function(obj, e, videoPath) {
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
	};
	this.draw = function(x, y) {
		my.state = true;
		var html = "";
		html += "<ul>";
		html += "<li class='icon_contextmenuAdd'>";
		html += "<a>" + lang.addSubject + "</a>";
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
	};
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	};
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
	};
	this.fucNew = function() {
	};
	this.fucdel = function() {
	};
	this.fucedit = function() {
	};
}
function contextTable() {
	var my = this;
	this.state = false;
	this.Id = null;
	this.load = function(_obj, e, videoPath) {
		this.Id = _obj;
		var posx = 0;
		var posy = 0;
		this.VideoPath = videoPath;
		var ls = this.VideoPath;
		if (!e)
			var e = window.event;
		if (e.pageX || e.pageY) {
			posx = e.pageX;
			posy = e.pageY;
		} else if (e.clientX || e.clientY) {
			posx = e.clientX + document.body.scrollLeft;
			+document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
					+ document.documentElement.scrollTop;
		}
		if (posy > 466) {
			posy = posy - 50;
		}
		my.draw(posx, posy - 10, ls);
	};
	this.draw = function(x, y, ls) {
//		var link = iponly + "/vod" + ls;
		var link = ls;
		my.state = true;
		var html = "";
		html += "<ul>";
		html += "<li class='icon_contextmenuinfo'>";
		html += "<a>" + lang.detailMove + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuDel'>";
		html += "<a>" + lang.delete_move + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenucopy'>";
		html += "<a>" + lang.changeSubject + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuEdit'>";
		html += "<a>" + lang.set_subtitle + "</a>";
		html += "</li>";
		// remove trailer 18.1
		// html+="<li class='icon_contextmenuEdit'>";
		// html+="<a>"+lang.set_trailer+"</a>";
		// html+="</li>";
		html += "<li class='icon_contextmenuinfo'>";
		html += "<a class='playvideo' rel='" + link + "'>" + lang.checkVideo
				+ "</a>";
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
		var itemnew = li[0].getElementsByTagName("a")[0];
		itemnew.onclick = function() {
			my.fucDetail();
		};
		var itemdel = li[1].getElementsByTagName("a")[0];
		itemdel.onclick = function() {
			my.fucdel();
		};
		var itemchange = li[2].getElementsByTagName("a")[0];
		itemchange.onclick = function() {
			my.changeSub();
		};
		var itemedit = li[3].getElementsByTagName("a")[0];
		itemedit.onclick = function() {
			my.Setsubtitle();
		};
		// remove trailer 18.1
		// var itemedit=li[4].getElementsByTagName("a")[0];
		// itemedit.onclick=function(){my.setTrailer();};
		var playvideo = li[4].getElementsByTagName("a")[0];
		var rellink = playvideo.getAttribute("rel");
		playvideo.onclick = function() {
			my.fucPlayVideo(rellink);
		};
	};
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	};
}
function detailMovie(_obj) {
	var my = this;
	var layer = new layer_vitual();
	this.Id = _obj;

	this.show = function() {
		layer.show();
		var url = "../ServiceJSP?ID=2";
		url += "&VodId=" + my.Id;
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_song");

			$("form_textbox_ok").onclick = function() {
				my.accept();
			};
			$("form_textbox_cancel").onclick = my.cancel;
			initTinymce(2, "540px");
		};
	};
	this.accept = function() {
		var title = encode($("Name").value);
		var Director = encode($("Director").value);
		var Actors = encode($("Actors").value);
		var description = getDataFromEditor("descriptionInput");
		var image = $("image").src;
		image = image.substring(image.lastIndexOf("/") + 1);
		var price = $("div_price").value;
		var select = $("div_money");
		var money = select.options[select.selectedIndex].value;
		if (title.length == 0) {
			var albox = new alertBox();
			albox.show("Name movie is empty");
			return;
		};
		var url = "VodCtnMain?CMD=4";
		url += "&t=" + Math.random();
		var param = "&name=" + title;
		param += "&id=" + my.Id;
		param += "&Director=" + Director;
		param += "&Actors=" + Actors;
		param += "&Desc=" + description;
		param += "&image=" + image;
		param += "&price=" + price;
		param += "&money=" + money;
		var f = new AjaxPost(url, param);
		f.complet = function(tx) {
			my.update();
			my.hide();
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
}
function changeSubject(vod, sub) {
	this.VodId = vod;
	this.SubId = sub;
	var my = this;
	var layer = new layer_vitual();
	this.load = function() {
		my.show();
	};
	this.show = function() {
		layer.show();
		var url = "../ServiceJSP?ID=3"
		url += "&VodId=" + my.VodId;
		url += "&SubId=" + my.SubId;
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			var x = 0;
			var y = 0;
			setCenterDIV("formCtnSubject");
			$("ctn_button_ok").onclick = function() {
				my.accept();
			};
			$("ctn_button_Cancel").onclick = my.cancel;
		};
	};
	this.accept = function() {
	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		layer.hide();
	};
}
function MovieTrailer(vod) {
	this.VodId = vod;
	var my = this;
	var layer = new layer_vitual();
	this.load = function() {
		path = "";
		my.show();
	};
	var path = "";
	var filename = "";
	this.show = function() {
		layer.show();
		var url = "../ServiceJSP?ID=4";
		url += "&VodId=" + my.VodId;
		url += "&t=" + Math.random();

		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("formTrailer");
			$("ctn_button_ok").onclick = function() {
				my.accept();
			};
			$("ctn_button_Cancel").onclick = my.cancel;
			my.connect(path);
		};
	};
	this.connect = function(path) {
		var url = "VodCtnMain?CMD=6";
		url += "&path=" + path;
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
		};
	};
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
		var ul = $("ul_ftp_file");
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
				a.ondblclick = function() {
					my.onclickFolder(this.name);
				};
			} else {
				li.setAttribute("type", 0);
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
			my.connect(path);
		} else {
			path += "/" + me;
			if (path.indexOf("/") == 0) {
				path = path.substring(1);
			}
			;
			my.connect(path);
		}
	};
	this.onclickFile = function(name) {
		var ar = new Array(".ts", ".mkv");
		var type = name.substring(name.lastIndexOf("."));
		var t = false;
		for ( var i = 0; i < ar.length; i++) {
			if (ar[i] == type)
				t = true;
		}
		if (!t) {
			var albox = new alertBox();
			albox.show("Please select type file .mkv or .ts!");
		} else {
			filename = name;
			if (name.length > 30)
				name = name.substring(0, 30);
			$("nameTrailer").innerHTML = name;
		}
	};
	this.accept = function() {
		if (filename.length <= 0) {
			var albox = new alertBox();
			albox.show("Please select file .ts or .mkv!");
			return;
		}
		var file = filename;
		if (path.length > 0) {
			file = path + "/" + filename;
		}
		var url = "VodCtnMain?CMD=7";
		url += "&path=" + file;
		url += "&VodId=" + my.VodId;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			my.hide();
		};
	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		layer.hide();
	};
	function itemFTP(name, type) {
		this.Name = name;
		this.Type = type;
	}
}
function Subtitle(id) {
	this.VodId = id;
	var my = this;
	var layer = new layer_vitual();
	var listSub = new Vector();
	var listDelete = new Vector();
	this.load = function() {
		my.show();
	};
	var path = "";
	var filename = "";
	this.show = function() {
		layer.show();
		var url = "../ServiceJSP?ID=5";
		url += "&VodId=" + my.VodId;
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("formTrailer");
			$("ctn_button_ok").onclick = function() {
				my.accept();
			};
			$("ctn_button_Cancel").onclick = function() {
				my.cancel();
			};
			my.connect(path);
			my.getSubtitle();
		};
	};
	this.connect = function(p) {
		var url = "VodCtnMain?CMD=15";
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
		};
	};
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
		var ul = $("ul_ftp_file");
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
			};
			path = path.substring(0, path.lastIndexOf("/"));
			my.connect(path);
		} else {
			path += "/" + me;
			if (path.indexOf("/") == 0) {
				path = path.substring(1);
			};
			my.connect(path);
		}
	};
	this.onclickFile = function(name) {
		var ar = new Array(".srt");
		var type = name.substring(name.lastIndexOf("."));
		var t = false;
		for ( var i = 0; i < ar.length; i++) {
			if (ar[i] == type)
				t = true;
		}
		if (!t) {
			var albox = new alertBox();
			albox.show("Please select type file true!");
		} else {
			my.addRow(name);
		}
	};
	this.setlang = function(id, me) {
		var lang = me.options[me.selectedIndex].value;
		listSub.get(id).Lang = lang;
	};
	this.getSubtitle = function() {
		var url = "VodCtnMain?CMD=9";
		url += "&VodId=" + my.VodId;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _type = it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
				var item = new itemSubtitle(_id, _name, _type);
				listSub.add(item);
				my.shift();
			}
			for ( var i = 0; i < listSub.size(); i++) {
				var item = listSub.get(i);
				listDelete.add(item);
			}
		};
	};
	this.addRow = function(name) {
		for ( var i = 0; i < listSub.size(); i++) {
			if (listSub.get(i).Name == name) {
				var albox = new alertBox();
				albox.show("Trailer da ton tai!");
				return;
			}
		}
		var file = name;
		if (path.length > 0)
			file = path + "/" + name;
		listSub.add(new itemSubtitle(-1, file, -1));
		my.shift();
	};
	this.removeRow = function(i) {
		var name = listSub.get(i).Name;
		var url = "VodCtnMain?CMD=17";
		url += "&name=" + name;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
		};
		listSub.remove(i);

		my.shift();
	};
	this.shift = function() {
		var html = "<table cellspacing=\"1\" cellpadding=\"1\" border=\"0\" width=\"94%\" class=\"tableSubtitle\" id=\"tableSubtitle\">";
		html += "<tr>";
		html += "<th>";
		html += "No";
		html += "</th>";
		html += "<th>";
		html += "name";
		html += "</th>";
		html += "<th>";
		html += "Lang";
		html += "</th>";
		html += "<th>";
		html += "Delete";
		html += "</th>";
		html += "</tr>";
		for ( var i = 0; i < listSub.size(); i++) {
			var name = listSub.get(i).Name;
			if (name.length > 30) {
				var l = name.length;
				name = name.substring(l - 30);
			}
			html += "<tr >";
			html += "<td  align=\"center\" width=\"10%\">";
			html += (i + 1);
			html += "</td>";
			html += "<td  align=\"left\" width=\"50%\">";
			html += name;
			html += "</td>";
			html += "<td>";
			html += "</td>";
			html += "<td align=\"center\" width=\"20%\">";
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		$("divlistsub").innerHTML = html;
		my.renderEvent();
	};
	this.renderEvent = function() {
		var tb = $("tableSubtitle");
		var tr = tb.getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var s = my.createSelect(listSub.get(i - 1).Lang);
			s.id = (i - 1);
			var td = tr[i].getElementsByTagName("td");
			td[2].appendChild(s);
			var im = document.createElement("img");
			im.setAttribute("src", "../icon/16-square-red.png");
			im.id = (i - 1);
			im.onclick = function() {
				my.removeRow(this.id);
			};
			td[3].appendChild(im);
		}
	};
	this.cancel = function() {
		my.hide();
	};
	this.accept = function() {
		var wt = new Waiting();
		if (listSub.size() == 0) {
			var p = "";
			for ( var i = 0; i < listDelete.size(); i++) {
				p += "&id" + i + "=" + listDelete.get(i).Id;
			}
			var url = "VodCtnMain?CMD=11";
			url += "&VodId=" + my.VodId;
			url += p;
			url += "&r=" + Math.random();
			wt.show(1000);
			var f = new AjaxGetXML(url);
			f.complet = function(tx) {
				my.hide();
				my.complet();
			};
			return;
		}
		for ( var i = 0; i < listSub.size(); i++) {
			if (listSub.get(i).Lang == -1) {
				var albox = new alertBox();
				albox.show("please select lang of " + listSub.get(i).Name + "");
				return;
			}
		}
		var param = "";
		for ( var i = 0; i < listSub.size(); i++) {
			param += "&name" + i + "=" + listSub.get(i).Name;
			param += "&lang" + i + "=" + listSub.get(i).Lang;
			param += "&id" + i + "=" + listSub.get(i).Id;
		}
		var url = "VodCtnMain?CMD=10";
		url += "&VodId=" + my.VodId;
		url += param;
		url += "&r=" + Math.random();
		wt.show(5000);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			setTimeout(function() {
				my.complet();
				wt.hide();
			}, 5000);
		};
		my.hide();
	};
	this.hide = function() {
		layer.hide();
	};
	function itemFTP(name, type) {
		this.Name = name;
		this.Type = type;
	}
	function itemSubtitle(id, name, lang) {
		this.Id = id;
		this.Name = name;
		this.Lang = lang;
	}
	this.createSelect = function(lang) {
		var select = document.createElement("select");
		select.onchange = function() {
			my.setlang(this.id, this);
		};
		var newOpt0 = new Option("Select", -1);
		var newOpt1 = new Option("English", 2);
		var newOpt2 = new Option("VietNam", 1);
		select.add(newOpt0);
		select.add(newOpt1);
		select.add(newOpt2);
		for ( var i = 0; i < select.options.length; i++) {
			if (select.options[i].value == lang) {
				select.options[i].selected = true;
				break;
			}
		}
		return select;
	};
}

function playEmbedVideo(name) {
	var my = this;
	var layer = new layer_vitual();
	this.obj = null;
	this.load = function(_obj) {
		this.obj = _obj;
		my.show();
	};
	this.show = function(name) {
		layer.show();
//		var embedlink = "<embed name='embedplay' id='embedplay' type='application/x-vlc-plugin' name='player' autoplay='no' ";
//		embedlink += "loop='no' src='" + name
//				+ "' width='450px;' height='350px;'/>";
//
//		var embedie = "<object type='application/x-vlc-plugin' id='embedobj' width='450px' height='350px'";
//		embedie += " classid='clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921'>";
//		embedie += "<param id='ieparam' name='src' value='" + name + "'/>";
//		embedie += "<param name='autoplay' value='false'/>";
//		embedie += "<param name='toolbar' value='true' />";
//		embedie += "</object>";
		var video = "<video width='450px;' height='350px;' id='checkvideo' name='checkvideo' src='" + name + "' controls preload autoplay>";
		video += "<p>Try this page in a modern browser! Or you can <a href='" + name + "'>download the video</a> instead.</p></video>";

		var html = "<div class='form_detail_song' id='div_playEmbed' align='center' style='height: 450px;'>";
		html += "<div class='div_Title_song'>";
		html += "CHECK VIDEO LINK</div>";
		html += "<div align='center' >";
//		html += embedlink;
//		html += embedie;
		html += video;
		html += "</div>";
		html += "<div style='float: left;width: 100%;height:30px;' align='center'>";
		html += "<div style='width: 70px; height: 30px; margin-top:15px;' >";
		html += "<input type=\"button\" value='" + langMain.ok
				+ " '  class='div_buton'id=\"button_ok\" >";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		layer.addHtml(html);

		// run on IE only to active vlc 16.1
//		if (/* @cc_on!@ */false) {
//			document.documentElement.className += ' ie10';
//			document.getElementById("embedplay").style.display = "none";
//			document.getElementById("embedobj").style.display = "inline-block";
//			document.getElementById("embedobj").style.width = 450;
//			document.getElementById("embedobj").style.height = 350;
//		} else {
//			document.getElementById("embedplay").style.display = "inline-block";
//			document.getElementById("embedobj").style.display = "none";
//		}
		setCenterDIV("div_playEmbed");
		$("button_ok").onclick = function() {
			my.accept();
			my.hide();
		};
	};
	this.accept = function() {
		my.hide();
	};
	this.hide = function() {
		document.getElementById("checkvideo").setAttribute("src", "");
		$("div_layer_vitual").style.display = "none";
//		document.getElementById("checkvideo").removeNode(true);
	};
}

document.onload = new function() {
	Vod.run();
};
var rte = new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var Vod = new function VodContent() {
	var tag = 0;
	var my = this;
	this.ctrl = false;
	this.sub = new subjectVod();
	this.Mov = new listMovie();
	this.add = new AddContent();
	this.sub.loadComplet = function(obj) {
		//
		if (tag == 1) {
			my.loadAdd();

		} else// tab ==0
		{
			my.Mov.load(obj.Id);
		};
	};
	this.run = function() {
		this.sub.load();
		this.add.init();
	};
	this.onclick = function() {
		this.sub.pageclick();
		this.Mov.pageclick();
		this.add.pageclick();
	};
	this.changeTab = function(id) {
		var div = $("tab_menu").getElementsByTagName("div");
		if (id == 0) {
			my.loadMovie();
			div[0].className = "tab_menu_item_sel";
			div[1].className = "tab_menu_item";
		} else {
			my.loadAdd();
			div[1].className = "tab_menu_item_sel";
			div[0].className = "tab_menu_item";
		};
		tag = id;

	};
	this.loadMovie = function() {
		this.add.backup();
		this.Mov.load(my.sub.ID);
	};
	this.loadAdd = function() {
		this.Mov.backup();
		this.add.restore();
	};
};
function subjectVod() {
	var my = this;
	var menu = new Tree();
	var ctMenu = new contextMenu();
	this.List = new Array();
	this.ID = -1;
	var pos = 0;
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
		//

	};
	this.reload = function() {
		my.get();
	};
	this.pageclick = function() {
		if (ctMenu.state)
			ctMenu.hide();
	};
	ctMenu.fucNew = function() {
		my.addSubject(this.obj);
	};
	ctMenu.fucdel = function() {
		var id = this.obj;
		var cfBox = new conformBox();
		cfBox.show();
		cfBox.accept = function() {
			var url = "VodSrvMainCharge?CMD=3";
			url += "&subid=" + id;
			url += "&t=" + Math.random();
			var f = new AjaxPost(url, "");
			f.complet = function(tx) {

				my.reload();
			};
		};
	};
	ctMenu.fucedit = function() {
		my.editsubject(this.obj);
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
			};
			this.hide();
			var url = "VodSrvMainCharge?CMD=1";
			url += "&t=" + Math.random();
			url += "&image=" + image;
			url += "&parent=" + id;
			var param = "&name=" + encode(text);
			var f = new AjaxPost(url, param);
			f.complet = function(tx) {
				my.reload();
				// my.reload(id);
			};
		};
	};
	this.editsubject = function(id) {

		var name = "";
		var image = null;
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == id) {
				name = my.List[i].Name;
				image = my.List[i].Image;
				break;
			};
		};

		var textbox = new newBox(lang.editSubject, name, image);
		textbox.show();
		textbox.accept = function(text, image) {
			image = image.substring(image.lastIndexOf("/") + 1);

			if (text.length <= 0) {
				var albox = new alertBox();
				albox.show(lang.pls_Subject);
				return;
			};
			this.hide();
			var url = "VodSrvMainCharge?CMD=2";
			url += "&t=" + Math.random();
			url += "&image=" + image;
			url += "&subid=" + id;
			var param = "&name=" + encode(text);
			var f = new AjaxPost(url, param);
			f.complet = function(tx) {
				my.reload();
			};
		};
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
			};
			item.oncontextmenu = function(e) {
				my.selectID(this.id);
				ctMenu.load(this.id, e);
				return false;
			};
		};
	};
	this.selectID = function(id) {
		my.ID = id;

		var a = $(menu.divName).getElementsByTagName("a");
		for ( var i = 0; i < a.length; i++) {
			if (a[i].id == id) {
				a[i].style.color = "#ab8718";
			} else {
				a[i].style.color = "#878888";
			};
		};
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == id) {
				my.loadComplet(my.List[i]);
				break;
			};
		};
	};
	this.get = function() {
		var url = "VodSrvMainCharge?CMD=1";
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
			};
			my.List = arr;
			menu.load(arr);
			my.shift();
			if (arr.length > 0) {
				$("div_AddSubject").style.display = "none";
			};
		};
		
//		var url="ServiceDinning?CMD=1";
//		url+="&r="+Math.random();	
//		var f=new funPropery();				
//		f.complet=function(tx)
//		{					
//			var arr=new Array();	
//			var it=tx.getElementsByTagName("Item");	
//			for(var i=0;i<it.length;i++)
//			{
//				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
//				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
//				var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
//				var _imagebg=it[i].getElementsByTagName("imagebg")[0].childNodes[0].nodeValue;
//				var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;		
//				var _type=it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
//				var _level=it[i].getElementsByTagName("level")[0].childNodes[0].nodeValue;
//				var item=new ItemMenu(_id,_parent,_name,_image);
//				item.ImageBG=_imagebg;
//				item.Type=_type;
//				item.Level=_level;
//				arr[i]=item;
//			};
//			my.List=arr;
//			if(my.List.length==0)return;
//			menu.load(arr);
//			my.selectID(arr[0].Id);
//		};
	};
	this.shift = function() {
		var i = 0;
		for (; i < my.List.length; i++) {
			if (my.List[i].Id == my.ID)
				break;
		};
		if (i == my.List.length)
			my.ID = -1;
		if (my.ID == -1) {
			my.ID = my.List[0].Id;
		};
		my.selectID(my.ID);
	};
	this.getCurent = function() {
		var obj = null;
		var i = 0;
		for (; i < my.List.length; i++) {
			if (my.List[i].Id == my.ID) {
				obj = my.List[i];
				break;
			};
		};
		return obj;
	};
};
function listMovie() {
	this.ListCheck = new Vector();
	this.List = new Array();
	var table = new Table();
	var ctTable = new contextTable();
	var my = this;
	this.count = 0;
	this.ID = 0;// id of subject
	var html = "";
	var page = 6;
	this.backup = function() {
		html = $("id_table").innerHTML;
	};
	this.restore = function() {
		$("id_table").innerHTML = html;
	};
	ctTable.fucDetail = function() {
		var _check = my.ListCheck.lastElement();
		var obj = null;
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == _check) {
				obj = my.List[i];
			};
		};
		var detail = new detailMovie(obj);
		detail.show();
		detail.update = function() {
			my.reload();
		};
	};
	ctTable.fucChange = function() {
		var _check = my.ListCheck.lastElement();
		var obj = null;
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == _check) {
				obj = my.List[i];
			};
		};
		var change = new changeSubject(obj);
		change.show();
		change.complet = function() {
			my.reload();
		};
	};
	ctTable.fucDelete = function() {
		var _id = my.ListCheck.lastElement();
		var cfBox = new conformBox();
		cfBox.show();
		cfBox.accept = function() {
			var url = "VodSrvMainCharge?CMD=10";
			url += "&vodId=" + _id;
			url += "&SubId=" + Vod.sub.ID;
			url += "&t=" + Math.random();
			var f = new AjaxGetXML(url, "");
			f.complet = function(tx) {
				if (Common.checkRole(tx))
					my.reload();
			};
		};
	};
	ctTable.fucNewReleased = function() {
		var url = "VodSrvMainCharge?CMD=5";
		url += "&vodId=" + this.ID;
		url += "&subId=" + my.ID;
		url += "&t=" + Math.random();
		var f = new AjaxGetXML(url, "");
		f.complet = function(tx) {
			my.reload();
		};
	};
	ctTable.fucChangeState = function() {

		var url = "VodSrvMainVodSrvMainCharge?CMD=6";
		url += "&vodId=" + this.ID;
		if (this.Status == 1) {
			url += "&Status=0";
		} else {
			url += "&Status=1";
		};
		url += "&subId=" + my.ID;
		url += "&t=" + Math.random();
		var f = new AjaxGetXML(url, "");
		f.complet = function(tx) {
			my.reload();
		};
	};
	ctTable.fucPlayVideo = function(rel) {

		var url = "VodSrvMainCharge?CMD=100";
		url += "&vodId=" + this.ID;
		url += "&link=" + rel;
		if (this.Status == 1) {
			url += "&Status=0";
		} else {
			url += "&Status=1";
		};
		url += "&subId=" + my.ID;
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			var eb = new playEmbedVideo(tx);
			eb.show(tx);
			my.reload();
		};

		// var eb = new playEmbedVideo(rel);
		// eb.show(rel);
	};
	var Index = 0;
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
	this.load = function(id) {
		Index = 0;
		this.ID = id;
		table.init(defaults);
		my.get(id, Index);
	};
	this.reload = function() {
		if ((Index * page) >= my.count - 1) {
			if (Index > 0)
				Index--;
		};
		my.get(my.ID, Index);
	};
	this.loadIndex = function(_index) {
		Index = _index;
		my.get(my.ID, Index);
	};
	this.nextindex = function() {
		var cpage = ((my.count - 1) / page) - 1;
		if (Index < cpage)
			Index++;
		my.loadIndex(Index);
	};
	this.backindex = function() {
		if (Index > 0)
			Index--;
		my.loadIndex(Index);
	};
	this.pageclick = function() {
		if (ctTable.state)
			ctTable.hide();
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
//		html += "<th align='left' width=\"" + 25 + "%\" valign=\"middle\"  >";
//		html += lang.actors;
//		html += "</th>";
//		html += "<th align='left' width=\"" + 15 + "%\" valign=\"middle\"  >";
//		html += "Pin Code";
//		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += lang.news;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += lang.Status;
		html += "</th>";
		html += "</tr>";
		for ( var i = 0; i < this.List.length; i++) {
			var actors = this.List[i].Actors;
			if (actors.length > 25)
				actors = actors.substring(0, 25) + "...";
			// them vao day xu ly su kien play video 12.1
			if (i % 2 == 0) {
				html += "<tr rel=\"" + this.List[i].VideoPath + "\" class=\""
						+ this.bgRow2 + "\" id=\"" + this.List[i].Id
						+ "\" oncontextmenu='mod.oncontextmenu(" + this.List[i]
						+ ");return false;' >";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id
						+ "\" oncontextmenu='mod.oncontextmenu(" + this.List[i]
						+ ");return false;'  >";
			};
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Name;
			html += "</td>";

//			html += "<td align=\"left\"  valign=\"middle\">";
//
//			html += actors
//			html += "</td>";
//
//			html += "<td align=\"left\"  valign=\"middle\">";
//			html += this.List[i].Director;
//			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			if (this.List[i].New == 0) {
				html += "<img src=\"../icon/159.png\"></img>";
			} else {
				html += "<img src=\"../icon/156.png\"></img>";
			};
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			if (this.List[i].Status == 0) {
				html += "<img src=\"../icon/16-square-green.png\"></img>";
			} else {
				html += "<img src=\"../icon/16-square-red.png\"></img>";
			};
			html += "</td>";
			html += "</tr>";
		};

		html += "</table>";
		html += "</div>";
		html += "<div id=\"div_page2\" class='div_page'>";
		if (my.count > 6) {
			var page = my.count / 6;
			html += createPaging(page, Index);
		};
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
				};
			};
			item.New = obj.New;
			item.Status = obj.Status;
			// them vao 12.1
			item.VideoPath = obj.VideoPath;
			item.onclick = function(e) {
				if (Common.ctrl) {
					my.addItem(this.id);
				} else {
					my.selectItem(this.id);
				};
			};
			item.oncontextmenu = function(e) {
				if (Common.ctrl) {
					my.addItem(this.id);
				} else {
					my.selectItem(this.id);
				};
				// them vao 12.1
				ctTable.load(this.id, this.New, this.Status, e, this.VideoPath);
				return false;
			};
		};
		;
		/**
		 * render paging
		 */
		if ($("div_page2") != undefined) {
			var div = $("div_page2");
			var a = div.getElementsByTagName("a");
			for ( var i = 0; i < a.length; i++) {
				var item = a[i];
				item.onclick = function(e) {
					my.loadIndex(this.id);
				};
			};
			var div1 = $("div_page2").getElementsByTagName("div");
			if (div1.length > 0) {
				div1[0].onclick = function() {
					my.backindex();
				};
				div1[1].onclick = function() {
					my.nextindex();
				};
			};
		};
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
		};
		my.shiftSelect();
	};
	this.shiftSelect = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			if (i % 2 == 0) {
				tr[i].className = table.bgRow1;
			} else {
				tr[i].className = table.bgRow2;
			};
			if (my.ListCheck.isObject(tr[i].id)) {
				tr[i].className = table.classRowsel;
			};
		};
	};
	this.get = function(id, index) {
		var url = "VodSrvMainCharge?CMD=2";
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
				// them vao 12.1 lay link video
				var _videopath = it[i].getElementsByTagName("link")[0].childNodes[0].nodeValue;
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _actors = it[i].getElementsByTagName("Actors")[0].childNodes[0].nodeValue;
				var _director = it[i].getElementsByTagName("Director")[0].childNodes[0].nodeValue;
				var _duration = it[i].getElementsByTagName("Duration")[0].childNodes[0].nodeValue;
				var _currency = it[i].getElementsByTagName("Currency")[0].childNodes[0].nodeValue;
				var _unit = it[i].getElementsByTagName("Unit")[0].childNodes[0].nodeValue;
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
				item.Currency = _currency;
				item.Unit = _unit;
				// them vao 12.1 lay link video
				item.VideoPath = _videopath;
				arr[i] = item;
			};
			my.List = arr;
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem0", 0);
			table.renderEvent();
			$("title_subject").innerHTML = Vod.sub.getCurent().Name + "("
					+ my.count + ")";
		};
	};
};

function contextTable() {
	var my = this;
	this.state = false;
	this.ID = null;
	this.newRealased = null;
	this.Status = null;
	this.load = function(_obj, _newR, status, e, videoPath) {
		this.newRealased = _newR;
		this.Status = status;
		this.ID = _obj;
		this.VideoPath = videoPath;
		var ls = this.VideoPath;
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
		};
		if (posy > 466) {
			posy = posy - 50;
		};
		my.draw(posx, posy - 10, ls);
	};
	this.draw = function(x, y, ls) {
		// them vao 12.1
		// var link = iponly + "/vod" + ls;
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
		if (this.newRealased == 0) {
			html += "<li class='icon_NewReleased1'>";
		} else {
			html += "<li class='icon_NewReleased2'>";
		};
		html += "<a>" + lang.setNewReleases + "</a>";
		html += "</li>";
		if (this.Status == 0) {
			html += "<li class='icon_visibility2'>";
		} else {
			html += "<li class='icon_visibility1'>";
		};
		html += "<a>" + lang.changeStatus + "</a>";
		html += "</li>";
		// them vao xu ly su kien play video 12.1
		html += "<li class='icon_contextmenuinfo' style='display: none;'>";
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
		};

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
			my.fucDelete();
		};
		var itemchange = li[2].getElementsByTagName("a")[0];
		itemchange.onclick = function() {
			my.fucChange();
		};
		var itemnew = li[3].getElementsByTagName("a")[0];
		itemnew.onclick = function() {
			my.fucNewReleased();
		};
		var itemstate = li[4].getElementsByTagName("a")[0];
		itemstate.onclick = function() {
			my.fucChangeState();
		};
		// them vao bo sung chuc nang play video 12.1
		var playvideo = li[5].getElementsByTagName("a")[0];
		var rellink = playvideo.getAttribute("rel");
		playvideo.onclick = function() {
			my.fucPlayVideo(rellink);
		};
	};
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	};
};
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
		};
		my.draw(posx, posy - 10);
	};
	this.draw = function(x, y) {
		my.state = true;
		var html = "";
		html += "<ul>";
		html += "<li class='icon_contextmenuAdd'>";
		html += "<a >" + lang.addSubject + "</a>";
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
		};
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
};
function detailMovie(_obj) {
	var my = this;
	var layer = new layer_vitual();
	this.obj = _obj;
	this.show = function() {
		layer.show();
		var linkImg = linksaveimage + moviedir + "/" + my.obj.Image;
		var html = "";

		html += "<div class=\"form_detail_Movie\" id=\"form_detail_song\" style='height: 470px;'>";
		html += "<div class='div_Title_song' align=\"left\">" + lang.info_movie;
		html += "</div>";
		html += "<div style='float: left;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10;'>";
		html += "<div class='left_Title_movie'>";
		html += "<div class='div_info_movie'>";
		html += "<div class='div_name_movie'>" + lang.name + ":</div>";
		html += "<input type=\"text\" size='34' id='Name' class='textbox_input_vod' value='"
				+ my.obj.Name + "'/>";
		html += "</div>";
		html += "<div class='div_info_movie'>";
		html += "<div class='div_name_movie'>" + lang.actors + ":</div>";
		html += " <input type=\"text\" size='34' id='Actors' class='textbox_input_vod'  value='"
				+ my.obj.Actors + "'/>";
		html += "</div>";
		html += "<div class='div_info_movie'>";
		html += "<div class='div_name_movie'>" + lang.director + ":</div>";
		html += "<input type=\"text\" size='34' id='Director' class='textbox_input_vod'  value='"
				+ my.obj.Director + "'/>";
		html += "</div>";
		html += "<div class='div_info_movie'><div class='div_name_movie'> "
				+ lang.price + ":</div>";
		html += "<input type='text' id='Price' class='textbox_input_vod' style='width:140px; margin-top: 2px;' value="
				+ my.obj.Currency + ">";
		html += "<div style='float: left; margin-left: 10px;'><select id='Unit' style='width:90px;'>";
		if (my.obj.Unit == 'VND') {
			html += "<option value='VND' SELECTED >VND</option>";
			html += "<option value='USD'>USD</option>";
		}else {
			html += "<option value='VND'>VND</option>";
			html += "<option value='USD' SELECTED >USD</option>";
		};

		html += "</select></div></div>";
		html += "<div class='div_info_movie'>";
		html += "<div class='div_name_movie'>" + lang.image + ":</div>";
		html += "<div style='float: left;width: 242px;' >";
		html += "	<iframe class='frame_upload' src=\"../upload\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		html += "<div class='right_Title_movie'>";
		html += "<img style='float: left;border: 1px solid #ddddd;margin-left: 20px;margin-top: 10px;'  id='image' src=\""
				+ linkImg + "\" width='170' height='130'></img>";
		html += "</div>";
		html += "</div>";
		html += "<div class='div_des_movie'>";
		html += "<div class='div_name_movie'>" + lang.Description + ":</div>";
		html += "<div style='float: left; margin-left: 10px; height: auto;'><textarea   rows='10' cols='9' name='descriptionInput' style='width: 485px; border: 1px solid #ddddd;margin-left: 5px;float: left' id='descriptionInput' >";
				+ my.obj.Desc + "</textarea>";
		html += "</div>";
		html += "<div  style='float: left; width: 100%;' align='center'>";
		html += "<div  class='div_sub_buton'>";
		html += "<input style='margin-top:5px' type=\"button\" value='"
				+ lang.ok + "' id=\"form_textbox_ok\" class='div_buton' >";
		html += "<input style='margin-top:5px' type=\"button\" value="
				+ lang.cancel
				+ " id=\"form_textbox_cancel\" class='div_buton'>";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		layer.addHtml(html);
		setCenterDIV("form_detail_song");
		initTinymce(2, "530px");
		$("form_textbox_ok").onclick = function() {
			my.accept();
		};
		$("form_textbox_cancel").onclick = my.cancel;
	};
	this.accept = function() {
		var title = $("Name").value;
		title = encode(title);
		var Director = encode($("Director").value);
		var Actors = encode($("Actors").value);
		var Price = encode($("Price").value);
		var Unit = encode($("Unit").value);
		var description = getDataFromEditor("descriptionInput");
		var image = $("image").src;
		image = image.substring(image.lastIndexOf("/") + 1);
		if (title.length == 0) {
			var albox = new alertBox();
			albox.show(lang.ple_namevod);
			return;
		};
		var url = "VodSrvMainCharge?CMD=4";
		url += "&t=" + Math.random();
		var param = "&name=" + title;
		param += "&id=" + my.obj.Id;
		param += "&Director=" + Director;
		param += "&Actors=" + Actors;
		param += "&Price=" + Price;
		param += "&Unit=" + Unit;
		param += "&Desc=" + description;
		param += "&image=" + image;
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
};
function changeSubject(_obj) {
	var my = this;
	this.obj = _obj;
	var layer = new layer_vitual();
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
		html += "<div style='float:left;width:100%;height: 180px;margin-top:10px;'>";
		html += "<div class='left_change_subject' id='left_change_subject'>";
		html += "</div>";
		html += "<div style='float:left;width:10;' align=\"center\">";
		html += "<div class='add_subject' id='add_subject'></div>";
		html += "<div class='remove_subject' id='remove_subject'></div>";
		html += "</div>";
		html += "<div class='right_change_subject' id='right_change_subject'>";
		html += "</div>";
		html += "</div>";
		html += "<div align='center'style='float: left;width:80%;margin-left: 60px;' >";
		html += "<div class='div_sub_buton'>";
		html += "<input type=\"button\" 	' value='  " + lang.ok
				+ "  ' id=\"form_textbox_ok\" class='div_buton'/ >";
		html += "<input type=\"button\" value=" + lang.cancel
				+ " id=\"form_textbox_cancel\" class='div_buton'/>";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		html += "<div class='change_subject_bottom'>";
		html += "</div>";
		html += "</div>";
		layer.addHtml(html);

		setCenterDIV("form_change_subject");
		// render function
		$("form_textbox_ok").onclick = function() {
			my.accept();
		};
		$("form_textbox_cancel").onclick = my.cancel;
		$("add_subject").onclick = this.addsubject;
		$("remove_subject").onclick = this.removesubject;
		my.loadleft();
		my.loadright();
	};
	this.loadleft = function() {
		var url = "VodSrvMainCharge?CMD=3";
		url += "&id=" + my.obj.Id;
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
			};
			var html = "";
			html += "<select size=\"10\" multiple=\"multiple\" id='list_select1' class='list_select_sub'>";

			for ( var i = 0; i < arr.length; i++) {
				html += "<option value='" + arr[i].Id + "'>" + arr[i].Name
						+ "</option>";
			};
			html += "</select>";
			$("left_change_subject").innerHTML = html;
			return arr;
		};
	};
	this.loadright = function() {
		var url = "VodSrvMainCharge?CMD=4";
		url += "&id=" + my.obj.Id;
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
			};
			var html = "";
			html += "<select size=\"10\" multiple=\"multiple\" id='list_select2' class='list_select_sub'>";

			for ( var i = 0; i < arr.length; i++) {
				html += "<option value='" + arr[i].Id + "'>" + arr[i].Name
						+ "</option>";
			};
			html += "</select>";
			$("right_change_subject").innerHTML = html;
			return arr;
		};
	};
	this.addsubject = function() {
		var select1 = $("list_select1");
		var select2 = $("list_select2");
		if (select1.selectedIndex < 0) {
			var albox = new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		};
		for ( var i = 0; i < select1.options.length; i++) {
			if (select1.options[i].selected == true) {
				select2.add(new Option(select1.options[i].text,
						select1.options[i].value));
				select1.remove(i);
			};
		};
	};
	this.removesubject = function() {
		var select1 = $("list_select1");
		var select2 = $("list_select2");
		if (select2.selectedIndex < 0) {
			var albox = new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		};
		for ( var i = 0; i < select2.options.length; i++) {
			if (select2.options[i].selected == true) {
				select1.add(new Option(select2.options[i].text,
						select2.options[i].value));
				select2.remove(i);
			};
		};
	};
	this.accept = function() {
		var select2 = $("list_select2");
		if (select2.options.length <= 0) {
			var albox = new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		};
		var param = "";
		for ( var i = 0; i < select2.options.length; i++) {
			param += "&subid" + i + "=" + select2.options[i].value;
		};
		var url = "VodSrvMainCge?CMD=7";
		url += "&vodId=" + my.obj.Id;
		url += param;
		url += "&r=" + Math.random();

		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			my.hide();
			my.complet();
		};
	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	};
};

// bo sung play video
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
		// var embedlink = "<embed name='embedplay' id='embedplay'
		// type='application/x-vlc-plugin' name='player' autoplay='no' ";
		// embedlink+= "loop='no' src='"+name+"' width='450px;'
		// height='350px;'/>";
		//		
		// var embedie = "<object type='application/x-vlc-plugin' id='embedobj'
		// width='450px' height='350px'";
		// embedie+= " classid='clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921'>";
		// embedie+= "<param id='ieparam' name='src' value='"+name+"'/>";
		// embedie+= "<param name='autoplay' value='false'/>";
		// embedie+= "<param name='toolbar' value='true' />";
		// embedie+= "</object>";

		var video = "<video width='450px;' height='350px;' id='checkvideo' name='checkvideo' src='"
				+ name + "' controls preload autoplay>";
		video += "<p>Try this page in a modern browser! Or you can <a href='"
				+ name + "'>download the video</a> instead.</p></video>";

		var html = "<div class='form_detail_song' id='div_playEmbed' align='center' style='height: 450px;'>";
		html += "<div class='div_Title_song'>";
		html += "CHECK VIDEO LINK</div>";
		html += "<div align='center' >";
		// html+=embedlink;
		// html+=embedie;
		html += video;
		html += "</div>";
		html += "<div style='float: left;width: 100%;height:30px;' align='center'>";
		html += "<div style='width: 70px; height:30px; margin-top:15px;'>";
		html += "<input type=\"button\" value='" + langMain.ok
				+ " '  class='div_buton'id=\"button_ok\" >";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		layer.addHtml(html);

		// run on IE only to active vlc 16.1
		// if (/*@cc_on!@*/false) {
		// document.documentElement.className+=' ie10';
		// document.getElementById("embedplay").style.display="none";
		// document.getElementById("embedobj").style.display="inline-block";
		// document.getElementById("embedobj").style.width = 450;
		// document.getElementById("embedobj").style.height = 350;
		// };
		// else {
		// document.getElementById("embedplay").style.display="inline-block";
		// document.getElementById("embedobj").style.display="none";
		// };
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
		document.getElementById("embedobj").removeNode(true);
	};
};

document.onload = new function() {
	Vod.run();
};
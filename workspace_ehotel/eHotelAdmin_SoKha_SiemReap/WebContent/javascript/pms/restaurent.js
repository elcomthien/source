function Restaurant() {
	var my = this;
	this.list = new listRestaurant();
	this.load = function(id) {
		my.list.load(id);
	};
	this.addItem = function(_id) {
		this.list.add(_id);
	};
	this.onclick = function() {
		my.list.pageclick();
	};
}
function listRestaurant() {
	var table = new Table();
	var ctTable = new contextTable();
	ctTable.fucDetail = function() {
		var detail = new editRestaurant();
		detail.load(this.obj);
		detail.update = function() {
			my.reload();
		};
	};
	ctTable.fucdel = function() {
		// my.ListCheck
//		var list = "";
//		if (my.ListCheck.length == 0) {
//			list += "&id0=" + this.obj;
//		} else {
//			for ( var i = 0; i < my.ListCheck.size(); i++) {
//				list += "&id" + i + "=" + my.ListCheck.get(i);
//			}
//		}
		var id = this.obj.Id;
		var cfBox = new conformBox();
		cfBox.show();
		cfBox.accept = function() {
			var url = "ServiceDinning?CMD=10";
//			url += list;
//			url += "&SubId=" + my.SubId;
//			url += "&id=" + id;
			url += "&r=" + Math.random();
			var param = "&id=" + id;
			var f = new AjaxPostText(url, param);
			f.complet = function(tx) {
				my.reload();
			};
			AjaxFuncGetXML(url, f);
		};
	};
	ctTable.fucAdd = function() {
		my.add(my.SubId);
	};
	ctTable.fucStatus = function() {
		var id = this.obj.Id;
		var url = "ServiceDinning?CMD=11";
		url += "&id=" + id;
		var param = "&id=" + id;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			my.reload();
		};
		AjaxFuncGetXML(url, f);
	};
	this.add = function(id) {
		var add = new AddRestaurant();
		add.load(id);
		add.update = function() {
			my.reload();
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
	var Index = 0;
	this.ListCheck = new Vector();
	this.List = new Array();
	var arr = new Array();
	this.page = 6;
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

	this.loadData = function() {

		var b = Index * this.page;
		var e = (Index + 1) * this.page;
		if (e > my.count)
			e = my.count;
		var arr = new Array();

		for ( var i = b; i < e; i++) {
			arr[i - b] = my.List[i];
		}
		table.load(arr);
		table.classCol("classItem1", 1);
		table.classCol("classItem0", 0);
		my.renderEvent();
	};
	this.load = function(id) {
		this.List = new Array();
		this.SubId = id;
		ID = id;
		table.init(defaults);
		my.get(id, Index);
	};
	this.reload = function() {
		this.List = new Array();
		Index = 0;
		my.get(my.SubId, Index);
	};
	this.loadIndex = function(index) {
		Index = index;
		my.loadData();
	};
	this.nextindex = function() {

		var cpage = (my.count / my.page) + 0.4;
		cpage = Math.round(cpage);
		if (Index < cpage - 1)
			Index++;
		smy.loadIndex(Index);
	};
	this.backindex = function() {
		if (Index > 0)
			Index--;
		my.loadIndex(Index);
	};
	this.renderEvent = function() {
		var tr = $(table.divName).getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var item = tr[i];
			item.onclick = function(e) {
				if (Common.ctrl) {
					my.addItem(this.id);
				} else {
					my.selectItem(this.id);
				}
				return false;
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
					my.loadIndex(this.id);
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
		} else {
			this.ListCheck.removeObj(id);
		}
		my.shiftSelect();
	};
	this.oncontextmenu = function(id, e) {
		var obj = null;
		for ( var i = 0; i < my.List.length; i++) {
			if (my.List[i].Id == id) {
				obj = my.List[i];
				break;
			}
		}
		ctTable.load(obj, e);
	};
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_listdining'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left'  valign=\"middle\"  width=\"" + 20 + "%\"  >";
		html += langpms.name;
		html += "</th>";
		html += "<th align='left' width=\"" + 30 + "%\" valign=\"middle\"  >";
		html += langpms.description;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += langpms.newprice;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += langpms.oldprice;
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += langpms.status;
		html += "</th>";
		html += "</tr>";

		for ( var i = 0; i < this.List.length; i++) {
			var des = this.List[i].Def;
			if (des.length > 60)
				des = des.substring(0, 60) + "...";
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\"  >";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\"   >";
			}

			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Name;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += des;
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			html += this.List[i].NewPrice;
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			html += this.List[i].OldPrice;
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			if (this.List[i].Invisible == 0) {
				html += "<img src=\"../icon/16-square-green.png\"></img>";
			} else {
				html += "<img src=\"../icon/16-square-red.png\"></img>";
			}
			html += "</td>";
			html += "</tr>";
		}

		html += "</table>";

		html += "</div>";
		if (my.count > my.page) {
			var page = my.count / my.page;
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
	this.get = function(id, index) {

		var url = "ServiceDinning?CMD=6";
		url += "&SubId=" + id;
		url += "&pageIndex=" + index;
		url += "&page=6";
		url += "&r=" + Math.random();
		var f = new funPropery();
		f.complet = function(tx) {
			var xml = tx.getElementsByTagName("xml");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _def = it[i].getElementsByTagName("def")[0].childNodes[0].nodeValue;
				var _new = it[i].getElementsByTagName("newprice")[0].childNodes[0].nodeValue;
				var _old = it[i].getElementsByTagName("oldprice")[0].childNodes[0].nodeValue;
				var _iunit = it[i].getElementsByTagName("iunit")[0].childNodes[0].nodeValue;
				var _invisible = it[i].getElementsByTagName("invisible")[0].childNodes[0].nodeValue;
				var _image = it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				my.List[i] = new ItemRestaurant(_id, _name, _def, _new,_old,_iunit, _image, _invisible);
			}
			my.count = my.List.length;
			my.loadData();
			my.renderEvent();
		};
		AjaxFuncGetXML(url, f);
	};
	this.loadcomplet = function() {

		// $("title_subject").innerHTML=my.subname+"("+my.count+")";

	};
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
			html += "<li class='icon_contextmenuplay'>";
			html += "<a>" + langpms.addItem + "</a>";
			html += "</li>";
			html += "<li class='icon_contextmenuEdit'>";
			html += "<a>" + langpms.detailItem + "</a>";
			html += "</li>";
			html += "<li class='icon_contextmenuDel'>";
			html += "<a>" + langpms.removeItem + "</a>";
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
			my.renderClick();
			eventMouse();
		};
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
			var itemAdd = li[0].getElementsByTagName("a")[0];
			itemAdd.onclick = function() {
				my.fucAdd();
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
	}
}
function AddRestaurant() {
	var my = this;
	var layer = new layer_vitual();
	this.subid = -1;
	this.load = function(id) {

		my.subid = id;
		my.show();
	};
	this.show = function() {
		layer.show();
		var html = "";
		var url = "ServiceDinning?CMD=7";
		url += "&id=-1";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_dinning");
			initTinymce(2, "540px");
			// var html=$("description").innerHTML;
			// rte.writeRichText('rte1',"description", html, 550, 150, true,
			// false);
			$("form_textbox_ok").onclick = function() {
				my.accept();
			};
			$("form_textbox_cancel").onclick = my.cancel;
		};
	};

	this.accept = function() {
		var name = $("name").value;
		var image = $("image").src;
		var newprice = $("price").value;
		var oldprice = $("sale").value;
		var select =$("div_money0");
		var iunit = select.options[select.selectedIndex].text;
		var des = getDataFromEditor("descriptionInput");
		image = image.substring(image.lastIndexOf("/") + 1);
		if (name.length == 0) {
			var albox = new alertBox();
			albox.show(langpms.name);
			return;
		}
		if(newprice.length == 0 || oldprice.length == 0){
			var albox = new alertBox();
			albox.show(langpms.pls_inputprice);
			return;
		}
		var url = "ServiceDinning?CMD=8";
		url += "&subid=" + my.subid;
		url += "&image=" + image;
		url += "&t=" + Math.random();
		var param = "&name=" + name;
		param += "&price=" + oldprice;
		param += "&sale=" + newprice;
		param += "&iunit=" + iunit;
		param += "&des=" + des;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			my.hide();
			my.update();
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
function editRestaurant() {
	var my = this;
	var layer = new layer_vitual();
	this.obj = null;
	this.load = function(obj_) {
		my.obj = obj_;
		my.show();
	};
	this.show = function() {
		layer.show();
		var html = "";
		var url = "ServiceDinning?CMD=7";
//		url += "&id=" + my.itemId;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			layer.addHtml(tx);
			setCenterDIV("form_detail_dinning");
			
			$("name").value = my.obj.Name;
			$("image").src = my.obj.Image;
			$("price").value = my.obj.OldPrice;
			$("sale").value = my.obj.NewPrice;
			$("descriptionInput").value = my.obj.Def; 
			$("div_money0").value = my.obj.Iunit;
//			$("div_money1").value = my.obj.Iunit;
			initTinymce(2, "540px");
			$("form_textbox_ok").onclick = function() {
				my.accept(my.obj.Id);
			};
			$("form_textbox_cancel").onclick = my.cancel;
		};
	};

	this.accept = function(id) {
		var name = $("name").value;
		var image = $("image").src;
		var newprice = $("price").value;
		var oldprice = $("sale").value;
		var select =$("div_money0");
		var iunit = select.options[select.selectedIndex].text;
		var des = getDataFromEditor("descriptionInput");
		image = image.substring(image.lastIndexOf("/") + 1);
		if (name.length == 0) {
			var albox = new alertBox();
			albox.show(langpms.name);
			return;
		}
		if(newprice.length == 0 || oldprice.length == 0){
			var albox = new alertBox();
			albox.show(langpms.pls_inputprice);
			return;
		}
		var url = "ServiceDinning?CMD=9";
		url += "&id=" + id;
		url += "&image=" + image;
		url += "&t=" + Math.random();
		var param = "&name=" + name;
		param += "&price=" + oldprice;
		param += "&sale=" + newprice;
		param += "&iunit=" + iunit;
		param += "&des=" + des;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			my.hide();
			my.update();
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

function ItemRestaurant(id, name, def, newprice, oldprice, iunit, image,
		invisible) {
	this.Id = id;
	this.Name = name;
	this.Def = def;
	this.NewPrice = newprice;
	this.OldPrice = oldprice;
	this.Iunit = iunit;
	this.Image = image;
	this.Invisible = invisible;
}
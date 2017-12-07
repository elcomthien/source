function mouseMove(e, me) {
	var mouse = false;
	document.body.onmousedown = function() {
		mouse = true;

		return false;
	};
	document.body.onmouseup = function() {
		mouse = false;
		return false;
	};
	document.body.onmousemove = function(e) {
		if (mouse) {
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
			$("form_detail_song").style.left = (posx - 200) + "px";
			$("form_detail_song").style.top = (posy - 10) + "px";
		}
	};
}
function Tree() {
	/**
	 * obj Array list with ItemObj
	 */
	var divName = "menu";
	this.list = null;
	this.Open = new Vector();
	var my = this;
	this.defaults = {
		divName : "menutree",
		classTree : "treeview",
		title : "Subject",
		classItem : "itemnomal",
		classfocus : "itemfocus",
		classRootOpen : "treeviewrootopen",
		classRootClose : "treeviewroot",
		clcassleaves : "treeviewleaves_admin_tt",
		classopen : "treeopen",
		classclose : "treecolse"
	};
	this.init = function(options) {
		for ( var name in my.defaults) {
			this[name] = (options !== undefined && options[name] !== undefined) ? options[name]
					: my.defaults[name];
			if (options !== undefined && options[name] !== undefined) {
				my.defaults[name] = options[name];
			}
			my[name] = this[name];
		}
	};
	this.load = function(obj) {
		if (obj.length == 0)
			return;
		my.list = new Vector();
		for ( var i = 0; i < obj.length; i++) {
			var it = new ItemObj(obj[i].Id, obj[i].Name);
			it.Parent = obj[i].Parent;
			it.isOpen = my.Open.isObject(obj[i].Id) ? 1 : 0;
			my.list.add(it);
		}
		my.show();
	};
	this.reload = function(obj) {
		my.list = new Vector();
		for ( var i = 0; i < obj.length; i++) {
			var it = new ItemObj(obj[i].Id, obj[i].Name);
			it.Parent = obj[i].Parent;
			it.isOpen = my.Open.isObject(obj[i].Id) ? 1 : 0;
			my.list.add(it);
		}
		my.show();
	};
	this.show = function() {
		var html = "";
		html += "<div class='" + my.classTree + "'>";
		html += "<ul style='padding-left:5px;margin-left:5px;' >";
		var parentid = new Vector();
		var sub = new Vector();
		var arr = new Vector();
		for ( var i = 0; i < my.list.size(); i++) {
			var it = new ItemObj(my.list.get(i).Id, my.list.get(i).Name);
			it.Parent = my.list.get(i).Parent;
			it.isOpen = my.list.get(i).isOpen;
			arr.add(it);
		}
		for ( var i = 0; i < arr.size(); i++) {
			if (arr.get(i).Parent == -1) {
				sub.add(arr.get(i));
				arr.remove(i);
				i--;
			}
		}
		for ( var j = 0; j < sub.size(); j++) {
			parentid.add(sub.get(j));
			var tag = 0;
			while (parentid.size() > 0) {
				var t = false;
				var p = parentid.lastElement().Id;
				for ( var i = 0; i < arr.size(); i++) {
					var k = arr.get(i).Parent;
					if (Number(k) == Number(p)) {
						t = true;
						var obj = arr.get(i);
						parentid.add(arr.get(i));
						arr.remove(i);
						break;
					}
				}
				if (t) {
					// co nhanh con
					if (parentid.size() - 1 > tag) {
						html += "<li pos='r' id='"
								+ parentid.get(parentid.size() - 2).Id
								+ "' isOpen='"
								+ parentid.get(parentid.size() - 2).isOpen
								+ "'>";
						html += "<a pos='r' href='javascript:' class='"
								+ my.classRootOpen + "' id='"
								+ parentid.get(parentid.size() - 2).Id + "'>";
						html += parentid.get(parentid.size() - 2).Name;
						html += "</a>";
						html += "<ul class='" + my.classopen + "'>";
						tag++;
					}
				} else {
					// neu la nut la
					if (parentid.size() > tag) {
						html += "<li pos='l' id='" + parentid.lastElement().Id
								+ "'>";
						html += "<a pos='l' href='javascript:' class='"
								+ my.clcassleaves + "' id='"
								+ parentid.lastElement().Id + "'>";
						html += parentid.lastElement().Name;
						html += "</a>";
						html += "</li>";
						parentid.remove(parentid.size() - 1);
					} else {
						tag--;
						html += "</ul>";
						html += "</li>";
						parentid.remove(parentid.size() - 1);
					}
				}
			}
		}
		html += "</ul>";
		html += "</div>";
		$(my.divName).innerHTML = html;
		my.renderEvent();
		my.shift();
	};
	this.renderEvent = function() {
		var a = $(my.divName).getElementsByTagName("a");
		for ( var i = 0; i < a.length; i++) {
			var item = a[i];
			item.onclick = function() {
			};
		}
	};
	this.addItem = function(obj) {
		my.list.add(obj);
		my.show();
	};
	this.refress = function() {
		this.show();
	};
	this.change = function(id) {
		var li = $(my.divName).getElementsByTagName("li");
		for ( var i = 0; i < li.length; i++) {
			if (li[i].id == id) {
				if (li[i].pos == "l")
					return;
				var ul = li[i].getElementsByTagName("ul");
				var a = li[i].getElementsByTagName("a");

				if (ul[0].className == my.classopen) {
					ul[0].className = my.classclose;
					a[0].className = my.classRootClose;
					my.Open.removeObj(id);
				} else {
					ul[0].className = my.classopen;
					a[0].className = my.classRootOpen;
					my.Open.add(id);
				}
				break;
			}
		}
	};
	this.shift = function() {
		var li = $(my.divName).getElementsByTagName("li");
		for ( var i = 0; i < li.length; i++) {
			if (li[i].getAttribute("pos") == "r") {
				var ul = li[i].getElementsByTagName("ul");
				var a = li[i].getElementsByTagName("a");
				if (li[i].getAttribute("isOpen") == "0") {
					ul[0].className = my.classclose;
					a[0].className = my.classRootClose;
				} else {
					ul[0].className = my.classopen;
					a[0].className = my.classRootOpen;
				}
			}
		}
	};
}
function createPagings(count, index) {
	count += 0.4;
	count = Math.round(count);
	var html = "";
	html += "<div class='div_page_icon_left' id='div_page_icon_left'>";
	html += "</div>";
	if (count <= 5) {
		if (index == 5) {
			index = index - 1;
		}
		for ( var i = 0; i < count; i++) {
			if (i == index) {
				html += "<a class='div_page_a_sel' href=\"javascript:\" id=\""
						+ i + "\">";
			} else {
				html += "<a class='div_page_a' href=\"javascript:\" id=\"" + i
						+ "\">";
			}
			html += (i + 1);
			html += " </a>";
		}
	} else {
		if (index <= 2) {
			for ( var i = 0; i < 5; i++) {
				if (i == index) {
					html += "<a class='div_page_a_sel' href=\"javascript:\" id=\""
							+ i + "\">";
				} else {
					html += "<a class='div_page_a' href=\"javascript:\" id=\""
							+ i + "\">";
				}
				html += (i + 1);
				html += " </a>";
			}
		} else {
			if (index >= count - 2) {
				var b = count - 4;
				var t = Number(index);
				var tam = t + 1;
				if (tam > count) {
					tam = count;
				}
				for ( var i = b; i <= count; i++) {
					if (i == (tam)) {
						html += "<a class='div_page_a_sel' href=\"javascript:\" id=\""
								+ (i - 1) + "\">";
					} else {
						html += "<a class='div_page_a' href=\"javascript:\" id=\""
								+ (i - 1) + "\">";
					}
					html += (i);
					html += " </a>";
				}
			} else {
				var b = index - 2;
				for ( var i = 0; i < 5; i++) {
					if ((i + b) == index) {
						html += "<a class='div_page_a_sel' href=\"javascript:\" id=\""
								+ (i + b) + "\">";
					} else {
						html += "<a class='div_page_a' href=\"javascript:\" id=\""
								+ (i + b) + "\">";
					}
					html += (i + 1) + b;
					html += " </a>";
				}
			}
		}
	}
	html += "<div class='div_page_icon_right' id='div_page_icon_right'>";
	html += "</div>";
	return html;
}
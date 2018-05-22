var Index = 0;
var page = 6;
var listArr;
var tempIdMenu = 0;
var temIdmenuGroup = 0;
var count = 0;
var arr;
var classItem = "classItem";
var classTable = "classTable";
var classRowsel = "mytable_row_sel";
var classTable = "mytable";
var bgRow1 = "mytable_row1";
var bgRow2 = "mytable_row2";
var bgmenu = "MenuBoder";
var Status;
var StatusLive;
// var ctMenu = new contextMenu();
var ctTable = new contextTableEX();
var my = this;
var tempId = 0;
var arrColor = new Array();
var dem = 0;
var demLiveAdd = 0;
var demLiveRemove = 0;
var trangthai = 0;
var countArr = 0;
var arrColorTemp = new Array();
var arrColorLive = new Array();
var arrColorLiveTmp = new Array();
var arrColorLiveRemove = new Array();
var arrColorLiveRemoveTmp = new Array();
var arrLive;
var arrChannel;
var menuIdGF = 0;
var checkTab = 0;
var pressId = 0;
var pressId1 = 0;
var checkPress = 0;
var checkPress1 = 0;
var cId1 = 0;
var cOnId = 0;
var r = 0;
var cOnId1 = 0;
var r1 = 0;
var checkDB = 0;
var checkDB1 = 0;
var BDcount = 0;
var checkSearch = 0;
var checkstatus = 0;
var temrowId = 0;
var temrowRemoveId = 0;
this.pageclick = function() {
	if (ctTable.state) {
		arrColor = new Array();
		countArr = 0;
		ctTable.hide();
	}
};
document.onclick = function() {
	pageclick();
};
function closeMenuTable(id) {
	pageclick();
}
function nextindex() {
	var cpage = ((count - 1) / page) - 1;
	if (Index <= cpage)
		Index++;
	loadIdex(Index);
}
function backindex() {

	if (Index > 0)
		Index--;
	loadIdex(Index);
}
function loadIdex(index) {
	Index = index;
	// loadChannelInfor(tempIdMenu, 0);
	loadAvertise();

}
var add = new AddChannels();
function changeTab(id) {
	var div = $("tab_menu").getElementsByTagName("div");
	if (id == 0) {
		$("id_table").style.display = 'block';
		$("id_table1").style.display = 'none';
		$("id_table2").style.display = 'none';
		// document.getElementById("menutree").style.display = "block";
		div[0].className = "tab_menu_item_sel";
		div[1].className = "tab_menu_item";
		div[2].className = "tab_menu_item";
		checkTab = 0;
		// $("frmSearch").style.display = 'block';
		// loadChannelInfor(tempIdMenu, 0);
		loadAvertise();
		checkSearch = 0;
		// $("textSearch").value = "";
	} else if (id == 1) {
		$("id_table1").style.display = 'none';
		$("id_table").style.display = 'none';
		$("id_table2").style.display = 'block';
		// document.getElementById("menutree").style.display = "block";
		div[1].className = "tab_menu_item_sel";
		div[0].className = "tab_menu_item";
		div[2].className = "tab_menu_item";
		checkTab = 1;
		add.load();
	} else {
		div[2].className = "tab_menu_item_sel";
		div[1].className = "tab_menu_item";
		div[0].className = "tab_menu_item";
		document.getElementById("menutree").style.display = "none";
		checkTab = 2;
		add.load();
	}
	tag = id;
}
function loadMenu(checkload) {
	loadAvertise();
	// var url = "Channel?CMD=1";
	// url += "&r=" + Math.random();
	// var f = new AjaxGetXML(url);
	// f.complet = function(tx) {
	// listArr = new Array();
	// var xml = tx.getElementsByTagName("xml");
	// menuIdGF = xml[0].getAttribute("menuIdGF");
	// var it = tx.getElementsByTagName("Item");
	// for ( var i = 0; i < it.length; i++) {
	// var _name =
	// it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
	// var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
	// var _parent =
	// it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
	// var _urlIma =
	// it[i].getElementsByTagName("urlIma")[0].childNodes[0].nodeValue;
	// listArr[i] = new ItemMenu(_id, _name, _parent, _urlIma);
	// }
	// document.getElementById("menutree").innerHTML = ShowHTMLMenu(listArr);
	// if (listArr.length == 0) {
	// $("AddMenu").style.display = 'block';
	// } else {
	// $("AddMenu").style.display = 'none';
	// }
	// renderEventGroup();
	// if (checkload != 0) {
	// ClickItem(checkload);
	// loadChannelInfor(checkload, 0);
	// } else {
	//
	// loadChannelInfor(menuIdGF, 1);
	// ClickItem(menuIdGF);
	// }
	// // loadChannelInfor(22, 1);
	// };
}
function loadChannelInfor(mId, cstatus) {
	if (checkTab == 0) {
		if (cstatus == 1) {
			Index = 0;
			$("textSearch").value = "";
		}
		checkSearch = 0;
		trangthai = 1;
		page = 6;
		tempIdMenu = mId;
		var url = "Channel?CMD=15";
		url += "&pageIndex=" + Index;
		url += "&page=" + page;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			document.getElementById("id_table").innerHTML = getHTMLUser(arr,
					count);
			// document.getElementById("totalUser").innerHTML =
			// ShowTotalUser(count);
			renderEvent();
			ClickItem(mId);
		};

	} else if (checkTab == 1) {
		tempIdMenu = mId;
		var nameSubSer = "";
		for ( var i = 0; listArr.length; i++) {
			if (listArr[i].Id == mId) {
				nameSubSer = listArr[i].Name;
				break;
			}
		}
		$("namSerSub").innerHTML = nameSubSer;
		OnloadChannel(mId);
		OnloadLive(mId);
		ClickItem(mId);
	}
}

function loadAvertise() {
	if (checkSearch == 0) {
		Index = 0;
	}
	checkstatus = 1;
	checkSearch = 1;
	page = 6;
	var url = "Channel?CMD=15";
	url += "&pageIndex=" + Index;
	url += "&page=" + page;
	url += "&r=" + Math.random();
	var f = new AjaxGetXML(url);
	f.complet = function(tx) {
		arr = new Array();
		var xml = tx.getElementsByTagName("xml");
		count = xml[0].getAttribute("count");
		var it = tx.getElementsByTagName("Item");
		for ( var i = 0; i < it.length; i++) {
			var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
			var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
			var _stt = it[i].getElementsByTagName("stt")[0].childNodes[0].nodeValue;
			var _channelCode = it[i].getElementsByTagName("ChannelCode")[0].childNodes[0].nodeValue;
			var _addr = it[i].getElementsByTagName("address")[0].childNodes[0].nodeValue;
			var _urlIma = it[i].getElementsByTagName("urlIma")[0].childNodes[0].nodeValue;
			var _serviceName = it[i].getElementsByTagName("serviceName")[0].childNodes[0].nodeValue;
			var _active = it[i].getElementsByTagName("Active")[0].childNodes[0].nodeValue;
			var _sl = it[i].getElementsByTagName("sl")[0].childNodes[0].nodeValue;
			arr[i] = new ItemChannel(_name, _id, _stt, _channelCode, _addr,
					_urlIma, _serviceName, _active, _sl);
			if (_id == tempId) {
				Status = _active;
			}
		}
		document.getElementById("id_table").innerHTML = getHTMLUser(arr, count);
		// document.getElementById("totalUser").innerHTML =
		// ShowTotalUser(count);
		renderEvent();
		// renderEventGroup();
		// ClickItem(tempIdMenu);
	};
}
function ShowTotalUser(count) {
	var tenMenu = "";
	for ( var i = 0; i < listArr.length; i++) {
		if (listArr[i].Id == tempIdMenu) {
			tenMenu = listArr[i].Name;
			break;
		}
	}
	var html = "";
	html += tenMenu + " (" + total + ":" + count + ")";
	return html;
}
function ItemChannel(name, id, no, channelCode, address, urlIma, serviceName,
		active, sl) {
	this.No = no;
	this.Id = id;
	this.Name = name;
	this.ChannelCode = channelCode;
	this.UrlIma = urlIma;
	this.ServiceName = serviceName;
	this.Active = active;
	this.Address = address;
	this.sl = sl;
}
function ItemMenu(id, name, parent, urlIma) {
	this.Id = id;
	this.Name = name;
	this.Parent = parent;
	this.UrlIma = urlIma;
}
function getHTMLUser(arr, count) {
	var html = "";
	if (typeof arr != 'undefined') {
		html += "<div class='div_table' style='width: 98%;'>";
		html += "<table  cellspacing=\"0\" width='99%' cellpadding=\"0\" border=\"0\" height='30' class='mytable'  id='table_id'>";
		html += "<tr class='mytable_header'>";
		html += "<th align='center' style='padding-left:5px'  height='30' valign=\"middle\"  width=\""
				+ 10 + "%\"  >";
		html += No;
		html += "</th>";
		html += "<th style='margin-left: 20px;' align='left'height='30' width=\""
				+ 30 + "%\" valign=\"middle\"  >";
		html += name;
		html += "</th>";
		html += "<th align='center' height='30' width=\"" + 30
				+ "%\" valign=\"middle\"  >";
		html += address;
		html += "</th>";
		html += "<th align='center' height='30' width=\"" + 15
				+ "%\" valign=\"middle\"  >";
		html += _Image;
		html += "</th>";
		html += "<th align='center' height='30' style='padding-right:10px' width=\""
				+ 15 + "%\" valign=\"middle\"  >";
		html += active;
		html += "</th>";
		// html += "<th align='center' height='30' style='padding-right:10px'
		// width=\""
		// + 15 + "%\" valign=\"middle\" >";
		// html += "Views";
		html += "</th>";
		html += "</tr bgcolor='#f2f2f2'>";
		var begin = Index * page;
		for ( var i = 0; i < arr.length; i++) {
			// them vao day 8.1
			if (i % 2 == 0) {
				html += "<tr  onmousedown='isKeyPressed(event," + arr[i].Id
						+ ")'  class=\"" + bgRow2 + "\" id=\"" + arr[i].Id
						+ "\" rel=\"" + arr[i].ServiceName + "\" >";
			} else {
				html += "<tr onmousedown='isKeyPressed(event," + arr[i].Id
						+ ")'  class=\"" + bgRow1 + "\" id=\"" + arr[i].Id
						+ "\" rel=\"" + arr[i].ServiceName + "\"  >";
			}
			html += "<td height='30' style='padding-left:5px' align='center' width='5%'>"
					+ arr[i].No + "</td>";
			html += "<td height='30' width='20%' style='color: #9aa685;font-size:18'>"
					+ arr[i].ServiceName + "</td>";
			html += "<td height='30' width='20%'>" + arr[i].Address + "</td>";
			html += "<td height='30' width='20%' align='center'><img id='image1' src='"
					+ linksaveimage
					+ arr[i].UrlIma
					+ "' width='63' height='30' style='border: 1px solid #dddddd;'></img></td>";
			if (arr[i].Active == 1) {
				html += "<td height='30' align='center' style='padding-right:10px' width='15%'><img src='../icon/16-square-green.png'></td>";
			} else {
				html += "<td height='30' align='center' style='padding-right:10px' width='15%'><img src='../icon/16-square-red.png'></td>";
			}
			// html += "<td height='30' align='center' width='10%'>" + arr[i].sl
			// + "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		/*
		 * if(count ==0){ if(aa!=0){ html+="<div id='addusergroup1'
		 * align='center' style='display:block'>"; html+="<a
		 * href='javascript:showfromAddUser()' class='div_addeImage'
		 * title='Click here add User'>"+ AddUser +"</a>" html+="</div>"; } }
		 */
		html += "<div id=\"div_page\" class='div_page'>";
		if (count > 6) {
			var page = count / 6;
			html += createPagings(page, Index);
		}
		html += "</div>";
		html += "<div class='div_bottom_table'>";
		html += "";
		html += "</div>";
	}
	return html;
}
function renderEvent() {
	if (count != 0) {
		var tr = $("table_id").getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var item = tr[i];
			item.oncontextmenu = function(e) {
				shiftSelect(this.id);
				if (this.id != "") {
					my.oncontextmenu(this.id, e);
				} else {
					closeMenu();
				}
				return false;
			};
		}
		;
	}
	/**
	 * render paging
	 */
	if ($("div_page") != undefined) {
		var div = $("div_page");
		var a = div.getElementsByTagName("a");
		for ( var i = 0; i < a.length; i++) {
			var item = a[i];
			item.onclick = function(e) {
				loadIdex(this.id);
			};
		}
		var div1 = $("div_page").getElementsByTagName("div");
		if (div1.length > 0) {
			div1[0].onclick = function() {
				backindex();
			};
			div1[1].onclick = function() {
				nextindex();
			};
		}
	}
}
this.oncontextmenu = function(id, e) {
	var obj = null;
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i].Id == id) {
			Status = arr[i].Active;
			tempId = id;
			obj = arr[i];
			break;
		}
	}
	ctTable.load(obj, e);
};
function shiftSelect(id) {
	var b = 0;
	var table = document.getElementById("table_id");
	var tr = table.getElementsByTagName("tr");
	for ( var i = 0; i < tr.length; i++) {
		if (i % 2 == 0) {
			tr[i].className = bgRow2;
		} else {
			tr[i].className = bgRow1;
		}
		if (id != 0) {
			if (tr[i].id == id) {
				b = i;
			}
		}
	}
	if (arrColor.length > 1) {
		for ( var j = 0; j < arrColor.length; j++) {
			var t = arrColor[j].Id;
			tr[t].className = "mytable_row_sel2";
		}
	} else {
		arrColor = new Array();
		tr[b].className = "mytable_row_sel2";
	}
}
function isKeyPressed(event, id) {
	pageclick();
	if (trangthai == 1) {
		arrColor = new Array();
		trangthai = 0;
		dem = 0;
	}
	var table = document.getElementById("table_id");
	var tr = table.getElementsByTagName("tr");
	for ( var i = 0; i < tr.length; i++) {
		if (event.ctrlKey == 1) {
			if (tr[i].id == id) {
				if (checkId(arrColor, id, i) == 0
						|| checkId(arrColor, id, i) == 2) {
					if (checkId(arrColor, id, i) == 2) {
						for ( var k = 0; k < arrColor.length; k++) {
							if (arrColor[k].Id == i) {
								arrColor[k].ItemId = id;
								break;
							}
						}
					} else {
						arrColor[dem] = new ItemCtrl(i, tr[i].id);
						dem = dem + 1;
					}
					for ( var j = 0; j < arrColor.length; j++) {
						if (arrColor[j].ItemId != 0) {
							var t = arrColor[j].Id;
							tr[t].className = "mytable_row_sel2";
						}
					}
				} else {
					for ( var p = 0; p < arrColor.length; p++) {
						if (arrColor[p].Id == i) {
							var f = arrColor[p].Id;
							arrColor[p].ItemId = 0;
							tr[f].className = "mytable_row1";
							break;
						}
					}
				}
			}
		} else {
			if (event.button == 0 || event.button == 1) {
				dem = 0;
				arrColor = new Array();
				closeMenuTable(id);
			} else {
				dem = 0;
				closeMenuTable(id);
			}
			break;
		}
	}
	arrColorTemp = arrColor;
}
function checkId(arr, id, i) {
	var k = 0;
	for ( var y = 0; y < arr.length; y++) {
		if (arr[y].Id == i) {
			if (arr[y].ItemId == id) {
				k = 1;
				break;
			} else {
				k = 2;
				break;
			}
			break;
		}
	}
	return k;
}
function ItemCtrl(id, itemId) {
	this.Id = id;
	this.ItemId = itemId;
}
function onChangeColorRow(id) {
	var table = document.getElementById("table_id");
	var tr = table.getElementsByTagName("tr");
	for ( var i = 0; i < tr.length; i++) {
		if (id != 0) {
			if (tr[i].id == id) {
				tr[i].className = "mytable_row_sel2";
			}
		}
	}
}

this.fucplaylink = function(rel) {
	pageclick();
	/*
	 * var url="Channel?CMD=100"; url+="&subjctId="+tempIdMenu;
	 * url+="&livetvId="+tempId; url+="&r="+Math.random(); url+="&link="+rel;
	 * var f=new AjaxGetText(url); f.complet=function(tx) { if(tx=="failed"){
	 * var albox=new alertBox(); albox.show("Service die, please restart
	 * service"); } } loadChannelInfor(tempIdMenu,0);
	 */
	var eb = new playEmbed(rel);
	eb.show(rel);
	onChangeColorRow(tempId);
};
this.fucStatus = function(rel) {
	pageclick();
	var url = "Channel?CMD=4";
	url += "&subjctId=" + tempIdMenu;
	url += "&livetvId=" + tempId;
	url += "&r=" + Math.random();
	url += "&command=" + rel;
	var f = new AjaxGetText(url);
	f.complet = function(tx) {
		if (tx == "failed") {
			var albox = new alertBox();
			albox.show("Service die, please restart service");
		}
	};
	// loadChannelInfor(tempIdMenu, 0);
	loadAvertise();
	onChangeColorRow(tempId);
};

function showfromEditChannel() {
	pageclick();
	var name = "";
	var serviceName = "";
	var image = null;
	var addr_ = "";
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i].Id == tempId) {
			name = arr[i].Name;
			serviceName = arr[i].ServiceName;
			image = arr[i].UrlIma;
			addr_ = arr[i].Address;
			break;
		}
	}
	var url = "Link";
	var textbox = new newBox1(subjectInfoLive, name, serviceName, image,
			_serviceName, _titleName, _Image, ok, cancel, url, addr_);
	textbox.show();
	textbox.accept = function(text, image, link) {
		image = image.substring(image.lastIndexOf("/") + 1);
		if (text.length <= 0) {
			var albox = new alertBox();
			albox.show(subjectName);
			return;
		}
		var url = "Channel?CMD=3";
		url += "&image=" + image;
		url += "&channelId=" + tempId;
		url += "&link=" + link;
		url += "&t=" + Math.random();
		var param = "&name=" + encode(text);
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			if (tx == "failed") {
				var albox = new alertBox();
				albox.show(checkUpdate);
			} else if (tx == "f") {
				var albox = new alertBox();
				albox.show(checkEditEx);
			} else {
				// loadMenu();
				loadAvertise();
				// loadChannelInfor(tempIdMenu, 0);
				textbox.hide();
			}
		};

	};
}
function showfromDeleteChannel() {
	pageclick();
	var cfBox = new conformBox();
	cfBox.show(checkDeleteFrom, ok, cancel);
	cfBox.accept = function() {
		var str = "";
		for ( var i = 0; i < arrColorTemp.length; i++) {
			if (arrColorTemp[i].ItemId != 0) {
				if (i == arrColorTemp.length - 1) {
					str += arrColorTemp[i].ItemId;
				} else {
					str += arrColorTemp[i].ItemId + ",";
				}
			}
		}
		if (str.length == 0) {
			str = tempId;
		}
		var url = "Channel?CMD=6";
		url += "&subjId=" + tempIdMenu;
		url += "&str=" + str;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			if (tx == "failed") {
				var albox = new alertBox();
				albox.show(checkDelete);
			}
			loadAvertise();
		};
		// loadChannelInfor(tempIdMenu, 0);
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
		html += "<li class='icon_contextmenuEdit'>";
		html += "<a>" + subjectInfoLive + "</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuDel'>";
		html += "<a>" + deleteLiveTv + "</a>";
		html += "</li>";
		// them vao day 8.1
		var rel = this.obj.Name.replace(/ /g, "_");
		// var rellink = this.obj.Address;
		if (Status == 1) {
			html += "<li class='icon_visibility2'><a rel='stop " + rel + "*'>";
		} else {
			html += "<li class='icon_visibility1'><a rel='play " + rel + "*'>";
		}
		html += changeStatus + "</a>";
		html += "</li>";
		// them vao day de tao popup menu cho viec play kenh livetv 12.1
		// html += "<li class='icon_visibility2'>";
		// html += "<a class='playlink' rel='" + rellink + "'>" +
		// checkLinkLiveTV
		// + "</a>";
		// html += "</li>";
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
		var itemedit = li[0].getElementsByTagName("a")[0];
		itemedit.onclick = function() {
			showfromEditChannel();
		};
		var itemdel = li[1].getElementsByTagName("a")[0];
		itemdel.onclick = function() {
			showfromDeleteChannel();
		};
		var changeStatus = li[2].getElementsByTagName("a")[0];
		var rel = changeStatus.getAttribute("rel");
		changeStatus.onclick = function() {
			fucStatus(rel);
		};
		// var playlink = li[3].getElementsByTagName("a")[0];
		// var rellink = playlink.getAttribute("rel");
		// playlink.onclick = function() {
		// fucplaylink(rellink);
		// };
	};
	this.hide = function() {
		this.state = false;
		$("div_contextMenu").style.display = "none";
	};
	this.fucStatus = function() {
		alert("FGFG");
	};
}
function TableSrv() {
	var my = this;
	this.show = function(arr) {
		var d = 1;
		var html = "<table class='table2' border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		html += "<thead class='fixedHeader'>";
		html += "<tr>";
		html += "<th align=\"left\">";
		html += _no;
		html += "</th>";
		html += "<th align=\"left\">";
		html += title;
		html += "</th>";
		html += "<th >";
		html += _trangthai;
		html += "</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody class='scrollContent'>";
		for ( var i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				html += "<tr onmousedown='isKeyPressedLiveTvChannelRemove(event,"
						+ arr[i].Id
						+ ","
						+ d++
						+ ")'  class='mytable2_row2' id='" + arr[i].Id + "'>";
			} else {
				html += "<tr onmousedown='isKeyPressedLiveTvChannelRemove(event,"
						+ arr[i].Id
						+ ","
						+ d++
						+ ")'  class='mytable2_row1' id='" + arr[i].Id + "'>";
			}
			html += "<td>";
			html += arr[i].No;
			html += "</td>";
			html += "<td style='color: #9aa685;font-size:18;width:200px'>";
			html += arr[i].ServiceName;
			html += "</td>";
			html += "<td align=\"center\">";
			html += "<img src=\"../icon/16-square-green.png\"></img>";
			html += "</td>";
			html += "</tr>";
		}
		html += "</tbody>";
		html += "</table>";
		$("tableSrv").innerHTML = html;
		this.renderEvent();
	};
	this.renderEvent = function() {
		var tr = $("tableSrv").getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var item = tr[i];
			item.onclick = function(e) {
				my.selectRow(this);
			};
			item.ondblclick = function() {
				my.remove(this.id);
				return false;
			};
		}
		;
	};
	this.selectRow = function(me) {
		/*
		 * var tr=$("tableSrv").getElementsByTagName("tr"); for(var i=1;i<tr.length;i++) {
		 * if(i%2==0) { tr[i].className="mytable2_row1"; }else {
		 * tr[i].className="mytable2_row2"; } }
		 */
		// me.className="mytable_row_sel";
	};
}
function TableCtn() {
	var my = this;
	this.show = function(arr) {
		var html = "<table class=\"table2\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >";
		html += "<thead class='fixedHeader'>";
		html += "<tr>";
		html += "<th align=\"left\">";
		html += _no;
		html += "</th>";
		html += "<th  align=\"left\">";
		html += title;
		html += "</th>";
		html += "<th>";
		html += _trangthai;
		html += "</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody class='scrollContent'>";
		var c = 1;
		for ( var i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				html += "<tr onmousedown='isKeyPressedLiveTvChannel(event,"
						+ arr[i].Id + "," + c++
						+ ")' class='mytable2_row2' id='" + arr[i].Id + "'>";
			} else {
				html += "<tr onmousedown='isKeyPressedLiveTvChannel(event,"
						+ arr[i].Id + "," + c++
						+ ")' class='mytable2_row1' id='" + arr[i].Id + "'>";
			}
			html += "<td>";
			html += arr[i].No;
			html += "</td>";
			html += "<td style='color: #9aa685;font-size:18;width:200px' >";
			html += arr[i].ChannelName;
			html += "</td>";
			html += "<td align=\"center\">";
			html += "<img src=\"../icon/16-square-green.png\"></img>";
			html += "</td>";
			html += "</tr>";
		}
		html += "</tbody>";
		html += "</table>";
		$("tableCtn").innerHTML = html;
		this.renderEvent();
	};
	this.renderEvent = function() {
		// Table
		var tr = $("tableCtn").getElementsByTagName("tr");
		for ( var i = 1; i < tr.length; i++) {
			var item = tr[i];
			item.onclick = function(e) {
				my.selectRow(this);
			};
			item.ondblclick = function() {
				my.addToService();
				return false;
			};
		}
		;
	};
	this.selectRow = function(me) {
		/*
		 * var tr=$("tableCtn").getElementsByTagName("tr"); for(var i=1;i<tr.length;i++) {
		 * if(i%2==0) { tr[i].className="mytable2_row1"; }else {
		 * tr[i].className="mytable2_row2"; } } if(arrColorLive.length>1 ){
		 * for(var j=0;j<arrColorLive.length;j++){ var t=arrColorLive[j].Id;
		 * tr[t].className="mytable_row_sel2"; } }else{
		 * 
		 * arrColorLive =new Array(); me.className="mytable_row_sel"; } /* var
		 * b=0; var table = document.getElementById("tableCtn"); var tr =
		 * table.getElementsByTagName("tr"); for(var i=0;i<tr.length;i++) {
		 * if(i%2==0) { tr[i].className=bgRow2; }else { tr[i].className=bgRow1; } }
		 * if(arrColorLive.length>1 ){ alert(arrColorLive.length); for(var j=0;j<arrColorLive.length;j++){
		 * var t=arrColorLive[j].Id; tr[t].className="mytable_row_sel2"; }
		 * 
		 * }else{
		 * 
		 * arrColorLive =new Array(); me.className="mytable_row_sel"; }
		 */
	};
}
function ItemTVChannel(id, no, channelName, serviceName, active) {
	this.Id = id;
	this.No = no;
	this.ChannelName = channelName;
	this.ServiceName = serviceName;
	this.Active = active;
}
var tableSrv = new TableSrv();
var tableCtn = new TableCtn();
function isKeyPressedLiveTvChannel(event, id, c) {
	var table = document.getElementById("tableCtn");
	var tr = table.getElementsByTagName("tr");
	for ( var i = 0; i < tr.length; i++) {
		if (event.ctrlKey == 1) {
			if (tr[i].id == id) {
				if (checkId(arrColorLive, id, i) == 0
						|| checkId(arrColorLive, id, i) == 2) {
					if (checkId(arrColorLive, id, i) == 2) {
						for ( var k = 0; k < arrColorLive.length; k++) {
							if (arrColorLive[k].Id == i) {
								arrColorLive[k].ItemId = id;
								break;
							}
						}
					} else {
						arrColorLive[demLiveAdd] = new ItemCtrl(i, tr[i].id);
						demLiveAdd = demLiveAdd + 1;
					}
					for ( var j = 0; j < arrColorLive.length; j++) {
						if (arrColorLive[j].ItemId != 0) {
							var t = arrColorLive[j].Id;
							tr[t].className = "mytable_row_sel2";
						}
					}
				} else {
					for ( var p = 0; p < arrColorLive.length; p++) {
						if (arrColorLive[p].Id == i) {
							var f = arrColorLive[p].Id;
							arrColorLive[p].ItemId = 0;
							tr[f].className = "mytable_row1";
							break;
						}
					}
				}
			}
		} else {
			if (event.button == 0 || event.button == 1) {
				demLiveAdd = 0;
				arrColorLive = new Array();
				var tr = $("tableCtn").getElementsByTagName("tr");
				for ( var i = 1; i < tr.length; i++) {
					if (i % 2 == 0) {
						tr[i].className = "mytable2_row1";
					} else {
						tr[i].className = "mytable2_row2";
					}
					if (tr[i].id == id) {
						temrowId = id;
						tr[i].className = "mytable_row_sel";
					}
				}
			} else {
				demLiveAdd = 0;
			}
			break;
		}
	}
	arrColorLiveTmp = arrColorLive;
}
function isKeyPressedLiveTvChannelRemove(event, id, c) {
	var table = document.getElementById("tableSrv");
	var tr = table.getElementsByTagName("tr");

	for ( var i = 0; i < tr.length; i++) {
		if (event.ctrlKey == 1) {
			if (tr[i].id == id) {
				if (checkId(arrColorLiveRemove, id, i) == 0
						|| checkId(arrColorLiveRemove, id, i) == 2) {
					if (checkId(arrColorLiveRemove, id, i) == 2) {
						for ( var k = 0; k < arrColorLiveRemove.length; k++) {
							if (arrColorLiveRemove[k].Id == i) {
								arrColorLiveRemove[k].ItemId = id;
								break;
							}
						}
					} else {
						arrColorLiveRemove[demLiveRemove] = new ItemCtrl(i,
								tr[i].id);
						demLiveRemove = demLiveRemove + 1;
					}
					for ( var j = 0; j < arrColorLiveRemove.length; j++) {
						if (arrColorLiveRemove[j].ItemId != 0) {
							var t = arrColorLiveRemove[j].Id;
							tr[t].className = "mytable_row_sel2";
						}
					}
				} else {
					for ( var p = 0; p < arrColorLiveRemove.length; p++) {
						if (arrColorLiveRemove[p].Id == i) {
							var f = arrColorLiveRemove[p].Id;
							arrColorLiveRemove[p].ItemId = 0;
							tr[f].className = "mytable_row1";
							break;
						}
					}
				}
			}
		} else {
			if (event.button == 0 || event.button == 1) {
				demLiveRemove = 0;
				arrColorLiveRemove = new Array();
				var tr = $("tableSrv").getElementsByTagName("tr");
				for ( var i = 1; i < tr.length; i++) {
					if (i % 2 == 0) {
						tr[i].className = "mytable2_row1";
					} else {
						tr[i].className = "mytable2_row2";
					}
					if (tr[i].id == id) {
						temrowRemoveId = id;
						tr[i].className = "mytable_row_sel";
					}
				}
			} else {
				demLiveRemove = 0;
			}
			// pageclick();
			break;
		}
	}
	arrColorLiveRemoveTmp = arrColorLiveRemove;
}
tableCtn.addToService = function() {
	arrColorLiveRemove = new Array();
	arrColorLiveRemoveTmp = new Array();
	arrColorLiveTmp = new Array();
	arrColorLive = new Array();
	demLiveAdd = 0;
	demLiveRemove = 0;
	var url = "Channel?CMD=9";
	url += "&subjId=" + tempIdMenu;
	url += "&str=" + temrowId;
	url += "&r=" + Math.random();
	var f = new AjaxGetText(url);
	f.complet = function(tx) {
		if (tx == "failed") {
			var al = new alertBox();
			al.show(checkAdd);
		} else {
			OnloadChannel(tempIdMenu);
			OnloadLive(tempIdMenu);
		}
	};
};
tableSrv.remove = function(id) {
	arrColorLiveRemove = new Array();
	arrColorLiveRemoveTmp = new Array();
	arrColorLiveTmp = new Array();
	arrColorLive = new Array();
	demLiveAdd = 0;
	demLiveRemove = 0;
	var url = "Channel?CMD=10";
	url += "&subjId=" + tempIdMenu;
	url += "&str=" + temrowRemoveId;
	url += "&r=" + Math.random();
	var f = new AjaxGetText(url);
	f.complet = function(tx) {
		if (tx == "failed") {
			var al = new alertBox();
			al.show(checkDelete);
		} else {
			OnloadChannel(tempIdMenu);
			OnloadLive(tempIdMenu);
		}
	};
};
function addsubject() {
	var str = "";
	for ( var i = 0; i < arrColorLiveTmp.length; i++) {
		if (arrColorLiveTmp[i].ItemId != 0) {
			if (i == arrColorLiveTmp.length - 1) {
				str += arrColorLiveTmp[i].ItemId;
			} else {
				str += arrColorLiveTmp[i].ItemId + ",";
			}
		}
	}
	if (str.length == 0) {
		str = temrowId;
	} else {
		str = str + "," + temrowId;
	}
	arrColorLiveRemove = new Array();
	arrColorLiveRemoveTmp = new Array();
	arrColorLiveTmp = new Array();
	arrColorLive = new Array();
	demLiveAdd = 0;
	demLiveRemove = 0;
	var url = "Channel?CMD=9";
	url += "&subjId=" + tempIdMenu;
	url += "&str=" + str;
	url += "&r=" + Math.random();
	var f = new AjaxGetText(url);
	f.complet = function(tx) {
		if (tx == "failed") {
			var al = new alertBox();
			al.show(checkAdd);
		} else {
			OnloadChannel(tempIdMenu);
			OnloadLive(tempIdMenu);
		}
	};
}
function removesubject() {
	var str = "";
	for ( var i = 0; i < arrColorLiveRemoveTmp.length; i++) {
		if (arrColorLiveRemoveTmp[i].ItemId != 0) {
			if (i == arrColorLiveRemoveTmp.length - 1) {
				str += arrColorLiveRemoveTmp[i].ItemId;
			} else {
				str += arrColorLiveRemoveTmp[i].ItemId + ",";
			}
		}
	}
	if (str.length == 0) {
		str = temrowRemoveId;
	} else {
		str = str + "," + temrowRemoveId;
	}
	arrColorLiveRemove = new Array();
	arrColorLiveRemoveTmp = new Array();
	arrColorLiveTmp = new Array();
	arrColorLive = new Array();
	demLiveAdd = 0;
	demLiveRemove = 0;
	var url = "Channel?CMD=10";
	url += "&subjId=" + tempIdMenu;
	url += "&str=" + str;
	url += "&r=" + Math.random();
	var f = new AjaxGetText(url);
	f.complet = function(tx) {
		if (tx == "failed") {
			var al = new alertBox();
			al.show(checkDelete);
		} else {
			OnloadChannel(tempIdMenu);
			OnloadLive(tempIdMenu);
		}
	};
}

function playEmbed(name) {
	var my = this;
	var layer = new layer_vitual();
	this.obj = null;
	this.load = function(_obj) {
		this.obj = _obj;
		my.show();
	};
	this.show = function(name) {
		layer.show();
		var embedlink = "<embed name='embedplay' id='embedplay' type='application/x-vlc-plugin' name='player' autoplay='no' ";
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
		html += "CHECK LIVETV LINK</div>";
		html += "<div align='center' >";
		html += embedlink;
		html += embedie;
		html += "</div>";
		html += "<div style='float: left;width: 100%;height:30px;' align='center'>";
		html += "<div style='width: 70;height:30px'>";
		html += "<input type=\"button\" value='" + langMain.ok
				+ " ' class='div_buton'id=\"button_ok\" >";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		layer.addHtml(html);
		// run on IE only to active vlc 15.1
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
	};
	this.accept = function() {
		my.hide();
	};
	this.hide = function() {
		document.getElementById("embedplay").setAttribute("src", "");
		$("div_layer_vitual").style.display = "none";
		document.getElementById("embedobj").removeNode(true);
	};
}

function AddChannels() {
	var html = "";
	var my = this;
	var path = "";
	this.load = function() {
		// loadChannel();
		var url = "../ServiceJSP?ID=8";
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			html = tx;
			my.show();
		};

	};
	this.show = function() {
		$("id_table2").innerHTML = html;
		$("ctn_button_ok").onclick = function() {
			my.accept();
		};
		$("ctn_button_Cancel").onclick = function() {
			my.resetAdd();
		};
		my.loadFile(path);
	};
	this.loadFile = function(p) {
		var wt = new Waiting();
		wt.show(null);
		var url = "Channel?CMD=12";
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
			li.appendChild(a);
			ul.appendChild(li);
		}
	};
	this.onclickFolder = function(me) {
		if (me == "..") {
			if (path.indexOf("/") == 0) {
				path = path.substring(1);
			}
			path = path.substring(0, path.lastIndexOf("/"));
			my.loadFile(path);
		} else {
			path += "/" + me;
			if (path.indexOf("/") == 0) {
				path = path.substring(1);
			}
			$("ctnaddfilepathtemp").value = path;
			my.loadFile(path);
		}
	};
	this.onclickFile = function(name) {
		var ar = new Array(".mov", ".mkv", ".mp4", ".avi");
		var type = name.substring(name.lastIndexOf("."));
		var t = false;
		for ( var i = 0; i < ar.length; i++) {
			if (ar[i] == type)
				t = true;
		}
		if (!t) {
			var albox = new alertBox();
			albox.show("Please select file .mov .mkv .mp4!");
		} else {
			$("fileName").innerHTML = name;
			$("ctnaddfilenametemp").value = name;
			filename = name;
		}
	};
	this.resetAdd = function() {
		$("div_channel_name").value = "";
		$("image_channel").src = "../resource/temp/noimage.gif";
		$("fileName").innerHTML = "Not Video";
		$("ctnaddfilenametemp").value = "";
	};
	this.accept = function() {
		var channelname = $("div_channel_name").value;
		for ( var j = 0; j < listChannel.length; j++) {
			if (listChannel[j].Name == channelname) {
				var albox = new alertBox();
				albox.show(checkEditEx);
				return;
			}
		}
		if (channelname == "") {
			var albox = new alertBox();
			albox.show("Channel name not empty!");
			return;
		}
		var filename = $("ctnaddfilenametemp").value;
		if (filename == "") {
			var albox = new alertBox();
			albox.show("Please, choose file video!");
			return;
		}
		var image = $("image_channel").src;
		image = image.substring(image.lastIndexOf("/") + 1);
		var wt = new Waiting();
		wt.show(null);
		var url = "Channel?CMD=4";
		url += "&t=" + Math.random();
		var param = "&channelname=" + channelname;
		param += "&filename=" + filename;
		param += "&image=" + image;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			wt.hide();
			if (tx == "success") {
				var albox = new successBox();
				albox.show("INFO: Add channel successful!");
				// loadChannel();
			} else {
				var albox = new successBox();
				albox.show("ERROR: Add channel unsuccessful!");
			}
		};
	};
}
function loadChannel() {
	var url = "Channel?CMD=13";
	url += "&r=" + Math.random();
	var f = new AjaxGetXML(url);
	f.complet = function(tx) {
		listChannel = new Array();
		var xml = tx.getElementsByTagName("xml");
		menuIdGF = xml[0].getAttribute("menuIdGF");
		var it = tx.getElementsByTagName("Item");
		for ( var i = 0; i < it.length; i++) {
			var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
			var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
			var _parent = it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
			var _urlIma = it[i].getElementsByTagName("urlIma")[0].childNodes[0].nodeValue;
			listChannel[i] = new ItemMenu(_id, _name, _parent, _urlIma);
		}
		document.getElementById("list_channel").innerHTML = ShowHTMLMenu(listChannel);
		renderEventChannel();
	};
}
function renderEventChannel() {
	var ul = $("list_channel").getElementsByTagName("ul");
	var li = ul[0].getElementsByTagName("li");
	var me = this;
	for ( var i = 0; i < li.length; i++) {
		var a = li[i].getElementsByTagName("a");
		var item = a[0];
		item.oncontextmenu = function(e) {
			for ( var j = 0; j < listChannel.length; j++) {
				if (listChannel[j].Id == this.id) {
					channelurl = listChannel[j].Parent;
					break;
				}
			}
			temIdmenuGroup = this.id;
			// loadChannelInfor(temIdmenuGroup, 0);
			loadAvertise();
			ClickItemChannel(this.id);
			ctChannel.load(this.id, e);
			return false;
		};
	}
}
function ClickItemChannel(id) {
	var ul = $("list_channel").getElementsByTagName("ul");
	var li = ul[0].getElementsByTagName("li");
	for ( var i = 0; i < li.length; i++) {
		var a = li[i].getElementsByTagName("a");
		if (a[0].id == id) {
			a[0].style.color = "#ab8718";
		} else {
			a[0].style.color = "#878888";
		}
	}
}

function contextChannel() {
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
		// html += "<li class='icon_contextmenuAdd'>";
		// html += "<a>" + NewSubject + "</a>";
		// html += "</li>";
		html += "<li class='icon_contextmenuDel'>";
		html += "<a>Delete Channel</a>";
		html += "</li>";
		html += "<li class='icon_contextmenuEdit'>";
		html += "<a>Info Channel</a>";
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
		// var itemnew = li[0].getElementsByTagName("a")[0];
		// itemnew.onclick = function() {
		// my.fucNew();
		// };
		var itemdel = li[0].getElementsByTagName("a")[0];
		itemdel.onclick = function() {
			my.fucdel();
		};
		var itemedit = li[1].getElementsByTagName("a")[0];
		itemedit.onclick = function() {
			my.fucedit();
		};
	};
	// this.fucNew = function() {
	// closeMenu();
	// showfromAddMenu();
	// };
	this.fucdel = function() {
		deleteChannel(temIdmenuGroup, channelurl);
		closeMenu();
	};
	this.fucedit = function() {
		showfromUpdateChannel();
		closeMenu();
	};
}
function deleteChannel(channelID, channelurl) {
	closeMenu(0);
	var cfBox = new conformBox();
	cfBox.show(checkDeleteFrom, ok, cancel);
	cfBox.accept = function() {
		var wt = new Waiting();
		wt.show(null);
		var url = "Channel?CMD=14";
		url += "&channelId=" + channelID;
		url += "&url=" + channelurl;
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			wt.hide();
			if (tx == "failed") {
				var albox = new alertBox();
				albox.show(checkDelete);
			} else {
				loadChannel();
			}
		};
	};
}
function showfromUpdateChannel() {
	var name = "";
	var image = null;
	for ( var i = 0; i < listChannel.length; i++) {
		if (listChannel[i].Id == temIdmenuGroup) {
			name = listChannel[i].Name;
			image = listChannel[i].UrlIma;
			break;
		}
	}
	var textbox = new newBox("Info Channel", name, image, _titleName, _Image,
			ok, cancel);
	textbox.show();
	textbox.accept = function(text, image) {
		image = image.substring(image.lastIndexOf("/") + 1);
		if (text.length <= 0) {
			var albox = new alertBox();
			albox.show(subjectName);
			return;
		}
		if (text.length > 29) {
			var albox = new alertBox();
			albox.show(subjectLength);
			return;
		}
		for ( var j = 0; j < listChannel.length; j++) {
			if (listChannel[j].Name == text
					&& listChannel[j].Id != temIdmenuGroup) {
				var albox = new alertBox();
				albox.show(checkEditEx);
				return;
			}
		}

		var url = "Channel?CMD=5";
		url += "&t=" + Math.random();
		url += "&image=" + image;
		url += "&subid=" + temIdmenuGroup;
		var param = "&name=" + encode(text);
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			// wt.hide();
			if (tx == "failed") {
				var albox = new alertBox();
				albox.show(checkUpdate);
			} else if (tx == "f") {
				var albox = new alertBox();
				albox.show(checkEditEx);
			} else {
				// loadChannel();
				textbox.hide();
			}
		};
	};
}
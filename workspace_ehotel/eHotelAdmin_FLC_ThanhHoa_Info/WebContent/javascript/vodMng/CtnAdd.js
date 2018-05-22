function ContentAdd() {
	var html = "";
	var my = this;
	var path = "";
	this.subId = -1;
	var filename = "";
	this.obj = null;
	var list;
	var ipserver = "";
	this.paths = "";
	var interval = null;
	this.load = function(_obj) {
		my.obj = _obj;
		var url = "../ServiceJSP?ID=6";
		url += "&VodId=" + my.VodId;
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			html = tx;
			my.show();
		};
	};
	this.loadSub = function(obj) {

		$("div_subjectname").innerHTML = obj.Name;
		my.subId = obj.Id;
	};
	var value = "";
	this.show = function() {
		var wt = new Waiting();
		wt.show(null);
		$("id_table").innerHTML = html;
		my.loadFile(path);
		$("div_subjectname").innerHTML = my.obj.Name;
		my.subId = my.obj.Id;
		$("im_loadInfoFilm").onclick = function() {
			my.loadInfo();
		};
		$("ctn_button_ok").onclick = function() {
			my.accept();
		};
		$("ctn_button_Cancel").onclick = my.resetAdd;
		initTinymce(2, "540px");
		clearInterval(interval);
		var url = "VodCtnMain?CMD=16";
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			if (tx != "") {
				value = tx;
				if (value.length > 10) {
					interval = setInterval(function() {
						my.showprocess(value);
					}, 1000);
				}
			} else {
				process = 0;
				$("div_process").innerHTML = "No movie upload.";
			}
		};
		wt.hide();
	};
	this.loadFile = function(p) {
		var wt = new Waiting();
		wt.show(null);
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
			;
			li.appendChild(a);
			ul.appendChild(li);
		}
	}
	this.onclickFolder = function(me) {
		if (me == "..") {
			if (path.indexOf("/") == 0) {
				path = path.substring(1);
			}
			;
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
	}
	this.onclickFile = function(name) {
		var ar = new Array(".ts", ".mkv", ".png", ".mp4", ".avi");
		var type = name.substring(name.lastIndexOf("."));
		var t = false;
		for ( var i = 0; i < ar.length; i++) {
			if (ar[i] == type)
				t = true;
		}
		if (!t) {
			var albox = new alertBox();
			albox.show("Please select file .ts .mkv .png .mp4!");
		} else {
			$("fileName").innerHTML = name;
			$("ctnaddfilenametemp").value = name;
			filename = name;
		}
	}
	this.loadInfo = function() {
		var wt = new Waiting();
		var element = document.getElementById("fileName");
		var title1 = element.textContent || element.innerText;
		var title = title1.substring(0, title1.indexOf("."));
		if (title.length == 0) {
			return;
		}
		title = URLEncode(title);
		var url = "VodCtnMain?CMD=12";
		url += "&title=" + title;
		url += "&r=" + Math.random();
		wt.show(3000);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			if(tx != "null"){
				var xml = tx.getElementsByTagName("xml");
				var _name = xml[0].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _actor = xml[0].getElementsByTagName("Actors")[0].childNodes[0].nodeValue;
				var _director = xml[0].getElementsByTagName("Director")[0].childNodes[0].nodeValue;
				var _image = xml[0].getElementsByTagName("Image")[0].childNodes[0].nodeValue;
				var _des = xml[0].getElementsByTagName("Descript")[0].childNodes[0].nodeValue;
				$("div_title").value = _name;
				$("div_Director").value = _director;
				$("div_actors").value = _actor;
				$("image1").src = linksaveimage + _image;
			}
			// "../resource/temp/"+_image
			// rte.writeRichText('rte1',"divrte2", _des, 665, 120, true, false);
			wt.hide();
		}
	}
	// submit form Add movide 16.1
	this.accept = function() {

		if (process == 10) {
			var albox = new alertBox();
			albox.show("Process is full!");
			return;
		}
		var wt = new Waiting();
		var name = $("div_title").value;
		var director = $("div_Director").value;
		var actor = $("div_actors").value;
		var image = $("image1").src
		var Desc = getDataFromEditor("descriptionInput");
		image = image.substring(image.lastIndexOf("/") + 1);
		var price = $("div_price").value;
		var select = $("div_money");
		var money = select.options[select.selectedIndex].value;
		filename = $("ctnaddfilenametemp").value;
		path = $("ctnaddfilepathtemp").value;
		var file = filename;
		if (path.length >= 0) {
			file = path + "/" + filename;
		}
		if (file.length == 0) {
			var albox = new alertBox();
			albox.show("Please input file!");
			return;
		}
		if (name.length == 0) {
			var albox = new alertBox();
			albox.show("Please input Title!");
			return;
		}
		var param = "&name=" + name;
		param += "&director=" + director;
		param += "&actor=" + actor;
		param += "&image=" + image;
		param += "&Desc=" + Desc;
		param += "&file=" + file;
		param += "&price=" + price;
		param += "&money=" + money;
		var url = "VodCtnMain?CMD=5";
		url += "&t=" + Math.random();
		url += "&SubId=" + my.subId;
		wt.show(3000);
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			if(tx == "false"){
				 var albox = new successBox();
				 albox.show("Add movie error! Please, check the file name of movie!");
			}
			clearInterval(interval);
			var ur = "VodCtnMain?CMD=16";
			ur += "&t=" + Math.random();
			var f = new AjaxGetText(ur);
			f.complet = function(tx) {
				if (tx != "") {
					value = tx;
					interval = setInterval(function() {
						my.showprocess(value);
					}, 1000);
				} else {
					process = 0;
					$("div_process").innerHTML = "No movie upload.";
				}
			};
		};
	};
	var process = 0;
	this.showprocess = function(value) {
		var url = "VodCtnMain?CMD=13";
		url += "&value=" + value;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			// neu khong co tap tin nao trong cay thu muc thi se bao loi 14.1
			if (tx != "") {
				var it = tx.getElementsByTagName("Item");
				if (it.length > 0) {
					var arr = new Array();
					for ( var i = 0; i < it.length; i++) {
						var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
						var _percent = it[i].getElementsByTagName("percent")[0].childNodes[0].nodeValue;
						var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
						var _filename = it[i].getElementsByTagName("filename")[0].childNodes[0].nodeValue;
						var item = new itemPer(_id, _name, _percent, _filename);
						arr[i] = item;
					}
					my.loadPercent(arr);
					process = arr.length;
					// setTimeout(function() {
					// my.showprocess(value);
					// }, 1000);
				} else {
					process = 0;
					$("div_process").innerHTML = "No movie upload.";
				}
			} else {
				process = 0;
				$("div_process").innerHTML = "No movie upload.";
			}
		}
	}
	this.loadPercent = function(list) {
		var html = "<table style=\"width: 95%\"  class=\"tableSubtitle\">";
		html += "<tr>";
		html += "<th>";
		html += "NO";
		html += "</th>";
		html += "<th width=\"60%\" align=\"left\">";
		html += "Name";
		html += "</th>";
		html += "<th>";
		html += "Percent";
		html += "</th>";
		html += "<th>";
		html += "Delete";
		html += "</th>";
		html += "</tr>";
		for ( var i = 0; i < list.length; i++) {
			if (parseFloat(list[i].Per) == 100) {
				clearInterval(interval);
				var ur = "VodCtnMain?CMD=16";
				ur += "&t=" + Math.random();
				var f = new AjaxGetText(ur);
				f.complet = function(tx) {
					value = tx;
					interval = setInterval(function() {
						my.showprocess(value);
					}, 1000);
				};
			}
			var name = list[i].Name;
			if (name.length > 50) {
				name = name.substring(0, 50) + "...";
			}
			html += "<tr>";
			html += "<td align=\"center\">";
			html += (i + 1);
			html += "</td>";
			html += "<td>";
			// html += list[i].Name + "(" + list[i].Id + ")";
			html += list[i].Name;
			html += "</td>";
			html += "<td align=\"center\">";
			html += list[i].Per + " %";
			html += "</td>";
			html += "<td align=\"center\">";
			html += "<img src='../icon/001_02.png' onclick=deleteMovieUpload("+ "'" + list[i].Id + "'" + "," + "'" + list[i].Filename + "'" + "," + "'" + encodeURIComponent(list[i].Name) + "'" + ")>";
			html += "</td>";
			html += "</tr>";
			if(parseFloat(list[i].Per) > 99.9){
				var url = "VodCtnMain?CMD=18";
				url += "&filename=" + list[i].Filename;
				var f = new AjaxGetText(url);
				f.complet = function(tx) {
					
				}
			}
			
		}
		html += "</table>";
		$("div_process").innerHTML = html;
	}
	this.resetAdd = function() {
		$("div_title").value = "";
		$("div_Director").value = "";
		$("div_actors").value = "";
		$("image1").src = "../resource/temp/noimage.gif"
		// rte.writeRichText('rte1',"divrte2", "", 665, 120, true, false);
		$("fileName").innerHTML = "Not set ";
		filename = "";
	}
	function itemPer(id, name, per, filename) {
		this.Name = name;
		this.Id = id;
		this.Per = per;
		this.Filename = filename;
	}
}
function deleteMovieUpload(uuid, filename, name) {
	name = decodeURIComponent(name);
	// alert(uuid + " - " + filename);
	var cfBox = new conformBox();
	cfBox.show("Are you sure stop transfer " + name);
	cfBox.accept = function() {
		var url = "VodCtnMain?CMD=14";
		url += "&uuid=" + uuid;
		url += "&filename=" + filename;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			var albox = new successBox();
			albox.show(tx);
			var vod = new  VodContent();
			vod.changeTab(1);
//			var ctadd = new ContentAdd();
//			ctadd.show();
		};
	};
}

function loadFTPServer() {
	var host = $("ctaddhost").value;
	var port = $("ctaddport").value;
	var username = $("ctaddusername").value;
	var password = $("ctaddpassword").value;

	$("hosttemp").value = host;
	$("porttemp").value = port;
	$("usertemp").value = username;
	$("passtemp").value = password;

	var ctadd = new ContentAdd();
	ctadd._host = host;
	ctadd._port = port;
	ctadd._username = username;
	ctadd._password = password;
	ctadd.loadFile("");
}

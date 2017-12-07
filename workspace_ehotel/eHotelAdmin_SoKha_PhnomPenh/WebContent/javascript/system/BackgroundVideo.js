function BgVideo()
{	
	var my = this;
	var path = "";
	var list=new Listservice();
	this.run=function()
	{
//		alert("vo day roi");
//		$("table_id_table").remove();
//		list.load();
		my.load();
	};
	this.onclick=function()
	{
		list.pageclick();
	};
	this.addItem=function()
	{
		list.addeImage();
	};
	this.load = function(){
		var url = "../ServiceJSP?ID=9";
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			html = tx;
			my.show();
		};
	};
	this.show = function(){
		$("id_table_bg_video").innerHTML = html;
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
//		var ar = new Array(".mov", ".mkv", ".mp4", ".avi");
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
			$("fileName").innerHTML = name;
			$("ctnaddfilenametemp").value = name;
			filename = name;
		}
	};
	this.resetAdd = function() {
		$("fileName").innerHTML = "No Media";
		$("ctnaddfilenametemp").value="";
	};
	this.accept = function(){
		var filename = $("ctnaddfilenametemp").value;
		if(filename == ""){
			var albox = new alertBox();
			 albox.show("ERROR: Please, select the media file ");
			 return false;
		}
		var url = "Background?CMD=4";
		url += "&t=" + Math.random();
		var param = "&filename=" + filename;
		param += "&path=" + path;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			if(tx == "success"){
				var albox = new successBox();
				 albox.show("INFO<br/><br/>Add music welcome successful!");
			}
			else{
				var albox = new alertBox();
				 albox.show("ERROR<br/><br/>Add music welcome unsuccessful!");
			}
		};
	};
};

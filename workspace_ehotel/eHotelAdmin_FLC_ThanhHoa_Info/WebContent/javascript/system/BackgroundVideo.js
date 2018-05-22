function BgVideo()
{	
	var my = this;
	var path = "";
	var list=new ListMedia();
	this.run=function()
	{
//		alert("vo day roi");
//		$("table_id_table").remove();
		list.load();
//		my.load();
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
		var url = "../ServiceJSP?ID=7";
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
				 albox.show("INFO<br/><br/>Add video successful!");
			}
			else{
				var albox = new alertBox();
				 albox.show("ERROR<br/><br/>Add video unsuccessful!");
			}
		};
	};
	this.getListMedia = function(){
		var url = "Background?CMD=6";
		url += "&path=" + p;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _filename = it[i].getElementsByTagName("filename")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				var _index = it[i].getElementsByTagName("index")[0].childNodes[0].nodeValue;
				var _type = it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
				var item = new itemWelcomMedia(_id, _name, _filename, _status,
						_index, _type);
				arr[i] = item;
			}
			my.bindData(arr);
			wt.hide();
		};
	};
	function itemWelcomMedia(id, name, filename, status, index, type) {
		this.Id = id;
		this.Name = name;
		this.FileName = filename;
		this.Status = status;
		this.index = index;
		this.type = type;
	}
};

function ListMedia(){
	var Index=0;
	var table=new Table();
	var ctTable=new contextTable();
	ctTable.fucDetail=function()
	{
		var detail=new DetailPromotion();
		detail.load(this.obj.Id);
		detail.update=function()
		{
			my.reload();
		};
	};	
	ctTable.fucdel=function()
	{
		var list="";
		if(my.ListCheck.length==0)
		{
			list+="&id0="+this.obj;
		}else
		{
			for(var i=0;i<my.ListCheck.size();i++)
			{
				list+="&id"+i+"="+my.ListCheck.get(i);
			}
		}
		var cfBox=new conformBox();
		cfBox.show();
		cfBox.accept=function()
		{				
			var url="ServicePromotion?CMD=3";
				url+=list;
				url+="&SubId="+my.SubId;
				url+="&r="+Math.random();	
				var f=new AjaxGetXML(url);					
				f.complet=function(tx)
				{
					my.reload();					
				};
		};
	};
	ctTable.fucAdd=function()
	{		
		var add=new addItemPromotion();
		add.load(my.SubId);
		add.update=function()
		{
			my.reload();
		};
	};
	this.addeImage=function()
	{
		var add=new addItemAttaction();
		add.load(my.SubId);
		add.update=function()
		{
			my.reload();
		};
	};
	ctTable.fucStatus=function()
	{
		var url="ServicePromotion?CMD=4";
			url+="&id="+this.obj.Id;
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);					
			f.complet=function(tx)
			{
				my.reload();
			};
	};
	this.pageclick=function()
	{
		if(ctTable.state) ctTable.hide();
	};
	var my=this;
	this.count=0;
	var ID=0;//id of subject
	this.SubId=-1;	
	this.ListCheck=new Vector();
	var arr=new Array();
	var defaults =
	{
		divName:		"id_table",
		col:  			5,
		classHeader:    "mytable_header",
		classItem: 		"classItem",
		classTable:		"classTable",
		classRowsel:		"mytable2_row_sel",
		classTable:		"mytable",
		bgRow1:		"mytable2_row1",
		bgRow2:		"mytable2_row2"
	};	
	/**
	 * @param id id subject
	 * @return
	 */
	this.load=function()
	{		
		Index=0;
		table.init(defaults);
		my.get(Index);			
	};
	this.reload=function()
	{				
		Index=0;
		my.get(ID,Index);	
	};
	this.loadIdex=function(index)
	{		
		Index=index;
		my.get(ID,index);		
	};
	this.nextindex=function()
	{	
		var page=5;
		var cpage=((my.count-1)/page)-1;
		if(Index<cpage)
				Index++;
		my.loadIdex(Index);
	};
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIdex(Index);
	};
	this.renderEvent=function()
	{		
		var tr=$(table.divName).getElementsByTagName("tr");		
		for(var i=1;i<tr.length;i++)
		{
			var item=tr[i];			
			item.onclick=function(e)
			{						
				my.selectItem(this.id);
			};
			item.oncontextmenu=function(e)
			{					
				if(Common.ctrl)
				{
					my.addItem(this.id);
				}else
				{
					my.selectItem(this.id);
				}
				my.oncontextmenu(this.id,e);
				return false;
			};
		};
		/**
		 * render paging
		 */
		if($("div_page")!=undefined)
		{
			var div=$("div_page");
			var a=div.getElementsByTagName("a");
			for(var i=0;i<a.length;i++)
			{
				var item=a[i];				
				item.onclick=function(e){
					my.loadIdex(this.id);					
				};
			}
			$("div_page_icon_right").onclick=function(){my.nextindex();};
			$("div_page_icon_left").onclick=function(){my.backindex();};
		}
		
	};
	this.selectItem=function(id)
	{		
		if(Common.ctrl)
		{
			if(!this.ListCheck.isObject(id))
			{
				this.ListCheck.add(id);
			}else
			{
				this.ListCheck.removeObj(id);
			}			
		}else
		{
			my.ListCheck=new Vector();
			my.ListCheck.add(id);
		}		
		my.shiftSelect();
	};
	this.addItem=function(id)
	{		
		if(!this.ListCheck.isObject(id))
		{
			this.ListCheck.add(id);
		}
		my.shiftSelect();
	};
	this.oncontextmenu=function(id,e)
	{			
		var obj=null;
		for(var i=0;i<arr.length;i++)
		{
			if(arr[i].Id==id)
			{
				obj=arr[i];
				break;
			}
		}		
		ctTable.load(obj, e);
	};
	table.dataBind = function() {
		var html = "";
		html += "<div class='div_formother1'>";
		html += "<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"97%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left'  valign=\"middle\"  width=\"" + 10
				+ "%\"  class='header0' >";
		html += "No";
		html += "</th>";
		html += "<th align='left' width=\"" + 35 + "%\" valign=\"middle\"  >";
		html += "Name";
		html += "</th>";
		html += "<th align='center' width=\"" + 35 + "%\" valign=\"middle\"  >";
		html += "FileName";
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += "Index";
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" valign=\"middle\"  >";
		html += "Status";
		html += "</th>";
		html += "</tr>";
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\"  >";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\"   >";
			}
			html += "<td align=\"left\"  valign=\"middle\">";
			html += convertToHTML(this.List[i].Name);
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].FileName;
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			html += this.List[i].Index;
			html += "</td>";
			html += "<td align=\"center\"  valign=\"middle\">";
			if (this.List[i].Status == 1) {
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
			html += "<div align='center'>";
			// html+="<a href='javascript:acti.list.addeImage()'
			// class='div_addeImage' title='Click here add Image'>Add
			// Image</a>";
			html += "<a href='javascript:addImageTemp()' class='div_addeImage' title='Click here add Image'>Add Image</a>";
			html += "</div>";
		}
		// if(my.count>5)
		// {
		// var page=my.count/5;
		// html+="<div id=\"div_page\" class='div_page'>";
		// html+="<div class='div_page_icon_left' id='div_page_icon_left'>";
		// html+="</div>";
		// for(var i=0;i<page;i++)
		// {
		// if(i==Index)
		// {
		// html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+i+"\">";
		// }else
		// {
		// html+="<a class='div_page_a' href=\"javascript:\" id=\""+i+"\">";
		// }
		// html+=(i+1);
		// html+=" </a>";
		// }
		//			html+="<div class='div_page_icon_right' id='div_page_icon_right'>";
		//			html+="</div>";
		//			html+="</div>";
		//		}		
		return html;
	};
	this.shiftSelect=function()
	{		
		var tr=$(table.divName).getElementsByTagName("tr");
		for(var i=1;i<tr.length;i++)
		{
			if(i%2==0)
			{
				tr[i].className=table.bgRow1;
			}else
			{
				tr[i].className=table.bgRow2;
			}	
			
			for(var j=0;j<my.ListCheck.size();j++)
			{
				if(tr[i].id==my.ListCheck.get(j))
				{
					tr[i].className=table.classRowsel;
					break;
				}
			}		
		}			
	};
	this.get = function(id) {
		var url = "Background?CMD=6";
		url += "&type=VIDEO";
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			var arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _filename = it[i].getElementsByTagName("filename")[0].childNodes[0].nodeValue;
				var _status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
				var _index = it[i].getElementsByTagName("index")[0].childNodes[0].nodeValue;
				var _type = it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
				var item = new itemWelcomMedia(_id, _name, _filename, _status,
						_index, _type);
				arr[i] = item;
			}
			table.load(arr);
			table.classCol("classItem1", 1);
			table.classCol("classItem3", 0);
			my.renderEvent();
		};
	};
	this.loadcomplet=function()
	{
		//$("title_subject").innerHTML=my.subname+"("+my.count+")";
	};
}

function itemWelcomMedia(id, name, filename, status, index, type) {
	this.Id = id;
	this.Name = name;
	this.FileName = filename;
	this.Status = status;
	this.index = index;
	this.type = type;
}

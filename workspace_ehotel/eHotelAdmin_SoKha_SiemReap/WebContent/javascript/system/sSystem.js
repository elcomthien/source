function SceSytem()
{
	var my=this;
	//khoi tao doi tuong lay list service 22.1
	var list=new Listservice();
	this.run=function()
	{
		list.load();		
	};
	this.onclick=function()
	{
		list.pageclick();
	};
	this.addItem=function()
	{
		list.addeImage();
	};
};
//ham lay list service 22.1
function Listservice()
{
	var Index=0;
	var table=new Table();
	var ctTable=new contextTable();
	//thay doi apk
	ctTable.fucDetail=function(name)
	{
		//khai bao chi tiet tung service 22.1
		var detail=new DetailApkService();
		detail.load(this.obj.Id,name);
		detail.update=function()
		{
			my.reload();
		};
	};
	ctTable.fucdel=function()
	{
		//khai bao chi tiet tung service 22.1
		var detail=new DetailService();
		detail.load(this.obj.Id);
		detail.update=function()
		{
			my.reload();
		};
	};
	//thay doi order 24.1
	ctTable.fucOrder=function()
	{		
		var add=new updateOrder();
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
		add.update=function() {
			my.reload();
		};
	};
	//thay doi status cua service hien tai 22.1
	ctTable.fucStatus=function()
	{
		var url="ServiceSystem?CMD=4";
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
	var defaults = {
		divName		: "id_table",
		col			: 5,
		classHeader	: "mytable_header",
		classItem	: "classItem",
		classTable	: "classTable",
		classRowsel	: "mytable2_row_sel",
		classTable	: "mytable",
		bgRow1		: "mytable2_row1",
		bgRow2		: "mytable2_row2"
	};
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
	//bat su kien phai chuot goi contextmenu 22.1
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
	this.oncontextmenu=function(id,e,name)
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
		ctTable.load(obj, e,name);
	};
	/*render du lieu ra html 22.1, tab ServiceSystem*/
	table.dataBind=function()
	{				
		var html="";
		html+="<div class='div_formother1' style='overflow-y:scroll;margin-top:0px;'>";
		html+="<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"97%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\" style='margin-top:10px;'>";
		html+="<tr class=\""+this.classHeader+"\">";		
		html+="<th align='left' valign=\"middle\"  width=\""+70+"%\" class='header0' >";
		html+=langpms.name;
		html+="</th>";		
//		html+="<th align='left' width=\""+45+"%\" valign=\"middle\">";
//		html+=langMain.maxuly;
//		html+="</th>";	
		html+="<th align='center' width=\""+20+"%\" valign=\"middle\">";
		html+=langpms.image;
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\">";
		html+=langpms.status;
		html+="</th>";
		html+="</tr>";
		for(var i=0;i<this.List.length;i++)
		{			
			var promotionlink = linksaveimage + this.List[i].Image;
				if(i%2==0)
				{				
					html+="<tr rel="+this.List[i].Name+" class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\">";
				}else
				{
					html+="<tr rel="+this.List[i].Name+" class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\">";
				}					
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=convertToHTML(this.List[i].Name);
				html+="</td>";				
//				html+="<td align=\"left\" valign=\"middle\">";
//				html+=this.List[i].Action;
//				html+="</td>";
				html+="<td align=\"center\" valign=\"middle\">";
				html+="<img class='im_ontable' src="+promotionlink+"></img>";
				html+="</td>";
				html+="<td align=\"center\" valign=\"middle\">";
//				alert(this.List[i].Name+this.List[i].Status);
				if(this.List[i].Status==0)
				{
					html+="<img src=\"../icon/16-square-green.png\"></img>";
				}else
				{
					html+="<img src=\"../icon/16-square-red.png\"></img>";
				}
				html+="</td>";					
				html+="</tr>";
		}
		html+="</table>";
		html+="</div>";
		if(this.List.length==0)
		{
			html+="<div align='center'>";
			html+="<a href='javascript:acti.list.addeImage()' class='div_addeImage' title='Click here add Image'>Add Image</a>";
			html+="</div>";
		}
		//loai bo paging 22.1
		
//		if(my.count>8)
//		{
//			var page=my.count/5;
//			html+="<div id=\"div_page\" class='div_page'>";
//			html+="<div class='div_page_icon_left' id='div_page_icon_left'>";
//			html+="</div>";
//			for(var i=0;i<page;i++)
//			{
//				if(i==Index)
//				{
//					html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+i+"\">";
//				}else
//				{
//					html+="<a class='div_page_a' href=\"javascript:\" id=\""+i+"\">";
//				}
//				html+=(i+1);
//				html+=" </a>";				
//			}			
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
	//goi phuong thuc GET CMD=1 de lay du lieu tu ham getServices tu server 22.1
	this.get=function(id)
	{		
		var url="ServiceSystem?CMD=1";
			url+="&lang="+language;
			url+="&r="+Math.random();			
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{		
				arr=new Array();
				var xml=tx.getElementsByTagName("xml");					
				my.count= xml[0].getAttribute("count");					
				var it=tx.getElementsByTagName("Item");
				for(var i=0;i<it.length;i++)
				{					
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _action=it[i].getElementsByTagName("action")[0].childNodes[0].nodeValue;					
					var _status=it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
					var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
					arr[i]=new ItemHotel(_id,_name,_action,_status);
					arr[i].Image=_image;	
				}	
				table.load(arr);
				table.classCol("classItem1",1);
				table.classCol("classItem3",0);			
				my.renderEvent();		
			};		
	};
	this.loadcomplet=function()
	{
	};
}
function contextTable()
{
	var my=this;
	this.state=false;
	this.obj=null;
	this.load=function(_obj,e)
	{
		this.obj=_obj;
		var posx = 0;
		var posy = 0;
		if (!e) var e = window.event;
		if (e.pageX || e.pageY) 
		{
			posx = e.pageX;
			posy = e.pageY;
		}else if (e.clientX || e.clientY) 	{
			posx = e.clientX + document.body.scrollLeft
				+ document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
				+ document.documentElement.scrollTop;
		}
		my.draw(posx,posy-10,this.obj.Name);
	};
	//context menu: thu tu la Update order, Update apk value, Update status, Update service
	this.draw=function(x,y,name)
	{		
		my.state=true;
		var html="";
		html+="<ul>";
			html+="<li class='icon_contextmenuEdit'>";
			html+="<a>"+langMain.updateorder+"</a>";
			html+="</li>";
			//html+="<li class='icon_contextmenuplay'>";
			//html+="<a rel='"+name+"'>"+langMain.updateapkvalue+"</a>";
			//html+="</li>";
			html+="<li class='icon_contextmenuDel'>";
			html+="<a>"+langMain.updateservice+"</a>";
			html+="</li>";			
			if(my.obj.Status==1)
			{
				html+="<li class='icon_visibility1'>";
			}else
			{
				html+="<li class='icon_visibility2'>";
			}			
			html+="<a>"+langMain.updatestatus+"</a>";
			html+="</li>";
		html+="</ul>";		
		if($("div_contextMenu")==undefined)
		{			
			var div_=document.createElement("div");			
			div_.id="div_contextMenu";			
			div_.className="div_contextMenu";	
			div_.innerHTML=html;
			$("main").appendChild(div_);			
		}else
		{
			$("div_contextMenu").className="div_contextMenu";
			$("div_contextMenu").innerHTML=html;
		}
		$("div_contextMenu").style.left=x+"px";
		$("div_contextMenu").style.top= y+"px";
		$("div_contextMenu").style.display= "block";
		my.renderClick();
		eventMouse();
	};
	this.renderClick=function()
	{	
		var ul=$("div_contextMenu").getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		var fucOrder=li[0].getElementsByTagName("a")[0];
		fucOrder.onclick=function(){my.fucOrder();};
		//var fucDetail=li[1].getElementsByTagName("a")[0];
		//var apkName=fucDetail.getAttribute("rel");
		//fucDetail.onclick=function(){my.fucDetail(apkName);};
		var fucdel=li[1].getElementsByTagName("a")[0];
		fucdel.onclick=function(){my.fucdel();};
		var itemstatus=li[2].getElementsByTagName("a")[0];
		itemstatus.onclick=function(){my.fucStatus();};
	};
	this.hide=function()
	{
		this.state=false;
		$("div_contextMenu").style.display= "none";
	};
}

function getChar (event,inp){
	//remove focus khi nhan cac phim CTRL,SHIFT,ALT
	if (event.keyCode ==16 || event.keyCode ==17 || event.keyCode ==18) {
		inp.blur();
		return false;
	}
	else {
		if ( (event.keyCode >=48 && event.keyCode <=57) || (event.keyCode >=96 && event.keyCode <=105) ) {
			//do nothing
		}
		else {
			event.preventDefault();
		}
	}
}
function updateOrder()
{
	var my=this;	
	var layer=new layer_vitual();	
	this.load=function()
	{			
		my.show();
	};
	this.show=function()
	{	
		layer.show();
		//lay lai danh sach service 24.1
		var url="ServiceSystem?CMD=5";
			url+="&id=-1";
			url+="&r="+Math.random();		
			var f=new AjaxGetText(url);
			f.complet=function(tx)
			{			
				layer.addHtml(tx);			
				setCenterDIV("form_detail_order");
				$("form_textbox_ok").onclick=function() { my.accept();};
				$("form_textbox_cancel").onclick=my.cancel;
			};
	};
	this.accept=function()
	{	
		//chuan bi du lieu cho name 24.1
		var idresult = "";
		var ids = document.getElementsByName("idS");
		for(var i = 0; i < ids.length; i++)
		{
			idresult += ids[i].value + ',';
		}
		idresult = idresult.substring(0, idresult.length-1);
		idresult = "(" + idresult + ")";
		//chuan bi du lieu cho order 24.1
		var orderresult = "";
		var orders = document.getElementsByName("orderS");
		for(var i = 0; i < orders.length; i++)
		{
			if (orders[i].value == '') orders[i].value = 1;
			orderresult += orders[i].value + ',';
		}
		orderresult = orderresult.substring(0, orderresult.length-1);
		orderresult = "(" + orderresult + ")";
		//post du lieu
		var url="ServiceSystem?CMD=4";
			url+="&t="+Math.random();
			var param="&id="+idresult;
			param+="&order="+orderresult;			
			var f=new AjaxPostText(url,param);			
			f.complet=function(tx)
			{
				my.hide();
				my.update();
			};
	};
	this.update=function()
	{
	};
	this.cancel=function()
	{
		my.hide();
	};
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	};
}
//context menu thu 2, update apk 22.1
function DetailApkService()
{
	var my=this;	
	var layer=new layer_vitual();	
	this.Id=-1;
	this.load=function(id,name)
	{			
		my.Id=id;
		my.show(id,name);
	};
	this.show=function(id,name)
	{			
		layer.show();			
		var url="ServiceSystem?CMD=2";
		url+="&id="+id;
		url+="&name="+name;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{			
			layer.addHtml(tx);
			//goi div voi id ben duoi (detailApk.jsp)
			setCenterDIV("form_detail_apk");
			$("form_textbox_ok").onclick=function() { my.accept(); };
			$("form_textbox_cancel").onclick=my.cancel;				
		};
	};
	//nhan nut OK tren dialog APK detail
	this.accept=function()
	{			
		var name=$("name").value;
		name=encode(name);
		var apkvalue=$("apkvalue").value;
		apkvalue=encode(apkvalue);
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show(langpms.pls_name);
			return;
		};
		var url="ServiceSystem?CMD=2";
			url+="&t="+Math.random();
			var param="&name="+name;
			param+="&id="+my.Id;
			param+="&apkvalue="+apkvalue;
			var f=new AjaxPostText(url,param);			
			f.complet=function(tx)
			{
				my.hide();
				my.update();
			};
	};
	this.update=function()
	{
	};
	this.cancel=function()
	{
		my.hide();
	};
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	};
}
//update service dong 3 trong menu context 23.1
function DetailService()
{
	var my=this;	
	var layer=new layer_vitual();	
	this.Id=-1;
	this.load=function(id) {			
		my.Id=id;
		my.show(id);
	};
	this.show=function(id) {			
		layer.show();			
		var url="ServiceSystem?CMD=3";
		url+="&id="+id;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{			
			layer.addHtml(tx);
			//goi div voi id ben duoi (detailServiceSystem.jsp) 23.1
			setCenterDIV("form_detail_servicesystem");
			$("form_textbox_ok").onclick=function() { my.accept(); };
			$("form_textbox_cancel").onclick=my.cancel;				
		};
	};
	this.accept=function() {			
		var name=$("name").value;
		name=encode(name);
		var image=$("image").src;
		image=image.substring(image.lastIndexOf("/")+1);
		if(name.length==0) {
			var albox=new alertBox();
			albox.show(langpms.pls_name);
			return;
		};
		var url="ServiceSystem?CMD=3";
			url+="&t="+Math.random();
			var param="&name="+name;
			param+="&id="+my.Id;
			param+="&image="+image;
			param+="&lang="+language;
			var f=new AjaxPostText(url,param);			
			f.complet=function(tx) {
				my.hide();
				my.update();
			};
	};
	this.update=function() {
	};
	this.cancel=function() {
		my.hide();
	};
	this.hide=function() {
		$("div_layer_vitual").style.display="none";
	};
}
var dinning=new function Dinning()
{
	var my=this;
	this.sub=new subject();
	var room=new RoomService();
	var restaurant=new Restaurant();
	this.run=function()
	{
		my.sub.load();
	};
	this.onclick=function()
	{		
		my.sub.pageclick();
		room.onclick();
		restaurant.onclick();
	};
	my.sub.loadRoomService=function(obj)
	{
		room.load(obj.Id);
	};
	my.sub.loadRestaurant=function(obj)
	{
		restaurant.load(obj.Id);
	};
	this.addRoomService=function(subid)
	{	
		room.addItem(subid);
	};	
	this.addRestaurant=function(subid)
	{	
		restaurant.addItem(subid);
	};	
};
function subject()
{
	var menu=new Tree();
	var ctMenu=new contextMenu();
	ctMenu.fucNew=function()
	{
		my.addSubject(this.obj.Id);
	};
	ctMenu.fucdel=function()
	{
		my.deletesubject(this.obj);
	};
	ctMenu.fucedit=function()
	{
		my.editsubject(this.obj);
	};
	ctMenu.addItem=function()
	{		
		var id=this.obj.Id;
		if(this.obj.Type==0)
		{
			dinning.addRestaurant(id);
		}else
		{
			dinning.addRoomService(id);
		};
	};		
	var my=this;
	this.divName="menu";
	var _row=0;
	this.ID=-1;
	this.List=new Array();
	this.defaults =
	{
		divName:		"menutree",
		classTree:		"treeview",
		title:			"Subject",
		classItem:		"itemnomal",
		classfocus:		"itemfocus",
		classRootOpen:	"treeviewrootopen",
		classRootClose:	"treeviewrootcolse",
		clcassleaves:	"treeviewleaves",
		classopen:		"treeopen",
		classclose:		"treecolse"
	};
	this.pageclick=function()
	{			
		if(ctMenu.state)
		ctMenu.hide();
	};
	this.selectID=function(id)
	{
		my.ID=id;
		var a=$(menu.divName).getElementsByTagName("a");
		for(var i=0;i<a.length;i++)
		{
			if(a[i].id==id)
			{
				a[i].style.color="#ab8718";				
			}else
			{
				a[i].style.color="#878888";
			};
		};		
		for(var i=0;i<my.List.length;i++)
		{
			if(my.List[i].Id==id&my.List[i].Type==1)
			{
				if(my.List[i].Level==1)
				{
					my.loadRoomService(my.List[i]);
				};
				break;
			};			
			if(my.List[i].Id==id&my.List[i].Type==0)
			{
				
				if(my.List[i].Level==1)
				{
					my.loadRestaurant(my.List[i]);
				};
				break;
			};
		};
	};
	this.addSubject=function(id)
	{			
		var textbox=new pmsBoxSub(langpms.addSubject,"","","");		
		textbox.show();
		textbox.accept=function(value,image1,image2)
		{					
			if(value.length==0)
			{
				var albox=new alertBox();
				albox.show(langpms.pls_Subject);
				return;
			};			
			image1=image1.substring(image1.lastIndexOf("/")+1);
			image2=image2.substring(image2.lastIndexOf("/")+1);
			this.hide();			
			var url="ServiceDinning?CMD=1";
				url+="&image1="+image1;
				url+="&image2="+image2;
				url+="&id="+id;
				url+="&t="+Math.random();					
				var param="&name="+encode(value);				
				var f=new AjaxPost(url,param);
				f.complet=function(tx)
				{					
					my.get();
				};
		};
	};
	this.deletesubject=function(obj)
	{
		
		var cfBox=new conformBox();
		cfBox.show();
		cfBox.accept=function()
		{
			var url="ServiceDinning?CMD=3";
			url+="&SubId="+obj.Id;
			url+="&t="+Math.random();
			var f=new funPropery();			
			f.complet=function(tx)
			{				
				my.get();
			};
			AjaxFuncGetXML(url,f);
		};
	};
	this.editsubject=function(obj)
	{
		var name=obj.Name;
		var image=obj.Image;
		var bg=obj.ImageBG;	
		
		var textbox=new pmsBoxSub(langpms.editSubject,name,image,bg);		
		textbox.show();
		textbox.accept=function(value,image1,image2)
		{		
			if(value.length==0)
			{
				var albox=new alertBox();
				albox.show(langpms.pls_Subject);
				return;
			};			
			image1=image1.substring(image1.lastIndexOf("/")+1);
			image2=image2.substring(image2.lastIndexOf("/")+1);
			this.hide();			
			var url="ServiceDinning?CMD=2";
				url+="&image1="+image1;
				url+="&image2="+image2;
				url+="&id="+obj.Id;
				url+="&t="+Math.random();					
				var param="&name="+encode(value);				
				var f=new AjaxPost(url,param);
				f.complet=function(tx)
				{					
					my.get();
				};
		};
	};
	this.setfocus=function()
	{	
		var ul=$(menu.divName).getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
	
		for(var i=0;i<li.length;i++)
		{
			var a=li[i].getElementsByTagName("a");
			if(li[i].id==_row)
			{							
				a[0].className="menu_item_focus";
				
			}else
			{
				a[0].className="menu_item_a";
			};			
		};		
	};	
	this.load=function()
	{	
		menu.init(this.defaults);		
		my.get();	
	};
	this.loadmp3=function(id)
	{		
	};
	this.reload=function(id)
	{
		/**
		 * neu xoa trung id dang focus
		 */		
			var url="ServiceDinning?CMD=1";
			url+="&r="+Math.random();	
			var f=new funPropery();			
			f.complet=function(tx)
			{		
				var arr=new Array();	
				var it=tx.getElementsByTagName("Item");					
				for(var i=0;i<it.length;i++)
				{			
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
					var _imagebg=it[i].getElementsByTagName("imagebg")[0].childNodes[0].nodeValue;
					var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
					var _type=it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
					var _level=it[i].getElementsByTagName("level")[0].childNodes[0].nodeValue;
					var item=new ItemMenu(_id,_parent,_name,_image);
					item.ImageBG=_imagebg;	
					item.Type=_type;
					item.Level=_level;
					arr[i]=item;
				};		
				
				my.List=arr;				
				menu.reload(arr);		
				my.selectID(id);				
			};
			AjaxFuncGetXML(url,f);		
	};
	this.subjectClick=function(id,name)
	{
	};
	this.loadComplet=function(id){};
	menu.renderEvent=function()
	{		
		var ul=$(this.divName).getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		var me=this;
		for(var i=0;i<li.length;i++)
		{			
			var a=li[i].getElementsByTagName("a");
			var item=a[0];			
			item.onclick=function(e)
			{					
				my.selectID(this.id);				
				me.change(this.id);					
			};
			item.oncontextmenu=function(e)
			{			
				my.selectID(this.id);	
				return my.contexmenu(this.id,e);					
			};								
		};
	};
	this.contexmenu=function(id,e)
	{
		var obj=null;
		for(var i=0;i<my.List.length;i++)
		{
			if(my.List[i].Id==id)
			{
				obj=my.List[i];
				break;
			};
		};		
		ctMenu.load(obj,e);				
		return false;
	};
	this.get=function()
	{		
		var url="ServiceDinning?CMD=1";
		url+="&r="+Math.random();	
		var f=new funPropery();				
		f.complet=function(tx)
		{					
			var arr=new Array();	
			var it=tx.getElementsByTagName("Item");	
			for(var i=0;i<it.length;i++)
			{
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _imagebg=it[i].getElementsByTagName("imagebg")[0].childNodes[0].nodeValue;
				var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;		
				var _type=it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;
				var _level=it[i].getElementsByTagName("level")[0].childNodes[0].nodeValue;
				var item=new ItemMenu(_id,_parent,_name,_image);
				item.ImageBG=_imagebg;
				item.Type=_type;
				item.Level=_level;
				arr[i]=item;
			};
			my.List=arr;
			if(my.List.length==0)return;
			menu.load(arr);
			my.selectID(arr[0].Id);
			my.loadDefault(my.List[0].ImageBG);
		};
		AjaxFuncGetXML(url,f);	
	};
	this.loadDefault=function(image)
	{
		var dininglink = linksaveimage + image;
		$("id_table").innerHTML="<div class='div_dinningimage'> <img src='"+dininglink+"' width='200px' height='150px' > </div>";
	};
};
function contextMenu()
{
	var my=this;
	this.state=false;
	this.obj=null;
	this.load=function(obj,e)
	{
		my.obj=obj;
		var posx = 0;
		var posy = 0;
		if (!e) var e = window.event;
		if (e.pageX || e.pageY)
		{
			posx = e.pageX;
			posy = e.pageY;
		}else if (e.clientX || e.clientY)
		{
			posx = e.clientX + document.body.scrollLeft;
				+ document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
				+ document.documentElement.scrollTop;
		};
		my.draw(posx,posy-10);
	};
	this.draw=function(x,y)
	{		
		my.state=true;
		var html="";
		html+="<ul>";
			
			html+="<li class='icon_contextmenuAdd'>";
			if(my.obj.Level==0)
			{
				html+="<a>"+langpms.addSubject+"</a>";
			}else
			{
				html+="<a>"+langpms.addItem+"</a>";
			};
			html+="</li>";
			if ( my.obj.Id.trim() != '-9012' && my.obj.Id.trim() != '-9013' ) {		
				html+="<li class='icon_contextmenuDel'>";
				html+="<a>"+langpms.deleteSubject+"</a>";
				html+="</li>";
				html+="<li class='icon_contextmenuEdit'>";
				html+="<a>"+langpms.editSubject+"</a>";
				html+="</li>";
			};
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
		};
		$("div_contextMenu").style.left=x+"px";
		$("div_contextMenu").style.top= y+"px";
		$("div_contextMenu").style.display= "block";
		my.renderClick();
		eventMouse();
	};
	
	this.hide=function()
	{
		this.state=false;
		$("div_contextMenu").style.display= "none";
	};
	this.renderClick=function()
	{
		var ul=$("div_contextMenu").getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		if(my.obj.Level==0)
		{
			var itemnew=li[0].getElementsByTagName("a")[0];		
			itemnew.onclick=function(){my.fucNew();};
		}else
		{
			var additem=li[0].getElementsByTagName("a")[0];		
			additem.onclick=function(){my.addItem();};
		};
		if ( my.obj.Id.trim() != '-9012' && my.obj.Id.trim() != '-9013' ) {
			var itemdel=li[1].getElementsByTagName("a")[0];
			itemdel.onclick=function(){my.fucdel();};
			var itemedit=li[2].getElementsByTagName("a")[0];
			itemedit.onclick=function(){my.fucedit();};
		};
	};
	this.fucNew=function()
	{
		
	};
	this.fucdel=function()
	{
		
	};
	this.fucedit=function()
	{
		
	};	
};
window.onload = function()
{
	dinning.run();
	document.body.onclick=dinning.onclick;	
		
}; 
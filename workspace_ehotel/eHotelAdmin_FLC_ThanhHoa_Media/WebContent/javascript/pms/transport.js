var rte=new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var transport=new function Transport()
{
	var my=this;
	this.sub=new subject();
	var list1=new ListGround();
	var list2=new ListURL();
	this.onclick=function()
	{	
		list1.pageclick();		
		list2.pageclick();
		my.sub.pageclick();
	}
	my.sub.loadComplet=function(obj)
	{		
		if(obj.Id==-9010)
		list1.load(obj.Id);
		
		if(obj.Id==-9011)
		{
			list2.load();
		}
	}
	this.run=function()
	{
		my.sub.run();
	}
	my.run();
}
function subject()
{
	var menu=new Tree();
	var ctMenu=new contextMenu();
	ctMenu.fucNew=function()
	{
		my.addSubject(this.obj);
	}
	ctMenu.fucdel=function()
	{
		my.deletesubject(this.obj);
	}
	ctMenu.fucedit=function()
	{
		my.editsubject(this.obj);
	}
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
		classRootOpen:		"treeviewrootopen",
		classRootClose:		"treeviewrootcolse",
		clcassleaves:	"treeviewleaves",
		classopen:		"treeopen",
		classclose:		"treecolse"
	}
	this.pageclick=function()
	{			
		if(ctMenu.state)
		ctMenu.hide();
	}
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
			}
		}		
		for(var i=0;i<my.List.length;i++)
		{
			if(my.List[i].Id==id)
			{
				my.loadComplet(my.List[i]);
				break;
			}
		}
	}
	this.addSubject=function(id)
	{			
		var textbox=new pmsBoxSub("Create new Subject","","","");		
		textbox.show();
		textbox.accept=function(value,image1,image2)
		{			
			image1=image1.substring(image1.lastIndexOf("/")+1);
			image2=image2.substring(image2.lastIndexOf("/")+1);
			this.hide();
			var url="hotelPms?CMD=1"
				url+="&image1="+image1
				url+="&image2="+image2
				url+="&t="+Math.random();	
				var param="&name="+encode(value);				
				var f=new AjaxPost(url,param);
				f.complet=function(tx)
				{					
					my.get();
				}
		}
	}
	this.deletesubject=function(id)
	{
		var cfBox=new conformBox();
		cfBox.show();
		cfBox.accept=function()
		{
			var url="hotelPms?CMD=3";
			url+="&SubId="+id;
			url+="&t="+Math.random();
			var f=new funPropery();			
			f.complet=function(tx)
			{				
				my.get();
			}
			AjaxFuncGetXML(url,f);
		}
	}
	this.editsubject=function(id)
	{
		var obj=null;		
		for(var i=0;i<my.List.length;i++)
		{			
			if(my.List[i].Id==id)
			{				
				obj=my.List[i];
				break;
			}
		}		
		var im1=obj.Image
		var im2=obj.ImageBG;
		var textbox=new pmsBoxSub("Edit Subject",obj.Name,im1,im2);		
		textbox.show();
		textbox.accept=function(value,image1,image2)
		{
			if(value.length==0)
			{
				var albox=new alertBox();
				albox.show("Please input subject name !");
				return;
			}
			image1=image1.substring(image1.lastIndexOf("/")+1);
			image2=image2.substring(image2.lastIndexOf("/")+1);
			this.hide();		
			var url="ServiceTranport?CMD=5"
				url+="&image="+image1
				url+="&icon="+image2;
				url+="&id="+id;
				url+="&t="+Math.random();	
				var param="&name="+encode(value);	
				var f=new AjaxPost(url,param)
				f.complet=function(tx)
				{							
					my.get();
				}
		}
	}
	this.setfocus=function()
	{	
		var ul=$(menu.divName).getElementsByTagName("ul")
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
			}			
		}		
	}	
	this.run=function()
	{	
		menu.init(this.defaults);		
		my.get();	
	}
	this.loadmp3=function(id)
	{		
	}
	this.reload=function(id)
	{
		/**
		 * neu xoa trung id dang focus
		 */		
			var url="hotelPms?CMD=1";
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
					var item=new ItemMenu(_id,_parent,_name,_image);
					item.ImageBG=_imagebg;					
					arr[i]=item;
				}		
				my.List=arr;				
				menu.reload(arr);		
				my.selectID(id);
			}
			AjaxFuncGetXML(url,f);
	}
	this.subjectClick=function(id,name)
	{
	}
	this.loadComplet=function(id){};
	menu.renderEvent=function()
	{
		var ul=$(this.divName).getElementsByTagName("ul")
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
				return my.contexmenu(this.id,e);		
				return false;
			};								
		}
	}
	this.contexmenu=function(id,e)
	{
		//my.mp3.load(obj.Id,obj.Name);		
		ctMenu.load(id,e);	
		return false;
	}
	this.get=function()
	{		
		var url="ServiceTranport?CMD=1"
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
				var item=new ItemMenu(_id,_parent,_name,_image);
				item.ImageBG=_imagebg;					
				arr[i]=item;					
			}		
			my.List=arr;
			if(my.List.length==0)return;
			menu.load(arr);
			my.selectID(arr[0].Id);
		}			
		AjaxFuncGetXML(url,f);		
	}
}
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
		}else if (e.clientX || e.clientY) 	{
			posx = e.clientX + document.body.scrollLeft
				+ document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
				+ document.documentElement.scrollTop;
		}
		
		my.draw(posx,posy-10);
	}
	this.draw=function(x,y)
	{		
		my.state=true;
		var html="";
		html+="<ul>";
			//html+="<li class='icon_contextmenuAdd'>";
			//html+="<a>"+langpms.addSubject+"</a>"
			//html+="</li>";
			///html+="<li class='icon_contextmenuDel'>";
			//html+="<a>"+langpms.deleteSubject+"</a>";
			//html+="</li>";			
			html+="<li class='icon_contextmenuEdit'>";
			html+="<a>"+langpms.editSubject+"</a>";
			
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
	}
	this.hide=function()
	{
		this.state=false;
		$("div_contextMenu").style.display= "none";
	}
	this.renderClick=function()
	{
		var ul=$("div_contextMenu").getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		//var itemnew=li[0].getElementsByTagName("a")[0];
		//itemnew.onclick=function(){my.fucNew();};
		//var itemdel=li[1].getElementsByTagName("a")[0];
		//itemdel.onclick=function(){my.fucdel();};
		var itemedit=li[0].getElementsByTagName("a")[0];
		itemedit.onclick=function(){my.fucedit();};
	}
	this.fucNew=function()
	{
	}
	this.fucdel=function()
	{
	}
	this.fucedit=function()
	{
	}
}
function ListGround()
{
	var Index=0;
	var table=new Table();
	var ctTable=new contextTableEX();
	ctTable.getHTML=function()
	{
		var html="";
		html+="<ul>";
		html+="<li class='icon_contextmenuEdit'>";
		html+="<a>"+langpms.addItem+"</a>";
		html+="</li>";
		html+="<li class='icon_contextmenuplay'>";		
		html+="<a>"+langpms.detailItem+"</a>"
		html+="</li>";
		html+="<li class='icon_contextmenuDel'>";
		html+="<a>"+langpms.removeItem+"</a>";
		html+="</li>";		
		html+="</ul>";	
		return html;
	}
	ctTable.fucDetail=function()
	{
		var detail=new DetailGrounds();
		detail.load(this.obj.Id);
		detail.update=function()
		{
			my.reload()
		}
	}	
	ctTable.fucdel=function()
	{
		var list="";
		if(my.ListCheck.size()==0)
		{
			list+="&id0="+this.obj;
		}else
		{
			for(i=0;i<my.ListCheck.size();i++)
			{
				list+="&id"+i+"="+my.ListCheck.get(i);
			}
		}
		var cfBox=new conformBox();
		cfBox.show();
		cfBox.accept=function()
		{				
			var url="ServiceTranport?CMD=6"
				url+=list;
				url+="&r="+Math.random();	
				var f=new AjaxGetXML(url);					
				f.complet=function(tx)
				{
					my.count-=my.ListCheck.size();
					my.reload();					
				}
		}
	}
	ctTable.fucAdd=function()
	{		
		var add=new AddGround();
		add.load(my.SubId);
		add.update=function()
		{
			my.reload()
		}
	}
	this.addeImage=function()
	{
		var add=new AddAdv();
		add.load();
		add.update=function()
		{
			my.reload();
		}
	}
	this.pageclick=function()
	{
		if(ctTable.state)
		ctTable.hide();
	}
	var my=this;
	this.count=0;
	var ID=0;//id of subject
	this.SubId=-1;	
	var page=5;
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
	}
	/**
	 * @param id id subject
	 * @return
	 */
	this.load=function(id)
	{		
		this.SubId=id;
		Index=0
		table.init(defaults);
		my.get(Index);			
	}
	this.reload=function()
	{				
		if((Index*page)>=my.count)
		{
			if(Index>0)Index--;
		}
		my.get(ID,Index);	
	}
	this.loadIdex=function(index)
	{		
		Index=index
		my.get(ID,index);		
	}
	this.nextindex=function()
	{	
		var cpage=((my.count-1)/page)-1;
		if(Index<cpage)
				Index++;
		my.loadIdex(Index);
	}
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIdex(Index);
	}
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
			$("div_page_icon_right").onclick=function(){my.nextindex()};
			$("div_page_icon_left").onclick=function(){my.backindex()};
		}	
	}
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
	}
	this.addItem=function(id)
	{		
		if(!this.ListCheck.isObject(id))
		{
			this.ListCheck.add(id);
		}
		my.shiftSelect();
	}
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
	}
	table.dataBind=function()
	{			
		var html="";
		html+="<div class='div_formtransport'>";
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";		
		html+="<th align='left'  valign=\"middle\"  width=\""+20+"%\"  class='header0' >";
		html+=langpms.name;
		html+="</th>";	
		html+="<th align='left' width=\""+10+"%\" valign=\"middle\"  >";
		html+=langpms.image;
		html+="</th>";	
		html+="<th align='left' width=\""+10+"%\" valign=\"middle\"  >";
		html+=langpms.Background;
		html+="</th>";	
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\"  >";
		html+=langpms.status;
		html+="</th>";		
		html+="</tr>";			
		for(var i=0;i<this.List.length;i++)
		{
		translink = linksaveimage + this.List[i].Image;
		transicon = linksaveimage + this.List[i].Icon;
				if(i%2==0)
				{				
					html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\"  >";
				}else
				{
					html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\"   >";
				}					
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=convertToHTML(this.List[i].Name);
				html+="</td>";	
				html+="<td align=\"left\"  valign=\"middle\">";
				html+="<img class='image_50' src="+translink+"></img>"
				html+="</td>";	
				html+="<td align=\"left\"  valign=\"middle\">";
				html+="<img class='image_50' src="+transicon+"></img>"
				html+="</td>";	
				html+="<td align=\"center\"  valign=\"middle\">";				
				html+="</td>";					
				html+="</tr>";
		}		
		html+="</table>";		
		html+="</div>"
		if(this.List.length==0)
		{
			html+="<div  align='center'>";
			html+="<a href='javascript:other.addItem()' class='div_addeImage' title='Click here add Image'>Add Image</a>"
			html+="</div>";
		}
		if(my.count>5)
		{
			var page=my.count/5;
			html+="<div id=\"div_page\" class='div_page'>";
			html+="<div class='div_page_icon_left' id='div_page_icon_left'>";
			html+="</div>";
			for(var i=0;i<page;i++)
			{
				if(i==Index)
				{
					html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+i+"\">";
				}else
				{
					html+="<a class='div_page_a' href=\"javascript:\" id=\""+i+"\">";
				}
				html+=(i+1)
				html+=" </a>";				
			}			
			html+="<div class='div_page_icon_right' id='div_page_icon_right'>";
			html+="</div>";
			html+="</div>";
		}		
		html+="<div class='div_bottom_table'>";
		html+="";	
		html+="</div>";
		return html;
	}
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
	}
	this.get=function(id)
	{			
		var url="ServiceTranport?CMD=5";
			url+="&pageIndex="+Index;
			url+="&page="+page;
			url+="&MenuId="+my.SubId;
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
					var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
					var _icon=it[i].getElementsByTagName("icon")[0].childNodes[0].nodeValue;
					arr[i]=new ItemImage(_id,_name,_image,_icon,1);								
				}	
				table.load(arr);
				table.classCol("classItem1",1);
				table.classCol("classItem3",0);			
				my.renderEvent();		
			}
	}
	this.loadcomplet=function()
	{	
	}
}
function DetailGrounds()
{
	var my=this;
	var layer=new layer_vitual();	
	this.Id=-1;
	this.load=function(id)
	{			
		my.Id=id;
		my.show(id);
	}
	this.show=function(id)
	{			
		layer.show();			
		var html="";			
		var url="ServiceTranport?CMD=4"
			url+="&id="+id;
			url+="&r="+Math.random();	
			var f=new AjaxGetText(url);
			f.complet=function(tx)
			{			
				layer.addHtml(tx);			
				setCenterDIV("form_detail");
				$("form_textbox_ok").onclick=function()
				{ my.accept();};
				$("form_textbox_cancel").onclick=my.cancel;				
			}				
	}
	this.accept=function()
	{			
		var name=encode($("name").value);	
		
		var image=$("image1").src;
		var icon=$("image2").src;
		image=image.substring(image.lastIndexOf("/")+1);
		icon=icon.substring(icon.lastIndexOf("/")+1);
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show("Name  is empty");
			return;
		};
		var url="ServiceTranport?CMD=2"
			url+="&t="+Math.random();
			var patam ="&name="+name;
			url+="&id="+my.Id
			url+="&icon="+icon;
			url+="&image="+image
			var f=new AjaxPost(url,patam);			
			f.complet=function(tx)
			{
				my.hide();
				my.update();
			}		
	}
	this.update=function()
	{			
	}
	this.cancel=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	}
}
function AddGround()
{
	var my=this;	
	var layer=new layer_vitual();	
	this.Id=-1;
	this.load=function(id)
	{			
		my.Id=id;
		my.show(id);
	}
	this.show=function(id)
	{			
		layer.show();			
		var html="";			
		var url="ServiceTranport?CMD=4"
			url+="&id=-1";
			url+="&r="+Math.random();	
			var f=new AjaxGetText(url);
			f.complet=function(tx)
			{			
				layer.addHtml(tx);			
				setCenterDIV("form_detail");
				$("form_textbox_ok").onclick=function()
				{		
					my.accept();};
				$("form_textbox_cancel").onclick=my.cancel;				
			}				
	}
	this.accept=function()
	{			
		var name=encode($("name").value);		
		var image=$("image1").src;
		var icon=$("image2").src;
		image=image.substring(image.lastIndexOf("/")+1);
		icon=icon.substring(icon.lastIndexOf("/")+1);
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show("Name  is empty");
			return;
		};
		var url="ServiceTranport?CMD=1"
			url+="&t="+Math.random();
			var patam ="&name="+name;
			url+="&id="+my.Id
			url+="&icon="+icon;
			url+="&image="+image
			var f=new AjaxPost(url,patam);			
			f.complet=function(tx)
			{
				my.hide();
				my.update();
			}		
	}
	this.update=function()
	{		
	}
	this.cancel=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	}
}
/**
 * @return
 */
function ListURL()
{
	var Index=0;
	var table=new Table();
	var ctTable=new contextTableEX();
	ctTable.fucDetail=function()
	{
		var detail=new DetaiURL();
		detail.load(this.obj.Id);
		detail.update=function()
		{
			my.reload()
		}
	}	
	ctTable.fucdel=function()
	{
		var list="";
		if(my.ListCheck.length==0)
		{
			list+="&id0="+this.obj;
		}else
		{
			for(i=0;i<my.ListCheck.size();i++)
			{
				list+="&id"+i+"="+my.ListCheck.get(i);
			}
		}
		var cfBox=new conformBox();
		cfBox.show();
		cfBox.accept=function()
		{				
			var url="ServiceTranport?CMD=7"
				url+=list;
				url+="&r="+Math.random();	
				var f=new AjaxGetXML(url);					
				f.complet=function(tx)
				{
					my.count--;
					my.reload();					
				}
		}
	}
	ctTable.fucAdd=function()
	{		
		var add=new AddURL();
		add.load();
		add.update=function()
		{
			my.reload()
		}
	}
	this.addeImage=function()
	{
		var add=new AddURL();
		add.load();
		add.update=function()
		{
			my.reload()
		}
	}	
	ctTable.fucStatus=function()
	{
		var url="ServiceTranport?CMD=8"
			url+="&id="+this.obj.Id;
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);					
			f.complet=function(tx)
			{
				my.reload();					
			}			
	}
	this.pageclick=function()
	{
		if(ctTable.state)
		ctTable.hide();
	}
	var my=this;
	this.count=0;
	var ID=0;//id of subject
	this.SubId=-1;	
	this.page=8;
	this.ListCheck=new Vector();
	var arr=new Array();
	var defaults =
	{
		divName:		"id_table",
		col:  			5,
		classHeader:    "mytable_header",
		classItem: 		"classItem",
		classTable:		"classTable",
		classRowsel:		"mytable_row_sel",
		classTable:		"mytable",
		bgRow1:		"mytable_row1",
		bgRow2:		"mytable_row2"
	}	
	/**
	 * @param id id subject
	 * @return
	 */
	this.load=function()
	{		
		table.init(defaults);
		my.get(Index);			
	}
	this.reload=function()
	{		
		if((Index*my.page)>=my.count)
		{
			if(Index>0)Index--;
		}
		my.get(ID,Index);	
	}
	this.loadIdex=function(index)
	{		
		Index=index
		my.get(ID,index);		
	}
	this.nextindex=function()
	{	
		var cpage=((my.count-1)/my.page)-1;
		if(Index<cpage)
				Index++;
		my.loadIdex(Index);
	}
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIdex(Index);
	}
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
			$("div_page_icon_right").onclick=function(){my.nextindex()};
			$("div_page_icon_left").onclick=function(){my.backindex()};
		}	
	}
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
	}
	this.addItem=function(id)
	{		
		if(!this.ListCheck.isObject(id))
		{
			this.ListCheck.add(id);
		}
		my.shiftSelect();
	}
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
	}
	table.dataBind=function()
	{				
		var html="";
		html+="<div class='div_formurl'>"
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"98%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";		
		html+="<th align='left'  valign=\"middle\"  width=\""+30+"%\"  class='header0' >";
		html+=langpms.name;
		html+="</th>";	
		html+="<th align='left' width=\""+60+"%\" valign=\"middle\"  >";
		html+="Url";
		html+="</th>";	
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\"  >";
		html+=langpms.status;
		html+="</th>";		
		html+="</tr>";			
		for(var i=0;i<this.List.length;i++)
		{					
				if(i%2==0)
				{				
					html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\"  >";
				}else
				{
					html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\"   >";
				}					
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=convertToHTML(this.List[i].Name);
				html+="</td>";	
				
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=this.List[i].Url
				html+="</td>";	
				html+="<td align=\"center\"  valign=\"middle\">";
				if(this.List[i].Status==0)
				{
					html+="<img src=\"../icon/16-square-green.png\"></img>"
				}else
				{
					html+="<img src=\"../icon/16-square-red.png\"></img>"
				}
				html+="</td>";					
				html+="</tr>";
		}		
		html+="</table>";		
		html+="</div>"
		if(this.List.length==0)
		{
			html+="<div  align='center'>";
			html+="<a href='javascript:other.addItem()' class='div_addeImage' title='Click here add Image'>Add Image</a>"
			html+="</div>";
		}
		if(my.count>my.page)
		{
			var page=my.count/my.page;
			html+="<div id=\"div_page\" class='div_page'>";
			html+="<div class='div_page_icon_left' id='div_page_icon_left'>";
			html+="</div>";
			for(var i=0;i<page;i++)
			{
				if(i==Index)
				{
					html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+i+"\">";
				}else
				{
					html+="<a class='div_page_a' href=\"javascript:\" id=\""+i+"\">";
				}
				html+=(i+1)
				html+=" </a>";				
			}			
			html+="<div class='div_page_icon_right' id='div_page_icon_right'>";
			html+="</div>";
			html+="</div>";
		}		
		html+="<div class='div_bottom_table'>"
		html+=""			
		html+="</div>"
		return html;
	}
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
	}
	this.get=function(id)
	{			
		var url="ServiceTranport?CMD=2"
			url+="&pageIndex="+Index;
			url+="&page="+my.page;
			url+="&MenuId="+id;
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
					var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
					var _url=it[i].getElementsByTagName("url")[0].childNodes[0].nodeValue;
					var _status=it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
					arr[i]=new ItemUrl(_id,_name,_image,_url,_status);								
				}				
				table.load(arr);
				table.classCol("classItem1",1);
				table.classCol("classItem3",0);			
				my.renderEvent();		
			}			
	}
	this.loadcomplet=function()
	{
	}
}
function contextTableEX()
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
		my.draw(posx,posy-10);
	}
	this.getHTML=function()
	{
		var html="";
		html+="<ul>";
		html+="<li class='icon_contextmenuEdit'>";
		html+="<a>"+langpms.addItem+"</a>";
		html+="</li>";
		html+="<li class='icon_contextmenuplay'>";
		html+="<a>"+langpms.detailItem+"</a>"
		html+="</li>";
		html+="<li class='icon_contextmenuDel'>";
		html+="<a>"+langpms.removeItem+"</a>";
		html+="</li>";			
		if(my.obj.Status==1)
		{
			html+="<li class='icon_visibility1'>";
		}else
		{
			html+="<li class='icon_visibility2'>";
		}			
		html+="<a>"+langpms.changeStatus+"</a>";
		html+="</li>";
		html+="</ul>";	
		return html;
	}
	this.draw=function(x,y)
	{		
		my.state=true;
		var html="";
			html+=my.getHTML();
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
	}
	this.renderClick=function()
	{	
		
		var ul=$("div_contextMenu").getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		var fucAdd=li[0].getElementsByTagName("a")[0];
		fucAdd.onclick=function(){my.fucAdd();};
		var fucDetail=li[1].getElementsByTagName("a")[0];
		fucDetail.onclick=function(){my.fucDetail();};
		var fucdel=li[2].getElementsByTagName("a")[0];
		fucdel.onclick=function(){my.fucdel();};
		if(li[3]!=undefined)
		{
			var itemstatus=li[3].getElementsByTagName("a")[0];
			itemstatus.onclick=function(){my.fucStatus();};
		}
	}
	this.hide=function()
	{
		this.state=false;
		$("div_contextMenu").style.display= "none";
	}
	my.fucStatus=function()
	{
	}
}
function DetaiURL()
{
	var my=this;	
	var layer=new layer_vitual();	
	this.Id=-1;
	this.load=function(id)
	{			
		my.Id=id;
		my.show(id);
	}
	this.show=function(id)
	{			
		layer.show();			
		var html="";			
		var url="ServiceTranport?CMD=3"
			url+="&id="+id;
			url+="&r="+Math.random();	
			var f=new AjaxGetText(url);
			f.complet=function(tx)
			{			
				layer.addHtml(tx);			
				setCenterDIV("form_detail_url");
				$("form_textbox_ok").onclick=function()
				{
					my.accept();};
				$("form_textbox_cancel").onclick=my.cancel;				
			}				
	}
	this.accept=function()
	{			
		var name=$("name").value;
		var link=$("url").value;	
		var image=$("image").src;
		image=image.substring(image.lastIndexOf("/")+1);
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show("Name Rate is empty");
			return;
		};		
		var url="ServiceTranport?CMD=4"
			url+="&t="+Math.random();
			url+="&url="+link;
			url+="&image="+image;
			url+="&id="+my.Id;
			var param="&name="+name;
			var f=new AjaxPost(url,param);			
			f.complet=function(tx)
			{
				my.hide();
				my.update();
			}		
	}
	this.update=function()
	{		
	}
	this.cancel=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	}
}
function AddURL()
{
	var my=this;	
	var layer=new layer_vitual();	
	this.Id=-1;
	this.load=function(id)
	{			
		my.Id=id;
		my.show(id);
	}
	this.show=function(id)
	{			
		layer.show();			
		var html="";			
		var url="ServiceTranport?CMD=3"
			url+="&id=-1";
			url+="&r="+Math.random();	
			var f=new AjaxGetText(url);
			f.complet=function(tx)
			{			
				layer.addHtml(tx);			
				setCenterDIV("form_detail_url");
				$("form_textbox_ok").onclick=function()
				{		
					my.accept();};
				$("form_textbox_cancel").onclick=my.cancel;				
			}				
	}
	this.accept=function()
	{			
		var name=$("name").value;
		var link=$("url").value;
		var image=$("image").src;
		image=image.substring(image.lastIndexOf("/")+1);
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show("Name Rate is empty");
			return;
		};
		var url="ServiceTranport?CMD=3"
			url+="&t="+Math.random();
			url+="&url="+link;
			url+="&image="+image;
			var param="&name="+name;
			var f=new AjaxPost(url,param);			
			f.complet=function(tx)
			{
				my.hide();
				my.update();
			}		
	}
	this.update=function()
	{
	}
	this.cancel=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	}
}
function ItemUrl(id,name,image,url,status)
{
	this.Id=id;this.Name=name;this.Url=url;this.Status=status;
}
window.onload = function()
{
		document.body.onclick=transport.onclick;
} 
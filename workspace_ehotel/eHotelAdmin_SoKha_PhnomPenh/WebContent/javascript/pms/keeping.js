var keeping=new function Keeping()
{
	var my=this;
	this.sub=new subject();
	var list=new listkeeping();
	this.run=function()
	{
		my.sub.load();
	}
	my.sub.loadComplet=function(obj)
	{		
		list.load(obj.Id);
	}
	this.addItem=function(id)
	{
		
		list.add(id);
	}
	this.onclick=function()
	{
		my.sub.pageclick();	
		list.pageclick();
	}
}
function subject()
{
	var menu=new Tree();
	var ctMenu=new contextMenu();
	ctMenu.fucNew=function()
	{				
		keeping.addItem(this.obj);
		
	}
	ctMenu.fucdel=function()
	{		
			var albox=new alertBox();
			albox.show(langpms.ifo_notdelsub);
			return;		
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
		var textbox=new pmsBoxSub(langpms.editSubject,obj.Name,im1,im2);		
		textbox.show();
		textbox.accept=function(value,image1,image2)
		{
			if(value.length==0)
			{
				var albox=new alertBox();
				albox.show(langpms.pls_Subject);
				return;
			}
			image1=image1.substring(image1.lastIndexOf("/")+1);
			image2=image2.substring(image2.lastIndexOf("/")+1);
			this.hide();
			var url="SvcHouseKeeping?CMD=3"
				url+="&image1="+image1
				url+="&image2="+image2;
				url+="&SubId="+id;
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
	this.load=function()
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
			var url="SvcHouseKeeping?CMD=1"
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
				my.selectID(this.id);
				return my.contexmenu(this.id,e);					
			};								
		}
	}	
	this.contexmenu=function(id,e)
	{
		//my.mp3.load(obj.Id,obj.Name);		
		ctMenu.load(id,e);	
		//		
		return false;
	}
	
	this.get=function()
	{		
		var url="SvcHouseKeeping?CMD=1"
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
			
			html+="<li class='icon_contextmenuAdd'>";
			
			html+="<a>"+langpms.addItem+"</a>";
			
			html+="</li>";
			html+="<li class='icon_contextmenuDel'>";
			html+="<a>"+langpms.deleteSubject+"</a>";
			html+="</li>";			
			html+="<li class='icon_contextmenuEdit'>";
			html+="<a>"+langpms.editSubject+"</a>";
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
		
		var itemnew=li[0].getElementsByTagName("a")[0];		
		itemnew.onclick=function(){my.fucNew();};		
		var itemdel=li[1].getElementsByTagName("a")[0];
		itemdel.onclick=function(){my.fucdel();};
		var itemedit=li[2].getElementsByTagName("a")[0];
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
function listkeeping()
{
	var table=new Table();
	var ctTable=new contextTable();
	ctTable.fucDetail=function()
	{
		
		var detail=new detailItem();
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
			var url="SvcHouseKeeping?CMD=4"
				url+=list;
				url+="&r="+Math.random();	
				var f=new funPropery();					
				f.complet=function(tx)
				{
					Index=0;
					my.reload();					
				}
				AjaxFuncGetXML(url,f);
		}
	}
	ctTable.fucAdd=function()
	{		
		var add=new AddItem();
		add.load(my.SubId);
		add.update=function()
		{
			my.reload();
		}
	}
	this.add=function(id)
	{
		if(id==undefined)
		{
			var add=new AddItem();
			add.load(my.SubId);
			add.update=function()
			{
				my.reload();
			}
		}else
		{
		var add=new AddItem();
		add.load(id);
		add.update=function()
		{
			my.reload();
		}
		}
	}	
	ctTable.fucStatus=function()
	{
		var url="SvcHouseKeeping?CMD=5"
			url+="&id="+this.obj.Id;
			url+="&r="+Math.random();				
			var f=new funPropery();					
			f.complet=function(tx)
			{
				my.reload();					
			}
			AjaxFuncGetXML(url,f);
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
	var Index=0;
	this.ListCheck=new Vector();
	this.List=new Array();
	var arr=new Array();
	this.page=6;
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
	 * 
	 * 
	 * @param id id subject
	 * @return
	 */
	this.load=function(id)
	{		
		Index=0;
		this.List=new Array();
		this.SubId=id;
		ID=id;
		table.init(defaults);
		my.get(id,Index);			
	}
	this.loadData=function()
	{		
		
		var b=Index*this.page;
		var t=Number(Index)+1;
		var e=(t)*this.page;
		if(e>my.count)e=my.count;
		var arr=new Array();		
		for(var i=b;i<e;i++)
		{
			arr[i-b]=my.List[i];
		}
		table.load(arr);
		table.classCol("classItem1",1);
		table.classCol("classItem0",0);			
		my.renderEvent();
	}
	this.reload=function()
	{		
		this.List=new Array();
		my.get(my.SubId,Index);		
	}
	this.loadIndex=function(index)
	{		
		Index=index
		my.loadData();		
	}
	this.nextindex=function()
	{			
		var cpage=(my.count/my.page)+0.4;
		cpage=Math.round(cpage);
		if(Index<cpage-1)
				Index++;
		my.loadIndex(Index);
	}
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIndex(Index);
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
					
					my.loadIndex(this.id);					
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
		for(var i=0;i<my.List.length;i++)
		{
			if(my.List[i].Id==id)
			{
				obj=my.List[i];
				break;
			}
		}		
		ctTable.load(obj, e);
	}
	table.dataBind=function()
	{		
		var html="";
		html+="<div class='div_listhotel'>"
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";		
		html+="<th align='left'  valign=\"middle\"  width=\""+50+"%\"  >";
		html+=langpms.name;
		html+="</th>";		
		html+="<th align='left' width=\""+20+"%\" valign=\"middle\"  >";
		html+=langpms.price_pms;
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
				html+=this.List[i].Title;
				html+="</td>";				
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=this.List[i].Price+" "+this.List[i].Unit;
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
					html+="<a href='javascript:keeping.addItem();' class='div_addeImage' title='Click here add "+langpms.addItem+"'>"+langpms.addItem+"</a>"
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
	this.get=function(id,index)
	{				
		var url="SvcHouseKeeping?CMD=2"
			url+="&SubId="+id;
			url+="&pageIndex="+index;
			url+="&page=6";
			url+="&r="+Math.random();	
		
			var f=new funPropery();		
			
			f.complet=function(tx)
			{									
				var xml=tx.getElementsByTagName("xml");	
				var it=tx.getElementsByTagName("Item");	
				for(var i=0;i<it.length;i++)
				{					
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _des=it[i].getElementsByTagName("Def")[0].childNodes[0].nodeValue;					
					var _status=it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
					var _price=it[i].getElementsByTagName("price")[0].childNodes[0].nodeValue;
					var _unit=it[i].getElementsByTagName("unit")[0].childNodes[0].nodeValue;
					my.List[i]=new ItemHotel(_id,_name,_des,_status);	
					my.List[i].Price=_price;
					my.List[i].Unit=_unit;
				}
				
				my.count=my.List.length;
				my.loadData();				
			}			
			AjaxFuncGetXML(url,f);
	}
	this.loadcomplet=function()
	{		
		//$("title_subject").innerHTML=my.subname+"("+my.count+")";		
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
			
			my.draw(posx,posy-10);
		}
		this.draw=function(x,y)
		{
			
			my.state=true;
			var html="";
			html+="<ul>";
				html+="<li class='icon_contextmenuplay'>";
				html+="<a>"+langpms.addItem+"</a>";
				html+="</li>";
				html+="<li class='icon_contextmenuEdit'>";
				html+="<a>"+langpms.detailItem+"</a>"			
				html+="</li>";
				html+="<li class='icon_contextmenuDel'>";
				html+="<a>"+langpms.removeItem+"</a>";
				html+="</li>";		
				/*
				html+="<li class='icon_contextmenucopy'>";
				html+="<a>"+langpms.changeSubject+"</a>";
				html+="</li>";
				*/				
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
		this.renderClick=function()
		{	
			var ul=$("div_contextMenu").getElementsByTagName("ul");
			var li=ul[0].getElementsByTagName("li");
			var itemdetail=li[1].getElementsByTagName("a")[0];
			itemdetail.onclick=function(){my.fucDetail();};
			var itemdel=li[2].getElementsByTagName("a")[0];
			itemdel.onclick=function(){my.fucdel();};
			//var itemchange=li[3].getElementsByTagName("a")[0];
			//itemchange.onclick=function(){my.change();};
			var itemAdd=li[0].getElementsByTagName("a")[0];
			itemAdd.onclick=function(){my.fucAdd();};
			var itemstatus=li[3].getElementsByTagName("a")[0];
			itemstatus.onclick=function(){my.fucStatus();};
		}
		this.hide=function()
		{
			this.state=false;
			$("div_contextMenu").style.display= "none";
		}
	}
}
function detailItem()
{	
	var my=this;	
	var layer=new layer_vitual();
	this.ItemId=null;
	this.load=function(id)
	{	
		my.ItemId=id;
		my.show();
	}
	this.show=function()
	{			
		layer.show();			
		var html="";
		var url="SvcHouseKeeping?CMD=3"
			url+="&id="+my.ItemId
			url+="&r="+Math.random();		
			var f=new AjaxGetText(url);
			f.complet=function(tx)
			{			
				layer.addHtml(tx);			
				setCenterDIV("form_detail_item");					
				$("form_textbox_ok").onclick=function()
				{			
					my.accept();};
				$("form_textbox_cancel").onclick=my.cancel;				
				
			}				
	}
	this.accept=function()
	{			
		var select =$("div_money");
		var name=$("name").value;
		name=encode(name);
		var image=$("image").src;
		var price=$("price").value;
		var unit=select.options[select.selectedIndex].text;
		image=image.substring(image.lastIndexOf("/")+1);
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show(langpms.pls_name);
			return;
		};	
		if(price.length==0)
		{
			var albox=new alertBox();
			albox.show(langpms.pls_price);
			return;
		};
		var url="SvcHouseKeeping?CMD=2"
			url+="&t="+Math.random();
			var param="&name="+name;
			
			param+="&id="+my.ItemId;			
			param+="&image="+image;
			param+="&price="+price;	
			param+="&unit="+unit;
			var f=new AjaxPostText(url,param);			
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
function AddItem()
{	
	var my=this;	
	var layer=new layer_vitual();
	this.subId=null;
	this.load=function(id)
	{	
		my.subId=id;
		my.show();
	}
	this.show=function()
	{			
		
		layer.show();			
		var html="";			
		var url="SvcHouseKeeping?CMD=3"
			url+="&id="+my.subId;
			url+="&r="+Math.random();		
			var f=new AjaxGetText(url);
			f.complet=function(tx)
			{			
				layer.addHtml(tx);			
				setCenterDIV("form_detail_item");
				$("form_textbox_ok").onclick=function()
				{			
					my.accept();};
				$("form_textbox_cancel").onclick=my.cancel;					
			}				
	}
	this.accept=function()
	{			
		var select =$("div_money");
		var name=$("name").value;
		name=encode(name);
		var image=$("image").src;
		var price=$("price").value;
		var unit=select.options[select.selectedIndex].text;
		image=image.substring(image.lastIndexOf("/")+1);
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show(langpms.pls_name);
			return;
		};	
		if(price.length==0)
		{
			var albox=new alertBox();
			albox.show(langpms.pls_price);
			return;
		};
		var url="SvcHouseKeeping?CMD=1"
			url+="&t="+Math.random();
			var param="&name="+name;
			param+="&subid="+my.subId;			
			param+="&image="+image;
			param+="&price="+price;		
			param+="&unit="+unit;
			var f=new AjaxPostText(url,param);			
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
window.onload = function()
{
	keeping.run();
	document.body.onclick=keeping.onclick;	
		
} 
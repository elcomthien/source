
var synvod=new  function SynVod()
{
	var my=this;
	var tag=0;
	this.sub=new subject();
	this.list=new listVOD();
	this.add=new AddVod();
	
	this.run=function()
	{
		my.sub.load();
	}
	my.sub.loadComplet=function(obj)
	{		
		
		if(tag==0)
		my.list.load(obj.Id);
		if(tag==1)
		{			
			$("div_setopbox").innerHTML=obj.Name;
			my.add.load(obj.Id);
		}
	}
	
	this.changeTab=function(id)
	{
		var div=$("tab_menu").getElementsByTagName("div");
		tag=id;
		if(id==0)
		{			
			div[0].className="tab_menu_item_sel";
			div[1].className="tab_menu_item";
			$("title_subject").innerHTML=langpms.listaddvod;
			my.loadList();
		}else
		{			
			div[1].className="tab_menu_item_sel";
			div[0].className="tab_menu_item";	
			$("title_subject").innerHTML=langpms.listwaitingvod;
			my.loadAdd();
		}		
	}	
	this.loadList=function()
	{
		$("id_table1").style.display="block";
		$("id_table2").style.display="none";
		my.list.load(my.sub.ID);
	}
	this.loadAdd=function()
	{		
		$("id_table2").style.display="block";
		$("id_table1").style.display="none";
		my.add.load(my.sub.ID);
		$("div_setopbox").innerHTML=my.sub.obj.Name;
	}	
	my.run();
}
function subject()
{
	var menu=new Tree();
	
	var my=this;
	this.divName="menu";
	var _row=0;
	this.ID=-1;
	this.obj;
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
				my.obj=my.List[i];
				my.loadComplet(my.List[i]);
				break;
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
		var url="ServiceSynVodStb?CMD=1"
		url+="&r="+Math.random();	
		var f=new funPropery();			
		f.complet=function(tx)
		{					
			var arr=new Array();	
			var it=tx.getElementsByTagName("Item");				
			for(var i=0;i<it.length;i++)
			{			
				var _name=it[i].getElementsByTagName("keycode")[0].childNodes[0].nodeValue;			
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _parent=-1;
				var item=new ItemMenu(_id,_parent,_name,"");
				arr[i]=item;
			}		
			my.List=arr;
			if(my.List.length==0)return;
			menu.load(arr);
			my.selectID(arr[0].Id);
		}			
		AjaxFuncGetXML(url,f);		
	}
	this.getcurent=function()
	{
		return this.ID;
	}
}
function listVOD()
{
	var table=new Table();
	/*
	var ctTable=new contextTable();
	ctTable.fucDetail=function()
	{
		
		var detail=new detailItem();
		detail.load(this.obj);
		detail.update=function()
		{
			my.reload()
		}
	}	
	ctTable.fucdel=function()
	{
		//my.ListCheck 
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
			var url="hotelPms?CMD=8"
				url+=list;
				url+="&SubId="+my.SubId;
				url+="&r="+Math.random();	
				var f=new funPropery();					
				f.complet=function(tx)
				{
					my.reload();					
				}
				AjaxFuncGetXML(url,f);
		}
	}
	ctTable.fucAdd=function()
	{
		
		var add=new addItemHotel();
		add.load(my.SubId);
		add.update=function()
		{
			my.reload()
		}
	}
	this.addeImage=function()
	{
		var add=new addItemHotel();
		add.load(my.SubId);
		add.update=function()
		{
			my.reload()
		}
	}
	ctTable.change=function()
	{			
		
		var obj=this.obj;		
		var url1="hotelPms?CMD=6";
		url1+="&id="+obj.Id;
		url1+="&r="+Math.random();
		var obj=this.obj;	
		var url2="hotelPms?CMD=5";
		url2+="&id="+obj.Id;
		url2+="&r="+Math.random();
		var url3="hotelPms?CMD=7";
		url3+="&id="+obj.Id;
		var change=new changeSubject(url2,url1,url3);		
		change.load(obj);
		change.complet=function()
		{
			my.reload();
		}
	}
	ctTable.fucStatus=function()
	{
		var url="hotelPms?CMD=9"
			url+="&id="+this.obj.Id;
			url+="&r="+Math.random();				
			var f=new funPropery();					
			f.complet=function(tx)
			{
				my.reload();					
			}
			AjaxFuncGetXML(url,f);
	}
	*/
	this.pageclick=function()
	{
		
	}
	var my=this;
	this.count=0;
	var ID=0;//id of subject
	this.Id="";
	var Index=0;
	this.page=5;
	this.ListCheck=new Vector();
	var arr=new Array();
	var defaults =
	{
		divName:		"id_listvod",
		col:  			5,
		classHeader:    "mytable_header",
		classItem: 		"classItem",
		classTable:		"classTable",
		classRowsel:		"mytable_row_sel",
		classTable:		"mytable",
		bgRow1:		"mytable2_row1",
		bgRow2:		"mytable2_row2"
	}	
	/**
	 * 
	 * 
	 * @param id id subject
	 * @return
	 */
	this.load=function(id)
	{		
		
		this.Id=id;
		table.init(defaults);
		my.get(id,Index);			
	}
	
	this.loadIndex=function(index)
	{		
		Index=index
		my.get(this.Id,index);		
	}
	this.Next=function()
	{	
		var page=my.page;
		var cpage=((my.count-1)/page)-1;
		if(Index<cpage)
				Index++;
		my.loadIndex(Index);
	}
	this.Back=function()
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
				item.onclick=function(e)
				{
					my.loadIndex(this.id);					
				};
			}
			var div1=$("div_page").getElementsByTagName("div");
			if(div1.length>0)
			{
				div1[0].onclick=function(){my.Back()};
				div1[1].onclick=function(){my.Next()};	
			}
		}
		//**xin nghi
		var select =$("pagesize");
		
			select.onchange=function(){
			Index=0;	
			var page=select.options[select.selectedIndex].value;
			my.page=Number(page);
			my.get(my.Id,Index);			
		};		
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
		html+="<div class='div_listvod'>"
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";		
		html+="<th align='center'  valign=\"middle\"  width=\""+6+"%\"  >";
		html+=langpms.no;
		html+="</th>";		
		html+="<th align='left' width=\""+40+"%\" valign=\"middle\"  >";
		html+=langpms.name;
		html+="</th>";
		html+="<th align='center' width=\""+30+"%\" valign=\"middle\"  >";
		html+=langpms.image;
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\"  >";
		html+=langpms.status;
		html+="</th>";		
		html+="</tr>";	
		html+="</table>";
		
		html+="<div class='div_bodytable'>"
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		for(var i=0;i<this.List.length;i++)
		{			
			
				var t=Index*my.page;
			
				if(i%2==0)
				{				
					html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\"  >";
				}else
				{
					html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\"   >";
				}					
				html+="<td align=\"center\"  valign=\"middle\"  width=\""+50+"px\">";
				html+=t+(i+1);
				html+="</td>";				
				html+="<td align=\"left\"  valign=\"middle\" width=\""+40+"%\">";
				html+=this.List[i].Name;
				html+="</td>";
				html+="<td align=\"center\"  valign=\"middle\">";
				html+="<img src=\"../resource/images/"+this.List[i].Image+"\" class='images_1' ></img>";
				html+="</td>";
				html+="<td align=\"center\"  valign=\"middle\">";
				if(this.List[i].Status==1)
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
			
		html+="</div>"
			
		html+="<div class='div_mainpage'>"
		html+="<div id=\"div_page\" class='div_page'>";
		if(my.count>my.page)
		{		
			
			var page=my.count/my.page;
			html+= createPaging(page,Index);
		}
		html+="</div>";	
		html+="<div class='div_pagesize'>"
		html+=createselect(my.page);
		html+="</div>"
		html+="</div>" //div main
			
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
		var url="ServiceSynVodStb?CMD=2"
			url+="&id="+id;
			url+="&pageIndex="+index;
			url+="&page="+my.page;
			url+="&r="+Math.random();		
			var f=new funPropery();					
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
					var _status=it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;					
					var item=new ItemVOD(_id,_name,_image);
					item.Status=_status;
					arr[i]=item;						
				}					
				table.load(arr);
				table.classCol("classItem1",1);
				table.classCol("classItem0",0);					
				my.renderEvent();				
			}			
			AjaxFuncGetXML(url,f);
	}
	this.getcurent=function()
	{		
		return this.Id;		
	}
}
function createselect(index)
{
	
	var html="";
	html+="<div class='text_pagesize'>Page Size: </div>";
	html+="<select id=\"pagesize\" size=\"1\">";
	if(index==5)
	{
		html+="<option value=\"5\" selected=\"selected\">5</option>";
	}else
		html+="<option value=\"5\">5</option>";
	if(index==10)
	{
		html+="<option value=\"10\" selected=\"selected\">10</option>";
	}else		
	html+="<option value=\"10\">10</option>";
	if(index==50)
	{
		html+="<option value=\"50\" selected=\"selected\">50</option>";
	}else
	html+="<option value=\"50\">50</option>";
	if(index==100)
	{
		html+="<option value=\"100\" selected=\"selected\">100</option>";
	}else
	html+="<option value=\"100\">100</option>";
	html+="</select>";
	return html;
}
function ItemVOD(id,name,image)
{
	this.Id=id;this.Image=image;this.Name=name;
}
function addLoadEvent()
{ 
	    window.onload = function()
	    {
	    	document.body.onclick=hotel.onclick;	    	
	    }   
}
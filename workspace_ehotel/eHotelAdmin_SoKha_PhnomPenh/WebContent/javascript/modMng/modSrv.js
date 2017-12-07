var rte=new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");	
var Mod=new function Modsrv()
{
	var tag=0;
	var my=this;
	this.sub=new subject();
	this.ctrl=false;
	this.mp3=new contentMp3();
	this.Add=new ModAdd();
	
	this.onclick=function()
	{
		my.sub.pageclick();	
		my.Add.pageclick();
		my.mp3.pageclick()
	}	
	my.sub.loadComplet=function(obj)
	{						
		if(tag==0)
		{		
			my.mp3.load(obj.Id);
			$("div_drop_sub2").innerHTML="'"+obj.Name+"'";
		}else
		{
			
			my.Add.setSubSrvMod(obj);
		}	
	}
	this.run=function()
	{
		my.sub.load()
	}	
	this.changeTab=function(id)
	{	
		var div=$("tab_menu").getElementsByTagName("div");
		tag=id;
		if(id==0)
		{			
			div[0].className="tab_menu_item_sel";
			div[1].className="tab_menu_item";	
			$("id_table").style.display="block";
			$("tag_add").style.display="none";
			my.loadList();
		}else
		{
			div[1].className="tab_menu_item_sel";
			div[0].className="tab_menu_item";
			$("tag_add").style.display="block";
			$("id_table").style.display="none";
			my.loadtagAdd();
		}		
	}
	this.loadtagAdd=function()
	{
		my.Add.load();
	}
	this.loadList=function()
	{
		my.mp3.load(my.sub.ID);
	}
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
	this.divName="menutree";
	var _row=0;
	this.ID=-1;
	this.List=new Array();
	this.Curent=null;
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
	menu.init(my.defaults);	
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
				my.Curent=my.List[i];
				my.loadComplet(my.List[i]);
				break;
			}
		}
	}
	this.addSubject=function(id)
	{		
		var textbox=new BoxSubject(lang.addSubject,"","","");			
		textbox.show();
		textbox.accept=function(text,image1,image2)
		{		
			image1=image1.substring(image1.lastIndexOf("/")+1);
			image2=image2.substring(image2.lastIndexOf("/")+1);
			if(text.length<=0)
			{
				var albox=new alertBox();
				albox.show(lang.pls_Subject);
				return;
			}
			this.hide();
			var url="ModSrvMain?CMD=1";
			url+="&t="+Math.random();
			url+="&image="+image1;
			url+="&urlBg="+image2;
			url+="&parent=-1";
			var param="&name="+encode(text);				
			var f=new AjaxPost(url,param);			
			f.complet=function(tx)
			{				
				my.reload(id);
			}
		}
	}
	this.deletesubject=function(id)
	{
		var cfBox=new conformBox();
		cfBox.show();
		cfBox.accept=function()
		{
			var url="ModSrvMain?CMD=2"
			url+="&id="+id;
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
		var textbox=new BoxSubject(lang.editSubject,obj.Name,obj.Image,obj.ImageBg);
		textbox.show();
		textbox.accept=function(text,image1,image2)
		{			
			image1=image1.substring(image1.lastIndexOf("/")+1);
			image2=image2.substring(image2.lastIndexOf("/")+1);
			
			if(text.length<=0)
			{
				var albox=new alertBox();
				albox.show(lang.pls_Subject);
				return;
			}
			this.hide();
			var url="ModSrvMain?CMD=2";
				url+="&subid="+id;
			url+="&t="+Math.random();
			url+="&image="+image1;
			url+="&urlBg="+image2;
			var param="&name="+encode(text);			
			var f=new AjaxPost(url,param);			
			f.complet=function(tx)
			{				
				my.reload(id);		
			}
		}
	}
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
			}			
		}	
	}
	this.load=function()
	{	
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
			var url="ModSrvMain?CMD=1"
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
					var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;					
					var _imageBg=it[i].getElementsByTagName("imageBg")[0].childNodes[0].nodeValue;
					arr[i]=new ItemMenu(_id,_parent,_name,_image);		
					arr[i].ImageBg=_imageBg;			
				}		
				my.List=arr;
				menu.reload(arr);		
				my.selectID(id);
				if(arr.length>0)
				{
					$("div_AddSubject").style.display="none";
				}
			}
			AjaxFuncGetXML(url,f);
	}
	this.subjectClick=function(id,name)
	{		
	}
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
				my.selectID(this.id)
				return my.showMenu(this.id,e);					
			};								
		}
	}
	this.showMenu=function(id,e)
	{
		//my.mp3.load(obj.Id,obj.Name);		
		ctMenu.load(id,e);	
		return false;
	}	
	this.get=function()
	{		
		var url="ModSrvMain?CMD=1"
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
				var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;				
				var _imageBg=it[i].getElementsByTagName("imageBg")[0].childNodes[0].nodeValue;
				arr[i]=new ItemMenu(_id,_parent,_name,_image);		
				arr[i].ImageBg=_imageBg;				
			}
			
			my.List=arr;
			menu.load(arr);
			my.selectID(arr[0].Id);
			if(arr.length>0)
			{
				$("div_AddSubject").style.display="none";
			}
		}			
		AjaxFuncGetXML(url,f);		
	}
}
function contentMp3()
{
	var table=new Table();
	var ctTable=new contextTable();
	ctTable.fucNew=function()
	{
		//var play=new playMod();
		//play.load(this.obj.Url)
		var albox=new alertBox();
		albox.show("Chuc nang nay chua mo");
	}
	
	ctTable.fucdel=function()
	{
		//my.ListCheck 
		var list="";
		if(my.ListCheck.length==0)
		{
			list+="&modId0="+this.obj;
		}else
		{
			for(i=0;i<my.ListCheck.size();i++)
			{
				list+="&modId"+i+"="+my.ListCheck.get(i);
			}
		}
		var cfBox=new conformBox();
		cfBox.show();
		cfBox.accept=function()
		{		
			var url="ModSrvMain?CMD=5"
				url+=list;
				url+="&SubId="+my.SubId;
				url+="&r="+Math.random();				
				var f=new funPropery();					
				f.complet=function(tx)
				{
					if(Common.checkRole(tx))
					my.reload();					
				}
				AjaxFuncGetXML(url,f);
		}
	}
	ctTable.fucedit=function()
	{
		var obj=this.obj;
		var detail=new detailSong();
		detail.load(obj);
		detail.update=function()
		{
			my.get(ID,Index);
		}
	}
	//them vao bo sung chuc nang play audio 16.1
	ctTable.fucPlayAudio=function(rel)
	{
		var eb = new playEmbedAudio(rel);
		eb.show(rel);
	}
	ctTable.change=function()
	{			
		var obj=this.obj;
		var change=new changeSubject();
		change.load(obj);
		change.complet=function()
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
	var Index=0;
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
	this.load=function(id)
	{		
		Index=0
		this.SubId=id;
		ID=id;
		table.init(defaults);
		my.get(id,Index);			
	}
	this.reload=function()
	{		
		Index=0
		my.get(ID,Index);	
	}
	this.loadIdex=function(index)
	{		
		Index=index
		my.get(ID,index);		
	}
	this.nextindex=function()
	{	
		var page=5;
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
		html+="<div class='div_table'>"
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";		
		html+="<th align='left'  valign=\"middle\"  width=\""+40+"%\"  >";
		html+=lang.AudioTitle;
		html+="</th>";		
//		html+="<th align='left' width=\""+20+"%\" valign=\"middle\"  >";
//		html+=lang.Singer;
//		html+="</th>";
		
//		html+="<th align='left' width=\""+30+"%\" valign=\"middle\"  >";
//		html+=lang.Album;
//		html+="</th>";		
		html+="<th align='center' width=\""+20+"%\" valign=\"middle\"  >";
		html+=lang.Status;
		html+="</th>";		
		html+="</tr>";
		
		for(var i=0;i<this.List.length;i++)
		{					
				if(i%2==0)
				{				
					html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\" oncontextmenu='mod.oncontextmenu("+this.List[i].Id+");return false;' >";
				}else
				{
					html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\" oncontextmenu='mod.oncontextmenu("+this.List[i].Id+");return false;'  >";
				}					
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=this.List[i].Title;
				html+="</td>";		
				
//				html+="<td align=\"left\"  valign=\"middle\">";
//				html+=this.List[i].Singer;
//				html+="</td>";	
				
//				html+="<td align=\"left\"  valign=\"middle\">";
//				html+=this.List[i].Album;
//				html+="</td>";					
				html+="<td align=\"center\"  valign=\"middle\">";
					html+="<img src=\"../icon/16-square-green.png\"></img>"
				html+="</td>";	
				
				html+="</tr>";
		}
		
		html+="</table>";
		html+="</div>"
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
		var url="ModSrvMain?CMD=3"
			url+="&SubId="+id;
			url+="&pageIndex="+index;
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
					var _singer=it[i].getElementsByTagName("Singer")[0].childNodes[0].nodeValue;
					var _url=it[i].getElementsByTagName("url")[0].childNodes[0].nodeValue;
					var _album=it[i].getElementsByTagName("Album")[0].childNodes[0].nodeValue;
					var _lyric=it[i].getElementsByTagName("lyric")[0].childNodes[0].nodeValue;
					arr[i]=new ItemMp3(_id,_name,_singer,_album,_url,_lyric);	
				}		
				
				table.load(arr);
				table.classCol("classItem1",1);
				table.classCol("classItem0",0);	
				table.setwidthHeader(500,0);
				my.renderEvent();
				my.loadcomplet();
			}			
			AjaxFuncGetXML(url,f);
	}
	this.loadcomplet=function()
	{		
		$("title_subject").innerHTML=Mod.sub.Curent.Name+"("+my.count+")";		
	}
}
function contextTable()
{
	var my=this;
	this.state=false;
	this.obj=null;
	this.load=function(_obj,e)
	{
		this.obj=_obj;
		var ls = this.obj.Url;
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
		my.draw(posx,posy-10,ls);
	}
	this.draw=function(x,y,ls)
	{
		//them vao 12.1
		var link = iponly + "/mp3/" + ls;
		my.state=true;
		var html="";
		html+="<ul>";
			//them vao bo sung menu play 16.1
			html+="<li class='icon_contextmenuEdit'>";
			html+="<a>"+lang.edit_song+"</a>";
			html+="</li>";
			html+="<li class='icon_contextmenuDel'>";
			html+="<a>"+lang.DeleteSong+"</a>";
			html+="</li>";			
			html+="<li class='icon_contextmenucopy'>";
			html+="<a>"+lang.changeSubject+"</a>";
			html+="</li>";
			html+="<li class='icon_contextmenuplay'>";
			html+="<a rel='"+link+"'>"+lang.checkAudio+"</a>";
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
		//var itemnew=li[0].getElementsByTagName("a")[0];
		//itemnew.onclick=function(){my.fucNew();};
		var itemdel=li[1].getElementsByTagName("a")[0];
		itemdel.onclick=function(){my.fucdel();};
		var itemchange=li[2].getElementsByTagName("a")[0];
		itemchange.onclick=function(){my.change();};
		var itemedit=li[0].getElementsByTagName("a")[0];
		itemedit.onclick=function(){my.fucedit();};
		//them vao bo sung chuc nang play nhac 16.1
		var playembed=li[3].getElementsByTagName("a")[0];
		var rellink = playembed.getAttribute("rel");
		playembed.onclick=function(){my.fucPlayAudio(rellink);};
	}
	this.hide=function()
	{
		this.state=false;
		$("div_contextMenu").style.display= "none";
	}
}
function detailSong()
{	
	var my=this;	
	var layer=new layer_vitual();
	this.obj=null;
	this.load=function(_obj)
	{	
		this.obj=_obj;
		my.show();
	}
	this.show=function()
	{						
		layer.show();			
		var html="";
			html+="<div class=\"form_detail_song\" id=\"form_detail_song\">";
			html+="<div class='div_Title_song' align=\"left\">"+lang.edit_song;					
			html+="</div>";
			html+="<div class='div_name_song'>";
			html+="<div class='left_detail'>"+lang.AudioTitle+":</div>"
			html+="<div class='right_detail'> <input type=\"text\"  id='title' class='textbox_detail' value='"+my.obj.Title+"'/></div>"
			html+="</div>";	
			html+="<div class='div_singer_song'>";	
			html+="<div class='left_detail'>"+lang.Singer+":</div>"
			html+="<div class='right_detail'> <input type=\"text\" id='singer' class='textbox_detail'  value='"+my.obj.Singer+"'/></div>"
			html+="</div>";	
			html+="<div class='div_album_song'>";	
			html+="<div class='left_detail'>"+lang.Album+":</div>"
			html+="<div class='right_detail'> <input type=\"text\" id='album' class='textbox_detail'  value='"+my.obj.Album+"'/></div>"
			html+="</div>";
			html+="<div class='div_lyric_song'>";
			html+="<div class='left_detail'>"+lang.lyric+":</div>"
			html+="<div class='right_detail' ><textarea   rows='10' cols='9' name='descriptionInput' style='width: 485px; border: 1px solid #ddddd;margin-left: 5px;float: left' id='descriptionInput' >" + my.obj.Lyric + "</textarea>";
			html+="</div>";
			html+="</div>";			
				html+="<div style='float: left;width: 100%;height:30px;' align='center'>"
					html+="<div  style='width: 170;height:30px'>";
					html+="<input type=\"button\" value='"+lang.ok+"' id=\"form_textbox_ok\" class='div_buton' >"
					html+="<input type=\"button\" value="+lang.cancel+" id=\"form_textbox_cancel\" class='div_buton'>"
					html+="</div>"
				html+="</div>";				
			html+="</div>";			
			layer.addHtml(html);	
			initTinymce(2, "420px");
			setCenterDIV("form_detail_song");
			$("form_textbox_ok").onclick=function()
			{				
				my.accept();};
			$("form_textbox_cancel").onclick=my.cancel;		
	}
	this.accept=function()
	{	
//		var title=encode($("title").value);
		var title=$("title").value;
		var singer=encode($("singer").value);
		var album=encode($("album").value);
		var _lyric=getDataFromEditor("descriptionInput");
		if(title.length==0)
		{
			var albox=new alertBox();
			albox.show("Name song is empty");
			return;
		};		
		var url="ModSrvMain?CMD=3"
			url+="&t="+Math.random();
			var param="&name="+title;
			param+="&id="+my.obj.Id;			
			param+="&singer="+singer;
			param+="&album="+album;
			param+="&lyric="+_lyric;			
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
function changeSubject()
{
	var my=this;
	this.obj=null;
	var layer=new layer_vitual();
	this.load=function(_obj)
	{
		my.obj=_obj;
		my.show();
	}
	this.show=function()
	{		
		
		layer.show();			
		var html="";
			html+="<div class=\"form_change_subject\" id=\"form_change_subject\">";
			html+="<div class='div_Title_song' align=\"center\">"+lang.ChangeSubject;			
			html+="</div>";			
			html+="<div class='change_subject_top'>";				
			html+="</div>";
			//html+="<div style='float:left;width:100%;height: auto;'>";			
			html+="<div class='change_subject_center'  >";				
				html+="<div style='float:left;width:100%;height: 180px;margin-top:10px;'>"
					html+="<div class='left_change_subject' id='left_change_subject'>";
					html+="</div>";	
					html+="<div style='float:left;width:20;' align=\"center\">"
						html+="<div class='add_subject' id='add_subject1'></div>"
						html+="<div class='remove_subject' id='remove_subject1'></div>"
					html+="</div>";
					html+="<div class='right_change_subject' id='right_change_subject'>";
					html+="</div>";
				html+="</div>"					
					html+="<div align='center' class='div_sub_buton' style='float:left;width:100%;'>";
					html+="<div style='width:160'>"
						html+="<input type=\"button\" 	' value=' "+langMain.ok+" ' id=\"form_textbox_ok\" class='div_buton'/ >"
						html+="<input type=\"button\" value="+langMain.cancel+" id=\"form_textbox_cancel\" class='div_buton'/>"
					html+="</div>"
					html+="</div>"
			html+="</div>"			
			html+="<div class='change_subject_bottom'>";				
			html+="</div>";			
			//html+="</div>";			
			html+="</div>";
			layer.addHtml(html);
			var x=0;
			var y=0;				
			x=document.body.clientWidth+document.body.scrollLeft+document.body.scrollLeft;
			y=document.body.clientHeight+document.body.scrollTop+ document.body.scrollTop;			
			$("form_change_subject").style.left=(x/2)-300+"px";
			$("form_change_subject").style.top=(y/2)-200+"px";	
			//render function
			$("form_textbox_ok").onclick=function(){
						
				my.accept();};				
			$("form_textbox_cancel").onclick=my.cancel;	
			$("add_subject1").onclick=this.addsubject;			
			$("remove_subject1").onclick=this.removesubject;
			//
			my.loadleft();
			my.loadright();				
	}
	this.loadleft=function()
	{
		
		var url="ModSrvMain?CMD=7";
			url+="&modId="+my.obj.Id;
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);
			f.complet=function(tx)
			{						
				var arr=new Array();	
				var it=tx.getElementsByTagName("Item");					
				for(var i=0;i<it.length;i++)
				{			
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
					var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
					
					arr[i]=new ItemMenu(_id,_parent,_name,_image);				
				}	
				
				var html=""
					html+="<select size=\"10\" multiple=\"multiple\" id='list_select1' class='list_select_sub'>";
				
				for(var i=0;i<arr.length;i++)
				{
					html+="<option value='"+arr[i].Id+"'>"+arr[i].Name+"</option>";
				}				
				html+="</select>";
				//alert(arr.length);
				$("left_change_subject").innerHTML=html			
				return arr;
			}
			
			
	}
	this.loadright=function()
	{
		var url="ModSrvMain?CMD=6";
		url+="&modId="+my.obj.Id;
		url+="&r="+Math.random();	
		var f=new AjaxGetXML(url);
		f.complet=function(tx)
		{		
			var arr=new Array();	
			var it=tx.getElementsByTagName("Item");		
			
			for(var i=0;i<it.length;i++)
			{			
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;				
				arr[i]=new ItemMenu(_id,_parent,_name,_image);					
			}			
			var html=""
				html+="<select size=\"10\" multiple=\"multiple\" id='list_select2' class='list_select_sub'>";			
			for(var i=0;i<arr.length;i++)
			{
				html+="<option value='"+arr[i].Id+"'>"+arr[i].Name+"</option>";
			}				
			html+="</select>";			
			$("right_change_subject").innerHTML=html;
			return arr;
		}			
	}	
	
	this.addsubject=function()
	{		
		
		var select1=$("list_select1");
		var select2=$("list_select2");
		if(select1.selectedIndex<0)
		{
			var albox=new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		}
		for(var i=0;i<select1.options.length;i++)
		{
			if (select1.options[i].selected==true)
			{								
				select2.add(new Option(select1.options[i].text,select1.options[i].value));
				select1.remove(i);
			}
		}
		
	}
	this.removesubject=function()
	{
		var select1=$("list_select1");
		var select2=$("list_select2");
		if(select2.selectedIndex<0)
		{
			var albox=new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		}
		for(var i=0;i<select2.options.length;i++)
		{
			if (select2.options[i].selected==true)
			{								
				select1.add(new Option(select2.options[i].text,select2.options[i].value));
				select2.remove(i);
			}
		}
	}
	this.accept=function()
	{
		var select2=$("list_select2");
		if(select2.options.length<=0)
		{
			var albox=new alertBox();
			albox.show(langMain.pls_selectsubject);
			return;
		}
		var param="";
		for(var i=0;i<select2.options.length;i++)
		{
			param+="&subid"+i+"="+select2.options[i].value;
		}
		var url="ModSrvMain?CMD=8";
		url+="&modId="+my.obj.Id;
		url+=param;
		url+="&r="+Math.random();	
		var f=new AjaxGetXML(url);
		f.complet=function(tx)
		{
			my.hide();	
			my.complet();			
		}	
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

//bo sung play audio 16.1
function playEmbedAudio(name)
{
	var my=this;	
	var layer=new layer_vitual();
	this.obj=null;
	this.load=function(_obj)
	{	
		this.obj=_obj;
		my.show();
	}
	this.show=function(name)
	{				
		layer.show();
		var embedlink = "<embed name='embedplay' id='embedplay' type='application/x-vlc-plugin' name='player' autoplay='false' ";
		embedlink+= "loop='no' src='"+name+"' width='450px;' height='350px;'/>";
		
		var embedie = "<object type='application/x-vlc-plugin' id='embedobj' width='450px' height='350px'";
		embedie+= " classid='clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921'>";
		embedie+= "<param id='ieparam' name='src' value='"+name+"'/>";
		embedie+= "<param name='autoplay' value='false'/>";
		embedie+= "<param name='toolbar' value='true' />";
		embedie+= "</object>";
			
		var html="<div class='form_detail_song' id='div_playEmbed' align='center' style='height: 450px;'>";
			html+="<div class='div_Title_song'>";
			html+="CHECK AUDIO LINK</div>";
				html+="<div align='center' >";
				html+=embedlink;
				html+=embedie;
				html+="</div>";
			html+="<div style='float: left;width: 100%;height:30px;' align='center'>";	
				html+="<div style='width: 70;height:30px'>";
				html+="<input type=\"button\" value='"+langMain.ok+" '  class='div_buton'id=\"button_ok\" >";	
				html+="</div>";
			html+="</div>";
		html+="</div>";
		layer.addHtml(html);
		
		//run on IE only to active vlc 16.1
		if (/*@cc_on!@*/false) {
			document.documentElement.className+=' ie10';
			document.getElementById("embedplay").style.display="none";
			document.getElementById("embedobj").style.display="inline-block";
			document.getElementById("embedobj").style.width = 450;
			document.getElementById("embedobj").style.height = 350;
		}
		else {
			document.getElementById("embedplay").style.display="inline-block";
			document.getElementById("embedobj").style.display="none";
		}
		setCenterDIV("div_playEmbed");
		$("button_ok").onclick=function()
		{						
			my.accept();
			my.hide();
		};
	}
	this.accept=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		document.getElementById("embedplay").setAttribute("src","");
		$("div_layer_vitual").style.display="none";
		document.getElementById("embedobj").removeNode(true);
		//dang co loi tu dong play audio 16.1
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
			html+="<a>"+lang.addSubject+"</a>"
			html+="</li>";
			html+="<li class='icon_contextmenuDel'>";
			html+="<a>"+lang.deleteSubject+"</a>";
			html+="</li>";			
			html+="<li class='icon_contextmenuEdit'>";
			html+="<a>"+lang.editSubject+"</a>";
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
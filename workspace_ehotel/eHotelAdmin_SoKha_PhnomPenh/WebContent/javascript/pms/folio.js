var folio=new function Folio()
{
	var my=this;	
	var stb=new SetTopBox();
	this.list = new ListAllSTB();
//	var record = new Record();
	this.onclick=function()
	{	
		room.onclick();
		stb.onclick();
		record.onclick();
	};
	this.run=function()
	{
		room.load();
	};
	this.changeTab=function(id)
	{
		var div=$("tab_menu").getElementsByTagName("div");
		for(var i=0;i<div.length;i++)
		{  
			div[i].className="tab_menu_item";
		}
		div[id].className="tab_menu_item_sel";		
		tag=id;			
		if(tag==0)
		{
			this.loadRoom();
			$("div_room").style.display="block";
			$("div_stb").style.display="none";
			$("div_record").style.display="none";
		}else if(tag==1)
		{
			this.loadSTB();
			$("div_room").style.display="none";
			$("div_stb").style.display="block";
			$("div_record").style.display="none";
		}
		else{
			this.loadRecord();
			$("div_room").style.display="none";
			$("div_stb").style.display="none";
			$("div_record").style.display="block";
		}
	};
	this.seach=function()
	{
		var key=$("text_seach").value;
		room.seach(key);
	};
	this.seachSTB=function()
	{
		var key=$("text_seachSTB").value;
		my.key = key;
		my.list.searchSTB(my.key);
	};
	this.loadRecord=function()
	{
		record.load();
	}
	this.loadSTB=function()
	{		
		stb.load();
	}
	this.loadRoom=function()
	{
		room.load();
	}
}
function SetTopBox()
{
	var my=this;
	var list=new ListAllSTB();
	this.load=function(id)
	{
		list.load();
	}
	this.onclick=function()
	{
		list.pageclick();
	}
}
function ListAllSTB()
{
	var my=this;
	var Index=0;
	this.page=7;
	var table=new Table();
	var ctTable=new contextTableSTB();
	this.count= 0;
	//bo sung 20.8 philao
	this.onlcnt = 0;
	
	this.ListCheck=new Vector();
	this.load=function()
	{		
		Index=0;
		my.get("");
	};
	this.searchSTB=function(key){
		my.get(key);
	};
	this.loadIdex=function(index)
	{				
		Index=index;
		my.loadData();		
	};
	this.nextindex=function()
	{	
		var cpage=((my.count-1)/my.page)-1;
		if(Index<cpage)
				Index++;
		my.loadIdex(Index);
	};
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIdex(Index);
	};
	this.pageclick=function()
	{
		if(ctTable.state)ctTable.hide();
	}
	this.reload=function()
	{
		my.get(roomId);
	}
	var defaults =
	{
		divName:		"div_tablestb",
		col:  			5,
		classHeader:    "mytable_header",
		classItem: 		"classItem",
		classTable:		"classTable",
		classRowsel:		"mytable_row_sel",
		classTable:		"mytable",
		bgRow1:		"mytable_row1",
		bgRow2:		"mytable_row2"
	}
	this.loadData=function()
	{
		var b=Index*my.page;
		var e=(Number(Index)+1)*my.page;
		if(e>my.count)e=my.count;
		var arr=new Array();
		for(var i=b;i<e;i++)
		{
			arr[i-b]=my.list[i];
		}
		table.load(arr);
		my.renderEvent();
	}
	table.init(defaults);	
	table.dataBind=function()
	{		
		var html="";
		html+="<div class='div_listallstb'>";
		html+="<span style='float: right;margin-left: 20px;margin-top: 10px;color: #878888;font-size: 22px;' id='title_subject'>";
		html+="Online:";
		html+=my.onlcnt;
		html+="</span>";
		html+="<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";		
		html+="<th align='center' valign=\"middle\" width=\""+8+"%\" >";
		html+=langpms.no;
		html+="</th>";
		html+="<th align='left' width=\""+30+"%\" valign=\"middle\" >";
		html+=langpms.smartcard;
		html+="</th>";
		html+="<th align='left' width=\""+20+"%\" valign=\"middle\" >";
		html+="IP";
		html+="</th>";	
		html+="<th align='left' width=\""+10+"%\" valign=\"middle\" >";		
		html+=langpms.room;
		html+="</th>";
//		html+="<th align='center' width=\""+20+"%\" valign=\"middle\" >";
//		html+=langpms.create_date;
//		html+="</th>";
		//bo sung status cua stb 26.2
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\" >";
		html+=langpms.status;
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\">";
		html+=langpms.check;
		html+="</th>";
		
		html+="</tr>";
		var begin=Index*my.page;
		//lay stb onl hien thi truoc 20.8
		for(var i=0;i<this.List.length;i++)
		{	
			if (this.List[i].Status == '1') {
				if(i%2==0)
				{				
					html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\">";
				}else
				{
					html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\">";
				}	
				html+="<td align=\"center\" valign=\"middle\">";
				html+=begin+i+1;
				html+="</td>";					
				html+="<td align=\"left\" valign=\"middle\">";
				html+=this.List[i].Key;
				html+="</td>";	
				html+="<td align=\"left\" valign=\"middle\">";
				html+=this.List[i].Ip;
//				alert(this.List[i].Ip);
				html+="</td>";	
				html+="<td align=\"lefts\" valign=\"middle\">";				
				html+=this.List[i].RoomCode;				
				html+="</td>";	
//				html+="<td align=\"center\" valign=\"middle\">";				
//				html+=this.List[i].Date;				
//				html+="</td>";
				//bo sung status cua stb 26.2
				html+="<td align=\"center\" valign=\"middle\">";
				if(this.List[i].Status==1)
				{
					html+="<img id='"+this.List[i].Key+"' src=\"../icon/16-square-green.png\"></img>";
				}else
				{
					html+="<img id='"+this.List[i].Key+"' src=\"../icon/16-square-red.png\"></img>";
				}
				html+="</td>";
				
				html += "<td  valign=\"middle\" style='text-align:center'  title='Ping IP STB'>";
				html+="<img src=\"../icon/check.png\" style='width:20px;' onclick=checkPing("+"'"+ this.List[i].Ip +"'"+","+"'"+ this.List[i].Key +"'"+") ></img>";
				html += "</td>";	
				
				html+="</tr>";
			}
		}
		for(var i=0;i<this.List.length;i++)
		{	
			if (this.List[i].Status != '1') {
				if(i%2==0)
				{				
					html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\">";
				}else
				{
					html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\">";
				}	
				html+="<td align=\"center\" valign=\"middle\">";
				html+=begin+i+1;
				html+="</td>";					
				html+="<td align=\"left\" valign=\"middle\">";
				html+=this.List[i].Key;
				html+="</td>";	
				html+="<td align=\"left\" valign=\"middle\">";
				html+=this.List[i].Ip;
				html+="</td>";
				html+="<td align=\"lefts\" valign=\"middle\">";				
				html+=this.List[i].RoomCode;				
				html+="</td>";
//				html+="<td align=\"center\" valign=\"middle\">";				
//				html+=this.List[i].Date;				
//				html+="</td>";
				//bo sung status cua stb 26.2
				html+="<td align=\"center\" valign=\"middle\">";
				if(this.List[i].Status==1)
				{
					html+="<img id='"+this.List[i].Key+"' src=\"../icon/16-square-green.png\"></img>";
				}else
				{
					html+="<img id='"+this.List[i].Key+"' src=\"../icon/16-square-red.png\"></img>";
				}
				html+="</td>";
				
				html+="<td align=\"lefts\" valign=\"middle\" style='text-align:center'>";			
				html+="<img  src=\"../icon/check.png\" style='width:20px;' onclick=checkPing("+"'"+ this.List[i].Ip +"'"+","+"'"+ this.List[i].Key +"'"+") ></img>";	
				html+="</td>";	
				
				html+="</tr>";
			}
		}		
		html+="</table>";		
		html+="</div>";
		html+="<div id=\"div_page2\" class='div_page' style='margin-top: 5px;'>";
		if(my.count>my.page)
		{
			var page=my.count/my.page;
			html+= createPaging(page,Index);
		}			
		html+="</div>";
		html+="<div class='div_bottom_table'></div>";
		return html;
	};
	this.checkpping = function(ip){
		
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
				/*
				if(Common.ctrl)
				{
					my.addItem(this.id);
					
				}else
				{
					my.selectItem(this.id);
				}
				//my.oncontextmenu(this.id,e);
				*/
				return false;
			};
		};
		if($("div_page2")!=undefined)
		{
			var div=$("div_page2");
			var a=div.getElementsByTagName("a");
			for(var i=0;i<a.length;i++)
			{
				var item=a[i];				
				item.onclick=function(e){
					my.loadIdex(this.id);					
				};
			}
			var div1=$("div_page2").getElementsByTagName("div");
			if(div1.length>0)
			{
				div1[0].onclick=function(){my.backindex();};
				div1[1].onclick=function(){my.nextindex();};	
			}
		}
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
	this.selectItem=function(id)
	{				
		my.ListCheck=new Vector();
		my.ListCheck.add(id);				
		my.shiftSelect();
	}
	this.list=new Array();	
	var wt=new Waiting();
	this.get=function(id)
	{
		var cntonl = 0;
		var url="folioPms?CMD=10";
			url+="&pageIndex="+Index;
			url+="&id="+id;
			url+="&page="+my.page;
			url+="&r="+Math.random();	
			wt.show(null);			
			var f=new AjaxGetXML(url);				
			f.complet=function(tx)
			{					
				var xml=tx.getElementsByTagName("xml");	
				var it=tx.getElementsByTagName("Item");	
				my.count= xml[0].getAttribute("count");	
				for(var i=0;i<it.length;i++)
				{						
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _ip=it[i].getElementsByTagName("ip")[0].childNodes[0].nodeValue;
					var _keycode=it[i].getElementsByTagName("keycode")[0].childNodes[0].nodeValue;
					var _date=it[i].getElementsByTagName("date")[0].childNodes[0].nodeValue;
					var _status=it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
					var _roomcode=it[i].getElementsByTagName("roomcode")[0].childNodes[0].nodeValue;
					my.list[i]=new itemSTB(_id,_ip,_keycode,_date,_status);
					my.list[i].RoomCode=_roomcode;
					if (_status == '1') cntonl+=1;
				}
				my.onlcnt = cntonl;
				my.loadData();
				my.renderEvent();
				wt.hide();
			}
	}
}
function contextTableSTB()
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
		html+="<a>Detail Item</a>";
		html+="</li>";
		html+="<li class='icon_contextmenuplay'>";
		html+="<a>Add Item</a>";
		html+="</li>";
		html+="<li class='icon_contextmenuDel'>";
		html+="<a>Delete item</a>";
		html+="</li>";			
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
		var fucAdd=li[1].getElementsByTagName("a")[0];
		fucAdd.onclick=function(){my.fucAdd();};
		var fucDetail=li[0].getElementsByTagName("a")[0];
		fucDetail.onclick=function(){my.fucDetail();};
		var fucdel=li[2].getElementsByTagName("a")[0];
		fucdel.onclick=function(){my.fucdel();};
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

function checkPing(ip, id) {
	if (ip.length > 0) {
		var wt=new Waiting();
		var url = "folioPms?CMD=22";
		url += "&ip=" + ip;
		url += "&r=" + Math.random();
		wt.show(null);
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			wt.hide();
			var albox = new successBox();
			if(tx == "success"){
				albox.show("Connect");
				document.getElementById(id).src = "../icon/16-square-green.png";
			}else{
				albox.show("Disconnect");
			}
		};
	}else{
		var al = new alertBox();
		al.show("Ip is null!");
	}
}

window.onload = function()
{
	folio.run();	
	document.body.onclick=folio.onclick;
	$("text_seach").value="";
};
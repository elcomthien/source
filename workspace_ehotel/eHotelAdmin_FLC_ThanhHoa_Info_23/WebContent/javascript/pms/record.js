var record = new function Record()
{
//	var my = this;
	var list=new ListAllRecord();
	this.load=function(id)
	{
		list.load();
	};
	this.onclick=function()
	{
		list.pageclick();
	};
};
function ListAllRecord()
{
	var my=this;
	var Index=0;
	this.page=7;
	var table=new Table();
	var ctTable=new contextTableRecord();
	this.count= 0;
	//bo sung 20.8 philao
	this.onlcnt = 0;
	
	this.ListCheck=new Vector();
	this.load = function() {
		Index = 0;
		my.get();
	}
	this.loadIdex = function(index) {
		Index = index;
		my.loadData();
	}
	this.nextindex = function() {
		var cpage = ((my.count - 1) / my.page) - 1;
		if (Index < cpage)
			Index++;
		my.loadIdex(Index);
	}
	this.backindex = function() {
		if (Index > 0)
			Index--;
		my.loadIdex(Index);
	}
	this.pageclick = function() {
		if (ctTable.state)
			ctTable.hide();
	}
	this.reload = function() {
		my.get(roomId);
	}
	var defaults = {
		divName : "div_tablerecord",
		col : 5,
		classHeader : "mytable_header",
		classItem : "classItem",
		classTable : "classTable",
		classRowsel : "mytable_row_sel",
		classTable : "mytable",
		bgRow1 : "mytable_row1",
		bgRow2 : "mytable_row2"
	}
	this.loadData = function() {
		var b = Index * my.page;
		var e = (Number(Index) + 1) * my.page;
		if (e > my.count)
			e = my.count;
		var arr = new Array();
		for ( var i = b; i < e; i++) {
			arr[i - b] = my.list[i];
		}
		table.load(arr);
		my.renderEvent();
	}
	table.init(defaults);	
	table.dataBind=function()
	{		
		var html="";
		html+="<div class='div_listallstb'>";
		html+="<span style='float: left;margin-left: 20px;margin-top: 10px;color: #878888;font-size: 22px;' id='title_subject'>";
		html+="Record: ";
		html+=my.onlcnt;
		html+="</span>";
		html+="<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";		
		html+="<th align='center' valign=\"middle\" width=\""+6+"%\" >";
		html+="No";
		html+="</th>";
		html+="<th align='center' width=\""+18+"%\" valign=\"middle\" >";		
		html+="Channel";
		html+="</th>";
		html+="<th align='center' width=\""+20+"%\" valign=\"middle\" >";		
		html+="Private";
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\" >";
		html+="SerNumber";
		html+="</th>";	
		html+="<th align='center' width=\""+19+"%\" valign=\"middle\" >";		
		html+="Start";
		html+="</th>";
		html+="<th align='center' width=\""+19+"%\" valign=\"middle\" >";
		html+="Stop";
		html+="</th>";
		html+="<th align='center' width=\""+8+"%\" valign=\"middle\" >";
		html+="Status";
		html+="</th>";
		html+="</tr>";	
		var begin=Index*my.page;
		for ( var i = 0; i < this.List.length; i++) {

			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\">";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\">";
			}
			html += "<td align=\"center\" valign=\"middle\">";
			html += begin + i + 1;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].ChannelName;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].PrivateChannelName;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].SerNumber;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].StartTime;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
			html += this.List[i].StopTime;
			html += "</td>";
			html += "<td align=\"center\" valign=\"middle\">";
//			html += this.List[i].Status;
			 if(this.List[i].Status==0)
			 {
			 html+="<img src=\"../images/0.png\" width='20px;' title='New record'></img>";
			 }else if(this.List[i].Status==1)
			 {
				 html+="<img src=\"../images/1.png\" width='20px;' title='Out of memory'></img>";
				 html+="<img src=\"../images/4.png\" width='20px;' style='margin-left: 10px;' title='Change status' onclick='clickUpdateStatus("+this.List[i].Id+")'></img>";
			 }else if(this.List[i].Status==2)
			 {
				 html+="<img src=\"../images/2.png\" width='20px;' title='Recording'></img>";
			 }else if(this.List[i].Status==3)
			 {
				 html+="<img src=\"../images/3.png\" width='20px;' title='Success' ></img>";
				 html+="<img src=\"../images/4.png\" width='20px;' style='margin-left: 10px;' title='Change status' onclick='clickUpdateStatus("+this.List[i].Id+")'></img>";
			 }else if(this.List[i].Status==4)
			 {
				 html+="<img src=\"../images/5.png\" width='20px;' title='Waiting delete'></img>";
			 }
			html += "</td>";
			html += "</tr>";
		}
			
		html+="</table>";		
		html+="</div>";
		html+="<div id=\"div_page3\" class='div_page' style='margin-top: 5px;'> ";
		if (my.count > my.page) {
			var page = my.count / my.page;
			html += createPaging(page, Index);
		}			
		html+="</div>";
		html+="<div class='div_bottom_table'></div>";
		return html;
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
		if($("div_page3")!=undefined)
		{
			var div=$("div_page3");
			var a=div.getElementsByTagName("a");
			for(var i=0;i<a.length;i++)
			{
				var item=a[i];				
				item.onclick=function(e){
					my.loadIdex(this.id);					
				};
			}
			var div1=$("div_page3").getElementsByTagName("div");
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
		var url="folioPms?CMD=17";
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
					var id = it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var channelname = it[i].getElementsByTagName("channelname")[0].childNodes[0].nodeValue;
					var urlrecord = it[i].getElementsByTagName("urlrecord")[0].childNodes[0].nodeValue;
					var sernumber = it[i].getElementsByTagName("sernumber")[0].childNodes[0].nodeValue;
					var starttime = it[i].getElementsByTagName("starttime")[0].childNodes[0].nodeValue;
					var stoptime = it[i].getElementsByTagName("stoptime")[0].childNodes[0].nodeValue;
					var status = it[i].getElementsByTagName("status")[0].childNodes[0].nodeValue;
					var privatechannelname = it[i].getElementsByTagName("privatechannelname")[0].childNodes[0].nodeValue;
					var sizeinkb = it[i].getElementsByTagName("sizeinkb")[0].childNodes[0].nodeValue;
					var urlpicture = it[i].getElementsByTagName("urlpicture")[0].childNodes[0].nodeValue;
					
					my.list[i]=new itemRecord(id,channelname,urlrecord,sernumber,starttime,stoptime,status,privatechannelname,sizeinkb,urlpicture);
					cntonl += 1;
				}
				my.onlcnt = cntonl;
				my.loadData();
				my.renderEvent();
				wt.hide();
			}
	}
}
function contextTableRecord()
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
function clickUpdateStatus(id) {
	var cfBox = new conformBox();
	cfBox.show();
	cfBox.accept = function() {
		var wt = new Waiting();
		var url = "folioPms?CMD=19";
		url += "&id=" + id;
		url += "&r=" + Math.random();
		wt.show(null);
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
		};
		folio.changeTab(2);
		wt.hide();
	}
}
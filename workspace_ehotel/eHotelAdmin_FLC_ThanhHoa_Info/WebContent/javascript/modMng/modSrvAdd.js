//var wt=new Waiting();
var tablectn=new TableCtn();
var tablesrv=new TableSrv();
var temp=0;
var tempsrc=0;
var temrowId=0;
var arraySrv;
var temrowRemoveId=0;
var arrColorTemp=new Array();
var arrColorLive=new Array();
var arrColorLiveTmp=new Array();
var arrColorLiveRemove=new Array();
var demLiveAdd=0;
var demLiveRemove=0;
var arrColorLiveRemoveTmp=new Array();
function isKeyPressedModAdd(event,id){
	
	var table = document.getElementById("tableCtn");
	var tr = table.getElementsByTagName("tr");	
		for(var i=0;i<tr.length;i++){
		if (event.ctrlKey==1)
		  {
		  		if(tr[i].id==id){
				  		if(checkId(arrColorLive,id,i)==0 || checkId(arrColorLive,id,i)==2 ){
				  			if(checkId(arrColorLive,id,i)==2){
				  				for(var k=0;k<arrColorLive.length;k++){
				  					if(arrColorLive[k].Id==i){
				  						arrColorLive[k].ItemId=id;
				  						break;
				  					}
				  				}
				  			}else{
					  		arrColorLive[demLiveAdd]=new ItemCtrl(i,tr[i].id);
					  		demLiveAdd=demLiveAdd+1;
					  		}
				  		for(var j=0;j<arrColorLive.length;j++){
				  			if(arrColorLive[j].ItemId!=0){
				  				var t=arrColorLive[j].Id;
				  				tr[t].className="mytable_row_sel";
				  			}
				  		}
				  		}else{
				  				for(var p=0;p<arrColorLive.length;p++){
				  					if(arrColorLive[p].Id==i){
				  						var f=arrColorLive[p].Id;
				  						arrColorLive[p].ItemId=0;
				  						tr[f].className="mytable_row1";
				  						break;
				  					}
				  				}
				  		}
			  	}	
		  }
		else
		  {
		  	if(event.button==0 ||event.button==1 ){
		  	demLiveAdd=0;
		  	arrColorLive= new Array();
			  	var tr=$("tableCtn").getElementsByTagName("tr");		
				for(var i=1;i<tr.length;i++)
				{
					if(i%2==0)
					{
						tr[i].className="mytable_row1";
					}else
					{
						tr[i].className="mytable_row2";
					}
					if(tr[i].id==id){
					temrowId=id;
					tr[i].className="mytable_row_sel";
					}
				}
		  	}else{
		  		demLiveAdd=0;
		  	}
		  	break;
		  }
		 }
		 arrColorLiveTmp=arrColorLive;
	}
function checkId(arr,id,i){
	var k=0;
	for(var y=0;y<arr.length;y++){
		if(arr[y].Id==i)
		{
			if(arr[y].ItemId==id){
				k=1;
				break;
			}else{
				k=2;
			break;
			}
			
			break;
		}
	}
	return k;
	}
	function ItemCtrl(id,itemId){
	this.Id=id;this.ItemId=itemId;
	}
	function addsubject(){
		var str="";	
		for(var i=0;i<arrColorLiveTmp.length;i++){
			if(arrColorLiveTmp[i].ItemId!=0){
				if(i==arrColorLiveTmp.length-1){
				str+=arrColorLiveTmp[i].ItemId;
				}else{
				str+=arrColorLiveTmp[i].ItemId +",";
				}
			}
		}
		if(str.length==0){
		str=temrowId;
		}else{
			if(temrowId!=0){
				str=str +","+ temrowId;
			}
		}
		var url="ModSrvMain?CMD=4";
		url+="&SubId="+ tempsrc;
		url+="&vodId="+str;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{			
			tablesrv.reload();
		}
		arrColorLiveRemove=new Array();
		arrColorLiveRemoveTmp=new Array();
		arrColorLiveTmp=new Array();
		arrColorLive =new Array(); 
		demLiveAdd=0;
		demLiveRemove=0;
		temrowRemoveId=0;
		temrowId=0;
		tablectn.load(temp);	
}
	function removesubject(){
			var str="";	
			for(var i=0;i<arrColorLiveRemoveTmp.length;i++){
				if(arrColorLiveRemoveTmp[i].ItemId!=0){
					if(i==arrColorLiveRemoveTmp.length-1){
					str+=arrColorLiveRemoveTmp[i].ItemId;
					}else{
					str+=arrColorLiveRemoveTmp[i].ItemId +",";
					}
				}
			}
			if(str.length==0){
			str=temrowRemoveId;
			}else{
				if(temrowRemoveId!=0){
					str=str +","+ temrowRemoveId;
				}
			}
				var url="ModSrvMain?CMD=5";
				url+="&SubId="+tempsrc;
				url+="&vodId="+str;
				url+="&r="+Math.random();	
				var f=new AjaxGetText(url);
				f.complet=function(tx)
				{
					tablesrv.reload();	
				}
		arrColorLiveRemove=new Array();
		arrColorLiveRemoveTmp=new Array();
		arrColorLiveTmp=new Array();
		arrColorLive =new Array(); 
		demLiveAdd=0;
		demLiveRemove=0;
		temrowRemoveId=0;
		temrowId=0;
		tablectn.load(temp);	
	}
	function isKeyPressedModRemove(event,id){
		var table = document.getElementById("tableSrv");
		var tr = table.getElementsByTagName("tr");	
		for(var i=0;i<tr.length;i++){
		if (event.ctrlKey==1)
		  {
		  		if(tr[i].id==id){
				  		if(checkId(arrColorLiveRemove,id,i)==0 || checkId(arrColorLiveRemove,id,i)==2 ){
				  			if(checkId(arrColorLiveRemove,id,i)==2){
				  				for(var k=0;k<arrColorLiveRemove.length;k++){
				  					if(arrColorLiveRemove[k].Id==i){
				  						arrColorLiveRemove[k].ItemId=id;
				  						break;
				  					}
				  				}
				  			}else{
					  		arrColorLiveRemove[demLiveRemove]=new ItemCtrl(i,tr[i].id);
					  		demLiveRemove=demLiveRemove+1;
					  		}
				  		for(var j=0;j<arrColorLiveRemove.length;j++){
				  			if(arrColorLiveRemove[j].ItemId!=0){
				  				var t=arrColorLiveRemove[j].Id;
				  				tr[t].className="mytable_row_sel";
				  			}
				  		}
				  		}else{
				  				for(var p=0;p<arrColorLiveRemove.length;p++){
				  					if(arrColorLiveRemove[p].Id==i){
				  						var f=arrColorLiveRemove[p].Id;
				  						arrColorLiveRemove[p].ItemId=0;
				  						tr[f].className="mytable_row1";
				  						break;
				  					}
				  				}
				  		}
			  	}	
		  }
		else
		  {
		  	if(event.button==0 ||event.button==1 ){
		  	demLiveRemove=0;
		  	arrColorLiveRemove= new Array();
			  	var tr=$("tableSrv").getElementsByTagName("tr");		
				for(var i=1;i<tr.length;i++)
				{
					if(i%2==0)
					{
						tr[i].className="mytable_row1";
					}else
					{
						tr[i].className="mytable_row2";
					}
					if(tr[i].id==id){
					temrowRemoveId=id;
					tr[i].className="mytable_row_sel";
					}
				}
		  	}else{
		  	demLiveRemove=0;
		
		  	}
		//  pageclick();
		  	break;
		  }
		 // alert(arrColor.length);
		 }
		 arrColorLiveRemoveTmp=arrColorLiveRemove;
}
function ModAdd()
{	
	var my=this;
	var ctnSubject=new dropSubject();
	
	this.listCtn=new Array();
	this.pageclick=function()
	{
		if(ctnSubject.state)ctnSubject.hide();		
	}
	this.load=function()
	{		
		renderEvent();
		tablesrv.load(Mod.sub.ID);
		tempsrc=Mod.sub.ID;
	}
	this.setSubSrvMod=function(obj)
	{
		$("div_drop_sub2").innerHTML=obj.Name;
		tablesrv.load(obj.Id);
		tempsrc=obj.Id;
	}
	renderEvent=function()
	{
		$("div_drop_sub1").onclick=function(e)
		{				
			ctnSubject.load(e);
		}
	}
	tablectn.addComplet=function()
	{
		tablesrv.reload();
	}
	ctnSubject.Select=function(obj)
	{
		$("div_drop_sub1").innerHTML=obj.Name;
		tablectn.load(obj.Id);
		temp =obj.Id;		
	}
}
function TableSrv()
{
	var my=this;
	this.List=new Vector();
	this.ID=-1;
	var divName="tableSrv";
	var Index=0;
	this.count=0;
	var page=5;
	this.load=function(id)
	{
		this.List.Empty();		
		my.ID=id;
		my.get(Index);
	}
	this.reload=function()
	{
		this.List.Empty();		
		my.get(Index);
	}
	this.loadIndex=function(index)
	{	
		this.List.Empty();
		Index=index;	
		this.get(Index);
	}
	this.nextindex=function()
	{				
		var cpage=Math.round(((my.count-1)/page)-0.4);
		if(my.count%5==0)
		{
			cpage=Math.round(((my.count-1)/page)-0.4);
		}else
		{
			cpage=Math.round(((my.count-1)/page)-0.4);
		}
		if(Index<cpage)
				Index++;
		my.loadIndex(Index);
	}
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIndex(Index);
	}
	this.loadIndex=function(index)
	{	
		this.List.Empty();
		Index=index;	
		this.get(Index);
	}
	this.show=function()
	{						
		var html="<table class='table2' border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		html+="<thead class='fixedHeader'>";
		html+="<tr>";
				html+="<th style='padding-left:5px' align=\"left\" >";
					html+=lang.AudioTitle;
				html+="</th>";
				html+="<th >";
					html+=lang.Status;
				html+="</th>";
			html+="</tr>";	
			html+="</thead>";
			html+="<tbody class='scrollContent'>";
		    for(var i=0;i<my.List.size();i++)
			{
				if(i%2==0)
				{
					html+="<tr style='padding-left:5px' onmousedown='isKeyPressedModRemove(event,"+ my.List.get(i).Id +")' class='mytable_row2' id='"+this.List.get(i).Id+"'>";
				}else
				{
					html+="<tr style='padding-left:5px' onmousedown='isKeyPressedModRemove(event,"+ my.List.get(i).Id +")' class='mytable_row1' id='"+this.List.get(i).Id+"'>";
				}				
					html+="<td>";
					html+=my.List.get(i).Title;
					html+="</td>";
					html+="<td align=\"center\">";
					html+="</td>";
					html+="<td align=\"center\">";
						html+="<img src=\"../icon/16-square-green.png\"></img>";
					html+="</td>";
				html+="</tr>";
			}
			html+="</tbody>";
			html+="</table>";
		    $(divName).innerHTML="";
			$(divName).innerHTML=html;			
			my.renderEvent();
	}
	this.renderEvent=function()
	{
		if($("div_page_add2")!=undefined)
		{
			var div=$("div_page_add2");
			var a=div.getElementsByTagName("a");
			for(var i=0;i<a.length;i++)
			{
				var item=a[i];				
				item.onclick=function(e)
				{		
					my.loadIndex(this.id);				
				};
			}
			$("div_page_srv_right").onclick=function(){my.nextindex()};
			$("div_page_srv_left").onclick=function(){my.backindex()};
		}
		//table
			var tr=$(divName).getElementsByTagName("tr");			
			for(var i=1;i<tr.length;i++)
			{
				var item=tr[i];				
				item.onclick=function(e)
				{				
					//my.selectRow(this);
				};					
				item.ondblclick=function()
				{					
					my.removeService(this.id)
					return false;
				}
			};			
	}
	this.removeService=function(id)
	{
		var url="ModSrvMain?CMD=5"
			url+="&SubId="+Mod.sub.ID;
			url+="&vodId="+id
			url+="&r="+Math.random();
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{
				if((my.count-1)%5==0)
				{
					if(Index>0)
					Index--;
				}
				my.reload();
			}
	}
	
	this.get=function(index)
	{		
		var url="ModSrvMain?CMD=9"
			url+="&SubId="+my.ID
			url+="&pageIndex="+index;
			url+="&r="+Math.random();		
		var f=new funPropery();			
		f.complet=function(tx)
		{					
			var arr=new Array();	
			var it=tx.getElementsByTagName("Item");	
			var xml=tx.getElementsByTagName("xml");	
			my.count= xml[0].getAttribute("count");	
			for(var i=0;i<it.length;i++)
			{					
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _singer=it[i].getElementsByTagName("Singer")[0].childNodes[0].nodeValue;
				var _url=it[i].getElementsByTagName("url")[0].childNodes[0].nodeValue;
				var _album=it[i].getElementsByTagName("Album")[0].childNodes[0].nodeValue;
				var _lyric=it[i].getElementsByTagName("lyric")[0].childNodes[0].nodeValue;
				var item=new ItemMp3(_id,_name,_singer,_album,_url,_lyric);	
				my.List.add(item);
			}	
			my.show();
		}
		AjaxFuncGetXML(url,f);		
	} 
}
function TableCtn()
{
	var my=this;
	this.List=new Vector();
	var Index=0;
	this.CtnId=-1;
	this.Srvid=-2;
	var divName="tableCtn";
	var page=5;
	this.init=function()
	{		
	}
	this.load=function(ctn)
	{
		this.CtnId=ctn;
		my.List=new Vector();
		Index=0;
		this.get(Index);
	}
	this.nextindex=function()
	{				
		var cpage=Math.round(((my.count-1)/page)-0.4);
		if(my.count%5==0)
		{
			cpage=Math.round(((my.count-1)/page)-0.4);
		}else
		{
			cpage=Math.round(((my.count-1)/page)-0.4);
		}
		if(Index<cpage)
				Index++;
		my.loadIndex(Index);
	}
	this.loadIndex=function(index)
	{	
		this.List.Empty()
		Index=index;	
		this.get(Index)
	}
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIndex(Index);
	}
	this.show=function()
	{	
		var html="<table class='table2' border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		html+="<thead class='fixedHeader'>";
		html+="<tr>";
				html+="<th  align=\"left\" style='padding-left:5px' >";
					html+=lang.AudioTitle;
				html+="</th>";
				html+="<th >";
					html+=lang.Status;
				html+="</th>";
			html+="</tr>";	
			html+="</thead>";
			html+="<tbody class='scrollContent'>";
		    for(var i=0;i<my.List.size();i++)
			{
				if(i%2==0)
				{
					html+="<tr style='padding-left:5px' onmousedown='isKeyPressedModAdd(event,"+my.List.get(i).Id+")'  class='mytable_row2' id='"+this.List.get(i).Id+"'>";
				}else
				{
					html+="<tr style='padding-left:5px'  onmousedown='isKeyPressedModAdd(event,"+my.List.get(i).Id+")' class='mytable_row1' id='"+this.List.get(i).Id+"'>";
				}				
					html+="<td>";
					html+=my.List.get(i).Title;
					html+="</td>";
					html+="<td align=\"center\">";
					html+="</td>";
					html+="<td align=\"center\">";
						html+="<img src=\"../icon/16-square-green.png\"></img>";
					html+="</td>";
				html+="</tr>";
			}
		    html+="</tbody>";
			html+="</table>";	
		    $(divName).innerHTML=html;			
			this.renderEvent();
	}
	this.renderEvent=function()
	{
		if($("div_page_add")!=undefined)
		{
			var div=$("div_page_add");
			var a=div.getElementsByTagName("a");
			for(var i=0;i<a.length;i++)
			{
				var item=a[i];				
				item.onclick=function(e)
				{		
					my.loadIndex(this.id);				
				};
			}
			$("div_page_ctn_right").onclick=function(){my.nextindex();};
			$("div_page_ctn_left").onclick=function(){my.backindex();};
		}
			//table
			var tr=$(divName).getElementsByTagName("tr");			
			for(var i=1;i<tr.length;i++)
			{
				var item=tr[i];				
				item.onclick=function(e)
				{			
					//my.selectRow(this);
				};		
				item.ondblclick=function()
				{					
					my.addToService(this.id);
					return false;
				}
			};	
	}
	this.addToService=function(id)
	{
		var url="ModSrvMain?CMD=4";
			url+="&SubId="+Mod.sub.ID;
			url+="&vodId="+id;
			url+="&r="+Math.random();
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{
				my.addComplet();
			}
	}
	//lay danh sach ben music service 28.1
	this.get=function(index)
	{					
		var url="../Content/ModCtnMain?CMD=2";
		url+="&SubId="+this.CtnId;
		url+="&pageIndex="+ "-1";
		url+="&r="+Math.random();	
		var f=new AjaxGetXML(url);			
		f.complet=function(tx)			{						
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
				var  item=new ItemMp3(_id,_name,_singer,_album,_url,_lyric);
				my.List.add(item);
			}					
			my.show();			
		}
	}
}
//dang dung o day
function dropSubject()
{	
	var my=this;
	var menu=new Tree();
	this.List=new Array();
	this.state=false;
	this.SubID=-1;
	this.defaults =
	{
		divName:		"div_drop_subject",
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
	this.load=function(e)
	{	
		my.show(e);
	}
	this.show=function(e)
	{
		my.state=false;		
		var div=null;
		if($("div_drop_subject")!=undefined)
		{
			div=$("div_drop_subject");
			div.style.display="block";
		}else
		{
			div=document.createElement("div");	
		}		
		
		div.className="div_drop_subject";		
		div.id="div_drop_subject";
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
		div.style.left=posx+"px";
		div.style.top=posy+"px";	
		document.body.appendChild(div);			
		menu.load(my.List);
		menu.renderEvent();
		setTimeout(function(){my.state=true;},500);
	}
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
				var type=this.getAttribute("pos");
				if(type=="r")my.pause();
				my.ClickItem(this.id);					
				me.change(this.id);
			};											
		}		
	}
	this.pause=function()
	{
		this.state=false;
		setTimeout(function(){my.state=true;},500);
	}
	this.ClickItem=function(id,type)
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
		if(obj.Parent)
		my.Select(obj);
	}
	this.hide=function()
	{
		if(this.state)
		{
			$("div_drop_subject").style.display="none";
			this.state=false;
		}
	}
	
	this.getCtn=function()
	{						
		var url="../Content/ModCtnMain?CMD=1"
		url+="&r="+Math.random();	
		var f=new AjaxGetXML(url);
		f.complet=function(tx)
		{					
			var it=tx.getElementsByTagName("Item");		
			for(var i=0;i<it.length;i++)
			{			
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;	
				var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;	
				my.List[i]=new ItemMenu(_id,_parent,_name,_image);				
			}	
			my.ClickItem(my.List[0].Id);
		}
	}
	this.init=new function()
	{
		my.getCtn();
	}
}
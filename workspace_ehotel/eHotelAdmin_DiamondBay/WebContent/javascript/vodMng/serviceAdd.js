var temp=0;
var tempsrc=0;
var temrowId=0;
var arraySrv;
var temrowRemoveId=0;
var dropSubCtn=new dropSubjectCtn();
var dropSubSrv=new dropSubject();		
var tableSrv=new TableSrv();
var tableCtn=new TableCtn();
var arrColorTemp=new Array();
var arrColorLive=new Array();
var arrColorLiveTmp=new Array();
var arrColorLiveRemove=new Array();
var demLiveAdd=0;
var demLiveRemove=0;
var arrColorLiveRemoveTmp=new Array();
function isKeyPressedVodAdd(event,id){
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
						tr[i].className="mytable2_row1";
					}else
					{
						tr[i].className="mytable2_row2";
					}
					if(tr[i].id==id){
					temrowId=id;
					tr[i].className="mytable_row_sel";
					}
				}
				
		  			
		  	}else{
		  	demLiveAdd=0;
		
		  	}
		 
		//  pageclick();
		  	break;
		  }
		 // alert(arrColor.length);
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
		var url="VodSrvMain?CMD=11";
		url+="&SubId="+ tempsrc;
		url+="&vodId="+str;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{			
			tableSrv.reload();
		};

		arrColorLiveRemove=new Array();
		arrColorLiveRemoveTmp=new Array();
		arrColorLiveTmp=new Array();
		arrColorLive =new Array(); 
		demLiveAdd=0;
		demLiveRemove=0;
		tableCtn.load(temp);	
		
		
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
				var url="VodSrvMain?CMD=10";
				url+="&SubId="+tempsrc;
				url+="&vodId="+str;
				url+="&r="+Math.random();	
				var f=new AjaxGetText(url);
				f.complet=function(tx)
				{
					tableSrv.reload();	
				};
			
		arrColorLiveRemove=new Array();
		arrColorLiveRemoveTmp=new Array();
		arrColorLiveTmp=new Array();
		arrColorLive =new Array(); 
		demLiveAdd=0;
		demLiveRemove=0;
		tableCtn.load(temp);	
		
	}
	function isKeyPressedVodRemove(event,id){
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
						tr[i].className="mytable2_row1";
					}else
					{
						tr[i].className="mytable2_row2";
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

function AddContent()
{
	var my=this;
//	var html="";
	
	tableCtn.addToService=function(id)
	{		
		
		var url="VodSrvMain?CMD=9";
		url+="&SubId="+dropSubSrv.SubID;
		url+="&vodId="+id;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{			
			if(tx=="1")
			{
				tableSrv.reload();
			}else
			{
				var al=new alertBox();
				al.show(lang.exist_movie);
			}
		};
	};
	
	tableSrv.remove=function(id)
	{
		var url="VodSrvMain?CMD=10";
		url+="&SubId="+dropSubSrv.SubID;
		url+="&vodId="+id;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{
			tableSrv.reload();
		};
	};
	dropSubCtn.Select=function(obj)
	{						
		this.SubID=obj.Id;
		temp =obj.Id;
		$("div_drop_sub1").innerHTML=obj.Name;
		tableCtn.load(obj.Id);		
	};
	dropSubSrv.Select=function(obj)
	{
	
		if(obj==-1)//load default bandau
		{			
			var _obj=Vod.sub.getCurent();
			$("div_drop_sub2").innerHTML=_obj.Name;
			dropSubSrv.Select(_obj);		
		}else
		{
			$("div_drop_sub2").innerHTML=obj.Name;
			tableSrv.load(obj.Id);
			tempsrc=obj.Id;
			//Load content
			this.SubID=obj.Id;
		}
	};
	this.init=function()
	{		
		var url="../ServiceJSP?ID=1";
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{
			html=tx;
		};
	};
	this.pageclick=function()
	{				
		if(dropSubSrv.state)dropSubSrv.hide();
		if(dropSubCtn.state)dropSubCtn.hide();
	};
	this.restore=function()
	{
		
		var url="../ServiceJSP?ID=1";
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{
			$("id_table").innerHTML=tx;
			my.renderEvent();				
			dropSubCtn.init();			
			dropSubSrv.init();			
		};
		
	};
	this.backup=function()
	{
		html=$("id_table").innerHTML;
	};
	this.renderEvent=function()
	{
		$("div_drop_sub1").onclick=my.onclickSub1;			
	};
	this.onclickSub1=function(e)
	{			
		dropSubCtn.show(e);			
	};
	this.onclickSub2=function(e)
	{
		dropSubSrv.show(e);			
	};
}
function TableSrv()
{
	var my=this;
	var table=new Table();
	this.List=new Vector();
	var Index=0;
	this.ID=-1;
	var page=5;
	var	divName=		"tableSrv";
	this.init=function()
	{
		
	};
	this.load=function(id)
	{
		this.List=new Vector();
		my.ID=id;
		my.get(my.ID,Index);
	};
	this.reload=function()
	{
		if((my.count-1)%5==0)
		{
			if(Index>0)Index--;
		}
		this.List=new Vector();
		my.get(my.ID,Index);
	};
	this.loadIndex=function(index)
	{
		Index=index;
		my.show();
	};
	this.nextindex=function()
	{			
		var cpage=Math.round(((my.count-1)/page)-0.4);
		if(my.count%5==0)
		{
			cpage=(my.count/page)-1;
		}else
		{
			cpage=Math.round(((my.count)/page)-0.4);
		}
		if(Index<cpage)
				Index++;		
		my.loadIndex(Index);
	};
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIndex(Index);
	};
	this.show=function()
	{		
		var html="<table class='table2' border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		html+="<thead class='fixedHeader'>";
		html+="<tr>";
				html+="<th style='padding-left:5px' align=\"left\" >";
					html+=lang.movieTitle;
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
					html+="<tr style='padding-left:5px' onmousedown='isKeyPressedVodRemove(event,"+ my.List.get(i).Id +")' class='mytable2_row2' id='"+this.List.get(i).Id+"'>";
				}else
				{
					html+="<tr style='padding-left:5px' onmousedown='isKeyPressedVodRemove(event,"+ my.List.get(i).Id +")' class='mytable2_row1' id='"+this.List.get(i).Id+"'>";
				}						
				html+="<td>";
				html+=this.List.get(i).Name;
				html+="</td>";
				html+="<td align=\"center\">";
					if(this.List.get(i).Status==0)
					{
						html+="<img src=\"../icon/16-square-green.png\"></img>";
					}else
					{
						html+="<img src=\"../icon/16-square-red.png\"></img>";
					}
				html+="</td>";
				html+="</tr>";
			}
			html+="</tbody>";
			html+="</table>";	 
			$(divName).innerHTML=html;			
			my.renderEvent();
	};
	this.get=function(id,index)
	{		
		
		var url="VodSrvMain?CMD=2";
			url+="&SubId="+id;
			url+="&pageIndex=-1";
			url+="&page=5";
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
					var _actors=it[i].getElementsByTagName("Actors")[0].childNodes[0].nodeValue;				
					var _director=it[i].getElementsByTagName("Director")[0].childNodes[0].nodeValue;					
					var _duration=it[i].getElementsByTagName("Duration")[0].childNodes[0].nodeValue;
					var _new=it[i].getElementsByTagName("New")[0].childNodes[0].nodeValue;
					var _des=it[i].getElementsByTagName("Desc")[0].childNodes[0].nodeValue;
					var _image=it[i].getElementsByTagName("Image")[0].childNodes[0].nodeValue;
					var _status=it[i].getElementsByTagName("Status")[0].childNodes[0].nodeValue;
					if(_director=="null")_director="";
					var item=new ItemVOD(_id,_name,_actors,_director,_duration);				
					item.Status=_status;
					item.New=_new;
					item.Desc=_des;
					item.Image=_image;
					my.List.add(item);					
				}				
				my.count=my.List.size();				
				my.show();
		};
	};
	this.renderEvent=function()
	{		
		if($("div_page_srv")!=undefined)
		{
			var div=$("div_page_srv");
			var a=div.getElementsByTagName("a");
			for(var i=0;i<a.length;i++)
			{
				var item=a[i];				
				item.onclick=function(e)
				{
					my.loadIndex(this.id);					
				};
			}
			$("page_right_srv").onclick=function()
			{				
				my.nextindex();
			};
			$("page_left_srv").onclick=function(){my.backindex();};
		}
		//Table
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
				my.remove(this.id);
				return false;
			};
		};		
	};
	my.init();
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
	};
	this.load=function(ctn)
	{
		this.CtnId=ctn;
		my.List=new Vector();
		Index=0;
		this.get(Index);
	};
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
	};
	this.loadIndex=function(index)
	{	
		Index=index;	
		this.show();
	};
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIndex(Index);
	};
	this.show=function()
	{			
		var html="<table class='table2' border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		html+="<thead class='fixedHeader'>";
		html+="<tr>";
				html+="<th style='padding-left:5px' align=\"left\" >";
					html+=lang.movieTitle;
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
					html+="<tr style='padding-left:5px' onmousedown='isKeyPressedVodAdd(event,"+my.List.get(i).Id+")' class='mytable2_row2' id='"+this.List.get(i).Id+"'>";
				}else
				{
					html+="<tr style='padding-left:5px' onmousedown='isKeyPressedVodAdd(event,"+my.List.get(i).Id+")' class='mytable2_row1' id='"+this.List.get(i).Id+"'>";
				}				
					html+="<td>";
					html+=my.List.get(i).Name;
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
	};
	
	this.renderEvent=function()
	{
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
				};
			};	
	};
	
	this.get=function(index)
	{			
			var url="VodSrvMain?CMD=8";
			url+="&SubId="+this.CtnId;
			url+="&subISvr="+tempsrc;
			url+="&r="+Math.random();			
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{						
				var xml=tx.getElementsByTagName("xml");	
				my.count= xml[0].getAttribute("count");				
				var it=tx.getElementsByTagName("Item");	
			
				for(var i=0;i<it.length;i++)
				{					
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;					
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;					
					var _actors=it[i].getElementsByTagName("Actors")[0].childNodes[0].nodeValue;				
					var _director=it[i].getElementsByTagName("Director")[0].childNodes[0].nodeValue;					
					var _duration=it[i].getElementsByTagName("Duration")[0].childNodes[0].nodeValue;
					var _new=it[i].getElementsByTagName("New")[0].childNodes[0].nodeValue;
					var _des=it[i].getElementsByTagName("Desc")[0].childNodes[0].nodeValue;
					var _image=it[i].getElementsByTagName("Image")[0].childNodes[0].nodeValue;
					var _status=it[i].getElementsByTagName("Status")[0].childNodes[0].nodeValue;					
					if(_director=="null")_director="";
					var item=new ItemVOD(_id,_name,_actors,_director,_duration);				
					item.Status=_status;
					item.New=_new;
					item.Desc=_des;
					item.Image=_image;
					my.List.add(item);					
				}	
				my.count=my.List.size();
				my.show();			
		};
	};
	my.init();
}
/**
 * 
 * @return
 */
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
	};
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
			posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
		}			
		div.style.left=posx+"px";
		div.style.top=posy+"px";	
		document.body.appendChild(div);		
		menu.load(my.List);
		setTimeout(function(){my.state=true;},500);
	};	
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
				my.ClickItem(this.id);		
			};											
		}
	};
	this.getSrv=function()
	{						
		var url="VodSrvMain?CMD=1";
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
			my.Select(-1);
		};			
	};
	this.ClickItem=function(id)
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
		my.Select(obj);
	};
	this.hide=function()
	{
		if(this.state)
		{
			$("div_drop_subject").style.display="none";
			this.state=false;
		}
	};
	this.init=function()
	{
		menu.init(my.defaults);
		my.getSrv();
	};
}
function dropSubjectCtn()
{
	var my=this;
	var menu=new Tree();
	this.List=new Array();
	this.state=false;
	this.SubID=-1;
	this.defaults =
	{
		divName:		"Ctn_drop_subject",
		classTree:		"treeview",
		title:			"Subject",
		classItem:		"itemnomal",
		classfocus:		"itemfocus",
		classRootOpen:		"treeviewrootopen",
		classRootClose:		"treeviewrootcolse",
		clcassleaves:	"treeviewleaves",
		classopen:		"treeopen",
		classclose:		"treecolse"
	};
	this.show=function(e)
	{
		my.state=false;		
		var div=null;
		if($("Ctn_drop_subject")!=undefined)
		{
			div=$("Ctn_drop_subject");
			div.style.display="block";
		}else
		{
			div=document.createElement("div");	
		}
		div.className="div_drop_subject";
		div.id="Ctn_drop_subject";
		var posx = 0;
		var posy = 0;
		if (!e) var e = window.event;
		if (e.pageX || e.pageY) 
		{
			posx = e.pageX;
			posy = e.pageY;
		} else if (e.clientX || e.clientY) 	{
			posx = e.clientX + document.body.scrollLeft
				+ document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
				+ document.documentElement.scrollTop;
		}			
		div.style.left=posx+"px";
		div.style.top=posy+"px";	
		document.body.appendChild(div);		
		menu.load(my.List);		
		setTimeout(function(){my.state=true;},500);		
	};	
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
				var type=this.getAttribute("pos");
				if(type=="r")my.pause();
				my.ClickItem(this.id);	
				me.change(this.id);
			};									
		}
	};
	this.pause=function()
	{
		this.state=false;
		setTimeout(function(){my.state=true;},500);
	};
	this.getCtn=function()
	{					
		var url=basePath+CtxCtn+"?CMD=1";
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
			my.Select(my.List[0]);
		};			
	};
	this.ClickItem=function(id)
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
		my.Select(obj);
	};
	this.hide=function()
	{
		if(this.state)
		{
			$("Ctn_drop_subject").style.display="none";
			this.state=false;
		}
	};
	this.init=function()
	{		
		menu.init(my.defaults);
		my.getCtn();
	};
}
dropSubjectCtn.prototype=new dropSubject();
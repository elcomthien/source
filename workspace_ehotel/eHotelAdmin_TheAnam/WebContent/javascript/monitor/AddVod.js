var p=new loadprocess();
var time;
function AddVod()
{
	var my=this;
	this.Id;
	var list=new listAddVod();	
	this.load=function(id)
	{			
		p.stop();
		this.Id=id;
		list.load(id);		
		p.load(my.Id);
		p.start();
	}	
}
function listAddVod()
{
	var table=new Table();	
	this.pageclick=function()
	{
		
	}
	var my=this;
	this.count=0;
	var ID=0;//id of subject
	this.Id="";
	var Index=0;
	this.page=6;
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
		$("div_buton").onclick=function(){my.accept();};
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
	table.dataBind=function()
	{		
		var html="";
		html+="<div class='div_listhotel'>"
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
		html+="Check"
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
				html+="<td align=\"center\"  valign=\"middle\" width=\""+50+"pxs\">";
				html+=t+(i+1);
				html+="</td>";				
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=this.List[i].Name;
				html+="</td>";
				html+="<td align=\"center\"  valign=\"middle\">";
				html+="<img src=\"../resource/images/"+this.List[i].Image+"\" class='images_1' ></img>";
				html+="</td>";
				html+="<td align=\"center\"  valign=\"middle\">";				
				html+="<input type=\"radio\" name=\"checkbox\" id=\""+this.List[i].Id+"\" />"				
				html+="</td>";					
				html+="</tr>";
		}		
		html+="</table>";
		html+="</div>"
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
	this.get=function(id,index)
	{				
		var url="ServiceSynVodStb?CMD=3"
			url+="&id="+id;
			url+="&pageIndex="+index;
			url+="&page="+my.page;
			url+="&r="+Math.random();		
		
			var f=new funPropery();					
			f.complet=function(tx)
			{		
				arr=new Array();			
				var xml=tx.getElementsByTagName("xml");	
					
				var it=tx.getElementsByTagName("Item");	
				
				for(var i=0;i<it.length;i++)
				{					
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;		
					var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
					var item=new ItemVOD(_id,_name,_image);
					arr[i]=item;
				}					
				table.load(arr);
				table.classCol("classItem1",1);
				table.classCol("classItem0",0);					
				my.renderEvent();
				
			}			
			AjaxFuncGetXML(url,f);
	}
	
	this.accept=function()
	{
		if(p.isruning())
		{
			alert("running");
			return;
		}
		var check=document.getElementsByName("checkbox");		
		var arr=new Array();
		var j=0;
		for(var i=0;i<check.length;i++)
		{
			if(check[i].checked)
			{
				arr[j]=check[i].id;
				j++;				
			}
		}
		if(arr.length==0)
		{
			var albox=new alertBox();
			albox.show(langpms.pls_selectmovies);			
			return;
		}
		var param="";
		for(var i=0;i<arr.length;i++)
		{
			param+="&id"+i+"="+arr[i];
		}
		var url="ServiceSynVodStb?CMD=5"
			url+=param;			
			url+="&stb="+my.Id
			url+="&r="+Math.random();	
			var f=new AjaxGetText(url);					
			f.complet=function(tx)
			{		
				my.get(my.Id,Index);
				p.stop();
				p.start();
			}		
		
	}
	this.acceptall=function()
	{
		
		if(p.isruning())
		{
			alert("running");
			return;
		}
		var check=document.getElementsByName("checkbox");		
		var arr=new Array();
		var j=0;
		for(var i=0;i<check.length;i++)
		{
			if(check[i].checked)
			{
				arr[j]=check[i].id;
				j++;				
			}
		}
		if(arr.length==0)
		{
			var albox=new alertBox();
			albox.show(langpms.pls_selectmovies);			
			return;
		}
		var param="";
		for(var i=0;i<arr.length;i++)
		{
			param+="&id"+i+"="+arr[i];
		}
		var url="ServiceSynVodStb?CMD=6"
			url+=param;			
			
			url+="&r="+Math.random();	
			var f=new AjaxGetText(url);					
			f.complet=function(tx)
			{		
				my.get(my.Id,Index);
				p.stop();
				p.start();
			}		
		 
	}	
}

function loadprocess()
{
	var t=0;
	var r=false;
	var my=this;
	this.Id;
	this.list=new Array();
	this.sesion=new Array();	
	this.load=function(id)
	{		
		my.Id=id;
			
	}	
	this.setId=function(id)
	{
		my.Id=id;
	}
	this.get=function()
	{			
		var url="ServiceSynVodStb?CMD=4"
			url+="&id="+my.Id;			
			url+="&r="+Math.random();			
			var f=new funPropery();					
			f.complet=function(tx)
			{		
				arr=new Array();			
				var xml=tx.getElementsByTagName("xml");					
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
				my.list=arr;
				my.show();				
				if(my.list.length==0)
				{				
					r=false;
				}else
				{
					r=true;
				}
			}			
			AjaxFuncGetXML(url,f);
	}	
	this.show=function()
	{
		var html="";
		html+="<div>"
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" >";
		html+="<tr class='mytable_header' >";		
		html+="<th align='center'  valign=\"middle\"  width=\""+6+"%\"  >";
		html+=langpms.no;
		html+="</th>";		
		html+="<th align='left' width=\""+40+"%\" valign=\"middle\"  >";
		html+=langpms.name;
		html+="</th>";		
		html+="<th align='center' width=\""+20+"%\" valign=\"middle\"  >";
		html+="Check"
		html+="</th>";		
		html+="</tr>";	
		html+="</table>";		
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" >";		
		for(var i=0;i<my.list.length;i++)
		{			
				html+="<tr class='mytable_row1'>";
				html+="<td align=\"center\"  valign=\"middle\" width=\""+6+"%\">";
				html+=(i+1);
				html+="</td>";				
				html+="<td align=\"left\"  valign=\"middle\" width=\""+40+"%\">";
				html+=my.list[i].Name;
				html+="</td>";
				html+="<td align=\"center\"  valign=\"middle\" width=\""+20+"%\">";				
				html+=my.list[i].Status+"%";			
				html+="</td>";					
				html+="</tr>";
		}		
		html+="</table>";		
		html+="</div>";
		
		$("div_process").innerHTML=html;
	}
	this.run=function()
	{
		my.get();
		if(r)
		time=setTimeout(my.run,3000);		
	}
	this.stop=function()
	{
		clearTimeout(time);
		r=false;
	}
	this.start=function()
	{
		r=true;
		my.run();
	}
	this.isruning=function()
	{
		return r;
	}
	
}

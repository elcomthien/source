function Table()
{
	var my=this;
	this.List=new Array();
	this.defaults =
	{
		divName:		"",		
		col:  			0,
		classHeader:    "mytable_header",
		classSelect: 		"",
		bgRow1:		"",
		bgRow2:		"",
		classRowsel:		"mytable_row",
		classTable:		"mytable"		
	}
	this.init= function(options)
	{		
		for(var name in my.defaults) 
		{			
			this[name] = (options !== undefined && options[name] !== undefined) ? options[name] : my.defaults[name];
			if(options !== undefined && options[name] !== undefined)
			{
				my.defaults[name]=options[name];
			}
			my[name]=this[name];
		}		
		if($(my.divName)!=undefined)
		$(my.divName).innerHTML="";		
	};	
	
	this.load=function(list)
	{
		this.List=list;
		my.drawHTML();
	}
	this.setwidthHeader=function(width,c)
	{
		var table=$("table_"+this.divName);
		var r=table.getElementsByTagName("th");
		r[c].width=width;
	}
	this.classCol=function(name,c)
	{
		var table=$("table_"+this.divName);
		var r=table.getElementsByTagName("tr");
		
		for(var i=1;i<r.length;i++)
		{
			var td=r[i].getElementsByTagName("td");
			td[c].className=name;
		}		
	}
	this.drawHTML=function()
	{		
		var html="";		
		html+=this.dataBind();				
		$(this.divName).innerHTML=html;
	}
	this.loaddata=function(tx)
	{
		$(this.divName).innerHTML=tx;
	};
}


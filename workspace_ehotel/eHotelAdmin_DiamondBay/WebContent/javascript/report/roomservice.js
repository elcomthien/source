var roomsrv=new function SroomService()
{
	var my=this;
	var table=new Table();
	var Index=0;
	this.List=new Array();	
	var defaults =
	{
		divName:		"div_Table",
		col:  			5,
		classHeader:    "mytable_header",
		classItem: 		"classItem",
		classTable:		"classTable",
		classRowsel:		"mytable_row_sel",
		classTable:		"mytable",
		bgRow1:		"mytable_row1",
		bgRow2:		"mytable_row2"
	}	
	this.load=function()
	{
		my.loadtime();
		table.init(defaults);
		
	}
	this.loadtime=function()
	{
		var html="";
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.from_date;
		html+="</div>";	
		
		html+="<div style=\"float: left;width:130px;\">";
		html+="<input size=\"10\" id=\"f_date1\" /><button id=\"f_btn1\" class='buttom_calenda'>...</button>";
		html+="</div>"
			
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.to_date;
		html+="</div>";
		html+="<div style=\"float: left;width: 130px;\">";
		html+="<input size=\"10\" id=\"f_date2\" /><button id=\"f_btn2\" class='buttom_calenda'>...</button>";
		html+="</div>"
		html+="<a class='bt_excel' title=\"bt_excel\" id='bt_excel'>";
		html+="</a>";	
		$("div_time").innerHTML=html;
		$("bt_excel").onclick=my.excel;
		var now = new Date();				
	    var prev = new Date(); prev.setMonth(prev.getMonth() - 1);
	    var time2= now.getDate()+"/"+(now.getMonth()+1)+"/"+now.getFullYear();		
		$("f_date2").value=time2;
		 var time1= prev.getDate()+"/"+(prev.getMonth()+1)+"/"+prev.getFullYear();		
		$("f_date1").value=time1;
	   	var cal1= Calendar.setup({
	   		
		        inputField : "f_date1",
		        trigger    : "f_btn1",
		        onSelect   : my.changeDatefrom,
		        dateFormat : "%d/%m/%Y",
		        date:prev   
		       
		 });   	   	
		var cal2= Calendar.setup({
	        inputField : "f_date2",
	        trigger    : "f_btn2",
	        onSelect   : my.changeDateNow,
	        dateFormat : "%d/%m/%Y",
	        date: now	        
	      });
		
		my.get();
		
	};	
	
	this.excel=function()
	{
		var from =$("f_date1").value;
		var to=$("f_date2").value;
		var href="../serviceImage?CMD=4&"+"from="+from+"&to="+to;
		href+="&r="+Math.random();
		$("bt_excel").href=href;
	}
	this.changeDateNow=function(cal)
	{
		var date = cal.selection.get();
		var now = new Date();
		var end=new Date();
		if (date)
		{
            date = Calendar.intToDate(date);
            var t = Calendar.printDate(date, "%Y/%m/%d");
            end=new Date(t);           
		}
		
		if(now.getTime()<date.getTime())
		{
			cal.refresh();
			d=Calendar.intToDate(now);
			var t = Calendar.printDate(d, "%d/%m/%Y");
			$("f_date2").value=t;				
			var albox=new alertBox();
			albox.show(langpms.calenda_now);
			this.hide();
			return;
		}		
		my.get();
		this.hide();
	}
	this.changeDatefrom=function(cal)
	{
		var date = cal.selection.get();
		var now = new Date();
		var end=new Date();
		if (date)
		{
            date = Calendar.intToDate(date);
            var t = Calendar.printDate(date, "%Y/%m/%d");
            end=new Date(t);           
		}		
		if(now.getTime()<date.getTime())
		{
			cal.refresh();
			d=Calendar.intToDate(now);
			var t = Calendar.printDate(d, "%d/%m/%Y");
			$("f_date1").value=t;				
			var albox=new alertBox();
			albox.show(langpms.calenda_now);
			this.hide();
			return;
		}		
		
		my.get();
		this.hide();
	}
	table.dataBind=function()
	{		
		var html="";
		html+="<div class='div_listhotel'>"
		html+="<table  cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";
		html+="<th align='left'  valign=\"middle\"  width=\""+2+"%\"  >";
		html+="</th>";	
		html+="<th align='left'  valign=\"middle\"  width=\""+10+"%\"  >";
		html+=langpms.no;
		html+="</th>";	
		html+="<th align='left'  valign=\"middle\"  width=\""+30+"%\"  >";
		html+=langpms.name;
		html+="</th>";	
		html+="<th align='center' width=\""+12+"%\" valign=\"middle\"  >";
		html+=langpms.price_pms;
		html+="</th>";
		html+="<th align='center' width=\""+15+"%\" valign=\"middle\"  >";
		html+=langpms.Quantity;
		html+="</th>";
		html+="<th align='center' width=\""+18+"%\" valign=\"middle\"  >";
		html+="Amount"
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
				html+="</td>";
				
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=this.List[i].Id;
				html+="</td>";
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=this.List[i].Name;
				html+="</td>";
				html+="<td align='center'  valign=\"middle\">";
				html+=this.List[i].Price;
				html+="</td>";
				html+="<td align='center' valign=\"middle\">";
				html+=this.List[i].Total
				html+="</td>";	
				html+="<td align='center'  valign=\"middle\">";
				html+=this.List[i].Amount
				html+="</td>";
				html+="</tr>";
		}
		
		html+="</table>";
		
		html+="</div>"
				
		return html;
	}
	this.get=function()
	{				
		
		var from =$("f_date1").value;
		var to =$("f_date2").value;
		var url="RoomsvcReport?CMD=1"
			url+="&pageIndex="+Index;
			url+="&from="+from;
			url+="&to="+to;
			url+="&r="+Math.random();			
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{					
				arr=new Array();
				var xml=tx.getElementsByTagName("xml");					
				var it=tx.getElementsByTagName("Item");	
				for(var i=0;i<it.length;i++)
				{					
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _Amount=it[i].getElementsByTagName("Amount")[0].childNodes[0].nodeValue;
					var _quantity=it[i].getElementsByTagName("quantity")[0].childNodes[0].nodeValue;
					var _price=it[i].getElementsByTagName("price")[0].childNodes[0].nodeValue;
					arr[i]=new itemData(_id,_name,_quantity);
					arr[i].Amount=_Amount;
					arr[i].Price=_price;
				}	
				my.List=arr;
				table.load(my.List);					
			}		
	}
	function itemData(id,name,total)
	{
		this.Id=id;this.Name=name;this.Total=total;
	}
}
window.onload = function()
{
	roomsrv.load();
}  
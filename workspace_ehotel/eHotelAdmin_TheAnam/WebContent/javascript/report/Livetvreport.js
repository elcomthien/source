var year = 0;
var currentYear = new Date().getFullYear();
var currentMonthTemp = new Date().getMonth() + 1;
if (currentMonthTemp < 10) {
	currentMonthTemp = "0" + currentMonthTemp;
}
var currentMonth = currentMonthTemp;
var LivetvReport=new function ReportLivetv()
{
	var my=this;
	this.obj=new Monthly();
	var type=0;
	this.load=function()
	{
		my.obj.load();
	}
	this.onchangeType=function(me)
	{
		var value= me.options[me.selectedIndex].value;
		type=value;
		if(value==0)
		{
			this.obj=new Monthly();
			my.obj.load();
		}
		if(value==1)
		{
			this.obj=new StaticGenric();
			my.obj.load();
		}
		if(value==2)
		{
			this.obj=new StaticFilm();
			my.obj.load();
		}	
	}
}
function Monthly()
{
	var my=this;
	var table=new Table();
	var Index=0;
	this.List=new Array();
	this.year=currentYear;
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
		my.get();
	}
	this.loadtime=function()
	{
		var html="";
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.select_year;
		html+="</div>";
		html+="";
		html+="<div style=\"float: left;\">";
		html+="<select id=\"slect_year\">";
		html+="<option selected=\"selected\">2011</option>";
		for (var i = 2012; i < 2500; i++) {
			if (i == currentYear)
				html+="<option selected=\"selected\">"+i+"</option>";
			else
				html+="<option >" + i + "</option>";
		}
		/*html+="<option>2013</option>";
		html+="<option>2014</option>";
		html+="<option>2015</option>";
		html+="<option>2016</option>";
		html+="<option>2017</option>";
		html+="<option>2018</option>";
		html+="<option>2019</option>";
		html+="<option>2020</option>";
		html+="<option>2021</option>";
		html+="<option>2022</option>";*/
		html+="</select>";
		html+="</div>";			
		/*html+="<div class='bt_charst' title=\"charts\" id='bt_charst'>";
		html+="</div>";	
		html+="<a class='bt_excel' title=\"export excel\" id='bt_excel'>";
		html+="</a>";*/
		$("div_time").innerHTML=html;		
		$("slect_year").onchange=my.onchangeyear;
		//$("bt_charst").onclick=my.showcharst;
		//$("bt_excel").href="../serviceImage?CMD=1&year="+year;
	}
	this.showcharst=function()
	{
		var layer=new layer_vitual();
		layer.show();
		var html="";
		html+="<div  class='div_formchar' id='formchar' >";	
		html+="<div class='div_Title'>";
		html+="<div id='bt_close' class=\"bt_close\" >";
		html+="<img src=\"../icon/close.png\" ></img>";
		html+="</div>";
		html+="</div>";
		html+="<div class='ifarme_charts'>";
		html+="<iframe src=\"../charts.jsp?year="+my.year+"\" scrolling=\"no\" frameborder='0' width=\"98%\" height=\"400px\" ></iframe>";
		html+="</div>";	
		html+="</div>";
		layer.addHtml(html);
		$("bt_close").onclick=function(){layer.hide();};
		setCenterDIV("formchar");			
	}
	this.excel=function()
	{
	}
	this.onchangeyear=function()
	{
		var me=$("slect_year");
		var value= me.options[me.selectedIndex].text;
		my.year=value;
		year = value;
		//$('bt_excel').href = "../serviceImage?CMD=1&year="+year;
		my.get();
	}
	table.dataBind=function()
	{		
		var html="";
		html+="<div class='div_listhotel'>";
		html+="<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";
		html+="<th align='left' valign=\"middle\" width=\""+2+"%\" >";
		html+="</th>";	
		html+="<th align='left' valign=\"middle\" width=\""+10+"%\" >";
		html+=langpms.no;
		html+="</th>";	
		html+="<th align='left' valign=\"middle\" width=\""+50+"%\" >";
		html+="Day";
		html+="</th>";		
		html+="<th align='center' width=\""+20+"%\" valign=\"middle\" >";
		html+=langpms.Quantity;
		html+="</th>";		
		html+="</tr>";
		var count = 0;
		for(var i=0;i<this.List.length;i++)
		{	
			count++;
			if(i%2==0)
			{
				html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\" >";
			}else
			{
				html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\" >";
			}	
			html+="<td align=\"left\" valign=\"middle\">";			
			html+="</td>";				
			html+="<td align=\"left\" valign=\"middle\">";
			html+=count;
			html+="</td>";
			html+="<td align=\"left\" valign=\"middle\">";
			html+=this.List[i].Name;
			html+="</td>";				
			html+="<td align=\"center\" valign=\"middle\">";
			html+=this.List[i].Total;
			html+="</td>";											
			html+="</tr>";
		}		
		html+="</table>";
		html+="</div>";
		return html;
	}
	this.get=function()
	{	
		my.List.length = 0;		
		var url="LivetvReport?CMD=1";
			url+="&pageIndex="+Index;
			url+="&year="+my.year;
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
					my.List[i]=new itemData(_id,_name,_Amount);
				}				
				table.load(my.List);			
			}
	}
	function itemData(id,name,total)
	{
		this.Id=id;this.Name=name;this.Total=total;
	}
}
function StaticGenric()
{
	var my=this;
	var table=new Table();
	var Index=0;
	this.List=new Array();
	this.year = currentMonth;
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
		/*html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.from_date;
		html+="</div>";	
		html+="<div style=\"float: left;width:130px;\">";
		html+="<input size=\"10\" id=\"f_date1\"/><button id=\"f_btn1\" class='buttom_calenda'>...</button>";
		html+="</div>";
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.to_date;
		html+="</div>";
		html+="<div style=\"float: left;width: 130px;\">";
		html+="<input size=\"10\" id=\"f_date2\" /><button id=\"f_btn2\" class='buttom_calenda'>...</button>";
		html+="</div>";*/
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.select_month;
		html+="</div>";
		html+="<div style=\"float: left;\">";
		html+="<select id=\"slect_month\">";
		var tmp = "";
		for (var i = 1; i <= 12; i++) {
			if (i < 10) tmp = "0" + i; else tmp = i;
			if (tmp == currentMonth)
				html+="<option selected=\"selected\">"+tmp+"</option>";
			else
				html+="<option >" + tmp + "</option>";
		}
		html+="</select>";
		html+="</div>";
		/*html+="<a class='bt_excel' title=\"export excel\" id='bt_excel'>";
		html+="</a>";*/
		$("div_time").innerHTML=html;	
		//$("bt_excel").onclick=my.excel;
		/*var now = new Date();				
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
	      });*/
		my.get();
		$("slect_month").onchange=my.onchangemonth;
	};
	this.onchangemonth=function()
	{
		var me=$("slect_month");
		var value= me.options[me.selectedIndex].text;
		my.year=value;
		my.get();
	}
	this.excel=function()
	{
		var from =$("f_date1").value;
		var to=$("f_date2").value;
		var href="../serviceImage?CMD=2&"+"from="+from+"&to="+to;
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
		var count = 0;
		var html="";
		html+="<div class='div_listhotel'>";
		html+="<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";
		html+="<th align='left' valign=\"middle\" width=\""+2+"%\" >";
		html+="</th>";
		html+="<th align='left' valign=\"middle\" width=\""+10+"%\" >";
		html+=langpms.no;
		html+="</th>";	
		html+="<th align='left' valign=\"middle\" width=\""+50+"%\" >";
		html+="Day";
		html+="</th>";	
		html+="<th align='center' width=\""+20+"%\" valign=\"middle\" >";
		html+=langpms.Quantity;
		html+="</th>";		
		html+="</tr>";			
		for(var i=0;i<this.List.length;i++)
		{
			count++;
			if(i%2==0)
			{				
				html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\" >";
			}else
			{
				html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\" >";
			}	
			html+="<td align=\"left\" valign=\"middle\">";			
			html+="</td>";
			html+="<td align=\"left\" valign=\"middle\">";
			html+=count;
			html+="</td>";
			html+="<td align=\"left\" valign=\"middle\">";
			html+=this.List[i].Name;
			html+="</td>";				
			html+="<td align=\"center\" valign=\"middle\">";
			html+=this.List[i].Total;
			html+="</td>";					
			html+="</tr>";
		}
		html+="</table>";
		html+="</div>";
		return html;
	}
	
	this.get=function()
	{			
		my.List.length = 0;
		/*var from =$("f_date1").value;
		var to =$("f_date2").value;
		var url="LivetvReport?CMD=1";
			url+="&pageIndex="+Index;
			url+="&from="+from;
			url+="&to="+to;
			url+="&r="+Math.random();			
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{					
				var arr=new Array();
				var xml=tx.getElementsByTagName("xml");					
				var it=tx.getElementsByTagName("Item");	
				for(var i=0;i<it.length;i++)
				{					
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _Amount=it[i].getElementsByTagName("Amount")[0].childNodes[0].nodeValue;
					arr[i]=new itemData(_id,_name,_Amount);
				}				
				my.List=arr;
				table.load(my.List);						
			}*/
		var url="LivetvReport?CMD=2";
		url+="&pageIndex="+Index;
		url+="&month="+my.year;
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
				my.List[i]=new itemData(_id,_name,_Amount);
			}					
			table.load(my.List);			
		}
	}
	function itemData(id,name,total)
	{
		this.Id=id;this.Name=name;this.Total=total;
	}
}
function StaticFilm()
{
	var my=this;
	var table=new Table();
	var Index=0;
	this.year = currentMonth + currentYear;
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
		/*html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.from_date;
		html+="</div>";	
		html+="<div style=\"float: left;width:130px;\">";
		html+="<input size=\"10\" id=\"f_date1\" /><button id=\"f_btn1\" class='buttom_calenda'>...</button>";
		html+="</div>";
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.to_date;
		html+="</div>";
		html+="<div style=\"float: left;width: 130px;\">";
		html+="<input size=\"10\" id=\"f_date2\" /><button id=\"f_btn2\" class='buttom_calenda'>...</button>";
		html+="</div>";*/
		
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.select_year;
		html+="</div>";
		html+="";
		html+="<div style=\"float: left;\">";
		html+="<select id=\"slect_year\">";
		for (var i = 2012; i < 2500; i++) {
			if (i == currentYear)
				html+="<option selected=\"selected\">"+i+"</option>";
			else
				html+="<option >" + i + "</option>";
		}
		html+="</select>";
		html+="</div>";
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.select_month;
		html+="</div>";
		html+="<div style=\"float: left;\">";
		html+="<select id=\"slect_month\">";
		var tmp = "";
		for (var i = 1; i <= 12; i++) {
			if (i < 10) tmp = "0" + i; else tmp = i;
			if (tmp == currentMonth)
				html+="<option selected=\"selected\">"+tmp+"</option>";
			else
				html+="<option >" + tmp + "</option>";
		}
		html+="</div>";	
		
		$("div_time").innerHTML=html;
		
		/*html+="<a class='bt_excel' title=\"export excel\" id='bt_excel'>";
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
	      });*/		
		my.get();
		$("slect_month").onchange=my.onchangemonth;
		$("slect_year").onchange=my.onchangeyear;
	};
	this.onchangemonth=function()
	{
		var year = $("slect_year");
		var yearvalue = year.options[year.selectedIndex].text;
		var me=$("slect_month");
		var value= me.options[me.selectedIndex].text;
		my.year=value + yearvalue;
		my.get();
	}
	this.onchangeyear=function()
	{
		var year = $("slect_year");
		var yearvalue = year.options[year.selectedIndex].text;
		var me=$("slect_month");
		var value= me.options[me.selectedIndex].text;
		my.year=value + yearvalue;
		my.get();
	}
	this.excel=function()
	{
		var from =$("f_date1").value;
		var to=$("f_date2").value;
		var href="../serviceImage?CMD=3&"+"from="+from+"&to="+to;
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
		var count = 0;
		var html="";
		html+="<div class='div_listhotel'>";
		html+="<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";
		html+="<th align='left' valign=\"middle\" width=\""+2+"%\" >";
		html+="</th>";	
		html+="<th align='left' valign=\"middle\" width=\""+10+"%\" >";
		html+=langpms.no;
		html+="</th>";	
		html+="<th align='left' valign=\"middle\" width=\""+30+"%\" >";
		html+=langpms.title;
		html+="</th>";	
		html+="<th align='center' width=\""+12+"%\" valign=\"middle\" >";
		html+=langpms.price_pms;
		html+="</th>";
		html+="<th align='center' width=\""+15+"%\" valign=\"middle\" >";
		html+=langpms.Quantity;
		html+="</th>";
/*		html+="<th align='center' width=\""+18+"%\" valign=\"middle\" >";
		html+="Amount";
		html+="</th>";
*/		html+="</tr>";	
		for(var i=0;i<this.List.length;i++)
		{
			count++;
			if(i%2==0) {				
				html+="<tr class=\""+this.bgRow2+"\" id=\""+this.List[i].Id+"\" >";
			} else {
				html+="<tr class=\""+this.bgRow1+"\" id=\""+this.List[i].Id+"\" >";
			}	
			html+="<td align=\"left\" valign=\"middle\">";			
			html+="</td>";
			html+="<td align=\"left\" valign=\"middle\">";
			html+=count;
			html+="</td>";
			html+="<td align=\"left\" valign=\"middle\">";
			html+=this.List[i].Name;
			html+="</td>";
			html+="<td align='center' valign=\"middle\">";
			html+=this.List[i].Price;
			html+="</td>";
			html+="<td align='center' valign=\"middle\">";
			html+=this.List[i].Total;
			html+="</td>";	
			/*html+="<td align='center'  valign=\"middle\">";
			html+=this.List[i].Amount;
			html+="</td>";*/
			html+="</tr>";
		}
		html+="</table>";
		html+="</div>";
		return html;
	}
	this.get=function()
	{
		my.List.length = 0;
		/*var from =$("f_date1").value;
		var to =$("f_date2").value;*/
		var url="LivetvReport?CMD=3";
			url+="&pageIndex="+Index;
			url+="&from="+my.year; 		//from month
			url+="&to=1234";			//to filmname
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
					my.List[i]=new itemData(_id,_name,_quantity);
					my.List[i].Amount=_Amount;
					my.List[i].Price=_price;
				}					
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
	LivetvReport.load();		
}
var gruoprpt=new function GroupReport()
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
	};	
	this.load=function()
	{
		my.loadtime();
		table.init(defaults);
		
	};
	this.loadtime=function()
	{
		var html="";
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.from_date;
		html+="</div>";	
		
		html+="<div style=\"float: left;width:150px;\">";
		html+="<input size=\"10\" id=\"g_date1\" /><button id=\"g_btn1\" class='buttom_calenda'>...</button>";
		html+="</div>";
			
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.to_date;
		html+="</div>";
		html+="<div style=\"float: left;width: 150px;\">";
		html+="<input size=\"10\" id=\"g_date2\" /><button id=\"g_btn2\" class='buttom_calenda'>...</button>";
		html+="</div>";
//		html+="<a class='bt_excel' title=\"bt_excel\" id='bt_excel'>";
//		html+="</a>";	
		$("div_time").innerHTML=html;
		$("bt_excel").onclick=my.excel;
		$("bt_print").onclick=my.print;
		var now = new Date();				
	    var prev = new Date(); prev.setMonth(prev.getMonth() - 1);
	    var time2= now.getDate()+"/"+(now.getMonth()+1)+"/"+now.getFullYear();		
		$("g_date2").value=time2;
		 var time1= prev.getDate()+"/"+(prev.getMonth()+1)+"/"+prev.getFullYear();		
		$("g_date1").value=time1;
	   	var cal1= Calendar.setup({
	   		
		        inputField : "g_date1",
		        trigger    : "g_btn1",
		        onSelect   : my.changeDatefrom,
		        dateFormat : "%d/%m/%Y",
		        date:prev   
		       
		 });   	   	
		var cal2= Calendar.setup({
	        inputField : "g_date2",
	        trigger    : "g_btn2",
	        onSelect   : my.changeDateNow,
	        dateFormat : "%d/%m/%Y",
	        date: now	        
	      });
		
		my.get();
	};	
	
	this.excel=function()
	{
		tableToExcel('data_table_group', 'Move Of Group');
	};
	this.print=function()
	{
		printDiv();
	};
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
			$("g_date2").value=t;				
			var albox=new alertBox();
			albox.show(langpms.calenda_now);
			this.hide();
			return;
		}		
		my.get();
		this.hide();
	};
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
			$("g_date1").value=t;				
			var albox=new alertBox();
			albox.show(langpms.calenda_now);
			this.hide();
			return;
		}		
		
		my.get();
		this.hide();
	};
	table.dataBind=function()
	{		
		var html="";
		html+="<div class='div_listhotel'>";
		html+="<table id='data_table_group' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";
		html+="<th align='left'  valign=\"middle\"  width=\""+11+"%\"  >";
		html+="Group";
		html+="</th>";	
		html+="<th align='left'  valign=\"middle\"  width=\""+34+"%\"  >";
		html+="Movie title";
		html+="</th>";	
		html+="<th align='center' width=\""+20+"%\" valign=\"middle\"  >";
		html+="Upload date";
		html+="</th>";
		html+="<th align='center' width=\""+5+"%\" valign=\"middle\"  >";
		html+="Subtitle";
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\"  >";
		html+="Sub lang";
		html+="</th>";
		html+="</tr>";	
		
		for(var i=0;i<this.List.length;i++)
		{		
				if(i%2==0)
					if(this.List[i].Group=="Sub-total" || this.List[i].Group=="Grand Total")			
						html+="<tr class=\""+this.bgRow2+"\" style='font-weight: bold;'>";
					else
						html+="<tr class=\""+this.bgRow2+"\">";
				else
					if(this.List[i].Group=="Sub-total" || this.List[i].Group=="Grand Total")
						html+="<tr class=\""+this.bgRow1+"\" style='font-weight: bold;'>";
					else
						html+="<tr class=\""+this.bgRow1+"\">";
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=this.List[i].Group;
				html+="</td>";
				html+="<td align=\"left\"  valign=\"middle\">";
				html+=this.List[i].Title;
				html+="</td>";
				html+="<td align='center'  valign=\"middle\">";
				html+=this.List[i].Upload;
				html+="</td>";
				html+="<td align='center' valign=\"middle\">";
				html+=this.List[i].Count;
				html+="</td>";
				if(this.List[i].Count == "0"){
					html+="<td align='center'  valign=\"middle\">";
					html+="0";
					html+="</td>";
				}else{
					html+="<td align='center'  valign=\"middle\">";
					html+=this.List[i].Lang;
					html+="</td>";
					
				}
				html+="</tr>";
		}
		html+="</table>";
		html+="</div>";
		return html;
	};
	this.get=function()
	{				
		var url="GroupReport?CMD=1";
			url+="&r="+Math.random();			
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{					
				arr=new Array();
//				var xml=tx.getElementsByTagName("xml");					
				var it=tx.getElementsByTagName("Item");	
				for(var i=0;i<it.length;i++)
				{					
					var _group=it[i].getElementsByTagName("group")[0].childNodes[0].nodeValue;
					var _title=it[i].getElementsByTagName("title")[0].childNodes[0].nodeValue;
					var _upload=it[i].getElementsByTagName("upload")[0].childNodes[0].nodeValue;
					var _countsub=it[i].getElementsByTagName("countsub")[0].childNodes[0].nodeValue;
					var _langsub=it[i].getElementsByTagName("langsub")[0].childNodes[0].nodeValue;
					arr[i]=new itemData(_group,_title,_upload,_countsub,_langsub);
				}	
				my.List=arr;
				table.load(my.List);	
				my.getGroup();
			};		
	};
	this.filter=function()
	{			
		var groupid = document.getElementById("filter-group").value;
		var url="GroupReport?CMD=1";
			url+="&r="+Math.random();			
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{					
				arr=new Array();
				var it=tx.getElementsByTagName("Item");	
				var j = 0;
				for ( var i = 0; i < it.length; i++)
				{		
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _group=it[i].getElementsByTagName("group")[0].childNodes[0].nodeValue;
					var _title=it[i].getElementsByTagName("title")[0].childNodes[0].nodeValue;
					var _upload=it[i].getElementsByTagName("upload")[0].childNodes[0].nodeValue;
					var _countsub=it[i].getElementsByTagName("countsub")[0].childNodes[0].nodeValue;
					var _langsub=it[i].getElementsByTagName("langsub")[0].childNodes[0].nodeValue;
					if(_id == groupid || groupid == "all"){
						arr[j]=new itemData(_group,_title,_upload,_countsub,_langsub);
						j++;
					}
				}
				my.List=arr;
				table.load(my.List);					
			};		
	};
	function itemData(group,title,upload,countsub,langsub)
	{
		this.Group = group;
		this.Title = title;
		this.Upload = upload;
		this.Count = countsub;
		this.Lang = langsub;
	}
	this.getGroup = function(){
		var url="GroupReport?CMD=3";
		url+="&r="+Math.random();
		var f=new AjaxGetText(url);			
		f.complet=function(tx) {
			document.getElementById("filter-group").innerHTML = tx;
		};
	};
};

window.onload = function()
{
	gruoprpt.load();
};

var tableToExcel = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', base64 = function(
			s) {
		return window.btoa(unescape(encodeURIComponent(s)));
	}, format = function(s, c) {
		return s.replace(/{(\w+)}/g, function(m, p) {
			return c[p];
		});
	};
	return function(table, name) {
		if (!table.nodeType)
			table = document.getElementById(table);
		var ctx = {
			worksheet : name || 'Worksheet',
			table : table.innerHTML
		};
		window.location.href = uri + base64(format(template, ctx));
	};
})();

function printDiv()
{
  var divToPrint=document.getElementById('data_table_group');
  newWin= window.open("");
  newWin.document.write(divToPrint.outerHTML);
  newWin.print();
  newWin.close();
}
var productionrpt=new function ProductionReport()
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
		html+="<input size=\"10\" id=\"p_date1\" /><button id=\"p_btn1\" class='buttom_calenda'>...</button>";
		html+="</div>";
			
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.to_date;
		html+="</div>";
		html+="<div style=\"float: left;width: 150px;\">";
		html+="<input size=\"10\" id=\"p_date2\" /><button id=\"p_btn2\" class='buttom_calenda'>...</button>";
		html+="</div>";
		$("div_time").innerHTML=html;
		$("bt_excel").onclick=my.excel;
		$("bt_print").onclick=my.print;

		var now = new Date();
		var d2 = now.getDate();
		if (d2 < 10)
			d2 = "0" + d2;
		var m2 = (now.getMonth() + 1);
		if (m2 < 10)
			m2 = "0" + m2;
		var time2 = d2 + "/" + m2 + "/" + now.getFullYear();
		$("p_date2").value = time2;
		
		var prev = new Date(now.setDate(now.getDate() - 6));
		var d1 = prev.getDate();
		if (d1 < 10)
			d1 = "0" + d1;
		var m1 = (prev.getMonth() + 1);
		if (m1 < 10)
			m1 = "0" + m1;
		var time1 = d1 + "/" + m1 + "/" + prev.getFullYear();
		$("p_date1").value = time1;
		
	   	var cal1= Calendar.setup({
	   		
		        inputField : "p_date1",
		        trigger    : "p_btn1",
		        onSelect   : my.changeDatefrom,
		        dateFormat : "%d/%m/%Y",
		        date:prev   
		       
		 });   	   	
		var cal2= Calendar.setup({
	        inputField : "p_date2",
	        trigger    : "p_btn2",
	        onSelect   : my.changeDateNow,
	        dateFormat : "%d/%m/%Y",
	        date: now	        
	      });
		
		my.get();
		
	};	
	
	this.excel=function()
	{
		tableToExcel('data_table_production', 'Movie Production');
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
			$("p_date2").value=t;				
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
			$("p_date1").value=t;				
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
		var html = "";
		html += "<div class='div_listhotel'>";
		html += "<table id='data_table_production' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 5 + "%\"  >";
		html += "No";
		html += "</th>";
		html += "<th align='left'  style='text-align: left;'  width=\"" + 35 + "%\"  >";
		html += "Movie title";
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" style='text-align: center;'  >";
		html += "Times";
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" style='text-align: center;'  >";
		html += "Currency";
		html += "</th>";
		html += "<th align='center' width=\"" + 10 + "%\" style='text-align: center;'  >";
		html += "Rate";
		html += "</th>";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 10 + "%\"  >";
		html += "Amount";
		html += "</th>";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 10 + "%\"  >";
		html += "Unique";
		html += "</th>";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 10 + "%\"  >";
		html += "Pincode";
		html += "</th>";
		html += "</tr>";
		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0)
				html += "<tr class=\"" + this.bgRow2 + "\">";
			else 
				html += "<tr class=\"" + this.bgRow1 + "\">";
			html += "<td align=\"left\"  style='text-align: center;'>";
			html += (i + 1);
			html += "</td>";
			html += "<td align=\"left\"  style='text-align: left;'>";
			html += this.List[i].Title;
			html += "</td>";
			html += "<td align=\"left\"  style='text-align: center;'>";
			html += this.List[i].Times;
			html += "</td>";
			html += "<td align='center'  style='text-align: center;'>";
			html += this.List[i].Currency  +  '$';
			html += "</td>";
			html += "<td align='center'  style='text-align: center;'>";
			html += this.List[i].Rate;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Amount;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Unique;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Pincode;
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		return html;
	};
	this.get = function() {
		var from = $("p_date1").value;
		var to = $("p_date2").value;
		var url = "ProductionReport?CMD=1";
		url += "&from=" + from;
		url += "&to=" + to;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var xml = tx.getElementsByTagName("xml");
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _title = it[i].getElementsByTagName("title")[0].childNodes[0].nodeValue;
				var _times = it[i].getElementsByTagName("times")[0].childNodes[0].nodeValue;
				var _currency = it[i].getElementsByTagName("currency")[0].childNodes[0].nodeValue;
				var _rate = it[i].getElementsByTagName("rate")[0].childNodes[0].nodeValue;
				var _amount = it[i].getElementsByTagName("amount")[0].childNodes[0].nodeValue;
				var _unique = it[i].getElementsByTagName("unique")[0].childNodes[0].nodeValue;
				var _pincode = it[i].getElementsByTagName("pincode")[0].childNodes[0].nodeValue;
				arr[i] = new itemData(_title, _times, _currency, _rate,_amount, _unique,_pincode);
			}
			my.List = arr;
			table.load(my.List);
		};
	};
	function itemData(title,times,currency,rate,amount,unique,pincode)
	{
		this.Title = title;
		this.Times = times;
		this.Currency = currency;
		this.Rate = rate;
		this.Amount = amount;
		this.Unique = unique;
		this.Pincode = pincode;
	}
};
function reload(){
	productionrpt.get();
}
window.onload = function()
{
	productionrpt.load();
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
  var divToPrint=document.getElementById('data_table_production');
  newWin= window.open("");
  newWin.document.write(divToPrint.outerHTML);
  newWin.print();
  newWin.close();
}
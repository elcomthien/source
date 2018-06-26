var svcorderrpt=new function RoomServiceOrderReport()
{
	var my=this;
	var table=new Table();
//	var Index=0;
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
		html+="<input size=\"10\" id=\"d_date1\" /><button id=\"d_btn1\" class='buttom_calenda'>...</button>";
		html+="</div>";
			
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.to_date;
		html+="</div>";
		html+="<div style=\"float: left;width: 150px;\">";
		html+="<input size=\"10\" id=\"d_date2\" /><button id=\"d_btn2\" class='buttom_calenda'>...</button>";
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
		$("d_date2").value = time2;
		
		var prev = new Date(now.setDate(now.getDate() - 6));
		var d1 = prev.getDate();
		if (d1 < 10)
			d1 = "0" + d1;
		var m1 = (prev.getMonth() + 1);
		if (m1 < 10)
			m1 = "0" + m1;
		var time1 = d1 + "/" + m1 + "/" + prev.getFullYear();
		$("d_date1").value = time1;
	   	var cal1= Calendar.setup({
	   		
		        inputField : "d_date1",
		        trigger    : "d_btn1",
		        onSelect   : my.changeDatefrom,
		        dateFormat : "%d/%m/%Y",
		        date:prev   
		       
		 });   	   	
		var cal2= Calendar.setup({
	        inputField : "d_date2",
	        trigger    : "d_btn2",
	        onSelect   : my.changeDateNow,
	        dateFormat : "%d/%m/%Y",
	        date: now	        
	      });
		
		my.get();
		
	};	
	
	this.excel=function()
	{
		tableToExcel('data_table_detail', 'Move Detail');
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
			$("d_date2").value=t;				
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
			$("d_date1").value=t;				
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
		html += "<table id='data_table_detail' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""
				+ this.classTable + "\" id=\"table_" + this.divName + "\">";
		html += "<tr class=\"" + this.classHeader + "\">";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 5 + "%\"  >";
		html += "No";
		html += "</th>";
		html += "<th align='left'  style='text-align: left;'  width=\"" + 16 + "%\"  >";
		html += "Food Items";
		html += "</th>";
		html += "<th align='center' width=\"" + 16 + "%\" style='text-align: center;'  >";
		html += "Orderd Date";
		html += "</th>";
		html += "<th align='center' width=\"" + 8 + "%\" style='text-align: center;'  >";
		html += "Quantity";
		html += "</th>";
		html += "<th align='center' width=\"" + 8 + "%\" style='text-align: center;'  >";
		html += "Currency";
		html += "</th>";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 8 + "%\"  >";
		html += "Amount";
		html += "</th>";
		html += "</th>";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 15 + "%\"  >";
		html += "Guest&#x27;s Name";
		html += "</th>";
		html += "</th>";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 8 + "%\"  >";
		html += "Room No";
		html += "</th>";
		html += "</th>";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 8 + "%\"  >";
		html += "Checkin";
		html += "</th>";
		html += "<th align='left'  style='text-align: center;'  width=\"" + 8 + "%\"  >";
		html += "Checkout";
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
			html += this.List[i].Name;
			html += "</td>";
			html += "<td align=\"left\"  style='text-align: center;'>";
			html += this.List[i].Orderdate;
			html += "</td>";
			html += "<td align='center'  style='text-align: center;'>";
			html += this.List[i].Quantity;
			html += "</td>";
			html += "<td align='center'  style='text-align: center;'>";
			html += this.List[i].Currency;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Amount;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Guest;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Room;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Checkin;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Checkout;
			html += "</td>";
			html += "</tr>";
		}
		html += "</table>";
		html += "</div>";
		return html;
	};
	this.get = function() {
		var from = $("d_date1").value;
		var to = $("d_date2").value;
		var url = "ServiceOrderReport?CMD=1";
		url += "&from=" + from;
		url += "&to=" + to;
		url += "&r=" + Math.random();
		var f = new AjaxGetXML(url);
		f.complet = function(tx) {
			arr = new Array();
			var it = tx.getElementsByTagName("Item");
			for ( var i = 0; i < it.length; i++) {
				var _name = it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _orderdate = it[i].getElementsByTagName("orderdate")[0].childNodes[0].nodeValue;
				var _quantity = it[i].getElementsByTagName("quantity")[0].childNodes[0].nodeValue;
				var _currency = it[i].getElementsByTagName("currency")[0].childNodes[0].nodeValue;
				var _amount = it[i].getElementsByTagName("amount")[0].childNodes[0].nodeValue;
				var _guest = it[i].getElementsByTagName("guest")[0].childNodes[0].nodeValue;
				var _room = it[i].getElementsByTagName("room")[0].childNodes[0].nodeValue;
				var _checkin = it[i].getElementsByTagName("checkin")[0].childNodes[0].nodeValue;
				var _checkout = it[i].getElementsByTagName("checkout")[0].childNodes[0].nodeValue;
				arr[i] = new itemData(_name, _orderdate, _quantity,_currency, _amount, _guest, _room, _checkin, _checkout);
			}
			my.List = arr;
			table.load(my.List);
		};
	};
	function itemData(name,orderdate,quantity,currency,amount,guest,room,checkin,checkout)
	{
		this.Name = name;
		this.Orderdate = orderdate;
		this.Quantity = quantity;
		this.Currency = currency;
		this.Amount = amount;
		this.Guest = guest;
		this.Room = room;
		this.Checkin = checkin;
		this.Checkout = checkout;
	}
};
function reload(){
	svcorderrpt.get();
}
window.onload = function()
{
	svcorderrpt.load();
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
  var divToPrint=document.getElementById('data_table_detail');
  newWin= window.open("");
  newWin.document.write(divToPrint.outerHTML);
  newWin.print();
  newWin.close();
}
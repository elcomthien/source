var messrpt=new function MessageReport()
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
		html+="<input size=\"10\" id=\"m_date1\" /><button id=\"m_btn1\" class='buttom_calenda'>...</button>";
		html+="</div>";
			
		html+="<div style=\"float: left;width: 100px;\">";
		html+=langpms.to_date;
		html+="</div>";
		html+="<div style=\"float: left;width: 150px;\">";
		html+="<input size=\"10\" id=\"m_date2\" /><button id=\"m_btn2\" class='buttom_calenda'>...</button>";
		html+="</div>";
		html+="<select id='filter-room-message' style='width: 120px; height: 21px; float: right; margin-right: 20px;' onchange='messrpt.filter();'></select>";
		$("div_time").innerHTML=html;
		$("bt_excel").onclick=my.excel;
		$("bt_print").onclick=my.print;
//		var now = new Date();				
//	    var prev = new Date(); prev.setMonth(prev.getMonth() - 1);
//	    var d2 = now.getDate();
//	    if(d2 < 10) 
//	    	d2 = "0" + d1;
//	    var m2 = (now.getMonth()+1);
//	    if(m2 < 10)
//	    	m2 = "0" + m2;
//	    var time2= d2 + "/" + m2 + "/" + now.getFullYear();
////	    var time2= now.getDate()+"/"+(now.getMonth()+1)+"/"+now.getFullYear();		
//		$("m_date2").value=time2;
//		var d1 = prev.getDate();
//	    if(d1 < 10) 
//	    	d1 = "0" + d1;
//	    var m1 = (prev.getMonth()+1);
//	    if(m1 < 10)
//	    	m1 = "0" + m1;
//	    var time1= d1 + "/" + m1 + "/" + prev.getFullYear();		
//		$("m_date1").value=time1;
		var now = new Date();
		var d2 = now.getDate();
		if (d2 < 10)
			d2 = "0" + d2;
		var m2 = (now.getMonth() + 1);
		if (m2 < 10)
			m2 = "0" + m2;
		var time2 = d2 + "/" + m2 + "/" + now.getFullYear();
		$("m_date2").value = time2;
		
		var prev = new Date(now.setDate(now.getDate() - 6));
		var d1 = prev.getDate();
		if (d1 < 10)
			d1 = "0" + d1;
		var m1 = (prev.getMonth() + 1);
		if (m1 < 10)
			m1 = "0" + m1;
		var time1 = d1 + "/" + m1 + "/" + prev.getFullYear();
		$("m_date1").value = time1;
		
	   	var cal1= Calendar.setup({
		        inputField : "m_date1",
		        trigger    : "m_btn1",
		        onSelect   : my.changeDatefrom,
		        dateFormat : "%d/%m/%Y",
		        date:prev   
		 });   	   	
		var cal2= Calendar.setup({
	        inputField : "m_date2",
	        trigger    : "m_btn2",
	        onSelect   : my.changeDateNow,
	        dateFormat : "%d/%m/%Y",
	        date: now	        
	      });
		
		my.get();
	};	
	
	this.excel=function()
	{
		tableToExcel('data_table_message', 'Message Record');
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
			$("m_date2").value=t;				
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
			$("m_date1").value=t;				
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
		html+="<table id='data_table_message' cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"99%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\">";
		html+="<tr class=\""+this.classHeader+"\">";
//		html+="<th align='left'  valign=\"middle\"  width=\""+1+"%\"  >";
//		html+="</th>";	
		html+="<th align='left'  style='text-align: center;'  width=\""+5+"%\"  >";
		html+="Room";
		html+="</th>";	
		html+="<th align='left'  valign=\"middle\"  width=\""+10+"%\"  >";
		html+="Guest name";
		html+="</th>";	
		html+="<th align='center' width=\""+7+"%\" valign=\"middle\"  >";
		html+="Check in";
		html+="</th>";
		html+="<th align='center' width=\""+7+"%\" valign=\"middle\"  >";
		html+="Check out";
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\"  >";
		html+="Msg create";
		html+="</th>";
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\"  >";
		html+="Msg read";
		html+="</th>";
		html+="<th align='center' width=\""+10+"%\" valign=\"middle\"  >";
		html+="Sender";
		html+="</th>";
		html+="<th align='center' width=\""+19+"%\" valign=\"middle\"  >";
		html+="Message Detail";
		html+="</th>";
		html+="</tr>";	

		for ( var i = 0; i < this.List.length; i++) {
			if (i % 2 == 0) {
				html += "<tr class=\"" + this.bgRow2 + "\" id=\""
						+ this.List[i].Id + "\" style='font-size: 13px;'>";
			} else {
				html += "<tr class=\"" + this.bgRow1 + "\" id=\""
						+ this.List[i].Id + "\" style='font-size: 13px;'>";
			}
//			html += "<td align=\"left\"  valign=\"middle\">";
//			html += "</td>";

			html += "<td align=\"left\"  style='text-align: center;'>";
			html += this.List[i].Room;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Guest;
			html += "</td>";
			html += "<td align='center'  style='text-align: center;'>";
			html += this.List[i].Checkin;
			html += "</td>";
			html += "<td align='center' style='text-align: center;'>";
			html += this.List[i].Checkout;
			html += "</td>";
			html += "<td align='center'  style='text-align: center;'>";
			html += this.List[i].Create;
			html += "</td>";
			html += "<td align=\"left\"  style='text-align: center;'>";
			html += this.List[i].Read;
			html += "</td>";
			html += "<td align=\"left\"  style='text-align: center;'>";
			html += this.List[i].Sender;
			html += "</td>";
			html += "<td align=\"left\"  valign=\"middle\">";
			html += this.List[i].Content;
			html += "</td>";
			html += "</tr>";
		}
		html+="</table>";
		html+="</div>";
		return html;
	};
	this.get=function()
	{				
		var from =$("m_date1").value;
		var to =$("m_date2").value;
		var url="MessageReport?CMD=1";
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
					var _room=it[i].getElementsByTagName("room")[0].childNodes[0].nodeValue;
					var _guest=it[i].getElementsByTagName("guest")[0].childNodes[0].nodeValue;
					var _checkin=it[i].getElementsByTagName("checkin")[0].childNodes[0].nodeValue;
					var _checkout=it[i].getElementsByTagName("checkout")[0].childNodes[0].nodeValue;
					var _create=it[i].getElementsByTagName("create")[0].childNodes[0].nodeValue;
					var _read=it[i].getElementsByTagName("read")[0].childNodes[0].nodeValue;
					var _sender=it[i].getElementsByTagName("sender")[0].childNodes[0].nodeValue;
					var _content=it[i].getElementsByTagName("content")[0].childNodes[0].nodeValue;
					arr[i]=new itemData(_room,_guest,_checkin,_checkout,_create,_read,_sender,_content);
				}	
				my.List=arr;
				table.load(my.List);
				my.loadRoom();
			};		
	};
	
	this.filter=function()
	{		
		var ftroom = document.getElementById("filter-room-message").value;
		var from =$("m_date1").value;
		var to =$("m_date2").value;
		var url="MessageReport?CMD=1";
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
				var j = 0;
				for(var i=0;i<it.length;i++)
				{					
					var _room=it[i].getElementsByTagName("room")[0].childNodes[0].nodeValue;
					var _guest=it[i].getElementsByTagName("guest")[0].childNodes[0].nodeValue;
					var _checkin=it[i].getElementsByTagName("checkin")[0].childNodes[0].nodeValue;
					var _checkout=it[i].getElementsByTagName("checkout")[0].childNodes[0].nodeValue;
					var _create=it[i].getElementsByTagName("create")[0].childNodes[0].nodeValue;
					var _read=it[i].getElementsByTagName("read")[0].childNodes[0].nodeValue;
					var _sender=it[i].getElementsByTagName("sender")[0].childNodes[0].nodeValue;
					var _content=it[i].getElementsByTagName("content")[0].childNodes[0].nodeValue;
					if(ftroom == "all" || _room == ftroom){
						arr[j]=new itemData(_room,_guest,_checkin,_checkout,_create,_read,_sender,_content);
						j++;
					}
				}	
				my.List=arr;
				table.load(my.List);
			};		
	};
	
	function itemData(room,guest,checkin,checkout,create,read,sender,content)
	{
		this.Room = room;
		this.Guest = guest;
		this.Checkin = checkin;
		this.Checkout = checkout;
		this.Create = create;
		this.Read = read;
		this.Sender = sender;
		this.Content = content;
	}
	this.loadRoom = function(){
		var from = $("m_date1").value;
		var to = $("m_date2").value;
		var url = "MessageReport?CMD=2";
		url += "&from=" + from;
		url += "&to=" + to;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			document.getElementById("filter-room-message").innerHTML = tx;
		};
	};
};
function reload(){
	messrpt.get();
}
window.onload = function()
{
	messrpt.load();
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
  var divToPrint=document.getElementById('data_table_message');
  newWin= window.open("");
  newWin.document.write(divToPrint.outerHTML);
  newWin.print();
  newWin.close();
}
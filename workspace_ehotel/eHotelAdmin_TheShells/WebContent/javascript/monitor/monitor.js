var arr;
var dtCh= "/";
var minYear=1900;
var maxYear=2100;
function changeTab(id)
{
		var div=$("tab_menu").getElementsByTagName("div");
		
		if(id==0)
		{	$("id_table").style.display = 'block';
			$("id_table1").style.display = 'none';
			div[0].className="tab_menu_item_sel";
			div[1].className="tab_menu_item";
			
		}else
		{	
			
			$("id_table1").style.display = 'block';
			$("id_table").style.display = 'none';
			div[1].className="tab_menu_item_sel";
			div[0].className="tab_menu_item";	
			loadstatus_music();
			showprocess_music();
			viewloadDf_muisc();
			setInterval( "viewloadDf_muisc()", 40000 );  
		}
}
function processService(serviceId,typeId){
	//serviceId=0;start  1= start
	var url="VideoMonitor?CMD=2"
		url+="&srcId="+ serviceId
		url+="&typeId="+ typeId
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{	
			if(typeId==0){
			hideShow(serviceId);
			}else{
				hideShow_music(serviceId);
			}
			
		}
}
function hideShow(id){
	if(id==1){
		
		//show button start ,hide lable start
		$("serviceStart").style.display = 'none';
		$("serviceStop").style.display = 'block';
		$("serviceStopStart").style.display = 'block';
		$("serviceNameStart").style.display = 'none';
	}else{
		//show lable start , hide buton start
		$("serviceStop").style.display = 'none';
		$("serviceStart").style.display = 'block';
		$("serviceNameStart").style.display = 'block';
		$("serviceStopStart").style.display = 'none';
	}
	
}
function hideShow_music(id){
	if(id==1){
		
		//show button start ,hide lable start
		$("serviceStart1").style.display = 'none';
		$("serviceStop1").style.display = 'block';
		$("serviceStopStart1").style.display = 'block';
		$("serviceNameStart1").style.display = 'none';
	}else{
		//show lable start , hide buton start
		$("serviceStop1").style.display = 'none';
		$("serviceStart1").style.display = 'block';
		$("serviceNameStart1").style.display = 'block';
		$("serviceStopStart1").style.display = 'none';
	}
	
}
function hideShowButtion(id){
	if(id==1){
		$("viewbutton").style.display = 'none';
		$("hidebutton").style.display = 'block';
	}else{
		
		$("hidebutton").style.display = 'none';
		$("viewbutton").style.display = 'block';
	}

}
function showDate(){
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var str= day + "/" + month + "/" + year;
	$("txtdate").value=str;
}
function checkCurrDate(date){
	var c =0;
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var str= day + "/" + month + "/" + year;
	if(str==date){
		c=1;//ngay hien tai
	}
	return c;
	
}
function loadstatus_video(){
	var url="VideoMonitor?CMD=9"
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{	
			if(tx=="run"){	
				hideShow(0);
			}else{
				hideShow(1);
			}
		}
}
function loadstatus_music(){
	var url="VideoMonitor?CMD=10"
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{	
			if(tx=="run"){	
				hideShow_music(0);
			}else{
				hideShow_music(1);
			}
		}
}
function showprocess(){
	var url="VideoMonitor?CMD=1"
		url+="&r="+Math.random();	
		var f=new AjaxGetXML(url);
		f.complet=function(tx)
		{	
			var xml=tx.getElementsByTagName("xml");			
			var it=tx.getElementsByTagName("Item");	
			for(var i=0;i<it.length;i++)
			{	
				if(i==0){
					var timeout=it[i].getElementsByTagName("timeout")[0].childNodes[0].nodeValue;	
					$("txtimeout").value=timeout;
				}
				if(i==1){
					var frompage=it[i].getElementsByTagName("frompage")[0].childNodes[0].nodeValue;
					$("txtfrompage").value=frompage;
				}
				if(i==2){
					var topage=it[i].getElementsByTagName("topage")[0].childNodes[0].nodeValue;
					$("txttopage").value=topage;
				}
					if(i==3){
						var process=it[i].getElementsByTagName("process")[0].childNodes[0].nodeValue;
						if(process=="true"){
							$("txtprocess").checked=true;
						}else{
							$("txtprocess").checked=false;
						}
					}
			}
			
		}
}
function showprocess_music(){
	var url="VideoMonitor?CMD=6"
		url+="&r="+Math.random();	
		var f=new AjaxGetXML(url);
		f.complet=function(tx)
		{	
			var xml=tx.getElementsByTagName("xml");			
			var it=tx.getElementsByTagName("Item");	
			for(var i=0;i<it.length;i++)
			{	
				if(i==0){
					var timeout=it[i].getElementsByTagName("timeout")[0].childNodes[0].nodeValue;	
					$("txtimeout_music").value=timeout;
				}
				if(i==1){
					var frompage=it[i].getElementsByTagName("frompage")[0].childNodes[0].nodeValue;
					$("txtfrompage_music").value=frompage;
				}
				if(i==2){
					var topage=it[i].getElementsByTagName("topage")[0].childNodes[0].nodeValue;
					$("txttopage_music").value=topage;
				}
				if(i==3){
					var process=it[i].getElementsByTagName("process")[0].childNodes[0].nodeValue;
					$("txttype").value=process;
				}
			}
			
		}
}
function viewload()
{	
	
		hideShowButtion(1);
			var str="";
			var kt=-1;
			var date =$("txtdate").value;
			var k=-1;
			k= isDate(date);
			if(k==1){
				alert('Not a valid date, format DD/MM/YY ');
				hideShowButtion(0);
				return;
			}
			if(k==2){
				alert('Invalid Date !')
				hideShowButtion(0);
				return;
			}
			kt=checkCurrDate(date);
			if(kt==0){
				//khac ngay hien tai
				kt =0
			}else{
				kt=1;
			}
			var chuoi =date.split("/");
			var dd =chuoi[0];
			var mm=chuoi[1];
			var yy =chuoi[2];
			if(Number(dd) < 10){
				if(dd.length<=1){
					dd ="0"+dd;
				}
				
			}
			if(Number(mm)<10){
				if(mm.length<=1){
					mm ="0"+mm;
				}
			}
			str =yy + "-" + mm + "-" + dd;
			var url="VideoMonitor?CMD=3"
			url+="&date="+str
			url+="&CurrId="+kt
			url+="&r="+Math.random();	
			if(kt==1){
				var f=new AjaxGetXML(url);	
				f.complet=function(tx)
				{	
					arr =new Array();	
					var xml=tx.getElementsByTagName("xml");			
					var it=tx.getElementsByTagName("Item");
					for(var i=0;i<it.length;i++)
					{	
						var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;	
						arr[i]=new ItemMenu(_name);
					}
					
					document.getElementById("bt_bentrong").innerHTML=showData(arr);	
				}	
				
			}
			if(kt==0){
				var f=new AjaxGetText(url);	
				f.complet=function(tx)
				{	
					if(tx=='f'){
					document.getElementById("bt_bentrong").innerHTML="";
					alert("no file log");
					hideShowButtion(0);
					return;
					}
					loadPathDesktop();
					hideShowButtion(0);
				}
			}
			hideShowButtion(0);
			
}
function viewloadDf()
{	
			var url="VideoMonitor?CMD=5"
			url+="&r="+Math.random();	
				var f=new AjaxGetXML(url);	
				f.complet=function(tx)
				{	
					arr =new Array();	
					var xml=tx.getElementsByTagName("xml");			
					var it=tx.getElementsByTagName("Item");
					for(var i=0;i<it.length;i++)
					{	
						var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;	
						arr[i]=new ItemMenu(_name);
					}
					
					document.getElementById("bt_bentrong").innerHTML=showData(arr);	
				}	
}
function viewloadDf_muisc()
{	
	
			var url="VideoMonitor?CMD=8"
			url+="&r="+Math.random();	
				var f=new AjaxGetXML(url);	
				f.complet=function(tx)
				{	
					arr =new Array();	
					var xml=tx.getElementsByTagName("xml");			
					var it=tx.getElementsByTagName("Item");
					for(var i=0;i<it.length;i++)
					{	
						var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;	
						arr[i]=new ItemMenu(_name);
					}
					
					document.getElementById("bt_bentrong1").innerHTML=showData(arr);	
				}	
}
function updateConfig(){
	var timeout =$("txtimeout").value;
	if(timeout.length<=0)
	{
		var albox=new alertBox();
		albox.show("Timeout not empty");
		return;
	}
	var frompage =$("txtfrompage").value;
	if(frompage.length<=0)
	{
		var albox=new alertBox();
		albox.show("From page not empty");
		return;
	}
	var topage =$("txttopage").value;
	if(topage.length<=0)
	{
		var albox=new alertBox();
		albox.show("To page not empty");
		return;
	}
	var process =$("txtprocess").checked;
	var trangthai="";
	if(process==true){
		trangthai="true";
	}else{
		trangthai="false";
	}
	
	var url="VideoMonitor?CMD=4"
		url+="&timeout="+timeout
		url+="&frompage="+frompage
		url+="&topage="+topage
		url+="&process="+trangthai
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);					
		f.complet=function(tx){
		
			var albox=new alertBox();
			if(tx=="fstop"){
				albox.show("Error stop service");
				return;
			}else if(tx=="fwlog"){
				albox.show("Error write log");
				return;
			}else if(tx=="fstart"){
				albox.show("Error start service");
				return;
			}else{
				albox.show("updated");
				return;
			}
		}
}
function updateConfig_music(){
	var timeout =$("txtimeout_music").value;
	if(timeout.length<=0)
	{
		var albox=new alertBox();
		albox.show("Timeout not empty");
		return;
	}
	var frompage =$("txtfrompage_music").value;
	if(frompage.length<=0)
	{
		var albox=new alertBox();
		albox.show("From page not empty");
		return;
	}
	var topage =$("txttopage_music").value;
	if(topage.length<=0)
	{
		var albox=new alertBox();
		albox.show("To page not empty");
		return;
	}
	var modtype =$("txttype").value;
	if(modtype.length<=0)
	{
		var albox=new alertBox();
		albox.show("Mod type not empty");
		return;
	}

	var url="VideoMonitor?CMD=7"
		url+="&timeout="+timeout
		url+="&frompage="+frompage
		url+="&topage="+topage
		url+="&modtype="+modtype
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);					
		f.complet=function(tx){
		
			var albox=new alertBox();
			if(tx=="fstop"){
				albox.show("Error stop service");
			}else if(tx=="fwlog"){
				albox.show("Error write log");
			}else if(tx=="fstart"){
				albox.show("Error start service");
			}else{
				albox.show("updated");
			}
		}
}

function loadPathDesktop(){
		fullPath ="Log file :<a href='../downloadFile?CMD=1'>open log</a>";
		document.getElementById("bt_bentrong").innerHTML=fullPath;	
}
function showData(arr){
	var html="<table class=\"table2\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >";
	html+="<tbody >"
	for(var i=0;i<arr.length;i++)
	{
		html+="<tr>";
		html+="<td width='auto' valign='top'>"
		html+=arr[i].Name
		html+="</td>"
		html+="</tr>";
	}
	html+="</tbody>"
	html+="</table>";
	return html;
}
function ItemMenu(name)
{
this.Name=name;
}

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}
function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	k=0;
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		k=1;
	}
	if (strMonth.length<1 || month<1 || month>12){
		k=2;
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		k=2;
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		k=2;
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		k=2;
	}
	return k;
}



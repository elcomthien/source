var tag=0;
var arr;
var menuId=0;
var _menuId=0;
var TempMenuId=0;
var tempMid=0;
var Index=0;
var page=5;
var count;
var tempSearch =0;
var tempCoord_x=0;
var tempCoord_y=0;
var truoc=0;
var sau=0;
var ListCheck=new Vector();
var	divName=		"id_table";
var	col=			5;
var	classHeader=   "mytable_header";
var	classItem=		"classItem";
var	classTable=		"classTable";
var	classRowsel=	"mytable_row_sel";
var	classTable=	"mytable";
var	bgRow1=		"mytable_row1";
var	bgRow2=	"mytable_row2";
var bgmenu="MenuBoder";
var tempIdMap=0;
var temHL=0;
var coord_x_temp=0;
var coord_y_temp=0;
var ca_coord=0;
var notpage_coord_x=0;
var notpage_coord_y=0;
var txtSearchTemp="";
var txtSearchTemp1="";
var textsearch="";
var checktext=0;
function changeTab(id)
{
	var div=$("tab_menu").getElementsByTagName("div");
	if(id==0)
	{	temHL=0;
		$("id_table").style.display = 'block';
		$("id_table1").style.display = 'none';
		div[0].className="tab_menu_item_sel";
		div[1].className="tab_menu_item";
		ca_coord=0;
	}else
	{	ca_coord=0;
		$("group1").checked=true;
		temHL=0;	
		$("txtsearch").value="";
		$("id_table1").style.display = 'block';
		$("id_table").style.display = 'none';
		div[1].className="tab_menu_item_sel";
		div[0].className="tab_menu_item";			
		changeLang();
		initialize_tab2();			
	}
	tag=id;		
}
function initialize_tab2(){
	var latlng1 = new google.maps.LatLng(10.779867 ,106.686037);
	var myOptions1 =
	{
		 zoom: 12,
		 center: latlng1,
	 mapTypeId: google.maps.MapTypeId.ROADMAP
	};    		
	var map1 = new google.maps.Map(document.getElementById("map_canvas1"),myOptions1);
	setFrm(map1);
	getMenuLocation();
	TempMenuId=0;
}
var layer=new layer_vitual();
var my=this;
function showfromAdd(event)
{	
	layer.show();						
	var html="";		
	html+="<div class=\"form_detail_Movie_pms\" id=\"form_detail_song\">";
	html+="<div class='div_Title_song' align=\"left\">"+ themmoi + "";			
	html+="</div>";
	html+="<div style='float: left;width:390px;height:210px ;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10'>"
	html+="<div  class='left_Title_movie1'>";			
		html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";
		html+="<div style='width:30%;float:left'>" + ten +"(<font color='red'>*</font>):</div>"
		html+="<div style='width:50%;float:left'><input type=\"text\" size='38' id='txtNameAdd' class='textbox_input'/></div>"
		html+="</div>";	
		html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px'>";	
		html+="<div style='width:30%;float:left'>"+ diachi +":</div>"
		html+="<div style='width:50%;float:left'> <input type=\"text\" size='38' id='txtAddressAdd' class='textbox_input'/></div>"
		html+="</div>";	
		html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px'>";	
		html+="<div style='width:30%;float:left'>"+ kc +"(<font color='red'>*</font>):</div>"
		html+="<div style='width:50%;float:left'> <input type=\"text\" size='38' id='txtDistanceAdd' class='textbox_input' value='"+ calculateDistance(fX,ly,event.latLng.lat(),event.latLng.lng()) + "'/></div>"
		html+="</div>";
		html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px'>";	
		html+="<div style='width:30%;float:left'>"+ toado_x +"(<font color='red'>*</font>):</div>"
		html+="<div style='width:50%;float:left'> <input type=\"text\" size='38' id='txtXAdd' class='textbox_input' value=" + event.latLng.lat() + "></div>"
		html+="</div>";
		html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px'>";	
		html+="<div style='width:30%;float:left'>"+ toado_y +"(<font color='red'>*</font>):</div>"
		html+="<div style='width:50%;float:left'> <input type=\"text\" size='38' id='txtYAdd' class='textbox_input' value=" + event.latLng.lng() + "></div>"
		html+="</div>";
	html+="<div  style='float: left;width: 100%;' align='center'>";
	html+="<div style='padding-left: 17px' class='div_sub_buton'>";
	html+="<input  type=\"button\" value='"+ ok +"' id=\"form_textbox_ok\" class='div_buton' >"
	html+="<input type=\"button\" value='" + cancel +"' id=\"form_textbox_cancel\" class='div_buton'>"
	html+="</div>"
	html+="</div>";
	html+="</div>";	
	layer.addHtml(html);
	setCenterDIV("form_detail_song");	
	$("form_textbox_ok").onclick=function(){				
	my.accept();};
	$("form_textbox_cancel").onclick=function(){
	kk();
	};	
}
function setFrm(map)
{
	checktext=0;
    var infowindow2 = new google.maps.InfoWindow();
    google.maps.event.addListener(map, 'click', function(event) {
	showfromAdd(event);
	});
} 
function kk(){
	my.hide();
}
function cancel()
{
	my.hide();
}
function hide()
{
	$("div_layer_vitual").style.display="none";
}
this.accept=function()
{	
	var Name=$("txtNameAdd").value;
	var Address=$("txtAddressAdd").value;
	var Distance=$("txtDistanceAdd").value;
	var X=$("txtXAdd").value;
	var Y=$("txtYAdd").value;
	if(Name.length==0)
	{
		var albox=new alertBox();
		albox.show(checkName);			
		return;
	};
	if(Distance.length==0)
	{
		var albox=new alertBox();
		albox.show(checkDistance);			
		return;
	};
	if(X.length==0)
	{
		var albox=new alertBox();
		albox.show(checkX);			
		return;
	};
	if(Y.length==0)
	{
		var albox=new alertBox();
		albox.show(checkY);			
		return;
	};	
	if(TempMenuId!=0){
		tempMid =TempMenuId;
	}else{
		tempMid =_menuId;
	}
	var url="MapDirection?CMD=2"
	url+="&Mid="+tempMid
	url+="&Distance="+Distance
	url+="&X="+X
	url+="&Y="+Y
	url+="&t="+Math.random();
	var param="&Name="+Name+"&Address="+Address 
	var f=new AjaxPostText(url,param);				
	f.complet=function(tx)
	{
		if(tx=="failed"){
			alert(checkAdd);
		}else{
			 coord_x_temp=X;
			 coord_y_temp=Y;
			 ca_coord=1;
			getMenuLocationOnClick(-1,tempMid,0,0,0);
				initializeOnlick(tempMid,X,Y);
			hide();
		}
	}
}
function getMenuLocation()
{	//initializeOnlick(0,0);
		tempSearch =0;
		var url="MapDirection?CMD=3"
			url+="&pageIndex="+Index
			url+="&page="+page
			url+="&r="+Math.random();	
			//var f=new AjaxGetXML(url);	
			var f=new funPropery();				
			f.complet=function(tx)
			{	
				arr=new Array();
				var xml=tx.getElementsByTagName("xml");			
				count= xml[0].getAttribute("count");
				var mId= xml[0].getAttribute("menuId");	
				_menuId =mId;
				var it=tx.getElementsByTagName("Item");		
				for(var i=0;i<it.length;i++)
				{			
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;					
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _x=it[i].getElementsByTagName("x")[0].childNodes[0].nodeValue;
					var _y=it[i].getElementsByTagName("y")[0].childNodes[0].nodeValue;
					var _phone=it[i].getElementsByTagName("phone")[0].childNodes[0].nodeValue;
					var _addr=it[i].getElementsByTagName("addr")[0].childNodes[0].nodeValue;
					var _dis=it[i].getElementsByTagName("dis")[0].childNodes[0].nodeValue;
					var _stt=it[i].getElementsByTagName("stt")[0].childNodes[0].nodeValue;
					arr[i]=new ItemUrl(_stt,_id,_name,_phone,_addr,_dis,_x,_y);								
				}
				document.getElementById("displayTable").innerHTML=getHTML(arr,count);	
				renderEvent();
				//
				if(temHL==0){
					HightLighMenu(searchMenuId(_menuId),_menuId);
				}else{
					HightLighMenu(temHL,_menuId);
				}
			}
			AjaxFuncGetXML(url,f);	
}
function getMenuLocationOnClick(menuid,_mId,idx,coord_x,coord_y)
{
$("group1").checked=true;
 showText();
temHL=_mId;
$("txtsearch1").value="";
$("txtsearch").value="";
HightLighMenu(menuid,_mId);
	if(idx==0){
		Index =0;
	}
	if(coord_x==0 && coord_y==0){
		initializeOnlick(0,0,0);
	}
	tempSearch =0;
	
		TempMenuId =_mId;
		var url="MapDirection?CMD=5"
			url+="&pageIndex="+Index
			url+="&page="+page
			url+="&menuId="+_mId
			url+="&r="+Math.random();			
			var f=new AjaxGetXML(url);				
			f.complet=function(tx)
			{	
				arr=new Array();
				var xml=tx.getElementsByTagName("xml");			
				count= xml[0].getAttribute("count");			
				var it=tx.getElementsByTagName("Item");		
				for(var i=0;i<it.length;i++)
				{					
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;					
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _x=it[i].getElementsByTagName("x")[0].childNodes[0].nodeValue;
					var _y=it[i].getElementsByTagName("y")[0].childNodes[0].nodeValue;
					var _phone=it[i].getElementsByTagName("phone")[0].childNodes[0].nodeValue;
					var _addr=it[i].getElementsByTagName("addr")[0].childNodes[0].nodeValue;
					var _dis=it[i].getElementsByTagName("dis")[0].childNodes[0].nodeValue;
					var _stt=it[i].getElementsByTagName("stt")[0].childNodes[0].nodeValue;
					arr[i]=new ItemUrl(_stt,_id,_name,_phone,_addr,_dis,_x,_y);								
				}
				document.getElementById("displayTable").innerHTML=getHTML(arr,count);	
				renderEvent();			
			}				
}
function getKqSearch(id)
{ca_coord=0;
if(id=="1"){
Index=0;
}
		tempSearch =1;
		if(TempMenuId!=0){
				tempMid =TempMenuId;
			}else{
				tempMid =_menuId;
			}
			textsearch=$("txtsearch").value;
		var url="MapDirection?CMD=6"
			url+="&textsearch="+textsearch
			//url+="&ids="+ids
			url+="&menuId="+tempMid
			url+="&pageIndex="+Index
			url+="&page="+page
			url+="&r="+Math.random();			
			var f=new AjaxGetXML(url);				
			f.complet=function(tx)
			{	
				arr=new Array();
				var xml=tx.getElementsByTagName("xml");			
				count= xml[0].getAttribute("count");				
				var it=tx.getElementsByTagName("Item");		
				for(var i=0;i<it.length;i++)
				{				
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
					var _stt=it[i].getElementsByTagName("stt")[0].childNodes[0].nodeValue;					
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _x=it[i].getElementsByTagName("x")[0].childNodes[0].nodeValue;
					var _y=it[i].getElementsByTagName("y")[0].childNodes[0].nodeValue;
					var _phone=it[i].getElementsByTagName("phone")[0].childNodes[0].nodeValue;
					var _addr=it[i].getElementsByTagName("addr")[0].childNodes[0].nodeValue;
					var _dis=it[i].getElementsByTagName("dis")[0].childNodes[0].nodeValue;
					arr[i]=new ItemUrl(_stt,_id,_name,_phone,_addr,_dis,_x,_y);								
				}
				document.getElementById("displayTable").innerHTML=getHTML(arr,count);	
				renderEvent();	
			}
}
function ItemUrl(stt,id,name,phone,addr,dis,x,y)
{
	this.Stt=stt; this.Id=id;this.Name=name;this.Phone=phone;this.Addr=addr;this.Dis=dis;this.X=x;this.Y=y;
}
function getHTML(arr,count)
	{
		var html="";
		html+="<table  cellspacing=\"0\" width='100%' cellpadding=\"0\" border=\"0\" height='30' class=\""+this.classTable+"\" id='table_id'>";
			for(i=0;i<arr.length;i++){
			if(i%2==0)
			{				
				html+="<tr  onclick='initializeOnlick("+ arr[i].Id +","+ arr[i].X +","+ arr[i].Y +")' class=\""+ bgRow2+"\" id=\""+this.arr[i].Id+"\"  >";
			}else
			{
				html+="<tr onclick='initializeOnlick("+ arr[i].Id +","+ arr[i].X +","+ arr[i].Y +")' class=\""+ bgRow1+"\" id=\""+this.arr[i].Id+"\"   >";
			}
				html+="<td   height=30' style='padding-left:5px' width='15%'>" + arr[i].Stt +"</td>";
				html+="<td  height='30' width='65%' ondblclick='showfrmDBClick("+ arr[i].Id +","+ arr[i].X +","+ arr[i].Y +")'><a style='text-decoration:none;color:#666666'>" + arr[i].Name +"</a></td>";
				html+="<td  height='30' width='10%'><img onclick='javascript:ShowfrmEditCoord("+ arr[i].Id +");' src='../icon/edit.png' height='15px' width='15px'></td>";
				html+="<td  height='30' width='10%'><img onclick='javascript:deleteLocation("+ arr[i].Id +");' src='../icon/16-square-green-remove.png' height='15px' width='15px'></td>";
				html+="</tr>";
			}
			if(count >5){
			html+="<tr>";
				html+="<td height='35' width='100%' colspan='4'>";
							var page=count/5;
							page+=0.4;
							page=Math.round(page);
							if(page >1){
								html+="<div id=\"div_page\" class='div_page1'>";
								html+="<div class='div_page_icon_left' id='div_page_icon_left'>";
								html+="</div>";
								if(page<=5){
								var f =0;
									for(var i=0;i<page;i++)
									{
										if(i==Index)
										{
											html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+i+"\">";
										}
										else
										{
											f++;
											if(f==page){
												html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+Index+"\">";
											}else{
												html+="<a class='div_page_a' href=\"javascript:\" id=\""+i+"\">";
											}
										}
										html+=(i+1);
										html+=" </a>";
									}	
								}else{
									if(Index<=3)
									{
										for(var i=0;i<5;i++)
										{
											if(i==Index)
											{
												html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+i+"\">";
											}else
											{
												html+="<a class='div_page_a' href=\"javascript:\" id=\""+i+"\">";
											}
											html+=(i+1)
											html+=" </a>";	
											sau=5;			
										}	
									}else{
										if(Index>=page)
										{				
											var b=page-4;
											var t=Number(Index);				
											for(var i=b;i<=page;i++)
											{
												if(i==(t+1))
												{
													html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+(i-1)+"\">";
												}else
												{
													html+="<a class='div_page_a' href=\"javascript:\" id=\""+(i-1)+"\">";
												}
												html+=(i)
												html+=" </a>";				
											}
										}else{
											
											var b=Index-1;
											if((sau==(b+2))&& sau >5){
												truoc =sau;
											}else if(sau==5){
												truoc =5;
											}else if(truoc==(b+2)){
												truoc =truoc -4;
											}
											for(var i=truoc;i<(truoc + 5);i++){
												if(truoc+4==i){
													sau =truoc + 4;
												}
													if(i<=page){
														if((i-2)==b){
															html+="<a class='div_page_a_sel' href=\"javascript:\" id=\""+(i-1)+"\">";
														}else{
															html+="<a class='div_page_a' href=\"javascript:\" id=\""+(i-1)+"\">";
														}
															html+=(i);
															html+=" </a>";
													}
											}		
										}
									}
								}		
								html+="<div class='div_page_icon_right' id='div_page_icon_right'>";
								html+="</div>";
								html+="</div>";
							}
				html+="</td>";
			html+="</tr>";
		}
		html+="</table>";	
		return html;
	}
function ShowfrmEditCoord(id)
		{ca_coord=0;
		var arr1 =new Array();
		for(var i=0;i<arr.length;i++){
			if(arr[i].Id==id){
				arr1[0] =new ItemUrl(1,arr[i].Id,arr[i].Name,arr[i].Phone,arr[i].Addr,arr[i].Dis,arr[i].X,arr[i].Y);
				break;
			}
		}	
		layer.show();						
		var html="";
		
			html+="<div class=\"form_detail_Movie_Edit\" id=\"form_detail_song\">";
			html+="<div class='div_Title_song' align=\"left\">"+ checkCoord +"";			
			html+="</div>";
			html+="<div style='float: left;border: 2px solid #dddddd; width:375px;height :150px ;margin-left: 10px;margin-top: 10;'>"
			html+="<div style='float: left' class='left_Title_movie'>";
				html+="<div style='height:20;width:350px;margin-bottom: 15px' >";
				html+="<div  style='width:25%;float:left'>" + ten +":</div>"
				html+="<div style='width:50%;float:left'>" + arr1[0].Name+"</div>";
				html+="</div>";	
				html+="<div style='height:20;width:350px;margin-bottom: 15px'>";	
				html+="<div   style='width:25%;float:left'>"+ toado_x +"(<font color='red'>*</font>):</div>"
				html+="<div style='width:50%;float:left'> <input  type=\"text\" size='38' id='txtXEdit' class='textbox_input' value=" + arr1[0].X + "></div>"
				html+="</div>";
				html+="<div style='height:20;width:350px;margin-bottom: 15px'  >";	
				html+="<div  style='width:25%;float:left'>"+ toado_y +"(<font color='red'>*</font>):</div>"
				html+="<div style='width:50%;float:left'> <input type=\"text\" size='38' id='txtYEdit' class='textbox_input' value=" + arr1[0].Y + "></div>"
				html+="</div>";
			html+="<div  style='float: left;width: 100%;' align='center'>";
			html+="<div style='padding-right: 20px'  class='div_sub_buton'>";
			html+="<input style='margin-left: 27px' type=\"button\" value='"+ ok +"' id=\"form_textbox_ok\" class='div_buton' >"
			html+="<input type=\"button\" value='"+ cancel +"' id=\"form_textbox_cancel\" class='div_buton'>"
			html+="</div>"
			html+="</div>";
			html+="</div>";	
			layer.addHtml(html);
			setCenterDIV("form_detail_song");	
			$("form_textbox_ok").onclick=function(){				
			callUpdateCoordAjax(arr1[0].Id,arr[i].X,arr[i].Y);};
			$("form_textbox_cancel").onclick=function(){
			kk();
			};			
	}
function calculateDistance(x,y,x1,y1){
	var R = 6371;
	var dLat = toRad(x1-x);
	var dLon = toRad(y1-y); 
	var dLat1 = toRad(x);
	var dLat2 = toRad(x1);
	var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	Math.cos(dLat1) * Math.cos(dLat1) *
	Math.sin(dLon/2) * Math.sin(dLon/2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
	var d = Math.round((R * c)*10)/10;
	return d +"km";
}
function toRad(deg)
{
return deg * Math.PI/180;
}
function callUpdateCoordAjax(id,coord_x,coord_y)
{ca_coord=0;
		var cx =$("txtXEdit").value;
		var cy =$("txtYEdit").value;
		if(cx.length==0)
		{
			var albox=new alertBox();
			albox.show("checkX");			
			return;
		};
		if(cy.length==0)
		{
			var albox=new alertBox();
			albox.show("checkY");			
			return;
		};	
		var url="MapDirection?CMD=4"
			url+="&id="+id
			url+="&cx="+cx
			url+="&cy="+cy
			url+="&t="+Math.random();
			var f=new AjaxGetText(url);			
			f.complet=function(tx)
			{				
				if(tx=="failed"){
					alert(checkUpdate);
				}else{
					if(TempMenuId==0){
						TempMenuId=_menuId;
					}
					if(tempSearch==1){
						getKqSearch(0);
					}else{
						getMenuLocationOnClick(-1,TempMenuId,1,0,0);
						tempSearch =0;
						initializeOnlick(id,coord_x,coord_y);
					}
					hide();
				}
			}
}
function callUpdateLocationAjax(id,phone,coord_x,coord_y)
{ca_coord=0;
		var cname =$("txtNameUp").value;
		var caddr =$("txtAddressUp").value;
		var cdis =$("txtDistanceUp").value;
		var cx =$("txtXUp").value;
		var cy =$("txtYUp").value;
		if(cname.length==0)
		{
			var albox=new alertBox();
			albox.show(checkName);			
			return;
		};
		if(cx.length==0)
		{
			var albox=new alertBox();
			albox.show(checkX);			
			return;
		};
		if(cy.length==0)
		{
			var albox=new alertBox();
			albox.show(checkY);			
			return;
		};	
		var url="MapDirection?CMD=8"
		url+="&id="+id
		url+="&dis="+cdis
		url+="&x="+cx
		url+="&y="+cy
		url+="&phone="+phone
		url+="&t="+Math.random();
		var param="&name="+cname+"&addr="+caddr 
		var f=new AjaxPostText(url,param);			
		f.complet=function(tx)
		{		
			if(tx=="failed"){
				alert(checkUpdate);
			}else{
				if(TempMenuId==0){
					TempMenuId=_menuId;
				}if(tempSearch==1){
					getKqSearch(0);
				}else{
					getMenuLocationOnClick(-1,TempMenuId,1,0,0);
					initializeOnlick(id,coord_x,coord_y);
				}
				hide();
			}
		}
}
function deleteLocation(id){
	ca_coord=0;
var cfBox=new conformBox();
		cfBox.show(checkDeleteFrom,ok,cancel);
		cfBox.accept=function(){
	  var url="MapDirection?CMD=7"
			url+="&id="+id
			url+="&t="+Math.random();
			var f=new AjaxGetText(url);			
			f.complet=function(tx)
			{				
				if(tx=="failed"){
					alert(checkDelete);
				}else{
					if(TempMenuId==0){
						TempMenuId=_menuId;
					}
		if(tempSearch==1){
			getKqSearch(0);
		}else{
			getMenuLocationOnClick(-1,TempMenuId,1,0,0);
			tempSearch =0;
		}
				}
			}
		}
}
function loadIdex(index)
{
	Index=index
	if(TempMenuId!=0){
		if(tempSearch ==1){
			getKqSearch(0);
			}else{
			getMenuLocationOnClick(-1,TempMenuId,1,0,0);
		}
	}else{
		if(tempSearch ==1){
			getKqSearch(0);
		}else{
			getMenuLocation();
		}
	}
}
function nextindex()
{	ca_coord=0;
	var cpage=((count-1)/page)-1;
	if(Index<cpage)
		Index++;
	loadIdex(Index);
}
function backindex()
{ca_coord=0;
		if(Index>0)Index--;
		loadIdex(Index);
}
 function renderEvent()
{	
	if($("div_page")!=undefined)
	{
		var div=$("div_page");
		var a=div.getElementsByTagName("a");
		for(var i=0;i<a.length;i++)
		{
			var item=a[i];				
			item.onclick=function(e){
				
				loadIdex(this.id);				
			};
		}
		$("div_page_icon_right").onclick=function(){nextindex()};
		$("div_page_icon_left").onclick=function(){backindex()};
	}	
}
function showfrmDBClick(id,x,y){
	ca_coord=0;
	var _name="";
	var _addr="";
	var _dis="";
	var _phone="";
	var _x="";
	var _y="";
	for(var i=0;i<arr.length;i++){
		if(arr[i].Id==id){
			_name =arr[i].Name;
			_addr =arr[i].Addr;
			if(_addr=='\nnull'){
				_addr="";
			}
			_dis =arr[i].Dis;
			_phone =arr[i].Phone;
			_x=arr[i].X;
			_y=arr[i].Y;
			break;
		
		}
	}
	layer.show();						
		var html="";
			html+="<div class=\"form_detail_Movie_pms\" id=\"form_detail_song\">";
			html+="<div class='div_Title_song' align=\"left\">" +changLocation+ "";			
			html+="</div>";
			html+="<div style='float: left;width:390px;height:210px ;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10'>"
			html+="<div class='left_Title_movie1'>";
				html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px;margin-top: 5px'>";
				html+="<div style='width:30%;float:left'>" + ten + "(<font color='red'>*</font>):</div>"
				html+="<div style='width:50%;float:left'><input type=\"text\" size='38' id='txtNameUp' class='textbox_input' value='" + _name + "'></div>"
				html+="</div>";	
				html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px'>";	
				html+="<div style='width:30%;float:left'>"+ diachi +":</div>"
				html+="<div style='width:50%;float:left'> <input type=\"text\" size='38' id='txtAddressUp' class='textbox_input' value='" + _addr + "'></div>"
				html+="</div>";	
				html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px'>";	
				html+="<div style='width:30%;float:left'>" +kc+"(<font color='red'>*</font>):</div>"
				html+="<div style='width:50%;float:left'> <input type=\"text\" size='38' id='txtDistanceUp' class='textbox_input' value='" + _dis + "'></div>"
				html+="</div>";
				html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px'>";	
				html+="<div style='width:30%;float:left'>"+toado_x+"(<font color='red'>*</font>):</div>"
				html+=" <div style='width:50%;float:left'><input type=\"text\" size='38' id='txtXUp' class='textbox_input' value='" + x + "'></div>"
				html+="</div>";
				html+="<div style='height:20;width:390px;margin-bottom: 15px;margin-left: 10px'>";	
				html+="<div style='width:30%;float:left'>"+toado_y+"(<font color='red'>*</font>):</div>"
				html+="<div style='width:50%;float:left'> <input type=\"text\" size='38' id='txtYUp' class='textbox_input' value='" + y + "'></div>"
				html+="</div>";
			html+="<div  style='float: left;width: 100%;' align='center'>";
			html+="<div style='padding-left: 17px'  class='div_sub_buton'>";
			html+="<input type=\"button\" value='"+ ok +"' id=\"form_textbox_ok\" class='div_buton' >"
			html+="<input type=\"button\"  value='"+ cancel +"' id=\"form_textbox_cancel\" class='div_buton'>"
			html+="</div>"
			html+="</div>";
			html+="</div>";	
			layer.addHtml(html);
			setCenterDIV("form_detail_song");	
			$("form_textbox_ok").onclick=function(){				
			callUpdateLocationAjax(id,_phone,_x,_y);};
			$("form_textbox_cancel").onclick=function(){
			kk();
			};		
}	
function shiftSelect(id)
	{
	var table = document.getElementById("table_id");
	var tr = table.getElementsByTagName("tr");		
		for(var i=0;i<tr.length;i++)
		{
			if(i%2==0)
			{
				tr[i].className=bgRow2;
			}else
			{
				tr[i].className=bgRow1;
			}	
			if(id!=0){
				if(tr[i].id==id)
				{ca_coord=0;
					tempIdMap =id;
					tr[i].className="mytable_row_sel2";
				}
			}
		}			
	}
function shiftSelect1(id)
	{
	var table = document.getElementById("table_menu");
	var td = table.getElementsByTagName("td");		
		for(var i=1;i<td.length;i++)
		{
		
			if(id!=0){
				if(td[i].id==id)
				{ca_coord=0;
					td[i].className="mytable_row_sel";
					break;
				}
			}
		}			
	}
function hideDivGoogleMap(){

	$("div1").style.width=640;
	$("div2").style.display = 'none';
	$("div5").style.display = 'block';
	initialize();
}
function showDivGoogleMap(){

	$("div1").style.width=400;
	$("div2").style.display = 'block';
	$("div5").style.display = 'none';
	initialize();
}
function hideDivGoogleMap1(){
	$("div3").style.width=640;
	$("div4").style.display = 'none';
	$("div_tab2").style.display = 'block';
	var x=document.getElementById("group1").checked;
	if(x==true){
		txtSearchTemp =$("txtsearch").value;
		if(ca_coord==1){
			initializeOnlick(tempIdMap,coord_x_temp,coord_y_temp);
		}else{
			if(tempIdMap!=0){
				ca_coord=0;
				var _x="";
				var _y="";
				for(var i=0;i<arr.length;i++){
					if(arr[i].Id==tempIdMap){
						_x=arr[i].X;
						_y=arr[i].Y;
						break;
					}
				}
				 notpage_coord_x=_x;
				 notpage_coord_y=_y;
				initializeOnlick(tempIdMap,_x,_y);
			}else{
				initializeOnlick(0,0,0);
			}
		}
	}else{
		txtSearchTemp1=$("txtsearch1").value;
		if(txtSearchTemp1!=""){
			getKqSearchDirection(txtSearchTemp1);
		}else{
			initializeOnlick(0,0,0);
		}
		checktext=1;
	}
}
function showDivGoogleMap1(){
	$("div3").style.width=400;
	$("div4").style.display = 'block';
	$("div_tab2").style.display = 'none';
	$("txtsearch").value=txtSearchTemp;
	if(checktext!=1){
		getKqSearch(0);
		initializeOnlick(tempIdMap,notpage_coord_x,notpage_coord_y);
	}else{
		$("search2").style.display = 'block';
		$("search1").style.display = 'none';
		$("txtsearch1").value=txtSearchTemp1;
		$("group2").checked=true;
	}
}
function HightLighMenu(id,menuid){
	temHL =menuid;
var _div ="div3"+id;
if("div30"==_div){
	$(_div).className="MenuBoder";
	$("div31").className="";
	$("div32").className="";
	$("div33").className="";
}else if("div31"==_div){
	$(_div).className="MenuBoder";
	$("div30").className="";
	$("div32").className="";
	$("div33").className="";
}
else if("div32"==_div){
	$(_div).className="MenuBoder";
	$("div30").className="";
	$("div31").className="";
	$("div33").className="";
}
else if("div33"==_div){
	$(_div).className="MenuBoder";
	$("div30").className="";
	$("div31").className="";
	$("div32").className="";
}
}
function searchMenuId(id){
var ima=$("phanduoi1").getElementsByTagName("img");
var kq =0;
for(var i=0;i<ima.length;i++){
		if(ima[i].id==id){
			kq =i;
		break;
		}
}
return kq;
}
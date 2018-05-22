var Index=0;
var page=6;
var	col=			5;
var	classItem=		"classItem";
var	classTable=		"classTable";
var	classRowsel=	"mytable_row_sel";
var	classTable=	"mytable";
var	bgRow1=		"mytable_row1";
var	bgRow2=	"mytable_row2";
var bgmenu="MenuBoder";
var layer=new layer_vitual();
var my=this;
var tempIdMenu=0;
var ctTable=new contextTableEX();
var ctMenu=new contextMenu1();
var arr;
var arrGroup;
var my=this;
var count=0;
var tempId=0;
var temIdmenuGroup=0;
var listArr;
var k=new Array();
var Status;
var fmenuIdGF=0;
var kt=0;
var aa=0;
var truoc=0;
var tampt=0;
var isAdmin="";
var userName="";
/*document.onclick=function()
{	
	closeMenu();	
}*/
function loadMenu(arr)
{	
	aa =arr.length;
	arrGroup =arr;			
	document.getElementById("menutreeAd").innerHTML=ShowHTMLMenu(arr);
	if(arr.length==0){
		$("AddMenu").style.display = 'block';
	}else{
		$("AddMenu").style.display = 'none';
	}
}
this.pageclick=function()
{
	if(ctTable.state)
	ctTable.hide();
}
function nextindex()
{	
	var cpage=((count-1)/page)-1;
	if(Index<=cpage)
		Index++;
	loadIdex(Index);
}
function backindex()
{
	if(Index>0)Index--;
		loadIdex(Index);
}
function loadIdex(index)
{
	Index=index;
	loadUserInfor(tempIdMenu);
}
function loadUserInfor(mId)
{
	page=6;
	tempIdMenu=mId;
		var url="UserMgn?CMD=2";
		url+="&pageIndex="+Index;
		url+="&page="+page;
		url+="&menuIdUser="+mId;
		url+="&r="+Math.random();	
		var f=new AjaxGetXML(url);				
		f.complet=function(tx)
		{	
			arr =new Array();	
			var xml=tx.getElementsByTagName("xml");			
			count= xml[0].getAttribute("count");
			var it=tx.getElementsByTagName("Item");	
			for(var i=0;i<it.length;i++)
			{	
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;					
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _department=it[i].getElementsByTagName("department")[0].childNodes[0].nodeValue;
				if(_department==-1){
				_department="";
				}
				var _createDate=it[i].getElementsByTagName("createDate")[0].childNodes[0].nodeValue;
				var _Active=it[i].getElementsByTagName("Active")[0].childNodes[0].nodeValue;
				var _stt=it[i].getElementsByTagName("stt")[0].childNodes[0].nodeValue;
				var _addr=it[i].getElementsByTagName("address")[0].childNodes[0].nodeValue;
				var _account=it[i].getElementsByTagName("account")[0].childNodes[0].nodeValue;
				arr[i]=new ItemUrlUser(_stt,_id,_name,_department,_createDate,_Active,_addr,_account);
				if(_id==tempId){
					Status =_Active;
				}
			}
			document.getElementById("id_table").innerHTML=getHTMLUser(arr,count);
			document.getElementById("totalUser").innerHTML=ShowTotalUser(count);	
			renderEvent();
			renderEventGroup();
			ClickItem(mId);
		}
}
function getMenuXML()
{
	var url="UserMgn?CMD=8";
	url+="&r="+Math.random();			
	var f=new AjaxGetXML(url);				
	f.complet=function(tx)
	{	
		listArr=new Array();
		var xml=tx.getElementsByTagName("xml");			
		var it=tx.getElementsByTagName("Item");	
		for(var i=0;i<it.length;i++)
		{				
			var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;					
			var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
			listArr[i]=new ItemMenu1(_id,_name);	
		}
		document.getElementById("menutreeAd").innerHTML=ShowHTMLMenu(listArr);
		renderEventGroup();
		ClickItem(temIdmenuGroup);			
	}
}
function getMenuXML1()
{
	var url="UserMgn?CMD=8";
	url+="&r="+Math.random();			
	var f=new AjaxGetXML(url);				
	f.complet=function(tx)
	{	
		listArr=new Array();
		var xml=tx.getElementsByTagName("xml");	
		 menuIdGF= xml[0].getAttribute("menuIdGF");
		 kt =menuIdGF;
		var it=tx.getElementsByTagName("Item");	
		for(var i=0;i<it.length;i++)
		{				
			var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;					
			var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
			listArr[i]=new ItemMenu1(_id,_name);	
		}
		document.getElementById("menutreeAd").innerHTML=ShowHTMLMenu(listArr);
		renderEventGroup();
		ClickItem(menuIdGF);	
		loadUserInfor(menuIdGF);	
		if(listArr.length==0){
		$("AddMenu").style.display = 'block';
		}
		aa=listArr.length;
	}
}
function checkIsAdmin(username)
{
	userName =username;
	var url="UserMgn?CMD=21";
	url+="&r="+Math.random();			
	var f=new AjaxGetText(url);				
	f.complet=function(tx)
	{	
		isAdmin =tx;
	}
}
function getMenuXML2()
{
	var url="UserMgn?CMD=8";
	url+="&r="+Math.random();			
	var f=new AjaxGetXML(url);				
	f.complet=function(tx)
	{	
		k=new Array();
		var xml=tx.getElementsByTagName("xml");			
		var it=tx.getElementsByTagName("Item");	
		for(var i=0;i<it.length;i++)
		{				
			var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;					
			var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
			k[i]=new ItemMenu1(_id,_name);	
		}
	}
}
function ItemMenu1(id,name)
{
	this.Id=id;this.Name=name;
}
//hien thi menu cua group 21.1
function renderEventGroup(){
	var ul=$("menutreeAd").getElementsByTagName("ul")
	var li=ul[0].getElementsByTagName("li");
	var me=this;
	for(var i=0;i<li.length;i++)
	{			
		var a=li[i].getElementsByTagName("a");
		var item=a[0];			
		item.oncontextmenu=function(e)
		{
			temIdmenuGroup=this.id;		
			loadUserInfor(temIdmenuGroup);
			ctMenu.load(this.id, e);
			return false;
		};								
	}
}
function closeMenuTable(id){
	pageclick();
	pageclickMenu();
	shiftSelect(id);
}
this.closeMenu=function(){
	pageclick();
	pageclickMenu();
	if(null!=this.id){
	shiftSelect(this.id);
	}
}
function closeAllMenu(){
	pageclick();
	pageclickMenu();
}
this.pageclickMenu=function()
{		
	if(ctMenu.state)ctMenu.hide();	
}
function renderEvent()
{		
	if(count!=0){	
		var tr=$("table_id").getElementsByTagName("tr");
		for(var i=1;i<tr.length;i++)
		{
			var item=tr[i];			
			item.oncontextmenu=function(e)
			{					
				shiftSelect(this.id);
				if(this.id!=""){
					var obj=null;
					for(var i=0;i<arr.length;i++)
					{
						if(arr[i].Id==this.id)
						{
							Status =arr[i].Active;
							tempId =this.id;
							obj=arr[i];
							break;
						}
					}		
					ctTable.load(obj, e);
					//my.oncontextmenu(this.id,e);
				}else{
					closeMenu();
				}
				return false;
			};
		};
	}
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
			item.onclick=function(e){
				loadIdex(this.id);				
			};
		}
		var div1=$("div_page").getElementsByTagName("div");
		if(div1.length>0)
		{
			div1[0].onclick=function(){backindex();};
			div1[1].onclick=function(){nextindex();};	
		}
	}	
}
/*this.oncontextmenu=function(id,e)
{			
	var obj=null;
	for(var i=0;i<arr.length;i++)
	{
		if(arr[i].Id==id)
		{
			Status =arr[i].Active;
			tempId =id;
			obj=arr[i];
			break;
		}
	}		
	ctTable.load(obj, e);
}*/
/*this.oncontextmenu1=function(id,e)
{			
	var obj=null;
	for(var i=0;i<arr.length;i++)
	{
		if(arrGroup[i].Id==id)
		{
			obj=arrGroup[i];
			break;
		}
	}		
	ctMenu.load(obj, e);
}*/
function ItemUrlUser(stt,id,name,department,createDate,active,add,acc)
{
	this.Stt=stt; this.Id=id;this.Name=name;this.Department=department;
	this.CreateDate=createDate;this.Active=active;this.Address=add;this.Account=acc;
}
function ShowTotalUser(count){
	var html="";
	html+=totalUser + "("+total+":" + count + ")";
	return html; 
}
function ShowHTMLMenu(arr){
	var html="";
	html+="<div class='treeview'>";
	html+="<ul style='padding-left:5px;margin-left:5px;' >";
		for(var i=0;i<arr.length;i++){
			html+="<li pos='l' id='"+arr[i].Id+"'>";
			html+="<a pos='l' onclick='closeMenu()' href='javascript:loadUserInfor("+arr[i].Id+")' class='treeviewleaves_admin' id='" +arr[i].Id+"'>";
			html+=arr[i].Name;
			html+="</a>";
			html+="</li>";
		}
	html+="</ul>";
	html+="</div>";
	return html;	
}
function getHTMLUser(arr,count)
{
	var html="";
	html+="<div class='div_table'>";
	html+="<table  cellspacing=\"0\" width='99%' cellpadding=\"0\" border=\"0\" height='30' class='mytable'  id='table_id'>";
	html+="<tr class='mytable_header'>";		
	html+="<th align='left' style='padding-left:5px'  height='30' valign=\"middle\"  width=\""+10+"%\"  >";		
	html+=no;		
	html+="</th>";				
	html+="<th align='left'height='30' width=\""+30+"%\" valign=\"middle\"  >";
	html+=name;
	html+="</th>";		
	html+="<th align='left'height='30' width=\""+20+"%\" valign=\"middle\"  >";
	html+=department;
	html+="</th>";	
	html+="<th align='left' height='30' width=\""+20+"%\" valign=\"middle\"  >";
	html+=createDate;
	html+="</th>";		
	html+="<th align='center' height='30' style='padding-right:10px' width=\""+15+"%\" valign=\"middle\"  >";
	html+=active;
	html+="</th>";		
	html+="</tr bgcolor='#f2f2f2'>";	
	var begin=Index*my.page;
		for(i=0;i<arr.length;i++){
		if(i%2==0)
		{				
			html+="<tr onclick='closeMenuTable("+ arr[i].Id+")' class=\""+ bgRow2+"\" id=\""+arr[i].Id+"\"  >";
		}else
		{
			html+="<tr onclick='closeMenuTable("+ arr[i].Id+")' class=\""+ bgRow1+"\" id=\""+arr[i].Id+"\"   >";
		}
			html+="<td height='30' style='padding-left:5px' width='10%'>" + arr[i].Stt +"</td>";
			html+="<td height='30' width='30%' style='color: #9aa685;font-size:18'>"+ arr[i].Name +"</td>";
			html+="<td height='30' width='20%'>"+ arr[i].Department +"</td>";
			html+="<td height='30' width='25%'>"+ (arr[i].CreateDate).substring(0,11) +"</td>";
			if(arr[i].Active==1){
				html+="<td height='30' align='center' style='padding-right:10px' width='15%'><img src='../icon/16-square-green.png'></td>";
			}else{
				html+="<td height='30' align='center' style='padding-right:10px' width='15%'><img src='../icon/16-square-red.png'></td>";
			}
			html+="</tr>";
		}
		html+="</table>";
		html+="</div>";
		if(count ==0){
			if(aa!=0){
				html+="<div id='addusergroup1' align='center' style='display:block'>";
				if(isAdmin=='true'){
					html+="<a href='javascript:showfromAddUser()' class='div_addeImage' title='Click here add User'>"+ AddUser +"</a>";
				}
				html+="</div>";	
			}
		}
		
html+="<div id=\"div_page\" class='div_page'>";
	if(count>6)
	{
		var page=count/6;
		html+= createPagings(page,Index);
	}			
	html+="</div>";
	html+="<div class='div_bottom_table'>";
	html+="";
	html+="</div>";	
	return html;
}
function showfromAddUser()
{
	closeMenu();
	layer.show();						
	var html="";
	html+="<div class=\"form_add_Movie_sysAdmin\" id=\"form_detail_song\">";
	html+="<div class='div_Title_song' align=\"left\">"+ createNewUser + "";			
	html+="</div>";
	html+="<div style='float: left;width:440px;height:250px ;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10'>";
		html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";
		html+="<div style='width:40%;float:left'>" + account +"(<font color='red'>*</font>):</div>";
		html+="<div style='width:60%;float:left'><input type=\"text\" size='38' id='txtAccountAdd' class='textbox_input' style='width:100%;' /></div>";
		html+="</div>";	
		html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";	
		html+="<div style='width:40%;float:left'>"+ password +"(<font color='red'>*</font>):</div>";
		html+="<div style='width:60%;float:left'> <input type=\"password\" size='38' id='txtPassAdd' class='textbox_input' style='width:100%;'/></div>";
		html+="</div>";	
		html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";	
		html+="<div style='width:40%;float:left'>"+ cfPassword +"(<font color='red'>*</font>):</div>";
		html+=" <div style='width:60%;float:left'><input type=\"password\" size='38' id='txtcfPassAdd' class='textbox_input' style='width:100%;'/></div>";
		html+="</div>";	
		html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";	
		html+="<div style='width:40%;float:left'>"+ name +"(<font color='red'>*</font>):</div>";
		html+="<div style='width:60%;float:left'> <input type=\"text\" size='38' id='txtNameAdd' class='textbox_input' style='width:100%;'/></div>";
		html+="</div>";
		html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";	
		html+="<div style='width:40%;float:left'>"+ address +":</div>";
		html+="<div style='width:60%;float:left'> <input type=\"text\" size='38' id='txtAddressAdd' class='textbox_input' style='width:100%;' /></div>";
		html+="</div>";
		html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";	
		html+="<div style='width:40%;float:left'>"+ department +":</div>";
		html+="<div style='width:60%;float:left'><input type=\"text\" size='38' id='txtDepartmentAdd' class='textbox_input'style='width:100%;' /></div>";
		html+="</div>";
	html+="<div  style='float: left;width: 420' align='center'>";
	html+="<div   class='div_sub_buton_tt'>";
	html+="<input type=\"button\" value='"+ ok +"' id=\"form_textbox_ok\" class='div_buton' style='margin-left: 155px;margin-top:5px'>";
	html+="<input type=\"button\" value='" + cancel +"' id=\"form_textbox_cancel\" class='div_buton' style='margin-top:5px'>";
	html+="</div>";
	html+="</div>";
	html+="</div>";	
	html+="</div>";	
	layer.addHtml(html);
	setCenterDIV("form_detail_song");	
	$("form_textbox_ok").onclick=function(){				
	my.accept();};
	$("form_textbox_cancel").onclick=function(){
	kk();
	};	
	onChangeColorRow(tempId);
}
function showfromUpdatePassUser()
	{
	var Id="";	
	var account="";
	var _name="";
	var address =""; 
	var department="";
	for(var i=0;i<arr.length;i++){
		if(arr[i].Id==tempId){
		Id =arr[i].Id;
		_name =arr[i].Name;
		break;
		}
	}
	closeMenu();
	layer.show();						
	var html="";
		html+="<div class=\"form_updateMK_sysAdmin\" id=\"form_detail_song\">";
		html+="<div class='div_Title_song' align=\"left\">"+ changePass + "";			
		html+="</div>";
		html+="<div style='float: left;width:440px;height:150px ;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10'>";
		html+="<div class='left_Title_movie1'>";
			html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";
			html+="<div style='width:35%;float:left'>" + name +"(<font color='red'>*</font>):</div>";
			html+="<div style='width:60%;float:left'>"+_name+"</div>";
			html+="</div>";	
			html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";	
			html+="<div style='width:35%;float:left'>"+ password +"(<font color='red'>*</font>):</div>";
			html+="<div style='width:60%;float:left'><input type=\"password\" size='38' id='txtPassAdd' class='textbox_input' style='width:100%;' /></div>";
			html+="</div>";	
			html+="<div style='height:20;width:420px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";	
			html+="<div style='width:35%;float:left'>"+ cfPassword +"(<font color='red'>*</font>):</div>";
			html+=" <div style='width:60%;float:left'><input type=\"password\" size='38' id='txtcfPassAdd' class='textbox_input' style='width:100%;'/></div>";
			html+="</div>";	
		html+="<div  style='float: left;width: 100%;' align='center'>";
		html+="<div   class='div_sub_buton_tt'>";
		html+="<input type=\"button\" value='"+ ok +"' id=\"form_textbox_ok\" class='div_buton' style='margin-left: 155px'>";
		html+="<input type=\"button\" value='" + cancel +"' id=\"form_textbox_cancel\" class='div_buton'>";
		html+="</div>";
		html+="</div>";
		html+="</div>";	
		layer.addHtml(html);
		setCenterDIV("form_detail_song");	
		$("form_textbox_ok").onclick=function(){				
		my.acceptChangePass(Id);};
		$("form_textbox_cancel").onclick=function(){
		kk();
		};
		onChangeColorRow(tempId);	
}
this.acceptChangePass=function(id)
{
	var password=$("txtPassAdd").value.replace(/#/g,'%23');
	var cfPass=$("txtcfPassAdd").value.replace(/#/g,'%23');
	if(password.length==0)
	{
		var albox=new alertBox();
		albox.show(checkPass);			
		return;
	};
	if(cfPass.length==0)
	{
		var albox=new alertBox();
		albox.show(checkcfPass);			
		return;
	};
	if(password!=cfPass)
	{
		var albox=new alertBox();
		albox.show(checkMachPass);			
		return;
	};	
		var url="UserMgn?CMD=17";
		//url+="&name="+name
		//url+="&address="+address
		//url+="&department="+department
		url+="&tempId="+id;
		url+="&password="+password;
		//url+="&r="+Math.random();	
		var f=new AjaxGetText(url);		
		f.complet=function(tx)
		{
			if(tx=="failed"){
			var albox=new alertBox();
			albox.show(checkUpdate);
			}else{
				hide();
			}
		}
}
function showfromAddMenu()
{
	pageclick();
	layer.show();						
	var html="";
		html+="<div class=\"form_add_menu_sysAdmin\" id=\"form_detail_song\">";
		html+="<div class='div_Title_song' align=\"left\">"+ NewSubject + "";			
		html+="</div>";
		html+="<div style='float: left;width:375px;height:70px ;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10'>";
			html+="<div style='height:20;width:350px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";
			html+="<div style='width:20%;float:left'>" + name +"(<font color='red'>*</font>):</div>";
			html+="<div style='width:50%;float:left'><input type=\"text\" size='38' id='txtAddMenu' class='textbox_input'/></div>";
			html+="</div>";	
		html+="<div  style='float: left;width: 100%;' align='center'>";
		html+="<div style='margin-left: 40px'  class='div_sub_buton'>";
		html+="<input type=\"button\" value='"+ ok +"' id=\"form_textbox_ok\" class='div_buton'>";
		html+="<input type=\"button\" value='" + cancel +"' id=\"form_textbox_cancel\" class='div_buton'>";
		html+="</div>";
		html+="</div>";
		html+="</div>";	
		html+="</div>";	
		layer.addHtml(html);
		setCenterDIV("form_detail_song");
		$("form_textbox_ok").onclick=function(){				
		my.acceptMenu();};
		$("form_textbox_cancel").onclick=function(){
		kk();
		};	
}
function showfromUpdateMenu()
	{
	var name1 ="";
	var url="UserMgn?CMD=8";
		url+="&r="+Math.random();			
		var f=new AjaxGetXML(url);				
		f.complet=function(tx)
		{	
			listArr=new Array();
			var xml=tx.getElementsByTagName("xml");			
			var it=tx.getElementsByTagName("Item");	
			for(var i=0;i<it.length;i++)
			{		
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				if(_id==temIdmenuGroup){
					name1=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;	
						pageclick();
	layer.show();						
	var html="";
		html+="<div class=\"form_add_menu_sysAdmin\" id=\"form_detail_song\">";
		html+="<div class='div_Title_song' align=\"left\">"+ checkUpdateMenuEx + "";			
		html+="</div>";
		html+="<div style='float: left;width:375px;height:70px ;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10'>";
			html+="<div style='height:20;width:350px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";
			html+="<div style='width:20%;float:left'>" + name +"(<font color='red'>*</font>):</div>";
			html+="<div style='width:50%;float:left'><input type=\"text\" size='38' id='txtUpMenu' class='textbox_input' value='"+name1+"'/></div>";
			html+="</div>";	
		html+="<div  style='float: left;width: 100%;' align='center'>";
		html+="<div style='margin-left: 40px'  class='div_sub_buton'>";
		html+="<input type=\"button\" value='"+ ok +"' id=\"form_textbox_ok\" class='div_buton' >";
		html+="<input type=\"button\" value='" + cancel +"' id=\"form_textbox_cancel\" class='div_buton'>";
		html+="</div>";
		html+="</div>";
		html+="</div>";	
		html+="</div>";	
		layer.addHtml(html);
		setCenterDIV("form_detail_song");	
		$("form_textbox_ok").onclick=function(){				
		my.acceptEditMenu();};
		$("form_textbox_cancel").onclick=function(){
		kk();
		};	
				}
			}
		}
}
function ClickItem(id)
{
	var ul=$("menutreeAd").getElementsByTagName("ul");
	var li=ul[0].getElementsByTagName("li");	
	for(var i=0;i<li.length;i++)
	{
		var a=li[i].getElementsByTagName("a");
		if(a[0].id==id)
		{			
			a[0].style.color="#ab8718";				
		}else
		{				
			a[0].style.color="#878888";
		}			
	}		
}
function showfromDetailUser()
{
var _name="";
var _addr="";
var _department="";
var _account="";

for(var i=0;i<arr.length;i++){
	if(arr[i].Id==tempId){
	_name =arr[i].Name;
	_addr =arr[i].Address;
	if(arr[i].Address=="null"){
	_addr="";
	}
	_department =arr[i].Department;
	if(arr[i].Department=="null"){
		_department="";
	}
	_account =arr[i].Account;
	break;
	}
}
pageclick();
layer.show();						
var html="";
	html+="<div class=\"form_detail_sysAdmin\" id=\"form_detail_song\">";
	html+="<div class='div_Title_song_admin' align=\"left\">"+ userInfo + "";			
	html+="</div>";
	html+="<div style='float: left;border: 2px solid #dddddd; width:375px;height :200px ;margin-left: 10px;margin-top: 10 '>";
		html+="<div style='height:20;width:350px;margin-bottom: 15px;margin-left:10px' >";
		html+="<div  style='width:25%;float:left'>" + account +"</div>";
		html+=" <div style='width:75%;float:left'>" + _account + "</div>";
		html+="</div>";	
		html+="<div style='height:20;width:350px;margin-bottom: 15px;margin-left:10px'>";		
		html+="<div style='width:25%;float:left'>"+ name +"(<font color='red'>*</font>):</div>";
		html+="<div style='width:75%;float:left'> <input type=\"text\" size='38' id='txtNameUp' class='textbox_input' value='"+_name+"' style='width:100%;'/></div>";
		html+="</div>";
		html+="<div style='height:20;width:350px;margin-bottom: 15px;margin-left:10px'>";	
		html+="<div style='width:25%;float:left'>"+ address +":</div>";
		html+="<div style='width:75%;float:left'> <input type=\"text\" size='38' id='txtAddressUp' class='textbox_input' value='"+_addr+"' style='width:100%;'/></div>";
		html+="</div>";
		html+="<div style='height:20;width:350px;margin-bottom: 15px;margin-left:10px'>";		
		html+="<div style='width:25%;float:left'>"+ department +":</div>";
		html+=" <div style='width:75%;float:left'><input type=\"text\" size='38' id='txtDepartmentUp' class='textbox_input' value='"+_department+"' style='width:100%;'/></div>";
		html+="</div>";
		html+="<div  style='float: left;width: 100%;' align='center'>";
		html+="<div style='padding-right: 20px '  class='div_sub_buton_admin_pms' >";
		if(isAdmin=='true'){
			html+="<input type=\"button\" value='"+ ok +"' id=\"form_textbox_ok\" class='div_buton' style='margin-top: 10px' >";
		}
		html+="<input type=\"button\" value='" + cancel +"' id=\"form_textbox_cancel\" class='div_buton' style='margin-top: 10px'>";
		html+="</div>";
		html+="</div>";
	html+="</div>";
	html+="</div>";	
	layer.addHtml(html);
	setCenterDIV("form_detail_song");	
	if(isAdmin=='true'){
		$("form_textbox_ok").onclick=function(){				
		my.editUser(_account);};
	}
	$("form_textbox_cancel").onclick=function(){
	kk();
	};	
}
this.fucStatus=function()
{	closeMenu();
	var url="UserMgn?CMD=15";
		url+="&userId="+tempId;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);					
		f.complet=function(tx)
		{
			if(tx=="failed"){
			var albox=new alertBox();
			albox.show(errorStatus);
			}
		}
		loadUserInfor(tempIdMenu);		
}
this.showfromAddPmsGroup=function(){
	var _name="";
	for(var i=0;i<arr.length;i++){
		if(arr[i].Id==tempId){
		_name =arr[i].Name;
		break;
		}
	}
	pageclick();
	layer.show();			
	var html="";
	html+="<div class='form_change_subject_admin_changeG'  id=\"form_change_subject\">";
	html+="<div class='div_Title_song_admin' style='width:500px' >"+NameGrant;	
	html+="</div>";	
		html+="<div style='margin-top:10px;margin-left:10px' >"+ userNameGroup +" : " + _name;				
		html+="</div>";
			html+="<div style='float:left;width:100%;height: 200px;margin-top:20px' >";
				html+="<div class='left_change_subject_adpms' id='left_change_subject'  >";
				html+="</div>";	
				html+="<div style='float:left;width:30;margin-left:15px' align=\"center\" >";
					html+="<div class='add_subject' id='add_subject'></div>";
					html+="<div class='remove_subject' id='remove_subject' ></div>";
				html+="</div>";
				html+="<div class='right_change_subject' id='right_change_subject' style='padding-right:35px'>";
				html+="</div>";
			html+="</div>";
				html+="<div align='center' class='div_sub_buton' style='float:left;width:100%;'>";
			html+="<div style='width:160'>";
				html+="<input type=\"button\" 	' value='" + ok + "' id=\"form_textbox_ok\" class='div_buton'/ >";
				html+="<input type=\"button\" value='"+cancel+"' id=\"form_textbox_cancel\" class='div_buton'/>";
			html+="</div>";
			html+="</div>";
		html+="</div>";			
	html+="</div>";
	layer.addHtml(html);
	var x=0;
	var y=0;				
	x=document.body.clientWidth+document.body.scrollLeft+document.body.scrollLeft;
	y=document.body.clientHeight+document.body.scrollTop+ document.body.scrollTop;			
	$("form_change_subject").style.left=(x/2)-300+"px";
	$("form_change_subject").style.top=(y/2)-200+"px";	
	//render function
	$("form_textbox_ok").onclick=function(){
		my.acceptPmsUser();};
		$("form_textbox_cancel").onclick=function(){
	kk();
	};	
	$("add_subject").onclick=this.addsubject;
	$("remove_subject").onclick=this.removesubject;
	my.loadleft();
	my.loadright();
}
function ItemMenuPms(id,name)
{
	this.Id=id;this.Name=name;
}
this.loadleft=function()
{
	var url="UserMgn?CMD=10";
		url+="&userId="+tempId;
		url+="&r="+Math.random();	
	var f=new AjaxGetXML(url);
	f.complet=function(tx)
	{						
		var arrPmsL=new Array();	
		var it=tx.getElementsByTagName("Item");					
		for(var i=0;i<it.length;i++)
		{		
			var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
			var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;			
			arrPmsL[i]=new ItemMenuPms(_id,_name);				
		}			
		var html="";
			html+="<select size=\"10\" multiple=\"multiple\" id='list_select1' class='list_select_sub_adpms' ondblclick='addsubject()' >";
		for(var i=0;i<arrPmsL.length;i++)
		{
			html+="<option value='"+arrPmsL[i].Id+"'>"+arrPmsL[i].Name+"</option>";
		}				
		html+="</select>";
		$("left_change_subject").innerHTML=html;
		return arrPmsL;
	}
}
this.loadright=function()
{
	var url="UserMgn?CMD=11";
	url+="&userId="+tempId;
	url+="&r="+Math.random();	
	var f=new AjaxGetXML(url);
	f.complet=function(tx)
	{		
		var arrPmsR=new Array();	
		var it=tx.getElementsByTagName("Item");		
		for(var i=0;i<it.length;i++)
		{			
			var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
			var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;			
			arrPmsR[i]=new ItemMenuPms(_id,_name);			
		}			
		var html="";
			html+="<select size=\"10\" multiple=\"multiple\" id='list_select2' class='list_select_sub_adpms' ondblclick='removesubject()'>";
		for(var i=0;i<arrPmsR.length;i++)
		{
			html+="<option value='"+arrPmsR[i].Id+"'>"+arrPmsR[i].Name+"</option>";
		}				
		html+="</select>";
		$("right_change_subject").innerHTML=html;
		return arrPmsR;
	}
}
this.addsubject=function()
{
	var select1=$("list_select1");
	var select2=$("list_select2");
	if(select1.selectedIndex<0)
	{
		var albox=new alertBox();
		albox.show(selectItem);
		return;
	}
	for(var i=0;i<select1.options.length;i++)
	{
		if (select1.options[i].selected==true)
		{								
			select2.add(new Option(select1.options[i].text,select1.options[i].value));
			select1.remove(i);
		}
	}
}
this.removesubject=function()
{
	var select1=$("list_select1");
	var select2=$("list_select2");
	if(select2.selectedIndex<0)
	{
		var albox=new alertBox();
		albox.show(selectItem2);
		return;
	}
	for(var i=0;i<select2.options.length;i++)
	{
		if (select2.options[i].selected==true)
		{								
			select1.add(new Option(select2.options[i].text,select2.options[i].value));
			select2.remove(i);
		}
	}
}
this.acceptPmsUser=function()
{
	var select2=$("list_select2");
	var param="";
	for(var i=0;i<select2.options.length;i++)
	{
		if(i==select2.options.length-1){
		param+=select2.options[i].value;
		}else{
		param+=select2.options[i].value +",";
		}
	}
	if(select2.options.length==0){
	param+="";
	}
	var url="UserMgn?CMD=16";
	url+="&userId="+tempId;
	url+="&str=" + param;
	url+="&r="+Math.random();
	var f=new AjaxGetText(url);		
	f.complet=function(tx)
	{
		if(tx=="failed"){
		var albox=new alertBox();
			albox.show(checkAdd);
		}
		kk();		
	}
}
this.editUser=function(acc)
{
	var name=$("txtNameUp").value;
	var address=$("txtAddressUp").value;
	var department=$("txtDepartmentUp").value;
	if(name.length==0)
	{
		var albox=new alertBox();
		albox.show(checkName);			
		return;
	};
	var url="UserMgn?CMD=3";
	url+="&password="+password;
	url+="&tempId="+tempId;
	url+="&t="+Math.random();
	var param="&name="+name+"&address="+address + "&department="+department +"&account="+acc;
	var f=new AjaxPostText(url,param);			
	f.complet=function(tx)
	{
		if(tx=="failed"){
		var albox=new alertBox();
		albox.show(checkUpdate);
		}else{
			hide();
			loadUserInfor(tempIdMenu);
		}
	}
}
this.acceptEditMenu=function()
{
	var name=$("txtUpMenu").value;
	if(name.length==0)
	{
		var albox=new alertBox();
		albox.show(checkName);			
		return;
	};
	var url="UserMgn?CMD=9";
	url+="&tempId="+temIdmenuGroup;
	url+="&t="+Math.random();
	var param="&name="+name;
	var f=new AjaxPostText(url,param);			
	f.complet=function(tx)
	{
		if(tx=="failed"){
		var albox=new alertBox();
		albox.show(checkUpdate);
		}else{
			hide();
			closeMenu();
			getMenuXML();
		}
	}
}
function deleteUser(groupId){
closeMenu();
var cfBox=new conformBox();
	cfBox.show(checkDeleteFrom,ok,cancel);
		cfBox.accept=function(){
	 		 var url="UserMgn?CMD=4";
	 		 url+="&groupId="+groupId;
			url+="&id="+tempId;
			url+="&t="+Math.random();
			var f=new AjaxGetText(url);			
			f.complet=function(tx)
			{		
				if(tx=="faileds"){
				var albox=new alertBox();
				albox.show(checkDelete);
				}else{
					hide();
					loadUserInfor(tempIdMenu);
				}
			}
		}
		onChangeColorRow(tempId);
}
function deleteGroup(groupId){
closeMenu();
var cfBox=new conformBox();
	cfBox.show(checkDeleteFrom,ok,cancel);
		cfBox.accept=function(){
	 		 var url="UserMgn?CMD=7";
	 		 url+="&groupId="+groupId;
			url+="&id="+tempId;
			url+="&t="+Math.random();
			var f=new AjaxGetText(url);			
			f.complet=function(tx)
			{		
				if(tx=="faileds"){
				var albox=new alertBox();
				albox.show(checkDelete);
				}else{
					hide();
					getMenuXML1();
					closeMenu();
				}
			}
		}
}
this.accept=function()
	{	
		var account=$("txtAccountAdd").value;
		var password=$("txtPassAdd").value.replace(/#/g,'%23');
		var cfPass=$("txtcfPassAdd").value.replace(/#/g,'%23');
		var name=$("txtNameAdd").value;
		var address=$("txtAddressAdd").value;
		var department=$("txtDepartmentAdd").value;
		if(account.length==0)
		{
			var albox=new alertBox();
			albox.show(checkAccount);			
			return;
		};
		if(account.length >10){
			var albox=new alertBox();
			albox.show(AccountLength);			
			return;
		}
		if(password.length==0)
		{
			var albox=new alertBox();
			albox.show(checkPass);			
			return;
		};
		if(cfPass.length==0)
		{
			var albox=new alertBox();
			albox.show(checkcfPass);			
			return;
		};
		if(password!=cfPass)
		{
			var albox=new alertBox();
			albox.show(checkMachPass);			
			return;
		};	
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show(checkName);			
			return;
		};
			var url="UserMgn?CMD=1";
			url+="&account="+account;
			url+="&password="+password;
			url+="&tempIdMenu="+tempIdMenu;
			//url+="&t="+Math.random();
			var param="&name="+name+"&address="+address + "&department="+department;
			var f=new AjaxPostText(url,param);				
			f.complet=function(tx)
			{
				if(tx=="failed"){
				var albox=new alertBox();
				albox.show(checkAdd);
				}else if(tx=="f"){
				var albox=new alertBox();
				albox.show(checkAddEx);
				}
				else{
					hide();
					loadUserInfor(tempIdMenu);	
				}
			}
	}
function kk(){
	my.hide();
}
this.acceptMenu=function()
	{	
		var name=$("txtAddMenu").value;
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show(checkName);			
			return;
		};
		var url="UserMgn?CMD=5";
		url+="&t="+Math.random();
		var param="&name="+name;
		var f=new AjaxPostText(url,param);				
		f.complet=function(tx)
		{
			if(tx=="failed"){
				var albox=new alertBox();
			albox.show(checkAdd);
			}else if(tx=="f"){
				var albox=new alertBox();
			albox.show(checkAddMenuEx);
			}
			else{
				hide();
				closeMenu();
				getMenuXML1();
				$("AddMenu").style.display = 'none';
			}
		}
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
			{
				tr[i].className="mytable_row_sel2";
			}
		}
	}			
}
function onChangeColorRow(id){
	var table = document.getElementById("table_id");
	var tr = table.getElementsByTagName("tr");		
	for(var i=0;i<tr.length;i++)
	{
		if(id!=0){
			if(tr[i].id==id)
			{
				tr[i].className="mytable_row_sel2";
			}
		}
	}
}
function contextTableEX()
{
	var my=this;
	this.state=false;
	this.obj=null;
	this.load=function(_obj,e)
	{
		this.obj=_obj;
		var posx = 0;
		var posy = 0;
		if (!e) var e = window.event;
		if (e.pageX || e.pageY) 
		{
			posx = e.pageX;
			posy = e.pageY;
		}else if (e.clientX || e.clientY) 	{
			posx = e.clientX + document.body.scrollLeft
				+ document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
				+ document.documentElement.scrollTop;
		}
		if(isAdmin=='true'){
		if(posy >380 ){
		posy=posy-130;
		}
		my.draw(posx,posy-10);
		}
		else{
			var _name="";
			var u ="\n"+userName;
			for(var i=0;i<arr.length;i++){
				if(arr[i].Id==tempId){
					_name=arr[i].Account;
				break;
				}
			}
			if(_name==u){
				my.draw1(posx,posy-10);
			}else{
				my.draw2(posx,posy-10);
			}
		}
	}
	this.draw=function(x,y)
	{		
		my.state=true;
		var html="";
		html+="<ul>";
		html+="<li class='icon_contextmenuAdd'>";
		html+="<a>"+AddUser+"</a>";
		html+="</li>";
		html+="<li class='icon_contextmenuAdd'>";
		html+="<a>"+grantPms+"</a>";
		html+="</li>";
		html+="<li class='icon_contextmenuplay_admin'>";
		html+="<a>"+detailuser+"</a>";
		html+="</li>";
		html+="<li class='icon_contextmenuDel'>";
		html+="<a>"+deleteuserg+"</a>";
		html+="</li>";			
		html+="<li class='icon_contextmenuDel'>";
		html+="<a>"+deleteusers+"</a>";
		html+="</li>";
		html+="<li class='icon_contextmenuEdit'>";
		html+="<a>"+changePass+"</a>";
		html+="</li>";	
		/*if(Status==1)
		{
			html+="<li class='icon_visibility2'>";
		}else
		{
			html+="<li class='icon_visibility1'>";
		}	
		html+="<a>"+changeStatus+"</a>";
		html+="</li>";*/
		html+="<li class='icon_contextmenuEdit'>";
		html+="<a>"+changegroupPms+"</a>";
		html+="</li>";
		html+="</ul>";		
		if($("div_contextMenu")==undefined)
		{			
			var div_=document.createElement("div");			
			div_.id="div_contextMenu";			
			div_.className="div_contextMenu";	
			div_.innerHTML=html;
			$("main").appendChild(div_);			
		}else
		{
			$("div_contextMenu").className="div_contextMenu";
			$("div_contextMenu").innerHTML=html;
		}
		$("div_contextMenu").style.left=x+ "px";
		$("div_contextMenu").style.top= y+ "px";
		$("div_contextMenu").style.display= "block";
		my.renderClick();
		eventMouse();
	}
	this.renderClick=function()
	{	
		var ul=$("div_contextMenu").getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		var fucAdd=li[0].getElementsByTagName("a")[0];
		fucAdd.onclick=function(){showfromAddUser();};
		var addpmsingroup=li[1].getElementsByTagName("a")[0];
		addpmsingroup.onclick=function(){showfromAddPmsGroup();};
		var fucDetail=li[2].getElementsByTagName("a")[0];
		fucDetail.onclick=function(){showfromDetailUser();};
		var fucdel=li[3].getElementsByTagName("a")[0];
		fucdel.onclick=function(){deleteUser(tempIdMenu);};
		var itemstatus=li[4].getElementsByTagName("a")[0];
		itemstatus.onclick=function(){deleteUser(-1);};
		var changepass=li[5].getElementsByTagName("a")[0];
		changepass.onclick=function(){showfromUpdatePassUser();};
		//var changeStatus=li[6].getElementsByTagName("a")[0];
		//changeStatus.onclick=function(){fucStatus();};
		var changegroup=li[6].getElementsByTagName("a")[0];
		changegroup.onclick=function(){Showcgroup();};
	}
	this.hide=function()
	{
		this.state=false;
		$("div_contextMenu").style.display= "none";
	}
	this.draw1=function(x,y)
	{		
		my.state=true;
		var html="";
		html+="<ul>";
			html+="<li class='icon_contextmenuplay_admin'>";
			html+="<a>"+detailuser+"</a>";
			html+="</li>";
			html+="<li class='icon_contextmenuEdit'>";
			html+="<a>"+changePass+"</a>";
			html+="</li>";	
			html+="</ul>";		
		if($("div_contextMenu")==undefined)
		{			
			var div_=document.createElement("div");			
			div_.id="div_contextMenu";			
			div_.className="div_contextMenu";	
			div_.innerHTML=html;
			$("main").appendChild(div_);			
		}else
		{
			$("div_contextMenu").className="div_contextMenu";
			$("div_contextMenu").innerHTML=html;
		}
		$("div_contextMenu").style.left=x+ "px";
		$("div_contextMenu").style.top= y+ "px";
		$("div_contextMenu").style.display= "block";
		my.renderClick3();
		eventMouse();
	}
	this.renderClick3=function()
	{	
		var ul=$("div_contextMenu").getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		var fucDetail=li[0].getElementsByTagName("a")[0];
		fucDetail.onclick=function(){showfromDetailUser();};
		var changepass=li[1].getElementsByTagName("a")[0];
		changepass.onclick=function(){showfromUpdatePassUser();};
	}
	this.draw2=function(x,y)
	{		
		my.state=true;
		var html="";
		html+="<ul>";
			html+="<li class='icon_contextmenuplay_admin'>";
			html+="<a>"+detailuser+"</a>";
			html+="</li>";
			html+="</ul>";		
		if($("div_contextMenu")==undefined)
		{			
			var div_=document.createElement("div");			
			div_.id="div_contextMenu";			
			div_.className="div_contextMenu";	
			div_.innerHTML=html;
			$("main").appendChild(div_);			
		}else
		{
			$("div_contextMenu").className="div_contextMenu";
			$("div_contextMenu").innerHTML=html;
		}
		$("div_contextMenu").style.left=x+ "px";
		$("div_contextMenu").style.top= y+ "px";
	
		$("div_contextMenu").style.display= "block";
		my.renderClick4();
		eventMouse();
	}
	this.renderClick4=function()
	{	
		var ul=$("div_contextMenu").getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		var fucDetail=li[0].getElementsByTagName("a")[0];
		fucDetail.onclick=function(){showfromDetailUser();};
	}
}
function Showcgroup()
	{
	var nameCG="";	
	for(var i=0;i<arr.length;i++){
		if(arr[i].Id==tempId){
			nameCG=arr[i].Name;
			break;
		}
	}
			closeMenu();
			layer.show();			
			var html="";
			html+="<div class=\"form_change_subject_admin_changeGG\" id=\"form_change_subject\">";
			html+="<div class='div_Title_song' align=\"center\">"+changegroup;	
			html+="</div>";	
				html+="<div style='margin-top:10px;margin-left:10px' >"+ userNameGroup +" : " + nameCG;				
			html+="</div>";	
			html+="<div class='change_subject_top' >";				
			html+="</div>";
			html+="<div class='change_subject_center'  >";				
				html+="<div style='float:left;width:100%;height: 195px;margin-top:10px'>";
					html+="<div class='left_change_subject' id='left_change_subject'>";
					html+="</div>";	
					html+="<div style='float:left;width:20;' align=\"center\">";
						html+="<div class='add_subject' id='add_subject'></div>";
						html+="<div class='remove_subject' id='remove_subject'></div>";
					html+="</div>";
					html+="<div class='right_change_subject' id='right_change_subject'>";
					html+="</div>";
				html+="</div>";
					html+="<div align='center' class='div_sub_buton' style='float:left;width:100%'>";
					html+="<div style='width:160' >";
						html+="<input type=\"button\" 	' value='" + ok + "' id=\"form_textbox_ok\" class='div_buton'/ >";
						html+="<input type=\"button\" value='"+cancel+"' id=\"form_textbox_cancel\" class='div_buton'/>";
					html+="</div>";
					html+="</div>";
			html+="</div>";
			html+="<div class='change_subject_bottom'>";				
			html+="</div>";			
			html+="</div>";
			layer.addHtml(html);
			var x=0;
			var y=0;				
			x=document.body.clientWidth+document.body.scrollLeft+document.body.scrollLeft;
			y=document.body.clientHeight+document.body.scrollTop+ document.body.scrollTop;			
			$("form_change_subject").style.left=(x/2)-300+"px";
			$("form_change_subject").style.top=(y/2)-200+"px";	
			$("form_textbox_ok").onclick=function(){
				acceptChangeGroup();};
				$("form_textbox_cancel").onclick=function(){
			kk();
			};	
			$("add_subject").onclick=this.addsubject;
			$("remove_subject").onclick=this.removesubject;
			my.loadleftChangeGroup();
			my.loadrightChangeGroup();
			onChangeColorRow(tempId);
	}
	this.acceptChangeGroup=function(){
		var select2=$("list_select2");
		if(select2.options.length<=0)
		{
			var albox=new alertBox();
			albox.show(selectItem2);
			return;
		}
				var param="";
		for(var i=0;i<select2.options.length;i++)
		{
			if(i==select2.options.length-1){
			param+=select2.options[i].value;
			}else{
			param+=select2.options[i].value +",";
			}
		}
		var url="UserMgn?CMD=20";
		url+="&userId="+tempId;
		url+="&str="+param;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{
			if(tx=="failed"){
				var albox=new alertBox();
				albox.show(checkUpdate);
			}
			kk();
			loadUserInfor(tempIdMenu);	
		}
	}
	this.loadleftChangeGroup=function()
	{
		var url="UserMgn?CMD=18";
			url+="&userId="+tempId;
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);
			f.complet=function(tx)
			{					
				var arrPmsCGL=new Array();	
				var it=tx.getElementsByTagName("Item");	
				for(var i=0;i<it.length;i++)
				{		
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;	
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;	
					arrPmsCGL[i]=new ItemMenuPGroup(_id,_name);				
				}		
				var html=""
					html+="<select size=\"10\" multiple=\"multiple\" id='list_select1' class='list_select_sub' ondblclick='addsubject()'>";
				for(var i=0;i<arrPmsCGL.length;i++)
				{
					html+="<option value='"+arrPmsCGL[i].Id+"'>"+arrPmsCGL[i].Name+"</option>";
				}				
				html+="</select>";
				$("left_change_subject").innerHTML=html			
				return arrPmsCGL;
			}
	}
	function ItemMenuPGroup(id,name)
	{
	this.Id=id;this.Name=name;
	}
	this.loadrightChangeGroup=function()
	{
			var url="UserMgn?CMD=19"
			url+="&userId="+tempId
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);
		f.complet=function(tx)
		{		
			var arrPmsCGR=new Array();	
			var it=tx.getElementsByTagName("Item");		
			for(var i=0;i<it.length;i++)
			{			
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;			
				arrPmsCGR[i]=new ItemMenuPGroup(_id,_name);			
			}			
			var html=""
				html+="<select size=\"10\" multiple=\"multiple\" id='list_select2' class='list_select_sub' ondblclick='removesubject()'>";
			for(var i=0;i<arrPmsCGR.length;i++)
			{
				html+="<option value='"+arrPmsCGR[i].Id+"'>"+arrPmsCGR[i].Name+"</option>";
			}				
			html+="</select>";
			
			$("right_change_subject").innerHTML=html;
			return arrPmsCGR;
		}
	}	
function contextMenu1()
{
	var my=this;
	this.state=false;
	this.obj=null;
	this.load=function(obj,e)
	{
		my.obj=obj;
		var posx = 0;
		var posy = 0;
		if (!e) var e = window.event;
		if (e.pageX || e.pageY) 
		{
			posx = e.pageX;
			posy = e.pageY;
		}else if (e.clientX || e.clientY) 	{
			posx = e.clientX + document.body.scrollLeft
				+ document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop
				+ document.documentElement.scrollTop;
		}	
		if(isAdmin=='true'){
		my.draw(posx,posy-10);
		}
	}
	this.draw=function(x,y)
	{		
		my.state=true;
		var html="";
		html+="<ul>";
			html+="<li class='icon_contextmenuAdd'>";
			html+="<a>"+ creategroup +"</a>"
			html+="</li>";
			html+="<li class='icon_contextmenuDel'>";
			html+="<a>"+deletegroup+"</a>";
			html+="</li>";			
			html+="<li class='icon_contextmenuEdit'>";
			html+="<a>"+updategroup+"</a>";
			html+="</li>";
			//html+="<li class='icon_contextmenuAdd'>";
			//html+="<a>"+pmsgroup+"</a>";
			//html+="</li>";
		html+="</ul>";
		if($("div_contextMenu")==undefined)
		{			
			var div_=document.createElement("div");			
			div_.id="div_contextMenu";			
			div_.className="div_contextMenu";	
			div_.innerHTML=html;
			$("main").appendChild(div_);			
		}else
		{
			$("div_contextMenu").className="div_contextMenu";
			$("div_contextMenu").innerHTML=html;
		}
		$("div_contextMenu").style.left=x+"px";
		$("div_contextMenu").style.top= y+"px";
		$("div_contextMenu").style.display= "block";
		my.renderClick();
		eventMouse();
	}
	this.hide=function()
	{
		this.state=false;
		$("div_contextMenu").style.display= "none";
	}
	this.renderClick=function()
	{
		var ul=$("div_contextMenu").getElementsByTagName("ul");
		var li=ul[0].getElementsByTagName("li");
		var itemnew=li[0].getElementsByTagName("a")[0];
		itemnew.onclick=function(){my.fucNew();};
		var itemdel=li[1].getElementsByTagName("a")[0];
		itemdel.onclick=function(){my.fucdel();};
		var itemedit=li[2].getElementsByTagName("a")[0];
		itemedit.onclick=function(){my.fucedit();};
		//var addpms=li[3].getElementsByTagName("a")[0];
		//addpms.onclick=function(){my.addpms();};
	}
	this.fucNew=function()
	{
		closeMenu();
		showfromAddMenu();
	}
	this.fucdel=function()
	{
	deleteGroup(temIdmenuGroup);	
	closeMenu();
	}
	this.fucedit=function()
	{	
	showfromUpdateMenu();	
	closeMenu();
	}
	this.addpms=function()
	{	
		closeMenu();
		layer.show();			
		var html="";
		html+="<div class=\"form_change_subject\" id=\"form_change_subject\">";
		html+="<div class='div_Title_song' align=\"center\">"+NamePms;			
		html+="</div>";			
		html+="<div class='change_subject_top'>";				
		html+="</div>";
		//html+="<div style='float:left;width:100%;height: auto;'>";			
		html+="<div class='change_subject_center'  >";				
			html+="<div style='float:left;width:100%;height: 200px;margin-top:10px;'>"
				html+="<div class='left_change_subject' id='left_change_subject'>";
				html+="</div>";	
				html+="<div style='float:left;width:20;' align=\"center\">"
					html+="<div class='add_subject' id='add_subject'></div>"
					html+="<div class='remove_subject' id='remove_subject'></div>"
				html+="</div>";
				html+="<div class='right_change_subject' id='right_change_subject'>";
				html+="</div>";
			html+="</div>"					
				html+="<div align='center' class='div_sub_buton' style='float:left;width:100%;'>";
				html+="<div style='width:160'>"
					html+="<input type=\"button\" 	' value='" + ok + "' id=\"form_textbox_ok\" class='div_buton'/ >"
					html+="<input type=\"button\" value='"+cancel+"' id=\"form_textbox_cancel\" class='div_buton'/>"
				html+="</div>"
				html+="</div>"
		html+="</div>"			
		html+="<div class='change_subject_bottom'>";				
		html+="</div>";			
		html+="</div>";
		layer.addHtml(html);
		var x=0;
		var y=0;				
		x=document.body.clientWidth+document.body.scrollLeft+document.body.scrollLeft;
		y=document.body.clientHeight+document.body.scrollTop+ document.body.scrollTop;			
		$("form_change_subject").style.left=(x/2)-300+"px";
		$("form_change_subject").style.top=(y/2)-200+"px";	
		$("form_textbox_ok").onclick=function(){
			my.acceptAddGroup();};
			$("form_textbox_cancel").onclick=function(){
		kk();
		};	
		$("add_subject").onclick=this.addsubject;
		$("remove_subject").onclick=this.removesubject;
		my.loadleftGroup();
		my.loadrightGroup();
	}
	function ItemMenuPmsGroup(id,name)
	{
	this.Id=id;this.Name=name;
	}
	this.loadleftGroup=function()
	{
		var url="UserMgn?CMD=12";
			url+="&groupId="+tempIdMenu
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);
			f.complet=function(tx)
			{						
				var arrPmsGL=new Array();	
				var it=tx.getElementsByTagName("Item");					
				for(var i=0;i<it.length;i++)
				{		
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;			
					arrPmsGL[i]=new ItemMenuPmsGroup(_id,_name);				
				}			
				var html=""
					html+="<select size=\"10\" multiple=\"multiple\" id='list_select1' class='list_select_sub' ondblclick='addsubject()'>";
				
				for(var i=0;i<arrPmsGL.length;i++)
				{
					html+="<option value='"+arrPmsGL[i].Id+"'>"+arrPmsGL[i].Name+"</option>";
				}				
				html+="</select>";
				$("left_change_subject").innerHTML=html			
				return arrPmsGL;
			}
	}
	this.loadrightGroup=function()
	{
			var url="UserMgn?CMD=13"
			url+="&groupId="+tempIdMenu
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);
		f.complet=function(tx)
		{		
			var arrPmsGR=new Array();	
			var it=tx.getElementsByTagName("Item");		
			for(var i=0;i<it.length;i++)
			{			
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;			
				arrPmsGR[i]=new ItemMenuPmsGroup(_id,_name);			
			}			
			var html=""
				html+="<select size=\"10\" multiple=\"multiple\" id='list_select2' class='list_select_sub' ondblclick='removesubject()'>";
			for(var i=0;i<arrPmsGR.length;i++)
			{
				html+="<option value='"+arrPmsGR[i].Id+"'>"+arrPmsGR[i].Name+"</option>";
			}				
			html+="</select>";
			$("right_change_subject").innerHTML=html;
			return arrPmsGR;
		}
	}
	this.addsubject=function()
	{
		var select1=$("list_select1");
		var select2=$("list_select2");
		if(select1.selectedIndex<0)
		{
			var albox=new alertBox();
			albox.show(selectItem);
			return;
		}
		for(var i=0;i<select1.options.length;i++)
		{
			if (select1.options[i].selected==true)
			{								
				select2.add(new Option(select1.options[i].text,select1.options[i].value));
				select1.remove(i);
			}
		}
	}
	this.removesubject=function()
	{
		var select1=$("list_select1");
		var select2=$("list_select2");
		if(select2.selectedIndex<0)
		{
			var albox=new alertBox();
			albox.show(selectItem2);
			return;
		}
		for(var i=0;i<select2.options.length;i++)
		{
			if (select2.options[i].selected==true)
			{								
				select1.add(new Option(select2.options[i].text,select2.options[i].value));
				select2.remove(i);
			}
		}
	}
	this.acceptAddGroup=function()
	{
		var select2=$("list_select2");
		var param="";
		for(var i=0;i<select2.options.length;i++)
		{
			if(i==select2.options.length-1){
			param+=select2.options[i].value;
			}else{
			param+=select2.options[i].value +",";
			}
		}
		if(select2.options.length==0){
			param+="";
		}
		var url="UserMgn?CMD=14";
		url+="&groupId="+tempIdMenu;
		url+="&str="+param;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{
			if(tx=="failed"){
			var albox=new alertBox();
				albox.show(checkAdd);
			}
			kk();	
		}	
	}
}
	var tempCategoryIdF=0;
	var tempCategoryId =0;
	var layer=new layer_vitual();
	var my=this;
	var menu=new Tree();
	this.List=new Array();	
	var ctMenu=new contextMenu();
	this.ID;
	var isAdmin="";
	document.onclick=function()
	{	
		pageclick();	
	}
	ctMenu.fucNew=function(idP)
	{
		pageclick();
		layer.show();						
		var html="";
		
			html+="<div class=\"form_add_menu_sysAdmin\" id=\"form_detail_song\">";
			html+="<div class='div_Title_song' align=\"left\">"+addgroup;			
			html+="</div>";
			html+="<div style='float: left;width:375px;height:100px ;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10'>"
			html+="<div class='left_Title_movie1'>";
				html+="<div style='height:20;width:350px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";
				html+="<div style='width:20%;float:left'>" + name +"(<font color='red'>*</font>):</div>"
				html+="<div style='width:50%;float:left'><input type=\"text\" size='38' id='txtAddMenuRole' class='textbox_input'/></div>"
				html+="</div>";	
			html+="<div  style='float: left;width: 100%;' align='center'>";
			html+="<div style='margin-left: 40px'  class='div_sub_buton'>";
			html+="<input type=\"button\" value='"+ ok +"' id=\"form_textbox_okss\" class='div_buton' >"
			html+="<input type=\"button\" value='" + cancel +"' id=\"form_textbox_cancel\" class='div_buton'>"
			html+="</div>"
			html+="</div>";
			html+="</div>";	
			layer.addHtml(html);
			setCenterDIV("form_detail_song");	
			$("form_textbox_okss").onclick=function(){				
			acceptMenuRole(idP);};
			$("form_textbox_cancel").onclick=function(){
			hide();
			};	
	}
	function checkIsAdmin(username)
	{
			userName =username;
			var url="RoleMgn?CMD=6"
			url+="&r="+Math.random();			
			var f=new AjaxGetText(url);				
			f.complet=function(tx)
			{	
				isAdmin =tx;
			}	
									
	}
	this.acceptMenuRole=function(idP)
	{
		var name=$("txtAddMenuRole").value;
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show(checkName);			
			return;
		};
		if(idP==-1){
		addPerent(idP,name);
		}else{
			var url="RoleMgn?CMD=1"
				url+="&r="+Math.random();	
				var f=new AjaxGetXML(url);			
				f.complet=function(tx)
				{		
						
					var it=tx.getElementsByTagName("Item");				
					for(var i=0;i<it.length;i++)
					{	
						var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					if(tempCategoryId ==_id){
						var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
						var url="RoleMgn?CMD=2"
				 url+="&ParentId="+_id
				url+="&t="+Math.random();
				var param="&CateName="+name
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
					reload();
						
						
					}
				}	
					break;
					}	
				}							
			}
		}		
	}
	function addPerent(idP,name){
		var url="RoleMgn?CMD=2"
			 url+="&ParentId="+idP
			url+="&t="+Math.random();
			var param="&CateName="+name
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
				reload();
					
					
				}
			}	
	}
	ctMenu.fucdel=function()
	{
	
	pageclick();		
	var cfBox=new conformBox();
		cfBox.show(checkDeleteFrom,ok,cancel);
		cfBox.accept=function(){
	 		 var url="RoleMgn?CMD=2"
	 		 url+="&cateId="+tempCategoryId
			url+="&t="+Math.random();
			var f=new AjaxGetText(url);			
			f.complet=function(tx)
			{		
				if(tx=="faileds"){
				var albox=new alertBox();
				albox.show(checkDelete);
				}else{
					hide();
					my.get();
				}
				
			}
		}
		
	}	
	ctMenu.fucedit=function()
	{
		pageclick();
		var arrG=new Array();
		var url="RoleMgn?CMD=1"
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{		
					
				var it=tx.getElementsByTagName("Item");				
				for(var i=0;i<it.length;i++)
				{	
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				if(tempCategoryId ==_id){
				
					var CteId =_id;
					var NameCate=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
					var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
				layer.show();						
				var html="";
				html+="<div class=\"form_add_menu_sysAdmin\" id=\"form_detail_song\">";
				html+="<div class='div_Title_song' align=\"left\">"+updategroup;			
				html+="</div>";
				html+="<div style='float: left;width:375px;height:100px ;border: 2px solid #dddddd;margin-left: 10px;margin-top: 10'>"
				html+="<div class='left_Title_movie1'>";
					html+="<div style='height:20;width:350px;margin-bottom: 15px;margin-top: 5px;margin-left: 10px'>";
					html+="<div style='width:20%;float:left'>Name(<font color='red'>*</font>):</div>"
					html+="<div style='width:50%;float:left'><input type=\"text\" size='38' id='txtUpCategory' class='textbox_input' value='"+NameCate+"'  /></div>"
					html+="</div>";	
				html+="<div  style='float: left;width: 100%;' align='center'>";
				html+="<div style='margin-left: 40px'  class='div_sub_buton'>";
				html+="<input type=\"button\" value='"+ ok +"' id=\"form_textbox_oks\" class='div_buton' >"
				html+="<input type=\"button\" value='" + cancel +"' id=\"form_textbox_cancels\" class='div_buton'>"
				html+="</div>"
				html+="</div>";
				html+="</div>";	
				layer.addHtml(html);
				setCenterDIV("form_detail_song");	
				$("form_textbox_oks").onclick=function(){				
				acceptEditCategory(CteId,_parent,NameCate);};
				$("form_textbox_cancels").onclick=function(){
				hide();
				};	
				break;
				}								
		}	
	}
			
	}
	function acceptEditCategory(CateId,ParentId,CateName){
		var name=$("txtUpCategory").value;
		if(name.length==0)
		{
			var albox=new alertBox();
			albox.show(checkName);			
			return;
		};
			var url="RoleMgn?CMD=1"
			url+="&CateId="+CateId
			url+="&ParentId="+ParentId
			url+="&t="+Math.random();
			var param="&CateName="+name
			var f=new AjaxPostText(url,param);			
			f.complet=function(tx)
			{
				if(tx=="failed"){
				var albox=new alertBox();
				albox.show(checkUpdate);
				}else{
					hide();
					reload();
					
				}
			}	
	}
	function hide()
	{
		$("div_layer_vitual").style.display="none";
	}
	this.pageclick=function()
	{		
		if(ctMenu.state)ctMenu.hide();	
	}
	this.defaults =
	{
		divName:		"menutree",
		classTree:		"treeview",
		title:			"Subject",
		classItem:		"itemnomal",
		classfocus:		"itemfocus",
		classRootOpen:	"treeviewrootopen",
		classRootClose:	"treeviewrootcolse",
		clcassleaves:	"treeviewleaves_admin_tt",
		classopen:		"treeopen",
		classclose:		"treecolse"
	}
	menu.init(my.defaults);
	this.reload=function()
	{
		var url="RoleMgn?CMD=1"
			url+="&r="+Math.random();	
			var f=new AjaxGetXML(url);			
			f.complet=function(tx)
			{		
				var arr=new Array();	
				var it=tx.getElementsByTagName("Item");				
				for(var i=0;i<it.length;i++)
				{			
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
					var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
					var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;				
					arr[i]=new ItemMenu(_id,_parent,_name);					
				}		
				my.List=arr;
				menu.reload(arr);	
				my.shiftfocus();
				
			}		
	}
	this.get=function()
	{				
		var menuF=0;
		var url="RoleMgn?CMD=1"
		url+="&r="+Math.random();	
		var f=new AjaxGetXML(url);			
		f.complet=function(tx)
		{		
			var arr=new Array();	
			var xml=tx.getElementsByTagName("xml");		
			var it=tx.getElementsByTagName("Item");		
			menuF= xml[0].getAttribute("menuIdCT");	
			for(var i=0;i<it.length;i++)
			{					
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;	
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;				
				arr[i]=new ItemMenu(_id,_parent,_name);
			}		
			my.List=arr;
			menu.load(arr);		
			my.ClickItem(menuF);
		}		
	}
	function ItemMenu(id,parent,name)
	{
	this.Id=id;this.Parent=parent;this.Name=name;
	}
	this.ClickItem=function(id)
	{
		LoadFrm(id);	
		this.ID=id;
		var ul=$(menu.divName).getElementsByTagName("ul")
		var li=ul[0].getElementsByTagName("li");	
		for(var i=0;i<li.length;i++)
		{
			var a=li[i].getElementsByTagName("a");
			
			if(a[0].id==id)
			{	tempCategoryId =id;		
				a[0].style.color="#ab8718";				
			}else
			{				
				a[0].style.color="#878888";
			}			
		}		
		//my.SelectItem(id);
	}
	this.shiftfocus=function()
	{
		var ul=$(menu.divName).getElementsByTagName("ul")
		var li=ul[0].getElementsByTagName("li");	
		for(var i=0;i<li.length;i++)
		{
			var a=li[i].getElementsByTagName("a");
		
			if(a[0].id==my.ID)
			{			
				a[0].style.color="#ab8718";				
			}else
			{				
				a[0].style.color="#878888";
			}			
		}
	}
	menu.renderEvent=function()
	{	
		var ul=$(this.divName).getElementsByTagName("ul")
		var li=ul[0].getElementsByTagName("li");
		var me=this;
		for(var i=0;i<li.length;i++)
		{			
			var a=li[i].getElementsByTagName("a");
			var item=a[0];			
			item.onclick=function(e)
			{					
				my.ClickItem(this.id);
				me.change(this.id);	
			};
			item.oncontextmenu=function(e)
			{			
				my.ClickItem(this.id);
				ctMenu.load(this.id, e);
				return false;
			};								
		}
	}	
	this.addSubject=function(id)
	{		
				
		
	}
	this.editSubject=function(id)
	{					
	
	}
	this.getItemId=function(id)
	{
		var obj=null;
		for(var i=0;i<my.List.length;i++)
		{
			if(my.List[i].Id==id)
			{
				obj=my.List[i];
				break;
			}
		}
		return obj;
	}
	this.pageclick=function()
	{		
		if(ctMenu.state)ctMenu.hide();	
	}

function LoadFrm(id){
	document.getElementById("id_table").innerHTML=showfromRoleMng();
	$("add_subject").onclick=this.addsubject;
	$("remove_subject").onclick=this.removesubject;
	if(isAdmin=='true'){
		$("form_textbox_ok").onclick=function(){
		acceptUpdateRole();};
	}
	loadleft(id);
	loadright(id);
}
function acceptUpdateRole(){

	var select2=$("list_select2");	
	/*
		if(select2.options.length<=0)
		{
			var albox=new alertBox();
			albox.show("please select item in select 2 !");
			return;
		}
		*/
		var param="";
		for(var i=0;i<select2.options.length;i++)
		{
			if(i==select2.options.length-1){
			param+=select2.options[i].value;
			}else{
			param+=select2.options[i].value +",";
			}
		}
		var url="RoleMgn?CMD=5";
		url+="&cateId="+tempCategoryId;
		url+="&str=" + param;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{
		
			if(tx=="failed"){
			var albox=new alertBox();
				albox.show(checkUpdate);
			}else{
			
			}		
		}	
}
function showfromRoleMng(){		
	
		var html="";
			html+="<div class=\"form_change_subject_admin\" id=\"form_change_subject\">";
			html+="<div class='div_Title_song_admin' align=\"center\" >"			
			html+="</div>";						
				html+="<div style='float:left;width:100%;height: 300px' >"
					html+="<div class='left_change_subject_admin' id='left_change_subject'>";
					html+="</div>";	
					html+="<div style='float:left;width:30;margin-top:30px ;margin-left:40px'  >"
						html+="<div class='add_subject' id='add_subject'></div>"
						html+="<div class='remove_subject' id='remove_subject'></div>"
					html+="</div>";	
					html+="<div class='right_change_subject_admin' id='right_change_subject'>";
					html+="</div>";
				html+="</div>"					
					html+="<div align='center' class='div_sub_buton_admin_role' style='float:left;width:100%'>";
					html+="<div style='width:160'>"
						if(isAdmin=='true'){
							html+="<input type=\"button\" 	' value='" +update + "' id=\"form_textbox_ok\"/ style='margin-right:25px' >"
						}
					html+="</div>"
					html+="</div>"
			html+="</div>"			
			html+="<div>";				
			html+="</div>";			
			//html+="</div>";			
			html+="</div>";
			return html;		
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
this.loadleft=function(id)
	{		
			var url="RoleMgn?CMD=3"
			url+="&CateId="+id
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
						
					arrPmsCGL[i]=new ItemMenuRole(_id,_name);				
				}		
				var html=""
					html+="<div align='left' style='color:#ab8718'>"+NameNotAssigned+"</div>";
					html+="<select size=\"15\" multiple=\"multiple\" id='list_select1' class='list_select_sub_admin' ondblclick='addsubject()'>";					
			
				for(var i=0;i<arrPmsCGL.length;i++)
				{
				
					html+="<option value='"+arrPmsCGL[i].Id+"'>"+arrPmsCGL[i].Name+"</option>";
				}				
				html+="</select>";
				//alert(arr.length);
				$("left_change_subject").innerHTML=html			
				return arrPmsCGL;
			}
						
			
	}
	this.loadright=function(id)
	{
			var url="RoleMgn?CMD=4"
			url+="&CateId="+id
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
						
					arrPmsCGR[i]=new ItemMenuRole(_id,_name);				
				}
					
				var html=""
				html+="<div align='left'  style='color:#ab8718;margin-left:5px'>"+NameAssigned + "</div>";
				html+="<select size=\"15\" multiple=\"multiple\" id='list_select2' class='list_select_sub_admin' ondblclick='removesubject()'>";
				for(var i=0;i<arrPmsCGR.length;i++)
				{

					html+="<option value='"+arrPmsCGR[i].Id+"'>"+arrPmsCGR[i].Name+"</option>";
				}				
				html+="</select>";
				//alert(arr.length);
				$("right_change_subject").innerHTML=html			
				return arrPmsCGR;
			}	
					
	}	
function ItemMenuRole(id,name)
	{
	this.Id=id;this.Name=name;
	}
function contextMenu()
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
			html+="<a>"+creategroup+"</a>"
			html+="</li>";
			html+="<li class='icon_contextmenuDel'>";
			html+="<a>"+deletegroup+"</a>";
			html+="</li>";			
			html+="<li class='icon_contextmenuEdit'>";
			html+="<a>"+updategroup+"</a>";
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
		itemnew.onclick=function(){my.fucNew(0);};
		var itemdel=li[1].getElementsByTagName("a")[0];
		itemdel.onclick=function(){my.fucdel();};
		var itemedit=li[2].getElementsByTagName("a")[0];
		itemedit.onclick=function(){my.fucedit();};
		
	}
}


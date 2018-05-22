var idTabMain=1;
var tabsub=0;
function MenuTop()
{
	var my=this;
	var submenu=new SubmenuItem();
	this.menuId=-1;
	var menu_top_focus="menu_top_focus";
	var menu_top_item="menu_top_item";
	this.load=function()
	{				
		my.menuId=menuId;
		submenu.load(my.menuId);
		my.renderEvent();		
	}
	this.renderEvent=function()
	{
		var li=$("div_topmenu").getElementsByTagName("li");		
		for(var i=0;i<li.length;i++)
		{
			li[i].onclick=function()
			{
				var role=this.getAttribute("role");
				if(role==-1)
				{
					var albox=new alertBox();
					albox.show(langMain.not_role);
					return ;
				}
				var url="../ServicePath?ID="+this.id;				
				url+="&t="+Math.random();	
				window.location = url;			
			}
		}	
	}
	this.changfocus=function(me)
	{
		var li=$("div_topmenu").getElementsByTagName("li");		
		for(var i=0;i<li.length;i++)
		{			
			li[i].className=menu_top_item;
		}
		me.className=menu_top_focus;		
		submenu.load(me.id);
		idTabMain=me.id;		
	}
	this.show=function(list)
	{
	}
}
function SubmenuItem()
{
	this.ID=-1;
	this.focus=-1;
	var my=this;
	var list=new Array()
	var item_select="submenu_item_select";
	var item="submenu_item";
	this.load=function(id)
	{
		$("submenu").innerHTML="";
		list=new Array();
		my.ID=id;		
		my.get();		
	}
	this.get=function()
	{		
		var url="../ServiceCommon?CMD=2"
		url+="&groups="+my.ID;
		url+="&r="+Math.random();				
		var f=new AjaxGetXML(url);			
		f.complet=function(tx)
		{	
			var it=tx.getElementsByTagName("Item");
			//var k = 0;	
			for(var i=0;i<it.length;i++)
			{
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;	
				var _url=it[i].getElementsByTagName("url")[0].childNodes[0].nodeValue;
				var _role=it[i].getElementsByTagName("role")[0].childNodes[0].nodeValue;		
				var o=new ItemObj(_id,_name);
				o.Role=_role;
				o.Url=_url;
				//bo sung chuc nang loai bo Housekeeping 13.03.13
				/*if (_id != "18") {
					list[k]=o;
					k++;
				}*/
				list[i]=o;
			}
			my.show(list);
		}
	}
	this.show=function(arr)
	{
		for(var i=0;i<arr.length;i++)
		{
			var sub=document.createElement("li");
			var div=document.createElement("div");
			
			if(arr[i].Id==subId)
			{				
				div.className=item_select;
				div.innerHTML=arr[i].Name;		
			}else
			{				
				div.className=item;
				div.innerHTML=arr[i].Name;
			}
			sub.url=arr[i].Url;
			sub.id=arr[i].Id;
			sub.role=arr[i].Role;
			sub.appendChild(div);
			$("submenu").appendChild(sub);
		}
		my.renderEvent();
	}
	this.renderEvent=function()
	{
		var li=$("submenu").getElementsByTagName("li");	
		for(var i=0;i<li.length;i++)
		{			
			li[i].onclick=function()
			{		
				if(this.role==-1)
				{
					var albox=new alertBox();
					albox.show(langMain.not_role);
					return ;
				} else {
					var url=basePath+this.url+"?MenuId="+my.ID;
					url+="&SubId="+this.id;
					url+="&t="+Math.random();	
					window.location = url;	
				}
			}
		}	
	}
	this.change=function(me)
	{
		var li=$("submenu").getElementsByTagName("li");	
		for(var i=0;i<li.length;i++)
		{
			var div=li[i].getElementsByTagName("div");
			div[0].className=item;
		};
		var div=me.getElementsByTagName("div");
		div[tabsub].className=item_select;	
	}
}
function changelang(id)
{
	var pageURL = document.location.href;
	var baseURL=pageURL.substring(0,pageURL.indexOf("?"));
	var paramURL=pageURL.substring(pageURL.indexOf("?")+1);
	var para=paramURL.split("&");
	var MenuId,SubId;
	for(var i=0;i<para.length;i++)
	{
		if(para[i].indexOf("MenuId")>=0)
		{
			MenuId=para[i].substring(para[i].indexOf("=")+1);
		}
		if(para[i].indexOf("SubId")>=0)
		{
			SubId=para[i].substring(para[i].indexOf("=")+1);
		}
	}
	if(para.length>0)
	{
		baseURL+="?MenuId="+MenuId+"&SubId="+SubId+"&Lang="+id+"&r="+Math.random();
	}else
	{
		baseURL+="?Lang="+id+"&r="+Math.random();
	}	
	window.location.href=baseURL;
}
function checkRoleGroup(id)
{	
}
function checkRoleMenu(id)

{
}
function goHome()
{
	var pageURL = "../ServiceHome?"+"&r="+Math.random();;
	window.location.href=pageURL;
}
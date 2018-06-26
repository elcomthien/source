var rte=new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var other=new function Other()
{
	var tag=0;
	var my=this;	
	var ss=new SceSytem();
	var ds = new DaemonServiceSystem();
	this.load=function()
	{
		if(tag==0) ss.run();
		else if (tag==1) ds.run();
	}
	this.addItem=function()
	{
		if(tag==0) ss.addItem();
		else if (tag==1) ds.addItem();
	}
	this.onclick=function()
	{
		if(tag==0) ss.onclick();
		else if (tag==1) ds.onclick();
		//ss.onclick();
	}
	this.changeTab=function(id)
	{
		var div=$("tab_menu").getElementsByTagName("div");
		for(var i=0;i<div.length;i++)
		{
			div[i].className="tab_menu_item";
		}
		div[id].className="tab_menu_item_sel";
		//quy dinh id cua tab
		tag=id;
		//bo sung viec chuyen doi giua cac tab thi menucontext phai tat di
		if ($("div_contextMenu") != null)
			$("div_contextMenu").style.display = 'none';	
		my.load();
	}
}
window.onload = function()
{
	other.load();    
	document.body.onclick=other.onclick;
}
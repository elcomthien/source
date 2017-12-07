var rte=new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var other=new  function Other()
{
	var tag=1;
	var my=this;	
	var prom=new Promotion();
	var exchange=new Exchange();	
	var adv=new Advertise();
	this.load=function()
	{
		if(tag==0)
		prom.run();
		if(tag==1)
			exchange.run();
		if(tag==2)adv.run();
	}
	this.addItem=function()
	{
		if(tag==0)
			prom.addItem();
			if(tag==1)
				exchange.addItem();
			if(tag==2)adv.addItem();
	}
	this.onclick=function()
	{
		prom.onclick();
		exchange.onclick();
		adv.onclick();
	}
	this.changeTab=function(id)
	{
		
		var div=$("tab_menu").getElementsByTagName("div");
		for(var i=0;i<div.length;i++)
		{
			div[i].className="tab_menu_item";
		}
		div[id].className="tab_menu_item_sel";		
		tag=id;		
		my.load();
	}
	
}
window.onload = function()
{
	other.load();    
	document.body.onclick=other.onclick;
}  
var Main =new function MainAcAt()
{
	var my=this;
	var tag=0;
	this.changeTab=function(id)
	{
		
		var div=$("tab_menu").getElementsByTagName("div");
		if(id==0)
		{		
			local.onclick();
			my.loadActiviti();
			div[0].className="tab_menu_item_sel";
			div[1].className="tab_menu_item";
		}else
		{
			acti.onclick();
			my.loadAttaction();
			div[1].className="tab_menu_item_sel";
			div[0].className="tab_menu_item";
		}
		
		tag=id;		
	}
	this.loadActiviti=function()
	{
		acti.run();
	}
	this.loadAttaction=function()
	{
		local.run();
	}
	this.load=function()
	{
		acti.run();
	}
}

 window.onload = function()
 {
	 Main.load();
		
 }   


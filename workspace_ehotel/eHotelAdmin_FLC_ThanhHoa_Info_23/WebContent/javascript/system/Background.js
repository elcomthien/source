var rte=new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var other=new function Other()
{
	var tag=0;
	var my=this;	
	var ss=new SceSytem();
	var bv = new BgVideo();
	var bt = new BgType();
	var wc = new WelcomeVideo();
	this.load=function()
	{
		if(tag==0) ss.run();
		else if (tag==1) bv.run();
		else if (tag==2) bt.run();
		else if (tag==3) wc.run();
	};
	this.addItem=function()
	{
		if(tag==0) ss.addItem();
		else if (tag==1) bv.addItem();
		else if (tag==2) bt.addItem();
		else if (tag==3) wc.addItem();
	};
	this.onclick=function()
	{
		if(tag==0) ss.onclick();
		else if (tag==1) bv.onclick();
		else if (tag==2) bt.onclick();
		else if (tag==3) wc.onclick();
	};
	this.changeTab=function(id)
	{
		var div=$("tab_menu").getElementsByTagName("div");
		for(var i=0;i<div.length;i++)
		{
			div[i].className="tab_menu_item";
		}
		div[id].className="tab_menu_item_sel";

		tag=id;
		
		if(tag == 0){
			$("id_table").style.display = "block";
			$("id_table_bg_video").style.display = "none";
			$("id_table_bg_type").style.display = "none";
		}
		if(tag == 1){
			$("id_table").style.display = "none";
			$("id_table_bg_video").style.display = "block";
			$("id_table_bg_type").style.display = "none";
		}
		if(tag == 2){
			$("id_table").style.display = "none";
			$("id_table_bg_video").style.display = "none";
			$("id_table_bg_type").style.display = "block";
		}
		if(tag == 3){
			$("id_table").style.display = "block";
			$("id_table_bg_video").style.display = "none";
			$("id_table_bg_type").style.display = "none";
		}
		
		if ($("div_contextMenu") != null)
			$("div_contextMenu").style.display = 'none';	
		my.load();
	};
};
window.onload = function()
{
	other.load();    
	document.body.onclick=other.onclick;
};
function SceSytem()
{
	var my=this;
	//khoi tao doi tuong lay list service 22.1
	var list=new Listservice();
	this.run=function()
	{
		list.load();		
	};
	this.onclick=function()
	{
		list.pageclick();
	};
	this.addItem=function()
	{
		list.addeImage();
	};
};
//ham lay list service 22.1
function Listservice()
{
	var Index=0;
	var table=new Table();

	this.addeImage=function()
	{
		var add=new addItemAttaction();
		add.load(my.SubId);
		add.update=function() {
			my.reload();
		};
	};

	this.pageclick=function()
	{
//		if(ctTable.state) ctTable.hide();
	};
	var my=this;
	this.count=0;
	var ID=0;//id of subject
	this.SubId=-1;	
	this.ListCheck=new Vector();
	var arr=new Array();
	var defaults = {
		divName		: "id_table",
		col			: 5,
		classHeader	: "mytable_header",
		classItem	: "classItem",
		classTable	: "classTable",
		classRowsel	: "mytable2_row_sel",
		classTable	: "mytable",
		bgRow1		: "mytable2_row1",
		bgRow2		: "mytable2_row2"
	};
	this.load=function()
	{		
		Index=0;
		table.init(defaults);
		my.get(Index);			
	};
	this.reload=function()
	{				
		Index=0;
		my.get(ID,Index);	
	};
	this.loadIdex=function(index)
	{		
		Index=index;
		my.get(ID,index);		
	};
	this.nextindex=function()
	{	
		var page=5;
		var cpage=((my.count-1)/page)-1;
		if(Index<cpage)
				Index++;
		my.loadIdex(Index);
	};
	this.backindex=function()
	{
		if(Index>0)Index--;
		my.loadIdex(Index);
	};

	this.selectItem=function(id)
	{		
		if(Common.ctrl)
		{
			if(!this.ListCheck.isObject(id))
			{
				this.ListCheck.add(id);
			}else
			{
				this.ListCheck.removeObj(id);
			}			
		}else
		{
			my.ListCheck=new Vector();
			my.ListCheck.add(id);
		}
		my.shiftSelect();
	};
	this.addItem=function(id)
	{		
		if(!this.ListCheck.isObject(id))
		{
			this.ListCheck.add(id);
		}
		my.shiftSelect();
	};
	this.oncontextmenu=function(id,e,name)
	{			
		var obj=null;
		for(var i=0;i<arr.length;i++)
		{
			if(arr[i].Id==id)
			{
				obj=arr[i];
				break;
			}
		}		
		ctTable.load(obj, e,name);
	};
	/*render du lieu ra html 22.1, tab ServiceSystem*/
	table.dataBind=function()
	{	
		var rannum = Math.floor((Math.random() * 100000) + 1);
		
		var html="";
			html+="<div class='div_formother1' style='overflow-y:hidden; margin-top:0px; height: 230px;'>";
			html+="<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"97%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\" style='margin-top:10px;'>";
			html+="<tr class=\""+this.classHeader+"\">";		
			html+="<th align='left' valign=\"middle\"  width=\""+70+"%\" class='header0' >";
			html+=langpms.name;
			html+="</th>";		
			html+="<th align='center' width=\""+20+"%\" valign=\"middle\">";
			html+=langpms.image;
			html+="</th>";
			html+="<th align='center' width=\""+10+"%\" valign=\"middle\">";
			html+="Action";
			html+="</th>";
			html+="</tr>";
			
			
			html+="<tr rel='bg' class=\""+this.bgRow2+"\" id='bg' style='cursor: default;'>";
			html+="<td align=\"left\"  valign=\"middle\">";
			html+="Background";
			html+="</td>";
			html+="<td align=\"center\" valign=\"middle\">";
			html+="<img class='im_ontable' src='" + this.List[0] + "/bg.png?"+rannum+"'></img>";
			html+="</td>";
			html+="<td align=\"center\" valign=\"middle\">";
			html+="<img src=\"../images/edit_bg.png\" width='30px;' title='Change image background' onclick=changeImage('bg','"+this.List[0]+"') style='cursor: pointer;'></img>";
			html+="</td>";
			html+="</tr>";
			
			html+="<tr rel='logo' class=\""+this.bgRow1+"\" id='logo' style='cursor: default;'>";
			html+="<td align=\"left\"  valign=\"middle\">";
			html+="Logo";
			html+="</td>";
			html+="<td align=\"center\" valign=\"middle\">";
			html+="<img class='im_ontable' src='" + this.List[0] + "/logo.png?"+rannum+"'></img>";
			html+="</td>";
			html+="<td align=\"center\" valign=\"middle\">";
			html+="<img src=\"../images/edit_bg.png\" width='30px;' title='Change image logo' onclick=changeImage('logo','"+this.List[0]+"') style='cursor: pointer;'></img>";
			html+="</td>";
			html+="</tr>";
			
			html+="<tr rel='logo_small' class=\""+this.bgRow2+"\" id='logo_small' style='cursor: default;'>";
			html+="<td align=\"left\"  valign=\"middle\">";
			html+="Small Logo";
			html+="</td>";
			html+="<td align=\"center\" valign=\"middle\">";
			html+="<img class='im_ontable' src='" + this.List[0] + "/logo_small.png?"+rannum+"'></img>";
			html+="</td>";
			html+="<td align=\"center\" valign=\"middle\">";
			html+="<img src=\"../images/edit_bg.png\" width='30px;' title='Change image small logo' onclick=changeImage('logo_small','"+this.List[0]+"') style='cursor: pointer;'></img>";
			html+="</td>";
			html+="</tr>";
	
			html+="</table>";
			html+="</div>";
			
			return html;
		
	};
	this.shiftSelect=function()
	{		
		var tr=$(table.divName).getElementsByTagName("tr");
		for(var i=1;i<tr.length;i++)
		{
			if(i%2==0)
			{
				tr[i].className=table.bgRow1;
			}else
			{
				tr[i].className=table.bgRow2;
			}	
			
			for(var j=0;j<my.ListCheck.size();j++)
			{
				if(tr[i].id==my.ListCheck.get(j))
				{
					tr[i].className=table.classRowsel;
					break;
				}
			}
		}
	};

	this.get=function(id)
	{
		var url="Background?CMD=9";
		url+="&r="+Math.random();			
		var f=new AjaxGetText(url);			
		f.complet=function(tx)
		{
				arr=new Array();
				arr[0] = tx;
				table.load(arr);
				table.classCol("classItem1",1);
				table.classCol("classItem3",0);
//				my.renderEvent();
		};
	};
	this.loadcomplet=function()
	{
	};
}
function changeImage(name,link){
	var text = "";
	if(name == "bg")
		text = "Background";
	if(name == "logo")
		text = "Logo";
	if(name == "logo_small")
		text = "Small Logo";
	var my=this;
	var layer=new layer_vitual();
	layer.show();
	var url="Background?CMD=3";
	url+="&r="+Math.random();	
	var f=new AjaxGetText(url);
	f.complet=function(tx)
	{			
		layer.addHtml(tx);
		setCenterDIV("form_detail_servicesystem");
		$("name").value = text;
		document.getElementById("name").readOnly = true;
		$("image").src = link + "/" + name + ".png";
		$("form_textbox_ok").onclick=function() { my.accept(name); };
		$("form_textbox_cancel").onclick=my.cancel;				
	};
	this.accept=function(name){
		var image=$("image").src;
		image=image.substring(image.lastIndexOf("/")+1);
		
		var url="Background?CMD=3";
			url+="&t="+Math.random();
			var param="&name="+name;
			param+="&image="+image;
			var f=new AjaxPostText(url,param);			
			f.complet=function(tx) {
				my.hide();
				location.reload(true);
			};
	};
	this.cancel=function() {
		my.hide();
	};
	this.hide=function() {
		$("div_layer_vitual").style.display="none";
	};
}



//update service dong 3 trong menu context 23.1
function DetailService()
{
	var my=this;	
	var layer=new layer_vitual();	
	this.Id=-1;
	this.load=function(id) {			
		my.Id=id;
		my.show(id);
	};
	this.show=function(id) {			
		layer.show();			
		var url="ServiceSystem?CMD=3";
		url+="&id="+id;
		url+="&r="+Math.random();	
		var f=new AjaxGetText(url);
		f.complet=function(tx)
		{			
			layer.addHtml(tx);
			//goi div voi id ben duoi (detailServiceSystem.jsp) 23.1
			setCenterDIV("form_detail_servicesystem");
			$("form_textbox_ok").onclick=function() { my.accept(); };
			$("form_textbox_cancel").onclick=my.cancel;				
		};
	};
	this.accept=function() {			
		var name=$("name").value;
		name=encode(name);
		var image=$("image").src;
		image=image.substring(image.lastIndexOf("/")+1);
		if(name.length==0) {
			var albox=new alertBox();
			albox.show(langpms.pls_name);
			return;
		};
		var url="ServiceSystem?CMD=3";
			url+="&t="+Math.random();
			var param="&name="+name;
			param+="&id="+my.Id;
			param+="&image="+image;
			param+="&lang="+language;
			var f=new AjaxPostText(url,param);			
			f.complet=function(tx) {
				my.hide();
				my.update();
			};
	};
	this.update=function() {
	};
	this.cancel=function() {
		my.hide();
	};
	this.hide=function() {
		$("div_layer_vitual").style.display="none";
	};
}
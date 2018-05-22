function ServiceBackup()
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
		my.get();			
	};
	this.reload=function()
	{				
		Index=0;
		my.get();	
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
		var html="";
			html+="<div class='div_formother1' style='overflow-y:auto; margin-top:0px; height: 230px; width: 70%; float: left;'>";
			html+="<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"97%\" class=\""+this.classTable+"\" id=\"table_"+this.divName+"\" style='margin-top:10px;'>";
			html+="<tr class=\""+this.classHeader+"\">";	
			html+="<th align='center' width=\""+10+"%\" valign=\"middle\">";
			html+="No";
			html+="</th>";
			html+="<th align='left' valign=\"middle\"  width=\""+70+"%\" class='header0' >";
			html+="File Name";
			html+="</th>";		
			html+="<th align='center' width=\""+20+"%\" valign=\"middle\">";
			html+="Action";
			html+="</th>";
			html+="</tr>";
			for(var i=0;i<this.List.length;i++)
			{			
				
					if(i%2==0)
					{				
						html+="<tr class='mytable_row2' style='cursor:default;'>";
					}else
					{
						html+="<tr class='mytable_row1' style='cursor:default;'>";
					}					
					
					html+="<td align=\"center\"  valign=\"middle\">";
					html+= i + 1;
					html+="</td>";
					html+="<td align=\"left\" valign=\"middle\">";
					html+=this.List[i].Name;
					html+="</td>";
					html+="<td align=\"center\" valign=\"middle\">";
					html+="<img src=\"../icon/download.png\" width='40px;' title='Download file backup data' onclick=downloadFile('"+this.List[i].File+"') style='cursor: pointer;'></img>";
//					html+="<a href='javascript:downloadFile('"+encodeURI(this.List[i].File)+"')'>download</a>";
					html+="</td>";
					html+="</tr>";				
			}
			
			
	
			html+="</table>";
			html+="</div>";
			
			html+="<div style='width: 30%; float: right;'><p style='margin-top: 50px; height: 20px; color: #A9A9A9; font-weight: bold; '>Config schedule backup</p>";
			html+="<input type='text' value='1' id='schedule-backup' style='border: 1px solid #dddddd; height: 24; float: left; width: 190px; margin-top: 5px; margin-left: 20px; text-align: right;' onkeypress='validate(event)'>";
			html+="<p style='font-size: 13px; float: right; margin-top: 0px;'>Data backup period (count by day)</p>";
			html+="<input type='button' value='Config' id='btn-schedule-backup' class='div_buton' style='margin-left: 70px; margin-top: 20px;'>";
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

	this.get=function()
	{
		var url="Backup?CMD=9";
		url+="&r="+Math.random();			
		var f=new AjaxGetXML(url);			
		f.complet=function(tx)
		{
			var arr=new Array();	
			var it=tx.getElementsByTagName("Item");					
			for(var i=0;i<it.length;i++)
			{
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;			
				var _file=it[i].getElementsByTagName("file")[0].childNodes[0].nodeValue;
				var item=new ItemFileBackup(_name,_file);
				arr[i]=item;
			}
			my.List = arr;
			table.load(arr);
			my.getschedule();
		};
	};
	function ItemFileBackup(name,file)
	{
		this.Name=name;
		this.File=file;
	}
	this.loadcomplet=function()
	{
	};
	
	this.getschedule = function(){
		var url="Backup?CMD=2";
		url+="&r="+Math.random();			
		var f=new AjaxGetText(url);			
		f.complet=function(tx)
		{
			var elem = document.getElementById("schedule-backup");
			elem.value = tx;
		};
	};
	
	this.configSchedule = function(){
		var schedule = document.getElementById('schedule-backup').value;
		var url="Backup?CMD=2";
		url+="&schedule=" + schedule;
		url+="&r="+Math.random();			
		var f=new AjaxGetText(url);			
		f.complet=function(tx)
		{
			my.reload();
		};
	};
}
function downloadFile(link){
	link = decodeURI(link);
	var win = window.open(link, '_blank');
	win.focus();

}
function validate(evt) {
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]|\./;
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
	}
}



window.onload = function()
{
	var backup = new ServiceBackup();
	backup.load();    
	document.body.onclick=backup.pageclick;
};
function AddMod()
{
	var my=this;
	var path="";
	var list=new Vector();
	this.SubID=-1;
	var albox=new alertBox();
	var wt=new Waiting();	
	this.load=function()
	{
		my.SubID=mod.sub.ID;
		my.loadFile();		
	}
	this.loadFile=function()
	{
		
		var url="ModCtnMain?CMD=8";
			url+="&path="+path;
			url+="&r="+Math.random();	
			wt.show(3000);
			var f=new AjaxGetXML(url);		
			
			f.complet=function(tx)
			{
				var arr=new Array();
				var it=tx.getElementsByTagName("Item");				
				for(var i=0;i<it.length;i++)
				{					
					var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;					
					var _type=it[i].getElementsByTagName("type")[0].childNodes[0].nodeValue;					
					var item=new itemFTP(_name,_type);	
					arr[i]=item;					
				}	
				wt.hide();
				my.bindData(arr);
			}
	}
	this.bindData=function(list)
	{		
		if(path.length>0)
		{
			var arr=new Array();
			arr[0]=new itemFTP("..",1);
			var j=1;
			for(var i=0;i<list.length;i++)
			{
				arr[j]=list[i];
				j++;
			}
			list=arr;
		}
		var ul=$("ul_ftp_file");
		ul.innerHTML="";		
		for(var i=0;i<list.length;i++)
		{
			var li=document.createElement("li");	
			var a=document.createElement("a");
			var name=list[i].Name;
			a.innerHTML=name;			
			a.href="javascript:";
			a.name=list[i].Name;			
			if(list[i].Type==1)
			{
				li.setAttribute("type", 1);
				a.className="itemfolder";
				a.onclick=function()
				{					
					my.onclickFolder(this.name);
				};
			}else
			{
				li.setAttribute("type", 0);
				a.className="itemfile";				
				a.ondblclick=function()
				{					
					my.onclickFile(this.name);
				};
			};			
			li.appendChild(a);
			ul.appendChild(li);
		}
		
	}
	this.onclickFolder=function(me)
	{		
		if(me=="..")
		{
			
			if(path.indexOf("/")==0){path=path.substring(1);};	
			path=path.substring(0, path.lastIndexOf("/"));
			my.loadFile(path);
			
		}else
		{			
			path+="/"+me;
			if(path.indexOf("/")==0){path=path.substring(1);};				
			my.loadFile(path);			
			
			
		}	
	}
	this.shiftTable=function()
	{
		var table=$("tableMp3");
		var rowCount = table.rows.length;
		for(var i=1;i<rowCount;i++)
		{
			var row=table.rows(i);
			var cell1=row.cells(0);
			cell1.innerHTML=i;
			var cell2=row.cells(3);
			var img=cell2.getElementsByTagName("img");
				img[0].id=i;
			img[0].onclick=function()
		    {				
				my.remove(this.id)
		    }
		}
	}
	this.onclickFile=function(name)
	{
		var file=name;
		if(path.length>=0)
		{
			file=path+"/"+name;
		}	
		
		var state=false;
		for(var i=0;i<list.size();i++)
		{
			if(list.get(i).Name==file)
			{
				state=true;
				break;
			}
		}	
		if(state)
		{			
			albox.show("da ton tai !");
			return false;
		}else
		{
			var item=new itemMp3(file,false);
			list.add(item);
		}
		var table=$("tableMp3");
		var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var cell1 = row.insertCell(0);
        cell1.innerHTML=rowCount;
        cell1.setAttribute("align","center");
        
        var cell2 = row.insertCell(1);
        cell2.innerHTML=name;
        
//        var cell3 = row.insertCell(2);      
//        var element0=document.createElement("input");
//        element0.setAttribute("type","checkbox");
//        element0.setAttribute("id",rowCount);
//        element0.onclick=function()
//        {        	
//        	 my.checkClip(this);
//        }       
//        cell3.appendChild(element0);
        
//        var cell4 = row.insertCell(3);
        var cell4 = row.insertCell(2);
        cell4.setAttribute("align","center");        
        var element1 = document.createElement("img");
        element1.src="../icon/001_02.png";         
        element1.onclick=function()
        {
        	my.remove(rowCount);
        }
        cell4.appendChild(element1);
	}
	this.remove=function(r)
	{
	
		list.remove(r-1);
		var table=$("tableMp3");
		table.deleteRow(r);
		my.shiftTable();
		
	}
	this.checkClip=function(me)
	{
		var r=me.id-1;
		list.get(r).Clip=me.checked;
		
	}
	this.accept=function()
	{		
		var param="";
		for(var i=0;i<list.size();i++)
		{
			param+="&file"+i+"="+list.get(i).Name;
			param+="&clip"+i+"="+list.get(i).Clip;
		}
		var url="ModCtnMain?CMD=9";
			url+=param;
			url+="&r="+Math.random();
			url+="&SubId="+my.SubID;
			wt.show(3000);
			var f=new AjaxGetXML(url);
			f.complet=function(tx)
			{
				var it=tx.getElementsByTagName("Item");
				
				if(it.length>0)
				{
					var st="";
					st+="Bai hat da ton tai:<br>";
					for(var i=0;i<it.length;i++)
					{					
						var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;						
						st+=_name+"<br>";
						albox.show(st);					
					}
				}
				wt.hide();
				my.reset();
			}
	}
	this.reset=function()
	{
		list.Empty();
		var table=$("tableMp3");
		var rowCount = table.rows.length;
		while(rowCount>1)
		{
			table.deleteRow(1);
			rowCount = table.rows.length;
		}
	}
	function itemFTP(name,type)
	{
		this.Name=name;this.Type=type;
	}
	function itemMp3(name,clip)
	{
		this.Name=name;this.Clip=clip;
	}
}
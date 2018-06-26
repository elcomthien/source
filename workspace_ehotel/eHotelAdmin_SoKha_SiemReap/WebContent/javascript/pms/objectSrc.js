function layer_vitual()
{
	var my=this;
	this.show=function()
	{		
		if($("div_layer_vitual")==undefined)
		{
			var div=document.createElement("div");		
			div.id="div_layer_vitual";
			div.className="div_layer_vitual";
			document.body.appendChild(div);				
			$("div_layer_vitual").style.height=document.body.clientHeight+document.body.scrollTop;
		}else
		{
			$("div_layer_vitual").innerHTML="";
			$("div_layer_vitual").style.display="block";			
		}	
	}
	this.addHtml=function(html)
	{
		$("div_layer_vitual").innerHTML=html;
	}
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	}
}
function conformBox()
{
	var layer=new layer_vitual();
	var my=this;
	this.show=function(text,ok,cancel)
	{
		layer.show();
		var html="<div class='div_conformBox' id='div_conformBox' align='center'>";
		//html+="<div class='conformBox_title'>"
		//html+="</div>"
		html+="<div class='conformBox_info'>";
			html+="<div class='icon_alert'>";
			html+="</div>";
			html+="<div class='messages_box' >";
			html+=text;
			html+="</div>";
		html+="</div>";
			html+="<div class='div_0' align='center'>";
			
			html+="<div class='div_1'>";
				
				html+="<input type=\"button\" value='"+ ok +"'  class='div_buton'id=\"button_ok\" >";
				html+="<input type=\"button\" value='"+ cancel +"'   class='div_buton' id=\"button_cancel\">";
			html+="</div>";
				html+="</div>";
		html+="</div>";
		layer.addHtml(html);		
		setCenterDIV("div_conformBox");
		my.renderClick();
	}
	this.renderClick=function()
	{
		$("button_ok").onclick=function(){						
			my.accept();
			layer.hide();
		};
		$("button_cancel").onclick=function(){my.cancel();};
	}
	this.accept=function()
	{
		layer.hide();
	}
	this.cancel=function()
	{
		layer.hide();
	}	
}
function alertBox()
{
	
	var my=this;
	this.show=function(text)
	{
		var div=null;	
		var html="<div class='div_alertBox' id='div_alertBox' align='center'>";
		//html+="<div class='conformBox_title'>"
		//html+="</div>"
		html+="<div class='conformBox_info'>";
			html+="<div class='icon_alert'>";
			html+="</div>";
			html+="<div class='messages_box' >";
			html+=text;
			html+="</div>";
		html+="</div>"
			html+="<div class='div_0' align='center'>";			
			html+="<div class='div_bt_alert'>";
			html+="<input type=\"button\" value='  OK  '  class='div_buton'id=\"button_ok\" >";
			html+="</div>";
				html+="</div>";
		html+="</div>";
		if($("bg_alertBox")==undefined)
		{
			div=document.createElement("div");	
			document.body.appendChild(div);	
			div.id="bg_alertBox";
			div.className="bg_alertBox";
			div.innerHTML=html;
			
		}else
		{				
			$("bg_alertBox").style.display="block";
			$("bg_alertBox").innerHTML=html;
				
		}		
		setCenterDIV("div_alertBox");
		my.renderClick();
	}

	this.getHtml=function()
	{
	}
	this.renderClick=function()
	{
		$("button_ok").onclick=function()
		{					
			my.accept();
			my.hide();
		};		
	}
	this.accept=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		$("bg_alertBox").style.display="none";
	}	
}

function Vector()
{
	var list=new Array();
	
	this.add=function(obj)
	{
		list[list.length]=obj;
	}
	this.size=function()
	{
		return list.length;
	}
	this.get=function(i)
	{
		if(i>=list.length)
		{
			alert("size out");
		}else
		{
			return list[i];
		}
	}
	this.remove=function(index)
	{
		var arr=new Array();
		var j=0;
		for(var i=0;i<list.length;i++)
		{
			if(i!=index)
			{
				arr[j]=list[i];
				j++;
			}
		}
		list=arr;
	}
	this.lastElement=function()
	{		
		return list[list.length-1];
	}
	this.clone=function()
	{
		var item=new Vector();		
		for(var i=0;i<list.length;i++)
		{		
			item.add(list[i]);		
		}
		return item;
	}
	this.getObject=function(obj)
	{
		for(var i=0;i<list.length;i++)
		{		
			if(list[i]==obj)
			{
				return list[i];
				
			}
		}
	}
	this.removeObj=function(obj)
	{
		for(var i=0;i<list.length;i++)
		{		
			if(list[i]==obj)
			{
				this.remove(i);
				break;
			}
		}
	}
	this.isObject=function(obj)
	{
		for(var i=0;i<list.length;i++)
		{		
			if(list[i]==obj)
			{
				return true;
				break;
			}
		}
		return false;
	}
	this.Empty=function()
	{
		
		list=new Array();
	}
}
function ItemObj(id,name)
{
	this.Id=id;this.Name=name;
}
function ItemMenu(id,parent,name,image)
{	
	this.Id=id;this.Name=name;this.Image=image;this.Parent=parent;
}
function ItemMp3(id,title,singer,album,url,lyric)
{
	this.Id=id;this.Title=title;this.Singer=singer;this.Album=album;this.Url=url;this.Lyric=lyric;
}
function ItemImage(id,name,image,icon,status)
{
	this.Id=id;this.Name=name;this.Image=image;this.Icon=icon;this.Status=status;
}
function ItemVOD(id,name,actors,director,duration)
{
	this.Id=id;
	this.Name=name;
	this.Actors=actors;
	this.Director=director;
	this.Duration=duration;
}

function BoxSubject(title,value,image1,image2)
{	
	var my=this;
	this.show=function()
	{				
		if($("div_layer_vitual")==undefined)
		{
			var div=document.createElement("div");		
			div.id="div_layer_vitual";
			div.className="div_layer_vitual";
			document.body.appendChild(div);				
			//$("div_layer_vitual").style.display="block";
		}else
		{			
			$("div_layer_vitual").style.display="block";			
		}		
		var html="";
		html+="<div class=\"form_textbox\" id=\"form_textbox\" style='width: 440px;height:370;'  align='center'>";
			html+="<div class=\"form_textbox_subject\"><div class='div_title_form_new'>"+title+"</div></div>";
			
				
				html+="<div class='form_textbox_center' style='width: 100%;'>"	;				
					html+="<div  class='form_subject_inbox'>"	;
						
					html+="<div class='subject_textbox_input' style='margin-top: 8px;'>";
						html+="<div class='subject_name_input'  >Name:</div>"		;				
						html+="<input type=\"text\" size='26'  style='border: 1px solid #dddddd;height:24;float:left;width:220;margin-top: 5px;'  id=\"form_textbox_input\" value='"+value+"'/>"
										
					html+="</div>"			;				
					html+="<div class='subject_textbox_input'>"	;
						html+="<div class='subject_name_input' >Image:</div>"	;
						html+="<div  style='float:left;width:220;height: 25px;margin-top: 8px;' >"
							html+="	<iframe class='frame_upload' src=\"../upload\?image=image1\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>"
						html+="</div>"		;				
					html+="</div>"		;
						html+="<div class='subject_textbox_input'>"	;
							html+="<div class='subject_name_input'  style='width:120;'>background:</div>"	;
							html+="<div  style='float:left;width:220;height: 25px;margin-top: 8px;' >";
								
									html+="	<iframe class='frame_upload' src=\"../upload\?image=image2\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>"
							html+="</div>"		;				
						html+="</div>"	;
							
					html+="<div  style='width: 100%;margin-top:20px;float:left;' >";
						html+="<div class='subject_div_image' style='margin-left: 40px'>";
						if(image1.length>0)
						{
							html+="<img id='image1' src=\"../resource/images/"+image1+"\" width='170' height='150'></img>";
						}else
						{
							html+="<img id='image1' src=\"../resource/images/noimage.gif\" width='170' height='150'></img>";
						}						
						html+="</div>";
						html+="<div class='subject_div_image'>";
							if(image2.length>0)
							{
								html+="<img id='image2' src=\"../resource/images/"+image2+"\" width='170' height='150'></img>";
							}else
							{
								html+="<img id='image2' src='../images/noimage.gif' width='170' height='150'></img>";
							}
						
						html+="</div>";
					html+="</div>"	;
					html+="<div  style='float:left;width: 100%;margin-top: 8px;'>";
						html+="<div style='width:160;'>";
							html+="<input type=\"button\" style='margin-left: 15px;' value='  OK  '  class='div_buton'id=\"form_textbox_ok\" >";
							html+="<input type=\"button\" value=Cancel  class='div_buton' id=\"form_textbox_cancel\">";
						html+="</div>";
						html+="</div>"	;	
						
					html+="</div>"	;					
				html+="</div>"	;
			html+="<div class='form_textbox_bottom'>";
			html+="</div>";
					
		html+="</div>";				
		var x=0;
		var y=0;			
		x=document.body.clientWidth+document.body.scrollLeft+document.body.scrollLeft;
		y=document.body.clientHeight+document.body.scrollTop+ document.body.scrollTop;			
		$("div_layer_vitual").innerHTML=html;
		
		$("form_textbox").style.left=(x/2)-200+"px";
		$("form_textbox").style.top=(y/2)-150+"px";			
		$("form_textbox_ok").onclick=function(){
			
			var value=$("form_textbox_input").value;			
			var image1=$("image1").src;
			var image2=$("image2").src;
			my.accept(value,image1,image2);
		};
		$("form_textbox_cancel").onclick=my.cancel;			
	}
	this.accept=function(t)
	{
		my.hide();
	}
	this.cancel=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	}	
}
function newBox(title,name,image,titleName,titleIma,ok,cancel)
{
	var my=this;
	this.show=function()
	{		
		
		if($("div_layer_vitual")==undefined)
		{
			var div=document.createElement("div");		
			div.id="div_layer_vitual";
			div.className="div_layer_vitual";
			document.body.appendChild(div);				
		}else
		{
			$("div_layer_vitual").style.display="block";				
		}	
		var html="";
		html+="<div class=\"form_textbox\" id=\"form_textbox\"  align='center'>";
			html+="<div class=\"form_textbox_subject\"><div class='div_title_form_new'>"+title+"</div></div>";
			
				
				html+="<div class='form_textbox_center'>"	;						
					html+="<div class='left_textbox'>"	;
						html+="<div class=\"form_textbox_input\">";				
						html+="<div class='div_name_input'>"+titleName+":</div>";						
							html+="<input type=\"text\" size='26'  style='border: 1px solid #dddddd;height:24;float:left;'  id=\"form_textbox_input\" value='"+name+"'/>";
						html+="</div>";
						html+="<div class=\"form_textbox_input\">";							
						html+="<div class='div_image_input'>"+titleIma+":</div>";
						html+="<div  style='float:left;width:180;height: 25px;margin-top: 8px;' >";
							html+="	<iframe class='frame_upload' src=\"../upload\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>";
						html+="</div>";
							
						html+="</div>";
							html+="<div style='margin-top: 5px;'>";
								html+="(Click";
									html+="<img  src=\"../icon/drop.png\"></img>";
								html+=changeIma+")";	
								html+="</div>";
						html+="</div>";
						html+="<div class='right_textbox' style='border: 1px solid #dddddd;'>"	;
							html+="<div class='div_subject_image' >";
							if(image=='')
							{
								html+="<img id='image' src='../images/noimage.gif' width='150' height='150'></img>";
							}else
							{
								html+="<img id='image' src=\"../resource/images/service/"+image+"\" width='150' height='150'></img>";
							}
							html+="</div>";
						html+="</div>";
				
				html+="<div class='form_1'>";
					html+="<input type=\"button\" style='margin-left: 15px;' value='"+ok+"'  class='div_buton'id=\"form_textbox_ok\" >";
					html+="<input type=\"button\" value='"+cancel+"'  class='div_buton' id=\"form_textbox_cancel\">";
					html+="</div>";
				html+="</div>";
					
			html+="<div class='form_textbox_bottom'>";
			html+="</div>";
		html+="</div>";			
		var x=0;
		var y=0;			
		x=document.body.clientWidth+document.body.scrollLeft+document.body.scrollLeft;
		y=document.body.clientHeight+document.body.scrollTop+ document.body.scrollTop;			
		$("div_layer_vitual").innerHTML=html;
		
		$("form_textbox").style.left=(x/2)-200+"px";
		$("form_textbox").style.top=(y/2)-150+"px";	
		$("form_textbox_ok").onclick=function(){
			var value=$("form_textbox_input").value;			
			var image=$("image").src;
			my.accept(value,image);
		};
		$("form_textbox_cancel").onclick=my.cancel;	
	}
	this.accept=function(t)
	{
		my.hide();
	}
	this.cancel=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	}	
}
//goi ham lay subjectInforLive 18.1 phai chuot vao tung livetv
function newBox1(title,name,serviceName,image,titleSName,titleName,titleIma,ok,cancel)
{
	var my=this;
	this.show=function()
	{		
		
		if($("div_layer_vitual")==undefined)
		{
			var div=document.createElement("div");		
			div.id="div_layer_vitual";
			div.className="div_layer_vitual";
			document.body.appendChild(div);				
			//$("div_layer_vitual").style.display="block";
		}else
		{
			$("div_layer_vitual").style.display="block";				
		}		
		var html="";
		html+="<div class=\"form_textbox\" id=\"form_textbox\"  align='center'>";
			html+="<div class=\"form_textbox_subject\"><div class='div_title_form_new'>"+title+"</div></div>";
			
				
				html+="<div class='form_textbox_center'>"	;						
					html+="<div class='left_textbox'>"	;
						html+="<div class=\"form_textbox_input\">";				
						html+="<div class='div_name_input'>"+titleName+":</div>"		;				
							html+="<input type=\"text\" size='26'  style='border: 1px solid #dddddd;height:24;float:left;'  id=\"form_textbox_input\" value='"+serviceName+"'/>";
						html+="</div>";
						html+="<div class=\"form_textbox_input\">";							
						html+="<div class='div_image_input'>"+titleIma+":</div>"
						html+="<div  style='float:left;width:180;height: 25px;margin-top: 8px;' >"
							html+="	<iframe class='frame_upload' src=\"../upload\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>"
						html+="</div>"
							
						html+="</div>"
							
							html+="<div style='margin-top: 5px;'>"
								html+="(Click"
									html+="<img  src=\"../icon/drop.png\"></img>";
								html+=changeIma+")"	
								html+="</div>"
						html+="</div>";
							
						html+="<div class='right_textbox' style='border: 1px solid #dddddd;'>"	
							html+="<div class='div_subject_image' >";
							var chuoi =image.split("/");
							var c=chuoi[1];
							if(c=='\nemp')
							{	
								html+="<img id='image' src='../images/noimage.gif' width='150' height='150'></img>";
							}else
							{
								html+="<img id='image' src=\"../resource/images/service/"+image+"\" width='150' height='150'></img>";
							}
							html+="</div>"
						html+="</div>"
				
				html+="<div class='form_1'>";
					html+="<input type=\"button\" style='margin-left: 15px;' value='"+ok+"'  class='div_buton'id=\"form_textbox_ok\" >"
					html+="<input type=\"button\" value='"+cancel+"'  class='div_buton' id=\"form_textbox_cancel\">"
					html+="</div>"
				html+="</div>"
					
			html+="<div class='form_textbox_bottom'>"
			html+="</div>"
		html+="</div>";			
		
				
		var x=0;
		var y=0;			
		x=document.body.clientWidth+document.body.scrollLeft+document.body.scrollLeft;
		y=document.body.clientHeight+document.body.scrollTop+ document.body.scrollTop;			
		$("div_layer_vitual").innerHTML=html;
		
		$("form_textbox").style.left=(x/2)-200+"px";
		$("form_textbox").style.top=(y/2)-150+"px";	
		
		$("form_textbox_ok").onclick=function(){
			
			var value=$("form_textbox_input").value;			
			var image=$("image").src;
			my.accept(value,image);
		};
		$("form_textbox_cancel").onclick=my.cancel;	
		
	}
function trimAll(sString)
{
while (sString.substring(0,1) == ' ')
{
sString = sString.substring(1, sString.length);
}
while (sString.substring(sString.length-1, sString.length) == ' ')
{
sString = sString.substring(0,sString.length-1);
}
return sString;
} 
	this.accept=function(t)
	{
		my.hide();
	}
	this.cancel=function()
	{
		my.hide();
	}
	this.hide=function()
	{
		$("div_layer_vitual").style.display="none";
	}	
}
function Waiting()
{
	var time=5000;
	var my=this;	
	this.show=function(_time)
	{
		var div=null;
		if($("div_bg_waiting")==undefined)
		{
			div=document.createElement("div");		
			div.id="div_bg_waiting";
			div.className="div_layer_vitual";
			document.body.appendChild(div);
			var html="<div class='div_waiting' id='div_waiting'>";
			html+="</div>";
			div.innerHTML=html;
		}else
		{
			$("div_bg_waiting").style.display="block";
		}			
		setCenterDIV("div_waiting");
		
		if(_time!=null)
		{
			var t=setTimeout(function(){my.hide();},_time);
		}
	}
	this.hide=function()
	{
		$("div_bg_waiting").style.display="none";
	}
}
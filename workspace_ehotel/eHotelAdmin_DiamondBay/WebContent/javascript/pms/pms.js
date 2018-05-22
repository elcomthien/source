var rte=new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
function pmsBoxSub(title,value,image1,image2)
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
		} else {
			$("div_layer_vitual").style.display="block";
		}
		var html="";
		var link1 = linksaveimage + image1;
		var link2 = linksaveimage + image2;
		html+="<div class=\"form_textbox\" id=\"form_textbox\" style='height:330;'>";
		html+="<div class='div_Title_detail' >"+title+"</div>";
			html+="<div class='form_textbox_center' style='width: 100%;'>";
				html+="<div style='width: 100%;'>";
				html+="<div class='pms_textbox_input' style='margin-top: 8px;'>";
					html+="<div class='pms_name_input' >"+langpms.name+":</div>";
					html+="<input type=\"text\" size='26' style='border: 1px solid #dddddd;height:24;float:left;width:220;margin-top: 5px;' id=\"form_textbox_input\" value='"+value+"'/>";
				html+="</div>";
				html+="<div class='pms_textbox_input'>";
					html+="<div class='pms_name_input'>"+langpms.image+":</div>";
					html+="<div  style='float:left;width:220;height: 25px;margin-top: 8px;'>";
						html+="	<iframe class='frame_upload' src=\"../upload\?image=image1\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>";
					html+="</div>";
				html+="</div>";
					html+="<div class='pms_textbox_input'>";
						html+="<div class='pms_name_input' style='width:120;'>"+langpms.Background+":</div>";
						html+="<div style='float:left;width:220;height: 25px;margin-top: 8px;' >";
								html+="	<iframe class='frame_upload' src=\"../upload\?image=image2\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>";
						html+="</div>";
					html+="</div>";
				html+="<div style='width: 100%;margin-top:20px;float:left;margin-left: 20px;'>";
					html+="<div class='div_image'>";
					if(image1.length>0)
					{
						html+="<img id='image1' src=\""+link1+"\" width='160' height='150'></img>";
					}else
					{
						html+="<img id='image1' src=\"../resource/images/noimage.gif\" width='160' height='150'></img>";
					}
					html+="</div>";
					html+="<div class='div_image'>";
						if(image2.length>0) {
							html+="<img id='image2' src=\""+link2+"\" width='170' height='150'></img>";
						} else {
							html+="<img id='image2' src=\"../resource/images/noimage.gif\" width='170' height='150'></img>";
						}
					html+="</div>";
				html+="</div>";
				html+="<div style='float:left;width: 100%;margin-top: 8px;' align='center'>";
					html+="<div style='width:150;'>";
						html+="<input type=\"button\" style='margin-left: 15px;' value='"+langMain.ok+" ' class='div_buton'id=\"form_textbox_ok\" >";
						html+="<input type=\"button\" value="+langMain.cancel+" class='div_buton' id=\"form_textbox_cancel\">";
					html+="</div>";
					html+="</div>";
				html+="</div>";
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
function changeSubject(url1,url2,url3)
{
	var my=this;
	this.obj=null;
	var layer=new layer_vitual();
	this.load=function(_obj)
	{
		my.obj=_obj;
		my.show();
	}
	this.show=function()
	{
		layer.show();
		var html="";
			html+="<div class=\"form_change_subject\" id=\"form_change_subject\">";
			html+="<div class='div_Title_song' align=\"center\">"+langpms.changeSubject;
			html+="</div>";
			html+="<div class='change_subject_top'>";
			html+="</div>";
			html+="<div class='change_subject_center'>";
				html+="<div style='float:left;width:100%;height: 200px;margin-top:10px;'>";
					html+="<div class='left_change_subject' id='left_change_subject'>";
					html+="</div>";
					html+="<div style='float:left;width:20;' align=\"center\">";
						html+="<div class='add_subject' id='add_subject'></div>";
						html+="<div class='remove_subject' id='remove_subject'></div>";
					html+="</div>";
					html+="<div class='right_change_subject' id='right_change_subject'>";
					html+="</div>";
				html+="</div>";
					html+="<div align='center' class='div_sub_buton' style='float:left;width:100%;'>";
					html+="<div style='width:160'>";
						html+="<input type=\"button\" value='"+langMain.ok+"' id=\"form_textbox_ok\" class='div_buton'/>";
						html+="<input type=\"button\" value="+langMain.cancel+" id=\"form_textbox_cancel\" class='div_buton'/>";
					html+="</div>";
					html+="</div>";
			html+="</div>";
			html+="<div class='change_subject_bottom'>";
			html+="</div>";
			html+="</div>";
			layer.addHtml(html);
			var x=0;
			var y=0;				
			x=document.body.clientWidth+document.body.scrollLeft+document.body.scrollLeft;
			y=document.body.clientHeight+document.body.scrollTop+ document.body.scrollTop;			
			$("form_change_subject").style.left=(x/2)-300+"px";
			$("form_change_subject").style.top=(y/2)-200+"px";	
			$("form_textbox_ok").onclick=function(){ my.accept();};
			$("form_textbox_cancel").onclick=my.cancel;	
			$("add_subject").onclick=this.addsubject;
			$("remove_subject").onclick=this.removesubject;
			my.loadleft();
			my.loadright();
	}
	this.loadleft=function()
	{
		var f=new AjaxGetXML(url1);
		f.complet=function(tx)
		{
			var arr=new Array();
			var it=tx.getElementsByTagName("Item");
			for(var i=0;i<it.length;i++)
			{			
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;
				arr[i]=new ItemMenu(_id,_parent,_name,_image);
			}
			var html="";
				html+="<select size=\"10\" multiple=\"multiple\" id='list_select1' class='list_select_sub'>";
			for(var i=0;i<arr.length;i++)
			{
				html+="<option value='"+arr[i].Id+"'>"+arr[i].Name+"</option>";
			}
			html+="</select>";
			html+="<div>"+langpms.notinsubject+"</div>";
			$("left_change_subject").innerHTML=html;
			my.eventrender1();
			return arr;
		}
	}
	this.loadright=function()
	{
		var f=new AjaxGetXML(url2);
		f.complet=function(tx)
		{
			var arr=new Array();
			var it=tx.getElementsByTagName("Item");
			for(var i=0;i<it.length;i++)
			{
				var _name=it[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
				var _id=it[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
				var _image=it[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
				var _parent=it[i].getElementsByTagName("parent")[0].childNodes[0].nodeValue;	
				arr[i]=new ItemMenu(_id,_parent,_name,_image);
			}
			var html="";
				html+="<select size=\"10\" multiple=\"multiple\" id='list_select2' class='list_select_sub'>";
			for(var i=0;i<arr.length;i++)
			{
				html+="<option value='"+arr[i].Id+"'>"+arr[i].Name+"</option>";
			}
			html+="</select>";
			html+="<div>"+langpms.CurrentSubject+"</div>";
			$("right_change_subject").innerHTML=html;
			my.eventrender1();
			return arr;
		}
	}
	this.eventrender1=function()
	{
		var select1=$("list_select1");
		select1.ondblclick=function(){my.addsubject();};
		var select2=$("list_select2");
		select2.ondblclick=function(){my.removesubject();};
	}
	this.addsubject=function()
	{
		var select1=$("list_select1");
		var select2=$("list_select2");
		if(select1.selectedIndex<0)
		{
			var albox=new alertBox();
			albox.show("Please select item !");
			return;
		}
		for(var i=0;i<select1.options.length;i++)
		{
			if (select1.options[i].selected==true)
			{
				select2.add(new Option(select1.options[i].text,select1.options[i].value));
				select1.remove(i);
				i--;
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
			albox.show("Please select item!");
			return;
		}
		for(var i=0;i<select2.options.length;i++)
		{
			if (select2.options[i].selected==true)
			{
				select1.add(new Option(select2.options[i].text,select2.options[i].value));
				select2.remove(i);
				i--;
			}
		}
	}
	this.accept=function()
	{
		var select2=$("list_select2");
		if(select2.options.length<=0)
		{
			var albox=new alertBox();
			albox.show("please select item in select 2!");
			return;
		}
		var param="";
		for(var i=0;i<select2.options.length;i++)
		{
			param+="&SubId"+i+"="+select2.options[i].value;
		}
		url3+=param;
		url3+="&r="+Math.random();
		var f=new AjaxGetXML(url3);
		f.complet=function(tx)
		{
			my.hide();
			my.complet();	
		}
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
function ItemHotel(id,title,des,status)
{
	this.Id=id;
	this.Title=title;this.Des=des,this.Status=status;
}
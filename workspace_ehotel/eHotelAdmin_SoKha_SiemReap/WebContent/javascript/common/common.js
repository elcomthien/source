function $(elementId)
{
	return document.getElementById(elementId);
}
var Common=new function()
{
	var my=this;
	this.ctrl=false;
	this.onkeydown=function(e)
	{			
		var keycode;
		if (window.event) keycode = window.event.keyCode;
		else if (e) keycode = e.which;			
		switch (keycode)
		{
		case 17:			
			my.ctrl=true;	
			setonmouse(false);
			break;
		default:
			break;
		}
		return true;
	};
	this.onkeyup=function(e)
	{
	
		try 
		{
			setonmouse(true);
			my.ctrl=false;	
		} catch (e) {
			// TODO: handle exception
		}
		
		return true;
	};
	this.setHTML=function(id,text)
	{
		$(id).innerHTML=text;
	};
	this.checkRole=function(tx)
	{
		
		if(tx==-1)
		{
			var albox=new alertBox();
			albox.show(langMain.not_role);
			return false;
		}
		return true;
	};
};
function setCenterDIV(id)
{
	var x=0;
	var y=0;				
		x=document.body.clientWidth+document.body.scrollLeft;
	if (document.all)
	{
		y=document.body.offsetHeight+document.body.scrollTop;
		
	}else
	{
		y=document.body.clientHeight+document.body.scrollTop;
	}	
	var h=$(id).offsetHeight;
	var w=$(id).offsetWidth;	
	$(id).style.left=(x-w)/2+"px";
	$(id).style.top=(y/2)-(h/2)+"px";
}
function setonmouse(state)
{	
	
	 if (document.all)
	 	{
	 		  document.onselectstart =
	 		    function () { return state; };
	 	}else
	 	{
	 		document.body.onmousedown =
	     	    function () { return state; };
	 	}
	     if (document.layers)
	     {
	 		    	  document.captureEvents(Event.MOUSEDOWN);
	 		    	  document.onmousedown =
	 		    	  function (evt) { return state; };
	 	}
}
function URLEncode (clearString)
{	 
	  return clearString;
}
function loadJSCSSFile (filename, filetype)
{
	 if (filetype=="js")
	 { //if filename is a external JavaScript file
	  var fileref=document.createElement('script');
	  //fileref.setAttribute("type","text/javascript");
	  fileref.setAttribute("src", filename);
	  fileref.setAttribute("language", "javascript");
	 }
	 
	 else if (filetype=="css"){ //if filename is an external CSS file
	  var fileref=document.createElement("link");
	  fileref.setAttribute("rel", "stylesheet");
	  fileref.setAttribute("type", "text/css");
	  fileref.setAttribute("href", filename);
	 }	 
	 if (typeof fileref!="undefined")
	  document.getElementsByTagName("head")[0].appendChild(fileref);
}
function convertToHTML(text)
{
	text=text.replace(/&lt;/gi,"<");
	text=text.replace(/&gt;/gi,">");
	text=text.replace(/&amp;/gi,"&");
	text=text.replace(/&nbsp;/gi," ");
	return text;
	
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
		   if(charCode==44||charCode==46)return true;
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   return true;
}
function createPaging(count, index) {
	count += 0.4;
	count = Math.round(count);
	var html = "";
	html += "<div class='div_page_icon_left' id='div_page_icon_left'>";
	html += "</div>";
	if (count <= 5) {

		for ( var i = 0; i < count; i++) {
			if (i == index) {
				html += "<a class='div_page_a_sel' href=\"javascript:\" id=\"" + i + "\">";
			} else {
				html += "<a class='div_page_a' href=\"javascript:\" id=\"" + i + "\">";
			}
			html += (i + 1);
			html += " </a>";
		}

	} else {

		if (index <= 2) {
			for ( var i = 0; i < 5; i++) {
				if (i == index) {
					html += "<a class='div_page_a_sel' href=\"javascript:\" id=\"" + i + "\">";
				} else {
					html += "<a class='div_page_a' href=\"javascript:\" id=\"" + i + "\">";
				}
				html += (i + 1);
				html += " </a>";
			}
		} else {
			if (index >= count - 2) {

				var b = count - 4;
				var t = Number(index);
				for ( var i = b; i <= count; i++) {
					if (i == (t + 1)) {
						html += "<a class='div_page_a_sel' href=\"javascript:\" id=\""
								+ (i - 1) + "\">";
					} else {
						html += "<a class='div_page_a' href=\"javascript:\" id=\""
								+ (i - 1) + "\">";
					}
					html += (i);
					html += " </a>";
				}
			} else {
				var b = index - 2;
				for ( var i = 0; i < 5; i++) {
					if ((i + b) == index) {
						html += "<a class='div_page_a_sel' href=\"javascript:\" id=\""
								+ (i + b) + "\">";
					} else {
						html += "<a class='div_page_a' href=\"javascript:\" id=\""
								+ (i + b) + "\">";
					}
					html += (i + 1) + b;
					html += " </a>";
				}
			}
		}
	}
	html += "<div class='div_page_icon_right' id='div_page_icon_right'>";
	html += "</div>";
	return html;
}
function eventMouse()
{
	
	var my=this;
	var div= $("div_contextMenu");
	var a=div.getElementsByTagName("a");
	for(var i=0;i<a.length;i++)
	{
		a[i].onmouseover=function()
		{		
			this.style.color="#878888";
					
		};
		a[i].onmouseout=function()
		{
			this.style.color="#A7800A";
		};
	}		
}
function initTinymce(language, w)
{
	if(language == 1) {
		language = 'vi';
	} else if(language == 3) {
		language = 'ru';
	} else {
		language = 'en';
	}
	tinyMCE.init({
		// General options
		mode : "textareas",
		theme : "advanced",
		language : language,
		plugins : "style,layer,advhr,inlinepopups,paste,fullscreen,noneditable,xhtmlxtras,advlist",

		// Theme options
		theme_advanced_buttons1 : "bold,italic,underline",/*,fontselect,fontsizeselect,forecolor, backcolor,fullscreen",*/
//		theme_advanced_buttons1 : "",/*,fontselect,fontsizeselect,forecolor, backcolor,fullscreen",*/
		theme_advanced_buttons2 : "",
		theme_advanced_buttons3 : "",
		theme_advanced_buttons4 : "",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		//theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,
		theme_advanced_source_editor_wrap : false,
		entity_encoding : "raw",

		// Example content CSS (should be your site CSS)
		content_css : "../javascript/tiny_mce/tiny_mce.css",
		
		// Style formats
		style_formats : [
			{title : 'Bold text', inline : 'b'},
			{title : 'Red text', inline : 'span', styles : {color : '#ff0000'}},
			{title : 'Red header', block : 'h1', styles : {color : '#ff0000'}},
			{title : 'Example 1', inline : 'span', classes : 'example1'},
			{title : 'Example 2', inline : 'span', classes : 'example2'},
			{title : 'Table styles'},
			{title : 'Table row 1', selector : 'tr', classes : 'tablerow1'}
		],

		formats : {
			alignleft : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'left'},
			aligncenter : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'center'},
			alignright : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'right'},
			alignfull : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'full'},
			//bold : {inline : 'span', 'classes' : 'bold'},
			//italic : {inline : 'span', 'classes' : 'italic'},
			//underline : {inline : 'span', 'classes' : 'underline', exact : true},
			//bold : {inline : 'b', styles : { fontWeight: 'b'}},
			//italic : {inline : 'i', styles : {fontStyle: 'i'}},
			//underline : {inline : 'u', styles : {textDecoration: 'u'}, exact : true},
			bold : {inline:"b", remove:"all"},
			italic : {inline:"i", remove:"all"},
			underline : {inline:"u", remove:"all"},	
			strikethrough : {inline : 'del'}
		},

		// Replace values for the template plugin
		template_replace_values : {
			username : "Some User",
			staffid : "991234"
		},
		
		width : w
	});
	
}
function initTinymceMess(language, w)
{
	if(language == 1) {
		language = 'vi';
	} else if(language == 3) {
		language = 'ru';
	} else {
		language = 'en';
	}
	tinyMCE.init({
		// General options
		mode : "textareas",
		theme : "advanced",
		language : language,
		plugins : "style,layer,advhr,inlinepopups,paste,fullscreen,noneditable,xhtmlxtras,advlist",

		// Theme options
		theme_advanced_buttons1 : "",/*,fontselect,fontsizeselect,forecolor, backcolor,fullscreen",*/
		theme_advanced_buttons2 : "",
		theme_advanced_buttons3 : "",
		theme_advanced_buttons4 : "",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		//theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,
		theme_advanced_source_editor_wrap : false,
		entity_encoding : "raw",

		// Example content CSS (should be your site CSS)
		content_css : "../javascript/tiny_mce/tiny_mce.css",
		
		// Style formats
		style_formats : [
			{title : 'Bold text', inline : 'b'},
			{title : 'Red text', inline : 'span', styles : {color : '#ff0000'}},
			{title : 'Red header', block : 'h1', styles : {color : '#ff0000'}},
			{title : 'Example 1', inline : 'span', classes : 'example1'},
			{title : 'Example 2', inline : 'span', classes : 'example2'},
			{title : 'Table styles'},
			{title : 'Table row 1', selector : 'tr', classes : 'tablerow1'}
		],

		formats : {
			alignleft : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'left'},
			aligncenter : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'center'},
			alignright : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'right'},
			alignfull : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'full'},
			//bold : {inline : 'span', 'classes' : 'bold'},
			//italic : {inline : 'span', 'classes' : 'italic'},
			//underline : {inline : 'span', 'classes' : 'underline', exact : true},
			//bold : {inline : 'b', styles : { fontWeight: 'b'}},
			//italic : {inline : 'i', styles : {fontStyle: 'i'}},
			//underline : {inline : 'u', styles : {textDecoration: 'u'}, exact : true},
			bold : {inline:"b", remove:"all"},
			italic : {inline:"i", remove:"all"},
			underline : {inline:"u", remove:"all"},	
			strikethrough : {inline : 'del'}
		},

		// Replace values for the template plugin
		template_replace_values : {
			username : "Some User",
			staffid : "991234"
		},
		
		width : w
	});
	
}
function getDataFromEditor2(id){
	var encodedInputString = $(id).value;
	if(encodedInputString != null && encodedInputString != 'null' && encodedInputString != ''){
		encodedInputString = encodeSpecialCharacter(encodedInputString);
	}
	return encodedInputString;
}
function getDataFromEditor(id)
{
	var ed1 = tinyMCE.get(id);
	if(ed1 != null){
		//alert(tinyMCE.activeEditor.getContent());
		var encodedInputString = encodeURIComponent(tinyMCE.activeEditor.getContent());
		//encodedInputString = encodedInputString.replace("+", "%2B");
		//encodedInputString = encodedInputString.replace("/", "%2F");
		return encodedInputString;
   	    //descript = encodeURI(ed1.getContent());
        //Base64.encode
    }
    return null;
}
function encode(s){
   
	s=s.replace(/&/i,"%26");
	return s;
} 
function converHTML(st)
{
	
	st=encode(st);
	var index1=st.indexOf("<");
	var index2=st.indexOf(">");
	
	while(index1>-1&&index2>-1)
	{
		var st1=st.substring(0,index1);
		var st2=st.substring(index2+1);
		st=st1+st2;
		index1=st.indexOf("<");
		index2=st.indexOf(">");
		
	}			
	return st;
	
}
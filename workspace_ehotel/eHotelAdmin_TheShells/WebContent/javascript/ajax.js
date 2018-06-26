/**
 * Create an Ajax object
 * */
function createXMLHttp() {
	if (typeof XMLHttpRequest != "undefined") {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		var aVersions = [ "MSXML2.XMLHttp.5.0", "MSXML2.XMLHttp.4.0",
				"MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp", "Microsoft.XMLHttp" ];

		for ( var i = 0; i < aVersions.length; i += 1) {
			try {
				var oXMLHttp = new ActiveXObject(aVersions[i]);
				return oXMLHttp;
			} catch (oError) {
				// Do nothing
			}
		}
	}
	throw new Error("XMLHttp object could be created.");
}

/**
 * Create an Ajax object
 * */
function getXMLHttp(){
	if (window.XMLHttpRequest){ 			
		return new XMLHttpRequest();
	}
	else if (window.ActiveXObject){
		var msxmls = ['Msxml2.XMLHTTP.5.0','Msxml2.XMLHTTP.4.0','Msxml2.XMLHTTP.3.0','Msxml2.XMLHTTP','Microsoft.XMLHTTP'];			
		$loop(msxmls,function(v){
			try	{
				return new ActiveXObject(v);
			}catch (e){}
		})			
	}
	this.errorStr = "Your browser dosen't support XMLHttpRequest object."
	if(this.callback["error_callback"]){
		this.callback["error_callback"](this.errorStr);
	}
	return null;
}

/***/
function AjaxFuncGetXML(url,func){	
	var xmlhttp;
	xmlhttp=getXMLHttp();
	xmlhttp.onreadystatechange=function(){
	if(xmlhttp.readyState==4)
	{					
		func.complet(xmlhttp.responseXML);	
	  }
	};	
	xmlhttp.open("GET",url,true);	
	xmlhttp.send(null);	
}
function AjaxGetXML(url)
{	
	var my=this;
	var xmlhttp;
	xmlhttp=getXMLHttp();
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4)
		{				
			my.complet(xmlhttp.responseXML);	
		}
	}	
	xmlhttp.open("GET",url,true);	
	xmlhttp.send(null);	
	return my;
}
/***/
function AjaxFuncGetText(url,func){	
	var xmlhttp;
	xmlhttp=getXMLHttp();	
	xmlhttp.onreadystatechange=function(){
	if(xmlhttp.readyState==4) {				
		func.complet(trimSt(xmlhttp.responseText));	
	  }
	}	
	xmlhttp.open("GET",url,true);	
	xmlhttp.send(null);	
}
function AjaxGetText(url){	
	var xmlhttp;
	var my=this;
	xmlhttp=getXMLHttp();	
	xmlhttp.onreadystatechange=function(){
	if(xmlhttp.readyState==4) {				
		my.complet(xmlhttp.responseText);	
	  }
	}	
	xmlhttp.open("GET",url,true);	
	xmlhttp.send(null);	
}
/***/
function AjaxFuncGetTextSyn(url,func){	
	var xmlhttp;
	xmlhttp=getXMLHttp();	
	xmlhttp.onreadystatechange=function(){
	if(xmlhttp.readyState==4) {				
		func.complet(trimSt(xmlhttp.responseText));	
	  }
	}	
	xmlhttp.open("GET",url,false);	
	xmlhttp.send(null);	
}
/***/
function AjaxPostText(url,params)
{	
	
	var xmlhttp;
	xmlhttp=getXMLHttp();
	var my=this;
	this.complet=function(xml)
	{
		return xmlhttp;
	};
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4)
		{				
			my.complet(xmlhttp.responseText);	
		}
	};
	xmlhttp.open("POST",url,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1;");
	xmlhttp.setRequestHeader("Content-length", params.length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(params);	
}
function AjaxPost(url,params)
{	
	
	
	
	var xmlhttp;
	var my=this;
	this.complet=function(xml)
	{
		return xmlhttp;
	};
	xmlhttp=getXMLHttp();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4)
		{		
			my.complet(xmlhttp.responseXML);	
	  }
	};
	xmlhttp.open("POST",url,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1;");
	xmlhttp.setRequestHeader("Content-length", params.length);
	xmlhttp.setRequestHeader("Connection", "close");
	xmlhttp.send(params);	
}

/***/
function funPropery(){};

/***/
function trimSt(st){	
	st=st.replace(/^\s+|\s+$/g, '') ;
	return st;
}
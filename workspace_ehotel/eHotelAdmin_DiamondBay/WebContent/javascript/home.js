function mouseover(m)
{
	var src=m.src;
	src=src.substring(0,src.lastIndexOf("."));
	src=src+"_focus.png";
	m.src=src;
}
function mouseout(m)
{
	var src=m.src;
	src=src.replace(/_focus/,"");
	m.src=src;
	
}
function gotoService(url)
{
	
	if(url.length==0)
	{
		var albox=new alertBox();
		albox.show(langMain.not_role);
		return;
	}
	url+="&t="+Math.random();
	window.location = url;
}
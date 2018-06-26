<body
	style="width: 100%; height: 100%; float: left; margin-left: 0; margin-top: 0;">

	<form enctype="multipart/form-data" name="myformUp" method="post"
		style="float: left; width: 100%; margin-top: 5px">
		<div id='fileimage'
			style="margin-top: 10px; float: left; width: 99%; border: 1px solid #dddddd; height: 20px;; background: transparent url(icon/drop.png) no-repeat right center;">
			<input type="file" name="file" id="upload2"
				onchange="loadImage(this)"
				style="width: 104%; float: left; margin-top: 0px; margin-left: 0px; padding-top: 0px; height: 22px; opacity: 0; filter: alpha(opacity :   0);"
				title="Click to upload images" />
		</div>
	</form>

</body>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%
 	String divImage="image";
 	if(request.getParameter("image")!=null)
 	{
 		divImage=request.getParameter("image").toString().trim();
 	} 	
 	String imageName="";
 	if(request.getAttribute("image")!=null)
 	imageName=request.getAttribute("image").toString();
 	String imageformlink = "";
	if(request.getAttribute("linksaveimage")!=null)
	{
		imageformlink=(String)request.getAttribute("linksaveimage") + imageName;
	}
	Properties prop = new Properties();
 	try {
 		prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
 	} catch (IOException ex) {
 		ex.printStackTrace();
 	}
 	int sizetoshow = Integer.parseInt(prop.getProperty("uploadfile.sizekb"));
 	int sizekb = Integer.parseInt(prop.getProperty("uploadfile.sizekb"))*1024;
%>

<script type="text/javascript">
var imageformlink2='<%=imageformlink%>';
var imagesName='<%=imageName%>';
var divImage='<%=divImage%>';
var sizetoshow='<%=sizetoshow%>';
var sizekb='<%=sizekb%>';
load();
function loadImage(is)
{
	var t=false;
	var im=is.value;
	var isize=is.files[0].size;
	im=im.substring(im.lastIndexOf("."));
	
	var ar=new Array(".jpg",".gif",".jpeg",".png");
	for(var i=0;i<ar.length;i++)
	{
		if(ar[i]==im)t=true;
	}
	if(!t)
	{
		alert("Only file type Image .jpg .gif .png .jpeg");		
	}else
	{	
		if (isize>sizekb) {
			alert("Image must be smaller than " + sizetoshow + " Kbytes");
		}
		else {
			document.myformUp.submit();	
		}
	}
}
function load()
{
	if(imagesName.length>0)
	{
		//document.getElementById("fileimage").innerHTML=imagesName;
		if(parent.document.getElementById(divImage)!=null)
		{
			//parent.document.getElementById(divImage).src="../resource/temp/"+imagesName;
			parent.document.getElementById(divImage).src= imageformlink2;
		}
	}
	if(imagesName.length>0)
	{
		var newImg = new Image();
		newImg.src = parent.document.getElementById("image").src;
		var curHeight = newImg.height;
		var curWidth = newImg.width;	
	}
}
</script>
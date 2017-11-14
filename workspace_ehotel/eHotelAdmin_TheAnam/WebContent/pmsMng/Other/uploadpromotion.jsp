<body
	style="height: 100%; float: left; margin-left: 0; margin-top: 0;">

	<form enctype="multipart/form-data" name="myformUp" method="post"
		style="float: left; width: 100%; margin-top: 5px">
		<div class='fileimage'
			style="margin-top: 8px; float: left; width: 99%; border: 1px solid #dddddd; height: 22px; background: transparent url(icon/drop.png) no-repeat right center;">
			<input type="file" size="42" name="file" class="upload2"
				onchange="loadImage(this)"
				style="width: 104%; float: left; margin-top: 0px; margin-left: 0px; padding-top: 0px; height: 24px; opacity: 0; filter: alpha(opacity :   0);"
				title="Click to upload images" />
		</div>
	</form>
</body>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%
String divImage = "image";
if (request.getParameter("image") != null) {
	divImage = request.getParameter("image").toString().trim();
}
String imageName = "";
if (request.getAttribute("image") != null) {
	imageName = request.getAttribute("image").toString();
}
String imageformlink = "";
if (request.getAttribute("linksaveimage") != null) {
	imageformlink = (String) request.getAttribute("linksaveimage") + imageName;
}
Properties prop = new Properties();
try {
	prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
} catch (IOException ex) {
	ex.printStackTrace();
}
	int sizetoshow = 0;
	int sizekb = 0;
	sizetoshow = Integer.parseInt(prop.getProperty("uploadfile.sizekb.pro"));
	sizekb = Integer.parseInt(prop.getProperty("uploadfile.sizekb.pro")) * 1024;
	
%>

<script type="text/javascript">
var imageformlink='<%=imageformlink%>';
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
	
	var ar=new Array(".png");
	for(var i=0;i<ar.length;i++)
	{
		if(ar[i]==im)t=true;
	}
	if(!t)
	{
		alert("Only file type image (.png)");		
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
		parent.document.getElementById("nameFile").value = imagesName;
		parent.document.getElementById("image-pro").src = imageformlink;
	}
}
</script>
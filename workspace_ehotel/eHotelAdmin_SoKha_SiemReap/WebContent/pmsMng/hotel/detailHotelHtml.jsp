<%@ include file="../../include/Paramter.jsp"%>
<%
	String link = "";
	
	if(request.getAttribute("link")!=null)
	{
		link = (String)request.getAttribute("link");
	};
 %>
<div class="form_detail_hotel" id="form_detail_hotel"
	style="height: 90%; width: 80%;">
	<div class='div_Title' id="div-title-multi" align="left">Hotel SoKha
		<button style="float: right; margin-right: 3px; margin-top: 3px; background-image: url('../icon/icon_close.png'); width: 24px; height: 24px;"
			id="form_textbox_close"></button>
	</div>
<!-- 	<div style="border: 1px solid #dddddd; width: 99%; height: 70%; margin-left: 0.3%"> -->
		<iframe id="iframe_hotel" src="<%=link%>" style="width: 99%; height: 90%; margin-left: 0.3%;"></iframe>
<!-- 	</div> -->
<!-- 	<br> -->
	<div align="center">
		<!-- <button id="btn_close" style="width: 150px;" onclick="close();">Close form</button> -->
		<input type="button" style="width: 100px; margin-top: 5px;"
			value="Close" id="form_textbox_cancel">
	</div>
</div>
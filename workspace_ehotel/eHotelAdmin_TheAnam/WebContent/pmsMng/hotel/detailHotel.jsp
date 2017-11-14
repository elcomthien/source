<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eImage"%>
<%
	String name="";
	String image="noimage.gif";
	String des="";
	eImage item=null;
	int status=0;
	
	if(request.getAttribute("eImage")!=null)
	{
		item=(eImage)request.getAttribute("eImage");
		name=item.getName();
		des=item.getDef();
		image=item.getUrlImage();
		status=item.getInvisible();		
		
	};
 %>

<!-- <div class="form_detail_hotel" id="form_detail_hotel" style="width: 950px; height: 580px;"> -->
<div class="form_detail_hotel" id="form_detail_hotel" style="width: 950px; height: 610px;">

	<div class='div_Title' align="left"><%=readerLang.getContent("Hotel")%>
	<button style="float: right; margin-right: 3px; margin-top: 3px; background-image: url('../icon/icon_close.png'); width: 24px; height: 24px;" id="form_textbox_close"></button>
	</div>
	<div style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 930px; height: 540; margin-top: 10px;">
	<iframe id="iframe_hotel" src="" style="width: 100%; height: 100%;"></iframe>
	</div>
	<br>
	<div align="center">
	<!-- <button id="btn_close" style="width: 150px;" onclick="close();">Close form</button> -->
	<input type="button" style="width: 100px; margin-top: 5px;" value="Close" id="form_textbox_cancel" >
	</div>
	
</div>
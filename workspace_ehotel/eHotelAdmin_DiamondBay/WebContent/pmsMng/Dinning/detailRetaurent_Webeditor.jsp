<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eImage"%>

<!-- <div class="form_detail_hotel" id="form_detail_hotel" style="width: 950px; height: 580px;"> -->
<div class="form_detail_dinning" id="form_detail_dinning" style="width: 950px; height: 610px;">

	<div class='div_Title' align="left"><%=readerLang.getContent("restaurant")%>
	</div>
	<div style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 930px; height: 540; margin-top: 10px;">
	<iframe id="iframe_hotel" src="" style="width: 100%; height: 100%;"></iframe>
	</div>
	<br>
	<div align="center">
	<!-- <button id="btn_close" style="width: 150px;" onclick="close();">Close form</button> -->
	<input type="button" style="width: 100px" value="<%=readerLang.getContent("Cancel") %>" id="form_textbox_cancel" >
	</div>
	
</div>
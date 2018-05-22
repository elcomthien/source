<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eImage"%>
<%@page import="ehotel.domain.pms.ePromotion"%>
<%@page import="ehotel.domain.pms.eExchangeRate"%>
<%@page import="ehotel.domain.pms.eUrlAirline"%>
<%
	String name="";
	String image="noimage.gif";
	String icon="noimage.gif";
	String url="";
	eUrlAirline item=null;	
	int status=0;
	if(request.getAttribute("Item")!=null)
	{
		item=(eUrlAirline)request.getAttribute("Item");
		url=item.getUrlLink();
		name=item.getName();
		image=item.getUrlImage();
	}
 %>
<div class="form_detail_url" id="form_detail_url" style="height: 300">
	<div class='div_Title_detail' align="left"><%=readerLang.getContent("urllink") %>
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 570px; margin-top: 20px;">
		<div style="float: left; width: 100%; height: 240px;">
			<div style="float: left; width: 430px;">

				<div style="width: 100%; float: left;" align="left">
					<div
						style="float: left; width: 70; margin-top: 5px; text-align: right; color: #878888; font-weight: 600"><%=readerLang.getContent("Name")%>:
					</div>
					<input type="text" size='60'
						style='border: 1px solid #dddddd; height: 24; float: left; width: 320; margin-top: 10px; margin-left: 20px;'
						id="name" value='<%=name %>' />
				</div>
				<div style="width: 100%; float: left; margin-top: 8px;" align="left">
					<div
						style="float: left; width: 70; margin-top: 5px; text-align: right; color: #878888; font-weight: 600"><%=readerLang.getContent("Image")%>:
					</div>
					<div
						style="width: 320px; float: left; height: 30px; margin-top: 5px; margin-left: 20px;">
						<iframe class='frame_upload' src="../upload?image=image"
							style='float: left;' valign="top" align="left" scrolling="no"
							frameborder='0' width="100%" height="40px"></iframe>
					</div>
				</div>
				<div style="width: 100%; float: left; margin-top: 8px;">
					<div
						style="float: left; width: 70; margin-top: 5px; text-align: right; color: #878888; font-weight: 600">URL:</div>
					<div style="float: left; width: 320;" align="left">
						<textarea rows="4" cols="5"
							style="float: left; padding-left: 0px; margin-left: 20px; width: 100%; height: 95px;"
							id="url"><%=url %></textarea>
					</div>
				</div>
			</div>
			<div
				style="float: left; width: 130px; height: 170px; border: 1px solid #dddddd; margin-top: 10px">
				<img id='image' src="<%=linksaveimage + image%>" width='100%'
					height='100%'></img>
			</div>
			<div style="width: 100%; float: left; margin-top: 20px;"
				align="center">
				<div align='left' class='div_sub_buton'>
					<input style='' type="button"
						value=' <%=readerLang.getContent("OK") %> ' id="form_textbox_ok"
						class='div_buton'> <input type="button"
						value="<%=readerLang.getContent("Cancel") %>"
						id="form_textbox_cancel" class='div_buton'>
				</div>
			</div>
		</div>
	</div>
</div>
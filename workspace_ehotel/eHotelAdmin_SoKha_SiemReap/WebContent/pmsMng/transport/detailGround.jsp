<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eImage"%>
<%@page import="ehotel.domain.pms.ePromotion"%>
<%@page import="ehotel.domain.pms.eExchangeRate"%>
<%@page import="ehotel.domain.pms.eMenu"%>
<%
	String name="";
	String image="noimage.gif";
	String icon="noimage.gif";
	String code="";
	String sell="";
	String tran="";
	String buy="";
	eMenu item=null;	
	int status=0;
	if(request.getAttribute("Item")!=null)
	{
		item=(eMenu)request.getAttribute("Item");
		name=item.getMenuName();
		image=item.getUrlImage();
		icon=item.getUrlBg();
		System.out.println("image:"+image);
		System.out.println("icon:"+icon);
	}
 %>
<div class="form_detail_url" id="form_detail"
	style="height: 350; width: 550">
	<div class='div_Title_detail' align="left"><%=readerLang.getContent("Transport") %>
	</div>
	<div
		style="float: left; margin-left: 20px; border: 1px solid #dddddd; width: 500px; margin-top: 20px;">
		<div style="float: left; width: 100%; height: 250px;">
			<div style="width: 100%; float: left; margin-top: 15px;"
				align="center">
				<div
					style="float: left; width: 70; margin-top: 5px; text-align: right; color: #878888; font-weight: 600"><%=readerLang.getContent("Name") %>:
				</div>
				<input type="text" size='24'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 340; margin-top: 5px; margin-left: 20px;'
					id="name" value='<%=name %>' />
			</div>
			<div style="width: 100%; float: left; margin-top: 15px;"
				align="center">
				<div
					style="float: left; width: 170px; height: 160px; margin-left: 85px;">
					<img id='image1' src="<%=linksaveimage + image%>" width='160'
						height='120' style="border: 1px solid #dddddd;"></img>
					<div style="color: #878888; font-weight: 600">
						<%=readerLang.getContent("Image") %>
					</div>
					<div
						style="width: 100%; float: left; height: 30px; margin-top: 5px">
						<iframe class='frame_upload' src="../upload?image=image1"
							style='float: left;' valign="top" align="left" scrolling="no"
							frameborder='0' width="100%" height="40px"></iframe>
					</div>
				</div>
				<div
					style="float: left; width: 170px; height: 160px; margin-left: 10px;">
					<img id='image2' src="<%=linksaveimage + icon%>" width='160'
						height='120' style="border: 1px solid #dddddd;"></img>
					<div style="color: #878888; font-weight: 600">
						<%=readerLang.getContent("Background") %>

					</div>
					<div style="width: 100%; float: left; height: 30px; margin-top: 5">
						<iframe class='frame_upload' src="../upload?image=image2"
							style='float: left;' valign="top" align="left" scrolling="no"
							frameborder='0' width="100%" height="40px"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="width: 100%; float: left; margin-top: 10px;" align="center">
		<div align='left' class='div_sub_buton'>
			<input style='' type="button"
				value='  <%=readerLang.getContent("OK") %> ' id="form_textbox_ok"
				class='div_buton'> <input type="button"
				value="<%=readerLang.getContent("Cancel") %>"
				id="form_textbox_cancel" class='div_buton'>
		</div>
	</div>
</div>
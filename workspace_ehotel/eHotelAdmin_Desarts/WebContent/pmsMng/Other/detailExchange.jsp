<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eImage"%>
<%@page import="ehotel.domain.pms.ePromotion"%>
<%@page import="ehotel.domain.pms.eExchangeRate"%>
<%
	String name="";
	String image="noimage.gif";
	String icon="noimage.gif";
	String code="";
	String sell="";
	String tran="";
	String buy="";
	eExchangeRate item=null;	
	int status=0;
	if(request.getAttribute("Item")!=null)
	{
		item=(eExchangeRate)request.getAttribute("Item");
		name=item.getName();
		icon=item.getUrlBg();
		image=item.getUrlImage();	
		code=item.getCode();
		sell=item.getSellRate();
		tran=item.getTransferRate();
		buy=item.getBuyRate();
	};
 %>
<div class="form_detail_prom" id="form_detail_exchange"
	style="height: 380">
	<div class='div_Title_prom' align="left"><%=readerLang.getContent("ExchangeRate") %>
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 600px; margin-top: 20px;">
		<div style="float: left; width: 100%; height: 270px;">
			<div style="width: 100%; float: left;" align="center">
				<div
					style="float: left; width: 70; margin-top: 5px; text-align: right;"><%=readerLang.getContent("Currency") %>:
				</div>
				<input type="text" size='24'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 320; margin-top: 5px; margin-left: 20px;'
					id="name" value='<%=name %>' />
				<div style="float: left; width: 80; margin-top: 5px;"><%=readerLang.getContent("Code_money") %>:
				</div>
				<input type="text" size=''
					style='border: 1px solid #dddddd; height: 24; float: left; width: 100; margin-top: 5px;'
					id="code" value='<%=code%>' />
			</div>
			<div style="width: 100%; float: left; margin-top: 15px;"
				align="center">
				<div
					style="float: left; width: 70; margin-top: 5px; text-align: right; text-indent: 20px;"><%=readerLang.getContent("Sell") %>:
				</div>
				<input type="text" size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 100; margin-top: 5px; margin-left: 20px;'
					onkeypress="return isNumberKey(event)" id="sell" value='<%=sell %>' />
				<div style="float: left; width: 120; margin-top: 5px;"><%=readerLang.getContent("Transfer") %>:
				</div>
				<input type="text" size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 100; margin-top: 5px;'
					onkeypress="return isNumberKey(event)" id="tran" value='<%=tran %>' />
				<div style="float: left; width: 80; margin-top: 5px;"><%=readerLang.getContent("Buy") %>:
				</div>
				<input type="text" size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 100; margin-top: 5px;'
					onkeypress="return isNumberKey(event)" id="buy" value='<%=buy %>' />
			</div>
			<div style="width: 100%; float: left; margin-top: 15px;"
				align="center">
				<div
					style="float: left; width: 160px; height: 160px; margin-left: 90px;">
					<img id='image1' src="<%=linksaveimage + icon%>" width='160'
						height='120' style="border: 1px solid #dddddd;"></img>
					<div>Icon</div>
					<div
						style="width: 100%; float: left; height: 30px; margin-top: 5px">
						<iframe class='frame_upload' src="../upload?image=image1"
							style='float: left;' valign="top" align="left" scrolling="no"
							frameborder='0' width="100%" height="40px"></iframe>
					</div>
				</div>
				<div
					style="float: left; width: 160px; height: 160px; margin-left: 10px;">
					<img id='image2' src="<%=linksaveimage + image%>" width='160'
						height='120' style="border: 1px solid #dddddd;"></img>
					<div>
						<%=readerLang.getContent("Image") %>
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
	<div style="width: 100%; float: left; margin-top: 20px;" align="center">
		<div align='left' class='div_sub_buton'>
			<input style='' type="button"
				value='<%=readerLang.getContent("lable_ok") %>' id="form_textbox_ok"
				class='div_buton'> <input type="button"
				value="<%=readerLang.getContent("label_cancel") %>"
				id="form_textbox_cancel" class='div_buton'>
		</div>
	</div>
</div>
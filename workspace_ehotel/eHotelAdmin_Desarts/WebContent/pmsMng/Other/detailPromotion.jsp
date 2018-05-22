<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eImage"%>
<%@page import="ehotel.domain.pms.ePromotion"%>
<%
	String name="";
	String image="noimage.gif";
	String des="";
	ePromotion item=null;
	int status=0;
	if(request.getAttribute("eItem")!=null)
	{
		item=(ePromotion)request.getAttribute("eItem");
		name=item.getName();
		des=item.getContent();
		image=item.getUrlImage();
		status=item.getInvisible();		
	};
 %>
<div class="form_detail_prom" id="form_detail_promotion"
	style="width: 590">
	<div class='div_Title_prom' align="left"><%=readerLang.getContent("Promotions") %>
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 570px; margin-top: 20px;">
		<div style="float: left; width: 350; height: 140px;">
			<div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input'><%=readerLang.getContent("Name") %>:
				</div>
				<input type="text" size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 220; margin-top: 5px;'
					id="name" value='<%=name %>' />
			</div>
			<div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input'><%=readerLang.getContent("Image") %>:
				</div>
				<div style="width: 220px; float: left; height: 30px;">
					<iframe class='frame_upload' src="../upload" style='float: left;'
						valign="top" align="left" scrolling="no" frameborder='0'
						width="100%" height="40px"></iframe>
				</div>
			</div>
			<div class='pms_textbox_input'>
				<div class='pms_name_input'><%=readerLang.getContent("Status") %>:
				</div>
				<input id="status" type="checkbox" size='26'
					checked="<%=(status==0?true:false)%>" style="margin-top: 10px;" />
			</div>
			<div class='pms_textbox_input'>
				<div class='pms_name_input'><%=readerLang.getContent("Description") %>:
				</div>
			</div>
		</div>
		<div
			style="float: left; width: 180px; height: 120px; border: 1px solid #dddddd; margin-left: 20px; margin-top: 5px">
			<img id='image' src="<%=linksaveimage + image%>" width='180'
				height='120'></img>
		</div>
		<div style='width: 100%; height: 200; float: left;'>
			<div style="float: left; margin-left: 10px;">
				<textarea rows="10" cols="10" name="descriptionInput"
					style="width: 550px; border: 1px solid #ddddd; margin-left: 5px; float: left;"
					id="descriptionInput">
  			 <%=des %>
  			 </textarea>
			</div>
		</div>
		<div style="width: 100%; float: left;" align="center">
			<div align='left' class='div_sub_buton'>
				<input style='' type="button"
					value=' <%=readerLang.getContent("lable_ok") %> '
					id="form_textbox_ok" class='div_buton'> <input
					type="button" value="<%=readerLang.getContent("label_cancel") %>"
					id="form_textbox_cancel" class='div_buton'>
			</div>
		</div>
	</div>
</div>
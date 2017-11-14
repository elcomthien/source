<%@page import="ehotel.domain.pms.eItem"%>
<%@page import="ehotel.domain.pms.eRestaurant"%><%@ include
	file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eImage"%>
<%
	String name="";
	String image="noimage.gif";
	String des="";
	String price="";
	String priceSmall="";
	String pricelarge="";
	String unit="";
	int status=0;	
	String[] money={"USD","VND"};
	
	if(request.getAttribute("Item")!=null)
	{
		eRestaurant item=(eRestaurant)request.getAttribute("Item");
		name=item.getName();
		image=item.getUrlImage();	
		des=item.getDef();
	}
 %> 
 <div class="form_detail_dinning" style="height: 450"
	id="form_detail_dinning">
	<div class='div_Title' align="left"><%=readerLang.getContent("restaurant")%>
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 570px; margin-top: 20px;">
		<div style="float: left; width: 350; height: 110px;">
			<div class='pms_textbox_input' style='margin-top: 2px;'>
				<div class='pms_name_input'><%=readerLang.getContent("Name") %>:
				</div>
				<input type="text" size="26"
					style="border: 1px solid #dddddd; height: 22; float: left; width: 220; margin-top: 5px;"
					id="name" value="<%=name%>" />
			</div>
			<div class='pms_textbox_input' style='margin-top: 20px;'>
				<div class='pms_name_input' style="margin-top: 0px;"><%=readerLang.getContent("Image") %>:
				</div>
				<div style="width: 220px; float: left; height: 30px;">
					<iframe class='frame_upload' src="../upload" style='float: left;'
						valign="top" align="left" scrolling="no" frameborder='0'
						width="101%" height="40px"></iframe>
				</div>
			</div>
			<div class='pms_textbox_input'
				style="height: 20px; margin-top: 10px;">
				<div class='pms_name_input' style="height: 20px;"><%=readerLang.getContent("Description") %>:
				</div>
			</div>
		</div>
		<div style="float: left; height: 120px; width: 200px;">
			<div
				style="float: left; width: 180px; height: 110px; border: 1px solid #dddddd; margin-left: 20px; margin-top: 5px;">
				<img id='image' src="<%=linksaveimage + image%>" width='180'
					height='110'></img>
			</div>
		</div>
		<div style='width: 100%; height: 220; float: left; margin-top: 10px'>
			<div style="float: left; margin-left: 10px;">
				<textarea rows="11" cols="10" name="descriptionInput"
					style="width: 550px; border: 1px solid #ddddd; margin-left: 5px; float: left;"
					id="descriptionInput"><%=des %></textarea>
			</div>
		</div>
		
	</div>
	<div style="width: 100%; float: left; margin-top: 5px;" align="center">
		<div align='left' class='div_sub_buton'>
			<input style='' type="button"
				value='<%=readerLang.getContent("OK")%>' id="form_textbox_ok"
				class='div_buton'> <input type="button"
				value="<%=readerLang.getContent("Cancel")%>"
				id="form_textbox_cancel" class='div_buton'>
		</div>
	</div>
</div> 
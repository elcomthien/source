<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eActivity"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.INSTANCEOF"%>
<%@page import="ehotel.domain.pms.eAttraction"%>
<%
String name="";
String image="noimage.gif";
String des="";
int status=0;
if(request.getAttribute("eImage")!=null)
{
	Object item=request.getAttribute("eImage");
	if(item instanceof eActivity)
	{
		name=((eActivity)item).getName();
		des=((eActivity)item).getDef();
		status=((eActivity)item).getInvisible();	
	}
	if(item instanceof eAttraction)
	{
		name=((eAttraction)item).getName();
		des=((eAttraction)item).getDef();
		status=((eAttraction)item).getInvisible();	
	}			
};
%>
<div class="form_detail_hotel" style="height: 410"
	id="form_detail_hotel">
	<div class='titleSubject' align="left"><%=readerLang.getContent("Promotions") %>
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 570px; margin-top: 20px;">
		<div style="float: left; width: 80%; height: 70px;">
			<div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input' style="width: 80px;">Name:</div>
				<input type="text" size='36'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 320; margin-top: 5px;'
					id="name" value='<%=name %>' />
			</div>
			<div class='pms_textbox_input'>
				<div class='pms_name_input'>Des:</div>
			</div>
		</div>
		<div style='width: 100%; height: 220; float: left; margin-top: 5px;'>
			<div style="width: 100%; margin-left: 10px; float: left;"
				id="description">
				<%=des%>
			</div>
		</div>
		<div class='pms_textbox_input'>
			<div class='pms_name_input'>Status:</div>
			<input id="status" type="checkbox" size='26'
				checked="<%=(status==0?true:false)%>" style="margin-top: 10px;" />
		</div>
		<div style="width: 100%; float: left;" align="center">
			<div align='left' class='div_sub_buton'>
				<input style='' type="button" value='OK' id="form_textbox_ok"
					class='div_buton'> <input type="button" value="Cancel"
					id="form_textbox_cancel" class='div_buton'>
			</div>
		</div>
	</div>
</div>
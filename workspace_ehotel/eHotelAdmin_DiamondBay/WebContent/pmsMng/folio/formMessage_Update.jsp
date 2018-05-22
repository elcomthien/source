<%@page import="java.util.List"%>
<%@page import="ehotel.admin.model.Message_Detail"%>
<%-- <%@page import="com.sun.org.apache.bcel.internal.generic.INSTANCEOF"%> --%>
<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eAttraction"%>
<%
String subject="";
String sender="";
String content="";
	if(request.getAttribute("eImage")!=null)
	{
		Object list =request.getAttribute("eImage");
		subject=((Message_Detail)list).getSubject();
		sender=((Message_Detail)list).getSender();
		content=((Message_Detail)list).getContent();
	};
 %>
<div class='form_detail_hotel' id='formmessage_update'
	style="width: 680px; height: 400px;">
	<div class='div_Title' align='left'>Send Message</div>
	<div style='float: left; margin-left: 10px; border: 1px solid #dddddd; width: 660px; height: 277px; margin-top: 20px;'>
		
		<div class='pms_textbox_input' style='margin-top: 8px;'>
			<div class='pms_name_input' style='width: 150px;'>Sender message:</div>
			<input type='text' size='26'
				style='border: 1px solid #dddddd; height: 24; float: left; width: 500px; margin-top: 5px;'
				id='messagesender' placeholder="Sender message ..." value='<%=sender %>' />
		</div>
		
		<div class='pms_textbox_input' style='margin-top: 8px;'>
			<div class='pms_name_input' style='width: 150px;'>Subject
				message:</div>
			<input type='text' size='26'
				style='border: 1px solid #dddddd; height: 24; float: left; width: 500px; margin-top: 5px;'
				id='messagesubject' placeholder="Subject message ..." value='<%=subject %>' />
		</div>
		<div class='pms_name_input' style='width: 150px;margin-top: 8px;'>Content
				message:</div>
		<div class='pms_textbox_input'
			style='margin-top: 8px; margin-bottom: 10px;overflow-x: auto;overflow-y: auto;width: 650px;height: 210px;'>
			
			<div >
			  <textarea style="width: 648px;height: 170px;" name="messagecontent_update" id="messagecontent_update" placeholder="Content message ..."><%=content %></textarea> 
         	</div>
         </div>		
	
	</div>
	<div
		style='width: 100%; float: left; margin-top: 15px; margin-bottom: 15px;'
		align='center'>
		<div align='left' class='div_sub_buton'>
			<input style='width: 80px;' type='button' value='OK' id='messok'
				class='div_buton'> <input type='button' style='width: 80px;'
				value='Cancel' id='messcancel' class='div_buton'>
		</div>
	</div>
</div>
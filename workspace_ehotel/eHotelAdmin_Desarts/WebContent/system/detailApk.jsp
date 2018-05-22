<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eAPKCode"%>
<%@page import="java.util.Vector"%>
<%
	String name= "";
	String apk = "";
	if(request.getAttribute("name")!=null)
	{
		name=(String)request.getAttribute("name");
	};
	if(request.getAttribute("apk")!=null)
	{
		apk=(String)request.getAttribute("apk");
	};
 %>
<div class="form_detail_prom" id="form_detail_apk"
	style="width: 590; height: 220px;">
	<div class='div_Title_prom' align="left"><%=readerLang.getContent("detailApk") %></div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 570px; margin-top: 20px;">
		<div style="float: left; width: 450; height: 140px;">
			<!--  <div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input'><%=readerLang.getContent("Name") %>:</div>
				<input type="text" size='26' style='border: 1px solid #dddddd;height:24;float:left;width:220;margin-top: 5px;' id="name" value='<%=name%>' readonly='readonly'/>
			</div>-->
			<div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input'><%=readerLang.getContent("action") %>:
				</div>
				<input type="text" size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 220; margin-top: 5px;'
					id="apkvalue" value='<%=apk%>' />
			</div>
			<br />
			<br />
			<br />
			<div style="width: 100%; float: left;" align="center">
				<div align='left' class='div_sub_buton'>
					<input style='' type="button"
						value='<%=readerLang.getContent("lable_ok")%>'
						id="form_textbox_ok" class='div_buton'> <input
						type="button" value="<%=readerLang.getContent("label_cancel") %>"
						id="form_textbox_cancel" class='div_buton'>
				</div>
			</div>
		</div>
	</div>
</div>
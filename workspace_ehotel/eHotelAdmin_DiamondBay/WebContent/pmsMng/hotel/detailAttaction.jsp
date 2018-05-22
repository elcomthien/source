<%@page import="ehotel.domain.pms.eActivity"%>
<%-- <%@page import="com.sun.org.apache.bcel.internal.generic.INSTANCEOF"%> --%>
<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eAttraction"%>
<%
	String name="";
	String image="noimage.gif";
	String des="";
	String addres="";
	int status=0;
	if(request.getAttribute("eImage")!=null)
	{
		
		Object item=request.getAttribute("eImage");		
		if(item instanceof eAttraction)
		{
			name=((eAttraction)item).getName();
			des=((eAttraction)item).getDef();
			status=((eAttraction)item).getInvisible();	
			addres=((eAttraction)item).getAddress();
		}			
	};
	
 %>
<div class="form_detail_hotel" style="height: 430"
	id="form_detail_hotel">

	<div class='titleSubject' align="left"><%=readerLang.getContent("Attractions") %>
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 570px; margin-top: 20px;">
		<div style="float: left; width: 550; height: 40px;">
			<div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input' style="width: 80px;"><%=readerLang.getContent("Name_guest") %>:
				</div>
				<input type="text" size='46'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 420; margin-top: 5px;'
					id="name" value='<%=name %>' />
			</div>
		</div>
		<div class='pms_textbox_input' style='margin-top: 10px'>
			<div class='pms_name_input' style="width: 80px;"><%=readerLang.getContent("Address") %>:
			</div>
			<input type="text" size='46'
				style='border: 1px solid #dddddd; height: 24; float: left; width: 420; margin-top: 5px;'
				id="addres" value='<%=addres %>' />
		</div>
		<div class='pms_textbox_input'>
			<div class='pms_name_input'><%=readerLang.getContent("Description") %>:
			</div>
		</div>
		<div style='width: 100%; height: 210; float: left; margin-top: 5px;'>
			<div style="float: left; margin-left: 10px;">
				<textarea rows="10" cols="10" name="descriptionInput"
					style="width: 550px; border: 1px solid #ddddd; margin-left: 5px; float: left;"
					id="descriptionInput">
		  			 <%=des %>
		  			 </textarea>
			</div>
		</div>


		<div style="width: 100%; float: left; margin-top: 10px;"
			align="center">
			<div align='left' class='div_sub_buton'>
				<input style='' type="button"
					value=' <%=readerLang.getContent("OK") %>  ' id="form_textbox_ok"
					class='div_buton'> <input type="button"
					value="<%=readerLang.getContent("Cancel") %>"
					id="form_textbox_cancel" class='div_buton'>
			</div>
		</div>
	</div>
</div>
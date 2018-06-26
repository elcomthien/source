<%@page import="java.util.Vector"%>
<%@page import="ehotel.abs.pms.HotelActivityPMS"%>
<%@page import="ehotel.domain.pms.eMenu"%>
<%@page import="ehotel.domain.pms.eActivity"%>
<%@page import="ehotel.domain.pms.eAttraction"%>
<%@page import="ehotel.abs.pms.LocalAttractionPMS"%>
<%@ include file="../../include/Paramter.jsp"%>
<%
	Vector<eMenu> subject=new Vector();
	int b=1;
	Object item=new Object();
	String name="";
	String subname="";
	if(request.getAttribute("Item")!=null)
	{
		item=request.getAttribute("Item");
		if(item instanceof eAttraction)
		{
			b=2;
			LocalAttractionPMS hotel=new LocalAttractionPMS();
			subject=hotel.getMenus(LangID);	
			item=(eAttraction)request.getAttribute("Item");
			name=((eAttraction)item).getName();
		}
		if(item instanceof eActivity)
		{			
			HotelActivityPMS hotel = new HotelActivityPMS();			
			subject=hotel.getMenus(LangID);	
			item=(eActivity)request.getAttribute("Item");
			name=((eActivity)item).getName();			
		}
	}
%>
<div class="formSubject" id="formSubject">
	<div class="titleSubject">
		<%=readerLang.getContent("ChangeSubject") %>
	</div>
	<div style="float: left;">
		<div style="width: 100%; float: left; margin-top: 15px;">
			<div style="width: 80%; float: left; margin-left: 20px;">
				<div style="float: left; width: 100px"><%=readerLang.getContent("Name") %>:
				</div>
				<div style="float: left; margin-left: 0px"><%=name%></div>
			</div>
		</div>
		<div style="width: 90%; float: left; margin-top: 15px;">
			<div style="width: 100px; float: left; margin-left: 20px;">
				<%=readerLang.getContent("NewSubject") %>:
			</div>
			<div style="float: left;">
				<select id="selectSubject">
					<option value="-1"><%=readerLang.getContent("select_subject") %></option>
					<%for(int i=0;i<subject.size();i++){
					out.write("<option value="+subject.get(i).getMenuId()+">");
					out.write(subject.get(i).getMenuName());				
					out.write("</option>");
				}
				%>
				</select>
			</div>
		</div>
	</div>
	<div style="width: 100%; float: left; margin-top: 15px;" align="center">
		<div style="width: 160px;">
			<input type="button" value='<%=readerLang.getContent("OK") %>'
				class='div_buton' id="pmsActi_button_ok"> <input
				type="button" value='<%=readerLang.getContent("Cancel") %>'
				class='div_buton' id="pmsActi_button_Cancel">
		</div>
	</div>
</div>
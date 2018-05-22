<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eService"%>
<%@page import="java.util.Vector"%>
<%
	String name="";
	String image="noimage.gif";
	int id = 0;
	String action="";
	Vector<eService> items=null;
	int status=0;
	if(request.getAttribute("eItem")!=null)
	{
		items = (Vector<eService>)request.getAttribute("eItem");
	};
%>
<div class="form_detail_prom" id="form_detail_order" style="width: 590">
	<div class='div_Title_prom' align="left"><%=readerLang.getContent("updateorder")%></div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 570px; margin-top: 20px; overflow-y: scroll; overflow-x: hidden;">
		<div style="float: left; width: 650; height: 340px;">
			<%
			for (int i=0; i<items.size(); i++ ) {
				eService item=items.get(i);
				if (item.getInvisble() == 0) {
		%>
			<div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input'><%=readerLang.getContent("Name") %>:
				</div>
				<input type="hidden" size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 220; margin-top: 5px;'
					name='idS' id="id<%=i%>" value='<%=item.getServiceId()%>' /> <input
					type="text" size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 220; margin-top: 5px;'
					name='nameS' id="name<%=i%>" value='<%=item.getServiceName()%>'
					readonly="readonly" />
			</div>
			<div class='pms_textbox_input' style='margin-top: 8px;'>
				<div class='pms_name_input'><%=readerLang.getContent("serviceorder") %>:
				</div>
				<input onkeydown='getChar(event,this)' class='Numeric' type="text"
					size='26'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 220; margin-top: 5px;'
					name='orderS' id="order<%=i%>" value='' />
			</div>
			<%
				}
			}
		%>
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
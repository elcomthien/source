
<%@page import="ehotel.domain.vod.Vod"%>
<%@page import="ehotel.domain.vod.Subject"%>
<%@page import="java.util.Vector"%>
<%@ include file="../../include/Paramter.jsp"%>
<%
	Vod vod=new Vod();
	Subject sub=new Subject();
	Vector<Subject> list=new Vector();
	if(request.getAttribute("VOD")!=null)
	{
		vod=(Vod)request.getAttribute("VOD");
	}
	if(request.getAttribute("SUB")!=null)
	{
		sub=(Subject)request.getAttribute("SUB");
	}
	if(request.getAttribute("LIST")!=null)
	{
		list=(Vector<Subject>)request.getAttribute("LIST");
	}
%>
<div class="formCtnSubject" id="formCtnSubject">
	<div class="titleSubject">
		<%=readerLang.getContent("ChangeSubject") %>
	</div>
	<div style="float: left; margin-top: 15px;">
		<div class="rowMovieSubject">
			<div style="width: 120px; float: left; margin-left: 10px;">
				<%=readerLang.getContent("Name") %>:
			</div>
			<div style="float: left;">
				<%=vod.getTitle() %>
			</div>
		</div>
		<div class="rowMovieSubject">
			<div style="width: 120px; float: left; margin-left: 10px;">
				<%=readerLang.getContent("CurrentSubject") %>:
			</div>
			<div style="float: left;">
				<%=sub.getName() %>
			</div>
		</div>
		<div class="rowMovieSubject">
			<div style="width: 120px; float: left; margin-left: 10px;">
				<%=readerLang.getContent("NewSubject") %>:
			</div>
			<div style="float: left;">
				<select id="selectSubject">
					<option value="-1">Select Subject</option>
					<%for(int i=0;i<list.size();i++){
					out.write("<option value="+list.get(i).getId()+">");
					out.write(list.get(i).getName());				
					out.write("</option>");
				}
				%>
				</select>
			</div>
		</div>
		<div class="rowMovieSubject" align="center">
			<div style="width: 160px;">
				<input type="button" value=' <%=readerLang.getContent("OK") %> '
					class='div_buton' id="ctn_button_ok"> <input type="button"
					value='<%=readerLang.getContent("label_cancel") %>'
					class='div_buton' id="ctn_button_Cancel">
			</div>
		</div>
	</div>
</div>
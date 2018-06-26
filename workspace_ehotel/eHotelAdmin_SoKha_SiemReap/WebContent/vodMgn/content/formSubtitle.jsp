<%@page import="ehotel.domain.vod.Vod"%>
<%@page import="ehotel.domain.vod.Subject"%>
<%@page import="java.util.Vector"%>
<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.vod.SubTitle"%>
<%
	Vod vod=new Vod();
	Vector<SubTitle> v_rs=new Vector();
	if(request.getAttribute("VOD")!=null)
	{
		vod=(Vod)request.getAttribute("VOD");		
	}
	if(request.getAttribute("SubTitle")!=null)
	{
		v_rs=(Vector<SubTitle>)request.getAttribute("SubTitle");
	}
	
%>
<div class="formSubtitle" id="formTrailer">
	<div class="titleSubject">
		<%=readerLang.getContent("Subtitle") %>:
		<%=vod.getTitle()%>
	</div>
	<div
		style="float: left; margin-left: 10px; margin-top: 10px; width: 94%; height: 150px; border: 1px solid #dddddd; overflow: hidden; overflow-y: scroll; overflow-x: scroll;">
		<ul class="ul_ftp_file" id="ul_ftp_file">

		</ul>
	</div>

	<div align="center" style="margin-top: 10px; float: left; width: 100%"
		id="divlistsub">
		<table cellspacing="1" cellpadding="1" border="0" width="94%"
			class="tableSubtitle" id="tableSubtitle">
			<tr>
				<th width="10%"><%=readerLang.getContent("No") %></th>
				<th width="50%"><%=readerLang.getContent("Title") %></th>
				<th width="20%"><%=readerLang.getContent("Language") %></th>
				<th width="20%"><%=readerLang.getContent("Delete") %></th>
			</tr>
		</table>
	</div>
	<div class="rowMovieSubject" align="center" style="margin-top: 20px;">
		<div style="width: 160px;">
			<input type="button" value=' <%=readerLang.getContent("OK") %> '
				class='div_buton' id="ctn_button_ok"> <input type="button"
				value='<%=readerLang.getContent("label_cancel") %>'
				class='div_buton' id="ctn_button_Cancel">
		</div>
	</div>
</div>
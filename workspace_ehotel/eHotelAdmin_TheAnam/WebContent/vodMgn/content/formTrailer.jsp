<%@page import="ehotel.domain.vod.Vod"%>
<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.vod.Subject"%>
<%@page import="java.util.Vector"%>
<%
	String fileName="";
	String trailer="";
	if(request.getAttribute("movie")!=null)
	{
		fileName=request.getAttribute("movie").toString();		
	}
	if(request.getAttribute("trailer")!=null)
	{
		trailer=request.getAttribute("trailer").toString();		
	}
%>
<div class="formTrailer" id="formTrailer">
	<div class="titleSubject">
		<%=readerLang.getContent("set_trailer") %>:<%=fileName%>
	</div>
	<div class="rowMovieSubject" style="overflow: hidden; width: auto;">
		<div style="float: left; margin-left: 10px;">
			<%=readerLang.getContent("Trailer") %>:
		</div>
		<div style="float: left;" id="nameTrailer">
			<%=readerLang.getContent("not_set") %>
		</div>
	</div>
	<div
		style="float: left; margin-left: 10px; width: 94%; height: 150px; border: 1px solid #dddddd; overflow: hidden; overflow-y: scroll; overflow-x: hidden;">
		<ul class="ul_ftp_file" id="ul_ftp_file">

		</ul>
	</div>

	<div class="rowMovieSubject" align="center" style="margin-top: 20px;">
		<div style="width: 160px;">
			<input type="button" value='<%=readerLang.getContent("OK") %>'
				class='div_buton' id="ctn_button_ok"> <input type="button"
				value='<%=readerLang.getContent("Cancel") %>' class='div_buton'
				id="ctn_button_Cancel">
		</div>
	</div>
</div>
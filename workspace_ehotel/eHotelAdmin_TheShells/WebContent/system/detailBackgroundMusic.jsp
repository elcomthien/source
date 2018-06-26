<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eService"%>
<%@page import="ehotel.domain.vod.Vod"%>
<%@page import="ehotel.domain.vod.Subject"%>
<%@page import="java.util.Vector"%>
<%@page import="ehotel.domain.vod.SubTitle"%>
<%
	String name="";
	String image="noimage.gif";
	int id = 0;
	String action="";
	eService item=null;
	int status=0;
	if(request.getAttribute("eItem")!=null)
	{
		item = (eService)request.getAttribute("eItem");
		action = item.getAction();
		name = item.getServiceName();
		image = item.getUrlImage();
		id = item.getServiceId();
		status = item.getInvisble();
	};
	String main = cf._main;
%>
<div class="form_detail_prom" id="form_detail_background_music" style="width: 590; height: 360px;">
	<div class='div_Title_prom' align="left">Background music by folio type infomation
	</div>
	<div style="float: left; margin-left: 10px; margin-top: 10px; width: 97%; height: 250px; border: 1px solid #dddddd; overflow: hidden; overflow-y: scroll; overflow-x: scroll;">
		<ul class="ul_ftp_file" id="ul_ftp_file_music" >
		</ul>
	</div>
	<input id="ctnaddfilenametempmusic" value="" style="display: none;" /> 
	<input id="ctnaddfilepathtempmusic" value="" style="display: none;" />
	<div style="float: left; width: 100%; text-align: left; height: 25px; margin-top: 10px;">
		<div style="float: left; width: 80px; color: #878888; margin-left: 10px;">
			File name:</div>
		<div id="fileNameMusic"
			style="width: 80%; float: left; overflow: hidden; margin-left: 3px;">No
			Media</div>
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
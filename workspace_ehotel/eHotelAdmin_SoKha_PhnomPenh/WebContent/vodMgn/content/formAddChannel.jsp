<%@page import="ehotel.domain.vod.Vod"%>
<%@page import="ehotel.domain.vod.Subject"%>
<%@page import="java.util.Vector"%>
<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.vod.SubTitle"%>

<div class="" id="formTrailer">
	<div style="float: left; margin-left: 10px; margin-top: 10px; width: 45%; height: 250px; border: 1px solid #dddddd; overflow: hidden; overflow-y: scroll; overflow-x: scroll;">
		<ul class="ul_ftp_file" id="ul_ftp_file">

		</ul>
	</div>
	<input id="ctnaddfilenametemp" value="" style="display: none;" />
	<input id="ctnaddfilepathtemp" value="" style="display: none;" />
	<div style="float: left; width: 100%; text-align: left; height: 25px; margin-top: 10px;">
		<div style="float: left; width: 80px; color: #878888; margin-left: 10px;">
					<%=readerLang.getContent("filename")%>:
		</div>
		<div id="fileName" style="width: 80%; float: left; overflow: hidden; margin-left: 3px;">No Video</div>
	</div>
	<div style="width: 350px; height: auto; float: right; margin-top: -285px;">
			<div style="float: left; margin-top: 0px;"><%=readerLang.getContent("logocn")%>
			</div>
			<div style="width: 240px; height: 150px; float: right; margin-right: 10px; border: 1px solid #dddddd;">

				<img id="image_channel" src="../resource/images/noimage.gif" width="100%"
					height="100%"></img>
			</div>
			<div class="div_row_right_ctn" style="margin-top: 20px;">
				<div class="div_left_ctnadd" style="width: 90px;">
					<%=readerLang.getContent("browselogo")%>:
				</div>
				<div style="float: right; width: 240px; margin-left: 10px; margin-right: 10px;">
					<iframe class='frame_upload' src="../upload?image=image_channel"
						style='overflow-x: hidden; overflow-y: hidden; overflow: hidden; float: left;'
						valign="top" scrolling="no" frameborder='0' width="100%"
						height="40px"></iframe>
				</div>
			</div>
			<div class="div_row_right_ctn">
				<div style="width: 100px; float: left; " class="div_left_ctnadd"><%=readerLang.getContent("channelname")%>:
				</div>
				<div style="width: 80px; float: left; margin-left: -10px;">
					<input id="div_channel_name" type="text" class="textbox_input"
						style="float: left; width: 240px; margin-left: 10;">
				</div>
			</div>
		</div>
		<div style="width: 100%; height: auto; float: left; margin-top: 10px;"
		align="center">
		<div style="width: 200px; height: 30px;">
			<input type="button" value='<%=readerLang.getContent("addchannel")%>' class='div_buton' id="ctn_button_ok" style="width: 90px;">
			<input type="button" value='<%=readerLang.getContent("reset")%>' class='div_buton' id="ctn_button_Cancel" style="width: 90px;">
		</div>
		
		<div style="width: 100%; float: left; margin-top: 10px;" align="center">
			<div id="div_process" style="width: 100%;"></div>
		</div>
	</div>
</div>
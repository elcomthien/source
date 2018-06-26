<%@page import="ehotel.domain.vod.Vod"%>
<%@page import="ehotel.domain.vod.Subject"%>
<%@page import="java.util.Vector"%>
<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.vod.SubTitle"%>

<div class="" id="formTrailer">
	<div style="float: left; margin-left: 10px; margin-top: 10px; width: 97%; height: 250px; border: 1px solid #dddddd; overflow: hidden; overflow-y: scroll; overflow-x: scroll;">
		<ul class="ul_ftp_file" id="ul_ftp_file" >
		</ul>
	</div>
	<input id="ctnaddfilenametemp" value="" style="display: none;" /> 
	<input id="ctnaddfilepathtemp" value="" style="display: none;" />
	<div style="float: left; width: 100%; text-align: left; height: 25px; margin-top: 10px;">
		<div style="float: left; width: 80px; color: #878888; margin-left: 10px;">
			File name:</div>
		<div id="fileName"
			style="width: 80%; float: left; overflow: hidden; margin-left: 3px;">No
			Media</div>
	</div>

	<div style="width: 100%; height: auto; float: left; margin-top: 10px;"
		align="center">
		<div style="width: 200px; height: 30px;">
			<input type="button" value='Add Media' class='div_buton'
				id="ctn_button_ok" style="width: 90px;"> <input
				type="button" value='Reset' class='div_buton' id="ctn_button_Cancel"
				style="width: 90px;">
		</div>

		<div style="width: 100%; float: left; margin-top: 10px;" align="center">
			<div id="div_process" style="width: 100%;"></div>
		</div>
	</div>
</div>
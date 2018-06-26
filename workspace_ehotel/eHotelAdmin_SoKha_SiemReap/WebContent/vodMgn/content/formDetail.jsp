<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.vod.Vod"%>
<%@page import="ehotel.admin.util.UtilString"%>
<%
	Vod vod=new Vod();
	if(request.getAttribute("Movie")!=null)
	{
		vod=(Vod)request.getAttribute("Movie");
	}
	String[] money={"USD","VND"};
	
	//bo sung link cho url image 17.1
	String imageformlink = "";
	//request.getScheme() + "://" + request.getServerName() + "/ePMS2.0/elcom/resources/image/media/" + vod.getPoster();
	if(request.getAttribute("linksaveimage")!=null)
	{
		imageformlink=(String)request.getAttribute("linksaveimage") + cf._movie + "/" + vod.getPoster();
	}
 %>
<div class="form_detail_Movie" id="form_detail_song"
	style="height: 470px;">
	<div class='div_Title_song' align="left"
		onmouseover="mouseMove(event,this)" id="div_Title_song"><%=readerLang.getContent("ViewVideo") %>
	</div>
	<div
		style="height: auto; float: left; margin-left: 20px; margin-top: 10px;">

		<div class='left_Title_movie'>
			<div class='div_info_movie'>
				<div class='div_name_movie'><%=readerLang.getContent("Title") %>:
				</div>
				<input type="text" id='Name'
					style="width: 240px; margin-left: 10px;" class='textbox_input'
					value="<%=vod.getTitle() %>">
			</div>
			<div class='div_info_movie'>
				<div class='div_name_movie'><%=readerLang.getContent(3) %>:
				</div>
				<input type="text" id='Actors'
					style="width: 240px; margin-left: 10px;" class='textbox_input'
					value="<%=vod.getActors() %>">
			</div>
			<div class='div_info_movie'>
				<div class='div_name_movie'><%=readerLang.getContent(4) %>:
				</div>
				<input type="text" id='Director'
					style="width: 240px; margin-left: 10px;" class='textbox_input'
					value="<%=vod.getDirector() %>">
			</div>
			<div class='div_info_movie'>
				<div class='div_name_movie'><%=readerLang.getContent("price_pms") %>:
				</div>
				<input type="text" id='div_price'
					style="margin-left: 10px; margin-top: 2px; width: 140px;"
					class='textbox_input' value="<%=vod.getCurrency() %>">
				<div style="float: left; margin-left: 10px;">
					<select id="div_money" style="width: 90px;">
						<%									
									for(int i=0;i<money.length;i++)
									{
										if(vod.getIUnit().equals(money[i]))
										{
											out.print("<option value=\""+money[i]+"\" SELECTED >"+money[i]+"</option>");
										}else
										{
											out.print("<option value=\""+money[i]+"\" >"+money[i]+"</option>");
										}
									}										
								 %>

					</select>
				</div>
			</div>

			<div class='div_info_movie'>
				<div class='div_name_movie'><%=readerLang.getContent("Image") %>:
				</div>
				<div
					style="width: 240px; float: left; height: 40px; margin-left: 10px;">
					<iframe class='frame_upload' src="../upload" style='float: left;'
						valign="top" align="left" scrolling="no" frameborder='0'
						width="100%" height="40px"></iframe>
				</div>
			</div>
		</div>
		<div class='right_Title_movie'>
			<img id='image' src="<%=imageformlink%>" width='160' height='120'></img>
		</div>
	</div>
	<div class='div_des_movie' style="margin-left: 10px;">
		<div class='div_name_movie'><%=readerLang.getContent("Description") %>:
		</div>
		<div class='div_des_textare' id="div_des_textare">
			<div style="float: left; margin-left: 10px;">
				<textarea rows="11" cols="9" name="descriptionInput"
					style="width: 485px; border: 1px solid #ddddd; margin-left: 5px; float: left;"
					id="descriptionInput">
		  				 <%= (vod.getPlot()) %>
		  			 </textarea>
			</div>
		</div>
	</div>
	<div style="width: 100%" align="center">
		<div align='left' class='div_sub_buton'>
			<input style='margin-top: 10px;' type="button"
				value=' <%=readerLang.getContent("OK") %>' id="form_textbox_ok"
				class='div_buton'> <input type="button" style='margin-top: 10px;'
				value="<%=readerLang.getContent("label_cancel") %>"
				id="form_textbox_cancel" class='div_buton'>
		</div>
	</div>
</div>
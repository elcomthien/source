
<%@page import="ehotel.domain.pms.eAdvertise"%>
<%@page import="java.util.Vector"%>
<%@page import="ehotel.abs.pms.AdvertisePMS"%>
<%@ include file="../../include/Paramter.jsp"%>
<%
	String name="";
	String image="noimage.gif";
	String icon="noimage.gif";
	String code="";
	String sell="";
	String tran="";
	String buy="";
	eAdvertise item=null;
	Vector<String> list = new Vector();
	AdvertisePMS  ehotel = new AdvertisePMS();
	list=ehotel.getAdvTypeList(LangID);
	int status=0;
	String type="";
	
	if(request.getAttribute("Item")!=null)
	{
		item=(eAdvertise)request.getAttribute("Item");
		name=item.getName();
		icon=item.getUrlBg();
		image=item.getUrlImage();	
		type=item.getType();			
	};
 %>

<div class="form_detail_prom" id="form_detail_adv"
	style="height: 370; width: 525">

	<div class='div_Title_prom' align="left"><%=readerLang.getContent("Advertise") %>
	</div>
	<div
		style="float: left; margin-left: 10px; border: 1px solid #dddddd; width: 500px; margin-top: 20px;">
		<div style="float: left; width: 100%; height: 300px;">
			<div style="width: 100%; float: left;" align="center">
				<div
					style="float: left; width: 70; margin-top: 5px; text-align: right; font-weight: 600"><%=readerLang.getContent("Name") %>:
				</div>
				<input type="text" size='24'
					style='border: 1px solid #dddddd; height: 24; float: left; width: 330; margin-top: 5px; margin-left: 20px;'
					id="name" value='<%=name %>' />
			</div>

			<div style="width: 100%; float: left; margin-top: 10px;" align="left">
				<div
					style="float: left; width: 70; margin-top: 5px; text-align: right; font-weight: 600"><%=readerLang.getContent("type") %>:
				</div>
				<div style="float: left; margin-left: 20">
					<select id="select">
						<option value='-1'>Select type</option>
						<%
							for(int i=0;i<list.size();i++)
							{
								if(type.equals(list.get(i)))
								{
								out.print("<option value="+i+" selected=\"selected\" >");
								}else
								{
								out.print("<option value="+i+"  >");
								}								
																
								out.print(list.get(i));
								out.print("</option>");
							}
						 %>
					</select>
				</div>
			</div>

			<div style="width: 100%; float: left; margin-top: 15px;"
				align="center">
				<div
					style="float: left; width: 160px; height: 160px; margin-left: 90px;">
					<img id='image1' src="<%=linksaveimage + icon%>" width='160'
						height='120' style="border: 1px solid #dddddd;"></img>
					<div>Icon</div>
					<div
						style="width: 100%; float: left; height: 30px; margin-top: 5px">
						<iframe class='frame_upload' src="../upload?image=image1"
							style='float: left;' valign="top" align="left" scrolling="no"
							frameborder='0' width="100%" height="40px"></iframe>
					</div>

				</div>
				<div
					style="float: left; width: 160px; height: 160px; margin-left: 10px;">
					<img id='image2' src="<%=linksaveimage + image%>" width='160'
						height='120' style="border: 1px solid #dddddd;"></img>
					<div>
						<%=readerLang.getContent("Image") %>
					</div>
					<div style="width: 100%; float: left; height: 30px; margin-top: 5">
						<iframe class='frame_upload' src="../upload?image=image2"
							style='float: left;' valign="top" align="left" scrolling="no"
							frameborder='0' width="100%" height="40px"></iframe>
					</div>

				</div>

			</div>

			<div style="width: 100%; float: left; margin-top: 20px;"
				align="center">
				<div align='left' class='div_sub_buton'>
					<input style='' type="button"
						value='<%=readerLang.getContent("lable_ok") %>'
						id="form_textbox_ok" class='div_buton'> <input
						type="button" value="<%=readerLang.getContent("label_cancel") %>"
						id="form_textbox_cancel" class='div_buton'>
				</div>
			</div>
		</div>
	</div>
</div>
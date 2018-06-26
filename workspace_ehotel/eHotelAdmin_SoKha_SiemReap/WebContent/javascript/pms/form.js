function formSubjectPMS(title, value, image1, image2) {
	var my = this;
	this.show = function() {
		if ($("div_layer_vitual") == undefined) {
			var div = document.createElement("div");
			div.id = "div_layer_vitual";
			div.className = "div_layer_vitual";
			document.body.appendChild(div);
		} else {
			$("div_layer_vitual").style.display = "block";
		}
		var html = "";
		var link1 = linksaveimage + image1;
		var link2 = linksaveimage + image2;
		html += "<div class=\"form_textbox\" id=\"form_textbox\" style='height:310px; width: 320px;'>";
		html += "<div class='div_Title_detail' >" + title + "</div>";
		html += "<div class='form_textbox_center' style='width: 100%;'>";
		html += "<div style='width: 100%;'>";
		html += "<div class='pms_textbox_input' style='margin-top: 8px;'>";
		html += "<div class='pms_name_input' style='width: 70px; margin-top: 8px;'>" + langpms.name + ":</div>";
		html += "<input type=\"text\" size='26' style='border: 1px solid #dddddd;height:24;float:left;width:220;margin-top: 5px;'  id=\"form_textbox_input\" value='"
				+ value + "'/>";
		html += "</div>";
		html += "<div class='pms_textbox_input'>";
		html += "<div class='pms_name_input' style='width: 70px; margin-top: 8px;'>" + langpms.image + ":</div>";
		html += "<div style='float:left;width:220;height: 25px;margin-top: 8px;'>";
		html += "	<iframe class='frame_upload' src=\"../upload\?image=image1\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>";
		html += "</div>";
		html += "</div>";
		html += "<div class='pms_textbox_input' style='display: none;'>";
		html += "<div class='pms_name_input' style='width:120;'>"
				+ langpms.Background + ":</div>";
		html += "<div style='float:left;width:220;height: 25px;margin-top: 8px;' >";
		html += "	<iframe class='frame_upload' src=\"../upload\?image=image2\" style='overflow-x: hidden;overflow-y: hidden;overflow: hidden;' valign=\"top\" scrolling=\"no\" frameborder='0' width=\"100%\" height=\"40px\" ></iframe>";
		html += "</div>";
		html += "</div>";
		html += "<div style='width: 100%;margin-top:20px;float:left;margin-left: 20px;'>";
		html += "<div class='div_image' style='margin-left: 60px;'>";
		if (image1.length > 0) {
			html += "<img id='image1' src=\"" + link1
					+ "\" width='160' height='150'></img>";
		} else {
			html += "<img id='image1' src=\"../resource/images/noimage.gif\" width='160' height='150'></img>";
		}
		html += "</div>";
		html += "<div class='div_image' style='display: none;'>";
		if (image2.length > 0) {
			html += "<img id='image2' src=\"" + link2
					+ "\" width='170' height='150'></img>";
		} else {
			html += "<img id='image2' src=\"../resource/images/noimage.gif\" width='170' height='150'></img>";
		}
		html += "</div>";
		html += "</div>";
		html += "<div style='float:left;width: 100%; margin-top: 15px;' align='center'>";
		html += "<div style='width:150;'>";
		html += "<input type=\"button\" style='margin-left: 15px;' value='"
				+ langMain.ok + " ' class='div_buton'id=\"form_textbox_ok\" >";
		html += "<input type=\"button\" value=" + langMain.cancel
				+ " class='div_buton' id=\"form_textbox_cancel\">";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		var x = 0;
		var y = 0;
		x = document.body.clientWidth + document.body.scrollLeft
				+ document.body.scrollLeft;
		y = document.body.clientHeight + document.body.scrollTop
				+ document.body.scrollTop;
		$("div_layer_vitual").innerHTML = html;
		$("form_textbox").style.left = (x / 2) - 200 + "px";
		$("form_textbox").style.top = (y / 2) - 150 + "px";
		$("form_textbox_ok").onclick = function() {
			var value = $("form_textbox_input").value;
			var image1 = $("image1").src;
			var image2 = $("image2").src;
			my.accept(value, image1, image2);
		};
		$("form_textbox_cancel").onclick = my.cancel;
	};
	this.accept = function(t) {
		my.hide();
	};
	this.cancel = function() {
		my.hide();
	};
	this.hide = function() {
		$("div_layer_vitual").style.display = "none";
	};
}
<%@ include file="../../include/Paramter.jsp"%>
<div>
	<div class="form_addContent">

		<div style="width: 100%; height: 25px; float: left; margin-top: 10px;">
			<div
				style="float: left; width: 300px; cursor: pointer; text-align: left;">
				<div style="float: left; margin-left: 5px">
					<%=readerLang.getContent("Subject") %>:
				</div>
				<div
					style="float: left; text-decoration: underline; color: #A7800A;"
					id="div_drop_sub1"></div>
			</div>
			<div
				style="float: left; width: 300px; cursor: pointer; text-align: left; margin-left: 50px;">
				<div style="float: left; margin-left: 5px">
					<%=readerLang.getContent("Subject") %>:
				</div>
				<div style="float: left; color: #A7800A;" id="div_drop_sub2">
				</div>
			</div>
		</div>

		<div style="width: 670px; height: auto;">
			<div class="left_form_addContent">
				<div id="tableCtn" class="tableContainer"></div>
				<div style="color: #878888;" title="Helo">
					(<%=readerLang.getContent("click_add") %>
					)
				</div>
			</div>
			<div style="float: left; padding-left: 10px">
				<div class="add_subject_src" id="add_subject"
					onclick="addsubject();"></div>
				<div class="remove_subject_src" id="remove_subject"
					onclick="removesubject();"></div>
			</div>
			<div class="right_form_addContent"">
				<div id="tableSrv" class="tableContainer"></div>
				<div style="color: #878888;">
					(<%=readerLang.getContent("click_remove") %>
					)
				</div>
			</div>
		</div>
	</div>
</div>
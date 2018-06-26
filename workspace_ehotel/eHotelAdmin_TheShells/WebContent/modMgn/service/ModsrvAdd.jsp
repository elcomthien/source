<%@ include file="../../include/Paramter.jsp"%>
<div>
	<div class="form_addContent">
		<div style="width: 100%; height: 30px;">
			<div
				style="float: left; width: 330px; cursor: pointer; margin-left: 10px; margin-top: 10px;">
				<div style="float: left; font-weight: 800"><%=readerLang.getContent("ctn_subject") %>:
				</div>
				<div style="float: left; color: #A7800A" id="div_drop_sub1">Action</div>
			</div>
			<div style="float: left; width: 300px; margin-top: 10px;">
				<div style="float: left; font-weight: 800; margin-left: 15px"><%=readerLang.getContent("srv_subject") %>:
				</div>
				<div style="float: left; color: #A7800A" id="div_drop_sub2">Action</div>
			</div>
		</div>
		<div style="width: 670px; height: auto;">
			<div class="left_form_addContent" style="height: 260px">
				<div id="tableCtn" class="tableContainer"></div>
				<div style="color: #878888;" title="Helo">
					(<%=readerLang.getContent("mod_toservice") %>
					)
				</div>
			</div>
			<div style="float: left; padding-left: 10px">
				<div class="add_subject_src" id="add_subject"
					onclick="addsubject();"></div>
				<div class="remove_subject_src" id="remove_subject"
					onclick="removesubject();"></div>
			</div>
			<div class="right_form_addContent" style="height: 260px;">
				<div id="tableSrv" class="tableContainer"></div>
				<div style="color: #878888;">
					(<%=readerLang.getContent("click_remove") %>)
				</div>
			</div>
		</div>
	</div>
</div>
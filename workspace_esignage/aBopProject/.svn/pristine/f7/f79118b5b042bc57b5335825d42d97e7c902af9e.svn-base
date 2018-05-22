<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div id="header">
	<div id='cssmenu' class="ssUser" data-creator="<s:property value='%{#session.USER.creator}'/>" data-parent="<s:property value='%{#session.USER.parentCreator}'/>">
		<ul>
			<s:set value="false" var="SUPER_ADMIN"></s:set>
			<s:set value="false" var="ADMIN"></s:set>
			<s:set value="false" var="USER_VIEW"></s:set>
			<s:set value="false" var="USER_EDIT"></s:set>
			<s:if test='%{#session.USER.role == "SUPER_ADMIN"}'>
				<s:set value="true" var="SUPER_ADMIN"></s:set>
				</s:if>
				<s:if test='%{#session.USER.role == "ADMIN"}'>
					<s:set value="true" var="ADMIN"></s:set>
				</s:if>
				<s:elseif  test='%{#session.USER.role == "USER_VIEW"}'>
					<s:set value="true" var="USER_VIEW"></s:set>
				</s:elseif>
				<s:elseif test='%{#session.USER.role == "USER_EDIT"}'>
					<s:set value="true" var="USER_EDIT"></s:set>
				</s:elseif>
			<s:if test='#USER_VIEW == true'>
				<li id="menu-layout" class='has-sub menu-main'><a href='javascript:void(0)'>Thiết Kế</a>
					<ul>
						<s:url action="player" var="urltag" />
						<li id='sub-menu'><a href="${urltag}" cmd='100'>Player</a></li>
						<s:url action="layout" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' cmd='101'>Bố Cục</a></li>
						<!--<s:url action="content" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' >NỘI DUNG</a></li>-->
						<s:url action="schedule" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' id='schedule' cmd='102'>Lập Lịch</a></li>
					</ul>
				</li>
			</s:if>
			<s:if test='#USER_EDIT == true'>
				<li id="menu-content" class='menu-main'><a href='javascript:void(0)'>Nội Dung</a>
					<ul>
						<s:url action="text" var="urltag" />
						<li id='content-text'><a href='${urltag}' cmd='103'>Nội Dung Text</a></li>
						<s:url action="media" var="urltag" />
						<li id='content-media'><a href='${urltag}' cmd='104'>Nội Dung Media</a></li>
						<s:url action="background" var="urltag" />
						<li id='content-background'><a href='${urltag}' cmd='105'>Nội Dung Background</a></li>
						<s:url action="web" var="urltag" />
						<li id='content-web'><a href='${urltag}' cmd='106'>Nội Dung Web</a></li>
						<s:url action="slide" var="urltag" />
						<li id='content-slide'><a href='${urltag}' cmd='107'>Slide Image</a></li>
						<s:url action="qms" var="urltag" />
						<li id='content-qms'><a href='${urltag}' cmd='108'>QMS</a></li>
					</ul>
				</li>
				<li id="menu-layout" class='has-sub menu-main'><a href='javascript:void(0)'>Thiết Kế</a>
					<ul>
						<s:url action="player" var="urltag" />
						<li id='sub-menu'><a href="${urltag}" cmd='100'>Player</a></li>
						<s:url action="layout" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' cmd='101'>Bố Cục</a></li>
						<!--<s:url action="content" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' >NỘI DUNG</a></li>-->
						<s:url action="schedule" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' id='schedule' cmd='102'>Lập Lịch</a></li>
					</ul>
				</li>
			</s:if>
			<s:if test='#ADMIN == true'>
				<li id="menu-content" class='menu-main'><a href='javascript:void(0)'>Nội Dung</a>
					<ul>
						<s:url action="text" var="urltag" />
						<li id='content-text'><a href='${urltag}' cmd='103'>Nội Dung Text</a></li>
						<s:url action="media" var="urltag" />
						<li id='content-media'><a href='${urltag}' cmd='104'>Nội Dung Media</a></li>
						<s:url action="background" var="urltag" />
						<li id='content-background'><a href='${urltag}' cmd='105'>Nội Dung Background</a></li>
						<s:url action="web" var="urltag" />
						<li id='content-web'><a href='${urltag}' cmd='106'>Nội Dung Web</a></li>
						<s:url action="slide" var="urltag" />
						<li id='content-slide'><a href='${urltag}' cmd='107'>Slide Image</a></li>
						<s:url action="qms" var="urltag" />
						<li id='content-qms'><a href='${urltag}' cmd='108'>QMS</a></li>
					</ul>
				</li>
				
				<li id="menu-layout" class='has-sub menu-main'><a href='javascript:void(0)'>Thiết Kế</a>
					<ul>
						<s:url action="player" var="urltag" />
						<li id='sub-menu'><a href="${urltag}" cmd='100'>Player</a></li>
						<s:url action="layout" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' cmd='101'>Bố Cục</a></li>
						<!--<s:url action="content" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' >NỘI DUNG</a></li>-->
						<s:url action="schedule" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' id='schedule' cmd='102'>Lập Lịch</a></li>
					</ul>
				</li>
				<li id="menu-user" class='menu-main'><a href='javascript:void(0)'>Người Dùng</a>
					<ul>
						<s:url action="user" var="urltag" />
						<li><a href='${urltag}'>Quản Lý Người Dùng</a></li>
					</ul>
				</li>
				<li id="menu-system" class='menu-main'><a href='javascript:void(0)'>Hệ Thống</a>
					<ul>
						<s:url action="config" var="urltag" />
						<li id='system-config'><a href='${urltag}' cmd='111'>Cấu Hình FTP</a></li>
	<%-- 					<s:url action="test" var="urltag" /> --%>
	<%-- 					<li id='system-test'><a href='${urltag}'>Test</a></li> --%>
					</ul>
				</li>
			</s:if>
			<s:if test='#SUPER_ADMIN == true'>
				<li id="menu-content" class='menu-main'><a href='javascript:void(0)'>Nội Dung</a>
					<ul>
						<s:url action="text" var="urltag" />
						<li id='content-text'><a href='${urltag}' cmd='103'>Nội Dung Text</a></li>
						<s:url action="media" var="urltag" />
						<li id='content-media'><a href='${urltag}' cmd='104'>Nội Dung Media</a></li>
						<s:url action="background" var="urltag" />
						<li id='content-background'><a href='${urltag}' cmd='105'>Nội Dung Background</a></li>
						<s:url action="web" var="urltag" />
						<li id='content-web'><a href='${urltag}' cmd='106'>Nội Dung Web</a></li>
						<s:url action="slide" var="urltag" />
						<li id='content-slide'><a href='${urltag}' cmd='107'>Slide Image</a></li>
						<s:url action="qms" var="urltag" />
						<li id='content-qms'><a href='${urltag}' cmd='108'>QMS</a></li>
					</ul>
				</li>
				
				<li id="menu-layout" class='has-sub menu-main'><a href='javascript:void(0)'>Thiết Kế</a>
					<ul>
						<s:url action="player" var="urltag" />
						<li id='sub-menu'><a href="${urltag}" cmd='100'>Player</a></li>
						<s:url action="layout" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' cmd='101'>Bố Cục</a></li>
						<!--<s:url action="content" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' >NỘI DUNG</a></li>-->
						<s:url action="schedule" var="urltag" />
						<li id='sub-menu'><a href='${urltag}' id='schedule' cmd='102'>Lập Lịch</a></li>
					</ul>
				</li>
				<%-- <li id="menu-user" class='menu-main'><a href='javascript:void(0)'>Người Dùng</a>
					<ul>
						<s:url action="user" var="urltag" />
						<li><a href='${urltag}'>Quản Lý Người Dùng</a></li>
					</ul>
				</li> --%>
				<li id="menu-system" class='menu-main'><a href='javascript:void(0)'>Hệ Thống</a>
					<ul>
						<s:url action="config" var="urltag" />
						<li id='system-config'><a href='${urltag}' cmd='111'>Cấu Hình FTP</a></li>
	<%-- 					<s:url action="test" var="urltag" /> --%>
	<%-- 					<li id='system-test'><a href='${urltag}'>Test</a></li> --%>
					</ul>
				</li>
			</s:if>
			
			<li style='float: right;'>
				<a href='http://<s:property value='%{#session.USER.host}'/>:<s:property value='%{#session.USER.port}'/>/eSmileMobifone/esmile-staff-rate.elcom' class="userlogin" data-role="<s:property value='%{#session.USER.role}'/>" data-user="<s:property value='%{#session.USER.creator}'/>">eSmile <i class="fa fa-arrow-circle-o-left"></i></a>
			</li>
			
		</ul>
		
	</div>
	<!-- end #menu -->
	<div id="search">
		<!-- <form method="get" action="">
				<fieldset>
				<input type="text" name="s" id="search-text" size="15" />
				<input type="submit" id="search-submit" value="Search" />
				</fieldset>
			</form> -->
	</div>
	<!-- end #search -->
	
</div>

<div class="dialog-view-info" style="display: none">
	<div class="info-detail-left-dialog-view-info">
		<form action="" method="post" class="ui-form" onclick="return false">
			<div class='error-view-info'></div>
			<label> 
				<span>Tên Đăng Nhập :</span> 
				<input readonly="readonly" id="user-username-view-info" type="text" /> 
			</label> 

			<label> 
				<span>Trạng Thái :</span> 
				<input readonly="readonly" id="user-status-view" type="text" /> 
			</label>
			<label style='margin: 0px 23px 5px 5px; text-align: right'> 
				<span></span> 
				<a href='javascript:void(0)' onclick="return changePassClick()" style='text-decoration: none;'>Đổi mật khẩu</a>
			</label>
			<div class='change-pass' style='display: none'>
				<label> 
					<span>Mật Khẩu :</span> 
					<input required='required' id="user-password-view-info" type="password" /> 
				</label>
				<label> 
					<span>Xác Nhận Mật Khẩu :</span> 
					<input required='required' id="user-confirm-pass-view-info" type="password" /> 
				</label>
			</div>
			<div class="dialog-button" style='float: right;'>
				<div style="float: left">
					<input type='submit' class='button' value='Cập Nhật' onclick='return UserUpdate_Click("<s:property value='%{#session.USER.username}'/>")'>
				</div>
				<div style="float: left; margin-left: 5px;">
					<input type='submit' class='button' value='Hủy' onclick='return unLockUI()'
						style="width: 97px;">
				</div>
			</div>
		</form>
	</div>
	<div class="info-detail-right-dialog-view-info">
		<div class="select-role-left">
			<h2 class="title-content">Quyền Được Cấp</h2>
			<select class="role-left-view-info" multiple="multiple"></select>
		</div>
	</div>
</div>
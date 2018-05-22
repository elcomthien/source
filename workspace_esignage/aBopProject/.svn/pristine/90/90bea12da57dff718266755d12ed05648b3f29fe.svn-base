var $flagError = '0';
var $contentError = "";
function createUserClick(){
	$.blockUI({ 
		message: $('.dialog-create-user'),
		css: { 
			padding:0, 
			margin:0, 
			width:'60%', 
			top:'20%', 
			left:'20%', 
			textAlign:'center', 
			border:'3px solid #aaa', 
			backgroundColor:'#fff', '-webkit-border-radius': '10px',
		     '-moz-border-radius': '10px',
			cursor:'progress' 
		},
		cursor:'progress',
		onOverlayClick: $.unblockUI
	});
	// get role
	$.post('ajaxGetRole.elcom', function(response){
		$('#role-left').empty();
		var length = response.role.length;
		if(length > 0){
			$('#role-left').append('<option value="'+response.role[0].id+'">'+response.role[0].name+'</option>');
			$('#role-right').empty();
			for(var i = 1; i < length; i++){
				$('#role-right').append('<option value="'+response.role[i].id+'">'+response.role[i].name+'</option>');
			}
		}
	});
}
function addSTBUser($this){
	var username = $($this).attr('user');
	$('.dialog-add-stb').attr('data-user', username);
	$('.dialog-add-stb .title-content:first').text('Box ' + username + ' Quản Lý');
	$.blockUI({ 
		message: $('.dialog-add-stb'),
		css: { 
			padding:0, 
			margin:0, 
			width:'60%', 
			top:'20%', 
			left:'20%', 
			textAlign:'center', 
			border:'3px solid #aaa', 
			backgroundColor:'#fff', '-webkit-border-radius': '10px',
		     '-moz-border-radius': '10px',
			cursor:'progress' 
		},
		cursor:'progress',
		onOverlayClick: $.unblockUI
	});
	
	// get role
	$.post('ajaxGetStb.elcom', function(response){
		$('.stb-left').empty();
		$('.stb-right').empty();
		$('.box-' + username).each(function(){
			$('.stb-left').append('<option value="'+$(this).attr('data-id')+'">'+$(this).text()+'</option>');
		});
		var length = response.playerOutGroup.length;
		if(length > 0){
			for(var i = 0; i < length; i++){
				$('.stb-right').append('<option value="'+response.playerOutGroup[i].id+'">'+response.playerOutGroup[i].mac+'</option>');
			}
		}
	});
}
function roleLeftRightClick(){
	$("#role-left option:selected").each(function() {
		$(this).appendTo('#role-right');
     });
	return false;
}
function roleRightLeftClick(){
	$("#role-left option").each(function() {
		$(this).appendTo('#role-right');
		$(this).prop('selected', false);
    });
	$("#role-right option:selected").each(function() {
		$(this).appendTo('#role-left');
		return false;
     });
	return false;
}

function roleLeftRightUpdateClick(){
	$(".role-left-update option:selected").each(function() {
			$(this).appendTo('.role-right-update');
    });
	return false;
}
function roleRightLeftUpdateClick(){
	$(".role-left-update option").each(function() {
		$(this).appendTo('.role-right-update');
		$(this).prop('selected', false);
    });
	$(".role-right-update option:selected").each(function() {
		$(this).appendTo('.role-left-update');
		return false;
     });

	return false;
}

function getRole(role, username){
	var str = role.split(';');
	var _length = str.length;
	var result = "";
	for(var i = 0; i < _length; i++){
		result += "<p class='role-"+username+"' style='text-align: center;'>"+str[i]+"</p>";
	}
	return result;
}
function getBox(box, username){
	var _length = box.length;
	var result = "";
	if(_length == 0){
		result += "<p style='text-align: center;'>Chưa quản lý box</p>";
	}
	for(var i = 0; i < _length; i++){
		result += "<p class='box-"+username+"' data-id='"+box[i].id+"' style='text-align: center;'>"+box[i].name+"</p>";
	}
	return result;
}
function getStatus(status, username){
	var result = "";
	if(status == '1'){
		result = "<a user='"+username+"' value='1' class='status-"+username+"' href='javascript:void(0)' onclick='return onClick(this)'><img class='img-on'"+
					"src='css/images/on.png'>"+
				 "</a>";
	} else if(status == '0'){
		result = "<a user='"+username+"' value='0' class='status-"+username+"' href='javascript:void(0)' onclick='return offClick(this)'><img class='img-on'"+
					"src='css/images/off.png'>"+
				 "</a>";
	}
	return result;
}
function offClick($this){
	var _class = $($this).attr('class');
	var username = $($this).attr('user');
	var xml = '<user><username>'+username+'</username><pw></pw><status>1</status><creator></creator><numrole>0</numrole></user>';
	$.post('ajaxUpdateUser.elcom', {data: xml}, function(response){
		if(response.data == 'SUCCESS'){
			// update status
			$('.' + _class).trigger('remove').remove();
			$('#'+ _class ).append(updateStatus(1, _class));
			showGrowlMsg('Cập nhật thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
		} else {
			showGrowlMsg('Cập nhật thất bại');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
		}
	});
	return false;
}
function onClick($this){
	var _class =  $($this).attr('class');
	var username = $($this).attr('user');
	var xml = '<user><username>'+username+'</username><pw></pw><status>0</status><creator></creator><numrole>0</numrole></user>';
	$.post('ajaxUpdateUser.elcom', {data: xml}, function(response){
		if(response.data == 'SUCCESS'){
			// update status
			$('.' + _class).trigger('remove').remove();
			$('#'+ _class ).append(updateStatus(0, _class));
			showGrowlMsg('Cập nhật thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
		} else {
			showGrowlMsg('Cập nhật thất bại');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
		}
	});
	
	return false;
}
function updateStatus(status, _class){
	var result = '';
	if(status == 0){
		result = "<a value='0' class='"+_class+"' href='javascript:void(0)' onclick='return offClick(this)'><img class='img-on'"+
					"src='css/images/off.png'>"+
				 "</a>";
	} else {
		result = "<a value='1' class='"+_class+"' href='javascript:void(0)' onclick='return onClick(this)'><img class='img-on'"+
					"src='css/images/on.png'>"+
				 "</a>";
	}

	return result;
}
function drawTableUser(user){
	var username = user.username.toString();
	var html = "<tr class='tr-"+username+"'>"+
					"<td style='text-align: center;'>"+
					"<a id='"+user.username+"' href='#'>"+user.username+"</a>"+
					"</td>"+
					"<td id='role-"+username+"'>"+
						getRole(user.role, user.username) +
					"</td>"+
					"<td  id='status-"+username+"' style='text-align: center;'>"+
						getStatus(user.status, user.username)+
					"</td>"+
					"<td  id='addbox-"+username+"' style='text-align: center;'>"+
						"<div style='max-height: 90px;overflow-y: auto;overflow-x: hidden;'>" +
							getBox(user.stb, user.username)+
						"<div>" +
					"</td>"+
					"<td  style='text-align: center;'>" +
					"<img title='Thêm box' user='"+username+"' class='img-add-stb'"+
					"src='css/images/addbox.png'"+
					"onclick='return addSTBUser(this)'><span style='font-size:17px;'>|</span>"+
					"<img title='Chỉnh sửa thông tin' user='"+username+"' class='img-update-user'"+
						"src='css/images/edit-user-icon.png'"+
						"onclick='return showUpdateUser(this)'><span style='font-size:17px;'>|</span>"+
						"<img title='Xóa' user='"+username+"' class='img-delete-user'src='css/images/delete-user-icon.png'"+
						"onclick='return showDeleteUser(this)'></td>"+
				"</tr>";
	return html;
}
function showUpdateUser($this){
	var username = $($this).attr('user');
	$.blockUI({ 
		message: $('.dialog-update-user'),
		css: { 
			padding:0, 
			margin:0, 
			width:'60%', 
			top:'20%', 
			left:'20%', 
			textAlign:'center', 
			border:'3px solid #aaa', 
			backgroundColor:'#fff', '-webkit-border-radius': '10px',
		     '-moz-border-radius': '10px',
			cursor:'progress' 
		},
		cursor:'progress',
		onOverlayClick: $.unblockUI
	});
	$('#user-username-update').val($.trim($('#' + username).text()));
	$('#user-status-update').val($('.status-' + username).attr('value')).trigger('change');
	// get role
	$.post('ajaxGetRole.elcom',function(response){
		var length = response.role.length;
		if(length > 0){
			$('.role-right-update').empty();
			$('.role-left-update').empty();
			
			for(var i = 0; i < length; i++){
				if(checkRole(response.role[i].name,username)){
					$('.role-left-update').append('<option value="'+response.role[i].id+'">'+response.role[i].name+'</option>');
				} else if(!checkRole(response.role[i].name,username)){
					$('.role-right-update').append('<option value="'+response.role[i].id+'">'+response.role[i].name+'</option>');
				}
				
			}
		}
	});
	return false;
}
function checkRole(roleName, username){
	var flag = false;
	$('.role-' + username).each(function(){
		if(roleName == $.trim($(this).text())){
			flag = true;
		}
	});
	return flag;
}
function showDeleteUser($this){
	var left = $($this).position().left - 125;
	var top = $($this).position().top;
	var username = $($this).attr('user');
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
			'left':left, 'top': top, 'jfunction':'ajaxDeleteUser.elcom'};
	showConfirm($this, options);
	$('.yes').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
		$.post(options.jfunction,{data: username}, function(response){
			if(response.data == 'SUCCESS'){
				$('.tr-' + username).trigger('remove').remove();
				showGrowlMsg("Đã xóa thành công");
			}
			
		});
	});
	return false;
}
$(function(){
	$('#div-add-user').on('ready', function(){
		$.post('ajaxGetListUser.elcom', function(response){
			if(response.users == null){
				return;
			}
			if(response.users.length > 0){
				$('.user-manager').empty();
				for(var i =0; i < response.users.length; i++){
					$('.user-manager').append(drawTableUser(response.users[i]));
				}
				
			} else{
				$('.user-manager').empty();
				$('.user-manager').append("<tr><td style='text-align:center'>Danh sách người dùng chưa được tạo</td></tr>");
			}
		});
	});
	$('#div-add-user').trigger('ready');
});
function checkUserName(){
	var username = $('#user-username').val();
	if(username == "" || username == null){
		$flagError = '1';
		return;
	} else{
		$.post('ajaxCheckUsername.elcom', {data: username}, function(response){
			if(response.data == 'SUCCESS'){// username not exist
				$('.error-create').empty();
				$contentError = '';
				$flagError = '0';
			} else {
				$flagError = '1';
				$contentError = "<p>[Lỗi] Tên đăng nhập đã tồn tại</p>";
				$('.error-create').empty();
				$('.error-create').append($contentError);
				return;
			}
		});
	}
	return;
}

function createUser_Click(parentcreator){
	if(checkCreateUser()){
		var username = $('#user-username').val();
		var pass = $('#user-password').val();
		var status = $('#user-status').val();
		var role = '';
		var roleTmp = '';
		var numrole = 0;
		$('#role-left option').each(function(){
			numrole+=1;
			role = role.concat('<role' + numrole + '>');
		    role = role.concat($(this).val());
		    role = role.concat('</role' + numrole + '>');
		    roleTmp+=$(this).text() + ';';
		});
		if(roleTmp.length > 1){
			roleTmp = roleTmp.substring(0, (roleTmp.length - 1));
		}
		var user = {'username':username, 'status': status, 'role': roleTmp, 'box':[]};
		var xml = '<user><username>'+username+'</username><pw>'+pass+'</pw><status>'+status+'</status><creator>'+parentcreator+'</creator><numrole>'+numrole+'</numrole>' +role+'</user>';
		console.log(xml);
		$.post('ajaxCreateUser.elcom', {data:xml}, function(response){
			if(response.data== 'SUCCESS'){
				$('.user-manager').append(drawTableUser(user));
				showGrowlMsg('Tạo thành công');
				$(document).ajaxStop(function(){
					$.unblockUI;
				});
				// update table user.
			}
		});
	}
	return false;
	
}
function checkCreateUser(){
	var username = $('#user-username').val();
	var pass = $('#user-password').val();
	var cfpass = $('#user-confirm-pass').val();
	var status = $('#user-status').val();
	var numrole = 0;

	$('#role-left option').each(function(){
		numrole+=1;
	});
	if(username == "" || username == null){
		$flagError = '1';
		$contentError += "<p>[Lỗi] Tên đăng nhập là bắt buộc</p>";
	} else if(username != ""){// check exist account
		$.post('ajaxCheckUsername.elcom', {data: username}, function(response){
			if(response.data != 'SUCCESS'){// username not exist
				$flagError = '1';
				$contentError += "<p>[Lỗi] Tên đăng nhập đã tồn tại</p>";
			}
		});
	}
	if(pass == "" || pass == null){
		$flagError = '1';
		$contentError += "<p>[Lỗi] Mật khẩu là bắt buộc</p>";
	}
	if(pass != cfpass){
		$flagError = '1';
		$contentError += "<p>[Lỗi] Mật khẩu không giống nhau</p>";
	}
	if(numrole == 0){
		$flagError = '1';
		$contentError += "<p>[Lỗi] Quyền cho người dùng là bắt buộc</p>";
	}
	if($flagError == '1'){
		$('.error-create').empty();
		$('.error-create').append($contentError);
		$contentError = '';
		$flagError = '0';
		return false;
	}
	$('.error-create').empty();
	return true;
}
function changePassClick(){
	$('.change-pass').fadeToggle("slow");
}
function checkUpdateUser(){
	var pass = $('#user-password-update').val();
	var cfpass = $('#user-confirm-pass-update').val();
	var status = $('#user-status-update').val();
	
	var numrole = 0;
	var error = false;
	var contentErr='';
	$('.error-update').empty();
	$('.role-left-update option').each(function(){
		numrole+=1;
	});
	if(numrole == 0){
		error = true;
		contentErr += "<p>[Lỗi] Quyền cho người dùng là bắt buộc</p>";
	}
	if((pass!= "" || cfpass!= "") && (pass != cfpass)){
		error = true;
		contentErr += "<p>[Lỗi] Mật khẩu không giống nhau</p>";
	}
	if(error){
		$('.error-update').empty();
		$('.error-update').append(contentErr);
		return false;
	}
	return true;
}
function createUpdate_Click(creator){
	if(!checkUpdateUser()){
		return false;
	}
	var username = $('#user-username-update').val();
	var pass = $('#user-password-update').val();
	var status = $('#user-status-update').val();
	var role = '';
	var roleTmp = '';
	var numrole = 0;
	$('.role-left-update option').each(function(){
		numrole+=1;
		role = role.concat('<role' + numrole + '>');
	    role = role.concat($(this).val());
	    role = role.concat('</role' + numrole + '>');
	    roleTmp+=$(this).text() + ';';
	});
	if(roleTmp.length > 1){
		roleTmp = roleTmp.substring(0, (roleTmp.length - 1));
	}
	var user = {'username':username, 'status': status, 'role': roleTmp};
	var xml = '<user><username>'+username+'</username><pw>'+pass+'</pw><status>'+status+'</status><creator>'+creator+'</creator><numrole>'+numrole+'</numrole>' +role+'</user>';
	console.log(xml);
	$.post('ajaxUpdateUser.elcom', {data:xml}, function(response){
		if(response.data == 'SUCCESS'){
			// update role
			$('.role-' + username).trigger('remove').remove();
			$('#role-' + username).append(getRole(roleTmp, username));
			// update status
			$('.status-' + username).trigger('remove').remove();
			$('#status-'+ username ).append(getStatus(status, username));
			
			showGrowlMsg('Cập nhật thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
		} else {
			showGrowlMsg('Cập nhật thất bại');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
		}
		
	});
	return false;
}
function stbLeftRightClick(){
	var stbId= "";
	var count = 0;
	$(".stb-left option:selected").each(function() {
			$(this).appendTo('.stb-right');
			stbId = stbId + $(this).val() + ";";
        });
	if(stbId.length > 1){
		stbId = stbId.substring(0, (stbId.length - 1));
	}
	return false;
}
function stbRightLeftClick(){
	var stbId= "";
	var count = 0;
	$(".stb-right option:selected").each(function() {
			$(this).appendTo('.stb-left');
			stbId = stbId + $(this).val() + ";";
    });
	if(stbId.length > 1){
		stbId = stbId.substring(0, (stbId.length - 1));
	}
	return false;
}
function saveSTB_Click(username_login){
	var username = $('.dialog-add-stb').attr('data-user');
	var stb = '';
	var stb_out = '';
	var stbTmp = [];
	var stbTmp_out = [];
	var num = 0;
	var num_out = 0;
	$('.stb-left option').each(function(){
		num+=1;
		stb = stb.concat('<stbin' + num + '>');
		stb = stb.concat($(this).val());
		stb = stb.concat('</stbin' + num + '>');
		var box = {'id':$(this).val(), 'name': $(this).text()};
	    stbTmp.push(box);
	});
	$('.stb-right option').each(function(){
		num_out+=1;
		stb_out = stb_out.concat('<stbout' + num_out + '>');
		stb_out = stb_out.concat($(this).val());
		stb_out = stb_out.concat('</stbout' + num_out + '>');
		var box = {'id':$(this).val(), 'name': $(this).text()};
		stbTmp_out.push(box);
	});
	
	var xml = '<user><username>'+username_login+'</username><creator>'+username+'</creator><numstbin>'+num+'</numstbin>'+stb+ '<numstbout>'+num_out+'</numstbout>'+stb_out+'</user>';
	console.log('Data send to server to add stb into user: ' + xml);
	$.post('ajaxAddStbUser.elcom', {data:xml}, function(response){
		if(response.data == 'SUCCESS'){
			// update stb
			$('#addbox-' + username + ' div').empty();
			$('#addbox-' + username + ' div').append(getBox(stbTmp, username));
			
			// update stb
			$('#addbox-' + username_login + ' div').empty();
			$('#addbox-' + username_login + ' div').append(getBox(stbTmp_out, username_login));
			
			showGrowlMsg('Cập nhật thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
		} else {
			showGrowlMsg('Cập nhật thất bại');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
		}
		
	});
	return false;
}
function getParentCreator(){
	return $('.ssUser').attr('data-parent');
}
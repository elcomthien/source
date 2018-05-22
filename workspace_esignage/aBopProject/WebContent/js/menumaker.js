
(function($) {

  $.fn.menumaker = function(options) {
      
      var cssmenu = $(this), settings = $.extend({
        title: "Menu",
        format: "dropdown",
        sticky: false
      }, options);

      return this.each(function() {
        cssmenu.prepend('<div id="menu-button">' + settings.title + '</div>');
        $(this).find("#menu-button").on('click', function(){
          $(this).toggleClass('menu-opened');
          var mainmenu = $(this).next('ul');
          if (mainmenu.hasClass('open')) { 
            mainmenu.hide().removeClass('open');
          }
          else {
            mainmenu.show().addClass('open');
            if (settings.format === "dropdown") {
              mainmenu.find('ul').show();
            }
          }
        });

        cssmenu.find('li ul').parent().addClass('has-sub');

        multiTg = function() {
          cssmenu.find(".has-sub").prepend('<span class="submenu-button"></span>');
          cssmenu.find('.submenu-button').on('click', function() {
            $(this).toggleClass('submenu-opened');
            if ($(this).siblings('ul').hasClass('open')) {
              $(this).siblings('ul').removeClass('open').hide();
            }
            else {
              $(this).siblings('ul').addClass('open').show();
            }
          });
        };

        if (settings.format === 'multitoggle') multiTg();
        else cssmenu.addClass('dropdown');

        if (settings.sticky === true) cssmenu.css('position', 'fixed');

        resizeFix = function() {
          if ($( window ).width() > 768) {
            cssmenu.find('ul').show();
          }

          if ($(window).width() <= 768) {
            cssmenu.find('ul').hide().removeClass('open');
          }
        };
        resizeFix();
        return $(window).on('resize', resizeFix);

      });
  };
})(jQuery);

// handle menu
(function($){
$(document).ready(function(){
$(document).ready(function() {
  $("#cssmenu").menumaker({
    title: "Menu",
    format: "multitoggle"
  });

$("#cssmenu").prepend("<div id='menu-line'></div>");

var foundActive = false, activeElement, linePosition = 0, menuLine = $("#cssmenu #menu-line"), lineWidth, defaultPosition, defaultWidth;

$("#cssmenu > ul > li").each(function() {
  if ($(this).hasClass('active')) {
    activeElement = $(this);
    foundActive = true;
  }
});

if (foundActive === false) {
  activeElement = $("#cssmenu > ul > li").first();
}

defaultWidth = lineWidth = activeElement.width();

defaultPosition = linePosition = activeElement.position().left;

menuLine.css("width", lineWidth);
menuLine.css("left", linePosition);

$("#cssmenu > ul > li").hover(function() {
  activeElement = $(this);
  lineWidth = activeElement.width();
  linePosition = activeElement.position().left;
  menuLine.css("width", lineWidth);
  menuLine.css("left", linePosition);
}, 
function() {
  menuLine.css("left", defaultPosition);
  menuLine.css("width", defaultWidth);
});

});


});
})(jQuery);
/*Define onclick menu*/
function drawMenuLine(){
	$("#cssmenu").prepend("<div id='menu-line'></div>");
	var foundActive = false, activeElement, linePosition = 0, menuLine = $("#cssmenu #menu-line"), lineWidth, defaultPosition, defaultWidth;
	$("#cssmenu > ul > li").each(function() {
	  if ($(this).hasClass('active')) {
	    activeElement = $(this);
	    foundActive = true;
	  }
	});

	if (foundActive === false) {
	  activeElement = $("#cssmenu > ul > li").first();
	}

	defaultWidth = lineWidth = activeElement.width();

	defaultPosition = linePosition = activeElement.position().left;

	menuLine.css("left", defaultPosition);
	menuLine.css("width", defaultWidth);
	
	$("#cssmenu > ul > li").hover(function() {
		  activeElement = $(this);
		  lineWidth = activeElement.width();
		  linePosition = activeElement.position().left;
		  menuLine.css("width", lineWidth);
		  menuLine.css("left", linePosition);
		}, 
		function() {
		  menuLine.css("left", defaultPosition);
		  menuLine.css("width", defaultWidth);
	});
}
//$(function(){
//	$('#menu-layout').on('click', function(event){
//		clearAutoRefresh();
//		$.cookie('id', '#menu-layout', { expires: 1 });
//	});
//	$('#menu-content').on('click', function(event){
//		clearAutoRefresh();
//		$.cookie('id', '#menu-content', { expires: 1 });
//	});
//	$('#menu-user').on('click', function(event){
//		clearAutoRefresh();
//		$.cookie('id', '#menu-user', { expires: 1 });
//	});
//	$('#menu-system').on('click', function(event){
//		clearAutoRefresh();
//		$.cookie('id', '#menu-system', { expires: 1 });
//	});
//});

// trigger ready(after load page)
$(function(){
	$('#cssmenu').on('ready', function(){
		
		clearAutoRefresh();
		
		clearInterval($timeLoopPlaylist);
		clearTimeout($timeStartPlaylist);
		clearTimeout($timeStartItem);
		clearTimeout($timeEndItem);
		
		var id = $.cookie('id');
		var pathname = window.location.pathname;
		var name = '';
		if(pathname.indexOf('player.elcom') > 0){
			name = 'Player';
			id = '#menu-layout';
		} else if(pathname.indexOf('layout.elcom') > 0){
			name = 'Bố Cục';
			id = '#menu-layout';
		} else if(pathname.indexOf('schedule.elcom') > 0){
			name = 'Lập Lịch';
			id = '#menu-layout';
		} else if(pathname.indexOf('text.elcom') > 0){
			name = 'Nội Dung Text';
			id = '#menu-content';
		} else if(pathname.indexOf('media.elcom') > 0){
			name = 'Nội Dung Media';
			id = '#menu-content';
		} else if(pathname.indexOf('background.elcom') > 0){
			name = 'Nội Dung Background';
			id = '#menu-content';
		} else if(pathname.indexOf('web.elcom') > 0){
			name = 'Nội Dung Web';
			id = '#menu-content';
		} else if(pathname.indexOf('slide.elcom') > 0){
			name = 'Slide Image';
			id = '#menu-content';
		} else if(pathname.indexOf('group.elcom') > 0){
			name = 'Quản Lý Nhóm';
			id = '#menu-user';
		} else if(pathname.indexOf('user.elcom') > 0){
			name = 'Quản Lý Người Dùng';
			id = '#menu-user';
		} else if(pathname.indexOf('membership.elcom') > 0){
			name = 'Quản Lý Quyền';
			id = '#menu-user';
		} else if(pathname.indexOf('config.elcom') > 0){
			name = 'Cấu Hình FTP';
			id = '#menu-system';
		}
		$(id).addClass('active');
		$(id + ' a').first().text(name);
		drawMenuLine();
	});
	$('#cssmenu').trigger('ready');
	
});

function viewInfor(username, roles){
	roles = roles.replace('[', '').replace(']','');
	
	$.blockUI({ 
		message: $('.dialog-view-info'),
		css: { 
			padding:0, 
			margin:0, 
			width:'38%', 
			top:'20%', 
			left:'30%', 
			textAlign:'center', 
			border:'3px solid #aaa', 
			backgroundColor:'#fff', '-webkit-border-radius': '10px',
		     '-moz-border-radius': '10px',
			cursor:'progress' 
		},
		cursor:'progress',
		onOverlayClick: $.unblockUI
	});
	//
	$('#user-username-view-info').val(username);
	$('#user-status-view').val("Hoạt động");
	var array = roles.split(',');
	for(var i = 0; i < array.length; i++){
		$('.role-left-view-info').append("<option>"+array[i]+"</option>");
	}
	return false;
}
function UserUpdate_Click(username){
	$('.error-view-info').empty();
	var pw = $('#user-password-view-info').val();
	var cfpw = $('#user-confirm-pass-view-info').val();
	if(pw == "" && cfpw == ""){
		return false;
	}
	if(pw != cfpw){
		var contentError = "<p>[Lỗi] Mật khẩu không giống nhau</p>";
		$('.error-view-info').append(contentError);
		return false;
	}
	var xml = '<user><username>'+username+'</username><pw>'+pw+'</pw><status></status><creator></creator><numrole>-1</numrole></user>';
	$.post('ajaxUserUpdate.elcom', {data: xml}, function(response){
		showGrowlMsg('Cập nhật thành công');
		$(document).ajaxStop(function(){
			$.unblockUI;
		});
	});
}
function fnGetRole(){
	var role = $('.userlogin').attr('data-role');
	return role;
}

// row number  => 2 hour
var $numRow = 181;
// width 1 cell
var $numCell = 40;
// container item draggable
var $arrItem = [];
// container array playlist type:JSON
var $arrPlaylist = [];
// second/pixcel
var $secondPixcel = 1;
// set maxtimeCurrent = 3hour
var $maxTimeCurrent = 2*3600;
var $maxTimeDefault = 2*3600;
// length name to substring
var LENGTH = 10;
// container JSON schedule daily
var $scheduleDailyJSON = {};
// flag to detect tab PLAYLIST_CLICK OR PLAYLIST OF DAILY
var $flag = 0; // flag = 0 tab PLAYLIST; =1 tab DAILY
var $arrPlaylistItemDelete=[];
$(function(){
	if(fnGetRole() != 'USER_VIEW'){
		initDraggable('.item-video');
		initDraggable('.item-image');
		initDraggable('.item-browser');
		initDraggable('.item-text');
		initContextMenuItem('.item-clone');
		initContextMenuTable('.container-playlist');
		initContextMenuDaily('.daily-schedule');
		initDraggableSchedule('.item-playlist');
		initDraggableSchedule('.daily-schedule');
		initDroppable('.schedule-daily', '.item-playlist');
		initContextMenuPlaylist('.item-playlist');
		initPlaylistClick();
		initDailyScheduleClick();
		initContextMenuCreateDaily('.schedule-daily');
		initPeriodicScheduleClick();
		initSelectRadioButton();
		initButtonTypeClick();
	}
});
function initTimePicker(){
	$('.time').each(function(index, element) {
	       $(this).datetimepicker({
				format: 'hh:mm:ss',
				language: 'en',
				pickDate: false
			});
	       $(this).datetimepicker().on("changeDate", function(e){
	   		var val = $(this).find('input').val();
	   		var _class = $(this).find('input').attr('class');
	   		var index = parseInt(_class.substring(_class.length -1, _class.length));
	   		if(_class.indexOf('timestart-dialog') != -1){
	   			var timetotal = toSecond($('.timetotal-dialog' + index).val());
	   			var timeend = toSecond(val) + timetotal;
   				$('.timeend-dialog' + index).text(toHHMMSS(timeend));
   				$('.timestart' + index).val(val);
   				$('.timeend' + index).text(toHHMMSS(timeend));
   				// update value trong arrItem
   				var length = $arrItem.length;
   				for(var i =0; i < length; i++){
   					if($arrItem[i].attr('time-total') == $('.timetotal-dialog' + index).val() &&
   							$arrItem[i].attr('name-item') == $('.nameitem'+ index).attr('title')){
   						$arrItem[i].css({'top': toSecond(val)});
   						$arrItem[i].attr('time-start', val);
   					}
   				}
	   		} else if(_class.indexOf('timetotal-dialog') != -1){
	   			var timestart = toSecond($('.timestart-dialog' + index).val());
	   			var timeend = toSecond(val) + timestart;
   				$('.timeend-dialog'  + index).text(toHHMMSS(timeend));
   				$('.timetotal' + index).val(val);
   				$('.timeend' + index).text(toHHMMSS(timeend));
   				// update value trong arrItem
   				var length = $arrItem.length;
   				for(var i =0; i < length; i++){
   					if($arrItem[i].attr('time-start') == $('.timestart-dialog' + index).val()){
   						$arrItem[i].css({'height': toSecond(val)});
   						$arrItem[i].attr('time-total', val);
   					}
   				}
	   		} else if(_class.indexOf('timestart') != -1){
	   			var timetotal = toSecond($('.timetotal' + index).val());
		   		var timeend = toSecond(val) + timetotal;
				$('.timeend' + index).text(toHHMMSS(timeend));
				// set top item select
				$('.item-select').css({'top': toSecond(val)});
				// update value trong arrItem
				var length = $arrItem.length;
				for(var i =0; i < length; i++){
					if($arrItem[i].attr('id-content') == $('.item-select').attr('id-content') &&
							$arrItem[i].attr('time-total') == $('.item-select').attr('time-total') &&
							$arrItem[i].hasClass('item-select')){
						$arrItem[i].css({'top': toSecond(val)});
						$arrItem[i].attr('time-start', val);
					}
				}
	   		} else if(_class.indexOf('timetotal') != -1){
	   			var timestart = toSecond($('.timestart'+ index).val());
	   			var timeend = toSecond(val) + timestart;
   				$('.timeend'  + index).text(toHHMMSS(timeend));
   				// set height item select
   				$('.item-select').css({'height': toSecond(val)});
   				// update value trong arrItem
   				var length = $arrItem.length;
   				for(var i =0; i < length; i++){
   					if($arrItem[i].attr('id-content') == $('.item-select').attr('id-content') && 
   							$arrItem[i].attr('time-start') == $('.item-select').attr('time-start') &&
   							$arrItem[i].hasClass('item-select')){
   						$arrItem[i].css({'height': toSecond(val)});
   						$arrItem[i].attr('time-total', val);
   					}
   				}
	   		} else if(_class.indexOf('starttime') != -1){
	   			var length = $arrPlaylist.length;
	   			for(var i = 0; i < length; i++){
	   				var clazz = $(this).find('input').attr('class');
	   				var tmp = 'starttime-' + $arrPlaylist[i].id + $arrPlaylist[i].suffix;
	   				if(clazz === tmp){
	   					$arrPlaylist[i].timestart = $(this).find('input').val();
	   				}
	   			}
	   		} else if(_class.indexOf('endtime') != -1){
	   			var length = $arrPlaylist.length;
	   			for(var i = 0; i < length; i++){
	   				var clazz = $(this).find('input').attr('class');
	   				var tmp = 'endtime-' + $arrPlaylist[i].id + $arrPlaylist[i].suffix;
	   				if(clazz === tmp ){
	   					$arrPlaylist[i].timeend = $(this).find('input').val();
	   				}
	   			}
	   		}
	   		
	     });
	      
	   });
	$('.add-on').on('click', function(){
		var time = $($(this).prev()).val();
		if(time == ""){
			$($(this).prev()).val("00:00:01");
		}
	});
}
function initDatePicker(){
	$('.date').each(function(index, element) {
	       $(this).datetimepicker({
				format: 'dd/MM/yyyy',
				language: 'en',
				pickDate: true,
				pickTime: false
			});
	   });
}
function initDraggable(classElement){
	$(classElement).each(function(index, element) {
        // make the event draggable using jQuery UI
		$(this).draggable({
			// zIndex: 999,
			helper: function() {
				// get width of colunm;
				var w = $('.column').width();
				var $this = $(this).clone();
				var detail = setTitle($this);
				var tagli;
				// add class color for video, image, text, browser
				if($this.attr('item-tyle') == 'video'){
					tagli = ($($this).attr('title',detail).removeAttr('id').addClass('item-clone-video').css({
						'width':w,
						'height':$numCell,
						'margin':'0',
						'color':'#ffffff'
					   }));
					
				} else if($this.attr('item-tyle') == 'image'){
					tagli = ($($this).attr('title',detail).removeAttr('id').addClass('item-clone-image').css({
						'width':w,
						'height':$numCell,
						'margin':'0',
						'color':'#ffffff'
					   }));
					
				} else if($this.attr('item-tyle') == 'text'){
					tagli = ($($this).attr('title',detail).removeAttr('id').addClass('item-clone-text').css({
						'width':w,
						'height':$numCell,
						'margin':'0',
						'color':'#ffffff'
					   }));
				
				} else if($this.attr('item-tyle') == 'browser'){
					tagli = ($($this).attr('title',detail).removeAttr('id').addClass('item-clone-browser').css({
						'width':w,
						'height':$numCell,
						'margin':'0',
						'color':'#ffffff'
					   }));
					
				}
				$($this).attr('name-item',$(this).attr('title'));
				$($this).attr('id-content',$(this).attr('id'));
				return tagli;
			},
			cursor: 'pointer',
			tolerance: 'fit',
			revert: true,
			scroll: true
		});
    });
}
function initDraggableSchedule(classElement){
	$(classElement).each(function(index, element) {
        // make the event draggable using jQuery UI
		$(this).draggable({
			helper: 'clone',
			cursor: 'pointer',
			tolerance: 'fit',
			revert: true,
			scroll: true
		});
    });
}
function initDroppable(containment, accept){
	  $(containment).droppable({
			accept: accept,
        	drop: function (e, ui) {
				
				var $element = null;
				// var w = $('.heading').width();
				if ($(ui.draggable)[0].id != "") {
					var id = ui.helper.attr('item-id');
					$('.title-dialog').text($.trim(ui.helper.text()));
					$('.title-dialog').attr('id-clone', id );
					$('.title-dialog').attr('title', ui.helper.attr('title'));
					$('.dialog-msg-content').empty();
					
// if(checkExist(id)){
// var msg = "Danh sách đã tồn tại trong lịch. Vui lòng chọn môt danh sách
// khác";
// showMessageDialogError(msg);
// return;
// }
					
					$.blockUI({ 
						message: $('.dialog-timepicker'),
						css: { 
							padding:0, 
							margin:0, 
							width:'65%', 
							top:'10%', 
							left:'50%', 
							textAlign:'center', 
							background: 'transparent',
							border: 'none',
							cursor:'progress' 
						},
						cursor:'progress'
					}); 
					ui.helper.remove();
					
				}	
			}
		});
}

function initDroppablePeriodic(containment, accept){
	  $(containment).droppable({
			accept: accept,
      	drop: function (e, ui) {
				
				var $element = null;
				if ($(ui.draggable)[0].id != "") {
					var id = ui.helper.attr('item-id');
					var time = ui.helper.attr('data-time');
					var name = ui.helper.attr('title');
					$('.schedule-periodic-info').empty();
					var item = "<td class='"+id+"'>"+
									"<input data-time='"+time+"' class='scheduleperiodic-"+id+"' type='text' style='width: 100px; font-size: 15px;' value='"+name+"'>" +
								"</td>"+
								"<td style='text-align: center'>"+
								"<div class='input-append date'>"+
									"<input class='startdate-"+id+"' style='width: 80px' placeholder='dd/mm/yyyy' data-format='dd/MM/yyyy' type='text'></input>"+
									"<span class='add-on'>"+ "<i data-date-icon='icon-calendar'></i>"+
									"</span>"+
								"</div>"+"</td>"+
								"<td style='text-align: center'>"+
									"<div class='input-append date'>"+
										"<input class='enddate-"+id+"' style='width: 80px' placeholder='dd/mm/yyyy' data-format='dd/MM/yyyy' type='text'>"+"</input>"+
										"<span class='add-on'>"+ "<i data-date-icon='icon-calendar'></i>"+
										"</span>"+
									"</div>"+"</td>"+
								"<td style='text-align:center;'>" + 
									  "<a href='#' title='Lưu' onclick='return saveSchedulePeriodic("+id+")'>" + 
										"<img src='css/images/page_white_save.png'/>" +
									  "</a>" +
									"</td>" ;
								
					$('.schedule-periodic-info').append(item);
					initDatePicker();
					ui.helper.remove();
					
				}	
			}
		});
}

function initPlaylistDaily(arrVi, arrImg, arrBro, arrTex){
	$('.container-heading-daily').empty();
	$('.container-playlist-daily').empty();

	var numcol = arrImg.length + arrVi.length + arrBro.length + arrTex.length;
	var percent = 92/numcol;
	var col = "";
	var cell = "";
	// draw column time
	$('.container-heading-daily').append("<div class='heading' style='width: 8%'>Time</div>");
	drawColumnTimeDaily();
	
	for(var i =0; i < arrVi.length; i++){
		$('.container-heading-daily').append($(drawHeader(arrVi[i].name, percent + '%')));
		var col = $(drawColumn(arrVi[i].id, 'col-video-'+i, percent + '%')).addClass('draw-cell');
		$('.container-playlist-daily').append(col);
		
	}
	for(var i =0; i < arrImg.length; i++){
		$('.container-heading-daily').append($(drawHeader(arrImg[i].name, percent + '%')));
		var col = $(drawColumn(arrImg[i].id, 'col-image-'+i, percent + '%')).addClass('draw-cell');
		$('.container-playlist-daily').append(col);
	
	}
	for(var i =0; i < arrBro.length; i++){
		$('.container-heading-daily').append($(drawHeader(arrBro[i].name, percent + '%')));
		var col = $(drawColumn(arrBro[i].id, 'col-browser-' + i, percent + '%')).addClass('draw-cell');
		$('.container-playlist-daily').append(col);
		
	}
	for(var i =0; i < arrTex.length; i++){
		$('.container-heading-daily').append($(drawHeader(arrTex[i].name, percent + '%')));
		var col = $(drawColumn(arrTex[i].id,'col-text-' + i, percent + '%')).addClass('draw-cell');
		$('.container-playlist-daily').append(col);
	}
	// draw all row
	updatePlaylist($numRow);
	$('.container-playlist-daily').removeClass('hidden');
}
function initPlaylist(arrVi, arrImg, arrBro, arrTex){
	$('.container-heading').empty();
	$('.container-playlist').empty();
	$('.playlist-detail').empty();// empty table
	var numcol = arrImg.length + arrVi.length + arrBro.length + arrTex.length;
	var percent = 92/numcol;
	var col = "";
	var cell = "";
	// draw column time
	$('.container-heading').append("<div class='heading' style='width: 8%'>Time</div>");
	drawColumnTime();
	
	for(var i =0; i < arrVi.length; i++){
		$('.container-heading').append($(drawHeader(arrVi[i].name, percent + '%')));
		var col = $(drawColumn(arrVi[i].id, 'col-video-'+i, percent + '%')).addClass('draw-cell');
		
		$('.container-playlist').append(col);
		
		$('.col-video-'+i).droppable({
               	accept: ".item-video",
                drop: function( event, ui ) {
					var $this = this;
					droppable(ui, $this);
                }
            });
	}
	for(var i =0; i < arrImg.length; i++){
		$('.container-heading').append($(drawHeader(arrImg[i].name, percent + '%')));
		var col = $(drawColumn(arrImg[i].id, 'col-image-'+i, percent + '%')).addClass('draw-cell');
		
		$('.container-playlist').append(col);
		$('.col-image-'+i).droppable({
               	accept: ".item-image",
                drop: function( event, ui ) {
					var $this = this;
					droppable(ui, $this);
                }
            });
	}
	for(var i =0; i < arrBro.length; i++){
		$('.container-heading').append($(drawHeader(arrBro[i].name, percent + '%')));
		var col = $(drawColumn(arrBro[i].id, 'col-browser-' + i, percent + '%')).addClass('draw-cell');
		
		$('.container-playlist').append(col);
		$('.col-browser-'+i).droppable({
               	accept: ".item-browser",
                drop: function( event, ui ) {
					var $this = this;
					
					droppable(ui, $this);
                }
            });
	}
	for(var i =0; i < arrTex.length; i++){
		$('.container-heading').append($(drawHeader(arrTex[i].name, percent + '%')));
		var col = $(drawColumn(arrTex[i].id,'col-text-' + i, percent + '%')).addClass('draw-cell');
		
		$('.container-playlist').append(col);
		$('.col-text-'+i).droppable({
               	accept: ".item-text",
                drop: function( event, ui ) {
					var $this = this;
					droppable(ui, $this);
                }
            });
	}
	// draw all row
	updatePlaylist($numRow);
	// remove class hidden
	$('.container-playlist').removeClass('hidden');
}
/* Init context menu */
function initContextMenuItem(selector){
            $.contextMenu({
                selector: selector,
                build: function ($trigger, e) {
                    console.log(e);
					
                    return {
                        callback: function (e, options) {
							if(e == 'delete'){
								deleteItemClick($trigger);
							} 
							
                        },
                        items: items
                    };
                }
				
            });
            var items = {
                "delete": {
                    name: "Xóa",
                    icon: "delete"
                }
                
            };
}
function initContextMenuTable(selector){
            $.contextMenu({
                selector: selector,
                build: function ($trigger, e) {
                    console.log(e);
					
                    return {
                        callback: function (e, options) {
                        	if($arrItem.length > 0){
								if(e == 'view'){
									viewPlaylistTable();
								} else if(e == 'delete'){
									deletePlaylistItem();
								} else if(e == 'save'){
									savePlaylistItem();
								}
                        	}
                        },
                        items: items
                    };
                }
				
            });
            var items = {
                "view": {
                    name: "Xem danh sách",
                    icon: "view"
                },
				"save": {
                    name: "Lưu danh sách",
                    icon: "save"
                },
				"sep1": "---------",
                "delete": {
                    name: "Xóa danh sách",
                    icon: "delete"
                }
                
            };
}
function initContextMenuDaily(selector){
    $.contextMenu({
        selector: selector,
        build: function ($trigger, e) {
            console.log(e);
			
            return {
                callback: function (e, options) {
                	if(e == 'edit'){
						changeNameDaily($trigger);
					} else if(e == 'delete'){
						deleteDaily($trigger);
					} 
                },
                items: items
            };
        }
		
    });
    var items = {
        "edit": {
            name: "Đổi tên",
            icon: "edit"
        },
		
		"sep1": "---------",
        "delete": {
            name: "Xóa lịch",
            icon: "delete"
        }
        
    };
}
function initContextMenuPlaylist(selector){
    $.contextMenu({
        selector: selector,
        build: function ($trigger, e) {
            console.log(e);
			
            return {
                callback: function (e, options) {
                	if(e == 'add'){
						addNewPlaylist($trigger);
					} else if(e =='update'){
						updatePlaylistMenu($trigger);
					} else if(e == 'delete'){
						deletePlaylist($trigger);
					} 
					
                },
                items: items
            };
        }
		
    });
    var items = {
    	"add": {
                name: "Tạo mới",
                icon: "add"
            },
		"update": {
            name: "Cập nhật",
            icon: "update"
        },
		"sep1": "---------",
        "delete": {
            name: "Xóa",
            icon: "delete"
        }
        
    };
}
function addNewPlaylist($this){
	var _length = $arrItem.length;
	for(var i =0; i < _length; i++){
		$arrItem[i].trigger('remove').remove();
	}
	$arrItem = [];
	// update select playlist
	$($this).removeClass('background-item-playlist');
	// delete table
	$('.layout-view-right').trigger('remove').remove();
}
function btnCreatePlaylist(){
	$this = $('.background-item-playlist');
	addNewPlaylist($this);
	return false;
}
function btnSaveAllItem(){
	savePlaylistItem();	
	return false;
}
function btnDeleteAllItem(){
	deletePlaylistItem();	
	return false;
}
function updatePlaylistMenu($this){
	var playlistId = $('.background-item-playlist').attr('id');
	// update playlist when playlist click
	if(typeof playlistId != 'undefined' && $('.background-item-playlist').text() != ""){
		if($arrItem.length == 0){
			showMessageDialogError("Lỗi", "Không thể cập nhật DANH SÁCH rỗng");
			return false;
		}
		var playlistId = $('.background-item-playlist').attr('id');
		var name = $('#playlistname').val();
		var desc = $('#description').val();
		var jsonObj = [];
		var _length = $arrItem.length;
		var layoutid = $('#playlist-layout').val();
		for(var i = 0; i < _length; i++){
			var item = {};
			item['name'] = $arrItem[i].attr('name-item');
			item['timestart'] = $arrItem[i].attr('time-start');
			item['timeend'] = $arrItem[i].attr('time-end');
			item['idparent'] = layoutid;
			item['id'] = $arrItem[i].attr('item-id');
			item['idcontent'] = $arrItem[i].attr('id-content');
			item['iditem'] = $arrItem[i].attr('id-private');
			jsonObj.push(item);
		}
		_length = $arrPlaylistItemDelete.length;
		var idDel = "";
		for(var i = 0; i < _length; i++){
			idDel+= $arrPlaylistItemDelete[i].attr('id-private') + ";";
		}
		if(idDel.length > 0){
			idDel = idDel.substring(0, (idDel.length - 1));
		}
		var jsPlaylist = {"id": playlistId, "name": name, "description": desc, "items": jsonObj, "iditemdelete": idDel};
		var jsonStr = JSON.stringify(jsPlaylist);
		$.post('ajaxUpdatePlaylistItem.elcom',{data: jsonStr}, function(response){
			// $.unblockUI();
			showGrowlMsg('Cập nhật thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
			// update array delete
			$arrPlaylistItemDelete.length = 0;
		});
	} else {
		showMessageDialogWarning('Cảnh Báo', 'Hãy chọn một DANH SÁCH trước khi cập nhật');
	}
}
function deletePlaylist($this){
	var playlistId = $('.background-item-playlist').attr('id');
	if(typeof playlistId != 'undefined'){
		var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không'};
		var left = $($this).position().left - 55;
		var top = $($this).position().top;
		var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
				'left':left, 'top': top, 'jfunction':'ajaxDeletePlaylist.elcom'};
		showConfirm($this, options);
		$('.yes').on('click', function(){
			$('.question').fadeOut(300, function() {
				$('.question').remove();
			});
			$.post(options.jfunction,{data: playlistId}, function(){
				showGrowlMsg("Đã xóa thành công");
				// update UI
				addNewPlaylist($this);
				// remove item playlist
				$($this).trigger('remove').remove();
				// trigger daily first click after delete
				$('.playlist li').first().trigger('click');
				// scroll auto
				var container = $('.playlist'),scrollTo = $('.playlist li').first();
				container.animate({
					scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()
				});
			});
		});
	} else{
		showMessageDialogWarning('Cảnh Báo', 'Hãy chọn một DANH SÁCH trước khi xóa');
	}
}
function initContextMenuCreateDaily(selector){
    $.contextMenu({
        selector: selector,
        build: function ($trigger, e) {
            console.log(e);
			
            return {
                callback: function (e, options) {
                	if(e == 'add'){
						addPlaylistDaily();
					} if(e =='save'){
						savePlaylistDaily();
					} else if(e == 'delete'){
						deletePlaylistDaily();
					} 
					
                },
                items: items
            };
        }
		
    });
    var items = {
    	"add": {
                name: "Tạo mới",
                icon: "add"
            },
		"save": {
            name: "Lưu lịch phát",
            icon: "save"
        },
		"sep1": "---------",
        "delete": {
            name: "Xóa lịch",
            icon: "delete"
        }
        
    };
}

function initDailyScheduleClick(){
	$('.daily-schedule').on('click', function(){
		var $this = this;
		var id = $(this).attr('id');
		$('.daily-schedule').removeClass('background-item-daily');
		$(this).addClass('background-item-daily');
		/* var item = detailScheduleDaily($(this)); */
		$.post('ajaxDailyScheduleItem.elcom', {data: id}, function(response){
			/* $('#content-detail-dailyItem').empty(); */
			// delete button
			$('.button-bottom').trigger('remove').remove();
			// set $arrPlaylist
			$arrPlaylist.length = 0;
			if(response.playlist == null){
				return;
			}
			var length= response.playlist.length;
			var row = "";
			var playlistJS = [];
			var item = '';
			if(length == 0){
				$('.schedule-daily').empty();
				$('.schedule-daily').append('<tr class="schedule-daily-info">'+
											'<td><span style="font-weight:bold">Hãy kéo một danh sách từ bên trái vào '+
													'đây để tạo một lịch phát theo ngày</span></td>'+
											'</tr>');
				
				var divButton = "<div class='button-bottom'>" +
						"<a href='#javascript' class='bt_red' onclick='return btnDeleteItemDaily()'>" +
							"<span class='bt_red_lft'></span>" +
							"<strong>Xóa lịch</strong>" +
							"<span class='bt_red_r'></span>" +
						"</a>" +
						"<a href='#javascript' class='bt_blue btn-save-item-daily' onclick='return btnSaveItemDaily()'>" +
							"<span class='bt_blue_lft'></span>" +
							"<strong>Lưu lịch</strong>" +
							"<span class='bt_blue_r'></span>" +
						"</a>" +
						"<a href='#javascript' class='bt_green' onclick='return btnCreateDaily()'>" +
							"<span class='bt_green_lft'></span>" +
							"<strong>Tạo mới</strong>" +
							"<span class='bt_green_r'></span>" +
						"</a>" +
					"</div>";
				$('.playerofgroup').append(divButton);
				return;
			}
			
			for(var i = 0; i< length; i++){
				var name = response.playlist[i].name;
				var starttime = response.playlist[i].startTime;
				var endtime = response.playlist[i].endTime;
				var id = response.playlist[i].id;
				var idItem = response.playlist[i].idItem;
				var suffix = randomString();
				row = row + (rowPlaylistDropable(name, starttime,endtime, id, idItem, suffix));
				var jsonItem = {};
				jsonItem['name'] = name;
				jsonItem['timestart'] = starttime;
				jsonItem['timeend'] = endtime;
				jsonItem['id'] = id;
				jsonItem['iditem'] = idItem;
				jsonItem['suffix'] = suffix;
				$arrPlaylist.push(jsonItem);
			}

			$('.schedule-daily').empty();
			$('.schedule-daily').append(row);
			var divButton = "<div class='button-bottom'>" +
								"<a href='#javascript' class='bt_red' onclick='return btnDeleteItemDaily()'>" +
									"<span class='bt_red_lft'></span>" +
									"<strong>Xóa lịch</strong>" +
									"<span class='bt_red_r'></span>" +
								"</a>" +
								"<a href='#javascript' class='bt_blue btn-save-item-daily' onclick='return btnSaveItemDaily()'>" +
									"<span class='bt_blue_lft'></span>" +
									"<strong>Lưu lịch</strong>" +
									"<span class='bt_blue_r'></span>" +
								"</a>" +
								"<a href='#javascript' class='bt_green' onclick='return btnCreateDaily()'>" +
									"<span class='bt_green_lft'></span>" +
									"<strong>Tạo mới</strong>" +
									"<span class='bt_green_r'></span>" +
								"</a>" +
							"</div>";
			$('.playerofgroup').append(divButton);
			initTimePicker();
		});
	});
}

function initPeriodicScheduleClick(){
	$('.group-periodic').each(function(index, element) {
		$(this).on('click', function(){
			$('#schedule-daily').hide();
			$('.group-periodic-parent').removeClass('group-periodic-fix');
			$('.group-periodic').removeClass('background-item-periodic');
			$(this).addClass('background-item-periodic');
			var $this = this;
			var id = $(this).attr('id');
			$.post('ajaxPeriodicScheduleItem.elcom', {data: id}, function(response){
				$('#tab-schedule-periodic').empty();
				var length= response.schedulePeriodic.length;
				if(length == 0){
					$('#tab-schedule-periodic').append(
						'<div id="msg" class="info_box" style="width: 66%; margin-left: 30px;">'+
							'Nhóm chưa có lịch định kì. '+
							'Hãy tạo <a href="javascript:void(0)" style="font-weight:bold;text-decoration:none" onclick="return createShedulePeriodic('+response.data+')">LỊCH ĐỊNH KÌ</a> cho nhóm.'+
						'</div>');
				} else {
					var id = response.schedulePeriodic[0].id;
					var name = response.schedulePeriodic[0].nameFull;
					var startdate = dateToString(response.schedulePeriodic[0].startTime);
					var enddate = dateToString(response.schedulePeriodic[0].endTime);
					var time = response.schedulePeriodic[0].time;
					$('#tab-schedule-periodic').empty();
					$('#tab-schedule-periodic').append(periodicSchedule(id,name, time, startdate, enddate));
					initDatePicker();
				}
			});
		});
	});
}
function changeNameDaily($this){
	var id = "";
	id = $('.background-item-daily').attr('id');
	if(id != "" && typeof id != 'undefined'){
		// show dialog name
		$('.title-dialog').text('Cập Nhật Lịch Ngày');
		$('#updatedailyname').val($.trim($('.background-item-daily').text()));
		$.blockUI({ 
			message: $('.dialog-updatedaily'),
			css: { 
				padding:0, 
				margin:0, 
				width:'65%', 
				top:'10%', 
				left:'50%', 
				textAlign:'center', 
				background: 'transparent',
				border: 'none',
				cursor:'progress' 
			},
			cursor:'progress'
			// onOverlayClick: $.unblockUI
		}); 
		} else {
			showMessageDialogWarning('Cảnh Báo', 'Hãy chọn một LỊCH NGÀY trước khi thực hiện chức năng này.');
		}
}
function deleteDaily($this){
	var id = "";
	id = $('.background-item-daily').attr('id');
	if(id != "" && typeof id != 'undefined'){
		var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không'};
		var left = $($this).position().left - 65;
		var top = $($this).position().top;
		var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
				'left':left, 'top': top, 'jfunction':'ajaxDeleteDaily.elcom'};
		showConfirm($this, options);
		$('.yes').on('click', function(){
			$('.question').fadeOut(300, function() {
				$('.question').remove();
			});
			$.post(options.jfunction,{data: id}, function(){
				showGrowlMsg("Đã xóa thành công");
				// update $arrPlaylist
				$arrPlaylist.length = 0;
				// update UI
				$('.background-item-daily').trigger('remove').remove();
				$('.schedule-daily').empty();
				$('.schedule-daily').append('<tr class="schedule-daily-info">'+
											'<td><span style="font-weight:bold">Hãy kéo một danh sách từ bên trái vào '+
													'đây để tạo một lịch phát theo ngày</span></td>'+
											'</tr>');
			});
		});
	} else {
		showMessageDialogWarning('Cảnh Báo', 'Hãy chọn một LỊCH NGÀY trước khi thực hiện chức năng này.');
	}
	return false;
}

function updateSchedulePeriodicItem(periodicId){
	// check error
	var name = $('.scheduleperiodic-' + periodicId).val();
	var startdate = $('.startdate-' + periodicId).val();
	var enddate = $('.enddate-' + periodicId).val();
	if(name =='' || name == null){
		showMessageDialogError("Lỗi", "Tên LỊCH ĐỊNH KÌ là bắt buộc");
		return false;
	}
	if(startdate == '' || enddate == ''){
		showMessageDialogError("Lỗi", "Hãy thiết lập ngày.");
		return false;
	}
	if(startdate == '' || enddate == ''){
		showMessageDialogError("Lỗi", "Hãy thiết lập ngày.");
		return false;
	}
	if(fnCompareDate(enddate, startdate)){
	    showMessageDialogError("Lỗi", "Ngày bắt đầu > Ngày kết thúc");
	    return false;
	}
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	if(date.getDate() < 10){
		d = '0' + date.getDate();
	}
	if((date.getMonth() + 1) < 10){
		m = '0' + (date.getMonth() + 1);
	}
	var current = d + '/' + m + '/' + date.getFullYear();
	if(fnCompareDate(enddate, current)){
		showMessageDialogError("Lỗi", "Ngày kết thúc < Ngày hiện tại");
	    return false;
	}
	//
	var time = $('.scheduleperiodic-' + periodicId).attr('data-time');
	$('.dialog-upload-content').attr('data-value', periodicId);
	$('.note').empty();
	var txt = 'Chú ý: Thời gian tải nội dung phải trước ' + time;
	//$('.note').append(txt);
	$('.dialog-upload-content').attr('data-time', time);
	$.blockUI({ 
		message: $('.dialog-upload-content'),
		css: { 
			padding:0, 
			margin:0, 
			width:'65%', 
			top:'30%', 
			left:'45%',
			textAlign:'center', 
			background: 'transparent',
			border: 'none',
			cursor:'progress' 
		},
		cursor:'progress'
	}); 
	return false;
}
function fnCompareDate(dateFrom, dateTo){
	var from = dateFrom.split('/');
	var to = dateTo.split('/');
	if(new Date(from[2], from[1] - 1, from[0]) < new Date(to[2], to[1] - 1, to[0])){
	   return true;
	}
	return false;
}
function acceptUpdatePeriodic(){
	var type = $('.dialog-upload-content').attr('data-type');
	if(type == "SAVE"){
		var idDaily = $('.dialog-upload-content').attr('data-value');
		var time = $('.dialog-upload-content').attr('data-time');
		var groupId = $('.schedule-periodic-info').attr('id');
		var name = $('.scheduleperiodic-' + idDaily).val();
		var startdate = $('.startdate-' + idDaily).val();
		var enddate = $('.enddate-' + idDaily).val();
		if(name =='' || name == null){
			showMessageDialogError("Lỗi", "Tên LỊCH ĐỊNH KÌ là bắt buộc");
			return false;
		}
		if(startdate == '' || enddate == ''){
			showMessageDialogError("Lỗi", "Hãy thiết lập ngày.");
			return false;
		}
		if(Date.parse(enddate) < Date.parse(startdate)){
		    showMessageDialogError("Lỗi", "Ngày bắt đầu > Ngày kết thúc");
		    return false;
		}
		var dateTime = '';
		if($('.radio-default').is(':checked')){
			dateTime= '-1';
		} else {
			 dateTime = toSecond($('.radio-custom').attr('value'));
		}
//		var dateTime = toSecond($('.radio-custom').attr('value'));
//		if($('.radio-custom').is(':checked')){
//			if(dateTime > toSecond(time)){
//				showMessageDialogError("Lỗi", "Thời gian không hợp lệ");
//				return false;
//			}
//		}
		var item = {'groupid': groupId, 'dailyid': idDaily.toString(), 'name': name,'time': dateTime.toString(), 'startdate': startdate, 'enddate':enddate };
		var obj = JSON.stringify(item);
		$.post('ajaxSaveSchedulePeriodic.elcom',{data: obj}, function(responseP){
			showGrowlMsg("Lưu thành công");
			$('#' + responseP.data).on('click', function(){
				$('#schedule-daily').hide();
				$('.group-periodic-parent').removeClass('group-periodic-fix');
				$.post('ajaxPeriodicScheduleItem.elcom', {data: responseP.data}, function(response){
					$('#tab-schedule-periodic').empty();
					var id = response.schedulePeriodic[0].id;
					var name = response.schedulePeriodic[0].nameFull;
					var startdate = dateToString(response.schedulePeriodic[0].startTime);
					var enddate = dateToString(response.schedulePeriodic[0].endTime);
					var time = response.schedulePeriodic[0].time;
					$('#tab-schedule-periodic').empty();
					$('#tab-schedule-periodic').append(periodicSchedule(id,name, time, startdate, enddate));
					initDatePicker();
				});
			});
			$('#' + responseP.data).trigger('click');
		});
		return false;
	} else {
		var periodicId = $('.dialog-upload-content').attr('data-value');
		var time = $('.dialog-upload-content').attr('data-time');
		var idGroup = $('.background-item-periodic').attr('id');
		var name = $('.scheduleperiodic-' + periodicId).val();
		var startdate = $('.startdate-' + periodicId).val();
		var enddate = $('.enddate-' + periodicId).val();
		var dataTime = '';
		if($('.radio-default').is(':checked')){
			dataTime= '-1';
		} else {
			dataTime = toSecond($('.radio-custom').attr('value'));
		}
//		var dataTime = $('.radio-custom').attr('value');
//		if($('.radio-custom').is(':checked')){
//			if(toSecond(dataTime) > toSecond(time)){
//				showMessageDialogError("Lỗi", "Thời gian không hợp lệ");
//				return false;
//			}
//			dataTime = toSecond(dataTime);
//		}
		var item = {'id': idGroup, 'periodicid': periodicId.toString(), 'name': name, 'time': dataTime.toString(), 'startdate': startdate, 'enddate':enddate };
		var obj = JSON.stringify(item);
		$.post('ajaxUpdatePeriodic.elcom',{data:obj}, function(response){
			showGrowlMsg("Cập nhật thành công");
		});
		return false;
	}
	
}
function deleteSchedulePeriodicItem(periodicId, $this){
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không'};
	var left = $($this).position().left - 125;
	var top = $($this).position().top;
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
			'left':left, 'top': top, 'jfunction':'ajaxDeletePeriodic.elcom'};
	showConfirm($this, options);
	$('.yes').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
		var groupid = $('.background-item-periodic').attr('id');
		var json = {'groupid': groupid, 'periodicid': periodicId.toString()};
		var obj = JSON.stringify(json);
		$.post(options.jfunction,{data: obj}, function(){
			showGrowlMsg("Đã xóa thành công");
			periodicAfterDelete();
			$('.background-item-periodic').trigger('click');
		});
	});
	return false;
}
function periodicAfterDelete(){
	$('#tab-schedule-periodic').empty();
	$('#tab-schedule-periodic').append('<div id="msg" class="info_box" style="width: 66%; margin-left: 30px;">Hãy chọn nhóm để thực hiện chức năng này.</div>');
}
function dateToString(date){
	var d = date.split('-');
	return (d[2] + '/' + d[1] + '/' + d[0]);
}
function createShedulePeriodic(groupId){
	$('#tab-schedule-periodic').empty();
	$('#tab-schedule-periodic').append(periodicScheduleCreate(groupId));
	$('#schedule-daily').show();
	$('.daily-schedule-parent').removeClass('daily-schedule-fix');
	$('.group-periodic-parent').addClass('group-periodic-fix');
	initDroppablePeriodic('.schedule-periodic-info', '.daily-schedule');
	return false;
}
function addPlaylistDaily(){
	$('.schedule-daily').empty();
	$('.schedule-daily').append('<tr class="schedule-daily-info">'+
								'<td><span style="font-weight:bold">Hãy kéo một danh sách từ bên trái vào'+
										' đây để tạo một lịch phát theo ngày</span></td>'+
								'</tr>');
	$('.daily-schedule').removeClass('background-item-daily');
	// update arrPlaylist
	$arrPlaylist.length = 0;
}
function savePlaylistDaily(){
	if($arrPlaylist.length > 0){
		$.blockUI({ 
			message: '<h2 style="color:#fff">Đang lưu...</h2>',
			css: { 
            border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        } });
		var idDaily = $('.background-item-daily').attr('id');
		var jsPlaylist = {"id": idDaily, "items": $arrPlaylist};
		var jsonStr = JSON.stringify(jsPlaylist);
		// Update
		if(typeof idDaily!= 'undefined'){
			$.post('ajaxUpdateScheduleDailyItem.elcom',{data: jsonStr}, function(response){
				if(response.data != null){
					var id = response.data.substring(0, (response.data.length -1));
					var str = id.split(';');
					var length = str.length;
					for(var  i =0; i < length; i++){
						$arrPlaylist[i].iditem = str[i];
						var clazz = '.scheduledailyItem-' + $arrPlaylist[i].id + $arrPlaylist[i].suffix;
						$(clazz).parent().parent().attr('iditem', str[i]);
						$(clazz).parent().parent().removeClass('-1');
						$(clazz).parent().parent().addClass(str[i]);
						$(clazz).parent().parent().find('td a').attr('data-iditem', str[i]);
					}
				}
				showGrowlMsg('Cập nhật thành công');
				$(document).ajaxStop(function(){
					$.unblockUI;
				});	
				// $('.background-item-daily').trigger('click');
			});
		} else{
			// show dialog name
			$('.title-dialog').text('Lưu Lịch Ngày');
			$.blockUI({ 
				message: $('.dialog-savedaily'),
				css: { 
					padding:0, 
					margin:0, 
					width:'65%', 
					top:'10%', 
					left:'50%', 
					textAlign:'center', 
					background: 'transparent',
					border: 'none',
					cursor:'progress' 
				},
				cursor:'progress'
				// onOverlayClick: $.unblockUI
			}); 
		}
	
	} else {
		showMessageDialogWarning('Cảnh Báo', 'Hãy kéo một danh sách từ bên trái vào đây để tạo một lịch phát theo ngày');
	}
}
function deletePlaylistDaily(){
	$('.schedule-daily').empty();
	var item = "<tr class='schedule-daily-info'>"+
					"<td><span style='font-weight:bold'>Hãy kéo một danh sách từ bên trái vào " +
								"đây để tạo một lịch phát theo ngày</span></td>"+
				"</tr>";
	$('.schedule-daily').append(item);
	$arrPlaylist.length = 0;
}
function btnCreateDaily(){
	addPlaylistDaily();
	return false;
}
function btnSaveItemDaily(){
	savePlaylistDaily();
	return false;
}
function btnDeleteItemDaily(){
	deletePlaylistDaily();
	return false;
}
function checkExist(id){
	var _length = $arrPlaylist.length;
	for(var i = 0; i < _length; i++){
		if($arrPlaylist[i].id == id){
			return true;
		}
	}
	return false;
}
function detailScheduleDaily($this){
	var item = "<div class='playerofgroup' style='width: 99%;'>"+
					"<table>"+
					"<thead>"+
						"<tr>"+
							"<th>Tên</th>"+
							"<th>Ngày Tạo</th>"+
							"<th>Mô Tả</th>"+
							"<th>Cập Nhật</th>" +
						"</tr>"+
					"</thead>"+
					"<tbody id='scrolling' class='schedule-daily-detail'>"+
						"<tr>"+
							"<td><input class='scheduledaily-"+$this.attr('id')+"' type='text' style='width: 100px; font-size: 15px;' value='"+$this.attr('title')+"' />"+
							"</td>"+
							"<td>"+$this.attr('createdate')+"</td>"+
							"<td title='"+$this.attr('description-full')+"'>"+$this.attr('description')+"</td>"+
							"<td style='text-align:center;'>" + 
							  "<a href='#' title='Cập nhật' onclick='return updateScheduleDaily("+$this.attr('id')+")'>" + 
								"<img src='css/images/page_white_update.png'/>" +
							  "</a>" +
							"</td>" +
						"</tr>"+
					"</tbody>"+
				"</table>"+
				"</div>"+
				"<div style='clear: both;'>&nbsp;</div>";
	return item;
}

function deleteScheduleDailyPlaylistItem($this){
	var idPlaylist = $($this).attr('data-id');
	var iditem = $($this).attr('data-iditem');
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không'};
	var left = $($this).position().left - 125;
	var top = $($this).position().top;
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
			'left':left, 'top': top, 'jfunction':'ajaxDeleteItemDaily.elcom'};
	showConfirm($this, options);
	
	$('.yes').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
		// var iditem = $('.' + idPlaylist).attr('iditem');
		if(iditem != '-1'){
			$.post(options.jfunction,{data: iditem}, function(response){
				showGrowlMsg("Đã xóa thành công");
				// update UI
				var length = $arrPlaylist.length;
				for(var  i= 0; i < length; i++){
					if(($arrPlaylist[i].id + $arrPlaylist[i].suffix)  == idPlaylist && $arrPlaylist[i].iditem == iditem){
						$arrPlaylist.splice(i, 1);
						if(length == 1){
							$('.schedule-daily').empty();
							var item = "<tr class='schedule-daily-info'>"+
											"<td><span style='font-weight:bold'>Hãy kéo một danh sách từ bên trái vào " +
														"đây để tạo một lịch phát theo ngày</span></td>"+
										"</tr>";
							$('.schedule-daily').append(item);
						} else {
							// update UI
							$('.' + iditem).trigger('remove').remove();
						}
						return false;
					}
				}
				return false;
			});
		} else{
			showGrowlMsg("Đã xóa thành công");
			// update UI
			var length = $arrPlaylist.length;
			for(var  i= 0; i < length; i++){
				if(($arrPlaylist[i].id + $arrPlaylist[i].suffix)  == idPlaylist && $arrPlaylist[i].iditem == iditem){
					$arrPlaylist.splice(i, 1);
					if(length == 1){
						$('.schedule-daily').empty();
						var item = "<tr class='schedule-daily-info'>"+
										"<td><span style='font-weight:bold'>Hãy kéo một danh sách từ bên trái vào " +
													"đây để tạo một lịch phát theo ngày</span></td>"+
									"</tr>";
						$('.schedule-daily').append(item);
					} else {
						// update UI
						$('.' + iditem).trigger('remove').remove();
					}
					return false;
				}
			}
		}
		
	});
	return false;
}
function updateScheduleDailyName(){
	var id = $('.background-item-daily').attr('id');
	var text = $('#updatedailyname').val();
	var desc = $('#daily-description-update').val();
	if(text == "" || text == null){
		var msg = 'Tên lịch là bắt buộc';
		title = ('Lỗi');
		showMessageDialogError(title, msg);
		return false;
	}
	var json = {'id': id, 'name': text, 'description': desc};

	var schedule = JSON.stringify(json);
	$.post('ajaxUpdateScheduleDailyName.elcom', {data: schedule}, function(){
		var msg = 'Cập nhật thành công';
		text = $('#updatedailyname').val();
		$('.background-item-daily').text(text);
		$('.background-item-daily').attr('title', text);
		unLockUI();
		showGrowlMsg(msg);
		
	});
	return false;
}
function showMessageDialogError(title, msg){
	
	$('.dialog-msg-content').empty();
	$('.dialog-msg-content').append(msg);
	$('.title-dialog').text(title);
	$.blockUI({
		message: $('.dialog-msg'),
		css: { 
			padding:0, 
			margin:0, 
			width:'65%', 
			top:'10%', 
			left:'50%', 
			textAlign:'center', 
			background: 'transparent',
			border: 'none',
			cursor:'progress' 
		},
		cursor:'progress',
		onOverlayClick: $.unblockUI
	}); 
	window.setTimeout($.unblockUI, 2000);
}
function updateScheduleDailyPlaylistItem(id){
	var name = $('.scheduledailyItem-' + id).val();
	var startTime = $('.starttime-' + id).val();
	var endTime = $('.endtime-' + id).val();
	if(!checkValidateTime(startTime, endTime, id)){
		$('.' + id).addClass('color-error');
	} else {
		$('.' + id).removeClass('color-error');
		var _length = $scheduleDailyJSON.items.length;
		for(var i =0; i < _length; i++){
			if($scheduleDailyJSON.items[i].id == id){
				$scheduleDailyJSON.items[i].name = name;
				$scheduleDailyJSON.items[i].timestart = startTime;
				$scheduleDailyJSON.items[i].timeend = endTime;
			}
		}
		showGrowlMsg('Cập nhật thành công'); 
	}
	return false;
}
function showGrowlMsg(msg){
	$('.growlUI').empty();
	$('.growlUI').append('<h1>'+msg+'</h1>');
	 $.blockUI({ 
            message: $('div.growlUI'), 
            fadeIn: 700, 
            fadeOut: 700, 
            timeout: 5000, 
            showOverlay: false, 
            centerY: false, 
            css: { 
                width: '250px', 
                top: '10px', 
                left: '', 
                right: '10px', 
                border: 'none', 
                padding: '5px', 
                backgroundColor: '#000', 
                '-webkit-border-radius': '10px', 
                '-moz-border-radius': '10px', 
                opacity: 0.8, 
                color: '#fff' 
            } 
        });
}
function updateCreateScheduleDaily(id){
	var startTime = $('.starttime-' + id).val();
	var endTime = $('.endtime-' + id).val();
	if(!checkValidateTime(startTime, endTime, id)){
		$('.' + id).addClass('color-error');
	} else {
		$('.' + id).removeClass('color-error');
		var length = $arrPlaylist.length;
		// update playlist
		for(var i = 0; i < length; i++){
			if($arrPlaylist[i].id == id){
				$arrPlaylist[i].timestart = starttime;
				$arrPlaylist[i].timeend = endtime;
			}
		}
		showGrowlMsg('Đã cập nhật');
	}
	return false;
}
function checkValidateTime(starttime, endtime, id){
	var title = (subString($.trim($('.' + id).attr('title'))));
	var length = $arrPlaylist.length;
	var startSecond = toSecond(starttime);
	var endSecond = toSecond(endtime);
	if(startSecond == 0 || endSecond == 0){
		var msg = 'Thời gian bắt buộc nhập';
		showMessageDialogError(title, msg);
		return false;
	}
	if(startSecond == endSecond){
		var msg = 'Thời gian: bắt đầu = kết thúc';
		showMessageDialogError(title, msg);
		return false;
	}
	if(startSecond > endSecond){
		var msg = 'Thời gian: bắt đầu > kết thúc';
		showMessageDialogError(title, msg);
		return false;
	}
	for(var i = 0; i < length; i++){
		if($arrPlaylist[i].id != id && startSecond >= toSecond($arrPlaylist[i].timestart) 
				&& startSecond <= toSecond($arrPlaylist[i].timeend)){
			var msg = 'Thời gian bị trùng với danh sách khác';
			showMessageDialogError(title, msg);
			return false;
		}
		if($arrPlaylist[i].id != id && startSecond <= toSecond($arrPlaylist[i].timestart) 
				&& endSecond >= toSecond($arrPlaylist[i].timestart)){
			var msg = 'Thời gian bị trùng với danh sách khác';
			showMessageDialogError(title, msg);
			return false;
		}
	}
	
	return true;
}
function playlistSchedule(row){
	var item = "<!--Add new schedule daily-->"+
				"<div class='playerofgroup' style='width: 99%;'>"+
				"<table>"+
					"<thead>"+
						"<tr>"+
							"<th>Tên</th>"+
							"<th>Bắt Đầu</th>"+
							"<th>Kết Thúc</th>"+
							"<th>Cập Nhật</th>"+
							"<th>Xóa</th>"+
						"</tr>"+
					"</thead>"+
					"<tbody id='scrolling'>"+
						row + 
					"</tbody>"+
				"</table>"+
				"</div>"+
				"<br /><br />";
	return item;
}
function periodicSchedule(id, name, time, startdate, enddate){
	var item = "<!--Add new schedule daily-->"+
				"<div class='playerofgroup' style='width: 99%;'>"+
				"<table>"+
					"<thead>"+
						"<tr>"+
							"<th>Tên</th>"+
							"<th>Bắt Đầu</th>"+
							"<th>Kết Thúc</th>"+
							"<th>Cập Nhật</th>"+
							"<th>Xóa</th>"+
						"</tr>"+
					"</thead>"+
					"<tbody>"+
					"<tr class='"+id+"'>"+
					"<td title='"+name+"' class='"+id+"'>"+
						"<input data-time='"+time+"' class='scheduleperiodic-"+id+"' type='text' style='width: 100px; font-size: 15px;' value='"+name+"'>" +
					"</td>"+
					"<td style='text-align: center'>"+
						"<div class='input-append date'>"+
							"<input class='startdate-"+id+"' style='width: 80px' data-format='yyyy-MM-dd' value='"+startdate+"' type='text'></input>"+
							"<span class='add-on'>"+ "<i data-date-icon='icon-calendar'></i>"+
							"</span>"+
						"</div>"+"</td>"+
						"<td style='text-align: center'>"+
							"<div class='input-append date'>"+
								"<input class='enddate-"+id+"' style='width: 80px' value='"+enddate+"' data-format='yyyy-MM-dd' type='text'>"+"</input>"+
								"<span class='add-on'>"+ "<i data-date-icon='icon-calendar'></i>"+
								"</span>"+
							"</div>"+"</td>"+
						"<td style='text-align:center;'>" + 
							  "<a href='#' title='Cập nhật' onclick='return updateSchedulePeriodicItem("+id+")'>" + 
								"<img src='css/images/page_white_update.png'/>" +
							  "</a>" +
							"</td>" +
						"<td style='text-align: center;'>"+"<a href='#' onclick='return deleteSchedulePeriodicItem("+id+", this)' title='Xóa'>"+
								"<img src='css/images/trash.png'>"+ "</a></td>"+
					"</tr>" +
					"</tbody>"+
				"</table>"+
				"</div>"+
				"<br /><br />";
	return item;
}
function periodicScheduleCreate(groupId){
	var item = "<!--Add new schedule daily-->"+
				"<div class='playerofgroup' style='width: 99%;'>"+
				"<table>"+
					"<thead>"+
						"<tr>"+
							"<th>Tên</th>"+
							"<th>Bắt Đầu</th>"+
							"<th>Kết Thúc</th>"+
							"<th>Lưu</th>"+							
						"</tr>"+
					"</thead>"+
					"<tbody id='scrolling'>"+
						"<tr class='schedule-periodic-info' id='"+groupId+"'>"+
							"<td><span style='font-weight:bold'>Hãy kéo một LỊCH NGÀY từ bên trái vào đây để tạo LỊCH ĐỊNH KÌ</span></td>"+
						"</tr>" +
					"</tbody>"+
				"</table>"+
				"</div>"+
				"<br /><br />";
	return item;
}

function saveSchedulePeriodic(idDaily){
	// check error
	var groupId = $('.schedule-periodic-info').attr('id');
	var name = $('.scheduleperiodic-' + idDaily).val();
	var startdate = $('.startdate-' + idDaily).val();
	var enddate = $('.enddate-' + idDaily).val();
	if(name =='' || name == null){
		showMessageDialogError("Lỗi", "Tên LỊCH ĐỊNH KÌ là bắt buộc");
		return false;
	}
	if(startdate == '' || enddate == ''){
		showMessageDialogError("Lỗi", "Hãy thiết lập ngày.");
		return false;
	}
	if(fnCompareDate(enddate, startdate)){
	    showMessageDialogError("Lỗi", "Ngày bắt đầu > Ngày kết thúc");
	    return false;
	}
	
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	if(date.getDate() < 10){
		d = '0' + date.getDate();
	}
	if((date.getMonth() + 1) < 10){
		m = '0' + (date.getMonth() + 1);
	}
	var current = d + '/' + m + '/' + date.getFullYear();
	if(fnCompareDate(enddate, current)){
		showMessageDialogError("Lỗi", "Ngày kết thúc < Ngày hiện tại");
	    return false;
	}
	//
	var dt = new Date();
	var time = $('.scheduleperiodic-' + idDaily).attr('data-time');
	$('.dialog-upload-content').attr('data-value', idDaily);
	$('.note').empty();
	var txt = 'Chú ý: Thời gian tải nội dung phải trước ' + time;
	//$('.note').append(txt);
	$('.dialog-upload-content').attr('data-time', time);
	$('.dialog-upload-content').attr('data-type', "SAVE");
	$.blockUI({ 
		message: $('.dialog-upload-content'),
		css: { 
			padding:0, 
			margin:0, 
			width:'65%', 
			top:'30%', 
			left:'45%',
			textAlign:'center', 
			background: 'transparent',
			border: 'none',
			cursor:'progress' 
		},
		cursor:'progress'
	}); 
	return false;
}
function acceptPlaylist(){
	var name = $.trim($('.title-dialog').attr('title'));
	var starttime = $('#starttime-dialog').val();
	var endtime = $('#endtime-dialog').val();
	if(!checkValidateTimeDialog(starttime, endtime)){
		return false;
	}
	var id = $('.title-dialog').attr('id-clone');
	var suffix = randomString();
	var item = rowPlaylistDropable(name, starttime, endtime, id, '-1', suffix);
	$('.schedule-daily').find('.schedule-daily-info').remove();
	var json = {"id":id,"iditem":'-1', "name": name,"timestart": starttime, "timeend": endtime, 'suffix': suffix};
	$arrPlaylist.push(json);
	$('.schedule-daily').append(item);
	initTimePicker();
	unLockUI();
	return false;
}
function checkValidateTimeDialog(starttime, endtime){
	var length = $arrPlaylist.length;
	var startSecond = toSecond(starttime);
	var endSecond = toSecond(endtime);
	if(startSecond == 0 || endSecond == 0){
		var msg = 'Thời gian bắt buộc nhập';
		$('.title-dialog').after(message(msg));
		return false;
	}
	if(startSecond == endSecond){
		var msg = 'Thời gian: bắt đầu = kết thúc';
		$('.title-dialog').after(message(msg));
		return false;
	}
	if(startSecond > endSecond){
		var msg = 'Thời gian: bắt đầu > kết thúc';
		$('.title-dialog').after(message(msg));
		return false;
	}
	for(var i = 0; i < length; i++){
		if(startSecond >= toSecond($arrPlaylist[i].timestart) 
				&& startSecond <= toSecond($arrPlaylist[i].timeend)){
			var msg = 'Thời gian bị trùng với danh sách khác';
			$('.title-dialog').after(message(msg));
			return false;
		}
		if(startSecond <= toSecond($arrPlaylist[i].timestart) 
				&& endSecond >= toSecond($arrPlaylist[i].timestart)){
			var msg = 'Thời gian bị trùng với danh sách khác';
			$('.title-dialog').after(message(msg));
			return false;
		}
	}
	return true;
}
function message(msg){
	$('.dialog-timepicker').css({
		'height': 205
	});
	var item = "<span class='msg-error'>"+msg+"</span> <br/>";
	window.setTimeout(function(){
		$('.title-dialog').next('.msg-error').remove();
		$("br").remove();
		$('.dialog-timepicker').css({
			'height': 190
		});
	}, 2000);
	return item;
}
function randomString() {
	var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
	var string_length = 5;
	var randomstring = '';
	for (var i=0; i< string_length; i++) {
		var rnum = Math.floor(Math.random() * chars.length);
		randomstring += chars.substring(rnum,rnum+1);
	}
	return randomstring;
}
function rowPlaylistDropable(name, starttime, endtime, id, iditem, suffix){
	id = id + suffix;
	var onblur = 'blurItemDaily(this)';
	var onfocus = 'focusItemDaily(this)';
	var item = "<tr iditem='"+iditem+"' class='"+id+" "+iditem+"'>"+
					"<td class='"+id+"'>"+
						"<input required='required' onblur='"+onblur+"' onfocus='"+onfocus+"' class='scheduledailyItem-"+id+"' type='text' style='width: 100px; font-size: 15px;' value='"+name+"'></td>" +
					"<td style='text-align: center'>"+
						"<div class='input-append time'>"+
							"<input data-id='"+id+"' maxlength='8' required='required'  onblur='blurStartTimeDaily(this)' onfocus='focusStartTimeDaily(this)' class='starttime-"+id+"' style='width: 60px' data-format='hh:mm:ss' value='"+starttime+"' type='text'></input>"+
							"<span class='add-on'>"+ "<i data-time-icon='icon-time'></i>"+
							"</span>"+
						"</div>"+"</td>"+
					"<td style='text-align: center'>"+
						"<div class='input-append time'>"+
							"<input data-id='"+id+"' maxlength='8' required='required'  onblur='blurEndTimeDaily(this)' onfocus='focusEndTimeDaily(this)' class='endtime-"+id+"' style='width: 60px' value='"+endtime+"' data-format='hh:mm:ss' type='text'>"+"</input>"+
							"<span class='add-on'>"+ "<i data-time-icon='icon-time'>"+"</i>"+
							"</span>"+
						"</div>"+"</td>"+
					/*
					 * "<td style='text-align:center;'>" + "<a href='#'
					 * title='Cập nhật' onclick='return
					 * updateCreateScheduleDaily("+id+")'>" + "<img
					 * src='css/images/page_white_update.png'/>" + "</a>" + "</td>" +
					 */
					"<td style='text-align: center;'>"+"<a href='#' data-iditem='"+iditem+"' data-id='"+id+"' onclick='return deleteScheduleDailyPlaylistItem(this)' title='Xóa' >"+
							"<img src='css/images/trash.png'>"+ "</a></td>"+
				
				"</tr>";
	return item;
}
function changeTimeStartScheduleDaily(id){
	var timestart = toSecond($('.starttime-' + id).val());
	var timeend = toSecond($('.endtime-' + id).val());
	if(timestart > timeend){
		msg = "Thời gian: bắt đầu > kết thúc";
		messeageError('.starttime-' + id, msg);
		return;
	} else {
		if(checkValidateTimeDaily(id, $('.starttime-' + id).val(), $('.endtime-' + id).val())){
			var length = $arrPlaylist.length;
			for(var i = 0; i < length; i++){
				if($arrPlaylist[i].id == id){
					$arrPlaylist[i].timestart= $('.starttime-' + id).val();
				}
			}
		} 
	}
}
function changeTimeEndScheduleDaily(id){
	var timestart = toSecond($('.starttime-' + id).val());
	var timeend = toSecond($('.endtime-' + id).val());
	if(timeend < timestart){
		msg = "Thời gian:kết thúc < bắt đầu";
		messeageError('.endtime-' + id, msg);
		return;
	} else {
		if(checkValidateTimeDaily(id, $('.starttime-' + id).val(), $('.endtime-' + id).val())){
			var length = $arrPlaylist.length;
			for(var i = 0; i < length; i++){
				if($arrPlaylist[i].id == id){
					$arrPlaylist[i].timeend = $('.endtime-' + id).val();
				}
			}
		}
	}
}
function blurItemDaily($this){
	var name = $($this).val();
	var id = $($this).parent().attr('class');
	if(name == ""){
		var msg = "Tên thuộc tính là bắt buộc";
		messeageError('.scheduledailyItem-' + id, msg);
		return;
	} else {
		var _length = $arrPlaylist.length;
		for(var i = 0; i < _length; i++){
			if(id == ($arrPlaylist[i].id + $arrPlaylist[i].suffix)){
				$arrPlaylist[i].name = name;
			}
		}
	}
}
function focusItemDaily($this){
	var id = $($this).parent().attr('class');
	if($('.scheduledailyItem-' + id).next('.error').length > 0){
		$('.scheduledailyItem-' + id).next('.error').remove();
	}
}
function blurStartTimeDaily($this){
	var msg = 'Vui lòng nhập thời gian'+'<br/>'  +
	'Theo định dạng: giờ:phút:giây' + '</br>' +
	'Ví dụ: 00:20:01' ;
	var clazz = '.' + $($this).attr('class');
	var id = $($this).attr('data-id');
	var val = $($this).val();
	if(!/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(val)){
		messeageError(clazz, msg);
		return;
	} else{
		var timestart = toSecond($($this).val());
		var timeend = toSecond($('.endtime-' + id).val());
		if(timestart > timeend){
			msg = "Thời gian: bắt đầu > kết thúc";
			messeageError('.starttime-' + id, msg);
			return;
		} else {
			if(checkValidateTimeDaily(id, $('.starttime-' + id).val(), $('.endtime-' + id).val())){
				var length = $arrPlaylist.length;
				for(var i = 0; i < length; i++){
					if($arrPlaylist[i].id == id){
						$arrPlaylist[i].timestart= $('.starttime-' + id).val();
					}
				}
			} /*
				 * else { // update time orginal var length =
				 * $arrPlaylist.length; for(var i = 0; i < length; i++){
				 * if($arrPlaylist[i].id == id){ $('.starttime-' +
				 * id).val($arrPlaylist[i].timestart); } } }
				 */
		}
	}
}

function focusStartTimeDaily($this){
	var id = $($this).attr('data-id');
	if($('.starttime-' + id).next('.error').length > 0){
		$('.starttime-' + id).next('.error').remove();
	}
}
function blurEndTimeDaily($this){
	var msg = 'Vui lòng nhập thời gian'+'<br/>'  +
	'Theo định dạng: giờ:phút:giây' + '</br>' +
	'Ví dụ: 00:20:01' ;
	var id = $($this).attr('data-id');
	var val = $('.endtime-' + id).val();
	if(!/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(val)){
		messeageError('.endtime-' + id, msg);
		return;
	} else{
		var timestart = toSecond($('.starttime-' + id).val());
		var timeend = toSecond($('.endtime-' + id).val());
		if(timeend < timestart){
			msg = "Thời gian:kết thúc < bắt đầu";
			messeageError('.endtime-' + id, msg);
			return;
		} else {
			if(checkValidateTimeDaily(id, $('.starttime-' + id).val(), $('.endtime-' + id).val())){
				var length = $arrPlaylist.length;
				for(var i = 0; i < length; i++){
					if($arrPlaylist[i].id == id){
						$arrPlaylist[i].timeend = $('.endtime-' + id).val();
					}
				}
				return;
			} /*
				 * else { // update time orginal var length =
				 * $arrPlaylist.length; for(var i = 0; i < length; i++){
				 * if($arrPlaylist[i].id == id){ $('.endtime-' +
				 * id).val($arrPlaylist[i].timeend); } } }
				 */
		}
	}
	return;
}
function focusEndTimeDaily($this){
	var id = $($this).attr('data-value');
	if($('.endtime-' + id).next('.error').length > 0){
		$('.endtime-' + id).next('.error').remove();
	}
}
function checkValidateTimeDaily(id, starttime, endtime){
	var length = $arrPlaylist.length;
	var startSecond = toSecond(starttime);
	var endSecond = toSecond(endtime);

	for(var i = 0; i < length; i++){
		if(($arrPlaylist[i].id + $arrPlaylist[i].suffix) != id){
			if(startSecond >= toSecond($arrPlaylist[i].timestart) 
					&& startSecond <= toSecond($arrPlaylist[i].timeend)){
				var msg = 'Thời gian bị trùng với danh sách khác';
				showMessageDialogError("Lỗi", msg);
				return false;
			}
			if(startSecond <= toSecond($arrPlaylist[i].timestart) 
					&& endSecond >= toSecond($arrPlaylist[i].timestart)){
				var msg = 'Thời gian bị trùng với danh sách khác';
				showMessageDialogError("Lỗi", msg);
				return false;
			}
		}
		
	}
	return true;
}
function deletePlaylistItemDrop(id){
	var length = $arrPlaylist.length;
	for(var  i= 0; i < length; i++){
		if($arrPlaylist[i].id == id.toString()){
			$arrPlaylist.splice(i, 1);
			if(length == 1){
				$('.schedule-daily').empty();
				var item = "<tr class='schedule-daily-info'>"+
								"<td><span style='font-weight:bold'>Hãy kéo một danh sách từ bên trái vào " +
											"đây để tạo một lịch phát theo ngày</span></td>"+
							"</tr>";
				$('.schedule-daily').append(item);
			} else {
				// update UI
				$('.' + id).trigger('remove').remove();
			}
		}
	}
	
	return false;
}
function savePlaylistItem(){
	if($arrItem.length == 0){
		showMessageDialogError("Lỗi", "Hãy kéo nội dung bên trái vào bảng để tạo DANH SÁCH");
		return false;
	}
	// if is update show name playlist
	$('#playlistname').val($.trim($('.background-item-playlist').text()));
	$('.title-dialog').text('Lưu Danh Sách');
	$.blockUI({ 
		message: $('.dialog-saveplaylist'),
		css: { 
			padding:0, 
			margin:0, 
			width:'65%', 
			top:'10%', 
			left:'50%', 
			textAlign:'center', 
			background: 'transparent',
			border: 'none',
			cursor:'progress' 
		},
		cursor:'progress'
		// onOverlayClick: $.unblockUI
	}); 
	return false;
}
function checkInput(idElement){
	var name = $(idElement).val();
	if(name =='' || name == null){
		var _left = $(idElement).position().left;
		var _top = $(idElement).position().top;
		var _w = $(idElement).width();
		var div = '<div class="error">'+
						'Tên danh sách là bắt buộc'+'<br/>'  +
					'</div>';
		if($(idElement).next('.error').length <= 0){
			$(idElement).after($(div).css({
					'left': _left + _w,
					'top': _top,
					'opacity': 1
				}));
		}
		$(idElement).next('.error').animate({opacity: 1}, 300);
	} else{
		$(idElement).next('.error').remove();
	}
}
function focusRemoveMsg(idElement){
	$(idElement).next('.error').remove();
}
function checkvalidate(msg, idElement){
	var name = $(idElement).val();
	if(name =='' || name == null){
		var _left = $(idElement).position().left;
		var _top = $(idElement).position().top;
		var _w = $(idElement).width();
		var div = '<div class="error">'+
						msg+'<br/>'  +
					'</div>';
		if($(idElement).next('.error').length <= 0){
			$(idElement).after($(div).css({
					'left': _left + _w,
					'top': _top,
					'opacity': 1
				}));
		}
		$(idElement).next('.error').animate({opacity: 1}, 300);
		return false;
	}
	return true;
}
function savePlaylist(){
	if(!checkvalidate('Tên danh sách là bắt buộc', '#playlistname')){
		return false;
	}
	// update playlist when playlist click
	if($('.background-item-playlist').text() != ""){
		var playlistId = $('.background-item-playlist').attr('id');
		var name = $('#playlistname').val();
		var desc = $('#description').val();
		var jsonObj = [];
		var _length = $arrItem.length;
		var layoutid = $('#playlist-layout').val();
		for(var i = 0; i < _length; i++){
			var item = {};
			var timestart = toSecond($arrItem[i].attr('time-start'));
			var timetotal = toSecond($arrItem[i].attr('time-total'));
			var timeend = timetotal + timestart;
			item['name'] = $arrItem[i].attr('name-item');
			item['timestart'] = $arrItem[i].attr('time-start');
			item['timeend'] = toHHMMSS(timeend);
			item['idparent'] = layoutid;
			item['id'] = $arrItem[i].attr('item-id');
			item['idcontent'] = $arrItem[i].attr('id-content');
			item['iditem'] = $arrItem[i].attr('id-private');
			jsonObj.push(item);
		}
		_length = $arrPlaylistItemDelete.length;
		var idDel = "";
		for(var i = 0; i < _length; i++){
			idDel+= $arrPlaylistItemDelete[i].attr('id-private') + ";";
		}
		if(idDel.length > 0){
			idDel = idDel.substring(0, (idDel.length - 1));
		}
		var jsPlaylist = {"id": playlistId, "name": name, "description": desc, "items": jsonObj, "iditemdelete": idDel};
		var jsonStr = JSON.stringify(jsPlaylist);
		$.post('ajaxUpdatePlaylistItem.elcom',{data: jsonStr}, function(response){
			// $.unblockUI();
			showGrowlMsg('Cập nhật thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			}); 
			// update array delete
			$arrPlaylistItemDelete.length = 0;
			// trigger item playlist click
			var playlistId = $('.background-item-playlist').attr('id');
			if(typeof playlistId != 'undefined'){
				$('#' + playlistId).trigger('click');
				// scroll auto
				var container = $('.playlist'),scrollTo = $('#' + playlistId);
				container.animate({
					scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()
				});
			}
		});
	} else {
		// save playlist
		var name = $('#playlistname').val();
		var desc = $('#description').val();
		var jsonObj = [];
		var _length = $arrItem.length;
		var layoutid = $('#playlist-layout').val();
		for(var i = 0; i < _length; i++){
			var item = {};
			var timestart = toSecond($arrItem[i].attr('time-start'));
			var timetotal = toSecond($arrItem[i].attr('time-total'));
			var timeend = timetotal + timestart;
			item['name'] = $arrItem[i].attr('name-item');
			item['timestart'] = $arrItem[i].attr('time-start');
			item['timeend'] = toHHMMSS(timeend);
			item['idparent'] = layoutid;
			item['id'] = $arrItem[i].attr('item-id');
			item['idcontent'] = $arrItem[i].attr('id-content');
			item['iditem'] = $arrItem[i].attr('id-private');
			jsonObj.push(item);
		}
		var jsPlaylist = {"name": name, "description": desc, "items": jsonObj};
		var jsonStr = JSON.stringify(jsPlaylist);
		$.post('ajaxSavePlaylistItem.elcom',{data: jsonStr}, function(response){
			name = $('#playlistname').val();
			// $.unblockUI();
			showGrowlMsg('Lưu thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
			$('.playlist').append('<li class="item-playlist" id="'+response.data+'" title="'+name+'" item-id="'+response.data+'">' +
								 name + 
								'</li>');
			
			initDraggableSchedule('.item-playlist');
			initPlaylistClick();
			
			// trigger item playlist click
			var playlistId = response.data;
			if(typeof playlistId != 'undefined'){
				$('#' + playlistId).trigger('click');
				// scroll auto
				var container = $('.playlist'),scrollTo = $('#' + playlistId);
				container.animate({
					scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()
				});
			}
			
		});
	}
	return false;
}
function saveScheduleDaily(){
	if(!checkvalidate('Tên lịch là bắt buộc', '#dailyname')){
		return false;
	}
	var name = $('#dailyname').val();
	var desc = $('#daily-description').val();
	
	var jsPlaylist = {"name": name, "description": desc, "items": $arrPlaylist};
	var jsonStr = JSON.stringify(jsPlaylist);
	
	$.post('ajaxSaveScheduleDaily.elcom',{data: jsonStr}, function(response){
		name = $('#dailyname').val();
		desc = $('#daily-description').val();
		showGrowlMsg('Tạo lịch thành công');
		$(document).ajaxStop(function(){
			$.unblockUI;
		});
		// unLockUI();
		$('.daily-schedule-parent').append('<li title="'+name+'" description="'+subString(desc)+'" description-full="'+desc+'" createdate="2014-10-29" item-id="'+response.data+'" class="daily-schedule background-item-daily" id="'+response.data+'">'+
							name +
						'</li>');
		
		initDraggableSchedule('.daily-schedule');
		initDailyScheduleClick();
		// scroll auto
		var dailyId = response.data;
		var container = $('.daily-schedule-parent'),scrollTo = $('#' + dailyId);
		container.animate({
			scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()
		});
		
	});
	
	return false;
}
function deletePlaylistItem(){
	var _length = $arrItem.length;
	for(var i =0; i < _length; i++){
		$arrItem[i].trigger('remove').remove();
	}
	$arrItem = [];
	// update select playlist
	$('.playlist li').removeClass('background-item-playlist');
}
function deleteItemClick($trigger){
	if($arrItem.length == 1){
		$arrItem.length = 0;
		$($trigger).trigger('remove').remove();
		return;
	}
	var index = parseInt($trigger.attr('index'));
	$arrPlaylistItemDelete.push($arrItem[index]);
	$($trigger).trigger('remove').remove();
	$arrItem.splice(index, 1);
}
function updatePlaylist(numRow){
	if($('div').hasClass('column') && $('div').hasClass('draw-cell')){
		$('.column').each(function(index, element) {
            for(var i = 0; i < numRow; i++){
				$(this).append(drawCell());
			}
        });
	}
}
function setTitle($this){
	return 'Tên:' + $.trim($($this).attr('name-item')) + '  ' +
							 'Bắt Đầu:' + $($this).attr('time-start') + '  ' +
						     'Kết Thúc:' + $($this).attr('time-end') + '  ' +
							 'Tổng:' + $($this).attr('time-total');
}
/* Handle droppable item into column */
function droppable(ui, $this){
	var $element = null;
	
	if ($(ui.draggable)[0].id != "") {
		// handle item fit into colum after drop
		if($($this).hasClass('column')){
			if(!isFitColumn(ui, $this)){
				return;
			}
		}
		
		$element = handleDragOut(ui, $this);
		
		// show detail element
		drawDetailItem($.trim($($element).text()), $element.attr('time-total'), 
						$element.attr('time-start'), $element.attr('time-end'), $element.attr('item-tyle'),$arrItem.length);

		$element.draggable({
				helper: 'original',
				containment: $this,
				tolerance: 'fit',
				drag: handleDragItem,
				stop: stopDragItem,
				scroll: true
		});
		// add class color for video, image, text, browser
		if($element.attr('item-tyle') == 'video'){
			$element.addClass('item-clone-video');
			// remove class when using ajax load content
			$element.removeClass('item-video-ajax');
			// remove icon
			$element.find('.content-icon-video').removeClass('content-icon-video');
		} else if($element.attr('item-tyle') == 'image'){
			$element.addClass('item-clone-image');
			$element.removeClass('item-image-ajax');
			$element.find('.content-icon-image').removeClass('content-icon-image');
		} else if($element.attr('item-tyle') == 'text'){
			$element.addClass('item-clone-text');
			$element.removeClass('item-text-ajax');
			$element.find('.content-icon-text').removeClass('content-icon-text');
		} else if($element.attr('item-tyle') == 'browser'){
			$element.addClass('item-clone-browser');
			$element.removeClass('item-browser-ajax');
			$element.find('.content-icon-browser').removeClass('content-icon-browser');
		}
		// dont resize video
		if($element.attr('item-tyle') != 'video'){
			$element.resizable({
				containment: $this,
				autoHide: true,
				handles: 's',
				resize: handleResizeItem,
				stop: stopResize
			});
		}
		
		$element.on('click', function(){
			$('.container-playlist').find('.item-select').removeClass('item-select');
			$element.addClass('item-select');
			drawDetailItem($.trim($($element).text()), $element.attr('time-total'), 
						$element.attr('time-start'), $element.attr('time-end'), $element.attr('item-tyle'), $element.attr('index'));
		});
		// index to compare item in column
		var index = 0;
		if($arrItem.length > 0){
			index = (parseInt($arrItem[$arrItem.length - 1].attr('index')) + 1).toString();
		}
		$element.attr('index', index);
		// add element to arrItem
		$arrItem.push($element);
		// drawDetailItem();
		$element.appendTo($this);
		// update UI
		drawUI();
	}	
}
function handleDragItem(event, ui){
	$('.container-playlist').find('.item-select').removeClass('item-select');
	$(this).addClass('item-select');
	var colname = ui.helper.attr('name');
	var index = ui.helper.attr('index');
	var _length = $arrItem.length;
	var _top = $(this).position().top;
	var _timeend = toSecond(ui.helper.attr('time-total')) + _top;
	/*
	 * for(var i = 0; i < _length; i++){ if(colname == $arrItem[i].attr('name') &&
	 * ui.helper.height() == $($arrItem[i]).height() && index ==
	 * $arrItem[i].attr('index')){ $arrItem[i].css({ 'width': Math.round($('.' +
	 * colname).width()) + 2, 'left': Math.round($('.' +
	 * colname).position().left) + 2 }); $arrItem[i].attr('time-start',
	 * toHHMMSS(_top)); $arrItem[i].attr('time-end', toHHMMSS(_timeend)); } }
	 */
	
	var index = ui.helper.attr('index');
	$('.nameitem').text(subString(ui.helper.text()));
	$('.timetotal'+ index).val(ui.helper.attr('time-total')); 
	$('.timestart'+ index).val(toHHMMSS(Math.floor(_top)));
	$('.timeend'+ index).text(toHHMMSS(Math.floor(_timeend)));
	$('.obj').text(ui.helper.attr('item-tyle'));
}
function stopDragItem(event, ui){
	var colname = ui.helper.attr('name');
	var _length = $arrItem.length;
	for(var i = 0; i < _length; i++){
		if(colname == $arrItem[i].attr('name') && 
			ui.helper.height() == $($arrItem[i]).height()){
			$arrItem[i].css({
					'width': Math.round($('.' + colname).width()),
					'left': ($('.' + colname).position().left)
				});
		}
	}
	handleAfterDrag(ui);
	// update UI
	drawUI();
}
function handleResizeItem(event, ui){
	$('.container-playlist').find('.item-select').removeClass('item-select');
	$(this).addClass('item-select');
	var _height = ui.size.height;
	var colname = ui.originalElement.attr('name');
	var index = ui.helper.attr('index');
	var _length = $arrItem.length;
	var time_total = Math.floor(_height*$secondPixcel);
	var time_end = Math.round(toSecond(ui.helper.attr('time-start')) + time_total);
	var index = ui.helper.attr('index');
	$('.nameitem').text(subString(ui.helper.text()));
	$('.timetotal'+ index).val(toHHMMSS(time_total)); 
	$('.timestart'+ index).val(ui.helper.attr('time-start'));
	$('.timeend'+ index).text(toHHMMSS(time_end));
	$('.obj').text(ui.helper.attr('item-tyle'));
}
function stopResize(e, ui){
	var _height = ui.size.height;
	var colname = ui.originalElement.attr('name');
	var index = ui.helper.attr('index');
	var _length = $arrItem.length;
	var time_total = Math.round(_height*$secondPixcel);
	var time_end = Math.round(toSecond(ui.helper.attr('time-start')) + time_total);
	for(var i = 0; i < _length; i++){
		if(colname == $arrItem[i].attr('name') && 
			index == $arrItem[i].attr('index')){
			$arrItem[i].css({
						'height': Math.round(_height/$secondPixcel),
						'width': ($('.' + colname).width()) 
					}); 
			$arrItem[i].attr('time-total', toHHMMSS(time_total));
			
			$arrItem[i].attr('time-end', toHHMMSS(time_end));
		}
	}
	handleAfterResize(ui);
	// update UI
	drawUI();
}
function handleAfterResize(ui){
	// get column name current
	var colName =ui.helper.attr('name');
	var timeend = toSecond(ui.helper.attr('time-end'));
	var index = ui.helper.attr('index');
	var _length = $arrItem.length;
	for(var i = 0; i < _length; i++){
		var timestart = toSecond($arrItem[i].attr('time-start'));
		if(index != $arrItem[i].attr('index') && colName == $arrItem[i].attr('name')
				&& timeend > timestart && timeend < toSecond( $arrItem[i].attr('time-end'))){
			var _top = $($arrItem[i]).position().top + (timeend - timestart);
			$arrItem[i].css({
				'top': _top + 1
				});
			
			$arrItem[i].attr('time-start', toHHMMSS(_top));
			timeend = _top + toSecond($arrItem[i].attr('time-total'));
			$arrItem[i].attr('time-end', toHHMMSS(timeend));
		}
	}
}
function handleAfterDrag(ui){
	// get column name current
	var colName =ui.helper.attr('name');
	var index = ui.helper.attr('index');
	// detetct index of ui in $arrItem
	var indexUI = 0;
	var _length = $arrItem.length;
	for(var i = 0; i < _length; i++){
		if(index == $arrItem[i].attr('index')){
			indexUI = i;
			break;
		}
	}
	// get width column
	var w = $('.item').width();
	
	var _top = ui.offset.top - $('.column').offset().top;
	var modulus = toSecond(ui.helper.attr('time-total'))%$secondPixcel;
	var _height = ui.helper.height();
	(modulus > 0)?(_height = Math.floor(toSecond(ui.helper.attr('time-total'))/$secondPixcel) + 1):
				 ( _height = Math.floor(toSecond(ui.helper.attr('time-total'))/$secondPixcel));
	// store item in colName
	var arrTmp = [];
	if(_length > 0){
		var indexIntersect = -1;
		var insertTop = -1;
		var j = 0;
		for(var i = 0; i < _length; i++){
			var column = $arrItem[i].attr('name');
			if(column == colName){// get item in colum
				arrTmp.push($arrItem[i]);
				if(index != $arrItem[i].attr('index') && _top <= ($($arrItem[i]).position().top + $($arrItem[i]).height()) && _top >= $($arrItem[i]).position().top){
					// only intersect 1 element
					indexIntersect = j;// index element in arr arrTmp
				} 
				j+=1;
			}
		}
		var time_end = 0;
		if(indexIntersect >= 0){// when 2 element interset
			for(var i = 0; i < arrTmp.length; i++){
				if(i == indexIntersect){
					// set css element clone
					$($arrItem[indexUI]).css({
					  'width':w, 
					  'height': _height,
					  'top': toSecond(arrTmp[i].attr('time-end')),
					  'left': $('.' + colName).position().left
					});
					// update time-start
					$($arrItem[indexUI]).attr('time-start',$arrItem[i].attr('time-end'));
					// update time-end
					time_end = toSecond($($arrItem[indexUI]).attr('time-start')) + toSecond(ui.helper.attr('time-total'));
					$($arrItem[indexUI]).attr('time-end', toHHMMSS(time_end));
					// set title
					$arrItem[indexUI].attr('title', setTitle($arrItem[indexUI]));
				} else if(_top < $(arrTmp[i]).position().top && (_top + _height) > $(arrTmp[i]).position().top){
					// set top of item
					$(arrTmp[i]).css({
					  'top': $(arrTmp[i]).position().top + ((_top + _height) - $(arrTmp[i]).position().top),
					  'left': $('.' + colName).position().left
					});
					// update time-start
					var time_start = Math.round(($(arrTmp[i]).position().top)*$secondPixcel);
					arrTmp[i].attr('time-start', toHHMMSS(time_start));
					// update time-end
					time_end = toSecond(arrTmp[i].attr('time-start')) + toSecond(arrTmp[i].attr('time-total'));
					arrTmp[i].attr('time-end', toHHMMSS(time_end));
					// set title
					arrTmp[i].attr('title', setTitle(arrTmp[i]));
				}
			}
			// update item in $arrItem;
			j = 0;
			for(var i = 0; i < _length; i++){
				var column = $arrItem[i].attr('name');
				if(column == colName){
					$arrItem[i] = arrTmp[j];
					j+=1;
				}
			}
			return;
		} else {// check insert top
			for(var i = 0; i < arrTmp.length; i++){
				if(_top < $(arrTmp[i]).position().top && (_top + _height) > $(arrTmp[i]).position().top){
					// set top of item
					$(arrTmp[i]).css({
					  'top': $(arrTmp[i]).position().top + ((_top + _height) - $(arrTmp[i]).position().top),
					  'left': $('.' + colName).position().left
					});
					// update time-start
					var time_start = Math.round(($(arrTmp[i]).position().top)*$secondPixcel);
					arrTmp[i].attr('time-start', toHHMMSS(time_start));
					// update time-end
					time_end = toSecond(arrTmp[i].attr('time-start')) + toSecond(arrTmp[i].attr('time-total'));
					arrTmp[i].attr('time-end', toHHMMSS(time_end));
					// set title
					arrTmp[i].attr('title', setTitle(arrTmp[i]));
				}
			}
			// update item in $arrItem;
			j = 0;
			for(var i = 0; i < _length; i++){
				var column = $arrItem[i].attr('name');
				if(column == colName){
					$arrItem[i] = arrTmp[j];
					j+=1;
				}
			}
		}
		
	} 
	// update css item dragged
	$($arrItem[indexUI]).css({
		  'width': w, 
		  'height': _height,
		  'left': $('.' + colName).position().left,
		  'top': Math.round(_top)
		});
	// update time-start
	var time_start = Math.round(_top*$secondPixcel);
	$($arrItem[indexUI]).attr('time-start', toHHMMSS(time_start));
	// update time-end
	time_end = time_start + toSecond($($arrItem[indexUI]).attr('time-total'));
	$($arrItem[indexUI]).attr('time-end', toHHMMSS(time_end));
	$arrItem[indexUI].attr('title', setTitle(ui));
	
	return;
}
/**
 * Handle when drag item from left into column ui: item $this:column
 */
function handleDragOut(ui, $this){
	$element = null;
	// get column name current
	var colName = $($this).attr('name');
	$element = ui.helper.clone();
	ui.helper.remove();
	// set name for item. 1 column has muilti item same name
	$element.attr('name', colName);
	$element.addClass('item-clone');
	
	// add col-id into item to know item of colum
	$element.attr('item-id', $($this).attr('col-id'));
	// add class select item
	$('.container-playlist').find('.item-select').removeClass('item-select');
	$($element).addClass('item-select');
	// get width column
	var w = $('.column').width();
	var _length = $arrItem.length;
	var _top = ui.offset.top - $('.column').offset().top;
	var modulus = toSecond($element.attr('time-total'))%$secondPixcel;
	var _height = $element.height();
	(modulus > 0)?(_height = Math.floor(toSecond($element.attr('time-total'))/$secondPixcel) + 1):
				 ( _height = Math.floor(toSecond($element.attr('time-total'))/$secondPixcel));
	// store item in colName
	var arrTmp = [];
	if(_length > 0){
		var indexIntersect = -1;
		var insertTop = -1;
		var j = 0;
		for(var i = 0; i < _length; i++){
			var column = $arrItem[i].attr('name');
			if(column == colName){
				arrTmp.push($arrItem[i]);
				if(_top <= ($($arrItem[i]).position().top + $($arrItem[i]).height()) && _top >= $($arrItem[i]).position().top){
					// only intersect 1 element
					indexIntersect = j;
				} 
				j+=1;
			}
		}
		var time_end;
		if(indexIntersect >= 0){
			for(var i = 0; i < arrTmp.length; i++){
				if(i == indexIntersect){
					// set css element clone
					$($element).css({
					  'width':w, 
					  'height': _height,
					  'top': toSecond(arrTmp[i].attr('time-end')) + 1,
					  'left': $($this).position().left
					});
					// update time-start
					$($element).attr('time-start',$arrItem[i].attr('time-end'));
					// update time-end
					time_end = toSecond($($element).attr('time-start')) + toSecond($($element).attr('time-total'));
					$($element).attr('time-end', Math.round(toHHMMSS(time_end)));
					// set title
					$element.attr('title', setTitle($element));
				} else if(_top < $(arrTmp[i]).position().top && (_top + _height) > $(arrTmp[i]).position().top){
					// set top of item
					$(arrTmp[i]).css({
					  'top': $(arrTmp[i]).position().top + ((_top + _height) - $(arrTmp[i]).position().top),
					  'left': $($this).position().left
					});
					// update time-start
					var time_start = Math.round(($(arrTmp[i]).position().top)*$secondPixcel) + 1;
					arrTmp[i].attr('time-start', (toHHMMSS(time_start)));
					// update time-end
					time_end = toSecond(arrTmp[i].attr('time-start')) + toSecond(arrTmp[i].attr('time-total'));
					arrTmp[i].attr('time-end', (toHHMMSS(time_end)));
					// set title
					arrTmp[i].attr('title', setTitle(arrTmp[i]));
				}
			}
			// update item in $arrItem;
			j = 0;
			for(var i = 0; i < _length; i++){
				var column = $arrItem[i].attr('name');
				if(column == colName){
					$arrItem[i] = arrTmp[j];
					j+=1;
				}
			}
			
			return $element;
		} else {// check insert top
			for(var i = 0; i < arrTmp.length; i++){
				if(_top < $(arrTmp[i]).position().top && (_top + _height) > $(arrTmp[i]).position().top){
					// set top of item
					$(arrTmp[i]).css({
					  'top': $(arrTmp[i]).position().top +  ((_top + _height) - $(arrTmp[i]).position().top),
					  'left': $($this).position().left
					});
					// update time-start
					var time_start = Math.round(($(arrTmp[i]).position().top)*$secondPixcel);
					arrTmp[i].attr('time-start', toHHMMSS(time_start));
					// update time-end
					time_end = Math.round(toSecond(arrTmp[i].attr('time-start'))) + Math.round(toSecond(arrTmp[i].attr('time-total')));
					arrTmp[i].attr('time-end', (toHHMMSS(time_end)));
					// set title
					arrTmp[i].attr('title', setTitle(arrTmp[i]));
				}
			}
			// update item in $arrItem;
			j = 0;
			for(var i = 0; i < _length; i++){
				var column = $arrItem[i].attr('name');
				if(column == colName){
					$arrItem[i] = arrTmp[j];
					j+=1;
				}
			}
		}
		
	} 
	$($element).css({
					  'width':w , 
					  'height': _height,
					  'left': $($this).position().left,
					  'top': Math.round(_top)
					});
	// update time-start
	var time_start = Math.round(_top*$secondPixcel);
	$($element).attr('time-start', (toHHMMSS(time_start)));
	// update time-end
	time_end = time_start + toSecond($($element).attr('time-total'));
	$($element).attr('time-end', (toHHMMSS(time_end)));
	$element.attr('title', setTitle($element));
	return $element;
}

function drawUI(){
	// find max time-end
	var maxTime = getMaxTime();
	if(maxTime > $maxTimeCurrent){
		$maxTimeCurrent += $maxTimeCurrent;
		numRow = Math.floor(($maxTimeCurrent* $numRow)/$maxTimeDefault);
		updatePlaylist(numRow - $numRow);
		updateColumnTime(numRow);
		/*
		 * var modulus = $maxTimeCurrent%$maxTimeDefault; (modulus >
		 * 0)?($secondPixcel = Math.floor($maxTimeCurrent/$maxTimeDefault) + 1):
		 * ($secondPixcel = parseInt($maxTimeCurrent/$maxTimeDefault));
		 */
	}
	// update column time
	// updateColumnTime();
		
	var _length = $arrItem.length;
	if(_length > 0){
		for(var i = 0; i < _length; i++){
			var colname =  $arrItem[i].attr('name');
			/*
			 * var _topCurrent = $arrItem[i].position().top; var modulus =
			 * (_topCurrent)%$secondPixcel; var _topNew; (modulus > 0)?(_topNew =
			 * Math.floor( (_topCurrent)/$secondPixcel) + 1): (_topNew =
			 * Math.floor( (_topCurrent)/$secondPixcel));
			 */
			var time_total = toSecond($arrItem[i].attr('time-total'));
			$arrItem[i].css({
					/* 'top': _topNew, */
					'height': Math.round((time_total)/$secondPixcel)
				});
			$arrItem[i].attr('title', setTitle($arrItem[i]));
		}
	}
	
}

function drawDetailItem(name, timeplay, timestart, timeend, obj, index){
  var table = 
	"<div class='layout-view-right playerofgroup' style='margin:10px 0px 0px 0px'>" +
		"<table>" +
			"<thead>" +
				"<tr>" +
					"<th style='width:16%'>" +"Tên"+"</th>" +
					"<th style='width:16%'>" +"Thời Gian Phát"+"</th>" +
					"<th style='width:16%'>" +"Bắt Đầu"+"</th>" +
					"<th style='width:16%'>" +"Kết Thúc"+"</th>" +
					"<th style='width:16%'>" +"Đối Tượng"+"</th>" +
					/* "<th style='width:16%'>" +"Cập Nhật"+"</th>" + */
				"</tr>" +
			"</thead>" +
		"<tbody id='scrolling'>" +
			"<tr class='row-select'>" +
				"<td class='nameitem' style='width:16%'>" + subString(name) +
				"</td>" +
				"<td style='text-align:center;width:16%'>" +
					"<div class='input-append time'>"+
						"<input name='timetotal"+index + "' class='timetotal"+index + "'  placeholder='00:00:00' value='"+timeplay+ "'  onblur='timetotalBlur($(this).val()," + index + ")' onfocus='timetotalFocus("+index +")' required='required' maxlength='8' style='width: 60px' data-format='hh:mm:ss' type='text'>"+
						"<span class='add-on'>"+ "<i data-time-icon='icon-time' class='icon-time'>"+"</i>"+
						"</span>"+
					"</div>" +
				"</td>" +
				"<td style='text-align:center;width:16%'>" +
					"<div class='input-append time'>"+
						"<input  name='timestart"+index + "' class='timestart"+index + "' placeholder='00:00:00' onblur='timestartBlur($(this).val()," + index + ")' onfocus='timestartFocus("+index +")'  value='"+timestart+ "' value='00:00:10' required='required' maxlength='8' style='width: 60px' data-format='hh:mm:ss' type='text'>"+
						"<span class='add-on'>"+ "<i data-time-icon='icon-time' class='icon-time'>"+"</i>"+
						"</span>"+
					"</div>" +
				"</td>" +
				"<td style='text-align:center;width:16%'>" +
				   "<h class='timeend"+index + "'>"+timeend +"</h>" +
				"</td>" +
				"<td class='obj'>" +
				   obj +
				"</td>" +
				/*
				 * "<td style='text-align:center;width:16%'>" + "<a href='#'
				 * title='Cập nhật'>" + "<img
				 * src='css/images/page_white_update.png'/>" + "</a>" + "</td>" +
				 */
			"</tr>" + 
		"</tbody>" +
	  "</table>" +
   "</div>";
   $('.playlist-detail').empty();
   $('.playlist-detail').append(table);
   initTimePicker();
}

function viewPlaylistTable(){
	 var table = 
	"<div class='playerofgroup playlist-item-dialog' style='margin-left: 0px'>" +
		"<table>" +
			"<thead>" +
				"<tr>" +
					"<th>" +"Tên"+"</th>" +
					"<th>" +"Thời Gian Phát"+"</th>" +
					"<th>" +"Bắt Đầu"+"</th>" +
					"<th>" +"Kết Thúc"+"</th>" +
					"<th>" +"Đối Tượng"+"</th>" +
				/* "<th>" +"Cập Nhật"+"</th>" + */
				"</tr>" +
			"</thead>" +
			"<tbody>" +
				
			"</tbody>" +
	  "</table>" +
   "</div>";
   /*
	 * "<div class='button-dialog-playlist' style='margin-bottom: 0px;'>" + "<div
	 * style='float:left; margin-right:10px;'>" + "<a href='#'
	 * class='button-dialog' onclick='return false'>Cập Nhật</a>" + "</div>" + "<div
	 * style='float:left'>" + "<a href='#' class='button-dialog'
	 * onclick='return unLockUI()'>Đóng</a>" + "</div>" + "</div>";
	 */
   $('.dialog-playlist').empty();
   $('.dialog-playlist').append(table);
   $.blockUI({ 
			message: $('.dialog-playlist'),
			css: { 
				padding:0, 
				margin:0, 
				width:'65%', 
				top:'10%', 
				left:'17%', 
				textAlign:'center', 
				border:'3px solid #aaa',
				backgroundColor:'#fff', '-webkit-border-radius': '10px',
			     '-moz-border-radius': '10px',
				cursor:'progress'
			},
			onOverlayClick: $.unblockUI
		}); 
   $('.playlist-item-dialog table tbody').append(drawRowTable());
   initTimePicker();
}
function drawRowTable(){
	var tr = "";
	var length = $arrItem.length;
	for(var i = 0; i < length; i++){
		tr = tr + "<tr class='row-select'>" +
				"<td title='"+($arrItem[i]).text()+"' class='nameitem"+i+"'>" + subString(($arrItem[i]).text())+
				"</td>" +
				"<td style='text-align:center;'>" +
					"<div class='input-append time'>"+
						"<input name='timetotal-dialog"+i + "' class='timetotal-dialog"+i + "'  placeholder='00:00:00' value='"+$arrItem[i].attr('time-total')+ "'  value='00:00:10' onblur='timetotalBlurDialog($(this).val()," + i + ")' onfocus='timetotalFocusDialog("+i+")' required='required' maxlength='8' style='width: 60px' data-format='hh:mm:ss' type='text'>"+
						"<span class='add-on'>"+ "<i data-time-icon='icon-time' class='icon-time'>"+"</i>"+
						"</span>"+
					"</div>" + 
				"</td>" +
				"<td style='text-align:center;'>" +
					"<div class='input-append time'>"+
						"<input name='timestart-dialog"+i + "' class='timestart-dialog"+i + "' placeholder='00:00:00' onblur='timestartBlurDialog($(this).val()," + i + ")' onfocus='timestartFocusDialog("+i +")'  value='"+$arrItem[i].attr('time-start')+ "'  value='00:00:10' required='required' maxlength='8' style='width: 60px' data-format='hh:mm:ss' type='text'>"+
						"<span class='add-on'>"+ "<i data-time-icon='icon-time' class='icon-time'>"+"</i>"+
						"</span>"+
					"</div>" + 
				"</td>" +
				"<td style='text-align:center;'>" +
				   "<h class='timeend-dialog"+i + "'>"+$arrItem[i].attr('time-end') +"</h>" +
				"</td>" +
				"<td class='obj'>" +
				   $arrItem[i].attr('item-tyle') +
				"</td>" +
				/*
				 * "<td style='text-align:center;'>" + "<a href='#'
				 * title='Cập nhật'>" + "<img
				 * src='css/images/page_white_update.png'/>" + "</a>" + "</td>" +
				 */
			"</tr>" ;
	}
	return tr;
}
function isFitColumn(ui, $this){
	var left_this = $($this).offset().left;
	var top_this = $($this).offset().top;
	var width_this = $($this).width();
	var height_this = $($this).height();
	if(ui.offset.left < left_this || 
	   ui.offset.top < top_this || (ui.offset.top + ui.helper.height()) > (height_this + top_this)){
		return false;
	} else {
		return true;
	}
}
function drawHeader(name, width){
	var div = ("<div title='"+name+"' class='heading' style='width:"+width+"'>" + name + "</div>");
	return div;
}
function drawColumn(id, type, width){
	var col = "<div col-id='"+id+"'  class='column " +id + " "+ type + "'  name='" + type + "' style='width:" + width + "'></div>";
	return col;
}
function drawCell(){
	return "<div class='row-cell'></div>";
}
function drawCellTimer(timer){
	return "<div class='row-cell' style='font-size:10px; color:#000000; text-align:center;'>" + timer + "</div>";
}
/* convert seconds to HH:MM:SS */
function toHHMMSS(seconds) {
    var h, m, s, result='';
    // HOURs
    h = Math.floor(seconds/3600);
    seconds -= h*3600;
    result = h<10 ? '0'+h+':' : h+':';
    // MINUTEs
    m = Math.floor(seconds/60);
    seconds -= m*60;
    result += m<10 ? '0'+m+':' : m+':';
    // SECONDs
    s=seconds%60;
    result += s<10 ? '0'+s : s;
    return result;
}
/* convert HH:MM:SS to seconds(integer) */
function toSecond(hhmmss){
	if(hhmmss == null){
		return 0;
	}
	var arrTime = hhmmss.split(':');
	if(arrTime.length == 3){
		return ((parseInt(arrTime[0]*3600) + parseInt(arrTime[1]*60) + parseInt(arrTime[2])));
	}
	return 0;
}
function drawColumnTimeDaily(){
	var col = $(drawColumn('-1','col-timer', '8%')).removeClass('column');
	$('.col-timer').empty();
	for(var j = 0; j < $numRow; j++){
		var timer = toHHMMSS(j*$numCell*$secondPixcel);
		col.append(drawCellTimeDaily());
	}
	$('.container-playlist-daily').append(col);
}
function drawCellTimeDaily(){
	var col = $(drawColumn('-1','col-timer', '8%')).removeClass('column');
	$('.col-timer').empty();
	for(var j = 0; j < $numRow; j++){
		var timer = toHHMMSS(j*$numCell*$secondPixcel);
		col.append(drawCellTimer(timer));
	}
	$('.container-playlist-daily').append(col);
}
function drawColumnTime(){
	var col = $(drawColumn('-1','col-timer', '8%')).removeClass('column');
	$('.col-timer').empty();
	for(var j = 0; j < $numRow; j++){
		var timer = toHHMMSS(j*$numCell*$secondPixcel);
		col.append(drawCellTimer(timer));
	}
	$('.container-playlist').append(col);
}
function updateColumnTime(allNumRow){
	$('.col-timer').empty();
	for(var j = 0; j < allNumRow; j++){
		var timer = toHHMMSS(j*$numCell*$secondPixcel);
		$('.col-timer').append(drawCellTimer(timer));
	}
}
function getMaxTime(){
	var length = $arrItem.length;
	if(length > 0){
		var max_time = toSecond($arrItem[0].attr('time-end'));
		for(var i = 1; i < length; i++){
			var time_end1 = toSecond($arrItem[i].attr('time-end'));
			if(time_end1 > max_time){
				max_time = time_end1;
			}
		}
		return max_time;
	}
	return 0;
}

function timetotalBlur(val, index){
	if(val == ""){
		$('.timetotal' + index).val("00:00:00");
	}
	var msg = 'Vui lòng nhập thời gian'+'<br/>'  +
	'Theo định dạng: giờ:phút:giây' + '</br>' +
	'Ví dụ: 00:20:01' ;
	val = $('.timetotal' + index).val();
	if(!/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(val)){
		messeageError('.timetotal' + index, msg);
		return;
	} else{
		var timestart = toSecond($('.timestart'+ index).val());
		if(/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test($('.timestart' + index).val())){
			var timeend = toSecond(val) + timestart;
			$('.timeend'  + index).text(toHHMMSS(timeend));
			// set height item select
			$('.item-select').css({'height': toSecond(val)});
			// update value trong arrItem
			var length = $arrItem.length;
			for(var i =0; i < length; i++){
				if($arrItem[i].attr('id-content') == $('.item-select').attr('id-content') && 
						$arrItem[i].attr('time-start') == $('.item-select').attr('time-start') &&
						$arrItem[i].hasClass('item-select')){
					$arrItem[i].css({'height': toSecond(val)});
					$arrItem[i].attr('time-total', val);
				}
			}
		} else{
			$('.timeend' + index).text('00:00:00');
		}
	}
}
function timestartBlur(val, index){
	if(val == "" || val == "00:00:00"){
		$('.timestart' + index).val("00:00:00");
	}
	var msg = 'Vui lòng nhập thời gian'+'<br/>'  +
	'Theo định dạng: giờ:phút:giây' + '</br>' +
	'Ví dụ: 00:20:01' ;
	val = $('.timestart' + index).val();
	if(!/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(val)){
		messeageError('.timestart' + index, msg);
		return;
	} else{
		var timetotal = toSecond($('.timetotal' + index).val());
		if(/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test($('.timetotal' + index).val())){
			var timeend = toSecond(val) + timetotal;
			$('.timeend' + index).text(toHHMMSS(timeend));
			// set top item select
			$('.item-select').css({'top': toSecond(val)});
			// update value trong arrItem
			var length = $arrItem.length;
			for(var i =0; i < length; i++){
				if($arrItem[i].attr('id-content') == $('.item-select').attr('id-content') &&
						$arrItem[i].attr('time-total') == $('.item-select').attr('time-total') &&
						$arrItem[i].hasClass('item-select')){
					$arrItem[i].css({'top': toSecond(val)});
					$arrItem[i].attr('time-start', val);
				}
			}
		} else{
			$('.timeend' + index).text('00:00:00');
		}
	}
	
}
function timetotalBlurDialog(val, index){
	if(val == ""){
		$('.timetotal-dialog' + index).val("00:00:00");
	}
	var msg = 'Vui lòng nhập thời gian'+'<br/>'  +
	'Theo định dạng: giờ:phút:giây' + '</br>' +
	'Ví dụ: 00:20:01' ;
	val = $('.timetotal-dialog' + index).val();
	if(!/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(val)){
		messeageError('.timetotal-dialog' + index, msg);
		return;
	} else{
		var timestart = toSecond($('.timestart-dialog' + index).val());
		if(/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test($('.timestart-dialog' + index).val())){
			var timeend = toSecond(val) + timestart;
			$('.timeend-dialog'  + index).text(toHHMMSS(timeend));
			// update value trong arrItem
			var length = $arrItem.length;
			for(var i =0; i < length; i++){
				if($arrItem[i].attr('time-start') == $('.timestart-dialog' + index).val()){
					$arrItem[i].css({'height': toSecond(val)});
					$arrItem[i].attr('time-total', val);
				}
			}
		} else{
			$('.timeend-dialog' + index).text('00:00:00');
		}
	}
}
function timestartBlurDialog(val, index){
	if(val == ""){
		$('.timestart-dialog' + index).val("00:00:00");
	}
	var msg = 'Vui lòng nhập thời gian'+'<br/>'  +
	'Theo định dạng: giờ:phút:giây' + '</br>' +
	'Ví dụ: 00:20:01' ;
	val = $('.timestart-dialog' + index).val();
	if(!/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(val)){
		messeageError('.timestart-dialog' + index, msg);
		return;
	} else{
		var timetotal = toSecond($('.timetotal-dialog' + index).val());
		if(/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test($('.timetotal-dialog' + index).val())){
			var timeend = toSecond(val) + timetotal;
			$('.timeend-dialog' + index).text(toHHMMSS(timeend));
			// update value trong arrItem
			var length = $arrItem.length;
			for(var i =0; i < length; i++){
				if($arrItem[i].attr('time-total') == $('.timetotal-dialog' + index).val() &&
						$arrItem[i].attr('name-item') == $('.nameitem'+ index).attr('title')){
					$arrItem[i].css({'top': toSecond(val)});
					$arrItem[i].attr('time-start', val);
				}
			}
		} else{
			$('.timeend-dialog' + index).text('00:00:00');
		}
	}
}

function timeendBlurDetail(val, id){
	if(val == ""){
		$('.timeend-detail' + index).val("00:00:00");
	}
	var msg = 'Vui lòng nhập thời gian'+'<br/>'  +
	'Theo định dạng: giờ:phút:giây' + '</br>' +
	'Ví dụ: 00:20:01' ;
	val = $('.timeend-detail' + id).val();
	if(!/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(val)){
		messeageError('.timetotal-detail' + id, msg);
		return;
	}
	var timestart = toSecond($('.timestart-detail'+ id).val());
	var timeend = toSecond($('.timeend-detail'+ id).val());
	if(timestart > timeend){
		showMessageDialogError("Lỗi", "Thời gian Bắt đầu > Thời gian Kết thúc");
		return;
	} else{
		// update value trong arrItem
		var length = $arrItem.length;
		for(var i =0; i < length; i++){
			if($arrItem[i].attr('id-private') == id.toString()){
				$arrItem[i].css({'height': timeend - timestart});
				$arrItem[i].attr('time-total', toHHMMSS(timeend - timestart));
			}
		}
	}
}
function timestartBlurDetail(val, id){
	if(val == ""){
		$('.timestart-detail' + index).val("00:00:00");
	}
	var msg = 'Vui lòng nhập thời gian'+'<br/>'  +
	'Theo định dạng: giờ:phút:giây' + '</br>' +
	'Ví dụ: 00:20:01' ;
	val = $('.timestart-detail' + id).val();
	if(!/^([01]?[0-9]|2[0-3])(:[0-5][0-9]){2}$/.test(val)){
		messeageError('.timetotal-detail' + id, msg);
		return;
	}
	var timestart = toSecond($('.timestart-detail' + id).val());
	var timeend = toSecond($('.timeend-detail' + id).val());
	if(timestart > timeend){
		showMessageDialogError("Lỗi", "Thời gian Bắt đầu > Thời gian Kết thúc");
		return;
	} else{
		// update value trong arrItem
		var length = $arrItem.length;
		for(var i =0; i < length; i++){
			if($arrItem[i].attr('id-private') == id.toString()){
				$arrItem[i].css({'top': toSecond(val)});
				$arrItem[i].attr('time-start', val);
			}
		}
	}
}
function messeageError(classElement, msg){
	var _left = $(classElement).position().left;
	var _top = $(classElement).position().top;
	var _w = $(classElement).width();
	var div = '<div class="error">'+
					msg +
				'</div>';
	if($(classElement).next('.error').length <= 0){
		$(classElement).after($(div).css({
				'left': _left + _w/2,
				'top': _top,
				'opacity': 1
			}));
	}
	$(classElement).next('.error').animate({opacity: 1}, 300);
}
function timetotalFocus(index){
	if($('.timetotal' + index).next('.error').length > 0){
		$('.timetotal' + index).next('.error').remove();
	}
}
function timestartFocus(index){
	if($('.timestart' + index).next('.error').length > 0){
		$('.timestart' + index).next('.error').remove();
	}
}
function timetotalFocusDialog(index){
	if($('.timetotal-dialog' + index).next('.error').length > 0){
		$('.timetotal-dialog' + index).next('.error').remove();
	}
}
function timestartFocusDialog(index){
	if($('.timestart-dialog' + index).next('.error').length > 0){
		$('.timestart-dialog' + index).next('.error').remove();
	}
}

function timeendFocusDetail(index){
	if($('.timeend-detail' + index).next('.error').length > 0){
		$('.timeend-detail' + index).next('.error').remove();
	}
}
function timestartFocusDetail(index){
	if($('.timestart-detail' + index).next('.error').length > 0){
		$('.timestart-detail' + index).next('.error').remove();
	}
}
function unLockUI(){
	$.unblockUI();
	return false;
}
/* Schedule daily */
$(function(){
	$('.time').each(function(index, element) {
        $(this).datetimepicker({
			format: 'hh:mm:ss',
			language: 'en',
			pickDate: false
		});
    });
});

function layoutOnClick(){
	// flag to detect tab PLAYLIST
	$flag = 0;
	// update name title
	$('.title-main').text('Danh Sách');
	
	$('#schedule-daily').hide();
	$('#schedule-periodic').hide();
	$('#playlist-item').show();
	$('#playlist-content').show();
	$('.playlist').addClass('playlist-fix');
	$('.playlist li').removeClass('background-item-playlist');

	$('.layout-view-right').trigger('remove').remove();
	// trigger playlist first click
	$('.playlist li').first().trigger('click');
	$('.container-playlist').addClass('hidden');
	// clear table PLAYLIST OF DAILY
	$('.container-heading-daily').empty();
	$('.container-playlist-daily').empty();
}
function dailyOnClick(){
	clearInterval($timeLoopPlaylist);
	window.clearTimeout($timeStartPlaylist);
	window.clearTimeout($timeStartItem);
	window.clearTimeout($timeEndItem);
	$('#item-playlist').empty();
	// init event
	initDailyScheduleClick();
	// flag to detect tab PLAYLIST DAILY
	$flag = 1;
	// update name title
	$('.title-main').text('Lịch Ngày');
	// trigger click
	$('.daily-schedule').first().trigger('click');
	
	$('#playlist-content').hide();
	$('#schedule-periodic').hide();
	$('#playlist-item').show();
	$('#schedule-daily').show();
	$('.daily-schedule-parent').addClass('daily-schedule-fix');
	$('.playlist').removeClass('playlist-fix');
	$('.playlist li').removeClass('background-item-playlist');
	
	$('.container-heading-daily').empty();
	$('.container-playlist-daily').addClass('hidden');
	// trigger click
	$('.item-playlist').first().trigger('click');
	
	// clear table PLAYLIST of TAB PLAYLIST
	$('.container-heading').empty();
	$('.container-playlist').empty();	
}
function periodicOnClick(){
	
	clearInterval($timeLoopPlaylist);
	window.clearTimeout($timeStartPlaylist);
	window.clearTimeout($timeStartItem);
	window.clearTimeout($timeEndItem);
	$('#item-playlist').empty();
	// update name title
	$('.title-main').text('Lịch Định Kì');
	
	$('#playlist-content').hide();
	$('#playlist-item').hide();
	$('#schedule-periodic').show();
	$('.group-periodic').removeClass('background-item-periodic');
	$('#schedule-daily').hide();
	$('.playlist').removeClass('playlist-fix');
	$('.group-periodic-parent').removeClass('group-periodic-fix');
	$('#tab-schedule-periodic').empty();
	$('#tab-schedule-periodic').append('<div id="msg" class="info_box" style="width: 66%; margin-left: 30px;">Hãy chọn nhóm để thực hiện chức năng này.</div>');
	$('.daily-schedule').removeClass('background-item-daily');
	// off event
	$('.daily-schedule').off('click');
	// trigger click
	$('.group-periodic-parent li').first().trigger('click');
	
}
function showDialog(nameplaylist){
	
	var div = "<div>" +
                    	"<h2 class='title'>" + nameplaylist + "</h2>" +
                    	"<span>Thời gian bắt đầu</span>" +
                    	"<div class='input-append time'>" +
                            "<input data-format='hh:mm:ss' type='text'>" +"</input>" +
                            "<span class='add-on'>" +
                              "<i data-time-icon='icon-time'>" +"</i>" +
                            "</span>" +
                       "</div>" +
                       "<span>Thời gian kết thúc:</span>" +
                    	"<div class='input-append time'>" +
                            "<input data-format='hh:mm:ss' type='text'>" +"</input>" +
                            "<span class='add-on'>" +
                              "<i data-time-icon='icon-time'>" +"</i>" +
                            "</span>" +
                       "</div>" +
                       "<div class='button-dialog-timepicker'>" +
                       		"<div style='float:left;'>" +
                            	"<a href='#' class='button-dialog' style='width:35px; margin-right:5px;' onclick='return false'>Chọn</a>" +
                            "</div>" +
                         	"<div style='float:left'>" +
                            	"<a href='#' class='button-dialog' style='width:35px;' onclick='return unLockUI()'>Đóng</a>" +
                            "</div>" +
                       "</div>" +
                    "</div>";
		$('.dialog-timepicker').append(div);
		$.blockUI({ 
			message: $('.dialog-timepicker'),
			css: { 
				padding:0, 
				margin:0, 
				width:'65%', 
				top:'10%', 
				left:'17%', 
				textAlign:'center', 
				border:'3px solid #aaa', 
				backgroundColor:'#fff', 
				cursor:'progress' 
			},
			cursor:'progress',
			onOverlayClick: $.unblockUI
		}); 
}
function layoutForDaily($this){
	// update arrItem
	$arrItem.length = 0;
	// update select playlist
	$('.playlist li').removeClass('background-item-playlist');
	$($this).addClass('background-item-playlist');
	$.post('ajaxPlaylist.elcom',{data: $($this).attr('id')},function(response){
			// draw table playlist
			updateTablePlaylistDaily(response.layoutItem);
			if(response.layout == null){
				return;
			}
			// get layout and show on table
			_length = response.layout.length;
			
			for(var i = 0; i < _length; i++){
				var starttime = response.layout[i].startTime;
				var endtime = response.layout[i].endTime;
				var time = toSecond(endtime) - toSecond(starttime);
				var timetotal = toHHMMSS(time);
				var idColumn = '.' + response.layout[i].idSubLayout;
				var name = $(idColumn).attr('name');
				var item = '';
				if(response.layout[i].type == 'Video'){
					item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
								'class="item-video item-clone item-clone-video item-video-ajax" item-tyle="video" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
								'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+
								'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
								response.layout[i].name + 
							'</li>');
				} else if(response.layout[i].type == 'Image'){
					item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
								'class="item-image item-clone-image item-clone item-image-ajax" item-tyle="image" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
								'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+
								'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
								response.layout[i].name + 
							'</li>');
				} else if(response.layout[i].type == 'Browser'){
					item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
								'class="item-browser item-clone-browser item-clone item-browser-ajax" item-tyle="browser" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
								'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+
								'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
								response.layout[i].name + 
							'</li>');
				} else if(response.layout[i].type == 'Text'){
					item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
								'class="item-text item-clone-text item-clone item-text-ajax" item-tyle="text" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
								'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+
								'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
								response.layout[i].name + 
							'</li>');
				}
				
				$(item).css({
					'width': $('.container-playlist-daily ' + idColumn).width(),
					'height': parseInt(toSecond(timetotal)),
					'top': parseInt(toSecond(starttime)),
					'left': $('.container-playlist-daily ' + idColumn).position().left
				});
				
				$(idColumn).append(item);
				// add element to arrItem
				$arrItem.push(item);
			}
			
		});
}
function initPlaylistForDailyClick(){
	$('.playlist-for-daily').on('click', function(e){
		e.preventDefault();
        e.stopImmediatePropagation();
		if($flag == 0){
			return;
		}
		// update arrItem
		$arrItem.length = 0;
		// update select playlist
		$('.playlist li').removeClass('background-item-playlist');
		$(this).addClass('background-item-playlist');
		$.post('ajaxPlaylist.elcom',{data: $(this).attr('id')},function(response){
				// draw table playlist
				updateTablePlaylistDaily(response.layoutItem);
				
				if(response.layout == null){
					return;
				}
				// get layout and show on table
				_length = response.layout.length;
				
				for(var i = 0; i < _length; i++){
					var starttime = response.layout[i].startTime;
					var endtime = response.layout[i].endTime;
					var time = toSecond(endtime) - toSecond(starttime);
					var timetotal = toHHMMSS(time);
					var idColumn = '.' + response.layout[i].idSubLayout;
					var name = $(idColumn).attr('name');
					var item = '';
					if(response.layout[i].type == 'Video'){
						item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
									'class="item-video item-clone item-clone-video item-video-ajax" item-tyle="video" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
									'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+
									'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
									response.layout[i].name + 
								'</li>');
					} else if(response.layout[i].type == 'Image'){
						item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
									'class="item-image item-clone-image item-clone item-image-ajax" item-tyle="image" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
									'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+
									'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
									response.layout[i].name + 
								'</li>');
					} else if(response.layout[i].type == 'Browser'){
						item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
									'class="item-browser item-clone-browser item-clone item-browser-ajax" item-tyle="browser" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
									'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+
									'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
									response.layout[i].name + 
								'</li>');
					} else if(response.layout[i].type == 'Text'){
						item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
									'class="item-text item-clone-text item-clone item-text-ajax" item-tyle="text" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
									'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+
									'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
									response.layout[i].name + 
								'</li>');
					}
					$(item).css({
						'width': $(idColumn).width(),
						'height': parseInt(toSecond(timetotal)),
						'top': parseInt(toSecond(starttime)),
						'left': $(idColumn).position().left
					});
					
					$(idColumn).append(item);
					// add element to arrItem
					$arrItem.push(item);
				}
			}
		);
	}).error(function(){
		console.log("Server turn off");
	});
}
function initPlaylistClick(){
	$('.item-playlist').on('click', function(e){
		e.preventDefault();
		e.stopImmediatePropagation();
		// flag detect PLAYLIST current or DAILY current
		if($flag == 1){// DAILY current
			layoutForDaily(this);
			return;
		}
		// update arrItem
		$arrItem.length = 0;
		// update array items delete
		$arrPlaylistItemDelete.length = 0;
		// update select playlist
		$('.playlist li').removeClass('background-item-playlist');
		$(this).addClass('background-item-playlist');
		
		$.post('ajaxPlaylist.elcom',{data: $(this).attr('id')},function(response){
				if(response.layout == null || response.layout.length == 0){
					return;
				}
				// draw table playlist and screen layout
				var layoutId = response.layout[0].idParent;
				$('#playlist-layout').val(layoutId);
				var direction = $("#playlist-layout option[value='"+layoutId+"']").attr('direction');
				$('.layout-view-h').trigger('remove').remove();
				$('.layout-view-v').trigger('remove').remove();
				if(direction == '0'){
					$('.layout-view-left').append("<div class='layout-view-h'><div class='playlist-content-layout-h'></div></div>");
				} else if(direction == '1'){
					$('.layout-view-left').append("<div class='layout-view-v'><div class='playlist-content-layout-v'></div></div>");
				}
				
				// append button
				$('.button-bottom').trigger('remove').remove();
				var divButton = '<div class="button-bottom" style="clear: both;">'+
					'<a href="#javascript" class="bt_red" onclick="return btnDeleteAllItem()">'+
						'<span class="bt_red_lft"></span>'+
						'<strong>Xóa nội dung </strong>'+
						'<span class="bt_red_r"></span>'+
					'</a>'+
					'<a href="#javascript" class="bt_blue" onclick="return btnSaveAllItem()">'+
						'<span class="bt_blue_lft"></span>'+
						'<strong>Lưu danh sách</strong>'+
						'<span class="bt_blue_r"></span>'+
					'</a>'+
					'<a href="#javascript" class="bt_green" onclick="return btnCreatePlaylist()">'+
						'<span class="bt_green_lft"></span>'+
						'<strong>Tạo mới</strong>'+
						'<span class="bt_green_r"></span>'+
					'</a>'+
				'</div>';
				$(divButton).insertAfter( "#item-playlist");
				
				// draw table playlist
				updateTablePlaylist(response.layoutItem);

				// get layout and show on table playlist view
				_length = response.layout.length;
				
				for(var i = 0; i < _length; i++){
					var starttime = response.layout[i].startTime;
					var endtime = response.layout[i].endTime;
					var time = toSecond(endtime) - toSecond(starttime);
					var timetotal = toHHMMSS(time);
					var idColumn = '.' + response.layout[i].idSubLayout;
					var name = $(idColumn).attr('name');
					var item = '';
					if(response.layout[i].type == 'Video'){
						item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
									'class="item-video item-clone item-clone-video item-video-ajax" item-tyle="video" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
									'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+ 'url="'+response.layout[i].url+'"' +
									'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
									response.layout[i].name + 
								'</li>');
					} else if(response.layout[i].type == 'Image'){
						item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
									'class="item-image item-clone-image item-clone item-image-ajax" item-tyle="image" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
									'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+ 'url="'+response.layout[i].url+'"' +
									'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
									response.layout[i].name + 
								'</li>');
					} else if(response.layout[i].type == 'Browser'){
						item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
									'class="item-browser item-clone-browser item-clone item-browser-ajax" item-tyle="browser" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
									'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+ 'url="'+response.layout[i].url+'"' +
									'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
									response.layout[i].name + 
								'</li>');
					} else if(response.layout[i].type == 'Text'){
						item = $('<li title="Tên:'+response.layout[i].name+'  Bắt Đầu:'+starttime+'  Kết Thúc:'+endtime+'  Tổng:'+timetotal+'" '+
									'class="item-text item-clone-text item-clone item-text-ajax" item-tyle="text" time-start="'+starttime+'" time-end="'+endtime+'" time-total="'+timetotal+'" name-item="'+response.layout[i].name+'" id-content="'+response.layout[i].id+'" '+
									'style="margin: 0px; color: rgb(255, 255, 255);position: absolute;" '+ ' url="'+response.layout[i].url+'"' + ' color="'+response.layout[i].color+'"' + ' font="'+response.layout[i].font +'"' +  ' size="'+response.layout[i].size+'"' +
									'name="'+name+'" item-id="'+response.layout[i].idSubLayout+'" index="'+i+'" id-private="'+response.layout[i].idItem+'">'+
									response.layout[i].name + 
								'</li>');
					}
					
					$(item).css({
						'width': $(idColumn).width(),
						'height': parseInt(toSecond(timetotal)),
						'top': parseInt(toSecond(starttime)),
						'left': $(idColumn).position().left
					});
					
					$(item).draggable({
						helper: 'original',
						containment: idColumn,
						tolerance: 'fit',
						drag: handleDragItem,
						stop: stopDragItem,
						scroll: true
					});
					if($(item).attr('item-tyle') != 'video'){
						$(item).resizable({
							containment: idColumn,
							autoHide: true,
							handles: 's',
							resize: handleResizeItem,
							stop: stopResize
						});
					}
					
					$(idColumn).append(item);
					// add element to arrItem
					$arrItem.push(item);
				}
				
				// ******playlist show review
				var urlHost = response.data;
				drawReviewPlaylist(response.layout, response.layoutItem, direction, urlHost);
				
				$('.item-clone').each(function(){
					$(this).on('click', function(){
						$('.container-playlist').find('.item-select').removeClass('item-select');
						$(this).addClass('item-select');
						drawDetailItem($.trim($(this).text()), $(this).attr('time-total'), 
								$(this).attr('time-start'), $(this).attr('time-end'), $(this).attr('item-tyle'), $(this).attr('index'));
					});
					
				});
			}
		);
	}).error(function(){
		console.log("Server turn off");
	});
}
function drawRowItemPlaylist(playlist){
	var item = "<tr>"+
					"<td title="+playlist.name+" style='width: 50%'>"+subString(playlist.name)+"</td>"+
					/* "<td style='text-align: center; width: 20%'>"+playlist.startTime+"</td>"+ */
					"<td style='text-align:center;'>" +
						"<div class='input-append time'>"+
							"<input name='timestart-detail"+playlist.idItem + "' class='timestart-detail"+playlist.idItem + "'  placeholder='00:00:00' value='"+playlist.startTime+ "' onblur='timestartBlurDetail($(this).val()," + playlist.idItem + ")' onfocus='timestartFocusDetail("+playlist.idItem+")' required='required' maxlength='8' style='width: 60px' data-format='hh:mm:ss' type='text'>"+
							"<span class='add-on'>"+ "<i data-time-icon='icon-time' class='icon-time'>"+"</i>"+
							"</span>"+
						"</div>" + 
					"</td>" +
					/* "<td style='text-align: center; width: 20%'>"+playlist.endTime+"</td>"+ */
					"<td style='text-align:center;'>" +
						"<div class='input-append time'>"+
							"<input name='timeend-detail"+playlist.idItem + "' class='timeend-detail"+playlist.idItem + "'  placeholder='00:00:00' value='"+playlist.endTime+ "' onblur='timeendBlurDetail($(this).val()," + playlist.idItem + ")' onfocus='timeendFocusDetail("+playlist.idItem+")' required='required' maxlength='8' style='width: 60px' data-format='hh:mm:ss' type='text'>"+
							"<span class='add-on'>"+ "<i data-time-icon='icon-time' class='icon-time'>"+"</i>"+
							"</span>"+
						"</div>" + 
					"</td>" +
				"</tr>";
	return item;
}
var $timeStartPlaylist = 0;
var $timeLoopPlaylist = 0;
var $timeStartItem = 0;
var $timeEndItem = 0;
/**
 * TV on show review
 * @param layoutItem
 * @param direction
 * @param urlHost
 */
function drawReviewPlaylist(layout, layoutItem, direction, urlHost){
	clearInterval($timeLoopPlaylist);
	window.clearTimeout($timeStartPlaylist);
	window.clearTimeout($timeStartItem);
	window.clearTimeout($timeEndItem);
	$('#item-playlist').empty();
	// detect direction to show TV
	var containment = '.playlist-content-layout-h-review';
	if(direction == '0'){
		$('#item-playlist').append("<div class='layout-view-right layout-view-h-review'><div class='playlist-content-layout-h-review'></div></div>");
	} else if(direction == '1'){
		$('#item-playlist').append("<div class='layout-view-right layout-view-v-review'><div class='playlist-content-layout-v-review'></div></div>");
		containment = '.playlist-content-layout-v-review';
	}
	// set time start show playlist
	$timeStartPlaylist = window.setTimeout(function(){
		console.log('PLAY PLAYLIST');
		$('#item-playlist').empty();
		// detect direction to show TV
		var containment = '.playlist-content-layout-h-review';
		if(direction == '0'){
			$('#item-playlist').append("<div class='layout-view-right layout-view-h-review'><div class='playlist-content-layout-h-review'></div></div>");
		} else if(direction == '1'){
			$('#item-playlist').append("<div class='layout-view-right layout-view-v-review'><div class='playlist-content-layout-v-review'></div></div>");
			containment = '.playlist-content-layout-v-review';
		}
		//sort time to show screen
		layout.sort(function(a, b){
			var t1 =  toSecond(a.startTime);
			var t2 =  toSecond(b.startTime);
			return t1-t2;
		});

		fnDrawNextItem(layout, 0, layoutItem, urlHost, containment, direction);
		$timeLoopPlaylist = setInterval(function(){
			fnDrawNextItem(layout, 0, layoutItem, urlHost, containment, direction);
		}, getMaxTimePlaylist());
		
	}, getMinTimePlaylist());

}
var $TIME_TMP = [];
var $INTERVAL = 0;
function fnDrawNextItem(layout, index, layoutItem, urlHost, containment, direction){
	clearInterval($INTERVAL);
	$TIME_TMP = [];
	var k = 0;
	$INTERVAL = setInterval(function(){
		// check draw item same time play
		var start = k*1000;
		for(var i = 0; i < layout.length; i++){
			if(start ===  toSecond(layout[i].startTime)*1000){
				//index+= 1;
				//console.log("NEXT INDEX: " + index);
				var indx = getIndex(layoutItem, layout[i].idSubLayout);
				console.log("INDEX: " + indx);
				var id = layoutItem[indx].id;
				var idContent = layout[i].id;
				var idPr = layout[i].idItem
				var name = getNameItemPlaylist(idContent, idPr);
				var type = layoutItem[indx].type;
				var _left = parseFloat(layoutItem[indx].left);
				var _top =  parseFloat(layoutItem[indx].top);
				var _w = parseFloat(layoutItem[indx].width);
				var _h = parseFloat(layoutItem[indx].height);
				playLayoutItemReview(layout, id, idContent, idPr , urlHost, name, containment, direction, type, _left, _top, _w, _h);
				var end = toSecond(layout[i].endTime)*1000;
				// set time end
				var obj = new Object();
				obj.id = id;
				obj.time = end - start;
				$TIME_TMP.push(obj);
				console.log("LENGTH TIME: " + $TIME_TMP.length);
				window.setTimeout(function(){
					console.log('ITEM ' + name + ' HIDE');
					if($TIME_TMP.length > 0){
						var id = $TIME_TMP[0].id;
						$('.' + id + '-review').trigger('remove').remove();
						// call next
						$TIME_TMP.splice(0,1);
						console.log("LENGTH TIME: " + $TIME_TMP.length);
						//fnDrawNextItem(layout, index, layoutItem, urlHost, containment, direction);
					} else {
						//fnDrawNextItem(layout, index, layoutItem, urlHost, containment, direction);
					}
					
				}, end - start);
			}
		}
		$TIME_TMP.sort(function(a,b){
			return parseInt(a.time)-parseInt(b.time);
		});	
		k+=1;
	}, 1000);
	
	
}
function getIndex(array, id){
	var length = array.length;
	for(var  i = 0; i < length; i++){
		if(array[i].id === id){
			return i;
		}
	}
	return -1;
}
function getNameItemPlaylist(id, idPr){
	var name = '';
	var _length = $arrItem.length;
	for(var i = 0; i <  _length; i++){
		if($arrItem[i].attr('id-content') == id && $arrItem[i].attr('id-private') == idPr){
			name = $.trim($arrItem[i].text());
			return name;
		}
	}
	return name;
}
function getMinTimePlaylist(){
	var _length = $arrItem.length;
	var min = 0;
	if(_length > 0){
		min = toSecond($arrItem[0].attr('time-start'));
		for(var i = 0; i < _length; i++){
			var _time = toSecond($arrItem[i].attr('time-start'));
			if(_time < min){
				min = _time;
			}
		}
	}
	
	return min*1000;
} 
function getMaxTimePlaylist(){
	var _length = $arrItem.length;
	var max = 0;
	if(_length > 0){
		max = toSecond($arrItem[0].attr('time-end'));
		for(var i = 0; i < _length; i++){
			var _time = toSecond($arrItem[i].attr('time-end'));
			if(_time > max){
				max = _time;
			}
		}
	}
	return max*1000;
}
var $jsonTimeStart = {'idprivate':'0'};
var $jsonTimeEnd = {'idprivate':'0'};
function getTimeStartItem(id){
	var _time = 0;
	var _length = $arrItem.length;
	for(var i = 0; i <  _length; i++){
		if($arrItem[i].attr('item-id') == id){
			_time = toSecond($arrItem[i].attr('time-start'));
			return _time*1000;
		}
	}
	
	return _time*1000;
} 
function getTimeEndItem(id){
	var _time = 0;
	var _length = $arrItem.length;
	for(var i = 0; i <  _length; i++){
		if($arrItem[i].attr('item-id') == id){
			_time = toSecond($arrItem[i].attr('time-end'));
			return _time*1000;
		}
	}
	return _time*1000;
}
function getContentText(id){
	var _length = $arrItem.length;
	var json = {'url':'', 'color':'', 'font':'', 'size':''};
	for(var i = 0; i < _length; i++){
		if(id == $arrItem[i].attr('item-id') && $arrItem[i].attr('item-tyle') == 'text'){
			json = {'url': $arrItem[i].attr('url'), 'color': $arrItem[i].attr('color'), 'font': $arrItem[i].attr('font'),'size': $arrItem[i].attr('size')};
		}
	}
	return json;
}
function getUrlBrowser(id){
	var _length = $arrItem.length;
	var url = '';
	for(var i = 0; i < _length; i++){
		if(id == $arrItem[i].attr('item-id') && $arrItem[i].attr('item-tyle') == 'browser'){
			url = $arrItem[i].attr('url');
		}
	}
	return url;
}
/**
 * 
 * @param layout: List content have in table playlist
 * @param id
 * @param urlHost
 * @param name
 * @param containment
 * @param direction
 * @param type
 * @param _left
 * @param _top
 * @param _w
 * @param _h
 */
function playLayoutItemReview(layout, id, idContent, idPr, urlHost, name, containment, direction, type, _left, _top, _w, _h){
	if(name == ""){
		return;
	}
	var item;
	if(type == 'Video'){//only play 1 video
		item = $("<video class='"+idPr+"-review' autoplay>" +
					"<source src='"+urlHost + name + "' type='video/mp4'/>"+
				"</video>");
	} else if(type == 'Image'){// muiltiple image
		if(name.indexOf('.png') >= 0 || name.indexOf('.jpg') >= 0 || name.indexOf('.jpeg') >= 0){
			item = $("<img  class='"+idPr+"-review' src='"+urlHost+ name + "'></img>");
		} else { // slide
			item = $("<img  class='"+idPr+"-review "+idContent+"-slide-image' src=''></img>");
			$.post('getSlide.elcom',{data: idContent}, function(response){
				if(response.content != null){
					response.content.sort(function(a,b){
						var t1 = parseInt(a.type);
						var t2 = parseInt(b.type);
						return t1-t2;
					});
					fnPlaySilde(response.content, urlHost, parseInt(response.content[0].time)*1000, idContent);
				}
			});
		}
		
	} else if(type == 'Browser'){// muiltiple browser
		// get from database
		var url = getUrlBrowser(id);
		item = $("<iframe  class='"+idPr+"-review' src='" + url + "'></iframe>");
		
	} else if(type == 'Text'){// muiltiple text
		// get from database
		var url = getContentText(id).url;
		var color = getContentText(id).color;
		var font = getContentText(id).font;
		var size = getContentText(id).size;
		item = $("<span class='"+id+"-review' title='Text'>"+url+"</span>");
		$(item).css({
			'font-size': parseInt(size),
			'font-family': font,
			'color': color
		});
	}
//	var start = getTimeStartItem(id);
//	var end = getTimeEndItem(id);
//	console.log("TIME START " + id + "-" + name+ " " + start);
//	console.log("TIME END " + id + "-" + name+ " " +end);
	
	console.log('Start item ' + name);
	// rate h(detect default)
	var rateLeftH = 1280/337;
	var rateTopH = 720/188;
	// rate v(detect default)
	var rateLeftV = 720/147;
	var rateTopV = 1280/152;
	
	var sizeScreen = $('#playlist-layout option:selected').attr('size-screen');
	if(direction == '0'){
		if(sizeScreen == "4"){
			rateLeftH = (3840/337);
			rateTopH = (2160/188);
		} else if(sizeScreen == "3"){
			rateLeftH = (2048/337);
			rateTopH = (1080/188);
		} else if(sizeScreen == "2"){
			rateLeftH = (1920/337);
			rateTopH = (1080/188);
		} else {
			rateLeftH = (1280/337);
			rateTopH = (720/188);
		}
		
		$(item).css({
			'left': _left/rateLeftH,
			'top': _top/rateTopH,
			'width': _w/rateLeftH,
			'height':_h/rateTopH,
			'position': 'absolute'
		});
	} else if(direction == '1'){
		if(sizeScreen == "4"){
			rateLeftV = (2160/147);
			rateTopV = (3840/152);
		} else if(sizeScreen == "3"){
			rateLeftV = (1080/147);
			rateTopV = (2048/152);
		} else if(sizeScreen == "2"){
			rateLeftV = (1080/147);
			rateTopV = (1920/152);
		} else {
			rateLeftV = (720/147);
			rateTopV = (1280/152);
		}
		$(item).css({
			'left': _left/rateLeftV,
			'top': _top/rateTopV,
			'width': _w/rateLeftV,
			'height':_h/rateTopV,
			'position': 'absolute'
		});
	}
	$(containment).append(item);
	try {
		$('.' + idPr + '-review')[0].play();
	}
	catch(err) {
	    console.log(err.message);
	}
}
function fnPlaySilde(array, host, time, idContent){
	if(array.length <= 0){
		return;
	}
	$('.' + idContent+ '-slide-image').attr('src', host + array[0].name);
	window.setTimeout(function(){
		array.splice(0,1);
		console.log("REMOVE ITEM fnPlaySilde");
		if(array.length > 0){
			fnPlaySilde(array, host, parseInt(array[0].time)*1000, idContent);
		}
	}, time);
}
function drawTableItemPlaylist(){
	var item = "<div class='layout-view-right playerofgroup' style='width: 58%;'>"+
					"<table>"+
					"<thead>"+
						"<tr>"+
							"<th style='width:35%'>Tên</th>"+
							"<th style='width:20%'>Bắt Đầu</th>"+
							"<th style='width:20%'>Kết Thúc</th>"+
						"</tr>"+
					"</thead>"+
					"<tbody id='scrolling' class='row-playlist-item'>"+
					"</tbody>"+
				"</table>"+
				"</div>";
	return item;
}
function selectlayoutPlaylistChange(){
	var type = $('#selectplaylistlayout').val();
	$.post('ajaxGetLayout.elcom',
			{
				data: type
			},
			function(response){
				$.post('ajaxGetLayout.elcom',{data:type}, function(response){
					$('.playlist li').removeClass('background-item-playlist');
					$('.layout-view-right').trigger('remove').remove();
					$('.layout-view-left').empty();
					var _length = response.layout.length;
					var option = "";
					if(type== '-1'){
						option = "<option selected='selected' value='-1'>Tất cả</option>"+
						"<option value='1'>Bố Cục Mẫu</option>" +
						"<option value='0'>Bố Cục Đã Tạo</option>";
					} else if(type == '0'){
						option = "<option value='-1'>Tất cả</option>"+
						"<option value='1'>Bố Cục Mẫu</option>" +
						"<option selected='selected' value='0'>Bố Cục Đã Tạo</option>";
					} else if(type == '1') {
						option= "<option value='-1'>Tất cả</option>"+
						"<option selected='selected' value='1'>Bố Cục Mẫu</option>" +
						"<option value='0'>Bố Cục Đã Tạo</option>";
					}
					var select = "<label>"+ 
									"<span style='line-height: 15px'>Bố Cục :</span>"+ 
									"<select id='selectplaylistlayout' onchange='selectlayoutPlaylistChange()'>"+
									option +
									"</select>" +
									"<select id='playlist-layout' onchange='ajaxGetLayoutItem()'></select>"+ 
								"</label>";
					var option = "";
					$('.layout-view-left').prepend(select);
					for(var i = 0; i < _length; i++){
						option = option + "<option size-screen='"+response.layout[i].size+"' type='"+response.layout[i].type+"' " +
										  "direction='"+response.layout[i].direction+"' " +
										  "value='"+response.layout[i].id+"'>"+ response.layout[i].name +
						 				  "</option>";
					}
					$('#playlist-layout').append(option);
					var direction = response.layout[0].direction;
					if(direction == '0'){
						$('.layout-view-left').append("<div class='layout-view-h'><div class='playlist-content-layout-h'></div></div>");
					} else if(direction == '1'){
						$('.layout-view-left').append("<div class='layout-view-v'><div class='playlist-content-layout-v'></div></div>");
					}
					updateTablePlaylist(response.layoutItem);	
					
					
				});
			}
	).error(function(){
		console.log("Server turn off");
	});
}
// trigger init ready class schedule-load-content
$(function(){
	$('.playlist').on('ready',function(){
		ajaxLoadLayout('-1');
	});
	$('.playlist').trigger('ready');
});
// $(function(){
// $('.playlist').on('ready', function(){
//		
// var id = $('.playlist li').first().attr('id');
// if(typeof id != 'undefined'){
// $('#' + id).addClass('background-item-playlist');
// $('.playlist li').first().trigger('click');
// }
// });
// $('.playlist').trigger('ready');
// });
// $(function(){
// $('.load-content').on('ready',function(){
// ajaxContent();
// });
// $('.load-content').trigger('ready');
// });
function ajaxContent(){
	$.post('ajaxContent.elcom',function(response){
		// load all group
		var item = '';
		var length = response.group.length;
		if(length > 1){
			for(var i = 1; i <  length; i++){
				item = item + '<option value="'+response.group[i].id+'">'+response.group[i].nameFull+'</option>';
			}
		}
		
		$('#groupbox-content').empty();
		$('#groupbox-content').append(item);
		if(response.group.length > 1){
			// trigger item first
			$('#groupbox-content').val(response.group[1].id).trigger('change');
		}
		
	});
	
}
function btnLeftContentClick(){
	$("#selectleftcontent option:selected").each(function() {
		$(this).appendTo('#selectrightcontent');
		var contentId = $(this).val();
		ajaxRemoveContent($groupId, contentId);
    });
return false;
}
function btnRightContentClick(){
	$("#selectrightcontent option:selected").each(function() {
		$(this).appendTo('#selectleftcontent');
		var contentId = $(this).val();
		ajaxAddContent($groupId, contentId);
    });
return false;
}
function ajaxAddContent(groupId, contentId){
	var json = {groupid: groupId, idcontent:contentId};
	var obj = JSON.stringify(json);
	$.post('ajaxAddContent.elcom',{data: obj},function(response){
			// createContentGroup(response);
		}
	).error(function(){
		console.log("Server turn off");
	});
	return false;
}
function ajaxRemoveContent(groupId, contentId){
	var json = {groupid: groupId, idcontent:contentId};
	var obj = JSON.stringify(json);
	$.post(
			'ajaxRemoveContent.elcom',
			{
				data: obj
			},
			function(response){
				// createContentGroup(response);
			}
			
	).error(function(){
		console.log("Server turn off");
	});
	return false;
}
function groupContentChange(){
	$groupId = $('#groupbox-content').val();
	$.post('ajaxContent.elcom',{data: $groupId}, 
			function(response) {
				createContentGroup(response);
			}
	).error(function() { 
		console.log("Server turn off"); 
	});
}
function ajaxLoadLayout(type){
	$.post('ajaxGetLayout.elcom',{data:type}, function(response){
		$('.layout-view-left').empty();
		if(response.layout == null){
			return;
		}
		var _length = response.layout.length;
		var select = "<label>"+ 
						"<span style='line-height: 15px'>Bố Cục :</span>"+ 
						"<select id='selectplaylistlayout' onchange='selectlayoutPlaylistChange()'>"+
							"<option value='-1'>Tất cả</option>"+
							"<option value='1'>Bố Cục Mẫu</option>" +
							"<option value='0'>Bố Cục Đã Tạo</option>"+
						"</select>" +
						"<select id='playlist-layout' onchange='ajaxGetLayoutItem()'></select>"+ 
					"</label>";
		var option = "";
		$('.layout-view-left').prepend(select);
		for(var i = 0; i < _length; i++){
			option = option + "<option size-screen='"+response.layout[i].size+"' type='"+response.layout[i].type+"' " +
							  "direction='"+response.layout[i].direction+"' " +
							  "value='"+response.layout[i].id+"'>"+ response.layout[i].name +
			 				  "</option>";
		}
		$('#playlist-layout').append(option);
		var direction = response.layout[0].direction;
		if(direction == '0'){
			$('.layout-view-left').append("<div class='layout-view-h'><div class='playlist-content-layout-h'></div></div>");
		} else if(direction == '1'){
			$('.layout-view-left').append("<div class='layout-view-v'><div class='playlist-content-layout-v'></div></div>");
		}
		updateTablePlaylist(response.layoutItem);	
		
		// trigger load playlist select first
		var id = $('.playlist li').first().attr('id');
		if(typeof id != 'undefined'){
			$('#' + id).addClass('background-item-playlist');
			$('.playlist li').first().trigger('click');
		}
	});
}
function updateTablePlaylistDaily(layoutItem){
	if(layoutItem == null || layoutItem.length == 0){
		return;
	}
	var _lengthItem = layoutItem.length;
	var arrVideo = [];
	var arrImage = [];
	var arrText = [];
	var arrBrowser = [];
	var contaiment = '';
	for(var i = 0; i < _lengthItem; i++){
		// draw table playlist
		var json = {'name': layoutItem[i].name, 'id': layoutItem[i].id};
		if(layoutItem[i].type == 'Video'){
			arrVideo.push(json);
		} else if(layoutItem[i].type == 'Image'){
			arrImage.push(json);
		}else if(layoutItem[i].type == 'Browser'){
			arrBrowser.push(json);
		}else if(layoutItem[i].type == 'Text'){
			arrText.push(json);
		}
	}
	initPlaylistDaily(arrVideo, arrImage, arrBrowser, arrText);
}
function updateTablePlaylist(layoutItem){
	var _lengthItem = layoutItem.length;
	var arrVideo = [];
	var arrImage = [];
	var arrText = [];
	var arrBrowser = [];
	var direction = $('#playlist-layout option:selected').attr('direction');
	var contaiment = '';
	if(direction == '0'){
		contaiment = '.playlist-content-layout-h';
	} else{
		contaiment = '.playlist-content-layout-v';
	}
	for(var i = 0; i < _lengthItem; i++){
		// draw layout on screen
		var name = layoutItem[i].name;
		var type = layoutItem[i].type;
		var _left =parseFloat( layoutItem[i].left);
		var _top =  parseFloat(layoutItem[i].top);
		var _w = parseFloat(layoutItem[i].width);
		var _h = parseFloat(layoutItem[i].height);
		drawLayoutItem(contaiment, name, direction, type, _left, _top, _w, _h);
		// draw table playlist
		var json = {'name': layoutItem[i].name, 'id': layoutItem[i].id};
		if(layoutItem[i].type == 'Video'){
			arrVideo.push(json);
		} else if(layoutItem[i].type == 'Image'){
			arrImage.push(json);
		}else if(layoutItem[i].type == 'Browser'){
			arrBrowser.push(json);
		}else if(layoutItem[i].type == 'Text'){
			arrText.push(json);
		}
	}
	initPlaylist(arrVideo, arrImage, arrBrowser, arrText);
}
function ajaxGetLayoutItem(){
	var id = $('#playlist-layout').val();
	var direction = $("#playlist-layout option[value='"+id+"']").attr('direction');
	$('.layout-view-h').trigger('remove').remove();
	$('.layout-view-v').trigger('remove').remove();
	if(direction == '0'){
		$('.layout-view-left').append("<div class='layout-view-h'><div class='playlist-content-layout-h'></div></div>");
	} else if(direction == '1'){
		$('.layout-view-left').append("<div class='layout-view-v'><div class='playlist-content-layout-v'></div></div>");
	}
	$.post('ajaxGetLayoutItem.elcom',{data: id}, function(response){
		updateTablePlaylist(response.layoutItem);
	}).error(function(){
		alert('Server turn off');
	});
	// update array data playlist
	$arrItem.length = 0;
	// remove background select playlist
	$('.playlist li').removeClass('background-item-playlist');
	// remove table detail playlist item
	$('.layout-view-right').trigger('remove').remove();
}
function drawLayoutItem(containment, name, direction, type, _left, _top, _w, _h){
	var item;
	var sizeScreen = $('#playlist-layout option:selected').attr('size-screen');
	if(direction == '0'){
		if(sizeScreen == "4"){
			$rateLeftHorizontal = (3840/236);
			$rateTopHorizontal = (2160/120);
		} else if(sizeScreen == "3"){
			$rateLeftHorizontal = (2048/236);
			$rateTopHorizontal = (1080/120);
		} else if(sizeScreen == "2"){
			$rateLeftHorizontal = (1920/236);
			$rateTopHorizontal = (1080/120);
		} else {
			$rateLeftHorizontal = (1280/236);
			$rateTopHorizontal = (720/120);
		}
		if(type == 'Video'){
			item = $('<div title="Video" class="drag-video-clone text"></div>');
			// $(item).append('<span>'+name+'</span>');
		} else if(type == 'Image'){
			item = $("<div title='Image' class='drag-image-clone text'></div>");
			// $(item).append('<span>'+name+'</span>');
		} else if(type == 'Browser'){
			item = $("<div title='Browser' class='drag-browser-clone text'></div>");
			// $(item).append('<span>'+name+'</span>');
		} else if(type == 'Text'){
			item = $("<div title='Text' class='drag-text-clone text'></div>");
			// $(item).append('<span>'+name+'</span>');
		}
	} else if(direction == '1'){
		if(sizeScreen == "4"){
			$rateLeftHorizontal = (2160/236);
			$rateTopHorizontal = (3840/120);
		} else if(sizeScreen == "3"){
			$rateLeftHorizontal = (1080/236);
			$rateTopHorizontal = (2048/120);
		} else if(sizeScreen == "2"){
			$rateLeftHorizontal = (1080/236);
			$rateTopHorizontal = (1920/120);
		} else {
			$rateLeftHorizontal = (720/236);
			$rateTopHorizontal = (1280/120);
		}
		if(type == 'Video'){
			item = $('<div title="Video" class="clone text"></div>');
			// $(item).append('<span>'+name+'</span>');
		} else if(type == 'Image'){
			item = $("<div title='Image' class='clone text'></div>");
			// $(item).append('<span>'+name+'</span>');
		} else if(type == 'Browser'){
			item = $("<div title='Browser' class='clone text'></div>");
			// $(item).append('<span>'+name+'</span>');
		} else if(type == 'Text'){
			item = $("<div title='Text' class='clone text'></div>");
			// $(item).append('<span>'+name+'</span>');
		}
	}
	$(item).css({
		'left': setLeftPos(_left, direction),
		'top': setTopPos(_top, direction),
		'width': setSubWidth(_w, direction),
		'height': setSubHeight(_h, direction),
		'position': 'absolute'
	});
	$(containment).append(item);
}
// Horizontal default: W: 236 H:120 = 0.0.1280.720
var $rateLeftHorizontal = (1280/236);
var $rateTopHorizontal = (720/120);
// Vertical default: W:314 H:363 = 0.0.720.1280
var $rateLeftVertical = (720/105);
var $rateTopVertical = (1280/124);
function setLeftPos(xpos, status){
	var xdefault = 0;
	var result = 0;
	if(status == '0'){
		result = (xpos)/$rateLeftHorizontal;
		console.log("xpos:" + xpos + " xdefault:" + xdefault);
		
	} else if(status == '1'){
		result = (xpos)/$rateLeftVertical;
	}
	
	return (Math.round(result));
}
function setTopPos(ypos, status){
	var orientation = $('#screensize').val();
	var ydefault = 0;
	var result = 0;
	if(status == '0'){
		// ydefault = $('.playlist-content-layout-h').position().top;
		result = (ypos)/$rateTopHorizontal;
	} else if(status == '1'){
		// ydefault = $('.playlist-content-layout-v').position().top;
		result = (ypos)/$rateTopVertical;
	}
	return (Math.round(result));
}
function setSubWidth(width, status){
	if(status == '0'){
		return (Math.round(width/$rateLeftHorizontal));
	} else if(status == '1'){
		console.log("X:" +width);
		return (Math.round(width/$rateLeftVertical));
	}
}
function setSubHeight(height, status){
	if(status == '0'){
		return (Math.round(height/$rateTopHorizontal));
	} else if(status == '1'){
		console.log("Y:" + height);
		return (Math.round(height/$rateTopVertical));
	}
}
function subString(str){
	var tmp = '';
	str = $.trim(str);
	if(str.length > LENGTH){
		tmp = str.substring(0, LENGTH) + '...';
		return tmp;
	}
	return str;
}
function selectSubjectChange(){	
	var type = $('input:radio[name="radiog_dark"]:checked').val();
	if(typeof type == 'undefined'){
		type = $('.selecttype-click').attr('type');
	}
	var subjectid = $('#selectsubject').val();
	var json ={'type': type, 'subjectid': subjectid};
	var obj = JSON.stringify(json);
	$.post('ajaxGetContentByType.elcom',{data: obj},function(response){
		$('.item-content').empty();
		var template = '';
		var item = '';
		var length = response.content.length;
		if(length == 0){
			template = "<li>"
								+ "Dữ liệu không tìm thấy"+
						"</li>";
			$('.item-content').append(template);
		} else {
			for(var i = 0; i < length; i++){
				// video || audio
				if(response.content[i].type == 'Video'){
					item = "<li title='"+response.content[i].nameFull+"' id='"+response.content[i].id+"' class='item-video item-video-ajax' item-tyle='video'" +
								"time-start='00:00:00' time-end='"+response.content[i].time+"' " +
								"time-total='"+response.content[i].time+"' id-private='-1'>"
								+
								"<div class='content-icon-video'>"+
									"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
								"</div>" +
						"</li>";
				} else if(response.content[i].type == 'Image'){// image
					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-image item-image-ajax' item-tyle='image'" +
								"time-start='00:00:00' time-end='00:00:20' " +
								"time-total='00:00:20' id-private='-1'>"
								+
								"<div class='content-icon-image'>"+
									"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
								"</div>" +
						"</li>";
				} else if(response.content[i].type == 'Browser'){// link
					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-browser item-browser-ajax' item-tyle='browser'" +
								"time-start='00:00:00' time-end='00:00:20' " +
								"time-total='00:00:20' id-private='-1'>"
								+
								"<div class='content-icon-browser'>"+
									"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
								"</div>" +
						"</li>";
				} else if(response.content[i].type == 'Text'){// text
					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-text item-text-ajax' item-tyle='text'" +
									"time-start='00:00:00' time-end='00:00:20' " +
									"time-total='00:00:20' id-private='-1'>"
									+
    								"<div class='content-icon-text'>"+
										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
									"</div>" +
							"</li>";
				} 
				
				template = template + item;
				
			}
			$('.item-content').append(template);
			initDraggable('.item-video-ajax');
			initDraggable('.item-image-ajax');
			initDraggable('.item-browser-ajax');
			initDraggable('.item-text-ajax');
		}
		
		
	});
}
function searchContent(){
	var txt = $('#input').val();
	var type = $('#selectsubject').val(); 
	if(txt != null){
		var json = {'type': type, 'text': txt};
		var jsonStr = JSON.stringify(json);
		$.post('ajaxAutoComplete.elcom', {data: jsonStr}, function(response){
			$('.item-content').empty();
			var template = '';
			var item = '';
			var length = response.content.length;
			if(length == 0){
				template = "<li>"
									+ "Dữ liệu không tìm thấy"+
							"</li>";
				$('.item-content').append(template);
			} else {
				for(var i = 0; i < length; i++){
					// video || audio
					if(response.content[i].type == 'Video'){
						item = "<li title='"+response.content[i].nameFull+"' id='"+response.content[i].id+"' class='item-video item-video-ajax' item-tyle='video'" +
									"time-start='00:00:00' time-end='"+response.content[i].time+"' " +
									"time-total='"+response.content[i].time+"' id-private='-1'>"
									+
    								"<div class='content-icon-video'>"+
										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
									"</div>" +
							"</li>";
					} else if(response.content[i].type == 'Image'){// image
						item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-image item-image-ajax' item-tyle='image'" +
									"time-start='00:00:00' time-end='00:00:20' " +
									"time-total='00:00:20' id-private='-1'>"
									+
    								"<div class='content-icon-image'>"+
										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
									"</div>" +
							"</li>";
					} else if(response.content[i].type == 'Browser'){// link
						item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-browser item-browser-ajax' item-tyle='browser'" +
									"time-start='00:00:00' time-end='00:00:20' " +
									"time-total='00:00:20' id-private='-1'>"
									+
    								"<div class='content-icon-browser'>"+
										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
									"</div>" +
							"</li>";
					} else if(response.content[i].type == 'Text'){// text
						item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-text item-text-ajax' item-tyle='text'" +
										"time-start='00:00:00' time-end='00:00:20' " +
										"time-total='00:00:20' id-private='-1'>"
										+
	    								"<div class='content-icon-text'>"+
											"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
										"</div>" +
								"</li>";
					} 
					
					template = template + item;
					
				}
				$('.item-content').append(template);
				initDraggable('.item-video-ajax');
				initDraggable('.item-image-ajax');
				initDraggable('.item-browser-ajax');
				initDraggable('.item-text-ajax');
			}
		});
	}
}
/* Handle periodic */
function initButtonTypeClick(){
	$('.button-type').on('click', function(){
		$('.button-type').removeClass('selecttype-click');
		$(this).addClass('selecttype-click');
		$('input:radio[name="radiog_dark"]:checked').removeAttr('checked');
		var type = $(this).attr('type');
		var subjectid = $('#selectsubject').val();
        var json = {'type': type,'subjectid':subjectid};
        var obj = JSON.stringify(json);
        $.post('ajaxGetContentByType.elcom',{data: obj},function(response){
    		$('.item-content').empty();
    		var template = '';
    		var item = '';
    		var length = response.content.length;
    		if(length == 0){
    			template = "<li>"
    								+ "Dữ liệu không tìm thấy"+
    						"</li>";
    			$('.item-content').append(template);
    		} else {
    			for(var i = 0; i < length; i++){
    				// video || audio
    				if(response.content[i].type == 'Video'){
    					item = "<li title='"+response.content[i].nameFull+"' id='"+response.content[i].id+"' class='item-video item-video-ajax' item-tyle='video'" +
    								"time-start='00:00:00' time-end='"+response.content[i].time+"' " +
    								"time-total='"+response.content[i].time+"' id-private='-1'>"+
    								"<div class='content-icon-video'>"+
  										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
  									"</div>" +
    						"</li>";
    				} else if(response.content[i].type == 'Image'){// image
    					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-image item-image-ajax' item-tyle='image'" +
    								"time-start='00:00:00' time-end='00:00:20' " +
    								"time-total='00:00:20' id-private='-1'>"+
    								"<div class='content-icon-image'>"+
										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
									"</div>" +
    						"</li>";
    				} else if(response.content[i].type == 'Browser'){// link
    					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-browser item-browser-ajax' item-tyle='browser'" +
    								"time-start='00:00:00' time-end='00:00:20' " +
    								"time-total='00:00:20' id-private='-1'>"
    								+
    								"<div class='content-icon-browser'>"+
										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
									"</div>" +
    						"</li>";
    				} else if(response.content[i].type == 'Text'){// text
    					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-text item-text-ajax' item-tyle='text'" +
    									"time-start='00:00:00' time-end='00:00:20' " +
    									"time-total='00:00:20' id-private='-1'>"
    									+
        								"<div class='content-icon-text'>"+
    										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
    									"</div>" +
    							"</li>";
    				} 
    				
    				template = template + item;
    				
    			}
    			$('.item-content').append(template);
    			initDraggable('.item-video-ajax');
    			initDraggable('.item-image-ajax');
    			initDraggable('.item-browser-ajax');
    			initDraggable('.item-text-ajax');
    		}
    		
    		
    	});
	});
}
function initSelectRadioButton(){
	$('input:radio[name="radiog_dark"]').change(
		    function(){
		    	//remove select type
		    	$('.button-type').removeClass('selecttype-click');
		        if ($(this).is(':checked')) {
		            var type = $(this).val();
		            var subjectid = $('#selectsubject').val();
		            var json = {'type': type,'subjectid':subjectid};
		            var obj = JSON.stringify(json);
		            $.post('ajaxGetContentByType.elcom',{data: obj},function(response){
		        		$('.item-content').empty();
		        		var template = '';
		        		var item = '';
		        		var length = response.content.length;
		        		if(length == 0){
		        			template = "<li>"
		        								+ "Dữ liệu không tìm thấy"+
		        						"</li>";
		        			$('.item-content').append(template);
		        		} else {
		        			for(var i = 0; i < length; i++){
		        				// video || audio
		        				if(response.content[i].type == 'Video'){
		        					item = "<li title='"+response.content[i].nameFull+"' id='"+response.content[i].id+"' class='item-video item-video-ajax' item-tyle='video'" +
		        								"time-start='00:00:00' time-end='"+response.content[i].time+"' " +
		        								"time-total='"+response.content[i].time+"' id-private='-1'>"
		        								+
		        								"<div class='content-icon-video'>"+
		    										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
		    									"</div>" +
		        						"</li>";
		        				} else if(response.content[i].type == 'Image'){// image
		        					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-image item-image-ajax' item-tyle='image'" +
		        								"time-start='00:00:00' time-end='00:00:20' " +
		        								"time-total='00:00:20' id-private='-1'>"
		        								+
		        								"<div class='content-icon-image'>"+
		    										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
		    									"</div>" +
		        						"</li>";
		        				} else if(response.content[i].type == 'Browser'){// link
		        					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-browser item-browser-ajax' item-tyle='browser'" +
		        								"time-start='00:00:00' time-end='00:00:20' " +
		        								"time-total='00:00:20' id-private='-1'>"
		        								+
		        								"<div class='content-icon-browser'>"+
		    										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
		    									"</div>" +
		        						"</li>";
		        				} else if(response.content[i].type == 'Text'){// text
		        					item = "<li title='"+response.content[i].nameFull+"'id='"+response.content[i].id+"' class='item-text item-text-ajax' item-tyle='text'" +
		        									"time-start='00:00:00' time-end='00:00:20' " +
		        									"time-total='00:00:20' id-private='-1'>"
		        									+
		            								"<div class='content-icon-text'>"+
		        										"<span style='margin-left: 36px;'>"+response.content[i].nameFull+"</span>"+
		        									"</div>" +
		        							"</li>";
		        				} 
		        				
		        				template = template + item;
		        				
		        			}
		        			$('.item-content').append(template);
		        			initDraggable('.item-video-ajax');
		        			initDraggable('.item-image-ajax');
		        			initDraggable('.item-browser-ajax');
		        			initDraggable('.item-text-ajax');
		        		}
		        		
		        		
		        	});
		        }
		    });
}
function logout(){
	$(location).attr('href','logout.elcom');
}
/*
 * event scroll $(function(){ var currentScroll = 0;
 * $('.container-playlist').scroll(function(){ var nextScroll =
 * $('.container-playlist').scrollTop(); if (nextScroll > currentScroll){
 * $('#scroll').text('Scroll DOWN:' + $('.container-playlist').scrollTop());
 * for(var i = 0; i < $arrItem.length; i++){ var _top =
 * $($arrItem[i]).offset().top - nextScroll; $arrItem[i].css('top', _top); } }
 * else { $('#scroll').text('Scroll UP:' +$('.container-playlist').scrollTop());
 * /*for(var i = 0; i < $arrItem.length; i++){ for(var i = 0; i <
 * $arrItem.length; i++){ var _top = $($arrItem[i]).offset().top + nextScroll;
 * $arrItem[i].css('top', _top); } } } //Updates current scroll position
 * currentScroll = nextScroll; })
 * 
 * });
 */
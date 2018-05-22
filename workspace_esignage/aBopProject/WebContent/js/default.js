/* global variable count video, image, browser, text dragged */
var $countVideoH = 0;
var $countImageH = 0;
var $countBrowserH = 0;
var $countTextH = 0;

var $countVideoV = 0;
var $countImageV = 0;
var $countBrowserV = 0;
var $countTextV = 0;
// id container table
var $aboptable = '#abop-table';
// id table
var $tabledetail = '#table-detail'; 
// screen honrizontal
var $honrizontal = '#honrizontal';
var $h = '-h';
// screen vertical
var $vertical = '#vertical';
var $v = '-v';
var $width = 1280;
var $height = 720;
// Horizontal default: X:568,5 Y:396 W: 500 H:280 = 0.0.1280.720
var $rateXHorizontal = ($width/500);
var $rateYHorizontal = ($height/280);
// Vertical default: X:650,5 Y:423 W:314 H:363 = 0.0.720.1280
var $rateXVertical = ($width/314);
var $rateYVertical = ($height/363);

//Horizontal default: W: 306 H:170 = 0.0.1280.720
var $rateLeftHorizontalDialog = ($width/306);
var $rateTopHorizontalDialog = ($height/170);
// Vertical default: W:135 H:175 = 0.0.720.1280
var $rateLeftVerticalDialog = ($width/135);
var $rateTopVerticalDialog = ($height/175);

// container item info layout Honizontal
var $jsonLayoutH = {'method':'save', 'id':'-1', 'size_screen':'1', 'direction':'', 'name': 'abop', 'items':[], 'deletes':[]};
// container item info layout Vertical
var $jsonLayoutV = {'method':'save', 'id':'-1', 'size_screen':'1', 'direction':'',  'name': 'abop', 'items':[],  'deletes':[]};

// container GROUPID
var $groupId;
// auto refresh
var autoRefresh;
$(document).ready(function () {
	init();
});

function init(){
	$('#tv-size').on('change', tvSizeChange);
	var msg = "Thông tin chi tiết chưa khởi tạo";
	$('.entry').append(drawMsgInfo(msg)).fadeIn();
	$('#layout-vertical').hide();
	$('#table-vertical').hide();
	if(fnGetRole() != 'USER_VIEW'){
		// init contxet menu group
		initContextMenuGroup();
		// init contextmenu create layout
		initContextMenuAdvanced('#content-select');
		// init contextmenu video
		initContextMenu('.drag-video-clone');
		// init contextmenu image
		initContextMenu('.drag-image-clone');
		// init contextmenu browser
		initContextMenu('.drag-browser-clone');
		// init contextmenu text
		initContextMenu('.drag-text-clone');
		// init contextmenu when select layout template
		initContextMenuTemplate('.clone');
		//
		initJConfirm('.delete-layout');
	
	    // element video draggable
	    $("#video").draggable({
	        helper: 'clone',
	        cursor: 'pointer',
			// drag: handleDrag,
	        tolerance: 'fit',
			revert: true
	    });
		$("#image").draggable({
	        helper: 'clone',
	        cursor: 'pointer',
			// drag: handleDrag,
	        tolerance: 'fit',
			revert: true
	    });
		$("#ebrowser").draggable({
	        helper: 'clone',
	        cursor: 'pointer',
			// drag: handleDrag,
	        tolerance: 'fit',
			revert: true
	    });
		$("#text").draggable({
	        helper: 'clone',
	        cursor: 'pointer',
			// drag: handleDrag,
	        tolerance: 'fit',
			revert: true
	    });
	
	    $($honrizontal).droppable({
				accept: ".drag-content > div",
	        	drop: function (e, ui) {
				// initDropable(ui, param1, param2)
				// ui: element dragged; param1: id container drop item; param2: tag
				// div container table
				initDropable(ui, $honrizontal, $aboptable + $h);
			}
		});
		$($vertical).droppable({
				accept: ".drag-content > div",
	        	drop: function (e, ui) {
				// initDropable(ui, param1, param2)
				// ui: element dragged; param: id container drop item; param2: tag
				// div container table
				initDropable(ui, $vertical, $aboptable + $v);
			}
		});
	}
}
function tvSizeChange(){
	var direction = $('#screensize').val();
	var val = $('#tv-size').val();
	if(direction == 0){
		$width = parseInt($('#tv-size option[value='+val+']').attr('width'));
		$height = parseInt($('#tv-size option[value='+val+']').attr('height'));
	} else if(direction == 1){
		$width = parseInt($('#tv-size option[value='+val+']').attr('height'));
		$height = parseInt($('#tv-size option[value='+val+']').attr('width'));
	}
	// Horizontal default: X:568,5 Y:396 W: 500 H:280 = 0.0.1280.720
	$rateXHorizontal = ($width/500);
	$rateYHorizontal = ($height/280);
	// Vertical default: X:650,5 Y:423 W:314 H:363 = 0.0.720.1280
	$rateXVertical = ($width/314);
	$rateYVertical = ($height/363);
	
	//Horizontal default: W: 306 H:170 = 0.0.1280.720
	$rateLeftHorizontalDialog = ($width/306);
	$rateTopHorizontalDialog = ($height/170);
	// Vertical default: W:135 H:175 = 0.0.720.1280
	$rateLeftVerticalDialog = ($width/135);
	$rateTopVerticalDialog = ($height/175);
	
}
function initJConfirm(classElement){
	$(classElement).jConfirmAction();
}
/* Handle revert item position init */
function isRevertItem(ui){
	// check element in containment
	var _select = $('#screensize').val();
	if(_select == '0'){
		var xHonrizontal = $('#honrizontal').offset().left;
		var yHonrizontal = $('#honrizontal').offset().top;
		if(ui.offset.left + 5 < xHonrizontal || getXPos(ui.offset.left + ui.helper.width()) > $width || 
		   ui.offset.top + 5 < yHonrizontal || getYPos(ui.offset.top + ui.helper.height()) > $height){
			return true;
		} else {
			return false;
		}
	} else if(_select == '1') {
		var xVertical = $('#vertical').offset().left;
		var yVertical = $('#vertical').offset().top;
		if(ui.offset.left + 5 < xVertical || getXPos(ui.offset.left + ui.helper.width()) > $width || 
		   ui.offset.top + 5 < yVertical || getYPos(ui.offset.top + ui.helper.height()) > $height){
			return true;
		} else {
			return false;
		}
	}
}
function initDropable(ui, containment, aboptable){
	if(isRevertItem(ui)){
		return;
	}
	var $x = null;
	var offsetXPos =  getXPos( ui.offset.left );
	var offsetYPos =  getYPos( ui.offset.top );
	var width = getWidth(ui.helper.width());
	var height = getHeight(ui.helper.height());
	
	// ui.draggable.draggable( 'option', 'revert', false );
	if ($(ui.draggable)[0].id != "") {
		// get info ui
		// get id of element dragged
		var id = ui.draggable.attr('id');
		// get title of element dragged
		var title=ui.draggable.attr('title');
		// get value size screen
		var _select = $('#screensize').val();
		// copy element
		$x = ui.helper.clone();
		ui.helper.remove();
		
		if (_select == '0') {
			// if dropable update method save
			$jsonLayoutH.method = "save";
			// name of element
			var name= id + "_h_" ;
			
			if(id=='video'){
			name = name + $countVideoH;
			$countVideoH += 1;
			$x.addClass('drag-video-clone');
			//$x.addClass('text');
			//$x.append('<span>Video</span>');
			$x.removeClass('drag-video');
			
			//$x.append("<img class='image-center' src='css/images/img-video.png'></img>");
			//$('.image-center').centerImage('inside');
		  } else if(id=='image'){
			  name = name + $countImageH;
			  $countImageH += 1;
			  $x.addClass('drag-image-clone');
			  //$x.addClass('text');
			  //$x.append('<span>Image</span>');
			  $x.removeClass('drag-image');
			  
		  } else if(id=='ebrowser'){
			  name = name + $countBrowserH;
			  $countBrowserH += 1;
			  $x.addClass('drag-browser-clone');
			  //$x.addClass('text');
			  //$x.append('<span>Browser</span>');
			  $x.removeClass('drag-browser');
		  
		  } else if(id=='text'){
			  name = name + $countTextH;
			  $countTextH += 1;
			  $x.addClass('drag-text-clone');
			  //$x.addClass('text');
			  //$x.append('<span>Text</span>');
			  $x.removeClass('drag-text');
		  }
		} else if(_select == '1') {
			// if dropable update method save
			$jsonLayoutV.method = "save";
			// name of element
			var name= id + "_v_" ;
			
			if(id=='video'){
				name = name + $countVideoV;
				$countVideoV += 1;
				$x.addClass('drag-video-clone');
				//$x.addClass('text');
				//$x.append('<span>Video</span>');
				$x.removeClass('drag-video');
				
			
		  } else if(id=='image'){
			  name = name + $countImageV;
			  $countImageV += 1;
			  $x.addClass('drag-image-clone');
			  //$x.addClass('text');
			  //$x.append('<span>Image</span>');
			  $x.removeClass('drag-image');
			  
		  } else if(id=='ebrowser'){
			  name = name + $countBrowserV;
			  $countBrowserV += 1;
			  $x.addClass('drag-browser-clone');
			 // $x.addClass('text');
			  //$x.append('<span>Browser</span>');
			  $x.removeClass('drag-browser');
		  
		  } else if(id=='text'){
			  name = name + $countTextV;
			  $countTextV += 1;
			  $x.addClass('drag-text-clone');
			  //$x.addClass('text');
			  //$x.append('<span>Text</span>');
			  $x.removeClass('drag-text');
		  }
		}
	
		// add attribute name into tag div purpos to determine only tag and
		// 'name' will id of row in table
		$x.attr('name', name);
		// add class to remove when right click
		$x.addClass(name);
		// draw table. If exist table then add row into table else draw table.
		if($(aboptable).length >= 1){
			addRow("-1", name, title, offsetXPos, offsetYPos, width, height);
		} else {
			drawTable("-1", name, title, offsetXPos, offsetYPos, width, height);
		}
		// set poition current
		$('#poitionCurrent').text("X:" + offsetXPos + " Y:" + offsetYPos  + 
							  " W:" + width + " H:" + height);
		$x.draggable({
			helper: 'original',
			containment: containment,
			tolerance: 'fit',
			drag: handleDrag
		});
		$x.resizable({
			containment: containment,
			autoHide: true,
			// helper: "ui-resizable-helper",
			resize: handleResize
			// animate: true,
			// animateDuration: "slow"
		});
		$x.appendTo(containment);
		$('#msg').fadeOut();
	}
}
/* Handle Drag */
function handleDrag( event, ui ) {
	var name = ui.helper.attr('name');
	var offsetXPos = getXPos( ui.offset.left );
	var offsetYPos = getYPos( ui.offset.top );
	var width = getWidth(ui.helper.width());
	var height = getHeight(ui.helper.height());
	var elementX='#' + name + 'x';
	var elementY='#' + name + 'y';
	$(elementX).find('input[type="number"]').val(offsetXPos);
	$(elementY).find('input[type="number"]').val(offsetYPos);
	// set poition current
	$('#poitionCurrent').text("X:" + offsetXPos + " Y:" + offsetYPos  + " W:" + width+ " H:" + height);
}
/* Handle Resize */
function handleResize(event, ui){
	var name = ui.originalElement.attr('name');
	var offsetXPos = getXPos( ui.originalPosition.left );
	var offsetYPos = getYPos( ui.originalPosition.top );
	var _w = getWidth(ui.size.width);
	var _h = getHeight(ui.size.height);
	var elementW='#' + name + 'w';
	var elementH='#' + name + 'h';
	$(elementW).find('input[type="number"]').val(_w);
	$(elementH).find('input[type="number"]').val(_h);
	// set poition current
	$('#poitionCurrent').text("X:" + offsetXPos + " Y:" + offsetYPos  + " W:" + _w + " H:" + _h);
}
/* Init context menu */
function initContextMenu(selector){
            $.contextMenu({
                selector: selector,
                build: function ($trigger, e) {
                    console.log(e);
					
                    return {
                        callback: function (e, options) {
							var _select = $('#screensize').val();
							if(_select == '0'){
								if(e == 'new'){
									newClick($trigger, $honrizontal);
								} else if(e == 'add'){
									// event addClick($trigger, param1)
									// $trigger is element selected
									// param: id of tag div container item
									// dragged
									addClick($trigger, $honrizontal);
								} else if(e == 'delete'){
									// event deleteClick($trigger, param1,
									// param2)
									// $trigger is element selected
									// param1: id table, param2: id tag div
									// container table
									deleteClick($trigger, $tabledetail + $h, $aboptable + $h);
								}
							} else if(_select == '1'){
								
								if(e == 'new'){
									newClick($trigger, $vertical);
								} else if(e == 'add'){
									// event addClick($trigger, param1)
									// $trigger is element selected
									// param: id of tag div container item
									// dragged
									addClick($trigger, $vertical);
								} else if(e == 'delete'){
									// event deleteClick($trigger, param1,
									// param2)
									// $trigger is element selected
									// param1: id table, param2: id tag div
									// container table
									deleteClick($trigger, $tabledetail + $v, $aboptable + $v);
								}
							}
							
                        },
                        items: items
                    };
                }
				
            });
            var items = {
            	"new": {
                        name: "Tạo mới",
                        icon: "new"
                    },
                "add": {
                    name: "Thêm",
                    icon: "add"
                },
				"sep1": "---------",
                "delete": {
                    name: "Xóa",
                    icon: "delete"
                }
                
            };
}
/* Init context menu */
function initContextMenuTemplate(selector){
            $.contextMenu({
                selector: selector,
                build: function ($trigger, e) {
                    console.log(e);
					
                    return {
                        callback: function (e, options) {
							var _select = $('#screensize').val();
							if(_select == '0'){
								if(e == 'new'){
									newClick($trigger, $honrizontal);
								}
							} else if(_select == '1'){
								if(e == 'new'){
									newClick($trigger, $vertical);
								} 
							}
							
                        },
                        items: items
                    };
                }
				
            });
            var items = {
            	"new": {
                        name: "Tạo mới",
                        icon: "new"
                    }                  
            };
}
/* init context menu for layout advanced */
function initContextMenuAdvanced(selector){
            $.contextMenu({
                selector: selector,
                build: function ($trigger, e) {
                    console.log(e);
					
                    return {
                        callback: function (e, options) {
							if(e == 'add'){
								addLayoutClick($trigger, $honrizontal);
							} else if(e == 'delete'){
								deleteLayoutClick($trigger, $tabledetail + $h, $aboptable + $h);
							} else if(e == 'copy'){
								copyLayoutClick($trigger);
							} else if(e == 'edit'){
								editLayoutClick();
							}
							
							
                        },
                        items: items
                    };
                }
				
            });
			var val = $('#content-select').val();
			var items = {};
			items = {
				  "add": {
					  name: "Thêm mới",
					  icon: "add"
				  },
				  "sep1": "---------",
				  "copy":{
					  name: "Sao chép",
					  icon: "copy"
				  },
				  "edit":{
					  name: "Đổi tên",
					  icon: "edit"
				  },
				  "sep2": "---------",
				  "delete": {
					  name: "Xóa",
					  icon: "delete"
				  }
			  };
}
/* Init context menu for group Player */
function initContextMenuGroup(){
            $.contextMenu({
                selector: '#groupbox',
                build: function ($trigger, e) {
                    console.log(e);
					
                    return {
                        callback: function (e, options) {
							var _select = $('#screensize').val();
							if(e == 'add'){				
								groupAddClick($trigger);
							} else if(e == 'delete'){
								groupDeleteClick($trigger);
							} else if(e == 'update'){
								groupUpdateClick($trigger);
							}
							
                        },
                        items: items
                    };
                }
				
            });
            var items = {
                "add": {
                    name: "Thêm",
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
function newClick($trigger, containment){
	// remove select layout
	$('#content-select-tmp').find('option').removeAttr('selected');
	$('#content-select').find('option').removeAttr('selected');
	$(containment).empty();
	var _select = $('#screensize').val();
	if(_select == '0'){
		$jsonLayoutH.method = 'save';
		// show messeage
		$('#table-honrizonal').empty();
		$('#table-honrizonal').append('<div id="msg" class="info_box">Thong tin chi tiet bo cuc chua khoi tao</div>');
	} else if(_select == '1'){
		$jsonLayoutV.method = 'save';
		// show messeage
		$('#table-vertical').empty();
		$('#table-vertical').append('<div id="msg" class="info_box">Thong tin chi tiet bo cuc chua khoi tao</div>');
	}
}
function addLayoutClick($trigger, containment){
	$('#content-select-tmp').find('option').removeAttr("selected");
//	var id = $('#content-select').val();
//	if(id == '' || id == null){
//		showMessageDialogWarning("Cảnh Báo", "Hãy chọn một bố cục trước khi thao tác lệnh");
//		return;
//	}
	newClick($trigger, containment);
}
function deleteLayoutClick($trigger, containment){
	var id = $('#content-select').val();
	if(id == '' || id == null){
		showMessageDialogWarning("Cảnh Báo", "Hãy chọn một bố cục trước khi thao tác lệnh");
		return;
	}
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không'};
	var $this = $('#content-select option[value="'+$trigger.val()+'"]');
	var left = $($this).position().left;
	var top = $($this).position().top;
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
			'left':left, 'top': top, 'jfunction':'ajaxDeleteLayout.elcom'};
	showConfirm($trigger, options);
	var id = $($this).val();
	$('.yes').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
		$.post(options.jfunction,{data: id}, function(){
			showGrowlMsg("Đã xóa thành công");
			layoutAfterDelete($this);
		});
	});
}
function editLayoutClick(){
	var id = $('#content-select').val();
	if(id == '' || id == null){
		showMessageDialogWarning("Cảnh Báo", "Hãy chọn một bố cục trước khi thao tác lệnh");
		return;
	}
	var direction = $('#screensize').val();
	if(direction == '0'){
		$jsonLayoutH.method = "edit";
		saveOnClick('.h-save');
	} else {
		$jsonLayoutV.method = "edit";
		saveVeticalOnClick('.v-save');
	}
}
function copyLayoutClick($trigger){
	var id = $('#content-select').val();
	if(id == '' || id == null){
		showMessageDialogWarning("Cảnh Báo", "Hãy chọn một bố cục trước khi 'Sao Chép'");
		return;
	}
	var direction = $('#screensize').val();
	if(direction == '0'){
		//update method when copy is save
		$jsonLayoutH.method = "copy";
		saveOnClick('.h-save');
	} else {
		//update method when copy is save
		$jsonLayoutV.method = "copy";
		saveOnClick('.v-save');
	}
	// update name when copy
	var name = $('#content-select option[value="'+$trigger.val()+'"]').attr('title') + '_copy';
	$('#namelayout').val(name);
	
	
}
/* Event Click on menu */
function addClick($trigger, containment){
	var _title = $trigger.attr('title');
	var _name = $trigger.attr('name');
	var _class = "." + _name;
	var _id = "#" + _name;
	var _select = $('#screensize').val();
	var name = _name + "_copy";
	console.log(_class);
	console.log(_id);
	// event add click
	$('.icon-add').on('click', function(){
		event.preventDefault();
		// alert( $( this ).text() );
		var tmp = $trigger.clone();// .css({
		  // left:_left,
		  // top: _top
		  // });
		  var offsetXPos =  getXPos( $trigger.offset().left );
		  var offsetYPos =  getYPos( $trigger.offset().top );
		  var width = getWidth($trigger.width());
		  var height = getHeight($trigger.height());
		// Important!!!!Requied remove '.ui-resizable-handle' before draggable
		// to can resize item
		tmp.find('.ui-resizable-handle').remove();
		tmp.draggable({
				helper: 'original',
				containment: containment,
				tolerance: 'fit',
				drag: handleDrag
		});
		tmp.resizable({
			containment: containment,
			autoHide: true,
			// helper: "ui-resizable-helper",
			resize: handleResize
			// animate: true,
			// animateDuration: "slow"
		});
		// remove class of parent
		tmp.removeClass(_name);
		// add attribute name into tag div purpos to determine only tag and
		// 'name' will id of row in table
		tmp.attr('name', name);
		// add class to remove when right click
		tmp.addClass(name);
		$(containment).append(tmp);
		// add row into table
		addRow('-1', name, _title, offsetXPos, offsetYPos, width, height);
	});
}
function deleteClick($trigger, table, containerTable){
	var _title = $trigger.attr('title');
	var _name = $trigger.attr('name');
	var _class = "." + _name;
	var _id = "#" + _name;
	var _select = $('#screensize').val();
	var name = _name + "_copy";
	$('.icon-delete').on('click', function(e){
	e.preventDefault();
	
	// remove on screen
	$(_class).remove();
	
	// remove in table
	if($(table + ' tr:last').index() == 1){	
		// delete table
		$(containerTable).remove();
		$(containerTable).trigger('remove').remove();
		
		if(_select == '0'){
			// push into array delete
			$jsonLayoutH.deletes.push($jsonLayoutH.items[0]);
			// set method save
			$jsonLayoutH.method = 'save';
			$jsonLayoutH.items.length = 0;
			// show messeage
			$('#table-honrizonal').empty();
			$('#table-honrizonal').append('<div id="msg" class="info_box">Thong tin chi tiet bo cuc chua khoi tao</div>');
			// set value default
			if(_name.indexOf('video') >= 0){
				$countVideoH = 0;
			} else if(_name.indexOf('image') >= 0){
				$countImageH = 0;
			}else if(_name.indexOf('ebrowser') >= 0){
				$countBrowserH = 0;
			}else if(_name.indexOf('text') >= 0){
				$countTextH = 0;
			}
		} else if(_select == '1'){
			// push into array delete
			$jsonLayoutV.deletes.push($jsonLayoutV.items[0]);
			// set method save
			$jsonLayoutV.method = 'save';
			$jsonLayoutV.items.length = 0;
			// show messeage
			$('#table-vertical').empty();
			$('#table-vertical').append('<div id="msg" class="info_box">Thong tin chi tiet bo cuc chua khoi tao</div>');
			// set value default
			if(_name.indexOf('video') >= 0){
				$countVideoV = 0;
			} else if(_name.indexOf('image') >= 0){
				$countImageV = 0;
			}else if(_name.indexOf('ebrowser') >= 0){
				$countBrowserV = 0;
			}else if(_name.indexOf('text') >= 0){
				$countTextV = 0;
			}
		}
		
	} else{
		$(_id).trigger('remove').remove();
		if(_select == '0'){
			var length = $jsonLayoutH.items.length;
			for(var i =0; i < length; i++){
				if(_name == $jsonLayoutH.items[i].name){
					// push into array delete
					$jsonLayoutH.deletes.push($jsonLayoutH.items[i]);
					$jsonLayoutH.items.splice(i, 1);
					break;
				}
			}
		} else if(_select == '0'){
			var length = $jsonLayoutV.items.length;
			for(var i =0; i < length; i++){
				if(_name == $jsonLayoutV.items[i].name){
					// push into array delete
					$jsonLayoutV.deletes.push($jsonLayoutV.items[i]);
					$jsonLayoutV.items.splice(i, 1);
					break;
				}
			}
		}
	}
	// clear text
	$('#poitionCurrent').empty();
  }); 
}

function groupAddClick($trigger){
	$.blockUI({ 
		message: $('.namegroup'),
		css: { 
			padding:0, 
			margin:0, 
			width:'65%', 
			top:'45%', 
			left:'45%', 
			textAlign:'center', 
			border: '3px solid transparent',
			background: 'transparent',
			cursor:'progress' 
		},
		cursor:'progress',
		onOverlayClick: $.unblockUI
	}); 
}
function saveGroupName(){
	var name = $('#namegroup').val();
	if(name != ""){
		$.post('ajaxSaveNameGroup.elcom',{data: name}, function(response){
			$('#groupbox').append('<option value="'+response.data+'">' + 
							$('#namegroup').val() +
					'</option>');
			showGrowlMsg('Tạo nhóm thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});
			
		});
	}
	return false;
}
function groupDeleteClick($trigger){
	if($trigger.val() != '' && $trigger.val() != null){
		var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không'};
		var $this = $('#groupbox option[value="'+$trigger.val()+'"]');
		var left = $($this).position().left;
		var top = $($this).position().top;
		var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
				'left':left, 'top': top, 'jfunction':'ajaxDeleteGroup.elcom'};
		showConfirm($trigger, options);
		var id = $($this).val();
		$('.yes').on('click', function(){
			$('.question').fadeOut(300, function() {
				$('.question').remove();
			});
			$.post(options.jfunction,{data: id}, function(){
				showGrowlMsg("Đã xóa thành công");
				$this.remove();
			});
		});
	} else{
		showMessageDialogWarning("Cảnh Báo", "Hãy chọn Nhóm cần xóa");
	}
	return false;
}
function showMessageDialogWarning(title, msg){
	$('.dialog-msg-content').empty();
	$('.dialog-msg-content').append(msg);
	$('.title-dialog').text(title);
	$.blockUI({
		message: $('.dialog-msg-warning'),
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
	setTimeout($.unblockUI, 2000);
}
function groupUpdateClick($trigger){
	if($trigger.val() != '' && $trigger.val() != null){
		$('.icon-update').on('click',function(){
			var nameGroup = $('#groupbox option:selected').attr('title');
			var idgroup = $('#groupbox').val();
			$('#name-group-input').val(nameGroup);
			// load DB before
			$.post('ajaxDetailGroup.elcom', {data:idgroup}, function(response){
				var periodic = response.schedulePeriodic;
				var dailyName= response.data;
				var playlistInDaily = response.playlist;
				var layoutItem = response.layoutItem;
				var layout = response.layout;
				var direction='0';
				var containment = '';
				// upate UI
				$('.layout-dialog-h').trigger('remove').remove();
				$('.layout-dialog-v').trigger('remove').remove();
				$('#option-playlist').find('option').trigger('remove').remove();
				// show data
				if(periodic.length > 0){
					$('#periodic-input').val(periodic[0].nameFull);
					$('#daily-input').val(dailyName);
					if(playlistInDaily.length > 0){
						var _length = playlistInDaily.length;
						var item = '';
						for(var i =0; i < _length; i++){
							item = "<option value='"+playlistInDaily[i].id+"'>"+playlistInDaily[i].name+"</option>";
							$('#option-playlist').append(item);
						}
						
						_length = layout.length;
						//detect direction layout
						for(var i =0; i < _length; i++){
							if(layoutItem[0].idParent == layout[i].id){
								direction = layout[i].direction;
							}
						}
						
						if(direction == '0'){
							containment = '.layout-view-dialog-h';
							$('.info-detail-right-dialog').append("<div class='layout-dialog-h'><div class='layout-view-dialog-h'></div></div>");
						} else if(direction == '1'){
							containment = '.layout-view-dialog-v';
							$('.info-detail-right-dialog').append("<div class='layout-dialog-v'><div class='layout-view-dialog-v'></div></div>");
						}
						// draw layout on TV
						_length = layoutItem.length;
						for(var i =0; i < _length; i++){
							// draw layout on screen
							var name = layoutItem[i].name;
							var type = layoutItem[i].type;
							var _left = layoutItem[i].left;
							var _top =  parseInt(layoutItem[i].top);
							var _h = layoutItem[i].height;
							if(i == 0){
								_top =  parseInt(layoutItem[i].top) + 66;
								var _h = parseInt(layoutItem[i].height) - 66;
							}
							
							var _w = layoutItem[i].width;
							
							drawLayoutItemDialog(containment, name, direction, type, _left, _top, _w, _h);
						}
					} else {
						$('#option-playlist').append('<option value="-1">Chưa được khởi tạo</option>');
					}
					
				} else{
					$('#periodic-input').val("Nhóm chưa có lịch định kì");
					$('#option-playlist').append('<option value="-1">Chưa được khởi tạo</option>');
					$('.info-detail-right-dialog').append("<div class='layout-dialog-h'><div class='layout-view-dialog-h'></div></div>");
				}
				
			});
			// show dialog
			$.blockUI({ 
				message: $('.dialog-update-group'),
				css: { 
					padding:0, 
					margin:0, 
					width:'54%', 
					top:'20%', 
					left:'23%', 
					textAlign:'center', 
					border:'3px solid #aaa', 
					backgroundColor:'#fff', '-webkit-border-radius': '10px',
				     '-moz-border-radius': '10px',
					
					cursor:'progress' 
				},
				cursor:'progress',
				onOverlayClick: $.unblockUI
			});
			
//			$.post('ajaxLoadPeriodic.elcom', {data: idgroup}, function(response){	 
//			});	
		});
	} else {
		showMessageDialogWarning("Cảnh Báo", "Hãy chọn Nhóm cần cập nhật");
	}
}
function loginSubmit(){
	var username = $('#username').val();
	var password = $('#password').val();
	var json = {'username': username, 'password': password};
	var strJS = JSON.stringify(json);
	$.post('ajaxLogin.elcom',{data: strJS}, function(response){
		alert(response.user.role);
		// check role
//		var role = $('#permission').val();
//		if(typeof role != 'undefined'){
//			if(!role.contains('ROLE_CONTENT')){
//				$('#menu-content').hide();
//			}
//			if(!role.contains('ROLE_ADMIN')){
//				$('#menu-user').hide();
//			}
//			if(!role.contains('ROLE_CONFIG')){
//				$('#menu-system').hide();
//			}
//			if(!role.contains('ROLE_SETTOPBOX')){
//				//$('#menu-layout').hide();
//			}
//			if(!role.contains('ROLE_LOG')){
//				//$('#').hide();
//			}
//			if(!role.contains('ROLE_REPORT')){
//				//$('#').hide();
//			}
//		}
	});
	
	return true;
}
function updateGroup_Click(){
	var name = $('#name-group-input').val();
	var groupId = $('#groupbox').val();
	var json = {'name': name, 'id': groupId};
	var obj = JSON.stringify(json);
	if(name != "" && groupId != ""){
		$.post('ajaxChangeNameGroup.elcom', {data: obj}, function(response){
			name = $('#name-group-input').val();
			showGrowlMsg('Cập nhật thành công');
			$(document).ajaxStop(function(){
				$.unblockUI;
			});	
			$('#groupbox option:selected').text(name);
		});
	} 
}
function drawLayoutItemDialog(containment, name, direction, type, _left, _top, _w, _h){
	var item;
	if(direction == '0'){
		if(type == 'Video'){
			item = $('<div title="Video" class="drag-video-clone text"></div>');
			//$(item).append('<span>'+name+'</span>');
		} else if(type == 'Image'){
			item = $("<div title='Image' class='drag-image-clone text'></div>");
			//$(item).append('<span>'+name+'</span>');
		} else if(type == 'Browser'){
			item = $("<div title='Browser' class='drag-browser-clone text'></div>");
			//$(item).append('<span>'+name+'</span>');
		} else if(type == 'Text'){
			item = $("<div title='Text' class='drag-text-clone text'></div>");
			//$(item).append('<span>'+name+'</span>');
		}
	} else if(direction == '1'){
		if(type == 'Video'){
			item = $('<div title="Video" class="clone text"></div>');
			//$(item).append('<span>'+name+'</span>');
		} else if(type == 'Image'){
			item = $("<div title='Image' class='clone text'></div>");
			//$(item).append('<span>'+name+'</span>');
		} else if(type == 'Browser'){
			item = $("<div title='Browser' class='clone text'></div>");
			//$(item).append('<span>'+name+'</span>');
		} else if(type == 'Text'){
			item = $("<div title='Text' class='clone text'></div>");
			//$(item).append('<span>'+name+'</span>');
		}
	}
	$(item).css({
		'left': setLeftPosDialog(_left, direction),
		'top': setTopPosDialog(_top, direction),
		'width': setSubWidthDialog(_w, direction),
		'height': setSubHeightDialog(_h, direction),
		'position': 'absolute'
	});
	$(containment).append(item);
}

function setLeftPosDialog(xpos, status){
	var xdefault = 0;
	var result = 0;
	if(status == '0'){
		result = (xpos)/$rateLeftHorizontalDialog;
		console.log("xpos:" + xpos);
		
	} else if(status == '1'){
		result = (xpos)/$rateLeftVerticalDialog;
	}
	
	return (Math.round(result));
}
function setTopPosDialog(ypos, status){
	var ydefault = 0;
	var result = 0;
	if(status == '0'){
		result = (ypos)/$rateTopHorizontalDialog;
	} else if(status == '1'){
		result = (ypos)/$rateTopVerticalDialog;
	}
	return (Math.round(result));
}
function setSubWidthDialog(width, status){
	if(status == '0'){
		return (Math.round(width/$rateLeftHorizontalDialog));
	} else if(status == '1'){
		console.log("X:" +width);
		return (Math.round(width/$rateLeftVerticalDialog));
	}
}
function setSubHeightDialog(height, status){
	if(status == '0'){
		return (Math.round(height/$rateTopHorizontalDialog));
	} else if(status == '1'){
		console.log("Y:" + height);
		return (Math.round(height/$rateTopVerticalDialog));
	}
}
function optionPlaylistDialogChange(){
	var playlistId = $('#option-playlist').val();
	if(val != '-1'){
		$.post('ajaxPlaylistDialog.elcom', {data: playlistId}, function(response){
			// upate UI
			$('.layout-dialog-h').trigger('remove').remove();
			$('.layout-dialog-v').trigger('remove').remove();
			
			var layoutItem = response.layoutItem;
			var layout = response.layout;
			var _length = layout.length;
			//detect direction layout
			for(var i =0; i < _length; i++){
				if(layoutItem[0].idParent == layout[i].id){
					direction = layout[i].direction;
				}
			}
			
			if(direction == '0'){
				containment = '.layout-view-dialog-h';
				$('.info-detail-right-dialog').append("<div class='layout-dialog-h'><div class='layout-view-dialog-h'></div></div>");
			} else if(direction == '1'){
				containment = '.layout-view-dialog-v';
				$('.info-detail-right-dialog').append("<div class='layout-dialog-v'><div class='layout-view-dialog-v'></div></div>");
			}
			// draw layout on TV
			_length = layoutItem.length;
			for(var i =0; i < _length; i++){
				// draw layout on screen
				var name = layoutItem[i].name;
				var type = layoutItem[i].type;
				var _left = layoutItem[i].left;
				var _top =  layoutItem[i].top + 96;
				var _w = layoutItem[i].width;
				var _h = layoutItem[i].height;
				drawLayoutItem(containment, name, direction, type, _left, _top, _w, _h);
			}
		});
	}
}
function onSelect(){
	$( this ).css({
      "background-color": "yellow !important",
      "font-weight": "bolder"
    });
}
/**
 * Method run when select change combobox:honrizonal, vertical
 */
function sizechange(){
	var tvSize = $('#tv-size').val();
	$('#tv-size').val(tvSize).trigger('change');
	var val = $('#screensize').val();
	$('#msg').fadeOut();
	if(val == '0'){
		// show layout-honrizontal
		$('#layout-honrizontal').fadeIn(1000);
		// hide layout-vertical;
		$('#layout-vertical').hide();
		$('#layout').addClass('content-honrizonal');
		$('#layout').removeClass('content-vertical');
		// hide table
		$('#table-vertical').fadeOut();
		$('#table-honrizonal').fadeIn();
		if($($tabledetail + $h + ' tr:last').index() > 0){
			// show table
			$($aboptable + $h).fadeIn();
		} else {
			$('#poitionCurrent').empty();
			$('#msg').fadeIn();
		}
		
	} else if(val == '1'){
		var options = { percent: 0 };
		$('#layout').addClass('content-vertical');
		$('#layout').removeClass('content-honrizonal');
		// hide layout-honrizontal
		$('#layout-honrizontal').hide();// dont using fadeOut
		// show layout-vertical;
		$('#layout-vertical').fadeIn(1000);
		// hide table
		$('#table-vertical').fadeIn();
		$('#table-honrizonal').fadeOut();
		if($($tabledetail + $v + ' tr:last').index() > 0){
			// show table
			$($aboptable + $v).fadeIn();
		} else {
			$('#msg').fadeIn();
		}
	}
}
function drawMsgInfo(msg){
	var str = "<div id='msg' class='info_box'>" + msg +"</div>";
	return str;
}
function drawTable(id, col1, col2, col3, col4, col5, col6){
	var _select = $('#screensize').val();
	var table = '';
	var tag = '';
	if(col3 < 0){
		col3 = 0;
	}
	if(col4 < 0){
		col4 = 0;
	}
	if(col5 < 0){
		col5 = 0;
	}
	if(col6 < 0){
		col6 = 0;
	}
	var obj = {};
	obj['id'] = id;
	obj['type'] = col2;
	obj['left'] = col3.toString();
	obj['top'] = col4.toString();
	obj['width'] = col5.toString();
	obj['height'] = col6.toString();
	if(_select == '0') {
		// check name same
		obj['name'] = checkSameName(col1, $jsonLayoutH.items);
		// add into json
		$jsonLayoutH.items.length = 0;
		$jsonLayoutH.items.push(obj);
		// hide messeage
		$('#table-honrizonal').find('#msg').hide();
		id = 'abop-table' + $h;
		table =  'table-detail' + $h;
		tag = '#table-honrizonal';
	} else if(_select == '1'){
		// check name same
		obj['name'] = checkSameName(col1, $jsonLayoutH.items);
		// add into json
		$jsonLayoutV.items.length = 0;
		$jsonLayoutV.items.push(obj);
		// hide messeage
		$('#table-vertical').find('#msg').hide();
		id = 'abop-table' + $v;
		table =  'table-detail' + $v;
		tag = '#table-vertical';
	}
	col1 = obj['name'];
	var x= col1 + "x";
	var y= col1 + "y";
	var w= col1 + "w";
	var h= col1 + "h";
	var div = "<div id='" + id + "' class='abop-table'>" + 
				"<table id='" + table + "'>" +
                     "<tr>"+
					 	"<td>Tên</td>"+
						"<td>Loại</td>"+
						"<td>X </td>"+
						"<td>Y</td>"+
						"<td>Rộng</td>"+
						"<td>Cao</td>"+
					 "</tr>" +
					 "<tr id='" + col1.replace(/\s/g,'') + "'>"+
					  "<td>"+ "<input class='input-name' type='text' value='" + col1 + "'/>" +"</td>"+
					  "<td style='text-align:center'>"+ col2 +"</td>"+
					  "<td style='text-align:center' id='"+ x + "'><input data-type='left' class='input-num' min='0' max='3840' type='number' value='" + col3 + "'/></td>"+
					  "<td style='text-align:center' id='"+ y + "'><input data-type='top' class='input-num' min='0' max='3840' type='number' value='" + col4 + "'/></td>"+
					  "<td style='text-align:center' id='"+ w + "'><input data-type='width' class='input-num' min='0' max='3840' type='number' value='" + col5 + "'/></td>"+
					  "<td style='text-align:center' id='"+ h + "'><input data-type='height' class='input-num' min='0' max='3840' type='number' value='" + col6 + "'/></td>"+
				   "</tr>" +
				 "</table>"+ 
			  "</div>";
	$(tag).append(div);
	$('input[type="number"]').numeric();
	$('input[type="number"]').on('blur', updateItemLayout);
}
function addRow(id, col1, col2, col3, col4, col5, col6){
	var _select = $('#screensize').val();
	// check same name to change other name
	
	var obj = {};
	obj['id'] = id;
	obj['type'] = col2;
	obj['left'] = col3.toString();
	obj['top'] = col4.toString();
	obj['width'] = col5.toString();
	obj['height'] = col6.toString();
	
	if(_select == '0') {
		obj['name'] = checkSameName(col1, $jsonLayoutH.items);
		id = $tabledetail + $h;
		$jsonLayoutH.items.push(obj);
	} else if(_select == '1'){
		obj['name'] = checkSameName(col1, $jsonLayoutV.items);
		id = $tabledetail + $v;
		$jsonLayoutV.items.push(obj);
	}
	// update name.
	col1 = obj['name'];
	
	var x= col1 + "x";
	var y= col1 + "y";
	var w= col1 + "w";
	var h= col1 + "h";
	var tr= "<tr class='"+id+"' id='" + col1.replace(/\s/g,'') + "'>"+
			  "<td>"+ "<input class='input-name' type='text' value='" + col1 + "'/>" +"</td>"+
			  "<td style='text-align:center'>"+ col2 +"</td>"+
			  "<td style='text-align:center' id='"+ x + "'><input data-type='left' class='input-num' min='0' max='3840' type='number' value='" + col3 + "'/></td>"+
			  "<td style='text-align:center' id='"+ y + "'><input data-type='top' class='input-num' min='0' max='3840' type='number' value='" + col4 + "'/></td>"+
			  "<td style='text-align:center' id='"+ w + "'><input data-type='width' class='input-num' min='0' max='3840' type='number' value='" + col5 + "'/></td>"+
			  "<td style='text-align:center' id='"+ h + "'><input data-type='height' class='input-num' min='0' max='3840' type='number' value='" + col6 + "'/></td>"+
		   "</tr>" ;
	$(id).append(tr);
	$('input[type="number"]').numeric();
	$('input[type="number"]').on('blur', updateItemLayout);
}
function updateItemLayout(e){
	var data_type = $(this).attr('data-type');
	var id = $(this).parent().parent().attr('id');
	var value = parseInt($(this).val());
	
	if('top' == data_type){
		var _top = $('.' + id).position().top;
		$('.' + id).css('top', getMaskYPos(value) + "px");
	} else if('left' == data_type){
		var _left = $('.' + id).position().left;
		$('.' + id).css('left', getMaskXPos(value) + "px");
	} else if('width' == data_type){
		$('.' + id).css('width', getMaskWidth(value) + "px");
	} else if('height' == data_type){
		$('.' + id).css('height', getMaskHeight(value) + "px");
	}
}
function checkSameName(name, arrJson){
	//remove space name
	name = name.replace(/\s/g,'');
	var length = arrJson.length;
	if(length > 0){
		for(var i =0; i < length; i++){
			if(arrJson[i].name == name){
				return (name + '_copy');
			}
		}
	} else {
		return name;
	}
	return name;
}
/* Tab */
$(function() {
    jQuery('.tabs .tab-links a').on('click', function(e)  {
        var currentAttrValue = jQuery(this).attr('href');
 
        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();
 
        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
 
        e.preventDefault();
    });
});

function btnLeftRightClick(){
	var playerId= "";
	var count = 0;
	$("#selectleft option:selected").each(function() {
			$(this).appendTo('#selectright');
			playerId = playerId + $(this).val() + ";";
        });
	if(playerId.length > 1){
		playerId = playerId.substring(0, (playerId.length - 1));
		ajaxRemoveList($groupId, playerId);
	}
	return false;
}
function btnRightLeftClick(){
	var playerId= "";
	$("#selectright option:selected").each(function() {
			$(this).appendTo('#selectleft');
			playerId = playerId + $(this).val() + ";";
			
        });
	if(playerId.length > 1){
		playerId = playerId.substring(0, (playerId.length - 1));
		ajaxAddList($groupId, playerId);
	}
	return false;
}
function ajaxAddList(groupId, playerId){
	var json = {groupid: groupId, playerid:playerId};
	var obj = JSON.stringify(json);
	$.post(
			'ajaxAddToList.elcom',
			{
				data: obj
			},
			function(response){
				createAddList(response);
			}
	).error(function(){
		console.log("Server turn off");
	});
	return false;
}
function ajaxRemoveList(groupId, playerId){
	var json = {groupid: groupId, playerid:playerId};
	var obj = JSON.stringify(json);
	$.post('ajaxRemoveFromList.elcom',{data: obj},function(response){
			createAddList(response);
	}
			
	).error(function(){
		console.log("Server turn off");
	});
	return false;
}
function Sort(elementId) {
    // Convert the listbox options to a javascript array and sort (ascending)
    var sortedList = $.makeArray($("#" + elementId + " option"))
        .sort(function(a, b) {
            return $(a).text() < $(b).text() ? -1 : 1;
        });
    // Clear the options and add the sorted ones
    $("#" + elementId).empty().html(sortedList);
}
/* End tab */
function getXPos(xpos){
	var orientation = $('#screensize').val();
	var xdefault = 0;
	var result = 0;
	if(orientation == '0'){
		xdefault = $('#honrizontal').offset().left;
		result = (xpos - xdefault)*$rateXHorizontal;
		console.log("xpos:" + xpos + " xdefault:" + xdefault);
	} else if(orientation == '1'){
		xdefault = $('#vertical').offset().left;
		result = (xpos - xdefault)*$rateXVertical;
		console.log("xpos:" + xpos + " xdefault:" + xdefault);
	}
	if(result < 0){
		return 0;
	}
	return (Math.round(result));
}
function getMaskXPos(xpos){
	var orientation = $('#screensize').val();
	var xdefault = 0;
	var result = 0;
	if(orientation == '0'){
		xdefault = $('#honrizontal').offset().left;
		result = xpos/$rateXHorizontal + xdefault;
		console.log("xpos:" + xpos + " xdefault:" + xdefault);
	} else if(orientation == '1'){
		xdefault = $('#vertical').offset().left;
		result = (xpos)/$rateXVertical + xdefault;
		console.log("xpos:" + xpos + " xdefault:" + xdefault);
	}
	if(result < 0){
		return 0;
	}
	return (Math.round(result));
}
function setXPos(xpos, status){
	var xdefault = 0;
	var result = 0;
	if(status == '0'){
		xdefault = $('#honrizontal').offset().left;
		result = (xpos)/$rateXHorizontal + xdefault;
		console.log("xpos:" + xpos + " xdefault:" + xdefault);
		
	} else if(status == '1'){
		xdefault = $('#vertical').offset().left;
		result = (xpos)/$rateXVertical + xdefault;
		console.log("xpos:" + xpos + " xdefault:" + xdefault);
	}
	if(xpos == 0){
		return xdefault;
	}
	return (Math.round(result));
}
function getYPos(ypos){
	var orientation = $('#screensize').val();
	var ydefault = 0;
	var result = 0;
	if(orientation == '0'){
		ydefault = $('#honrizontal').offset().top;
		result = (ypos - ydefault)*$rateYHorizontal;
	} else if(orientation == '1'){
		ydefault = $('#vertical').offset().top;
		result = (ypos - ydefault)*$rateYVertical;
	}
	if(result < 0){
		return 0;
	}
	return (Math.round(result));
}
function getMaskYPos(ypos){
	var orientation = $('#screensize').val();
	var ydefault = 0;
	var result = 0;
	if(orientation == '0'){
		ydefault = $('#honrizontal').offset().top;
		result = (ypos)/$rateYHorizontal + ydefault;
	} else if(orientation == '1'){
		ydefault = $('#vertical').offset().top;
		result = (ypos)/$rateYVertical + ydefault;
	}
	if(result < 0){
		return 0;
	}
	return (Math.round(result));
}
function setYPos(ypos, status){
	var orientation = $('#screensize').val();
	var ydefault = 0;
	var result = 0;
	if(status == '0'){
		ydefault = $('#honrizontal').offset().top;
		result = (ypos)/$rateYHorizontal + ydefault;
	} else if(status == '1'){
		ydefault = $('#vertical').offset().top;
		result = (ypos)/$rateYVertical + ydefault;
	}
	if(result == 0){
		return ydefault;
	}
	return (Math.round(result));
}
function getWidth(width){
	var orientation = $('#screensize').val();
	if(orientation == '0'){
		return (Math.round(width*$rateXHorizontal));
	} else if(orientation == '1'){
		console.log("X:" +width);
		return (Math.round(width*$rateXVertical));
	}
}
function getMaskWidth(width){
	var orientation = $('#screensize').val();
	if(orientation == '0'){
		return (Math.round(width/$rateXHorizontal));
	} else if(orientation == '1'){
		console.log("X:" +width);
		return (Math.round(width/$rateXVertical));
	}
}
function setWidth(width, status){
	if(status == '0'){
		return (Math.round(width/$rateXHorizontal));
	} else if(status == '1'){
		console.log("X:" +width);
		return (Math.round(width/$rateXVertical));
	}
}
function getHeight(height){
	var orientation = $('#screensize').val();
	if(orientation == '0'){
		return (Math.round(height*$rateYHorizontal));
	} else if(orientation == '1'){
		console.log("Y:" + height);
		return (Math.round(height*$rateYVertical));
	}
}
function getMaskHeight(height){
	var orientation = $('#screensize').val();
	if(orientation == '0'){
		return (Math.round(height/$rateYHorizontal));
	} else if(orientation == '1'){
		console.log("Y:" + height);
		return (Math.round(height/$rateYVertical));
	}
}
function setHeight(height, status){
	if(status == '0'){
		return (Math.round(height/$rateYHorizontal));
	} else if(status == '1'){
		console.log("Y:" + height);
		return (Math.round(height/$rateYVertical));
	}
}
//options using for muilti language
function showConfirm($this, options){
	if($($this).next('.question').length <= 0){
		$($this).after('<div class="question">'+options.question+'<br/> <span class="yes">'+options.yes+
				'</span><span class="cancel">'+options.no+'</span></div>');
		$('.question').css({
			'left': options.left + 150,
			'top':  options.top
		});
	}
	
	$($this).next('.question').animate({opacity: 1}, 300);
	

	$('.cancel').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
	});
	setTimeout(function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
	}, 10000);
}
function layoutAfterDelete($this){
	// update UI layout
	$('option[value="'+$this.val()+'"]').trigger('remove').remove();
	var direction = $('#screensize').val();
	if(direction == '0'){
		$('#honrizontal').empty();
		$('#table-honrizonal').empty();
		$('#table-honrizonal').append('<div id="msg" class="info_box">Thong tin chi tiet bo cuc chua khoi tao</div>');
	} else{
		$('#vertical').empty();
		$('#table-vertical').empty();
		$('#table-vertical').append('<div id="msg" class="info_box">Thong tin chi tiet bo cuc chua khoi tao</div>');
	}
}
/* confirm messeage */
(function($){
	jQuery.fn.jConfirmAction = function (options) {
		
		// Some jConfirmAction options (limited to customize language) :
		// question : a text for your question.
		// yesAnswer : a text for Yes answer.
		// cancelAnswer : a text for Cancel/No answer.
		var theOptions = jQuery.extend ({
			question: "Bạn có muốn xóa?",
			yesAnswer: "Có",
			cancelAnswer: "Không"
		}, options);
		
		return $(this).bind('click', function(e) {

				e.preventDefault();
				thisHref	= $(this).attr('href');
				
				if($(this).next('.question').length <= 0)
					$(this).after('<div class="question">'+theOptions.question+'<br/> <span class="yes">'+theOptions.yesAnswer+'		</span><span class="cancel">'+theOptions.cancelAnswer+'</span></div>');
				
				$(this).next('.question').animate({opacity: 1}, 300);
				
				$('.yes').bind('click', function(){
					window.location = thisHref;
				});
		
				$('.cancel').bind('click', function(){
					$(this).parents('.question').fadeOut(300, function() {
						$(this).remove();
					});
				});
				setTimeout(function(){
					$(this).parents('.question').fadeOut(300, function() {
						$(this).remove();
					});
				}, 2000);
				
			});
	}
	
})(jQuery);
/* Start not run */
function runEffectShow(val) {
      // get effect type from
      var selectedEffect = "scale";
       // most effect types need no options passed by default
      var options = { percent: 0 };
	  if(val =='0'){
		  $( "#layout-vertical" ).hide( selectedEffect, options, 1000, callbackshow_honrizonal );
	  } else if(val== '1'){
		  $( "#layout-honrizonal" ).hide( selectedEffect, options, 1000, callbackshow_vertical );
	  }
      
}
function callbackshow_honrizonal() {
      setTimeout(function() {
        $( "#layout-honrizonal" ).removeAttr( "style" ).hide().fadeIn();
      }, 1000 );
}
function callbackshow_vertical() {
      setTimeout(function() {
        $( "#layout-vertical" ).removeAttr( "style" ).hide().fadeIn();
      }, 1000 );
}
function listLayoutOnChange(){

	// remove selected on layout template
	$('#content-select-tmp').find('option').removeAttr("selected");
	var item = '<div class="loading" style="text-align:center;"><img style="width:30px; height:30px; margin-top:25%" src="css/images/loading.gif"></div>';
	var typeLayout = $('#content-select option:selected').attr('type');
	var id = $('#content-select').val();
	var status = $('#content-select option:selected').attr('direction');
	var sizeScreen = $('#content-select option:selected').attr('size-screen');
	$('#tv-size').val(sizeScreen).trigger('change');
	$('#screensize').val(status).trigger('change');
	if(status == '0'){
		$jsonLayoutH.method = 'update';
		$jsonLayoutH.id = id;
		$jsonLayoutH.items.length = 0;
		$jsonLayoutH.deletes.length = 0;
		$('#honrizontal').empty();
		$('#table-honrizonal').empty();
		$('#honrizontal').append(item);
	} else if(status == '1'){
		$jsonLayoutV.method = 'update';
		$jsonLayoutV.id = id;
		$jsonLayoutV.items.length = 0;
		$jsonLayoutV.deletes.length = 0;
		$('#vertical').empty();
		$('#table-vertical').empty();
		$('#vertical').append(item);
	}
	$.post('ajaxlayout.elcom', {data: id}, function(response) {
				$('.loading').trigger('remove').remove();
				var length = response.layoutItem.length;
				for(var i = 0; i < length; i++){
					var id = response.layoutItem[i].id;
					var name = response.layoutItem[i].nameFull;
					var type = response.layoutItem[i].type;
					var _left = response.layoutItem[i].left;
					var _top =  response.layoutItem[i].top;
					var _w = response.layoutItem[i].width;
					var _h = response.layoutItem[i].height;
					drawLayout(id, typeLayout, name, status, type, _left, _top, _w, _h);
				}
				$('input').prop('readonly', false);
			}
	)
	.error(function() { 
		console.log("Server turn off"); 
	});
}
function listLayoutTmpOnChange(){
	// remove selected on layout avanced
	$('#content-select').find('option').removeAttr("selected");
	var item = '<div class="loading" style="text-align:center;"><img style="width:30px; height:30px; margin-top: 25%" src="css/images/loading.gif"></div>';
	var typeLayout = $('#content-select-tmp option:selected').attr('type');
	var id = $('#content-select-tmp').val();
	var status = $('#content-select-tmp option:selected').attr('direction');
	var sizeScreen = $('#content-select-tmp option:selected').attr('size-screen');
	$('#tv-size').val(sizeScreen).trigger('change');
	$('#screensize').val(status).trigger('change');
	if(status == '0'){
		$jsonLayoutH.method = 'save';
		$jsonLayoutH.id = '-1';
		$jsonLayoutH.items.length = 0;
		$('#honrizontal').empty();
		$('#table-honrizonal').empty();
		$('#honrizontal').append(item);
	} else if(status == '1'){
		$jsonLayoutV.method = 'save';
		$jsonLayoutV.id = '-1';
		$jsonLayoutV.items.length = 0;
		$('#vertical').empty();
		$('#table-vertical').empty();
		$('#vertical').append(item);
	}
	$.post('ajaxlayout.elcom', {data: id}, function(response) {
				$('.loading').trigger('remove').remove();
				var length = response.layoutItem.length;
				for(var i = 0; i < length; i++){
					var name = response.layoutItem[i].nameFull;
					var type = response.layoutItem[i].type;
					var _left = response.layoutItem[i].left;
					var _top =  response.layoutItem[i].top;
					var _w = response.layoutItem[i].width;
					var _h = response.layoutItem[i].height;
					drawLayout('-1', typeLayout, name, status, type, _left, _top, _w, _h);
				}
				$('input').prop('readonly', true);
			}
	)
	.error(function() { 
		console.log("Server turn off"); 
	});
}
function drawLayout(id, typeLayout, name, status, type, x, y, w, h){
	var item;
	var containment = '#honrizontal';
	var aboptable = $aboptable + $h;
	if(typeLayout == '0'){// layout advance
		if(type == 'Video'){
			item = $('<div data-id="'+id+'" title="Video" class="drag-video-clone text"></div>');
			//$(item).append('<span>Video</span>');
		} else if(type == 'Image'){
			item = $("<div data-id='"+id+"' title='Image' class='drag-image-clone text'></div>");
			//$(item).append('<span>Image</span>');
		} else if(type == 'Browser'){
			item = $("<div data-id='"+id+"' title='Browser' class='drag-browser-clone text'></div>");
			//$(item).append('<span>Browser</span>');
		} else if(type == 'Text'){
			item = $("<div data-id='"+id+"' title='Text' class='drag-text-clone text'></div>");
			//$(item).append('<span>Text</span>');
		}
	} else if(typeLayout == '1'){// layout template
		if(type == 'Video'){
			item = $('<div data-id="'+id+'" title="Video" class="clone-video text"></div>');
			//$(item).append('<span>Video</span>');
		} else if(type == 'Image'){
			item = $("<div data-id='"+id+"' title='Image' class='clone-image text'></div>");
			//$(item).append('<span>Image</span>');
		} else if(type == 'Browser'){
			item = $("<div data-id='"+id+"' title='Browser' class='clone-browser text'></div>");
			//$(item).append('<span>Browser</span>');
		} else if(type == 'Text'){
			item = $("<div data-id='"+id+"' title='Text' class='clone-text text'></div>");
			//$(item).append('<span>Text</span>');
		}
	}
	
	$(item).css({
		'left': setXPos(x, status),
		'top': setYPos(y, status),
		'width': setWidth(w, status),
		'height': setHeight(h, status),
		'position': 'absolute'
	});
	if(status == '0'){
		containment = ('#honrizontal');
		aboptable = $aboptable + $h;
	} else if(status == '1'){
		containment = ('#vertical');
		aboptable = $aboptable + $v;
	}
	// replace space in name
	var _name = name.replace(/\s/g,'');
	// add attribute name into tag div purpose to determine only tag and
	// 'name' will id of row in table
	$(item).attr('name', _name);
	// add class to remove when right click
	$(item).addClass(_name);
	// draw table. If exist table then add row into table else draw table.
	if($(aboptable).length >= 1){
		addRow(id, name, type, x, y, w, h);
	} else {
		drawTable(id, name, type, x, y, w, h);
	}
	if(typeLayout != '1'){
		$(item).draggable({
			helper: 'original',
			containment: containment,
			tolerance: 'fit',
			drag: handleDrag
		});
		$(item).resizable({
			containment: containment,
			autoHide: true,
			resize: handleResize
		});
	}
	
	$('#msg').fadeOut();
	if(status == '0'){
		$(containment).append(item);
	} else if(status == '1'){
		$(containment).append(item);
	}
}
function saveOnClick(idElement){
	var div = '';
	var idlayout = $('#content-select option:selected').val();
	if(typeof idlayout != 'undefined'){
		div='<div class="namelayout">'+
		'<span>Đặt Tên Bố Cục</span>' + 
		'<input id="namelayout" value="'+$('#content-select option:selected').attr('title')+'" style="width:90%;height:20px; margin:5px;" type="text" class="input-name"/>'  +
		'<div style="float:left; margin:5px;"><a  href="#" class="button-dialog" style="width:30px;" onclick="return saveCreateLayout()">Lưu</a></div>' +
		'<div style="float:left; margin:5px;"><a href="#" class="button-dialog" style="width:30px;" onclick="return closeDialog()">Đóng</a></div>' +
	'</div>';
	} else {
		div = '<div class="namelayout">'+
		'<span>Đặt Tên Bố Cục</span>' + 
		'<input id="namelayout" style="width:90%;height:20px; margin:5px;" type="text" class="input-name"/>'  +
		'<div style="float:left; margin:5px;"><a  href="#" class="button-dialog" style="width:30px;" onclick="return saveCreateLayout()">Lưu</a></div>' +
		'<div style="float:left; margin:5px;"><a href="#" class="button-dialog" style="width:30px;" onclick="return closeDialog()">Đóng</a></div>' +
	'</div>';
	}
	var _left = $(idElement).position().left;
	var _top = $(idElement).position().top;
	var _w = $(idElement).width();
	
	if($(idElement).next('.namelayout').length <= 0){
		$(idElement).after($(div).css({
				'left': _left + _w*70/100,
				'top': _top + 50,
				'opacity': 1
			}));
	}
	$(idElement).next('.namelayout').animate({opacity: 1}, 300);
	return false;
}

function saveVeticalOnClick(idElement){
	var div = '';
	var idlayout = $('#content-select option:selected').val();
	if(typeof idlayout != 'undefined'){
		div='<div class="namelayout">'+
		'<span>Đặt Tên Bố Cục</span>' + 
		'<input id="namelayout" value="'+$('#content-select option:selected').attr('title')+'" style="width:90%;height:20px; margin:5px;" type="text" class="input-name"/>'  +
		'<div style="float:left; margin:5px;"><a  href="#" class="button-dialog" style="width:30px;" onclick="return saveCreateLayout()">Lưu</a></div>' +
		'<div style="float:left; margin:5px;"><a href="#" class="button-dialog" style="width:30px;" onclick="return closeDialog()">Đóng</a></div>' +
	'</div>';
	} else {
		div = '<div class="namelayout">'+
		'<span>Đặt Tên Bố Cục</span>' + 
		'<input id="namelayout" style="width:90%;height:20px; margin:5px;" type="text" class="input-name"/>'  +
		'<div style="float:left; margin:5px;"><a  href="#" class="button-dialog" style="width:30px;" onclick="return saveCreateLayout()">Lưu</a></div>' +
		'<div style="float:left; margin:5px;"><a href="#" class="button-dialog" style="width:30px;" onclick="return closeDialog()">Đóng</a></div>' +
	'</div>';
	}
	var _left = $(idElement).position().left;
	var _top = $(idElement).position().top;
	var _w = $(idElement).width();
	
	if($(idElement).next('.namelayout').length <= 0){
		$(idElement).after($(div).animate({
				'left': _left + _w*45/100,
				'top': _top + 60,
				'opacity': 1
			}, 1000));
	}
	$(idElement).next('.namelayout').animate({opacity: 1}, 300);
	return false;
}
function saveCreateLayout(){
	var direction = $('#screensize').val();
	var sizeScreen = $('#tv-size').val();
	var namelayout = $('#namelayout').val();
	if(namelayout == '' || namelayout == null){
		showMessageDialogError('Lỗi', 'Tên bố cục là bắt buộc');
		return false;
	}
	if(direction == '0'){
		// update $jsonLayoutH
		var length = $jsonLayoutH.items.length;
		if(length == 0){
			showMessageDialogError('Lỗi', 'Bố cục chưa được tạo. Hãy kéo một đối tượng bên trái vào màn hình để tạo bố cục');
			return false;
		}
		for(var i = 0; i < length; i++){
			var idRow = '#' + $jsonLayoutH.items[i].name;
			$jsonLayoutH.items[i].left = $(idRow + 'x').find('input[type="number"]').val();
			$jsonLayoutH.items[i].top = $(idRow + 'y').find('input[type="number"]').val();
			$jsonLayoutH.items[i].width = $(idRow + 'w').find('input[type="number"]').val();
			$jsonLayoutH.items[i].height = $(idRow + 'h').find('input[type="number"]').val();
			// set name when change name
			$jsonLayoutH.items[i].name = $(idRow + ' td input').val();
		}
		$jsonLayoutH.name = namelayout;
		// set direction
		$jsonLayoutH.direction = direction.toString();
		// set tv size
		$jsonLayoutH.size_screen = sizeScreen.toString();
		var item = JSON.stringify($jsonLayoutH);
		
		// update
		var idlayout = $('#content-select option:selected').val();
		if(typeof idlayout != 'undefined'){
			// copy layout
			if($jsonLayoutH.method == "copy"){
				$.post('ajaxSaveLayout.elcom', {data: item}, function(response){
					showGrowlMsg("Lưu bố cục thành công");
					// update layout on screen
					$('#content-select').append(
							'<option size-screen="'+sizeScreen+'" title="'+namelayout+'" type="0" direction="0" value="'+response.data+'">'+subString(namelayout)+'</option>');
					$('#content-select').val(response.data).trigger('change');
				});
			} else {
				$.post('ajaxUpdateLayout.elcom', {data: item}, function(response){
					showGrowlMsg("Cập nhật thành công");
					// update layout on screen
					$('#content-select').val(response.data).trigger('change');
					$('#content-select option[value="'+response.data+'"]').attr('title', namelayout);
					$('#content-select option[value="'+response.data+'"]').text(namelayout);
				});
			}
			
		} else {
			$.post('ajaxSaveLayout.elcom', {data: item}, function(response){
				showGrowlMsg("Lưu bố cục thành công");
				// update layout on screen
				$('#content-select').append(
						'<option size-screen="'+sizeScreen+'" title="'+namelayout+'" type="0" direction="0" value="'+response.data+'">'+subString(namelayout)+'</option>');
				$('#content-select').val(response.data).trigger('change');
			});
		}
	} else if(direction == '1'){
		// update jsonLayoutV
		var length = $jsonLayoutV.items.length;
		if(length == 0){
			showMessageDialogError('Lỗi', 'Bố cục chưa được tạo. Hãy kéo một đối tượng bên trái vào màn hình để tạo bố cục');
			return false;
		}
		for(var i = 0; i < length; i++){
			var idRow = '#' + $jsonLayoutV.items[i].name;
			$jsonLayoutV.items[i].left = $(idRow + 'x').find('input[type="number"]').val();
			$jsonLayoutV.items[i].top = $(idRow + 'y').find('input[type="number"]').val();
			$jsonLayoutV.items[i].width = $(idRow + 'w').find('input[type="number"]').val();
			$jsonLayoutV.items[i].height = $(idRow + 'h').find('input[type="number"]').val();
			// set name when change name
			$jsonLayoutV.items[i].name = $(idRow + ' td input').val();
		}
		$jsonLayoutV.name = namelayout;
		// set direction
		$jsonLayoutV.direction = direction.toString();
		// set tv size
		$jsonLayoutV.size_screen = sizeScreen.toString();
		
		var item = JSON.stringify($jsonLayoutV);
		// update
		var idlayout = $('#content-select option:selected').val();
		if(typeof idlayout != 'undefined'){
			// copy layout
			if($jsonLayoutV.method == "copy"){
				$.post('ajaxSaveLayout.elcom', {data: item}, function(response){
					showGrowlMsg("Lưu bố cục thành công");
					// update layout on screen
					$('#content-select').append(
							'<option size-screen="'+sizeScreen+'" title="'+namelayout+'" type="0" direction="0" value="'+response.data+'">'+subString(namelayout)+'</option>');
					$('#content-select').val(response.data).trigger('change');
				});
			} else {
				$.post('ajaxUpdateLayout.elcom', {data: item}, function(response){
					showGrowlMsg("Cập nhật thành công");
					// update layout on screen
					$('#content-select').val(response.data).trigger('change');
					$('#content-select option[value="'+response.data+'"]').attr('title', namelayout);
					$('#content-select option[value="'+response.data+'"]').text(namelayout);
				});
			}
		} else {// save
			$.post('ajaxSaveLayout.elcom', {data: item}, function(response){
				showGrowlMsg("Lưu bố cục thành công");
				// update layout on screen
				$('#content-select').append(
						'<option size-screen="'+sizeScreen+'" title="'+namelayout+'" type="0" direction="1" value="'+response.data+'">'+subString(namelayout)+'</option>');
				$('#content-select').val(response.data).trigger('change');
			});
		}
	}
	$('.namelayout').remove();
	return false;
}
function closeDialog(){
	$('.namelayout').trigger('remove').remove();
	return false;
}
function clearAutoRefresh(){
	clearInterval(autoRefresh);
}
function groupSelectChange(){
	// clear auto refresh;
	clearAutoRefresh();
	$('#detail-player').empty();
	$groupId = $('#groupbox').val();
	$.post('ajaxplayer.elcom',{data: $groupId}, function(response) {
				createAddList(response);
				// trigger click 
				$('.load-box tr td a').first().trigger('click');
			}
	).error(function() { 
		console.log("Server turn off"); 
	});

}

function groupContentChange(){
	$groupId = $('#groupbox').val();
	$.post('ajaxContent.elcom',{data: $groupId},function(response) {
		createContentGroup(response);
			}
	).error(function() { 
		console.log("Server turn off"); 
	});

}
function createTablePlayer(player){
	var id = player.id;
	var ip = player.ip;
	var name = player.name;
	var mac = player.mac;
	var status = player.status;
	var statusMonitor =  player.statusMonitor;
	var note = player.note;
	var noteFull = player.noteFull;
	var ram = player.ram;
	var totalram= player.totalRam;
	var sdcard = player.sdCard;
	var totalsdcard = player.totalSDCard;
	var serinumber = player.serinumber;
	var itemStaus = "";
	var itemStatusMonitor = "";
	if(status == 'Active'){
		itemStatus ="<td style='text-align: center'>"+
							"<img src='css/images/validgreen16x.png' title='Active'/>"+
					"</td>";
	} else if(status == 'Inactive'){
		itemStatus ="<td style='text-align: center'>"+
						"<img src='css/images/cancel16x.png' title='Inactive'/>"+
					"</td>";
	}
	if(statusMonitor == 'YES'){
		itemStatusMonitor ="<td class='monitoring-"+id+"' style='text-align: center'>"+
								"<a href=':javascript' onclick='return MonitoringYesClick(this, "+id+")'>"+
									"<img src='css/images/unlock16x.png' title='Open'>"+ 
								"</a>" +
							"</td>";
	} else if(statusMonitor == 'NO'){
		itemStatusMonitor ="<td class='monitoring-"+id+"' style='text-align: center'>"+
								"<a href=':javascript' onclick='return MonitoringNoClick(this, "+id+")' >" +
								"<img src='css/images/lock16x.png'  title='Close'>"+
								"</a>" +
							"</td>";
	}
	var item= "<tr class='"+id+"'>"+
					"<td>"+
					"<a title='"+mac+"' href=':javascript' ip='" + ip + "' id='"+ id + "' ram='"+ ram + "' " +
						"totalram='"+totalram+"' sdcard='"+sdcard+"' totalsdcard='"+totalsdcard+"' " +
						"statusMonitor='" +statusMonitor + "'" + "serinumber='"+serinumber +"'" +
						"onclick='return playerOnClick("+id+")'" +
						"class='player-link'>"+ mac +
						
					"</a>" +
				"</td>"+
				itemStatus +
				itemStatusMonitor +
				"<td title='"+noteFull+"'>"+note+"</td>"+
				"<td style='text-align: center'>" +
					"<a href='#javascript' id='"+id+"' onclick='return deleteSTB(this, "+id+")'>" +
					"<img src='css/images/trash.png'></a></td>" +
			"</tr>";
	$('.player-group').append(item);
}
function MonitoringYesClick($this, id){
	var itemStatusMonitor ="<a href=':javascript' onclick='return MonitoringNoClick(this, "+id+")'>"+
								"<img src='css/images/lock16x.png' title='Open'>"+ 
							"</a>";
	var left = $($this).position().left - 125;
	var top = $($this).position().top;
	var options = {'question':'Giám sát sẽ TẮT?', 'yes': 'Có', 'no': 'Không',
			'left':left, 'top': top, 'jfunction':'ajaxMonitoring.elcom'};
	showConfirm($this, options);
	$('.yes').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
		var json = {'flag': 'NO', 'id': id.toString()};
		var obj = JSON.stringify(json);
		$.post(options.jfunction,{data: obj}, function(){
			showGrowlMsg("Đã tắt thành công");
			$('.monitoring-' + id).empty();
			$('.monitoring-' + id).append(itemStatusMonitor);
			$('.img-monitoring-'+id).attr('src', 'css/images/lock16x.png');
			$('#' + id).attr('statusmonitor', 'NO');
			// trigger player click
			$('#' + id).trigger('click');
		});
	});
	return false;
}
function MonitoringNoClick($this, id){
	var itemStatusMonitor ="<a href=':javascript' onclick='return MonitoringYesClick(this, "+id+")'>"+
								"<img src='css/images/unlock16x.png' title='Open'>"+ 
							"</a>";
	var left = $($this).position().left - 125;
	var top = $($this).position().top;
	var options = {'question':'Giám sát sẽ MỞ?', 'yes': 'Có', 'no': 'Không',
			'left':left, 'top': top, 'jfunction':'ajaxMonitoring.elcom'};
	showConfirm($this, options);
	$('.yes').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
		var json = {'flag': 'YES', 'id': id.toString()};
		var obj = JSON.stringify(json);
		$.post(options.jfunction,{data: obj}, function(){
			showGrowlMsg("Đã mở thành công");
			$('.monitoring-' + id).empty();
			$('.monitoring-' + id).append(itemStatusMonitor);
			$('img-monitoring-'+id).attr('src', 'css/images/unlock16x.png');
			$('#' + id).attr('statusmonitor', 'YES');
			// trigger player click
			$('#' + id).trigger('click');
		});
	});
	return false;
}
function deleteSTB($this, id){
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không'};
	var left = $($this).position().left - 125;
	var top = $($this).position().top;
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
			'left':left, 'top': top, 'jfunction':'ajaxDeleteSTB.elcom'};
	showConfirm($this, options);
	$('.yes').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
		$.post(options.jfunction,{data: id}, function(response){
			showGrowlMsg("SettopBox đã xóa");
			//update UI
			$('.' + id).trigger('remove').remove();
			//trigger group[0] select and stb[0] select
			var idgroup = $('#groupbox option:first-child').val();
			if(typeof idgroup != 'undefined'){
				$('#groupbox').val(idgroup).trigger('change');
			}
			
		});
		return false;
	});
	return false;
	
}
function createAddList(response){
	// delete item in table
	$('.player-group').empty();
	var length = response.player.length;
	if(length == 0){
		var item ="<td style='text-align: center; font-size:20px'>"+
							"Nhóm không có settopbox"+
				  "</td>";
		$('.player-group').empty();
		$('.player-group').append(item);
		$('.info-detail').trigger('remove').remove();
		clearAutoRefresh();
	}
	var lengthGroup = response.playerOutGroup.length;
	addList();
	for(var i = 0; i < length; i++){
		createTablePlayer(response.player[i]);
		var id = response.player[i].id;
		var name = response.player[i].mac;
		$('#selectleft').append(createOption(id, name));
	}
	// Sort list
	Sort('selectleft');
	for(var i = 0; i < lengthGroup; i++){
		var id = response.playerOutGroup[i].id;
		var name = response.playerOutGroup[i].mac;
		$('#selectright').append(createOption(id, name));
	}
	// Sort list
	Sort('selectright');
}
function createContentGroup(response){
	// get content in group
	var length = response.content.length;
	addContent();
	for(var i = 0; i < length; i++){
		var id = response.content[i].id;
		var name = response.content[i].nameFull;
		$('#selectleftcontent').append(createOption(id, name));
	}
	// Sort list
	Sort('selectleftcontent');
	// get content out group
	var lengthGroup = response.contentOutGroup.length;
	for(var i = 0; i < lengthGroup; i++){
		var id = response.contentOutGroup[i].id;
		var name = response.contentOutGroup[i].nameFull;
		$('#selectrightcontent').append(createOption(id, name));
	}
	// Sort list
	Sort('selectrightcontent');
}
function createOption(id, name){
	var option = "<option value='"+ id + "'>"+name + "</option>";
	return option;
}
function addContent(){
	$('#content').empty();
	var item = "<div class='select-left-content'><h2 class='title-content'>Nội Dung Nhóm</h2>"+
					"<select class='content' id='selectleftcontent' multiple='multiple'>"+
						
					"</select>"+
				"</div>"+
				"<div class='button-center'>"+
					"<div style='margin-bottom: 10px;'>"+
						"<a href=':javascript' class='btn-left-to-right' onclick='return btnLeftContentClick()'> >> </a>"+
					"</div>"+
					
					"<div>"+
						"<a href=':javascript' class='btn-left-to-right' onclick='return btnRightContentClick()'> << </a>"+
					"</div>"+
				"</div>"+
				"<div class='select-right-content'><h2 class='title-content'>Nội Dung</h2>"+
					"<select class='content' id='selectrightcontent' multiple='multiple'>"+
					"</select>"+
				"</div>" + 
				"</div>";
	$('#content').addClass('post');
	$('#content').append(item);
}
function addList(){
	$('#addlist').empty();
	var item = "<div class='select-left'><h2 class='title-content'>Box Nhóm "+$.trim($('#groupbox option:selected').text())+"</h2>"+
					"<select class='player' id='selectleft' multiple='multiple'>"+
					"</select>"+
				"</div>"+
				"<div class='button-center'>"+
					"<div style='margin-bottom: 10px;'>"+
						"<a href=':javascript' class='btn-left-to-right' onclick='return btnLeftRightClick()'> >> </a>"+
					"</div>"+
					
					"<div>"+
						"<a href=':javascript' class='btn-left-to-right' onclick='return btnRightLeftClick()'> << </a>"+
					"</div>"+
				"</div>"+
				"<div class='select-right'><h2 class='title-content'>Box Ngoài Nhóm</h2>"+
					"<select class='player' id='selectright' multiple='multiple'>"+
					"</select>"+
				"</div>" + 
				"</div>";
	$('#addlist').append(item);
}
function tabListSTB_Click(){
	// trigger click 
	$('.load-box tr td a').first().trigger('click');
}
function tabAddListOnClick(){
	$groupId = $('#groupbox').val();
	if($groupId == '' || $groupId == null){
		$('#addlist').find('#msg').remove();
		var msg = "Vui lòng chọn nhóm để thực hiện chức năng này.";
		var item = $(drawMsgInfo(msg)).css({
			'width': 66 + '%',
			'margin-left': 45
		});
		$('#addlist').prepend(item).fadeIn();
		
	}
	return true;
}
function getDetailSTB(itemMonitor, ip, id, name){
	var item = "<div class='info-detail'>"+
					"<div class='info-detail-left'>"+
					"<form action='' method='post' class='ui-form'>"+
						"<label>"+ "<span>IP :</span>"+ 
						"<input id='ip' type='text'" + "value='" + ip + "'" +
								"name='ip' placeholder='IP' />"+ "</label>"+ "<label>"+ 
						"<span>Tên :</span>"+ 
						"<input id='nameplayer' type='text' name='name' placeholder='Tên Player' value='"+name+"' />"+ 
						"</label>"+
					"</form>"+
					"<div style='margin-left: 10px;'>"+
						"<p1 class='text-title'>Danh Sách Tập Tin:</p1>"+
						"<div style='clear: both;'>&nbsp;</div>"+
						"<div class='playerofgroup'>"+
							"<table>"+
								"<thead>"+
									"<tr>"+
										"<th>Tên</th>"+
										"<th>Download</th>"+
										"<th>Xóa</th>"+
									"</tr>"+
								"</thead>"+
								"<tbody id='scrolling' class='content-player'>"+ 
								"</tbody>"+
							"</table>" +
						"</div>" +
					"</div>"+
					"<div class='btn-update'>"+
						"<form class='ui-form'>"+
							"<label>"+ 
							"<input type='submit'" +
									"class='button' id='btnupdate' value='Cập Nhật' onclick='return btnUpdatePlayer_onclick("+id+")' /></label>"+
						"</form>"+
					"</div>"+
				"</div>"+
				
				"<div class='info-detail-right'>"+
						itemMonitor +
					"<div class='layout-detail'><div class='layoutdetail-h'></div></div>"+
					"<div class='chart'>"+
						"<div id='piechart-memory' " +
						"style='width: 200px; height: 60px; float: left'></div>"+
						"<div id='piechart-ram'" +
						"style='width: 200px; height: 60px; float: left'></div>"+
					"</div>" +
				"</div>"+
				"</div>";
	return item;
}
// clear refresh when leave page
$(function(){
	if($('#page').find('#detail-player').length <= 0){
		// clear auto refresh;
		clearAutoRefresh();
	}
});
function playerOnClick(id){
	// clear auto refresh;
	clearAutoRefresh();
	// css when click
	$('.player-group tr td a').removeClass('background-item-player');
	$('#' + id).addClass('background-item-player');
	var _id = '#' + id;
	var ip = $(_id).attr('ip');
	var name = $(_id).attr('title');
	var ram = parseInt($(_id).attr('ram'));
	var totalRam= parseInt($(_id).attr('totalram'));
	var sdcard = parseInt($(_id).attr('sdcard'));
	var totalSDCard= parseInt($(_id).attr('totalsdcard'));
	var statusMonitor = $(_id).attr('statusMonitor');
	var serinumber = $(_id).attr('serinumber');
	var itemMonitor= "";
	if(statusMonitor == 'YES'){
		itemMonitor = "<div>"+
							"<p1 class='text-title'>Giám sát:</p1>"+
							"<img class='img-monitoring-"+id+"' src='css/images/unlock16x.png' title='Open'"+
					 "</div>";
	} else if(statusMonitor == 'NO'){
		itemMonitor = "<div>"+
							"<p1 class='text-title'>Giám sát:</p1>"+
							"<img class='img-monitoring-"+id+"' src='css/images/lock16x.png' title='Close'"+
					 "</div>";
	}
	var item = getDetailSTB(itemMonitor, ip, id, name);
	$.post('ajaxDetailPlayer.elcom',{data: id},function(response){
				console.log('Download Run time');
				$('#detail-player').empty();
				$('#detail-player').append(item);
				var _length = response.content.length;
				if(_length == 0){
					item ="<td style='text-align: center; font-size:16px'>"+
										"Box chưa có nội dung"+
							  "</td>";
					$('.content-player').append(item);

				} else {
					for(var i = 0; i < _length; i++){
						var content = createContentPlayer(response.content[i].id, response.content[i].nameFull, response.content[i].statusDownload);
						$('.content-player').append(content);
					}
				}
				drawChartRam(ram, totalRam);
				drawChartMemory(sdcard, totalSDCard);
				
				// draw capture
				if(statusMonitor == 'YES'){
					$.post('ajaxMonitor.elcom',{data: id}, function(response){
						var _length = response.layout.length;
						var layout = response.layout;
						$('.layoutdetail-h').empty();
						for(var i = 0; i < _length; i++){
							var random = layout[i].random;
							var x = layout[i].left;
							var y = layout[i].top;
							var w = layout[i].width;
							var h = layout[i].height;
							var url = layout[i].url;
							drawImageCapture(random, x, y, w, h, url);
						}
						
					});
				}
			}
		).error(function(){
			console.log("Server turn off");
		});
	autoRefresh = setInterval(function(){
					var item = getDetailSTB(itemMonitor, ip, id, name);
					$.post('ajaxDetailPlayer.elcom',{data: id},function(response){
						console.log('Download Run time');
						$('#detail-player').empty();
						$('#detail-player').append(item);
						var _length = response.content.length;
						if(_length == 0){
							item ="<td style='text-align: center; font-size:16px'>"+
												"Box chưa có nội dung"+
									  "</td>";
							$('.content-player').append(item);
						} else {
							for(var i = 0; i < _length; i++){
								var content = createContentPlayer(response.content[i].id, response.content[i].nameFull, response.content[i].statusDownload);
								$('.content-player').append(content);
							}
						}
						
						drawChartRam(ram, totalRam);
						drawChartMemory(sdcard, totalSDCard);
						// draw capture
						if(statusMonitor == 'YES'){
							$.post('ajaxMonitor.elcom',{data: serinumber}, function(response){
								var _length = response.layout.length;
								var layout = response.layout;
								$('.layoutdetail-h').empty();
								for(var i = 0; i < _length; i++){
									var random = layout[i].random;
									var x = layout[i].left;
									var y = layout[i].top;
									var w = layout[i].width;
									var h = layout[i].height;
									var url = layout[i].url;
									drawImageCapture(random, x, y, w, h, url);
								}
								
							});
						}
					}
					).error(function(){
						console.log("Server turn off");
					});
	}, 5000);

	return false;
	
}
function drawImageCapture(random, x, y, w, h, url){
	var w_default = 441;
	var h_default = 227;
	var rateLeft = $width/441;
	var rateTop = $height/227;
	var img = $("<img src='"+url+"#"+random+"'></img>");
	img.css({
		'left':x/rateLeft,
		'top': y/rateTop,
		'width': w/rateLeft,
		'height': h/rateTop
	});
	$('.layoutdetail-h').append(img);
}

var $ram, $totalram, $memory, $totalmemory;
function drawChartRam(ram, totalram){
	$ram = ram;
	$totalram = totalram;
	google.load('visualization', '1.0', {packages:['corechart'], callback: drawVisualizationRam});
}
function drawChartMemory(memory, totalmemory){
	$memory = memory;
	$totalmemory = totalmemory;
	google.load('visualization', '1.0', {packages:['corechart'], callback: drawVisualizationMemory});
}
function drawVisualizationRam() {
	// Create and populate the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Ram');
    data.addColumn('number', 'Percent');
    data.addRows(2);
    data.setCell(0, 0, 'Sử dụng');
    data.setCell(1, 0, 'Còn trống');
    data.setCell(0, 1, $totalram);
    data.setCell(1, 1, $ram);
    var options = {
    		title : 'Ram',
    		is3D: true
    	};
    
    // Create and draw the visualization.
    var chart= new google.visualization.PieChart(document.getElementById('piechart-ram'));
    chart.draw(data, options);
 }

function drawVisualizationMemory() {
	// Create and populate the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Memory');
    data.addColumn('number', 'Percent');
    data.addRows(2);
    data.setCell(0, 0, 'Sử dụng');
    data.setCell(1, 0, 'Còn trống');
    data.setCell(0, 1, $totalmemory);
    data.setCell(1, 1, $memory);
    var options = {
    		title : 'Bộ nhớ',
    		is3D: true
    	};
    
    // Create and draw the visualization.
    var chart= new google.visualization.PieChart(document.getElementById('piechart-memory'));
    chart.draw(data, options);
 }
function btnUpdatePlayer_onclick(id){
	var name = $('#nameplayer').val();
	var ip = $('#ip').val();
	var obj = {"id": id.toString(), "name":name, "ip": ip};
	var jsObj = JSON.stringify(obj); 
	$.post('ajaxUpdatePlayer.elcom',{data: jsObj}, function(response){
			showGrowlMsg('Cập nhật thành công');
			$('.background-item-player').text(subString(name));
			$('.background-item-player').attr('title', name);
		}
	).error(function(){
		console.log("Server turn off");
	});
	return false;
}
function createContentPlayer(id, namefull, statusDownload){
	var item =  "<tr class='"+id+"'>"+
					"<td title='"+namefull+"'>"+subString(namefull)+"</td>"+
					"<td style='text-align: center'>"+statusDownload+"</td>" + 
					"<td style='text-align: center'>"+
						"<a href='#javascript' id='"+id+"' onclick='return deleteContentPlayer(this, "+id+")'>"+
								"<img src='css/images/trash.png'>"+
						"</a>"+
					"</td>"+
				"</tr>";
	return item;
}
function deleteContentPlayer($this, id){
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không'};
	var left = $($this).position().left - 125;
	var top = $($this).position().top;
	var options = {'question':'Bạn có chắc xóa?', 'yes': 'Có', 'no': 'Không',
			'left':left, 'top': top, 'jfunction':'ajaxDeleteContentPlayer.elcom'};
	showConfirm($this, options);
	$('.yes').on('click', function(){
		$('.question').fadeOut(300, function() {
			$('.question').remove();
		});
		var idPlayer = $('.background-item-player').attr('id');
		var json = {'idplayer': idPlayer, 'idcontent': id.toString()};
		var obj = JSON.stringify(json);
		$.post(options.jfunction,{data: obj}, function(){
			showGrowlMsg("Đã xóa thành công");
			$('.' + id).trigger('remove').remove();
		});
	});
	return false;
}
// trigger ready 
$(function(){
	// trigger option[0] layout template select 
	$('#content-select-tmp').on('ready', function(){
		var idlayout = $('#content-select-tmp option:first-child').val();
		if(typeof idlayout != 'undefined'){
			$('#content-select-tmp').val(idlayout).trigger('change');
		}
	});
	$('#content-select-tmp').trigger('ready');
	// when table show conbox in group load
	$('.load-box').on('ready',function(){
		//trigger group[0] select and stb[0] select
		var idgroup = $('#groupbox option:first-child').val();
		if(typeof idgroup != 'undefined'){
			$('#groupbox').val(idgroup).trigger('change');
			
		}
		
	});
	$('.load-box').trigger('ready');
});

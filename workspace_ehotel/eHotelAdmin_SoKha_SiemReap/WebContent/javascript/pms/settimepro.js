function SetTimePromotion() {
	var lt = new LoadTime();
	this.run = function() {
		lt.load();
	};
}

function LoadTime() {
	var my = this;
	this.load = function() {
		my.draw();
	};
	this.reload = function() {

	};
	this.draw = function() {
		my.get();
	};
	this.get = function() {
		var url = "ServicePromotion?CMD=5";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			var arr = tx.split(",");
			$("time_show").value = arr[0];
			$("time_hide").value = arr[1];
			$("set_time_config").onclick = function() {
				my.accept();
			};
		};
	};
	this.accept = function() {
		var timeshow = $("time_show").value;
		var timehide = $("time_hide").value;
		if (timeshow == "") {
			var albox = new alertBox();
			albox.show("<br/>Time show promotion is empty???");
			return;
		}
		if (timehide == "") {
			var albox = new alertBox();
			albox.show("<br/>Time hide promotion is empty???");
			return;
		}
		var url = "ServicePromotion?CMD=3";
		url += "&t=" + Math.random();
		var param = "&timeshow=" + timeshow;
		param += "&timehide=" + timehide;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			if (tx == "true") {
				var albox = new successBox();
				albox.show("<br/>Config time success!!!");
			} else {
				var albox = new alertBox();
				albox.show("Config time error!!!");
			}
		};
	};
}
function validate(evt) {
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]/;
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
	}
}
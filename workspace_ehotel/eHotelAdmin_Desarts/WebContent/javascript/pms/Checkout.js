var checkout = new function Checkout() {
	var my = this;
	this.load = function() {
		var date = new Date();
		var mm = date.getMonth();
		mm = mm + 1;
		var m = mm + "";
		if (mm < 10)
			m = 0 + m;
		var dd = date.getDate() + "";
		if (date.getDate() < 10)
			dd = "0" + dd;
		var hh = date.getHours() + "";
		if (date.getHours() < 10)
			hh = "0" + hh;
		var pp = date.getMinutes() + "";
		if (date.getMinutes() < 10)
			pp = "0" + pp;
		var current = date.getFullYear() + "-" + m + "-" + dd + "T" + hh + ":"
				+ pp;

		document.getElementById('checkout_alarm').value = current;

		// document.getElementById('checkin_file_history').value = current;
		my.getHistory();
	};
	this.onclick = function() {

	};
	this.getHistory = function() {
		var url = "folioPms?CMD=28";
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			if (tx == "") {
				document.getElementById('checkout_file_history').type = 'text';
				document.getElementById('checkout_file_history').value = "No content";
			} else {
				document.getElementById('checkout_file_history').type = 'datetime-local';
				document.getElementById('checkout_file_history').value = tx;
			}
		};
	};
};

function getHistory(current) {
	var url = "folioPms?CMD=28";
	url += "&r=" + Math.random();
	var f = new AjaxGetText(url);
	f.complet = function(tx) {
		if (tx == "") {
			document.getElementById('checkout_file_history').type = 'text';
			document.getElementById('checkout_file_history').value = "No content";
		} else {
			document.getElementById('checkout_file_history').type = 'datetime-local';
			document.getElementById('checkout_file_history').value = tx;
		}
	};
}

function checkoutFileForFolio() {
	var time = document.getElementById('checkout_alarm').value;
	var filename = document.getElementById('nameFileout').value;
	var url = "folioPms?CMD=27";
	url += "&time=" + time;
	url += "&filename=" + filename;
	url += "&r=" + Math.random();
	var f = new AjaxGetText(url);
	f.complet = function(tx) {
		var albox = new successBox();
		if (tx == "success")
			albox.show("Successful");
		else
			albox.show("Error");
	};
}
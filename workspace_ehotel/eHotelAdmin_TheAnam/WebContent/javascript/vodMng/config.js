function reset() {
	$("cfhost").value = "";
	$("cfport").value = "";
	$("cfuser").value = "";
	$("cfpass").value = "";
}

function config() {
	if ($("cfhost").value == "" || $("cfport").value == ""
			|| $("cfuser").value == "" || $("cfpass").value == "") {
		alert("Data input invalid!");
		return false;
	}
	else{
		var url = "ConfigFtp?CMD=1";
		url += "&host=" + $("cfhost").value;
		url += "&port=" + $("cfport").value;
		url += "&user=" + $("cfuser").value;
		url += "&pass=" + $("cfpass").value;
		url += "&r=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			var albox = new successBox();
			albox.show(tx);
		};
	}
}


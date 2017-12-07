var rte=new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var mess = new function Message() {
	var my = this;
	this.load = function() {
		initTinymceMess(2, "680px");
//		var x = document.getElementsByClassName("mceFirst");
		
		$("messupdate").onclick = function() {
			my.updateMessage();
		};
	};
	this.updateMessage = function(){
		var mess = getDataFromEditor("descriptionInput");
		var url = "Message?CMD=1";
		url += "&t=" + Math.random();
		var param = "&message=" + mess;
		var f = new AjaxPostText(url, param);
		f.complet = function(tx) {
			if(tx=="true"){
				var albox = new successBox();
				albox.show("INFO<br/><br/>Update welcome message successful!");
			}else{
				var albox = new successBox();
				albox.show("ERROR<br/><br/>Update welcome message unsuccessful!");
			}
		};
	};
	this.get = function(){
		var url = "Message?CMD=1";
		url += "&t=" + Math.random();
		var f = new AjaxGetText(url);
		f.complet = function(tx) {
			alert(tx);
		};
	};
};
window.onload = function() {
	mess.load();
};
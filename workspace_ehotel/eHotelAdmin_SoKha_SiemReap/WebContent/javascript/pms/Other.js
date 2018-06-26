var rte = new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var other = new function Other() {
	var tag = 0;
	var my = this;
	var prom = new Promotion();
	var exchange = new Exchange();
	var adv = new Advertise();
	var lt = new SetTimePromotion();
	this.load = function() {
		if (tag == 0){
			$("id_table").style.display = "block";
			$("div_set_time").style.display = "none";
			prom.run();
		}
		if (tag == 1){
			$("id_table").style.display = "block";
			$("div_set_time").style.display = "none";
			exchange.run();
		}
		if (tag == 2){
			$("id_table").style.display = "block";
			$("div_set_time").style.display = "none";
			adv.run();
		}
		if (tag == 5){
			$("id_table").style.display = "none";
			$("div_set_time").style.display = "block";
			lt.run();
		}
	};
	this.addItem = function() {
		if (tag == 0)
			prom.addItem();
		if (tag == 1)
			exchange.addItem();
		if (tag == 2)
			adv.addItem();
	};
	this.onclick = function() {
		prom.onclick();
		exchange.onclick();
		adv.onclick();
	};
	this.changeTab = function(id) {

		var div = $("tab_menu").getElementsByTagName("div");
		for ( var i = 0; i < div.length; i++) {
			div[i].className = "tab_menu_item";
		}
		div[id].className = "tab_menu_item_sel";
		tag = id;
		my.load();
	};

};
window.onload = function() {
	other.load();
	document.body.onclick = other.onclick;
};
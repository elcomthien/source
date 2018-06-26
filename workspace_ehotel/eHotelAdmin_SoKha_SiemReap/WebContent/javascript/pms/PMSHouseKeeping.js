var rte = new RTE();
rte.initRTE("../rte/images/", "../rte/", "../rte/");
var hk = new function HouseKeeping() {
	var tag = 0;
	var my = this;
	this.load = function() {
		if (tag == 0)
			user.run();
		if (tag == 1)
			bar.run();
		if (tag == 2)
			rs.run();
	};
	this.addItem = function() {
		if (tag == 0)
			user.addItem();
		if (tag == 1)
			bar.addItem();
		if (tag == 2)
			rs.addItem();
	};
	this.onclick = function() {
		if (tag == 0)
			user.onclick();
		if (tag == 1)
			bar.onclick();
		if (tag == 2)
			rs.onclick();
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
	hk.load();
	document.body.onclick = hk.onclick;
};

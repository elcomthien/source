function MemoryServiceSystem() {
	// var my = this;
	this.run = function() {
		$("table_id_table").remove();
		getMemorySystem();
	};
	this.onclick = function() {

	};
	this.addItem = function() {

	};
};

function getMemorySystem() {
	var url = "ServiceSystem?CMD=9";
	url += "&r=" + Math.random();
	var f = new AjaxGetText(url);
	f.complet=function(tx){	
		var totalhdd = parseXML("totalhdd", tx);
		var usedhdd = parseXML("usedhdd", tx);
		var freehdd = parseXML("freehdd", tx);
		var usedperhdd = parseXML("usedperhdd", tx);
		var freeperhdd = parseXML("freeperhdd", tx);
		
		var totalram = parseXML("totalram", tx);
		var usedram = parseXML("usedram", tx);
		var freeram = parseXML("freeram", tx);
		var usedperram = parseXML("usedperram", tx);
		var freeperram = parseXML("freeperram", tx);
		
		var html = "<div id='canvas-holder-ram' style='float: left; margin-top: 20px; margin-left: 50px;'><canvas id='chart-area-ram' width='210' height='210'/></div>";
		
		html += "<div style='width: 20px; height: 20px; background-color: #F7464A; margin-top: 70px; float: left; margin-left:70px;'></div>";
		html += "<div style='width: 20px; height: 20px; margin-top: 72px; float: left; margin-left:20px;'>Used</div>";
		
		html += "<div style='width: 20px; height: 20px; background-color: #4169E1; margin-top: 100px; float: left; margin-left:-60px;'></div>";
		html += "<div style='width: 20px; height: 20px; margin-top: 102px; float: left; margin-left:-20px;'>Free</div>";
		
		html += "<div id='canvas-holder-hdd' style='float: right; margin-top: 20px; margin-right: 50px;'><canvas id='chart-area-hdd' width='210' height='210'/></div>";
		
		html += "<div style='float: left; margin-top: 230px; margin-left: -260px; font-size: 30px;'>RAM</div>";
		html += "<div style='float: right; margin-top: 230px; margin-right: -138px; font-size: 30px;'>HDD</div>";
		
		document.getElementById("id_table").innerHTML = html;
		document.getElementById("id_table").style.height="270px";
		var pieram = [
						{
							value: parseInt(usedperram),
							color:"#F7464A",
							highlight: "#FF5A5E",
							label: "Used: "+usedram+"M ("+usedperram+"%)"
						},
						{
							value: parseInt(freeperram),
							color: "#4169E1",
							highlight: "#6495ED",
							label: "Free: "+freeram+"M ("+freeperram+"%)"
						}
					];
		
		var ctxram = document.getElementById("chart-area-ram").getContext("2d");
		window.myPie = new Chart(ctxram).Pie(pieram);
		
		var piehdd = [
						{
							value: parseInt(usedperhdd),
							color:"#F7464A",
							highlight: "#FF5A5E",
							label: "Used: "+usedhdd+"M ("+usedperhdd+"%)"
						},
						{
							value: parseInt(freeperhdd),
							color: "#4169E1",
							highlight: "#6495ED",
							label: "Free: "+freehdd+"M ("+freeperhdd+"%)"
						}
					];
			var ctxhdd = document.getElementById("chart-area-hdd").getContext("2d");
			window.myPie = new Chart(ctxhdd).Pie(piehdd);
	};
}

function parseXML(name, xml) {
	var start = "<" + name + ">";
	var stop = "</" + name + ">";
	return xml.substring(xml.indexOf(start) + start.length, xml.indexOf(stop));
}

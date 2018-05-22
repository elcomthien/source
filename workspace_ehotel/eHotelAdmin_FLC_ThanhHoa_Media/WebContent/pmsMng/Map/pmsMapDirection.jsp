<%@ include file="../../include/Paramter.jsp"%>
<%@page import="ehotel.domain.pms.eLocationMap"%>
<%@page import="ehotel.domain.pms.eMenu"%>
<%@page pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>

<jsp:directive.page import="java.util.Vector" />
<%
eLocationMap _eLocationMap =new eLocationMap();
if(null!=request.getAttribute("getLocation")){
	_eLocationMap =(eLocationMap)request.getAttribute("getLocation");
}
Vector<eMenu> loadMenu =new Vector();;
if(null!=request.getAttribute("loadMenu")){
	loadMenu =(Vector<eMenu>)request.getAttribute("loadMenu");
}
%>
<link rel="stylesheet" href="../css/table.css" type="text/css"></link>
<link rel="stylesheet" href="../css/pmsHotel.css" type="text/css"></link>
<link rel="stylesheet" href="../css/cssTableMapDirection.css"
	type="text/css"></link>
<link rel="stylesheet" href="../css/object.css" type="text/css"></link>
<script type="text/javascript"
	src="../javascript/pms/pmsMapDirection.js"></script>
<script type="text/javascript"
	src="../javascript/pms/ajaxPmsMapDirection.js"></script>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=false"></script>
<script type="text/javascript">
var ten="";
var diachi="";
var kc="";
var toado_x="";
var toado_y="";
var themmoi="";
var ok="";
var cancel="";
var checkName="";
var checkDistance="";
var checkX="";
var checkY="";
var checkAdd="";
var checkCoord="";
var checkUpdate="";
var checkDelete="";
var fX=<%=_eLocationMap.getX() %>;
var ly=<%=_eLocationMap.getY() %>;
var checkDeleteFrom="";
var changLocation="";
var changeWay="";
var changeLocation="";
var changeMap="";
var changeLocation="";
var changeMapTap="";
var changeLocationTap="";
function changeLang(){
	ten ="<%=readerLang.getContent("Name") %>";
	diachi="<%=readerLang.getContent("Address") %>";
	kc ="<%=readerLang.getContent("distance") %>";
	toado_x="<%=readerLang.getContent("coord_x") %>";
	toado_y="<%=readerLang.getContent("coord_y") %>";
	themmoi="<%=readerLang.getContent("add") %>";
	ok="<%=readerLang.getContent("OK") %>";
	cancel="<%=readerLang.getContent("Cancel") %>";
	checkName="<%=readerLang.getContent("checkName") %>";
	checkDistance="<%=readerLang.getContent("checkDistance") %>";
	checkX="<%=readerLang.getContent("checkX") %>";
	checkY="<%=readerLang.getContent("checkY") %>";
	checkAdd="<%=readerLang.getContent("checkAdd") %>";
	checkCoord="<%=readerLang.getContent("checkCoord") %>";
	checkUpdate="<%=readerLang.getContent("checkUpdate") %>";
	checkDelete="<%=readerLang.getContent("checkDelete") %>";
	checkDeleteFrom="<%=readerLang.getContent("checkDeleteFrom") %>";
	changLocation="<%=readerLang.getContent("changLocation") %>";
	changeWay="<%=readerLang.getContent("changeWay") %>";
	changeLocation="<%=readerLang.getContent("changeLocation") %>";
	changeMap="<%=readerLang.getContent("changeMap") %>";
	changeLocation="<%=readerLang.getContent("changeLocation") %>";
	changeMapTap="<%=readerLang.getContent("changeMapTap") %>";
	changeLocationTap="<%=readerLang.getContent("changeLocationTap") %>";	
}
function initialize() 
  {
  	//10.779867 ,106.686037
    var latlng = new google.maps.LatLng(<%=_eLocationMap.getX() %>,<%=_eLocationMap.getY() %>);
    var myOptions =
    {
      zoom: 12,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    
    var map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);   
   setMarkers(map);    
  }  
 window.onload = function()
{
	initialize() ;
}  
function setMarkers(map) {
   // Add markers to the map
   // Marker sizes are expressed as a Size of X,Y
   // where the origin of the image (0,0) is located
   // in the top left of the image.
   // Origins, anchor positions and coordinates of the marker
   // increase in the X direction to the right and in
   // the Y direction down.
   var image = new google.maps.MarkerImage('../icon/markerA.png',
       // This marker is 20 pixels wide by 32 pixels tall.
       new google.maps.Size(20, 32),
       // The origin for this image is 0,0.
       new google.maps.Point(0,0),
       // The anchor for this image is the base of the flagpole at 0,32.
       new google.maps.Point(0, 32));
   var shadow = new google.maps.MarkerImage('../icon/markerA.png',
       // The shadow image is larger in the horizontal dimension
       // while the position and offset are the same as for the main image.
       new google.maps.Size(37, 32),
       new google.maps.Point(0,0),
       new google.maps.Point(0, 32));
  
    marker;
      var myLatLng = new google.maps.LatLng(<%=_eLocationMap.getX() %>,<%=_eLocationMap.getY() %>);
    var marker= new google.maps.Marker({
         position: myLatLng,
         map: map,
         shadow: shadow,
         icon: image
     });
 }
  var geocoder;
var mapDirection;
 function getKqSearchDirection(textsearch1) {
       geocoder = new google.maps.Geocoder();
        var myOptions = {
          center: new google.maps.LatLng(10.779867 ,106.686037),
          zoom: 12,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        mapDirection = new google.maps.Map(document.getElementById("map_canvas1"),
            myOptions);
        codeAddress(textsearch1);
      }
  function codeAddress(textsearch1) {
    var address ="";
  	if(textsearch1==0){
  	  var address =$("txtsearch1").value;  	 
  	}else{
  		address=textsearch1;  		
  	}
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        mapDirection.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: mapDirection,
            position: results[0].geometry.location
        });
      } else
      {        
      	
      }
    });
  }
function searchCheck(){
var x=document.getElementById("group1").checked;
if(x==true){
getKqSearch(1);
}else{
	var add =$("txtsearch1").value;	
	if(add==""){
	var albox=new alertBox();
		albox.show(checkName);
		return;	
	}else{	
	  var address =$("txtsearch1").value;	
		getKqSearchDirection(0);
	}
}
}
 function initialize111(chuoi) {
        var mapOptionsAuto = {
          center: new google.maps.LatLng(10.779867 ,106.686037),
          zoom: 13,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };       
        var mapAuto = new google.maps.Map(document.getElementById('map_canvas1'),
          mapOptionsAuto );
        var input="";
		if(chuoi==0){
        input =document.getElementById('txtsearch1'); 
        }else{
        input=chuoi;
        }
        var autocomplete = new google.maps.places.Autocomplete(input);
        autocomplete.bindTo('bounds', mapAuto);
        setFrm(mapAuto);
        var infowindow = new google.maps.InfoWindow();
        var marker = new google.maps.Marker({
          map: mapAuto 
        });
        google.maps.event.addListener(autocomplete, 'place_changed', function() {
          infowindow.close();
          var place = autocomplete.getPlace();
          if (place.geometry.viewport) {
            mapAuto.fitBounds(place.geometry.viewport);
          } else {
            mapAuto.setCenter(place.geometry.location);
            mapAuto.setZoom(12);  // Why 17? Because it looks good.
          }
          var image = new google.maps.MarkerImage(
              place.icon,
              new google.maps.Size(71, 71),
              new google.maps.Point(0, 0),
              new google.maps.Point(17, 34),
              new google.maps.Size(35, 35));
          marker.setIcon(image);
          marker.setPosition(place.geometry.location);
          var address = '';
          if (place.address_components) {
            address = [(place.address_components[0] &&
                        place.address_components[0].short_name || ''),
                       (place.address_components[1] &&
                        place.address_components[1].short_name || ''),
                       (place.address_components[2] &&
                        place.address_components[2].short_name || '')
                      ].join(' ');
          }
          infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
          infowindow.open(mapAuto, marker);
        });
      }
function  loadMap(chuoi){
$("search2").style.display = 'block';
	$("search1").style.display = 'none';
initialize111(chuoi);
}
function HideloadMap(){
//initialize_tab2();
}
function showText()
{
	$("search1").style.display = 'block';
	$("search2").style.display = 'none';
}
</script>
<div style="width: 1024;">
	<div class="left_content">
		<div class="left_content_bottom">
			<div class="grid">
				<div class="grid_header">
					<span
						style="float: left; margin-left: 20px; margin-top: 10px; color: white; font-size: 22px;"
						id="title_subject"></span>
					<div style="float: right;" class="tab_menu" id="tab_menu">
						<div class="tab_menu_item_sel" id="0"
							onclick="changeTab(this.id);">
							<p style="margin-top: 5px; height: 20px;"><%=readerLang.getContent("changeMap") %></p>
						</div>
						<div class="tab_menu_item" id="1" onclick="changeTab(this.id);">
							<p style="margin-top: 5px; height: 0px;"><%=readerLang.getContent("changeLocation") %></p>
						</div>
					</div>
				</div>
				<div class="grid_center" id="grid_center" style="height: 450px">
					<div id="id_table"
						style="height: auto; min-height: 270; height: 450; float: left">
						<div id="div1"
							style="height: 430; width: 400px; float: left; border: 1px solid gray; margin-left: 10px; margin-top: 10px; margin-bottom: 10px">
							<div id="map_canvas" style="height: 100%"></div>
						</div>
						<div id="div2"
							style="height: 430; width: 275; float: left; border: 1px solid gray; margin-left: 5px; margin-top: 10px; margin-right: 10px">
							<div id="phantren" style="height: 5%">
								<div id="div2_0" style="float: left; margin-top: 10px">
									<input type="image" src="../icon/edit_redo _next.png"
										onclick="hideDivGoogleMap();" />
								</div>
								<div id="div2_1"
									style="margin-top: 10px; margin-left: 70; float: left"><%=readerLang.getContent("changeMapTap") %>
								</div>
							</div>
							<div id="clear" style="clear: both"></div>
							<div id="phanduoi"
								style="height: 89%; background: #dbeef4; border-top: 1px solid gray;">
								<div id="ngoai1" style="width: 100%; margin-top: 20px">
									<div id="name1"
										style="width: 30%; float: left; margin-left: 10" align="left"><%=readerLang.getContent("Name") %>:
									</div>
									<div id="name2" style="width: 60%; float: left" align="left">
										<input type="text" id="txtName" name="txtName" size="24px"
											style="color: red" value="<%=_eLocationMap.getName()%>" />
									</div>
								</div>
								<div id="clear" style="clear: both"></div>
								<div id="ngoai2" style="width: 100%; margin-top: 15">
									<div id="name3"
										style="width: 30%; float: left; margin-left: 10" align="left"><%=readerLang.getContent("Address") %>:
									</div>
									<div id="name4" style="width: 60%; float: left" align="left">
										<textarea cols="17" rows="5" id="txtAddress"
											style="margin-right: 10px" name="txtAddress"><%=_eLocationMap.getAddress()%></textarea>
									</div>

								</div>
								<div id="clear" style="clear: both"></div>
								<div id="ngoai3" style="width: 100%; margin-top: 15">
									<div id="name5"
										style="width: 30%; float: left; margin-left: 10" align="left"><%=readerLang.getContent("coord_x") %>:
									</div>
									<div id="name6" style="width: 60%; float: left" align="left"><%=_eLocationMap.getX()%></div>
								</div>
								<div id="clear" style="clear: both"></div>
								<div id="ngoai4" style="width: 100%; margin-top: 15">
									<div id="name7"
										style="width: 30%; float: left; margin-left: 10" align="left"><%=readerLang.getContent("coord_y") %>:
									</div>
									<div id="name8" style="width: 60%; float: left" align="left"><%=_eLocationMap.getY()%></div>
								</div>
								<div id="clear" style="clear: both"></div>
								<div id="div2_10" style="margin-top: 25px;">
									<input type="button" onclick="callAjax();"
										value="<%=readerLang.getContent("update") %>" />
								</div>
							</div>
						</div>
						<div id="div5"
							style="height: 400; width: 30; float: left; border: 0px solid gray; margin-left: 5px; margin-top: 10px; margin-right: 10px; display: none">
							<input type="image" src="../icon/edit_redo_back.png"
								onclick="showDivGoogleMap();" />
						</div>
					</div>
					<div id="id_table1"
						style="height: auto; min-height: 270; height: 450; float: left; display: none;">
						<div id="div3"
							style="height: 430; width: 400; float: left; border: 1px solid gray; margin-left: 10px; margin-top: 10px">
							<div id="map_canvas1" style="height: 100%"></div>
						</div>
						<div id="div4"
							style="height: 430; width: 275; float: left; border: 1px solid gray; margin-left: 5px; margin-top: 10px; margin-right: 10px">
							<div id="phantren1" style="height: 5%">
								<div id="div4_0" style="float: left; margin-top: 10px">
									<input type="image" src="../icon/edit_redo _next.png"
										onclick="hideDivGoogleMap1();" />
								</div>
								<div id="div4_1"
									style="margin-top: 10px; float: left; margin-left: 70px">
									<%=readerLang.getContent("changeLocationTap") %>
								</div>
							</div>
							<div id="clear1" style="clear: both"></div>
							<div id="pp"
								style="height: 89%; background: #dbeef4; border-top: 1px solid gray;">
								<div id="phanduoi1" style="height: 10%; margin-top: 20px">
									<%for(int i=0;i<loadMenu.size();i++){ %>
									<div id="div3<%=i%>"
										style="float: left; font-family: tahoma; font-size: 10px; margin-left: 15px">
										<img title="<%=loadMenu.get(i).getMenuName()%>"
											id="<%=loadMenu.get(i).getMenuId()%>"
											onclick="javascript:getMenuLocationOnClick(<%=i%>,'<%=loadMenu.get(i).getMenuId()%>',0,0,0)"
											height="40px" ;width="40px"
											src="<%=linksaveimage + loadMenu.get(i).getUrlImage()%>">
										<br><%=loadMenu.get(i).getMenuName()%>
									</div>
									<%}%>
								</div>
								<div id="clear1" style="clear: both"></div>
								<div id="phangiua1"
									style="height: 10%; margin-top: 20px; margin-left: 10px">
									<div id="phangiua11" style="float: left"></div>
									<div id="phangiua12" style="float: left; margin-left: 5px">
										<div id="search1">
											<input type="text" id="txtsearch" onkeyup="getKqSearch(0)"
												name="txtsearch" size="30" />
										</div>
										<div id="search2" style="display: none">
											<input type="text" id="txtsearch1" name="txtsearch1"
												size="30" />
										</div>
										<input type="radio" name="group1" id="group1"
											onclick="showText()" value="diadiem"><%=readerLang.getContent("changeLocation") %>
										<input type="radio" name="group1" id="group2" value="timduong"
											onclick="loadMap(0)"><%=readerLang.getContent("changeWay") %>
									</div>
									<div id="phangiua13" style="float: left; margin-left: 10px">
										<input type="image" src="../icon/find-icon.png" value="OK"
											onclick="searchCheck()" />
									</div>
								</div>
								<div id="clear" style="clear: both"></div>
								<div id="phancuoi1">
									<div id="displayTable"></div>
								</div>
							</div>
						</div>
						<div id="div_tab2"
							style="height: 420; width: 30; float: left; border: 0px solid gray; margin-left: 5px; margin-top: 10px; margin-right: 10px; display: none">
							<input type="image" src="../icon/edit_redo_back.png"
								onclick="showDivGoogleMap1();" />
						</div>
					</div>
				</div>
			</div>
			<div class="gird_bottom"></div>
		</div>
	</div>
</div>
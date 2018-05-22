this.count=0;	
	var page=5;
function callAjax()
{

	var name =document.getElementById("txtName").value;
	var addr =document.getElementById("txtAddress").value;
	var url="MapDirection?CMD=1"
			url+="&name="+name
			url+="&addr="+addr
			url+="&t="+Math.random();
			var f=new AjaxGetText(url);			
			f.complet=function(tx)
			{				
				if(tx=="failed"){
					alert("Updated failed");
				}
			}	
}
function initializeOnlick(id,coord_x,coord_y) 
  {
	shiftSelect(id);
	$("group1").checked=true;
  $("txtsearch1").value="";
  showText();
		var coord_x1 ="10.779867";
		var coord_y1="106.686037";
		if(coord_x!=0 && coord_y1!=0){
			coord_x1 =coord_x;
			coord_y1=coord_y;
		}
		
	    var latlng2 = new google.maps.LatLng(coord_x1 ,coord_y1);
	    var myOptions2 =
	    {
	      zoom: 12,
	      center: latlng2,
	      mapTypeId: google.maps.MapTypeId.ROADMAP
	    };
	    
	    var map2 = new google.maps.Map(document.getElementById("map_canvas1"),myOptions2);   
	   setMarkersOnclick(map2,coord_x,coord_y)
	   setFrm(map2);
  
    
  }
  function setMarkersOnclick(map,coord_x,coord_y) {
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
  
      var myLatLng = new google.maps.LatLng(coord_x,coord_y);
    var marker= new google.maps.Marker({
         position: myLatLng,
         map: map,
         shadow: shadow,
         icon: image
     });
 }
	
	
	
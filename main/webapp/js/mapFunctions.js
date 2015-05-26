function gmapAddressSuggistions(data){
	data = data[0];
	cid = data.clientid;
	var id = "map1"+data.idval;
	var str = $("#"+id).parent()[0].id;
	var lastIndex = str.lastIndexOf(":");
	var formID = str.substring(0, lastIndex);
	var directionsService = new google.maps.DirectionsService();
	new google.maps.places.SearchBox(document.getElementById(formID+':txtSource'));
	var source = document.getElementById(formID+':txtSource').value;
	if(source!=""&&source!=null){
		getRoute(data);
	}
}
function getRoute(data){
	var directionsService = new google.maps.DirectionsService();
	var directionsDisplay = new google.maps.DirectionsRenderer({ 'draggable': false });
	var clinicid = data.clientid;
	var idval = data.idval;
	var lat = data.lat;
	var lng = data.lng;
	$("#map1"+idval).css({"display":"none"});
	$("#dvDistance"+clinicid).css({"display":"block"});
	$("#dvMap"+clinicid).css({"display":"block"});
	$("#directionsspan"+clinicid).css({"display":"block"});
    var center = new google.maps.LatLng(lat,lng);
    var mapOptions = {
        zoom: 7,
        center: center
    };
    map = new google.maps.Map(document.getElementById('dvMap'+clinicid), mapOptions);
    directionsDisplay.setMap(map);
    directionsDisplay.setPanel(document.getElementById('dvPanel'+clinicid));

    //*********DIRECTIONS AND ROUTE**********************//
    var id = "map1"+data.idval;
	var str = $("#"+id).parent()[0].id;
	var lastIndex = str.lastIndexOf(":");
	var formID = str.substring(0, lastIndex);
	
    source = document.getElementById(formID+":txtSource").value;
   // destination = document.getElementById(formID+":txtDestination"+clinicid).value;
    destination = document.getElementById(formID+":txtDestination"+clinicid);
    if(destination != null) {
     destination = destination.value;
    var request = {
        origin: source,
        destination: destination,
        travelMode: google.maps.TravelMode.DRIVING
    };
    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
    });

    //*********DISTANCE AND DURATION**********************//
    var service = new google.maps.DistanceMatrixService();
    service.getDistanceMatrix({
        origins: [source],
        destinations: [destination],
        travelMode: google.maps.TravelMode.DRIVING,
        unitSystem: google.maps.UnitSystem.METRIC,
        avoidHighways: false,
        avoidTolls: false
    }, function (response, status) {
        if (status == google.maps.DistanceMatrixStatus.OK && response.rows[0].elements[0].status != "ZERO_RESULTS") {
            var distance = response.rows[0].elements[0].distance.text;
            var duration = response.rows[0].elements[0].duration.text;
            var dvDistance = document.getElementById("dvDistance"+clinicid);
            dvDistance.innerHTML = "";
            dvDistance.innerHTML += "Distance: " + distance + " / ";
            dvDistance.innerHTML += "  Duration:" + duration;
           /* var dvd = document.getElementById("distanced"+clinicid);
            dvd.innerHTML = distance;
*/
        } else {
            alert("Unable to find the distance via road.");
        }
    });
    }
}

function directions(data){
	var clinicid = data.clientid;
	$("#dvPanel"+clinicid).toggle();
	$("#directionshidespan"+clinicid).toggle();
	
}
function directionshides(data){
	var clinicid = data.clinicid;
	$("#dvPanel"+clinicid).css({"display":"none"});
	$("#directionshidespan"+clinicid).css({"display":"none"});
}
function showTab(data){
	data = data[0];
	data1= data[1];
	cid = data.clientid;
	tabindex = data1.index;
	alert(cid+tabindex);
        
	
}


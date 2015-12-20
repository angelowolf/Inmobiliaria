var markers = [];
var myCenter = new google.maps.LatLng(-31.420899517920617, -64.5003890991211);

var mapProp = {
    center: myCenter,
    zoom: 15,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    scrollwheel: false,
    styles: [{featureType: "poi", elementType: "labels", stylers: [{visibility: "off"}]},
        {featureType: "administrative", elementType: "labels", stylers: [{visibility: "off"}]}]
};
var map = new google.maps.Map(document.getElementById("mapa-alta"), mapProp);
function initialize() {

//            var marker = new google.maps.Marker({
//                position: myCenter,
//            });
//
//            marker.setMap(map);
//            markers.push(marker);
}



//        google.maps.event.addDomListener(window, 'load', initialize);
google.maps.event.addListener(map, "click", function (event) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];

    var lat = event.latLng.lat();
    var lng = event.latLng.lng();


    var centroElegido = new google.maps.LatLng(lat, lng);
    var marker = new google.maps.Marker({
        position: centroElegido,
    });
    markers.push(marker);
    marker.setMap(map);

    $("#latitud").val(lat);
    $("#longitud").val(lng);
});


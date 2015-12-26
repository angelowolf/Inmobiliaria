var lat = $("#latitud").val();
var lon = $("#longitud").val();
if (lat == 0) {
    lat = -31.420753028734406;
}
if (lon == 0) {
    lon = -64.49841499328613;
}
var myCenter = new google.maps.LatLng(lat, lon);
var markers = [];
var mapProp = {
    center: myCenter,
    zoom: 16,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    scrollwheel: false,
    styles: [{featureType: "poi", elementType: "labels", stylers: [{visibility: "off"}]},
        {featureType: "administrative", elementType: "labels", stylers: [{visibility: "off"}]}]
};
var map = new google.maps.Map(document.getElementById("mapa-edit-contacto"), mapProp);
function initialize() {



    var marker = new google.maps.Marker({
        position: myCenter,
    });

    marker.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);
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
        icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
    });
//    marker.setIcion('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
    markers.push(marker);
    marker.setMap(map);
    $("#latitud").val(lat);
    $("#longitud").val(lng);
});

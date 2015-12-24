var lat = $("#latitud").val();
var lon = $("#longitud").val();
var myCenter = new google.maps.LatLng(lat, lon);

function initialize() {
    var mapProp = {
        center: myCenter,
        zoom: 16,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        scrollwheel: false
    };
    var map = new google.maps.Map(document.getElementById("mapa-contacto"), mapProp);

    var marker = new google.maps.Marker({
        position: myCenter,
    });

    marker.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);


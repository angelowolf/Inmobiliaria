$(function () {
    initialize();
});

function initialize() {
            
    var latlng = null;

    if ($('#latitud').val() != '0.0') {
        latlng =  new google.maps.LatLng($('#latitud').val(), $('#longitud').val());
    } else {
        latlng = new google.maps.LatLng(-31.420899517920617, -64.5003890991211);
    }
    
    var mapProp = {
            center: latlng,
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            scrollwheel: false,
            styles: [{featureType: "poi", elementType: "labels", stylers: [{visibility: "off"}]},
                {featureType: "administrative", elementType: "labels", stylers: [{visibility: "off"}]}]
        },
        map = new google.maps.Map(document.getElementById("mapa-alta"), mapProp),
        marker = new google.maps.Marker( {position: latlng, map: map} );

    marker.setMap( map );
    
    if ($('#marker').val() != 1) {
        marker.setVisible(false);
    } else {
        marker.setVisible(true);
    }
    
    google.maps.event.addListener(map, "click", function (event) {
        $('#marker').val(1);
        marker.setVisible(true);
        var lat = event.latLng.lat();
        var lng = event.latLng.lng();
        mover(lat, lng, marker );
    });
}

function mover( lat, lng, marker ) {
    marker.setPosition(new google.maps.LatLng(lat, lng));
    $("#latitud").val(lat);
    $("#longitud").val(lng);
}

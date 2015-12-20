<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fieldset>
    <legend>
        Nueva Propiedad
    </legend>
</fieldset>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror theme="bootstrap"/>
<div id="alerta-size" class="alert alert-info"></div>
<div class="form-group col-md-8 col-md-offset-2" >
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form id="form" style="padding-top: 25px;" action="guardar" method="POST" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <%--<s:push value="candidato">--%>
                <s:hidden name="propiedad.idPropiedad"/>
                <s:hidden name="propiedad.latitud" id="latitud" />
                <s:hidden name="propiedad.longitud" id="longitud" />
                
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.nombre" label="Nombre" placeholder="Ingrese el nombre de la propiedad" required="true" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.codigoPropiedad" label="Codigo" placeholder="Ingrese el codigo de la propiedad" required="true" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.habitacion" label="Habitaciones" placeholder="Ingrese la cantidad de habitaciones" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.bano" label="Baños" placeholder="Ingrese la cantidad de baños" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.terreno" label="Terreno" placeholder="Ingrese la superficie del terreno" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.edificado" label="Edificado" placeholder="Ingrese la superficie construida" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textarea name="propiedad.detalle" label="Detalles" placeholder="Ingrese algun detalle" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:checkboxlist label="Servicios" value="serviciosDefault" list="todosServicios" name="serviciosElegidos" listKey="idServicio" listValue="nombre"/>
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:checkboxlist label="Ambientes" value="ambientesDefault" list="todosAmbientes" name="ambientesElegidos" listKey="idAmbiente" listValue="nombre"/>
                </div> 
                <div id="imagenes" class="col-md-8 col-md-offset-2 input">
                    <s:file id="imagen1" name="imagen" label="Imagen 1:" />
                </div>

                <s:submit id="submit" value="Agregar Propiedad" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
                <%--</s:push>--%> 
                <div class="form-group col-md-offset-3">
                    <button id="btnAgregar" class="btn btn-warning ">Agregar Imagen</button>
                </div>
            </s:form>

        </div>
    </div>
    <div id="ubicaccion">
        <h2 class="text-center text-info">Ubicacion</h2>
        <div id="googleMap" style="width:100%;height:500px;"></div>
    </div>

    <script src="http://maps.googleapis.com/maps/api/js"></script>
    <script>
        var markers = [];
        var myCenter = new google.maps.LatLng(-31.420899517920617, -64.5003890991211);

        var mapProp = {
            center: myCenter,
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            scrollwheel: false,
            styles: [{ featureType: "poi", elementType: "labels", stylers: [{ visibility: "off" }]},
                     { featureType: "administrative", elementType: "labels", stylers: [{ visibility: "off" }]}]
        };
        var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
        function initialize() {

            var marker = new google.maps.Marker({
                position: myCenter,
            });

            marker.setMap(map);
            markers.push(marker);
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
            });
            markers.push(marker);
            marker.setMap(map);
            
            $("#latitud").val(lat);
            $("#longitud").val(lng);
        });
        
        

    </script>
</div>

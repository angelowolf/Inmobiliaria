<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style type="text/css">
    #wrap {
        padding:2em;
    }
    .panel.panel-horizontal {
        display:table;
        width:100%;
    }
    .panel.panel-horizontal > .panel-heading, .panel.panel-horizontal > .panel-body, .panel.panel-horizontal > .panel-footer {
        display:table-cell;
    }
    .panel.panel-horizontal > .panel-heading {
        /*width: 25%;*/
        border:0;
        vertical-align: middle;
    }.panel.panel-horizontal > .panel-body {
        /*width: 25%;*/
        border:0;
        vertical-align: middle;
    }
    .panel.panel-horizontal > .panel-footer{
        width: 40%;
        border:0;
        vertical-align: middle;
    }
    .panel.panel-horizontal > .panel-heading {
        border-right: 1px solid #ddd;
        border-top-right-radius: 0;
        border-bottom-left-radius: 4px;
    }
    .panel.panel-horizontal > .panel-body {
        border-right: 1px solid #ddd;
        /*border-top-right-radius: 0;*/
        border-bottom-left-radius: 4px;
    }
    .panel.panel-horizontal > .panel-footer {
        /*border-left: 1px solid #ddd;*/
        /*border-top-left-radius: 0;*/
        /*border-bottom-right-radius: 4px;*/
        border-radius: 0px 4px 4px 0px;

    }
    .panel-body.panel-color{
        color:white;
        background: #2EA3E7;
    }
    .panel-footer.panel-color{
        width: 40%;
        color:white;
        background: #2EA3E7;
    }
</style>
<div class="col-md-12">   
    <div id="wrap" class="col-md-8">
        <h1 class="text-center text-primary"><s:property value="propiedad.nombre"/></h1>
        <div class="panel panel-primary panel-horizontal">
            <div class="panel-body">
                <i class="fa fa-home fa-1x"></i>
                Semipiso      
            </div>
            <div class="panel-body">
                <i class="glyphicon glyphicon-fullscreen fa-1x"></i>
                <s:property value="propiedad.terreno"/>m<sup>2</sup>
            </div>           
            <div class="panel-body">
                <i class="icon-bathrooms"></i>
                <s:property value="propiedad.terreno"/> baño
            </div>           
            <div class="panel-body">
                <i class="glyphicon glyphicon-resize-small fa-1x"></i>
                <s:property value="propiedad.edificado"/>m<sup>2</sup>
            </div>
            <div class="panel-body">
                <i class="fa fa-bed fa-1x"></i>
                <s:property value="propiedad.habitacion"/>
            </div>
            <div class="panel-footer panel-color">
                Codigo: <s:property value="propiedad.codigoPropiedad"/>
            </div>
        </div>            
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <s:iterator value="propiedad.imagenes" var="imagen" status="stat">
                    <s:if test="#stat.index == 0">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        </s:if>
                        <s:else>
                        <li data-target="#myCarousel" data-slide-to="<s:property value="#stat.index"/>"></li>
                        </s:else>
                    </s:iterator>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <s:iterator value="propiedad.imagenes" var="imagen" status="stat">
                    <s:if test="#stat.index == 0">
                        <div class="item active">
                            <img src="/Inmobiliaria/Imagen?idImagen=<s:url value="%{#imagen.idImagenPropiedad}"/>" style="height: 100%;width: 100%;"/>
                        </div></s:if>
                    <s:else>
                        <div class="item">
                            <img src="/Inmobiliaria/Imagen?idImagen=<s:url value="%{#imagen.idImagenPropiedad}"/>" style="height: 100%;width: 100%;"/>
                        </div> </s:else>
                </s:iterator>          
            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </a>
        </div>
        <div id="detalles">
            <h2 class="text-center text-info">Detalles</h2>
            <p><s:property value="propiedad.detalle"/></p>
        </div>
        <div id="ubicaccion">
            <h2 class="text-center text-info">Ubicacion</h2>
            <div id="googleMap" style="width:100%;height:500px;"></div>
        </div>
    </div>
    <div class="col-md-4">
        <div id="servicios" style="padding-top: 150px">
            <h3 class="text-center text-info">Servicios</h3>
            <s:if test="serviciosUno.isEmpty">
                <div class="text-center">
                    Sin datos
                </div>
            </s:if>
            <div class="col-md-6">
                <s:iterator value="serviciosUno" var="servicio" status="stat">                  
                    <div class="panel panel-default panel-horizontal">
                        <div class="panel-body">
                            <i class="fa fa-check fa-1x" style="color: green"></i><span >   <s:property value="#servicio.nombre"/></span>
                        </div>                       
                    </div>              
                </s:iterator>
            </div>
            <div class="col-md-6">
                <s:iterator value="serviciosDos" var="servicio" >
                    <div class="panel panel-default panel-horizontal">
                        <div class="panel-body">
                            <i class="fa fa-check fa-1x" style="color: green"></i><span >   <s:property value="#servicio.nombre"/></span>
                        </div>                       
                    </div>              
                </s:iterator>
            </div>
        </div>
        <div id="ambientes">
            <div class="col-md-12">
                <h3 class="text-center text-info">Ambientes</h3>    
            </div>            
            <s:if test="ambientesUno.isEmpty">
                <div class="text-center">
                    Sin datos
                </div>
            </s:if>
            <div class="col-md-6">               
                <s:iterator value="ambientesUno" var="ambiente" >
                    <div class="panel panel-default panel-horizontal">
                        <div class="panel-body">
                            <i class="fa fa-check fa-1x" style="color: green"></i><span >   <s:property value="#ambiente.nombre"/></span>
                        </div>                       
                    </div>              
                </s:iterator>
            </div>
            <div class="col-md-6">
                <s:iterator value="ambientesDos" var="ambiente" >
                    <div class="panel panel-default panel-horizontal">
                        <div class="panel-body">
                            <i class="fa fa-check fa-1x" style="color: green"></i><span >   <s:property value="#ambiente.nombre"/></span>
                        </div>                       
                    </div>              
                </s:iterator>
            </div>

        </div>
        <div class="col-md-12" id="formulario-consulta" style="background: #F1F3F6; padding-bottom: 50px;border-radius: 4px;">
            <h3 class="text-center" style="color: black; padding-bottom: 25px; padding-top: 40px">Contacto</h3>
            <s:form action="" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal ">
                <div class="col-md-10 col-md-offset-2 ">
                    <s:textfield name="nombre" placeholder="Nombre" cssClass="form-control" />
                </div>
                <div class="col-md-10 col-md-offset-2 ">
                    <s:textfield name="email" placeholder="Email" cssClass="form-control" />
                </div>
                <div class="col-md-10 col-md-offset-2 ">
                    <s:textfield name="telefono" placeholder="Teléfono" cssClass="form-control" />
                </div>
                <div class="col-md-10 col-md-offset-2 ">
                    <s:textarea name="consulta" placeholder="Mensaje" cssClass="form-control" />
                </div>
                <div class="col-md-offset-4 ">
                    <s:submit value="Enviar Consulta" cssClass="btn" style="border-color:#adb2b6;"/>
                </div>
            </s:form>
        </div>
    </div>


    <script src="http://maps.googleapis.com/maps/api/js"></script>
    <script>

        var myCenter = new google.maps.LatLng(51.508742, -0.120850);

        function initialize() {
            var mapProp = {
                center: myCenter,
                zoom: 15,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                scrollwheel: false
            };
            var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

            var marker = new google.maps.Marker({
                position: myCenter,
            });

            marker.setMap(map);
        }

        google.maps.event.addDomListener(window, 'load', initialize);


    </script>
</div>
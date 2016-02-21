<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style type="text/css">

</style>
<s:hidden name="propiedad.latitud" id="latitud" />
<s:hidden name="propiedad.longitud" id="longitud" />
<div class="col-md-12">   
    <div id="wrap" class="col-md-8">
        <h1 class="text-center text-primary"><s:property value="propiedad.direccion"/></h1>
        <div class="panel panel-primary panel-horizontal hidden-xs">
            <div class="panel-body">
                <i class="fa fa-home fa-1x"></i>
                <s:property value="propiedad.tipoPropiedad.nombre"/>
            </div>
            <div class="panel-body">
                <i class="glyphicon glyphicon-fullscreen fa-1x"></i>
                <s:property value="propiedad.terreno"/>m<sup>2</sup>
            </div>           
            <div class="panel-body">
                <i class="glyphicon glyphicon-resize-small fa-1x"></i>
                <s:property value="propiedad.edificado"/>m<sup>2</sup>
            </div>
            <div class="panel-body">
                <i class="fa fa-bed fa-1x"></i>
                <s:property value="propiedad.habitacion"/>
            </div>
            <div class="panel-body">
                <i class="icon-bathrooms"></i>
                <s:property value="propiedad.bano"/>
            </div>           
            <div class="panel-footer panel-color">
                Codigo: <s:property value="propiedad.codigoPropiedad"/>
            </div>
        </div>       
        <div class="panel panel-primary panel-horizontal hidden-sm hidden-md hidden-lg">
            <div class="panel-body">
                <i class="fa fa-home fa-1x"></i>
                <s:property value="propiedad.tipoPropiedad.nombre"/>
            </div>
            <div class="panel-body">
                <i class="glyphicon glyphicon-fullscreen fa-1x"></i>
                <s:property value="propiedad.terreno"/>m<sup>2</sup>
            </div>           
            <div class="panel-body">
                <i class="glyphicon glyphicon-resize-small fa-1x"></i>
                <s:property value="propiedad.edificado"/>m<sup>2</sup>
            </div>
        </div>  
        <div class="panel panel-primary panel-horizontal hidden-sm hidden-md hidden-lg">
            <div class="panel-body">
                <i class="fa fa-bed fa-1x"></i>
                <s:property value="propiedad.habitacion"/>
            </div>
            <div class="panel-body">
                <i class="icon-bathrooms"></i>
                <s:property value="propiedad.bano"/>
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
            <div class="price">
                <!--               Venta-->
                <strong><span><s:property value="propiedad.tipoMoneda.sigla"/> <s:property value="propiedad.precio"/></span></strong>
            </div>
            <div class="carousel-inner" role="listbox" >

                <s:iterator value="propiedad.imagenes" var="imagen" status="stat">
                    <s:if test="#stat.index == 0">
                        <div class="item active">
                            <img src="/Imagen?idImagen=<s:url value="%{#imagen.idImagenPropiedad}"/>"/>
                        </div></s:if>
                    <s:else>
                        <div class="item">
                            <img src="/Imagen?idImagen=<s:url value="%{#imagen.idImagenPropiedad}"/>"/>
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
        <s:if test="!propiedad.detalle.isEmpty()">
            <div id="detalles">
                <h2 class="text-center text-info">Detalles</h2>
                <p><s:property value="propiedad.detalle"/></p>
            </div>
        </s:if>       
        <div id="ubicaccion">
            <h2 class="text-center text-info">Ubicacion</h2>
            <div id="mapa-vista" style="width:100%;height:500px;"></div>
        </div>
    </div>
    <div class="col-md-4" style="padding-top: 25px">  
        <s:if test="!serviciosUno.isEmpty()">        
            <div id="servicios">
                <h3 class="text-center text-info col-md-12">Servicios</h3>
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
        </s:if> 
        <s:if test="!ambientesUno.isEmpty()">
            <div id="ambientes">
                <h3 class="text-center text-info col-md-12">Ambientes</h3>    
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
        </s:if>
        <div class="text-center col-md-12">
            <s:actionerror theme="bootstrap"/>
            <s:actionmessage theme="bootstrap"/>
        </div>
        <div class="col-md-12" id="formulario-consulta" style="background: #F1F3F6; padding-top:15px; padding-bottom: 15px;border-radius: 4px; margin-top: 50px;">
            <div id="alerta-mail" class="alert text-center" style="display: none; margin-bottom: 0;"></div>
            <div id="contenedor-contacto">
                <h3 class="text-center" style="color: black; padding-bottom: 25px; padding-top: 10px">Contacto</h3>
                <form id="form-contact" validate="true" enctype="multipart/form-data" theme="simple">
                    <s:hidden name="propiedad.idPropiedad" id="idpropiedad" />
                    <div class="form-group">
                        <input type="text" name="nombre" id="nombre" placeholder="Nombre" class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <input type="email"  name="email" id="email" placeholder="Email" class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <input type="tel"  name="telefono" id="telefono" placeholder="Teléfono" class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <textarea name="consulta" placeholder="Mensaje" id="consulta" class="form-control" required></textarea>
                    </div>
                    <div class="form-group text-center">
                        <button class="btn" style="border-color:#adb2b6;" >Enviar Consulta</button>
                    </div>
                </form>
            </div>
            <div class="text-center loading" style="display:none;">
                 <i class=" fa fa-circle-o-notch fa-spin"></i>
            </div>
        </div>
    </div>

</div>
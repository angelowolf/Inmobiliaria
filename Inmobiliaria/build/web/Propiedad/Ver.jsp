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
        <div class="panel panel-primary panel-horizontal">
            <div class="panel-body">
                <i class="fa fa-home fa-1x"></i>
                <s:property value="propiedad.tipoPropiedad.nombre"/>
            </div>
            <div class="panel-body">
                <i class="glyphicon glyphicon-fullscreen fa-1x"></i>
                <s:property value="propiedad.terreno"/>m<sup>2</sup>
            </div>           
            <div class="panel-body">
                <i class="icon-bathrooms"></i>
                <s:property value="propiedad.bano"/>
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
        <s:if test="propiedad.detalle.lenght != 0">
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
    <div class="col-md-4">
        <div id="servicios" style="padding-top: 150px">
            <s:if test="!serviciosUno.isEmpty">               
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
            </s:if>
        </div>
        <div id="ambientes">          
            <s:if test="!ambientesUno.isEmpty">
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
            </s:if>
        </div>
        <div class="col-md-12" id="formulario-consulta" style="background: #F1F3F6; padding-bottom: 50px;border-radius: 4px;">
            <h3 class="text-center" style="color: black; padding-bottom: 25px; padding-top: 40px">Contacto</h3>
            <s:form action="mandarEmail" validate="true" enctype="multipart/form-data" theme="simple">
                <s:hidden value="propiedad.idPropiedad" name="idPropiedad"/>
                <div class="form-group">
                    <s:textfield type="text" name="nombre" placeholder="Nombre" class="form-control" />
                </div>
                <div class="form-group">
                    <s:textfield type="text"  name="email" placeholder="Email" class="form-control" />
                </div>
                <div class="form-group">
                    <s:textfield type="text"  name="telefono" placeholder="Teléfono" class="form-control" />
                </div>
                <div class="form-group">
                    <s:textfield type="text"  name="consulta" placeholder="Mensaje" class="form-control" />
                </div>
                <div class="form-group text-center">
                    <s:submit value="Enviar Consulta" cssClass="btn" style="border-color:#adb2b6;" disabled="true"/>
                </div>
            </s:form>
        </div>
    </div>

</div>

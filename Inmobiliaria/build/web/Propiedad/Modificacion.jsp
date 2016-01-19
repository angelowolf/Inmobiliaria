<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style type="text/css">
    .form-inline .form-group{
        margin-left: 0;
        /*margin-right: 0;*/
    }
    .input{
        padding-top: 10px;
    }
</style>
<fieldset>
    <legend>
        Modificar Propiedad
    </legend>
</fieldset>
<div class="text-center">
    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>
    <div id="alerta-size" class="alert alert-info"></div>
</div>
<div class="form-group col-md-8 col-md-offset-2">   
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form action="modificar" method="POST" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <s:hidden name="propiedad.idPropiedad"/>
                <s:hidden name="propiedad.latitud" id="latitud" />
                <s:hidden name="propiedad.longitud" id="longitud" />
                <s:hidden name="propiedad.oportunidad"/>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.direccion" label="Direccion" placeholder="Ingrese la direccion de la propiedad" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.codigoPropiedad" label="Codigo" placeholder="Ingrese el codigo de la propiedad" />
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
                    <s:select value="propiedad.tipoPropiedad.idTipoPropiedad" label="Tipo Propiedad" name="propiedad.tipoPropiedad.idTipoPropiedad" headerKey="-1" headerValue="Seleccione" list="tipoPropiedadLista" listKey="idTipoPropiedad" listValue="nombre"/>
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.precio" id="precio" label="Precio" placeholder="Ingrese el precio" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:select value="propiedad.tipoMoneda.idTipoMoneda" label="Moneda" name="propiedad.tipoMoneda.idTipoMoneda" headerKey="-1" headerValue="Seleccione" list="tipoMonedaLista" listKey="idTipoMoneda" listValue="sigla"/>
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:checkboxlist label="Servicios" value="serviciosDefault" list="todosServicios" name="serviciosElegidos" listKey="idServicio" listValue="nombre"/>
                </div> 
                <div class="col-md-8 col-md-offset-2 input">
                    <s:checkboxlist label="Ambientes" value="ambientesDefault" list="todosAmbientes" name="ambientesElegidos" listKey="idAmbiente" listValue="nombre"/>
                </div> 
                <div class="col-md-12">
                    <h3 class="text-center">Imagenes Guardadas</h3>
                    <div id="contenedor-checks">
                        <s:iterator value="todosImagenes" var="imagen" status="stat">
                            <div class="col-md-4 col-sm-6">
                                <div class="form-group">
                                    <div class="elems">
                                        <img width="140" height="79" src="/Imagen?idImagen=<s:url value="%{#imagen.idImagenPropiedad}"/>" />
                                        <s:checkbox name="imagenesElegidos" value="true" fieldValue="%{idImagenPropiedad}" id="%{#stat.count}" theme="simple">
                                        </s:checkbox> Guardar
                                    </div>
                                </div>
                            </div>
                        </s:iterator>
                    </div>
                </div>
                <div class="col-md-8 col-md-offset-2" id="imagenes">
                    <h3 class="text-center" style="padding-bottom: 15px;">Imagenes Nuevas</h3>
                    <s:file multiple="true" name="imagen"  label="Imagenes"/>
                </div>             
                <div id="ubicaccion">
                    <h2 class="col-md-12 text-center text-info">Ubicacion</h2>
                    <div id="mapa-edit" style="width:100%;height:500px;"></div>
                </div>    
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
            </s:form>
        </div>
    </div>
</div>

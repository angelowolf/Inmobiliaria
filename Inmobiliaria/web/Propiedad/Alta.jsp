<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fieldset>
    <legend>
        Nueva Propiedad
    </legend>
</fieldset>
<div class="text-center">
    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>
    <div id="alerta-size" class="alert alert-info"></div>
</div>
<div class="form-group col-md-8 col-md-offset-2" >
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form id="form" style="padding-top: 25px;" action="guardar" method="POST" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <%--<s:push value="candidato">--%>
                <s:hidden name="propiedad.idPropiedad"/>
                <s:hidden name="propiedad.latitud" id="latitud" />
                <s:hidden name="propiedad.longitud" id="longitud" />

                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.direccion" label="Direccion" placeholder="Ingrese la direccion de la propiedad" required="true" />
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
                <div id="imagenes" class="col-md-8 col-md-offset-2 input">
                    <s:file id="imagen1" name="imagen" label="Imagen 1:" />
                </div>
                <div class="col-md-3 col-md-offset-3">
                    <button id="btnAgregar" class="btn btn-warning ">Agregar Imagen</button>
                </div>
                <s:submit id="submit" value="Agregar Propiedad" cssClass="btn btn-success col-md-offset-9 col-md-3"/>             

            </s:form>

        </div>
    </div>
    <div id="ubicaccion">
        <h2 class="text-center text-info">Ubicacion</h2>
        <div id="mapa-alta" style="width:100%;height:500px;"></div>
    </div>    
</div>

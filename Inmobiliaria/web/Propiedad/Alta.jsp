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
<div class="form-group col-md-8 col-md-offset-2" >  
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form id="form" style="padding-top: 25px;" action="guardar" method="POST" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <%--<s:push value="candidato">--%>
                <s:hidden name="propiedad.idPropiedad"/>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.nombre" label="Nombre" placeholder="Ingrese el nombre de la propiedad" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.codigoPropiedad" label="Codigo" placeholder="Ingrese el codigo de la propiedad" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.habitacion" label="Habitaciones" placeholder="Ingrese la cantidad de habitaciones" />
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
                <s:submit value="Agregar Propiedad" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
                <%--</s:push>--%> 
                <div class="form-group col-md-offset-3">
                    <button id="btnAgregar" class="btn btn-warning ">Agregar Imagen</button>
                </div>
            </s:form>
           
        </div>
    </div>
</div>

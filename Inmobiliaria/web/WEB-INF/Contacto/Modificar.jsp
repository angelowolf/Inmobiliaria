<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="text-center">
    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>
</div>
<fieldset>   
    <legend>
        Formulario Contacto
    </legend>
</fieldset>
<div class="form-group col-md-8 col-md-offset-2">       
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form  style="padding-top: 25px;" action="guardarOModificar" validate="true"  theme="bootstrap" cssClass="form-horizontal">
                <s:hidden name="contacto.idContacto"/>
                <s:hidden name="contacto.latitud" id="latitud"/>
                <s:hidden name="contacto.longitud" id="longitud"/>
                <s:hidden name="marker" id="marker"/>
                <div class="col-md-8 col-md-offset-2">

                    <s:textfield name="contacto.nombre" label="Nombre" placeholder="Ingrese el nombre de la inmobiliaria" />
                    <s:textfield name="contacto.direccion" label="Dirección" placeholder="Ingrese la dirección" />

                    <s:textfield name="contacto.telefono" label="Teléfono" placeholder="Ingrese el teléfono" />

                    <s:textfield name="contacto.email" label="EMail" placeholder="Ingrese el email" />
                </div>
                <div id="ubicaccion">
                    <h2 class="text-center text-info col-md-12">Ubicacion</h2>
                    <div id="mapa-edit-contacto" style="width:100%;height:500px;"></div>
                </div>    
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
            </s:form>
        </div>
    </div>
</div>
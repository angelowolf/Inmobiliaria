<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fieldset>   
    <legend>
        Formulario Contacto
    </legend>
</fieldset>
<div class="text-center">
    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>
    <div id="alerta-size" class="alert alert-info"></div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">   
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form  style="padding-top: 25px;" action="guardarOModificar" validate="true"  theme="bootstrap" cssClass="form-horizontal">
                <s:hidden name="contacto.idContacto"/>
                <s:hidden name="contacto.latitud" id="latitud"/>
                <s:hidden name="contacto.longitud" id="longitud"/>
                <div class="col-md-8 col-md-offset-2">
                    <s:textfield name="contacto.direccion" label="Dirección" placeholder="Ingrese la dirección" />
                </div>
                <div class="col-md-8 col-md-offset-2">
                    <s:textfield name="contacto.telefono" label="Teléfono" placeholder="Ingrese el teléfono" />
                </div>
                <div class="col-md-8 col-md-offset-2">
                    <s:textfield name="contacto.email" label="EMail" placeholder="Ingrese el email" />
                </div>
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
            </s:form>
            <div id="ubicaccion">
                <h2 class="text-center text-info">Ubicacion</h2>
                <div id="mapa-edit-contacto" style="width:100%;height:500px;"></div>
            </div>    
        </div>
    </div>
</div>
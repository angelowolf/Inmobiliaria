<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:hidden name="#application.contacto.latitud" id="latitud" />
<s:hidden name="#application.contacto.longitud" id="longitud" />
<div id="margen">
    <fieldset>
        <legend>
            CONTACTO
        </legend>
    </fieldset>
    <div class="col-md-8">
        <p class="lead">
            <strong>Inmobiliaria Patioto Feo | </strong><s:property value="#application.contacto.direccion"/>
            <br><s:property value="#application.contacto.email"/>
            <br><s:property value="#application.contacto.telefono"/>
        </p>
        <div id="ubicaccion">
            <h2 class="text-center text-info">Ubicacion</h2>
            <div id="mapa-contacto" style="width:100%;height:500px;"></div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="col-md-12" id="formulario-consulta" style="background: #F1F3F6; padding-bottom: 50px;border-radius: 4px;">
            <h3 class="text-center" style="color: black; padding-bottom: 25px; padding-top: 40px">Contacto</h3>
            <s:form action="asd" validate="true" enctype="multipart/form-data" theme="bootstrap">
                <div class="form-group">
                    <input type="text" name="nombre" placeholder="Nombre" class="form-control" />
                </div>
                <div class="form-group">
                    <input type="text"  name="email" placeholder="Email" class="form-control" />
                </div>
                <div class="form-group">
                    <input type="text"  name="telefono" placeholder="Teléfono" class="form-control" />
                </div>
                <div class="form-group">
                    <input type="text"  name="consulta" placeholder="Mensaje" class="form-control" />
                </div>
                <div class="form-group text-center">
                    <s:submit value="Enviar Consulta" cssClass="btn" style="border-color:#adb2b6;" disabled="true"/>
                </div>
            </s:form>
        </div>
    </div>
</div>

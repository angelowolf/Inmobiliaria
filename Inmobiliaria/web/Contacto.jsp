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
            <strong><s:property value="#application.contacto.nombre"/> | </strong><s:property value="#application.contacto.direccion"/>
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
            <div class="text-center col-md-12" style="padding-top: 20px;">
                <s:actionerror theme="bootstrap"/>
                <s:actionmessage theme="bootstrap"/>
            </div>
            <h3 class="text-center" style="color: black; padding-bottom: 25px; padding-top: 40px">Contacto</h3>
            <s:form action="mandarEmail" validate="true" enctype="multipart/form-data" theme="simple">
                <div class="form-group">
                    <input type="text" name="nombre" placeholder="Nombre" class="form-control" required="true"/>
                </div>
                <div class="form-group">
                    <input type="text"  name="email" placeholder="Email" class="form-control" required="true"/>
                </div>
                <div class="form-group">
                    <input type="text"  name="telefono" placeholder="Teléfono" class="form-control" required="true"/>
                </div>
                <div class="form-group">
                    <textarea name="consulta" placeholder="Mensaje" class="form-control" required="true"></textarea>
                </div>
                <div class="form-group text-center">
                    <s:submit value="Enviar Consulta" cssClass="btn" style="border-color:#adb2b6;" />
                </div>
            </s:form>
        </div>
    </div>
</div>

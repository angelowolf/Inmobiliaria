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
        <div class="col-md-12" id="formulario-consulta" style="background: #F1F3F6; padding-top:15px; padding-bottom: 15px;border-radius: 4px; margin-top: 50px;">
            <div id="alerta-mail" class="alert text-center" style="display: none; margin-bottom: 0;"></div>
            <div id="contenedor-contacto">
                <h3 class="text-center" style="color: black; padding-bottom: 25px; padding-top: 10px">Contacto</h3>
                <form id="form-contact" validate="true" enctype="multipart/form-data" theme="simple">
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

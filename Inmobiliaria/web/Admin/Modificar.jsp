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
        Modificar Cuenta
    </legend>
</fieldset>
<div class="form-group col-md-6 col-md-offset-2">   
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form  style="padding-top: 25px;" action="modificar" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <%--<s:push value="candidato">--%>
                <s:hidden name="usuario.idUsuario"/>
                <div class="col-md-8 col-md-offset-2">
                    <s:textfield name="#session.user.nombre" label="Nombre" placeholder="Ingrese su nombre" />
                    <s:textfield name="#session.user.apellido" label="Apellido" placeholder="Ingrese su apellido" />
                    <s:textfield name="#session.user.email" label="Email" placeholder="Ingrese su email" />
                    <s:textfield name="#session.user.nick" label="Usuario" placeholder="Ingrese un nombre de usuario" />
                    <s:textfield name="clave1" label="Contraseña" placeholder="Ingrese una contraseña" />
                    <s:textfield name="clave2" label="Contraseña" placeholder="Repita la contraseña" />
                </div>
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
                <%--</s:push>--%>            
            </s:form>
        </div>
    </div>
</div>
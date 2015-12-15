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
<div class="form-group col-md-6 col-md-offset-2">  

    <s:form action="guardar" method="POST" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
        <%--<s:push value="candidato">--%>
        <s:hidden name="propiedad.idPropiedad"/>
        <s:textfield name="propiedad.nombre" label="Nombre" placeholder="Ingrese el nombre de la propiedad" />
        <s:textfield name="propiedad.codigoPropiedad" label="Codigo" placeholder="Ingrese el codigo de la propiedad" />
        <label>Servicios</label>
        <s:checkboxlist list="todosServicios" name="serviciosElegidos" listKey="idServicio" listValue="nombre"/>
        <s:label for="imagen1" value="Imagen 1: " />
        <s:file id="imagen1" name="imagen" /> <br /> 
        <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
        <%--</s:push>--%>            
    </s:form>
    <button id="btnAgregar">Agregar Elemento</button>
</div>

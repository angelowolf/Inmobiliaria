<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fieldset>
    <legend>
        Modificar Propiedad
    </legend>
</fieldset>
<div class="form-group col-md-6 col-md-offset-2">   
    <s:form action="modificar" method="POST" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
        <%--<s:push value="candidato">--%>
        <s:hidden name="propiedad.idPropiedad"/>
        <s:textfield name="propiedad.nombre" label="Nombre" placeholder="Ingrese el nombre de la propiedad" />

        <label>Servicios</label>
        <%--<s:checkboxlist value="serviciosDefault.idServicio" list="servicioLista" name="serviciosElegidos" listKey="idServicio" listValue="nombre"/>--%>
        <s:checkboxlist value="serviciosDefault" list="todos" name="serviciosElegidos" />

        <s:textfield name="propiedad.codigoPropiedad" label="Codigo" placeholder="Ingrese el codigo de la propiedad" />
        <div class="form-group"></div>
        <%--<s:file name="imagen" label="Imagen"/>--%>
        <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
        <%--</s:push>--%>            
    </s:form>
</div>
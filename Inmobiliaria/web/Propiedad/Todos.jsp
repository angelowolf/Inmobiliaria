<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<style type="text/css">
    .table > tbody > tr > td {
        vertical-align: middle;
    }
</style>
<div class="form-group">
    <s:actionmessage theme="bootstrap"/>
    <s:actionerror theme="bootstrap"/>
    <fieldset>
        <legend>
            Propiedades
        </legend>
    </fieldset>  
    <div class=" col-md-12">
        <display:table name="propiedadLista" pagesize="10" requestURI="${listar}" uid="row">
            <display:column property="idPropiedad" title="Id Propiedad"/>   
            <display:column title="Imagen"><img width="140" height="79" src="/Inmobiliaria/Imagen?idImagen=<s:url value="%{#attr.row.imagenDefault.idImagenPropiedad}"/>" /></display:column>
            <display:column sortable="true" property="codigoPropiedad" title="Codigo"/>
            <display:column sortable="true" property="nombre" title="Nombre"/>       
            <display:column sortable="true" property="habitacion" title="Habitaciones"/>       
            <display:column sortable="true" property="terreno" title="Terreno"/>       
            <display:column sortable="true" property="edificado" title="Edificado"/>       
            <display:column title="Funciones">
                <s:form action="editar" id="editURL" style="display:inline-block;">
                    <s:hidden name="idPropiedad" value="%{#attr.row.idPropiedad}"/>
                    <s:submit value="Editar" action="%{editURL}" cssClass="btn btn-info"/>
                </s:form>
                <s:form action="eliminar" id="deleteURL" style="display:inline-block;">
                    <s:hidden name="idPropiedad" value="%{#attr.row.idPropiedad}"/>
                    <s:submit value="Eliminar" action="%{deleteURL}" cssClass="btn btn-danger"/>
                </s:form>
                <s:form action="ver" method="get" id="verURL" style="display:inline-block;">
                    <s:hidden name="idPropiedad" value="%{#attr.row.idPropiedad}"/>
                    <s:submit value="Ver" action="%{verURL}" cssClass="btn btn-warning"/>
                </s:form>
            </display:column>
        </display:table>

    </div>
</div>
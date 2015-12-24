<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<div class="form-group">
    <div class="text-center">
        <s:actionerror theme="bootstrap"/>
        <s:actionmessage theme="bootstrap"/>
        <s:fielderror theme="bootstrap"/>
    </div>
    <fieldset>
        <legend>
            Destacados
            <div class="pull-right">
                <s:form action="nuevo" >
                    <s:submit value="Nuevo" cssClass="btn btn-info"/>
                </s:form>
            </div>
        </legend>
    </fieldset>  
    <div class=" col-md-12">
        <display:table name="destacadosLista" pagesize="10" requestURI="${listar}" uid="row">
            <display:column property="idDestacado" title="Numero Destacado"/>
            <display:column title="Imagen"><img width="140" height="79" src="/Inmobiliaria/Imagen?idImagenDestacado=<s:url value="%{#attr.row.imagen.idImagen}"/>" /></display:column>
            <display:column sortable="true" property="nombre" title="Título"/>
            <display:column title="Funciones">
                <s:form action="editar" id="editURL" style="display:inline-block;">
                    <s:hidden name="idDestacado" value="%{#attr.row.idDestacado}"/>
                    <s:submit value="Editar" action="%{editURL}" cssClass="btn btn-info"/>
                </s:form>
                <s:form action="eliminar" id="deleteURL" style="display:inline-block;">
                    <s:hidden name="idDestacado" value="%{#attr.row.idDestacado}"/>
                    <s:submit value="Eliminar" action="%{deleteURL}" cssClass="btn btn-danger"/>
                </s:form>
            </display:column>
        </display:table>

    </div>
</div>
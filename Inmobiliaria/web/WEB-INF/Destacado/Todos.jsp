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
            <display:setProperty name="basic.msg.empty_list" >Aún no hay propiedades destacadas registradas. Puede agregar una desde <a href="<s:url action='nuevo' namespace='/Destacado'/>">aquí</a>.</display:setProperty>
            <display:column property="idDestacado" title="Numero Destacado"/>
            <display:column title="Imagen"><img width="140" height="79" src="/Imagen?idImagenDestacado=<s:url value="%{#attr.row.imagen.idImagen}"/>" /></display:column>
            <display:column sortable="true" property="nombre" title="Título"/>
            <display:column title="Funciones">
                <s:form action="editar"  style="display:inline-block;">
                    <s:hidden name="idDestacado" value="%{#attr.row.idDestacado}"/>
                    <button value="Editar" title="Editar" class="btn btn-info"><i class='fa fa-pencil-square-o'></i></button>
                    </s:form>
                    <s:submit type="button" data-id="%{#attr.row.idDestacado}" title="Eliminar" theme="simple" cssClass="btn btn-danger" data-toggle="modal" data-target="#modal-delete"><i class='fa fa-trash'></i></s:submit>
            </display:column>
        </display:table>

    </div>
</div>
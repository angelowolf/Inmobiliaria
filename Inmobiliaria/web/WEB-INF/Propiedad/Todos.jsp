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
            Propiedades   
            <div class="pull-right">
                <s:form action="nuevo" >
                    <s:submit value="Nuevo" cssClass="btn btn-info"/>
                </s:form>
            </div>
        </legend>

    </fieldset>  
    <div class=" col-md-12">
        <display:table name="propiedadLista" pagesize="10" requestURI="${listar}" uid="row">
            <display:setProperty name="basic.msg.empty_list" >Aún no hay propiedades registradas. Puede crear una desde <a href="<s:url action='nuevo' namespace='/Propiedad'/>">aquí</a>.</display:setProperty>
            <display:column property="idPropiedad" title="Id Propiedad"/>
            <display:column title="Imagen"><img width="140" height="79" src="/Imagen?idImagen=<s:url value="%{#attr.row.imagenDefault.idImagenPropiedad}"/>" /></display:column>
            <display:column sortable="true" property="codigoPropiedad" title="Codigo"/>
            <display:column sortable="true" property="direccion" title="Direccion"/>       
            <display:column sortable="true" property="habitacion" title="Habitaciones"/>       
            <display:column sortable="true" property="terreno" title="Terreno"/>       
            <display:column sortable="true" property="edificado" title="Edificado"/>       
            <display:column sortable="true" title="Oportunidad">
                <s:if test="%{#attr.row.oportunidad}">
                    <input type="checkbox" checked="checked" disabled="true"/>
                </s:if>
                <s:else>
                    <input type="checkbox" disabled="true"/>
                </s:else>
            </display:column>
            <display:column title="Funciones">
                <s:form action="ver" method="get"  style="display:inline-block;">
                    <s:hidden name="idPropiedad" value="%{#attr.row.idPropiedad}"/>
                    <button value="Ver" title="Ver propiedad" class="btn btn-warning"><i class='fa fa-search'></i></button>
                    </s:form>
                    <s:form action="editar"  style="display:inline-block;">
                        <s:hidden name="idPropiedad" value="%{#attr.row.idPropiedad}"/>
                    <button value="Editar" title="Editar" class="btn btn-info"><i class='fa fa-pencil-square-o'></i></button>
                    </s:form>
                    <s:form action="oportunidad"  style="display:inline-block;">
                        <s:hidden name="idPropiedad" value="%{#attr.row.idPropiedad}"/>
                    <button value="O" title="Oportunidad"  class="btn btn-success"><i class='fa fa-star'></i></button>
                    </s:form>
                    <s:submit type="button" data-id="%{#attr.row.idPropiedad}" title="Eliminar" theme="simple" cssClass="btn btn-danger" data-toggle="modal" data-target="#modal-delete"><i class='fa fa-trash'></i></s:submit>
            </display:column>
        </display:table>

    </div>
</div>
